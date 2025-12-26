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
import main.java.yt_multi_lang_ui_validator.config.ConfigManager;
import main.java.yt_multi_lang_ui_validator.fileReader.FileReader;
import main.java.yt_multi_lang_ui_validator.fileWriter.ExcelFileWriter;
import main.java.yt_multi_lang_ui_validator.fileWriter.FileWriter;
import main.java.yt_multi_lang_ui_validator.lingua.LinguaHelper;
import main.java.yt_multi_lang_ui_validator.logger.LoggerUtility;
import main.java.yt_multi_lang_ui_validator.pages.YtLandingPage;
import main.java.yt_multi_lang_ui_validator.utilities.GenericUtility;
import main.java.yt_multi_lang_ui_validator.utilities.ScreenshotUtil;



public class YtMasterTests extends BaseTest{

	private static final  Logger log=LoggerUtility.getLogger(YtMasterTests.class);

	@Test(retryAnalyzer = RetryFailedTest.class)
	public void verifyingSideMenuLanguageAsInSettings() throws InterruptedException {



		YtLandingPage landingPage=new YtLandingPage();
		SoftAssert softAssert = new SoftAssert();


		FileReader reader=new FileReader();
		reader.loadWorkbook("data/LanguagesList.xlsx");
		reader.loadSheet("LanguagesList");


		FileReader verifyingSideMenuLanguageAsInSettingsDataReader=new FileReader();
		verifyingSideMenuLanguageAsInSettingsDataReader.loadWorkbook("data/verifyingSideMenuLanguageAsInSettings.xlsx");
		verifyingSideMenuLanguageAsInSettingsDataReader.loadSheet("verifyingSideMenuLanguageAsInSe");


		landingPage.openingLandingPage();
		landingPage.clickingSettingEllipsesButton();
		Thread.sleep(2000);
		landingPage.clickingLanguageDropdownButton();
		Thread.sleep(2000);
		String testName = ThreadContext.get("logFileName");
		int LanguagesRowCount= reader.getRowCount();

		boolean isCron = Boolean.parseBoolean(System.getenv("IS_CRON"));
		if(isCron) {

			log.info("[{}] Execution is scheduled type /CRON Job on CI Hence refering to CI keys from UtilData", ThreadContext.get("testName"));

			System.out.println("Execution is scheduled type /CRON Job on CI Hence refering to CI keys from UtilData");

			int defaultLanguageCount=ConfigManager.getInt("overideLanguageCountDefaultCI");
			boolean runForAllLanguages=ConfigManager.getBoolean("runForAllLanguagesCI", false);

			if(runForAllLanguages==false) {
				System.out.println("In the run for all languages if condition");
				LanguagesRowCount=ConfigManager.getInt("overideLanguageCountCI", defaultLanguageCount);
				System.out.println("Row count is =="+LanguagesRowCount);
			}
		}else {

			log.info("[{}] Execution is Not CRON Job Hence refering to normal keys from UtilData", ThreadContext.get("testName"));

			System.out.println("Execution is Not CRON Job Hence refering to normal keys from UtilData");

			int defaultLanguageCount=ConfigManager.getInt("overideLanguageCountDefault");
			boolean runForAllLanguages=ConfigManager.getBoolean("runForAllLanguages", false);

			if(runForAllLanguages==false) {
				System.out.println("In the run for all languages if condition");
				LanguagesRowCount=ConfigManager.getInt("overideLanguageCount", defaultLanguageCount);
				System.out.println("Row count is =="+LanguagesRowCount);
			}

		}


		for(int languageIndex=1;languageIndex<LanguagesRowCount;languageIndex++) {

			String langText=reader.getCellValue(languageIndex, 0);
			System.out.println(langText+" lang text from the sheet   "+languageIndex);

			ScreenshotUtil.capture(testName, langText);
			Thread.sleep(1000);

			landingPage.getLanguageElementByName(langText).click();	

			Thread.sleep(2000);
			List<WebElement> listOfSideMenu=landingPage.gettingSideMenuExpandedList();

			StringBuilder sideMenuItems=new StringBuilder();
			for(int sideMenuItem=0;sideMenuItem<listOfSideMenu.size();sideMenuItem++) {
				System.out.println(listOfSideMenu.get(sideMenuItem).getText());
				sideMenuItems.append(listOfSideMenu.get(sideMenuItem).getText());
				if(sideMenuItem!=listOfSideMenu.size()-1) {
					sideMenuItems.append(" ");
				}

			}



			String expectedData=verifyingSideMenuLanguageAsInSettingsDataReader.getCellValue(languageIndex, 1);
			String actualData=sideMenuItems.toString();

			System.out.println("========="+expectedData+" =====  Expected data from the sheet");
			softAssert.assertEquals(actualData,expectedData,"Mismatch in the Actual and Expected data");

			landingPage.clickingSettingEllipsesButton();
			Thread.sleep(2000);
			landingPage.clickingLanguageDropdownButton();
			Thread.sleep(2000);

		}



		softAssert.assertAll();
	}









	@Test(retryAnalyzer = RetryFailedTest.class,groups = {"lingua"})
	public void verifyingSideMenuLanguageAsInSettingsLingua() throws InterruptedException {



		YtLandingPage landingPage=new YtLandingPage();
		GenericUtility genericUtility=new GenericUtility();
		SoftAssert softAssert = new SoftAssert();


		FileReader reader=new FileReader();
		reader.loadWorkbook("data/LanguagesList.xlsx");
		reader.loadSheet("LanguagesList");


		landingPage.openingLandingPage();
		landingPage.clickingSettingEllipsesButton();
		Thread.sleep(2000);
		landingPage.clickingLanguageDropdownButton();
		Thread.sleep(2000);
		String testName = ThreadContext.get("logFileName");
		int LanguagesRowCount= reader.getRowCount();

		boolean isCron = Boolean.parseBoolean(System.getenv("IS_CRON"));
		if(isCron) {

			log.info("[{}] Execution is scheduled type /CRON Job on CI Hence refering to CI keys from UtilData", ThreadContext.get("testName"));

			System.out.println("Execution is scheduled type /CRON Job on CI Hence refering to CI keys from UtilData");

			int defaultLanguageCount=ConfigManager.getInt("overideLanguageCountDefaultCI");
			boolean runForAllLanguages=ConfigManager.getBoolean("runForAllLanguagesCI", false);

			if(runForAllLanguages==false) {
				System.out.println("In the run for all languages if condition");
				LanguagesRowCount=ConfigManager.getInt("overideLanguageCountCI", defaultLanguageCount);
				System.out.println("Row count is =="+LanguagesRowCount);
			}
		}else {

			log.info("[{}] Execution is Not CRON Job Hence refering to normal keys from UtilData", ThreadContext.get("testName"));

			System.out.println("Execution is Not CRON Job Hence refering to normal keys from UtilData");

			int defaultLanguageCount=ConfigManager.getInt("overideLanguageCountDefault");
			boolean runForAllLanguages=ConfigManager.getBoolean("runForAllLanguages", false);

			if(runForAllLanguages==false) {
				System.out.println("In the run for all languages if condition");
				LanguagesRowCount=ConfigManager.getInt("overideLanguageCount", defaultLanguageCount);
				System.out.println("Row count is =="+LanguagesRowCount);
			}

		}


		for(int languageIndex=1;languageIndex<LanguagesRowCount;languageIndex++) {

			String langText=reader.getCellValue(languageIndex, 0);
			System.out.println(langText+" lang text from the sheet   "+languageIndex);

			ScreenshotUtil.capture(testName, langText);
			Thread.sleep(1000);

			landingPage.getLanguageElementByName(langText).click();	

			Thread.sleep(2000);
			List<WebElement> listOfSideMenu=landingPage.gettingSideMenuExpandedList();

			StringBuilder sideMenuItems=new StringBuilder();
			for(int sideMenuItem=0;sideMenuItem<listOfSideMenu.size();sideMenuItem++) {
				System.out.println(listOfSideMenu.get(sideMenuItem).getText());
				sideMenuItems.append(listOfSideMenu.get(sideMenuItem).getText());
				if(sideMenuItem!=listOfSideMenu.size()-1) {
					sideMenuItems.append(" ");
				}

			}




			String applicableLanguage=reader.getCellValue(languageIndex, 0);
			String detectedLanguage=LinguaHelper.detectLanguage(sideMenuItems.toString());
			String expectedLanguage=genericUtility.getExpectedLangageViaApplicableLangInput(applicableLanguage);

			String expectedLanguageAttribute=genericUtility.getLangAttributeViaLanguageInput(applicableLanguage);
			String detectedLanguageAttribute=genericUtility.getLangAttribute();

			System.out.println("ApplicableLanguage  "+applicableLanguage+"     "+"Detected Language   "+detectedLanguage+"  "+"Expected Language  "+expectedLanguage);
			System.out.println("ApplicableLanguage  "+applicableLanguage+"     "+"Detected Language attribute   "+detectedLanguageAttribute+"  "+"Expected Language attribute "+expectedLanguageAttribute);

			softAssert.assertEquals(detectedLanguage, expectedLanguage, "Page title mismatch");
			softAssert.assertEquals(detectedLanguageAttribute, expectedLanguageAttribute, "Language attributr mismatch");


			landingPage.clickingSettingEllipsesButton();
			Thread.sleep(2000);
			landingPage.clickingLanguageDropdownButton();
			Thread.sleep(2000);

		}

		softAssert.assertAll();
	}





	@Test(retryAnalyzer = RetryFailedTest.class)
	public void verifyingSideMenuCollapsedLangAsInSettings() throws InterruptedException, InvalidFormatException, IOException {


		YtLandingPage landingPage=new YtLandingPage();
		GenericUtility genericUtility=new GenericUtility();
		SoftAssert softAssert = new SoftAssert();




		FileReader reader=new FileReader();
		reader.loadWorkbook("data/LanguagesList.xlsx");
		reader.loadSheet("LanguagesList");


		FileReader verifyingSideMenuCollapsedLangAsInSettingsDataReader=new FileReader();
		verifyingSideMenuCollapsedLangAsInSettingsDataReader.loadWorkbook("data/verifyingSideMenuCollapsedLangAsInSettings.xlsx");
		verifyingSideMenuCollapsedLangAsInSettingsDataReader.loadSheet("verifyingSideMenuCollapsedLangA");


		FileReader applicableExpectedReader=new FileReader();
		applicableExpectedReader.loadWorkbook("data/ApplicableLanguageExpectedLanguage.xlsx");
		applicableExpectedReader.loadSheet("AppVExpectLanguages");


		int LanguagesRowCount= reader.getRowCount();
		String testName = ThreadContext.get("logFileName");
		genericUtility.maximizeDisplay();
		landingPage.openingLandingPage();


		List<Integer> sizes=genericUtility.getWindowHeightWidth();
		if(sizes.get(1)==1552) {
			landingPage.clickingLeftEllipses();
			Thread.sleep(2000);
		}


		landingPage.clickingSettingEllipsesButton();
		Thread.sleep(2000);

		landingPage.clickingLanguageDropdownButton();
		Thread.sleep(2000);

		boolean isCron = Boolean.parseBoolean(System.getenv("IS_CRON"));
		if(isCron) {

			log.info("[{}] Execution is scheduled type /CRON Job on CI Hence refering to CI keys from UtilData", ThreadContext.get("testName"));

			System.out.println("Execution is scheduled type /CRON Job on CI Hence refering to CI keys from UtilData");

			int defaultLanguageCount=ConfigManager.getInt("overideLanguageCountDefaultCI");
			boolean runForAllLanguages=ConfigManager.getBoolean("runForAllLanguagesCI", false);

			if(runForAllLanguages==false) {
				System.out.println("In the run for all languages if condition");
				LanguagesRowCount=ConfigManager.getInt("overideLanguageCountCI", defaultLanguageCount);
				System.out.println("Row count is =="+LanguagesRowCount);
			}
		}else {

			log.info("[{}] Execution is Not CRON Job Hence refering to normal keys from UtilData", ThreadContext.get("testName"));

			System.out.println("Execution is Not CRON Job Hence refering to normal keys from UtilData");

			int defaultLanguageCount=ConfigManager.getInt("overideLanguageCountDefault");
			boolean runForAllLanguages=ConfigManager.getBoolean("runForAllLanguages", false);

			if(runForAllLanguages==false) {
				System.out.println("In the run for all languages if condition");
				LanguagesRowCount=ConfigManager.getInt("overideLanguageCount", defaultLanguageCount);
				System.out.println("Row count is =="+LanguagesRowCount);
			}

		}


		for(int languageIndex=1;languageIndex<LanguagesRowCount;languageIndex++) {

			String langText=reader.getCellValue(languageIndex, 0);
			System.out.println(langText+"    "+languageIndex);
			Thread.sleep(1000);

			landingPage.getLanguageElementByName(langText).click();	
			genericUtility.getLangAttribute();
			Thread.sleep(2000);
			List<WebElement> listOfSideMenu=landingPage.gettingSideMenuCollapsedList();

			StringBuilder sideMenuItems=new StringBuilder();
			for(int sideMenuItem=0;sideMenuItem<listOfSideMenu.size();sideMenuItem++) {
				System.out.println(listOfSideMenu.get(sideMenuItem).getText());
				sideMenuItems.append(listOfSideMenu.get(sideMenuItem).getText());
				sideMenuItems.append(" ");
			}

			String expectedData =	verifyingSideMenuCollapsedLangAsInSettingsDataReader.getCellValue(languageIndex, 1);
			String actualData=sideMenuItems.toString();


			softAssert.assertEquals(actualData, expectedData, "Mismatch in the Expected and actual Data");


			landingPage.clickingSettingEllipsesButton();
			Thread.sleep(2000);
			landingPage.clickingLanguageDropdownButton();
			Thread.sleep(2000);
		}



		softAssert.assertAll();	
	}









	@Test(retryAnalyzer = RetryFailedTest.class,groups = {"lingua"})
	public void verifyingSideMenuCollapsedLangAsInSettingsLingua() throws InterruptedException, InvalidFormatException, IOException {


		YtLandingPage landingPage=new YtLandingPage();
		GenericUtility genericUtility=new GenericUtility();
		SoftAssert softAssert = new SoftAssert();




		FileReader reader=new FileReader();
		reader.loadWorkbook("data/LanguagesList.xlsx");
		reader.loadSheet("LanguagesList");



		FileReader applicableExpectedReader=new FileReader();
		applicableExpectedReader.loadWorkbook("data/ApplicableLanguageExpectedLanguage.xlsx");
		applicableExpectedReader.loadSheet("AppVExpectLanguages");


		int LanguagesRowCount= reader.getRowCount();
		String testName = ThreadContext.get("logFileName");
		genericUtility.maximizeDisplay();
		landingPage.openingLandingPage();


		List<Integer> sizes=genericUtility.getWindowHeightWidth();
		if(sizes.get(1)==1552) {
			landingPage.clickingLeftEllipses();
			Thread.sleep(2000);
		}


		landingPage.clickingSettingEllipsesButton();
		Thread.sleep(2000);

		landingPage.clickingLanguageDropdownButton();
		Thread.sleep(2000);


		boolean isCron = Boolean.parseBoolean(System.getenv("IS_CRON"));
		if(isCron) {

			log.info("[{}] Execution is scheduled type /CRON Job on CI Hence refering to CI keys from UtilData", ThreadContext.get("testName"));

			System.out.println("Execution is scheduled type /CRON Job on CI Hence refering to CI keys from UtilData");

			int defaultLanguageCount=ConfigManager.getInt("overideLanguageCountDefaultCI");
			boolean runForAllLanguages=ConfigManager.getBoolean("runForAllLanguagesCI", false);

			if(runForAllLanguages==false) {
				System.out.println("In the run for all languages if condition");
				LanguagesRowCount=ConfigManager.getInt("overideLanguageCountCI", defaultLanguageCount);
				System.out.println("Row count is =="+LanguagesRowCount);
			}
		}else {

			log.info("[{}] Execution is Not CRON Job Hence refering to normal keys from UtilData", ThreadContext.get("testName"));

			System.out.println("Execution is Not CRON Job Hence refering to normal keys from UtilData");

			int defaultLanguageCount=ConfigManager.getInt("overideLanguageCountDefault");
			boolean runForAllLanguages=ConfigManager.getBoolean("runForAllLanguages", false);

			if(runForAllLanguages==false) {
				System.out.println("In the run for all languages if condition");
				LanguagesRowCount=ConfigManager.getInt("overideLanguageCount", defaultLanguageCount);
				System.out.println("Row count is =="+LanguagesRowCount);
			}

		}

		for(int languageIndex=1;languageIndex<LanguagesRowCount;languageIndex++) {

			String langText=reader.getCellValue(languageIndex, 0);
			System.out.println(langText+"    "+languageIndex);
			Thread.sleep(1000);

			landingPage.getLanguageElementByName(langText).click();	
			genericUtility.getLangAttribute();
			Thread.sleep(2000);
			List<WebElement> listOfSideMenu=landingPage.gettingSideMenuCollapsedList();

			StringBuilder sideMenuItems=new StringBuilder();
			for(int sideMenuItem=0;sideMenuItem<listOfSideMenu.size();sideMenuItem++) {
				System.out.println(listOfSideMenu.get(sideMenuItem).getText());
				sideMenuItems.append(listOfSideMenu.get(sideMenuItem).getText());
				sideMenuItems.append(" ");
			}




			String applicableLanguage=reader.getCellValue(languageIndex, 0);
			String detectedLanguage=LinguaHelper.detectLanguage(sideMenuItems.toString());
			log.info("text sent to lingua is "+"      "+sideMenuItems.toString());
			String expectedLanguage=applicableExpectedReader.getCellValue(languageIndex, 1);



			String expectedLanguageAttribute=genericUtility.getLangAttributeViaLanguageInput(applicableLanguage);
			String detectedLanguageAttribute=genericUtility.getLangAttribute();

			System.out.println("ApplicableLanguage  "+applicableLanguage+"     "+"Detected Language   "+detectedLanguage+"  "+"Expected Language  "+expectedLanguage);
			System.out.println("ApplicableLanguage  "+applicableLanguage+"     "+"Detected Language attribute   "+detectedLanguageAttribute+"  "+"Expected Language attribute "+expectedLanguageAttribute);



			softAssert.assertEquals(detectedLanguage, expectedLanguage, "Language detection mismatch");
			softAssert.assertEquals(detectedLanguageAttribute, expectedLanguageAttribute, "Language attributr mismatch");


			landingPage.clickingSettingEllipsesButton();
			Thread.sleep(2000);
			landingPage.clickingLanguageDropdownButton();
			Thread.sleep(2000);
		}



		softAssert.assertAll();	
	}


	@Test(retryAnalyzer = RetryFailedTest.class)
	public void verifyingSettingOptionsLang() throws InterruptedException {


		FileReader reader=new FileReader();
		YtLandingPage landingPage=new YtLandingPage();
		GenericUtility genericUtility=new GenericUtility();
		SoftAssert softAssert=new SoftAssert();


		reader.loadWorkbook("data/LanguagesList.xlsx");
		reader.loadSheet("LanguagesList");
		int LanguagesRowCount= reader.getRowCount();







		landingPage.openingLandingPage();
		landingPage.clickingSettingEllipsesButton();
		Thread.sleep(1000);
		landingPage.clickingLanguageDropdownButton();
		Thread.sleep(1000);



		boolean isCron = Boolean.parseBoolean(System.getenv("IS_CRON"));
		if(isCron) {

			log.info("[{}] Execution is scheduled type /CRON Job on CI Hence refering to CI keys from UtilData", ThreadContext.get("testName"));

			System.out.println("Execution is scheduled type /CRON Job on CI Hence refering to CI keys from UtilData");

			int defaultLanguageCount=ConfigManager.getInt("overideLanguageCountDefaultCI");
			boolean runForAllLanguages=ConfigManager.getBoolean("runForAllLanguagesCI", false);

			if(runForAllLanguages==false) {
				System.out.println("In the run for all languages if condition");
				LanguagesRowCount=ConfigManager.getInt("overideLanguageCountCI", defaultLanguageCount);
				System.out.println("Row count is =="+LanguagesRowCount);
			}
		}else {

			log.info("[{}] Execution is Not CRON Job Hence refering to normal keys from UtilData", ThreadContext.get("testName"));

			System.out.println("Execution is Not CRON Job Hence refering to normal keys from UtilData");

			int defaultLanguageCount=ConfigManager.getInt("overideLanguageCountDefault");
			boolean runForAllLanguages=ConfigManager.getBoolean("runForAllLanguages", false);

			if(runForAllLanguages==false) {
				System.out.println("In the run for all languages if condition");
				LanguagesRowCount=ConfigManager.getInt("overideLanguageCount", defaultLanguageCount);
				System.out.println("Row count is =="+LanguagesRowCount);
			}

		}

		for(int languageIndex=1;languageIndex<LanguagesRowCount;languageIndex++) {

			String langText=reader.getCellValue(languageIndex, 0);
			System.out.println(langText+"    "+languageIndex);

			Thread.sleep(1000);
			landingPage.getLanguageElementByName(langText).click();	

			Thread.sleep(2000);

			landingPage.clickingSettingEllipsesButton();
			Thread.sleep(1000);
			String languageFromSettings=landingPage.getsettingEllipsesOptionsListLandingPage();
			landingPage.clickingSettingEllipsesButton();


			String applicableLanguage=reader.getCellValue(languageIndex, 0);
			String detectedLanguage=LinguaHelper.detectLanguage(languageFromSettings);
			String expectedLanguage=genericUtility.getExpectedLangageViaApplicableLangInput(applicableLanguage);


			String expectedLanguageAttribute=genericUtility.getLangAttributeViaLanguageInput(applicableLanguage);
			String detectedLanguageAttribute=genericUtility.getLangAttribute();

			System.out.println("ApplicableLanguage  "+applicableLanguage+"     "+"Detected Language   "+detectedLanguage+"  "+"Expected Language  "+expectedLanguage);
			System.out.println("ApplicableLanguage  "+applicableLanguage+"     "+"Detected Language attribute   "+detectedLanguageAttribute+"  "+"Expected Language attribute "+expectedLanguageAttribute);

			softAssert.assertEquals(detectedLanguage, expectedLanguage, "Language detection mismatch");
			softAssert.assertEquals(detectedLanguageAttribute, expectedLanguageAttribute, "Language attributr mismatch");


			landingPage.clickingSettingEllipsesButton();
			Thread.sleep(2000);
			landingPage.clickingLanguageDropdownButton();
			Thread.sleep(2000);

		}

		softAssert.assertAll();	
	}




	@Test(retryAnalyzer = RetryFailedTest.class)
	public void verifyCountryCodeAsBasedOnRegion() throws InterruptedException {


		FileReader reader=new FileReader();
		YtLandingPage landingPage=new YtLandingPage();
		SoftAssert softAssert=new SoftAssert();


		reader.loadWorkbook("data/Country_Name_Code.xlsx");
		reader.loadSheet("CountryNameCode");



		landingPage.openingLandingPage();
		Thread.sleep(2000);
		landingPage.clickingSettingEllipsesButton();
		Thread.sleep(2000);
		landingPage.clickingLocationDropdownUnderSettings();
		Thread.sleep(2000);



		List<WebElement> locationList=landingPage.getLocationList();
		int locationListSize=locationList.size();





		boolean isCron = Boolean.parseBoolean(System.getenv("IS_CRON"));
		if(isCron) {

			log.info("[{}] Execution is scheduled type /CRON Job on CI Hence refering to CI keys from UtilData", ThreadContext.get("testName"));

			System.out.println("Execution is scheduled type /CRON Job on CI Hence refering to CI keys from UtilData");

			int defaultCountriesCount=ConfigManager.getInt("overideCountriesCountDefaultCI");
			boolean runForAllCountries=ConfigManager.getBoolean("runForAllCountriesCI", false);

			if(runForAllCountries==false) {
				System.out.println("In the run for all languages if condition");
				locationListSize=ConfigManager.getInt("overideCountriesCountCI", defaultCountriesCount);
				System.out.println("Row count is =="+locationListSize);
			}
		}else {

			log.info("[{}] Execution is Not CRON Job Hence refering to normal keys from UtilData", ThreadContext.get("testName"));

			System.out.println("Execution is Not CRON Job Hence refering to normal keys from UtilData");

			int defaultLanguageCount=ConfigManager.getInt("overideLanguageCountDefault");
			boolean runForAllLanguages=ConfigManager.getBoolean("runForAllLanguages", false);

			if(runForAllLanguages==false) {
				System.out.println("In the run for all languages if condition");
				locationListSize=ConfigManager.getInt("overideLanguageCount", defaultLanguageCount);
				System.out.println("Row count is =="+locationListSize);
			}

		}





		for(int locationIndex=1;locationIndex<locationListSize;locationIndex++) {
			locationList=landingPage.getLocationList();
			String locationText=locationList.get(locationIndex).getText();
			System.out.println(locationText+"    "+locationIndex);

			String locationTextFromSheet=reader.getCellValue(locationIndex, 0);
			System.out.println(locationTextFromSheet+"  location text from sheet  "+locationIndex);


			Thread.sleep(1000);
			locationList.get(locationIndex).click();
			Thread.sleep(1000);


			String applicableLocation=locationText;
			String expectedCountryCode=reader.getCellValue(locationIndex, 1);
			String detectedCountryCode=landingPage.getCountryCode();

			System.out.println("applicable location is ==  "+applicableLocation+" Expected location  =="+expectedCountryCode+"  detected country code"+detectedCountryCode);
			softAssert.assertEquals(detectedCountryCode, expectedCountryCode,"Country code mismacth");

			landingPage.clickingSettingEllipsesButton();
			Thread.sleep(2000);
			landingPage.clickingLocationDropdownUnderSettings();
			Thread.sleep(2000);


		}
		softAssert.assertAll();
	}



	@Test(retryAnalyzer = RetryFailedTest.class)
	public void verifyingGlobalFilterLandingPage() throws InterruptedException {

		FileReader reader=new FileReader();
		GenericUtility genericUtility=new GenericUtility();
		YtLandingPage landingPage=new YtLandingPage();
		SoftAssert softAssert =new SoftAssert();


		reader.loadWorkbook("data/LanguagesList.xlsx");
		reader.loadSheet("LanguagesList");
		int LanguagesRowCount= reader.getRowCount();



		FileReader verifyingGlobalFilterLandingPageDataReader=new FileReader();
		verifyingGlobalFilterLandingPageDataReader.loadWorkbook("data/verifyingGlobalFilterLandingPage.xlsx");
		verifyingGlobalFilterLandingPageDataReader.loadSheet("verifyingGlobalFilterLandingPag");


		landingPage.openingLandingPage();
		landingPage.givingInputUnderSearchBar("youtube");
		genericUtility.clickEnter(landingPage.searchInputLandinfPage);




		boolean isCron = Boolean.parseBoolean(System.getenv("IS_CRON"));
		if(isCron) {

			log.info("[{}] Execution is scheduled type /CRON Job on CI Hence refering to CI keys from UtilData", ThreadContext.get("testName"));

			System.out.println("Execution is scheduled type /CRON Job on CI Hence refering to CI keys from UtilData");

			int defaultLanguageCount=ConfigManager.getInt("overideLanguageCountDefaultCI");
			boolean runForAllLanguages=ConfigManager.getBoolean("runForAllLanguagesCI", false);

			if(runForAllLanguages==false) {
				System.out.println("In the run for all languages if condition");
				LanguagesRowCount=ConfigManager.getInt("overideLanguageCountCI", defaultLanguageCount);
				System.out.println("Row count is =="+LanguagesRowCount);
			}
		}else {

			log.info("[{}] Execution is Not CRON Job Hence refering to normal keys from UtilData", ThreadContext.get("testName"));

			System.out.println("Execution is Not CRON Job Hence refering to normal keys from UtilData");

			int defaultLanguageCount=ConfigManager.getInt("overideLanguageCountDefault");
			boolean runForAllLanguages=ConfigManager.getBoolean("runForAllLanguages", false);

			if(runForAllLanguages==false) {
				System.out.println("In the run for all languages if condition");
				LanguagesRowCount=ConfigManager.getInt("overideLanguageCount", defaultLanguageCount);
				System.out.println("Row count is =="+LanguagesRowCount);
			}

		}

		for(int languageIndex=1;languageIndex<LanguagesRowCount;languageIndex++) {

			landingPage.clickingSettingEllipsesButton();
			Thread.sleep(2000);
			landingPage.clickingLanguageDropdownButton();
			Thread.sleep(2000);

			String langText=reader.getCellValue(languageIndex, 0);
			System.out.println(langText+"    "+languageIndex);

			Thread.sleep(1000);
			landingPage.getLanguageElementByName(langText).click();	

			Thread.sleep(2000);

			landingPage.clickingGlobalFilterButton();
			String globalSettingText=landingPage.getDataFromGlobalFilterPopup();
			landingPage.closeGlobalFilterPopup();


			String expectedData=verifyingGlobalFilterLandingPageDataReader.getCellValue(languageIndex, 1);
			String actualValue=globalSettingText;


			softAssert.assertEquals(actualValue, expectedData, "Mismatch in the Expected and Actual Data");



		}

		softAssert.assertAll();	

		landingPage.clickingGlobalFilterButton();
		String globalSettingText=landingPage.getDataFromGlobalFilterPopup();
		System.out.println(globalSettingText);
		landingPage.closeGlobalFilterPopup();

	}






	@Test(retryAnalyzer = RetryFailedTest.class,groups = {"lingua"})
	public void verifyingGlobalFilterLandingPageLingua() throws InterruptedException {


		GenericUtility genericUtility=new GenericUtility();
		YtLandingPage landingPage=new YtLandingPage();
		SoftAssert softAssert =new SoftAssert();


		FileReader reader=new FileReader();
		reader.loadWorkbook("data/LanguagesList.xlsx");
		reader.loadSheet("LanguagesList");
		int LanguagesRowCount= reader.getRowCount();




		landingPage.openingLandingPage();
		landingPage.givingInputUnderSearchBar("youtube");
		genericUtility.clickEnter(landingPage.searchInputLandinfPage);


		boolean isCron = Boolean.parseBoolean(System.getenv("IS_CRON"));
		if(isCron) {

			log.info("[{}] Execution is scheduled type /CRON Job on CI Hence refering to CI keys from UtilData", ThreadContext.get("testName"));

			System.out.println("Execution is scheduled type /CRON Job on CI Hence refering to CI keys from UtilData");

			int defaultLanguageCount=ConfigManager.getInt("overideLanguageCountDefaultCI");
			boolean runForAllLanguages=ConfigManager.getBoolean("runForAllLanguagesCI", false);

			if(runForAllLanguages==false) {
				System.out.println("In the run for all languages if condition");
				LanguagesRowCount=ConfigManager.getInt("overideLanguageCountCI", defaultLanguageCount);
				System.out.println("Row count is =="+LanguagesRowCount);
			}
		}else {

			log.info("[{}] Execution is Not CRON Job Hence refering to normal keys from UtilData", ThreadContext.get("testName"));

			System.out.println("Execution is Not CRON Job Hence refering to normal keys from UtilData");

			int defaultLanguageCount=ConfigManager.getInt("overideLanguageCountDefault");
			boolean runForAllLanguages=ConfigManager.getBoolean("runForAllLanguages", false);

			if(runForAllLanguages==false) {
				System.out.println("In the run for all languages if condition");
				LanguagesRowCount=ConfigManager.getInt("overideLanguageCount", defaultLanguageCount);
				System.out.println("Row count is =="+LanguagesRowCount);
			}

		}


		for(int languageIndex=1;languageIndex<LanguagesRowCount;languageIndex++) {

			landingPage.clickingSettingEllipsesButton();
			Thread.sleep(2000);
			landingPage.clickingLanguageDropdownButton();
			Thread.sleep(2000);

			String langText=reader.getCellValue(languageIndex, 0);
			System.out.println(langText+"    "+languageIndex);

			Thread.sleep(1000);
			landingPage.getLanguageElementByName(langText).click();	

			Thread.sleep(2000);

			landingPage.clickingGlobalFilterButton();
			String globalSettingText=landingPage.getDataFromGlobalFilterPopup();
			landingPage.closeGlobalFilterPopup();


			String applicableLanguage=reader.getCellValue(languageIndex, 0);
			String detectedLanguage=LinguaHelper.detectLanguage(globalSettingText);
			String expectedLanguage=genericUtility.getExpectedLangageViaApplicableLangInput(applicableLanguage);


			String expectedLanguageAttribute=genericUtility.getLangAttributeViaLanguageInput(applicableLanguage);
			String detectedLanguageAttribute=genericUtility.getLangAttribute();

			System.out.println("ApplicableLanguage  "+applicableLanguage+"     "+"Detected Language   "+detectedLanguage+"  "+"Expected Language  "+expectedLanguage);
			System.out.println("ApplicableLanguage  "+applicableLanguage+"     "+"Detected Language attribute   "+detectedLanguageAttribute+"  "+"Expected Language attribute "+expectedLanguageAttribute);

			softAssert.assertEquals(detectedLanguage, expectedLanguage, "Language detection mismatch");
			softAssert.assertEquals(detectedLanguageAttribute, expectedLanguageAttribute, "Language attributr mismatch");



		}

		softAssert.assertAll();	

		landingPage.clickingGlobalFilterButton();
		String globalSettingText=landingPage.getDataFromGlobalFilterPopup();
		System.out.println(globalSettingText);
		landingPage.closeGlobalFilterPopup();

	}






}
