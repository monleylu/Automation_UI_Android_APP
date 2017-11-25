/**
 * Copyright (c) 2006-2016 . All Rights Reserved.
 */
package com.monleylu.internal.http;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 *
 * @Description: 从奥卡姆剃刀系统获取自动化测试用例数据
 * @author lujian
 * @date 2016年9月9日
 * @version
 *    2016年9月9日  v1.0  create
 *
 */
public class AutoMationHttp {

    /**
     * 通过get方式获取数据
     * @param url 待访问url
     * @return url返回的结果
     */
    public static String getHttpResponseData(String url) {

        String responseData = "";

        CloseableHttpClient httpclient = HttpClients.createDefault();

        HttpGet httpget = new HttpGet(url);

        try {

            CloseableHttpResponse response = httpclient.execute(httpget);
            HttpEntity entity = response.getEntity();
            String responseDataString = EntityUtils.toString(entity);
            responseData = responseDataString;


        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return responseData;
    }

    /**
     * 获取测试用例和测试产品关联关系对象 
     * @param ID 测试用例ID
     */
    public static String getTestCaseRelateProduct(String ID) {
        String urlString = "http://occamrazor.dev.monleylu.org/automation/getTestcaseWithRelateProduct?ID=";
        return AutoMationHttp.getHttpResponseData(urlString + ID);
    }

    /**
     * 获取cookies
     * @param ID 测试cookies的ID
     */
    public static String getTestCooikes(String ID) {
        String urlString = "http://occamrazor.dev.monleylu.org/automation/getAllTestCookies?ID=";
        return AutoMationHttp.getHttpResponseData(urlString + ID);
    }

}
