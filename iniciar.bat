@echo off
chcp 65001 >nul
title BSC Southwest - Inicio
cd /d "%~dp0"
powershell -NoProfile -ExecutionPolicy Bypass -File "%~dp0iniciar.ps1"
echo.
echo La aplicacion se detuvo. Puedes cerrar esta ventana.
pause >nul
