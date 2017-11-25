package com.monleylu.internal.pageobject;

/**
 * 定义页面编码，每个页面对应一个数字
 * Created by monley_Lu on 2017/2/24.
 */
public interface PageCode {

    /**
     * 未定义
     **/
    final int PageProductUnDefine = 0;

    /**
     * 详情页
     **/
    final int PageProductDetail = 1;

    /**
     * 价格日历页
     **/
    final int PageProductCalendar = 2;

    /**
     * 重单页
     **/
    final int PageProductRepeatOrder = 3;

    /**
     * 1/2页面
     **/
    final int PageProductStepOne = 4;

    /**
     * 2/2页面
     **/
    final int PageProductStepTwo = 5;

    /**
     * 选择出游人页
     **/
    final int PageProductTourist = 6;

    /**
     * 预订成功页
     **/
    final int PageProductBookSuccess = 7;

    /**
     * 预订失败页
     **/
    final int PageProductBookFail = 8;

    /**
     * 收银台
     **/
    final int PageProductCasher = 9;

    /**
     * 签约页面
     **/
    final int PageProductSign = 10;

    /**
     * 通过产品ID搜索不到产品时展示的页面
     **/
    final int PageProductSearchEmpty = 11;

    /**
     * APP首屏
     **/
    final int PageMainFragment = 12;

    /**
     * 产品搜索页
     **/
    final int PageSearchProduct = 13;

    /**
     * 会员 全部订单 页面
     **/
    final int PageAllOrders = 14;
}
