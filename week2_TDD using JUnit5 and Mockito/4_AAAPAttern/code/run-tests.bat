@echo off
echo ==============================================
echo   Exercise 4: AAA Pattern and Test Fixtures
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
)

if not exist "lib\hamcrest-core-1.3.jar" (
    echo Downloading Hamcrest Core...
    powershell -Command "Invoke-WebRequest -Uri 'https://repo1.maven.org/maven2/org/hamcrest/hamcrest-core/1.3/hamcrest-core-1.3.jar' -OutFile 'lib\hamcrest-core-1.3.jar'"
)

echo.
echo Compiling main classes...
javac -d target\classes -cp "lib\*" src\main\java\com\example\*.java
if %ERRORLEVEL% neq 0 (
    echo Main compilation failed!
    pause
    exit /b 1
)

echo.
echo Compiling test classes...
javac -d target\test-classes -cp "target\classes;lib\*" src\test\java\com\example\*.java
if %ERRORLEVEL% neq 0 (
    echo Test compilation failed!
    pause
    exit /b 1
)

echo.
echo Running Simple AAA Pattern Test (Core Exercise)...
java -cp "target\classes;target\test-classes;lib\*" org.junit.runner.JUnitCore com.example.SimpleAAAPatternTest

echo.
echo Running Bank Account AAA Test (Comprehensive Example)...
java -cp "target\classes;target\test-classes;lib\*" org.junit.runner.JUnitCore com.example.BankAccountAAATest

echo.
echo Running Advanced AAA Pattern Test (Advanced Examples)...
java -cp "target\classes;target\test-classes;lib\*" org.junit.runner.JUnitCore com.example.AdvancedAAAPatternTest

echo.
if %ERRORLEVEL% equ 0 (
    echo ==============================================
    echo   All tests passed successfully!
    echo ==============================================
) else (
    echo ==============================================
    echo   Some tests failed. Check output above.
    echo ==============================================
)

echo.
pause
