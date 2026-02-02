package main.java.yt_multi_lang_ui_validator.lingua;

import com.github.pemistahl.lingua.api.*; 
import com.github.pemistahl.lingua.api.*;

import main.java.yt_multi_lang_ui_validator.driverManager.DriverManager;
import main.java.yt_multi_lang_ui_validator.logger.LoggerUtility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;

public class LinguaHelper {
	
	
	  private static final Logger log = LoggerUtility.getLogger(LinguaHelper.class);

	    // Build detector once — expensive to create, cheap to reuse.
//	    private static final LanguageDetector DETECTOR =
//	            LanguageDetectorBuilder.fromAllLanguages().build();
	  
	  
	  
	  private static final LanguageDetector DETECTOR =
			    LanguageDetectorBuilder.fromLanguages(

			        Language.AFRIKAANS,
			        Language.AZERBAIJANI,
			        Language.INDONESIAN,
			        Language.BOSNIAN,
			        Language.CATALAN,
			        Language.DANISH,
			        Language.GERMAN,
			        Language.ESTONIAN,
			        Language.ENGLISH,
			        Language.SPANISH,
			        Language.BASQUE,
			        Language.FRENCH,
			        Language.CROATIAN,
			        Language.ICELANDIC,
			        Language.ITALIAN,
			        Language.LATVIAN,
			        Language.LITHUANIAN,
			        Language.HUNGARIAN,
			        Language.DUTCH,
			        Language.POLISH,
			        Language.PORTUGUESE,
			        Language.ALBANIAN,
			        Language.SLOVENE,
			        Language.FINNISH,
			        Language.SWEDISH,
			        Language.VIETNAMESE,
			        Language.TURKISH,
			        Language.BELARUSIAN,
			        Language.BULGARIAN,
			        Language.RUSSIAN,
			        Language.UKRAINIAN,
			        Language.GREEK,
			        Language.ARMENIAN,
			        Language.HEBREW,
			        Language.ARABIC,
			        Language.PERSIAN,
			        Language.HINDI,
			        Language.BENGALI,
			        Language.PUNJABI,
			        Language.GUJARATI,
			        Language.TAMIL,
			        Language.TELUGU,
			        Language.CHINESE,
			        Language.JAPANESE,
			        Language.KOREAN

			    ).build();


	    private LinguaHelper() {
	        // Prevent instantiation
	    }

	    /**
	     * Detect the language of the given text.
	     * Returns "Unknown" if detection fails or text is blank.
	     */



	   
//    
//	    public static String detectLanguage(String text) {
//	        try {
//	            if (text == null || text.trim().isEmpty()) {
//	                log.debug("detectLanguage called with empty text.");
//	                return "Unknown";
//	            }
//
//	            String cleanText = text.trim();
//
//	            // ---------- First attempt ----------
//	            Language detected = DETECTOR.detectLanguageOf(cleanText);
//
//	            // ---------- Retry if Unknown ----------
//	            if (detected == null) {
//	                log.debug("First detection failed. Retrying with sanitized text...");
//
//	                // remove numbers/symbols for second attempt
//	                String sanitized = cleanText.replaceAll("[^\\p{L}\\s]", "");
//
//	                detected = DETECTOR.detectLanguageOf(sanitized);
//	            }
//
//	            if (detected == null) {
//	                log.debug("Language still not detected after retry: {}", cleanText);
//	                return "Unknown";
//	            }
//
//	            log.info("\n========== LANGUAGE DETECTION ==========\n" +
//	                     "Detected Language : {}\n" +
//	                     "Input Text        : {}\n" +
//	                     "========================================",
//	                     detected.name(), cleanText);
//
//	            System.out.println(detected.name()+"    Detected.name();");
//	            return detected.name();
//
//	        } catch (Exception e) {
//	            log.error("Language detection failed: {}", e.getMessage());
//	            return "Unknown, error msg is " + e.getMessage();
//	        }
//	    }
	    
	    
	    
//	    public static String detectLanguage(String text) {
//
//	        if (text == null || text.trim().isEmpty()) {
//	            return "Unknown";
//	        }
//
//	        try {
//	            String cleanText = text.trim();
//	            
//	            log.info("Clean Text in detection: =="+cleanText+"      "   +cleanText.length());
//
//	            // sanitize once (not retry)
//	            String sanitized = cleanText.replaceAll("[^\\p{L}\\s]", "");
//
//	            log.info("sanitized Text in detection: =="+sanitized+"      "   +sanitized.length());
//	            Map<Language, Double> scores =
//	                    DETECTOR.computeLanguageConfidenceValues(sanitized);
//
//	            return scores.entrySet()
//	                    .stream()
//	                    .max(Map.Entry.comparingByValue())
//	                    .filter(e -> e.getValue() > 0.30)   // threshold
//	                    .map(e -> e.getKey().name())
//	                    .orElse("Unknown");
//
//	        } catch (Exception e) {
//	        	
//	        	 log.info("In the catch block of detection=="+e.getMessage());
//	            return "Unknown";
//	        }
//	    }

	    
	    public static String detectLanguage(String text) {
	    	log.info("[{}]detectLanguage called here with text ==",text,ThreadContext.get("testName"));
	    	
        try {
            if (text == null || text.trim().isEmpty()) {
                log.debug("detectLanguage called with empty text.");
                return "Unknown";
            }

            String cleanText = text.trim();

            Language detected = DETECTOR.detectLanguageOf(cleanText);
            if (detected == null) {
                log.debug("[{}]No language detected for text===", cleanText,ThreadContext.get("testName"));
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
            log.error("[{}]Language detection failed: {}", e.getMessage(),ThreadContext.get("testName"));
            return "Unknown , error msg is "+e.getMessage()	 ;
            }
    }
	    
	    
}
