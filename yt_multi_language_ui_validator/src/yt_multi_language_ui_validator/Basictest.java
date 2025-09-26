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
 
public class BasicTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		System.out.println("hey");
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.youtube.com/");
		
		
//String recommendationsOnYoutube=	driver.findElement(By.xpath("//div[@id='scroll-container']")).getText();
//		
//		System.out.println(recommendationsOnYoutube);
		
		
		
		List<WebElement> listOfSideMenu=driver.findElements(By.xpath("//ytd-guide-entry-renderer[@class='style-scope ytd-guide-section-renderer']"));
		
		for(int i=0;i<listOfSideMenu.size();i++) {
			System.out.println(listOfSideMenu.get(i).getText());
		}
		
		// ellipses three dot click
		driver.findElement(By.xpath("//ytd-topbar-menu-button-renderer[@class='style-scope ytd-masthead style-default']")).click();
		Thread.sleep(3000);
		//language dropdown click
	//	driver.findElement(By.xpath("//div[@id='primary-text-container']//yt-formatted-string[@id='label' and text()='Language:']")).click();
		Thread.sleep(2000);
		//Language list 
//		List<WebElement> languageList=	driver.findElements(By.xpath("//yt-multi-page-menu-section-renderer[@class='style-scope ytd-multi-page-menu-renderer']//ytd-compact-link-renderer"));
//		languageList.removeIf(el -> el.getText().trim().isEmpty());
		
		
		String settingOptionsList=driver.findElement(By.xpath("//div[@id='contentWrapper']")).getText();
		System.out.println(settingOptionsList);
		
		
		
		driver.findElement(By.xpath("//div[@id='primary-text-container']//yt-formatted-string[@id='label' and text()='Language:']")).click();
		
		
		String langList=driver.findElement(By.xpath("//ytd-multi-page-menu-renderer[@slot='dropdown-content']")).getText();
		
		System.out.println(langList);
		
		
	String recommendationsOnYoutube=	driver.findElement(By.xpath("//div[@id='scroll-container']")).getText();
		
		System.out.println(recommendationsOnYoutube);
		
		
		
		
		
		
		
		
		
//		
//		
//		String languageList=driver.findElement(By.xpath("//ytd-multi-page-menu-renderer[@slot='dropdown-content']")).getText();
//		
//
//		
//		
//		for(int j=1;j<languageList.length();j++) {
//			
//		
//			languageList=	driver.findElements(By.xpath("//yt-multi-page-menu-section-renderer[@class='style-scope ytd-multi-page-menu-renderer']//ytd-compact-link-renderer"));
//			languageList.removeIf(el -> el.getText().trim().isEmpty());
//
//			String langText=languageList.get(j).getText();
//			System.out.println(langText+"    "+j);
//			
//			
//			    WebElement test=driver.findElement(By.xpath("//yt-multi-page-menu-section-renderer[@class='style-scope ytd-multi-page-menu-renderer']//yt-formatted-string[@id='label' and text()='" + langText + "']"));
//				WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
//				wait.until(ExpectedConditions.elementToBeClickable(test));
//				test.click();
//				Thread.sleep(2000);
//			//	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//ytd-topbar-menu-button-renderer[@class='style-scope ytd-masthead style-default']")));
//				
//				driver.findElement(By.xpath("//ytd-topbar-menu-button-renderer[@class='style-scope ytd-masthead style-default']")).click();
//			//	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@id='content-icon'])[2]")));
//				Thread.sleep(2000);
//
//				driver.findElement(By.xpath("(//div[@id='content-icon'])[2]")).click();
//			
//				
//			}
			
		
		
		
	}	
}