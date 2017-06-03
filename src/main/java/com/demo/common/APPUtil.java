package com.demo.common;


import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

/**
 * APP的一些功能组建或者功能
 * Created by monley_Lu on 2017/6/2.
 */
public class APPUtil {

    /**
     * 切换APP底部toolbar
     * @param driver appium driver instance
     * @param nameBtn  toolbar按钮名称
     */
    public static void clickBottomToolBar(AndroidDriver<AndroidElement> driver,String nameBtn){
        //定义导航栏内容以及顺序
        String[] namesBtnArr= new String[]{"首页","视频","微头条","未登录"};
        //which btn to select
        int offset =-1;
        for (int i = 0; i < namesBtnArr.length; i++) {
            if (namesBtnArr[i].equals(nameBtn)){
                offset=i+1;
                break;
            }
        }
        if (-1==offset) return;
        driver.findElementById("android:id/tabs").findElementByXPath("//android.widget.RelativeLayout["+offset+"]").click();

    }


    /**
     * save screenshot,save jpg to directory screenshot ,name format is yyyyMMdd_HHmmss
     * @param driver  appium driver instance
     */
    public static void saveScreenShot(AndroidDriver<AndroidElement> driver){

        String currentPath = System.getProperty("user.dir"); // get current work path
        String fileToSavePathString = currentPath +"/screenshot"; //default path to save screenshot
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String fileNameofScreenShot = simpleDateFormat.format(new Date())+".jpg";
        File screenFile =  driver.getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenFile, new File(fileToSavePathString +"/" + fileNameofScreenShot));

        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    /**
     * 滑动屏幕
     * @param driver  appium instace
     * @param X  original x coordinate
     * @param Y  original y coordinate
     * @param timeToWait wait itme in millionseconds
     * @param relativeX new x coordinate
     * @param relativeY  new y coordiante
     */
    public static void swipe(AndroidDriver<AndroidElement> driver,int X,int Y,long timeToWait,int relativeX,int relativeY){
        TouchAction touchAction = new TouchAction(driver);
        touchAction.press(X,Y).waitAction(Duration.ofMillis(timeToWait)).moveTo(relativeX,relativeY).release().perform();

    }

    /**
     * 滑动手机屏幕
     * @param driver   appium driver instabce
     * @param direction direction to swipe ，
     *                  1 swipe from top    to  bottom;
     *                  2 swipe from bottom to  top;
     *                  3 swipe from right  to  left;
     *                  4 swipe from left   to  right
     */
    public static void swipeScreen(AndroidDriver<AndroidElement> driver,int direction){
        //get mobile screen size
        Dimension dimension = driver.manage().window().getSize();
        int xLen = dimension.getWidth();
        int yLen = dimension.getHeight();
        switch (direction){
            case 1:
                //APPUtil.swipe(driver,xLen/2,yLen/2,1000L,0,-200);
                APPUtil.swipe(driver,xLen/2,yLen*3/10,1000L,0,yLen*9/10);
                break;
            case 2:
                APPUtil.swipe(driver,xLen/2,yLen*9/10,1000L,0,yLen*1/10);
                break;
            case 3:
                //APPUtil.swipe(driver,xLen*9/10,yLen/2,100L,-xLen*8/10,0);
                APPUtil.swipe(driver,xLen*9/10,yLen/2,1000L,xLen*1/10,0);
                break;
            case 4:
                APPUtil.swipe(driver,xLen*1/10,yLen/2,1000L,xLen*9/10,0);
                break;
            default:

        }

    }

    public  static void swipeScreenFromTopToBottom(AndroidDriver<AndroidElement> driver){
        APPUtil.swipeScreen(driver,1);
    }

    public  static void swipeScreenFromBottomToTop(AndroidDriver<AndroidElement> driver){
        APPUtil.swipeScreen(driver,2);
    }

    public  static void swipeScreenFromRightToLeft(AndroidDriver<AndroidElement> driver){
        APPUtil.swipeScreen(driver,3);
    }

    public  static void swipeScreenFromLeftToRight(AndroidDriver<AndroidElement> driver){
        APPUtil.swipeScreen(driver,4);
    }

}
