package com.monleylu.internal.testcase.diyflow;

import com.monleylu.internal.common.APPUtil;
import com.monleylu.internal.pageobject.FlightDialogType;
import com.monleylu.internal.pageobject.PageCode;
import com.monleylu.internal.pageobject.diy.DiyProduct;
import com.monleylu.internal.testcase.data.TestUnit;
import com.monleylu.internal.testcase.result.ProductBookInformation;
import com.monleylu.internal.testcase.result.SaveOrderResult;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static com.monleylu.internal.variable.GlobalVars.log;

/**
 * Created by monley_Lu on 2017/3/14.
 */
public class DiyBookFlow {


    /**
     * @param diyProduct  自助实例
     * @param testUnit    测试数据
     * @param currentFlow 当然任务
     * @return 测试用例执行结果
     */
    public SaveOrderResult driverBookFlow(DiyProduct diyProduct, TestUnit testUnit, int currentFlow) {

        SaveOrderResult saveOrderResult = new SaveOrderResult(testUnit);
        ProductBookInformation productBookInformation = testUnit.getProductBookInformation();

        log.debug("开始自动化测试预订流程业务");
        try {

            log.debug("初始化应用程序，返回首页");
            APPUtil.backToMianPage(diyProduct.getDriver());

            //汽车票产品时h5页面直接返回
            if (13==productBookInformation.getProductMode()){
                log.debug("汽车票产品时h5页面直接返回");
                saveOrderResult.setSuccess(false);
                saveOrderResult.setMsg("汽车票产品时h5页面直接返回");
                return saveOrderResult;
            }

            log.debug("开始访问产品");
            diyProduct.getPageDetail().visitProduct(productBookInformation.getProductID());

            log.debug("if exist product can not found");
            if (diyProduct.getPageSearchEmptyResult().waitPageLoadReady(5)) {
                log.debug("product can not found ");
                saveOrderResult.setSuccess(false);
                saveOrderResult.setMsg("product can not found");
                return saveOrderResult;
            }

            log.debug("wait detail page load ready");
            if (!diyProduct.getPageDetail().waitPageLoadReady(30)) {
                log.error("detail page load failed in 30 sec");
                saveOrderResult.setSuccess(false);
                saveOrderResult.setMsg("detail page load failed in 30 sec");
                return saveOrderResult;
            }

            log.debug("change tour date");
            //scroll about 200px
            APPUtil.swipeUpPageView(diyProduct.getDriver());
            diyProduct.getPageDetail().clickSelectCalendarBtn();

            log.debug("wait calendar page load ready");
            if (!diyProduct.getPageCalendar().waitPageLoadReady(30)) {
                log.debug("calendar page load failed in 30 sec");
                saveOrderResult.setSuccess(false);
                saveOrderResult.setMsg("calendar page load failed in 30 sec");
                return saveOrderResult;

            }

            diyProduct.getPageCalendar().selectTourDate(productBookInformation.getBuyDate());
            diyProduct.getPageCalendar().selectAdult(productBookInformation.getAdultNum());
            diyProduct.getPageCalendar().selectChild(productBookInformation.getChildNum());

            diyProduct.getPageCalendar().clickNextPageBtn();

            diyProduct.getPageDetail().waitPageLoadReady(30);

            diyProduct.getPageDetail().clickNextPageBtn();

            if (5 == productBookInformation.getProductMode()) {
                log.debug("if exist flight alert dialog");
                if (diyProduct.getPageDetail().isExistFlightDialog(10)) {
                    log.error("exist flight alert dialog");
                    switch (diyProduct.getPageDetail().flightAlertDialogType()) {
                        case FlightDialogType.SoldOut:
                            //详情页售空弹窗，点击取消，继续走预订流程
                            diyProduct.getPageDetail().clickConfirmBtnOfSellOutAlterDialog();
                            diyProduct.getPageDetail().clickNextPageBtn();
                            break;
                        case FlightDialogType.PriceChange:
                            //详情页价格变动弹窗
                            diyProduct.getPageDetail().clickContinueBookBtnOfPriceChangeAlertDialog();
                    }

                }
            }


            log.debug("enter stepone page flow");
            if (5 == productBookInformation.getProductMode()) {
                log.debug("first time , if exist flight dialog");
                if (diyProduct.getPageStepOne().isExistFlightDialog(30)) {
                    log.error("exist flight alert dialog");
                    switch (diyProduct.getPageStepOne().flightAlertDialogType()) {
                        case FlightDialogType.SoldOut:
                            //详情页售空弹窗，点击取消，继续走预订流程
                            log.debug("详情页售空弹窗，点击取消，继续走预订流程");
                            diyProduct.getPageStepOne().clickConfirmBtnOfSellOutAlterDialog();
                            break;
                        case FlightDialogType.PriceChange:
                            //详情页价格变动弹窗
                            log.debug("详情页价格变动弹窗");
                            diyProduct.getPageStepOne().clickContinueBookBtnOfPriceChangeAlertDialog();
                    }

                }
            }


            log.debug("wait page step one load ready");
            if (!diyProduct.getPageStepOne().waitPageLoadReady(30)) {
                log.error("stepone page load failed in 30 sec");
                saveOrderResult.setSuccess(false);
                saveOrderResult.setMsg("stepone page load failed in 30 sec");
                return saveOrderResult;
            }

            diyProduct.getPageStepOne().clickNextPageBtn();

            if (5 == productBookInformation.getProductMode()) {
                log.debug("second time , if exist flight dialog");
                if (diyProduct.getPageStepOne().isExistFlightDialog(5)) {
                    log.error("exist flight alert dialog");
                    switch (diyProduct.getPageStepOne().flightAlertDialogType()) {
                        case FlightDialogType.SoldOut:
                            //详情页售空弹窗，点击取消，继续走预订流程
                            log.debug("详情页售空弹窗，点击取消，继续走预订流程");
                            diyProduct.getPageStepOne().clickConfirmBtnOfSellOutAlterDialog();
                            break;
                        case FlightDialogType.PriceChange:
                            //详情页价格变动弹窗
                            log.debug("详情页价格变动弹窗");
                            diyProduct.getPageStepOne().clickContinueBookBtnOfPriceChangeAlertDialog();
                    }

                }
            }


            log.debug("wait steptwo page load ready");
            if (!diyProduct.getPageStepTwo().waitPageLoadReady(30)) {
                log.error("steptwo page load failed in 30 sec");
                saveOrderResult.setSuccess(false);
                saveOrderResult.setMsg("steptwo page load failed in 30 sec");
                return saveOrderResult;
            }

            diyProduct.getPageStepTwo().clickSelectTouristBtn();

            log.debug("wait selecttourist page load ready");
            if (!diyProduct.getPageTourists().waitPageLoadReady(30)) {
                log.error("selecttourist page load fail in 30 sec");
                saveOrderResult.setSuccess(false);
                saveOrderResult.setMsg("selecttourist page load fail in 30 sec");
                return saveOrderResult;
            }

            log.debug("start select tourist");
            diyProduct.getPageTourists().selectTourist(productBookInformation.getAdultNum() + productBookInformation.getChildNum());
            diyProduct.getPageTourists().clickNextPageBtn();

            log.debug("back to steptwo page");
            diyProduct.getPageStepTwo().waitPageLoadReady(30);
            diyProduct.getPageStepTwo().agreeAllNotice();

            log.debug("save order");
            diyProduct.getPageStepTwo().clickNextPageBtn();


            log.debug("loop to get save order status");
            boolean continueLoop = true;
            int loopCount = 0;
            while (continueLoop) {
                //生僻字

                //占位浮层

                //加载中浮层
                log.debug("if exist progressing view");
                if (diyProduct.getPageStepTwo().isVisibilityProgressBox(5)) {
                    log.debug("exist progressing view");
                    diyProduct.getPageStepTwo().isInVisibilityProgressBox(30);
                }

                //占位浮层
                log.debug("if exist occupy view");
                if (diyProduct.getPageStepTwo().isVisibilityOccupyBox(5)){
                    log.debug("exist occupy view");
                    diyProduct.getPageStepTwo().isInVisibilityOccupyBox(30);
                }

                log.debug("if exist alert dialog");
                if (diyProduct.getPageStepTwo().isExistAlertDialog(1)) {
                    log.debug("exist alert");
                    diyProduct.getPageStepTwo().clickConfirmBtnOfAlertBox();
                    saveOrderResult.setSuccess(true);
                    saveOrderResult.setMsg("save order success");
                    continueLoop = false;
                }

                //预订成功页
                log.debug("if exist book success");
                if (PageCode.PageProductBookSuccess == APPUtil.getCurrentPage(diyProduct.getDriver())) {
                    log.debug("book successs");
                    saveOrderResult.setSuccess(true);
                    saveOrderResult.setMsg("book success");
                    continueLoop = false;
                }

                //下单失败页
                log.debug("if exist book fail");

                //收银台
                log.debug("if exist casher page");
                if (PageCode.PageProductCasher == APPUtil.getCurrentPage(diyProduct.getDriver())) {
                    log.debug("log into Casher Page");
                    saveOrderResult.setSuccess(true);
                    saveOrderResult.setMsg("log into CasherPage");
                    continueLoop = false;
                }

                if (loopCount > 5) {
                    log.error("loop save order status failed");
                    saveOrderResult.setSuccess(false);
                    saveOrderResult.setMsg("loop save order status failed");
                    continueLoop = false;
                }
                loopCount++;

            }


            return saveOrderResult;

        } catch (Exception e) {

            e.printStackTrace();
            //保存日志
            ByteArrayOutputStream logArrayOutputStream = new ByteArrayOutputStream();
            e.printStackTrace(new PrintStream(logArrayOutputStream));
            log.error(logArrayOutputStream.toString());
            saveOrderResult.setSuccess(Boolean.FALSE);
            saveOrderResult.setMsg("下单流程出现异常，具体错误请查看日志");
            return saveOrderResult;
        }


    }
}
