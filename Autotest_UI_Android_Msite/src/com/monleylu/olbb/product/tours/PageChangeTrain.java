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

public class PageChangeTrain extends BasePage implements CommonActionModule,TrainListModule{

    public PageChangeTrain(AndroidDriver<AndroidElement> androidDriver) {
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
	public int getTrainNum(){
		List<AndroidElement> allFilghtAndroidElement = this.getAndroidDriver().findElements(By.xpath("//div[@class='flights']//div[@class='flight-light']"));
		return allFilghtAndroidElement.size();
		
	}
	
	@Override
    public void clickChangeTrainBtn() {
	//点击更改火车票弹框
		this.clickElement(this.find(By.xpath("//*[@id='trainBox']/div[1]/a")));
    }
	
	public int creRangeRanNum(int range){    //生成指定范围内的随机数，筛选火车票;选择火车票
		Random r = new Random();
		int i = r.nextInt(range)+1;
		return i;
	}
	
	@Override
	public void siftingTrain(){    
		//1-车型 2-坐席  3-出发车站  4-到达车站 5-出发时间  6-到达时间   随机一种方式筛选火车票
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
	public void sortTrain(){   
		//1-低价 2-耗时短 3-出发 早到晚 4-出发 晚到早 5-达到 早到晚 6-达到 晚到早 随机一种排序方式排序火车票列表
		int sortType = creRangeRanNum(6);
		//点击排序按钮
		this.clickElement(this.find(By.xpath("//div[@class='filter-container']//*[@class='filter-tabs']/li[2]/span/i")));
		//点击排序项
		this.clickElement(this.find(By.xpath("//*[@class='tab2-content show']/li[" + sortType +"]")));
	}
	
	@Override
	public boolean checkSelectedTrainResult(int timeToWaitInSec){   //筛选后判断页面是否有火车票  后续再做每种筛选项对应的校验
		return ElementUtil.waitVisibilityOnExpectTime(getAndroidDriver(), By.xpath("//div[@class='container train-list']//div[@class='box train-full']"), timeToWaitInSec);
	}
	
	@Override
	public boolean slctTrainThenBack(){        //选择第一程火车票后点返回功能(点返回按钮返回)
		this.clickElement(this.find(By.xpath("//div[@class='container train-list']/div[1]//div[@class='train-seats']/div[1]")));   //选择第一个火车票
		if(waitPageLoadReady(20)){
			this.clickElement(this.find(By.xpath("//div[@class='choose-list bus-choose-list']/div[1]//div[@class='checked']//div[@class='flight-checked']")));   //点击第一程火车票返回
			return true;
		}else
			return false;
	}
	
	@Override
	public int countTrainNum(){   //获取机票列表火车票总数
		List<AndroidElement> allTrainBox = this.getAndroidDriver().findElements(By.xpath("//div[@class='container train-list']//div[@class='box train-full']"));
		int countTrain = allTrainBox.size();
		return countTrain;
	}
	
	@Override
	public boolean selectTrain(int slcFlight){     //随机选择每一程火车票
			//判断是否出现需要选择的火车票，出现则选择
			if(ElementUtil.waitVisibilityOnExpectTime(getAndroidDriver(), By.xpath("//div[@class='container train-list']/div[" + slcFlight + "]//div[@class='train-seats']/div[1]"), 30)){
				this.clickElement(this.find(By.xpath("//div[@class='container train-list']/div[" + slcFlight + "]//div[@class='train-seats']/div[1]")));
				return true;
			}else{
				logger.debug("超过30s，要选择的火车票还未出现");
				return false;
			}
	}
	
	@Override
	public boolean isSelcTrainBack(int timeToWaitInSec){   //检查是否回到1/2页面
		return ElementUtil.waitVisibilityOnExpectTime(getAndroidDriver(), By.id("nextStep"), timeToWaitInSec);
	}
	
	public  void  scrollToTopByJs() {
		// 将页面滚动条拖到底部
		String js = "var q=document.scrollingElement.scrollTop=0";
		this.getAndroidDriver().executeScript(js);
	}
}
