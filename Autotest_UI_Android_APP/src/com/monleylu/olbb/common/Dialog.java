package com.monleylu.olbb.common;

import static com.monleylu.olbb.staticvariables.StaticVars.logger;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Dialog {
	
	
	/**
	 * 
	 * @param locate 定位元素方法
	 * @return 元素可见返回true，否则返回false
	 */
	public static boolean VisibilityElementByLocat(AndroidDriver<AndroidElement> driver,By locate) {
		logger.debug("通过Locate判断元素是否可见，" + locate.toString());
		try {
			new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(locate));
			logger.debug("元素可见");
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			logger.debug("元素不可见");
			return false;
		}
		
		
		
	}
	
	
	
	
	/**
	 * 判断是否出现订单占位的页面
	 * @return
	 */
	public static boolean getOrderHolderState(AndroidDriver<AndroidElement> driver) {
		logger.debug("判读是否出现订单占位浮层");
		try {
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.id("com.monleylu.app.ui:id/plane_wait_layout")));
			logger.debug("存在订单占位浮层");
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			logger.debug("不存在订单占位浮层");
			return false;
		}
		
		
	}
	
	
	
	/**
	 * 验证页面是否加载完毕,安卓使用公共加载页面
	 * @return 10s内加载完毕返回true，否则返回false
	 */
	public static boolean  pageReadyState(AndroidDriver<AndroidElement> driver) {
		
		logger.debug("验证页面是否加载完毕");
		try {
			new WebDriverWait(driver, 10).until(ExpectedConditions.invisibilityOfElementLocated(By.id("com.monleylu.app.ui:id/gif_loading")));
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		
		
		
	}
	
	
	/**
	 * 判断是否存在修改出游人弹层
	 * @return 如果存在返回true，不存在返回false
	 */
	public static boolean isExistModifyTourist(AndroidDriver<AndroidElement> driver) {
		try {
			new  WebDriverWait(driver, 5).until(ExpectedConditions.invisibilityOfElementLocated(By.id("android:id/alertTitle")));
			return false;
		} catch (Exception e) {
			// TODO: handle exception
			return true;
		}
	  
		
	}
	
	

}
