package main.java.yt_multi_lang_ui_validator.driverManager;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import main.java.yt_multi_lang_ui_validator.config.ConfigManager;
import main.java.yt_multi_lang_ui_validator.logger.LoggerUtility;

public class DriverManager {

	 // Thread-local ensures driver is isolated per parallel thread/test
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    // Logger for this class
    private static final Logger log = LoggerUtility.getLogger(DriverManager.class);

    /** Get current thread's WebDriver instance */
    public static WebDriver getDriver() {
        return driver.get();
    }

    /** Initialize a new WebDriver instance if not already present for this thread */
    public static void initDriver() {
        if (driver.get() == null) {
            log.info("No existing WebDriver found for current thread. Initializing a new ChromeDriver...");

            try {
                ChromeOptions options = new ChromeOptions();

                // Browser startup configurations
                options.addArguments("--start-maximized");
                options.addArguments("--disable-gpu");
                options.addArguments("--disable-blink-features=AutomationControlled");
                options.addArguments("--disable-dev-shm-usage");
                options.addArguments("--no-sandbox");
                options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
                options.setExperimentalOption("useAutomationExtension", false);
                options.addArguments("--disable-extensions");

                // Initialize and store driver for this thread
                WebDriver chromeDriver = new ChromeDriver(options);
                driver.set(chromeDriver);
                driver.get().manage().deleteAllCookies();

                log.info("ChromeDriver initialized successfully for thread: {}", Thread.currentThread().threadId());
            } catch (Exception e) {
                log.error("Failed to initialize WebDriver: {}", e.getMessage());
                throw new RuntimeException("WebDriver initialization failed", e);
            }
        } else {
            log.info("Reusing existing WebDriver instance for thread: {}", Thread.currentThread().threadId());
        }
    }

    /** Quit and clean up WebDriver for current thread */
    public static void quitDriver() {
        WebDriver currentDriver = driver.get();

        if (currentDriver != null) {
            try {
                log.info("Closing WebDriver for thread: {}", Thread.currentThread().threadId());
                currentDriver.quit();
            } catch (Exception e) {
                log.error("Error while quitting WebDriver for thread {}: {}", Thread.currentThread().threadId(), e.getMessage());
            } finally {
                driver.remove();
                log.info("WebDriver instance removed from ThreadLocal for thread: {}", Thread.currentThread().threadId());
            }
        } else {
            log.warn("quitDriver() called but no WebDriver was found for thread: {}", Thread.currentThread().threadId());
        }
    }
	
	
	
}
