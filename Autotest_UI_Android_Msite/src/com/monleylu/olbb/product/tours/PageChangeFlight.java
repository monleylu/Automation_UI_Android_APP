/**
*Copyright (c) 2006-2016 . All Rights Reserved.
*/
package com.monleylu.olbb.product.tours;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import static com.monleylu.olbb.logback.LogBack.logger;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;

import com.monleylu.olbb.core.ElementUtil;
import com.monleylu.olbb.core.PageUtil;
import com.monleylu.olbb.inter.product.BasePage;
import com.monleylu.olbb.inter.product.CommonActionModule;

public class PageChangeFlight extends BasePage implements CommonActionModule,FlightListModule{

    public PageChangeFlight(AndroidDriver<AndroidElement> androidDriver) {
	super(androidDriver);
	// TODO Auto-generated constructor stub
    }

    @Override
    public boolean waitPageLoadReady(int timeToWaitInSec) {
	
    	PageUtil.waitPageDomLoadReady(this.getAndroidDriver());
		return ElementUtil.waitAttributeToBeOnExpectTime(getAndroidDriver(), By.id("loading"), "style", "display: none;", timeToWaitInSec);
    }

    @Override
    public boolean waitLeaveCurrentPage(int timeToWaitInSec) {
	// TODO Auto-generated method stub
	return false;
    }
    
    @Override
	public boolean clickNextStepBtn() {
		// TODO Auto-generated method stub
		return false;
	}

	//1/2获取机票乘数
	@Override
	public int getFlightNum(){
		List<AndroidElement> allFilghtAndroidElement = this.getAndroidDriver().findElements(By.xpath("//div[@class='flights']//div[@class='flight-light']"));
		return allFilghtAndroidElement.size();
		
	}
	
	@Override
    public void clickChangeFlightsBtn() {
	//点击更改机票弹框
		this.clickElement(this.find(By.xpath("//div[@id='flightBox']//div[@class='box-header']/a")));
    }
	
	@Override
	public void clickFlightNotice(){   //点击第一个机票的预定须知，检查并关闭页面
		this.clickElement(this.find(By.xpath("//div[@class='container']//div[1]//div[@class='flight-notice']//div[@class='notice']")));
		this.clickElement(this.find(By.xpath("//div[@class='modal-footer']/button")));
	}
	
	public int creRangeRanNum(int range){    //生成指定范围内的随机数，筛选机票;选择机票
		Random r = new Random();
		int i = r.nextInt(range)+1;
		return i;
	}
	
	@Override
	public void siftingFlight(){   
		//1-起飞时间段 2-达到时段  3-舱位等级  4-航空公司 5-起飞机场  6-到达机场   随机一种方式筛选机票
		int siftingType = creRangeRanNum(6);
		//点击筛选按钮
		this.clickElement(this.find(By.xpath("//div[@class='filter-container']//*[@class='filter-tabs']/li[1]/span/i")));
		//String jsString = "$('.filter-tabs .checked span').click()";
	    //this.getAndroidDriver().executeScript(jsString);
		//点击筛选项
		this.clickElement(this.find(By.xpath("//div[@class='filter-flat']//div[@class='filter-body']/div[" + siftingType +"]/div[2]/span[1]")));
		//选择完筛选条件后，点击确定按钮
		this.clickElement(this.find(By.xpath("//*[@class='button confirm']")));
	}
	
	@Override
	public void sortFlight(){   
		//1-直飞 2-低价 3-耗时短 4-出发 早到晚 5-出发 晚到早 6-达到 早到晚 7-达到 晚到早  随机一种排序方式排序机票列表
		int sortType = creRangeRanNum(7);
		//点击排序按钮
		this.clickElement(this.find(By.xpath("//div[@class='filter-container']//*[@class='filter-tabs']/li[2]/span/i")));
		//点击排序项
		this.clickElement(this.find(By.xpath("//*[@class='tab2-content show']/li[" + sortType +"]")));
	}
	
	@Override
	public boolean checkSelectedFlightResult(int timeToWaitInSec){   //筛选后判断页面是否有机票  后续再做每种筛选项对应的校验
		return ElementUtil.waitVisibilityOnExpectTime(getAndroidDriver(), By.xpath("//div[@class='container']//div[@class='box flight-full']"), timeToWaitInSec);
	}
	
	@Override
	public boolean slctFlightThenBack(){        //选择第一程机票后点返回功能(点返回按钮返回)
		this.clickElement(this.find(By.xpath("//div[@class='container']/div[1]")));   //选择第一个机票
		if(waitPageLoadReady(20)){
			this.clickElement(this.find(By.xpath("//*[@id='info']/div")));   //点击第一程机票返回
			return true;
		}else
			return false;
	}
	
	@Override
	public boolean isExitFlightLimit(int timeToWaitInSec){   //判断是否存在预定限制弹框
		return ElementUtil.waitVisibilityOnExpectTime(getAndroidDriver(), By.xpath("//div[@class='popup-black']//div[@class='popup-box']"), timeToWaitInSec);
	}
	
	@Override
	public void clickFlightLimit(){    //点击预定限制弹框
		this.clickElement(this.find(By.xpath("//div[@class='popup-ok']")));
	}
	
	@Override
	public int countFlightNum(){   //获取机票列表机票总数
		List<AndroidElement> allFilghtBox = this.getAndroidDriver().findElements(By.xpath("//div[@class='container']//div[@class='box flight-full']"));
		int countFlight = allFilghtBox.size();
		return countFlight;
	}
	
	@Override
	public boolean selectFlight(int slcFlight){     //随机选择每一程机票
			//判断是否出现需要选择的机票，出现则选择
			if(ElementUtil.waitVisibilityOnExpectTime(getAndroidDriver(), By.xpath("//div[@class='container']/div[" + slcFlight + "]"), 30)){
				this.clickElement(this.find(By.xpath("//div[@class='container']/div[" + slcFlight + "]")));
				return true;
			}else{
				logger.debug("超过30s，要选择的机票还未出现");
				return false;
			}
	}
	
	@Override
	public boolean isSelcFlightBack(int timeToWaitInSec){   //检查是否回到1/2页面
		return ElementUtil.waitVisibilityOnExpectTime(getAndroidDriver(), By.id("nextStep"), timeToWaitInSec);
	}
}
