/**
*Copyright (c) 2006-2016 . All Rights Reserved.
*/
package com.monleylu.olbb.internal.tours;


/**
* @Description: 定义门票模块操作，包括选择门票、获取门票各种信息等
* @author lujian
* @date 2016年7月31日
* @version 
*    2016年7月31日  v1.0  create 
*
*/
public interface GenericTicketModule {
    
   
    /**
     * 判断是否存在门票模块
     * @param timeToWaitInSec 等待时间
     * @return 存在返回true，不存在返回false
     */
    boolean isExistTicketModule(int timeToWaitInSec);
    
    /**
     * 默认展示的门票数量
     * @return 返回默认展示的门票数量，不存在门票模块时，返回0
     */
    int countOfDefaultShowTicket();
    
    
    
    /**
     * 点击索引指定的门票的使用日期按钮，索引从1开始，1代表第一个门票，以此类推
     * @return 点击完毕返回true
     */
    boolean clickUseDateBtn(int index);
    
    
    /**
     * 选择索引指定的日期，1代表选择第一个有效日期，依次类推，0代表随机选择一个有效日期
     * @param index 日期索引顺序
     * @return 返回选择的门票使用日期
     */
    String selectTicketUseDate(int index);
    
    /**
     * 门票可以选择使用的日期总数
     * @return 门票可以选择使用的日期总数
     */
    int countOfTicketUseDate();

    
}
