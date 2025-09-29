package yt_multi_language_ui_validator;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



public class BasePage {

	public WebDriver driver;
	protected WebDriverWait wait;

	public BasePage() {
        this.driver = DriverManager.getDriver();
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        
        
    }	
	
	
	// ---- Common reusable helpers available to all POMs ----
    protected void click(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    protected void type(WebElement element, String text) {
        WebElement e = wait.until(ExpectedConditions.visibilityOf(element));
        e.clear();
        e.sendKeys(text);
    }
    

    protected WebElement waitVisible(By by) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    protected WebElement waitClickable(By by) {
        return wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    protected void jsClick(By by) {
        WebElement e = waitClickable(by);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", e);
    }
}
