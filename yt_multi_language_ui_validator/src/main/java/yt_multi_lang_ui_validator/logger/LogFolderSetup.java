package main.java.yt_multi_lang_ui_validator.logger;


import org.testng.annotations.BeforeSuite;

import main.java.yt_multi_lang_ui_validator.config.ConfigManager;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LogFolderSetup {

    @BeforeSuite(alwaysRun = true)
    public void createLogFolderForRun() {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
        String logDir = "logs/run_" + timestamp;
        System.setProperty("logs.dir", logDir);

        File folder = new File(logDir);
        if (!folder.exists()) folder.mkdirs();

        new File(logDir + "/archive").mkdirs();
        System.out.println("✅ Logs will be stored under: " + logDir);

    }
}

