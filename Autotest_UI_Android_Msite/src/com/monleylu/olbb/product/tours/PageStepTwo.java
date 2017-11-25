/**
*Copyright (c) 2006-2016 . All Rights Reserved.
*/
package com.monleylu.olbb.product.tours;

import static com.monleylu.olbb.logback.LogBack.logger;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;

import com.monleylu.olbb.core.ElementUtil;
import com.monleylu.olbb.core.PageUtil;
import com.monleylu.olbb.inter.product.BasePage;
import com.monleylu.olbb.inter.product.CommonActionModule;
import com.monleylu.olbb.inter.product.ContactModule;
import com.monleylu.olbb.inter.product.PageIdentifyCode;
import com.monleylu.olbb.inter.product.TouristModule;
import com.monleylu.olbb.inter.product.UnCommonUserNameModule;
import com.monleylu.olbb.result.SaveOrderResult;

public class PageStepTwo extends BasePage implements CommonActionModule,TouristModule,UnCommonUserNameModule,ContactModule {

    public PageStepTwo(AndroidDriver<AndroidElement> androidDriver) {
	
	super(androidDriver);
    }

    @Override
    public boolean waitPageLoadReady(int timeToWaitInSec) {
	
	PageUtil.waitPageDomLoadReady(this.getAndroidDriver());
	return ElementUtil.waitTextPresentInElementOnExpectTime(this.getAndroidDriver(), By.id("nextStep"), "立即付款", timeToWaitInSec);
	
    }

    @Override
    public boolean clickCommonTouristBtn() {
	
	return this.clickElement(this.find(By.xpath("//*[@class='tourist-more']")));
    }

    @Override
    public boolean clickAdultTouristBtn() {
	
	return this.clickElement(this.find(By.id("tourist-adult")));
    }

    @Override
    public boolean clickChildTouristBtn() {
	
	return this.clickElement(this.find(By.id("tourist-child")));
    }

    @Override
    public boolean clickFreeChildTouristBtn() {
	
	return this.clickElement(this.find(By.id("tourist-free-child")));
    }
    
    /**
     * 通过native swipe方式向下滑动
     * @param driver  进程实例
     */
    public void scrollToBottomByNativeSwipe(){
	String contextString = this.getAndroidDriver().getContext();
	this.getAndroidDriver().context("NATIVE_APP");
	Dimension screenDimension = this.getAndroidDriver().manage().window().getSize();
	//System.err.println(screenDimension.getWidth()+":"+ screenDimension.getHeight());
	this.getAndroidDriver().swipe(screenDimension.getWidth()/2, screenDimension.getHeight()/2, screenDimension.getWidth()*3/4,screenDimension.getHeight()/4, 500);
	this.getAndroidDriver().context(contextString);
	
    }
    
	/**
	 * 通过执行javascript滚动页面到最底部
	 */
	public  void  scrollToBottomByJs() {
		// 将页面滚动条拖到底部
		String js = "var q=document.scrollingElement.scrollTop=10000";
		this.getAndroidDriver().executeScript(js);
	}
	
	
	/**
	 * 2/2页面勾选出游人是否满足出游条件
	 * @return 点击完毕返回true
	 */
	public  boolean  selectLimitAgreeBtn() {
	    
	    //判断是否为react的火车票页面
	    if (ElementUtil.isExist(this.getAndroidDriver(), By.xpath("//div[@class='limit_agree']/i[@class='agree_choice icon-no-choice']"))) {
		this.clickElement(this.find(By.xpath("//div[@class='limit_agree']/i[@class='agree_choice icon-no-choice']")));
	    }else {
		//解决勾选协议有缓存问题
		if (this.find(By.xpath("//*[@id=\"limit-agree\"]")).getAttribute("class").contains("icon-no-choice")) {
		    this.clickElement(this.find(By.xpath("//*[@id=\"limit-agree\"]")));
		}
	    }
	    return true;
	}
	
	/**
	 * 2/2协议勾选，阅读协议、须知等
	 * @return 点击完毕返回true
	 */
	public  boolean  selectNoteAgreeBtn() {
	  
	    //判断是否为react的火车票页面
	    if (ElementUtil.isExist(this.getAndroidDriver(), By.xpath("//div[@class='agree_agreement']/i[@class='agree_choice icon-no-choice']"))) {
		this.clickElement(this.find(By.xpath("//div[@class='agree_agreement']/i[@class='agree_choice icon-no-choice']")));
	    }else {
		//熟悉比较时，不能使用全部的熟悉来比较，因为发现客户端的熟悉和chrome模拟的熟悉顺序不一定一致
		if (this.find(By.xpath("//*[@id=\"note-agree\"]")).getAttribute("class").contains("icon-no-choice")) {
		    this.clickElement(this.find(By.xpath("//*[@id=\"note-agree\"]")));
		}
	    }
	    return true;
	}
    
	/**
	 * 点击下一步按钮
	 * @return 点击完毕返回true
	 */
	public boolean clickNextStepBtn(){
	    return this.clickElement(this.find(By.id("nextStep")));
	}
	
	/**
	 * 获取下单结果
	 * @param saveOrderResult 保存预订信息和结果对象
	 */
	public void getSaveOrderResult(SaveOrderResult saveOrderResult) {
		boolean continueLoop = true;
		int continueLoopInt=0;
		String strAttribute;
		String orderID="";
		AndroidDriver<AndroidElement> driver = this.getAndroidDriver();
		
		logger.debug("检查2/2点击立即付款后，是否变成提交中文案");
		
		if (!ElementUtil.waitTextPresentInElementOnExpectTime(getAndroidDriver(),By.id("nextStep"), "提交中", 5)) {
		    saveOrderResult.setSuccess(false);
		    saveOrderResult.setMsg("等待5s，2/2页面付款按钮仍然没有改变文案，应该是出了某些问题或者页面有不满足下单条件的选项，截图保存");
		    return;
		}
		/*WebDriverWait webDriverWait = new WebDriverWait(driver, 5);
		try {
		    	//火车票没有id
			//webDriverWait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("nextStep"), "提交中"));
		      	webDriverWait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//div[@class='btn-box fix']/a[1]"), "提交中"));
		} catch (Exception e) {
			// TODO: handle exception
			saveOrderResult.setMsg("等待5s，2/2页面付款按钮仍然没有改变文案，应该是出了某些问题或者页面有不满足下单条件的选项，截图保存");
			//ScreenShot.getScreenShot(driver);
			return;
		}*/
		logger.debug("进入2/2循环轮询占位浮层");
		
		while (continueLoop) {
			
			continueLoopInt=continueLoopInt+1;
			//总共轮询一分钟左右就结束循环
			if (continueLoopInt>6) {
				saveOrderResult.setSuccess(Boolean.FALSE);
				saveOrderResult.setMsg("轮询下单浮层60s，一直没有出现浮层，结束轮询");
				return;
			}
			
			logger.debug("循环判断是否存在占位浮层");
			// 判读占位浮层是否展示出来
			if(ElementUtil.waitVisibilityOnExpectTime(driver, By.xpath("//div[@class=\"wait_bg hide\"]"), 5)) {
				logger.debug("存在占位浮层");
		

					// 判断是否下单成功且正在轮询状态,查询style熟悉是否打开,如果进入轮询状态，会存在style属性
					strAttribute = driver.findElementByXPath("//div[@class=\"wait_bg hide\"]").getAttribute("style");
					//System.err.println(strAttribute);
					// 如果是block熟悉，则此时还在轮询
					if (strAttribute != null&& strAttribute.trim().equalsIgnoreCase("display: block;")) {
						logger.debug("下单成功");
						saveOrderResult.setSuccess(Boolean.TRUE);
						orderID =this.getAutoMationOrderIDByJS(driver);
						if (orderID==null) {
							saveOrderResult.setMsg("订单下单成功，已弹出浮层，但是没有能通过js获取到订单id");
						}else {
							saveOrderResult.setOrderID(orderID);
						}
						 
						/*WebDriverWait wait = new WebDriverWait(driver, 30);
						// 最长延时30s，以判读占位浮层最后是否不可见，如果浮层不可见了，但是存在占位失败弹出，则给出提示
						wait.until(ExpectedConditions.invisibilityOfElementLocated(By
								.xpath("//div[@class=\"wait_bg hide\"]")));*/
						//三十秒内判断占位浮层是否消失
						ElementUtil.waitInVisibilityOnExpectTime(driver, By.xpath("//div[@class=\"wait_bg hide\"]"), 30);
						if (ElementUtil.isExist(driver,By.xpath("//div[@class=\"dialog-ok\"]"))) {
							//logger.debug("下单成功，但是占位失败1");
							saveOrderResult.setMsg(saveOrderResult.getMsg() + "\r\n 订单占位失败" );
							// 页面元素太复杂，动态生成无法通过特定view找到,可以通过遍历找到，暂时不做处理吧
							// switch content
							// driver.context("NATIVE_APP");
							// driver.context("WEBVIEW_1");
							continueLoop = false;
						}

					}

					// 处理没有捕捉到占位提示框，直接出现占位失败框的情况
					if (strAttribute != null&& strAttribute.trim().equalsIgnoreCase("display: none;")) {
						logger.debug("下单成功，但是占位失败2");
						saveOrderResult.setSuccess(Boolean.TRUE);
						orderID =this.getAutoMationOrderIDByJS(driver);
						if (orderID==null) {
							saveOrderResult.setMsg("没有捕捉到下单占位轮询浮层。订单下单成功，已弹出浮层，但是没有能通过js获取到订单id");
						}else {
							saveOrderResult.setOrderID(orderID);
						}
						
						//三十秒内判断占位浮层是否消失
						ElementUtil.waitInVisibilityOnExpectTime(driver, By.xpath("//div[@class=\"wait_bg hide\"]"), 30);
						
						if (ElementUtil.isExist(driver,
								By.xpath("//div[@class=\"dialog-ok\"]"))) {
							//logger.debug("下单成功，但是占位失败3");
							saveOrderResult.setMsg(saveOrderResult.getMsg() + "\r\n 订单占位失败" );
							// 页面元素太复杂，动态生成无法通过特定view找到,可以通过遍历找到，暂时不做处理吧
							// switch content
							// driver.context("NATIVE_APP");
							// driver.context("WEBVIEW_1");
							//continueLoop = false;
						}

						continueLoop = false;
				}

			}
			
			//处理火车票react浮层
			if (ElementUtil.waitVisibilityOnExpectTime(driver, By.xpath("//div[@class='wait_main']"), 5)) {
				logger.debug("存在火车票下单占位浮层。");
				saveOrderResult.setSuccess(Boolean.TRUE);
				orderID =this.getAutoMationOrderIDByJS(driver);
				if (orderID==null) {
					saveOrderResult.setMsg("订单下单成功，已弹出浮层，但是没有能通过js获取到订单id");
				}else {
					saveOrderResult.setOrderID(orderID);
				}
				
				continueLoop = false;
				saveOrderResult.setMsg("订单下单成功，已弹出浮层，火车票订单一般不会占位成功，跳出循环");
				//还有其他弹窗暂时没处理，后续再优化
				
			}

			logger.debug("判断是否进入下单失败页面");
			if (PageIdentifyCode.PageProductBookFail==PageUtil.getCurrentPageIdentifyCode(this.getAndroidDriver())) {
				logger.debug("下单失败");
				saveOrderResult.setSuccess(Boolean.FALSE);
				saveOrderResult.setMsg("下单失败");
				continueLoop = false;
			}
		


			// 判断是否进入网络单页面
			logger.debug("判断是否进入下单成功页面");
			if (PageIdentifyCode.PageProductBookSuccess==PageUtil.getCurrentPageIdentifyCode(this.getAndroidDriver())) {
				logger.debug("下单成功，进入网络单成功页面 ");
				saveOrderResult.setSuccess(Boolean.TRUE);
				orderID = this.getAutoMationOrderIDByRegular(driver.getCurrentUrl());
				if (orderID==null) {
					saveOrderResult.setMsg("下单成功，进入下单成功页面，通常此情况是出现了网络单情况,同时通过url没有获取到id"  );
				}else {
					saveOrderResult.setOrderID(orderID);
					saveOrderResult.setMsg("下单成功，进入下单成功页面，通常此情况是出现了网络单情况");
				}
				continueLoop = false;
			}
			
			

			// 判断是否进入支付页面，此时占位成功
			logger.debug("判断是否进入支付页面");
			if (PageIdentifyCode.PageProductCasher==PageUtil.getCurrentPageIdentifyCode(this.getAndroidDriver())) {
				logger.debug("下单成功，已进入支付页面");
				saveOrderResult.setSuccess(Boolean.TRUE);
				saveOrderResult.setMsg(saveOrderResult.getMsg() + "\r\n 已进入支付页面");
				continueLoop = false;
			}
			

			
		}
		
		    
	}

	@Override
	public boolean waitLeaveCurrentPage(int timeToWaitInSec) {
	    // TODO Auto-generated method stub
	    return false;
	}
	
	
	/**
	 * 获取2/2页面订单号,通过执行js获取全局订单变量
	 * @param driver 实例
	 * @return 订单id，在订单占位状态和占位失败确认状态时，存在此属性,获取不到订单id时，返回null
	 */
	public  String getAutoMationOrderIDByJS(AppiumDriver<AndroidElement> driver) {
		
		//获取orderid
		Object object = driver.executeScript("return  window.orderId");
		if (object!=null) {
			return object.toString();
			
		}else {
			return null;
		}
		
	}
	
	/**
	 * 获取2/2页面订单号，通过url，正则匹配出订单号
	 * @param urlString 待匹配订单id的url，通过正则法则匹配
	 * @return 返回订单id，如果没匹配到返回null，适用于跳到支付状态或者网络单状态的订单，订单号出现在url中
	 */
	
	public  String getAutoMationOrderIDByRegular(String urlString) {
		
		Pattern pattern = Pattern.compile(".+?orderId=([^&]+)");
		Matcher matcher = pattern.matcher(urlString);
		if(matcher.find()){
			
			return matcher.group(1);
		}else {
			return null;
		}
	}

	@Override
	public boolean waitExistUnCommonUserNameModule(int timeToWaitInSec) {
	    
	    return ElementUtil.waitAttributeToBeOnExpectTime(this.getAndroidDriver(), By.xpath("//div[@class='checkNameDialog']"), "style", "display: block;", timeToWaitInSec);
	}

	@Override
	public void setUserXingOfUnCommonUserNameModule(By locatorInput,String xingString) {
	    
	    find(locatorInput).sendKeys(xingString);
	}
	
	public void setUserXingOfUnCommonUserNameModule(String xingString) {
	    
	    //setUserXingOfUnCommonUserNameModule(By.id("J_lastName"),xingString);
		find(By.id("J_lastName")).click();
		find(By.xpath("//div[@class='w-name-choice']//*[@class='w-except-last']")).click();
		
	}

	@Override
	public void setUserMingOfUnCommonUserNameModule(By locatorInput,String mingString) {
	    find(locatorInput).sendKeys(mingString);
	    
	}
	
	public void setUserMingOfUnCommonUserNameModule(String mingString) {
	    //setUserMingOfUnCommonUserNameModule(By.id("J_firstName"),mingString);	 
		find(By.id("J_firstName")).click();
	}

	@Override
	public void clickCancelBtnOfUnCommonUserNameModule() {
	    
	    find(By.xpath("//p[@class='check_name_cancel']")).click();
	    
	}

	@Override
	public void clickConfirmBtnOfUnCommonUserNameModule() {
	    
	    find(By.xpath("//p[@class='check_name_submit']")).click();
	    
	}

	@Override
	public int typeOfUnCommonUserNameModule() {
	    //获取标题
	    String titleName = find(By.xpath("//p[@class='checkNameBox_title']")).getText().trim();
	    if (titleName.equalsIgnoreCase("生僻字校验")) {
		return 1;
	    } else if (titleName.equalsIgnoreCase("婴儿校验")) {
		return 2;
	    }else {
		return 0;
	    }
	    
	}

	@Override
	public void setUserNameOfContactModule(String userName) {
	    
	    find(By.xpath("//input[@id='realname']")).clear();
	    find(By.xpath("//input[@id='realname']")).sendKeys(userName);
	    
	}

	@Override
	public String getUserNameOfContactModule() {
	    //无法获取到值
	    //return getAndroidDriver().findElement(By.id("realname")).getText();
	    Object objectNameObject  = getAndroidDriver().executeScript("return $('#realname').val()");
	    return objectNameObject.toString();
	}

	@Override
	public void setAreaCodeOfContactModule(String areaCodeID) {
	    //点击选择切换区号按钮
	    getAndroidDriver().findElement(By.id("AreaCode")).click();
	    
	    //由于页面功能有限，不再做单独处理，直接此处处理掉过程
	    PageUtil.waitPageDomLoadReady(getAndroidDriver());
	    
	    //引入zepto
	    PageUtil.ImportZepto(getAndroidDriver());
	    String jqueryString = "$(\"a[data-intlcodermzero='"+ areaCodeID +"']\").trigger('tap')";
	    getAndroidDriver().executeScript(jqueryString);
	    
	    
	}

	@Override
	public String getAreaCodeOfContactModule() {
	    
	   // return find(By.id("AreaCode")).getText();
	    
	    Object object  = getAndroidDriver().executeScript("return $('#AreaCode').val()");
	    return object.toString();
	}

	@Override
	public void setUserEmailOfContactModule(String userEmail) {
	    find(By.id("email")).clear();
	    find(By.id("email")).sendKeys(userEmail);
	    
	}

	@Override
	public String getUserEmailOfContactModule() {
	    //return find(By.id("email")).getText();
	    Object object  = getAndroidDriver().executeScript("return $('#email').val()");
	    return object.toString();
	}

	@Override
	public boolean waitExistLoginFrameOfContactModule(int timeToWaitInSec) {
	     
	    return ElementUtil.waitVisibilityOnExpectTime(getAndroidDriver(), By.xpath("//div[@class='loginIframe']"), timeToWaitInSec);
	}

	@Override
	public void setValidateTextOfContactModule(String validateTextString) {
	    getAndroidDriver().switchTo().frame("loginIframe");
	    find(By.xpath("//input[@class='identify_code']")).sendKeys(validateTextString);
	    getAndroidDriver().switchTo().defaultContent();
	    
	}

	@Override
	public void setPasswordOfContactModule(String randomPassword) {
	    getAndroidDriver().switchTo().frame("loginIframe");
	    find(By.xpath("//input[@name='password']")).sendKeys(randomPassword);
	    getAndroidDriver().switchTo().defaultContent();
	    
	}

	@Override
	public void clickLoginBtnOfContactModule() {
	    getAndroidDriver().switchTo().frame("loginIframe");
	    //处理输入法挡住了登陆按钮功能
	    if(ElementUtil.waitVisibilityOnExpectTime(getAndroidDriver(), By.xpath("//a[@class='btn J_loginBtn']"), 1)){
	    	clickElement(find(By.xpath("//a[@class='btn J_loginBtn']")));
	    }else{
	    	getAndroidDriver().executeScript("$('.btn.J_loginBtn').click()");
	    }
	    	
	    
	    getAndroidDriver().switchTo().defaultContent();
	    
	}

	@Override
	public void setTelOfContactModule(String tel) {
	    find(By.id("tel")).clear();
	    find(By.id("tel")).sendKeys(tel);
	    
	}

	@Override
	public String getTelOfContactModule() {
	     
	   // return find(By.id("tel")).getText();
	    Object object  = getAndroidDriver().executeScript("return $('#tel').val()");
	    return object.toString();
	}
	
	//判断是否存在签注类型选择框，如果有则选择L签
	public boolean isExitVisaDetail(int timeToWaitInSec){
		return ElementUtil.waitVisibilityOnExpectTime(getAndroidDriver(), By.xpath("//div[@class='visa-detail choose-btn']"), timeToWaitInSec);
	}
	
	//计算签注类型选择框数量
	public int getCountOfVisaDetail() {
	    //计算需要处理附加项的个数
	    return getAndroidDriver().findElements(By.xpath("//div[@class='visa-detail choose-btn']")).size();
	}
	
	//选择所有人的签证类型
	public void selectAllVisaDetail(int adultNum,int childNum){
		for(int i = 0;i<adultNum;i++){
			String jsStringAudlt = "$('.tourists .content-adult .visa-detail')["+ i +"].click()";
			getAndroidDriver().executeScript(jsStringAudlt);
			if (ElementUtil.waitVisibilityOnExpectTime(getAndroidDriver(), By.xpath("//div[@class='visa-type-choose-box']"), 1)) {
				//选择默认第一个签注类型
				String jsString="$('.visa-type-items-box').children(\"[data-id='1']\").click()";
				getAndroidDriver().executeScript(jsString);
			}
		}
		
		for(int j = 0;j<childNum;j++){
			String jsStringChild = "$('.tourists .content-child .visa-detail')["+ j +"].click()";
			getAndroidDriver().executeScript(jsStringChild);
			if (ElementUtil.waitVisibilityOnExpectTime(getAndroidDriver(), By.xpath("//div[@class='visa-type-choose-box']"), 1)) {
				//选择默认第一个签注类型
				String jsString="$('.visa-type-items-box').children(\"[data-id='1']\").click()";
				getAndroidDriver().executeScript(jsString);
			}
		}
//		List<AndroidElement> visaList = getAndroidDriver().findElementsByXPath("//*[@class='visa-type choose-type none-selected']");
//		for (AndroidElement androidElement : visaList) {
//			androidElement.click();
//			if (ElementUtil.waitVisibilityOnExpectTime(getAndroidDriver(), By.xpath("//div[@class='visa-type-choose-box']"), 1)) {
//				//选择默认第一个签注类型
//				String jsString="$('.visa-type-items-box').children(\"[data-id='1']\").click()";
//				getAndroidDriver().executeScript(jsString);
//			}
//		}
	}
	
}
