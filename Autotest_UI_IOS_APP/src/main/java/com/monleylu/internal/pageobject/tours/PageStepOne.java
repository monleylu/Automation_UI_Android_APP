package com.monleylu.internal.pageobject.tours;


import com.monleylu.internal.common.ElementUtil;
import com.monleylu.internal.pageobject.BasePage;
import com.monleylu.internal.pageobject.CommonActionModule;
import com.monleylu.internal.pageobject.GenericFlightModule;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.openqa.selenium.By;

/**
 * Created by monley_Lu on 2017/2/25.
 */
public class PageStepOne extends BasePage implements CommonActionModule, GenericFlightModule {

    public PageStepOne(IOSDriver<IOSElement> driver) {
        super(driver);
    }

    public boolean clickNextPageBtn() {
        getDriver().findElementByName("填写订单").click();
        return true;
    }

    public boolean waitPageLoadReady(int timeToWaitInSec) {
        return ElementUtil.waitAttributeToBe(getDriver(), By.name("填写订单"), "enabled", "true", timeToWaitInSec);
    }

    public boolean waitLeaveCurrentPage(int timeToWaitInSec) {
        return ElementUtil.waitInVisibilityOnExpectTime(getDriver(), By.name("1/2选择资源"), timeToWaitInSec);
    }

    /**
     * 判断是否存在弹窗，目前主要是进入1／2页面后，机票加载后的提示弹窗
     *
     * @return 存在弹窗返回true，否则返回false
     */
    public boolean isExistAlertDialog() {
        return ElementUtil.isExist(getDriver(), By.xpath("//XCUIElementTypeAlert[@type='XCUIElementTypeAlert']"));
    }

    public int flightAlertDialogType() {

        if (ElementUtil.isExist(getDriver(), By.name("您预订的本次航班的舱位已经售空，请您更换其他航班后继续预订。"))) {
            return 1;
        }

        return 0;
    }

    public void clickConfirmBtnOfSellOutAlterDialog() {
        getDriver().findElementByName("确认").click();
    }

    public void clickChangeFlightBtnOfSellOutAlterDialog() {
        getDriver().findElementByName("更换航班").click();

    }

    public void clickChangeFlightBtnOfPriceChangeAlertDialog() {

    }

    public void clickContinueBookBtnOfPriceChangeAlertDialog() {

    }
}
