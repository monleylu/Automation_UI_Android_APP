package com.monleylu.olbb.product.tours;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import org.openqa.selenium.By;

import com.monleylu.olbb.common.ElementUtil;
import com.monleylu.olbb.internal.tours.ProductPageCode;

public class ToursProduct {
	private AndroidDriver<AndroidElement> driver;
	private PageProductDetail pageProductDetail;
	private PageProductCalendar pageProductCalendar;
	private PageProductFastOrder pageProductFastOrder;
	private PageProductRepeatOrder pageProductRepeatOrder;
	private PageProductStepOne pageProductStepOne;
	private PageProductStepTwo pageProductStepTwo;
	private PageProductTourist pageProductTourist;
	
	public ToursProduct(AndroidDriver<AndroidElement> driver) {
		this.setDriver(driver);
		this.setPageProductDetail(new PageProductDetail(driver));
		this.setPageProductCalendar(new PageProductCalendar(driver));
		this.setPageProductFastOrder(new PageProductFastOrder(driver));
		this.setPageProductRepeatOrder(new PageProductRepeatOrder(driver));
		this.setPageProductStepOne(new PageProductStepOne(driver));
		this.setPageProductStepTwo(new PageProductStepTwo(driver));
		this.setPageProductTourist(new PageProductTourist(driver));

	}
	
	/**
	 * 返回当前是哪个页面
	 * @return
	 */
	public int getCurrentPage() {
	    
	    String titleNameString ="";
	    
	    if (ElementUtil.isExist(getDriver(),By.id("com.monleylu.app.ui:id/tv_header_title"))) {
		titleNameString = driver.findElement(By.id("com.monleylu.app.ui:id/tv_header_title")).getAttribute("text");
		if (titleNameString.equals("2/2填写订单")) {
		    
		    return ProductPageCode.PageProductStepTwo;
		    
		}else if (titleNameString.equals("选择常用游客")) {
		    
		    return ProductPageCode.PageProductTourist;
		    
		}else if (titleNameString.equals("1/2选择资源")) {
		    
		    return ProductPageCode.PageProductStepOne;
		    
		}else if (titleNameString.equals("预订成功")) {
		    
		    return ProductPageCode.PageProductBookSuccess;
		    
		}/*else{
		    
		    return ProductPageCode.PageProductUnDefine;
		}*/
		
	    }else if (ElementUtil.isExist(getDriver(),By.id("com.monleylu.app.ui:id/v_header_text"))) {
		titleNameString = driver.findElement(By.id("com.monleylu.app.ui:id/v_header_text")).getAttribute("text");
		
		if (titleNameString.equals("预订提示")) {
		    
		    return ProductPageCode.PageProductRepeatOrder;
		    
		}else {
		    
		    return ProductPageCode.PageProductUnDefine;
		    
		}
	    }else if (ElementUtil.isExist(getDriver(),By.id("com.monleylu.app.ui:id/sdk_tv_header_title"))) {
		titleNameString = driver.findElement(By.id("com.monleylu.app.ui:id/sdk_tv_header_title")).getAttribute("text");
		
		if (titleNameString.equals("收银台")) {
		    return ProductPageCode.PageProductCasher;
		}else {
		    return ProductPageCode.PageProductUnDefine;
		}
		
	    }else if (ElementUtil.isTextPresent(getDriver(), By.id("com.monleylu.app.ui:id/bt_call"), "联系客服修改", 1)) {
		//由于组件化导致丢失了识别页面的头部title关键字标识，重新根据别的内容识别是否重单页面
		return ProductPageCode.PageProductRepeatOrder; 
	    }
	    
	    //假如执行到了这一步基本上是出错了
	   return ProductPageCode.PageProductUnDefine;
	    
	     
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

	public PageProductRepeatOrder getPageProductRepeatOrder() {
	    return pageProductRepeatOrder;
	}

	public void setPageProductRepeatOrder(
		PageProductRepeatOrder pageProductRepeatOrder) {
	    this.pageProductRepeatOrder = pageProductRepeatOrder;
	}

	public PageProductFastOrder getPageProductFastOrder() {
	    return pageProductFastOrder;
	}

	public void setPageProductFastOrder(PageProductFastOrder pageProductFastOrder) {
	    this.pageProductFastOrder = pageProductFastOrder;
	}



}
