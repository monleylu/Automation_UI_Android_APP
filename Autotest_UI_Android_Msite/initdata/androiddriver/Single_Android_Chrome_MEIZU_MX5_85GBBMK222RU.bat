@echo off
title Single_Android_Chrome_MEIZU_MX5_85GBBMK222RU

rem  �ļ����浱ǰ�ű����е�ͬ��Ŀ¼
Set FileSavePathOnUserPC=%cd%

appium   -g %FileSavePathOnUserPC%\Single_Android_Chrome_MEIZU_MX5_85GBBMK222RU.log     -a 172.31.2.22 -p 4700  -U 85GBBMK222RU


pause
