@echo off
echo ===============================
echo  Compiling Java Project...
echo ===============================
javac Main.java model\*.java service\*.java db\*.java util\*.java

IF %ERRORLEVEL% NEQ 0 (
    echo.
    echo ❌ Compilation failed!
    pause
    exit /b
)

echo.
echo ===============================
echo  Running Project...
echo ===============================

java -cp ".;model;service;db;util" Main

echo.
pause
