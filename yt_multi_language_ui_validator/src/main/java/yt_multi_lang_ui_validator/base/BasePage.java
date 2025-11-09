package main.java.yt_multi_lang_ui_validator.base;

import java.time.Duration;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import main.java.yt_multi_lang_ui_validator.config.ConfigManager;
import main.java.yt_multi_lang_ui_validator.driverManager.DriverManager;
import main.java.yt_multi_lang_ui_validator.logger.LoggerUtility;
import main.java.yt_multi_lang_ui_validator.utilities.WaitUtility;



public class BasePage {

	private static  final  Logger log=LoggerUtility.getLogger(BasePage.class);
	
	protected WebDriver driver;
	protected WebDriverWait wait;
	
	 public BasePage() {
		 

	        this.driver = DriverManager.getDriver();

	        
	        if (driver == null) {
	            throw new IllegalStateException("WebDriver not initialized!");
	        }
	        
	        ConfigManager cfg = ConfigManager.getInstance();  
	        int waitDuration= cfg.getInt("explicit.wait", 10);
	        System.out.println(waitDuration+"   wait in the base page added");
	        
	        this.wait = new WebDriverWait(driver, Duration.ofSeconds(waitDuration));
	    }

}
