package com.monleylu.internal.pageobject;

/**
 * flight dialog type
 * Created by monley_Lu on 2017/3/20.
 */
public interface FlightDialogType {

    /**
     * 未识别弹窗
     **/
    final int Undefine = 0;

    /**
     * 航班或舱位售空
     **/
    final int SoldOut = 1;

    /**
     * 价格变动
     **/
    final int PriceChange = 2;
}
