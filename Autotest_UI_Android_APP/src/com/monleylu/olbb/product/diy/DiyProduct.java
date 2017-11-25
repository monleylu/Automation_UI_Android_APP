/**
*Copyright (c) 2006-2016 . All Rights Reserved.
*/
package com.monleylu.olbb.product.diy;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import com.monleylu.olbb.common.AppUtil;

public class DiyProduct {
    
    private AndroidDriver<AndroidElement> driver;
    
    private DiyPageProductDetail diyPageProductDetail;
    private DiyPageProductCalendar diyPageProductCalendar;
    private DiyPageProductStepOne diyPageProductStepOne;
    private DiyPageProductStepTwo diyPageProductStepTwo;
    private DiyPageProductTourist diyPageProductTourist;
    
    public DiyProduct(AndroidDriver<AndroidElement> driver){
	this.driver =driver;
	this.setDiyPageProductDetail(new DiyPageProductDetail(driver));
	this.setDiyPageProductCalendar(new DiyPageProductCalendar(driver));
	this.setDiyPageProductStepOne(new DiyPageProductStepOne(driver));
	this.setDiyPageProductStepTwo(new DiyPageProductStepTwo(driver));
	this.setDiyPageProductTourist(new DiyPageProductTourist(driver));
    }

    public DiyPageProductDetail getDiyPageProductDetail() {
        return diyPageProductDetail;
    }

    public void setDiyPageProductDetail(DiyPageProductDetail diyPageProductDetail) {
        this.diyPageProductDetail = diyPageProductDetail;
    }

    public DiyPageProductCalendar getDiyPageProductCalendar() {
        return diyPageProductCalendar;
    }

    public void setDiyPageProductCalendar(
    	DiyPageProductCalendar diyPageProductCalendar) {
        this.diyPageProductCalendar = diyPageProductCalendar;
    }

    public DiyPageProductStepOne getDiyPageProductStepOne() {
        return diyPageProductStepOne;
    }

    public void setDiyPageProductStepOne(DiyPageProductStepOne diyPageProductStepOne) {
        this.diyPageProductStepOne = diyPageProductStepOne;
    }

    public DiyPageProductStepTwo getDiyPageProductStepTwo() {
        return diyPageProductStepTwo;
    }

    public void setDiyPageProductStepTwo(DiyPageProductStepTwo diyPageProductStepTwo) {
        this.diyPageProductStepTwo = diyPageProductStepTwo;
    }

    public DiyPageProductTourist getDiyPageProductTourist() {
        return diyPageProductTourist;
    }

    public void setDiyPageProductTourist(DiyPageProductTourist diyPageProductTourist) {
        this.diyPageProductTourist = diyPageProductTourist;
    }

    public int getCurrentPage() {
	//自助页面模块化已经不能继续使用了
/*	String currentActivityString = driver.currentActivity();
	
 	if (currentActivityString.equalsIgnoreCase(".activity.DiyProductDetailActivity")) {
	    return ProductPageCode.PageProductDetail;
	}else if (currentActivityString.equalsIgnoreCase(".activity.DiyOnlineBookStepOneActivity")) {
	    return ProductPageCode.PageProductStepOne;
	}else if (currentActivityString.equalsIgnoreCase(".activity.DiyOnlineBookStepTwoActivity")) {
	    return ProductPageCode.PageProductStepTwo;
	}else if (currentActivityString.equalsIgnoreCase(".activity.TouristsMainActivity")) {
	    return ProductPageCode.PageProductTourist;
	}else if (currentActivityString.equalsIgnoreCase("com.monleylu.paysdk.PaymentActivity")){
	    return ProductPageCode.PageProductCasher;
	}else {
	    return ProductPageCode.PageProductUnDefine;
	}*/
	
	return AppUtil.getCurrentPage(getDriver());
    }

    public AndroidDriver<AndroidElement> getDriver() {
        return driver;
    }

    public void setDriver(AndroidDriver<AndroidElement> driver) {
        this.driver = driver;
    }

}
