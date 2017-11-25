package com.monleylu.internal.pageobject.diy;

import com.monleylu.internal.common.ElementUtil;
import com.monleylu.internal.pageobject.BasePage;
import com.monleylu.internal.pageobject.CommonActionModule;
import com.monleylu.internal.pageobject.FlightDialogType;
import com.monleylu.internal.pageobject.GenericFlightModule;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.openqa.selenium.By;

/**
 * Created by monley_Lu on 2017/3/14.
 */
public class PageDetail extends BasePage implements CommonActionModule, GenericFlightModule {

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
        return ElementUtil.waitVisibilityOnExpectTime(getDriver(), By.name("立即预订"), timeToWaitInSec);
    }

    public boolean waitLeaveCurrentPage(int timeToWaitInSec) {
        return false;
    }

    /**
     * 点击选择价格日历按钮
     *
     * @return
     */
    public boolean clickSelectCalendarBtn() {
        //9.1.9版本样式改变 无法直接识别到更改团期按钮
        //getDriver().findElementByXPath("//XCUIElementTypeTable/XCUIElementTypeOther[2]/XCUIElementTypeButton").click();
        getDriver().findElementByXPath("//XCUIElementTypeStaticText[@name='选择出游人数和日期：2成人 0儿童']/parent::XCUIElementTypeOther/XCUIElementTypeButton").click();

        return true;
    }

    /**
     * if exist flight exception dialog
     *
     * @param timeToWaitInSec time to wait in second
     * @return if exist ,return true
     */
    public boolean isExistFlightDialog(int timeToWaitInSec) {
        // return  ElementUtil.waitVisibilityOnExpectTime(getDriver(),By.xpath("//XCUIElementTypeAlert"),timeToWaitInSec);
        return ElementUtil.waitExistAlertDialogOnExpectTime(getDriver(), timeToWaitInSec);
    }

    public int flightAlertDialogType() {
        int flightDialogType = FlightDialogType.Undefine;
        String dialogString = getDriver().findElementByXPath("//XCUIElementTypeAlert/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeStaticText").getAttribute("value");
        System.out.println("机票弹窗内容为：" + dialogString);
        if (dialogString.contains("当前选择的航班或舱位已售完，请更换后继续预订")) {
            flightDialogType = FlightDialogType.SoldOut;
        } else if (dialogString.contains("航班实时价格变动")) {
            flightDialogType = FlightDialogType.PriceChange;
        }

        return flightDialogType;
    }

    public void clickConfirmBtnOfSellOutAlterDialog() {
        //此处要注意，详情页的机票售空弹窗和预订流程的售空弹窗，文案不一样，确认功能，详情页展示的是取消文案，预订流程展示的时继续预订文案
        getDriver().findElementByName("取消").click();

    }

    public void clickChangeFlightBtnOfSellOutAlterDialog() {
        getDriver().findElementByName("更换航班").click();

    }

    public void clickChangeFlightBtnOfPriceChangeAlertDialog() {
        getDriver().findElementByName("更换航班").click();
    }

    public void clickContinueBookBtnOfPriceChangeAlertDialog() {
        getDriver().findElementByName("继续预订").click();

    }
}
