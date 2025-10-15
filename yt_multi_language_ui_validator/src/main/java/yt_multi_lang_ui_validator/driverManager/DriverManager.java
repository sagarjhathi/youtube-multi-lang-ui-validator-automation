package main.java.yt_multi_lang_ui_validator.driverManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import io.github.bonigarcia.wdm.WebDriverManager;
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
            	
//            	ConfigManager cfg = ConfigManager.getInstance();  
//                String browser = cfg.getString("browser", "chrome");
            	
//                ChromeOptions options = new ChromeOptions();
//
//                List<String> appliedFlags = new ArrayList<>();
//
//                if (cfg.getBoolean("chrome.arg.start_maximized", true)) {
//                    options.addArguments("--start-maximized");
//                    appliedFlags.add("--start-maximized");
//                }
//                if (cfg.getBoolean("chrome.arg.disable_gpu", true)) {
//                    options.addArguments("--disable-gpu");
//                    appliedFlags.add("--disable-gpu");
//                }
//                if (cfg.getBoolean("chrome.arg.disable_blink_features_automation_controlled", true)) {
//                    options.addArguments("--disable-blink-features=AutomationControlled");
//                    appliedFlags.add("--disable-blink-features=AutomationControlled");
//                }
//                if (cfg.getBoolean("chrome.arg.disable_dev_shm_usage", true)) {
//                    options.addArguments("--disable-dev-shm-usage");
//                    appliedFlags.add("--disable-dev-shm-usage");
//                }
//                if (cfg.getBoolean("chrome.arg.no_sandbox", true)) {
//                    options.addArguments("--no-sandbox");
//                    appliedFlags.add("--no-sandbox");
//                }
//                if (cfg.getBoolean("chrome.arg.disable_extensions", true)) {
//                    options.addArguments("--disable-extensions");
//                    appliedFlags.add("--disable-extensions");
//                }
//
//                // 👇 Simple one-line log — easy to read in console or log file
//                log.info("Chrome flags applied: {}", String.join(", ", appliedFlags));
//               
//
//                // Initialize and store driver for this thread
//                WebDriver chromeDriver = new ChromeDriver(options);
//                driver.set(chromeDriver);
//                driver.get().manage().deleteAllCookies();
//
//                log.info("ChromeDriver initialized successfully for thread: {}", Thread.currentThread().threadId());
//            } catch (Exception e) {
//                log.error("Failed to initialize WebDriver: {}", e.getMessage());
//                throw new RuntimeException("WebDriver initialization failed", e);
          
            	
            	
            	ConfigManager cfg = ConfigManager.getInstance();
            	String browser = cfg.getString("browser", "chrome");
            	if (browser != null) browser = browser.trim().toLowerCase(Locale.ENGLISH);

            	switch (browser) {
            	    case "firefox": {
            	        // ---------- Firefox ----------
            	        org.openqa.selenium.firefox.FirefoxOptions firefoxOptions = new org.openqa.selenium.firefox.FirefoxOptions();

            	        if (cfg.getBoolean("firefox.arg.disable_gpu", true)) {
            	            firefoxOptions.addArguments("--disable-gpu");
            	        }
            	        if (cfg.getBoolean("firefox.arg.disable_dev_shm_usage", true)) {
            	            firefoxOptions.addArguments("--disable-dev-shm-usage");
            	        }
            	        if (cfg.getBoolean("firefox.arg.no_sandbox", true)) {
            	            firefoxOptions.addArguments("--no-sandbox");
            	        }
            	        if (cfg.getBoolean("firefox.arg.disable_extensions", true)) {
            	            firefoxOptions.addArguments("--disable-extensions");
            	        }
            	        if (cfg.getBoolean("firefox.headless", false)) {
            	            firefoxOptions.addArguments("-headless");
            	        }

            	        // ensure driver binary available (try WebDriverManager, fallback to local path)
            	        try {
            	            io.github.bonigarcia.wdm.WebDriverManager.firefoxdriver().setup();
            	            log.info("WebDriverManager: geckodriver setup OK");
            	        } catch (Exception e) {
            	            log.warn("WDM geckodriver setup failed: {}. Will try local fallback if configured.", e.getMessage());
            	            String local = cfg.getString("webdriver.firefox.local.path", "");
            	            if (!local.isBlank()) System.setProperty("webdriver.gecko.driver", local);
            	        }

            	        WebDriver firefoxDriver = new org.openqa.selenium.firefox.FirefoxDriver(firefoxOptions);
            	        driver.set(firefoxDriver);
            	        try { firefoxDriver.manage().deleteAllCookies(); } catch (Exception ignored) {}
            	        // maximize explicitly for Firefox (more reliable)
            	        if (cfg.getBoolean("firefox.arg.start_maximized", true)) {
            	            try { firefoxDriver.manage().window().maximize(); } catch (Exception ignored) {}
            	        }
            	        log.info("FirefoxDriver initialized successfully for thread: {}", Thread.currentThread().threadId());
            	        break;
            	    }

            	    case "edge": {
            	        // ---------- Edge ----------
            	        org.openqa.selenium.edge.EdgeOptions edgeOptions = new org.openqa.selenium.edge.EdgeOptions();

            	        if (cfg.getBoolean("edge.arg.start_maximized", true)) {
            	            edgeOptions.addArguments("--start-maximized");
            	        }
            	        if (cfg.getBoolean("edge.arg.disable_gpu", true)) {
            	            edgeOptions.addArguments("--disable-gpu");
            	        }
            	        if (cfg.getBoolean("edge.arg.disable_dev_shm_usage", true)) {
            	            edgeOptions.addArguments("--disable-dev-shm-usage");
            	        }
            	        if (cfg.getBoolean("edge.arg.no_sandbox", true)) {
            	            edgeOptions.addArguments("--no-sandbox");
            	        }
            	        if (cfg.getBoolean("edge.arg.disable_extensions", true)) {
            	            edgeOptions.addArguments("--disable-extensions");
            	        }

            	        boolean useExtEdge = cfg.getBoolean("edge.use.automation.extension", false);
            	        edgeOptions.setExperimentalOption("useAutomationExtension", useExtEdge);

            	        try {
            	            io.github.bonigarcia.wdm.WebDriverManager.edgedriver().setup();
            	            log.info("WebDriverManager: edgedriver setup OK");
            	        } catch (Exception e) {
            	            log.warn("WDM edgedriver setup failed: {}. Will try local fallback if configured.", e.getMessage());
            	            String local = cfg.getString("webdriver.edge.local.path", "");
            	            if (!local.isBlank()) System.setProperty("webdriver.edge.driver", local);
            	        }

            	        WebDriver edgeDriver = new org.openqa.selenium.edge.EdgeDriver(edgeOptions);
            	        driver.set(edgeDriver);
            	        try { edgeDriver.manage().deleteAllCookies(); } catch (Exception ignored) {}
            	        log.info("EdgeDriver initialized successfully for thread: {}", Thread.currentThread().threadId());
            	        break;
            	    }

            	    case "chrome":
            	    default: {
            	        // ---------- Chrome ----------
            	        org.openqa.selenium.chrome.ChromeOptions options = new org.openqa.selenium.chrome.ChromeOptions();

            	        if (cfg.getBoolean("chrome.arg.start_maximized", true)) {
            	            options.addArguments("--start-maximized");
            	        }
            	        if (cfg.getBoolean("chrome.arg.disable_gpu", true)) {
            	            options.addArguments("--disable-gpu");
            	        }
            	        if (cfg.getBoolean("chrome.arg.disable_blink_features_automation_controlled", true)) {
            	            options.addArguments("--disable-blink-features=AutomationControlled");
            	        }
            	        if (cfg.getBoolean("chrome.arg.disable_dev_shm_usage", true)) {
            	            options.addArguments("--disable-dev-shm-usage");
            	        }
            	        if (cfg.getBoolean("chrome.arg.no_sandbox", true)) {
            	            options.addArguments("--no-sandbox");
            	        }
            	        if (cfg.getBoolean("chrome.arg.disable_extensions", true)) {
            	            options.addArguments("--disable-extensions");
            	        }
            	        // optional experimental options
            	        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
            	        options.setExperimentalOption("useAutomationExtension", false);

            	        try {
            	            io.github.bonigarcia.wdm.WebDriverManager.chromedriver().setup();
            	            log.info("WebDriverManager: chromedriver setup OK");
            	        } catch (Exception e) {
            	            log.warn("WDM chromedriver setup failed: {}. Will try local fallback if configured.", e.getMessage());
            	            String local = cfg.getString("webdriver.chrome.local.path", "");
            	            if (!local.isBlank()) System.setProperty("webdriver.chrome.driver", local);
            	        }

            	        WebDriver chromeDriver = new org.openqa.selenium.chrome.ChromeDriver(options);
            	        driver.set(chromeDriver);
            	        try { chromeDriver.manage().deleteAllCookies(); } catch (Exception ignored) {}
            	        log.info("ChromeDriver initialized successfully for thread: {}", Thread.currentThread().threadId());
            	        break;
            	    }
            	}

                
                
            }catch(Exception e) {
            	log.error("Failed to initialize WebDriver: {}", e.getMessage(), e);
                throw new RuntimeException("WebDriver initialization failed", e);
            }
            
        } 
         else {
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
