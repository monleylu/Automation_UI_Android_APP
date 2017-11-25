/**
*Copyright (c) 2006-2016 . All Rights Reserved.
*/
package com.monleylu.olbb.testcase;

import java.util.ArrayList;

import com.monleylu.olbb.result.ProductBookInformation;

public class TestcaseWithRelateProductBean {
    private TestCase testCase ;
    private ArrayList<ProductBookInformation> testProducts ;
    
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
