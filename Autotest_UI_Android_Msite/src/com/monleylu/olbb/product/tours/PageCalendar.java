/**
*Copyright (c) 2006-2016 . All Rights Reserved.
*/
package com.monleylu.olbb.product.tours;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;

import com.monleylu.olbb.core.ElementUtil;
import com.monleylu.olbb.core.PageUtil;
import com.monleylu.olbb.inter.product.BasePage;
import com.monleylu.olbb.inter.product.CalendarModule;
import com.monleylu.olbb.inter.product.CommonActionModule;

public class PageCalendar extends BasePage implements CommonActionModule, CalendarModule{

    public PageCalendar(AndroidDriver<AndroidElement> androidDriver) {
	super(androidDriver);
    }

    @Override
    public boolean waitPageLoadReady(int timeToWaitInSec) {
	PageUtil.waitPageDomLoadReady(this.getAndroidDriver());
	return ElementUtil.waitInVisibilityOnExpectTime(this.getAndroidDriver(), By.xpath("//div[@class='loading bg-white J_body_loading']"), timeToWaitInSec);
    }

    @Override
    public String getRandomTourDate() {
	
	//获取价格日历页面月份标签
	List<AndroidElement> listMonthElements = this.getAndroidDriver().findElements(By.xpath("//div[@id='J_calendar-tabs']/section/ul/li")); 
	
	//随机获取一个月份进行切换
	int randomMonthTab= new Random().nextInt(listMonthElements.size());
	int monthTabToSelect=randomMonthTab +1;
	this.clickElement(this.find(By.xpath("//div[@id='J_calendar-tabs']/section/ul/li[" + monthTabToSelect +"]")));
	
	List<AndroidElement> listWebElements = this.getAndroidDriver().findElementsByXPath("//td[@data-key]");
	
	int sumCountWeb = listWebElements.size();
	int randomNum = new Random().nextInt(sumCountWeb);
	return listWebElements.get(randomNum).getAttribute("data-key");
	
    }

    @Override
    public boolean selectTourDate(String bookDate) {
	//保存待执行js
	String jsToExecuteString="";
	
	//截取月份，用来选择切换月份标签
	String monthTabToSelect = bookDate.substring(0, 7);
	jsToExecuteString = "$('#J_calendar-tabs').children().children().children(\"[data-key='"+monthTabToSelect+"']\").trigger('click')";
	this.getAndroidDriver().executeScript(jsToExecuteString);
	jsToExecuteString ="$(\"[data-key='"+ bookDate +"']\").trigger('tap')";
	this.getAndroidDriver().executeScript(jsToExecuteString);
	return true;

    }

    @Override
    public boolean selectAdult(int adultNum) {
	/** select adult **/
	// default adult number is 2
	if (adultNum < 2) {
		//logger.debug("less adult");
		for (int i = 0; i < Math.abs(adultNum - 2); i++) {
			//driver.findElementByXPath("//div[@id=\"adult\"]/div[1]").click();// less adult
			this.getAndroidDriver().executeScript("$('#J_number-section').children(\"li.adult\").children('.calendar-nc').children('div.number-chooser').children('span.minus').trigger('tap')");
			
		}
	} else {
		//logger.debug("add adult");
		for (int i = 0; i < Math.abs(adultNum - 2); i++) {
			//driver.findElementByXPath("//div[@id=\"adult\"]/div[2]").click(); // add adult
			this.getAndroidDriver().executeScript("$('#J_number-section').children(\"li.adult\").children('.calendar-nc').children('div.number-chooser').children('span.plus').trigger('tap')");
		}
	}
	
	return Boolean.TRUE;
    }

    @Override
    public boolean selectChild(int childNum) {
	if (childNum < 0) {
		for (int i = 0; i < Math.abs(childNum - 0); i++) {
			this.getAndroidDriver().executeScript("$('#J_number-section').children(\"li.child\").children('.calendar-nc').children('div.number-chooser').children('span.minus').trigger('tap')");
		}
	} else {
		for (int i = 0; i < Math.abs(childNum - 0); i++) {
			this.getAndroidDriver().executeScript("$('#J_number-section').children(\"li.child\").children('.calendar-nc').children('div.number-chooser').children('span.plus').trigger('tap')");
		}
	}
	
	return Boolean.TRUE;
    }

    @Override
    public boolean selectFreeChild(int freeChildNum) {
	if (freeChildNum < 0) {
		//logger.debug("less child");
		for (int i = 0; i < Math.abs(freeChildNum - 0); i++) {
			//driver.findElementByXPath("//div[@id=\"freeChild\"]/div[1]").click();// less child
			this.getAndroidDriver().executeScript("$('#J_number-section').children(\"li.free-child\").children('.calendar-nc').children('div.number-chooser').children('span.minus').trigger('tap')");
		}
	} else {
		//logger.debug("add child");
		for (int i = 0; i < Math.abs(freeChildNum - 0); i++) {
			//driver.findElementByXPath("//div[@id=\"freeChild\"]/div[2]").click(); // add child
			this.getAndroidDriver().executeScript("$('#J_number-section').children(\"li.free-child\").children('.calendar-nc').children('div.number-chooser').children('span.plus').trigger('tap')");
		}
	}
	
	return Boolean.TRUE;
    }

    /**
     * 点击价格日历页下一步按钮
     * @return 点击完毕返回true
     */
    public boolean clickNextStepBtn(){
	this.getAndroidDriver().executeScript("$('#J_next-step').trigger('tap')");
	return true;
    }
 
    /**
     * 将zepto引入当前页面
     */
    public void ImportZepto(){
	PageUtil.ImportZepto(this.getAndroidDriver());
    }

    @Override
    public boolean waitLeaveCurrentPage(int timeToWaitInSec) {
	
	return ElementUtil.waitUrlNotContainsSpecificTextOnExpectTime(this.getAndroidDriver(), "calendar", timeToWaitInSec);
    }

}
