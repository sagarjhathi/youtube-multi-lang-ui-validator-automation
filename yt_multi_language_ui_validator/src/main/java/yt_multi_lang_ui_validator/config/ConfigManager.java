package main.java.yt_multi_lang_ui_validator.config;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.InputStream;
import java.time.Duration;
import java.util.Locale;
import java.util.Properties;

/**
 * Simple and easy-to-understand configuration manager.
 * 
 * Loads values from:
 *   1. src/test/resources/utildata.properties
 * 
 * (If you later want, you can extend it to check system properties and env vars.)
 *
 * Usage:
 *   ConfigManager cfg = ConfigManager.getInstance();
 *   String url = cfg.getString("base.url");
 *   int wait = cfg.getInt("explicit.wait", 10);
 *   boolean headless = cfg.getBoolean("chrome.headless", false);
 */
public class ConfigManager {

    private static  final Logger log = LogManager.getLogger(ConfigManager.class);

    private static ConfigManager instance;
    private final Properties props = new Properties();

    // --- Constructor: load file once ---
    private ConfigManager() {
        loadProperties();
    }

    // --- Singleton accessor ---
    public static synchronized ConfigManager getInstance() {
        if (instance == null) {
            instance = new ConfigManager();
        }
        return instance;
    }

    // --- Load from src/test/resources/utildata.properties ---
    private void loadProperties() {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("masterdata.properties")) {
            if (input != null) {
                props.load(input);
                log.info("Loaded masterdata.properties from classpath ({} keys).", props.size());
                if (log.isDebugEnabled()) {
                    props.forEach((k, v) -> log.debug("{} = {}", k, v));
                }
            } else {
                log.warn("masterdata.properties not found in classpath! Using only defaults and hardcoded values.");
            }
        } catch (Exception e) {
            log.error("Error loading masterdata.properties: {}", e.getMessage(), e);
        }
    }

    // --- Internal helper: simple resolver ---
    private String resolveValue(String key) {
        String val = props.getProperty(key);
        if (val != null && !val.isEmpty()) {
            log.debug("Resolved key '{}' = '{}'", key, val);
            return val;
        } else {
            log.debug("Key '{}' not found in properties file.", key);
            return null;
        }
    }

    // --- Public getters ---

    public String getString(String key) {
        return resolveValue(key);
    }

    public String getString(String key, String defaultValue) {
        String v = resolveValue(key);
        if (v == null) {
            log.debug("Key '{}' missing, using default '{}'", key, defaultValue);
        }
        return v != null ? v : defaultValue;
    }

    public int getInt(String key, int defaultValue) {
        String v = resolveValue(key);
        try {
            int result = (v != null) ? Integer.parseInt(v.trim()) : defaultValue;
            log.debug("Integer key '{}' = {}", key, result);
            return result;
        } catch (NumberFormatException e) {
            log.warn("Invalid integer for key '{}': '{}'. Using default {}", key, v, defaultValue);
            return defaultValue;
        }
    }

    public boolean getBoolean(String key, boolean defaultValue) {
        String v = resolveValue(key);
        if (v == null) {
            log.debug("Boolean key '{}' not found. Using default {}", key, defaultValue);
            return defaultValue;
        }
        boolean result = v.equalsIgnoreCase("true") || v.equalsIgnoreCase("yes") || v.equals("1");
        log.debug("Boolean key '{}' = {}", key, result);
        return result;
    }

    public Duration getDuration(String key, int defaultSeconds) {
        String v = resolveValue(key);
        try {
            Duration result = (v != null)
                    ? Duration.ofSeconds(Long.parseLong(v.trim()))
                    : Duration.ofSeconds(defaultSeconds);
            log.debug("Duration key '{}' = {} seconds", key, result.getSeconds());
            return result;
        } catch (Exception e) {
            log.warn("Invalid duration for key '{}': '{}'. Using default {}s", key, v, defaultSeconds);
            return Duration.ofSeconds(defaultSeconds);
        }
    }

    // --- Debug helper ---
    public void printAll() {
        log.info("------ Loaded Config ({} entries) ------", props.size());
        props.forEach((k, v) -> log.info("{} = {}", k, v));
        log.info("----------------------------------------");
    }
}
