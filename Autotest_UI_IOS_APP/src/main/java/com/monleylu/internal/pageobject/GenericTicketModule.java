package com.monleylu.internal.pageobject;

/**
 * 门票模块
 * Created by monley_Lu on 2017/2/24.
 */
public interface GenericTicketModule {

    /**
     * 判断是否存在门票模块
     *
     * @param timeToWaitInSec 等待时间
     * @return 存在返回true，不存在返回false
     */
    boolean isExistTicketModule(int timeToWaitInSec);

    /**
     * 默认展示的门票数量
     *
     * @return 返回默认展示的门票数量，不存在门票模块时，返回0
     */
    int countOfDefaultShowTicket();


    /**
     * 点击索引指定的门票的使用日期按钮，索引从1开始，1代表第一个门票，以此类推
     *
     * @return 点击完毕返回true
     */
    boolean clickUseDateBtn(int index);


    /**
     * 选择索引指定的日期，1代表选择第一个有效日期，依次类推，0代表随机选择一个有效日期
     *
     * @param index 日期索引顺序
     * @return 返回选择的门票使用日期
     */
    String selectTicketUseDate(int index);

    /**
     * 门票可以选择使用的日期总数
     *
     * @return 门票可以选择使用的日期总数
     */
    int countOfTicketUseDate();

}
