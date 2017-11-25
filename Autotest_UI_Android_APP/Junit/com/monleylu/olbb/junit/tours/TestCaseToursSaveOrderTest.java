package com.monleylu.olbb.junit.tours;

import static com.test.variable.TestVariable.driverFactory;
import static com.test.variable.TestVariable.hashMapDataSrc;
import static com.monleylu.olbb.staticvariables.StaticVars.logger;
import static org.junit.Assert.assertTrue;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import java.io.File;
import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized.Parameters;
import org.slf4j.MDC;

import com.googlecode.junittoolbox.ParallelParameterized;
import com.monleylu.olbb.common.ScreenShot;
import com.monleylu.olbb.data.DataUtil;
import com.monleylu.olbb.driver.UserDriverFactory;
import com.monleylu.olbb.file.SaveStringToFile;
import com.monleylu.olbb.product.tours.ToursProduct;
import com.monleylu.olbb.result.SaveOrderResult;
import com.monleylu.olbb.testcase.TestUnit;
import com.monleylu.olbb.testcase.tours.TestCaseSaveOrder;
import com.monleylu.olbb.xml.XmlDataSrc;

@RunWith(ParallelParameterized.class)
public class TestCaseToursSaveOrderTest {

	private static boolean isSingle=false;
	private TestUnit testUnit;
	private AndroidDriver<AndroidElement> driver;
	
	//存储测试产品文件名称
	private static String testProductsFileName;
	//存储注入cookies文件名称
	private static String testCookiesFileName;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	    	logger.debug("开始初始化类: " + Thread.currentThread().getStackTrace()[1].getClassName() );
	    	
	    	if(driverFactory == null){
		    synchronized (UserDriverFactory.class) {
			if(driverFactory == null){
			    logger.debug("准备初始化驱动信息");
			    driverFactory= new UserDriverFactory("androiddriver.txt");
			    logger.debug("初始化驱动信息完毕");
			    isSingle=true;
			    if (driverFactory.getSize()==0) {
				   logger.debug("驱动初始化后，没有有效驱动实例，请检查配置文件维护的手机节点是否有效");
				   throw new Exception("驱动初始化后，没有有效驱动实例，请检查配置文件维护的手机节点是否有效,退出执行程序");
				   
			    }
			}
		    }
		}
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	    
	    if (isSingle) {
		    logger.debug("非全局运行测试用例，当前用例全部执行完毕，释放所有申请实例");
		    AndroidDriver<AndroidElement> tmpDriver = driverFactory.getDriver_poll();
		    while (tmpDriver!=null) {
			tmpDriver.quit();
			tmpDriver=driverFactory.getDriver_poll();
		    }
		    logger.debug("清除运行实例: " + Thread.currentThread().getStackTrace()[1].getClassName() );
		}
		
	}
	
	public TestCaseToursSaveOrderTest(TestUnit testUnit) {
	     this.testUnit=testUnit;
	     //MDC，获取线程ID，用来区分不同线程的日志
	     MDC.put("threadNum",String.valueOf(Thread.currentThread().getId()) );
	}

        @Before
        public void setUp() throws Exception {
            logger.debug("获取一个用于执行用例的驱动实例");
            driver = driverFactory.getDriver();
            logger.debug("获取到用于执行用例的驱动实例");
        }
    
    
        @After
        public void tearDown() throws Exception {
            logger.debug("用例执行完毕，释放使用的驱动实例");
            driverFactory.setDriver(driver);
            logger.debug("释放完成使用的驱动实例");
        }
        
	@Parameters
	public static ArrayList<TestUnit[]>  initTestData(){
	    
	    //获取类全路径
	    String classNameString =Thread.currentThread().getStackTrace()[1].getClassName();
	    
	    logger.debug("开始参数化 " + classNameString);
	    //初始化测试数据参数
	    if (hashMapDataSrc==null) {
		File file = new File(System.getProperty("user.dir") + "/initdata/configure/" + "configuredata.xml");
		hashMapDataSrc=DataUtil.getDataSrcConfigure(file);
	    }
	    
	     
	    //测试数据默认存储文件和路径
	    testProductsFileName = "Product_"+ classNameString +".txt";
	    String testProductsPath ="/initdata/toursproduct/";
	    File fileProducts = new File(System.getProperty("user.dir")+ testProductsPath + testProductsFileName);
	    
	    //注入登录cookie默认存储路径和文件
	    testCookiesFileName = "Cookies"+ classNameString + ".txt";
	    File fileCookie = new File(System.getProperty("user.dir")+"/initdata/cookies/" + testCookiesFileName);
	    
	    //获取当前类测试数据
	    XmlDataSrc xmlDataSrc = hashMapDataSrc.get(classNameString);
	    
	    //获取cookies注入数据
	    String cookieFlag = xmlDataSrc!=null?xmlDataSrc.getCookieFlag():"";
	    String cookieData="";
	    if (!cookieFlag.isEmpty()) {
		cookieData= xmlDataSrc.getCookieData();
	    }
	    
	    
	    //获取测试用例数据
	    String srcFlag = xmlDataSrc!=null?xmlDataSrc.getSrcFlag():"";
	    String srcData="";
	    if (!srcFlag.isEmpty()) {
		 srcData = xmlDataSrc.getSrcData();
	    }
	    
	    
	    logger.debug("cookieFlag：cookieData = " + cookieFlag +":" + cookieData);
	    logger.debug("srcFlag:srcData = " + srcFlag +":" + srcData);
	    
	    
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
	    
	    
	    //配置为从数据库获取测试产品数据
	    if (srcFlag.equalsIgnoreCase("DB")) {
		//写入文件
		DataUtil.getTestProductWriteToFile(srcData, fileProducts);
	    }
	    
	    //配置为从文件获取测试产品数据
	    if (srcFlag.equalsIgnoreCase("FILE")) {
		//设置测试产品文件
		testProductsFileName=srcData;
		//设置上层传入的配置文件
		fileProducts = new File(System.getProperty("user.dir")+ testProductsPath  + testProductsFileName);
	    }
	    
	   
	    return DataUtil.getTestUnitsFromFile(fileProducts);
	}
	
	@Test
	public void testTestCaseToursSaveOrder() {
	    

        	
        	logger.debug("测试用例为： " + testUnit.getTestCase().getTestCaseName());
        	logger.debug("下单入参为: " + testUnit.getProductBookInformation().getHostUrl()+","+testUnit.getProductBookInformation().getProductID()+","+testUnit.getProductBookInformation().getBuyDate()+","+testUnit.getProductBookInformation().getAdultNum()+ "," + testUnit.getProductBookInformation().getChildNum() + "," + testUnit.getProductBookInformation().getChildFreedNum());
        	
        	//实例化测试用例
        	TestCaseSaveOrder testCaseSaveOrder = new TestCaseSaveOrder();
        	
        	//实例化测试产品
        	ToursProduct toursProduct = new ToursProduct(driver);
        	
        	//执行测试用例
        	SaveOrderResult saveOrderResult = testCaseSaveOrder.saveOrder(toursProduct, testUnit);
        	
        	//保存测试结果
        	SaveStringToFile.SaveGsonObjToFile(saveOrderResult,"saveorder_android.txt");
        	
        	//保存最后停留页面截图
        	ScreenShot.getScreenShot(driver);
        	
        	//判断测试用例执行成功与否
        	assertTrue("用例名称：" + testUnit.getTestCase().getTestCaseName() + "	失败原因：" + saveOrderResult.getMsg()+ "	测试产品：" + testUnit.getProductBookInformation().getProductID() , saveOrderResult.isSuccess());

	}

}
