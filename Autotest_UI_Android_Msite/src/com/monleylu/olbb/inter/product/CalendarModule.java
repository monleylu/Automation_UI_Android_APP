/**
*Copyright (c) 2006-2016 . All Rights Reserved.
*/
package com.monleylu.olbb.inter.product;

/**
 * 
* @Description 价格日历模块相关内容,包括选择团期、出游人等
* @author lujian
* @date 2016年8月24日
* @version 
*    2016年8月24日  v1.0  create 
*
 */
public interface CalendarModule {
    
    /**
     * 获取一个随机的有效出游日期
     * @return 出游日期
     */
    String getRandomTourDate();
    
    /**
     * 选择出游日期
     * @param bookDate 出游日期
     * @return 选择完毕返回true，出现异常等返回false
     */
    boolean selectTourDate(String bookDate);
    
    /**
     * 选择出游成人数
     * @param adultNum 成人出游人数
     * @return 选择完毕返回true，其他情况返回false
     */
    boolean selectAdult(int  adultNum);
    
    /**
     * 选择出游儿童数目
     * @param childNum 儿童出游人数
     * @return 选择完毕返回true，其他情况返回false
     */
    boolean selectChild(int childNum);
    
    /**
     * 选择出游免票儿童
     * @param freeChildNum 免票儿童人数
     * @return 选择完毕返回true，其他情况返回false
     */
    boolean selectFreeChild(int freeChildNum);
    

}
