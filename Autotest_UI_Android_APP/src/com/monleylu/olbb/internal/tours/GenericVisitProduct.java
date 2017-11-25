/**
*Copyright (c) 2006-2016 . All Rights Reserved.
*/
package com.monleylu.olbb.internal.tours;


/**
 * 
* @Description: 定义关于产品的常规操作
* @author lujian
* @date 2016年8月2日
* @version 
*    2016年8月2日  v1.0  create 
*
 */
public interface GenericVisitProduct {
    
    /**
     * 定义各种操作来打开产品详情页
     * @param productID 产品id
     * @return 执行完毕返回true
     */
    boolean visitProduct(String productID);
    


}
