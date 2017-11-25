package com.monleylu.internal.testcase.toursflow;

import com.monleylu.internal.common.APPUtil;
import com.monleylu.internal.pageobject.PageCode;
import com.monleylu.internal.pageobject.tours.ToursProduct;
import com.monleylu.internal.testcase.data.TestUnit;
import com.monleylu.internal.testcase.result.ProductBookInformation;
import com.monleylu.internal.testcase.result.SaveOrderResult;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static com.monleylu.internal.variable.GlobalVars.log;

/**
 *  跟团预订流程,所有预订流程的用例都基于此函数，通过任务号执行不同任务
 * Created by monley_Lu on 2017/2/24.
 */
public class ToursFlow {

    /**
     * @param toursProduct 产品实例
     * @param testUnit     测试用例信息
     * @param currentFlow  本次执行任务，任务定义在com.monleylu.testcase.tasks类中
     * @return 返回测试用例执行结果
     */
    public SaveOrderResult toursBookFlow(ToursProduct toursProduct, TestUnit testUnit, int currentFlow) {
        SaveOrderResult saveOrderResult = new SaveOrderResult(testUnit);
        ProductBookInformation productBookInformation = testUnit.getProductBookInformation();

        //test start,
      // boolean loop =true;
       //IOSElement iosElement;

        /*while (loop){
            try{*/
                //String bb=toursProduct.getDriver().getPageSource();
                //System.out.println(bb);
                //APPUtil.swipeRightPageView(toursProduct.getDriver());
                //int page = APPUtil.getCurrentPageNew(toursProduct.getDriver());
                //System.out.println(page);
                //APPUtil.backToMianPage(toursProduct.getDriver());
                //    //XCUIElementTypeTable/XCUIElementTypeOther/XCUIElementTypeCollectionView
                /*String xpath="//XCUIElementTypeStaticText[@name='成人']/parent::XCUIElementTypeOther";
                iosElement = toursProduct.getDriver().findElementByXPath(xpath);
                String xpath1="//XCUIElementTypeOther/XCUIElementTypeButton[@name='planDate minus enable']";
                String xpath2="//XCUIElementTypeOther/XCUIElementTypeButton[1]";
                if (!xpath1.isEmpty()){
                    iosElement.findElementByXPath(xpath1).click();
                    iosElement.findElementByXPath(xpath2).click();
                }*/

                //toursProduct.getPageCalendar().selectAdult(4);
                //toursProduct.getPageCalendar().selectAdult(1);
                //toursProduct.getPageCalendar().selectChild(2);
                //toursProduct.getPageCalendar().selectChild(1);
                //toursProduct.getPageCalendar().selectTourDate("31");
                //toursProduct.getPageCalendar().clickNextPageBtn();
                //toursProduct.getPageStepTwo().clickSelectAdultBtn();
                //toursProduct.getPageStepTwo().clickSelectChildBtn();
                //toursProduct.getPageStepTwo().clickSelectFreeChildBtn();
             //   toursProduct.getPageStepTwo().clickSelectAdultBtn();
               // toursProduct.getPageTourists().waitPageLoadReady(30);
               // toursProduct.getPageTourists().selectTourist(2,2);
                //toursProduct.getPageTourists().clickNextPageBtn();
                //toursProduct.getPageStepTwo().clickSelectChildBtn();
                ///toursProduct.getPageTourists().waitPageLoadReady(30);
                //toursProduct.getPageTourists().selectTourist(2,1);
                //toursProduct.getPageTourists().clickNextPageBtn();
                //toursProduct.getPageStepTwo().clickSelectFreeChildBtn();
                //toursProduct.getPageTourists().waitPageLoadReady(30);
                //toursProduct.getPageTourists().selectTourist(2,0);

                //toursProduct.getPageTourists().selectTourist(2,2);
                //toursProduct.getPageTourists().selectTourist(2,2);
                //toursProduct.getPageStepTwo().agreeAllNotice();
          /*      toursProduct.getPageStepTwo().selectAllSignType(productBookInformation.getAdultNum()+productBookInformation.getChildNum()+productBookInformation.getChildFreedNum());

            }catch (Exception e){
                e.printStackTrace();
           }

        }*/

        log.debug("开始自动化测试预订流程业务");
        try {
            log.debug("初始化应用程序，返回首页");
            APPUtil.backToMianPage(toursProduct.getDriver());

            toursProduct.getPageDetail().visitProduct(productBookInformation.getProductID());
            log.debug("wait detail page load ready");
            if (!toursProduct.getPageDetail().waitPageLoadReady(30)) {
                log.error("detail page does not load ready in 30 sec");
                saveOrderResult.setSuccess(false);
                saveOrderResult.setMsg("detail page does not load ready in 30 sec");
                return saveOrderResult;
            }

            toursProduct.getPageDetail().clickNextPageBtn();

            log.debug("wait calendar page load ready");
            if (!toursProduct.getPageCalendar().waitPageLoadReady(30)) {
                log.error("calendar page load failed in 30 sec");
                saveOrderResult.setSuccess(false);
                saveOrderResult.setMsg("calendar page load failed in 30 sec");
                return saveOrderResult;
            }

            log.debug("start select tour date , because some uncommon design , this function select the first vaild date");
            toursProduct.getPageCalendar().selectTourDate(productBookInformation.getBuyDate());
            //火车票采购方式
            if (2 == productBookInformation.getProductMode()) {
                //由于设计问题，当有儿童或者已选择儿童时，设计上没有做互斥而是返回预订页面报错，交互太复杂，暂时不处理这种交互
                //暂时通过只支持成人下单
                log.debug("由于设计问题，当有儿童或者已选择儿童时，设计上没有做互斥而是返回预订页面报错，暂时不处理这种交互,暂时只支持成人");
                toursProduct.getPageCalendar().selectAdult(productBookInformation.getAdultNum());
                testUnit.getProductBookInformation().setChildNum(0);
                testUnit.getProductBookInformation().setChildFreedNum(0);

            } else {
                toursProduct.getPageCalendar().selectAdult(productBookInformation.getAdultNum());
                toursProduct.getPageCalendar().selectChild(productBookInformation.getChildNum());
            }


            toursProduct.getPageCalendar().clickNextPageBtn();

            log.debug("wait leave calendar page ");
            toursProduct.getPageCalendar().waitLeaveCurrentPage(30);

            log.debug("wait if exist repeat page");
            if (toursProduct.getPageRepeat().waitPageLoadReady(5)) {
                log.debug("enter repeat page");
                toursProduct.getPageRepeat().clickNextPageBtn();
            }

            log.debug("wait step one page load ready");
            if (!toursProduct.getPageStepOne().waitPageLoadReady(30)) {
                log.error("step one page load failed in 30 sec");
                saveOrderResult.setSuccess(false);
                saveOrderResult.setMsg("step one page load failed in 30 sec");
                return saveOrderResult;

            }

            log.debug("if exist alert dialog");
            if (toursProduct.getPageStepOne().isExistAlertDialog()) {
                log.debug("exist alert dialog");
                int dialogType = toursProduct.getPageStepOne().flightAlertDialogType();
                switch (dialogType) {
                    case 1:
                        log.debug("step one flight sell out  process");
                        toursProduct.getPageStepOne().clickConfirmBtnOfSellOutAlterDialog();
                        break;
                    case 2:
                        log.debug("step one verify flight price  process");
                        break;
                    default:
                        log.debug("step one default alert dialog process");
                }

                if (1 == dialogType) {
                    saveOrderResult.setSuccess(false);
                    saveOrderResult.setMsg("step one exist alter dialog,stop process");
                    return saveOrderResult;
                }

            }

            toursProduct.getPageStepOne().clickNextPageBtn();
            toursProduct.getPageStepOne().waitLeaveCurrentPage(30);

            log.debug("wait step two page load ready");
            if (!toursProduct.getPageStepTwo().waitPageLoadReady(30)) {
                log.error("step two page load failed in 30 sec");
                saveOrderResult.setSuccess(false);
                saveOrderResult.setMsg("step two page load failed in 30 sec");
                return saveOrderResult;

            }

/*
            toursProduct.getPageStepTwo().clickSelectTouristsBtn();

            log.debug("wait page tourists load ready");
            if (!toursProduct.getPageTourists().waitPageLoadReady(30)) {
                log.error("tourist page load failed in 30 sec");
                saveOrderResult.setSuccess(false);
                saveOrderResult.setMsg("tourist page load failed in 30 sec");
                return saveOrderResult;

            }

            toursProduct.getPageTourists().selectTourist(productBookInformation.getAdultNum() + productBookInformation.getChildNum());
*/

            //select adult
            if (productBookInformation.getAdultNum()>0){
                toursProduct.getPageStepTwo().clickSelectAdultBtn();
                toursProduct.getPageTourists().waitPageLoadReady(30);
                toursProduct.getPageTourists().selectAdultTourist(productBookInformation.getAdultNum());
                toursProduct.getPageTourists().clickNextPageBtn();
                toursProduct.getPageStepTwo().waitPageLoadReady(5);
            }

            //select child
            if (productBookInformation.getChildNum()>0){
                toursProduct.getPageStepTwo().clickSelectChildBtn();
                toursProduct.getPageTourists().waitPageLoadReady(30);
                toursProduct.getPageTourists().selectChildTourist(productBookInformation.getChildNum());
                toursProduct.getPageTourists().clickNextPageBtn();
                toursProduct.getPageStepTwo().waitPageLoadReady(5);
            }

            //select freechild
            if (productBookInformation.getChildFreedNum()>0){
                toursProduct.getPageStepTwo().clickSelectFreeChildBtn();
                toursProduct.getPageTourists().waitPageLoadReady(30);
                toursProduct.getPageTourists().selectFreeChildTourist(productBookInformation.getChildFreedNum());
                toursProduct.getPageTourists().clickNextPageBtn();
                toursProduct.getPageStepTwo().waitPageLoadReady(5);
            }

            //每次选择完出游人后，清空已选择出游人缓存
            toursProduct.getPageTourists().clearSelectedTourists();

            //判断是否存在G／L签注需要选择
            if (toursProduct.getPageStepTwo().isNeedSignType()){
                toursProduct.getPageStepTwo().selectAllSignType(productBookInformation.getAdultNum() + productBookInformation.getChildNum());
            }


            toursProduct.getPageStepTwo().agreeAllNotice();
            toursProduct.getPageStepTwo().clickNextPageBtn();

            toursProduct.getPageStepTwo().waitPageLoadReady(30);

            //if exist alert dialog
            if (toursProduct.getPageStepTwo().isExistAlertDialog(1)) {
                log.debug("exist alert dialog");
                toursProduct.getPageStepTwo().clickConfirmBtnOfAlertBox();
                saveOrderResult.setSuccess(false);
                saveOrderResult.setMsg("exist age not satisfy alert dialog");
                return saveOrderResult;
            }



            log.debug("loop to get save order status");
            boolean continueLoop = true;
            int loopCount = 0;
            while (continueLoop) {
                //生僻字

                //占位浮层

                //加载中浮层
                if (toursProduct.getPageStepTwo().isVisibilityProgressBox(5)) {
                    log.debug("exist progressing view");
                    toursProduct.getPageStepTwo().isInVisibilityProgressBox(30);
                }

                if (toursProduct.getPageStepTwo().isExistAlertDialog(1)) {
                    log.debug("exist alert");
                    toursProduct.getPageStepTwo().clickConfirmBtnOfAlertBox();
                    saveOrderResult.setSuccess(true);
                    saveOrderResult.setMsg("save order success");
                    continueLoop = false;
                }

                //下单成功页
                if (PageCode.PageProductBookSuccess ==APPUtil.getCurrentPage(toursProduct.getDriver())){
                    log.debug("save order success");
                    saveOrderResult.setSuccess(true);
                    saveOrderResult.setMsg("save order success");
                    continueLoop=false;
                }

                //下单失败页
                if (PageCode.PageProductBookFail ==APPUtil.getCurrentPage(toursProduct.getDriver())){
                    log.debug("save order fail");
                    saveOrderResult.setSuccess(true);
                    saveOrderResult.setMsg("save order fail");
                    continueLoop=false;
                }

                //收银台
                if (PageCode.PageProductCasher == APPUtil.getCurrentPage(toursProduct.getDriver())) {
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
