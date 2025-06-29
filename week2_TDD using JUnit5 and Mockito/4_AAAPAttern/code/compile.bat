@echo off
echo ==============================================
echo   Compiling Exercise 4: AAA Pattern
echo ==============================================
echo.

echo Setting up directories...
if not exist "target\classes" mkdir target\classes
if not exist "target\test-classes" mkdir target\test-classes
if not exist "lib" mkdir lib

echo.
echo Downloading JUnit JAR if not exists...
if not exist "lib\junit-4.13.2.jar" (
    echo Downloading JUnit 4.13.2...
    powershell -Command "Invoke-WebRequest -Uri 'https://repo1.maven.org/maven2/junit/junit/4.13.2/junit-4.13.2.jar' -OutFile 'lib\junit-4.13.2.jar'"
    if %ERRORLEVEL% neq 0 (
        echo Failed to download JUnit. Please download manually.
        pause
        exit /b 1
    )
)

if not exist "lib\hamcrest-core-1.3.jar" (
    echo Downloading Hamcrest Core...
    powershell -Command "Invoke-WebRequest -Uri 'https://repo1.maven.org/maven2/org/hamcrest/hamcrest-core/1.3/hamcrest-core-1.3.jar' -OutFile 'lib\hamcrest-core-1.3.jar'"
    if %ERRORLEVEL% neq 0 (
        echo Failed to download Hamcrest. Please download manually.
        pause
        exit /b 1
    )
)

echo.
echo Compiling main classes...
javac -d target\classes src\main\java\com\example\*.java
if %ERRORLEVEL% neq 0 (
    echo Main compilation failed!
    pause
    exit /b 1
)

echo.
echo Compiling test classes...
javac -d target\test-classes -cp "target\classes;lib\junit-4.13.2.jar;lib\hamcrest-core-1.3.jar" src\test\java\com\example\*.java
if %ERRORLEVEL% neq 0 (
    echo Test compilation failed!
    pause
    exit /b 1
)

echo.
echo ==============================================
echo   Compilation successful!
echo ==============================================
echo.
echo Available test classes:
echo 1. SimpleAAAPatternTest - Basic AAA pattern and @Before/@After
echo 2. BankAccountAAATest - Comprehensive AAA pattern examples
echo 3. AdvancedAAAPatternTest - Advanced test fixtures and patterns
echo.
echo To run individual tests:
echo java -cp "target\classes;target\test-classes;lib\junit-4.13.2.jar;lib\hamcrest-core-1.3.jar" org.junit.runner.JUnitCore com.example.SimpleAAAPatternTest
echo.
pause
