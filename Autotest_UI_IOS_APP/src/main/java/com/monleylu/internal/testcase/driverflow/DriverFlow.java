package com.monleylu.internal.testcase.driverflow;

import com.monleylu.internal.common.APPUtil;
import com.monleylu.internal.pageobject.PageCode;
import com.monleylu.internal.pageobject.drive.DriveProduct;
import com.monleylu.internal.testcase.data.TestUnit;
import com.monleylu.internal.testcase.result.ProductBookInformation;
import com.monleylu.internal.testcase.result.SaveOrderResult;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static com.monleylu.internal.variable.GlobalVars.log;

/**
 * Created by monley_Lu on 2017/3/9.
 */
public class DriverFlow {


    /**
     * @param driveProduct 产品实例
     * @param testUnit     测试用例信息
     * @param currentFlow  本次执行任务，任务定义在com.monleylu.testcase.tasks类中
     * @return 返回测试用例执行结果
     */
    public SaveOrderResult driverBookFlow(DriveProduct driveProduct, TestUnit testUnit, int currentFlow) {

        SaveOrderResult saveOrderResult = new SaveOrderResult(testUnit);
        ProductBookInformation productBookInformation = testUnit.getProductBookInformation();

        log.debug("开始自动化测试预订流程业务");

        try {
            log.debug("初始化应用程序，返回首页");
            APPUtil.backToMianPage(driveProduct.getDriver());

            log.debug("start visit product");
            driveProduct.getPageDetail().visitProduct(productBookInformation.getProductID());

            log.debug("wait detail page load ready");
            if (!driveProduct.getPageDetail().waitPageLoadReady(30)) {
                log.debug("detail page load failed in 30 sec");
                saveOrderResult.setSuccess(false);
                saveOrderResult.setMsg("detail page load failed in 30 sec");
                return saveOrderResult;
            }

            //scroll about 200px
            APPUtil.swipeUpPageView(driveProduct.getDriver());

            log.debug("select tour date");
            driveProduct.getPageDetail().clickSelectCalendarBtn();

            log.debug("wait calendar page load ready");
            if (!driveProduct.getPageCalendar().waitPageLoadReady(30)) {
                log.debug("calendar page load failed in 30 sec");
                saveOrderResult.setSuccess(false);
                saveOrderResult.setMsg("calendar page load failed in 30 sec");
                return saveOrderResult;
            }

            log.debug("select tourist date ,because design , you can select a valiad date ");
            driveProduct.getPageCalendar().selectTourDate(productBookInformation.getBuyDate());
            log.debug("select adult");
            driveProduct.getPageCalendar().selectAdult(productBookInformation.getAdultNum());
            log.debug("select child");
            driveProduct.getPageCalendar().selectChild(productBookInformation.getChildNum());
            driveProduct.getPageCalendar().clickNextPageBtn();

            log.debug("返回详情页，等待页面加载完毕");
            driveProduct.getPageDetail().waitPageLoadReady(30);

            //if exist ticket module
            //app 9.1.9之后改版，门票默认有日期，不需要处理选择门票日期的逻辑
           /* if (driveProduct.getPageDetail().isExistTicketModule(1)) {
                log.debug("exist ticket module");
                //move page view to ticket module
                driveProduct.getPageDetail().clickNextPageBtn();

                //select ticket
                int countTickets = driveProduct.getPageDetail().countOfDefaultShowTicket();
                for (int i = 1; i <= countTickets; i++) {
                    driveProduct.getPageDetail().clickUseDateBtn(i);
                    driveProduct.getPageDetail().selectTicketUseDate(0);
                }
            }*/

            driveProduct.getPageDetail().clickNextPageBtn();

            log.debug("wait step one page load ready");
            if (!driveProduct.getPageStepOne().waitPageLoadReady(30)) {
                log.debug("step one page load fail in 30 sec");
                saveOrderResult.setSuccess(false);
                saveOrderResult.setMsg("step one page load fail in 30 sec");
                return saveOrderResult;
            }

            driveProduct.getPageStepOne().clickNextPageBtn();

            driveProduct.getPageStepOne().waitLeaveCurrentPage(30);

            log.debug("wait step two page load ready");
            if (!driveProduct.getPageStepTwo().waitPageLoadReady(30)) {
                log.debug("step two page load failed in 30 sec");
                saveOrderResult.setSuccess(false);
                saveOrderResult.setMsg("step two page load failed in 30 sec");
                return saveOrderResult;
            }

            log.debug("start select tourist");
            driveProduct.getPageStepTwo().clickSelectTouristBtn();


            log.debug("wait page tourist load ready");
            if (!driveProduct.getPageTourists().waitPageLoadReady(30)) {
                log.debug("page tourist load fail in 30 sec");
                saveOrderResult.setSuccess(false);
                saveOrderResult.setMsg("page tourist load fail in 30 sec");
                return saveOrderResult;
            }

            log.debug("start select adult");
            driveProduct.getPageTourists().selectTourist(productBookInformation.getAdultNum() + productBookInformation.getChildNum());
            driveProduct.getPageTourists().clickNextPageBtn();

            log.debug("back to Page step two");
            driveProduct.getPageStepTwo().waitPageLoadReady(30);
            driveProduct.getPageStepTwo().agreeAllNotice();

            log.debug("save order");
            driveProduct.getPageStepTwo().clickNextPageBtn();


            log.debug("loop to get save order status");
            boolean continueLoop = true;
            int loopCount = 0;
            while (continueLoop) {
                //生僻字

                //加载中浮层
                log.debug("if exist progressing view");
                if (driveProduct.getPageStepTwo().isVisibilityProgressBox(5)) {
                    log.debug("exist progressing view");
                    driveProduct.getPageStepTwo().isInVisibilityProgressBox(30);
                }

                //占位浮层
                log.debug("if exist occupy view");
                if (driveProduct.getPageStepTwo().isVisibilityOccupyBox(5)){
                    log.debug("exist occupy view");
                    driveProduct.getPageStepTwo().isInVisibilityOccupyBox(30);
                }

                log.debug("if exist alert dialog");
                if (driveProduct.getPageStepTwo().isExistAlertDialog(1)) {
                    log.debug("exist alert");
                    driveProduct.getPageStepTwo().clickConfirmBtnOfAlertBox();
                    saveOrderResult.setSuccess(true);
                    saveOrderResult.setMsg("save order success");
                    continueLoop = false;
                }


                //全部订单
                if (PageCode.PageAllOrders == APPUtil.getCurrentPage(driveProduct.getDriver())){
                    log.debug("login to all order page");
                    saveOrderResult.setSuccess(true);
                    saveOrderResult.setMsg("login to all order page");
                    continueLoop=false;
                }

                //下单成功页
                if (PageCode.PageProductBookSuccess ==APPUtil.getCurrentPage(driveProduct.getDriver())){
                    log.debug("save order success");
                    saveOrderResult.setSuccess(true);
                    saveOrderResult.setMsg("save order success");
                    continueLoop=false;
                }

                //下单失败页
                if (PageCode.PageProductBookFail ==APPUtil.getCurrentPage(driveProduct.getDriver())){
                    log.debug("save order fail");
                    saveOrderResult.setSuccess(true);
                    saveOrderResult.setMsg("save order fail");
                    continueLoop=false;
                }

                //收银台
                log.debug("if exist casher page");
                if (PageCode.PageProductCasher == APPUtil.getCurrentPage(driveProduct.getDriver())) {
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
