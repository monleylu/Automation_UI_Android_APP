package com.monleylu.internal.testcase.driverflow;

import com.monleylu.internal.pageobject.drive.DriveProduct;
import com.monleylu.internal.testcase.data.TestUnit;
import com.monleylu.internal.testcase.result.SaveOrderResult;
import com.monleylu.internal.testcase.tasks.DriveTasks;

/**
 * Created by monley_Lu on 2017/3/9.
 */
public class DriverProductSample {


    public SaveOrderResult saveOrder(DriveProduct driveProduct, TestUnit testUnit) {
        DriverFlow driverFlow = new DriverFlow();
        return driverFlow.driverBookFlow(driveProduct, testUnit, DriveTasks.SaveOrder);
    }
}
