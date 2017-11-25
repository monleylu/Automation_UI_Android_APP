/**
 * 对元素的操作，如是否存在等
 */
package com.monleylu.olbb.common;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementUtil {
	
	
	/**
	 * 判断元素是否存在
	 * @param by  定位参数
	 * @param timeoutSec  等待时间
	 * @return  如果存在，返回true，否则返回false
	 * 
	 */
	public static boolean isExist(AndroidDriver<AndroidElement> driver,By by ,int timeoutSec){
		
		try {
			new WebDriverWait(driver, timeoutSec).until(ExpectedConditions.visibilityOfElementLocated(by));
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}		
	}
	
	/**
	 * 判断元素中子元素是否存在
	 * @param driver 驱动实例
	 * @param element 父元素
	 * @param childLocator 子元素识别标识
	 * @param timeoutSec 等待时间
	 * @return
	 */
	public static boolean isExist(AndroidDriver<AndroidElement> driver,MobileElement element,By childLocator ,int timeoutSec){
		
		try {
			new WebDriverWait(driver, timeoutSec).until(ExpectedConditions.visibilityOfNestedElementsLocatedBy(element, childLocator));
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}		
	}
	
	/**
	 * 判断元素是否存在，使用系统默认的查询等待时间
	 * @param by 元素定位标示
	 * @return 找到元素返回true，否则返回false
	 */
	public static boolean isExist(AndroidDriver<AndroidElement> driver,By by) {
	    try {
		driver.findElement(by);
		return true;
	    } catch (Exception e) {
		// TODO: handle exception
		return false;
	    }
	    
	}
	
	
	/**
	 * 指定时间内，期望指定元素可见，可见不仅指元素存在Dom树中，并且具有大小
	 * @param driver  实例
	 * @param by   元素定位标识
	 * @param timeToWaitInSec  等待时间，单位秒
	 * @return 元素可见返回true，
	 */
	public static boolean  isVisibility(AndroidDriver<AndroidElement> driver,By by ,int timeToWaitInSec) {
		
		WebDriverWait webDriverWait  = new WebDriverWait(driver, timeToWaitInSec);
		
		try {
			webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(by));
			return Boolean.TRUE;
		} catch (Exception e) {
			// TODO: handle exception
			return Boolean.FALSE;
		}
		
		
	}
	
	/**
	 * 指定时间期望指定元素不可见，不可见包括元素肉眼不可见或者DOM树中不可见
	 * @param driver
	 * @param by
	 * @param timeToWaitInSec
	 * @return 当元素不可见或者DOM树中不存在返回true 
	 */
	public static boolean  isInVisibility(AppiumDriver<AndroidElement> driver,By by ,int timeToWaitInSec) {
		WebDriverWait webDriverWait  = new WebDriverWait(driver, timeToWaitInSec);
		try {
			webDriverWait.until(ExpectedConditions.invisibilityOfElementLocated(by));
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		
	}
	
	
	/**
	 * 判断元素上的字符是否包含指定的待匹配字符串
	 * @param driver 进程实例
	 * @param by     待匹配元素
	 * @param textToMatch  待被包含匹配的字符串
	 * @param timeToWaitInSec  等待时间
	 * @return  如果元素字符串包含待匹配字符串则返回true，其他返回false
	 */
	public static boolean isTextPresent(AppiumDriver<AndroidElement> driver,By by ,String textToMatch , int timeToWaitInSec) {
	    WebDriverWait webDriverWait  = new WebDriverWait(driver, timeToWaitInSec);
	    try {
		webDriverWait.until(ExpectedConditions.textToBePresentInElementLocated(by, textToMatch));
		return true;
	    } catch (Exception e) {
		// TODO: handle exception
		return false;
	    }
	    
	}

	
	/**
	 * 判断元素是否可点击
	 * @param driver  进程实例
	 * @param by  元素定位标示
	 * @param timeToWaitInSec 等待时间
	 * @return  可点击返回true，否则返回false
	 */
	public static boolean isClickable(AppiumDriver<AndroidElement> driver,By by ,int timeToWaitInSec) {
	    WebDriverWait webDriverWait  = new WebDriverWait(driver, timeToWaitInSec);
	    try {
		webDriverWait.until(ExpectedConditions.elementToBeClickable(by));
		return true;
	    } catch (Exception e) {
		// TODO: handle exception
		//e.printStackTrace();
		return false;
	    }
	    
	}
	

}
