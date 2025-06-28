package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Demonstrates SLF4J logging with error and warning levels
 * This class shows how to use SLF4J for logging different levels of messages
 */
public class LoggingExample {
    // Create a logger instance for this class
    private static final Logger logger = LoggerFactory.getLogger(LoggingExample.class);

    public static void main(String[] args) {
        logger.info("Starting LoggingExample application...");
        
        // Demonstrate error logging
        logger.error("This is an error message");
        
        // Demonstrate warning logging
        logger.warn("This is a warning message");
        
        // Additional logging levels for demonstration
        logger.info("This is an info message");
        logger.debug("This is a debug message");
        logger.trace("This is a trace message");
        
        // Example with parameterized messages
        String userName = "John Doe";
        int attempts = 3;
        logger.warn("User {} has failed login {} times", userName, attempts);
        
        // Example with exception logging
        try {
            int result = 10 / 0; // This will cause an exception
        } catch (ArithmeticException e) {
            logger.error("Mathematical error occurred: {}", e.getMessage(), e);
        }
        
        logger.info("LoggingExample application completed.");
    }
}
