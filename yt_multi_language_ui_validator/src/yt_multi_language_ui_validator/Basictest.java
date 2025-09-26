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
				
		YtLandingPage yt=new YtLandingPage();
		
		
		
		driver.findElement(yt.settingEllipsesButton).click();
		Thread.sleep(2000);
		driver.findElement(yt.languageDropdownUnderSettings).click();
		Thread.sleep(2000);
//		WebElement test2=driver.findElement(yt.getLanguageElementByName("Filipino"));
//		test2.click();
//		Thread.sleep(3000);
		
//		List<WebElement> listOfSideMenu=driver.findElements(yt.sideMenuExpandedList);
//		StringBuilder sb=new StringBuilder();
//		for(int i=0;i<listOfSideMenu.size();i++) {
//			System.out.println(listOfSideMenu.get(i).getText());
//			sb.append(listOfSideMenu.get(i).getText());
//		}
//		
//		LinguaHelper.detectLanguage(sb.toString());
		
//		
//		driver.findElement(yt.settingEllipsesButton).click();
//		Thread.sleep(3000);
//		String settingOptionsList=driver.findElement(yt.settingEllipsesOptionsListLandingPage).getText();
//		System.out.println(settingOptionsList);
//		
//		driver.findElement(yt.languageDropdownUnderSettings).click();
//		Thread.sleep(2000);
//		
//		
//		String langList=driver.findElement(yt.languageList).getText();
//		System.out.println(langList);
//		
		List<WebElement> languageList=	driver.findElements(yt.languageList);
     	languageList.removeIf(el -> el.getText().trim().isEmpty());
//	
//     	
//			
		for(int j=1;j<languageList.size();j++) {
			
		
			languageList=	driver.findElements(yt.languageList);		
			languageList.removeIf(el -> el.getText().trim().isEmpty());
			String langText=languageList.get(j).getText();
			System.out.println(langText+"    "+j);
			
			
	     		WebElement test=driver.findElement(yt.getLanguageElementByName(langText));
				WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
				wait.until(ExpectedConditions.elementToBeClickable(test));
				test.click();
				Thread.sleep(3000);
				List<WebElement> listOfSideMenu=driver.findElements(yt.sideMenuExpandedList);
				StringBuilder sb=new StringBuilder();
				for(int i=0;i<listOfSideMenu.size();i++) {
					System.out.println(listOfSideMenu.get(i).getText());
					sb.append(listOfSideMenu.get(i).getText());
				}
				
				LinguaHelper.detectLanguage(sb.toString());
				Thread.sleep(2000);				
				driver.findElement(yt.settingEllipsesButton).click();
				Thread.sleep(2000);
				driver.findElement(yt.languageDropdownUnderSettings).click();
			}
			
		
		
		
	}	
}