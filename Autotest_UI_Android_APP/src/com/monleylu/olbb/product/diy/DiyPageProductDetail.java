/**
*Copyright (c) 2006-2016 . All Rights Reserved.
*/
package com.monleylu.olbb.product.diy;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidKeyCode;

import org.openqa.selenium.By;

import com.monleylu.olbb.common.AppUtil;
import com.monleylu.olbb.common.ElementUtil;
import com.monleylu.olbb.internal.tours.BasePage;
import com.monleylu.olbb.internal.tours.CommonActionModule;
import com.monleylu.olbb.internal.tours.GenericVisitProduct;

public class DiyPageProductDetail extends BasePage implements GenericVisitProduct,CommonActionModule{

    public DiyPageProductDetail(AndroidDriver<AndroidElement> driver) {
	super(driver);
    }

    public boolean visitProduct(String productID) {

	// 获取原有ime
	//String preIME = getDriver().manage().ime().getActiveEngine();
	// 失活ime
	//getDriver().manage().ime().deactivate();
	this.clickElement(By.id("header_search_homepage"));
	getDriver().findElementById("com.monleylu.app.ui:id/ev_search").sendKeys(productID);
	//搜狗等输入法时无法搜索,需要把输入法切换成谷歌之类的输入法
	getDriver().pressKeyCode(AndroidKeyCode.ENTER);
	//this.clickElement(By.id("iv_find"));
	// 激活ime
	// 增加空判断否则IME为空时会抛异常
	/*if (preIME.length() != 0) {
	    getDriver().manage().ime().activateEngine(preIME);
	}*/

	return true;

    }
    
    /**
     * 点击详情页下一步按钮
     * @return 执行完毕返回true
     */
    @Override
    public boolean  clickNextStepBtn() {
	return this.clickElement(By.id("com.monleylu.productdetail:id/tv_book"));
    }
    
    public boolean clickSelectCalendarBtn() {
	return this.clickElement(By.id("com.monleylu.productdetail:id/tv_edit"));
    }
    /**
     * 滚动页面到描述或者名称中包含指定字符的元素位置
     * @param byLocator 元素定位符
     * @param scrollTime 滚动次数
     * @return
     * @throws Exception 
     *
     */
    public boolean viewScrollTo(By byLocator,int scrollTime) throws Exception{
	//default scroll number to  limit scroll times
	 
	AppUtil.swipeUpDown(getDriver(), -200);
	if (ElementUtil.isVisibility(getDriver(), byLocator, 1)) {
	    //because price bar
	    AppUtil.swipeUpDown(getDriver(), -200);
	    return true;
	}else {
	    if (scrollTime<=0) {
		throw new Exception("scroll too much more time,but still could not find " + byLocator.toString());
	    }
	    viewScrollTo(byLocator, scrollTime-1);
	}
	
	return true;
	
	
	
    }
    
    /**
     * 滚动页面到选择出游人数和日期位置
     * @return
     * @throws Exception could not find element
     */
    public boolean viewScrollToSelectCalendar() throws Exception {
	//return this.viewScrollTo(By.id("com.monleylu.productdetail:id/tv_travel_info"),10);
	//由于bottombar是浮层，需要多滚动一些，目前以滚到日期那一行为准，多滚一个元素位置
	return AppUtil.moveScreenToElement(getDriver(), By.id("com.monleylu.productdetail:id/hlv_plan_date"), 10);
    }
    
    /**
     * 判断是否存在机票异常弹窗
     * @param timeToWaitInSec 等待时间
     * @return 存在返回true
     */
    public boolean isExistFlightExceptionDialog(int timeToWaitInSec) {
	return ElementUtil.isVisibility(this.getDriver(), By.id("android:id/parentPanel"), timeToWaitInSec);
    }
    
    /**
     * 点击机票异常弹窗取消按钮
     * @return
     */
    public boolean clickCalcelBtnOfFlightExcrptionDialog(){
	return this.clickElement(By.id("android:id/button1"));
    }
    
    /**
     * 点击机票异常弹窗确认或者更改航班按钮
     * @return
     */
    public boolean clickConfirmBtnOfFlightExcrptionDialog(){
	return this.clickElement(By.id("android:id/button2"));
    }



    @Override
    public boolean waitPageLoadReady(int timeToWaitInSec) {
	//目前仅通过判断下一步按钮状态来判断页面是否加载完毕
	boolean isNextPageBtnEnable = ElementUtil.isTextPresent(this.getDriver(), By.id("com.monleylu.productdetail:id/tv_book"), "立即预订" , timeToWaitInSec);
	
	return isNextPageBtnEnable;
    }

    @Override
    public boolean waitLeaveCurrentPage(int timeToWaitInSec) {
	// TODO Auto-generated method stub
	return false;
    }
    


}
