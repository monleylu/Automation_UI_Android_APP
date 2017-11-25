/**
*Copyright (c) 2006-2016 . All Rights Reserved.
*/
package com.monleylu.olbb.product.diy;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import org.openqa.selenium.By;

import com.monleylu.olbb.internal.tours.BasePage;
import com.monleylu.olbb.internal.tours.CommonActionModule;

public class PageProductDetail extends BasePage implements CommonActionModule{

    public PageProductDetail(AndroidDriver<AndroidElement> driver) {
	super(driver);
    }

    /**
     * 访问产品
     * @param productID 产品id
     * @return 访问成功返回true
     */
    public boolean visitProduct(String productID) {
	this.clickElement(By.id("header_search_homepage"));
	this.getDriver().findElementById("ev_search").sendKeys(productID);
	this.clickElement(By.id("iv_find"));
	return true;
    }

    @Override
    public boolean waitPageLoadReady(int timeToWaitInSec) {
	// TODO Auto-generated method stub
	return false;
    }

    @Override
    public boolean clickNextStepBtn() {
	// TODO Auto-generated method stub
	return false;
    }

    @Override
    public boolean waitLeaveCurrentPage(int timeToWaitInSec) {
	// TODO Auto-generated method stub
	return false;
    }

}
