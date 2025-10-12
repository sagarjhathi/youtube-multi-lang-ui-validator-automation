package main.java.yt_multi_lang_ui_validator.reporting;



import com.aventstack.extentreports.ExtentReports; 
import com.aventstack.extentreports.ExtentTest;

import main.java.yt_multi_lang_ui_validator.logger.LoggerUtility;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.Logger;

public class ExtentTestManager {

    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();
    private static Map<String, ExtentTest> testMap = new HashMap<>();
    private static final  Logger log=LoggerUtility.getLogger(ExtentTestManager.class);

    
    public static synchronized ExtentTest startTest(String testName) {
        ExtentReports extent = ExtentManager.getInstance();
        ExtentTest test = extent.createTest(testName);
        extentTest.set(test);
        testMap.put(testName, test);
        return test;
    }

    public static synchronized ExtentTest getTest() {
        return extentTest.get();
    }
}

