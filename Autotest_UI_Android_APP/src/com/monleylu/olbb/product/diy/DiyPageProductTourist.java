/**
*Copyright (c) 2006-2016 . All Rights Reserved.
*/
package com.monleylu.olbb.product.diy;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;

import com.monleylu.olbb.common.ElementUtil;
import com.monleylu.olbb.common.ReglarExp;
import com.monleylu.olbb.internal.tours.BasePage;
import com.monleylu.olbb.internal.tours.CommonActionModule;
import com.monleylu.olbb.internal.tours.TouristInfo;

public class DiyPageProductTourist extends BasePage implements CommonActionModule{

	int touristCountPerPage=0; //获取每页能展示多少个出游人
	int selectedTouristNum =0;//已选择出游人数
	// 获取第一个出游人中心位置
	Point firstTouristPoint=null ;
	// 获取最后一个出游人中心位置
	Point lastTouristPoint=null;
	// 存储已经选择的出游人
	List<TouristInfo> selectedTouristInfos = new ArrayList<TouristInfo>();
	
    public DiyPageProductTourist(AndroidDriver<AndroidElement> driver) {
	super(driver);
	 
    }


    @Override
    public boolean waitPageLoadReady(int timeToWaitInSec) {
	 
	return ElementUtil.isInVisibility(this.getDriver(), By.id("com.monleylu.app.ui:id/tv_loading"), timeToWaitInSec);
    }
    
    
	/**
	 * select tourist
	 */
	public boolean selectTourist(int touristNumToSelect) {
		//boolean booleanContinue=true;
		boolean alertTitleVisable =false ;//判断是否弹出了修改出游人弹窗
		int toSelectTouristNum = touristNumToSelect; //总共需要选择的出游人数
		int offsest=0;
		AndroidElement currentTouristTmp;

		// 获取默认展示的出游人数目
		List<AndroidElement> listAndroidElements = getDriver()
				.findElementsByXPath("//android.widget.ListView[@resource-id=\"com.monleylu.app.ui:id/ll_tourists\"]/android.widget.LinearLayout");
		touristCountPerPage = listAndroidElements.size();
		// 获取第一个出游人中心位置
		firstTouristPoint = listAndroidElements.get(0).getCenter();
		// 获取最后一个出游人中心位置
		lastTouristPoint = listAndroidElements.get(listAndroidElements.size() - 1).getCenter();

		// 已选择出游人数
		String text = getDriver().findElementById(
				"com.monleylu.app.ui:id/tv_choose_num").getAttribute("text");
		selectedTouristNum = Integer.parseInt(ReglarExp
				.getSelectTouristNum(text));
		
		while (selectedTouristNum < toSelectTouristNum) {

		    	//get current tourist
		    	currentTouristTmp=listAndroidElements.get(offsest);
		    	//get tourist information
			TouristInfo touristInfo = getTouristInfo(currentTouristTmp);
			
			//if not exist in already select tourist array then select
			if (!isRepeatTourist(touristInfo)) {
			    currentTouristTmp.findElementById("com.monleylu.app.ui:id/tv_checkBox").click();
			    // 判断是否弹出了修改出游人弹窗
			    alertTitleVisable=ElementUtil.isVisibility(getDriver(), By.id("android:id/alertTitle"), 1);
				
			    // 判断是否存在需要修改出游人的弹窗
			    if (alertTitleVisable) {
				getDriver().findElementById("android:id/button2").click();
			    }else {
				selectedTouristInfos.add(touristInfo);
			    	}	
			}
			
				
			offsest = offsest + 1;

			// 判断是否需要滑动了
			if ((offsest % (touristCountPerPage - 1)) == 0) {
			    /*getDriver().swipe(lastTouristPoint.getX(), lastTouristPoint.getY(),
						firstTouristPoint.getX(), firstTouristPoint.getY(),
						5000);*/
			    swipeNextPageTourist();
			    offsest = 0;// 计数归零
			}

			/*text = getDriver().findElementById("com.monleylu.app.ui:id/tv_choose_num")
					.getAttribute("text");
			selectedTouristNum = Integer.parseInt(ReglarExp
					.getSelectTouristNum(text));*/
			selectedTouristNum=getSelectedTouristNum();

		}
		
		return true;

	}

	/**
	 * select tourist
	 */
	public boolean selectChildTourist(int touristNumToSelect) {
		//boolean booleanContinue=true;
		boolean alertTitleVisable =false ;//判断是否弹出了修改出游人弹窗
		int toSelectTouristNum = touristNumToSelect; //总共需要选择的出游人数
		int offsest=0;
		AndroidElement currentTouristTmp;

		// 获取默认展示的出游人数目
		List<AndroidElement> listAndroidElements = getDriver()
				.findElementsByXPath("//android.widget.ListView[@resource-id=\"com.monleylu.app.ui:id/ll_tourists\"]/android.widget.LinearLayout");
		touristCountPerPage = listAndroidElements.size();
		// 获取第一个出游人中心位置
		firstTouristPoint = listAndroidElements.get(0).getCenter();
		// 获取最后一个出游人中心位置
		lastTouristPoint = listAndroidElements.get(listAndroidElements.size() - 1).getCenter();

		// 已选择出游人数
		String text = getDriver().findElementById(
				"com.monleylu.app.ui:id/tv_choose_num").getAttribute("text");
		selectedTouristNum = Integer.parseInt(ReglarExp
				.getSelectTouristNum(text));
		
		while (selectedTouristNum < toSelectTouristNum) {

		    	//get current tourist
		    	currentTouristTmp=listAndroidElements.get(offsest);
		    	//get tourist information
			TouristInfo touristInfo = getTouristInfo(currentTouristTmp);
			
			//if not exist in already select tourist array then select
			if (!isRepeatTourist(touristInfo) && touristInfo.getTouristname().contains("儿童")) {
			    currentTouristTmp.findElementById("com.monleylu.app.ui:id/tv_checkBox").click();
			    // 判断是否弹出了修改出游人弹窗
			    alertTitleVisable=ElementUtil.isVisibility(getDriver(), By.id("android:id/alertTitle"), 1);
				
			    // 判断是否存在需要修改出游人的弹窗
			    if (alertTitleVisable) {
				getDriver().findElementById("android:id/button2").click();
			    }else {
				selectedTouristInfos.add(touristInfo);
			    	}	
			}
			
				
			offsest = offsest + 1;

			// 判断是否需要滑动了
			if ((offsest % (touristCountPerPage - 1)) == 0) {
			    /*getDriver().swipe(lastTouristPoint.getX(), lastTouristPoint.getY(),
						firstTouristPoint.getX(), firstTouristPoint.getY(),
						5000);*/
			    swipeNextPageTourist();
			    offsest = 0;// 计数归零
			}

			/*text = getDriver().findElementById("com.monleylu.app.ui:id/tv_choose_num")
					.getAttribute("text");
			selectedTouristNum = Integer.parseInt(ReglarExp
					.getSelectTouristNum(text));*/
			selectedTouristNum=getSelectedTouristNum();

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
	/**
	 * swipe down page to show next page tourist 
	 */
	public void swipeNextPageTourist(){
	    //if not the first time scroll page ,no need to get scroll information 
	    if (0==touristCountPerPage) {
		// 获取默认展示的出游人数目
		List<AndroidElement> listAndroidElements = getDriver().findElementsByXPath("//android.widget.ListView[@resource-id=\"com.monleylu.app.ui:id/ll_tourists\"]/android.widget.LinearLayout");
		touristCountPerPage = listAndroidElements.size();
		// 获取第一个出游人中心位置
		firstTouristPoint = listAndroidElements.get(0).getCenter();
		// 获取最后一个出游人中心位置
		lastTouristPoint = listAndroidElements.get(listAndroidElements.size() - 1).getCenter();		
	    }
	    getDriver().swipe(lastTouristPoint.getX(), lastTouristPoint.getY(),firstTouristPoint.getX(), firstTouristPoint.getY(),5000);
	    
	}
	
	/**
	 * get already selected tourist number
	 * @return return selected tourist number
	 */
	public int getSelectedTouristNum(){
	    String text = getDriver().findElementById("com.monleylu.app.ui:id/tv_choose_num")
			.getAttribute("text");
	    return Integer.parseInt(ReglarExp
			.getSelectTouristNum(text));
	}
	
	/**
	 * get tourist information 
	 * @param touristName of the tourist to get 
	 * @return
	 */
	public TouristInfo getTouristInfo(String touristName){
	    TouristInfo touristInfoTmp = null;
	    List<AndroidElement> list=getCurrentPageTouris();
	    for(int i=0;i<list.size();i++){
		 touristInfoTmp=getTouristInfo(list.get(i));
		if (touristInfoTmp.getTouristname().equals(touristName)) {
		    touristInfoTmp.setIndex(i+1);
		    return touristInfoTmp;
		}
	    }
	    //if not found ,continus 
	    swipeNextPageTourist();
	    getTouristInfo(touristName);
	    return touristInfoTmp;
	}
	
	/**
	 * get current page tourist information
	 * @return current page tourist information
	 */
	public List<AndroidElement> getCurrentPageTouris() {
	    return getDriver().findElementsByXPath("//android.widget.ListView[@resource-id=\"com.monleylu.app.ui:id/ll_tourists\"]/android.widget.LinearLayout");
	}
	
	/**
	 * get tourist information from tourist element 
	 * @param androidElement
	 * @return
	 */
	public TouristInfo getTouristInfo(AndroidElement touristElement){
	    TouristInfo touristInfo = new TouristInfo();
	    if (ElementUtil.isExist(getDriver(), touristElement, By.id("com.monleylu.app.ui:id/tv_user_name"), 1)) {
		touristInfo.setTouristname(touristElement.findElementById("com.monleylu.app.ui:id/tv_user_name").getAttribute("text"));
	    }
	    if (ElementUtil.isExist(getDriver(), touristElement, By.id("com.monleylu.app.ui:id/tv_user_pspt"), 1)) {
		touristInfo.setTouristPaperwork(touristElement.findElementById("com.monleylu.app.ui:id/tv_user_pspt").getAttribute("text"));
	    }
	    return touristInfo;
	}
	
	/**
	 * if tourist is exist
	 * @param touristInfo
	 * @return return true if exist,else return false
	 */
	public boolean isRepeatTourist(TouristInfo touristInfo){
	    for (TouristInfo touristInfoTmp : selectedTouristInfos) {
		if (touristInfo.getTouristname().equals(touristInfoTmp.getTouristname())) {
		    return true;
		}
	    }
	    return false;
	}
	
   
}
