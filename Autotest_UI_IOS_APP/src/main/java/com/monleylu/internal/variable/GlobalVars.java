package com.monleylu.internal.variable;

import com.monleylu.internal.driver.UserDriverFactory;
import com.monleylu.internal.xml.XmlDataSrc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;


/**
 * Created by monley_Lu on 2017/2/26.
 */
public class GlobalVars {
    public static Logger log = LoggerFactory.getLogger("User Define logger");
    public static HashMap<String, XmlDataSrc> hashMapDataSrc = null;

    public static String testEnv = "";
    public static volatile UserDriverFactory driverFactory = null;

}
