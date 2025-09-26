package yt_multi_language_ui_validator;

import java.time.Duration; 
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
public class YtLandingPage  extends BasicTest{
	
	By sideMenuExpandedList=By.xpath("//ytd-guide-entry-renderer[@class='style-scope ytd-guide-section-renderer']");
	
	By settingEllipsesButton=By.xpath("//ytd-topbar-menu-button-renderer[@class='style-scope ytd-masthead style-default']");
	
	By languageDropdownUnderSettings=By.xpath("(//div[@id='content-icon'])[2]");
	
	By languageList=By.xpath("//yt-multi-page-menu-section-renderer[@class='style-scope ytd-multi-page-menu-renderer']//ytd-compact-link-renderer");
	
	By ellipsesYtLandingPageTopLeft=By.xpath("//ytd-topbar-logo-renderer[@id='logo']/preceding-sibling::*[@id='guide-button']");
	
	By sideMenuCollapsedList=By.xpath("//ytd-mini-guide-renderer[@class='style-scope ytd-app']//ytd-mini-guide-entry-renderer");
	
	By countryCodeOnYtLogoLandingPage=By.xpath("(//span[@id='country-code'])[1]");
	
	By settingEllipsesOptionsListLandingPage=By.xpath("//ytd-multi-page-menu-renderer");
	
	By languageListUpdated=By.xpath("//ytd-multi-page-menu-renderer[@slot='dropdown-content']");
	
	
	//Cannot use this directly as this banner wouldnt show if yt does not have a search histroy so to show these recommendation banner
	By horizontalScrollBarLandingPage=By.xpath("//div[@id='scroll-container']");
	
	
	
	public By getLanguageElementByName(String Name) {
		
	    return  By.xpath("//yt-multi-page-menu-section-renderer[@class='style-scope ytd-multi-page-menu-renderer']//yt-formatted-string[@id='label' and text()='" + Name + "']");
	}
	
}
