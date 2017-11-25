/**
*Copyright (c) 2006-2016 . All Rights Reserved.
*/
package com.monleylu.olbb.product.drive;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;

import com.monleylu.olbb.common.ElementUtil;
import com.monleylu.olbb.common.ReglarExp;
import com.monleylu.olbb.internal.tours.BasePage;
import com.monleylu.olbb.internal.tours.CommonActionModule;

public class PageProductTourist extends BasePage implements CommonActionModule{

    public PageProductTourist(AndroidDriver<AndroidElement> driver) {
	 super(driver);
    }

    @Override
    public boolean waitPageLoadReady(int timeToWaitInSec) {
	 
	return ElementUtil.isInVisibility(this.getDriver(), By.id("com.monleylu.app.ui:id/tv_loading"), timeToWaitInSec);
    }
    
    
    /**
     * 顺序选择指定数目的出游人
     * @param touristNumToSelect  要选择的出游人数目
     * @return 选择成功返回true，否则返回false
     */
    public boolean selectTourist(int touristNumToSelect) {
	boolean booleanContinue=true;
	int touristCountPerPage=0; //获取每页能展示多少个出游人
	int selectedTouristNum =0;//已选择出游人数
	boolean alertTitleVisable =false ;//判断是否弹出了修改出游人弹窗
	int toSelectTouristNum = touristNumToSelect; //总共需要选择的出游人数

	int offsest=0;

	// 获取默认展示的出游人数目
	List<AndroidElement> listAndroidElements = this.getDriver()
			.findElementsByXPath("//android.widget.ListView[@resource-id=\"com.monleylu.app.ui:id/ll_tourists\"]/android.widget.LinearLayout");
	touristCountPerPage = listAndroidElements.size();
	// 获取第一个出游人中心位置
	Point firstTouristPoint = listAndroidElements.get(0).getCenter();
	// 获取最后一个出游人中心位置
	Point lastTouristPoint = listAndroidElements.get(
			listAndroidElements.size() - 1).getCenter();

	// 已选择出游人数
	String text = this.getDriver().findElementById(
			"com.monleylu.app.ui:id/tv_choose_num").getAttribute("text");
	selectedTouristNum = Integer.parseInt(ReglarExp
			.getSelectTouristNum(text));

	while (selectedTouristNum < toSelectTouristNum) {

		listAndroidElements.get(offsest)
				.findElementById("com.monleylu.app.ui:id/tv_checkBox").click();
		offsest = offsest + 1;

		// 判断是否弹出了修改出游人弹窗
		alertTitleVisable=ElementUtil.isVisibility(this.getDriver(), By.id("android:id/alertTitle"), 5);
		
		// 判断是否存在需要修改出游人的弹窗
		if (alertTitleVisable) {
		    this.getDriver().findElementById("android:id/button2").click();
		}

		// 判断是否需要滑动了
		if ((offsest % (touristCountPerPage - 1)) == 0) {
		    this.getDriver().swipe(lastTouristPoint.getX(), lastTouristPoint.getY(),
					firstTouristPoint.getX(), firstTouristPoint.getY(),
					5000);
			offsest = 0;// 计数归零
		}

		text = this.getDriver().findElementById("com.monleylu.app.ui:id/tv_choose_num")
				.getAttribute("text");
		selectedTouristNum = Integer.parseInt(ReglarExp
				.getSelectTouristNum(text));

	}
	
	return true;

}	
    
    	/**
    	 * 点击选择出游人页面确定按钮
    	 * @return 点击完毕返回true
    	 */
	public boolean clickNextStepBtn() {
	    return this.clickElement(By.id("com.monleylu.app.ui:id/tv_right_function"));
	}



	@Override
	public boolean waitLeaveCurrentPage(int timeToWaitInSec) {
	    // TODO Auto-generated method stub
	    return false;
	}
    
}
