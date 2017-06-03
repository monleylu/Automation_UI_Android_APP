package com.demo.pageview;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.By;

/**
 * 搜索页
 * Created by monley_Lu on 2017/6/2.
 */
public class SearchPage extends BasePage {
    public SearchPage(AndroidDriver<AndroidElement> driver) {
        super(driver);
    }

    /**
     * 在搜索页顶部搜索框内输入想要搜索的内容
     * @param toSearch  待搜索内容
     */
    public void searchNews(String toSearch){
        getDriver().findElement(By.id("com.ss.android.article.news:id/m8")).sendKeys(toSearch);
    }

    /**
     * 点击搜索按钮
     */
    public void clickSearchBtn(){
        clickElement(By.id("com.ss.android.article.news:id/ou"));
    }

}
