@echo off
echo ========================================
echo SLF4J Logging Exercise 1
echo ========================================
echo.

echo Setting up directories...
if not exist "logs" mkdir logs
if not exist "target\classes" mkdir target\classes

echo.
echo Note: This exercise requires Maven to be properly installed.
echo If Maven is not available, please install it from https://maven.apache.org/
echo.

echo To run this exercise manually:
echo 1. Ensure Maven is installed and in your PATH
echo 2. Run: mvn clean compile
echo 3. Run: mvn exec:java -Dexec.mainClass="com.example.LoggingExample"
echo.

echo Alternatively, if you have the required JAR files:
echo 1. Download slf4j-api-1.7.30.jar
echo 2. Download logback-classic-1.2.3.jar  
echo 3. Download logback-core-1.2.3.jar
echo 4. Compile: javac -cp "slf4j-api-1.7.30.jar;logback-classic-1.2.3.jar;logback-core-1.2.3.jar" src\main\java\com\example\LoggingExample.java -d target\classes
echo 5. Run: java -cp "slf4j-api-1.7.30.jar;logback-classic-1.2.3.jar;logback-core-1.2.3.jar;target\classes;src\main\resources" com.example.LoggingExample
echo.

echo Project structure created successfully!
echo Check the following files:
echo - pom.xml (Maven configuration)
echo - src\main\java\com\example\LoggingExample.java (Main class)
echo - src\main\resources\logback.xml (Logging configuration)
echo - README.md (Detailed instructions)
echo.

pause
