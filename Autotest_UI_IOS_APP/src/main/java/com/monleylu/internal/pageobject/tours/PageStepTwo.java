package com.monleylu.internal.pageobject.tours;

import com.monleylu.internal.common.APPUtil;
import com.monleylu.internal.common.DataUtil;
import com.monleylu.internal.common.ElementUtil;
import com.monleylu.internal.pageobject.BasePage;
import com.monleylu.internal.pageobject.CommonActionModule;
import io.appium.java_client.TouchAction;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;

import java.util.List;
import java.util.Random;

/**
 * Created by monley_Lu on 2017/2/25.
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
        return ElementUtil.waitAttributeToBe(getDriver(), By.name("立即付款"), "enabled", "true", timeToWaitInSec);
    }

    public boolean waitLeaveCurrentPage(int timeToWaitInSec) {
        return false;
    }


    public void clickSelectTouristsBtn() {
        getDriver().findElementByName("常用游客").click();

    }

    /**
     * 点击选择成人框
     */
    public void clickSelectAdultBtn(){
        clickSelectTourist(2);
    }

    /**
     * 点击选择儿童框
     */
    public void clickSelectChildBtn(){
        //2/2有个浮层，在自动化时会出现，不知道什么原因，有时会正好挡住第一个儿童选择框，加一个滑动处理下这个问题
        //AppUtil.swipeUpDown(getDriver(), -300);
        //当成人出游人过多时，儿童出游人框不一定可见，继续滚动
        //AppUtil.moveScreenToElement(getDriver(), By.xpath("//android.widget.TextView[@resource-id = 'com.monleylu.app.ui:id/tv_tourist_empty' and @text='点击选择儿童']"), 3);
        clickSelectTourist(1);
    }

    public void clickSelectFreeChildBtn(){
        clickSelectTourist(0);
    }

    /**
     * 2/2页面选择不同类型出游人
     * @param personType 2:adult;1:child；0：freechild
     */
    private void clickSelectTourist(int personType){
        if (2==personType) {
            //当只有成人时，会出现常用出游人模块，此处随机选择点击更多出游人按钮或者点击成人出游人模块
            if (ElementUtil.isExist(getDriver(),By.name("更多出游人 >"))){
                if (DataUtil.randomBoolean()){
                    getDriver().findElementByName("更多出游人 >").click();
                }else{
                    getDriver().findElementByName("成人1    请编辑成人信息").click();
                }

            }else {
                //当选择成人时，可以点击默认第一个成人选择框，火车票地接产品成人编辑框名字相同
                getDriver().findElementByName("成人1    请编辑成人信息").click();
            }

            return;
        }

        if (1==personType) {

            if (ElementUtil.isExist(getDriver(),By.name("儿童1    请编辑儿童信息"))){
                getDriver().findElementByName("儿童1    请编辑儿童信息").click();
                return;
            }

            //火车票儿童选择框
            if(ElementUtil.isExist(getDriver(),By.name("儿童(含车票)1    请编辑儿童(含车票)信息"))){
                getDriver().findElementByName("儿童(含车票)1    请编辑儿童(含车票)信息").click();
                return;
            }
            return;

        }

        if (0==personType){

            if (ElementUtil.isExist(getDriver(),By.name("儿童(免车票)1    请编辑儿童(免车票)信息"))){
                getDriver().findElementByName("儿童(免车票)1    请编辑儿童(免车票)信息").click();
                return;
            }
            return;
        }
    }

    public void agreeAllNotice() {

        //判断出游人、协议这个视图高度，根据每次滚动像素数，多滚动几次
        int count = getDriver().findElementByXPath("//XCUIElementTypeTable").getSize().getHeight() / 200 + 1;
        for (; count > 0; count--) {
            APPUtil.swipeUpPageView(getDriver());
        }

        //List<IOSElement> listCheckBtn = getDriver().findElementsByName("onlinebook enabled unchecked");
        List<IOSElement> listCheckBtn = getDriver().findElementsByXPath("//XCUIElementTypeButton[@name='onlinebook enabled unchecked'  and @visible='true']");
        for (IOSElement iosElement : listCheckBtn) {
            iosElement.click();
        }

        //当存在签证时，勾选阅读协议需要另外处理，没有name属性
        //if (ElementUtil.isExist(getDriver(), By.xpath("//XCUIElementTypeTable/XCUIElementTypeOther/XCUIElementTypeButton"))){
        //    getDriver().findElementByXPath("//XCUIElementTypeTable/XCUIElementTypeOther/XCUIElementTypeButton").click();
        //}


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
     * 目前发现的弹窗有：
     * 占位失败；
     * 含机票资源产品实际出游人类型和选择的出游人类型不同，提示儿童选择了成人票；
     * 出游人不满足年龄要求，如低于最低年龄下限或者高于上限等
     *
     * @param timeToWaitInSec wait time
     * @return 存在返回true，否则返回false
     */
    public boolean isExistAlertDialog(int timeToWaitInSec) {

        return ElementUtil.waitVisibilityOnExpectTime(getDriver(), By.xpath("//XCUIElementTypeAlert[@type='XCUIElementTypeAlert']"), timeToWaitInSec);

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


    private List<IOSElement> getCurrentPageShowSignType(){
       // return getDriver().findElementsByXPath("//XCUIElementTypeCell/XCUIElementTypeButton[@name='booksteptwo visa tip' and @visible='true']");
        return getDriver().findElementsByName("booksteptwo visa tip");
    }

    /**
     * 选择所有出游人的签证
     * @param touristNum 出游人总数
     */
    public void selectAllSignType(int touristNum){
        List<IOSElement> list=getCurrentPageShowSignType();
        for(int i=0;i<list.size();i++){
            if (list.get(i).getAttribute("visible").equals("true")){
                selectSignType(list.get(i));
            }else{
                APPUtil.moveScreenToElement(getDriver(),list.get(i),10);
                selectSignType(list.get(i));
            }

        }
        /*if (touristNum-list.size()!=0) {
            APPUtil.moveScreenToElement(getDriver(), By.xpath("//XCUIElementTypeCell/XCUIElementTypeButton[@name='booksteptwo visa tip' and @visible='false']"), 2);
            selectAllSignType(touristNum-list.size());
        }*/

        /*for (int i=0;i<touristNum;i++){
            //无法直接识别选择证件按钮，通过后面的tip提示来识别
            String xpathTmp="//XCUIElementTypeCell/XCUIElementTypeButton[@name='booksteptwo visa tip']/parent::XCUIElementTypeCell";
        }*/
    }

    public boolean isNeedSignType(){
        return ElementUtil.isExist(getDriver(), By.xpath("//XCUIElementTypeCell/XCUIElementTypeButton[@name='booksteptwo visa tip']"));
    }

    /**
     * 选择签注类型
     * @param signTypeElement 签注元素
     */
    private void selectSignType(IOSElement signTypeElement){
        //signTypeElement.click();
        //get tip point position
        Point tipPoint = signTypeElement.getCenter();
        TouchAction touchAction = new TouchAction(getDriver());
        touchAction.tap(tipPoint.getX()/2,tipPoint.getY()).perform();
        if(ElementUtil.waitVisibilityOnExpectTime(getDriver(), By.xpath("//XCUIElementTypeScrollView"), 1)){
            switch (new Random().nextInt(4)) {
                case 0:
                    getDriver().findElementByName("个人旅游(G)").click();
                    break;
                case 1:
                    getDriver().findElementByName("团队旅游(L)").click();
                    break;
                case 2:
                    getDriver().findElementByName("其他类型").click();
                    break;
                case 3:
                    getDriver().findElementById("未办理").click();
                    break;
                default:
                    break;
            }
        }
    }
}
