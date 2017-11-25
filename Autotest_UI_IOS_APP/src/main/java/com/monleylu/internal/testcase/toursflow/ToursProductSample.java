package com.monleylu.internal.testcase.toursflow;

import com.monleylu.internal.pageobject.tours.ToursProduct;
import com.monleylu.internal.testcase.data.TestUnit;
import com.monleylu.internal.testcase.result.SaveOrderResult;
import com.monleylu.internal.testcase.tasks.ToursTasks;

/**
 * Created by monley_Lu on 2017/2/26.
 */
public class ToursProductSample {

    public SaveOrderResult saveOrder(ToursProduct toursProduct, TestUnit testUnit) {
        ToursFlow toursFlow = new ToursFlow();
        return toursFlow.toursBookFlow(toursProduct, testUnit, ToursTasks.SaveOrder);
    }
}
