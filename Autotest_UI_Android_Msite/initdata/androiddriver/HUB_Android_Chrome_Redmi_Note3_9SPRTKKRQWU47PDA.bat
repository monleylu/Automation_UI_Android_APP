@echo off
title HUB_Android_Chrome_Redmi_Note3_9SPRTKKRQWU47PDA

rem  �ļ����浱ǰ�ű����е�ͬ��Ŀ¼
Set FileSavePathOnUserPC=%cd%

appium --nodeconfig %FileSavePathOnUserPC%\Redmi_Note3_9SPRTKKRQWU47PDA.json  -g %FileSavePathOnUserPC%\HUB_Android_Chrome_Redmi_Note3_9SPRTKKRQWU47PDA.log  -a 172.31.2.22 -p 4723  -U 9SPRTKKRQWU47PDA


pause