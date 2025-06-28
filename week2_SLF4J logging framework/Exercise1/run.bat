@echo off
echo Compiling and running SLF4J Logging Exercise...
echo.

REM Create logs directory if it doesn't exist
if not exist "logs" mkdir logs

REM Compile the project using Maven
echo Compiling with Maven...
mvn clean compile

if %ERRORLEVEL% neq 0 (
    echo Compilation failed!
    pause
    exit /b 1
)

echo.
echo Running the application...
mvn exec:java -Dexec.mainClass="com.example.LoggingExample"

echo.
echo Check the logs directory for the application.log file
echo.
pause
