package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    private static final Properties config = new Properties();

    public static void main(String[] args) {
        loadConfiguration();
        logApplicationInfo();
        
        logger.info("Hello, Gradle World!");
        System.out.println("Application started successfully!");
        
        if (args.length > 0) {
            logger.info("Command line arguments: {}", String.join(", ", args));
        }
    }

    private static void loadConfiguration() {
        try (InputStream input = Main.class.getClassLoader()
                .getResourceAsStream("application.properties")) {
            if (input != null) {
                config.load(input);
                logger.info("Configuration loaded successfully");
            } else {
                logger.warn("Configuration file not found, using defaults");
            }
        } catch (IOException e) {
            logger.error("Error loading configuration: {}", e.getMessage());
        }
    }

    private static void logApplicationInfo() {
        String appName = config.getProperty("app.name", "Unknown");
        String appVersion = config.getProperty("app.version", "Unknown");
        String environment = config.getProperty("app.environment", "development");
        
        logger.info("Starting {} v{} in {} mode", appName, appVersion, environment);
        
        if (Boolean.parseBoolean(config.getProperty("app.debug", "false"))) {
            logger.debug("Debug mode enabled");
        }
    }
}
