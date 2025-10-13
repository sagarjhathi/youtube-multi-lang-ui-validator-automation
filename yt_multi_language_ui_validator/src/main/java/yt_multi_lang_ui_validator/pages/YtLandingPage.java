package main.java.yt_multi_lang_ui_validator.pages;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

import main.java.yt_multi_lang_ui_validator.base.BasePage;
import main.java.yt_multi_lang_ui_validator.lingua.LinguaHelper;
import main.java.yt_multi_lang_ui_validator.logger.LoggerUtility;
import main.java.yt_multi_lang_ui_validator.safeActions.SafeActions;
import main.java.yt_multi_lang_ui_validator.utilities.WaitUtility;
import yt_multi_language_ui_validator.BasicTest;
public class YtLandingPage  extends BasePage{
	
     private static final Logger log = LoggerUtility.getLogger(YtLandingPage.class);
	 private final SafeActions safeAct;

	 public YtLandingPage() {
	        super();
	        this.safeAct = new SafeActions(driver);
	        log.info("Initialized YtLandingPage with driver: {}", driver);
	    }

	
	public By sideMenuExpandedList=By.xpath("//ytd-guide-entry-renderer[@class='style-scope ytd-guide-section-renderer']");
	
	public By settingEllipsesButton=By.xpath("//ytd-topbar-menu-button-renderer[@class='style-scope ytd-masthead style-default']");
	
	public By languageDropdownUnderSettings=By.xpath("(//div[@id='content-icon'])[2]");
	
	public By languageList=By.xpath("//yt-multi-page-menu-section-renderer[@class='style-scope ytd-multi-page-menu-renderer']//ytd-compact-link-renderer");
	
	public By ellipsesYtLandingPageTopLeft=By.xpath("//ytd-topbar-logo-renderer[@id='logo']/preceding-sibling::*[@id='guide-button']");
	
	public By sideMenuCollapsedList=By.xpath("//ytd-mini-guide-renderer[@class='style-scope ytd-app']//ytd-mini-guide-entry-renderer");
	
	public By countryCodeOnYtLogoLandingPage=By.xpath("(//span[@id='country-code'])[1]");
	
	public By settingEllipsesOptionsListLandingPage=By.xpath("//ytd-multi-page-menu-renderer");
	
	public By languageListUpdated=By.xpath("//ytd-multi-page-menu-renderer[@slot='dropdown-content']");
	
	public By searchInputLandinfPage=By.xpath("//input[@class='ytSearchboxComponentInput yt-searchbox-input title']");
	
	//Cannot use this directly as this banner wouldnt show if yt does not have a search histroy so to show these recommendation banner
	public By horizontalScrollBarLandingPage=By.xpath("//div[@id='scroll-container']");
	
	public By locationDropdownUnderSettings=By.xpath("(//div[@id='content-icon'])[4]");
	
	public By locationList=By.xpath("//yt-multi-page-menu-section-renderer[@class='style-scope ytd-multi-page-menu-renderer']//ytd-compact-link-renderer");
	
	public By globalFilterButton = By.xpath("//div[@id='filter-button']");
	
	public By globalFilterData=By.xpath("//ytd-search-filter-options-dialog-renderer[@class='style-scope ytd-popup-container']");
	
	public By closeButtonGlobalFilterPopup=By.xpath("//yt-icon[@icon='close']");
	
	
	
	
	
	public WebElement getLanguageElementByName(String name) {
	    	By locator=By.xpath("//yt-multi-page-menu-section-renderer[@class='style-scope ytd-multi-page-menu-renderer']//yt-formatted-string[@id='label' and text()='" + name + "']");
	    	log.info("Looking for language element with name: {}", name);
		    WebElement safeElement = safeAct.safeFindElement(locator);
		    return safeElement;
	}

	public void clickingUnderSearchInput() {
		log.info("Clicking into the search bar...");
		safeAct.safeFindElement(searchInputLandinfPage);
		safeAct.safeClick(searchInputLandinfPage);
	}
	
	public void givingInputUnderSearchBar(String input) {
		log.info("Giving input into the search bar with input as  = "+input);
		safeAct.safeClick(searchInputLandinfPage);
		safeAct.safeFindElement(searchInputLandinfPage).sendKeys(input);
	}

	
	public void clickingGlobalFilterButton() {
		log.info("Clicking on global filter button in landing page..");
		safeAct.safeClick(globalFilterButton);
	}
	
	
	public void clickingLocationDropdownUnderSettings() {
		log.info("Clicking on location dropdown under settings..");
		safeAct.safeClick(locationDropdownUnderSettings);
		
	}
	
	
	
	public void closeGlobalFilterPopup() {
		log.info("Closing the global filter pop-up in landing page..");
		safeAct.safeFindElement(closeButtonGlobalFilterPopup).click();
	}
	
	
	public List<WebElement> getLocationList() {
		log.info("Fetching the location list ...");
		List<WebElement>list=safeAct.safeFindElements(locationList);
		list.removeIf(el -> el.getText().trim().isEmpty());
		log.info("Fetched  the location list == "+list);
		return list;
		
	}
	
	
    public void openingLandingPage() {
    	driver.get("https://www.youtube.com/");
    	log.info("Opened YouTube landing page.");
    }
    
    public void clickingSettingEllipsesButton() throws InterruptedException {
    	    log.info("Clicking settings ellipses button...");
        	safeAct.safeFindElement(settingEllipsesButton).click();
    }
    
    public void clickingLanguageDropdownButton() throws InterruptedException {
    	 log.info("Clicking language dropdown...");
    	safeAct.safeFindElement(languageDropdownUnderSettings).click();
    	
    }
    
    public List<WebElement> gettingLanguageList() throws InterruptedException{
          	log.info("Fetching available language list...");
    		 List<WebElement> langList=safeAct.safeFindElements(languageList);
    		 langList.removeIf(el -> el.getText().trim().isEmpty());
    		 log.info("Found {} languages.", langList.size());
    		 return langList;
    }
    
    
    public String getsettingEllipsesOptionsListLandingPage() {
    	log.info("Getting the setting ellipses options");
    	String str=safeAct.safeFindElement(settingEllipsesOptionsListLandingPage).getText();
    	return str;
    }
    
    
    public List<WebElement> gettingSideMenuExpandedList() throws InterruptedException{
        log.info("Fetching the side menu in expanded view");
		 List<WebElement> sideMenuList=safeAct.safeFindElements(sideMenuExpandedList);
		 return sideMenuList;
    }
    
    
    public List<WebElement> gettingSideMenuCollapsedList() throws InterruptedException{
    	 log.info("Fetching the side menu in collapsed view");
		 List<WebElement> sideMenuList=safeAct.safeFindElements(sideMenuCollapsedList);
		 return sideMenuList;
   }
    
    
    public void clickingLeftEllipses() {
    	log.info("Clicking left ellipses in the landing page");
    	safeAct.safeFindElement(ellipsesYtLandingPageTopLeft).click();
    }
    
    
    
    public String getDataFromGlobalFilterPopup() {
    	log.info("Fetching the data from the global filter in landing page");
    	String str=safeAct.safeFindElement(globalFilterData).getText();
    	return str;
    }
    
    
    
    
    public List<String> applyLanguagesFromInternalDataset(){
    	log.info("In the applyLanguagesFromInternalDataset ");
    	List<String> linguaAccurateLanguages = Arrays.asList(
    		    	 "Afrikaans",
    		        "Azərbaycan",
    		        "Bahasa Indonesia",
    		        "Bosanski",
    		        "Català",
    		        "Dansk",
    		        "Deutsch",
    		        "Eesti",
    		        "English (India)",
    		        "English (UK)",
    		        "English (US)",
    		        "Español (España)",
    		        "Español (Latinoamérica)",
    		        "Español (US)",
    		        "Euskara",
    		        "Français",
    		        "Français (Canada)",
    		        "Hrvatski",
    		        "Íslenska",
    		        "Italiano",
    		        "Latviešu valoda",
    		        "Lietuvių",
    		        "Magyar",
    		        "Nederlands",
    		        "Polski",
    		        "Português",
    		        "Português (Brasil)",
    		        "Shqip",
    		        "Slovenščina",
    		        "Suomi",
    		        "Svenska",
    		        "Tiếng Việt",
    		        "Türkçe",
    		        "Беларуская",
    		        "Български",
    		        "Русский",
    		        "Українська",
    		        "Ελληνικά",
    		        "Հայերեն",
    		        "עברית",
    		        "العربية",
    		        "فارسی",
    		        "हिन्दी",
    		        "বাংলা",
    		        "ਪੰਜਾਬੀ",
    		        "ગુજરાતી",
    		        "தமிழ்",
    		        "తెలుగు",
    		        "中文 (简体)",
    		        "中文 (繁體)",
    		        "中文 (香港)",
    		        "日本語",
    		        "한국어"
    		);
    	
    	
    		return linguaAccurateLanguages;
    }
    
    
    public String getCountryCode() {
    	log.info("Retrieving country code from YouTube logo...");
    	String code= safeAct.safeFindElement(countryCodeOnYtLogoLandingPage).getText();
    	log.info("Detected country code: {}", code);
    	return code;
     }


    public String getExpectedCountryCodeViaLocation(String location) {
    	
    	log.info("In the getExpectedCountryCodeViaLocation ");
    	
    	Map<String, String> countryCodeMap = new HashMap<>() {{
    	    put("Argentina", "AR");
    	    put("Australia", "AU");
    	    put("Austria", "AT");
    	    put("Azerbaijan", "AZ");
    	    put("Bahrain", "BH");
    	    put("Bangladesh", "BD");
    	    put("Belarus", "BY");
    	    put("Belgium", "BE");
    	    put("Bolivia", "BO");
    	    put("Bosnia and Herzegovina", "BA");
    	    put("Brazil", "BR");
    	    put("Bulgaria", "BG");
    	    put("Cambodia", "KH");
    	    put("Canada", "CA");
    	    put("Chile", "CL");
    	    put("Colombia", "CO");
    	    put("Costa Rica", "CR");
    	    put("Croatia", "HR");
    	    put("Cyprus", "CY");
    	    put("Czechia", "CZ");
    	    put("Denmark", "DK");
    	    put("Dominican Republic", "DO");
    	    put("Ecuador", "EC");
    	    put("Egypt", "EG");
    	    put("El Salvador", "SV");
    	    put("Estonia", "EE");
    	    put("Finland", "FI");
    	    put("France", "FR");
    	    put("Georgia", "GE");
    	    put("Germany", "DE");
    	    put("Ghana", "GH");
    	    put("Greece", "GR");
    	    put("Guatemala", "GT");
    	    put("Honduras", "HN");
    	    put("Hong Kong", "HK");
    	    put("Hungary", "HU");
    	    put("Iceland", "IS");
    	    put("India", "IN");
    	    put("Indonesia", "ID");
    	    put("Iraq", "IQ");
    	    put("Ireland", "IE");
    	    put("Israel", "IL");
    	    put("Italy", "IT");
    	    put("Jamaica", "JM");
    	    put("Japan", "JP");
    	    put("Jordan", "JO");
    	    put("Kazakhstan", "KZ");
    	    put("Kenya", "KE");
    	    put("Kuwait", "KW");
    	    put("Laos", "LA");
    	    put("Latvia", "LV");
    	    put("Lebanon", "LB");
    	    put("Libya", "LY");
    	    put("Liechtenstein", "LI");
    	    put("Lithuania", "LT");
    	    put("Luxembourg", "LU");
    	    put("Malaysia", "MY");
    	    put("Malta", "MT");
    	    put("Mexico", "MX");
    	    put("Moldova", "MD");
    	    put("Montenegro", "ME");
    	    put("Morocco", "MA");
    	    put("Nepal", "NP");
    	    put("Netherlands", "NL");
    	    put("New Zealand", "NZ");
    	    put("Nicaragua", "NI");
    	    put("Nigeria", "NG");
    	    put("North Macedonia", "MK");
    	    put("Norway", "NO");
    	    put("Oman", "OM");
    	    put("Pakistan", "PK");
    	    put("Panama", "PA");
    	    put("Papua New Guinea", "PG");
    	    put("Paraguay", "PY");
    	    put("Peru", "PE");
    	    put("Philippines", "PH");
    	    put("Poland", "PL");
    	    put("Portugal", "PT");
    	    put("Puerto Rico", "PR");
    	    put("Qatar", "QA");
    	    put("Romania", "RO");
    	    put("Russia", "RU");
    	    put("Saudi Arabia", "SA");
    	    put("Senegal", "SN");
    	    put("Serbia", "RS");
    	    put("Singapore", "SG");
    	    put("Slovakia", "SK");
    	    put("Slovenia", "SI");
    	    put("South Africa", "ZA");
    	    put("South Korea", "KR");
    	    put("Spain", "ES");
    	    put("Sri Lanka", "LK");
    	    put("Sweden", "SE");
    	    put("Switzerland", "CH");
    	    put("Taiwan", "TW");
    	    put("Tanzania", "TZ");
    	    put("Thailand", "TH");
    	    put("Tunisia", "TN");
    	    put("Turkey", "TR");
    	    put("Uganda", "UG");
    	    put("Ukraine", "UA");
    	    put("United Arab Emirates", "AE");
    	    put("United Kingdom", "GB");
    	    put("United States", "");
    	    put("Uruguay", "UY");
    	    put("Venezuela", "VE");
    	    put("Vietnam", "VN");
    	    put("Yemen", "YE");
    	    put("Zimbabwe", "ZW");
    	}};

    	
    	if(countryCodeMap.containsKey(location)) {
    		log.info("Country code exists in the data set , value is ="+countryCodeMap.get(location));
    		return countryCodeMap.get(location);
    	}else {
    		log.info("Country code does not exists in the data set, returning null");
    		return null;
    	}
    }
    
    
 }
	

