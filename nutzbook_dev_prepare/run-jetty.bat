@echo 欢迎使用Nutz
title %cd%
echo [信息] 使用jetty运行工程。

rem pause
rem echo.

cd %~dp0
call mvn jetty:run


pause