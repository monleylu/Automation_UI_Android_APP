/**
*Copyright (c) 2006-2016 . All Rights Reserved.
*/
package com.monleylu.olbb.inter.product;

/**
 * 
* @Description: 2/2页面出游人模块
* @author lujian
* @date 2016年8月24日
* @version 
*    2016年8月24日  v1.0  create 
*
 */
public interface TouristModule {
    
    /**
     * 点击选择常旅按钮
     * @return 点击完毕返回true
     */
    boolean clickCommonTouristBtn();
    
    /**
     * 点击成人出游人区域
     * @return 点击完毕返回true
     */
    boolean clickAdultTouristBtn();
    
    /**
     * 点击儿童出游人区域
     * @return 点击完毕返回true
     */
    boolean clickChildTouristBtn();
    
    /**
     * 点击免票儿童出游人区域
     * @return 点击完毕返回true
     */
    boolean clickFreeChildTouristBtn();

}
