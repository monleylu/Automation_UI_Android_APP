@echo off
title HUB_Android_Chrome_Redmi_Note3_9SPRTKKRQWU47PDA

rem  文件保存当前脚本运行的同级目录
Set FileSavePathOnUserPC=%cd%

appium --nodeconfig %FileSavePathOnUserPC%\Redmi_Note3_9SPRTKKRQWU47PDA.json  -g %FileSavePathOnUserPC%\HUB_Android_Chrome_Redmi_Note3_9SPRTKKRQWU47PDA.log  -a 172.31.2.22 -p 4723  -U 9SPRTKKRQWU47PDA


pause