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

	            String cleanText = text.trim();

	            Language detected = DETECTOR.detectLanguageOf(cleanText);
	            if (detected == null) {
	                log.debug("No language detected for text: {}", cleanText);
	                return "Unknown";
	            }

	            // 👇 clean, readable log format showing detected language + full text clearly
	            log.info("\n========== LANGUAGE DETECTION ==========\n" +
	                     "Detected Language : {}\n" +
	                     "Input Text        : {}\n" +
	                     "========================================", 
	                     detected.name(), cleanText);

	            return detected.name();

	        } catch (Exception e) {
	            log.error("Language detection failed: {}", e.getMessage());
	            return "Unknown";
	        }
	    }


	   
    
}
