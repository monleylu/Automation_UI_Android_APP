package com.monleylu.internal.pageobject.cruise;


import com.monleylu.internal.common.ElementUtil;
import com.monleylu.internal.pageobject.BasePage;
import com.monleylu.internal.pageobject.CommonActionModule;
import com.monleylu.internal.pageobject.GenericFlightModule;
import com.monleylu.internal.pageobject.cruise.view.CruiseRoom;
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

    //选择邮轮模块
    CruiseRoom cruiseRoom = new CruiseRoom(getDriver());

    public boolean clickNextPageBtn() {
        getDriver().findElementByName("下一步").click();
        return true;
    }

    public boolean waitPageLoadReady(int timeToWaitInSec) {
        return ElementUtil.waitAttributeToBe(getDriver(), By.name("下一步"), "enabled", "true", timeToWaitInSec);
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


    /**
     * 判断是否存在"请先选择邮轮房型"弹窗
     * @return
     */
    public boolean isExistPlsSelectCruiseRoom(){
        return ElementUtil.isExist(getDriver(),By.name("请先选择邮轮房型"));
    }

    /**
     * 判断是否存在 "您好，当前选择的邮轮入住人数与出游人数不一致，请修改" 弹窗
     * @return
     */
    public  boolean isExistCruiseChenkinUnmatch(){
        return  ElementUtil.isExist(getDriver(),By.name("您好，当前选择的邮轮入住人数与出游人数不一致，请修改"));
    }
    /**
     * 点击 没有选择邮轮房型 或者 出游人不一致时 的弹窗 确认按钮
     */
    public void clickConfirmBtn(){
        getDriver().findElementByXPath("//XCUIElementTypeButton[@name='确认']").click();
    }

    public CruiseRoom getCruiseRoom() {
        return cruiseRoom;
    }

    public void setCruiseRoom(CruiseRoom cruiseRoom) {
        this.cruiseRoom = cruiseRoom;
    }
}
