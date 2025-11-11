package main.java.yt_multi_lang_ui_validator.utilities;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import main.java.yt_multi_lang_ui_validator.base.BasePage;
import main.java.yt_multi_lang_ui_validator.logger.LoggerUtility;
import main.java.yt_multi_lang_ui_validator.pages.YtLandingPage;
import main.java.yt_multi_lang_ui_validator.reporting.TestListener;
import main.java.yt_multi_lang_ui_validator.safeActions.SafeActions;

public class GenericUtility extends BasePage {
	
	
	 private static final  Logger log=LoggerUtility.getLogger(GenericUtility.class);
	 SafeActions safeAct = new SafeActions(driver);
		
	
	public String getLangAttribute() {
        log.info("getLangAttribute() - locating <html> and reading lang property");
        try {
            WebElement html = driver.findElement(By.xpath("//html"));
            String langProp = html.getDomProperty("lang");
            log.info("Language attribute read: {}", langProp);
            return langProp;
        } catch (Exception e) {
            log.error("Failed to read html lang attribute: {}", e.getMessage());
            return null;
        }
    }
	
	
	
	public String getLangAttributeViaLanguageInput(String langInput) {
		log.info("getLangAttributeViaLanguageInput({})", langInput);
		Map<String, String> languageAttributeMap = new HashMap<>() {{
		    put("Afrikaans", "af-ZA");
		    put("Azərbaycan", "az-Latn-AZ");
		    put("Bahasa Indonesia", "id-ID");
		    put("Bosanski", "bs-Latn-BA");
		    put("Català", "ca-ES");
		    put("Dansk", "da-DK");
		    put("Deutsch", "de-DE");
		    put("Eesti", "et-EE");
		    put("English (India)", "en-IN");
		    put("English (UK)", "en-GB");
		    put("English (US)", "en");
		    put("Español (España)", "es-ES");
		    put("Español (Latinoamérica)", "es-419");
		    put("Español (US)", "es-US");
		    put("Euskara", "eu-ES");
		    put("Français", "fr-FR");
		    put("Français (Canada)", "fr-CA");
		    put("Hrvatski", "hr-HR");
		    put("Íslenska", "is-IS");
		    put("Italiano", "it-IT");
		    put("Latviešu valoda", "lv-LV");
		    put("Lietuvių", "lt-LT");
		    put("Magyar", "hu-HU");
		    put("Nederlands", "nl-NL");
		    put("Polski", "pl-PL");
		    put("Português", "pt-PT");
		    put("Português (Brasil)", "pt-BR");
		    put("Shqip", "sq-AL");
		    put("Slovenščina", "sl-SI");
		    put("Suomi", "fi-FI");
		    put("Svenska", "sv-SE");
		    put("Tiếng Việt", "vi-VN");
		    put("Türkçe", "tr-TR");
		    put("Беларуская", "be-BY");
		    put("Български", "bg-BG");
		    put("Русский", "ru-RU");
		    put("Українська", "uk-UA");
		    put("Ελληνικά", "el-GR");
		    put("Հայերեն", "hy-AM");
		    put("עברית", "he-IL");
		    put("العربية", "ar");
		    put("فارسی", "fa-IR");
		    put("हिन्दी", "hi-IN");
		    put("বাংলা", "bn-BD");
		    put("ਪੰਜਾਬੀ", "pa-Guru-IN");
		    put("ગુજરાતી", "gu-IN");
		    put("தமிழ்", "ta-IN");
		    put("తెలుగు", "te-IN");
		    put("中文 (简体)", "zh-Hans-CN");
		    put("中文 (繁體)", "zh-Hant-TW");
		    put("中文 (香港)", "zh-Hant-HK");
		    put("日本語", "ja-JP");
		    put("한국어", "ko-KR");
		}};
		
		
		if(languageAttributeMap.containsKey(langInput)) {
			String langAttribute=languageAttributeMap.get(langInput);
			 log.info("Mapped {} -> {}", langInput, langAttribute);
			return langAttribute;
		}else {
			log.warn("No lang attribute mapping for '{}'", langInput);
			return null;
		}

	}
	
	
	
	public void maximizeDisplay() {
		driver.manage().window().maximize();
	}
	
	
	public String getExpectedLangageViaApplicableLangInput(String langInput) {

		 log.info("getExpectedLangageViaApplicableLangInput({})", langInput);
		
		 Map<String, String> languageMap = new HashMap<>() {{
		    	put("Afrikaans", "AFRIKAANS");
		        put("Azərbaycan", "AZERBAIJANI");
		        put("Bahasa Indonesia", "INDONESIAN");
		        put("Bosanski", "CROATIAN");
		        put("Català", "CATALAN");
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
		    
		    
		    
		    if(languageMap.containsKey(langInput)) {
				String lang=languageMap.get(langInput);
				 log.info("Expected language for '{}' -> {}", langInput, lang);
				return lang;
			}else {
				log.warn("No expected language mapping for '{}'", langInput);
				return null;
			}
		    
		    
	}
 
	// ---- convenience methods ----
    public void clickEnter(By locator) {
        log.info("clickEnter({})", locator);
        WebElement el = safeAct.safeFindElement(locator);
        if (el != null) {
            el.sendKeys(Keys.ENTER);
            log.info("Sent ENTER to {}", locator);
        } else {
            log.warn("clickEnter - element not found: {}", locator);
        }
    }

}
