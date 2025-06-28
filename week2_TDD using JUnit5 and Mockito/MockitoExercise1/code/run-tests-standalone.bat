@echo off
echo Running Mockito Exercise 1 Tests (Standalone)...
echo.

REM Create output directories
if not exist "target\classes" mkdir "target\classes"
if not exist "target\test-classes" mkdir "target\test-classes"

REM Build classpath
set CLASSPATH=lib\junit-jupiter-api-5.10.0.jar;lib\junit-jupiter-engine-5.10.0.jar;lib\junit-platform-engine-1.10.0.jar;lib\junit-platform-commons-1.10.0.jar;lib\mockito-core-5.5.0.jar;lib\byte-buddy-1.14.5.jar;lib\objenesis-3.3.jar

echo [INFO] Compiling main classes...
javac -d target\classes -cp "%CLASSPATH%" src\main\java\com\example\*.java

echo [INFO] Compiling test classes...
javac -d target\test-classes -cp "%CLASSPATH%;target\classes" src\test\java\com\example\*.java

echo [INFO] Running tests...
java -cp "%CLASSPATH%;target\classes;target\test-classes" org.junit.platform.console.ConsoleLauncher --class-path=target\test-classes --scan-classpath

echo.
echo [INFO] Test execution completed!
pause
