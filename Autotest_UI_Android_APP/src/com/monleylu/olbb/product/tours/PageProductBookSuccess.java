package com.monleylu.olbb.product.tours;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import org.openqa.selenium.By;

import com.monleylu.olbb.internal.tours.BasePage;
import com.monleylu.olbb.internal.tours.CommonActionModule;

public class PageProductBookSuccess extends BasePage implements CommonActionModule {


    
    public PageProductBookSuccess(AndroidDriver<AndroidElement> driver) {
	super(driver);
    }




    public String getPageTitleString() {
	return getDriver().findElement(By.id("com.monleylu.app.ui:id/tv_header_title")).getAttribute("text");
	
    }


    public String getOrderID() {
	return getDriver().findElement(By.id("com.monleylu.app.ui:id/tv_auto_test_order_id")).getAttribute("text");
    }

    @Override
    public boolean waitPageLoadReady(int timeToWaitInSec) {
	// TODO Auto-generated method stub
	return false;
    }

    @Override
    public boolean waitLeaveCurrentPage(
	    int timeToWaitInSec) {
	// TODO Auto-generated method stub
	return false;
    }


    @Override
    public boolean clickNextStepBtn() {
	// TODO Auto-generated method stub
	return false;
    }

}
