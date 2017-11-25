/**
*Copyright (c) 2006-2016 . All Rights Reserved.
*/
package com.monleylu.olbb.inter.product;

/**
 * 
* @Description: 定义页面识别码
* @author lujian
* @date 2016年8月24日
* @version 
*    2016年8月24日  v1.0  create 
*
 */
public interface PageIdentifyCode {
    
    /** 未定义 **/
    int PageProductUnDefine =0;
    
    /** 详情页 **/
    int PageProductDetail = 1;
    
    /** 价格日历页 **/
    int PageProductCalendar = 2;
    
    /** 重单页 **/
    int PageProductRepeatOrder = 3;
    
    /** 1/2页面 **/
    int PageProductStepOne =4;
    
    /**  2/2页面 **/
    int PageProductStepTwo =5;
    
    /**  选择出游人页 **/
    int PageProductTourist =6;
    
    /** 预订成功页 **/
    int PageProductBookSuccess=7;
    
    /** 预订失败页 **/
    int PageProductBookFail=8;
    
    /** 收银台 **/
    int PageProductCasher=9;
    
    /** 快速网络单 **/
    int PageProductFastNetOrder=10;
}
