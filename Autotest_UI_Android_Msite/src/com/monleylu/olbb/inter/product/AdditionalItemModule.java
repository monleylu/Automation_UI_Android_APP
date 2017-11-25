/**
*Copyright (c) 2006-2016 . All Rights Reserved.
*/
package com.monleylu.olbb.inter.product;

/**
 * 
* @Description: 处理附加项目模块
* @author lujian
* @date 2016年9月27日
* @version 
*    2016年9月27日  v1.0  create 
*
 */
public interface AdditionalItemModule {
    
    /**
     * 判断是否存在附加项目
     * PS：
     * 自助类，详情页展示的附加项目都是一项起选的附加项目，门票和附加项目处理逻辑一样，实现上也在一起，所以把门票也当成一种附加项目
     * @param timeToWaitInSec 等待时间
     * @return 存在返回true，否则返回false
     */
    boolean waitExistAdditionalItemModule(int timeToWaitInSec);
    
    /**
     * 获取附加项目个数
     * @return 附加项目个数
     */
    int getCountOfAdditionalItemModule();
    
    /**
     * 获取指定索引的附加项目名称，如是门票还是卡券
     * @param areaIndex 从0开始
     * @return 返回标题
     */
    String getAdditionalItemTitleName(int areaIndex);
    

    /**
     * 选择areaIndex指定模块下面dataIndex指定的附加项目使用日期，使用日期目前默认选择第一个有效日期
     * @param areaIndex 模块索引，从0开始
     * @param dataIndex 模块里面的具体售卖附加项目索引，从零开始
     * @param 执行成功返回true，否则返回false
     */
    boolean selectUseTimeOfAdditionalItem(int areaIndex,int dataIndex);
    
    /**
     * 选择areaIndex指定模块下面dataIndex指定的附加项目购买份数
     * @param areaIndex 模块索引，从0开始
     * @param dataIndex 模块里面的具体售卖附加项目索引，从零开始
     */
    //暂不开发，使用默认份数
    //void selectBookNumOfAdditionalItem(int areaIndex,int dataIndex);

}
