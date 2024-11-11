package project3;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.SimpleFormatter;

public class Logger {
  /**
   * Creates and configures a Logger instance with file handler
   *
   * @param className The name of the class requesting the logger (used for logger naming)
   * @param logFilePattern The pattern for the log file name
   * @return configured Logger instance
   */
  public static java.util.logging.Logger setupLogger(String className, String logFilePattern) {
    java.util.logging.Logger logger = java.util.logging.Logger.getLogger(className);
    try {
      FileHandler fileHandler = new FileHandler(logFilePattern);
      fileHandler.setFormatter(new SimpleFormatter());

      java.util.logging.Logger rootLogger = java.util.logging.Logger.getLogger("");
      Handler[] handlers = rootLogger.getHandlers();
      for (Handler handler : handlers) {
        rootLogger.removeHandler(handler);
      }

      logger.addHandler(fileHandler);
    } catch (IOException e) {
      System.err.println("Failed to setup logger: " + e.getMessage());
    }
    return logger;
  }
}
