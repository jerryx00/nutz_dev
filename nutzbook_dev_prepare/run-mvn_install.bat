@echo off
title %cd%
echo.
echo [信息] 使用mvn编译工程。
echo.
rem pause
rem echo.

cd %~dp0
call mvn install


pause