/**
 * Copyright (c) 2006-2016 . All Rights Reserved.
 */
package com.monleylu.internal.xml;

/**
 *
 * @Description: 定义读取测试数据源配置文件格式
 * @author lujian
 * @date 2016年9月20日
 * @version
 *    2016年9月20日  v1.0  create
 *
 */
public class XmlDataSrc {
    private String id;
    private String cookieFlag = "";
    private String cookieData = "";
    private String srcFlag = "";
    private String srcData = "";

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCookieFlag() {
        return cookieFlag;
    }

    public void setCookieFlag(String cookieFlag) {
        this.cookieFlag = cookieFlag;
    }

    public String getCookieData() {
        return cookieData;
    }

    public void setCookieData(String cookieData) {
        this.cookieData = cookieData;
    }

    public String getSrcFlag() {
        return srcFlag;
    }

    public void setSrcFlag(String srcFlag) {
        this.srcFlag = srcFlag;
    }

    public String getSrcData() {
        return srcData;
    }

    public void setSrcData(String srcData) {
        this.srcData = srcData;
    }
}
