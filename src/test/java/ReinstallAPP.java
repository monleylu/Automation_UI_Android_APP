import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import org.junit.*;
import org.openqa.selenium.remote.DesiredCapabilities;

import javax.net.ssl.SSLContext;
import java.net.URL;

import static org.junit.Assert.assertTrue;

/**
 * 安装以及卸载app，如果APP已安装，先卸载然后再安装，这里也测试了运行程序之前，如果没有安装APP，两种DesiredCapabilities设置程序的响应；
 * 本用例还验证了不同DesiredCapabilities设置时，appium的launchApp操作对应的不同行为模式，注意观察
 * Created by monley_Lu on 2017/5/31.
 */
public class ReinstallAPP {

    public static AndroidDriver<AndroidElement> driver = null;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {

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
    @Ignore
    public void reinstallAPPtest() throws Exception {

        //user current work directory
        String pathRoot=System.getProperty("user.dir");

        //path where apk to be install
        String apkPrefixName="jinritoutiao_";
        String apkVersion="v6.0.4";

        //full apk path
        String fullApkPath=pathRoot+ "/src/main/resources/appsources/" +apkPrefixName + apkVersion +".apk";

        //appium ip address and port
        String appiumIP = "127.0.0.1";
        String appiumPort = "4723";

        //contact commom url to send command
        String url = "http://" + appiumIP + ":" + appiumPort + "/wd/hub";

        //devices udid ,use adb devices to check
        String deviceUDID = "2400bac60299";

        //今日头条package和首页activity
        String appPackageToOpen = "com.ss.android.article.news";
        String appPackageMainActivity = ".activity.MainActivity";


        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "nameofdevices");
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
        desiredCapabilities.setCapability(MobileCapabilityType.UDID, deviceUDID);
        desiredCapabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 180);
        desiredCapabilities.setCapability(MobileCapabilityType.APP,fullApkPath);


        driver = new AndroidDriver<AndroidElement>(new URL(url), desiredCapabilities);

        if (driver.isAppInstalled(appPackageToOpen)){
            System.out.println("APP already install,so uninstall first");
            driver.removeApp(appPackageToOpen);
            driver.installApp("/Users/mac/IdeaProjects/Automation_UI_Android_APP/src/main/resources/appsources/jinritoutiao_v6.1.7.apk");
            driver.launchApp();//becarefor ,this action will uninstall apk first ,and then install apk provide in desiredCapabilities,so now app version is v6.0.4
        }else{
            System.out.println("APP has not yet install");
            driver.installApp("/Users/mac/IdeaProjects/Automation_UI_Android_APP/src/main/resources/appsources/jinritoutiao_v6.1.7.apk");
            driver.launchApp(); //becarefor ,this action will uninstall apk first ,and then install apk provide in desiredCapabilities,so now app version is v6.0.4
        }


    }



    @Test
    public void reinstallAPPtest2() throws Exception {

        //appium ip address and port
        String appiumIP = "127.0.0.1";
        String appiumPort = "4723";

        //contact commom url to send command
        String url = "http://" + appiumIP + ":" + appiumPort + "/wd/hub";

        //devices udid ,use adb devices to check
        String deviceUDID = "2400bac60299";

        //今日头条package和首页activity
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


        driver = new AndroidDriver<AndroidElement>(new URL(url), desiredCapabilities);

        if (driver.isAppInstalled(appPackageToOpen)){
            System.out.println("APP already install,so uninstall first");
            driver.removeApp(appPackageToOpen);
            driver.installApp("/Users/mac/IdeaProjects/Automation_UI_Android_APP/src/main/resources/appsources/jinritoutiao_v6.1.7.apk");
            driver.launchApp();
        }else{
            System.out.println("APP has not yet install");
            driver.installApp("/Users/mac/IdeaProjects/Automation_UI_Android_APP/src/main/resources/appsources/jinritoutiao_v6.1.7.apk");
            driver.launchApp(); //becarefor ,this action will uninstall apk first ,and then install apk provide in desiredCapabilities
        }


    }

}
