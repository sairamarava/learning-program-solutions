@echo off
echo ==============================================
echo    Mockito Exercise 1 - Runner Options
echo ==============================================
echo.
echo Choose how you want to run the exercise:
echo.
echo 1. Run Simple Demo (No external dependencies)
echo 2. Run Simple Test Runner (Shows mocking concepts)
echo 3. Try to run with Maven (if available)
echo 4. Download dependencies and run full tests
echo 5. Exit
echo.
set /p choice="Enter your choice (1-5): "

if "%choice%"=="1" goto demo
if "%choice%"=="2" goto simple_tests
if "%choice%"=="3" goto maven_tests
if "%choice%"=="4" goto download_deps
if "%choice%"=="5" goto exit
goto invalid

:demo
echo.
echo Running MockingDemo...
echo.
javac -d target\classes src\main\java\com\example\*.java
java -cp target\classes com.example.MockingDemo
goto end

:simple_tests
echo.
echo Running SimpleTestRunner...
echo.
javac -d target\classes src\main\java\com\example\*.java
java -cp target\classes com.example.SimpleTestRunner
goto end

:maven_tests
echo.
echo Trying to run with Maven...
echo.
mvn clean test
if errorlevel 1 (
    echo.
    echo Maven failed. Try option 1 or 2 for alternatives.
)
goto end

:download_deps
echo.
echo This will download JAR files from Maven Central...
set /p confirm="Continue? (y/n): "
if /i "%confirm%"=="y" (
    call download-dependencies.bat
    echo.
    echo Now running tests with downloaded dependencies...
    call run-tests-standalone.bat
) else (
    echo Download cancelled.
)
goto end

:invalid
echo Invalid choice. Please try again.
goto end

:exit
echo Goodbye!
goto end

:end
echo.
echo Press any key to return to menu or close window...
pause >nul
cls
goto start

:start
goto :EOF
