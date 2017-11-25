/**
*Copyright (c) 2006-2016 . All Rights Reserved.
*/
package com.monleylu.olbb.driver;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * 
* @Description: 驱动实例
* @author lujian
* @date 2016年8月23日
* @version 
*    2016年8月23日  v1.0  create 
*
 */
public class Driver {
    
    /** 测试进程实例  **/
    private AndroidDriver<AndroidElement> androidDriver;
    
    private static volatile Driver driver = null;
    
    private String driverFileName;
    
    /**
     * 构造函数，驱动所在的文件名称
     * @param driverFileName  驱动所在的文件名称
     * @throws MalformedURLException 
     */
    private  Driver(String driverFileName) throws MalformedURLException{
	this.driverFileName=driverFileName;
	/** 保存driver访问路径，默认为本地访问路径，端口为默认端口  **/
  	String driverString="http://127.0.0.1:4723/wd/hub";
  	
  	//参数文档位置http://appium.io/slate/en/master/#running-on-windows.md
	DesiredCapabilities capabilities = new DesiredCapabilities();
	capabilities.setCapability("device", "Android");
	//capabilities.setCapability("app", "Chrome");
	capabilities.setCapability(CapabilityType.BROWSER_NAME, "Chrome");
	capabilities.setCapability("deviceName", "Android Emulator");
	capabilities.setCapability("CapabilityType.VERSION", "4.4");
	capabilities.setCapability("CapabilityType.PLATFORM", "WINDOWS");
	
	try {
	    Gson gson = new Gson();
	    File file = new File(System.getProperty("user.dir")+"/initdata/androiddriver/" +this.driverFileName);
	    List<String> listDriver =  FileUtils.readLines(file);
	    UserDriver driverGsonTmp = gson.fromJson(listDriver.get(0), new TypeToken<UserDriver>(){}.getType());
	    driverString="http://" + driverGsonTmp.getIp() +":" + driverGsonTmp.getPort() + "/wd/hub";
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	
	
	this.androidDriver= new AndroidDriver<AndroidElement>(new URL(driverString), capabilities);
    }
    
    //lazy double check
    public static Driver getInstance(String driverFileName) throws MalformedURLException{
	if (driver == null) {
	    synchronized (Driver.class) {
		if (driver ==null) {
		   driver= new Driver(driverFileName);
		}
	    }
	}
	return driver;
    }

    public AndroidDriver<AndroidElement> getAndroidDriver() {
	
	return androidDriver;
	
    }

    public void setAndroidDriver(AndroidDriver<AndroidElement> androidDriver) {
	this.androidDriver = androidDriver;
    }
    
    
    

}
