package com.demo.pageinterface;

/**
 * Created by monley_Lu on 2017/6/3.
 */
public interface PageLoadModule {
    /**
     * wait page load ready
     * @param timeToWaitInSec wait time
     * @return if load ready in special time ,return true ,or return false
     */
    boolean waitPageLoadReady(int timeToWaitInSec);

    /**
     * wait page leave current page
     * @param timeToWaitInSec  time to wait leave current view
     * @return if leave return true,otherwise reurn false
     */
    boolean leaveCurrentPage(int timeToWaitInSec);
}
