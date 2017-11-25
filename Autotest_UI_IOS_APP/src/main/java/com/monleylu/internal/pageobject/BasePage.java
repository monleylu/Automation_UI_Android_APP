package com.monleylu.internal.pageobject;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

/**
 * 所有页面的基类
 * Created by monley_Lu on 2017/2/24.
 */
public class BasePage {
    private IOSDriver<IOSElement> driver;

    public BasePage(IOSDriver<IOSElement> driver) {
        this.driver = driver;
    }

    public IOSDriver<IOSElement> getDriver() {
        return driver;
    }

    public void setDriver(IOSDriver<IOSElement> driver) {
        this.driver = driver;
    }
}
