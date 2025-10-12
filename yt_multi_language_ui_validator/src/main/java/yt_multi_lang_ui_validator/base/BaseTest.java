package main.java.yt_multi_lang_ui_validator.base;

import java.lang.reflect.Method; 

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import main.java.yt_multi_lang_ui_validator.driverManager.DriverManager;
import main.java.yt_multi_lang_ui_validator.logger.LoggerUtility;


public class BaseTest {

	
	private  static  Logger log = LoggerUtility.getLogger(BaseTest.class);
	public WebDriver driver;

	    @BeforeMethod
	    public void setUp(Method method) {
	       
	    	
	    	    String testName = method.getName();
	    	    String threadId = String.valueOf(Thread.currentThread().threadId());
	    	    String logFileName = testName + "_" + threadId;

	    	    ThreadContext.put("logFileName", logFileName);
	    	    ThreadContext.put("testName", testName);
	    	    ThreadContext.put("threadId", threadId);
	    	
	    	    log = LogManager.getLogger(testName); 
	    	    log.info("Starting test: " + testName + " on thread " + threadId);	    	   
	    	    
	    	    
	            DriverManager.initDriver();
	            driver = DriverManager.getDriver();
	    }
	 
	
	    	@AfterMethod
	    	public void tearDown(ITestResult result) {
	    	    try {
	    	        switch (result.getStatus()) {
	    	            case ITestResult.FAILURE ->
	    	                log.error("Test FAILED: {} - {}", result.getName(), result.getThrowable());
	    	            case ITestResult.SKIP ->
	    	                log.warn("Test SKIPPED: {}", result.getName());
	    	            default ->
	    	                log.info("Test PASSED: {}", result.getName());
	    	        }
	    	    } catch (Exception e) {
	    	        System.err.println("Error while logging test result: " + e.getMessage());
	    	    } finally {
	    	        try {
	    	            DriverManager.quitDriver();
	    	        } catch (Exception e) {
	    	            System.err.println("Error while quitting driver: " + e.getMessage());
	    	        } finally {
	    	            ThreadContext.clearAll();
	    	        }
	    	    }
	    	    	    	    
	    	}
	
	    }
	   

