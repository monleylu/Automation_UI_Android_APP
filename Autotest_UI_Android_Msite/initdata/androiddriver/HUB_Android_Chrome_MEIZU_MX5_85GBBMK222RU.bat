@echo off
title HUB_Android_Chrome_MEIZU_MX5_85GBBMK222RU

rem  文件保存当前脚本运行的同级目录
Set FileSavePathOnUserPC=%cd%

appium --nodeconfig %FileSavePathOnUserPC%\MEIZU_MX5_85GBBMK222RU.json  -g %FileSavePathOnUserPC%\HUB_Android_Chrome_MEIZU_MX5_85GBBMK222RU.log     -a 172.31.2.22 -p 4700  -U 85GBBMK222RU


pause
