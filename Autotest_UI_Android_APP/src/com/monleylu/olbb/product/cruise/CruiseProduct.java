/**
*Copyright (c) 2006-2016 . All Rights Reserved.
*/
package com.monleylu.olbb.product.cruise;

import com.monleylu.olbb.product.cruise.PageProductCalendar;
import com.monleylu.olbb.product.cruise.PageProductDetail;
//import com.monleylu.olbb.product.cruise.PageProductRepeatOrder;
import com.monleylu.olbb.product.cruise.PageProductStepOne;
import com.monleylu.olbb.product.cruise.PageProductStepTwo;
import com.monleylu.olbb.product.cruise.PageProductTourist;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class CruiseProduct {
	private AndroidDriver<AndroidElement> driver;
	private PageProductDetail pageProductDetail;
	private PageProductCalendar pageProductCalendar;
	private PageProductFastOrder pageProductFastOrder;
	//private PageProductRepeatOrder pageProductRepeatOrder;
	private PageProductStepOne pageProductStepOne;
	private PageProductStepTwo pageProductStepTwo;
	private PageProductTourist pageProductTourist;
	
	public CruiseProduct(AndroidDriver<AndroidElement> driver) {
		this.setDriver(driver);
		this.setPageProductDetail(new PageProductDetail(driver));
		this.setPageProductCalendar(new PageProductCalendar(driver));
		this.setPageProductFastOrder(new PageProductFastOrder(driver));
		this.setPageProductStepOne(new PageProductStepOne(driver));
		this.setPageProductStepTwo(new PageProductStepTwo(driver));
		this.setPageProductTourist(new PageProductTourist(driver));
		
	}

	public AndroidDriver<AndroidElement> getDriver() {
	    return driver;
	}

	public void setDriver(AndroidDriver<AndroidElement> driver) {
	    this.driver = driver;
	}

	public PageProductDetail getPageProductDetail() {
	    return pageProductDetail;
	}

	public void setPageProductDetail(PageProductDetail pageProductDetail) {
	    this.pageProductDetail = pageProductDetail;
	}

	public PageProductCalendar getPageProductCalendar() {
	    return pageProductCalendar;
	}

	public void setPageProductCalendar(PageProductCalendar pageProductCalendar) {
	    this.pageProductCalendar = pageProductCalendar;
	}

	public PageProductStepOne getPageProductStepOne() {
	    return pageProductStepOne;
	}

	public void setPageProductStepOne(PageProductStepOne pageProductStepOne) {
	    this.pageProductStepOne = pageProductStepOne;
	}

	public PageProductStepTwo getPageProductStepTwo() {
	    return pageProductStepTwo;
	}

	public void setPageProductStepTwo(PageProductStepTwo pageProductStepTwo) {
	    this.pageProductStepTwo = pageProductStepTwo;
	}

	public PageProductTourist getPageProductTourist() {
	    return pageProductTourist;
	}

	public void setPageProductTourist(PageProductTourist pageProductTourist) {
	    this.pageProductTourist = pageProductTourist;
	}

	public PageProductFastOrder getPageProductFastOrder() {
	    return pageProductFastOrder;
	}

	public void setPageProductFastOrder(PageProductFastOrder pageProductFastOrder) {
	    this.pageProductFastOrder = pageProductFastOrder;
	}
	
	
	
}
