package com.demo.pageview;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.By;

/**
 * base class for all page
 * Created by monley_Lu on 2017/6/2.
 */
public class BasePage {

    private AndroidDriver<AndroidElement> driver;

    public BasePage(AndroidDriver<AndroidElement> driver) {
        this.driver = driver;
    }

    public AndroidDriver<AndroidElement> getDriver() {
        return driver;
    }

    public void clickElement(By by){
        getDriver().findElement(by).click();
    }
}
