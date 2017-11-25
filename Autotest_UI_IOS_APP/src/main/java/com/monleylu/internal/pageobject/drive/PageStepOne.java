package com.monleylu.internal.pageobject.drive;

import com.monleylu.internal.common.ElementUtil;
import com.monleylu.internal.pageobject.BasePage;
import com.monleylu.internal.pageobject.CommonActionModule;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.openqa.selenium.By;

/**
 * Created by monley_Lu on 2017/3/10.
 */
public class PageStepOne extends BasePage implements CommonActionModule {
    public PageStepOne(IOSDriver<IOSElement> driver) {
        super(driver);
    }

    public boolean clickNextPageBtn() {
        getDriver().findElementByName("填写订单").click();
        return true;
    }

    public boolean waitPageLoadReady(int timeToWaitInSec) {
        return ElementUtil.waitVisibilityOnExpectTime(getDriver(), By.name("1/2选择资源"), timeToWaitInSec);
    }

    public boolean waitLeaveCurrentPage(int timeToWaitInSec) {
        return ElementUtil.waitInVisibilityOnExpectTime(getDriver(), By.name("1/2选择资源"), timeToWaitInSec);
    }
}
