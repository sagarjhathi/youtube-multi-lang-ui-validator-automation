package main.test.java.tests;

import java.io.IOException;
import java.util.List;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import main.test.java.retry.*;
import main.java.yt_multi_lang_ui_validator.base.BaseTest;
import main.java.yt_multi_lang_ui_validator.fileReader.FileReader;
import main.java.yt_multi_lang_ui_validator.lingua.LinguaHelper;
import main.java.yt_multi_lang_ui_validator.logger.LoggerUtility;
import main.java.yt_multi_lang_ui_validator.pages.YtLandingPage;
import main.java.yt_multi_lang_ui_validator.utilities.GenericUtility;
import main.java.yt_multi_lang_ui_validator.utilities.ScreenshotUtil;



public class YtMasterTests extends BaseTest{
	
	private static final  Logger log=LoggerUtility.getLogger(YtMasterTests.class);
	
	@Test(retryAnalyzer = RetryFailedTest.class)
	public void verifyingSideMenuLanguageAsInSettings() throws InterruptedException {

		
		FileReader reader=new FileReader();
		YtLandingPage yt=new YtLandingPage();
		GenericUtility g=new GenericUtility();
		SoftAssert softAssert = new SoftAssert();
		
		
		reader.loadWorkbook("data/LanguagesList.xlsx");
		reader.loadSheet("LanguagesList");
		
		int LanguagesRowCount= reader.getRowCount();

		
		yt.openingLandingPage();
		yt.clickingSettingEllipsesButton();
		Thread.sleep(2000);
		yt.clickingLanguageDropdownButton();
		Thread.sleep(2000);
		String testName = ThreadContext.get("logFileName");

		
		


		for(int j=1;j<LanguagesRowCount;j++) {

			String langText=reader.getCellValue(j, 0);

			System.out.println(langText+" lang text from the sheet   "+j);
			
			ScreenshotUtil.capture(testName, langText);
			Thread.sleep(1000);
			yt.getLanguageElementByName(langText).click();	

			Thread.sleep(2000);
			List<WebElement> listOfSideMenu=yt.gettingSideMenuExpandedList();

			StringBuilder sb=new StringBuilder();
			for(int i=0;i<listOfSideMenu.size();i++) {
				System.out.println(listOfSideMenu.get(i).getText());
				sb.append(listOfSideMenu.get(i).getText());
				sb.append(" ");
			}

			String applicableLanguage=reader.getCellValue(j, 0);
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

		}
		softAssert.assertAll();
	}
	

	
	@Test(retryAnalyzer = RetryFailedTest.class)
	public void verifyingSideMenuCollapsedLangAsInSettings() throws InterruptedException, InvalidFormatException, IOException {
		
		FileReader reader=new FileReader();
		YtLandingPage yt=new YtLandingPage();
		GenericUtility gn=new GenericUtility();
		SoftAssert softAssert = new SoftAssert();
		
		
		reader.loadWorkbook("data/LanguagesList.xlsx");
		reader.loadSheet("LanguagesList");
		int LanguagesRowCount= reader.getRowCount();


		FileReader applicableExpectedReader=new FileReader();
		applicableExpectedReader.loadWorkbook("data/ApplicableLanguageExpectedLanguage.xlsx");
		applicableExpectedReader.loadSheet("AppVExpectLanguages");


		
		String testName = ThreadContext.get("logFileName");
		gn.maximizeDisplay();
		yt.openingLandingPage();

		
		List<Integer> sizes=gn.getWindowHeightWidth();
		if(sizes.get(1)==1552) {
			yt.clickingLeftEllipses();
			Thread.sleep(2000);
		}


		yt.clickingSettingEllipsesButton();
		Thread.sleep(2000);

		yt.clickingLanguageDropdownButton();
		Thread.sleep(2000);

		

		for(int j=1;j<LanguagesRowCount;j++) {

			String langText=reader.getCellValue(j, 0);
			System.out.println(langText+"    "+j);
			Thread.sleep(1000);
		
			yt.getLanguageElementByName(langText).click();	
			gn.getLangAttribute();
			Thread.sleep(2000);
			List<WebElement> listOfSideMenu=yt.gettingSideMenuCollapsedList();

			StringBuilder sb=new StringBuilder();
			for(int i=0;i<listOfSideMenu.size();i++) {
				System.out.println(listOfSideMenu.get(i).getText());
				sb.append(listOfSideMenu.get(i).getText());
				sb.append(" ");
			}

			String applicableLanguage=reader.getCellValue(j, 0);
			String detectedLanguage=LinguaHelper.detectLanguage(sb.toString());
			log.info("text sent to lingua is "+"      "+sb.toString());
			String expectedLanguage=applicableExpectedReader.getCellValue(j, 1);


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
		}

		softAssert.assertAll();	
	}
	
	
	@Test(retryAnalyzer = RetryFailedTest.class)
	public void verifyingSettingOptionsLang() throws InterruptedException {
		
		
		FileReader reader=new FileReader();
		YtLandingPage yt=new YtLandingPage();
		GenericUtility gn=new GenericUtility();
		SoftAssert softAssert=new SoftAssert();
		
		
		reader.loadWorkbook("data/LanguagesList.xlsx");
		reader.loadSheet("LanguagesList");
		int LanguagesRowCount= reader.getRowCount();


		
		yt.openingLandingPage();
		yt.clickingSettingEllipsesButton();
		Thread.sleep(1000);
		yt.clickingLanguageDropdownButton();
		Thread.sleep(1000);

		

		for(int j=1;j<LanguagesRowCount;j++) {

			String langText=reader.getCellValue(j, 0);
			System.out.println(langText+"    "+j);

			Thread.sleep(1000);
			yt.getLanguageElementByName(langText).click();	

			Thread.sleep(2000);

			yt.clickingSettingEllipsesButton();
			Thread.sleep(1000);
			String sb=yt.getsettingEllipsesOptionsListLandingPage();
			yt.clickingSettingEllipsesButton();


			String applicableLanguage=reader.getCellValue(j, 0);
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

		}

		softAssert.assertAll();	
	}
	
	
	
	
	@Test(retryAnalyzer = RetryFailedTest.class)
	public void verifyCountryCodeAsBasedOnRegion() throws InterruptedException {
		
		
		FileReader reader=new FileReader();
		YtLandingPage yt=new YtLandingPage();
		GenericUtility gn=new GenericUtility();
		SoftAssert softAssert=new SoftAssert();
		
		
		reader.loadWorkbook("data/Country_Name_Code.xlsx");
		reader.loadSheet("CountryNameCode");


		
		yt.openingLandingPage();
		Thread.sleep(2000);
		yt.clickingSettingEllipsesButton();
		Thread.sleep(2000);
		yt.clickingLocationDropdownUnderSettings();
		Thread.sleep(2000);

		
		List<WebElement> locationList=yt.getLocationList();

		for(int i=1;i<locationList.size();i++) {
			locationList=yt.getLocationList();
			String locationText=locationList.get(i).getText();
			System.out.println(locationText+"    "+i);

			String locationTextFromSheet=reader.getCellValue(i, 0);
			System.out.println(locationTextFromSheet+"  location text from sheet  "+i);


			Thread.sleep(1000);
			locationList.get(i).click();
			Thread.sleep(1000);


			String applicableLocation=locationText;
			String expectedCountryCode=reader.getCellValue(i, 1);
			String detectedCountryCode=yt.getCountryCode();

			System.out.println("applicable location is ==  "+applicableLocation+" Expected location  =="+expectedCountryCode+"  detected country code"+detectedCountryCode);
			softAssert.assertEquals(detectedCountryCode, expectedCountryCode,"Country code mismacth");

			yt.clickingSettingEllipsesButton();
			Thread.sleep(2000);
			yt.clickingLocationDropdownUnderSettings();
			Thread.sleep(2000);


		}
		softAssert.assertAll();
	}

	
	
	@Test(retryAnalyzer = RetryFailedTest.class)
	public void verifyingGlobalFilterLandingPage() throws InterruptedException {

		FileReader reader=new FileReader();
		GenericUtility gn=new GenericUtility();
		YtLandingPage yt=new YtLandingPage();
		SoftAssert softAssert =new SoftAssert();
		
		
		reader.loadWorkbook("data/LanguagesList.xlsx");
		reader.loadSheet("LanguagesList");
		int LanguagesRowCount= reader.getRowCount();

		
		yt.openingLandingPage();
		yt.givingInputUnderSearchBar("video");
		gn.clickEnter(yt.searchInputLandinfPage);

		

		for(int j=1;j<LanguagesRowCount;j++) {
			
			yt.clickingSettingEllipsesButton();
			Thread.sleep(2000);
			yt.clickingLanguageDropdownButton();
			Thread.sleep(2000);

			String langText=reader.getCellValue(j, 0);
			System.out.println(langText+"    "+j);

			Thread.sleep(1000);
			yt.getLanguageElementByName(langText).click();	

			Thread.sleep(2000);

			yt.clickingGlobalFilterButton();
			String str=yt.getDataFromGlobalFilterPopup();
			yt.closeGlobalFilterPopup();

			String applicableLanguage=reader.getCellValue(j, 0);
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
