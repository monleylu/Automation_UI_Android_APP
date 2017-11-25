/**
 * Copyright (c) 2006-2016 . All Rights Reserved.
 */
package com.monleylu.internal.testcase.data;

import com.monleylu.internal.testcase.result.ProductBookInformation;

import java.util.ArrayList;

public class TestcaseWithRelateProductBean {
    private TestCase testCase;
    private ArrayList<ProductBookInformation> testProducts;

    public TestCase getTestCase() {
        return testCase;
    }

    public void setTestCase(TestCase testCase) {
        this.testCase = testCase;
    }

    public ArrayList<ProductBookInformation> getTestProducts() {
        return testProducts;
    }

    public void setTestProducts(ArrayList<ProductBookInformation> testProducts) {
        this.testProducts = testProducts;
    }

}
