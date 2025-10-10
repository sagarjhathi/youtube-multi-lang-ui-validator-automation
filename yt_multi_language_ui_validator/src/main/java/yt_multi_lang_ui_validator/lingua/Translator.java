package main.java.yt_multi_lang_ui_validator.lingua;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import org.json.JSONObject; // add org.json dependency (or any JSON lib)

/**
 * Very simple translation function: takes any text, returns English.
 */
public class Translator {

    public static String translateToEnglish(String text) {
        if (text == null || text.trim().isEmpty()) return "";

        try {
            String baseUrl = "https://libretranslate.com/translate";
            String payload = "q=" + URLEncoder.encode(text, StandardCharsets.UTF_8)
                    + "&source=auto&target=en&format=text";

            HttpRequest req = HttpRequest.newBuilder()
                    .uri(URI.create(baseUrl))
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .POST(HttpRequest.BodyPublishers.ofString(payload))
                    .build();

            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> resp = client.send(req, HttpResponse.BodyHandlers.ofString());

            if (resp.statusCode() == 200) {
                JSONObject json = new JSONObject(resp.body());
                return json.optString("translatedText", "");
            } else {
                System.err.println("Translation failed, status: " + resp.statusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    // Demo
    public static void main(String[] args) {
        String foreign = "Anmelden"; // German
        String english = translateToEnglish(foreign);
        System.out.println("Input: " + foreign);
        System.out.println("Translated: " + english);
    }
}

