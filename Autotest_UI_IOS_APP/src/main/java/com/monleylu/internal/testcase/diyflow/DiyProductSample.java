package com.monleylu.internal.testcase.diyflow;

import com.monleylu.internal.pageobject.diy.DiyProduct;
import com.monleylu.internal.testcase.data.TestUnit;
import com.monleylu.internal.testcase.result.SaveOrderResult;
import com.monleylu.internal.testcase.tasks.DiyTasks;

/**
 * Created by monley_Lu on 2017/3/14.
 */
public class DiyProductSample {


    public SaveOrderResult saveOrder(DiyProduct diyProduct, TestUnit testUnit) {
        DiyBookFlow diyBookFlow = new DiyBookFlow();
        return diyBookFlow.driverBookFlow(diyProduct, testUnit, DiyTasks.SaveOrder);
    }
}
