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

	
	private static final  Logger log=LoggerUtility.getLogger(ExtentManager.class);
	
	 public static ExtentReports extent;
	 public static final String RUN_TIMESTAMP = new SimpleDateFormat("yyyy-MM-dd_HHmm").format(new Date());
	 public static final String BASE_SCREENSHOT_DIR = System.getProperty("user.dir") + "/test-output/ExtentReports/screenshots/Run_" + RUN_TIMESTAMP;

    
   
    public static ExtentReports getInstance() {
        if (extent == null) {
            extent = createInstance();
        }
        return extent;
    }

    private static ExtentReports createInstance() {
        String reportPath = "test-output/ExtentReports/ExtentReport.html";

        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
        sparkReporter.config().setDocumentTitle("Automation Test Report");
        sparkReporter.config().setReportName("Youtube multi lang ui validator");
        sparkReporter.config().setTheme(Theme.STANDARD); // Use Theme.DARK for dark mode

        ExtentReports extent = new ExtentReports();
        extent.attachReporter(sparkReporter);

        // Optional system info
        extent.setSystemInfo("OS", System.getProperty("os.name"));
        extent.setSystemInfo("Java Version", System.getProperty("java.version"));
        extent.setSystemInfo("Tester", "Sagar Hathi");

        return extent;
    }
    
    public synchronized static void flush() {
        if (extent != null) {
            extent.flush();
        }
    }
}


