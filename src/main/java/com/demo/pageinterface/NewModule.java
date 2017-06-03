package com.demo.pageinterface;

/**
 * 定义首页每个新闻模块，模块里包含很多内容，做个例子，获取标题
 * Created by monley_Lu on 2017/6/3.
 */
public interface NewModule {
    /**
     * 返回指定新闻的标题
     * @param index 指定第几条新闻
     * @return 新闻标题
     */
    String getTitle(int index);

    /**
     * 点击阅读指定的新闻，打开详情页面
     * @param index
     */
    void readNew(int index);
}
