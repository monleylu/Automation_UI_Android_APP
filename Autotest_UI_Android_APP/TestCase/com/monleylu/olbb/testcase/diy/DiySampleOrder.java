/**
*Copyright (c) 2006-2016 . All Rights Reserved.
*/
package com.monleylu.olbb.testcase.diy;

import static com.monleylu.olbb.staticvariables.StaticVars.logger;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.openqa.selenium.By;

import com.monleylu.olbb.common.AppUtil;
import com.monleylu.olbb.common.ElementUtil;
import com.monleylu.olbb.internal.tours.ProductPageCode;
import com.monleylu.olbb.product.diy.DiyProduct;
import com.monleylu.olbb.result.ProductBookInformation;
import com.monleylu.olbb.result.SaveOrderResult;
import com.monleylu.olbb.testcase.TestUnit;

public class DiySampleOrder {

    
    public SaveOrderResult DiySaveOrder(DiyProduct diyProduct,TestUnit testUnit) {
	

	SaveOrderResult saveOrderResult = new SaveOrderResult(testUnit);
	ProductBookInformation productBookInformation = testUnit.getProductBookInformation();
	AndroidDriver<AndroidElement> androidDriver = diyProduct.getDriver();
	
	try {
	       
		//开始访问产品
		logger.debug("开始进入下单流程");
		// 判断是否存在切换城市浮层
		if (AppUtil.isVisibleSwitchCityDialog(diyProduct.getDriver(), 3)) {
		    AppUtil.clickSwitchCityDialogCancelElement(androidDriver);
		}

		// 还存在其他一些浮层，如提示抢红包以及会员活动等，偶尔能遇到，等遇到时再分析处理
		// 返回首页
		AppUtil.backToMainPage(androidDriver);
		
		
		diyProduct.getDiyPageProductDetail().visitProduct(productBookInformation.getProductID());
		
		//判断详情页是否加载完毕
		logger.debug("判断详情页是否加载完毕");
		if(!diyProduct.getDiyPageProductDetail().waitPageLoadReady(30)){
		    logger.error("详情页超过30s仍然没有加载完毕，用例执行结束");
		    saveOrderResult.setSuccess(false);
		    saveOrderResult.setMsg("详情页超过30s仍然没有加载完毕，用例执行结束");
		    return saveOrderResult;
		}
		
		//选择出游日期及出游人数
		logger.debug("滚动视图到价格日历模块可见");
		diyProduct.getDiyPageProductDetail().viewScrollToSelectCalendar();
		
		logger.debug("点击详情页更改出游人和日期的按钮");
		diyProduct.getDiyPageProductDetail().clickSelectCalendarBtn();
		
		//判断价格日历页是否加载完毕
		logger.debug("判断价格日历页是否加载完毕");
		if (!diyProduct.getDiyPageProductCalendar().waitPageLoadReady(30)) {
		    logger.error("价格日历页超过30s仍然没有加载完毕，用例执行结束");
		    saveOrderResult.setSuccess(false);
		    saveOrderResult.setMsg("价格日历页超过30s仍然没有加载完毕，用例执行结束");
		    return saveOrderResult;
		}
		
		logger.debug("选择出游日期");
		diyProduct.getDiyPageProductCalendar().selectTourDate(productBookInformation.getBuyDate());
		
		logger.debug("选择出游成人数");
		diyProduct.getDiyPageProductCalendar().selectAdult(productBookInformation.getAdultNum());
		logger.debug("选择出游儿童数");
		diyProduct.getDiyPageProductCalendar().selectChild(productBookInformation.getChildNum());
		logger.debug("选择出游免票儿童");
		diyProduct.getDiyPageProductCalendar().selectFreeChild(productBookInformation.getChildFreedNum());
		
		diyProduct.getDiyPageProductCalendar().clickNextStepBtn();
		
		
		logger.debug("返回详情页，点击详情页下一步按钮");
		if(!diyProduct.getDiyPageProductDetail().waitPageLoadReady(30)){
		    logger.error("选择完毕出游日期后返回详情页，但是详情页超过30s仍然没有加载完毕，用例执行结束");
		    saveOrderResult.setSuccess(false);
		    saveOrderResult.setMsg("选择完毕出游日期后返回详情页，但是详情页超过30s仍然没有加载完毕，用例执行结束");
		    return saveOrderResult;
		}
		
		//处理选择出游人后，返回时，快速点击下一步按钮，提示机票异常问题，等一会再点击可顺利进入下一步流程
		diyProduct.getDiyPageProductDetail().viewScrollToSelectCalendar();
		
		diyProduct.getDiyPageProductDetail().clickNextStepBtn();
		
		//判断是否存在机票异常弹窗
		logger.debug("判断是否存在机票异常弹窗，包括售罄以及价格变动");
		if(diyProduct.getDiyPageProductDetail().isExistFlightExceptionDialog(5)){
		    logger.debug("存在机票异常弹窗");
		    diyProduct.getDiyPageProductDetail().clickCalcelBtnOfFlightExcrptionDialog();
		    //当出现这个弹窗时，有可能已经是1/2页面，由于无法区分页面就做了判断
		    if (ElementUtil.isExist(diyProduct.getDiyPageProductDetail().getDriver(), By.id("com.monleylu.productdetail:id/tv_book"))) {
			diyProduct.getDiyPageProductDetail().clickNextStepBtn();
		    }
		    
		}
		
		//还存在门票、附加项目、火车票等其他资源需要处理
		
		logger.debug("判断1/2是否加载完毕");
		if (!diyProduct.getDiyPageProductStepOne().waitPageLoadReady(30)) {
		    logger.error("1/2页超过30s仍然没有加载完毕，用例执行结束");
		    saveOrderResult.setSuccess(false);
		    saveOrderResult.setMsg("1/2页页超过30s仍然没有加载完毕，用例执行结束");
		    return saveOrderResult;
		}
		
		diyProduct.getDiyPageProductStepOne().clickNextStepBtn();
		
		logger.debug("判断2/2页面是否加载完毕");
		if (!diyProduct.getDiyPageProductStepTwo().waitPageLoadReady(30)) {
		    logger.error("2/2页超过30s仍然没有加载完毕，用例执行结束");
		    saveOrderResult.setSuccess(false);
		    saveOrderResult.setMsg("2/2页页超过30s仍然没有加载完毕，用例执行结束");
		    return saveOrderResult;
		}
		
		logger.debug("选择出游人");
		if (6==productBookInformation.getProductMode()) {
		    logger.debug("火车票出游人选择模型");
		    if (productBookInformation.getAdultNum()>0) {
			diyProduct.getDiyPageProductStepTwo().getTouristView().clickSelectAdultTouristsBtn();
			//选择出游人
			logger.debug("判断出游人页面是否加载完毕");
			if (!diyProduct.getDiyPageProductTourist().waitPageLoadReady(30)) {
			    logger.error("出游人页面超过30秒仍然未加载完毕，用例执行结束");
			    saveOrderResult.setSuccess(false);
			    saveOrderResult.setMsg("出游人页面超过30秒仍然未加载完毕，用例执行结束");
			    return saveOrderResult;
			}
			
			logger.debug("进入出游人页面，开始选择成人出游人");
			diyProduct.getDiyPageProductTourist().selectTourist(productBookInformation.getAdultNum());
			diyProduct.getDiyPageProductTourist().clickNextStepBtn();
			//选择完出游人，等待页面加载完毕
			logger.debug("出游人选择完毕，返回2/2页面，等待页面加载完毕");
			diyProduct.getDiyPageProductStepTwo().waitPageLoadReady(30);
		    }
		    
		    if (productBookInformation.getChildNum()>0) {
			diyProduct.getDiyPageProductStepTwo().getTouristView().clickSelectChildTouristsBtn();
			//选择出游人
			logger.debug("判断出游人页面是否加载完毕");
			if (!diyProduct.getDiyPageProductTourist().waitPageLoadReady(30)) {
			    logger.error("出游人页面超过30秒仍然未加载完毕，用例执行结束");
			    saveOrderResult.setSuccess(false);
			    saveOrderResult.setMsg("出游人页面超过30秒仍然未加载完毕，用例执行结束");
			    return saveOrderResult;
			}
			
			logger.debug("进入出游人页面，开始选择儿童出游人");
			diyProduct.getDiyPageProductTourist().selectChildTourist(productBookInformation.getChildNum());
			diyProduct.getDiyPageProductTourist().clickNextStepBtn();
			//选择完出游人，等待页面加载完毕
			logger.debug("出游人选择完毕，返回2/2页面，等待页面加载完毕");
			diyProduct.getDiyPageProductStepTwo().waitPageLoadReady(30);
		    }
		    
		    if (productBookInformation.getChildFreedNum()>0) {
			diyProduct.getDiyPageProductStepTwo().getTouristView().clickSelectFreeChildTouristsBtn();
			//选择出游人
			logger.debug("判断出游人页面是否加载完毕");
			if (!diyProduct.getDiyPageProductTourist().waitPageLoadReady(30)) {
			    logger.error("出游人页面超过30秒仍然未加载完毕，用例执行结束");
			    saveOrderResult.setSuccess(false);
			    saveOrderResult.setMsg("出游人页面超过30秒仍然未加载完毕，用例执行结束");
			    return saveOrderResult;
			}
			
			logger.debug("进入出游人页面，开始选择儿童出游人");
			diyProduct.getDiyPageProductTourist().selectChildTourist(productBookInformation.getChildFreedNum());
			diyProduct.getDiyPageProductTourist().clickNextStepBtn();
			//选择完出游人，等待页面加载完毕
			logger.debug("出游人选择完毕，返回2/2页面，等待页面加载完毕");
			diyProduct.getDiyPageProductStepTwo().waitPageLoadReady(30);
		    }
		    
		    
		}else{

			diyProduct.getDiyPageProductStepTwo().clickSelectTouristBtn();
			
			//选择出游人
			logger.debug("判断出游人页面是否加载完毕");
			if (!diyProduct.getDiyPageProductTourist().waitPageLoadReady(30)) {
			    logger.error("出游人页面超过30秒仍然未加载完毕，用例执行结束");
			    saveOrderResult.setSuccess(false);
			    saveOrderResult.setMsg("出游人页面超过30秒仍然未加载完毕，用例执行结束");
			    return saveOrderResult;
			}
			
			logger.debug("进入出游人页面，开始选择出游人");
			diyProduct.getDiyPageProductTourist().selectTourist(productBookInformation.getAdultNum() + productBookInformation.getChildNum());
			diyProduct.getDiyPageProductTourist().clickNextStepBtn();
		}
		
		
		
		//选择完出游人，等待页面加载完毕
		logger.debug("出游人选择完毕，返回2/2页面，等待页面加载完毕");
		diyProduct.getDiyPageProductStepTwo().waitPageLoadReady(30);
		
		//滚动页面到底部
		diyProduct.getDiyPageProductStepTwo().scrollToBottom();
		
		logger.debug("出游人选择完毕，滚动页面，勾选协议");
		diyProduct.getDiyPageProductStepTwo().clickTouristSatisfyAllRequirementElement();
		diyProduct.getDiyPageProductStepTwo().clickReadLawsAndOtherElement();
		//判断是否存在visa选框
		if (diyProduct.getDiyPageProductStepTwo().isExistVisaBtn()) {
		    diyProduct.getDiyPageProductStepTwo().clickVisaBtn();
		}
		
		logger.debug("点击立即付款按钮");
		diyProduct.getDiyPageProductStepTwo().clickNextStepBtn();
		

		logger.debug("轮询下单状态");
		boolean continueLoop=true;
		int continueLoopCount =0;
		while (continueLoop) {
		    
		  //判断是否存在生僻字及婴幼儿姓名校验对话框
		    /*logger.debug("判断是否存在生僻字及婴幼儿姓名校验对话框");
		    if (driveTours.getPageProductStepTwo().isVisibleCheckOutDialog(2)) {
			    logger.debug("存在生僻字及婴幼儿姓名校验对话框");
			    driveTours.getPageProductStepTwo().sendFirstNameOfCheckOutDialog("testfirstname");
			    driveTours.getPageProductStepTwo().sendLastNameOfCheckOutDialog("testlastname");
			    driveTours.getPageProductStepTwo().clickConfirmBtnOfCheckOutDialog();
			}*/
		    
		    logger.debug("判断是否存在占位浮层");
		    if (diyProduct.getDiyPageProductStepTwo().isVisibleLayOutView(5)) {
			    logger.debug("存在占位浮层");
			    saveOrderResult.setSuccess(true);
			    saveOrderResult.setOrderID(diyProduct.getDiyPageProductStepTwo().getOrderID());
			    saveOrderResult.setMsg(saveOrderResult.getMsg() + "  下单成功");
			    logger.debug("已获取订单号");
			    continueLoop=false;
			    //等待占位浮层消失
			    if (diyProduct.getDiyPageProductStepTwo().isInVisibleLayOutView(30)) {
				logger.debug("占位浮层消失");
			    }else {
				logger.error("等待30秒，占位浮层仍然未消失，应该存在问题");
				saveOrderResult.setMsg(saveOrderResult.getMsg() + "  等待30秒，占位浮层仍然未消失，可能存在问题");
				
			    }
			    
			}
		    
		    logger.debug("判断是否存在占位超时对话框");
		    if (diyProduct.getDiyPageProductStepTwo().isVisibleTimeOutDialog(5)) {
			logger.debug("存在占位超时对话框");
			diyProduct.getDiyPageProductStepTwo().clickElement(By.id("com.monleylu.productdetail:id/plane_wait_confirm_tv"));
			continueLoop=false;
		    }
		    
		    logger.debug("判断是否进入预订成功页面");
		    if(ProductPageCode.PageProductBookSuccess == diyProduct.getCurrentPage()){
			logger.debug("进入预订成功页面");
			saveOrderResult.setSuccess(true);
			saveOrderResult.setOrderID(diyProduct.getDiyPageProductStepTwo().getOrderID());
			saveOrderResult.setMsg(saveOrderResult.getMsg() + "  进入预订成功页");
			continueLoop=false;
		    }
		   
		    logger.debug("判断是否进入收银台");
		    if (ProductPageCode.PageProductCasher == diyProduct.getCurrentPage()) {
			logger.debug("进入收银台");
			saveOrderResult.setSuccess(true);
			//saveOrderResult.setOrderID(tours.getPageProductStepTwo().getOrderID());
			saveOrderResult.setMsg(saveOrderResult.getMsg() + "  进入收银台");
			continueLoop=false;
		    }
		
		    continueLoopCount=continueLoopCount+1;
		    logger.error("判断已轮询次数");
		    if (continueLoopCount>12) {
			logger.error("循环超过一分钟，仍然没有结果,结束循环等待");
			saveOrderResult.setMsg(saveOrderResult.getMsg() + "  循环超过一分钟，仍然没有结果,结束循环等待");
			continueLoop=false;
		    }
		}
		
		return saveOrderResult;
	} catch (Exception e) {
	    	e.printStackTrace();
		//保存日志
		ByteArrayOutputStream logArrayOutputStream = new ByteArrayOutputStream();
		e.printStackTrace(new PrintStream(logArrayOutputStream));
		logger.error(logArrayOutputStream.toString());
		saveOrderResult.setSuccess(Boolean.FALSE);
		saveOrderResult.setMsg("下单流程出现异常，具体错误请查看日志");
		return saveOrderResult; 
	}
	

    }

}
