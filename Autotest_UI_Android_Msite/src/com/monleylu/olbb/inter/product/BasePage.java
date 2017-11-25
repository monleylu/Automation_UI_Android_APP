/**
*Copyright (c) 2006-2016 . All Rights Reserved.
*/
package com.monleylu.olbb.inter.product;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import org.openqa.selenium.By;

/**
 * 
* @Description: 所有页面的父页面，其他所有具体的页面业务也都是基于此页面扩展
* @author lujian
* @date 2016年8月23日
* @version 
*    2016年8月23日  v1.0  create 
*
 */
public abstract class BasePage {
    
    /** 测试进程实例 **/
    private AndroidDriver<AndroidElement> androidDriver;
    
    
    public BasePage(AndroidDriver<AndroidElement> androidDriver){
	this.setAndroidDriver(androidDriver);
    }
    
    

    
    /**
     * 查找页面元素
     * @param locator 元素定位标示
     * @return 返回定位标示的元素
     */
    public AndroidElement find(By locator){
	return androidDriver.findElement(locator);
    }
    
    /**
     * 点击页面元素
     * @param AndroidElement 页面元素
     * @return 执行完毕返回true，出现元素无法识别或者无法点击等异常返回false
     */
    public boolean clickElement(AndroidElement androidElement){
	try {
	    androidElement.click();
	    return true;
	} catch (Exception e) {
	    //e.printStackTrace();
	    return false;
	}
	
    }

    public AndroidDriver<AndroidElement> getAndroidDriver() {
        return androidDriver;
    }

    public void setAndroidDriver(AndroidDriver<AndroidElement> androidDriver) {
        this.androidDriver = androidDriver;
    }
    

}






