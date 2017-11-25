package com.monleylu.olbb.common;

import static com.monleylu.olbb.staticvariables.StaticVars.logger;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;

public class ScreenShot {
	public static void getScreenShot(AppiumDriver<AndroidElement> driver){
		
		 String currentPath = System.getProperty("user.dir"); // get current work path
		 String fileToSavePathString = currentPath +"/screenshot"; //default path to save screenshot
		 SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
		 String fileNameofScreenShot = simpleDateFormat.format(new Date())+".jpg";
		 File screenFile =  driver.getScreenshotAs(OutputType.FILE);
		 try {
			FileUtils.copyFile(screenFile, new File(fileToSavePathString +"/" + fileNameofScreenShot));
			logger.debug("save screenshot ,Path: " + fileToSavePathString +"/" + fileNameofScreenShot);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(e.toString());
		}
		 
	}
}
