package com.monleylu.olbb.product.tours;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import org.openqa.selenium.By;

import com.monleylu.olbb.common.ElementUtil;
import com.monleylu.olbb.internal.tours.BasePage;
import com.monleylu.olbb.internal.tours.CommonActionModule;

public abstract class PageProductCasher extends BasePage implements CommonActionModule{
    

	
    public PageProductCasher(AndroidDriver<AndroidElement> driver) {
	super(driver);
    }



 
    public String getPageTitleString() {
	
	try {
	    return getDriver().findElement(By.id("com.monleylu.app.ui:id/sdk_tv_header_title")).getAttribute("text");
	} catch (Exception e) {
	    // TODO: handle exception
	    e.printStackTrace();
	   return "";
	}
	
	
    }

 
    public boolean isPageProductCasher() {
	
	//存在主view
	boolean mainFrameLayOut = ElementUtil.isExist(getDriver(),By.id("com.monleylu.app.ui:id/sdk_ll_root"));
	boolean payAccountLayOut= ElementUtil.isExist(getDriver(),By.id("com.monleylu.app.ui:id/sdk_layout_order_pay_content"));
	boolean payTypeLayOut = ElementUtil.isExist(getDriver(),By.id("com.monleylu.app.ui:id/sdk_lv_pay_type"));
	
	return mainFrameLayOut&&payAccountLayOut&&payTypeLayOut;
	
    }

    @Override
    public boolean waitPageLoadReady(int timeToWaitInSec) {
	// TODO Auto-generated method stub
	return false;
    }
    

}
