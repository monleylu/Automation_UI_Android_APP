/**
*Copyright (c) 2006-2016 . All Rights Reserved.
*/
package com.monleylu.olbb.inter.product;


/**
 * 
* @Description: 门票模块
* @author lujian
* @date 2016年8月27日
* @version 
*    2016年8月27日  v1.0  create 重构
*
 */
public interface TicketModule {
    
    /**
     * 等待门票模块div层展示
     * @param timeToWaitInSec 等待时间
     * @return 门票模块div层展示返回true，否则返回false
     */
    boolean waitShowTicketModule(int timeToWaitInSec);
    
    //门票模块目前只接触常规自驾酒店门票模块，待所有存在门票的品类接触后再抽象公共操作
    /**
     * 选择页面指定顺序的门票日期
     * @param ticketIndex
     * @return
     */
    //String selectTicket(By locator);

}
