/**
*Copyright (c) 2006-2016 . All Rights Reserved.
*/
package com.monleylu.olbb.internal.tours;

/**
 * 
* @Description: 定义选择出游人模块业务功能
* @author lujian
* @date 2017年7月8日
* @version 
*    2017年7月8日  v1.0  create 
*
 */
public interface TouristModelInterface {

    /**
     * 选择成人出游人
     */
    void clickSelectAdultTouristsBtn();
    
    /**
     * 选择儿童出游人
     */
    void clickSelectChildTouristsBtn();
    
    /**
     * 选择免票儿童出游人
     */
    void clickSelectFreeChildTouristsBtn();
    

}
