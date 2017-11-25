package com.monleylu.internal.testcase.cruiseflow;

import com.monleylu.internal.pageobject.cruise.CruiseProduct;
import com.monleylu.internal.testcase.data.TestUnit;
import com.monleylu.internal.testcase.result.SaveOrderResult;
import com.monleylu.internal.testcase.tasks.CruiseTasks;

/**
 * Created by monley_Lu on 2017/8/3.
 */
public class CruiseProductSample {

    public SaveOrderResult saveOrder(CruiseProduct cruiseProduct, TestUnit testUnit) {
        CruiseFlow cruiseFlow = new CruiseFlow();
        return cruiseFlow.bookFlow(cruiseProduct, testUnit, CruiseTasks.SaveOrder);
    }
}
