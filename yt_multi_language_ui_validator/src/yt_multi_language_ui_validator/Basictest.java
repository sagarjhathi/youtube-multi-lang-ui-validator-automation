package yt_multi_language_ui_validator;

import java.time.Duration; 
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
 
public class BasicTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		System.out.println("hey");
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.youtube.com/");
		
		List<WebElement> listOfSideMenu=driver.findElements(By.xpath("//ytd-guide-entry-renderer[@class='style-scope ytd-guide-section-renderer']"));
		
		for(int i=0;i<listOfSideMenu.size();i++) {
			System.out.println(listOfSideMenu.get(i).getText());
		
//				WebElement test=listOfSideMenu.get(i);
//				WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
//				wait.until(ExpectedConditions.elementToBeClickable(test));
//				test.click();
			
		}
		
		// ellipses three dot click
		driver.findElement(By.xpath("//ytd-topbar-menu-button-renderer[@class='style-scope ytd-masthead style-default']")).click();
		Thread.sleep(3000);
		//language dropdown click
		driver.findElement(By.xpath("//div[@id='primary-text-container']//yt-formatted-string[@id='label' and text()='Language:']")).click();
		Thread.sleep(2000);
		//Language list 
		List<WebElement> languageList=	driver.findElements(By.xpath("//yt-multi-page-menu-section-renderer[@class='style-scope ytd-multi-page-menu-renderer']//ytd-compact-link-renderer"));
		languageList.removeIf(el -> el.getText().trim().isEmpty());
		
	    //	driver.findElement(By.xpath("//yt-multi-page-menu-section-renderer[@class='style-scope ytd-multi-page-menu-renderer']//yt-formatted-string[@id='label' and text()='Afrikaans']")).click();
		for(int j=1;j<languageList.size();j++) {
			
		
			languageList=	driver.findElements(By.xpath("//yt-multi-page-menu-section-renderer[@class='style-scope ytd-multi-page-menu-renderer']//ytd-compact-link-renderer"));
			languageList.removeIf(el -> el.getText().trim().isEmpty());

			String langText=languageList.get(j).getText();
			System.out.println(langText+"    "+j);
			
			
			    WebElement test=driver.findElement(By.xpath("//yt-multi-page-menu-section-renderer[@class='style-scope ytd-multi-page-menu-renderer']//yt-formatted-string[@id='label' and text()='" + langText + "']"));
				WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
				wait.until(ExpectedConditions.elementToBeClickable(test));
				test.click();
				Thread.sleep(2000);
			//	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//ytd-topbar-menu-button-renderer[@class='style-scope ytd-masthead style-default']")));
				
				driver.findElement(By.xpath("//ytd-topbar-menu-button-renderer[@class='style-scope ytd-masthead style-default']")).click();
			//	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@id='content-icon'])[2]")));
				Thread.sleep(2000);

				driver.findElement(By.xpath("(//div[@id='content-icon'])[2]")).click();
			
				
			}
			
		
		
		
	}
	
	
	
	 public static void waitForPageOrRefresh(WebDriver driver, int attempts, long sleepMillis) {
	        JavascriptExecutor js = (JavascriptExecutor) driver;
	        int tries = 0;

	        while (tries < attempts) {
	            String state = js.executeScript("return document.readyState").toString();
	            if ("complete".equals(state)) {
	                System.out.println("Page loaded!");
	                return;
	            }
	            try { Thread.sleep(sleepMillis); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
	            tries++;
	        }

	        // refresh once if not loaded
	        System.out.println("Page not loaded after " + (attempts * sleepMillis / 1000) + "s. Refreshing once...");
	        driver.navigate().refresh();
	    }
	
}
