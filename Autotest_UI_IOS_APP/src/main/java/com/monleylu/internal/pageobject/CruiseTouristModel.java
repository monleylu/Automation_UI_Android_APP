package com.monleylu.internal.pageobject;

/**
 * Created by monley_Lu on 2017/8/4.
 */
public interface CruiseTouristModel {

    /**
     * 获取2/2页面邮轮房间数量
     * @return
     */
    public  int getTotalCruiseRoomNum();

    /**
     * 获取指定房间总入住人数
     * @param indexTourist 指定位置房间，从1开始计数
     * @return
     */
    public int getCheckinTouristNum(int indexTourist);

    /**
     * 点击指定位置的出游人
     * @param indexTourist 指定位置房间，从0开始计数
     */
    public void clickSelectTouristBtn(int indexTourist);

}


