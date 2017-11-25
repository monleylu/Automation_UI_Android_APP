package com.test.run;


import static com.test.variable.TestVariable.driverFactory;
import static com.monleylu.olbb.staticvariables.StaticVars.logger;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.monleylu.olbb.driver.UserDriverFactory;
import com.monleylu.olbb.junit.cruise.TestCaseCruiseSaveOrderTest;
import com.monleylu.olbb.junit.diy.DiySampleOrderTest;
import com.monleylu.olbb.junit.drive.DriveSampleOrderTest;
import com.monleylu.olbb.junit.tours.TestCaseToursSaveOrderTest;

@RunWith(Suite.class)
@SuiteClasses({
    	//跟团、跟队自驾、常规自驾打包售卖
    	TestCaseToursSaveOrderTest.class,

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
		
            	AndroidDriver<AndroidElement> tmpDriver = driverFactory.getDriver_poll();
        	logger.debug("释放driver实例");
        	while (tmpDriver != null) {
        	    tmpDriver.quit();
        	    tmpDriver = driverFactory.getDriver_poll();
        	}
        
        	logger.debug("RullAll类测试用例执行完毕");
	}
	

}
