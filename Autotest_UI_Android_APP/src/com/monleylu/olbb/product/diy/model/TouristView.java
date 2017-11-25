/**
*Copyright (c) 2006-2016 . All Rights Reserved.
*/
package com.monleylu.olbb.product.diy.model;

import com.monleylu.olbb.internal.tours.BasePage;
import com.monleylu.olbb.internal.tours.TouristModelInterface;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

/**
 * 
* @Description: 定义自助品类火车票地接资源时选择出游人
* @author lujian
* @date 2017年7月8日
* @version 
*    2017年7月8日  v1.0  create 
*
 */
public class TouristView extends BasePage implements TouristModelInterface {

   
    public TouristView(AndroidDriver<AndroidElement> driver) {
	super(driver);
	// TODO Auto-generated constructor stub
    }

    @Override
    public void clickSelectAdultTouristsBtn() {
	getDriver().findElementByXPath("//android.widget.TextView[@resource-id ='com.monleylu.productdetail:id/tv_tourist_empty' and @text='点击选择成人游客'] ").click();
	
    }

    @Override
    public void clickSelectChildTouristsBtn() {
	getDriver().findElementByXPath("//android.widget.TextView[@resource-id ='com.monleylu.productdetail:id/tv_tourist_empty' and @text='点击选择儿童(含火车票)'] ").click();
	
    }

    @Override
    public void clickSelectFreeChildTouristsBtn() {
	getDriver().findElementByXPath("//android.widget.TextView[@resource-id ='com.monleylu.productdetail:id/tv_tourist_empty' and @text='点击选择儿童(免火车票)'] ").click();
	
    }



}
