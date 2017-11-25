/**
*Copyright (c) 2006-2016 . All Rights Reserved.
*/
package com.monleylu.olbb.product.cruise;

import org.openqa.selenium.By;

import com.monleylu.olbb.common.ElementUtil;
import com.monleylu.olbb.internal.tours.BasePage;
import com.monleylu.olbb.internal.tours.CommonActionModule;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

/**
 * 
* @Description: 快速网络单页面
* @author lujian
* @date 2017年7月11日
* @version 
*    2017年7月11日  v1.0  create 
*
 */
public class PageProductFastOrder extends BasePage implements CommonActionModule{

    public PageProductFastOrder(AndroidDriver<AndroidElement> driver) {
	super(driver);
	// TODO Auto-generated constructor stub
    }

    @Override
    public boolean clickNextStepBtn() {
	getDriver().findElementById("com.monleylu.app.ui:id/tv_bottom").click();
	return true;
    }

    @Override
    public boolean waitPageLoadReady(int timeToWaitInSec) {
	
	return ElementUtil.isVisibility(getDriver(), By.id("com.monleylu.app.ui:id/tv_bottom"), timeToWaitInSec);
    }

    @Override
    public boolean waitLeaveCurrentPage(int timeToWaitInSec) {
	// TODO Auto-generated method stub
	return false;
    }
    
    
    

}
