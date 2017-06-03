package com.demo.pageview;

import com.demo.common.ElementUtil;
import com.demo.pageinterface.NewModule;
import com.demo.pageinterface.PageLoadModule;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.By;
import sun.nio.cs.ext.EUC_CN;

/**
 * 首页
 * Created by monley_Lu on 2017/6/2.
 */
public class IndexPage extends  BasePage implements NewModule,PageLoadModule{

    public IndexPage(AndroidDriver<AndroidElement> driver) {
        super(driver);
    }


    /**
     * 点击首页顶部的搜素框
     */
    public void clickSearchNewsBtn(){
        clickElement(By.id("com.ss.android.article.news:id/ag5"));
    }

    /**
     * 获取当前页面展示的新闻条数
     * @return 新闻数目
     */
    public int getNewsCount(){
        return getDriver().findElementsById("com.ss.android.article.news:id/in").size();
    }


    public String getTitle(int index) {

        return getDriver().findElementsById("com.ss.android.article.news:id/in").get(index).findElementById("com.ss.android.article.news:id/title").getAttribute("text");
    }

    public void readNew(int index) {
        getDriver().findElementsById("com.ss.android.article.news:id/in").get(index).click();
    }

    public boolean waitPageLoadReady(int timeToWaitInSec) {

        return ElementUtil.isExist(getDriver(),By.id("com.ss.android.article.news:id/d0"),timeToWaitInSec);
    }

    public boolean leaveCurrentPage(int timeToWaitInSec) {
        return false;
    }
}
