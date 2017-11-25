/**
*Copyright (c) 2006-2016 . All Rights Reserved.
*/
package com.monleylu.olbb.internal.tours;

/**
 * 
* @Description: 定义常旅模块
* @author lujian
* @date 2017年7月5日
* @version 
*    2017年7月5日  v1.0  create 
*
 */
public class TouristInfo {

    /**索引顺序**/
    int index=-1;
    /**出游人姓名**/
    String touristname="";
    /**出游人证件内容**/
    String touristPaperwork="";
    /**出游人所在的出游类型框 1 成人 , 0 儿童**/
    int touristType=-1;
    
    public TouristInfo() {
	
    }
    
    public TouristInfo(int index, String touristname, String touristPaperwork) {
	this.index = index;
	this.touristname = touristname;
	this.touristPaperwork = touristPaperwork;
    }
    
    public TouristInfo(int index, String touristname, String touristPaperwork,int touristType) {
	this.index = index;
	this.touristname = touristname;
	this.touristPaperwork = touristPaperwork;
	this.touristType=touristType;
    }
    
    public int getIndex() {
        return index;
    }
    public void setIndex(int index) {
        this.index = index;
    }
    public String getTouristname() {
        return touristname;
    }
    public void setTouristname(String touristname) {
        this.touristname = touristname;
    }
    public String getTouristPaperwork() {
        return touristPaperwork;
    }
    public void setTouristPaperwork(String touristPaperwork) {
        this.touristPaperwork = touristPaperwork;
    }

    public int getTouristType() {
        return touristType;
    }

    public void setTouristType(int touristType) {
        this.touristType = touristType;
    }
    
}
