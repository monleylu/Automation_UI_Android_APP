/**
*Copyright (c) 2006-2016 . All Rights Reserved.
*/
package com.monleylu.olbb.product.cruise;

import com.monleylu.olbb.product.cruise.model.CruiseRoomModel;
import org.openqa.selenium.By;

import com.monleylu.olbb.common.ElementUtil;
import com.monleylu.olbb.internal.tours.BasePage;
import com.monleylu.olbb.internal.tours.CommonActionModule;
import com.monleylu.olbb.internal.tours.GenericFlightModule;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class PageProductStepOne extends BasePage implements CommonActionModule,GenericFlightModule{

	
	
	public PageProductStepOne(AndroidDriver<AndroidElement> driver){
	    super(driver);
	}

	public CruiseRoomModel getCruiseRoomModel() {
		return cruiseRoomModel;
	}

	private CruiseRoomModel cruiseRoomModel = new CruiseRoomModel(getDriver());

	public boolean clickNextStepBtn(By by) {
		getDriver().findElement(by).click();
		return Boolean.TRUE;
	}

	
	public boolean clickNextPageElement() {
		
		return this.clickNextStepBtn(By.id("com.monleylu.app.ui:id/btn_submit"));
		
	}

	@Override
	public boolean waitPageLoadReady(int timeToWaitInSec) {
	    //也可以通过activity判断页面是否出现,由于页面存在异步加载机票、保险、优惠等资源，特别是机票加载非常慢，这里只通过activity判断是否进入当前页面，其他代码来处理异步加载情况
	    /*boolean stepOneTitle = ElementUtil.isVisibility(this.getDriver(), By.id("com.monleylu.app.ui:id/tv_header_title"), timeToWaitInSec);
	    boolean clickNextPageBtn = ElementUtil.isVisibility(this.getDriver(), By.id("com.monleylu.app.ui:id/bt_next"), timeToWaitInSec);
	    return stepOneTitle&&clickNextPageBtn;*/
	    //9.1.7改版后页面变化
/*	    int loopCount=30;
	    while (loopCount>=0) {
		if (ProductPageCode.PageProductStepOne==AppUtil.getCurrentPage(getDriver())) {
		    return true;
		}
		
		try {
		    Thread.sleep(1000);
		} catch (InterruptedException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}
		
		loopCount--;
	    }
	   return false;*/
	    //判断牛头是否消失，现在页面加载会有loading头像
	   boolean b1=ElementUtil.isInVisibility(getDriver(), By.id("com.monleylu.app.ui:id/tv_loading"), timeToWaitInSec);
	   boolean b2=ElementUtil.isClickable(getDriver(), By.id("com.monleylu.app.ui:id/btn_submit"), timeToWaitInSec);
	   return b1&&b2;
	}

	@Override
	public boolean waitLeaveCurrentPage(
		int timeToWaitInSec) {
	    // TODO Auto-generated method stub
	    return false;
	}

	
	public boolean isExistFlightSellOutDialog(int timeToWaitInSec) {
	    
	    return ElementUtil.isTextPresent(this.getDriver(), By.id("android:id/message"), "您预订的本次航班的舱位已经售空，请您更换其他航班后继续预订", timeToWaitInSec);
	     
	}

	 
	public void clickConfirmBtnOfFlightSellOutDialog() {
	    
	     this.clickNextStepBtn(By.id("android:id/button2"));
	    
	}

	 
	public void cilckChangeFlightBtnOfFlightSellOutDialog() {
	    
	     this.clickNextStepBtn(By.id("android:id/button1"));
	    
	}

	 
	public boolean isClickableOfNextPageElement() {
	    
	    return  ElementUtil.isClickable(this.getDriver(), By.id("com.monleylu.app.ui:id/btn_submit"), 1);
	}

	
	public boolean isExistFlightExceptionDialog(int timeToWaitInSec) {
	   
	    return ElementUtil.isTextPresent(this.getDriver(), By.id("android:id/message"), "当前机票信息异常，您可尝试更换其他航班后继续预订，或者提交免费的咨询订单，等待客服电话联系您", timeToWaitInSec);
	     
	}

	 
	public void clickBookNoticeBtnOfFlightExceptionDialog() {
	     this.clickNextStepBtn(By.id("android:id/button2"));
	}

	 
	public void clickChangeFlightBtnOfFlightExceptionDialog() {
	     this.clickNextStepBtn(By.id("android:id/button1"));
	}

	public boolean isExistDialog(int timeToWaitInSec) {
	    //目前看机票弹窗都是用的一个窗口，只是展示的文案有区别
	    return ElementUtil.isVisibility(this.getDriver(), By.id("android:id/parentPanel"), timeToWaitInSec);
	    
	}


	@Override
	public boolean clickNextStepBtn() {
	    // TODO Auto-generated method stub
	    return this.clickNextStepBtn(By.id("com.monleylu.app.ui:id/btn_submit"));
	}


	@Override
	public boolean isExistFlightMoneyChangeDialog(int timeToWaitInSec) {
	    
	    return ElementUtil.isTextPresent(this.getDriver(), By.id("android:id/message"), "很抱歉，航班价格变化频繁，该航班价格发生变动", timeToWaitInSec);
	}

	/**
	 * 判断是否存在“您好，当前选择的邮轮入住人数与出游人数不一致，请修改”弹窗
	 * @param timeToWaitInSec
	 * @return
	 */
	public boolean isExistCruiseChenkinUnmatch(int timeToWaitInSec){
	    return ElementUtil.isTextPresent(getDriver(), By.id("com.monleylu.app.ui:id/tv_text_des"), "您好，当前选择的邮轮入住人数与出游人数不一致，请修改", timeToWaitInSec);
	}

	@Override
	public void clickConfirmBtnOfFlightMoneyChangeDialog() {
	    this.clickNextStepBtn(By.id("android:id/button2"));
	    
	}


	@Override
	public void cilckChangeFlightBtnOfFlightMoneyChangeDialog() {
	    this.clickNextStepBtn(By.id("android:id/button1"));
	    
	}
	

}

