@echo off
title Hub_SeleniumGrid
rem  �ļ����浱ǰ�ű����е�ͬ��Ŀ¼
Set FileSavePathOnUserPC=%cd%

java -jar %FileSavePathOnUserPC%\selenium-server-standalone-2.53.1.jar -role hub


pause
