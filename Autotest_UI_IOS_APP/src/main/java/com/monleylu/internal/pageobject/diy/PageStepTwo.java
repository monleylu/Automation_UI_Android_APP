package com.monleylu.internal.pageobject.diy;

import com.monleylu.internal.common.APPUtil;
import com.monleylu.internal.common.ElementUtil;
import com.monleylu.internal.pageobject.BasePage;
import com.monleylu.internal.pageobject.CommonActionModule;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.openqa.selenium.By;

import java.util.List;
import java.util.Random;

/**
 * Created by monley_Lu on 2017/3/14.
 */
public class PageStepTwo extends BasePage implements CommonActionModule {
    public PageStepTwo(IOSDriver<IOSElement> driver) {
        super(driver);
    }

    public boolean clickNextPageBtn() {
        getDriver().findElementByName("立即付款").click();
        return true;
    }

    public boolean waitPageLoadReady(int timeToWaitInSec) {
        return ElementUtil.waitVisibilityOnExpectTime(getDriver(), By.name("2/2填写订单"), timeToWaitInSec);
    }

    public boolean waitLeaveCurrentPage(int timeToWaitInSec) {
        return false;
    }

    public boolean clickSelectTouristBtn() {
        getDriver().findElementByName("更多出游人 >").click();
        return true;
    }

    public void agreeAllNotice() {

        //判断出游人、协议这个视图高度，根据每次滚动像素数，多滚动几次
        int count = getDriver().findElementByXPath("//XCUIElementTypeTable").getSize().getHeight() / 200 + 1;
        System.out.println("----------------" + count);
        for (; count > 0; count--) {
            APPUtil.swipeUpPageView(getDriver());
            System.out.println("%%%%");
        }

        List<IOSElement> listCheckBtn = getDriver().findElementsByName("normalDrive unselected");
        for (IOSElement iosElement : listCheckBtn) {
            //勾选的协议value属性为1,没勾选的没有value属性，返回内容为 null
            if (iosElement.getAttribute("value") == null)
                iosElement.click();
        }

        //当存在签证时，勾选阅读协议需要另外处理，没有name属性
        //if (ElementUtil.isExist(getDriver(), By.xpath("//XCUIElementTypeTable/XCUIElementTypeOther/XCUIElementTypeButton"))){
        //    getDriver().findElementByXPath("//XCUIElementTypeTable/XCUIElementTypeOther/XCUIElementTypeButton").click();
        //}


    }


    /**
     * 目前发现的弹窗有：
     * 占位失败；
     * 含机票资源产品实际出游人类型和选择的出游人类型不同，提示儿童选择了成人票；
     * 出游人不满足年龄要求，如低于最低年龄下限或者高于上限等
     * 出游人名称包含 先生 女士
     *
     * @param timeToWaitInSec wait time
     * @return 存在返回true，否则返回false
     */
    public boolean isExistAlertDialog(int timeToWaitInSec) {

        if (ElementUtil.waitVisibilityOnExpectTime(getDriver(), By.xpath("//XCUIElementTypeAlert[@type='XCUIElementTypeAlert']"), timeToWaitInSec)) {
            return true;
        }

        //driver product
        if (ElementUtil.waitVisibilityOnExpectTime(getDriver(), By.name("占位超时"), timeToWaitInSec)) {
            return true;
        }

        return false;

    }


    /**
     * 点击填写订单页面弹窗等确定或者取消按钮，使流程继续流转，否则无法进行返回首页等后续操作
     */
    public void clickConfirmBtnOfAlertBox() {

        //占位失败/年龄校验失败弹窗时，走此流程，页面弹窗里只有一个确定按钮
        if (ElementUtil.isExist(getDriver(), By.xpath("//XCUIElementTypeButton[@name='确定']"))) {
            getDriver().findElementByXPath("//XCUIElementTypeButton[@name='确定']").click();
        } else
            //选择了机票资源时，实际出游人类型和选择的出游人类型不同时弹窗
            //这里有两种选择，一种提交订单跳下单成功页面，一种继续预订走在线预订流程，这里使用随机数处理
            if (ElementUtil.isExist(getDriver(), By.name("提交订单"))) {
                if (new Random().nextBoolean()) {
                    getDriver().findElementByName("提交订单").click();
                } else {
                    getDriver().findElementByName("继续预订").click();
                }

            }


    }


    /**
     * 点击签约付款后，弹出的加载中弹窗
     *
     * @param timeToWaitInSec
     * @return 存在返回true，or return false
     */
    public boolean isVisibilityProgressBox(int timeToWaitInSec) {
        return ElementUtil.waitVisibilityOnExpectTime(getDriver(), By.name("正在加载"), timeToWaitInSec);
    }


    public boolean isInVisibilityProgressBox(int timeToWaitInSec) {
        return ElementUtil.waitInVisibilityOnExpectTime(getDriver(), By.name("正在加载"), timeToWaitInSec);
    }

    /**
     * 占位等待浮层
     * @param timeToWaitInSec
     * @return
     */
    public boolean isVisibilityOccupyBox(int timeToWaitInSec){
        return ElementUtil.waitVisibilityOnExpectTime(getDriver(),By.xpath("//XCUIElementTypeStaticText[contains(@name,'订单占位中')]"),timeToWaitInSec);
    }

    /**
     * 占位等待浮层
     * @param timeToWaitInSec
     * @return
     */
    public boolean isInVisibilityOccupyBox(int timeToWaitInSec){
        return ElementUtil.waitInVisibilityOnExpectTime(getDriver(),By.xpath("//XCUIElementTypeStaticText[contains(@name,'订单占位中')]"),timeToWaitInSec);
    }

}
