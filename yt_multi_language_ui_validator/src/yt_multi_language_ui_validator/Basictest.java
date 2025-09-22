package yt_multi_language_ui_validator;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Basictest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println("hey");
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.youtube.com/");
		
		List<WebElement> listOfSideMenu=driver.findElements(By.xpath("//ytd-guide-entry-renderer[@class='style-scope ytd-guide-section-renderer']"));
		
		for(int i=0;i<listOfSideMenu.size();i++) {
			System.out.println(listOfSideMenu.get(i).getText());
		
				WebElement test=listOfSideMenu.get(i);
				WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
				wait.until(ExpectedConditions.elementToBeClickable(test));
				test.click();
		}
	}
	
}
