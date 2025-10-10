package main.java.yt_multi_lang_ui_validator.reporting;



import com.aventstack.extentreports.ExtentReports; 
import com.aventstack.extentreports.ExtentTest;

import java.util.HashMap;
import java.util.Map;

public class ExtentTestManager {

    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();
    private static Map<String, ExtentTest> testMap = new HashMap<>();


    
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

