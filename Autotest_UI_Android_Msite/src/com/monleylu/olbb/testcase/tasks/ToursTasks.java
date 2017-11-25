/**
*Copyright (c) 2006-2016 . All Rights Reserved.
*/
package com.monleylu.olbb.testcase.tasks;

/**
 * 
* @Description: 跟团业务功能定义
* @author lujian
* @date 2016年9月26日
* @version 
*    2016年9月26日  v1.0  create 
*
 */
public interface ToursTasks {

    /** 下单功能**/
    public static final int SaveOrder = 0;
    
    /** 检查生僻字功能  **/
    public static final int UncommonUserName = 1; 
    
    /** 检查不登录时到预订流程填写手机号码弹窗登录页面功能  **/
    public static final int CheckLoginOnBookProcess =2;
    
    /** 检查跟团更改机票功能  **/
    public static final int CheckChangeSingleFlight =3;
    
    /**返赠品产品登录+选择返赠品优惠+下单**/
    public static final int FreeGiftPromation =4;
    
    /**检查跟团更改火车票功能**/
    public static final int CheckChangeTrain =5;
}
