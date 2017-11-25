package com.monleylu.olbb.internal.tours;

public interface CommonActionModule {
     	
    	
        /**
         * 点击下一步按钮继续后续业务流程
         * @return 点击完毕返回true
         */
	public boolean clickNextStepBtn();

	
	/**
	 * 等待页面加载完毕
	 * @param timeToWaitInSec 等待时间
	 * @return 加载完毕返回true，否则返回false
	 */
	boolean waitPageLoadReady(int timeToWaitInSec);
	
	
	/**
	 * 点击下一步按钮，进入下一步页面前，加载内容的loading图标消失
	 * @param timeToWaitInSec 等待时间
	 * @return 指定时间内加载浮层不出现返回true，如果浮层一直出现，返回false
	 */
	 boolean waitLeaveCurrentPage(int timeToWaitInSec);
	 
	
}
