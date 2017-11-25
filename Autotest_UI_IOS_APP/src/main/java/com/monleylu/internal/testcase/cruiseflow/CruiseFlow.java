package com.monleylu.internal.testcase.cruiseflow;

import com.monleylu.internal.common.APPUtil;
import com.monleylu.internal.pageobject.CruiseRoomInformation;
import com.monleylu.internal.pageobject.PageCode;
import com.monleylu.internal.pageobject.cruise.CruiseProduct;
import com.monleylu.internal.testcase.data.TestUnit;
import com.monleylu.internal.testcase.result.ProductBookInformation;
import com.monleylu.internal.testcase.result.SaveOrderResult;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static com.monleylu.internal.variable.GlobalVars.log;

/**
 * Created by monley_Lu on 2017/8/3.
 */

public class CruiseFlow {

    /**
     * @param cruiseProduct 产品实例
     * @param testUnit     测试用例信息
     * @param currentFlow  本次执行任务，任务定义在com.monleylu.testcase.tasks类中
     * @return 返回测试用例执行结果
     */
    public SaveOrderResult bookFlow(CruiseProduct cruiseProduct, TestUnit testUnit, int currentFlow) {
        SaveOrderResult saveOrderResult = new SaveOrderResult(testUnit);
        ProductBookInformation productBookInformation = testUnit.getProductBookInformation();

        //test start,
        boolean loop =false;
        //IOSElement iosElement;

        while (loop){
/*            int ad=2;
            int cd=2;
            */
            try{
/*
                cruiseProduct.getPageStepOne().clickNextPageBtn();
                cruiseProduct.getPageStepOne().clickConfirmBtn();
                cruiseProduct.getPageStepOne().getCruiseRoom().seletCruiseRoomRandom(ad,cd);
                cruiseProduct.getPageStepOne().getCruiseRoom().clickConfirmBtn();
*/


                cruiseProduct.getPageTourists().selectTourist();

            }catch (Exception e){
                e.printStackTrace();
           }

        }

        log.debug("开始自动化测试预订流程业务");
        try {
            log.debug("初始化应用程序，返回首页");
            APPUtil.backToMianPage(cruiseProduct.getDriver());

            cruiseProduct.getPageDetail().visitProduct(productBookInformation.getProductID());
            log.debug("wait detail page load ready");
            if (!cruiseProduct.getPageDetail().waitPageLoadReady(30)) {
                log.error("detail page does not load ready in 30 sec");
                saveOrderResult.setSuccess(false);
                saveOrderResult.setMsg("detail page does not load ready in 30 sec");
                return saveOrderResult;
            }

            cruiseProduct.getPageDetail().clickNextPageBtn();

            log.debug("wait calendar page load ready");
            if (!cruiseProduct.getPageCalendar().waitPageLoadReady(30)) {
                log.error("calendar page load failed in 30 sec");
                saveOrderResult.setSuccess(false);
                saveOrderResult.setMsg("calendar page load failed in 30 sec");
                return saveOrderResult;
            }

            log.debug("start select tour date , because some uncommon design , this function select the first vaild date");
            cruiseProduct.getPageCalendar().selectTourDate(productBookInformation.getBuyDate());
            //火车票采购方式
            if (2 == productBookInformation.getProductMode()) {
                //由于设计问题，当有儿童或者已选择儿童时，设计上没有做互斥而是返回预订页面报错，交互太复杂，暂时不处理这种交互
                //暂时通过只支持成人下单
                log.debug("由于设计问题，当有儿童或者已选择儿童时，设计上没有做互斥而是返回预订页面报错，暂时不处理这种交互,暂时只支持成人");
                cruiseProduct.getPageCalendar().selectAdult(productBookInformation.getAdultNum());
                testUnit.getProductBookInformation().setChildNum(0);
                testUnit.getProductBookInformation().setChildFreedNum(0);

            } else {
                cruiseProduct.getPageCalendar().selectAdult(productBookInformation.getAdultNum());
                cruiseProduct.getPageCalendar().selectChild(productBookInformation.getChildNum());
            }


            cruiseProduct.getPageCalendar().clickNextPageBtn();

            log.debug("wait leave calendar page ");
            cruiseProduct.getPageCalendar().waitLeaveCurrentPage(30);

            log.debug("wait if exist repeat page");
            if (cruiseProduct.getPageRepeat().waitPageLoadReady(5)) {
                log.debug("enter repeat page");
                cruiseProduct.getPageRepeat().clickNextPageBtn();
            }

            log.debug("wait step one page load ready");
            if (!cruiseProduct.getPageStepOne().waitPageLoadReady(30)) {
                log.error("step one page load failed in 30 sec");
                saveOrderResult.setSuccess(false);
                saveOrderResult.setMsg("step one page load failed in 30 sec");
                return saveOrderResult;

            }

            log.debug("if exist alert dialog");
            if (cruiseProduct.getPageStepOne().isExistAlertDialog()) {
                log.debug("exist alert dialog");
                int dialogType = cruiseProduct.getPageStepOne().flightAlertDialogType();
                switch (dialogType) {
                    case 1:
                        log.debug("step one flight sell out  process");
                        cruiseProduct.getPageStepOne().clickConfirmBtnOfSellOutAlterDialog();
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

            //第一次点击下一步按钮，调整邮轮资源模块到可见视区，此时同时会弹窗弹窗选择
            cruiseProduct.getPageStepOne().clickNextPageBtn();
            if (cruiseProduct.getPageStepOne().isExistPlsSelectCruiseRoom()){
                log.debug("第一次点击下一步按钮，调整邮轮资源模块到可见视区");
                cruiseProduct.getPageStepOne().clickConfirmBtn();
            }

            log.debug("开始选择邮轮房型");
            CruiseRoomInformation cruiseRoomInformation =cruiseProduct.getPageStepOne().getCruiseRoom().seletCruiseRoomRandom(productBookInformation.getAdultNum(),productBookInformation.getChildNum()+productBookInformation.getChildFreedNum());
            cruiseProduct.getPageStepOne().getCruiseRoom().clickConfirmBtn();
            log.debug("选择的房型：" +cruiseRoomInformation.getRoomName() +" 入住限制：" + cruiseRoomInformation.getLowerLimit() + "-" + cruiseRoomInformation.getUpLimit());

            cruiseProduct.getPageStepOne().waitPageLoadReady(5);
            log.debug("选择完邮轮房型,点击下一步按钮");
            cruiseProduct.getPageStepOne().clickNextPageBtn();
            if (cruiseProduct.getPageStepOne().isExistCruiseChenkinUnmatch()){
                log.debug("存在选择的出游人和入住人数不一致问题");
                cruiseProduct.getPageStepOne().clickConfirmBtn();
                saveOrderResult.setSuccess(false);
                saveOrderResult.setMsg("存在选择的出游人和入住人数不一致问题" + "选择的房型：" +cruiseRoomInformation.getRoomName() +" 入住限制：" + cruiseRoomInformation.getLowerLimit() + "-" + cruiseRoomInformation.getUpLimit());
                return saveOrderResult;
            }


            cruiseProduct.getPageStepOne().waitLeaveCurrentPage(30);

            log.debug("wait step two page load ready");
            if (!cruiseProduct.getPageStepTwo().waitPageLoadReady(30)) {
                log.error("step two page load failed in 30 sec");
                saveOrderResult.setSuccess(false);
                saveOrderResult.setMsg("step two page load failed in 30 sec");
                return saveOrderResult;

            }


/*            //select adult
            if (productBookInformation.getAdultNum()>0){
                cruiseProduct.getPageStepTwo().clickSelectAdultBtn();
                cruiseProduct.getPageTourists().waitPageLoadReady(30);
                cruiseProduct.getPageTourists().selectAdultTourist(productBookInformation.getAdultNum());
                cruiseProduct.getPageTourists().clickNextPageBtn();
                cruiseProduct.getPageStepTwo().waitPageLoadReady(5);
            }

            //select child
            if (productBookInformation.getChildNum()>0){
                cruiseProduct.getPageStepTwo().clickSelectChildBtn();
                cruiseProduct.getPageTourists().waitPageLoadReady(30);
                cruiseProduct.getPageTourists().selectChildTourist(productBookInformation.getChildNum());
                cruiseProduct.getPageTourists().clickNextPageBtn();
                cruiseProduct.getPageStepTwo().waitPageLoadReady(5);
            }

            //select freechild
            if (productBookInformation.getChildFreedNum()>0){
                cruiseProduct.getPageStepTwo().clickSelectFreeChildBtn();
                cruiseProduct.getPageTourists().waitPageLoadReady(30);
                cruiseProduct.getPageTourists().selectFreeChildTourist(productBookInformation.getChildFreedNum());
                cruiseProduct.getPageTourists().clickNextPageBtn();
                cruiseProduct.getPageStepTwo().waitPageLoadReady(5);
            }*/


            int sumRoom = cruiseProduct.getPageStepTwo().getCruiseTourist().getTotalCruiseRoomNum();
            log.debug("2/2邮轮房间数： " +sumRoom);

            //火车票
            if (productBookInformation.getProductMode()==15){

            }else{

                for (int i =0;i<sumRoom;i++){
                    //由于每选择一个出游人后都会消失一个选择出游人按钮，所以每次都选择第一个按钮就行
                    cruiseProduct.getPageStepTwo().getCruiseTourist().clickSelectTouristBtn(0);
                    cruiseProduct.getPageTourists().waitPageLoadReady(30);
                    cruiseProduct.getPageTourists().selectTourist();
                    cruiseProduct.getPageTourists().clickNextPageBtn();
                    cruiseProduct.getPageStepTwo().waitPageLoadReady(5);
                }

            }


            //每次选择完出游人后，清空已选择出游人缓存
            cruiseProduct.getPageTourists().clearSelectedTourists();

            //判断是否存在G／L签注需要选择
            /*if (cruiseProduct.getPageStepTwo().isNeedSignType()){
                cruiseProduct.getPageStepTwo().selectAllSignType(productBookInformation.getAdultNum() + productBookInformation.getChildNum());
            }*/


            cruiseProduct.getPageStepTwo().agreeAllNotice();
            cruiseProduct.getPageStepTwo().clickNextPageBtn();

            cruiseProduct.getPageStepTwo().waitPageLoadReady(30);

            //if exist alert dialog
            if (cruiseProduct.getPageStepTwo().isExistAlertDialog(1)) {
                log.debug("exist alert dialog");
                cruiseProduct.getPageStepTwo().clickConfirmBtnOfAlertBox();
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
                if (cruiseProduct.getPageStepTwo().isVisibilityProgressBox(5)) {
                    log.debug("exist progressing view");
                    cruiseProduct.getPageStepTwo().isInVisibilityProgressBox(30);
                }

                if (cruiseProduct.getPageStepTwo().isExistAlertDialog(1)) {
                    log.debug("exist alert");
                    cruiseProduct.getPageStepTwo().clickConfirmBtnOfAlertBox();
                    saveOrderResult.setSuccess(true);
                    saveOrderResult.setMsg("save order success");
                    continueLoop = false;
                }

                //下单成功页
                if (PageCode.PageProductBookSuccess ==APPUtil.getCurrentPage(cruiseProduct.getDriver())){
                    log.debug("save order success");
                    saveOrderResult.setSuccess(true);
                    saveOrderResult.setMsg("save order success");
                    continueLoop=false;
                }

                //下单失败页
                if (PageCode.PageProductBookFail ==APPUtil.getCurrentPage(cruiseProduct.getDriver())){
                    log.debug("save order fail");
                    saveOrderResult.setSuccess(true);
                    saveOrderResult.setMsg("save order fail");
                    continueLoop=false;
                }

                //收银台
                if (PageCode.PageProductCasher == APPUtil.getCurrentPage(cruiseProduct.getDriver())) {
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
