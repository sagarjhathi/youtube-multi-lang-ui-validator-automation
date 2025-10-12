package main.java.yt_multi_lang_ui_validator.config;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import org.apache.logging.log4j.Logger;

import main.java.yt_multi_lang_ui_validator.base.BasePage;
import main.java.yt_multi_lang_ui_validator.logger.LoggerUtility;

public final class ConfigManager {
    private static final Properties props = new Properties();
    private static final String RESOURCE = "UtilData.properties";

    private static final  Logger log=LoggerUtility.getLogger(ConfigManager.class);
    static {
        // 1) External file override (useful for CI): -Dconfig.file=/path/UtilData.properties
        String external = System.getProperty("config.file");
        if (external != null && !external.isBlank()) {
            try (FileInputStream fis = new FileInputStream(external)) {
                props.load(fis);
                System.out.println("Loaded config from external file: " + external);
            } catch (IOException e) {
                throw new RuntimeException("Failed to load external config: " + external, e);
            }
        } else {
            // 2) Try classpath resource (src/test/resources/UtilData.properties)
            try (InputStream in = ConfigManager.class.getClassLoader().getResourceAsStream(RESOURCE)) {
                if (in != null) {
                    props.load(in);
                    System.out.println("Loaded config from classpath: " + RESOURCE);
                } else {
                    System.out.println("Config not found on classpath: " + RESOURCE);
                }
            } catch (IOException e) {
                throw new RuntimeException("Failed to load config from classpath: " + RESOURCE, e);
            }
        }
    }

    private ConfigManager() { /* utility class */ }

    /** Simple get: system property -> env var -> properties file */
    public static String get(String key) {
        String sys = System.getProperty(key);
        System.out.printf("ConfigManager.get(): %s = %s%n", key, sys);
        if (sys != null) return sys;
        String env = System.getenv(key.toUpperCase().replace('.', '_'));
        if (env != null) return env;
        return props.getProperty(key);
    }

    public static String get(String key, String defaultValue) {
        String v = get(key);
        System.out.printf("ConfigManager.get(): %s = %s%n", key, v);

        return v != null ? v : defaultValue;
    }

    public static boolean getBoolean(String key, boolean defaultValue) {
        String v = get(key);
        System.out.printf("ConfigManager.get(): %s = %s%n", key, v);

        return v == null || v.isBlank() ? defaultValue : Boolean.parseBoolean(v.trim());
    }

    public static int getInt(String key, int defaultValue) {
        String v = get(key);
        System.out.printf("ConfigManager.get(): %s = %s%n", key, v);

        if (v == null || v.isBlank()) return defaultValue;
        try { return Integer.parseInt(v.trim()); } catch (NumberFormatException e) { return defaultValue; }
    }
 
}



