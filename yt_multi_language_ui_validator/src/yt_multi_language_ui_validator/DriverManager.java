package yt_multi_language_ui_validator;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverManager {

	public static final ThreadLocal<WebDriver> driver=new ThreadLocal<>();
	
	public static WebDriver getDriver() {
		return driver.get();
	}
	
	public static void initDriver() {
		if(driver.get()==null) {
			
			
	     	ChromeOptions options = new ChromeOptions();
			
         //   String ua = ConfigManager.get("userAgent");
        
            //    options.addArguments("user-agent=" + ua);
            

            // headless toggle
           
               // options.addArguments("--headless");
            

            // language
//            String lang = ConfigManager.get("lang", "en");
//            options.addArguments("--lang=" + lang);

            // start maximized
           
                options.addArguments("--start-maximized");
            

            // disable GPU
          
                options.addArguments("--disable-gpu");
            
            
		
		options.addArguments("--disable-blink-features=AutomationControlled");
		options.addArguments("--disable-dev-shm-usage");
		options.addArguments("--no-sandbox");
		options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
		options.setExperimentalOption("useAutomationExtension", false);
		options.addArguments("--disable-extensions");
		driver.set(new ChromeDriver(options));
		driver.get().manage().deleteAllCookies();
		}
	}
	
	
	
	
	public static void quitDriver() {
		if(driver.get()!=null) {
			getDriver().quit();
			driver.remove();
		}
	}
	
	
	
}
