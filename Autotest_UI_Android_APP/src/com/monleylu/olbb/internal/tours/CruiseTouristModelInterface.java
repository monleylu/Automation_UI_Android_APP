/**
*Copyright (c) 2006-2016 . All Rights Reserved.
*/
package com.monleylu.olbb.internal.tours;

import org.apache.bcel.generic.Select;

/**
 * 
* @Description: 定义邮轮品类出游人模块
* @author lujian
* @date 2017年7月10日
* @version 
*    2017年7月10日  v1.0  create 
*
 */
public interface CruiseTouristModelInterface {
    
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
     * @param indexTourist 指定位置房间，从1开始计数
     */
    public void clickSelectTouristBtn(int indexTourist);
    
 
}
