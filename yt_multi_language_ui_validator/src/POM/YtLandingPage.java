package POM;

import java.time.Duration;   
import java.util.List;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import yt_multi_language_ui_validator.BasePage;
import yt_multi_language_ui_validator.BasicTest;
import yt_multi_language_ui_validator.LinguaHelper;
public class YtLandingPage  extends BasePage{
	
//	
	private  final Logger log = yt_multi_language_ui_validator.LoggerUtility.getLogger(YtLandingPage.class);
	
	public By sideMenuExpandedList=By.xpath("//ytd-guide-entry-renderer[@class='style-scope ytd-guide-section-renderer']");
	
	public By settingEllipsesButton=By.xpath("//ytd-topbar-menu-button-renderer[@class='style-scope ytd-masthead style-default']");
	
	public By languageDropdownUnderSettings=By.xpath("(//div[@id='content-icon'])[2]");
	
	public By languageList=By.xpath("//yt-multi-page-menu-section-renderer[@class='style-scope ytd-multi-page-menu-renderer']//ytd-compact-link-renderer");
	
	public By ellipsesYtLandingPageTopLeft=By.xpath("//ytd-topbar-logo-renderer[@id='logo']/preceding-sibling::*[@id='guide-button']");
	
	public By sideMenuCollapsedList=By.xpath("//ytd-mini-guide-renderer[@class='style-scope ytd-app']//ytd-mini-guide-entry-renderer");
	
	public By countryCodeOnYtLogoLandingPage=By.xpath("(//span[@id='country-code'])[1]");
	
	public By settingEllipsesOptionsListLandingPage=By.xpath("//ytd-multi-page-menu-renderer");
	
	public By languageListUpdated=By.xpath("//ytd-multi-page-menu-renderer[@slot='dropdown-content']");
	
	
	//Cannot use this directly as this banner wouldnt show if yt does not have a search histroy so to show these recommendation banner
	public By horizontalScrollBarLandingPage=By.xpath("//div[@id='scroll-container']");
	
	
	public By locationDropdownUnderSettings=By.xpath("(//div[@id='content-icon'])[4]");
	
	//Have to follow the same process of find the location list similar to language list where will have to remove some elements 
	public By locationList=By.xpath("//yt-multi-page-menu-section-renderer[@class='style-scope ytd-multi-page-menu-renderer']//ytd-compact-link-renderer");
	
	public By getLanguageElementByName(String Name) {
		
	    return  By.xpath("//yt-multi-page-menu-section-renderer[@class='style-scope ytd-multi-page-menu-renderer']//yt-formatted-string[@id='label' and text()='" + Name + "']");
	}

	

    private By langOptionLabel(String name) {
        return By.xpath("//yt-multi-page-menu-section-renderer[@class='style-scope ytd-multi-page-menu-renderer']" +
                        "//yt-formatted-string[@id='label' and normalize-space(text())='" + name + "']");
    }

    public void openingLandingPage() {
    	driver.get("https://www.youtube.com/");
    }
    
    public void clickingSettingEllipsesButton() throws InterruptedException {
   
    	driver.findElement(settingEllipsesButton).click();
    }
    
    public void clickingLanguageDropdownButton() throws InterruptedException {

    	driver.findElement(languageDropdownUnderSettings).click();
    	
    }
    
    public List<WebElement> gettingLanguageList() throws InterruptedException{
    
    		 List<WebElement> langList=driver.findElements(languageList);
    		 langList.removeIf(el -> el.getText().trim().isEmpty());
    		 return langList;
    }
    
    
    public void applyingAllLanguagesFromList() throws InterruptedException {
    	
    	
    	 List<WebElement> languageList = gettingLanguageList();
    	
    	
    	for(int j=1;j<languageList.size();j++) {
			
			languageList =	gettingLanguageList();
			String langText=languageList.get(j).getText();
			System.out.println(langText+"    "+j);
			
			
	     		WebElement test=driver.findElement(getLanguageElementByName(langText));
				wait.until(ExpectedConditions.elementToBeClickable(test));
				test.click();
				Thread.sleep(3000);
				List<WebElement> listOfSideMenu=driver.findElements(sideMenuExpandedList);
				StringBuilder sb=new StringBuilder();
				for(int i=0;i<listOfSideMenu.size();i++) {
					System.out.println(listOfSideMenu.get(i).getText());
					sb.append(listOfSideMenu.get(i).getText());
				}
				
				LinguaHelper.detectLanguage(sb.toString());
				Thread.sleep(2000);				
				driver.findElement(settingEllipsesButton).click();
				Thread.sleep(2000);
				driver.findElement(languageDropdownUnderSettings).click();
			}
		
	}
    
    
    
    
    
 }
	

