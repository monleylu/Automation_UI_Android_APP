/**
 * Copyright (c) 2006-2016 . All Rights Reserved.
 */
package com.monleylu.internal.testcase.data;

import com.monleylu.internal.testcase.result.ProductBookInformation;


/**
 *
 * @Description: 最小测试单元，包括测试用例名称以及测试用例需要使用的产品信息
 * @author lujian
 * @date 2016年8月13日
 * @version
 *    2016年8月13日  v1.0  create
 *
 */
public class TestUnit {

    /** 测试用例描述信息 **/
    private TestCase testCase;

    /** 产品预订信息  **/
    private ProductBookInformation productBookInformation;


    public TestCase getTestCase() {
        return testCase;
    }

    public void setTestCase(TestCase testCase) {
        this.testCase = testCase;
    }

    public ProductBookInformation getProductBookInformation() {
        return productBookInformation;
    }

    public void setProductBookInformation(
            ProductBookInformation productBookInformation) {
        this.productBookInformation = productBookInformation;
    }
}
