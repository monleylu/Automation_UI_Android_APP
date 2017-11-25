package com.monleylu.internal.pageobject;

/**
 * Created by monley_Lu on 2017/2/24.
 */
public interface CommonActionModule {


    /**
     * 点击下一步按钮继续后续业务流程
     *
     * @return 点击完毕返回true
     */
    public boolean clickNextPageBtn();


    /**
     * 等待页面加载完毕
     *
     * @param timeToWaitInSec 等待时间
     * @return 加载完毕返回true，否则返回false
     */
    boolean waitPageLoadReady(int timeToWaitInSec);


    /**
     * 等待离开当前页面，当点击下一步按钮后，不知道后面会跳到哪个页面，所以就以是否离开了当前页面作为按钮是否生效基准
     *
     * @param timeToWaitInSec 等待时间
     * @return 离开了当前页面返回true，否则返回false
     */
    boolean waitLeaveCurrentPage(int timeToWaitInSec);


}
