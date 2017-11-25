/**
*Copyright (c) 2006-2016 . All Rights Reserved.
*/
package com.monleylu.olbb.driver;

/**
 * 
* @Description: 定义移动手机信息，用来保存机器运行实例基础信息
* @author lujian
* @date 2016年8月16日
* @version 
*    2016年8月16日  v1.0  create 
*
 */
public class UserDriver {
    /** 预留手机驱动信息的id字段，待后期配置数据存储mysql时使用  **/
    private int id;
    
    /** appium所在机器ip地址 **/
    private String ip="";
    
    /** appium所在机器port地址 **/
    private String port="";
    
    /** 手机品牌 **/
    private String brandOfPhone ="";

    /** 手机型号 **/
    private String modelOfPhone ="";
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getBrandOfPhone() {
        return brandOfPhone;
    }

    public void setBrandOfPhone(String brandOfPhone) {
        this.brandOfPhone = brandOfPhone;
    }

    public String getModelOfPhone() {
        return modelOfPhone;
    }

    public void setModelOfPhone(String modelOfPhone) {
        this.modelOfPhone = modelOfPhone;
    }

    public String getPort() {
	return port;
    }

    public void setPort(String port) {
	this.port = port;
    }

    public String getIp() {
	return ip;
    }

    public void setIp(String ip) {
	this.ip = ip;
    }
    
    
}
