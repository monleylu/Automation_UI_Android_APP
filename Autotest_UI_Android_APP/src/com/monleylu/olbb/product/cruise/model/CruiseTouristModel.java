/**
*Copyright (c) 2006-2016 . All Rights Reserved.
*/
package com.monleylu.olbb.product.cruise.model;

import java.util.List;

import org.openqa.selenium.By;

import com.monleylu.olbb.common.AppUtil;
import com.monleylu.olbb.internal.tours.BasePage;
import com.monleylu.olbb.internal.tours.CruiseTouristModelInterface;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import net.sf.cglib.core.TinyBitSet;

public class CruiseTouristModel extends BasePage
	implements CruiseTouristModelInterface {

    public CruiseTouristModel(AndroidDriver<AndroidElement> driver) {
	super(driver);
	
    }

    @Override
    public int getTotalCruiseRoomNum() {
	return getDriver().findElementsByXPath("//android.widget.LinearLayout[@resource-id='com.monleylu.app.ui:id/lv_tourist']/android.widget.LinearLayout").size();
    }

    @Override
    public int getCheckinTouristNum(int indexTourist) {
	int adultNum=0;
	int childNum=0;
	List<AndroidElement> touristTmp=getDriver().findElementsByXPath("//android.widget.LinearLayout[@resource-id='com.monleylu.app.ui:id/lv_tourist']/android.widget.LinearLayout");
	AndroidElement toSelectTourist =touristTmp.get(indexTourist-1);
	String strTourist=toSelectTourist.findElementById("com.monleylu.app.ui:id/tv_num").getAttribute("text");
	if (strTourist.contains("成人")) {
	    adultNum=Integer.parseInt(strTourist.substring(0, strTourist.indexOf("成人")));
	}
	
	if (strTourist.contains("儿童")) {
	    childNum=Integer.parseInt(strTourist.substring(strTourist.indexOf("，")+1, strTourist.indexOf("儿童")));
	}
	
	return adultNum + childNum;
    }

    @Override
    public void clickSelectTouristBtn(int indexTourist) {
	//这种下滑处理是暂时的，选择出游人这块需要改下逻辑
	//2/2有个浮层，在自动化时会出现，不知道什么原因，有时会正好挡住最下层的选择框，加一个滑动处理下这个问题
	AppUtil.swipeUpDown(getDriver(), -300);
	//当有多个房间时，要选择的房间不一定可见继续滚动
	//AppUtil.moveScreenToElement(getDriver(), By.xpath("//android.widget.TextView[@resource-id = 'com.monleylu.app.ui:id/tv_tourist_empty' and @text='点击选择儿童']"), 3);
	
	List<AndroidElement> touristTmp=getDriver().findElementsByXPath("//android.widget.LinearLayout[@resource-id='com.monleylu.app.ui:id/lv_tourist']/android.widget.LinearLayout");
	AndroidElement toSelectTourist =touristTmp.get(indexTourist-1);
	toSelectTourist.findElementById("com.monleylu.app.ui:id/rl_add").click();
	
    }

}
