/**
*Copyright (c) 2006-2016 . All Rights Reserved.
*/
package com.monleylu.olbb.internal.tours;

public interface GenericFlightModule {
    
    /**
     * 判断是否存在异常弹窗
     * @param timeToWaitInSec 等待时间
     * @return 存在返回true，否则返回false
     */
    boolean isExistDialog(int timeToWaitInSec);
    
    /**
     * 判断当前弹窗是否为售空弹窗，售空时，弹窗文案为“您预订的本次航班的舱位已经售空，请您更换其他航班后继续预订”
     * @param timeToWaitInSec  等待时间
     * @return 是售空弹窗返回true，否则返回false
     */
    boolean isExistFlightSellOutDialog(int timeToWaitInSec);
    
    /**
     * 点击售空弹窗的确认按钮
     * 
     */
    void clickConfirmBtnOfFlightSellOutDialog();
    
    /**
     * 点击售空弹窗的取消按钮
     * 
     */
    void cilckChangeFlightBtnOfFlightSellOutDialog();
    
    /**
     * 判断当前弹窗是否为机票信息异常弹窗，弹窗文案为“当前机票信息异常，您可尝试更换其他航班后继续预订，或者提交免费的咨询订单，等待客服电话联系您”
     * @param timeToWaitInSec 等待时间
     * @return 是机票信息异常弹窗返回true，否则返回false
     */
    boolean isExistFlightExceptionDialog(int timeToWaitInSec);
    
    /**
     * 点击提交免费咨询订单，转网络单流程
     */
    void clickBookNoticeBtnOfFlightExceptionDialog();
    
    /**
     * 点击更改机票按钮
     */
    void clickChangeFlightBtnOfFlightExceptionDialog();
    
    /**
     * 验仓验价失败，存在价格有变动弹窗，弹窗文案格式为 “很抱歉，航班价格变化频繁，该航班价格发生变动：+￥104，请确认价格。”
     * @param timeToWaitInSec 等待时间
     * @return 存在验价失败返回true，否在返回false
     */
    boolean isExistFlightMoneyChangeDialog(int timeToWaitInSec);
    
    /**
     * 点击验仓验价弹窗的确认按钮
     * 
     */
    void clickConfirmBtnOfFlightMoneyChangeDialog();
    
    /**
     * 点击更换航班按钮
     * 
     */
    void cilckChangeFlightBtnOfFlightMoneyChangeDialog();
    
}
