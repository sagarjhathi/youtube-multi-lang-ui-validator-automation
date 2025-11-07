package main.java.yt_multi_lang_ui_validator.logger;


import org.testng.annotations.BeforeSuite;

import main.java.yt_multi_lang_ui_validator.config.ConfigManager;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LogFolderSetup {

//    @BeforeSuite(alwaysRun = true)
//    public void createLogFolderForRun() {
//        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
//        String logDir = "logs/run_" + timestamp;
//        System.setProperty("logs.dir", logDir);
//
//        File folder = new File(logDir);
//        if (!folder.exists()) folder.mkdirs();
//
//        new File(logDir + "/archive").mkdirs();
//        System.out.println("✅ Logs will be stored under: " + logDir);
//
//    }
	
	
	
	 @BeforeSuite(alwaysRun = true)
	    public void createLogFolderForRun() {
	        // Prefer CI-provided value; otherwise create one locally
	        String runTs = System.getProperty("runTimestamp");
	        if (runTs == null || runTs.isEmpty()) {
	            runTs = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
	        }

	        // Prefer explicit logs.dir if provided (absolute or relative); otherwise default to repo-workdir relative path
	        String logDir = System.getProperty("logs.dir");
	        if (logDir == null || logDir.isEmpty()) {
	            logDir = "logs/run_" + runTs;
	        }

	        // Create folders and ensure Log4j2 sees the same logs.dir
	        System.setProperty("logs.dir", logDir);

	        File folder = new File(logDir);
	        if (!folder.exists()) {
	            boolean ok = folder.mkdirs();
	            if (!ok) {
	                System.err.println("Warning: could not create logs folder: " + folder.getAbsolutePath());
	            }
	        }
	        File archive = new File(folder, "archive");
	        if (!archive.exists()) {
	            archive.mkdirs();
	        }

	        System.out.println("✅ Logs will be stored under: " + folder.getAbsolutePath());
	    }
}

