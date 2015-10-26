@echo off

set root=C:\Source\BRIDGE-HQ

cd %root%
call mvn clean install
rem if not %ERRORLEVEL%==0 goto error

cd %root%\target
call java -Xmx4096m -XX:MaxPermSize2048m -jar BridgeLC-1.0-SNAPSHOT.jar

:error
echo Error occurs. Exit.
cd %build%
exit /b