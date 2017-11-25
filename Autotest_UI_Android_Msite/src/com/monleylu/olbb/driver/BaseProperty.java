/**
*Copyright (c) 2006-2016 . All Rights Reserved.
*/
package com.monleylu.olbb.driver;

/**
 * 
* @Description: 定义key-value格式的数据结构
* @author lujian
* @date 2016年12月3日
* @version 
*    2016年12月3日  v1.0  create 
*
 */
public class BaseProperty {
    /** 名称  **/
    private String name;
    /** 属性值 **/
    private String value;
    
    public BaseProperty(){
	
    }
    
    
    public BaseProperty(String name ,String value){
	this.name=name;
	this.value=value;
    }
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }
    
}
