/**
*Copyright (c) 2006-2016 . All Rights Reserved.
*/
package com.monleylu.olbb.product.tours;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import org.openqa.selenium.By;

import com.monleylu.olbb.core.ElementUtil;
import com.monleylu.olbb.core.PageUtil;
import com.monleylu.olbb.inter.product.BasePage;
import com.monleylu.olbb.inter.product.CommonActionModule;
import com.monleylu.olbb.login.Login;



public class PageFreeGiftPromation extends BasePage implements CommonActionModule,FreeGiftPromationModule{

    public PageFreeGiftPromation(AndroidDriver<AndroidElement> androidDriver) {
	super(androidDriver);
	// TODO Auto-generated constructor stub
    }

    @Override
    public boolean waitPageLoadReady(int timeToWaitInSec) {
	
    	PageUtil.waitPageDomLoadReady(this.getAndroidDriver());
		return ElementUtil.waitAttributeToBeOnExpectTime(getAndroidDriver(), By.id("loading"), "style", "display: none;", timeToWaitInSec);
    }

    @Override
    public boolean waitLeaveCurrentPage(int timeToWaitInSec) {
	// TODO Auto-generated method stub
	return false;
    }
    
    @Override
	public boolean clickNextStepBtn() {
		// TODO Auto-generated method stub
		return false;
	}
    @Override
    //判断是否有添加地址模块露出（多个优惠时返赠品优惠会被收起、或者没有返赠品优惠）
    public boolean isExitFreeGiftPro(int timeToWaitInSec){
    	
    	if(ElementUtil.waitVisibilityOnExpectTime(getAndroidDriver(), By.xpath("//div[@class='gift-address content-cell edit-show']"), timeToWaitInSec))
    		return true;
    	else{
    		this.clickElement(this.find(By.xpath("//*[@id='promoChange']")));
    		if(ElementUtil.waitVisibilityOnExpectTime(getAndroidDriver(), By.xpath("//div[@class='gift-address content-cell edit-show']"), timeToWaitInSec))
        		return true;
    		else
    			return false;
    	}
    }
    @Override
    //点击添加地址跳登录页面登录
    public void clickAddAddress(){
    	String jsString = "$('.gift-address-status').click()";
	    this.getAndroidDriver().executeScript(jsString);
    }
    @Override
    //判断是否跳转到登录页面
    public boolean isExistLoginButt(int timeToWaitInSec){
    	return ElementUtil.waitVisibilityOnExpectTime(getAndroidDriver(), By.xpath("//*[@id='normalNum']"), timeToWaitInSec);
    }
    @Override
    //使用cookie登录
    public boolean isLoginSuccess(String hostUrl){
    	String testCookiesFileName = "Cookie_com.test.testcase.product.tours.ToursFreeGiftPromationTest.txt";
    	return Login.LoginToStepOne(getAndroidDriver(),hostUrl,testCookiesFileName);
    }
    @Override
    //选择第一个地址回到1/2;点击一次页面会刷新，故这里点击两次，手动操作没有该问题
    public void selcFirstAddress(){
    	if(ElementUtil.waitVisibilityOnExpectTime(getAndroidDriver(), By.xpath("//div[@class='T_address_list T_body']/div[1]/div[1]/div"), 5))
    		this.clickElement(this.find(By.xpath("//div[@class='T_address_list T_body']/div[1]/div[1]/div")));
    	if(ElementUtil.waitVisibilityOnExpectTime(getAndroidDriver(), By.xpath("//div[@class='T_address_list T_body']/div[1]/div[1]/div"), 5))
    		this.clickElement(this.find(By.xpath("//div[@class='T_address_list T_body']/div[1]/div[1]/div")));
    }
    @Override
    //选中返赠品优惠
    public void slecFreeGiftPromation(int timeToWaitInSec){
    	if(ElementUtil.waitVisibilityOnExpectTime(getAndroidDriver(), By.xpath("//div[@class='gift-address content-cell edit-show']"), timeToWaitInSec))
    		this.clickElement(this.find(By.xpath("//*[@id='promotion_134688']/div[1]/i")));
    	else{
    		this.clickElement(this.find(By.xpath("//*[@id='promoChange']")));
    		if(ElementUtil.waitVisibilityOnExpectTime(getAndroidDriver(), By.xpath("//div[@class='gift-address content-cell edit-show']"), timeToWaitInSec))
    			this.clickElement(this.find(By.xpath("//*[@id='promotion_134688']/div[1]/i")));
    	}
    	
    }

}
