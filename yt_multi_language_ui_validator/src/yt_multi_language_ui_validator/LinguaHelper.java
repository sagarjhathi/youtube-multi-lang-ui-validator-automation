package yt_multi_language_ui_validator;

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
    	
    	
    	List<String> sentences = Arrays.asList(
    		    "Please start watching videos to build a better feed.",          // ENGLISH
    		    "Bitte beginnen Sie mit dem Ansehen von Videos jetzt.",          // GERMAN
    		    "Commencez à regarder des vidéos pour personnaliser le fil.",    // FRENCH
    		    "Comience a ver vídeos para crear un mejor feed personal.",      // SPANISH
    		    "Comece a assistir vídeos para ajudar a melhorar o feed.",       // PORTUGUESE
    		    "Inizia a guardare i video per migliorare il tuo feed oggi.",    // ITALIAN
    		    "Begin met het bekijken van video’s om je feed te verbeteren.",  // DUTCH
    		    "Börja titta på videor för att bygga upp ditt rekommenderade flöde.", // SWEDISH
    		    "Begynd at se videoer for at hjælpe med at bygge feedet.",       // DANISH
    		    "Begynn å se videoer for å hjelpe med å bygge feeden din.",      // NORWEGIAN
    		    "Aloita videoiden katselu auttaaksesi suosittelun rakentamista.",// FINNISH
    		    "Zacznij oglądać filmy, aby pomóc zbudować lepszy kanał.",       // POLISH
    		    "Začněte sledovat videa, aby vytvořili lepší doporučený feed.",  // CZECH
    		    "Začnite sledovať videá, aby sme vytvorili lepší odporúčaný kanál.", // SLOVAK
    		    "Kezdje el nézni a videókat, hogy jobb ajánlott feedet kapjon.", // HUNGARIAN
    		    "Începeți să vizionați videoclipuri pentru a îmbunătăți feedul.", // ROMANIAN
    		    "Започнете да гледате видеа, за да помогнете за персонализиране.", // BULGARIAN
    		    "Начните просматривать видео, чтобы помочь создать ленту по вкусу.", // RUSSIAN
    		    "Почніть переглядати відео, щоб допомогти сформувати стрічку.", // UKRAINIAN
    		    "Ξεκινήστε να παρακολουθείτε βίντεο για να βελτιώσετε το feed.", // GREEK
    		    "Beğeneceğiniz videolar için izlemeye şimdi başlayın lütfen.",   // TURKISH
    		    "ابدأ بمشاهدة مقاطع الفيديو لمساعدتنا في بناء الخلاصة المفضلة.", // ARABIC
    		    "התחל לצפות בסרטונים כדי שנבנה עבורך פיד מותאם אישית.",          // HEBREW
    		    "برای ساختن فید بهتر، تماشای ویدیوها را شروع کنید همین حالا.",  // PERSIAN
    		    "अपनी पसंदीदा वीडियो फ़ीड बनाने में मदद के लिए देखें अभी।",       // HINDI
    		    "আপনার পছন্দের ভিডিও ফিড গঠনে সাহায্য করতে দেখুন এখনই।",         // BENGALI
    		    "તમારા માટે શ્રેષ્ઠ ફીડ બનાવવા માટે ઑનલાઇન વીડિયો જોવાનું શરૂ કરો.", // GUJARATI
    		    "तुमच्या पसंतीच्या व्हिडिओ फीडसाठी आता व्हिडिओ पहायला सुरूवात करा.", // MARATHI
    		    "உங்களுக்கு பிடித்தவாறு வீடியோ கள் காண தொடங்குங்கள் இப்போது உடனே.", // TAMIL
    		    "మీకు నచ్చే వీడియో ఫీడ్ కోసం వీడియోలు చూడటం ప్రారంభించండి ఇప్పడు.", // TELUGU
    		    "Magsimulang manood ng mga video upang buuin ang feed mo ngayon.", // TAGALOG
    		    "Bắt đầu xem video để giúp chúng tôi tạo feed phù hợp cho bạn.",  // VIETNAMESE
    		    "เริ่มดูวิดีโอเพื่อช่วยสร้างฟีดวิดีโอที่คุณจะชอบ ได้เลย",      // THAI
    		    "Mulai menonton video untuk membantu membangun umpan yang cocok.", // INDONESIAN
    		    "Mula menonton video untuk membantu bina suapan yang anda suka kini.", // MALAY
    		    "开始观看视频，帮助我们为您建立更符合喜好的视频流。",                // CHINESE
    		    "動画を見始めて、あなた好みのフィードを作る手助けをしてください。", // JAPANESE
    		    "동영상을 보기 시작하여 좋아할 만한 피드를 구성하는 데 도움 주세요.", // KOREAN
    		    "Hasi bideoak ikusten zuretzat egokitutako iragarki-sorta bultzatzeko.", // BASQUE
    		    "Comenceu a veure vídeos per ajudar-nos a crear el vostre fil de contingut.", // CATALAN
    		    "Comeza a ver vídeos para axudarnos a crear un feed do teu gusto agora.", // GALICIAN
    		    "Byrjaðu að horfa á myndbönd til að hjálpa við að byggja upp strauminn.", // ICELANDIC
    		    "Започніть переглядати відео, щоб створити особисту стрічку рекомендацій.", // (extra UKRAINIAN test)
    		    "Дайте старт просмотру видео, чтобы настроить персональную ленту.", // (extra RUSSIAN test)
    		    "Ξεκινήστε να δείτε μερικά βίντεο για να δημιουργήσετε μια καλύτερη ροή.", // (extra GREEK test)
    		    "Commencez à visionner des vidéos pour améliorer votre expérience générale.", // (extra FRENCH test)
    		    "Bitte sehen Sie sich einige Videos an, um den Feed besser anzupassen.", // (extra GERMAN test)
    		    "Start med at se videoer for at forbedre din personlige oplevelse." // (extra DANISH test)
    		);


    	LanguageDetector detector = LanguageDetectorBuilder.fromAllLanguages().build();
    	String s="Palakasan";
    	var detected = detector.detectLanguageOf(s);
	    System.out.printf("Text: %-60s | Detected: %s%n", s, detected);
    	

    }
}
