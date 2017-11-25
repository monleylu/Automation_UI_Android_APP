/**
*Copyright (c) 2006-2016 . All Rights Reserved.
*/
package com.monleylu.olbb.product.diy;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.By.ById;

import com.monleylu.olbb.common.ElementUtil;
import com.monleylu.olbb.internal.tours.BasePage;
import com.monleylu.olbb.internal.tours.CommonActionModule;
import com.monleylu.olbb.product.diy.model.TouristView;

public class DiyPageProductStepTwo extends BasePage implements CommonActionModule {

    public DiyPageProductStepTwo(AndroidDriver<AndroidElement> driver) {
	super(driver);
    }

    private TouristView touristView = new TouristView(getDriver());
    
    public TouristView getTouristView() {
        return touristView;
    }

    @Override
    public boolean waitPageLoadReady(int timeToWaitInSec) {
	
	//boolean isLoadIcon =ElementUtil.isTextPresent(getDriver(), By.id("com.monleylu.productdetail:id/tv_header_title"), "2/2填写订单",timeToWaitInSec);
	return ElementUtil.isTextPresent(getDriver(), By.xpath("//android.widget.LinearLayout[@resource-id='com.monleylu.app.ui:id/ll_middle']/android.widget.TextView"), "2/2填写订单", timeToWaitInSec);
	
    }
  
    /**
     * 点击选择出游人按钮
     * @return 执行完毕返回true
     */
    public boolean  clickSelectTouristBtn() {
	
	List<AndroidElement> listCommonTourist= this.getDriver().findElements(By.id("com.monleylu.productdetail:id/tv_common_tourist"));
	for (int i = listCommonTourist.size() -1 ; i >0; i--) {
	    if (listCommonTourist.get(i).getAttribute("text").equals("更多出游人")) {
		listCommonTourist.get(i).click();
		return true;
	    }
	}
	
	return true;
    }
    
    /**
     * 滚动到页面底部
     * @return 执行完毕返回true
     */
    public boolean scrollToBottom() {
	Dimension windowDimension = this.getDriver().manage().window().getSize();
	this.getDriver().swipe(windowDimension.getWidth()/2, windowDimension.getHeight()*2/3, windowDimension.getWidth()/2, 0, 70);
	return true;
}

	/**
	 * 点击出游人满足条件复选框
	 * @return
	 */
	public boolean clickTouristSatisfyAllRequirementElement() {
		return this.clickElement(By.id("com.monleylu.productdetail:id/iv_ignore_all"));
	}

	/**
	 * 点击阅读协议复选框
	 * @return
	 */
	public boolean clickReadLawsAndOtherElement() {
		return this.clickElement(By.id("com.monleylu.productdetail:id/iv_satisfy"));
	}
	
	/**
	 * 点击立即付款按钮
	 * @return 按钮可选点击完毕返回true，不可选返回false
	 */
	public boolean clickNextStepBtn() {
		if (!this.getDriver().findElementById("com.monleylu.productdetail:id/tv_submit").getAttribute("enabled").equalsIgnoreCase("false")) {
			this.getDriver().findElementById("com.monleylu.productdetail:id/tv_submit").click();
			return true;
		}else {
			return false;
		}
	}

	///**
	// * 判断是否存在生僻字以及婴幼儿校验框，自驾暂未发现会存在这种类型复选框，直接返回fasle
	// * @param i
	// * @return
	// */
	/*public boolean isVisibleCheckOutDialog(int i) {
	    // TODO Auto-generated method stub
	    return false;
	}*/

	/**
	 * 判断是否存在占位浮层
	 * @param timeToWaitInSec
	 * @return
	 */
	public boolean isVisibleLayOutView(int timeToWaitInSec) {
	    return ElementUtil.isVisibility(this.getDriver(), By.id("com.monleylu.productdetail:id/plane_wait_layout"), timeToWaitInSec);
		    
	}
	
	/**
	 * 获取订单ID
	 * @return
	 */
	public String getOrderID() {
	    
	    return this.getDriver().findElement(By.id("com.monleylu.productdetail:id/tv_auto_test_order_id")).getAttribute("text");
	}

	/**
	 * 判断是否不存在占位浮层
	 * @param timeToWaitInSec
	 * @return
	 */
	public boolean isInVisibleLayOutView(int timeToWaitInSec) {
	    return ElementUtil.isInVisibility(this.getDriver(), By.id("com.monleylu.productdetail:id/plane_wait_layout"), timeToWaitInSec);
	}
	
	/**
	 * 判断是否存在占位超时对话框
	 * @param timeToWaitInSec 等待时间
	 * @return 存在返回true，否则返回fasle
	 */
	public  boolean isVisibleTimeOutDialog(int timeToWaitInSec) {
	    return ElementUtil.isVisibility(this.getDriver(), By.id("com.monleylu.productdetail:id/plane_wait_timeout_layout"), timeToWaitInSec);
	}
	
	/**
	 * 判断是否存在visa复选框
	 * @param timeToWaitInSec 等待时间
	 * @return 存在返回true
	 */
	public  boolean isExistVisaBtn() {
	    return ElementUtil.isExist(getDriver(),By.id("com.monleylu.productdetail:id/iv_unsatisfy"));
	}
	
	/**
	 * 点击visa复选框
	 * @return  执行完毕true
	 */
	public boolean clickVisaBtn() {
	    return this.clickElement(By.id("com.monleylu.productdetail:id/iv_unsatisfy"));
	}



	@Override
	public boolean waitLeaveCurrentPage(int timeToWaitInSec) {
	    // TODO Auto-generated method stub
	    return false;
	}
    

}
