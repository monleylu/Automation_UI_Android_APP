import com.demo.common.APPUtil;
import com.demo.common.ElementUtil;
import com.demo.pageinterface.APPToolBar;
import com.demo.pageview.IndexPage;
import com.demo.pageview.SearchPage;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

import static org.junit.Assert.assertTrue;

/**
 * 测试APP滑动功能，测试上下左右滑动
 * 注意不同软件需要设置的滑动距离参数一般都不同，需要分别调试各个参数
 * Created by monley_Lu on 2017/5/31.
 */
public class SwipeScreenJob {

    public static AndroidDriver<AndroidElement> driver = null;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {

        //appium ip address and port
        String appiumIP = "192.168.1.107";
        String appiumPort = "4723";

        //contact commom url to send command
        String url = "http://" + appiumIP + ":" + appiumPort + "/wd/hub";

        //devices udid ,use adb devices to check
        String deviceUDID = "2400bac60299";

        //今日头条
        String appPackageToOpen = "com.ss.android.article.news";
        String appPackageMainActivity = ".activity.MainActivity";


        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "nameofdevices");
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
        desiredCapabilities.setCapability(MobileCapabilityType.UDID, deviceUDID);
        desiredCapabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 180);
        desiredCapabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, appPackageToOpen);
        desiredCapabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, appPackageMainActivity);
        desiredCapabilities.setCapability(AndroidMobileCapabilityType.APP_WAIT_ACTIVITY,appPackageMainActivity);

        //in order to input chinese
        desiredCapabilities.setCapability(AndroidMobileCapabilityType.UNICODE_KEYBOARD,true);
        desiredCapabilities.setCapability(AndroidMobileCapabilityType.RESET_KEYBOARD,true);

        driver = new AndroidDriver<AndroidElement>(new URL(url), desiredCapabilities);

    }

    @Before
    public void setUp() {

    }

    @After
    public void tearDown() {

    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        if (driver != null) {
            System.out.println("exist appium driver,release data");
            driver.quit();
        }
    }

    @Test
    public void SwipeScreenJobtest() throws Exception {
        //judege if exist top toolbar search editor
        assertTrue("exist toolbar", ElementUtil.isExist(driver, By.id("com.ss.android.article.news:id/ag5"),3));

        APPUtil.swipeScreenFromBottomToTop(driver);
        APPUtil.swipeScreenFromTopToBottom(driver);
        APPUtil.swipeScreenFromLeftToRight(driver);
        APPUtil.swipeScreenFromRightToLeft(driver);

    }

}
