/**
*Copyright (c) 2006-2016 . All Rights Reserved.
*/
package com.monleylu.olbb.internal.tours;

/**
 * 
* @Description: 定义邮轮房型字段
* @author lujian
* @date 2017年7月8日
* @version 
*    2017年7月8日  v1.0  create 
*
 */
public class CruiseRoomInformation {
    String roomName;
    int upLimit;
    int lowerLimit;
    
    public String getRoomName() {
        return roomName;
    }
    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }
    public int getUpLimit() {
        return upLimit;
    }
    public void setUpLimit(int upLimit) {
        this.upLimit = upLimit;
    }
    public int getLowerLimit() {
        return lowerLimit;
    }
    public void setLowerLimit(int lowerLimit) {
        this.lowerLimit = lowerLimit;
    }
    
    
}
