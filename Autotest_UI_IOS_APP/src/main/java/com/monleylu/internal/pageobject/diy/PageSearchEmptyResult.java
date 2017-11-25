package com.monleylu.internal.pageobject.diy;

import com.monleylu.internal.common.ElementUtil;
import com.monleylu.internal.pageobject.BasePage;
import com.monleylu.internal.pageobject.CommonActionModule;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.openqa.selenium.By;

/**
 * Created by monley_Lu on 2017/3/22.
 */
public class PageSearchEmptyResult extends BasePage implements CommonActionModule {

    public PageSearchEmptyResult(IOSDriver<IOSElement> driver) {
        super(driver);
    }

    public boolean clickNextPageBtn() {
        return false;
    }

    public boolean waitPageLoadReady(int timeToWaitInSec) {
        return ElementUtil.waitVisibilityOnExpectTime(getDriver(), By.name("重新搜索"), timeToWaitInSec);
    }

    public boolean waitLeaveCurrentPage(int timeToWaitInSec) {
        return false;
    }
}
