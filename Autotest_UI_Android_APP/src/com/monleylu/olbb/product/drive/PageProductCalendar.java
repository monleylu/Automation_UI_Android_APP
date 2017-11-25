/**
*Copyright (c) 2006-2016 . All Rights Reserved.
*/
package com.monleylu.olbb.product.drive;

import static com.monleylu.olbb.staticvariables.StaticVars.logger;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;

import com.monleylu.olbb.common.ElementUtil;
import com.monleylu.olbb.internal.tours.BasePage;
import com.monleylu.olbb.internal.tours.CommonActionModule;

public class PageProductCalendar extends BasePage implements CommonActionModule{

    public PageProductCalendar(AndroidDriver<AndroidElement> driver) {
	super(driver);
    }

    @Override
    public boolean waitPageLoadReady(int timeToWaitInSec) {
	    if (ElementUtil.isInVisibility(getDriver(), By.id("com.monleylu.app.ui:id/tv_loading"), timeToWaitInSec)) {
		//日历框架
		boolean tourDateScrollView = ElementUtil.isVisibility(getDriver(), By.id("com.monleylu.app.ui:id/calendarView"), timeToWaitInSec);
		//下一步按钮
		boolean nextPageBtnElement = ElementUtil.isVisibility(getDriver(), By.id("com.monleylu.app.ui:id/tv_bottom_right"), timeToWaitInSec);
		
		return tourDateScrollView&&nextPageBtnElement;
	    }else {
		return false;
	    }
    }
    
    /**
     * 选择出游日期（由于价格日历实现机制，暂时无法选择准确出游日期，目前轮询当前页面，直到有选中的资源）
     * @param tourDate 出游日期
     */
    public void selectTourDate(String tourDate) {
/*	boolean booleanContinue = true;
	List<AndroidElement> listElements;
	List<AndroidElement> listViewElements;
	String strXpath = null;
	//判断价格日历有多少行，默认第一行是星期行，即列名称
	listElements= this.getDriver().findElementsByXPath("//android.widget.LinearLayout[@resource-id=\"com.monleylu.app.ui:id/layout_content\"]/android.widget.LinearLayout");
	//List<AndroidElement> listElements= driver.findElementsByXPath("//android.widget.LinearLayout[@id="layout_content"]")
	logger.debug("ListElements is ；" + listElements.size());
	for(int i=2;i<=listElements.size();i++){
		//查询每行有多少view，然后依次点击每个view，直到点击到一个有效日期
		strXpath ="//android.widget.LinearLayout[@resource-id=\"com.monleylu.app.ui:id/layout_content\"]/android.widget.LinearLayout[" + i + "]/android.view.View";
		listViewElements =this.getDriver().findElementsByXPath(strXpath);
		logger.debug("listViewElements " + listViewElements.size());
		for (int j = 0; j < listViewElements.size(); j++) {
			listViewElements.get(j).click();
			if(!this.getDriver().findElementById("com.monleylu.app.ui:id/tv_bottom_right").getAttribute("enabled").equalsIgnoreCase("false")){
				logger.debug("binggo...");
				booleanContinue= false;
				break;
			}
			logger.debug("the " + j + " view is not avaiable calendar");
			
		}
		
		if (!booleanContinue) {
			break;
		}
		
	}*/
	
	
	     List<AndroidElement> avaiableDateElement= getDriver().findElementsById("com.monleylu.app.ui:id/min_price_tv");
	     int randomDate = new Random().nextInt(avaiableDateElement.size());
	     avaiableDateElement.get(randomDate).click();
    }
    
    
    /**
     * 点击价格日历页确定按钮
     * @return 执行完毕返回true
     */
    public  boolean  clickNextStepBtn() {
	return this.clickElement(By.id("com.monleylu.app.ui:id/tv_bottom_right"));
    }



    @Override
    public boolean waitLeaveCurrentPage(int timeToWaitInSec) {
	// TODO Auto-generated method stub
	return false;
    }

}
