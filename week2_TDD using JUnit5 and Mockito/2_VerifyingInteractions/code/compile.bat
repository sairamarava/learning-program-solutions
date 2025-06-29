@echo off
echo ==============================================
echo   Mockito Exercise 2: Verifying Interactions
echo ==============================================
echo.

echo Setting up directories...
if not exist "lib" mkdir lib

echo.
echo Downloading required JARs if not exists...

REM JUnit 5 Platform Console Standalone
if not exist "lib\junit-platform-console-standalone-1.9.2.jar" (
    echo Downloading JUnit Platform Console Standalone...
    powershell -Command "Invoke-WebRequest -Uri 'https://repo1.maven.org/maven2/org/junit/platform/junit-platform-console-standalone/1.9.2/junit-platform-console-standalone-1.9.2.jar' -OutFile 'lib\junit-platform-console-standalone-1.9.2.jar'"
)

REM Mockito Core
if not exist "lib\mockito-core-4.11.0.jar" (
    echo Downloading Mockito Core...
    powershell -Command "Invoke-WebRequest -Uri 'https://repo1.maven.org/maven2/org/mockito/mockito-core/4.11.0/mockito-core-4.11.0.jar' -OutFile 'lib\mockito-core-4.11.0.jar'"
)

REM Byte Buddy (Mockito dependency)
if not exist "lib\byte-buddy-1.12.18.jar" (
    echo Downloading Byte Buddy...
    powershell -Command "Invoke-WebRequest -Uri 'https://repo1.maven.org/maven2/net/bytebuddy/byte-buddy/1.12.18/byte-buddy-1.12.18.jar' -OutFile 'lib\byte-buddy-1.12.18.jar'"
)

REM Byte Buddy Agent (Mockito dependency)
if not exist "lib\byte-buddy-agent-1.12.18.jar" (
    echo Downloading Byte Buddy Agent...
    powershell -Command "Invoke-WebRequest -Uri 'https://repo1.maven.org/maven2/net/bytebuddy/byte-buddy-agent/1.12.18/byte-buddy-agent-1.12.18.jar' -OutFile 'lib\byte-buddy-agent-1.12.18.jar'"
)

REM Objenesis (Mockito dependency)
if not exist "lib\objenesis-3.2.jar" (
    echo Downloading Objenesis...
    powershell -Command "Invoke-WebRequest -Uri 'https://repo1.maven.org/maven2/org/objenesis/objenesis/3.2/objenesis-3.2.jar' -OutFile 'lib\objenesis-3.2.jar'"
)

echo.
echo Compiling Java files...
javac -cp ".;lib\*" *.java

echo.
echo Running tests...
java -cp ".;lib\*" org.junit.platform.console.ConsoleLauncher --class-path . --scan-class-path

echo.
echo Done!
pause
