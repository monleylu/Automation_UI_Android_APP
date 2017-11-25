package com.monleylu.internal.pageobject;

/**
 * 访问产品接口
 * Created by monley_Lu on 2017/2/24.
 */
public interface GenericVisitProduct {

    /**
     * 定义各种操作来打开产品详情页
     *
     * @param productID 产品id
     * @return 执行完毕返回true
     */
    boolean visitProduct(String productID);

}
