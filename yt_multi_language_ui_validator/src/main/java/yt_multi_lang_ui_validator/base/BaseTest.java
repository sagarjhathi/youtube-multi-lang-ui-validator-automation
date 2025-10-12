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

	
	private   Logger log = LoggerUtility.getLogger(BaseTest.class);
	public WebDriver driver;

	    @BeforeMethod
	    public void setUp(Method method) {
	        // ✅ Assign unique thread name for routing log
	    	    String testName = method.getName(); // The actual test method name
	    	    String threadName = testName + "-" + Thread.currentThread().threadId();
	    	    String logName = method.getName() + "_" + Thread.currentThread().getId();
//	    	    ThreadContext.put("logFileName", logName);  // ✅ Very important
//	    	    ThreadContext.put("threadName", threadName); // Used in file name routing (if needed)
//	    	    ThreadContext.put("testName", testName);     // ✅ Add this for use in logs
//	    	    ThreadContext.put("logFileName", testName); // ✅ must come before logger is called
	    	    log = LogManager.getLogger(testName); 
	    	    System.out.println("🧪 logFileName: " + ThreadContext.get("logFileName"));	    	   
	    	    log.info("🔹 Starting test method: " + testName);
	    	    
	        DriverManager.initDriver();
	        driver = DriverManager.getDriver();
	    }
	 
	    @AfterMethod
	    public void tearDown(ITestResult result) {
	        log.info("✅ Finished test method: " + result.getName());
	        ThreadContext.clearAll();  // ✅ Critical to avoid context bleed
	        DriverManager.quitDriver();
	    }
	   
}
