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

public class PageTourist extends BasePage implements CommonActionModule {

    public PageTourist(AndroidDriver<AndroidElement> androidDriver) {
	super(androidDriver);
    }

    @Override
    public boolean waitPageLoadReady(int timeToWaitInSec) {
	PageUtil.waitPageDomLoadReady(getAndroidDriver());
	return ElementUtil.waitVisibilityOnExpectTime(getAndroidDriver(), By.xpath("//ul[@id='list']"), timeToWaitInSec);
    }
    
    /**
     * 选择指定数量的出游人
     * @param sumTouristToSelect 待选择出游人数量
     * @return 执行完毕返回true
     */
    public boolean selectTourist(int sumTouristToSelect){
	
	String strSelectTouristJS="";
	Object totalContacterObject = null;
	int selectedNum = 0;// already selected tourist
	boolean continueCheck = true;
	int offSet = 0;// 选择出游人偏移量
	AndroidDriver<AndroidElement>  driver = this.getAndroidDriver();
	
	totalContacterObject = driver.executeScript("return document.getElementById(\"list\").getElementsByTagName(\"li\").length"); //get total tourist
	Integer.parseInt(totalContacterObject.toString());
	
	while (continueCheck) {
		if (selectedNum < sumTouristToSelect) {
			strSelectTouristJS="return document.getElementById(\"list\").getElementsByTagName(\"li\").item("+ offSet + ").id";
			Object objectID = driver.executeScript(strSelectTouristJS); 
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			strSelectTouristJS = "return document.getElementById(\"" + Integer.parseInt(objectID.toString()) + "\").childNodes[3].click()";
			//String strSelectTouristJS = "return document.getElementById(\"" + "6710697" + "\").childNodes[3].childNodes[1].click()";
			driver.executeScript(strSelectTouristJS);
			offSet=offSet+1;

			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			//logger.debug("判断出游人页面是否存在编辑联系人的浮层");
			// judge if edit box exist ,if yes ,click away
			if (ElementUtil.isExist(driver,
					By.xpath("//div[@class=\"dialog-ok\"]"))) {
				//logger.debug("存在浮层");
				this.cancelEditDialogBox(driver);
				//logger.debug("浮层点掉");

			}//else {
				//当提示框为一个浮层，自动消失时，这个就会统计不准确
				//selectedNum=selectedNum+1;
			//}
			selectedNum = driver.findElements(By.xpath("//span[@class='check-item icon-select']")).size();

		} else {
			continueCheck = false;
		}
	}
	
	return true;
    }

    /**
     * 选择指定的出游人
     * @param touristID 出游人ID
     * @return 选择成功返回true,出现完善出游人弹窗或者其他异常返回false
     */
    public boolean selectSpecialTourist(String touristID){
	String strSelectTouristJS="";
	strSelectTouristJS = "return document.getElementById(" + touristID + ").childNodes[3].click()";
	this.getAndroidDriver().executeScript(strSelectTouristJS);
	// judge if edit box exist ,if yes ,click away
	if (ElementUtil.waitElementPresentOnDOMOnExpectTime(this.getAndroidDriver(), By.xpath("//div[@class='dialog-ok']"), 1)) {
	    //this.cancelEditDialogBox(driver);
	    return false;
	}
	return true;
    }

    @Override
    public boolean clickNextStepBtn(){
	
	this.getAndroidDriver().executeScript("document.getElementById(\"saveChosen\").click()");
	return true;
    }

    @Override
    public boolean waitLeaveCurrentPage(int timeToWaitInSec) {
	// TODO Auto-generated method stub
	return false;
    }
    
    /**
     * 点击取消修改联系人浮层弹窗
     * @param driver 进程实例
     */
	public  void cancelEditDialogBox(AndroidDriver<AndroidElement> driver){
		//dialog render too show ,loop check for display
		while(true){
			if(driver.findElement(By.xpath("//div[@class=\"dialog-ok\"]")).isDisplayed()){
				break;
			}
		}
		/*driver.context("NATIVE_APP");
		Point pointCenter = driver.findElementByXPath("//*[@class='android.webkit.WebView']/android.view.View[5]").getCenter();
		//System.out.println("Position cancelEditDialogBox is :" + pointCenter.getX() +":" +pointCenter.getY());
		driver.tap(1, pointCenter.getX(), pointCenter.getY(), 200);	
		driver.context("WEBVIEW_1");*/
		PageUtil.ImportZepto(driver);
		driver.executeScript("$('.dialog-ok').trigger('click')");
		
	}
}
