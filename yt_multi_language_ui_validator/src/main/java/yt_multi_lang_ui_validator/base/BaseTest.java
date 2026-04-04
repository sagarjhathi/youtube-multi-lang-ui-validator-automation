package main.java.yt_multi_lang_ui_validator.base;

import java.io.File;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import main.java.yt_multi_lang_ui_validator.driverManager.DriverManager;
import main.java.yt_multi_lang_ui_validator.logger.LoggerUtility;
import main.java.yt_multi_lang_ui_validator.pathManager.PathManager;
import main.java.yt_multi_lang_ui_validator.reporting.ReportManager;

public class BaseTest {

//	private  static  Logger log = LoggerUtility.getLogger(BaseTest.class);
//	public WebDriver driver;
//
//	
//	    @BeforeMethod(alwaysRun = true)
//	    public void setUp(Method method) throws InterruptedException {
//	    		    	
//	    	    String testName = method.getName();
//	    	    String threadId = String.valueOf(Thread.currentThread().threadId());
//	    	    String logFileName = testName;
//
//	    	    ThreadContext.put("logFileName", logFileName);
//	    	    ThreadContext.put("testName", testName);
//	    	    ThreadContext.put("threadId", threadId);
//
//	            Logger testLog = LogManager.getLogger(testName);
//	            testLog.info("===== STARTING TEST: {}  | Thread: {} =====", testName, threadId);
//	    	    
//	    	    
//	            // Initialize WebDriver
//	            try {
//	                DriverManager.initDriver();
//	                driver = DriverManager.getDriver();
//	                testLog.info("Driver initialized successfully for test: {}", testName);
//	            } catch (Exception e) {
//	            	testLog.error("Failed to initialize WebDriver for test: {}", testName, e);
//	                throw e;
//	            }
//	    }
//	 
//	
//	    
//	    
//	    	@AfterMethod(alwaysRun = true)
//	    	public void tearDown(ITestResult result) {
//	    		
//	    		 Logger testLog = LogManager.getLogger(result.getName());
//	    		 
//	    	    try {
//	    	        switch (result.getStatus()) {
//	    	            case ITestResult.FAILURE ->
//	    	                  testLog.error("Test FAILED: {} - {}", result.getName(), result.getThrowable());
//	    	            case ITestResult.SKIP ->
//	    	                  testLog.warn("Test SKIPPED: {}", result.getName());
//	    	            default ->
//	    	                  testLog.info("Test PASSED: {}", result.getName());
//	    	        }
//	    	    } catch (Exception e) {
//	    	    	testLog.error("Error while logging test result: {}", e.getMessage(), e);
//	    	    } finally {
//	    	        try {
//	    	            DriverManager.quitDriver();
//	    	            testLog.info("Driver closed for test: {}", result.getName());
//	    	        } catch (Exception e) {
//	    	        	 testLog.error("Error while quitting driver: {}", e.getMessage(), e);
//	    	        } finally {
//	    	            ThreadContext.clearAll();
//	    	            testLog.info("===== FINISHED TEST: {} =====", result.getName());
//	    	        }
//	    	    }
//	    	    	    	    
//	    	}
	
	
	
	
	public WebDriver driver;
	  
	  @BeforeSuite(alwaysRun = true)
	  public void createRunFolder() {
		  
	      String timestamp = LocalDateTime.now()
      .format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
	      
	      String runPath= System.getProperty("user.dir")
	              + File.separator + "run_" + timestamp;
	      
	      System.out.println(runPath+"    RUN PATH HERE FOR TESTING");
	      
	      
		  PathManager.setRunFolderPath(runPath);
	      ReportManager.initReport(runPath);
	 
	      File runFolder = new File(runPath);
	      runFolder.mkdirs();
	  }
	  
	  
	  
	  
	  
	  
	  @BeforeMethod(alwaysRun = true)
	  public void beforeTest(ITestResult result) {

	      String baseName = result.getMethod().getMethodName();
	      
	      System.out.println(baseName+"  =======  BASE NAME HERE ");
	      String params = Arrays.toString(result.getParameters())
	              .replaceAll("[\\[\\] ]", "")
	              .replace(",", "_");

	      String timestamp = LocalDateTime.now()
	              .format(DateTimeFormatter.ofPattern("HH-mm-ss"));

	      String testName = baseName 
	              + (params.isEmpty() ? "" : "_" + params)
	              + "_" + timestamp;

	      
	      String logPathName = PathManager.getLogPath(testName);

	      // ✅ SET CONTEXT FIRST (VERY IMPORTANT)
	      ThreadContext.put("testNameShort", params);
	      ThreadContext.put("logFileName", testName);
	      ThreadContext.put("logPath", logPathName);
	      ThreadContext.put("testName", testName);
	      ThreadContext.put("baseTestName",baseName);

	      // ✅ Ensure folder exists BEFORE logging
	      new File(logPathName).mkdirs();

	      // Now safe to do anything else
	      DriverManager.initDriver();
	      driver = DriverManager.getDriver();

	      String path = PathManager.getRunFolderPath()
	              + File.separator + testName;

	      PathManager.setTestFolderPath(path);
	  }
	    
	  
	  
	  
	  
	  
	  @AfterMethod(alwaysRun = true)
	  public void afterTest(ITestResult result) {
		  
	      String testName = ThreadContext.get("testName");
	      System.out.println(testName+"    checking the test name being null");
	      	      
	      System.out.println("Looking logs in: " + PathManager.getLogPath(testName));
	      System.out.println("Looking screenshots in: " + PathManager.getScreenshotPath(testName));
	     
	      
	      ThreadContext.clearAll();
	      PathManager.clearTestFolder();
	      DriverManager.quitDriver();
	  }


	  
	  
	    }
	   

