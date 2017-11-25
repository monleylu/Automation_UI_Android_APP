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
import com.monleylu.olbb.inter.product.SelectedFlightModule;
import com.monleylu.olbb.inter.product.AdditionalItemModule;

public class PageStepOne extends BasePage implements CommonActionModule,SelectedFlightModule,AdditionalItemModule {

    public PageStepOne(AndroidDriver<AndroidElement> androidDriver) {
	super(androidDriver);
	// TODO Auto-generated constructor stub
    }

    @Override
    public boolean waitPageLoadReady(int timeToWaitInSec) {
	
	PageUtil.waitPageDomLoadReady(this.getAndroidDriver());
	//牛头浮层
	ElementUtil.waitAttributeToBeOnExpectTime(getAndroidDriver(), By.id("loading"), "style", "display: none;", timeToWaitInSec);
	return ElementUtil.waitTextPresentInElementOnExpectTime(getAndroidDriver(), By.id("nextStep"), "填写订单", timeToWaitInSec);
    }

    @Override
    public boolean isExistFlightExceptionDialog() {
	// TODO Auto-generated method stub
	return false;
    }
    
    @Override
    public boolean isExistFlightPriceChangeDialog(){
    	return ElementUtil.isExist(this.getAndroidDriver(),By.xpath("//div[@m='点击_价格变动弹窗____确认']"));
    }
    
    @Override
    public void clickPriceChangeBtn(){
    	this.clickElement(this.find(By.xpath("//div[@class='dialog-cancel']")));
    }
    
    /**
     * 点击1/2下一步按钮
     * @return 点击完毕返回true
     */
    public boolean clickNextStepBtn(){
	return this.clickElement(find(By.id("nextStep")));
    }

    @Override
    public boolean waitLeaveCurrentPage(int timeToWaitInSec) {
	// TODO Auto-generated method stub
	return false;
    }

    @Override
    public boolean waitShowFlightModule(int timeToWaitInSec) {
	// TODO Auto-generated method stub
	return false;
    }

    @Override
    public boolean waitShowFlightOfSelectedFlightModule(int timeToWaitInSec) {
	// TODO Auto-generated method stub
	return false;
    }

    @Override
    public String getFlightDialogMessage() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public void clickChangeFlightsBtn() {
	// TODO Auto-generated method stub
	
    }
    
	@Override
	public boolean waitExistAdditionalItemModule(int timeToWaitInSec) {
	    
	    //判断存在多少个附加项目,大于零说明存在附加项目
	    return  (getAndroidDriver().findElements(By.xpath("//div[@id='addItemBox']")).size()>0)?true:false;
	}
    
	@Override
	public int getCountOfAdditionalItemModule() {
	    //计算需要处理附加项的个数
	    return getAndroidDriver().findElements(By.xpath("//div[@id='addItemBox']//div[@class='add_item']")).size();
	}
	
	@Override
	public String getAdditionalItemTitleName(int areaIndex) {
		//获取附加项名称
	    String jsString = "return $(\"#addItemBox\").children(\".content\").children(\"[data-sort-index='" + areaIndex +"']\").find(\"span\").text()";
	    return getAndroidDriver().executeScript(jsString).toString();
	}
	
	@Override
	public boolean selectUseTimeOfAdditionalItem(int areaIndex, int dataIndex) {
	    //有更简单的选择器，但是为了好理解些，我写了个长些的
	    String jsString = "$(\"#addItemBox\").children(\".content\").children(\"[data-sort-index='" + areaIndex +"']\").children(\"[data-index='" + dataIndex + "']\").children(\".cont-c\").children(\".date-box\").trigger('tap')";
	    //点击选择日期元素
	    getAndroidDriver().executeScript(jsString);
	    
	    //判断是否弹出弹窗
	    if (ElementUtil.waitVisibilityOnExpectTime(getAndroidDriver(), By.xpath("//div[@class='dc-choose-block']"), 1)) {
		//选择默认第一个日期，可通过修改eq()选择指定的日期
		jsString="$('.dc-choose-block').children('ul').children('li').eq(0).trigger('tap')";
		getAndroidDriver().executeScript(jsString);
		return true;
	    }else {
		return false;
	    }
	    
	    
	}
}
