package main.java.yt_multi_lang_ui_validator.reporting;


import com.aventstack.extentreports.MediaEntityBuilder; 
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.apache.logging.log4j.ThreadContext;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestListener implements ITestListener {

    private static final String RUN_TIMESTAMP = new SimpleDateFormat("yyyy-MM-dd_HHmmss").format(new Date());
    private static final String SCREENSHOT_BASE_DIR = System.getProperty("user.dir") + "/test-output/screenshots/Run_" + RUN_TIMESTAMP;

    

    
    @Override
    public void onTestStart(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        ExtentTestManager.startTest(testName).log(Status.INFO, "🔹 Test Started: " + testName);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
     //   ExtentTestManager.getTest().log(Status.PASS, "✅ Test Passed");
        attachLogFile();
        attachScreenshotFolder(result);
    }

    @Override
    public void onTestFailure(ITestResult result) {

        // Screenshot on failure
        String testName = result.getMethod().getMethodName();
        Throwable cause = result.getThrowable();

        // Log failure in Extent Report

        Throwable throwable = result.getThrowable();
        if (throwable != null) {
            String message = throwable.getMessage();

            if (message != null) {
                String formattedMessage = formatFailureMessage(message);
                ExtentTestManager.getTest().fail(formattedMessage);
            }
        }
        // No screenshot taken
        attachLogFile();             // 🔹 Still attach logs if needed
        attachScreenshotFolder(result); // 🔹 Still attach folder path for context if needed
    }

    @Override
    public void onTestSkipped(ITestResult result) {
    	if (result.getThrowable() != null) {
            ExtentTestManager.getTest().skip("Test Skipped: " + result.getThrowable().getMessage());
        } else {
            ExtentTestManager.getTest().skip("Test Skipped (No Exception)");
        }
        attachLogFile();
        attachScreenshotFolder(result);
    }

    @Override
    public void onFinish(ITestContext context) {
        ExtentManager.getInstance().flush();
    }

    private void attachLogFile() {
        try {
            String logFileName = ThreadContext.get("logFileName");
            if (logFileName != null) {
            	 String relativePath = "./logs/" + logFileName + ".log";
                String absolutePath = System.getProperty("user.dir") + "/logs/" + logFileName + ".log";
                String absolutePathNew = "C:/Sagar/google-shopping-aggregator-automation/eclipse-workspace/Intro/logs/" + logFileName + ".log";

                String fileUrl = "file:///" + absolutePathNew.replace("\\", "/");

                ExtentTestManager.getTest().info("📄 <a href='" + fileUrl + "' target='_blank'>Click to view log file</a>");
            }
        } catch (Exception e) {
            ExtentTestManager.getTest().warning("Failed to attach log file: " + e.getMessage());
        }
    }
    
    

    
    
    private void attachScreenshotFolder(ITestResult result) {
        try {
            String testName = result.getMethod().getMethodName();
            String relativeFolderPath = "./test-output/screenshots/Run_" + ExtentManager.RUN_TIMESTAMP + "/" + testName;
            String testFolderPath = System.getProperty("user.dir") + "/test-output/screenshots/Run_" + ExtentManager.RUN_TIMESTAMP + "/" + testName;
            File testFolder = new File(testFolderPath);

            if (testFolder.exists()) {
                StringBuilder html = new StringBuilder();
                html.append("<details><summary>📂 Open Screenshots</summary>");

                File[] files = testFolder.listFiles((dir, name) -> name.toLowerCase().endsWith(".png"));
                if (files != null && files.length > 0) {
                    for (File file : files) {
                        String filePath = file.getAbsolutePath().replace("\\", "/");
                        String fileUrl = "file:///" + filePath;
                        String fileName = file.getName(); // 📌 This is the screenshot file name

                        // Embed image with file name as label
                        html.append("<div style='margin-top:10px; border:1px solid #ccc; padding:5px;'>")
                            .append("<div style='font-weight:bold; margin-bottom:3px;'>📸 ").append(fileName).append("</div>")
                            .append("<a href='").append(fileUrl).append("' target='_blank'>")
                            .append("<img src='").append(fileUrl).append("' style='max-width:600px; border:1px solid #ddd;'/>")
                            .append("</a></div>");
                    }
                } else {
                    html.append("<div>No screenshots found</div>");
                }

                html.append("</details>");

                ExtentTestManager.getTest().info(html.toString());
                
            }
        } catch (Exception e) {
            ExtentTestManager.getTest().warning("Could not attach screenshot folder: " + e.getMessage());
        }
    }
   
    

    private String formatFailureMessage(String message) {
        // Optional: detect structured failure message by keywords
        if (message.contains("Brand filter") && message.contains("📦 Title:")) {

            // Split for better readability
            String[] lines = message.split("\n");
            String filter = lines[0]; // first line like Brand filter '...' not found

            // Build collapsible HTML
            StringBuilder html = new StringBuilder();
            html.append("<b>").append(filter).append("</b>");
            html.append("<details><summary>📄 Click to expand product details</summary>");

            for (int i = 1; i < lines.length; i++) {
                html.append(lines[i].replace("\n", "<br>")).append("<br>");
            }

            html.append("</details>");
            return html.toString();
        }

        // If no special format, return as-is
        return message;
    }


    

}

