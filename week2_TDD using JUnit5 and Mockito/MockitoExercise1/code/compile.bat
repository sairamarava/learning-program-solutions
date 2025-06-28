@echo off
echo Compiling Mockito Exercise 1...
echo.

set JAVA_HOME=C:\Program Files\Java\jdk-11.0.2
set PATH=%JAVA_HOME%\bin;%PATH%

echo [INFO] Creating directories...
if not exist "target\classes" mkdir "target\classes"
if not exist "target\test-classes" mkdir "target\test-classes"

echo [INFO] Downloading dependencies...
if not exist "lib" mkdir "lib"

echo [INFO] Note: You may need to manually download the following JAR files to the lib directory:
echo - junit-jupiter-engine-5.10.0.jar
echo - junit-jupiter-api-5.10.0.jar
echo - mockito-core-5.5.0.jar
echo - mockito-junit-jupiter-5.5.0.jar
echo - byte-buddy-1.14.5.jar
echo - objenesis-3.3.jar

echo.
echo [INFO] To run this exercise:
echo 1. Ensure Maven is properly installed
echo 2. Run: mvn clean test
echo 3. Or use your IDE to run the tests directly

pause
