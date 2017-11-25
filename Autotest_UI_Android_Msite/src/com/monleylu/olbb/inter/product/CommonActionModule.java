/**
*Copyright (c) 2006-2016 . All Rights Reserved.
*/
package com.monleylu.olbb.inter.product;


/**
 * 
* @Description: 页面的基础功能的操作
* @author lujian
* @date 2016年8月25日
* @version 
*    2016年8月25日  v1.0  create 
*
 */
public interface CommonActionModule {
    
    /**
     * 等待页面加载完毕
     * @param timeToWaitInSec  等待页面加载完毕时间
     * @return 加载完毕返回true，否则返回false
     */
    public abstract boolean waitPageLoadReady(int timeToWaitInSec);
    
    
    //由于点击下一步按钮后，页面还要进行一些操作才会执行页面跳转，所以增加此功能
    /**
     * 等待指定时间，判断是否已离开当前页面
     * @param timeToWaitInSec 等待页面离开当前页面时间
     * @return 不再是当前页面返回true，否则返回false
     */
    public abstract boolean waitLeaveCurrentPage(int timeToWaitInSec);
    
    /**
     * 点击下一步按钮继续后续业务流程
     * @return 点击完毕返回true
     */
    public abstract boolean clickNextStepBtn();
    
    
    //后续扩展上一页按钮
    
    

}
