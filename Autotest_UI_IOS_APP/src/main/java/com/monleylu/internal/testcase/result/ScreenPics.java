/**
 * Copyright (c) 2006-2016 . All Rights Reserved.
 */
package com.monleylu.internal.testcase.result;

import java.util.ArrayList;

/**
 *
 * @Description: 保存截图名称及路径
 * @author lujian
 * @date 2016年8月13日
 * @version
 *    2016年8月13日  v1.0  create
 *
 */
public class ScreenPics {
    /** 截图保存的路径，默认保存路径为当前工程下screenshot目录 **/
    private String pathOfSavePics;

    /** 截图名称 **/
    private ArrayList<String> picNames;

    public ScreenPics() {
        //设置默认保存路径
        this.pathOfSavePics = System.getProperty("user.dir") + "/screenshot";
        this.picNames = new ArrayList<String>();
    }

    public String getPathOfSavePics() {
        return pathOfSavePics;
    }

    public void setPathOfSavePics(String pathOfSavePics) {
        this.pathOfSavePics = pathOfSavePics;
    }

    public ArrayList<String> getPicNames() {
        return picNames;
    }

    public void setPicNames(ArrayList<String> picNames) {
        this.picNames = picNames;
    }

    /**
     * 增加一个单张图片名称
     * @param picName 图片名称
     */
    public void addSinglePic(String picName) {

        if (!picName.equals("")) {
            this.picNames.add(picName);
        }

    }

}
