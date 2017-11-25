/**
*Copyright (c) 2006-2016 . All Rights Reserved.
*/
package com.monleylu.olbb.product.drive;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import org.openqa.selenium.By;

import com.monleylu.olbb.common.ElementUtil;
import com.monleylu.olbb.internal.tours.BasePage;
import com.monleylu.olbb.internal.tours.CommonActionModule;

public class PageProductStepOne extends BasePage implements CommonActionModule{

    public PageProductStepOne(AndroidDriver<AndroidElement> driver) {
	super(driver);
    }

    @Override
    public boolean waitPageLoadReady(int timeToWaitInSec) {
	
	return ElementUtil.isInVisibility(this.getDriver(), By.id("com.monleylu.app.ui:id/tv_loading"), timeToWaitInSec);
	
	  
    }
    
    

    public boolean clickNextStepBtn() {
	return this.clickElement(By.id("com.monleylu.app.ui:id/tv_submit"));
    }

    @Override
    public boolean waitLeaveCurrentPage(int timeToWaitInSec) {
	// TODO Auto-generated method stub
	return false;
    }

}
