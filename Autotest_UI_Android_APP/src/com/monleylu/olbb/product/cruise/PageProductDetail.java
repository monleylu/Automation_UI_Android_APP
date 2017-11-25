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
import io.appium.java_client.android.AndroidKeyCode;


public class PageProductDetail extends BasePage implements CommonActionModule  {

	public PageProductDetail(AndroidDriver<AndroidElement> driver){
	    super(driver);
	}
	

	public boolean clickNextStepBtn() {
		return clickElement(By.id("com.monleylu.cruiseship:id/ll_online_book"));
		
	}



	
	public boolean visitProduct(String productID) {
		
	    	
		this.clickElement(By.id("header_search_homepage"));
		
		//获取原有ime
	    	//String preIME= getDriver().manage().ime().getActiveEngine();
	    	//失活ime
	    	//getDriver().manage().ime().deactivate();
	    	
		getDriver().findElementById("com.monleylu.app.ui:id/ev_search").sendKeys(productID);
		//搜狗等输入法时无法搜索,需要把输入法切换成谷歌之类的输入法
		getDriver().pressKeyCode(AndroidKeyCode.ENTER);
		//getDriver().pressKeyCode(AndroidKeyCode.KEYCODE_SEARCH);
		
		//this.clickElement(By.id("iv_find"));
		
		//激活ime
		//增加空判断否则IME为空时会抛异常
		//if(preIME.length()!=0){	
		//    getDriver().manage().ime().activateEngine(preIME);
		//}
		
		return true;
		
	}



	
	public boolean waitPageLoadReady(int timeToWaitInSec) {
	    //效率差、并且不准确
/*	    boolean mainFrameListView = ElementUtil.isVisibility(this.getDriver(), By.id("android:id/list"), timeToWaitInSec);
	    boolean isVisibleclickNextPageElement = ElementUtil.isVisibility(this.getDriver(), By.id("com.monleylu.app.ui:id/tv_book"), timeToWaitInSec);
	    return mainFrameListView&&isVisibleclickNextPageElement;*/
	    if (ElementUtil.isTextPresent(getDriver(), By.id("com.monleylu.productdetail:id/tv_title"), "当前产品卖完了", 5)) {
		return false;
	    }
	    return ElementUtil.isClickable(getDriver(), By.id("com.monleylu.cruiseship:id/ll_online_book"), timeToWaitInSec);
	}



	
	public boolean waitLeaveCurrentPage(
		int timeToWaitInSec) {
	    // TODO Auto-generated method stub
	    return false;
	}

	

}