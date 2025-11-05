package main.java.yt_multi_lang_ui_validator.base;

import java.lang.reflect.Method;  

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import main.java.yt_multi_lang_ui_validator.driverManager.DriverManager;
import main.java.yt_multi_lang_ui_validator.logger.LoggerUtility;

@Listeners(main.java.yt_multi_lang_ui_validator.reporting.TestListener.class)
public class BaseTest {

	//This logger is at framework / class level currently nothing is being logged at class level so loggers at test level are created and used.
	private  static  Logger log = LoggerUtility.getLogger(BaseTest.class);
	public WebDriver driver;

	    @BeforeMethod
	    public void setUp(Method method) {
	       
	    	
	    	    String testName = method.getName();
	    	    String threadId = String.valueOf(Thread.currentThread().threadId());
	    	    String logFileName = testName;

	    	    ThreadContext.put("logFileName", logFileName);
	    	    ThreadContext.put("testName", testName);
	    	    ThreadContext.put("threadId", threadId);
	    	
	    	    // Get a logger specifically for this test method
	            Logger testLog = LogManager.getLogger(testName);
	            testLog.info("===== STARTING TEST: {}  | Thread: {} =====", testName, threadId);
	    	    
	    	    
	            // Initialize WebDriver
	            try {
	                DriverManager.initDriver();
	                driver = DriverManager.getDriver();
	                testLog.info("Driver initialized successfully for test: {}", testName);
	            } catch (Exception e) {
	            	testLog.error("Failed to initialize WebDriver for test: {}", testName, e);
	                throw e;
	            }
	    }
	 
	
	    
	    
	    	@AfterMethod
	    	public void tearDown(ITestResult result) {
	    		
	    		 Logger testLog = LogManager.getLogger(result.getName());
	    		 
	    	    try {
	    	        switch (result.getStatus()) {
	    	            case ITestResult.FAILURE ->
	    	                  testLog.error("Test FAILED: {} - {}", result.getName(), result.getThrowable());
	    	            case ITestResult.SKIP ->
	    	                  testLog.warn("Test SKIPPED: {}", result.getName());
	    	            default ->
	    	                  testLog.info("Test PASSED: {}", result.getName());
	    	        }
	    	    } catch (Exception e) {
	    	    	testLog.error("Error while logging test result: {}", e.getMessage(), e);
	    	    } finally {
	    	        try {
	    	            DriverManager.quitDriver();
	    	            testLog.info("Driver closed for test: {}", result.getName());
	    	        } catch (Exception e) {
	    	        	 testLog.error("Error while quitting driver: {}", e.getMessage(), e);
	    	        } finally {
	    	            ThreadContext.clearAll();
	    	            testLog.info("===== FINISHED TEST: {} =====", result.getName());
	    	        }
	    	    }
	    	    	    	    
	    	}
	
	    }
	   

