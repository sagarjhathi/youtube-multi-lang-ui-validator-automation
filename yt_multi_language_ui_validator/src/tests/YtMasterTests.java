package tests;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
	    	
	    
	    
	    Map<String, String> languageMap = new HashMap<>() {{
	        put("Azərbaycan", "AZERBAIJANI");
	        put("Bahasa Indonesia", "INDONESIAN");
	        put("Bosanski", "CROATIAN");
	        put("Català", "CATALAN");
	        put("Čeština", "SLOVAK");
	        put("Dansk", "DANISH");
	        put("Deutsch", "GERMAN");
	        put("Eesti", "ESTONIAN");
	        put("English (India)", "ENGLISH");
	        put("English (UK)", "ENGLISH");
	        put("English (US)", "ENGLISH");
	        put("Español (España)", "SPANISH");
	        put("Español (Latinoamérica)", "SPANISH");
	        put("Español (US)", "SPANISH");
	        put("Euskara", "BASQUE");
	        put("Français", "FRENCH");
	        put("Français (Canada)", "FRENCH");
	        put("Hrvatski", "CROATIAN");
	        put("Íslenska", "ICELANDIC");
	        put("Italiano", "ITALIAN");
	        put("Latviešu valoda", "LATVIAN");
	        put("Lietuvių", "LITHUANIAN");
	        put("Magyar", "HUNGARIAN");
	        put("Nederlands", "DUTCH");
	        put("Polski", "POLISH");
	        put("Português", "PORTUGUESE");
	        put("Português (Brasil)", "PORTUGUESE");
	        put("Română", "SOTHO");
	        put("Shqip", "ALBANIAN");
	        put("Slovenščina", "SLOVENE");
	        put("Suomi", "FINNISH");
	        put("Svenska", "SWEDISH");
	        put("Tiếng Việt", "VIETNAMESE");
	        put("Türkçe", "TURKISH");
	        put("Беларуская", "BELARUSIAN");
	        put("Български", "BULGARIAN");
	        put("Русский", "RUSSIAN");
	        put("Українська", "UKRAINIAN");
	        put("Ελληνικά", "GREEK");
	        put("Հայերեն", "ARMENIAN");
	        put("עברית", "HEBREW");
	        put("العربية", "ARABIC");
	        put("فارسی", "PERSIAN");
	        put("हिन्दी", "HINDI");
	        put("বাংলা", "BENGALI");
	        put("ਪੰਜਾਬੀ", "PUNJABI");
	        put("ગુજરાતી", "GUJARATI");
	        put("தமிழ்", "TAMIL");
	        put("తెలుగు", "TELUGU");
	        put("中文 (简体)", "CHINESE");
	        put("中文 (繁體)", "CHINESE");
	        put("中文 (香港)", "CHINESE");
	        put("日本語", "JAPANESE");
	        put("한국어", "KOREAN");
	    }};
	    
	    
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
				
				String applicableLanguage=languageList.get(j);
				String detectedLanguage=LinguaHelper.detectLanguage(sb.toString());
				String expectedLanguage=languageMap.get(applicableLanguage);
			
				System.out.println("ApplicableLanguage  "+applicableLanguage+"     "+"Detected Language   "+detectedLanguage+"  "+"Expected Language  "+expectedLanguage);
				
				
			    softAssert.assertEquals(detectedLanguage, expectedLanguage, "Page title mismatch");
			    
				Thread.sleep(2000);				
				driver.findElement(yt.settingEllipsesButton).click();
				Thread.sleep(2000);
				driver.findElement(yt.languageDropdownUnderSettings).click();
			}
    	     softAssert.assertAll();
    	
		
	}
	
	

	

	
	
}
