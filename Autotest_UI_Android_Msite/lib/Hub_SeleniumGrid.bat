@echo off
title Hub_SeleniumGrid
rem  文件保存当前脚本运行的同级目录
Set FileSavePathOnUserPC=%cd%

java -jar %FileSavePathOnUserPC%\selenium-server-standalone-2.53.1.jar -role hub


pause
