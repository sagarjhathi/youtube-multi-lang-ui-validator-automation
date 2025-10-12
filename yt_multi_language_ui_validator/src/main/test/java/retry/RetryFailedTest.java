package main.test.java.retry;
import org.apache.logging.log4j.Logger;
import org.testng.IRetryAnalyzer; 
import org.testng.ITestResult;

import main.java.yt_multi_lang_ui_validator.logger.LoggerUtility;
import main.java.yt_multi_lang_ui_validator.utilities.GenericUtility;

import java.util.Arrays;
import java.util.List;

public class RetryFailedTest implements IRetryAnalyzer {

    private int retryCount = 0;
    private static final int maxRetryCount = 1; // Retry only once
    private final  Logger log=LoggerUtility.getLogger(RetryFailedTest.class);
    
    // List of exceptions to retry on (excluding assertion errors)
    private static final List<Class<? extends Throwable>> nonRetryableExceptions =
            Arrays.asList(AssertionError.class);

    @Override
    public boolean retry(ITestResult result) {
        Throwable cause = result.getThrowable();

        if (retryCount < maxRetryCount && (cause == null || isRetryable(cause))) {
            retryCount++;
            System.out.println("Retrying test: " + result.getName() + " due to " + cause);
            return true;
        }
        return false;
    }

    private boolean isRetryable(Throwable cause) {
        for (Class<? extends Throwable> nonRetryable : nonRetryableExceptions) {
            if (nonRetryable.isInstance(cause)) {
                return false;
            }
        }
        return true;
    }
}

