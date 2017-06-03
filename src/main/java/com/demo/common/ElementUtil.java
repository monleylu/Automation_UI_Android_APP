package com.demo.common;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * 元素常用操作功能合集
 * Created by monley_Lu on 2017/6/2.
 */
public class ElementUtil {


    /**
     * Judge whether Element exist
     * @param driver  appium driver instance
     * @param by       used to find the element
     * @param timeOutInSec  time to wait
     * @return  if exist return true ,or return false
     */
    public static boolean isExist(AndroidDriver<AndroidElement> driver,By by,int timeOutInSec){

        WebDriverWait webDriverWait = new WebDriverWait(driver,timeOutInSec);
        try{
            webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(by));
            return  Boolean.TRUE;
        }catch (Exception e){
            return  Boolean.FALSE;
        }
    }



}
