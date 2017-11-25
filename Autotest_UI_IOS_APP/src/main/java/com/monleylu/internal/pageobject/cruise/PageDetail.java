package com.monleylu.internal.pageobject.cruise;

import com.monleylu.internal.common.ElementUtil;
import com.monleylu.internal.pageobject.BasePage;
import com.monleylu.internal.pageobject.CommonActionModule;
import com.monleylu.internal.pageobject.GenericVisitProduct;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.openqa.selenium.By;

/**
 * 详情页模型
 * Created by monley_Lu on 2017/2/23.
 */
public class PageDetail extends BasePage implements CommonActionModule, GenericVisitProduct {


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
        getDriver().findElementByName("开始预订").click();
        return true;
    }

    public boolean waitPageLoadReady(int timeToWaitInSec) {
        if (ElementUtil.waitVisibilityOnExpectTime(getDriver(), By.name("开始预订"), timeToWaitInSec)) {
            return true;
        }
        return false;
    }

    public boolean waitLeaveCurrentPage(int timeToWaitInSec) {
        return false;
    }
}
