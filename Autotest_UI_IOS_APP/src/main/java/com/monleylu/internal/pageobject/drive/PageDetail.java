package com.monleylu.internal.pageobject.drive;

import com.monleylu.internal.common.ElementUtil;
import com.monleylu.internal.pageobject.BasePage;
import com.monleylu.internal.pageobject.CommonActionModule;
import com.monleylu.internal.pageobject.GenericTicketModule;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.openqa.selenium.By;

/**
 * Created by monley_Lu on 2017/3/9.
 */
public class PageDetail extends BasePage implements GenericTicketModule, CommonActionModule {


    public PageDetail(IOSDriver<IOSElement> driver) {
        super(driver);
    }


    /**
     * 定义各种操作来打开产品详情页
     *
     * @param productID 产品id
     * @return 执行完毕返回true
     */
    public boolean visitProduct(String productID) {

        getDriver().findElementByXPath("//XCUIElementTypeStaticText[@name=\"目的地/关键词\"]").click();
        getDriver().findElementByXPath("//XCUIElementTypeTextField[@type=\"XCUIElementTypeTextField\"]").setValue(productID);
        getDriver().findElementByName("Search").click();
        return true;
    }

    public boolean clickNextPageBtn() {
        getDriver().findElementByName("立即预订").click();
        return true;
    }

    public boolean waitPageLoadReady(int timeToWaitInSec) {
        if (ElementUtil.waitVisibilityOnExpectTime(getDriver(), By.name("立即预订"), timeToWaitInSec)) {
            return true;
        }
        return false;
    }

    public boolean waitLeaveCurrentPage(int timeToWaitInSec) {
        if (ElementUtil.waitInVisibilityOnExpectTime(getDriver(), By.name("立即预订"), timeToWaitInSec)) {
            return true;
        }
        return false;
    }

    public boolean isExistTicketModule(int timeToWaitInSec) {
        return ElementUtil.isExist(getDriver(), By.name("门票"));
    }

    public int countOfDefaultShowTicket() {
        return getDriver().findElementsByName("使用日期").size();
    }

    //as ios design problem ,each time only select the fisrt "使用日期" element
    public boolean clickUseDateBtn(int index) {
        getDriver().findElementByName("使用日期").click();
        return true;
    }

    public String selectTicketUseDate(int index) {
        //目前仅支持选择第一个
        getDriver().findElementByXPath("/XCUIElementTypeApplication/XCUIElementTypeWindow/XCUIElementTypeOther[2]/XCUIElementTypeOther[2]/XCUIElementTypeTable/XCUIElementTypeCell").click();
        return "";
    }

    public int countOfTicketUseDate() {
        return 0;
    }

    /**
     * 点击选择价格日历按钮
     *
     * @return
     */
    public boolean clickSelectCalendarBtn() {
        getDriver().findElementByName("出游 日期").click();
        return true;
    }

}
