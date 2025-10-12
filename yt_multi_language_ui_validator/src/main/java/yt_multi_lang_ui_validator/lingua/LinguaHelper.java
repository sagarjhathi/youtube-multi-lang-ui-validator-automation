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
	
	
	  private static final Logger log = LoggerUtility.getLogger(LinguaHelper.class);

	    // Build detector once — expensive to create, cheap to reuse.
	    private static final LanguageDetector DETECTOR =
	            LanguageDetectorBuilder.fromAllLanguages().build();

	    private LinguaHelper() {
	        // Prevent instantiation
	    }

	    /**
	     * Detect the language of the given text.
	     * Returns "Unknown" if detection fails or text is blank.
	     */
	    public static String detectLanguage(String text) {
	        try {
	            if (text == null || text.trim().isEmpty()) {
	                log.debug("detectLanguage called with empty text.");
	                return "Unknown";
	            }

	            // Clean up text input
	            String cleanText = text.trim();

	            // Detect language
	            Language detected = DETECTOR.detectLanguageOf(cleanText);
	            if (detected == null) {
	                log.debug("No language detected for text: {}", cleanText);
	                return "Unknown";
	            }

	            log.info("Detected language: {} for sample: {}", detected.name(), shorten(cleanText));
	            return detected.name(); // e.g. "ENGLISH", "SPANISH", etc.
	        } catch (Exception e) {
	            log.error("Language detection failed: {}", e.getMessage());
	            return "Unknown";
	        }
	    }

	    /** Utility to log only a small preview of the text */
	    private static String shorten(String text) {
	        return text.length() > 60 ? text.substring(0, 60) + "..." : text;
	    }
    
}
