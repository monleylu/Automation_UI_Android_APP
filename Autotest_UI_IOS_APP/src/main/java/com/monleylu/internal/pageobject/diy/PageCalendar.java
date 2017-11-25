package com.monleylu.internal.pageobject.diy;

import com.monleylu.internal.common.ElementUtil;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;

import java.util.List;

/**
 * Created by monley_Lu on 2017/3/14.
 */
public class PageCalendar extends com.monleylu.internal.pageobject.tours.PageCalendar {
    public PageCalendar(IOSDriver<IOSElement> driver) {
        super(driver);
    }

    @Override
    public boolean waitPageLoadReady(int timeToWaitInSec) {
        while (ElementUtil.isExist(getDriver(), By.name("加载中..."))) {
            ElementUtil.waitInVisibilityOnExpectTime(getDriver(), By.name("加载中..."), 1);
        }
        //return ElementUtil.waitVisibilityOnExpectTime(getDriver(), By.name("确定"), timeToWaitInSec);
        return ElementUtil.waitVisibilityOnExpectTime(getDriver(),By.xpath("//XCUIElementTypeStaticText[@name='成人']"),timeToWaitInSec);
    }




    /**
     * select tour date, actually now it onley select the first valiable date
     *
     * @param tourDate the date to play
     */
    public void selectTourDate(String tourDate) {

        //由于有默认时间，直接返回，不再操作节约时间
        if (Boolean.TRUE) return;

        String nameAtt = "";

        //List<MobileElement> monthElements = new ArrayList<MobileElement>();
        //main calendar view
        IOSElement calendarTabElement = getDriver().findElementByXPath("//XCUIElementTypeTable[@type='XCUIElementTypeTable']");
        //each  row
        List<MobileElement> cellElements = calendarTabElement.findElementsByXPath("//XCUIElementTypeCell[@type='XCUIElementTypeCell']");
        for (int i = 0; i < cellElements.size(); i++) {
            //get all calendar row
            MobileElement currentElement = cellElements.get(i);
            //if contains name attribute ,then this line is switch date btn ,ignore
            nameAtt = currentElement.getAttribute("name");
            if ((nameAtt != null)) {
                // monthElements.add(currentElement);
                continue;
            }
            List<MobileElement> tmpDateRow = currentElement.findElementsByXPath("//XCUIElementTypeButton");
            for (MobileElement m : tmpDateRow
                    ) {
                if (m.getAttribute("enabled").equals("true")) {
                    if (!m.getAttribute("name").equals("今天")) {
                        m.click();
                        return;
                    }
                }

            }

        }

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
            MobileElement adultPlusElement = adultAreaElement.findElementByXPath("//XCUIElementTypeOther/XCUIElementTypeButton[@name='planDate plus enable']");
            for (int i = 0; i < Math.abs(diffNum); i++) {
                //getDriver().findElementsByName("base number picker plus").get(0).click();
                adultPlusElement.click();

            }
        } else {
            //minus number
            MobileElement adultMinusElement = adultAreaElement.findElementByXPath("//XCUIElementTypeOther/XCUIElementTypeButton[@name='planDate minus enable']");
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
            MobileElement childPlusElement = childAreaElement.findElementByXPath("//XCUIElementTypeOther/XCUIElementTypeButton[@name='planDate plus enable']");
            for (int i = 0; i < Math.abs(diffNum); i++) {
                //getDriver().findElementsByName("base number picker plus").get(1).click();
                childPlusElement.click();
            }
        } else {
            //minus number
            MobileElement childMinusElement = childAreaElement.findElementByXPath("//XCUIElementTypeOther/XCUIElementTypeButton[@name='planDate minus enable']");
            for (int i = 0; i < Math.abs(diffNum); i++) {
                //getDriver().findElementsByName("base number picker minus").get(1).click();
                childMinusElement.click();
            }
        }
        return true;

    }
    @Override
    public boolean clickNextPageBtn() {
        //价格日历页底下的确定按钮无法识别，通过xpath也很难识别
        //getDriver().findElementByName("确定").click();
        TouchAction touchAction = new TouchAction(getDriver());
        Dimension dimension =getDriver().manage().window().getSize();
        int clickX=dimension.getWidth()/2;
        int clickY=dimension.getHeight()-10;
        touchAction.tap(clickX,clickY).release().perform();
        return true;
    }
}
