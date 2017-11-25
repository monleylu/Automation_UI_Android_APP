package com.monleylu.ios.automation;

import com.monleylu.internal.driver.UserDriverFactory;
import com.monleylu.ios.automation.cruise.CruiseFlowBook;
import com.monleylu.ios.automation.diy.DiyFlowBook;
import com.monleylu.ios.automation.driver.DriverFlowBook;
import com.monleylu.ios.automation.tours.ToursFlowBookTours;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import static com.monleylu.internal.variable.GlobalVars.driverFactory;
import static com.monleylu.internal.variable.GlobalVars.log;

/**
 * Created by monley_Lu on 2017/3/24.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        ToursFlowBookTours.class,
        //DriverFlowBook.class,

})
public class RunAllTest {
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {


        log.debug("开始从RullAll类运行测试用例");
        //初始化进程实例参数
        //driver=Driver.getInstance("androiddriver.txt");
        if (driverFactory == null) {
            synchronized (UserDriverFactory.class) {
                if (driverFactory == null) {
                    log.debug("准备初始化驱动信息");
                    driverFactory = new UserDriverFactory("iosdriver.txt");
                    log.debug("初始化驱动信息完毕");
                    if (driverFactory.getSize() == 0) {
                        log.debug("驱动初始化后，没有有效驱动实例，请检查配置文件维护的手机节点是否有效");
                        throw new Exception("驱动初始化后，没有有效驱动实例，请检查配置文件维护的手机节点是否有效,退出执行程序");

                    }
                }
            }
        }
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {

        IOSDriver<IOSElement> tmpDriver = driverFactory.getDriver_poll();
        log.debug("释放driver实例");
        while (tmpDriver != null) {
            tmpDriver.quit();
            tmpDriver = driverFactory.getDriver_poll();
        }

        log.debug("RullAll类测试用例执行完毕");
    }
}
