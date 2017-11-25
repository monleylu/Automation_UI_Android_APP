package com.monleylu.internal.pageobject.tours;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

/**
 * 度假跟团、定制游、跟对自驾业务模型
 * Created by monley_Lu on 2017/2/23.
 */
public class ToursProduct {

    private IOSDriver<IOSElement> driver;
    /**
     * 详情页
     **/
    private PageDetail pageDetail;

    /**
     * 价格日历页面
     **/
    private PageCalendar pageCalendar;
    private PageRepeat pageRepeat;
    private PageStepOne pageStepOne;
    private PageStepTwo pageStepTwo;

    public IOSDriver<IOSElement> getDriver() {
        return driver;
    }

    public void setDriver(IOSDriver<IOSElement> driver) {
        this.driver = driver;
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

    public PageRepeat getPageRepeat() {
        return pageRepeat;
    }

    public void setPageRepeat(PageRepeat pageRepeat) {
        this.pageRepeat = pageRepeat;
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

    public PageTourists getPageTourists() {
        return pageTourists;
    }

    public void setPageTourists(PageTourists pageTourists) {
        this.pageTourists = pageTourists;
    }

    private PageTourists pageTourists;

    //构造函数
    public ToursProduct(IOSDriver<IOSElement> driver) {
        this.driver = driver;


        this.pageDetail = new PageDetail(driver);
        this.pageCalendar = new PageCalendar(driver);
        this.pageRepeat = new PageRepeat(driver);
        this.pageStepOne = new PageStepOne(driver);
        this.pageStepTwo = new PageStepTwo(driver);
        this.pageTourists = new PageTourists(driver);


    }
}
