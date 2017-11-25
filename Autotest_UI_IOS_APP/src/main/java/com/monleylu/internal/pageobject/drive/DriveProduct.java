package com.monleylu.internal.pageobject.drive;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

/**
 * Created by monley_Lu on 2017/3/9.
 */
public class DriveProduct {

    private IOSDriver<IOSElement> driver;
    private PageDetail pageDetail;
    private PageCalendar pageCalendar;
    private PageStepOne pageStepOne;
    private PageStepTwo pageStepTwo;
    private PageTourists pageTourists;

    public DriveProduct(IOSDriver<IOSElement> driver) {
        this.driver = driver;
        this.pageDetail = new PageDetail(driver);
        this.pageCalendar = new PageCalendar(driver);
        this.pageStepOne = new PageStepOne(driver);
        this.pageStepTwo = new PageStepTwo(driver);
        this.pageTourists = new PageTourists(driver);
    }


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
}
