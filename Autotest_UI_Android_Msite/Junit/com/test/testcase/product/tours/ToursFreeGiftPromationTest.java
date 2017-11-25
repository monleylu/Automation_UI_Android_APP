/**
*Copyright (c) 2006-2017 . All Rights Reserved.
*/
package com.test.testcase.product.tours;

import static com.test.variable.TestVariable.driverFactory;
import static com.test.variable.TestVariable.hashMapDataSrc;
import static com.test.variable.TestVariable.testEnv;
import static com.monleylu.olbb.logback.LogBack.logger;
import static org.junit.Assert.assertTrue;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import java.io.File;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized.Parameters;
import org.slf4j.MDC;

import com.googlecode.junittoolbox.ParallelParameterized;
import com.monleylu.olbb.data.DataUtil;
import com.monleylu.olbb.driver.UserDriverFactory;
import com.monleylu.olbb.file.SaveStringToFile;
import com.monleylu.olbb.login.Logout;
import com.monleylu.olbb.product.tours.ToursProduct;
import com.monleylu.olbb.result.SaveOrderResult;
import com.monleylu.olbb.screenshot.ScreenShot;
import com.monleylu.olbb.testcase.TestUnit;
import com.monleylu.olbb.xml.XmlDataSrc;

@RunWith(ParallelParameterized.class)
public class ToursFreeGiftPromationTest {
    
    private static boolean isSingle=false;
    private AndroidDriver<AndroidElement> driver;
    private TestUnit testUnit;
    
    // 存储测试产品文件名称
    private static String testProductsFileName;
    // 存储注入cookies文件名称
    private static String testCookiesFileName;

    @Parameters
    public static ArrayList<TestUnit[]> initTestData() {
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
	    
	// 获取当前类测试数据
	String classNameString = Thread.currentThread().getStackTrace()[1]
			.getClassName();
		
	logger.debug("开始参数化" + classNameString);

	// 初始化测试数据参数
	if (hashMapDataSrc == null) {
	    File file = new File(System.getProperty("user.dir")
		    + "/initdata/configure/" + configureDataFile);
	    hashMapDataSrc = DataUtil.getDataSrcConfigure(file);
	}

	

	// 测试数据默认存储文件和路径
	testProductsFileName = "Products_" + classNameString + ".txt";
	String testProductsPath = "/initdata/toursproduct/";
	File fileProducts = new File(System.getProperty("user.dir")
		+ testProductsPath + testProductsFileName);

	// 注入登录cookie默认存储路径和文件
	testCookiesFileName = "Cookie_" + classNameString + ".txt";
	File fileCookie = new File(System.getProperty("user.dir")
		+ "/initdata/cookies/" + testCookiesFileName);

	XmlDataSrc xmlDataSrc = hashMapDataSrc.get(classNameString);

	// 获取cookies注入数据
	String cookieFlag = xmlDataSrc != null ? xmlDataSrc.getCookieFlag()
		: "";
	String cookieData = "";
	if (!cookieFlag.isEmpty()) {
	     cookieData = xmlDataSrc.getCookieData();
	}
	

	// 获取测试用例数据
	String srcFlag = xmlDataSrc != null ? xmlDataSrc.getSrcFlag() : "";
	String srcData = xmlDataSrc.getSrcData();

	logger.debug("tours cookieFlag：cookieData = " + cookieFlag + ":"
		+ cookieData);
	logger.debug("tours srcFlag:srcData = " + srcFlag + ":" + srcData);

	// 从奥卡姆剃刀系统获取登录注入数据
	if (cookieFlag.equalsIgnoreCase("DB")) {
	    // 写入文件
	    DataUtil.getCookiesWriteToFile(cookieData, fileCookie);
	}

	// 从配置文件获取cookies注入数据
	if (cookieFlag.equalsIgnoreCase("FILE")) {
	    // 设置保存cookies文件
	    testCookiesFileName = cookieData;
	}

	// 配置为从数据库获取测试产品数据
	if (srcFlag.equalsIgnoreCase("DB")) {
	    // 写入文件
	    DataUtil.getTestProductWriteToFile(srcData, fileProducts);
	}

	// 配置为从文件获取测试产品数据
	if (srcFlag.equalsIgnoreCase("FILE")) {
	    // 设置测试产品文件
	    testProductsFileName = srcData;
	    // 设置上层传入的配置文件
	    fileProducts = new File(System.getProperty("user.dir")
		    + testProductsPath + srcData);
	}

	return DataUtil.getTestUnitsFromFile(fileProducts);
    }
    
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
	if(driverFactory == null){
	    synchronized (UserDriverFactory.class) {
		if(driverFactory == null){
		    logger.debug("ToursFreeGiftPromationTest:初始化进程实例");
		    driverFactory= new UserDriverFactory("androiddriver.txt");
		    isSingle=true; 
		    
		    if (driverFactory.getSize()==0) {
			   throw new Exception("驱动初始化后，没有有效驱动实例，请检查配置文件维护的手机节点是否有效");
		    }
		}
	    }
	}
    }

    public ToursFreeGiftPromationTest(TestUnit testUnit){
	this.testUnit=testUnit;
	//MDC，获取线程ID，用来区分不同线程的日志
	MDC.put("threadNum",String.valueOf(Thread.currentThread().getId()) );	
    }
    
    @AfterClass
    public static void tearDownAfterClass() throws Exception {
	if (isSingle) {
	    AndroidDriver<AndroidElement> tmpDriver = driverFactory.getDriver_poll();
	    while (tmpDriver!=null) {
		tmpDriver.quit();
		tmpDriver=driverFactory.getDriver_poll();
	    }
	    logger.debug("清除tearDownAfterClass ToursFreeGiftPromationTest 运行实例");
	}
    }
    
    @Before
    public void setUp() throws Exception {
        driver = driverFactory.getDriver();
    }


    @After
    public void tearDown() throws Exception {
        driverFactory.setDriver(driver);
    }
    
    @Test
    public void test() {
	

	//test delete
	//Login.injectLogin(androidDriver, testUnit.getProductBookInformation().getHostUrl(),testCookiesFileName);
	//删除所有登录信息
	Logout.logoutSystemByDeleteCookies(driver,testCookiesFileName);

	logger.debug("测试用例为： " + testUnit.getTestCase().getTestCaseName());
	logger.debug("下单入参为: " + testUnit.getProductBookInformation().getHostUrl()+","+testUnit.getProductBookInformation().getProductID()+","+testUnit.getProductBookInformation().getBuyDate()+","+testUnit.getProductBookInformation().getAdultNum()+ "," + testUnit.getProductBookInformation().getChildNum() + "," + testUnit.getProductBookInformation().getChildFreedNum());
	
	ToursProduct toursProduct = new ToursProduct(driver);
	ToursFreeGiftPromation toursFreeGiftPromation = new ToursFreeGiftPromation();

	SaveOrderResult saveOrderResult = toursFreeGiftPromation.checkFreeGiftPromation(toursProduct, testUnit);
	//测试用例执行完毕，截图
	String nameOfPicString = ScreenShot.getScreenShot(driver,saveOrderResult.getScreenPics().getPathOfSavePics());
	saveOrderResult.getScreenPics().addSinglePic(nameOfPicString);
	//保存结果
	SaveStringToFile.SaveGsonObjToFile(saveOrderResult, "saveorder.txt");
	
	assertTrue("用例名称：" + testUnit.getTestCase().getTestCaseName() + "	失败原因：" + saveOrderResult.getMsg()+ "	测试产品：" + testUnit.getProductBookInformation().getProductID() , saveOrderResult.isSuccess());
		
	
    }

}
