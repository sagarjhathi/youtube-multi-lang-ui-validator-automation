package main.java.yt_multi_lang_ui_validator.utilities;

import java.io.File;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.logging.log4j.ThreadContext;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import main.java.yt_multi_lang_ui_validator.driverManager.DriverManager;
import main.java.yt_multi_lang_ui_validator.pathManager.PathManager;

public class ScreenshotUtilUpdated {

	
	
	
	
	WebDriver driver;
	
	 public static String capture(String... names) {
	        WebDriver driver = DriverManager.getDriver(); // ✅ get from ThreadLocal

	        try {
	
	        	String testName = ThreadContext.get("testName");

	        	String screenShotPath = PathManager.getScreenshotPath(testName);

	        	// create folder
	        	File folder = new File(screenShotPath);
	        	folder.mkdirs();

	        	// unique filename
	        	String fileName = testName + "_" +
	        	        LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH-mm-ss-SSS")) +
	        	        "_" + System.nanoTime() + ".png";

	        	
	        	String finalPath= screenShotPath + File.separator + ThreadContext.get("testNameShort")+System.nanoTime()+".png";
	        		System.out.println(finalPath+"   -->> final path if param exist");
	        	
	        //	String finalPath = screenShotPath + File.separator + fileName;
	        	
	        	
	       
	        	// capture
	        	File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	        	File dest = new File(finalPath);

	        	Files.copy(src.toPath(), dest.toPath());

	        	return finalPath;

	        } catch (Exception e) {
	            System.out.println("Screenshot failed: " + e.getMessage()+ThreadContext.get("testName"));
	            return null;
	        }
	        
	    }
}
