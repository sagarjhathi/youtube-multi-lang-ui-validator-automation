package main.java.yt_multi_lang_ui_validator.lingua;

import com.github.pemistahl.lingua.api.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LinguaHelper {

    /**
     * Detects language of a given text among the given candidate languages.
     *
     * @param text The text to detect
     * @param candidateLanguages List of languages to check against (max 10 here)
     * @return Detected language name (or "Unknown" if none)
     */
    // --- your detectLanguage function ---
    public static String detectLanguage(String text) {
        if (text == null || text.trim().isEmpty()) {
            return "Unknown";
        }
        LanguageDetector detector = LanguageDetectorBuilder.fromAllLanguages().build();
        Language detected = detector.detectLanguageOf(text);
        System.out.printf("Text: %-60s | Detected: %s%n", text, detected);
        return detected != null ? detected.name() : "Unknown";
    }
    
 
    // Example usage
    public static void main(String[] args) {
    	
    	String[] languages = {
    		    "English",
    		    "German",
    		    "French",
    		    "Spanish",
    		    "Portuguese",
    		    "Italian",
    		    "Dutch",
    		    "Swedish",
    		    "Danish",
    		    "Finnish",
    		    "Polish",
    		    "Czech",
    		    "Slovak",
    		    "Hungarian",
    		    "Romanian",
    		    "Bulgarian",
    		    "Russian",
    		    "Ukrainian",
    		    "Greek",
    		    "Turkish",
    		    "Arabic",
    		    "Hebrew",
    		    "Persian",
    		    "Hindi",
    		    "Bengali",
    		    "Punjabi",
    		    "Gujarati",
    		    "Tamil",
    		    "Telugu",
    		    "Tagalog / Filipino",
    		    "Vietnamese",
    		    "Thai",
    		    "Indonesian",
    		    "Malay",
    		    "Chinese",
    		    "Japanese",
    		    "Korean",
    		    "Basque",
    		    "Catalan",
    		    "Icelandic",
    		    "Estonian",
    		    "Latvian",
    		    "Lithuanian",
    		    "Albanian",
    		    "Slovenian / Slovene",
    		    "Belarusian",
    		    "Armenian"
    		};

    	
    	

    	LanguageDetector detector = LanguageDetectorBuilder.fromAllLanguages().build();
    	
    	
    	for(int i=0;i<languages.length;i++) {
    		String s=languages[i];
    		var detected = detector.detectLanguageOf(s);
    	    System.out.printf("Text: %-60s | Detected: %s%n", s, detected);
    	}
    	
    	

    }
}
