package main.java.yt_multi_lang_ui_validator.reporting;



import java.text.SimpleDateFormat; 
import java.util.Date;

import org.apache.logging.log4j.Logger;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import main.java.yt_multi_lang_ui_validator.logger.LoggerUtility;
import main.java.yt_multi_lang_ui_validator.pages.YtInnerPage;

public class ExtentManager {

	
	 private static ExtentReports extent; 
		
	    public static final String RUN_TIMESTAMP =
	        (System.getProperty("runTimestamp") != null && !System.getProperty("runTimestamp").isEmpty())
	            ? System.getProperty("runTimestamp")
	            : new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());

	    
	    public static final String BASE_SCREENSHOT_DIR = System.getProperty("user.dir") + "/test-output/ExtentReports/screenshots/Run_" + RUN_TIMESTAMP;
	    public static final String REPORT_PATH = System.getProperty("user.dir") + "/test-output/ExtentReports/ExtentReport.html";

	   
	    public synchronized static ExtentReports getInstance() {
	        if (extent == null) {
	        	
	   
	            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(REPORT_PATH);
	            sparkReporter.config().setDocumentTitle("Automation Report");
	            sparkReporter.config().setReportName("Youtube multi lang ui validator");
	            sparkReporter.config().setTheme(Theme.STANDARD);
	            
	            extent = new ExtentReports();
	            extent.attachReporter(sparkReporter);

	            extent.setSystemInfo("OS", System.getProperty("os.name"));
	            extent.setSystemInfo("Java Version", System.getProperty("java.version"));
	            extent.setSystemInfo("Tester", "Sagar Hathi");
	            System.setProperty("logs.dir", System.getProperty("user.dir") + "/logs/run_" + ExtentManager.RUN_TIMESTAMP);

	        }
	        return extent;
	    }

	   
	    public synchronized static void flush() {
	        if (extent != null) {
	            extent.flush();
	        }
	    }
}


