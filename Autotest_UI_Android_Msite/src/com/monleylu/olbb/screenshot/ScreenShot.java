/**
 * 截图
 * 
 */
package com.monleylu.olbb.screenshot;

import static com.monleylu.olbb.logback.LogBack.logger;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;


public class ScreenShot {
    
        /**
         * 保存手机截图，默认保存在当前工程screenshot路径
         * @param driver 进程实例
         * @return 如果截图成功，发挥图片名称，否则返回长度为零的字符串
         */
	public static String getScreenShot(AppiumDriver<AndroidElement> driver){
		
		 String currentPath = System.getProperty("user.dir"); // get current work path
		 String fileToSavePathString = currentPath +"/screenshot"; //default path to save screenshot
		 SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		 String fileNameofScreenShot = simpleDateFormat.format(new Date())+"_"+Thread.currentThread().getId()+".jpg";
		 File screenFile =  driver.getScreenshotAs(OutputType.FILE);
		 
		 try {
			FileUtils.copyFile(screenFile, new File(fileToSavePathString +"/" + fileNameofScreenShot));
			logger.debug("save screenshot ,Path: " + fileToSavePathString +"/" + fileNameofScreenShot);
			return fileNameofScreenShot;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(e.toString());
			return "";
		}
		 
	}
	
        /**
         * 保存手机截图 
         * @param driver 进程实例
         * @param pathToSavePic  指定的图片保存路径
         * @return 如果截图成功，发挥图片名称，否则返回长度为零的字符串
         */
	public static String getScreenShot(AppiumDriver<AndroidElement> driver,String pathToSavePic){
		
		 String fileToSavePathString = pathToSavePic; //default path to save screenshot
		 SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		 String fileNameofScreenShot = simpleDateFormat.format(new Date())+"_"+Thread.currentThread().getId()+".jpg";
		 File screenFile =  driver.getScreenshotAs(OutputType.FILE);
		 
		 try {
			FileUtils.copyFile(screenFile, new File(fileToSavePathString +"/" + fileNameofScreenShot));
			logger.debug("save screenshot ,Path: " + fileToSavePathString +"/" + fileNameofScreenShot);
			return fileNameofScreenShot;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(e.toString());
			return "";
		}
		 
	}
}
