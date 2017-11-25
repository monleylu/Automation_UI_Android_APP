/**
*Copyright (c) 2006-2016 . All Rights Reserved.
*/
package com.monleylu.olbb.testcase.drive;

import static com.monleylu.olbb.staticvariables.StaticVars.logger;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.openqa.selenium.By;

import com.monleylu.olbb.common.AppUtil;
import com.monleylu.olbb.common.ElementUtil;
import com.monleylu.olbb.internal.tours.ProductPageCode;
import com.monleylu.olbb.product.drive.DriveProduct;
import com.monleylu.olbb.result.ProductBookInformation;
import com.monleylu.olbb.result.SaveOrderResult;
import com.monleylu.olbb.testcase.TestUnit;

public class DriveSampleOrder {

   public SaveOrderResult saveOrder(DriveProduct driveProduct,TestUnit testUnit) {
       
       SaveOrderResult saveOrderResult = new SaveOrderResult(testUnit);
       ProductBookInformation productBookInformation = testUnit.getProductBookInformation();
       AndroidDriver<AndroidElement> androidDriver=driveProduct.getDriver();
	
      // SaveOrderResult saveOrderResult = new SaveOrderResult(productBookInformation);
      // DriveProduct driveProduct = new DriveProduct(driver);
       
       try {
	   
	   	logger.debug("开始进入下单流程");
		
		// 判断是否存在切换城市浮层
		if (AppUtil.isVisibleSwitchCityDialog(driveProduct.getDriver(), 3)) {
		    AppUtil.clickSwitchCityDialogCancelElement(androidDriver);
		}

		// 还存在其他一些浮层，如提示抢红包以及会员活动等，偶尔能遇到，等遇到时再分析处理
		// 返回首页
		logger.debug("由于预订流程需要从首页进入，操作APP返回首页");
		AppUtil.backToMainPage(androidDriver);
		
	

		//访问产品详情页
		logger.debug("访问产品详情页");
		driveProduct.getPageProductDetail().visitProduct(productBookInformation.getProductID());
		
		//判断页面是否加载完毕
		logger.debug("等待产品详情页加载完毕");
		if(!driveProduct.getPageProductDetail().waitPageLoadReady(30)){
		    logger.error("产品详情页加载失败");
		    saveOrderResult.setSuccess(false);
		    saveOrderResult.setMsg("产品详情页加载失败");
		    return saveOrderResult;
		}
		
		
		
		//选择价格日历
		logger.debug("选择出游日期");
		if (!driveProduct.getPageProductDetail().moveScreenToSelectCalendarBtn()) {
		    logger.debug("循环多次仍然没有发现团期控件可见，退出当前用例执行");
		    saveOrderResult.setSuccess(false);
		    saveOrderResult.setMsg("循环多次仍然没有发现团期控件可见，退出当前用例执行");
		    return saveOrderResult;
		}
		
		driveProduct.getPageProductDetail().clickSelectCalendarBtn();
		
		//wait calender page load ready
		if (!driveProduct.getPageProductCalendar().waitPageLoadReady(30)) {
		    logger.debug("价格日历页等待30秒仍然未加载成功");
		    saveOrderResult.setSuccess(false);
		    saveOrderResult.setMsg("价格日历页等待30秒仍然未加载成功");
		    return saveOrderResult;
		}
		logger.debug("随机选择一个时间，暂不支持指定日期");
		driveProduct.getPageProductCalendar().selectTourDate(productBookInformation.getBuyDate());
		driveProduct.getPageProductCalendar().clickNextStepBtn();
		
		//缺少选择成人和儿童的逻辑，由于详情页9.1.8又要改版，暂时不优化了
		
		//wait load ready
		driveProduct.getPageProductDetail().waitPageLoadReady(30);
		//点击立即预订按钮
		logger.debug("点击立即预订按钮");
		driveProduct.getPageProductDetail().clickNextStepBtn();
		
		//判断门票模块是否可见，点击下一步时，如果有门票模块未选择日期，门票模块会被滑动到可见区域
		logger.debug("判断门票模块是否可见，点击下一步时，如果有门票模块未选择日期，门票模块会被滑动到可见区域");
		if(driveProduct.getPageProductDetail().isExistTicketModule(5)){
		    logger.debug("存在门票模块，开始选择门票使用日期");
		    for (int i = 0; i < driveProduct.getPageProductDetail().countOfDefaultShowTicket(); i++) {
			driveProduct.getPageProductDetail().clickUseDateBtn(i+1);
			driveProduct.getPageProductDetail().selectTicketUseDate(0);
		    }
		    
		    logger.debug("再次点击详情页立即预订按钮");
		    driveProduct.getPageProductDetail().clickNextStepBtn();
		}
		
		//此处逻辑有问题，仅做了一次滑动查找，应该轮询，直到出现产品详情
		
		logger.debug("除了门票，还有其他必须附加项目时也会要求选择日期，但是此时页面不会自动滚动到待填写日期资源位置");
		//查看详情页咨询客服按钮是否依然存在，如果存在说明还停留在详情页，有其他问题或者资源没选择日期
		if (ElementUtil.isVisibility(driveProduct.getPageProductDetail().getDriver(), By.id("com.monleylu.app.ui:id/tv_book"),2)) {
		    if(AppUtil.moveScreenToElement(driveProduct.getPageProductDetail().getDriver(), By.xpath("//android.widget.TextView[@resource-id='com.monleylu.app.ui:id/tv_usedate' and @text='使用日期']"), 5)){
			driveProduct.getPageProductDetail().getDriver().findElement(By.xpath("//android.widget.TextView[@resource-id='com.monleylu.app.ui:id/tv_usedate' and @text='使用日期']")).click();
			driveProduct.getPageProductDetail().getDriver().findElement(By.id("com.monleylu.app.ui:id/lv_date")).findElementById("com.monleylu.app.ui:id/tv_date").click();
			logger.debug("再次点击详情页立即预订按钮");
			driveProduct.getPageProductDetail().clickNextStepBtn();
		    }
		}
		
		//判断是否进入1/2页面
		logger.debug("判断是否进入1/2页面");
		if (!driveProduct.getPageProductStepOne().waitPageLoadReady(30)&&ProductPageCode.PageProductStepOne!=driveProduct.getCurrentPage()) {
		    logger.error("1/2页面超过30s仍然未加载完毕或者未进入1/2页面");
		    saveOrderResult.setSuccess(false);
		    saveOrderResult.setMsg("1/2页面超过30s仍然未加载完毕或者未进入1/2页面");
		    return saveOrderResult;
		}
		
		logger.debug("点击1/2填写订单按钮");
		driveProduct.getPageProductStepOne().clickNextStepBtn();
		
		
		//进入2/2流程
		logger.debug("点击进入2/2流程");
		if(!driveProduct.getPageProductStepTwo().waitPageLoadReady(30)&&ProductPageCode.PageProductStepTwo!=driveProduct.getCurrentPage()){
		    logger.error("2/2页面超过30s仍然未加载完毕或者未进入2/2页面");
		    saveOrderResult.setSuccess(false);
		    saveOrderResult.setMsg("2/2页面超过30s仍然未加载完毕或者未进入2/2页面");
		    return saveOrderResult;
		}
		
		logger.debug("点击选择出游人按钮");
		driveProduct.getPageProductStepTwo().clickSelectTouristBtn();
		
		logger.debug("判断出游人页面是否加载完毕。");
		if(!driveProduct.getPageProductTourist().waitPageLoadReady(30)&&ProductPageCode.PageProductTourist!=driveProduct.getCurrentPage()){
		    logger.error("出游人页面超过30s仍然未加载完毕或者未进入出游人页面");
		    saveOrderResult.setSuccess(false);
		    saveOrderResult.setMsg("出游人页面超过30s仍然未加载完毕或者未进入出游人页面");
		    return saveOrderResult;
		}
		
		logger.debug("开始选择出游人");
		driveProduct.getPageProductTourist().selectTourist(productBookInformation.getAdultNum() + productBookInformation.getChildNum());
		driveProduct.getPageProductTourist().clickNextStepBtn();
		logger.debug("选择出游人完毕，返回2/2页面");
		
		//等待页面加载完毕
		driveProduct.getPageProductStepTwo().waitPageLoadReady(30);
		
		//滚动页面到底部
		driveProduct.getPageProductStepTwo().scrollToBottom();
		
		logger.debug("出游人选择完毕，滚动页面，勾选协议");
		driveProduct.getPageProductStepTwo().clickTouristSatisfyAllRequirementElement();
		driveProduct.getPageProductStepTwo().clickReadLawsAndOtherElement();
		
		logger.debug("点击立即付款按钮");
		driveProduct.getPageProductStepTwo().clickNextStepBtn();
		

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
		    if (driveProduct.getPageProductStepTwo().isVisibleLayOutView(5)) {
			    logger.debug("存在占位浮层");
			    saveOrderResult.setSuccess(true);
			    saveOrderResult.setOrderID(driveProduct.getPageProductStepTwo().getOrderID());
			    saveOrderResult.setMsg(saveOrderResult.getMsg() + "  下单成功");
			    //等待占位浮层消失
			    if (driveProduct.getPageProductStepTwo().isInVisibleLayOutView(30)) {
				logger.debug("占位浮层消失");
			    }else {
				logger.error("等待30秒，占位浮层仍然未消失，应该存在问题");
				saveOrderResult.setMsg(saveOrderResult.getMsg() + "  等待30秒，占位浮层仍然未消失，可能存在问题");
				
			    }
			    
			}
		    
		    logger.debug("判断是否存在占位超时对话框");
		    if (driveProduct.getPageProductStepTwo().isVisibleTimeOutDialog(5)) {
			logger.debug("存在占位超时对话框");
			driveProduct.getPageProductStepTwo().clickElement(By.id("com.monleylu.app.ui:id/plane_wait_confirm_tv"));
			continueLoop=false;
		    }
		    
		    logger.debug("判断是否进入预订成功页面");
		    if(ProductPageCode.PageProductBookSuccess == driveProduct.getCurrentPage()){
			logger.debug("进入预订成功页面");
			saveOrderResult.setSuccess(true);
			saveOrderResult.setOrderID(driveProduct.getPageProductStepTwo().getOrderID());
			saveOrderResult.setMsg(saveOrderResult.getMsg() + "  进入预订成功页");
			continueLoop=false;
		    }
		   
		    logger.debug("判断是否进入收银台");
		    if (ProductPageCode.PageProductCasher == driveProduct.getCurrentPage()) {
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







