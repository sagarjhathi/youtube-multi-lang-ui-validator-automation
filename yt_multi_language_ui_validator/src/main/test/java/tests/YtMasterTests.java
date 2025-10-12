package main.test.java.tests;

import java.time.Duration; 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import main.test.java.retry.*;
import main.java.yt_multi_lang_ui_validator.base.BaseTest;
import main.java.yt_multi_lang_ui_validator.lingua.LinguaHelper;
import main.java.yt_multi_lang_ui_validator.logger.LoggerUtility;
import main.java.yt_multi_lang_ui_validator.pages.YtLandingPage;
import main.java.yt_multi_lang_ui_validator.utilities.GenericUtility;



public class YtMasterTests extends BaseTest{
	 private static final  Logger log=LoggerUtility.getLogger(YtMasterTests.class);
	
	@Test(retryAnalyzer = RetryFailedTest.class)
	public void verifyingSideMenuLanguageAsInSettings() throws InterruptedException {

		YtLandingPage yt=new YtLandingPage();
		GenericUtility g=new GenericUtility();
		yt.openingLandingPage();
		yt.clickingSettingEllipsesButton();
		Thread.sleep(2000);
		yt.clickingLanguageDropdownButton();
	    Thread.sleep(2000);
	    		    
	    SoftAssert softAssert = new SoftAssert();
        List<String> languageList = yt.applyLanguagesFromInternalDataset();
    	
    	for(int j=0;j<languageList.size();j++) {
			
			String langText=languageList.get(j);
			System.out.println(langText+"    "+j);
			
			 g.ensurePageLoadedOrRefresh();
	     	yt.getLanguageElementByName(langText).click();	
	     	 g.ensurePageLoadedOrRefresh();
			Thread.sleep(2000);
			List<WebElement> listOfSideMenu=yt.gettingSideMenuExpandedList();
			
				StringBuilder sb=new StringBuilder();
				for(int i=0;i<listOfSideMenu.size();i++) {
					System.out.println(listOfSideMenu.get(i).getText());
					sb.append(listOfSideMenu.get(i).getText());
					sb.append(" ");
				}
				
				String applicableLanguage=languageList.get(j);
				String detectedLanguage=LinguaHelper.detectLanguage(sb.toString());
				String expectedLanguage=g.getExpectedLangageViaApplicableLangInput(applicableLanguage);
			
				String expectedLanguageAttribute=g.getLangAttributeViaLanguageInput(applicableLanguage);
				String detectedLanguageAttribute=g.getLangAttribute();
				
				System.out.println("ApplicableLanguage  "+applicableLanguage+"     "+"Detected Language   "+detectedLanguage+"  "+"Expected Language  "+expectedLanguage);
				System.out.println("ApplicableLanguage  "+applicableLanguage+"     "+"Detected Language attribute   "+detectedLanguageAttribute+"  "+"Expected Language attribute "+expectedLanguageAttribute);

			    softAssert.assertEquals(detectedLanguage, expectedLanguage, "Page title mismatch");
			    softAssert.assertEquals(detectedLanguageAttribute, expectedLanguageAttribute, "Language attributr mismatch");

			    
			    yt.clickingSettingEllipsesButton();
				Thread.sleep(2000);
				yt.clickingLanguageDropdownButton();
			    Thread.sleep(2000);
			    g.ensurePageLoadedOrRefresh();
			}
    	     softAssert.assertAll();
	}
	

	
	@Test(retryAnalyzer = RetryFailedTest.class)
	public void verifyingSideMenuCollapsedLangAsInSettings() throws InterruptedException {
		YtLandingPage yt=new YtLandingPage();
		GenericUtility gn=new GenericUtility();
		yt.openingLandingPage();
		yt.clickingLeftEllipses();
		Thread.sleep(1000);
		yt.clickingSettingEllipsesButton();
		Thread.sleep(2000);
		yt.clickingLanguageDropdownButton();
        Thread.sleep(2000);
        
        
		    SoftAssert softAssert = new SoftAssert();
	        List<String> languageList = yt.applyLanguagesFromInternalDataset();
	    	
	    	for(int j=0;j<languageList.size();j++) {
				
				String langText=languageList.get(j);
				System.out.println(langText+"    "+j);
				
				 gn.ensurePageLoadedOrRefresh();
		     	yt.getLanguageElementByName(langText).click();	
		     	 gn.ensurePageLoadedOrRefresh();
		     	gn.getLangAttribute();
				Thread.sleep(2000);
				List<WebElement> listOfSideMenu=yt.gettingSideMenuCollapsedList();
				
					StringBuilder sb=new StringBuilder();
					for(int i=0;i<listOfSideMenu.size();i++) {
						System.out.println(listOfSideMenu.get(i).getText());
						sb.append(listOfSideMenu.get(i).getText());
						sb.append(" ");
					}
					
					String applicableLanguage=languageList.get(j);
					String detectedLanguage=LinguaHelper.detectLanguage(sb.toString());
					String expectedLanguage=gn.getExpectedLangageViaApplicableLangInput(applicableLanguage);

				
					String expectedLanguageAttribute=gn.getLangAttributeViaLanguageInput(applicableLanguage);
					String detectedLanguageAttribute=gn.getLangAttribute();
					
					System.out.println("ApplicableLanguage  "+applicableLanguage+"     "+"Detected Language   "+detectedLanguage+"  "+"Expected Language  "+expectedLanguage);
					System.out.println("ApplicableLanguage  "+applicableLanguage+"     "+"Detected Language attribute   "+detectedLanguageAttribute+"  "+"Expected Language attribute "+expectedLanguageAttribute);

				    softAssert.assertEquals(detectedLanguage, expectedLanguage, "Language detection mismatch");
				    softAssert.assertEquals(detectedLanguageAttribute, expectedLanguageAttribute, "Language attributr mismatch");
				    
				    
				    yt.clickingSettingEllipsesButton();
					Thread.sleep(2000);
					yt.clickingLanguageDropdownButton();
				    Thread.sleep(2000);
				    gn.ensurePageLoadedOrRefresh();
				}
	    	
	    	    softAssert.assertAll();	
	}
	
	
	@Test(retryAnalyzer = RetryFailedTest.class)
	public void verifyingSettingOptionsLang() throws InterruptedException {
		YtLandingPage yt=new YtLandingPage();
		GenericUtility gn=new GenericUtility();
		yt.openingLandingPage();
		yt.clickingSettingEllipsesButton();
		Thread.sleep(1000);
		yt.clickingLanguageDropdownButton();
		Thread.sleep(1000);
		
		SoftAssert softAssert=new SoftAssert();
		
		List<String> languageList=yt.applyLanguagesFromInternalDataset();
		
		for(int j=0;j<languageList.size();j++) {
			
			String langText=languageList.get(j);
			System.out.println(langText+"    "+j);
			
			 gn.ensurePageLoadedOrRefresh();
	     	yt.getLanguageElementByName(langText).click();	
	     	 gn.ensurePageLoadedOrRefresh();
			Thread.sleep(2000);
			
			yt.clickingSettingEllipsesButton();
			Thread.sleep(1000);
			String sb=yt.getsettingEllipsesOptionsListLandingPage();
			yt.clickingSettingEllipsesButton();
		
				
				String applicableLanguage=languageList.get(j);
				String detectedLanguage=LinguaHelper.detectLanguage(sb.toString());
				String expectedLanguage=gn.getExpectedLangageViaApplicableLangInput(applicableLanguage);

			
				String expectedLanguageAttribute=gn.getLangAttributeViaLanguageInput(applicableLanguage);
				String detectedLanguageAttribute=gn.getLangAttribute();
				
				System.out.println("ApplicableLanguage  "+applicableLanguage+"     "+"Detected Language   "+detectedLanguage+"  "+"Expected Language  "+expectedLanguage);
				System.out.println("ApplicableLanguage  "+applicableLanguage+"     "+"Detected Language attribute   "+detectedLanguageAttribute+"  "+"Expected Language attribute "+expectedLanguageAttribute);

			    softAssert.assertEquals(detectedLanguage, expectedLanguage, "Language detection mismatch");
			    softAssert.assertEquals(detectedLanguageAttribute, expectedLanguageAttribute, "Language attributr mismatch");
			    
			    
			    yt.clickingSettingEllipsesButton();
				Thread.sleep(2000);
				yt.clickingLanguageDropdownButton();
			    Thread.sleep(2000);
			   gn.ensurePageLoadedOrRefresh();
			}
    	
    	    softAssert.assertAll();	
	}
	
	
	
	
	@Test(retryAnalyzer = RetryFailedTest.class)
	public void verifyCountryCodeAsBasedOnRegion() throws InterruptedException {
		YtLandingPage yt=new YtLandingPage();
		GenericUtility gn=new GenericUtility();
		yt.openingLandingPage();
		Thread.sleep(3000);
		yt.clickingSettingEllipsesButton();
		Thread.sleep(2000);
		yt.clickingLocationDropdownUnderSettings();
		Thread.sleep(2000);
		
		SoftAssert softAssert=new SoftAssert();
		List<WebElement> locationList=yt.getLocationList();
		
		for(int i=1;i<locationList.size();i++) {
			locationList=yt.getLocationList();
			String locationText=locationList.get(i).getText();
			System.out.println(locationText+"    "+i);

			 gn.ensurePageLoadedOrRefresh();
			 locationList.get(i).click();
			 gn.ensurePageLoadedOrRefresh();
			
			String applicableLocation=locationText;
			String expectedCountryCode=yt.getExpectedCountryCodeViaLocation(locationText);
			String detectedCountryCode=yt.getCountryCode();
			
			System.out.println("applicable location is ==  "+applicableLocation+" Expected location  =="+expectedCountryCode+"  detected country code"+detectedCountryCode);
			softAssert.assertEquals(detectedCountryCode, expectedCountryCode,"Country code mismacth");
			
				Thread.sleep(2000);
		    	yt.clickingSettingEllipsesButton();
				Thread.sleep(2000);
				yt.clickingLocationDropdownUnderSettings();
			    Thread.sleep(2000);
			    gn.ensurePageLoadedOrRefresh();
			
			
		}
		softAssert.assertAll();
	}

	
	
	@Test(retryAnalyzer = RetryFailedTest.class)
	public void verifyingGlobalFilterLandingPage() throws InterruptedException {
		GenericUtility gn=new GenericUtility();
		
		YtLandingPage yt=new YtLandingPage();
		yt.openingLandingPage();
		yt.givingInputUnderSearchBar("video");
		gn.clickEnter(yt.searchInputLandinfPage);
		
		List<String> languageList=yt.applyLanguagesFromInternalDataset();
		SoftAssert softAssert =new SoftAssert();
		
		
		
         for(int j=0;j<languageList.size();j++) {
        	 yt.clickingSettingEllipsesButton();
				Thread.sleep(2000);
				yt.clickingLanguageDropdownButton();
			    Thread.sleep(2000);
			   gn.ensurePageLoadedOrRefresh();
			   
			String langText=languageList.get(j);
			System.out.println(langText+"    "+j);
			
			 gn.ensurePageLoadedOrRefresh();
	     	yt.getLanguageElementByName(langText).click();	
	     	 gn.ensurePageLoadedOrRefresh();
			Thread.sleep(2000);
			
			yt.clickingGlobalFilterButton();
			String str=yt.getDataFromGlobalFilterPopup();
			yt.closeGlobalFilterPopup();
				
				String applicableLanguage=languageList.get(j);
				String detectedLanguage=LinguaHelper.detectLanguage(str);
				String expectedLanguage=gn.getExpectedLangageViaApplicableLangInput(applicableLanguage);

			
				String expectedLanguageAttribute=gn.getLangAttributeViaLanguageInput(applicableLanguage);
				String detectedLanguageAttribute=gn.getLangAttribute();
				
				System.out.println("ApplicableLanguage  "+applicableLanguage+"     "+"Detected Language   "+detectedLanguage+"  "+"Expected Language  "+expectedLanguage);
				System.out.println("ApplicableLanguage  "+applicableLanguage+"     "+"Detected Language attribute   "+detectedLanguageAttribute+"  "+"Expected Language attribute "+expectedLanguageAttribute);

			    softAssert.assertEquals(detectedLanguage, expectedLanguage, "Language detection mismatch");
			    softAssert.assertEquals(detectedLanguageAttribute, expectedLanguageAttribute, "Language attributr mismatch");
			    
			    
			   
			}
    	
    	    softAssert.assertAll();	
		
		yt.clickingGlobalFilterButton();
		String str=yt.getDataFromGlobalFilterPopup();
		System.out.println(str);
		yt.closeGlobalFilterPopup();
		
	}
	
	
	
	
	
	
	
	
	
}
