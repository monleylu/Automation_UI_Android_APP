/**
*Copyright (c) 2006-2016 . All Rights Reserved.
*/
package com.monleylu.olbb.product.drive;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import com.monleylu.olbb.internal.tours.ProductPageCode;

public class DriveProduct {

    private AndroidDriver<AndroidElement> driver;
    private PageProductDetail pageProductDetail;
    private PageProductCalendar pageProductCalendar;
    private PageProductStepOne pageProductStepOne;
    private PageProductStepTwo pageProductStepTwo;
    private PageProductTourist pageProductTourist;
    
    public DriveProduct(AndroidDriver<AndroidElement> driver) {
	this.driver = driver;
	this.setPageProductDetail(new PageProductDetail(driver));
	this.setPageProductCalendar(new PageProductCalendar(driver));
	this.setPageProductStepOne(new PageProductStepOne(driver));
	this.setPageProductStepTwo(new PageProductStepTwo(driver));
	this.setPageProductTourist(new PageProductTourist(driver));

    }

    public AndroidDriver<AndroidElement> getDriver() {
        return driver;
    }

    public void setDriver(AndroidDriver<AndroidElement> driver) {
        this.driver = driver;
    }

    public PageProductDetail getPageProductDetail() {
        return pageProductDetail;
    }

    public void setPageProductDetail(PageProductDetail pageProductDetail) {
        this.pageProductDetail = pageProductDetail;
    }

    /**
     * 获取当前app的是哪个页面
     * @return  PageProductPageCode类中定义的值
     */
    public int getCurrentPage() {
	
	String currentActivityString = driver.currentActivity();
	
 	if (currentActivityString.equalsIgnoreCase(".activity.DriveProductDetailV2Activity")) {
	    return ProductPageCode.PageProductDetail;
	}else if (currentActivityString.equalsIgnoreCase(".activity.GeneralDriveOrderOneActivity")) {
	    return ProductPageCode.PageProductStepOne;
	}else if (currentActivityString.equalsIgnoreCase(".activity.GeneralDriveOnlineBookStepTwoActivity")) {
	    return ProductPageCode.PageProductStepTwo;
	}else if (currentActivityString.equalsIgnoreCase(".activity.TouristsMainActivity")) {
	    return ProductPageCode.PageProductTourist;
	}else if (currentActivityString.equalsIgnoreCase("com.monleylu.paysdk.PaymentActivity")){
	    return ProductPageCode.PageProductCasher;
	}else {
	    return ProductPageCode.PageProductUnDefine; 
	}

     }

    public PageProductStepOne getPageProductStepOne() {
	return pageProductStepOne;
    }

    public void setPageProductStepOne(PageProductStepOne pageProductStepOne) {
	this.pageProductStepOne = pageProductStepOne;
    }

    public PageProductStepTwo getPageProductStepTwo() {
	return pageProductStepTwo;
    }

    public void setPageProductStepTwo(PageProductStepTwo pageProductStepTwo) {
	this.pageProductStepTwo = pageProductStepTwo;
    }

    public PageProductTourist getPageProductTourist() {
	return pageProductTourist;
    }

    public void setPageProductTourist(PageProductTourist pageProductTourist) {
	this.pageProductTourist = pageProductTourist;
    }

    public PageProductCalendar getPageProductCalendar() {
	return pageProductCalendar;
    }

    public void setPageProductCalendar(PageProductCalendar pageProductCalendar) {
	this.pageProductCalendar = pageProductCalendar;
    }
    

}
