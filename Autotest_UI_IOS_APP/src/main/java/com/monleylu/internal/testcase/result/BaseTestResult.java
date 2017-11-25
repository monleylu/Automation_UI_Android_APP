/**
 * Copyright (c) 2006-2016 . All Rights Reserved.
 */
package com.monleylu.internal.testcase.result;

/**
 *
 * @Description: 定义基础测试结果信息
 * @author lujian
 * @date 2016年8月12日
 * @version
 *    2016年8月12日  v1.0  create
 *
 */
public class BaseTestResult {
    /** 测试用例执行状态 **/
    private boolean success = false;

    /** 测试用例执行结果说明信息 **/
    private String msg;


    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


}
