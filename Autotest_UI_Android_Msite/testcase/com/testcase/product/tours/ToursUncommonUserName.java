/**
*Copyright (c) 2006-2016 . All Rights Reserved.
*/
package com.testcase.product.tours;

import com.monleylu.olbb.product.tours.ToursProduct;
import com.monleylu.olbb.result.SaveOrderResult;
import com.monleylu.olbb.testcase.TestUnit;
import com.monleylu.olbb.testcase.tasks.ToursTasks;

/**
 * 
* @Description: 生僻字用例
* @author lujian
* @date 2016年9月21日
* @version 
*    2016年9月21日  v1.0  create 
*
 */
public class ToursUncommonUserName {
    
    public static ThreadLocal<String> uncommonUserName = new ThreadLocal<>();
    
    
    
    /**
     * 生僻字功能校验
     * @param toursProduct 产品模型
     * @param testUnit 测试用例
     * @param usesrIDList 指定的待选择 生僻字用户
     * @return 测试结果
     */
    public SaveOrderResult checkUncommonUserNameDialog(ToursProduct toursProduct,TestUnit testUnit,String usesrIDList){
	uncommonUserName.set(usesrIDList);
	ToursOrder toursOrder = new ToursOrder();
	return toursOrder.ToursTaskActions(toursProduct, testUnit, ToursTasks.UncommonUserName);
      }
    
}
