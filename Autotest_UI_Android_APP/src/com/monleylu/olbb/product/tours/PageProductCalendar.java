package com.monleylu.olbb.product.tours;

import static com.monleylu.olbb.staticvariables.StaticVars.logger;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;

import com.monleylu.olbb.common.ElementUtil;
import com.monleylu.olbb.internal.tours.BasePage;
import com.monleylu.olbb.internal.tours.CommonActionModule;

public class PageProductCalendar extends BasePage implements CommonActionModule {


	public  PageProductCalendar(AndroidDriver<AndroidElement> driver) {
		super(driver);
	}
	
	public boolean clickNextStepBtn(By by) {
		getDriver().findElement(by).click();
		return Boolean.TRUE;
		
	}

	
	/**
	 * notice:由于无法通过view识别准确日期，此处选择日期通过枚举当前页面可点击元素来选择日期
	 */
	public void selectTourDate(String tourDate) {
	    //9.1.4之后详情页改版
/*		// TODO Auto-generated method stub
		
		boolean booleanContinue = true;
		List<AndroidElement> listElements;
		List<AndroidElement> listViewElements;
		String strXpath = null;
		//判断价格日历有多少行，默认第一行是星期行，即列名称
		listElements= getDriver().findElementsByXPath("//android.widget.LinearLayout[@resource-id=\"com.monleylu.productdetail:id/layout_content\"]/android.widget.LinearLayout");
		//List<AndroidElement> listElements= driver.findElementsByXPath("//android.widget.LinearLayout[@id="layout_content"]")
		logger.debug("ListElements is ；" + listElements.size());
		for(int i=2;i<=listElements.size();i++){
			//查询每行有多少view，然后依次点击每个view，直到点击到一个有效日期
			strXpath ="//android.widget.LinearLayout[@resource-id=\"com.monleylu.productdetail:id/layout_content\"]/android.widget.LinearLayout[" + i + "]/android.view.View";
			listViewElements =getDriver().findElementsByXPath(strXpath);
			logger.debug("listViewElements " + listViewElements.size());
			for (int j = 0; j < listViewElements.size(); j++) {
				listViewElements.get(j).click();
				if(!getDriver().findElementById("com.monleylu.productdetail:id/tv_bottom_right").getAttribute("enabled").equalsIgnoreCase("false")){
					logger.debug("binggo...");
					booleanContinue= false;
					break;
				}
				logger.debug("the " + j + " view is not avaiable calendar");
				
			}
			
			if (!booleanContinue) {
				break;
			}
			
		}*/
	    
	     List<AndroidElement> avaiableDateElement= getDriver().findElementsById("com.monleylu.productdetail:id/min_price_tv");
	     int randomDate = new Random().nextInt(avaiableDateElement.size());
	     avaiableDateElement.get(randomDate).click();
	    
		
	}

	public boolean selectAdult(int adultNum) {
		int currentAdultNum=0;
		int differenceNum=0;
		String locateAdultElementString = "com.monleylu.productdetail:id/ccv_adult_number";
		//如果默认的元素不存在，那么这个页面就是火车票地接页面，更改识别元素id
		if (!ElementUtil.isExist(getDriver(),By.id(locateAdultElementString))) {
		    locateAdultElementString="com.monleylu.productdetail:id/ccv_train_adult_number";
		}
		
		//get current adult number
		currentAdultNum= Integer.parseInt( getDriver().findElement(By.id(locateAdultElementString)).findElement(By.id("com.monleylu.productdetail:id/tv_number_content")).getAttribute("text") );
		
		//System.err.println("当前成人数为：" + currentAdultNum);
		
		differenceNum=adultNum-currentAdultNum;
		
		if (0==differenceNum) {
			return true;
			
		}else if (differenceNum>0) {
			//need plus
			for (int i = 0; i < Math.abs(differenceNum); i++) {
				this.clickNextStepBtn(By.xpath("//android.widget.RelativeLayout[@resource-id='"+ locateAdultElementString +"']/android.widget.ImageView[@resource-id='com.monleylu.productdetail:id/iv_number_add']"));
			}
		}else {
			//need minus
			for (int i = 0; i < Math.abs(differenceNum); i++) {
				this.clickNextStepBtn(By.xpath("//android.widget.RelativeLayout[@resource-id='"+ locateAdultElementString +"']/android.widget.ImageView[@resource-id='com.monleylu.productdetail:id/iv_number_sub']"));
			}
		}
		
		return true;
		
		
	}

	public boolean selectChild(int childNum) {
		int currentChildNum=0;
		int differenceNum=0;
		String locateChildElementString = "com.monleylu.productdetail:id/ccv_child_number";
		//如果默认的元素不存在，那么这个页面就是火车票地接页面，更改识别元素id
		if (!ElementUtil.isExist(getDriver(),By.id(locateChildElementString))) {
		    locateChildElementString="com.monleylu.productdetail:id/ccv_train_child_number";
		}
		//get current adult number
		currentChildNum= Integer.parseInt( getDriver().findElement(By.id(locateChildElementString)).findElement(By.id("com.monleylu.productdetail:id/tv_number_content")).getAttribute("text") );
		
		//System.err.println("当前儿童数为：" + currentChildNum);
		
		differenceNum=childNum-currentChildNum;
		
		if (0==differenceNum) {
			
			return true;
			
		}else if (differenceNum>0) {
			//need plus
			for (int i = 0; i < Math.abs(differenceNum); i++) {
				this.clickNextStepBtn(By.xpath("//android.widget.RelativeLayout[@resource-id='"+ locateChildElementString +"']/android.widget.ImageView[@resource-id='com.monleylu.productdetail:id/iv_number_add']"));
			}
		}else {
			//need minus
			for (int i = 0; i < Math.abs(differenceNum); i++) {
				this.clickNextStepBtn(By.xpath("//android.widget.RelativeLayout[@resource-id='"+ locateChildElementString +"']/android.widget.ImageView[@resource-id='com.monleylu.productdetail:id/iv_number_sub']"));
			}
		}
		
		return true;
		
	}

	public boolean clickNextStepBtn() {
		
		return this.clickNextStepBtn(By.id("com.monleylu.productdetail:id/tv_bottom_right"));
		
	}

	@Override
	public boolean waitPageLoadReady(int timeToWaitInSec) {
	    
	    if (ElementUtil.isInVisibility(getDriver(), By.id("com.monleylu.app.ui:id/tv_loading"), timeToWaitInSec)) {
		//日历框架
		boolean tourDateScrollView = ElementUtil.isVisibility(getDriver(), By.id("com.monleylu.productdetail:id/calendarView"), timeToWaitInSec);
		//下一步按钮
		boolean nextPageBtnElement = ElementUtil.isVisibility(getDriver(), By.id("com.monleylu.productdetail:id/tv_bottom_right"), timeToWaitInSec);
		
		return tourDateScrollView&&nextPageBtnElement;
	    }else {
		return false;
	    }
	    
	}
	
	/**
	 * 等待点击价格日历页下一步按钮后，页面弹出加载弹框完毕
	 * @param timeToWaitInSec 等待时间
	 * @return 指定时间内加载浮层不出现返回true，如果浮层一直出现，返回false
	 */
	public boolean waitLeaveCurrentPage(int timeToWaitInSec){
	    //由于点击下一步后，后面是哪个页面无法判断，所以以离开了当前页面为标准
	    if (ElementUtil.isExist(getDriver(), By.id("com.monleylu.productdetail:id/calendarView"))) {
		 if(ElementUtil.isVisibility(this.getDriver(), By.id("com.monleylu.productdetail:id/calendarView"), timeToWaitInSec)){
			if(ElementUtil.isInVisibility(this.getDriver(),By.id("com.monleylu.productdetail:id/calendarView"), timeToWaitInSec)){
			    return true;
			}else {
			    return false;
			}
		    }else{
			return true;
		    }
	    }else{
		return false;
	    }
	   
	}

	public boolean selectFreeChild(int freeChildNum) {
		int currentChildNum=0;
		int differenceNum=0;
		String locateFreeChildElementString = "com.monleylu.productdetail:id/ccv_train_free_child_number";
		//如果默认的元素不存在，那么这个页面就是火车票地接页面，更改识别元素id
		if (!ElementUtil.isExist(getDriver(),By.id(locateFreeChildElementString))) {
		    locateFreeChildElementString="com.monleylu.productdetail:id/ccv_train_free_child_number";
		}
		//get current adult number
		currentChildNum= Integer.parseInt( getDriver().findElement(By.id(locateFreeChildElementString)).findElement(By.id("com.monleylu.productdetail:id/tv_number_content")).getAttribute("text") );
		
		//System.err.println("当前儿童数为：" + currentChildNum);
		
		differenceNum=freeChildNum-currentChildNum;
		
		if (0==differenceNum) {
			
			return true;
			
		}else if (differenceNum>0) {
			//need plus
			for (int i = 0; i < Math.abs(differenceNum); i++) {
				this.clickNextStepBtn(By.xpath("//android.widget.RelativeLayout[@resource-id='"+ locateFreeChildElementString +"']/android.widget.ImageView[@resource-id='com.monleylu.productdetail:id/iv_number_add']"));
			}
		}else {
			//need minus
			for (int i = 0; i < Math.abs(differenceNum); i++) {
				this.clickNextStepBtn(By.xpath("//android.widget.RelativeLayout[@resource-id='"+ locateFreeChildElementString +"']/android.widget.ImageView[@resource-id='com.monleylu.productdetail:id/iv_number_sub']"));
			}
		}
		
		return true;
	}



}
