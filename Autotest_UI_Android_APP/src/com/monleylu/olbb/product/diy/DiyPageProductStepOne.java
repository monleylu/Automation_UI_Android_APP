/**
*Copyright (c) 2006-2016 . All Rights Reserved.
*/
package com.monleylu.olbb.product.diy;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import org.openqa.selenium.By;

import com.monleylu.olbb.common.ElementUtil;
import com.monleylu.olbb.internal.tours.BasePage;
import com.monleylu.olbb.internal.tours.CommonActionModule;

public class DiyPageProductStepOne extends BasePage implements CommonActionModule {

    public DiyPageProductStepOne(AndroidDriver<AndroidElement> driver) {
	super(driver);
	// TODO Auto-generated constructor stub
    }

    @Override
    public boolean waitPageLoadReady(int timeToWaitInSec) {
	
	boolean isLoadIcon =ElementUtil.isInVisibility(this.getDriver(), By.id("com.monleylu.productdetail:id/tv_loading"), timeToWaitInSec);
	return isLoadIcon;
    }
    
    /**
     * 点击1/2下一步按钮
     * @return 执行完毕返回true
     */
    public boolean clickNextStepBtn() {
	return this.clickElement(By.id("com.monleylu.productdetail:id/tv_submit"));
    }



    @Override
    public boolean waitLeaveCurrentPage(int timeToWaitInSec) {
	// TODO Auto-generated method stub
	return false;
    }

}
