@echo off
echo Downloading required JAR files for Mockito Exercise...
echo.

if not exist "lib" mkdir lib

echo Downloading JUnit Jupiter API...
powershell -Command "Invoke-WebRequest -Uri 'https://repo1.maven.org/maven2/org/junit/jupiter/junit-jupiter-api/5.10.0/junit-jupiter-api-5.10.0.jar' -OutFile 'lib/junit-jupiter-api-5.10.0.jar'"

echo Downloading JUnit Jupiter Engine...
powershell -Command "Invoke-WebRequest -Uri 'https://repo1.maven.org/maven2/org/junit/jupiter/junit-jupiter-engine/5.10.0/junit-jupiter-engine-5.10.0.jar' -OutFile 'lib/junit-jupiter-engine-5.10.0.jar'"

echo Downloading JUnit Platform Engine...
powershell -Command "Invoke-WebRequest -Uri 'https://repo1.maven.org/maven2/org/junit/platform/junit-platform-engine/1.10.0/junit-platform-engine-1.10.0.jar' -OutFile 'lib/junit-platform-engine-1.10.0.jar'"

echo Downloading JUnit Platform Commons...
powershell -Command "Invoke-WebRequest -Uri 'https://repo1.maven.org/maven2/org/junit/platform/junit-platform-commons/1.10.0/junit-platform-commons-1.10.0.jar' -OutFile 'lib/junit-platform-commons-1.10.0.jar'"

echo Downloading Mockito Core...
powershell -Command "Invoke-WebRequest -Uri 'https://repo1.maven.org/maven2/org/mockito/mockito-core/5.5.0/mockito-core-5.5.0.jar' -OutFile 'lib/mockito-core-5.5.0.jar'"

echo Downloading Byte Buddy...
powershell -Command "Invoke-WebRequest -Uri 'https://repo1.maven.org/maven2/net/bytebuddy/byte-buddy/1.14.5/byte-buddy-1.14.5.jar' -OutFile 'lib/byte-buddy-1.14.5.jar'"

echo Downloading Objenesis...
powershell -Command "Invoke-WebRequest -Uri 'https://repo1.maven.org/maven2/org/objenesis/objenesis/3.3/objenesis-3.3.jar' -OutFile 'lib/objenesis-3.3.jar'"

echo.
echo Download completed! You can now run the tests using run-tests-standalone.bat
pause
