/**
*Copyright (c) 2006-2016 . All Rights Reserved.
*/
package com.monleylu.olbb.product.tours;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

/**
 * 
* @Description:  跟团、跟队自驾、常规自驾打包采购方式;跟团更改机票检查列表
* @author lujian
* @date 2016年8月22日
* @version 
*    2016年8月26日  v1.0  create  重构代码，解耦业务和数据，抽象业务模型
*
 */
public class ToursProduct {
    /** 跟团详情页  **/
    private PageDetail pageDetail;
    private PageCalendar pageCalendar;
    private PageDuplicateOrder pageDuplicateOrder;
    private PageStepOne pageStepOne;
    private PageStepTwo pageStepTwo;
    private PageTourist pageTourist;
    private PageChangeFlight pageChangeFlight;
    private PageFreeGiftPromation pageFreeGiftPromation;
    private PageChangeTrain pageChangeTrain;
    
    public ToursProduct(AndroidDriver<AndroidElement> androidDriver) {
	this.setPageDetail(new PageDetail(androidDriver));
	this.setPageCalendar(new PageCalendar(androidDriver));
	this.setPageDuplicateOrder(new PageDuplicateOrder(androidDriver));
	this.setPageStepOne(new PageStepOne(androidDriver));
	this.setPageStepTwo(new PageStepTwo(androidDriver));
	this.setPageTourist(new PageTourist(androidDriver));
	this.setPageChangeFlight(new PageChangeFlight(androidDriver));
	this.setPageFreeGiftPromation(new PageFreeGiftPromation(androidDriver));
	this.setPageChangeTrain(new PageChangeTrain(androidDriver));
    }

    public PageDetail getPageDetail() {
	return pageDetail;
    }

    public void setPageDetail(PageDetail pageDetail) {
	this.pageDetail = pageDetail;
    }

    public PageCalendar getPageCalendar() {
        return pageCalendar;
    }

    public void setPageCalendar(PageCalendar pageCalendar) {
        this.pageCalendar = pageCalendar;
    }

    public PageDuplicateOrder getPageDuplicateOrder() {
        return pageDuplicateOrder;
    }

    public void setPageDuplicateOrder(PageDuplicateOrder pageDuplicateOrder) {
        this.pageDuplicateOrder = pageDuplicateOrder;
    }

    public PageStepOne getPageStepOne() {
        return pageStepOne;
    }

    public void setPageStepOne(PageStepOne pageStepOne) {
        this.pageStepOne = pageStepOne;
    }

    public PageStepTwo getPageStepTwo() {
        return pageStepTwo;
    }

    public void setPageStepTwo(PageStepTwo pageStepTwo) {
        this.pageStepTwo = pageStepTwo;
    }

    public PageTourist getPageTourist() {
        return pageTourist;
    }

    public void setPageTourist(PageTourist pageTourist) {
        this.pageTourist = pageTourist;
    }
    
    public PageChangeFlight getPageChangeFlight() {
        return pageChangeFlight;
    }

    public void setPageChangeFlight(PageChangeFlight pageChangeFlight) {
        this.pageChangeFlight = pageChangeFlight;
    }
    
    public PageFreeGiftPromation getPageFreeGiftPromation() {
        return pageFreeGiftPromation;
    }

    public void setPageFreeGiftPromation(PageFreeGiftPromation pageFreeGiftPromation) {
        this.pageFreeGiftPromation = pageFreeGiftPromation;
    }
    
    public PageChangeTrain getPageChangeTrain() {
        return pageChangeTrain;
    }

    public void setPageChangeTrain(PageChangeTrain pageChangeTrain) {
        this.pageChangeTrain = pageChangeTrain;
    }

}
