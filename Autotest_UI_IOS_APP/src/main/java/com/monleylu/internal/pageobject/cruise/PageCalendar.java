package com.monleylu.internal.pageobject.cruise;

import com.monleylu.internal.common.ElementUtil;
import com.monleylu.internal.pageobject.BasePage;
import com.monleylu.internal.pageobject.CommonActionModule;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.openqa.selenium.By;

import java.util.List;

/**
 * 价格日历页面模型
 * Created by monley_Lu on 2017/2/23.
 */
public class PageCalendar extends BasePage implements CommonActionModule {
    public PageCalendar(IOSDriver<IOSElement> driver) {
        super(driver);
    }

    public boolean clickNextPageBtn() {
        getDriver().findElementByName("下一步 选择资源和优惠").click();
        /*TouchAction touchAction = new TouchAction(getDriver());
        Dimension dimension =getDriver().manage().window().getSize();
        int clickX=dimension.getWidth()/2;
        int clickY=dimension.getHeight()-10;
        touchAction.press(clickX,clickY).release().perform();*/
        return true;
    }

    public boolean waitPageLoadReady(int timeToWaitInSec) {

        while (ElementUtil.isExist(getDriver(), By.name("加载中..."))) {
            ElementUtil.waitInVisibilityOnExpectTime(getDriver(), By.name("加载中..."), 1);
        }
        // 下一步按钮，9.1.9版本后无法识别下一步按钮
        //return ElementUtil.waitVisibilityOnExpectTime(getDriver(), By.name("下一步:选资源和优惠"), timeToWaitInSec);
        //xpath 太复杂，以后和详情页沟通下，加个标识符吧
        //return  ElementUtil.waitVisibilityOnExpectTime(getDriver(),By.xpath("//XCUIElementTypeWindow／XCUIElementTypeOther[@visible='true']/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[3]/"),timeToWaitInSec);
        return ElementUtil.waitVisibilityOnExpectTime(getDriver(),By.xpath("//XCUIElementTypeStaticText[@name='成人']"),timeToWaitInSec);
    }

    public boolean waitLeaveCurrentPage(int timeToWaitInSec) {

        while (ElementUtil.isExist(getDriver(), By.name("加载中..."))) {
            ElementUtil.waitInVisibilityOnExpectTime(getDriver(), By.name("加载中..."), 1);
        }
        //return ElementUtil.waitInVisibilityOnExpectTime(getDriver(), By.name("下一步:选资源和优惠"), timeToWaitInSec);
        return ElementUtil.waitInVisibilityOnExpectTime(getDriver(),By.xpath("//XCUIElementTypeStaticText[@name='成人']"),timeToWaitInSec);
    }

    /**
     * select adult num
     *
     * @param adultNum adult tourist number
     * @return when done return true
     */
    public boolean selectAdult(int adultNum) {
        //default adult number
        int currentTouristNum = 2;
        int diffNum = 0;

        //由于进入这个页面默认成人数就是2，所以如果入参为2 可以直接返回
        if (currentTouristNum==adultNum) return Boolean.TRUE;


        // 成人的父元素
        String adultParentXpath="//XCUIElementTypeStaticText[@name='成人']/parent::XCUIElementTypeOther";
        IOSElement adultAreaElement = getDriver().findElementByXPath(adultParentXpath);

        currentTouristNum = Integer.parseInt(adultAreaElement.findElementByXPath("//XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeStaticText").getAttribute("value"));



        diffNum = adultNum - currentTouristNum;
        if (0 == diffNum) {
            return true;
        }

        if (diffNum > 0) {
            //plus number
            MobileElement adultPlusElement = adultAreaElement.findElementByXPath("//XCUIElementTypeOther/XCUIElementTypeButton[@name='base number picker plus']");
            for (int i = 0; i < Math.abs(diffNum); i++) {
                //getDriver().findElementsByName("base number picker plus").get(0).click();
                adultPlusElement.click();

            }
        } else {
            //minus number
            MobileElement adultMinusElement = adultAreaElement.findElementByXPath("//XCUIElementTypeOther/XCUIElementTypeButton[@name='base number picker minus']");
            for (int i = 0; i < Math.abs(diffNum); i++) {
                //getDriver().findElementsByName("base number picker minus").get(0).click();
                adultMinusElement.click();
            }
        }


        return true;
    }


    /**
     * select child number
     *
     * @param childNum children tourist number
     * @return when done retun true
     */
    public boolean selectChild(int childNum) {
        int currentTouristNum = 0;
        int diffNum = 0;

        //由于默认儿童数为零，所以当入参为零时，可以直接返回
        if (currentTouristNum == childNum) return Boolean.TRUE;

        /*if (ElementUtil.isExist(getDriver(), By.xpath("//XCUIElementTypeScrollView/XCUIElementTypeOther[3]/XCUIElementTypeOther[2]/XCUIElementTypeStaticText"))) {
            currentTouristNum = Integer.parseInt(getDriver().findElementByXPath("//XCUIElementTypeScrollView/XCUIElementTypeOther[3]/XCUIElementTypeOther[2]/XCUIElementTypeStaticText").getAttribute("value"));

        }*/
        // 儿童的父元素
        String childParentXpath="//XCUIElementTypeStaticText[@name='儿童']/parent::XCUIElementTypeOther";
        IOSElement childAreaElement = getDriver().findElementByXPath(childParentXpath);

        currentTouristNum = Integer.parseInt(childAreaElement.findElementByXPath("//XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeStaticText").getAttribute("value"));



        diffNum = childNum - currentTouristNum;
        if (0 == diffNum) {
            return true;
        }

        if (diffNum > 0) {
            //plus number
            MobileElement childPlusElement = childAreaElement.findElementByXPath("//XCUIElementTypeOther/XCUIElementTypeButton[@name='base number picker plus']");
            for (int i = 0; i < Math.abs(diffNum); i++) {
                //getDriver().findElementsByName("base number picker plus").get(1).click();
                childPlusElement.click();
            }
        } else {
            //minus number
            MobileElement childMinusElement = childAreaElement.findElementByXPath("//XCUIElementTypeOther/XCUIElementTypeButton[@name='base number picker minus']");
            for (int i = 0; i < Math.abs(diffNum); i++) {
                //getDriver().findElementsByName("base number picker minus").get(1).click();
                childMinusElement.click();
            }
        }
        return true;

    }

    /**
     * select free child number
     *
     * @param freeChildNum free child tourist number
     * @return when done return true
     */
    public boolean selectFreeChild(int freeChildNum) {
        return false;
    }

    /**
     * select tour date, actually now it only select the first valiable date
     *
     * @param tourDate the date to play
     */
    public void selectTourDate(String tourDate) {

        //由于有默认时间，直接返回，不再操作节约时间
        //if (Boolean.TRUE) return;
        //improve effcient，user can select tour date byself
        /*if (ElementUtil.isExist(getDriver(), By.xpath("//XCUIElementTypeStaticText[contains(@name,'团期')]"))) {
            return;
        }*/
        //由于有默认时间，直接返回，不再操作节约时间
        if (Boolean.TRUE) return;

        //当选择了日期后，选择出游人模块 人员类型后面会出现对应的价格，通过这个来标记是否已成功选择了出游人
        // 成人的父元素
        String adultParentXpath="//XCUIElementTypeStaticText[@name='成人']/parent::XCUIElementTypeOther";
        IOSElement adultAreaElement = getDriver().findElementByXPath(adultParentXpath);

        boolean isExistShowAdultPriceElement=ElementUtil.isExist(getDriver(),By.xpath("//XCUIElementTypeStaticText[@name='成人']/parent::XCUIElementTypeOther/XCUIElementTypeStaticText[2]"));
        //在高版本IOS 10.3.3中，没选中团期时，展示价格的元素不存在，在低版本时会存在,需要额外处理下
        MobileElement showAdultPriceElement = null;
        if (isExistShowAdultPriceElement){
            showAdultPriceElement = adultAreaElement.findElementByXPath("//XCUIElementTypeStaticText[2]");
            if (showAdultPriceElement.getAttribute("visible").equals("true")){
                return;
            }
        }



        String nameAtt = "";

        //List<MobileElement> monthElements = new ArrayList<MobileElement>();
        //main calendar view
        //IOSElement calendarTabElement = getDriver().findElementByXPath("//XCUIElementTypeTable[@type='XCUIElementTypeTable']");
        //avaiable data
        List<IOSElement> cellElements = getDriver().findElementsByXPath("//XCUIElementTypeCell[@enabled='true' and @visible='true']/XCUIElementTypeButton[@enabled='true' and @visible='true']");
        for (int i = 0; i < cellElements.size(); i++) {

            //improve effcient，user can select tour date byself
            /*if (ElementUtil.isExist(getDriver(), By.xpath("//XCUIElementTypeStaticText[contains(@name,'团期')]"))) {
                break;
            }*/

            isExistShowAdultPriceElement=ElementUtil.isExist(getDriver(),By.xpath("//XCUIElementTypeStaticText[@name='成人']/parent::XCUIElementTypeOther/XCUIElementTypeStaticText[2]"));
            if (isExistShowAdultPriceElement){
                if (null ==showAdultPriceElement){
                    showAdultPriceElement = adultAreaElement.findElementByXPath("//XCUIElementTypeStaticText[2]");
                }
                if (showAdultPriceElement.getAttribute("visible").equals("true")){
                    return;
                }
            }

            //get one avaiable date
            IOSElement currentElement = cellElements.get(i);
            //if contains name attribute ,then this line is switch date btn ,ignore
            //nameAtt = currentElement.getAttribute("name");
            currentElement.click();
            /*if (!m.getAttribute("name").equals("今天")) {
                m.click();
                return;
            }*/

        }

    }
}
