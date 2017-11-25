/**
*Copyright (c) 2006-2016 . All Rights Reserved.
*/
package com.monleylu.olbb.product.tours;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;

import com.monleylu.olbb.core.PageUtil;
import com.monleylu.olbb.inter.product.BasePage;
import com.monleylu.olbb.inter.product.CommonActionModule;

public class PageDuplicateOrder extends BasePage implements CommonActionModule {

    public PageDuplicateOrder(AndroidDriver<AndroidElement> androidDriver) {
	super(androidDriver);
	// TODO Auto-generated constructor stub
    }

    @Override
    public boolean waitPageLoadReady(int timeToWaitInSec) {
	return PageUtil.waitPageDomLoadReady(getAndroidDriver());
	
    }
    
    /**
     * 点击重单页继续下单按钮
     * @return
     */
    public boolean clickNextStepBtn(){
	
	String currentContext = this.getAndroidDriver().getContext();
	this.getAndroidDriver().context("NATIVE_APP");
	//获取选择下单那一行view
	WebElement duplicateBillElement=this.getAndroidDriver().findElementByXPath("//*[@class='android.view.View' and @index='13']");
	
	Point billBtnPoint=duplicateBillElement.getLocation();
	Dimension billBtnDimension = duplicateBillElement.getSize();
	 //可点击区域只有继续下单 四个字，点击位置需要偏右
	 int x = billBtnPoint.getX() + 2*billBtnDimension.getWidth()/3;
	 int y = billBtnPoint.getY() + billBtnDimension.getHeight()/2;
	 this.getAndroidDriver().tap(1, x, y, 200);
	 this.getAndroidDriver().context(currentContext);
	 
	 return true;
    }

    @Override
    public boolean waitLeaveCurrentPage(int timeToWaitInSec) {
	// TODO Auto-generated method stub
	return false;
    }

}
