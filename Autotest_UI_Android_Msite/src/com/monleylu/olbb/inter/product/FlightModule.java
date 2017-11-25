/**
*Copyright (c) 2006-2016 . All Rights Reserved.
*/
package com.monleylu.olbb.inter.product;

/**
 * 
* @Description: 预订1/2页面机票模块
* @author lujian
* @date 2016年8月24日
* @version 
*    2016年8月24日  v1.0  create 
*
 */
public interface FlightModule {
    
    /**
     * 判断是否存在机票异常弹窗
     * @notice 机票异常弹窗有很多种不同识别样式，如果机票为空、验仓验价失败等，暂时定义一个接口处理，后续根据实际情况再决定是否拆分，已要求研发统一样式
     * @return
     */
    boolean isExistFlightExceptionDialog();
    
    
    /**
     * 等待机票模块div层展示
     * @param timeToWaitInSec 等待时间
     * @return 出现机票模块返回true，否则返回false
     */
    boolean waitShowFlightModule(int timeToWaitInSec);
    
    /**
     * 等待机票信息加载完毕
     * @param timeToWaitInSec 等待时间
     * @return 出现机票信息返回true
     */
    boolean waitShowFlight(int timeToWaitInSec);
    
    /**
     * 获取机票弹窗浮层内容
     * @return 返回机票弹窗内容
     */
    String  getFlightDialogMessage();

}
