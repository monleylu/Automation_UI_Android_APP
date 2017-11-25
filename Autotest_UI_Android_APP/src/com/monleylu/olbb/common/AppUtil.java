package com.monleylu.olbb.common;



import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidKeyCode;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ById;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.remote.server.handler.GetElementDisplayed;
import org.yaml.snakeyaml.introspector.BeanAccess;

import com.monleylu.olbb.internal.tours.ProductPageCode;

public class AppUtil {

    /**
     * 判断是否存在切换城市提示对话框
     * @param driver
     * @param timeToWaitInSec 等待时间
     * @return 存在返回true，否则返回false
     */
    public static boolean isVisibleSwitchCityDialog(AndroidDriver<AndroidElement> driver,int timeToWaitInSec) {
	return ElementUtil.isTextPresent(driver, By.id("android:id/alertTitle"),"切换城市提示" ,timeToWaitInSec);
	
    }
    


    /**
     * 点击取消按钮
     */
    public static void clickSwitchCityDialogCancelElement(AndroidDriver<AndroidElement> androidDriver) {
	androidDriver.findElement(By.id("android:id/button2")).click();
	 
    }
    
    /**
     * 点击确定切换按钮
     */
    public static void clickSwitchCityDialogSwitchElement(AndroidDriver<AndroidElement> androidDriver) {
	androidDriver.findElement(By.id("android:id/button1")).click();
    }
    
    /**
     * 发送返回按钮给app，让app返回上一页
     */
    public static void sendBackUpKey(AndroidDriver<AndroidElement> androidDriver) {
	androidDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
    }
    
    public static void resetAPP(AndroidDriver<AndroidElement> androidDriver) {
	androidDriver.resetApp();
    }
    
    
    /**
     * 点击APP首页下方banner的首页按钮
     */
    public static void clickMainPageBtnElement(AndroidDriver<AndroidElement> androidDriver) {
	androidDriver.findElement(By.id("com.monleylu.app.ui:id/mainpage")).click();
    }
    
    /**
     * 返回APP首页
     * @throws Exception 长时间未返回首页抛出异常
     */
    public static boolean backToMainPage(AndroidDriver<AndroidElement> androidDriver) throws Exception {
	
	int loopCount=0;
	while (ProductPageCode.PageMainFragment != AppUtil.getCurrentPage(androidDriver)) {
	    //app bug ,if send back command ,the page will fall into loop
	    if (ProductPageCode.PageAllOrders==AppUtil.getCurrentPage(androidDriver)) {
		androidDriver.findElement(By.id("com.monleylu.app.ui:id/ll_back")).click();
	    }else {
		 AppUtil.sendBackUpKey(androidDriver);
	    }
	   
	    
	    //收银台页面现在增加了一个返回确认页面，不点击确定就会阻止页面返回
	    if(ProductPageCode.PageProductCasher==AppUtil.getCurrentPage(androidDriver)){
		if (ElementUtil.isExist(androidDriver, By.id("com.monleylu.app.ui:id/sdk_dialog_text_view"))) {
		    androidDriver.findElementById("com.monleylu.app.ui:id/sdk_dialog_text_view").click();
		}
	    }
	    
	    if (loopCount++>20) {
		throw new Exception("执行二十次返回仍然未返回到首页，终止执行");
	    } 
	}
	AppUtil.clickMainPageBtnElement(androidDriver);
	return true;
	
    }


    /**
     * 判断是否首页
     * @return 是首页返回true，否则返回false
     */
    public static boolean isMainPage(AndroidDriver<AndroidElement> androidDriver,int timeToWaitInSec){
	//从目前分析来看，只要存在头部banner，即可认为已是app首页
	return isVisibleMainPageHeaderBanner(androidDriver,timeToWaitInSec);
    }
    
    /**
     * 判断APP首页最上层选择城市与搜索框banner是否可见
     * @return 可见返回true，否则返回false
     */
    public static boolean  isVisibleMainPageHeaderBanner(AndroidDriver<AndroidElement> androidDriver,int timeToWaitInSec) {
	
	return ElementUtil.isVisibility(androidDriver, By.id("com.monleylu.app.ui:id/main_page_header_normal"), timeToWaitInSec);
    }
    
    
    /**
     * 判断是否存在APP首页最下方的“首页、目的地、发现、行程玩法、我的”banner
     * @param timeToWaitInSec
     * @return 存在返回true，否则返回false
     */
    public static boolean  isVisibleMainPageBottomBanner(AndroidDriver<AndroidElement> androidDriver,int timeToWaitInSec) {
	return ElementUtil.isVisibility(androidDriver, By.id("com.monleylu.app.ui:id/mainpage_buttons"), timeToWaitInSec);
    }
    
    
    /**
     * 获取当前页面编码
     * @param androidDriver
     * @return
     */
    public static int getCurrentPage(AndroidDriver<AndroidElement> androidDriver){
	
	String currentActivityString = androidDriver.currentActivity();
	int productPageCode=ProductPageCode.PageProductUnDefine;
	switch (currentActivityString) {
	//APP首屏
	case ".homepage.MainFragmentActivity":
	    productPageCode = ProductPageCode.PageMainFragment;
	    break;
	
	//详情页
	case ".activity.GroupProductDetailActivity":  		//跟团
	case ".activity.DriveProductDetailV2Activity":  		//自驾
	    productPageCode = ProductPageCode.PageProductDetail;
	    break;

	//价格日历页
	case ".activity.BossChooseTermActivity": 		//跟团
	case ".activity.DriveV2PlanDateActivity":   	//自驾
	    productPageCode = ProductPageCode.PageProductCalendar;
	    break;
	    
	//重单页    
	case ".activity.RepeatOrdersActivity": //跟团
	    productPageCode = ProductPageCode.PageProductRepeatOrder;
	    break;
	    
	//1/2页面    
	case ".activity.GroupFillOrderOneActivity": 	//跟团
	case ".activity.GeneralDriveOrderOneActivity": 	//自驾
	case ".onlinebook.cruiseship.activity.CruiseShipStepOneActivity": //邮轮
	    productPageCode = ProductPageCode.PageProductStepOne;
	    break;
	    
	//2/2页面    
	case ".productorder..OnlineBookStepTwoActivity": 	//跟团
	case ".onlinebook.group.activity.GroupStepTwoActivity":
	case ".activity.GeneralDriveOnlineBookStepTwoActivity":	//自驾
	    productPageCode = ProductPageCode.PageProductStepTwo;
	    break;
	    
	//2/2选择出游人页面
	case ".activity.TouristsMainActivity": 				//跟团、自驾
	    productPageCode = ProductPageCode.PageProductTourist;
	    break;
	
	//签约页面    
	case ".payment.SignOrderActivity": //跟团
	    productPageCode = ProductPageCode.PageProductSign;
	    break;
	    
	//付款页面
	case "com.monleylu.paysdk.PaymentActivity":
	    productPageCode = ProductPageCode.PageProductCasher;
	    break;
	
	//自助插件化之后，除公共页面，大部分页面的activity都变成下面这样的了，需要额外处理
	case "com.monleylu.plugin.dl.DLProxyFragmentActivity":
	    if (ElementUtil.isExist(androidDriver, By.id("com.monleylu.diy:id/pager_view"))) {
		//详情页头部banner
		 productPageCode = ProductPageCode.PageProductDetail;
		 break;
	    }else if (ElementUtil.isExist(androidDriver, By.id("com.monleylu.productdetail:id/rl_product_detail_header"))) {
		//跟团详情页头部banner
		 productPageCode = ProductPageCode.PageProductDetail;
		 break;
	    }else if (ElementUtil.isExist(androidDriver, By.id("com.monleylu.diy:id/sv_plandate"))) {
		//价格日历页
		productPageCode = ProductPageCode.PageProductCalendar;
		 break;
	    }else if (ElementUtil.isTextPresent(androidDriver, By.id("com.monleylu.diy:id/tv_header_title"),"1/2选择资源", 1)) {
		productPageCode = ProductPageCode.PageProductStepOne;
		 break;
	    }else if (ElementUtil.isTextPresent(androidDriver, By.id("com.monleylu.diy:id/tv_header_title"), "2/2填写订单", 1)) {
		productPageCode = ProductPageCode.PageProductStepTwo;
		 break;
	    }else if(ElementUtil.isTextPresent(androidDriver, By.id("com.monleylu.diy:id/tv_header_title"), "预订成功", 1)){
		productPageCode = ProductPageCode.PageProductBookSuccess;
		 break;
	    }
	    break;
	
	case ".search.global.GlobalSearchResultActivity":
	case ".search.global.GlobalSearchActivity":
	    //通过搜索产品ID访问产品，但产品搜索不到时页面
	    productPageCode = ProductPageCode.PageProductSearchEmpty;
	    break;
	    
	case ".usercenter.UserCenterH5Activity":
	    productPageCode= ProductPageCode.PageAllOrders;
	    break;
	    
	default:
	    //如果都没匹配到就返回这个
	    productPageCode = ProductPageCode.PageProductUnDefine;
	    break;
	}

	//如果通过activity没有获取到当前页面，再通过标题来判断
	if (ProductPageCode.PageProductUnDefine == productPageCode){
        String pageTitle = "未识别页面";
        //目前由于改版原因，存在多种展示页面标题的方式，需要分别处理
        if(ElementUtil.isExist(androidDriver,By.xpath("//android.widget.LinearLayout[@resource-id='com.monleylu.app.ui:id/ll_middle']/android.widget.TextView"))){
            	pageTitle=androidDriver.findElementByXPath("//android.widget.LinearLayout[@resource-id='com.monleylu.app.ui:id/ll_middle']/android.widget.TextView").getAttribute("text");
          }else{
              	if (ElementUtil.isExist(androidDriver,By.id("com.monleylu.app.ui:id/tv_header_title"))) {
          	//预订流程title
          	pageTitle = androidDriver.findElement(By.id("com.monleylu.app.ui:id/tv_header_title")).getAttribute("text");
  	    	}else{
  	    	    	if (ElementUtil.isExist(androidDriver,By.id("com.monleylu.app.ui:id/v_header_text"))) {
  			pageTitle = androidDriver.findElement(By.id("com.monleylu.app.ui:id/v_header_text")).getAttribute("text");
  		    	}else {
      		    		if (ElementUtil.isExist(androidDriver,By.id("com.monleylu.app.ui:id/sdk_tv_header_title"))) {
      				//收银台title
      				pageTitle = androidDriver.findElement(By.id("com.monleylu.app.ui:id/sdk_tv_header_title")).getAttribute("text");
      			    } 
			}
  	    	}  
          } 

        switch (pageTitle){
            case "1/2选择资源":
        	productPageCode=ProductPageCode.PageProductStepOne;
        	break;
            case "2/2填写订单": 
            case "2/2 填写订单":
                productPageCode=ProductPageCode.PageProductStepTwo;
                break;
            case "产品预订":
        	productPageCode=ProductPageCode.PageFastOrder;
        	break;
            case "选择常用游客":
        	productPageCode=ProductPageCode.PageProductTourist;
        	break;
            case "预订成功":
        	productPageCode=ProductPageCode.PageProductBookSuccess;
        	break;
            case "预订提示":
        	productPageCode=ProductPageCode.PageProductRepeatOrder;
        	break;
            case "收银台":
        	productPageCode=ProductPageCode.PageProductCasher;
        	break;
            case "预订失败":
        	productPageCode=ProductPageCode.PageProductBookFail;
        	break;
        	
        	
        }

    }

	
	return productPageCode;
    }
    
    /**
     * 将APP页面上下滑动，当yCoordinateHeight为正整数，APP页面往上滚动，当yCoordinateHeight为负数，APP页面往下滚动
     * @param androidDriver 驱动实例
     * @param yCoordinateHeight 纵轴滚动的像素差
     */
    public static void  swipeUpDown(AndroidDriver<AndroidElement> androidDriver,int yHeight){
	Dimension dimension =  androidDriver.manage().window().getSize();
	TouchAction touchAction = new TouchAction(androidDriver);
	touchAction.press(dimension.getWidth()/2, dimension.getHeight()/2).waitAction(2000).moveTo(dimension.getWidth()/2, dimension.getHeight()/2 + yHeight).release().perform();
	
    }
    
    /**
     * 向下滚动页面，直到元素可见
     * @param androidDriver appium驱动实例
     * @param by 元素定位标识
     * @param loopCount 滚动次数
     * @return 滚动到元素可见返回true，或者滚动了指定次数仍然不可见返回false
     */
    public static boolean moveScreenToElement(AndroidDriver<AndroidElement> androidDriver,By by,int loopCount){
	
	AppUtil.swipeUpDown(androidDriver, -300);
	
	if (ElementUtil.isExist(androidDriver, by,1)) {
	    return Boolean.TRUE;
	}
	
	if (loopCount<0) {
	    return Boolean.FALSE;
	}
	return moveScreenToElement(androidDriver,by, --loopCount);
    }
    
    
}





















