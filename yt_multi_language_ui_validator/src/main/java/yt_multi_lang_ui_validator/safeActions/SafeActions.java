package main.java.yt_multi_lang_ui_validator.safeActions;


import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import main.java.yt_multi_lang_ui_validator.base.BasePage;
import main.java.yt_multi_lang_ui_validator.config.ConfigManager;
import main.java.yt_multi_lang_ui_validator.logger.LoggerUtility;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

public class SafeActions{
   
	private  static final Logger log = LoggerUtility.getLogger(SafeActions.class);


	    private final WebDriver driver;
	    private final WebDriverWait wait;
	    

	    int timeoutSec;
	    int attempts;
	    
	    public SafeActions(WebDriver driver) {
	        this.driver = driver;
	        
//	         ConfigManager cfg = ConfigManager.getInstance();
	         timeoutSec = ConfigManager.getInt("explicit.wait", 10); // seconds
	         attempts = ConfigManager.getInt("element.retry.attempts", 2);
	        
	        this.wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSec));
	        log.info("SafeActions initialized with driver {}", driver);
	    }
	    
	    
	    
	    // ---------- CORE CHECKS ----------

	    private boolean isReady(WebElement element) {
	        try {
	            return element != null && element.isDisplayed() && element.isEnabled();
	        } catch (StaleElementReferenceException e) {
	            return false;
	        }
	    }

	    // ---------- SAFE FIND ELEMENT ----------

	    public WebElement safeFindElement(By locator) {
	        log.info("[{}] Trying to find element: {}", ThreadContext.get("testName"), locator);

	        for (int attempt = 1; attempt <= attempts; attempt++) {
	            try {
	                WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));

	                if (isReady(element)) {
	                    log.info("[{}] Element found and ready (attempt {}): {}", ThreadContext.get("testName"), attempt, locator);
	                    return element;
	                } else {
	                    log.warn("[{}] Element found but not ready (attempt {}): {}", ThreadContext.get("testName"), attempt, locator);
	                }
	            } catch (TimeoutException e) {
	                log.warn("[{}] Timeout finding element (attempt {}): {}", ThreadContext.get("testName"), attempt, locator);
	            } catch (Exception e) {
	                log.warn("[{}] Error while finding element (attempt {}): {} -> {}", ThreadContext.get("testName"), attempt, locator, e.toString());
	            }

	            refreshIfNeeded(attempt);
	        }

	        log.error("[{}] Element not found after {} attempts: {}", ThreadContext.get("testName"), attempts, locator);
	        return null;
	    }

	    // ---------- SAFE FIND ELEMENTS ----------

	    public List<WebElement> safeFindElements(By locator) {
	        log.info("[{}] Finding elements list: {}", ThreadContext.get("testName"), locator);

	        for (int attempt = 1; attempt <= attempts; attempt++) {
	            try {
	                List<WebElement> elements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));

	                if (!elements.isEmpty()) {
	                    elements.removeIf(el -> !isReady(el));
	                    if (!elements.isEmpty()) {
	                        log.info("[{}] Found {} ready elements (attempt {}): {}", ThreadContext.get("testName"), elements.size(), attempt, locator);
	                        return elements;
	                    }
	                }
	            } catch (Exception e) {
	                log.warn("[{}] Attempt {} failed to find elements: {} -> {}", ThreadContext.get("testName"), attempt, locator, e.toString());
	            }

	            refreshIfNeeded(attempt);
	        }

	        log.error("[{}] No elements ready after {} attempts: {}", ThreadContext.get("testName"), attempts, locator);
	        return List.of();
	    }

	    // ---------- SAFE CLICK ----------
//
//	    public boolean safeClick(By locator) {
//	        log.info("[{}] Trying to click element: {}", ThreadContext.get("testName"), locator);
//
//	        for (int attempt = 1; attempt <= attempts; attempt++) {
//	            try {
//	                WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
//
//	                if (isReady(element)) {
//	                    element.click();
//	                    log.info("[{}] Clicked successfully (attempt {}): {}", ThreadContext.get("testName"), attempt, locator);
//	                    return true;
//	                } else {
//	                    log.warn("[{}] Element not ready to click (attempt {}): {}", ThreadContext.get("testName"), attempt, locator);
//	                }
//
//	            } catch (ElementClickInterceptedException e) {
//	                log.warn("[{}] Element intercepted (attempt {}): {}", ThreadContext.get("testName"), attempt, locator);
//	            } catch (TimeoutException e) {
//	                log.warn("[{}] Timeout waiting for click (attempt {}): {}", ThreadContext.get("testName"), attempt, locator);
//	            } catch (WebDriverException e) {
//	                log.warn("[{}] WebDriver exception on click (attempt {}): {} -> {}", ThreadContext.get("testName"), attempt, locator, e.toString());
//	            }
//
//	            refreshIfNeeded(attempt);
//	        }
//
//	        log.error("[{}] Failed to click element after {} attempts: {}", ThreadContext.get("testName"), attempts, locator);
//	        return false;
//	    }

	    // ---------- HELPER ----------

//	    private void refreshIfNeeded(int attempt) {
//	        if (attempt < attempts) {
//	            try {
//	                driver.navigate().refresh();
//	                log.info("[{}] Refreshed page (attempt {})", ThreadContext.get("testName"), attempt);
//	                Thread.sleep(1000);
//	            } catch (InterruptedException ignored) {}
//	        }
//	    }
	    
	    
	    public boolean safeClick(By locator) {
	        log.info("[{}] Trying to click element: {}", ThreadContext.get("testName"), locator);

	        for (int attempt = 1; attempt <= attempts; attempt++) {
	            try {
	                // Wait for the element to be present, visible, and clickable
	                WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	                element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	                element = wait.until(ExpectedConditions.elementToBeClickable(locator));

	                if (isReady(element)) {
	                    try {
	                        // Try the normal native click first
	                        element.click();
	                        log.info("[{}] Clicked successfully (attempt {}): {}", ThreadContext.get("testName"), attempt, locator);
	                        Thread.sleep(250); // small buffer for SPA stability
	                        return true;

	                    } catch ( WebDriverException e) {
	                        // These include cases where browser driver times out internally
	                        log.warn("[{}] Native click failed (attempt {}): {} -> {}", 
	                                 ThreadContext.get("testName"), attempt, locator, e.toString());

	                        // Fallback to JS click (handles overlays / stalled drivers)
	                        try {
	                            log.info("[{}] Trying JS click fallback for: {}", ThreadContext.get("testName"), locator);
	                            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
	                            Thread.sleep(200);
	                            return true;
	                        } catch (Exception jsEx) {
	                            log.error("[{}] JS click fallback failed (attempt {}): {} -> {}", 
	                                      ThreadContext.get("testName"), attempt, locator, jsEx.toString());
	                        }
	                    }

	                } else {
	                    log.warn("[{}] Element not ready to click (attempt {}): {}", ThreadContext.get("testName"), attempt, locator);
	                }

	            } catch (StaleElementReferenceException stale) {
	                log.warn("[{}] Stale element detected (attempt {}): {}", ThreadContext.get("testName"), attempt, locator);
	            } catch (org.openqa.selenium.TimeoutException timeout) {
	                log.warn("[{}] Timeout waiting for element (attempt {}): {}", ThreadContext.get("testName"), attempt, locator);
	            } catch (Exception e) {
	                // This block also catches java.net.http.HttpTimeoutException indirectly
	                log.warn("[{}] Unexpected WebDriver exception (attempt {}): {} -> {}", 
	                         ThreadContext.get("testName"), attempt, locator, e.toString());
	            }

	            // Refresh the page only after multiple failed attempts
	            refreshIfNeeded(attempt);
	        }

	        log.error("[{}] Failed to click element after {} attempts: {}", ThreadContext.get("testName"), attempts, locator);
	        return false;
	    }

	    
	    
	    protected void refreshIfNeeded(int attempt) {
	        // refresh only after multiple failed attempts
	        if (attempt >= 2) {
	            try {
	                log.warn("[{}] Attempt {} failed — refreshing the page to recover...", 
	                         ThreadContext.get("testName"), attempt);

	                // print current URL to help debug which page failed
	                String currentUrl = driver.getCurrentUrl();
	                log.info("[{}] Current URL before refresh: {}", ThreadContext.get("testName"), currentUrl);

	                // refresh the page
	                driver.navigate().refresh();
	                log.info("[{}] Page refreshed successfully.", ThreadContext.get("testName"));

	                // wait briefly for the DOM to reload and stabilize
	                Thread.sleep(1500);

	                // optional: verify the page is ready again via JS readiness check
	                boolean pageReady = false;
	                try {
	                    WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(8));
	                    pageReady = shortWait.until(drv ->
	                        ((JavascriptExecutor) drv)
	                            .executeScript("return document.readyState")
	                            .toString()
	                            .equals("complete")
	                    );
	                } catch (Exception jsWaitEx) {
	                    log.warn("[{}] Page readiness check failed after refresh (non-fatal): {}", 
	                             ThreadContext.get("testName"), jsWaitEx.getMessage());
	                }

	                if (pageReady) {
	                    log.info("[{}] Page fully loaded after refresh.", ThreadContext.get("testName"));
	                } else {
	                    log.warn("[{}] Page not fully loaded even after refresh — proceeding with caution.", 
	                             ThreadContext.get("testName"));
	                }

	            } catch (InterruptedException ie) {
	                Thread.currentThread().interrupt();
	                log.error("[{}] Interrupted while waiting after refresh.", ThreadContext.get("testName"));
	            } catch (Exception e) {
	                log.error("[{}] Unexpected error during refresh (attempt {}): {}", 
	                          ThreadContext.get("testName"), attempt, e.toString());
	            }
	        }
	    }

	    
	    
	    
	    
	    
//	public void safeClick(By locator) {
//		log.info("[{}] Within safeClick method", ThreadContext.get("testName"));
//
//	    int attempts = 0;
//	    while (attempts < 2) {
//	        try {
//	        	
//	            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
//	            Thread.sleep(2000);
//	            element.click();
//	    		log.info("[{}] Clicked the  "+element+"   using safeClick", ThreadContext.get("testName"));
//	            System.out.println("Clicked using safeClick");
//	            return;
//	        } catch (TimeoutException |ElementClickInterceptedException | StaleElementReferenceException | InterruptedException e) {
//	            System.out.println("Retrying click for: " + locator + " - Attempt " + (attempts + 1));
//	    		log.info("[{}] Cannot click the butto using safeClick, trying again", ThreadContext.get("testName"));
//	            attempts++;
//	            try {
//	                driver.navigate().refresh();
//	                Thread.sleep(1000); // small delay before retry
//	            } catch (InterruptedException ignored) {}
//	        }
//	    }
//	    // After 3 attempts, skip the action without throwing exception
//		log.info("[{}] Skipping click action: Element not clickable after- "+attempts, ThreadContext.get("testName"));
//	    System.out.println("Skipping click action: Element not clickable after 3 attempts - " + locator);
//	}
//
//	
//	public List<WebElement> safeFindElements(By locator) {
//		log.info("[{}] Within safeFindElements ", ThreadContext.get("testName"));
//
//	    int attempts = 0;
//	    while (attempts < 2) {
//	        try {
//	            List<WebElement> elements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
//	    		log.info("[{}] Returning the  "+elements+" from the safeFindElements method", ThreadContext.get("testName"));
//
//	            System.out.println("Found the elements: " + locator);
//	            return elements;
//	        } catch (TimeoutException | NoSuchElementException | StaleElementReferenceException e) {
//	    		log.info("[{}] Retrying findElements for:"+locator, ThreadContext.get("testName"));
//	            System.out.println("Retrying findElements for: " + locator + " - Attempt " + (attempts + 1));
//	            attempts++;
//	            try {
//	                driver.navigate().refresh();
//		    		log.info("[{}] Refreshing the page while trying to find :"+locator, ThreadContext.get("testName"));
//
//	                System.out.println("Refreshing the page in safeFindElements Method");
//	                Thread.sleep(1000);
//	            } catch (InterruptedException ignored) {}
//	        }
//	    }
//	    // After 3 attempts, return null instead of throwing exception
//	    System.out.println("Skipping action: Elements not found after 3 attempts - " + locator);
//		log.info("[{}] Skipping action Elements not found after"+attempts+"  Attempts", ThreadContext.get("testName"));
//
//	    return null;
//	}
//
//		
//		
//		public WebElement safeFindElement(By locator) {
//			log.info("[{}] Within safeFindElement method", ThreadContext.get("testName"));
//
//		    int attempts = 0;
//		    while (attempts < 2) {
//		        try {
//		        	
//		            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
//					log.info("[{}]Found the element returning it ,element ->"+element, ThreadContext.get("testName"));
//		            System.out.println("Found the element: " + locator);
//		            return element;
//		        } catch (Exception e) {
//					log.info("[{}]Retrying findElement for: ->"+locator, ThreadContext.get("testName"));
//
//		            System.out.println("Retrying findElement for: " + locator + " - Attempt " + (attempts + 1));
//		            attempts++;
//		            try {
//		                driver.navigate().refresh();
//						log.info("[{}]Refrehsing the page , while trying to safely find : ->"+locator, ThreadContext.get("testName"));
//		                System.out.println("Refreshing the page in safeFindElement Method");
//		                Thread.sleep(1000);
//		            } catch (InterruptedException ignored) {}
//		        }
//		    }
//
//
//			log.info("[{}] Skipping action: Element not found after "+attempts+"   attemps", ThreadContext.get("testName"));
//
//		    System.out.println("Skipping action: Element not found after 3 attempts - " + locator);
//		    return null;
//		}
		
		
		
		
		
		
		
		
		
//		public boolean safeClickBoolean(By locator) throws InterruptedException {
//			log.info("[{}] Within safeClickBoolean method", ThreadContext.get("testName"));
//
//				GenericUtility genericUtility=new GenericUtility();
//				
//								
//				//ScreenshotUtil screenUtil=new ScreenshotUtil();
//				String testName = ThreadContext.get("logFileName");
//				
//		    int attempts = 0;
//		    while (attempts < 2) {
//		        try {
//					log.info("[{}] Within safeClickBoolean method Try block after the scroll line", ThreadContext.get("testName"));
//		            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
//		            System.out.println(element+"  printing the element address from the safeBooleanClick from safeActions");
//		            element.click();
//		            Thread.sleep(2000);
//		            genericUtility.smoothScrollToElement(productPage.getfilterByTypeAndName(testName, testName));
//		        	Thread.sleep(2000);
//					log.info("[{}] Clicked the element using safe click ,element is "+element ,ThreadContext.get("testName"));
//		            System.out.println("Clicking using safeClick");
//		            
//		          //  ScreenshotUtil.capture(testName);
//		            return true; // success
//		        } catch (TimeoutException | ElementClickInterceptedException | StaleElementReferenceException e) {
//		            System.out.println("Retrying click for: " + locator + " - Attempt " + (attempts + 1));
//					log.info("[{}] Cannot click the button, element ->"+locator ,ThreadContext.get("testName"));
//					
//				
//		            attempts++;
//		            try {
//		                driver.navigate().refresh();
//						log.info("[{}] Refrshing the page , while trying to click ->"+locator ,ThreadContext.get("testName"));
//		                Thread.sleep(1000);
//		            } catch (InterruptedException ignored) {}
//		        }
//		    }
//			log.info("[{}]Skipping click action: Element not clickable after"+attempts+"   attempts" ,ThreadContext.get("testName"));
//
//		    System.out.println("Skipping click action: Element not clickable after 3 attempts - " + locator);
//		    return false; // failure
//		}
		
		
		
		
//		public boolean safeClickBooleanWithScreenShot(By locator,String filterName,String filterOption) throws InterruptedException {
//			    log.info("[{}] Within safeClickBooleanWithScreenShot method", ThreadContext.get("testName"));
//
//				GenericUtility genericUtility=new GenericUtility();
//				SafeActions safeAct=new SafeActions();
//				ProductListingPage productPage=new ProductListingPage();
//				String testName = ThreadContext.get("logFileName");
//				String filterOptionToPass=filterOption;
//				
////		    	String os="verifyingOperatingSystemVersionFilterFunctionality";
////            	String brands ="verifyingTheBrandsFilterFunctionality";
//		    int attempts = 0;
//		    while (attempts < 1) {
//		        try {
//                	genericUtility.smoothScrollToElement(productPage.getfilterHeaderByTypeAndName(filterName));
//                	
//
//            
//                	if(testName.equals(filterName)) {
//                		if(genericUtility.isElementInViewport(productPage.getMoreButtonByFilterTypeAndName(filterName))) {
//                    		safeAct.safeClick(productPage.getMoreButtonByFilterTypeAndName(filterName));
//                    	}
//                	}
//                	
//		            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
//		            System.out.println(element+"  printing the element address from the safeBooleanClick from safeActions");
//		            element.click();
//		            Thread.sleep(1000);
//		            
//		            genericUtility.smoothScrollToElement(productPage.getfilterHeaderByTypeAndName(filterName));
//		            Thread.sleep(1000);             
//
//		            
//		            if(testName.equals(filterName)) {
//                		if(genericUtility.isElementInViewport(productPage.getMoreButtonByFilterTypeAndName(filterName))) {
//                    		safeAct.safeClick(productPage.getMoreButtonByFilterTypeAndName(filterName));
//                    	}
//                	}
//		            Thread.sleep(1000);
//                	
//		            genericUtility.smoothScrollToElement(productPage.getAppliedfilterByTypeAndName(filterName, filterOption));
//		        	Thread.sleep(1000);
//					log.info("[{}] Clicked the element using safe click ,element is "+element ,ThreadContext.get("testName"));
//		            System.out.println("Clicking using safeClick");  
//		            ScreenshotUtil.capture(testName,filterOptionToPass);
//		            return true; // success
//		        } catch (TimeoutException | ElementClickInterceptedException | StaleElementReferenceException e) {
//		            System.out.println("Retrying click for: " + locator + " - Attempt " + (attempts + 1));
//					log.info("[{}] Cannot click the button, element ->"+locator ,ThreadContext.get("testName"));
//		            attempts++;
//		            
//		            try {
//		            	
//		                   driver.navigate().refresh();
//		                	Thread.sleep(1000);		
//		                	log.info("[{}] Cannot apply filter taking failed screenshot for ->"+filterOption ,ThreadContext.get("testName"));
//		                	genericUtility.smoothScrollToElement(productPage.getfilterHeaderByTypeAndName(filterName));
//		                	Thread.sleep(2000);
//
//		                	
//		                	if(testName.equals(filterName)) {
//		                		if(genericUtility.isElementInViewport(productPage.getMoreButtonByFilterTypeAndName(filterName))) {
//		                    		safeAct.safeClick(productPage.getMoreButtonByFilterTypeAndName(filterName));
//		                    	}
//		                	}
//		                	
//		                	Thread.sleep(1000);
//				            ScreenshotUtil.capture(testName,filterOptionToPass,"failed filter apply screenshot-");
//		                
//		                
//						log.info("[{}] Refrshing the page , while trying to click ->"+locator ,ThreadContext.get("testName"));
//		                Thread.sleep(1000);
//		            } catch (InterruptedException ignored) {}
//		        }
//		    }
//			log.info("[{}]Skipping click action: Element not clickable after"+attempts+"   attempts" ,ThreadContext.get("testName"));
//
//		    System.out.println("Skipping click action: Element not clickable after 3 attempts - " + locator);
//		    return false; // failure
//		}
		
		
		
		

}


