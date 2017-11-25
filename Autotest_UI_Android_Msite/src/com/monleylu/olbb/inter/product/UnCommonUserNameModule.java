/**
*Copyright (c) 2006-2016 . All Rights Reserved.
*/
package com.monleylu.olbb.inter.product;

import org.openqa.selenium.By;

/**
 * 
* @Description: 生僻字及婴幼儿校验模块
* @author lujian
* @date 2016年9月22日
* @version 
*    2016年9月22日  v1.0  create 
*
 */
public interface UnCommonUserNameModule {
    
    /**
     * 判断是否存在生僻字及婴幼儿校验模块弹窗
     * @param timeToWaitInSec 等待时间
     * @return 指定时间弹窗出现返回true，否则返回fasle
     */
    boolean waitExistUnCommonUserNameModule(int timeToWaitInSec);
    
    /**
     * 设置拼音姓输入框内容
     * @param xingString 待输入内容
     *  
     */
    void setUserXingOfUnCommonUserNameModule(By locatorInput,String xingString);
    
    /**
     * 设置拼音名输入框内容
     * @param mingString  待输入内容
     *  
     */
    void setUserMingOfUnCommonUserNameModule(By locatorInput,String mingString);
    
    /**
     * 点击取消按钮
     */
    void clickCancelBtnOfUnCommonUserNameModule();
    
    /**
     * 点击确认按钮
     */
    void clickConfirmBtnOfUnCommonUserNameModule();
    
    /**
     * 获取窗口类型，对跟团产品，窗口标题有“生僻字校验”和“婴儿校验”两种，自助产品只有“填写拼音姓名”一种标题
     * @return 当窗口标题为“生僻字校验”返回1，当标题为“婴儿校验”返回2,当标题为“填写拼音姓名”返回3，其他返回0
     */
    int typeOfUnCommonUserNameModule();
    

}
