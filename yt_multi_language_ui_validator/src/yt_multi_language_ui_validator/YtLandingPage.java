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
	
	
	public void getLanguageList() {
//		Here because languageList gives 91 as output but 9 elements are empty and not visible on ui in youtube
//		so to ignore those will have to process by removing the empty elemets within the list will have to cover that part.
//		
		
		//languageList.removeIf(el -> el.getText().trim().isEmpty());
	}
}
