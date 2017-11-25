package com.monleylu.internal.pageobject.tours;

import com.monleylu.internal.common.APPUtil;
import com.monleylu.internal.common.ElementUtil;
import com.monleylu.internal.pageobject.BasePage;
import com.monleylu.internal.pageobject.CommonActionModule;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by monley_Lu on 2017/2/25.
 */
public class PageTourists extends BasePage implements CommonActionModule {

    //获取每页能展示多少个出游人
    int touristCountPerPage = 0;
    //第一个出游人位置
    Point firstTouristPoint = null;
    //页面上可见的最后一个出游人位置
    Point lastTouristPoint = null;

    //已选择了的人员
    public List<String> selectedTourists =new ArrayList<String>();

    public PageTourists(IOSDriver<IOSElement> driver) {
        super(driver);
    }

    public boolean clickNextPageBtn() {
        getDriver().findElementByName("确定").click();
        return true;
    }

    public boolean waitPageLoadReady(int timeToWaitInSec) {
        //deal ui fresh problem
        if (ElementUtil.isExist(getDriver(), By.name("加载中..."))) {
            ElementUtil.waitInVisibilityOnExpectTime(getDriver(), By.name("加载中..."), timeToWaitInSec);
        }
        return ElementUtil.waitVisibilityOnExpectTime(getDriver(), By.xpath("//XCUIElementTypeTable[@type='XCUIElementTypeTable']"), timeToWaitInSec);
    }

    public boolean waitLeaveCurrentPage(int timeToWaitInSec) {
        return ElementUtil.waitInVisibilityOnExpectTime(getDriver(), By.name("选择出游人"), timeToWaitInSec);
    }


    /**
     * select tourist to play
     *
     * @param touristNumToSelect the number of tourists to play
     * @return true when selected
     */
    public boolean selectTourist(int touristNumToSelect) {

        //total tourist number
        int sumTourists = 0;
        //already selected count
        int selectedCount = 0;
        //获取每页能展示多少个出游人
        int touristCountPerPage = 0;


        int offset = 0;
        //新版本的appium中，通过xpath获取的元素，每次样式改变，都需要重新获取页面xpath，否则会报找不到元素的错误
        List<IOSElement> listTourists = getDriver().findElementsByXPath("//XCUIElementTypeCell[@type='XCUIElementTypeCell']");
        sumTourists = listTourists.size();
        //calculate tourist view size
        //first visible tourist position
        Point firstTouristPoint = listTourists.get(0).getCenter();
        Point lastTouristPoint = null;
        //judge the last visible tourist
        for (int i = 1; i < sumTourists; i++) {
            if (listTourists.get(i).getAttribute("visible").equals("false")) {
                //最后一个可见的tourist位置
                lastTouristPoint = listTourists.get(i - 1).getCenter();
                touristCountPerPage = i;
                break;
            }
        }

        //通过name查找元素可避免每次都刷新xpath问题
        List<IOSElement> listTouristsTwo = getDriver().findElementsByName("icon uncheck tourist");

        for (int i = 0; i < sumTourists; i++) {
            //get tourist view
            //IOSElement touristElement = listTourists.get(i);
            IOSElement touristElement=listTouristsTwo.get(i);
            offset++;
            try {
                //fix element already check bug
                //touristElement.findElementByName("icon uncheck tourist").click();
                touristElement.click();
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (ElementUtil.isExist(getDriver(), By.xpath("//XCUIElementTypeAlert[@type='XCUIElementTypeAlert']"))) {
                getDriver().findElementByName("取消").click();
            }

            //get already select tourist count
            selectedCount = getDriver().findElementsByName("icon check tourist").size();
            if (selectedCount == touristNumToSelect) break;

            //whether need swipe page
            if (offset % (touristCountPerPage - 1) == 0) {
                APPUtil.swipe(getDriver(), lastTouristPoint.getX(), lastTouristPoint.getY(), 0, firstTouristPoint.getY() - lastTouristPoint.getY(), 5000);
                offset = 0;//清零
            }
        }


        return true;
    }


    /**
     * 选择成人
     * @param touristNumToSelect
     * @return
     */
    public boolean selectAdultTourist(int touristNumToSelect){
        return selectTourist(touristNumToSelect,2);
    }

    /**
     * 选择儿童
     * @param touristNumToSelect
     * @return
     */
    public boolean selectChildTourist(int touristNumToSelect){
        return  selectTourist(touristNumToSelect,1);
    }

    /**
     * 选择免票儿童
     * @param touristNumToSelect
     * @return
     */
    public boolean selectFreeChildTourist(int touristNumToSelect){
        return selectTourist(touristNumToSelect,0);
    }

    /**
     * 选择出游人
     * @param touristNumToSelect  出游人数
     * @param personType  选择出游人类型  2:adult;1:child；0：freechild
     * @return
     */
    public boolean selectTourist(int touristNumToSelect,int personType){

        //total tourist number
        int sumTourists = 0;
        //all tourist list
        List<IOSElement> listTourists = null;

        //already selected count
        int selectedCount = 0;

        int offset = 0;
        if (0==sumTourists){
            listTourists= getDriver().findElementsByXPath("//XCUIElementTypeTable/XCUIElementTypeCell[@type='XCUIElementTypeCell']");
            sumTourists = listTourists.size();
        }

/*
        for (IOSElement aa:listTourists
             ) {
            String as= aa.findElementByXPath("//XCUIElementTypeStaticText").getText();
            System.out.println(as);

        }
*/

        //calculate tourist view size
        //first visible tourist position
        if (firstTouristPoint==null){
            firstTouristPoint = listTourists.get(0).getCenter();
        }

        //last visible tourist position
        /*if (lastTouristPoint == null){
            //judge the last visible tourist,checkbtn is a little large then editbtn,so use editbtn to check
            List<IOSElement> visibleListTourists =getDriver().findElementsByXPath("//XCUIElementTypeTable/XCUIElementTypeCell[@type='XCUIElementTypeCell' and @visible='true']/XCUIElementTypeButton[@name='icon online edit' and @visible='true']");
            touristCountPerPage = visibleListTourists.size();
            lastTouristPoint =visibleListTourists.get(touristCountPerPage-1).getCenter();
        }*/


        for (int i = 1; i < sumTourists; i++) {
            if (listTourists.get(i).getAttribute("visible").equals("false")) {

                //最后一个可见的tourist位置
                lastTouristPoint = listTourists.get(i - 1).getCenter();
                touristCountPerPage = i;
                break;
            }
        }

        for (int i = 0; i < sumTourists; i++) {

           //get tourist view
            IOSElement touristElement = listTourists.get(i);
            offset++;


            //strage error
            //int j=i+1;
            //String xpathTmp ="//XCUIElementTypeTable/XCUIElementTypeCell["+ j +"]/XCUIElementTypeStaticText]";
            //String nameTmp = getDriver().findElementByXPath(xpathTmp).getText();
            String nameTmp = touristElement.findElementByXPath("//XCUIElementTypeStaticText").getText();
            //String nameTmp=String.valueOf(System.currentTimeMillis());
            //选择儿童／免票儿童时，以名字里是否包含儿童为判断是否儿童的标识
            if (!repeatTourist(nameTmp)){
                boolean selectAdultCondition=(2==personType);
                boolean selectChildCondition=((1==personType || 0==personType) && nameTmp.contains("儿童"));
                try {

                    if (selectAdultCondition || selectChildCondition) {
                        //fix element already check bug
                        touristElement.findElementByName("icon uncheck tourist").click();
                        if (ElementUtil.isExist(getDriver(), By.xpath("//XCUIElementTypeAlert[@type='XCUIElementTypeAlert']"))) {
                            getDriver().findElementByName("取消").click();
                        } else {
                            selectedTourists.add(nameTmp);

                            //当页面改变后，需要刷新
                            listTourists= getDriver().findElementsByXPath("//XCUIElementTypeTable/XCUIElementTypeCell[@type='XCUIElementTypeCell']");
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }


            }



            //get already select tourist count
            selectedCount = getDriver().findElementsByName("icon check tourist").size();
            if (selectedCount == touristNumToSelect) break;

            //whether need swipe page
            if (offset % (touristCountPerPage - 1) == 0) {
                APPUtil.swipe(getDriver(), lastTouristPoint.getX(), lastTouristPoint.getY(), 0, firstTouristPoint.getY() - lastTouristPoint.getY(), 5000);
                offset = 0;//清零
            }
        }


        return  true;

    }

    /**
     * 判断用户是否已选择过
     * @param nameToComp
     * @return
     */
    private  boolean repeatTourist(String nameToComp){
        for (String name:selectedTourists
             ) {
            if (nameToComp.equals(name)){
                return  Boolean.TRUE;
            }
        }
        return  Boolean.FALSE;
    }

    /**
     * 清空已选择出游人缓存
     */
    public void  clearSelectedTourists(){
        selectedTourists.clear();
    }

}
