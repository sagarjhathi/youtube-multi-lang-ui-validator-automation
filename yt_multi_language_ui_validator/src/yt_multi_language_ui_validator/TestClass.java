package yt_multi_language_ui_validator;



import java.time.Duration;  
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
public class TestClass {

	public static void main (String [] args) throws InterruptedException  {
		
		
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.youtube.com/watch?v=q0aFOxT6TNw");
		Thread.sleep(2000);
		List<WebElement> list=driver.findElements(By.xpath("//div[@class='yt-content-metadata-view-model__metadata-row']"));
		
		for(int i=0;i<list.size();i++) {
			
			System.out.println(list.get(i).getText());
			
		}
	}
}
