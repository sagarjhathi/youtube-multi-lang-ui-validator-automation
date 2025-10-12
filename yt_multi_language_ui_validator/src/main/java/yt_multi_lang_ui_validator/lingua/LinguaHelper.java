package main.java.yt_multi_lang_ui_validator.lingua;

import com.github.pemistahl.lingua.api.*;

import main.java.yt_multi_lang_ui_validator.driverManager.DriverManager;
import main.java.yt_multi_lang_ui_validator.logger.LoggerUtility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.Logger;

public class LinguaHelper {
	
	
	private final  Logger log=LoggerUtility.getLogger(LinguaHelper.class);

    public static String detectLanguage(String text) {
        if (text == null || text.trim().isEmpty()) {
            return "Unknown";
        }
        LanguageDetector detector = LanguageDetectorBuilder.fromAllLanguages().build();
        Language detected = detector.detectLanguageOf(text);
        System.out.printf("Text: %-60s | Detected: %s%n", text, detected);
        return detected != null ? detected.name() : "Unknown";
    }
    
}
