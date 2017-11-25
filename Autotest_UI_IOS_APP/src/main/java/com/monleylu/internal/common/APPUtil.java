package com.monleylu.internal.common;

import com.monleylu.internal.pageobject.PageCode;
import io.appium.java_client.TouchAction;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.ios.IOSTouchAction;
import org.aspectj.util.FileUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by monley_Lu on 2017/2/25.
 */
public class APPUtil {


    /**
     * get current page code
     * this function does not work efficient,but getPageSoure function has a bug,just wait to fix
     *
     * @param driver
     * @return return which page it is
     */
    public static int getCurrentPage(IOSDriver<IOSElement> driver) {
        int currentPageCode = PageCode.PageProductUnDefine;

        /** Index page **/
        //这里仅判断是否存在底部导航栏，如果出现导航栏，默认就到了主页，如果要切换不同tab，需要而外处理
        //if exist search box and bottom tabbar
        if (ElementUtil.isExist(driver, By.xpath("//XCUIElementTypeButton[@name='首页']")) && ElementUtil.isExist(driver, By.xpath("//XCUIElementTypeButton[@name='我的']"))) {
            return PageCode.PageMainFragment;
        }

        //当存在大促／会员日时，UI样式会调整，导致失败出现问题
        if (ElementUtil.isExist(driver, By.name("目的地/关键词")) && ElementUtil.isExist(driver, By.xpath("//XCUIElementTypeTabBar"))) {
            return PageCode.PageMainFragment;
        }

        /** search page **/
        //not search product page
        if (ElementUtil.isExist(driver, By.id("icon_homeSearch")) && ElementUtil.isExist(driver, By.name("重新搜索"))) {
            return PageCode.PageProductSearchEmpty;
        }

        //if exist search product text field
        if (ElementUtil.isExist(driver, By.id("icon_homeSearch"))) {
            return PageCode.PageSearchProduct;
        }

        /** Product detail Page**/
        if (ElementUtil.isExist(driver, By.name("立即预订"))) {
            return PageCode.PageProductDetail;
        }
        //driver
        if (ElementUtil.isExist(driver, By.name("产品详情"))) {
            return PageCode.PageProductDetail;
        }

        /** Product Calendar Page**/
        if (ElementUtil.isExist(driver, By.name("下一步:选资源和优惠"))) {
            return PageCode.PageProductCalendar;
        }

        //driver
        if (ElementUtil.isExist(driver, By.name("出游日期和人数"))) {
            return PageCode.PageProductCalendar;
        }

        /**repeat page**/
        if (ElementUtil.isExist(driver, By.name("预订提示"))) {
            return PageCode.PageProductRepeatOrder;
        }

        /**  Page Step One **/
        if (ElementUtil.isExist(driver, By.name("1/2选择资源"))) {
            return PageCode.PageProductStepOne;
        }

        /** Page Step Two **/
        if (ElementUtil.isExist(driver, By.name("2/2填写订单"))) {
            return PageCode.PageProductStepTwo;
        }

        /** Page Select Tourist Page **/
        if (ElementUtil.isExist(driver, By.name("选择出游人"))) {
            return PageCode.PageProductTourist;
        }

        if (ElementUtil.isExist(driver, By.name("预订成功"))) {
            return PageCode.PageProductBookSuccess;
        }

        /**Page pay money **/
        if (ElementUtil.isExist(driver, By.name("收银台"))) {
            return PageCode.PageProductCasher;
        }

        /** Page all orders **/
        if (ElementUtil.isExist(driver, By.name("全部订单"))) {
            return PageCode.PageAllOrders;
        }


        return currentPageCode;
    }

    public static int getCurrentPageNew(IOSDriver<IOSElement> driver) {
        int currentPageCode = PageCode.PageProductUnDefine;
        //still a bug cannot fix
        //https://github.com/appium/appium/issues/7441
        //
        String pageSoure = driver.getPageSource();

        //APP首页
        if (pageSoure.contains("首页") && pageSoure.contains("我的")){
            return  PageCode.PageMainFragment;
        }

        //产品搜索页
        if (pageSoure.contains("name=\"取消\"")){
            return PageCode.PageSearchProduct;
        }

        //产品详情页
        if (pageSoure.contains("name=\"立即预订\"")){
            return PageCode.PageProductDetail;
        }

        //价格日历
        if (pageSoure.contains("planDate_minus_enable") && pageSoure.contains("planDate_plus_enable")){
            return PageCode.PageProductCalendar;
        }

        //预定提示  重单页面
        if (pageSoure.contains("name=\"预订提示\"")){
            return PageCode.PageProductRepeatOrder;
        }

        //1／2
        if (pageSoure.contains("name=\"1/2选择资源\"")){
            return  PageCode.PageProductStepOne;
        }

        //2/2
        if (pageSoure.contains("name=\"2/2填写订单\"")){
            return  PageCode.PageProductStepTwo;
        }

        //选择出游人
        if (pageSoure.contains("name=\"选择出游人\"")){
            return PageCode.PageProductTourist;
        }

        //预定成功
        if (pageSoure.contains("name=\"预订成功\"")){
            return PageCode.PageProductBookSuccess;
        }

        //预定失败
        if (pageSoure.contains("name=\"预订失败\"")){
            return PageCode.PageProductBookFail;
        }

        //首页台
        if (pageSoure.contains("name=\"收银台\"")){
            return PageCode.PageProductCasher;
        }

        //全部订单
        if (pageSoure.contains("name=\"全部订单\"")){
            return PageCode.PageAllOrders;
        }


        return currentPageCode;
    }

    /**
     * swip window
     *
     * @param driver       driver instance
     * @param startx       start x-coordinate
     * @param starty       start y-coordinate
     * @param endxRelative relative end x-coordinate
     * @param endyRelative relative end y-coordinate
     */
    public static void swipe(IOSDriver<IOSElement> driver, int startx, int starty, int endxRelative, int endyRelative) {

        IOSTouchAction touchAction = new IOSTouchAction(driver);
        //should wait more long time,or swipe fail
        touchAction.press(startx, starty).waitAction(100).moveTo(endxRelative, endyRelative).release().perform();

    }

    /**
     * swip window
     *
     * @param driver         driver instance
     * @param startx         start x-coordinate
     * @param starty         start y-coordinate
     * @param endxRelative   relative end x-coordinate
     * @param endyRelative   relative end y-coordinate
     * @param timeToWaitInMs time to wait in ms
     */
    public static void swipe(IOSDriver<IOSElement> driver, int startx, int starty, int endxRelative, int endyRelative, int timeToWaitInMs) {

        IOSTouchAction touchAction = new IOSTouchAction(driver);
        //should wait more long time,or swipe fail
        touchAction.press(startx, starty).waitAction(timeToWaitInMs).moveTo(endxRelative, endyRelative).release().perform();

    }

    /**
     * swipe down page view,about 200 pixel
     *
     * @param driver
     */
    public static void swipeDownPageView(IOSDriver<IOSElement> driver) {

        Dimension screenDimension = driver.manage().window().getSize();
        int startx = screenDimension.getWidth() / 2;
        int starty = screenDimension.getHeight() / 2;
        int swipePix = 100;
        APPUtil.swipe(driver, startx, starty, 0, swipePix);

    }

    /**
     * 将APP页面上下滑动，当yCoordinateHeight为正整数，APP页面往上滚动，当yCoordinateHeight为负数，APP页面往下滚动
     * @param driver 驱动实例
     * @param yHeight 纵轴滚动的像素差
     */
    public static void  swipeDownPageView(IOSDriver<IOSElement> driver,int yHeight){
        Dimension dimension =  driver.manage().window().getSize();
        TouchAction touchAction = new TouchAction(driver);
        touchAction.press(dimension.getWidth()/2, dimension.getHeight()/2).waitAction(2000).moveTo(0,  yHeight).release().perform();

    }

    /**
     * swipe up page view ,about 200 pixel
     *
     * @param driver
     */
    public static void swipeUpPageView(IOSDriver<IOSElement> driver) {
        Dimension screenDimension = driver.manage().window().getSize();
        int startx = screenDimension.getWidth() / 2;
        int starty = screenDimension.getHeight() / 2;
        int swipePix = -200;
        APPUtil.swipe(driver, startx, starty, 0, swipePix);
    }

    /**
     * swipe to left page view ,swipe from left to right
     * @param driver
     */
    public static void swipeRightPageView(IOSDriver<IOSElement> driver){
        Dimension screenDimension = driver.manage().window().getSize();
        int startx = 0;
        int starty = screenDimension.getHeight() / 2;
        int swipePix = screenDimension.getWidth();
        APPUtil.swipe(driver, startx, starty, swipePix, 0);
    }

    /**
     * 返回APP默认首页
     *
     * @param driver
     * @return
     */
    public static boolean backToMianPage(IOSDriver<IOSElement> driver) throws Exception {
        int loopCount = 0;
        //int currentPageCode = APPUtil.getCurrentPage(driver);
        int currentPageCode = APPUtil.getCurrentPageNew(driver);

        while (PageCode.PageMainFragment != currentPageCode) {

  /*          switch (currentPageCode) {

                case PageCode.PageSearchProduct:
                    driver.findElementByName("取消").click();
                    break;
                case PageCode.PageProductSearchEmpty:
                    driver.findElementByName("base topbar icon nav back").click();
                    break;
                case PageCode.PageProductDetail:
                    if (ElementUtil.isExist(driver, By.name("packageBack black"))) {
                        driver.findElementByName("packageBack black").click();
                    } else {
                        //自助
                        driver.findElementByName("packageBack green").click();
                    }

                    break;
                case PageCode.PageProductCalendar:
                    driver.findElementByName("base topbar icon nav back").click();
                    break;
                case PageCode.PageProductRepeatOrder:
                    driver.findElementByName("base topbar icon nav back").click();
                    break;
                case PageCode.PageProductStepOne:
                    driver.findElementByName("base topbar icon nav back").click();
                    break;
                case PageCode.PageProductStepTwo:
                    driver.findElementByName("base topbar icon nav back").click();
                    break;
                case PageCode.PageProductTourist:
                    driver.findElementByName("base topbar icon nav back").click();
                    break;
                case PageCode.PageProductCasher:
                    driver.findElementByName("tfpay backImage icon").click();
                    //deal confirm dialog
                    driver.findElementByName("确认").click();
                    break;
                case PageCode.PageAllOrders:
                    driver.findElementByName("p back green").click();
                    //  driver.findElementByXPath("/XCUIElementTypeApplication/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeButton").click();
                    break;

                default:
                    if (ElementUtil.isExist(driver, By.name("base topbar icon nav back"))) {
                        driver.findElementByName("base topbar icon nav back").click();
                    }
                    //部分页面返回箭头改版，未来会统一样式
                    if (ElementUtil.isExist(driver, By.name("p back green"))) {
                        driver.findElementByName("p back green").click();
                    }

            }
*/

            //收银台需要特别处理下，不支持右滑返回上一页,其他页面仍然正常处理
            if(PageCode.PageProductCasher==currentPageCode){
                //由于通过页面源码判断页面状态，速度很快，存在收银台还没跳到上一个页面就再次获取页面状态，需要额外处理下
                if (ElementUtil.waitVisibilityOnExpectTime(driver,By.name("tfpay backImage icon"),1)){
                    driver.findElementByName("tfpay backImage icon").click();
                    //deal confirm dialog
                    driver.findElementByName("确认").click();
                }

            }else {
                APPUtil.swipeRightPageView(driver);
            }


            if (loopCount > 20) {
                throw new Exception("执行20次返回仍然未返回到首页");
            }
            currentPageCode = APPUtil.getCurrentPageNew(driver);
            loopCount++;
        }

        //main page
        if (ElementUtil.isExist(driver, By.xpath("//XCUIElementTypeButton[@name='首页']"))) {
            driver.findElementByXPath("//XCUIElementTypeButton[@name='首页']").click();
        } else {
            //处理大促、会员日等节日时，样式调整导致无法失败问题
            driver.findElementByXPath("//XCUIElementTypeTabBar/XCUIElementTypeOther[2]/XCUIElementTypeButton").click();
        }


        return Boolean.TRUE;
    }

    /**
     * 截图
     *
     * @param driver 驱动实例
     */
    public static void getScreenShot(IOSDriver<IOSElement> driver) {
        String currentPath = System.getProperty("user.dir"); // get current work path
        String fileToSavePathString = currentPath + "/screenshot"; //default path to save screenshot
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String fileNameofScreenShot = simpleDateFormat.format(new Date()) + ".jpg";
        File screenFile = driver.getScreenshotAs(OutputType.FILE);

        try {
            FileUtil.copyFile(screenFile, new File(fileToSavePathString + System.getProperty("file.separator") + fileNameofScreenShot));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 向下滚动页面，直到元素可见
     * @param driver appium驱动实例
     * @param by 元素定位标识
     * @param loopCount 滚动次数
     * @return 滚动到元素可见返回true，或者滚动了指定次数仍然不可见返回false
     */
    public static boolean moveScreenToElement(IOSDriver<IOSElement> driver,By by,int loopCount){

        APPUtil.swipeDownPageView(driver, -300);

        if (ElementUtil.isExist(driver, by)) {
            return Boolean.TRUE;
        }

        if (loopCount<0) {
            return Boolean.FALSE;
        }
        return moveScreenToElement(driver,by, --loopCount);
    }


    /**
     * 向下滚动页面，直到元素可见
     * @param driver appium驱动实例
     * @param element 元素定位标识
     * @param loopCount 滚动次数
     * @return 滚动到元素可见返回true，或者滚动了指定次数仍然不可见返回false
     */
    public static boolean moveScreenToElement(IOSDriver<IOSElement> driver,IOSElement element,int loopCount){

        APPUtil.swipeDownPageView(driver, -300);

        if (ElementUtil.isExist(driver, element)) {
            return Boolean.TRUE;
        }

        if (loopCount<0) {
            return Boolean.FALSE;
        }
        return moveScreenToElement(driver,element, --loopCount);
    }
}
