package com.monleylu.internal.pageobject;

/**
 * 机票模块
 * Created by monley_Lu on 2017/2/24.
 */
public interface GenericFlightModule {

    /**
     * 返回机票弹窗类型
     *
     * @return 0:未识别弹窗
     * 1：机票售空弹窗
     * 2：验舱验价弹窗
     */
    int flightAlertDialogType();

    /**
     * 点击机票售空弹窗确认按钮
     */
    void clickConfirmBtnOfSellOutAlterDialog();


    /**
     * 点击机票售空弹窗更改机票按钮
     */
    void clickChangeFlightBtnOfSellOutAlterDialog();

    /**
     * 点击机票价格变动弹窗的更换航班按钮
     */
    void clickChangeFlightBtnOfPriceChangeAlertDialog();

    /**
     * 点击机票价格变动弹窗的继续预订按钮
     */
    void clickContinueBookBtnOfPriceChangeAlertDialog();

}
