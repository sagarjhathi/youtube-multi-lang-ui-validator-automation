package tests;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
		
		yt.clickingSettingEllipsesButton();
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(yt.languageDropdownUnderSettings));
		yt.clickingLanguageDropdownButton();
	    Thread.sleep(2000);
	    
	    
		List<String> languageList=new ArrayList<>();
		languageList.add("Afrikaans");
		languageList.add("Azərbaycan");
		languageList.add("Bahasa Indonesia");
		
		yt.applyingAllLanguagesFromListTrial();
		
	}
	
	

//	@Test
//	public void verifyingSideMenuLanguageAsInSettings2() throws InterruptedException {
//		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(15));
//		YtLandingPage yt=new YtLandingPage();
//		yt.openingLandingPage();
//		
//		yt.clickingSettingEllipsesButton();
//		Thread.sleep(2000);
//		wait.until(ExpectedConditions.elementToBeClickable(yt.languageDropdownUnderSettings));
//		yt.clickingLanguageDropdownButton();
//	    Thread.sleep(2000);
//	    
//		List<WebElement> languageList=	yt.gettingLanguageList();     		
//		yt.applyingAllLanguagesFromList();
//		
//	}
//	
//	@Test
//	public void verifyingSideMenuLanguageAsInSettings3() throws InterruptedException {
//		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(15));
//		YtLandingPage yt=new YtLandingPage();
//		yt.openingLandingPage();
//		
//		yt.clickingSettingEllipsesButton();
//		Thread.sleep(2000);
//		wait.until(ExpectedConditions.elementToBeClickable(yt.languageDropdownUnderSettings));
//		yt.clickingLanguageDropdownButton();
//	    Thread.sleep(2000);
//	    
//		List<WebElement> languageList=	yt.gettingLanguageList();     		
//		yt.applyingAllLanguagesFromList();
//		
//	}
//	
//	
//	@Test
//	public void verifyingSideMenuLanguageAsInSettings4() throws InterruptedException {
//		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(15));
//		YtLandingPage yt=new YtLandingPage();
//		yt.openingLandingPage();
//		
//		yt.clickingSettingEllipsesButton();
//		Thread.sleep(2000);
//		wait.until(ExpectedConditions.elementToBeClickable(yt.languageDropdownUnderSettings));
//		yt.clickingLanguageDropdownButton();
//	    Thread.sleep(2000);
//	    
//		List<WebElement> languageList=	yt.gettingLanguageList();     		
//		yt.applyingAllLanguagesFromList();
//		
//	}
	

	
	
}
