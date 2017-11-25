/*
 * 保存整个下单流程最后结果
 */
package com.monleylu.internal.testcase.result;

import com.monleylu.internal.testcase.data.TestUnit;

public class SaveOrderResult extends BaseTestCaseResult {

    //private boolean success=Boolean.FALSE; //下单是否成功标志
    //private String orderMsg=""; //保存成功或失败等信息
    private String orderID = "";  //订单号

    /**
     * 保存用例执行过程中的截图信息
     **/
    private ScreenPics screenPics;
    //private ProductBookInformation  productBookInformation ;//保存预订信息

    public SaveOrderResult() {

    }

/*	public SaveOrderResult(ProductBookInformation productBookInformation){
		this.productBookInformation = productBookInformation;
	}*/

    public SaveOrderResult(TestUnit testUnit) {
        super(testUnit);
    }

    /*	public boolean isSuccess() {
            return success;
        }
        public void setSuccess(boolean success) {
            this.success = success;
        }
        public String getOrderMsg() {
            return orderMsg;
        }
        public void setOrderMsg(String orderMsg) {
            this.orderMsg = orderMsg;
        }*/
    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }
	/*public ProductBookInformation getProductBookInformation() {
		return productBookInformation;
	}
	public void setProductBookInformation(
			ProductBookInformation productBookInformation) {
		this.productBookInformation = productBookInformation;
	}
	*/

    public ScreenPics getScreenPics() {
        if (this.screenPics == null) {
            screenPics = new ScreenPics();
        }
        return screenPics;
    }

    public void setScreenPics(ScreenPics screenPics) {
        this.screenPics = screenPics;
    }
}
