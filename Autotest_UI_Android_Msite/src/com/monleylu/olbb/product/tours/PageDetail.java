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

public class PageDetail extends BasePage implements CommonActionModule{

    public PageDetail(AndroidDriver<AndroidElement> androidDriver) {
	super(androidDriver);
    }

    @Override
    public boolean waitPageLoadReady(int timeToWaitInSec) {
	//首先判断Dom是否加载完毕
	PageUtil.waitPageDomLoadReady(this.getAndroidDriver());
	return waitPageReadyByCheckLoadingIcon(timeToWaitInSec);
	
    }
    

    /**
     * 通过判断牛头状态确定加载状态
     * @param androidDriver
     * @param timeToWaitInSec 等待时间
     * @return 加载完毕返回true，否则返回false
     */
    private boolean waitPageReadyByCheckLoadingIcon(int timeToWaitInSec){
	
	return ElementUtil.waitInVisibilityOnExpectTime(this.getAndroidDriver(), By.xpath("//div[@class='full-loading']"), timeToWaitInSec);
	
    }
    

    	/**
    	 * 访问产品详情页面
    	 * @param driver  进程实例
    	 * @param urlToVisit  待访问产品url
    	 * @return   成功打开返回true
    	 */
    	public  boolean visitProduct (String urlToVisit) {
    		this.getAndroidDriver().get(urlToVisit);
    		return Boolean.TRUE;	
    	}
    	
    	
	/**
	 * 点击产品详情页下一步按钮，进入价格日历页
	 * @return 成功返回true
	 */
	public  boolean clickNextStepBtn() {
		//调用页面等待，此方法还是有缺陷，异步加载时还需要进一步处理
		//PageProductDetail.waitPageReady();
		//PageProductDetail.waitPageReadyByCheckLoadingIcon();
		return this.clickElement(this.find(By.id("orderButton")));
	}
	


	@Override
	public boolean waitLeaveCurrentPage(int timeToWaitInSec) {
	    // TODO Auto-generated method stub
	    return false;
	}
}
