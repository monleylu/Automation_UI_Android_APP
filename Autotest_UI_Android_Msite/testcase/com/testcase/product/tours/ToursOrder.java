/**
*Copyright (c) 2006-2016 . All Rights Reserved.
*/
package com.testcase.product.tours;

import static com.monleylu.olbb.logback.LogBack.logger;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.openqa.selenium.By;

import com.monleylu.olbb.core.ElementUtil;
import com.monleylu.olbb.core.PageUtil;
import com.monleylu.olbb.inter.product.PageIdentifyCode;
import com.monleylu.olbb.product.tours.ToursProduct;
import com.monleylu.olbb.result.ProductBookInformation;
import com.monleylu.olbb.result.SaveOrderResult;
import com.monleylu.olbb.testcase.TestUnit;
import com.monleylu.olbb.testcase.tasks.ToursTasks;

/**
 * 
* @Description: 订单相关测试用例类，如下单、订单比对等
* @author lujian
* @date 2016年8月30日
* @version 
*    2016年8月30日  v1.0  create 
*
 */
public class ToursOrder {
    
    
    /**
     * 跟团、跟队自驾、常规自驾打包品类下单流程
     * @param toursProduct 产品实例
     * @param testUnit 测试用例信息
     * @return 测试用例执行结果
     * 
     */
    public SaveOrderResult SaveOrder(ToursProduct toursProduct,TestUnit testUnit){
	
	SaveOrderResult saveOrderResult = new SaveOrderResult(testUnit);
	ProductBookInformation productBookInformation =testUnit.getProductBookInformation();
	
	try {

		//保存预订对象下单信息
		//saveOrderResult.setProductBookInformation(productBookInformation);
		
		//组装待访问产品url
		String StrPrdUrl = "https://" + productBookInformation.getHostUrl() + "/tours/" + productBookInformation.getProductID(); // concat visit product url
		
		/**
		 * 打开产品详情页
		 */
		logger.debug("开始打开产品详情，进入测试流程");
		toursProduct.getPageDetail().visitProduct(StrPrdUrl);
		
		logger.debug("等待详情页页面加载完毕");
		if (!toursProduct.getPageDetail().waitPageLoadReady(30)) {
		    logger.error("等待30s详情页仍然未加载完毕");
		    saveOrderResult.setSuccess(false);
		    saveOrderResult.setMsg("等待30s详情页仍然未加载完毕");
		    return saveOrderResult;
		}
		
		//判断产品是否存在问题
		if(ElementUtil.isExist(toursProduct.getPageDetail().getAndroidDriver(), By.id("formBtn"))){
		    logger.error("访问的产品不存在");
		    saveOrderResult.setSuccess(false);
		    saveOrderResult.setMsg("访问的产品不存在");
		    return saveOrderResult;
		}
			
		
		logger.debug("点击详情页下一步按钮");
		toursProduct.getPageDetail().clickNextStepBtn();
		
		/**
		 * 进入价格日历页
		 * 
		 */
		logger.debug("进入价格日历页面");
		//等待价格日历页面加载完毕
		logger.debug("等待价格日历页加载完毕");
		if(!toursProduct.getPageCalendar().waitPageLoadReady(30)){
		    logger.error("价格日历页超过30s仍然未加载完毕");
		    saveOrderResult.setSuccess(false);
		    saveOrderResult.setMsg("价格日历页超过30s仍然未加载完毕");
		    return saveOrderResult;
		}
		
		
		//引入zepto到当前页面
		toursProduct.getPageCalendar().ImportZepto();
		
		//选择出游人
		logger.debug("价格日历页选择成人出游人数");
		toursProduct.getPageCalendar().selectAdult(productBookInformation.getAdultNum());
		logger.debug("价格日历页选择儿童出游人数");
		toursProduct.getPageCalendar().selectChild(productBookInformation.getChildNum());

		//采购方式为火车票地接
		if(productBookInformation.getProductMode()==2){
			logger.debug("价格日历页面选择免票儿童出游人数");
			toursProduct.getPageCalendar().selectFreeChild(productBookInformation.getChildFreedNum());
		}
		
		
		//选择出游日期
		logger.debug("选择出游日期");
		if (productBookInformation.getBuyDate().trim().length()==0) {
		    
		    logger.debug("价格日历出游日期为空，随机选择一个出游日期");
		    productBookInformation.setBuyDate(toursProduct.getPageCalendar().getRandomTourDate());
		    logger.debug("随机日期为： "  + productBookInformation.getBuyDate());
		    
		}
		
		toursProduct.getPageCalendar().selectTourDate(productBookInformation.getBuyDate());
		

		//日历页面操作完毕，点击下一步进入后续流程
		logger.debug("价格日历页点击下一步按钮");
		toursProduct.getPageCalendar().clickNextStepBtn();
		
		logger.debug("判断是否已离开价格日历页");
		if (!toursProduct.getPageCalendar().waitLeaveCurrentPage(5)) {
		    logger.error("价格日历页点击确定后，超过5s仍然停留在当前页面");
		    saveOrderResult.setSuccess(false);
		    saveOrderResult.setMsg("价格日历页点击确定后，超过5s仍然停留在当前页面");
		    return saveOrderResult;
		}
		
		
		
		
		//判断是否有重单
		logger.debug("检查是否有重单流程");
		if (PageIdentifyCode.PageProductRepeatOrder == PageUtil.getCurrentPageIdentifyCode(toursProduct.getPageDuplicateOrder().getAndroidDriver())) {
			logger.debug("存在重单，开始处理");
			toursProduct.getPageDuplicateOrder().waitPageLoadReady(30);
			toursProduct.getPageDuplicateOrder().clickNextStepBtn();
		}
		
		//暂不处理
/*		logger.debug("检查是否进入网络单流程");
		//判断是否网络单
		if(PageProductFastOrder.checkWhetherFastOrder()){
			logger.debug("未进入预订流程，进入快速网络单流程");
			PageProductFastOrder.saveFastOrder();
			ScreenShot.getScreenShot(driver);
			logger.error("未进入预订流程，走快速网络单流程");
			saveOrderResult.setSuccess(Boolean.FALSE);
			saveOrderResult.setMsg("未进入预订流程，走快速网络单流程");
			return saveOrderResult;
		}*/
		
		/**
		 * 进入1/2流程
		 */
		
		logger.debug("进入1/2流程,等待页面加载完毕");
		//判断1/2是否露出下一步按钮
		if (!toursProduct.getPageStepOne().waitPageLoadReady(50)) {
			logger.error("1/2加载异常，等待50秒仍然没有展示");
			saveOrderResult.setSuccess(Boolean.FALSE);
			saveOrderResult.setMsg("1/2加载异常，等待50秒仍然没有展示");
			return saveOrderResult;
		}
		
		/*//判断1/2下一步按钮是否打开,仍然没打开的话，可能存在异常了
		if (!PageProductStepOne.checkWhetherPageNextEnable()) {
			//存在飞机票异常浮层
			if(PageProductStepOne.checkWhetherExistFlightDialog()){
				if(PageProductFastOrder.checkWhetherFastOrder()){
					PageProductFastOrder.saveFastOrder();
					ScreenShot.getScreenShot(driver);
					logger.error("由于机票异常，走快速网络单流程");
					saveOrderResult.setSuccess(Boolean.FALSE);
					saveOrderResult.setMsg("由于机票异常，走快速网络单流程");
					return saveOrderResult;
				}
			}
		}*/
		
		//机票异常代码尚未处理，只留了空架子，后续针对性补充代码
		//判断是否有机票异常弹窗
		logger.debug("检查1/2页面是否存在机票异常模块");
		if (toursProduct.getPageStepOne().isExistFlightExceptionDialog()) {
		    
		}
		
		//判断机票线路productMode:7是否存在机票价格变更弹框，存在则点击确定继续
		logger.debug("检查1/2页面是否存在机票价格变更模块");
		if (productBookInformation.getProductMode()==7){
			if(toursProduct.getPageStepOne().isExistFlightPriceChangeDialog()){
				toursProduct.getPageStepOne().clickPriceChangeBtn();
			}
		}
		
		//处理必选产品（一项起选附加项目）模块
		if (toursProduct.getPageStepOne().waitExistAdditionalItemModule(1)) {
		    logger.debug("存在必选产品模块");
		    //循环处理必选产选择日期问题，目前附加项目基本都需要选择使用日期
		    for (int i = 1; i <= toursProduct.getPageStepOne().getCountOfAdditionalItemModule(); i++) {
			//logger.debug("当前处理附加项目名称：" +toursProduct.getPageStepOne().getAdditionalItemTitleName(i) );
			//由于必选产品附加项目都是一项起选，所以默认只会勾选每个附加项目中的第一个资源，此处也这么处理，如果以后需要复杂操作再修改此处代码即可
			toursProduct.getPageStepOne().selectUseTimeOfAdditionalItem(i, 0);
		    }
		}
		
		//继续走下单流程进入2/2页面
		logger.debug("1/2页面点击下一步按钮");
		toursProduct.getPageStepOne().clickNextStepBtn();
		
		//进入2/2页面流程
		//检查页面是否加载完毕
		logger.debug("等待2/2页面加载完毕");
		if(!toursProduct.getPageStepTwo().waitPageLoadReady(30)){
			logger.error("2/2页面超过30s仍然未加载完毕");
			saveOrderResult.setSuccess(Boolean.FALSE);
			saveOrderResult.setMsg("2/2页面超过30s仍然未加载完毕");
			return saveOrderResult;
		}
		
		//选择出游人
		//火车票资源时另外处理----跟团所有的都区分成人、儿童选择出游人
		logger.debug("开始选择出游人");
		//if (productBookInformation.getProductMode()==2) {
			//选择成人
		    	logger.debug("选择成人");
			if (productBookInformation.getAdultNum()>0) {
			    	toursProduct.getPageStepTwo().clickAdultTouristBtn();
			    	toursProduct.getPageTourist().waitPageLoadReady(30);
			    	toursProduct.getPageTourist().selectTourist(productBookInformation.getAdultNum());
			    	toursProduct.getPageTourist().clickNextStepBtn();
			    	
			}
			
			//选择儿童
			logger.debug("选择儿童");
			if (productBookInformation.getChildNum()>0) {
			    	toursProduct.getPageStepTwo().waitPageLoadReady(30);
				ElementUtil.waitElementPresentOnDOMOnExpectTime(toursProduct.getPageStepTwo().getAndroidDriver(), By.id("tourist-child"), 10);
				
				//滚动页面到底部
				toursProduct.getPageStepTwo().scrollToBottomByNativeSwipe();
				
				toursProduct.getPageStepTwo().clickChildTouristBtn();
				
				toursProduct.getPageTourist().waitPageLoadReady(30);
				toursProduct.getPageTourist().selectTourist(productBookInformation.getChildNum());
				toursProduct.getPageTourist().clickNextStepBtn();
			}
			
			//选择免票儿童
			logger.debug("选择免票儿童");
			if (productBookInformation.getChildFreedNum() >0) {
			    	
			    	toursProduct.getPageStepTwo().waitPageLoadReady(30);
			    	ElementUtil.waitElementPresentOnDOMOnExpectTime(toursProduct.getPageStepTwo().getAndroidDriver(), By.id("tourist-free-child"), 30);
			    	

				//滚动页面到底部
			    	toursProduct.getPageStepTwo().scrollToBottomByNativeSwipe();
			    	
			    	toursProduct.getPageStepTwo().clickFreeChildTouristBtn();
			    	toursProduct.getPageTourist().waitPageLoadReady(30);
			    	toursProduct.getPageTourist().selectTourist(productBookInformation.getChildFreedNum());
			    	toursProduct.getPageTourist().clickNextStepBtn();
			}
			
		//} else {
		    //其他采购方式直接点击编辑游客信息按钮选择出游人
		//    	toursProduct.getPageStepTwo().clickCommonTouristBtn();
		//    	toursProduct.getPageTourist().waitPageLoadReady(30);
		//    	toursProduct.getPageTourist().selectTourist(productBookInformation.getAdultNum() + productBookInformation.getChildNum() + productBookInformation.getChildFreedNum());
		//    	toursProduct.getPageTourist().clickNextStepBtn();
		//}

		//等待页面加载完毕 
		toursProduct.getPageStepTwo().waitPageLoadReady(30);
		
		//判断是否存在签注类型选择框，如果有则选择L签
		if (toursProduct.getPageStepTwo().isExitVisaDetail(2)) {
		    logger.debug("存在签注类型选择框");
		    //循环处理必选产选择日期问题，目前附加项目基本都需要选择使用日期
		    toursProduct.getPageStepTwo().selectAllVisaDetail(productBookInformation.getAdultNum(),productBookInformation.getChildNum());
		}
		
		//滚动页面到底部
		//PageProductStepTwo.scrollToBottomByJs();
		toursProduct.getPageStepTwo().scrollToBottomByNativeSwipe();
		toursProduct.getPageStepTwo().scrollToBottomByJs();
	
		
		//选择出游协议
		logger.debug("2/2页面勾选协议");
		toursProduct.getPageStepTwo().selectLimitAgreeBtn();
		toursProduct.getPageStepTwo().selectNoteAgreeBtn();
		

		
		
		//处理react页面特殊情况
		//PageProductStepTwo.clickElement(By.id("nextStep"));
		logger.debug("2/2页面点击下一步按钮");
		toursProduct.getPageStepTwo().clickNextStepBtn();
		
		//获取订单ID
		logger.debug("2/2页面获取下单结果情况");
		toursProduct.getPageStepTwo().getSaveOrderResult(saveOrderResult);

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
    
    /**
     * 跟团、跟队自驾、常规自驾打包品类下单流程;跟团、自助生僻字校验功能;免登陆功能;检查更改机票功能
     * @param toursProduct 产品实例
     * @param testUnit 测试用例信息
     * @param currentTask 本次执行任务，任务定义在 ToursTask类
     * @return 测试用例执行结果
     * 
     */
    public SaveOrderResult ToursTaskActions(ToursProduct toursProduct,TestUnit testUnit,int currentTask){
	SaveOrderResult saveOrderResult = new SaveOrderResult(testUnit);
	ProductBookInformation productBookInformation =testUnit.getProductBookInformation();
	
	try {
		
	    
	    	
		//保存预订对象下单信息
		//saveOrderResult.setProductBookInformation(productBookInformation);
		
		//组装待访问产品url
		String StrPrdUrl = "https://" + productBookInformation.getHostUrl() + "/tours/" + productBookInformation.getProductID(); // concat visit product url
		
		/**
		 * 打开产品详情页
		 */
		logger.debug("开始打开产品详情，进入测试流程");
		toursProduct.getPageDetail().visitProduct(StrPrdUrl);
		
		logger.debug("等待详情页页面加载完毕");
		if (!toursProduct.getPageDetail().waitPageLoadReady(30)) {
		    logger.error("等待30s详情页仍然未加载完毕");
		    saveOrderResult.setSuccess(false);
		    saveOrderResult.setMsg("等待30s详情页仍然未加载完毕");
		    return saveOrderResult;
		}
		
		//判断产品是否存在问题
		if(ElementUtil.isExist(toursProduct.getPageDetail().getAndroidDriver(), By.id("formBtn"))){
		    logger.error("访问的产品不存在");
		    saveOrderResult.setSuccess(false);
		    saveOrderResult.setMsg("访问的产品不存在");
		    return saveOrderResult;
		}
			
		
		logger.debug("点击详情页下一步按钮");
		toursProduct.getPageDetail().clickNextStepBtn();
		
		/**
		 * 进入价格日历页
		 * 
		 */
		logger.debug("进入价格日历页面");
		//等待价格日历页面加载完毕
		logger.debug("等待价格日历页加载完毕");
		if(!toursProduct.getPageCalendar().waitPageLoadReady(30)){
		    logger.error("价格日历页超过30s仍然未加载完毕");
		    saveOrderResult.setSuccess(false);
		    saveOrderResult.setMsg("价格日历页超过30s仍然未加载完毕");
		    return saveOrderResult;
		}
		
		
		//引入zepto到当前页面
		toursProduct.getPageCalendar().ImportZepto();
		
		//选择出游人
		logger.debug("价格日历页选择成人出游人数");
		toursProduct.getPageCalendar().selectAdult(productBookInformation.getAdultNum());
		logger.debug("价格日历页选择儿童出游人数");
		toursProduct.getPageCalendar().selectChild(productBookInformation.getChildNum());

		//采购方式为火车票地接
		if(productBookInformation.getProductMode()==2){
			logger.debug("价格日历页面选择免票儿童出游人数");
			toursProduct.getPageCalendar().selectFreeChild(productBookInformation.getChildFreedNum());
		}
		
		
		//选择出游日期
		logger.debug("选择出游日期");
		if (productBookInformation.getBuyDate().trim().length()==0) {
		    
		    logger.debug("价格日历出游日期为空，随机选择一个出游日期");
		    productBookInformation.setBuyDate(toursProduct.getPageCalendar().getRandomTourDate());
		    logger.debug("随机日期为： "  + productBookInformation.getBuyDate());
		    
		}
		
		toursProduct.getPageCalendar().selectTourDate(productBookInformation.getBuyDate());
		

		//日历页面操作完毕，点击下一步进入后续流程
		logger.debug("价格日历页点击下一步按钮");
		toursProduct.getPageCalendar().clickNextStepBtn();
		
		logger.debug("判断是否已离开价格日历页");
		if (!toursProduct.getPageCalendar().waitLeaveCurrentPage(5)) {
		    logger.error("价格日历页点击确定后，超过5s仍然停留在当前页面");
		    saveOrderResult.setSuccess(false);
		    saveOrderResult.setMsg("价格日历页点击确定后，超过5s仍然停留在当前页面");
		    return saveOrderResult;
		}
		
		
		
		
		//判断是否有重单
		logger.debug("检查是否有重单流程");
		if (PageIdentifyCode.PageProductRepeatOrder == PageUtil.getCurrentPageIdentifyCode(toursProduct.getPageDuplicateOrder().getAndroidDriver())) {
			logger.debug("存在重单，开始处理");
			toursProduct.getPageDuplicateOrder().waitPageLoadReady(30);
			toursProduct.getPageDuplicateOrder().clickNextStepBtn();
		}
		
		//暂不处理
/*		logger.debug("检查是否进入网络单流程");
		//判断是否网络单
		if(PageProductFastOrder.checkWhetherFastOrder()){
			logger.debug("未进入预订流程，进入快速网络单流程");
			PageProductFastOrder.saveFastOrder();
			ScreenShot.getScreenShot(driver);
			logger.error("未进入预订流程，走快速网络单流程");
			saveOrderResult.setSuccess(Boolean.FALSE);
			saveOrderResult.setMsg("未进入预订流程，走快速网络单流程");
			return saveOrderResult;
		}*/
		
		/**
		 * 进入1/2流程
		 */
		
		logger.debug("进入1/2流程,等待页面加载完毕");
		//判断1/2是否露出下一步按钮
		if (!toursProduct.getPageStepOne().waitPageLoadReady(50)) {
			logger.error("1/2加载异常，等待50秒仍然没有展示");
			saveOrderResult.setSuccess(Boolean.FALSE);
			saveOrderResult.setMsg("1/2加载异常，等待50秒仍然没有展示");
			return saveOrderResult;
		}
		
		/*//判断1/2下一步按钮是否打开,仍然没打开的话，可能存在异常了
		if (!PageProductStepOne.checkWhetherPageNextEnable()) {
			//存在飞机票异常浮层
			if(PageProductStepOne.checkWhetherExistFlightDialog()){
				if(PageProductFastOrder.checkWhetherFastOrder()){
					PageProductFastOrder.saveFastOrder();
					ScreenShot.getScreenShot(driver);
					logger.error("由于机票异常，走快速网络单流程");
					saveOrderResult.setSuccess(Boolean.FALSE);
					saveOrderResult.setMsg("由于机票异常，走快速网络单流程");
					return saveOrderResult;
				}
			}
		}*/
		
		//机票异常代码尚未处理，只留了空架子，后续针对性补充代码
		//判断是否有机票异常弹窗
		logger.debug("检查1/2页面是否存在机票异常模块");
		if (toursProduct.getPageStepOne().isExistFlightExceptionDialog()) {
				    
		}
		
		//判断机票线路productMode:7是否存在机票价格变更弹框，存在则点击确定继续
		logger.debug("检查1/2页面是否存在机票价格变更模块");
		if (productBookInformation.getProductMode()==7){
			if(toursProduct.getPageStepOne().isExistFlightPriceChangeDialog()){
				toursProduct.getPageStepOne().clickPriceChangeBtn();
			}
		}
		
		//检查更改机票功能
		if (currentTask == ToursTasks.CheckChangeSingleFlight){
			logger.debug("检查更改机票功能");
			//在详情页获取机票乘数
			int flightNum = toursProduct.getPageChangeFlight().getFlightNum();
			logger.debug("点击更改机票按钮");
			toursProduct.getPageChangeFlight().clickChangeFlightsBtn();
			//检查更改机票页面是否加载完成
			if(!toursProduct.getPageChangeFlight().waitPageLoadReady(20)){
				logger.error("更改机票页面超过20s仍然未加载完毕");
				saveOrderResult.setSuccess(Boolean.FALSE);
				saveOrderResult.setMsg("更改机票页面超过20s仍然未加载完毕");
				return saveOrderResult;
			}
			logger.debug("点击第一个机票的预定须知");
			toursProduct.getPageChangeFlight().clickFlightNotice();
			logger.debug("机票随机一种筛选功能");
			toursProduct.getPageChangeFlight().siftingFlight();
			//检查筛选后机票是否刷新  只判断页面是否有机票  后续再做不同筛选功能的检查
			toursProduct.getPageChangeFlight().checkSelectedFlightResult(2);
			logger.debug("机票随机一种排序功能");
			toursProduct.getPageChangeFlight().sortFlight();
			//检查排序后机票是否刷新  只判断页面是否有机票  后续再做不同排序功能的检查
			toursProduct.getPageChangeFlight().checkSelectedFlightResult(2);
			logger.debug("选择第一程机票后点击返回按钮返回");
			toursProduct.getPageChangeFlight().slctFlightThenBack();
			//随机选择所有乘数的机票并回到1/2
			logger.debug("机票乘数："+flightNum);
			logger.debug("随机选择所有程数的机票并回到1/2");
			for(int i=1;i<=flightNum;i++){
				logger.debug("选择机票程数"+i);
				if(!toursProduct.getPageChangeFlight().waitPageLoadReady(20)){
					logger.error("机票列表页加载异常");
					saveOrderResult.setSuccess(Boolean.FALSE);
					saveOrderResult.setMsg("机票列表页加载异常");
					return saveOrderResult;
				}
				int countFlight = toursProduct.getPageChangeFlight().countFlightNum();
				int slcFlight = toursProduct.getPageChangeFlight().creRangeRanNum(countFlight);
				logger.debug("机票个数"+countFlight+"选择第几个机票"+slcFlight);
				if(toursProduct.getPageChangeFlight().selectFlight(slcFlight)){
					while(toursProduct.getPageChangeFlight().isExitFlightLimit(5)){  //判断是否存在预定限制弹框，有则点击掉重新选择
						toursProduct.getPageChangeFlight().clickFlightLimit();
						slcFlight = toursProduct.getPageChangeFlight().creRangeRanNum(countFlight);
						toursProduct.getPageChangeFlight().selectFlight(slcFlight);
					}
					continue;
				}else{
					logger.error("随机选择所有程数的机票异常");
					saveOrderResult.setSuccess(Boolean.FALSE);
					saveOrderResult.setMsg("随机选择所有程数的机票异常");
					return saveOrderResult;
				}
			}
			if(!toursProduct.getPageChangeFlight().isSelcFlightBack(30)){
				logger.error("选择完机票回到1/2失败");
				saveOrderResult.setSuccess(Boolean.FALSE);
				saveOrderResult.setMsg("选择完机票回到1/2失败");
				return saveOrderResult;
			}else{
				saveOrderResult.setSuccess(true);
				saveOrderResult.setMsg("检查更改机票功能完成");
				return saveOrderResult;
			}	
		}
		
		//检查更改火车票功能
		if (currentTask == ToursTasks.CheckChangeTrain){
			logger.debug("检查更改火车票功能");
			logger.debug("点击更改火车票按钮");
			toursProduct.getPageChangeTrain().clickChangeTrainBtn();
			//检查更改机票页面是否加载完成
			if(!toursProduct.getPageChangeTrain().waitPageLoadReady(20)){
				logger.error("更改火车票页面超过20s仍然未加载完毕");
				saveOrderResult.setSuccess(Boolean.FALSE);
				saveOrderResult.setMsg("更改火车票页面超过20s仍然未加载完毕");
				return saveOrderResult;
			}
			logger.debug("火车票随机一种筛选功能");
			toursProduct.getPageStepTwo().scrollToBottomByJs();
			toursProduct.getPageChangeTrain().scrollToTopByJs();
			toursProduct.getPageChangeTrain().siftingTrain();
			//检查筛选后火车票是否刷新  只判断页面是否有火车票  后续再做不同筛选功能的检查
			toursProduct.getPageChangeTrain().checkSelectedTrainResult(2);
			logger.debug("火车票随机一种排序功能");
			toursProduct.getPageChangeTrain().sortTrain();
			//检查排序后火车票是否刷新  只判断页面是否有火车票  后续再做不同排序功能的检查
			toursProduct.getPageChangeTrain().checkSelectedTrainResult(2);
			logger.debug("选择第一程火车票后点击返回按钮返回");
			toursProduct.getPageChangeTrain().slctTrainThenBack();
			//随机选择所有乘数的火车票并回到1/2
			logger.debug("随机选择所有程数的火车票并回到1/2");
			for(int i=1;i<=2;i++){
				logger.debug("选择火车票程数"+i);
				if(!toursProduct.getPageChangeTrain().waitPageLoadReady(20)){
					logger.error("火车票列表页加载异常");
					saveOrderResult.setSuccess(Boolean.FALSE);
					saveOrderResult.setMsg("火车票列表页加载异常");
					return saveOrderResult;
				}
				int countTrain = toursProduct.getPageChangeTrain().countTrainNum();
				int slcTrain = toursProduct.getPageChangeTrain().creRangeRanNum(countTrain);
				logger.debug("火车票个数"+countTrain+"选择第几个火车票"+slcTrain);
				if(!toursProduct.getPageChangeTrain().selectTrain(slcTrain)){
					logger.error("随机选择所有程数的火车票异常");
					saveOrderResult.setSuccess(Boolean.FALSE);
					saveOrderResult.setMsg("随机选择所有程数的火车票异常");
					return saveOrderResult;
				}
			}
			if(!toursProduct.getPageChangeTrain().isSelcTrainBack(30)){
				logger.error("选择完火车票回到1/2失败");
				saveOrderResult.setSuccess(Boolean.FALSE);
				saveOrderResult.setMsg("选择完火车票回到1/2失败");
				return saveOrderResult;
			}else{
				saveOrderResult.setSuccess(true);
				saveOrderResult.setMsg("检查更改火车票功能完成");
				return saveOrderResult;
			}		
		}
		
		//处理必选产品（一项起选附加项目）模块
		if (toursProduct.getPageStepOne().waitExistAdditionalItemModule(1)) {
		    logger.debug("存在必选产品模块");
		    //循环处理必选产选择日期问题，目前附加项目基本都需要选择使用日期
		    for (int i = 1; i < toursProduct.getPageStepOne().getCountOfAdditionalItemModule(); i++) {
			logger.debug("当前处理附加项目名称：" +toursProduct.getPageStepOne().getAdditionalItemTitleName(i) );
			//由于必选产品附加项目都是一项起选，所以默认只会勾选每个附加项目中的第一个资源，此处也这么处理，如果以后需要复杂操作再修改此处代码即可
			toursProduct.getPageStepOne().selectUseTimeOfAdditionalItem(i, 0);
		    }
		}
		
		//未登录状态，选择返赠品优惠并下单功能验证
		if (currentTask == ToursTasks.FreeGiftPromation){
			logger.debug("未登录状态，选择返赠品优惠并下单功能验证");
			toursProduct.getPageStepTwo().scrollToBottomByNativeSwipe();
			if(toursProduct.getPageFreeGiftPromation().isExitFreeGiftPro(5))
				toursProduct.getPageFreeGiftPromation().clickAddAddress();
			else{
				logger.error("不存在返赠品优惠的添加地址模块");
				saveOrderResult.setSuccess(Boolean.FALSE);
				saveOrderResult.setMsg("不存在返赠品优惠的添加地址模块");
				return saveOrderResult;
			}
			if(toursProduct.getPageFreeGiftPromation().isExistLoginButt(6)){
				if(!toursProduct.getPageFreeGiftPromation().isLoginSuccess(productBookInformation.getHostUrl())){
					logger.error("cookie登录失败");
					saveOrderResult.setSuccess(Boolean.FALSE);
					saveOrderResult.setMsg("cookie登录失败");
					return saveOrderResult;
				}
			}
			logger.debug("选择第一个地址回到1/2");
			toursProduct.getPageFreeGiftPromation().selcFirstAddress();
			toursProduct.getPageStepTwo().scrollToBottomByNativeSwipe();
			logger.debug("勾选返赠品优惠继续下单");
			toursProduct.getPageFreeGiftPromation().slecFreeGiftPromation(15);
		}
		
		//继续走下单流程进入2/2页面
		logger.debug("1/2页面点击下一步按钮");
		toursProduct.getPageStepOne().clickNextStepBtn();
		
		//进入2/2页面流程
		//检查页面是否加载完毕
		logger.debug("等待2/2页面加载完毕");
		if(!toursProduct.getPageStepTwo().waitPageLoadReady(30)){
			logger.error("2/2页面超过30s仍然未加载完毕");
			saveOrderResult.setSuccess(Boolean.FALSE);
			saveOrderResult.setMsg("2/2页面超过30s仍然未加载完毕");
			return saveOrderResult;
		}
		
		PageUtil.ImportZepto(toursProduct.getPageStepTwo().getAndroidDriver());
		//判断是否是未登录状态，验证预订流程登录
		if (currentTask == ToursTasks.CheckLoginOnBookProcess) {
		    logger.debug("验证预订流程登录功能");
		    logger.debug("验证用户名");
		    //填写用户名
		    toursProduct.getPageStepTwo().setUserNameOfContactModule(ToursLoginOnStepTwo.contactInformation.get().getUserName());
		    
		    //验证用户名
		    if (!ToursLoginOnStepTwo.contactInformation.get().getUserName().equalsIgnoreCase(toursProduct.getPageStepTwo().getUserNameOfContactModule())) {
			logger.debug("验证用户名错误");
			saveOrderResult.setSuccess(false);
			saveOrderResult.setMsg("验证用户名错误");
			return saveOrderResult;
		    }
		    //修改区号
		    logger.debug("修改区号功能");
		    if (ToursLoginOnStepTwo.contactInformation.get().getAreaCode()!=null && !ToursLoginOnStepTwo.contactInformation.get().getAreaCode().isEmpty()) {
			toursProduct.getPageStepTwo().setAreaCodeOfContactModule(ToursLoginOnStepTwo.contactInformation.get().getAreaCode());
			logger.debug("等待2/2页面加载完毕");
			if(!toursProduct.getPageStepTwo().waitPageLoadReady(30)){
				logger.error("2/2页面超过30s仍然未加载完毕");
				saveOrderResult.setSuccess(Boolean.FALSE);
				saveOrderResult.setMsg("2/2页面超过30s仍然未加载完毕");
				return saveOrderResult;
			}
			PageUtil.ImportZepto(toursProduct.getPageStepTwo().getAndroidDriver());
		    }
		    
		    logger.debug("验证输入手机号码功能");
		    //设置手机号码
		    toursProduct.getPageStepTwo().setTelOfContactModule(ToursLoginOnStepTwo.contactInformation.get().getUserTel());
		    
		    if (!ToursLoginOnStepTwo.contactInformation.get().getUserTel().equalsIgnoreCase( toursProduct.getPageStepTwo().getTelOfContactModule())) {
			logger.debug("验证手机号码错误");
			saveOrderResult.setSuccess(false);
			saveOrderResult.setMsg("验证手机号码错误");
			return saveOrderResult;
		    }
		    
		    logger.debug("验证登录frame功能");
		    //判断是否出现登录frame，由于输入后需要离开手机号码输入框才会触发事件，需要额外处理下,比如点击名称输入框
		    //由于还没有开发绕过登录的功能，暂不做其他处理
		    toursProduct.getPageStepTwo().find(By.id("realname")).click();
		    if (toursProduct.getPageStepTwo().waitExistLoginFrameOfContactModule(5)) {
			logger.debug("出现登录frame，还可以进一步处理，后续再补充功能了");
			
			//验证码输入框有可能不存在，添加额外处理
			toursProduct.getPageStepTwo().getAndroidDriver().switchTo().frame("loginIframe");
			if(ElementUtil.waitVisibilityOnExpectTime(toursProduct.getPageStepTwo().getAndroidDriver(), By.xpath("//input[@class='identify_code']"), 1)){
				toursProduct.getPageStepTwo().getAndroidDriver().switchTo().defaultContent();
				toursProduct.getPageStepTwo().setValidateTextOfContactModule("1111");				
			}
			
			toursProduct.getPageStepTwo().setPasswordOfContactModule("222222");
			toursProduct.getPageStepTwo().clickLoginBtnOfContactModule();
		    }
		    
		    //设置登录邮箱
		    toursProduct.getPageStepTwo().setUserEmailOfContactModule(ToursLoginOnStepTwo.contactInformation.get().getUserEmail());
		    
		    if (!ToursLoginOnStepTwo.contactInformation.get().getUserEmail().equalsIgnoreCase( toursProduct.getPageStepTwo().getUserEmailOfContactModule())) {
			logger.debug("验证邮箱错误");
			saveOrderResult.setSuccess(false);
			saveOrderResult.setMsg("验证邮箱错误");
			return saveOrderResult;
		    }
		    
		    //运行到这边基本上说明上面没错错误，可返回结果了
		    saveOrderResult.setSuccess(true);
		    saveOrderResult.setMsg("验证预订流程登录功能成功");
		    return saveOrderResult;
		}
		
		//选择出游人
		//火车票资源时另外处理---跟团选择出游人区分成人、儿童
		logger.debug("开始选择出游人");
		//if (productBookInformation.getProductMode()==2) {
			//选择成人
		    	logger.debug("选择成人");
			if (productBookInformation.getAdultNum()>0) {
			    	toursProduct.getPageStepTwo().clickAdultTouristBtn();
			    	toursProduct.getPageTourist().waitPageLoadReady(30);
			    	if (ToursTasks.UncommonUserName==currentTask) {
			    	    //选择指定的含有生僻字的人员
			    	    logger.debug("生僻字校验服务，出游人选择指定含有生僻字的出游人");
			    	    toursProduct.getPageTourist().selectSpecialTourist(ToursUncommonUserName.uncommonUserName.get());
			    	    //此处不需要额外减一，函数里做了处理
			    	    toursProduct.getPageTourist().selectTourist(productBookInformation.getAdultNum());
				}else{
			    	toursProduct.getPageTourist().selectTourist(productBookInformation.getAdultNum());
				}
			    	toursProduct.getPageTourist().clickNextStepBtn();
			    	
			}
			
			//选择儿童
			logger.debug("选择儿童");
			if (productBookInformation.getChildNum()>0) {
			    	toursProduct.getPageStepTwo().waitPageLoadReady(30);
				ElementUtil.waitElementPresentOnDOMOnExpectTime(toursProduct.getPageStepTwo().getAndroidDriver(), By.id("tourist-child"), 10);
				
				//滚动页面到底部
				toursProduct.getPageStepTwo().scrollToBottomByNativeSwipe();
				
				toursProduct.getPageStepTwo().clickChildTouristBtn();
				
				toursProduct.getPageTourist().waitPageLoadReady(30);
				toursProduct.getPageTourist().selectTourist(productBookInformation.getChildNum());
				toursProduct.getPageTourist().clickNextStepBtn();
			}
			
			//选择免票儿童
			logger.debug("选择免票儿童");
			if (productBookInformation.getChildFreedNum() >0) {
			    	
			    	toursProduct.getPageStepTwo().waitPageLoadReady(30);
			    	ElementUtil.waitElementPresentOnDOMOnExpectTime(toursProduct.getPageStepTwo().getAndroidDriver(), By.id("tourist-free-child"), 30);
			    	

				//滚动页面到底部
			    	toursProduct.getPageStepTwo().scrollToBottomByNativeSwipe();
			    	
			    	toursProduct.getPageStepTwo().clickFreeChildTouristBtn();
			    	toursProduct.getPageTourist().waitPageLoadReady(30);
			    	toursProduct.getPageTourist().selectTourist(productBookInformation.getChildFreedNum());
			    	toursProduct.getPageTourist().clickNextStepBtn();
			}
			
		//} else {
		    //其他采购方式直接点击编辑游客信息按钮选择出游人
		    /*	toursProduct.getPageStepTwo().clickCommonTouristBtn();
		    	toursProduct.getPageTourist().waitPageLoadReady(30);
		    	
		    	//处理如果任务为生僻字功能时，需要选择名字包含生僻字的出游人
		    	if (ToursTasks.UncommonUserName==currentTask) {
		    	    //选择指定的含有生僻字的人员
		    	    logger.debug("生僻字校验服务，出游人选择指定含有生僻字的出游人");
		    	    toursProduct.getPageTourist().selectSpecialTourist(ToursUncommonUserName.uncommonUserName.get());
		    	    //此处不需要额外减一，函数里做了处理
		    	    toursProduct.getPageTourist().selectTourist(productBookInformation.getAdultNum() + productBookInformation.getChildNum() + productBookInformation.getChildFreedNum());
			}else {
			    toursProduct.getPageTourist().selectTourist(productBookInformation.getAdultNum() + productBookInformation.getChildNum() + productBookInformation.getChildFreedNum()); 
			}
		    	
		    	toursProduct.getPageTourist().clickNextStepBtn();  */
		//}

		//等待页面加载完毕 
		toursProduct.getPageStepTwo().waitPageLoadReady(30);
		
		//判断是否存在签注类型选择框，如果有则选择L签
		if (toursProduct.getPageStepTwo().isExitVisaDetail(2)) {
			logger.debug("存在签注类型选择框");
			//循环处理必选产选择日期问题，目前附加项目基本都需要选择使用日期
			toursProduct.getPageStepTwo().selectAllVisaDetail(productBookInformation.getAdultNum(),productBookInformation.getChildNum());
		}
		
		//滚动页面到底部
		//PageProductStepTwo.scrollToBottomByJs();
		toursProduct.getPageStepTwo().scrollToBottomByNativeSwipe();
		toursProduct.getPageStepTwo().scrollToBottomByJs();
	
		
		//选择出游协议
		logger.debug("2/2页面勾选协议");
		toursProduct.getPageStepTwo().selectLimitAgreeBtn();
		toursProduct.getPageStepTwo().selectNoteAgreeBtn();
		

		
		
		//处理react页面特殊情况
		//PageProductStepTwo.clickElement(By.id("nextStep"));
		//下单及生僻字功能都需要点击下一步按钮
		if (ToursTasks.SaveOrder==currentTask || ToursTasks.UncommonUserName==currentTask || ToursTasks.FreeGiftPromation==currentTask) {
		    logger.debug("2/2页面点击下一步按钮");
		    toursProduct.getPageStepTwo().clickNextStepBtn(); 
		}
		
		
		/**
		 * 判断是否是生僻字事务，生僻字功能会弹出生僻字校验弹窗
		 */
		
		    //判断是否弹窗弹窗
		    logger.debug("循环判断是否存在生僻字校验弹窗");
		    while(toursProduct.getPageStepTwo().waitExistUnCommonUserNameModule(5)){
			logger.debug("存在生僻字校验弹窗");
			//生僻字校验功能逻辑校验
			if (ToursTasks.UncommonUserName==currentTask) {
			    logger.debug("进入校验生僻字功能流程");
			    logger.debug("校验生僻字弹窗标题");
			    //验证弹窗titile
			    if(!(toursProduct.getPageStepTwo().typeOfUnCommonUserNameModule()==1) && !(toursProduct.getPageStepTwo().typeOfUnCommonUserNameModule()==2) ){
				saveOrderResult.setSuccess(false);
				saveOrderResult.setMsg("生僻字校验功能异常，弹窗标题出错");
				return saveOrderResult;
			    }
			    
			    logger.debug("校验生僻字弹窗取消按钮功能");
			    //点击取消按钮
			    logger.debug("点击取消按钮");
			    toursProduct.getPageStepTwo().clickCancelBtnOfUnCommonUserNameModule();
			    
			    //验证取消按钮功能
			    if(toursProduct.getPageStepTwo().waitExistUnCommonUserNameModule(1)){
				    saveOrderResult.setSuccess(false);
				    saveOrderResult.setMsg("生僻字校验功能异常，点击取消按钮后生僻字弹窗仍然存在");
				    return saveOrderResult;
			    }
			    
			    logger.debug("再次发起提交订单，验证二次校验功能");
			    //验证确定按钮功能
			    logger.debug("2/2页面再次点击下一步按钮");
			    toursProduct.getPageStepTwo().clickNextStepBtn(); 
			    
			    
			    if(toursProduct.getPageStepTwo().waitExistUnCommonUserNameModule(5)){
				
				logger.debug("校验生僻字弹窗文本框输入功能");
				toursProduct.getPageStepTwo().setUserXingOfUnCommonUserNameModule("tao");
				toursProduct.getPageStepTwo().setUserMingOfUnCommonUserNameModule("zhe");
				
				//判断按钮是否可见，发现在小屏手机上这个按钮会被输入框遮挡
				if (ElementUtil.waitVisibilityOnExpectTime(toursProduct.getPageStepTwo().getAndroidDriver(), By.xpath("//p[@class='check_name_submit']"), 1)) {
				    //由于无法操作ime，通过点击其他位置以达到隐藏ime目的
				    toursProduct.getPageStepTwo().find(By.xpath("//p[@class='checkNameBox_title']")).click();
				    
				}
				
				//获取当前生僻字人的id
				String currentUserID= toursProduct.getPageStepTwo().find(By.xpath("//div[@class='checkNameBox']")).getAttribute("data-serial");
				logger.debug("当前出游人data-serial 为：" +currentUserID );
				
				
				logger.debug("校验生僻字弹窗，点击确定按钮");
				//点击确定按钮
				toursProduct.getPageStepTwo().clickConfirmBtnOfUnCommonUserNameModule();
				
				//窗口消失了
				if (ElementUtil.waitAttributeToBeOnExpectTime(toursProduct.getPageStepTwo().getAndroidDriver(), By.xpath("//div[@class='checkNameDialog']"), "style", "display: none;", 1)) {
				    logger.debug("生僻字弹窗消失，生僻字校验功能正常");
				    saveOrderResult.setSuccess(true);
				    saveOrderResult.setMsg("生僻字校验功能正常");
				    return saveOrderResult;
				}else //窗口没消失，当时ID变了（存在多个出游人生僻字的时候）
				if (toursProduct.getPageStepTwo().waitExistUnCommonUserNameModule(1)) {
				    logger.debug("生僻字弹窗仍然存在，检查是存在多个生僻字还是确定按钮功能异常");
				    String nowCurrentUserID= toursProduct.getPageStepTwo().find(By.xpath("//div[@class='checkNameBox']")).getAttribute("data-serial");
				    logger.debug("当前出游人data-serial 为：" +nowCurrentUserID );
				    if (!nowCurrentUserID.equalsIgnoreCase(currentUserID)) {
					logger.debug("存在多个生僻字用户，功能正常");
					saveOrderResult.setSuccess(true);
					saveOrderResult.setMsg("生僻字校验功能正常");
					return saveOrderResult;
				    }else {
					logger.debug("生僻字校验功能异常，两次弹窗出游人ID一样");
					saveOrderResult.setSuccess(false);
					saveOrderResult.setMsg("生僻字校验功能异常，两次弹窗出游人ID一样");
					return saveOrderResult;
				    }
				}else {
				    //所有其他情况出现，都是产生异常了
				    saveOrderResult.setSuccess(false);
				    saveOrderResult.setMsg("生僻字校验功能异常");
				    return saveOrderResult;
				}
				
				
				
			    }else {
				saveOrderResult.setSuccess(false);
				saveOrderResult.setMsg("生僻字校验功能异常，点击取消按钮后再次点击提交按钮，没有继续弹窗生僻字校验弹窗");
				return saveOrderResult;
			    }
			}else {
			    
			    logger.debug("进入常规生僻字处理流程");
			    toursProduct.getPageStepTwo().setUserXingOfUnCommonUserNameModule("tao");
			    toursProduct.getPageStepTwo().setUserMingOfUnCommonUserNameModule("zhe");
				
				//判断按钮是否可见，发现在小屏手机上这个按钮会被输入框遮挡
				if (!ElementUtil.waitVisibilityOnExpectTime(toursProduct.getPageStepTwo().getAndroidDriver(), By.xpath("//p[@class='check_name_submit']"), 1)) {
				    //由于无法操作ime，通过点击其他位置以达到隐藏ime目的
				    toursProduct.getPageStepTwo().find(By.xpath("//p[@class='checkNameBox_title']")).click();

				}
				//点击确定按钮
				toursProduct.getPageStepTwo().clickConfirmBtnOfUnCommonUserNameModule();
			}
			       
		    }
		    
		//获取订单ID
		logger.debug("2/2页面获取下单结果情况");
		toursProduct.getPageStepTwo().getSaveOrderResult(saveOrderResult);
		
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
