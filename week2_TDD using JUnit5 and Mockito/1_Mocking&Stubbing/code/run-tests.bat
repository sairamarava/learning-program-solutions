@echo off
echo Compiling and running Mockito Exercise 1 tests...
echo.

echo [INFO] Cleaning previous build...
call mvn clean

echo.
echo [INFO] Compiling source code...
call mvn compile

echo.
echo [INFO] Compiling test code...
call mvn test-compile

echo.
echo [INFO] Running tests...
call mvn test

echo.
echo [INFO] Build completed. Check target/surefire-reports for detailed test results.
pause
