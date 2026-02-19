package main.java.yt_multi_lang_ui_validator.reporting;


import com.aventstack.extentreports.MediaEntityBuilder; 
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import main.java.yt_multi_lang_ui_validator.config.ConfigManager;
import main.java.yt_multi_lang_ui_validator.logger.LoggerUtility;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.Optional;
import java.util.stream.Stream;

public class TestListener implements ITestListener {

	private static final String RUN_FOLDER = "run_" + ExtentManager.RUN_TIMESTAMP;
    private static final  Logger log=LoggerUtility.getLogger(TestListener.class);
    
    
    
    @Override
    public void onTestStart(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        ExtentTestManager.startTest(testName).log(Status.INFO, "🔹 Test Started: " + testName);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        attachLogFile(result);
        attachScreenshotFolder(result);
    }
    
    
    @Override
    public void onTestFailure(ITestResult result) {
        Throwable t = result.getThrowable();
        if (t != null) {
            String msg = t.getMessage();
            if (msg == null || msg.trim().isEmpty()) {
                msg = t.toString();
            }

            // Build a compact HTML-safe block: message + first few stack frames
            StringBuilder sb = new StringBuilder();
            sb.append("<div>");
            sb.append("<b>Failure:</b> ");
            sb.append(escapeHtml(msg));
            sb.append("</div>");

            sb.append("<pre style='margin-top:8px; font-size:12px;'>");
            StackTraceElement[] ste = t.getStackTrace();
            int limit = Math.min(10, ste.length); // keep stacktrace short
            for (int i = 0; i < limit; i++) {
                sb.append(escapeHtml(ste[i].toString())).append("\n");
            }
            if (ste.length > limit) {
                sb.append("... (").append(ste.length - limit).append(" more stack frames)\n");
            }
            sb.append("</pre>");

            ExtentTestManager.getTest().fail(sb.toString());
        } else {
            ExtentTestManager.getTest().fail("Test failed.");
        }

        // Keep attaching logs and screenshots as before
        attachLogFile(result);
        attachScreenshotFolder(result);
    }
    
    
    
    @Override
    public void onTestSkipped(ITestResult result) {
        try {
            String testName = result.getMethod().getMethodName();
            Throwable t = result.getThrowable();

            if (t != null) {
                String reason = t.getMessage() == null ? t.toString() : t.getMessage();
                StringBuilder sb = new StringBuilder();
                sb.append("<b>Test skipped:</b> ").append(escapeHtml(testName)).append("<br/>")
                  .append("<b>Reason:</b> ").append(escapeHtml(reason)).append("<br/>");

                // attach a couple of stack frames for context (not whole trace)
                StackTraceElement[] trace = t.getStackTrace();
                int limit = Math.min(5, trace.length);
                sb.append("<pre>");
                for (int i = 0; i < limit; i++) {
                    sb.append(escapeHtml(trace[i].toString())).append("\n");
                }
                if (trace.length > limit) sb.append("... (").append(trace.length - limit).append(" more frames)");
                sb.append("</pre>");

                ExtentTestManager.getTest().skip(sb.toString());
            } else {
                ExtentTestManager.getTest().skip("Test skipped: " + escapeHtml(testName));
            }

            attachLogFile(result);
            attachScreenshotFolder(result);
        } catch (Exception e) {
            ExtentTestManager.getTest().warning("Failed in onTestSkipped: " + e.getMessage());
        }
    }


    @Override
    public void onFinish(ITestContext context) {
        ExtentManager.getInstance().flush();
    }

    
 
    
    

    
   

    
    
   


    
    private void attachLogFile(ITestResult result) {
        try {
            if (result == null) return;

            // determine test name (allow custom attribute)
            String perTestName = (String) result.getAttribute("logFileName");
            if (perTestName == null || perTestName.isEmpty()) {
                perTestName = result.getMethod().getMethodName();
            }

            final String runFolder = "run_" + ExtentManager.RUN_TIMESTAMP;
            final String fileName = perTestName + ".log";
            final String relPath = "logs/" + runFolder + "/" + fileName; // normalized relative path (posix-style)

            // find OS path for diagnostics only (do not use as href)
            Path projectRoot = Paths.get(System.getProperty("user.dir")).toAbsolutePath().normalize();
            Path foundLogPath = projectRoot.resolve(Paths.get("logs", runFolder, fileName)).normalize();
            if (!java.nio.file.Files.exists(foundLogPath)) {
                Path logsRoot = projectRoot.resolve("logs");
                if (java.nio.file.Files.exists(logsRoot)) {
                    final String expectedFileName = fileName; // effectively-final for lambda
                    try (Stream<Path> walk = java.nio.file.Files.walk(logsRoot)) {
                        Optional<Path> found = walk
                            .filter(p -> java.nio.file.Files.isRegularFile(p)
                                && p.getFileName().toString().equalsIgnoreCase(expectedFileName))
                            .max(Comparator.comparingLong(p -> p.toFile().lastModified()));
                        if (found.isPresent()) {
                            foundLogPath = found.get().toAbsolutePath().normalize();
                        }
                    } catch (IOException ignored) { }
                }
            }

            if (foundLogPath == null || !java.nio.file.Files.exists(foundLogPath)) {
                ExtentTestManager.getTest().info("Log file not found for: " + perTestName);
                return;
            }

            // 1) GitHub Pages absolute path (if env present)
            String pagesHref = "";
            String ghRepo = System.getenv("GITHUB_REPOSITORY"); // owner/repo
            if (ghRepo != null && ghRepo.contains("/")) {
                String repoName = ghRepo.substring(ghRepo.indexOf('/') + 1).trim();
                if (!repoName.isEmpty()) {
                    pagesHref = ("/" + repoName + "/" + relPath).replaceAll("//+", "/");
                }
            }

            // 2) local/artifact relative fallbacks (cover common depths)
            String hrefDot    = ("./"  + relPath).replaceAll("//+", "/"); // if index.html sits next to logs/
            String hrefUp1    = ("../" + relPath).replaceAll("//+", "/"); // if report sits one level under root
            String hrefUp2    = ("../../" + relPath).replaceAll("//+", "/"); // if report sits two levels under (ExtentReports under test-output)
            // include all anchors in the snippet - user will click the one that works

            StringBuilder html = new StringBuilder();
            html.append("<div style='margin:6px 0; padding:6px; border-left:3px solid #999;'>");
            html.append("<div style='font-weight:bold; margin-bottom:4px;'>Open log — choose the link that works in your environment</div>");
            if (!pagesHref.isEmpty()) {
                html.append("<div><a href='").append(pagesHref).append("' target='_blank'>Open (GitHub Pages)</a></div>");
            }
            html.append("<div><a href='").append(hrefDot).append("' target='_blank'>Open (artifact root / index.html)</a></div>");
            html.append("<div><a href='").append(hrefUp1).append("' target='_blank'>Open (report one level deep)</a></div>");
            html.append("<div><a href='").append(hrefUp2).append("' target='_blank'>Open (report inside ExtentReports/test-output)</a></div>");
            html.append("<div style='margin-top:6px;color:#666;font-size:0.9em;'>Detected log on disk: ");
            html.append(foundLogPath.toString().replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;"));
            html.append("</div>");
            html.append("</div>");

            ExtentTestManager.getTest().info(html.toString());

        } catch (Exception e) {
            ExtentTestManager.getTest().warning("Failed to attach log file: " + e.getMessage());
        }
    }
    
    
    
    
    
  
    
    private void attachScreenshotFolder(ITestResult result) {
    	
        try {
            String testName = result.getMethod().getMethodName();
            String runTs = ExtentManager.RUN_TIMESTAMP;
            String relFolder = "screenshots/Run_" + runTs + "/" + testName;
            
            // Absolute folder where screenshots are written during the run
            Path absFolder = Paths.get(System.getProperty("user.dir"))
                    .resolve("test-output").resolve(relFolder).normalize();

            if (!java.nio.file.Files.exists(absFolder)) {
                ExtentTestManager.getTest().info("Screenshot folder not found: " + relFolder);
                return;
            }

            // Where the report typically sits (we use this to compute local-relative links)
            Path reportDir = Paths.get(System.getProperty("user.dir"))
                    .resolve("test-output").resolve("ExtentReports").normalize();

            // candidate local prefixes (from the report to the screenshots folder)
            String[] localPrefixes = new String[] { "../", "../../", "./" };

            StringBuilder html = new StringBuilder();
            html.append("<details><summary>Screenshots for ").append(testName).append("</summary>");
           
           
            try (Stream<Path> files = java.nio.file.Files.list(absFolder)) {
            	//String screenShotFormat;
            	
            	
                     
                files.filter(p -> p.getFileName().toString().toLowerCase().endsWith(ConfigManager.get("screenshotFormat")))
                     .sorted()
                     .forEach(p -> {
                         String fileName = p.getFileName().toString();

                         // CI / Pages link (absolute to site root + repo name handled elsewhere)
                         String ciLink = buildPublicUrl(relFolder + "/" + fileName);

                         // find a local prefix that actually points to the file from reportDir
                         String chosenLocalLink = null;
                         for (String prefix : localPrefixes) {
                             try {
                                 Path candidate = reportDir.resolve(prefix + relFolder + "/" + fileName).normalize();
                                 if (java.nio.file.Files.exists(candidate)) {
                                     // convert to forward slashes for URLs
                                     chosenLocalLink = (prefix + relFolder + "/" + fileName).replace("\\", "/");
                                     break;
                                 }
                             } catch (Exception ex) {
                                 // try next prefix
                             }
                         }
                         // fallback: assume '../' (most common: report under test-output/ExtentReports)
                         if (chosenLocalLink == null) {
                             chosenLocalLink = ("../" + relFolder + "/" + fileName).replace("\\", "/");
                         }
                         
                         
                      // defensive sanitization: remove any accidental HTML tokens that may have leaked into the path
                         if (chosenLocalLink != null) {
                             // remove any embedded anchor markup or tags (e.g. "<a href=...")
                             chosenLocalLink = chosenLocalLink.replaceAll("(?i)<a\\s+href=.*", "");
                             // remove any stray angle brackets
                             chosenLocalLink = chosenLocalLink.replace("<", "").replace(">", "");
                             // trim whitespace
                             chosenLocalLink = chosenLocalLink.trim();
                             // ensure forward slashes
                             chosenLocalLink = chosenLocalLink.replace("\\", "/");
                         }


                         html.append("<div style='margin-top:10px; border:1px solid #ccc; padding:5px;'>")
                             .append("<div style='font-weight:bold; margin-bottom:5px;'>").append(fileName).append("</div>")
                             // show Pages link (works on CI/Pages)
                             .append("<div style='margin-bottom:6px;'><a href='").append(ciLink)
                             .append("' target='_blank'>View on GitHub Pages</a></div>")
                             // show thumbnail (local/artifact-friendly link)
                             .append("<a href='").append(chosenLocalLink).append("' target='_blank'>")
                             .append("<img src='").append(chosenLocalLink)
                             .append("' style='max-width:600px; border:1px solid #ddd;'/>")
                             .append("</a></div>");
                     });
            }

            html.append("</details>");
            ExtentTestManager.getTest().info(html.toString());

        } catch (Exception e) {
            ExtentTestManager.getTest().warning("Could not attach screenshot folder: " + e.getMessage());
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    private String buildPublicUrl(String relativeFromSiteRoot) {

        System.out.println("VMARK: buildPublicUrl input='" + relativeFromSiteRoot + "'");

        // sanitize purely relative
        String rel = relativeFromSiteRoot.replace("\\", "/");
        while (rel.startsWith("./"))  rel = rel.substring(2);
        while (rel.startsWith("../")) rel = rel.substring(3);
        if (rel.startsWith("/"))      rel = rel.substring(1);

        // read possibly corrupted REPORT_BASE (CI)
        String base = System.getenv("REPORT_BASE");
        if (base != null) base = base.trim();

        // detect invalid REPORT_BASE (Windows absolute paths)
        boolean invalid =
            (base == null || base.isEmpty()) ||
            base.matches("(?i).*[A-Za-z]:.*") ||
            base.toLowerCase().contains("program files");

        if (invalid) {
            // repair using official GitHub env
            String ghRepo = System.getenv("GITHUB_REPOSITORY"); // e.g. "username/repo"
            if (ghRepo != null && ghRepo.contains("/")) {
                String repoName = ghRepo.substring(ghRepo.indexOf('/') + 1).trim();
                if (!repoName.isEmpty()) {
                    base = "/" + repoName + "/"; // correct Pages prefix
                }
            }
        }

        // final URL construction
        String out;
        if (base != null && !base.isEmpty()) {
            if (!base.startsWith("/")) base = "/" + base;
            if (!base.endsWith("/"))   base = base + "/";
            out = (base + rel).replaceAll("//+", "/");
        } else {
            out = ("../../" + rel).replaceAll("//+", "/");
        }

        System.out.println("VMARK: buildPublicUrl output='" + out + "'");
        return out;
    }
    
    
    
    
    /** Simple HTML escaper to avoid broken markup in report output. */
    private static String escapeHtml(String s) {
        if (s == null) return "";
        return s.replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&#39;");
    }
    
    
    
    

    private String formatFailureMessage(String message) {
        // Optional: detect structured failure message by keywords
        if (message.contains("Brand filter") && message.contains("📦 Title:")) {

            // Split for better readability
            String[] lines = message.split("\n");
            String filter = lines[0]; // first line like Brand filter '...' not found

            // Build collapsible HTML
            StringBuilder html = new StringBuilder();
            html.append("<b>").append(filter).append("</b>");
            html.append("<details><summary>📄 Click to expand product details</summary>");

            for (int i = 1; i < lines.length; i++) {
                html.append(lines[i].replace("\n", "<br>")).append("<br>");
            }

            html.append("</details>");
            return html.toString();
        }

        // If no special format, return as-is
        return message;
    }

    

}

