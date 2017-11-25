package com.monleylu.olbb.initdriver;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;




//废弃了
public class InitDriver {

	public static AndroidDriver<AndroidElement> getDriver()  {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "anything");
		capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, ".homepage.LaunchActivity");
		capabilities.setCapability(AndroidMobileCapabilityType.APP_WAIT_ACTIVITY, ".homepage.LaunchActivity");
		capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.monleylu.app.ui");
		capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 60);
		
		try {
			return new AndroidDriver<AndroidElement>(new URL("http://172.31.2.34:4723/wd/hub"), capabilities);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
