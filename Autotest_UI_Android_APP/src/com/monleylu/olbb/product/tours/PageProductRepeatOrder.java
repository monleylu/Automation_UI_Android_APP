package com.monleylu.olbb.product.tours;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import org.openqa.selenium.By;

import com.monleylu.olbb.common.ElementUtil;
import com.monleylu.olbb.internal.tours.BasePage;
import com.monleylu.olbb.internal.tours.CommonActionModule;

public class PageProductRepeatOrder extends BasePage implements CommonActionModule {

    
    public PageProductRepeatOrder(AndroidDriver<AndroidElement> driver) {
	super(driver);
    }



  
    public boolean isExistRepeatOrder() {
	return ElementUtil.isVisibility(getDriver(), By.id("com.monleylu.app.ui:id/tv_order_continue"), 3);
    }


    public boolean clickNextStepBtn() {
	return clickElement(By.id("com.monleylu.app.ui:id/tv_order_continue"));
	
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

}
