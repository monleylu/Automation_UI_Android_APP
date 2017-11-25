/**
 * 注入登录
 */
package com.monleylu.olbb.login;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import java.util.ArrayList;

import org.openqa.selenium.Cookie;

import com.monleylu.olbb.cookies.UserCookies;
import com.monleylu.olbb.cookies.UserCookiesObj;
import com.monleylu.olbb.core.PageUtil;


public class Login {
	
	
	/**
	 * M站通过注入技术登录系统
	 * @param driver 进程实例
	 * @param hostUrl 待注入域名
	 * @param fileNameStoreCookie 注入使用的cookie文件
	 * @return  cookies注入成功返回true，否则返回false
	 */
	public static boolean  injectLogin(AndroidDriver<AndroidElement> driver,String hostUrl,String fileNameStoreCookie ){
		
		//默认sso会员中心地址
		String ssoHost="passport.monlylu.com";
		//logger.debug("start login m site by inject");
		if (!hostUrl.trim().equalsIgnoreCase("m.monlylu.com") && !hostUrl.trim().equalsIgnoreCase("m-p.monlylu.com") ) {
			ssoHost="passport.sit.monlylu.org";
		} 
		
		//driver.get("http://"+ HostUrl + "/u/login");
		driver.get("https://" + ssoHost + "/login/mapp?origin=http://dynamic." + hostUrl + "/user/tokenLogin");
		
		PageUtil.waitPageDomLoadReady(driver);
		
/*		boolean booleanPageState = PageReadyState.getPageReadyState(driver);
		//logger.debug("judge  web readyState");
		while (!booleanPageState) {
			
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			booleanPageState = PageReadyState.getPageReadyState(driver);
		}*/
		
		//验证是否已登录
		String getCurrentUrl=driver.getCurrentUrl();
		if (getCurrentUrl.equals("http://"+  hostUrl + "/")) {
			return true;
		}
		
		//m站升级https，需要修改此处代码
		if (getCurrentUrl.equals("https://"+  hostUrl + "/#form_http")) {
			return true;
		}
		
		//logger.debug("web ready");
		 //inject cookies
		 UserCookies userCookies = new UserCookies(fileNameStoreCookie);
		 ArrayList<UserCookiesObj> cookiesArrayList = userCookies.arrayListCookies(userCookies.getCookiesPath());
		 
		 for (int i = 0; i < cookiesArrayList.size(); i++) {
			Cookie cookie = new Cookie(cookiesArrayList.get(i).getNameString(), cookiesArrayList.get(i).getValueString());
			driver.manage().addCookie(cookie);
		}
		 
		 driver.get("http://"+ hostUrl + "/u/login");
		 
		 getCurrentUrl=driver.getCurrentUrl();
		 //如果已经登录成功，就不会有passport登录url
		 if(!getCurrentUrl.contains("passport")){
			return true;
		}else {
			//logger.debug("inject login false");
			return false;
		}
		 
		
		
	}
	
	/**
	 * 1/2返赠品优惠添加地址后通过cookie登录跳回地址列表
	 * @param driver 进程实例
	 * @param hostUrl 待注入域名
	 * @param fileNameStoreCookie 注入使用的cookie文件
	 * @return  cookies注入成功返回true，否则返回false
	 */
	public static boolean  LoginToStepOne(AndroidDriver<AndroidElement> driver,String hostUrl,String fileNameStoreCookie ){
		
		//默认sso会员中心地址
		String ssoHost="passport.monlylu.com";
		//logger.debug("start login m site by inject");
		if (!hostUrl.trim().equalsIgnoreCase("m.monlylu.com") && !hostUrl.trim().equalsIgnoreCase("m-p.monlylu.com") ) {
			ssoHost="passport.sit.monlylu.org";
		} 
		
		//driver.get("http://"+ HostUrl + "/u/login");
		driver.get("https://" + ssoHost + "/login/mapp?origin=http://dynamic." + hostUrl + "/user/tokenLogin");
		
		PageUtil.waitPageDomLoadReady(driver);
			
		//验证是否已登录
		String getCurrentUrl=driver.getCurrentUrl();
		if (getCurrentUrl.equals("http://"+  hostUrl + "/")) {
			return true;
		}
		
		//m站升级https，需要修改此处代码
		if (getCurrentUrl.equals("https://"+  hostUrl + "/#form_http")) {
			return true;
		}
		
		//logger.debug("web ready");
		 //inject cookies
		 UserCookies userCookies = new UserCookies(fileNameStoreCookie);
		 ArrayList<UserCookiesObj> cookiesArrayList = userCookies.arrayListCookies(userCookies.getCookiesPath());
		 
		 for (int i = 0; i < cookiesArrayList.size(); i++) {
			Cookie cookie = new Cookie(cookiesArrayList.get(i).getNameString(), cookiesArrayList.get(i).getValueString());
			driver.manage().addCookie(cookie);
		}
		 
		 driver.get("https://"+ ssoHost + "/login/mapp?origin=https://m.monlylu.com/user/tokenLogin/urlRefer/JTJGbTIwMTUlMkZib29rJTJGZ3JvdXBUb3VyQm9vayUyRnByb21vdGlvbkRlbGl2ZXJ5QWRkcmVzcw%3D%3D&pValue=");
		 
		 getCurrentUrl=driver.getCurrentUrl();
		 //如果已经登录成功，就不会有passport登录url
		 if(!getCurrentUrl.contains("passport")){
			return true;
		}else {
			//logger.debug("inject login false");
			return false;
		}	
		
	}
	
	/**
	 * M站通过注入技术登录系统，域名自包含在cookies中
	 * @param driver 进程实例
	 * @param fileNameStoreCookie 注入使用的cookie文件
	 * @return  cookies注入成功返回true，否则返回false
	 */
	public static boolean  injectLogin(AndroidDriver<AndroidElement> driver,String fileNameStoreCookie ){
		
	    	 String hostUrl="";
	    	 UserCookies userCookies = new UserCookies(fileNameStoreCookie);
		 ArrayList<UserCookiesObj> cookiesArrayList = userCookies.arrayListCookies(userCookies.getCookiesPath());
		 for (UserCookiesObj userCookiesObj : cookiesArrayList) {
		    if (userCookiesObj.getNameString().equalsIgnoreCase("Host")) {
			hostUrl = userCookiesObj.getValueString().trim();
			break;
		    }
		}
		 
		//默认sso会员中心地址
		String ssoHost="passport.monlylu.com";
		//logger.debug("start login m site by inject");
		if (!hostUrl.trim().equalsIgnoreCase("m.monlylu.com") && !hostUrl.trim().equalsIgnoreCase("m-p.monlylu.com") ) {
			ssoHost="passport.sit.monlylu.org";
		} 
		
		//driver.get("http://"+ HostUrl + "/u/login");
		driver.get("https://" + ssoHost + "/login/mapp?origin=http://dynamic." + hostUrl + "/user/tokenLogin");
		
		PageUtil.waitPageDomLoadReady(driver);
		
/*		boolean booleanPageState = PageReadyState.getPageReadyState(driver);
		//logger.debug("judge  web readyState");
		while (!booleanPageState) {
			
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			booleanPageState = PageReadyState.getPageReadyState(driver);
		}*/
		
		String getCurrentUrl=driver.getCurrentUrl();
		if (getCurrentUrl.equals("http://"+  hostUrl + "/")) {
			return true;
		}
		
		//m站升级https，需要修改此处代码
        	if (getCurrentUrl.equals("https://" + hostUrl + "/#form_http")) {
        	    return true;
        	}
		
		//logger.debug("web ready");
		 //inject cookies
		/* UserCookies userCookies = new UserCookies(fileNameStoreCookie);
		 ArrayList<UserCookiesObj> cookiesArrayList = userCookies.arrayListCookies(userCookies.getCookiesPath());*/
		 
		 for (int i = 0; i < cookiesArrayList.size(); i++) {
			Cookie cookie = new Cookie(cookiesArrayList.get(i).getNameString(), cookiesArrayList.get(i).getValueString());
			driver.manage().addCookie(cookie);
		}
		 
		 driver.get("http://"+ hostUrl + "/u/login");
		 
		 getCurrentUrl=driver.getCurrentUrl();
		 //如果已经登录成功，就不会有passport登录url
		 if (!getCurrentUrl.contains("passport")) {
			 //logger.debug("inject login success");
			 return true;
		}else {
			//logger.debug("inject login false");
			return false;
		}
		 
		
		
	}

}
