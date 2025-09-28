package tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import POM.YtLandingPage;
import yt_multi_language_ui_validator.BaseTest;
import yt_multi_language_ui_validator.LinguaHelper;

public class YtMasterTests extends BaseTest{

	
	@Test
	public void verifyingSideMenuLanguageAsInSettings() throws InterruptedException {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(15));
		YtLandingPage yt=new YtLandingPage();
		yt.openingLandingPage();
		driver.findElement(yt.settingEllipsesButton).click();
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(yt.languageDropdownUnderSettings));
 		driver.findElement(yt.languageDropdownUnderSettings).click();
	    Thread.sleep(2000);
	    
	    
	    
	    String langList=driver.findElement(yt.languageList).getText();
		System.out.println(langList);
		
		List<WebElement> languageList=	driver.findElements(yt.languageList);
     	languageList.removeIf(el -> el.getText().trim().isEmpty());
	
     	
			
		for(int j=1;j<languageList.size();j++) {
			
		
			languageList=	driver.findElements(yt.languageList);		
			languageList.removeIf(el -> el.getText().trim().isEmpty());
			String langText=languageList.get(j).getText();
			System.out.println(langText+"    "+j);
			
			
	     		WebElement test=driver.findElement(yt.getLanguageElementByName(langText));
			
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
	
	@Test
	public void verifyingSideMenuLanguageAsInSettings2() throws InterruptedException {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(15));
		
		
		YtLandingPage yt=new YtLandingPage();
		yt.openingLandingPage();
		driver.findElement(yt.settingEllipsesButton).click();
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(yt.languageDropdownUnderSettings));
 		driver.findElement(yt.languageDropdownUnderSettings).click();
	    Thread.sleep(2000);
	    
	    
	    
	    String langList=driver.findElement(yt.languageList).getText();
		System.out.println(langList);
		
		List<WebElement> languageList=	driver.findElements(yt.languageList);
     	languageList.removeIf(el -> el.getText().trim().isEmpty());
	
     	
			
		for(int j=1;j<languageList.size();j++) {
			
		
			languageList=	driver.findElements(yt.languageList);		
			languageList.removeIf(el -> el.getText().trim().isEmpty());
			String langText=languageList.get(j).getText();
			System.out.println(langText+"    "+j);
			
			
	     		WebElement test=driver.findElement(yt.getLanguageElementByName(langText));
		     
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
