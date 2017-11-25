package com.monleylu.internal.pageobject.cruise.view;

import com.monleylu.internal.pageobject.BasePage;
import com.monleylu.internal.pageobject.CruiseRoomInformation;
import com.monleylu.internal.pageobject.CruiseRoomModel;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

import java.util.List;
import java.util.Random;

/**
 *
 * Created by monley_Lu on 2017/8/3.
 */
public class CruiseRoom extends BasePage implements CruiseRoomModel{

    public CruiseRoom(IOSDriver<IOSElement> driver) {
        super(driver);
    }

    public List<IOSElement> getCurrentShowCruiseRoomElement() {
        return getDriver().findElementsByXPath("//XCUIElementTypeCell/XCUIElementTypeButton[@name='选择']/parent::XCUIElementTypeCell");
    }

    public CruiseRoomInformation getCruiseRoomInformation(IOSElement cruiseRoomElement) {
        CruiseRoomInformation cruiseRoomInformation = new CruiseRoomInformation();
        cruiseRoomInformation.setRoomName(cruiseRoomElement.findElementByXPath("//XCUIElementTypeStaticText[1]").getText().trim());
        //第三个text是可入住人数元素
        String canLiveTag = cruiseRoomElement.findElementByXPath("//XCUIElementTypeStaticText[3]").getText().trim();

        if (canLiveTag.contains("-")) {
            cruiseRoomInformation.setLowerLimit(Integer.parseInt(canLiveTag.substring(3, canLiveTag.indexOf("-"))));
            cruiseRoomInformation.setUpLimit(Integer.parseInt(canLiveTag.substring(canLiveTag.indexOf("-")+1, canLiveTag.indexOf("人"))));
        }else{
            cruiseRoomInformation.setLowerLimit(Integer.parseInt(canLiveTag.substring(3,canLiveTag.indexOf("人"))) );
            cruiseRoomInformation.setUpLimit(cruiseRoomInformation.getLowerLimit());
        }
        return cruiseRoomInformation;
    }

    /**
     * 随机选择一个邮轮房型入住
     * @return  返回选择的房型
     */
    public CruiseRoomInformation seletCruiseRoomRandom(int adultNum,int childNum){
        List<IOSElement> listCruiseRoom = getCurrentShowCruiseRoomElement();
        int randomInt=new Random().nextInt(listCruiseRoom.size());
        CruiseRoomInformation cruiseRoomInformation =getCruiseRoomInformation(listCruiseRoom.get(randomInt));
        //System.out.println("随机选择邮轮房型为："+ cruiseRoomInformation.getRoomName());
        selectCruiseRoom(listCruiseRoom.get(randomInt),adultNum,childNum);
        return cruiseRoomInformation;
    }

    public boolean selectCruiseRoom(IOSElement cruiseRoomElement, int adultNum, int childNum) {
        cruiseRoomElement.findElementByName("选择").click();
        if (adultNum==0)return  false;
        selectCruiseRoom(adultNum,childNum);
        return true;
    }


    private void selectCruiseRoom(int adultNum,int childNum){
        if (adultNum>0){
            clickAdultPlusBtn();
            adultNum--;
        }

        if (childNum>0){
            clickChildPlusBtn();
            childNum--;
        }

        if (adultNum>0 || childNum >0 ){
            selectCruiseRoom(adultNum,childNum);
        }
    }

    public int getAdultNum() {
        //由于设计原因，无法识别到成人元素框
        return 0;
    }

    public int getChildNum() {
        //由于设计原因，无法识别到儿童元素框
        return 0;
    }

    public int getRoomNum() {
        //由于设计原因，无法识别到房间元素框
        return 0;
    }

    public void clickAdultMinusBtn() {
        getDriver().findElementsByName("base number picker minus").get(0).click();

    }

    public void clickAdultPlusBtn() {
        getDriver().findElementsByName("base number picker plus").get(0).click();

    }

    public void clickChildMinusBtn() {
        getDriver().findElementsByName("base number picker minus").get(1).click();

    }

    public void clickChildPlusBtn() {
        getDriver().findElementsByName("base number picker plus").get(1).click();

    }

    public void clickRoomMinusBtn() {
        getDriver().findElementsByName("base number picker minus").get(2).click();
    }

    public void clickRoomPlusBtn() {
        getDriver().findElementsByName("base number picker plus").get(2).click();
    }

    public boolean adultMinusIconStatus() {
        return false;
    }

    public boolean adultPlusIconStatus() {
        return false;
    }

    public boolean childMinusIconStatus() {
        return false;
    }

    public boolean childPlusIconStatus() {
        return false;
    }

    public boolean roomMinusIconStatus() {
        return false;
    }

    public boolean roomPlusIconStatus() {
        return false;
    }

    public void clickCancelBtn() {
        getDriver().findElementByName("取消").click();

    }

    public void clickConfirmBtn() {
        getDriver().findElementByName("确定").click();

    }
}
