/**
*Copyright (c) 2006-2016 . All Rights Reserved.
*/
package com.monleylu.olbb.testcase.cruise;

import static com.monleylu.olbb.staticvariables.StaticVars.logger;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import com.monleylu.olbb.product.cruise.*;
import com.monleylu.olbb.common.AppUtil;
import com.monleylu.olbb.internal.tours.ProductPageCode;
import com.monleylu.olbb.result.ProductBookInformation;
import com.monleylu.olbb.result.SaveOrderResult;
import com.monleylu.olbb.testcase.TestUnit;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;


public class CruiseSampleOrder {


    
    public  SaveOrderResult	saveOrder(CruiseProduct cruiseProduct,TestUnit testUnit) {
        	
		SaveOrderResult saveOrderResult = new SaveOrderResult(testUnit);
		ProductBookInformation productBookInformation = testUnit.getProductBookInformation();
    	AndroidDriver<AndroidElement> androidDriver=cruiseProduct.getDriver();
        	
    	boolean continueLoop=true;
        	int continueLoopCount=0;
    
    	try {
    
    	// 判断是否存在切换城市浮层
    	logger.debug("判断是否存在切换城市浮层");
    	
    	if (AppUtil.isVisibleSwitchCityDialog(cruiseProduct.getDriver(), 3)) {
    	    AppUtil.clickSwitchCityDialogCancelElement(androidDriver);
    	}
    	
    	// 还存在其他一些浮层，如提示抢红包以及会员活动等，偶尔能遇到，等遇到时再分析处理
    	// 返回首页
    	logger.debug("由于预订流程需要从首页进入，操作APP返回首页");
    	AppUtil.backToMainPage(androidDriver);
    
    	
    	
    	logger.debug("从APP首页访问产品，通过搜索框输入产品id访问产品详情");
    	cruiseProduct.getPageProductDetail().visitProduct(productBookInformation.getProductID());
    	
    	//由于页面改版，这里原来的跳转页面有改变，暂时不处理这种情况
    	//处理通过ID搜索不到结果情况
    	/*logger.debug("判断是否搜索到产品");
    	if (ProductPageCode.PageProductSearchEmpty==AppUtil.getCurrentPage(androidDriver)) {
    	    logger.error("通过产品ID搜索不到结果");
    	    saveOrderResult.setSuccess(false);
    	    saveOrderResult.setMsg("通过产品ID搜索不到结果");
    	    return saveOrderResult; 
    	}*/
    	
    	//处理产品详情页加载慢或者加载不出来问题
    	logger.debug("开始打开产品详情页");
    	if(!cruiseProduct.getPageProductDetail().waitPageLoadReady(30)){
    	    logger.error("产品详情页未加载出来");
    	    saveOrderResult.setSuccess(false);
    	    saveOrderResult.setMsg("产品详情页未加载出来");
    	    return saveOrderResult;
    	}
    	cruiseProduct.getPageProductDetail().clickNextStepBtn();
    	
    	
    	logger.debug("开始访问价格日历页面");
    	if(!cruiseProduct.getPageProductCalendar().waitPageLoadReady(30)){
    	    logger.error("价格日历页没有加载出来");
    	    saveOrderResult.setSuccess(false);
    	    saveOrderResult.setMsg("价格日历页没有加载出来");
    	    return saveOrderResult;
    	}
    	
    	//由于无法选择指定日期，现在填写的日期参数也没有使用
    	logger.debug("开始选择出游团期，目前团期都是随机选择，没有根据指定参数选择");
    	cruiseProduct.getPageProductCalendar().selectTourDate(productBookInformation.getBuyDate());
    	
    	//判断是否火车票地接采购方式
    	if(productBookInformation.getProductMode()==2){
    	    logger.debug("选择出游人数");
    	    cruiseProduct.getPageProductCalendar().selectAdult(productBookInformation.getAdultNum());
    	    
    	    //未来删除代码开始
    	    //由于选择出游人页面研发设计问题，如果要选择儿童，逻辑会非常复杂，故这边将儿童置零，待以后研发优化代码再去掉此处逻辑
    	    logger.debug("判断为火车票地接售卖方式，由于选择出游人页面研发设计问题，如果要选择儿童，逻辑会非常复杂，故这边将儿童置零，仅选择成人下单");
    	    saveOrderResult.setMsg("判断为火车票地接售卖方式，由于选择出游人页面研发设计问题，如果要选择儿童，逻辑会非常复杂，故这边将儿童置零，仅选择成人下单");
    	    productBookInformation.setChildNum(0);
    	    productBookInformation.setChildFreedNum(0);
    	    //未来删除代码结束
    	    
    	    
    	    cruiseProduct.getPageProductCalendar().selectChild(productBookInformation.getChildNum());
    	    if (productBookInformation.getChildFreedNum()>0) {
    		cruiseProduct.getPageProductCalendar().selectFreeChild(productBookInformation.getChildFreedNum());
    	    }
    	}else {
    		logger.debug("选择出游人数");
    		cruiseProduct.getPageProductCalendar().selectAdult(productBookInformation.getAdultNum());
    		cruiseProduct.getPageProductCalendar().selectChild(productBookInformation.getChildNum());
    	}
    	
    
    	
    
    	
    	
    	cruiseProduct.getPageProductCalendar().clickNextStepBtn();
    	cruiseProduct.getPageProductCalendar().waitLeaveCurrentPage(10);
    	
    	//判断是否快速网络单页面
    	if (ProductPageCode.PageFastOrder == AppUtil.getCurrentPage(cruiseProduct.getDriver())) {
	    if(!cruiseProduct.getPageProductFastOrder().waitPageLoadReady(30)){
		logger.error("快速网络单页面30s仍然未加载出来");
	    	saveOrderResult.setSuccess(false);
	    	saveOrderResult.setMsg("快速网络单页面30s仍然未加载出来");
	    	return saveOrderResult;
	    }
	    logger.debug("点击快速网络单提交订单按钮");
	    cruiseProduct.getPageProductFastOrder().clickNextStepBtn();
	    if (ProductPageCode.PageProductBookSuccess == AppUtil.getCurrentPage(cruiseProduct.getDriver()) ) {
		logger.error("快速网络单页面下单成功");
	    	saveOrderResult.setSuccess(true);
	    	saveOrderResult.setMsg("快速网络单页面下单成功");
	    	return saveOrderResult;
	    }else{
		logger.error("快速网络单页面下单失败");
	    	saveOrderResult.setSuccess(false);
	    	saveOrderResult.setMsg("快速网络单页面下单失败");
	    	return saveOrderResult;
	    }
	    
	}
    	
    	//判断是否存在重单页，存在继续下单
    	logger.debug("判断是否有重单");
/*    	if(ProductPageCode.PageProductRepeatOrder==AppUtil.getCurrentPage(androidDriver)){
    	    logger.debug("有重单页");
    	    toursProduct.getPageProductRepeatOrder().clickNextStepBtn(); 
    	}*/
    	
    	
    	logger.debug("进入1/2页面,判断页面是否加载出来");
    	if (!cruiseProduct.getPageProductStepOne().waitPageLoadReady(30)) {
    	    logger.error("1/2页面30s仍然未加载出来");
    	    saveOrderResult.setSuccess(false);
    	    saveOrderResult.setMsg("1/2页面30s仍然未加载出来");
    	    return saveOrderResult;
    	    
    	}
    	
    	//判断下一步按钮是否可点击,如果不可点击，判断是否存在浮层
    	logger.debug("判断下一步按钮是否可点击");
    	while (!cruiseProduct.getPageProductStepOne().isClickableOfNextPageElement()) {
    	    logger.debug("1/2下一步按钮仍然不可点击，判断是否存在机票异常等弹窗");
    	    //判断是否存在浮层
    	    if(cruiseProduct.getPageProductStepOne().isExistDialog(5)){
    		logger.debug("存在弹窗");
    		if(cruiseProduct.getPageProductStepOne().isExistFlightExceptionDialog(1)){
    		    	logger.error("存在机票异常弹窗");
    			saveOrderResult.setSuccess(false);
    			saveOrderResult.setMsg("存在机票异常弹窗");
    			return saveOrderResult;
    		    }else if (cruiseProduct.getPageProductStepOne().isExistFlightSellOutDialog(1)) {
    			logger.error("存在机票售空弹窗");
    			saveOrderResult.setSuccess(false);
    			saveOrderResult.setMsg("存在机票售空弹窗");
    			return saveOrderResult;
    		    }else {
    			logger.error("未知弹窗，请查看截图和日志处理");
    			saveOrderResult.setSuccess(false);
    			saveOrderResult.setMsg("未知弹窗，请查看截图和日志处理");
    			return saveOrderResult;
    		    }
    	    }
    	    
    	}

    	logger.debug("选择邮轮");
    	logger.debug("邮轮资源不一定正好展示在页面，先点击下一步按钮，将邮轮资源呈现出来");
    	cruiseProduct.getPageProductStepOne().clickNextPageElement();
    	cruiseProduct.getPageProductStepOne().clickNextStepBtn(By.id("com.monleylu.app.ui:id/tv_confirm"));
    	cruiseProduct.getPageProductStepOne().getCruiseRoomModel().seletCruiseRoomRandom(productBookInformation.getAdultNum(),productBookInformation.getChildNum());
    	cruiseProduct.getPageProductStepOne().getCruiseRoomModel().clickConfirmBtn();

    	logger.debug("重新等待页面加载完毕");
    	cruiseProduct.getPageProductStepOne().waitPageLoadReady(30);
    	logger.debug("点击1/2下一步按钮");
    	cruiseProduct.getPageProductStepOne().clickNextPageElement();
    	
    	//用户点击提交后会再次验仓验价
    	boolean continueLoopStepOne= true;
    	int continueLoopStepOneCount=0;
    	logger.debug("1/2点击下一步后，再次判断是否有机票模块验仓验价浮层");
    	while (continueLoopStepOne) {
    	    
    	    //处理万一存在未捕捉到的窗口时，循环能够及时终止
    	    continueLoopStepOneCount=continueLoopStepOneCount+1;
    	    if (continueLoopStepOneCount>15) {
    		logger.error("1/2页面循环多次，仍然没有进入2/2页面，也没有判断到有弹窗");
    		continueLoopStepOne=false;
    		saveOrderResult.setSuccess(false);
    		saveOrderResult.setMsg("1/2页面循环多次，仍然没有进入2/2页面，也没有判断到有弹窗，出现未知异常");
    		return saveOrderResult;
    	    }
    	    
    	    //点击下一步后，如果已经不是1/2页面就退出循环
    	    logger.debug("判断当前页面是否为2/2页面");
    	    if (ProductPageCode.PageProductStepTwo == AppUtil.getCurrentPage(androidDriver)) {
    		logger.error("已进入2/2页面,跳出循环");
    		continueLoopStepOne=false;
    		break;
    	    }
    	    
    	    //判断是否存在验仓失败
    	    logger.debug("判断是否存在弹窗");
    	    if(cruiseProduct.getPageProductStepOne().isExistDialog(2)){
    		logger.debug("存在弹窗");
    		if (cruiseProduct.getPageProductStepOne().isExistFlightSellOutDialog(1)) {
    			logger.error("存在机票售空弹窗");
    			continueLoopStepOne=false;
    			saveOrderResult.setSuccess(false);
    			saveOrderResult.setMsg("存在机票售空弹窗，结束用例，后续再处理机票选择情况");
    			return saveOrderResult;
    		    }else if (cruiseProduct.getPageProductStepOne().isExistFlightMoneyChangeDialog(1)) {
    			logger.error("存在机票验仓验价后价格有变动弹窗");
    			continueLoopStepOne=false;
    			saveOrderResult.setSuccess(false);
    			saveOrderResult.setMsg("存在机票验仓验价后价格有变动弹窗 ");
    			//点击确定按钮，继续后续流程
    			cruiseProduct.getPageProductStepOne().clickConfirmBtnOfFlightMoneyChangeDialog();
    			
    		    }else if (cruiseProduct.getPageProductStepOne().isExistFlightExceptionDialog(1)) {
    			logger.error("存在机票信息异常弹窗");
    			continueLoopStepOne=false;
    			saveOrderResult.setSuccess(false);
    			saveOrderResult.setMsg("存在机票信息异常弹窗，结束用例，后续再处理机票选择情况");
    			return saveOrderResult;
    		    }else if (cruiseProduct.getPageProductStepOne().isExistCruiseChenkinUnmatch(1)) {
    			logger.error("存在邮轮入住人数和出游人数不匹配弹窗");
    			continueLoopStepOne=false;
    			saveOrderResult.setSuccess(false);
    			saveOrderResult.setMsg("存在邮轮入住人数和出游人数不匹配弹窗，结束用例，后续再处理机票选择情况");
    			return saveOrderResult;
		    }else {
    			logger.error("未知弹窗，请查看截图和日志处理");
    			continueLoopStepOne=false;
    			saveOrderResult.setSuccess(false);
    			saveOrderResult.setMsg("未知弹窗，请查看截图和日志处理");
    			return saveOrderResult;
    		    }
    	    }
    	    
    
    	    
    	}
    	
    	
    	
    	
    	
    	logger.debug("进入2/2页面");
    	if(!cruiseProduct.getPageProductStepTwo().waitPageLoadReady(30)){
    	    logger.error("2/2页面30s仍然未加载出来");
    	    saveOrderResult.setSuccess(false);
    	    saveOrderResult.setMsg("2/2页面30s仍然未加载出来");
    	    return saveOrderResult;  
    	}
    	
    	//选择出游人
    	if (2==productBookInformation.getProductMode()) {
    	    //处理火车票需要分别选择成人、儿童、免票儿童情况
    	    //研发在view层上没有区别三类出游人，后续出游人模块改版时让研发加一个，方便我们自动化，目前先自动化代码里处理,目前代码处理方式是通过每类出游人前面的小人头图标判断人员类型
    	    //同时由于出游人选择为非儿童时，app没有在选择出游人页面处理，而是返回上一级页面时才验证是否为儿童是否重复选择，
    	    //这块自动化代码要处理起来会非常麻烦，故在上面价格日历页选择出游人时过滤了儿童情况，以后研发代码优化了再打开此处功能
    	    
    	    logger.debug("选择成人");
    	    cruiseProduct.getPageProductStepTwo().clickSelectAdultBtn();
    	    logger.debug("进入选择出游人页面");
    	    cruiseProduct.getPageProductTourist().waitPageLoadReady(20);
    	    cruiseProduct.getPageProductTourist().selectTourist(productBookInformation.getAdultNum());
    	    cruiseProduct.getPageProductTourist().clickNextStepBtn();
    	    
    	    //儿童
    	    if (productBookInformation.getChildNum()!=0) {
    		logger.debug("选择儿童");
    		List<AndroidElement> list = cruiseProduct.getDriver().findElements(By.id("com.monleylu.app.ui:id/iv_tourist_icon"));
    		list.get(1).click();
    		logger.debug("进入选择出游人页面");
    		cruiseProduct.getPageProductTourist().waitPageLoadReady(20);
    		cruiseProduct.getPageProductTourist().selectTourist(productBookInformation.getChildNum());
    		cruiseProduct.getPageProductTourist().clickNextStepBtn();
    	    }
    	    
    	    //免票儿童
    	    if(productBookInformation.getChildFreedNum()!=0){
    		logger.debug("选择免票儿童");
    		List<AndroidElement> list = cruiseProduct.getDriver().findElements(By.id("com.monleylu.app.ui:id/iv_tourist_icon"));
    		if (productBookInformation.getChildNum()!=0) {
    		    list.get(2).click();
    		}else {
    		    list.get(1).click();
    		}
    		
    		logger.debug("进入选择出游人页面");
    		cruiseProduct.getPageProductTourist().waitPageLoadReady(20);
    		cruiseProduct.getPageProductTourist().selectTourist(productBookInformation.getChildFreedNum());
    		cruiseProduct.getPageProductTourist().clickNextStepBtn();
    		
    	    }
    	}else {
    		logger.debug("开始选择出游人");
    	/*	toursProduct.getPageProductStepTwo().clickSelectTouristElement();
    		
    		logger.debug("进入选择出游人页面");
    		toursProduct.getPageProductTourist().waitPageLoadReady(20);
    		toursProduct.getPageProductTourist().selectTourist(productBookInformation.getAdultNum() + productBookInformation.getChildNum() );
    		toursProduct.getPageProductTourist().clickNextStepBtn();*/
    		//选择成人
    		/*if (productBookInformation.getAdultNum()>0) {
    		    cruiseProduct.getPageProductStepTwo().clickSelectAdultBtn();
    		    cruiseProduct.getPageProductTourist().waitPageLoadReady(20);
    		    cruiseProduct.getPageProductTourist().selectTourist(productBookInformation.getAdultNum());
    		    cruiseProduct.getPageProductTourist().clickNextStepBtn();
    		}*/
    		
    		//选择儿童
    		/*if (productBookInformation.getChildNum()>0) {
    		    cruiseProduct.getPageProductStepTwo().waitPageLoadReady(30);
    		    cruiseProduct.getPageProductStepTwo().clickSelectChildBtn();
    		    cruiseProduct.getPageProductTourist().waitPageLoadReady(20);
    		    cruiseProduct.getPageProductTourist().selectChildTourist(productBookInformation.getChildNum());
    		    cruiseProduct.getPageProductTourist().clickNextStepBtn();
    		}*/
    		
    		//判断是否存在签注类型选择框
    		/*if(cruiseProduct.getPageProductStepTwo().isNeedSignType()){
    		    cruiseProduct.getPageProductStepTwo().selectAllSignType(productBookInformation.getAdultNum()+productBookInformation.getChildNum());
    		}*/
    		
    		//这块选择出游人代码逻辑上有些问题，当房间数目过多时，当前页面不一定展示了所有房间数，需要滚动之后才能看到，会导致部分情况下选择不到房间的报错，后面有空优化下此块代码
    		int totalCruiseRoomNum = cruiseProduct.getPageProductStepTwo().getCruiseTouristModel().getTotalCruiseRoomNum();
    	    	logger.debug("总共入住房间数：" + totalCruiseRoomNum);
    	    	for(int i=1;i<=totalCruiseRoomNum;i++){
    	    	    cruiseProduct.getPageProductStepTwo().waitPageLoadReady(30);
    	    	    int touristCheckin = cruiseProduct.getPageProductStepTwo().getCruiseTouristModel().getCheckinTouristNum(i);
    	    	    logger.debug("第 "+i+" 房间入住人数: " + touristCheckin);
    	    	    cruiseProduct.getPageProductStepTwo().getCruiseTouristModel().clickSelectTouristBtn(i);
    	    	    cruiseProduct.getPageProductTourist().waitPageLoadReady(30);
    	    	    cruiseProduct.getPageProductTourist().selectTourist(touristCheckin);
    	    	    cruiseProduct.getPageProductTourist().clickNextStepBtn();
    	    	}
    	    	
    	}
    	
    	
    	
    	//选择完毕出游人后，返回2/2要等待下
    	logger.debug("再次等待页面加载完毕");
    	if(!cruiseProduct.getPageProductStepTwo().waitPageLoadReady(10)){
    	    saveOrderResult.setSuccess(false);
    	    saveOrderResult.setMsg("2/2页面加载异常");
    	    return saveOrderResult;
    	}
    	
    	logger.debug("出游人选择完毕，滚动页面，勾选协议");
    	cruiseProduct.getPageProductStepTwo().scrollToBottom();
    	cruiseProduct.getPageProductStepTwo().clickTouristSatisfyAllRequirementElement();
    	cruiseProduct.getPageProductStepTwo().clickReadLawsAndOtherElement();
    	
    	//处理立即支付按钮仍然为灰色，不可点击
    	if (!cruiseProduct.getPageProductStepTwo().isEnableClickNextPageElement()) {
    	    logger.debug("2/2立即付款按钮不可点击");
    	    saveOrderResult.setSuccess(false);
    	    saveOrderResult.setMsg("立即付款按钮不可点击");
    	    return saveOrderResult;
    	}
    	
    	logger.debug("点击2/2立即付款按钮");
    	cruiseProduct.getPageProductStepTwo().clickNextStepBtn();
	
    	
    	logger.debug("轮询下单状态");
    	while (continueLoop) {
    	    
    	  //判断是否存在生僻字及婴幼儿姓名校验对话框
    	    logger.debug("判断是否存在生僻字及婴幼儿姓名校验对话框");
    	    if (cruiseProduct.getPageProductStepTwo().isVisibleCheckOutDialog(2)) {
    		    logger.debug("存在生僻字及婴幼儿姓名校验对话框");
    		    cruiseProduct.getPageProductStepTwo().sendFirstNameOfCheckOutDialog("testfirstname");
    		    cruiseProduct.getPageProductStepTwo().sendLastNameOfCheckOutDialog("testlastname");
    		    cruiseProduct.getPageProductStepTwo().clickConfirmBtnOfCheckOutDialog();
    		}
    	    
    	    logger.debug("判断是否存在选择的人员类型和实际勾选出游人类型不匹配，弹窗预订机票提示框");
    	    if(cruiseProduct.getPageProductStepTwo().isExistFlightTipView(1)){
    		logger.debug("存在选择的人员类型和实际勾选出游人类型不匹配，弹窗预订机票提示框");
    		if ((new Random().nextBoolean())) {
    		    cruiseProduct.getPageProductStepTwo().clickSubmitOrderBtnOfFlightTipView();
    		}else{
    		    cruiseProduct.getPageProductStepTwo().clickContinusBookBtnOfFlightTipView();
    		}
    	    }
    	    
    	    logger.debug("判断是否存在占位浮层");
    	    if (cruiseProduct.getPageProductStepTwo().isVisibleLayOutView(5)) {
    		    logger.debug("存在占位浮层");
    		    saveOrderResult.setSuccess(true);
    		    saveOrderResult.setOrderID(cruiseProduct.getPageProductStepTwo().getOrderID());
    		    saveOrderResult.setMsg("下单成功");
    		    //等待占位浮层消失,跟团火车票地接还会存在另一个弹窗，id为“com.monleylu.app.ui:id/plane_wait_timeout_layout”，目前可不处理
    		    if (cruiseProduct.getPageProductStepTwo().isInVisibleLayOutView(30)) {
    			logger.debug("占位浮层消失");
    		    }else {
    			logger.error("等待30秒，占位浮层仍然未消失，应该存在问题");
    			saveOrderResult.setMsg("等待30秒，占位浮层仍然未消失，可能存在问题");
    			
    		    }
    		    continueLoop=false;
    		}
    	    
    	    logger.debug("判断是否进入预订成功页面");
    	    if(ProductPageCode.PageProductBookSuccess == AppUtil.getCurrentPage(androidDriver)){
    		logger.debug("进入预订成功页面");
    		saveOrderResult.setSuccess(true);
    		saveOrderResult.setOrderID(cruiseProduct.getPageProductStepTwo().getOrderID());
    		saveOrderResult.setMsg("进入预订成功页");
    		continueLoop=false;
    	    }
    	   
    	    logger.debug("判断是否进入收银台");
    	    if (ProductPageCode.PageProductCasher == AppUtil.getCurrentPage(androidDriver)) {
    		logger.debug("进入收银台");
    		saveOrderResult.setSuccess(true);
    		//saveOrderResult.setOrderID(toursProduct.getPageProductStepTwo().getOrderID());
    		saveOrderResult.setMsg("进入收银台");
    		continueLoop=false;
    	    }
    	
    	    continueLoopCount=continueLoopCount+1;
    	    logger.error("判断已轮询次数");
    	    if (continueLoopCount>12) {
    		logger.error("循环超过一分钟，仍然没有结果,结束循环等待");
    		saveOrderResult.setMsg("循环超过一分钟，仍然没有结果,结束循环等待");
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


