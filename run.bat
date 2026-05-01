@echo off
setlocal
cd /d "%~dp0"

call mvn clean package
if errorlevel 1 exit /b 1

java -jar "target\term-game-1.0.jar"