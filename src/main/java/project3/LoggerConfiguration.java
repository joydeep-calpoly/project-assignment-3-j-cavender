package project3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.*;

/**
 * Singleton class responsible for managing logging configuration for the news parser application.
 * This class handles the initialization and management of logging settings, including file handlers
 * and log file locations. It ensures consistent logging behavior across the application.
 */
public class LoggerConfiguration {
    private static final String LOG_DIRECTORY = "project_3/logs";
    private static final String LOG_FILE_NAME = "parser.log";
    private static final String LOGGER_NAME = "NewsAPILogger";
    private static LoggerConfiguration instance;
    private final Logger logger;
    private FileHandler fileHandler;

    /**
     * Private constructor to enforce singleton pattern.
     * Initializes the logging system by creating necessary directories and configuring
     * the logger with appropriate handlers and formatters.
     *
     * @throws RuntimeException if the log directory cannot be created or the file handler cannot be initialized
     */
    private LoggerConfiguration() {
        try {
            initializeLogDirectory();
            logger = Logger.getLogger(LOGGER_NAME);
            fileHandler = new FileHandler(getLogPath(), true);
            fileHandler.setFormatter(new SimpleFormatter());
            configureLogger(logger);
        } catch (IOException e) {
            System.err.println("Failed to create log directory or file handler: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * Returns the singleton instance of LoggerConfiguration.
     * Creates a new instance if one doesn't exist, following the singleton pattern.
     *
     * @return The singleton instance of LoggerConfiguration
     */
    public static synchronized LoggerConfiguration getInstance() {
        if (instance == null) {
            instance = new LoggerConfiguration();
        }
        return instance;
    }

    /**
     * Returns the configured logger instance.
     *
     * @return The configured Logger instance
     */
    public Logger getLogger() {
        return logger;
    }

    /**
     * Returns the full path to the log file.
     * Combines the log directory path with the log file name to create the complete path.
     *
     * @return A String representing the full path to the log file
     */
    public String getLogPath() {
        return LOG_DIRECTORY + "/" + LOG_FILE_NAME;
    }

    /**
     * Closes the file handler.
     */
    public void close() {
        if (fileHandler != null) {
            fileHandler.close();
        }
    }

    private void configureLogger(Logger logger) {
        for (Handler handler : logger.getHandlers()) {
            logger.removeHandler(handler);
        }
        logger.setUseParentHandlers(false);
        if (fileHandler != null) {
            logger.addHandler(fileHandler);
        } else {
            ConsoleHandler consoleHandler = new ConsoleHandler();
            consoleHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(consoleHandler);
            logger.warning("Failed to create log file. Logging to console instead.");
        }
    }

    private void initializeLogDirectory() throws IOException {
        Path logDir = Paths.get(LOG_DIRECTORY);
        if (!Files.exists(logDir)) {
            Files.createDirectories(logDir);
        }
    }
}