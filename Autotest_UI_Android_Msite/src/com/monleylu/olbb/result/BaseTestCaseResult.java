/**
*Copyright (c) 2006-2016 . All Rights Reserved.
*/
package com.monleylu.olbb.result;

import com.monleylu.olbb.testcase.TestUnit;

/**
 * 
* @Description: 定义保存测试用例结果数据结构
* @author lujian
* @date 2016年8月13日
* @version 
*    2016年8月13日  v1.0  create 
*
 */
public class BaseTestCaseResult extends BaseTestResult {

    /** 测试用例以及预订产品信息 **/
    private TestUnit testUnit;

    public BaseTestCaseResult(){
	
    }
    
    public BaseTestCaseResult(TestUnit testUnit){
	this.setTestUnit(testUnit);
    }
    
    public TestUnit getTestUnit() {
	return testUnit;
    }

    public void setTestUnit(TestUnit testUnit) {
	this.testUnit = testUnit;
    }
   
}
