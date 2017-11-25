/**
*Copyright (c) 2006-2016 . All Rights Reserved.
*/
package com.monleylu.olbb.obj;

/**
 * 
* @Description: 存储联系人信息
* @author lujian
* @date 2016年9月25日
* @version 
*    2016年9月25日  v1.0  create 
*
 */
public class ContactInfo {

    private String userName;
    private String userTel;
    private String areaCode;
    private String userEmail;
    
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getUserTel() {
        return userTel;
    }
    public void setUserTel(String userTel) {
        this.userTel = userTel;
    }
    public String getAreaCode() {
        return areaCode;
    }
    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }
    public String getUserEmail() {
        return userEmail;
    }
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
    

}
