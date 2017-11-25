/**
*Copyright (c) 2006-2016 . All Rights Reserved.
*/
package com.monleylu.olbb.product.cruise;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;

import com.monleylu.olbb.common.AppUtil;
import com.monleylu.olbb.common.ElementUtil;
import com.monleylu.olbb.internal.tours.BasePage;
import com.monleylu.olbb.internal.tours.CommonActionModule;
import com.monleylu.olbb.product.cruise.model.CruiseTouristModel;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class PageProductStepTwo extends BasePage implements CommonActionModule {

 
	
	public PageProductStepTwo(AndroidDriver<AndroidElement> driver){
		super(driver);
	}
	
	 
	CruiseTouristModel cruiseTouristModel = new CruiseTouristModel(getDriver());
	

	 
	public CruiseTouristModel getCruiseTouristModel() {
	    return cruiseTouristModel;
	}



	public boolean clickSelectTouristElement() {
		
		return this.clickElement(By.id("com.monleylu.app.ui:id/bt_tourist_copy"));
		
	}

	 
	/**
	 * 出游人满足出游条件
	 * @return
	 */
	public boolean clickTouristSatisfyAllRequirementElement() {
		return this.clickElement(By.id("com.monleylu.app.ui:id/iv_ignore_all"));
	}

	 /**
	  * 阅读并接受协议
	  * @return
	  */
	public boolean clickReadLawsAndOtherElement() {
		return this.clickElement(By.id("com.monleylu.app.ui:id/iv_read_accept"));
	}

	@Override
	public boolean clickNextStepBtn() {
		if (!getDriver().findElementById("com.monleylu.app.ui:id/btn_submit").getAttribute("enabled").equalsIgnoreCase("false")) {
		    getDriver().findElementById("com.monleylu.app.ui:id/btn_submit").click();
			return true;
		}else {
			return false;
		}
	}

	 
	public boolean scrollToBottom() {
		Dimension windowDimension = getDriver().manage().window().getSize();
		getDriver().swipe(windowDimension.getWidth()/2, windowDimension.getHeight()*2/3, windowDimension.getWidth()/2, 0, 70);
		try {
		    Thread.sleep(1000);
		} catch (InterruptedException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}
		return true;
	}

	 
	public String getOrderID() {
	    return getDriver().findElement(By.id("com.monleylu.app.ui:id/tv_auto_test_order_id")).getAttribute("text");
	}

	 
	public boolean isVisibleLayOutView(int timeToWaitInSec) {
	    return ElementUtil.isVisibility(getDriver(), By.id("com.monleylu.app.ui:id/plane_wait_layout"), timeToWaitInSec);
	}

	/**
	 * 机票浮层弹窗
	 * @param timeToWaitInsec
	 * @return
	 */
	public boolean isExistFlightTipView(int timeToWaitInsec){
	     if(ElementUtil.isExist(getDriver(), By.id("android:id/message"), timeToWaitInsec)){
		 if (getDriver().findElementById("android:id/message").getAttribute("text").contains("机票年龄标准成人12周岁及以上、儿童2周岁(含)-12周岁(不含)")) {
		    return true;
		}else{
		    return false;
		}
	     }else{
		 return false;
	     }
	     
	}
	
	public void clickSubmitOrderBtnOfFlightTipView(){
	    getDriver().findElementById("android:id/button2").click();
	}
	
	public void clickContinusBookBtnOfFlightTipView(){
	    getDriver().findElementById("android:id/button1").click();
	}
	
	public boolean isEnableClickNextPageElement() {
	    
	    if(getDriver().findElement(By.id("com.monleylu.app.ui:id/btn_submit")).getAttribute("enabled").equalsIgnoreCase("true")){
		return true;
	    }else {
		return false;
	    }
	}

 
	public boolean isInVisibleLayOutView(int timeToWaitInSec) {
	    return ElementUtil.isInVisibility(getDriver(), By.id("com.monleylu.app.ui:id/plane_wait_layout"), timeToWaitInSec);
	}

	@Override
	public boolean waitPageLoadReady(int timeToWaitInSec) {
	    
	   
	    // boolean stepOneTitle = ElementUtil.isVisibility(this.getDriver(), By.id("com.monleylu.app.ui:id/tv_header_title"), timeToWaitInSec);
	    boolean clickNextPageBtn = ElementUtil.isVisibility(this.getDriver(), By.id("com.monleylu.app.ui:id/btn_submit"), timeToWaitInSec);
	    //return stepOneTitle&&clickNextPageBtn;
	    return clickNextPageBtn;
	    
	    
	}

	@Override
	public boolean waitLeaveCurrentPage(
		int timeToWaitInSec) {
	    // TODO Auto-generated method stub
	    return false;
	}

	 
	public boolean isVisibleCheckOutDialog(int timeToWaitInSec) {
	   boolean textClildCheckOut =  ElementUtil.isTextPresent(this.getDriver(), By.id("com.monleylu.app.ui:id/tv_dialog_title"), "婴儿校验", timeToWaitInSec);
	   boolean textUncommCheckOut = ElementUtil.isTextPresent(this.getDriver(), By.id("com.monleylu.app.ui:id/tv_dialog_title"), "生僻字校验", timeToWaitInSec);
	   return textClildCheckOut||textUncommCheckOut;
	}

	 
	public boolean sendFirstNameOfCheckOutDialog(String textToInput) {
	    return this.sendKeysOfCheckOutDialog(By.id("com.monleylu.app.ui:id/et_dialog_first_name"), textToInput);
	}

	 
	public boolean sendLastNameOfCheckOutDialog(String textToInput) {
	    
	    return this.sendKeysOfCheckOutDialog(By.id("com.monleylu.app.ui:id/et_dialog_last_name"), textToInput);
	}
	
	private boolean  sendKeysOfCheckOutDialog(By by, String textToInput) {
	    this.getDriver().findElement(by).sendKeys(textToInput);
	    return true;
	}

	 
	public boolean clickCancelBtnOfCheckOutDialog() {
	    
	    return this.clickElement(By.id("com.monleylu.app.ui:id/btn_dialog_cancel"));
	}

 
	public boolean clickConfirmBtnOfCheckOutDialog() {
	    
	    return this.clickElement(By.id("com.monleylu.app.ui:id/btn_dialog_confirm"));
	    
	}
	
	/**
	 * 点击选择成人框
	 */
	public void clickSelectAdultBtn(){
	    clickSelectTourist(1);
	}
	
	/**
	 * 点击选择儿童框
	 */
	public void clickSelectChildBtn(){
	    //2/2有个浮层，在自动化时会出现，不知道什么原因，有时会正好挡住第一个儿童选择框，加一个滑动处理下这个问题
	    AppUtil.swipeUpDown(getDriver(), -300);
	    //当成人出游人过多时，儿童出游人框不一定可见，继续滚动
	    AppUtil.moveScreenToElement(getDriver(), By.xpath("//android.widget.TextView[@resource-id = 'com.monleylu.app.ui:id/tv_tourist_empty' and @text='点击选择儿童']"), 3);
	    clickSelectTourist(0);
	}
	
	/**
	 * 2/2页面选择不同类型出游人
	 * @param personType 1:adult;0:child
	 */
	private void clickSelectTourist(int personType){
	    AndroidElement touristView=getDriver().findElement(By.id("com.monleylu.app.ui:id/view_tourist_ticket"));
	    if (1==personType) {
		touristView.findElementByXPath("//android.widget.TextView[@resource-id = 'com.monleylu.app.ui:id/tv_tourist_empty' and @text='点击选择成人游客']").click();
	    }else if (0==personType) {
		touristView.findElementByXPath("//android.widget.TextView[@resource-id = 'com.monleylu.app.ui:id/tv_tourist_empty' and @text='点击选择儿童']").click();
	    }
	}

	/**
	 * 选择签注类型
	 * @param signTypeElement 签注元素
	 */
	private void selectSignType(AndroidElement signTypeElement){
	    signTypeElement.click();
	    if(ElementUtil.isVisibility(getDriver(), By.id("com.monleylu.app.ui:id/rg_sign_type"), 1)){
		switch (new Random().nextInt(4)) {
		case 0:
		    getDriver().findElementById("com.monleylu.app.ui:id/rb_person").click();
		    break;
		case 1:
		    getDriver().findElementById("com.monleylu.app.ui:id/rb_team").click();
		    break;
		case 2:
		    getDriver().findElementById("com.monleylu.app.ui:id/rb_else").click();
		    break;
		case 3:
		    getDriver().findElementById("com.monleylu.app.ui:id/rb_null").click();
		    break;
		default:
		    break;
		}
	    }
	}
	
	
	private List<AndroidElement> getCurrentPageShowSignType(){
	   return getDriver().findElementsByXPath("//android.widget.TextView[@resource-id ='com.monleylu.app.ui:id/tv_sign_type' and @text = '签注类型']");
	}
	
	/**
	 * 选择所有出游人的签证
	 * @param touristNum 出游人总数
	 */
	public void selectAllSignType(int touristNum){
	    List<AndroidElement> list=getCurrentPageShowSignType();
	    for(int i=0;i<list.size();i++){
		selectSignType(list.get(i));
	    }
	    if (touristNum-list.size()!=0) {
		AppUtil.moveScreenToElement(getDriver(), By.xpath("//android.widget.TextView[@resource-id ='com.monleylu.app.ui:id/tv_sign_type' and @text = '签注类型']"), 2);
		selectAllSignType(touristNum-list.size());
	    }
	}
	
	public boolean isNeedSignType(){
	    return ElementUtil.isExist(getDriver(), By.xpath("//android.widget.TextView[@resource-id ='com.monleylu.app.ui:id/tv_sign_type' and @text = '签注类型']"));
	}

}

