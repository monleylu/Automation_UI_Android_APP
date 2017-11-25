/**
*Copyright (c) 2006-2016 . All Rights Reserved.
*/
package com.monleylu.olbb.internal.tours;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import org.openqa.selenium.By;

/**
 * 
* @Description: 所有页面基类
* @author lujian
* @date 2016年7月31日
* @version 
*    2016年7月31日  v1.0  create 
*
 */
public  class BasePage {
    
    /** 进程实例 **/
    private AndroidDriver<AndroidElement> driver;
    
    public BasePage(AndroidDriver<AndroidElement> driver) {
	this.driver = driver;
    }

    /**
     * 根据定位标示查找页面元素 
     * @param by  定位标示
     * @return 返回元素
     */
    public AndroidElement find(By by){
	return getDriver().findElement(by);
    }
    
    /**
     * 点击元素
     * @param by 元素定位标示
     * @return 执行成功返回true
     */
    public boolean clickElement(By by) {
	find(by).click();
	return true;
    }

    public AndroidDriver<AndroidElement> getDriver() {
	return driver;
    }

    public void setDriver(AndroidDriver<AndroidElement> driver) {
	this.driver = driver;
    }


}
