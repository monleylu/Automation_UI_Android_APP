/**
*Copyright (c) 2006-2016 . All Rights Reserved.
*/
package com.monleylu.olbb.login;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import java.util.ArrayList;

import org.openqa.selenium.Cookie;

import com.monleylu.olbb.cookies.UserCookies;
import com.monleylu.olbb.cookies.UserCookiesObj;

public class Logout {
    
    /**
     * 通过调用系统的退出链接退出指定的系统，执行后登录cookies失效，不能继续使用注入登录
     * @param driver 进程实例
     * @param host 退出登录的系统主域名，例如“m.monlylu.com”
     * @return 退出登录返回true，否则返回false
     */
    public static boolean logoutSystemBySystemFunction(AndroidDriver<AndroidElement> driver , String host){
	
	//调用退出登录接口
	driver.get("http://dynamic."+ host.trim() + "/user/doLogout");
	
	String titleString = driver.getTitle();
	if (titleString.contains("用户退出 - autotest通行证")) {
	    return true;
	}else if (driver.getCurrentUrl().contains(host.trim())) {
	    return true;
	}else {
	    return false;
	}
	
    }
    
    /**
     * 通过调用系统的退出链接退出指定的系统，主域名通过cookies读取，执行后登录cookies失效，不能继续使用注入登录
     * @param driver 进程实例
     * @param fileNameStoreCookie 注入使用的cookie文件
     * @return 退出登录返回true，否则返回false
     */
    public static boolean logoutSystemBySystemFunctionWithCookieFile(AndroidDriver<AndroidElement> driver , String fileNameStoreCookie){
	
	String hostUrl="";
   	 UserCookies userCookies = new UserCookies(fileNameStoreCookie);
	 ArrayList<UserCookiesObj> cookiesArrayList = userCookies.arrayListCookies(userCookies.getCookiesPath());
	 for (UserCookiesObj userCookiesObj : cookiesArrayList) {
	    if (userCookiesObj.getNameString().equalsIgnoreCase("Host")) {
		hostUrl = userCookiesObj.getValueString().trim();
		break;
	    }
	}
	//调用退出登录接口
	driver.get("http://dynamic."+ hostUrl.trim() + "/user/doLogout");
	
	String titleString = driver.getTitle();
	if (titleString.contains("用户退出 - autotest通行证")) {
	    return true;
	}else if (driver.getCurrentUrl().contains(hostUrl.trim())) {
	    return true;
	}else {
	    return false;
	}
	
    }
    
    /**
     * 通过清空所有注册cookies完成退出操作，不调用系统退出链接，原始注入cookies仍有效
     * @param driver 进程实例
     * @param hostUrl  m站主域名
     *
     */
    public static void logoutSystemByDeleteCookies(AndroidDriver<AndroidElement> driver,String hostUrl){
	//默认sso会员中心地址
	String ssoHost = "passport.monlylu.com";
	// logger.debug("start login m site by inject");
	if (!hostUrl.trim().equalsIgnoreCase("m.monlylu.com")
		&& !hostUrl.trim().equalsIgnoreCase("m-p.monlylu.com")) {
	    ssoHost = "passport.sit.monlylu.org";
	}
	
	driver.manage().deleteAllCookies();
	//模拟退出流程，但是仅是欺骗浏览器退出登录，服务器端认证信息仍然存在，可继续使用
	Cookie c8= new Cookie("PASSPORTSESSID","7758521");
	
	String logoutUrl ="https://"+ ssoHost + "//logout/passport?logout_hash=21048862a4c5b2aed70e33d6dd72676a&back_url=http://" + hostUrl + "/";
	
	driver.get(logoutUrl);
	driver.manage().addCookie(c8); 

    }
    
    /**
     * 通过清空所有注册cookies完成退出操作，不调用系统退出链接，原始注入cookies仍有效，主域名通过cookies文件读取
     * @param driver 进程实例
     * @param fileNameStoreCookie  存储cookie的文件
     *
     */
    public static void logoutSystemByDeleteCookiesWithCookieFile(AndroidDriver<AndroidElement> driver,String fileNameStoreCookie){
	
	 //取消登录的主域名,默认生产环境
	 String hostUrl="m.monlylu.com";
  	 UserCookies userCookies = new UserCookies(fileNameStoreCookie);
	 ArrayList<UserCookiesObj> cookiesArrayList = userCookies.arrayListCookies(userCookies.getCookiesPath());
	 for (UserCookiesObj userCookiesObj : cookiesArrayList) {
	    if (userCookiesObj.getNameString().equalsIgnoreCase("Host")) {
		hostUrl = userCookiesObj.getValueString().trim();
		break;
	    }
	}
	 
	//默认sso会员中心地址
	String ssoHost = "passport.monlylu.com";
	// logger.debug("start login m site by inject");
	if (!hostUrl.trim().equalsIgnoreCase("m.monlylu.com")
		&& !hostUrl.trim().equalsIgnoreCase("m-p.monlylu.com")) {
	    ssoHost = "passport.sit.monlylu.org";
	}
	
	driver.manage().deleteAllCookies();
	//模拟退出流程，但是仅是欺骗浏览器退出登录，服务器端认证信息仍然存在，可继续使用
	Cookie c8= new Cookie("PASSPORTSESSID","7758521");
	
	String logoutUrl ="https://"+ ssoHost + "//logout/passport?logout_hash=21048862a4c5b2aed70e33d6dd72676a&back_url=http://" + hostUrl + "/";
	
	driver.get(logoutUrl);
	driver.manage().addCookie(c8); 

    }

}
