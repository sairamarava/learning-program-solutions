@echo off
echo ==============================================
echo   Compiling Java Project (without Maven)
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
        echo Failed to download JUnit. Please download manually from:
        echo https://repo1.maven.org/maven2/junit/junit/4.13.2/junit-4.13.2.jar
        echo And place it in the lib folder.
        pause
        exit /b 1
    )
)

if not exist "lib\hamcrest-core-1.3.jar" (
    echo Downloading Hamcrest Core...
    powershell -Command "Invoke-WebRequest -Uri 'https://repo1.maven.org/maven2/org/hamcrest/hamcrest-core/1.3/hamcrest-core-1.3.jar' -OutFile 'lib\hamcrest-core-1.3.jar'"
    if %ERRORLEVEL% neq 0 (
        echo Failed to download Hamcrest. Please download manually from:
        echo https://repo1.maven.org/maven2/org/hamcrest/hamcrest-core/1.3/hamcrest-core-1.3.jar
        echo And place it in the lib folder.
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
echo To run tests manually:
echo java -cp "target\classes;target\test-classes;lib\junit-4.13.2.jar;lib\hamcrest-core-1.3.jar" org.junit.runner.JUnitCore com.example.CalculatorTest
echo.
pause
