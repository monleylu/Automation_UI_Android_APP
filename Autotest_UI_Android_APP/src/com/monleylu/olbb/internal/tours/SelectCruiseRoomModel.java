/**
*Copyright (c) 2006-2016 . All Rights Reserved.
*/
package com.monleylu.olbb.internal.tours;

import java.util.List;

import io.appium.java_client.android.AndroidElement;

/**
 * 
* @Description: 邮轮模块接口模型
* @author lujian
* @date 2017年7月8日
* @version 
*    2017年7月8日  v1.0  create 
*
 */
public interface SelectCruiseRoomModel {

    /**
     * 获取当前页面显示的邮轮房型元素
     * @return 
     */
    public List<AndroidElement> getCurrentShowCruiseRoomElement();
    
    /**
     * 获取指定房型的基本信息
     * @param cruiseRoomElement 房型元素
     * @return
     */
    public CruiseRoomInformation getCruiseRoomInformation(AndroidElement cruiseRoomElement);
    
    /**
     * 选择指定房型
     * @param cruiseRoomElement 房型元素
     * @param adultNum 入住成人
     * @param childNum 入住儿童
     * @return 可以入住返回true，否则返回false
     */
    public boolean selectCruiseRoom(AndroidElement cruiseRoomElement,int adultNum ,int childNum);

    /**
     * 获取已选择成人数
     * @return 入住人数
     */
    public int getAdultNum();

    /**
     * 获取已选择儿童数
     * @return 入住人数
     */
    public int getChildNum();

    /**
     * 获取已选择房间数
     * @return 入住人数
     */
    public int getRoomNum();

    /**
     * 点击成人减号
     */
    public void clickAdultMinusBtn();

    /**
     * 点击成人加号
     */
    public void clickAdultPlusBtn();

    /**
     * 点击儿童减号
     */
    public void clickChildMinusBtn();

    /**
     * 点击儿童加号
     */
    public void clickChildPlusBtn();

    /**
     * 点击房间减号
     */
    public void clickRoomMinusBtn();

    /**
     * 点击房间加号
     */
    public void clickRoomPlusBtn();


    /**
     * 成人减号状态
     * @return 可点击返回true，否则返回false
     */
    public boolean adultMinusIconStatus();
    /**
     * 成人加号状态
     * @return 可点击返回true，否则返回false
     */
    public boolean adultPlusIconStatus();
    /**
     * 儿童减号状态
     * @return 可点击返回true，否则返回false
     */
    public boolean childMinusIconStatus();
    /**
     * 儿童加号状态
     * @return 可点击返回true，否则返回false
     */
    public boolean childPlusIconStatus();
    /**
     * 房型减号状态
     * @return 可点击返回true，否则返回false
     */
    public boolean roomMinusIconStatus();
    /**
     * 房型加号状态
     * @return 可点击返回true，否则返回false
     */
    public boolean roomPlusIconStatus();
    
    /**
     * 点击取消按钮
     */
    public void clickCancelBtn();
    
    /**
     * 点击确定按钮
     */
    public void clickConfirmBtn();
    
    
    
}
