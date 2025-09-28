package yt_multi_language_ui_validator;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;



public class BasePage {

	WebDriver driver;
	WebDriverWait wait;

	public BasePage() {
        this.driver = DriverManager.getDriver();
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        
        
    }	
}
