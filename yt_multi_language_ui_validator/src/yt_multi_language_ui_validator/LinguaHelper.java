package yt_multi_language_ui_validator;

import com.github.pemistahl.lingua.api.*;

import java.util.Arrays;
import java.util.List;

public class LinguaHelper {

    /**
     * Detects language of a given text among the given candidate languages.
     *
     * @param text The text to detect
     * @param candidateLanguages List of languages to check against (max 10 here)
     * @return Detected language name (or "Unknown" if none)
     */
    public static String detectLanguage(String text, List<Language> candidateLanguages) {
        if (text == null || text.trim().isEmpty()) {
            return "Unknown";
        }

        // Build detector for given languages
        LanguageDetector detector = LanguageDetectorBuilder
                .fromLanguages(candidateLanguages.toArray(new Language[0]))
                .build();

        Language detected = detector.detectLanguageOf(text);

        return detected != null ? detected.name() : "Unknown";
    }

    // Example usage
    public static void main(String[] args) {
        List<Language> langs = Arrays.asList(
                Language.ENGLISH,
                Language.GERMAN,
                Language.FRENCH,
                Language.SPANISH,
                Language.PORTUGUESE,
                Language.ITALIAN,
                Language.DUTCH,
                Language.RUSSIAN,
                Language.HINDI,
                Language.CHINESE,
                Language.TAGALOG
        );

        String text = "Magsimulang manood ng mga video para matulungan kaming bumuo ng feed ng mga video na magugustuhan mo."; // German word
        String result = detectLanguage(text, langs);
        System.out.println("Detected: " + result);
    }
}
