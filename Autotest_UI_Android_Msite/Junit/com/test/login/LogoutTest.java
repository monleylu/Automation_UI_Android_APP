/**
*Copyright (c) 2006-2016 . All Rights Reserved.
*/
package com.test.login;

import static com.test.variable.TestVariable.driverFactory;
import static com.test.variable.TestVariable.hashMapDataSrc;
import static com.test.variable.TestVariable.testEnv;
import static com.monleylu.olbb.logback.LogBack.logger;
import static org.junit.Assert.assertTrue;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import java.io.File;
import java.lang.management.ManagementFactory;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.monleylu.olbb.data.DataUtil;
import com.monleylu.olbb.driver.UserDriverFactory;
import com.monleylu.olbb.login.Login;
import com.monleylu.olbb.login.Logout;
import com.monleylu.olbb.xml.XmlDataSrc;

public class LogoutTest {

    private static boolean isSingle=false;
    private AndroidDriver<AndroidElement> driver;
    //存储注入cookies文件名称
    private static String testCookiesFileName; 
    
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
	
	if(driverFactory == null){
	    synchronized (UserDriverFactory.class) {
		if(driverFactory == null){
		    logger.debug("ToursSaveOrderParameter:初始化进程实例");
		    driverFactory= new UserDriverFactory("androiddriver.txt");
		    isSingle=true; 
		    
		    if (driverFactory.getSize()==0) {
			   throw new Exception("驱动初始化后，没有有效驱动实例，请检查配置文件维护的手机节点是否有效");
		    }
		}
	    }
	}
	    //获取运行环境
	    List<String> inputArguments = ManagementFactory.getRuntimeMXBean().getInputArguments();  
	    logger.debug("java进程运行参数"  + inputArguments);
	    for (String string : inputArguments) {
		if (string.startsWith("-DtestEnv=")) {
		    testEnv = string.substring(10).trim();
		    break;
		}
	    }  
	    
	    //默认测试数据配置文件
	    String configureDataFile ="configuredata.xml";
	    switch (testEnv) {
	    //生产环境
	    case "Production":
		break;
	    //预发布环境
	    case "PreProduction":
		configureDataFile ="configuredata_pre.xml";
		break;
	    //sit环境
	    case "Sit":
		configureDataFile ="configuredata_sit.xml";
		break;

	    default:
		break;
	    }
	    
	    logger.debug("根据运行环境，测试用例读取配置文件为" + configureDataFile);
	    
	    //初始化测试数据参数
	    if (hashMapDataSrc==null) {
		File file = new File(System.getProperty("user.dir") + "/initdata/configure/" + configureDataFile);
		hashMapDataSrc=DataUtil.getDataSrcConfigure(file);
	    }
	
	    //注入登录cookie默认存储路径和文件
	    testCookiesFileName = "LogoutTest_Cookies.txt";
	    File fileCookie = new File(System.getProperty("user.dir")+"/initdata/cookies/" + testCookiesFileName);
	    
	    //获取当前类测试数据
	    String classNameString =Thread.currentThread().getStackTrace()[1].getClassName();
	    XmlDataSrc xmlDataSrc = hashMapDataSrc.get(classNameString);
	    
	    //获取cookies注入数据
	    String cookieFlag = xmlDataSrc!=null?xmlDataSrc.getCookieFlag():"";
	    String cookieData = xmlDataSrc.getCookieData();
	    
	    //获取测试用例数据
	    String srcFlag = xmlDataSrc!=null?xmlDataSrc.getSrcFlag():"";
	    String srcData = xmlDataSrc.getSrcData();
	    
	    //从奥卡姆剃刀系统获取登录注入数据
	    if (cookieFlag.equalsIgnoreCase("DB")) {
		//写入文件
		DataUtil.getCookiesWriteToFile(cookieData, fileCookie);
	    }
	    
	    //从配置文件获取cookies注入数据
	    if (cookieFlag.equalsIgnoreCase("FILE")) {
		//设置保存cookies文件
		testCookiesFileName=cookieData;
	    }
	    
	logger.debug("开始运行LogoutTest");
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
	
	if (isSingle) {
	    AndroidDriver<AndroidElement> tmpDriver = driverFactory.getDriver_poll();
	    while (tmpDriver!=null) {
		tmpDriver.quit();
		tmpDriver=driverFactory.getDriver_poll();
	    }
		logger.debug("LogoutTest:清除运行实例");
	}
	logger.debug("结束LogoutTest");
    }

    @Test
    public void testLogOut() {
	 
	Login.injectLogin(driver,testCookiesFileName);
	//这个方法退出会导致后续需要注入登录的用例执行失败
	assertTrue("退出登录成功", Logout.logoutSystemBySystemFunctionWithCookieFile(driver,testCookiesFileName));;
	
    }
    

}
