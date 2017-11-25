package com.monleylu.internal.pageobject.cruise.view;

import com.monleylu.internal.common.ElementUtil;
import com.monleylu.internal.pageobject.BasePage;
import com.monleylu.internal.pageobject.CruiseTouristModel;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.openqa.selenium.By;

/**
 * Created by monley_Lu on 2017/8/4.
 */
public class CruiseTourist extends BasePage implements CruiseTouristModel{
    public CruiseTourist(IOSDriver<IOSElement> driver) {
        super(driver);
    }

    public int getTotalCruiseRoomNum() {

        if (ElementUtil.isExist(getDriver(), By.name("选择入住人"))){
            return getDriver().findElementsByName("选择入住人").size();
        }else{
            //火车票,由于每个房间必须至少一成人入住，所以可以通过此条件判断房间数
            return  getDriver().findElementsByName("成人1 请编辑成人信息").size();
        }

    }

    public int getCheckinTouristNum(int indexTourist) {
        return 0;
    }

    public void clickSelectTouristBtn(int indexTourist) {
        if (ElementUtil.isExist(getDriver(), By.name("选择入住人"))){
            //由于每选择一个房型，选择入住人的按钮就会消失，所以这里要注意index值
            getDriver().findElementsByName("选择入住人").get(indexTourist).click();
        }else{
            //火车票,由于每个房间必须至少一成人入住，所以可以通过此条件判断房间数
            //火车票区分出游人类型，所以不能这么写
            //getDriver().findElementsByName("成人1 请编辑成人信息").get(indexTourist).click();
        }
    }
}
