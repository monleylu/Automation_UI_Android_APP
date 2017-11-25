/**
 * Copyright (c) 2006-2016 . All Rights Reserved.
 */
package com.monleylu.internal.testcase.data;


/**
 *
 * @Description: 定义测试用例结构
 * @author lujian
 * @date 2016年8月12日
 * @version
 *    2016年8月12日  v1.0  create
 *
 */

public class TestCase {

    /** 测试编号，预留后续开发使用**/
    private int ID;

    /** 测试用例名称 **/
    private String testCaseName;

    /** 测试用例详细描述 **/
    private String testCaseDescription;


    public TestCase() {
    }

    public TestCase(int testCaseID, String testCaseName, String testCaseDescription) {
        this.ID = testCaseID;
        this.testCaseName = testCaseName;
        this.testCaseDescription = testCaseDescription;
    }


    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTestCaseName() {
        return testCaseName;
    }

    public void setTestCaseName(String testCaseName) {
        this.testCaseName = testCaseName;
    }

    public String getTestCaseDescription() {
        return testCaseDescription;
    }

    public void setTestCaseDescription(String testCaseDescription) {
        this.testCaseDescription = testCaseDescription;
    }

}
