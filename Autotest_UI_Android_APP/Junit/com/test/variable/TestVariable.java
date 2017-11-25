/**
*Copyright (c) 2006-2016 . All Rights Reserved.
*/
package com.test.variable;

import java.util.HashMap;

import com.monleylu.olbb.driver.UserDriverFactory;
import com.monleylu.olbb.xml.XmlDataSrc;

public class TestVariable {

    //public static Driver driver=null;
    public static HashMap<String, XmlDataSrc> hashMapDataSrc = null;

    public static String testEnv="";
    public static volatile UserDriverFactory driverFactory= null;

}
