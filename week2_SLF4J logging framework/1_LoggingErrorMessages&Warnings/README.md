# SLF4J Logging Exercise 1

## Overview

This exercise demonstrates logging error messages and warning levels using SLF4J (Simple Logging Facade for Java) with Logback as the implementation.

## Project Structure

```
Exercise1/
├── pom.xml                                 # Maven configuration with SLF4J dependencies
├── src/
│   └── main/
│       ├── java/
│       │   └── com/
│       │       └── example/
│       │           └── LoggingExample.java # Main class with logging examples
│       └── resources/
│           └── logback.xml                 # Logback configuration
├── run.bat                                 # Batch file to compile and run
└── README.md                              # This file
```

## Dependencies

- **SLF4J API** (1.7.30): The logging facade
- **Logback Classic** (1.2.3): The logging implementation

## Features Demonstrated

1. **Error Logging**: `logger.error()` for critical errors
2. **Warning Logging**: `logger.warn()` for warnings
3. **Info Logging**: `logger.info()` for informational messages
4. **Debug/Trace Logging**: Additional logging levels
5. **Parameterized Messages**: Using placeholders for dynamic content
6. **Exception Logging**: Logging exceptions with stack traces

## How to Run

### Option 1: Using the Batch File

```bash
run.bat
```

### Option 2: Using Maven Commands

```bash
# Compile the project
mvn clean compile

# Run the application
mvn exec:java -Dexec.mainClass="com.example.LoggingExample"
```

### Option 3: Manual Compilation (if Maven is not available)

```bash
# Ensure you have the SLF4J and Logback JAR files in your classpath
javac -cp "path/to/slf4j-api.jar;path/to/logback-classic.jar;path/to/logback-core.jar" src/main/java/com/example/LoggingExample.java
java -cp "path/to/slf4j-api.jar;path/to/logback-classic.jar;path/to/logback-core.jar;src/main/java" com.example.LoggingExample
```

## Expected Output

The application will output log messages to both:

1. **Console**: Real-time output during execution
2. **Log File**: `logs/application.log` (created automatically)

Sample console output:

```
2024-01-15 10:30:45 [main] INFO  com.example.LoggingExample - Starting LoggingExample application...
2024-01-15 10:30:45 [main] ERROR com.example.LoggingExample - This is an error message
2024-01-15 10:30:45 [main] WARN  com.example.LoggingExample - This is a warning message
2024-01-15 10:30:45 [main] INFO  com.example.LoggingExample - This is an info message
2024-01-15 10:30:45 [main] DEBUG com.example.LoggingExample - This is a debug message
2024-01-15 10:30:45 [main] TRACE com.example.LoggingExample - This is a trace message
2024-01-15 10:30:45 [main] WARN  com.example.LoggingExample - User John Doe has failed login 3 times
2024-01-15 10:30:45 [main] ERROR com.example.LoggingExample - Mathematical error occurred: / by zero
java.lang.ArithmeticException: / by zero
	at com.example.LoggingExample.main(LoggingExample.java:32)
2024-01-15 10:30:45 [main] INFO  com.example.LoggingExample - LoggingExample application completed.
```

## Key Learning Points

1. **SLF4J Facade**: Provides a simple interface that can work with different logging implementations
2. **Logback Configuration**: XML-based configuration for controlling log format and output destinations
3. **Log Levels**: Different severity levels (ERROR, WARN, INFO, DEBUG, TRACE)
4. **Parameterized Logging**: Efficient way to include dynamic content in log messages
5. **Exception Handling**: Proper way to log exceptions with stack traces

## Configuration Details

The `logback.xml` file configures:

- **Console Appender**: Outputs to system console
- **File Appender**: Outputs to `logs/application.log`
- **Pattern Layout**: Timestamp, thread, level, logger name, and message
- **Log Level**: Set to DEBUG to show all message types
