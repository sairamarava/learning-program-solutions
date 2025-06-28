// Simple version without package declaration for easier compilation
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Simple SLF4J Logging Example
 * This demonstrates basic error and warning logging using SLF4J
 */
public class SimpleLoggingExample {
    private static final Logger logger = LoggerFactory.getLogger(SimpleLoggingExample.class);

    public static void main(String[] args) {
        // Basic error and warning logging as requested in the exercise
        logger.error("This is an error message");
        logger.warn("This is a warning message");
        
        // Additional examples
        logger.info("Application started successfully");
        
        // Example with variables
        String user = "testUser";
        int errorCount = 5;
        logger.warn("User {} has {} error attempts", user, errorCount);
        
        // Example with exception
        try {
            int result = 100 / 0;
        } catch (Exception e) {
            logger.error("Division by zero error: {}", e.getMessage(), e);
        }
        
        logger.info("Application completed");
    }
}
