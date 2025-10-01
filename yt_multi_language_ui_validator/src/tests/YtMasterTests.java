package tests;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import POM.YtLandingPage;
import yt_multi_language_ui_validator.BaseTest;
import yt_multi_language_ui_validator.LinguaHelper;

public class YtMasterTests extends BaseTest{

	
	@Test
	public void verifyingSideMenuLanguageAsInSettings() throws InterruptedException {

		YtLandingPage yt=new YtLandingPage();
		yt.openingLandingPage();
		yt.clickingSettingEllipsesButton();
		Thread.sleep(2000);
		yt.clickingLanguageDropdownButton();
	    Thread.sleep(2000);
	    	
	    SoftAssert softAssert = new SoftAssert();
        List<String> languageList = yt.applyLanguagesFromInternalDataset();
    	
    	for(int j=1;j<languageList.size();j++) {
			
			String langText=languageList.get(j);
			System.out.println(langText+"    "+j);
			
	     	yt.getLanguageElementByName(langText).click();	
			Thread.sleep(2000);
			List<WebElement> listOfSideMenu=yt.gettingSideMenuExpandedList();
			
				StringBuilder sb=new StringBuilder();
				for(int i=0;i<listOfSideMenu.size();i++) {
					System.out.println(listOfSideMenu.get(i).getText());
					sb.append(listOfSideMenu.get(i).getText());
				}
				
				String applicableLanguage=languageList.get(j).toLowerCase();
				String detectedLanguage=LinguaHelper.detectLanguage(sb.toString()).toLowerCase();
			
				System.out.println("Applicable language  "+applicableLanguage+"     "+"Detected Language   "+detectedLanguage);
				
				
			    softAssert.assertEquals(detectedLanguage, applicableLanguage, "Page title mismatch");
			    
				Thread.sleep(2000);				
				driver.findElement(yt.settingEllipsesButton).click();
				Thread.sleep(2000);
				driver.findElement(yt.languageDropdownUnderSettings).click();
			}
    	softAssert.assertAll();
    	
		
	}
	
	

	

	
	
}
