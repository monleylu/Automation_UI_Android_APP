/**
*Copyright (c) 2006-2016 . All Rights Reserved.
*/
package com.monleylu.olbb.inter.product;

/**
 * 
* @Description: 预订流程的联系人模块
* @author lujian
* @date 2016年9月25日
* @version 
*    2016年9月25日  v1.0  create 
*
 */
public interface ContactModule {
    
    /**
     * 设置联系人姓名
     * @param userName 联系人姓名
     */
    void setUserNameOfContactModule(String userName);
    
    /**
     * 获取联系人姓名
     * @return 返回联系人姓名
     */
    String getUserNameOfContactModule();

    /**
     * 设置国家区号或者代码
     * @param areaCodeID 区号
     */
    void setAreaCodeOfContactModule(String areaCodeID);
    
    /**
     * 获取区号
     * @return
     */
    String getAreaCodeOfContactModule();
    
    /**
     * 设置登录手机号码
     * @param tel 手机号码
     */
    void setTelOfContactModule(String tel);
    
    /**
     * 获取手机号码
     * @return
     */
    String getTelOfContactModule();
    
    /**
     * 设置出游人邮箱账号
     * @param userEmail 邮箱账号
     */
    void setUserEmailOfContactModule(String userEmail);
    
    /**
     * 获取出游人邮箱账号
     * @return
     */
    String getUserEmailOfContactModule();
    
    /**
     * 判断联系人登录模块是否出现
     * @param timeToWaitInSec 等待时间
     * @return 可见返回true，否则返回fasle
     */
    boolean waitExistLoginFrameOfContactModule(int timeToWaitInSec);
    
    /**
     * 设置验证码
     * @param validateTextString 验证码
     */
    void setValidateTextOfContactModule(String validateTextString);
    
    /**
     * 设置登录密码
     * @param randomPassword 密码
     */
    void setPasswordOfContactModule(String randomPassword);
    
    /**
     * 点击登录按钮
     */
    void clickLoginBtnOfContactModule();
}
