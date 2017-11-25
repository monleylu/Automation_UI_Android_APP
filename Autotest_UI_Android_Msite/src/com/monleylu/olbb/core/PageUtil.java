/**
*Copyright (c) 2006-2016 . All Rights Reserved.
*/
package com.monleylu.olbb.core;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import com.monleylu.olbb.inter.product.PageIdentifyCode;

/**
 * 
* @Description: 页面通用公共操作
* @author lujian
* @date 2016年8月24日
* @version 
*    2016年8月24日  v1.0  create 
*
 */
public class PageUtil {
    
	/**
	 * 判断页面当前是否加载完毕，此处通过document.readyState 熟悉判断页面加载情况，对存在异步请求的页面需要额外处理
	 * @param driver 进程实例
	 * @return  返回页面实时加载状态，加载成功返回true，否则返回false
	 */
	private static boolean isPageLoadReadyOfDom(AppiumDriver<AndroidElement> driver) {
		
		Object object = driver.executeScript("return document.readyState");
		String objString = object.toString();
		return  objString.equalsIgnoreCase("complete");
		
	}

	    /**
	     * 等待页面Dom内容加载完毕，由于页面使用ajax等异步请求方式，不能单纯的通过dom是否加载情况来确认页面是否加载完毕
	     * @param driver 进程实例
	     * @return 加载完毕返回true
	     */
	    public static boolean waitPageDomLoadReady(AndroidDriver<AndroidElement> driver){
		
		while(!PageUtil.isPageLoadReadyOfDom(driver)){
		    try {
			Thread.sleep(1000);
		    } catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		    }
		}
		return true;
	    }
	    
		/**
		 * 将zepto的参数暴露到当前页面
		 */
		public static void ImportZepto(AndroidDriver<AndroidElement> driver) {
			
			driver.executeScript("seajs.use(['zepto'], function($){window.$ = $})");
			
			
		}
		
		/**
		 * 获取当前页面识别码,目前没有处理完所有识别码页面，如果要使用，可继续添加判断分支
		 * @param driver 进程实例
		 * @return 返回页面识别码
		 */
		public static int getCurrentPageIdentifyCode(AndroidDriver<AndroidElement> driver){
		    
		    //获取当前页面标题
		    String currentPageTitle = driver.getTitle();
		    
		    //获取当前页面url
		    String currentPageUrl = driver.getCurrentUrl();
		    
		    if (currentPageTitle.equalsIgnoreCase("重单提示 - autotest旅游网")) {
			return PageIdentifyCode.PageProductRepeatOrder;
		    }else if (currentPageTitle.equalsIgnoreCase("1/2选择资源")) {
			return PageIdentifyCode.PageProductStepOne;
		    }else if (currentPageTitle.equalsIgnoreCase("2/2填写订单")) {
			return PageIdentifyCode.PageProductStepTwo;
		    }else if (currentPageTitle.equalsIgnoreCase("autotest旅游网")) {
			//首页、快速网络单标题相同，需要额外处理
			if (currentPageUrl.contains("fastNetOrder")) {
			    return PageIdentifyCode.PageProductFastNetOrder;
			}else {
			    return PageIdentifyCode.PageProductDetail;
			}
		    }else if (currentPageUrl.contains("cashier")) {
			return PageIdentifyCode.PageProductCasher;
		    }else if (currentPageUrl.contains("orderSuccess")) {
			return PageIdentifyCode.PageProductBookSuccess;
		    }else if (currentPageUrl.contains("orderFail")) {
			return PageIdentifyCode.PageProductBookFail;
		    }else{
			return PageIdentifyCode.PageProductUnDefine;
		    }
		    
		    
		}
}
