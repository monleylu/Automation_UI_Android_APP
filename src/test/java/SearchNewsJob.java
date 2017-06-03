import com.demo.common.APPUtil;
import com.demo.common.ElementUtil;
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
 * 测试APP搜索新闻功能,这个主要介绍一个稍微完整些的业务流程可以如何操作，这个仍然不是一个生产级别的代码
 * Created by monley_Lu on 2017/5/31.
 */
public class SearchNewsJob {

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
    public void searchNewsJobtest() throws Exception {
        //judege if exist top toolbar search editor
        assertTrue("exist toolbar", ElementUtil.isExist(driver, By.id("com.ss.android.article.news:id/ag5"),3));

        IndexPage indexPage = new IndexPage(driver);
        SearchPage searchPage = new SearchPage(driver);

        indexPage.clickSearchNewsBtn();
        searchPage.searchNews("中文测试chinesetest");
        searchPage.clickSearchBtn();

        //usually  save screenshot at the end of testcase
        APPUtil.saveScreenShot(driver);
        assertTrue("exist result",ElementUtil.isExist(driver,By.id("com.ss.android.article.news:id/arb"),3));
    }

}
