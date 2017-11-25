/**
*Copyright (c) 2006-2016 . All Rights Reserved.
*/
package com.monleylu.olbb.core;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * 
* @Description: 元素通用操作
* @author lujian
* @date 2016年8月24日
* @version 
*    2016年8月24日  v1.0  create 
*
 */
public class ElementUtil {

	/**
	 * 判断元素是否存在
	 * @param androidDriver
	 * @param Locator 判断元素是否存在,使用系统默认时间
	 * @return  如果存在返回true，否则返回false
	 */
	public static boolean isExist(AndroidDriver<AndroidElement> androidDriver,By Locator) {
		try {
		    	androidDriver.findElement(Locator);
			return true;
			
		} catch (org.openqa.selenium.WebDriverException e) {
			// TODO: handle exception.
			//System.out.println(e);
			return false;
		}
		
	}
	
	/**
	 * 预期指定时间内元素可见
	 * @param androidDriver driver 实例
	 * @param Locator  定位元素标识
	 * @param timeToWaitInSec 等待时间
	 * @return  元素可见返回true，否则返回false
	 */
	public static boolean  waitVisibilityOnExpectTime(AndroidDriver<AndroidElement> androidDriver,By Locator,int timeToWaitInSec) {
		
		WebDriverWait webDriverWait = new WebDriverWait(androidDriver, timeToWaitInSec);
		try {
			webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(Locator));
			return Boolean.TRUE;
		} catch (Exception e) {
			// TODO: handle exception
			return Boolean.FALSE;
			
		}
	}
	
	/**
	 * 预期指定时间内元素不可见
	 * @param androidDriver   driver 实例
	 * @param locator  定位元素标识
	 * @param timeToWaitInSec 等待时间
	 * @return 元素不可见或者不存在返回true，否则返回false
	 */
	public static boolean waitInVisibilityOnExpectTime(AndroidDriver<AndroidElement> androidDriver,By locator,int timeToWaitInSec) {
		WebDriverWait webDriverWait = new WebDriverWait(androidDriver, timeToWaitInSec);
		try {
			webDriverWait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		
		
	}
	
	/**
	 * 检测元素是否在DOM树上存在，不一定可见
	 * @param androidDriver  进程实例
	 * @param locator  定位元素标示
	 * @param timeToWaitInSec  等待时间
	 * @return  在DOM上检测到元素返回true，超时或者其他返回false
	 */
	public static boolean  waitElementPresentOnDOMOnExpectTime(AndroidDriver<AndroidElement> androidDriver,By locator,int timeToWaitInSec) {
		WebDriverWait webDriverWait = new WebDriverWait(androidDriver, timeToWaitInSec);
		try {
			webDriverWait.until(ExpectedConditions.presenceOfElementLocated(locator));
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
	
	/**
	 * 判断指定文字是否展示元素上
	 * @param androidDriver 进程实例
	 * @param locator  定位元素标示
	 * @param stringToPresent 待展示文字
	 * @param timeToWaitInSec 等待时间
	 * @return 指定时间内，元素上的文件为指定文字时，返回true
	 */
	public static boolean waitTextPresentInElementOnExpectTime(AndroidDriver<AndroidElement> androidDriver,By locator,String stringToPresent,int timeToWaitInSec){
	    
	    WebDriverWait webDriverWait = new WebDriverWait(androidDriver, timeToWaitInSec);
	    try {
		webDriverWait.until(ExpectedConditions.textToBePresentInElementLocated(locator, stringToPresent));
		return true;
	    } catch (Exception e) {
		// TODO: handle exception
		return false;
	    }
	    
	}
	
	/**
	 * 指定时间内，当前页面url是否包含指定字符串
	 * @param androidDriver 进程实例
	 * @param stringContain 待匹配字符串
	 * @param timeToWaitInSec 等待时间
	 * @return 当url包含指定字符串返回true
	 */
	public static boolean waitUrlContainsSpecificTextOnExpectTime(AndroidDriver<AndroidElement> androidDriver,String stringContain,int timeToWaitInSec){
	   
	    WebDriverWait webDriverWait = new WebDriverWait(androidDriver, timeToWaitInSec);
	    try {
		webDriverWait.until(ExpectedConditions.urlContains(stringContain));
		return true;
	    } catch (Exception e) {
		// TODO: handle exception
		return false;
	    }
	}
	
	/**
	 * 指定时间内，当前页面url是否不包含指定字符串
	 * @param androidDriver 进程实例
	 * @param stringContain 待匹配字符串
	 * @param timeToWaitInSec 等待时间
	 * @return 当url不包含指定字符串返回true
	 */
	public static boolean waitUrlNotContainsSpecificTextOnExpectTime(AndroidDriver<AndroidElement> androidDriver,String stringContain,int timeToWaitInSec){
	   
	    WebDriverWait webDriverWait = new WebDriverWait(androidDriver, timeToWaitInSec);
	    try {
		webDriverWait.until(ExpectedConditions.not(ExpectedConditions.urlContains(stringContain)));
		return true;
	    } catch (Exception e) {
		// TODO: handle exception
		return false;
	    }
	}
	
	
	/**
	 * 指定时间，等待元素属性为指定值
	 * @param androidDriver 进程实例
	 * @param locator 元素定位标示
	 * @param attribute 元素属性
	 * @param toBeValue 期望属性值
	 * @param timeToWaitInSec 等待时间
	 * @return 指定时间制定属性出现返回true
	 */
	public static boolean waitAttributeToBeOnExpectTime(AndroidDriver<AndroidElement> androidDriver,By locator,String attribute,String toBeValue,int timeToWaitInSec){
	    WebDriverWait webDriverWait = new WebDriverWait(androidDriver, timeToWaitInSec);
	    try {
		webDriverWait.until(ExpectedConditions.attributeToBe(locator, attribute, toBeValue));
		return true;
	    } catch (Exception e) {
		// TODO: handle exception
		return false;
	    }
	}
	
	

}
