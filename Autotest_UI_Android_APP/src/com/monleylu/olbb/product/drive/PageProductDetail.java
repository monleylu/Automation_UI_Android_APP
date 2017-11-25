/**
*Copyright (c) 2006-2016 . All Rights Reserved.
*/
package com.monleylu.olbb.product.drive;



import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidKeyCode;

import java.util.List;
import java.util.Random;

import org.apache.regexp.recompile;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.interactions.touch.TouchActions;

import com.monleylu.olbb.common.AppUtil;
import com.monleylu.olbb.common.ElementUtil;
import com.monleylu.olbb.internal.tours.BasePage;
import com.monleylu.olbb.internal.tours.CommonActionModule;
import com.monleylu.olbb.internal.tours.GenericTicketModule;
import com.monleylu.olbb.internal.tours.ProductPageCode;

public  class PageProductDetail extends BasePage implements  GenericTicketModule,CommonActionModule {

    private List<AndroidElement> listTickesAndroidElements =null;
    private List<AndroidElement> listTicketUseDateAndroidElements =null;
    
    public PageProductDetail(AndroidDriver<AndroidElement> driver) {
	super(driver);
    }

	public boolean visitProduct(String productID) {
		
	    	//获取原有ime
	    	//String preIME= getDriver().manage().ime().getActiveEngine();
	    	//失活ime
	    	//getDriver().manage().ime().deactivate();
		this.clickElement(By.id("header_search_homepage"));
		getDriver().findElementById("com.monleylu.app.ui:id/ev_search").sendKeys(productID);
		//搜狗等输入法时无法搜索,需要把输入法切换成谷歌之类的输入法
		getDriver().pressKeyCode(AndroidKeyCode.ENTER);
		//this.clickElement(By.id("iv_find"));
		//激活ime
		//增加空判断否则IME为空时会抛异常
		/*if(preIME.length()!=0){
		    getDriver().manage().ime().activateEngine(preIME);
		}*/
		
		return true;
		
	}

    @Override
    public boolean clickUseDateBtn(int index) {
	String xpathToLacateElementString="//android.widget.LinearLayout[@resource-id='com.monleylu.app.ui:id/lv_addition']/android.widget.LinearLayout["+ index +"]";
	this.getDriver().findElement(By.xpath(xpathToLacateElementString)).findElement(By.id("com.monleylu.app.ui:id/rl_use_date")).click();
	//this.getDriver().findElement(By.xpath(xpathToLacateElementString)).findElement(By.id("com.monleylu.app.ui:id/rl_use_date")).click();
	return true;
    }

    @Override
    public String selectTicketUseDate(int index) {
	String dateToSelect="";
	
	if (listTicketUseDateAndroidElements==null) {
	    listTicketUseDateAndroidElements =  this.getDriver().findElements(By.id("com.monleylu.app.ui:id/tv_date"));
	}
	
	index = index == 0 ? ((new Random()).nextInt(listTicketUseDateAndroidElements.size()))+1: index;
	 
	dateToSelect = listTicketUseDateAndroidElements.get(index-1).getAttribute("text");
	listTicketUseDateAndroidElements.get(index -1 ).click();
	return dateToSelect;
	
    }

    @Override
    public int countOfTicketUseDate() {
	
	listTicketUseDateAndroidElements =  this.getDriver().findElements(By.id("com.monleylu.app.ui:id/tv_date"));
	return listTicketUseDateAndroidElements.size();
	
    }

    @Override
    public boolean waitPageLoadReady(int timeToWaitInSec) {
	boolean listElement = ElementUtil.isVisibility(this.getDriver(), By.id("com.monleylu.app.ui:id/v_product_detail"), timeToWaitInSec);
	boolean nextPageElement = ElementUtil.isVisibility(this.getDriver(), By.id("com.monleylu.app.ui:id/tv_book"), timeToWaitInSec);
	return listElement&&nextPageElement;

    }

    @Override
    public int countOfDefaultShowTicket() {
	
	//handle no ticket status
	try {
	    listTickesAndroidElements = this.getDriver().findElements(By.xpath("//android.widget.LinearLayout[@resource-id='com.monleylu.app.ui:id/lv_addition']/android.widget.LinearLayout"));
	    return listTickesAndroidElements.size();
	} catch (Exception e) {
	    return 0;
	}
	
	
    }

    @Override
    public boolean isExistTicketModule(int timeToWaitInSec) {
	return ElementUtil.isVisibility(this.getDriver(), By.id("com.monleylu.app.ui:id/rl_title"), timeToWaitInSec);
    }
    
    /**
     * 移动鼠标到指定元素中间位置，滚动屏幕到指定的元素
     * @param by 元素定位标示
     * @return 执行完毕返回true
     */
    public boolean  MoveToElement(By by) {
	//TouchAction touchAction = new TouchAction(this.getDriver());
	//touchAction.moveTo(this.getDriver().findElement(by)).perform();
	
	
	//Actions actions = new Actions(this.getDriver());
	//actions.moveToElement(this.getDriver().findElement(by));
	
	//TouchActions toursActions = new TouchActions(this.getDriver());
	//toursActions.moveToElement(this.getDriver().findElement(by)).perform();
	//点击立即预订，页面会自动滚动到没有填写使用日期的门票模块
	this.clickElement(By.id("com.monleylu.app.ui:id/tv_book"));
	return true;
	
    }
    
    /**
     * 点击产品详情页页面立即预订按钮
     * @return
     */
    public boolean clickNextStepBtn() {
	this.clickElement(By.id("com.monleylu.app.ui:id/tv_book"));
	return true;

    }
    
    
    /**
     * 点击选择价格日历按钮
     * @return 执行完毕返回true
     */ 
    public boolean clickSelectCalendarBtn() {
	return this.clickElement(By.id("com.monleylu.app.ui:id/tv_go_calendar"));
    }

    /**
     * 判断价格日历按钮是否可见
     * @return 可见返回true，否则返回false
     */
    public boolean isVisiableOfSelectCalendarBtn(){
	return ElementUtil.isExist(getDriver(), By.id("com.monleylu.app.ui:id/tv_go_calendar"));
    }
    
    public boolean moveScreenToSelectCalendarBtn(){
	 if (AppUtil.moveScreenToElement(getDriver(), By.id("com.monleylu.app.ui:id/tv_go_calendar"), 10)) {
	    return AppUtil.moveScreenToElement(getDriver(), By.id("com.monleylu.app.ui:id/tv_go_calendar"), 1);
	    }else {
		return false;
	    }
    }
    
    @Override
    public boolean waitLeaveCurrentPage(int timeToWaitInSec) {
	// TODO Auto-generated method stub
	return false;
    }
    
    


}
