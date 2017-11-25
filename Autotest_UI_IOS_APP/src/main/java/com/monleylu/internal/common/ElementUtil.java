package com.monleylu.internal.common;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * 页面元素相关操作
 * Created by monley_Lu on 2017/2/25.
 */
public class ElementUtil {

    /**
     * Judge whether element is exist
     *
     * @param driver  driver instance
     * @param locator mechanism used to locate element
     * @return if mechanism exist return true , if no return false
     */
    public static boolean isExist(IOSDriver<IOSElement> driver, By locator) {
        try {
            driver.findElement(locator);
            return Boolean.TRUE;
        } catch (Exception e) {
            //e.printStackTrace();
            return Boolean.FALSE;
        }
    }

    /**
     * Judge whether element is exist
     *
     * @param driver  driver instance
     * @param element mechanism used to locate element
     * @return if mechanism exist return true , if no return false
     */
    public static boolean isExist(IOSDriver<IOSElement> driver, IOSElement element) {
        WebDriverWait webDriverWait = new WebDriverWait(driver,1);
        try {
            webDriverWait.until(ExpectedConditions.visibilityOf(element));
            return Boolean.TRUE;
        } catch (Exception e) {
            //e.printStackTrace();
            return Boolean.FALSE;
        }
    }

    /**
     * wait element to visible
     *
     * @param driver          driver instance
     * @param locator         mechanism used to locate element
     * @param timeToWaitInSec time to wait
     * @return if element is located and visiable return true , if not return false
     */
    public static boolean waitVisibilityOnExpectTime(IOSDriver<IOSElement> driver, By locator, int timeToWaitInSec) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, timeToWaitInSec);
        try {
            webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return true;
        } catch (Exception e) {
            return false;
        }

    }


    /**
     * wait element attribute to be expect value
     *
     * @param driver          driver instance
     * @param locator         mechanism used to locate element
     * @param attributeName   mechanism attribute
     * @param attributeValue  value of mechanism
     * @param timeToWaitInSec time to wait in second
     * @return true when expect attribute match,otherwise return false
     */
    public static boolean waitAttributeToBe(IOSDriver<IOSElement> driver, By locator, String attributeName, String attributeValue, int timeToWaitInSec) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, timeToWaitInSec);

        try {
            webDriverWait.until(ExpectedConditions.attributeToBe(locator, attributeName, attributeValue));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * @param driver          driver instance
     * @param locator         mechanism used to locate element
     * @param timeToWaitInSec time wo wait in second
     * @return true if the element is not displayed or the element doesn't exist or stale element
     */
    public static boolean waitInVisibilityOnExpectTime(IOSDriver<IOSElement> driver, By locator, int timeToWaitInSec) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, timeToWaitInSec);

        try {
            webDriverWait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    /**
     * wait if exist alert box
     *
     * @param driver          driver instance
     * @param timeToWaitInSec time to wait in second
     * @return if exist return true ,else return false
     */
    public static boolean waitExistAlertDialogOnExpectTime(IOSDriver<IOSElement> driver, int timeToWaitInSec) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, timeToWaitInSec);
        try {
            webDriverWait.until(ExpectedConditions.alertIsPresent());
            return true;
        } catch (Exception e) {
            return false;
        }

    }

}
