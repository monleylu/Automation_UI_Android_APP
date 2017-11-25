package com.test.allrun;

import static com.test.variable.TestVariable.driverFactory;
import static com.monleylu.olbb.logback.LogBack.logger;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.test.login.UserLoginByCookies;
import com.test.testcase.orderdetail.CheckInvoiceProgressTest;
import com.test.testcase.orderdetail.CheckRefundProgressTest;
import com.test.testcase.product.diy.DiyFreeGiftPromationTest;
import com.test.testcase.product.diy.DiySaveOrderParameter;
import com.test.testcase.product.diy.DiyUncommonUserNameTest;
import com.test.testcase.product.drive.DriveFreeGiftPromationTest;
import com.test.testcase.product.drive.DriveSaveOrderParameter;
import com.test.testcase.product.team.TeamCompanySaveOrderTest;
import com.test.testcase.product.team.TeamCustomSaveOrderTest;
import com.test.testcase.product.team.TeamFamilySaveOrderTest;
import com.test.testcase.product.tours.ToursChangeSingleFlightTest;
import com.test.testcase.product.tours.ToursFreeGiftPromationTest;
import com.test.testcase.product.tours.ToursSaveOrderParameter;
import com.monleylu.olbb.driver.UserDriverFactory;

@RunWith(Suite.class)
@SuiteClasses({

    	//通过cookie注入登录场景
    	UserLoginByCookies.class,
    	
    	//退出登录测试场景,会使cookies失效
    	//LogoutTest.class,
    	
    	//跟团、跟队自驾下单场景
    	ToursSaveOrderParameter.class,
   	 
   	 	//更改机票列表功能检查
   	    ToursChangeSingleFlightTest.class,
   	    
   	    //跟团预定流程选择返赠品优惠下单
   	    ToursFreeGiftPromationTest.class,
   	    

    	
	})

public class RunAll {
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	    
	    	logger.debug("开始从RullAll类运行测试用例");
		//初始化进程实例参数
		//driver=Driver.getInstance("androiddriver.txt");
	    	if(driverFactory == null){
		    synchronized (UserDriverFactory.class) {
			if(driverFactory == null){
			    logger.debug("准备初始化驱动信息");
			    driverFactory= new UserDriverFactory("androiddriver.txt");
			    logger.debug("初始化驱动信息完毕");
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
		
		
        	AndroidDriver<AndroidElement> tmpDriver = driverFactory
        		.getDriver_poll();
        	logger.debug("释放driver实例");
        	while (tmpDriver != null) {
        	    tmpDriver.quit();
        	    tmpDriver = driverFactory.getDriver_poll();
        	}
        
        	logger.debug("RullAll类测试用例执行完毕");
        	
	}
	
	
}
