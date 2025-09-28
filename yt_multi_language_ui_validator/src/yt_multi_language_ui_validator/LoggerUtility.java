package yt_multi_language_ui_validator;




//File: LoggerUtility.java
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerUtility {
public static Logger getLogger(Class<?> clazz) {
   return LogManager.getLogger(clazz);
}
}


