/**
*Copyright (c) 2006-2016 . All Rights Reserved.
*/
package com.monleylu.olbb.product.cruise.model;

import java.util.List;
import java.util.Random;

import com.monleylu.olbb.internal.tours.BasePage;
import com.monleylu.olbb.internal.tours.CruiseRoomInformation;
import com.monleylu.olbb.internal.tours.SelectCruiseRoomModel;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class CruiseRoomModel extends BasePage implements SelectCruiseRoomModel{

    public CruiseRoomModel(AndroidDriver<AndroidElement> driver) {
	super(driver);
	// TODO Auto-generated constructor stub
    }
    
    /**
     * 入住指定房间
     * @param cruiseRoomInformation
     * @return
     */
/*    public boolean selectCruiseRoom(CruiseRoomInformation cruiseRoomInformation){
	return false;
    }*/
    
    /**
     * 获取当前页面显示的邮轮房型元素
     * @return 
     */
    public List<AndroidElement> getCurrentShowCruiseRoomElement(){
	return getDriver().findElementsByXPath("//android.widget.LinearLayout[@resource-id='com.monleylu.app.ui:id/vglv_cruise_room']/android.widget.RelativeLayout");
    }
    
    
    /**
     * 获取指定房型的基本信息
     * @param cruiseRoomElement 房型元素
     * @return
     */
    public CruiseRoomInformation getCruiseRoomInformation(AndroidElement cruiseRoomElement){
	CruiseRoomInformation cruiseRoomInformation = new CruiseRoomInformation();
	cruiseRoomInformation.setRoomName(cruiseRoomElement.findElementById("com.monleylu.app.ui:id/tv_room_name").getAttribute("text"));
	
	String canLiveTag = cruiseRoomElement.findElementByXPath("//android.widget.LinearLayout[@resource-id='com.monleylu.app.ui:id/vglv_room_tag']/android.widget.LinearLayout[1]/android.widget.TextView[@resource-id='com.monleylu.app.ui:id/tv_tag']").getAttribute("text");
	if (canLiveTag.contains("-")) {
	    cruiseRoomInformation.setLowerLimit(Integer.parseInt(canLiveTag.substring(3, canLiveTag.indexOf("-"))));
	    cruiseRoomInformation.setUpLimit(Integer.parseInt(canLiveTag.substring(canLiveTag.indexOf("-")+1, canLiveTag.indexOf("人"))));
	}else{
	    cruiseRoomInformation.setLowerLimit(Integer.parseInt(canLiveTag.substring(3,canLiveTag.indexOf("人"))) );
	    cruiseRoomInformation.setUpLimit(cruiseRoomInformation.getLowerLimit());
	}
	
	return cruiseRoomInformation;
    }

    /**
     * 随机选择一个邮轮房型入住
     * @return
     */
    public boolean seletCruiseRoomRandom(int adultNum,int childNum){
        List<AndroidElement> listCruiseRoom = getCurrentShowCruiseRoomElement();
        int randomInt=new Random().nextInt(listCruiseRoom.size());
        CruiseRoomInformation cruiseRoomInformation =getCruiseRoomInformation(listCruiseRoom.get(randomInt));
        System.out.println("随机选择邮轮房型为："+ cruiseRoomInformation.getRoomName());
        return  selectCruiseRoom(listCruiseRoom.get(randomInt),adultNum,childNum);
    }
    
    public boolean selectCruiseRoom(AndroidElement cruiseRoomElement,int adultNum ,int childNum){
        boolean loopBoolean=true;
        //点击选择按钮
        cruiseRoomElement.findElementById("com.monleylu.app.ui:id/tv_select_room").click();
        if (adultNum==0)return  false;
        selectCruiseRoom(adultNum,childNum);
        return true;
    }

    private void selectCruiseRoom(int adultNum,int childNum){
        if (adultNum>0){
            clickAdultPlusBtn();
            adultNum--;
        }

        if (childNum>0){
            clickChildPlusBtn();
            childNum--;
        }

        if (adultNum>0 || childNum >0 ){
            selectCruiseRoom(adultNum,childNum);
        }
    }

    @Override
    public int getAdultNum() {
        return Integer.parseInt(getDriver().findElementById("com.monleylu.app.ui:id/ccv_adult_number").findElementById("com.monleylu.app.ui:id/tv_number_content").getAttribute("text"));
    }

    @Override
    public int getChildNum() {
        return Integer.parseInt(getDriver().findElementById("com.monleylu.app.ui:id/ccv_child_number").findElementById("com.monleylu.app.ui:id/tv_number_content").getAttribute("text"));
    }

    @Override
    public int getRoomNum() {
        return Integer.parseInt(getDriver().findElementById("com.monleylu.app.ui:id/ccv_room_number").findElementById("com.monleylu.app.ui:id/tv_number_content").getAttribute("text"));
    }

    @Override
    public void clickAdultMinusBtn() {
        getDriver().findElementById("com.monleylu.app.ui:id/ccv_adult_number").findElementById("com.monleylu.app.ui:id/iv_number_sub").click();
    }

    @Override
    public void clickAdultPlusBtn() {
        getDriver().findElementById("com.monleylu.app.ui:id/ccv_adult_number").findElementById("com.monleylu.app.ui:id/iv_number_add").click();
    }

    @Override
    public void clickChildMinusBtn() {
        getDriver().findElementById("com.monleylu.app.ui:id/ccv_child_number").findElementById("com.monleylu.app.ui:id/iv_number_sub").click();
    }

    @Override
    public void clickChildPlusBtn() {
        getDriver().findElementById("com.monleylu.app.ui:id/ccv_child_number").findElementById("com.monleylu.app.ui:id/iv_number_add").click();
    }

    @Override
    public void clickRoomMinusBtn() {
        getDriver().findElementById("com.monleylu.app.ui:id/ccv_room_number").findElementById("com.monleylu.app.ui:id/iv_number_sub").click();
    }

    @Override
    public void clickRoomPlusBtn() {
        getDriver().findElementById("com.monleylu.app.ui:id/ccv_room_number").findElementById("com.monleylu.app.ui:id/iv_number_add").click();
    }

    @Override
    public boolean adultMinusIconStatus() {
        String status = getDriver().findElementById("com.monleylu.app.ui:id/ccv_adult_number").findElementById("com.monleylu.app.ui:id/iv_number_sub").getAttribute("enabled");
        return status.equals("true");
    }

    @Override
    public boolean adultPlusIconStatus() {
        String status = getDriver().findElementById("com.monleylu.app.ui:id/ccv_adult_number").findElementById("com.monleylu.app.ui:id/iv_number_add").getAttribute("enabled");
        return status.equals("true");
    }

    @Override
    public boolean childMinusIconStatus() {
        String status = getDriver().findElementById("com.monleylu.app.ui:id/ccv_child_number").findElementById("com.monleylu.app.ui:id/iv_number_sub").getAttribute("enabled");
        return status.equals("true");
    }

    @Override
    public boolean childPlusIconStatus() {
        String status = getDriver().findElementById("com.monleylu.app.ui:id/ccv_child_number").findElementById("com.monleylu.app.ui:id/iv_number_add").getAttribute("enabled");
        return status.equals("true");
    }

    @Override
    public boolean roomMinusIconStatus() {
        String status = getDriver().findElementById("com.monleylu.app.ui:id/ccv_room_number").findElementById("com.monleylu.app.ui:id/iv_number_sub").getAttribute("enabled");
        return status.equals("true");
    }

    @Override
    public boolean roomPlusIconStatus() {

        String status = getDriver().findElementById("com.monleylu.app.ui:id/ccv_room_number").findElementById("com.monleylu.app.ui:id/iv_number_add").getAttribute("enabled");
	    return status.equals("true");
    }

    @Override
    public void clickCancelBtn() {
	getDriver().findElementById("com.monleylu.app.ui:id/tv_cancel").click();
    }

    @Override
    public void clickConfirmBtn() {
	getDriver().findElementById("com.monleylu.app.ui:id/tv_confirm").click();
    }
    

    
}
