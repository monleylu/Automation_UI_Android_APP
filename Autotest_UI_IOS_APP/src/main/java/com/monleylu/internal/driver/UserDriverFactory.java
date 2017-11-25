/**
 * Copyright (c) 2006-2016 . All Rights Reserved.
 */
package com.monleylu.internal.driver;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 *
 * @Description: 驱动实例，所有驱动存储节点
 * @author lujian
 * @date 2016年12月3日
 * @version
 *    2016年12月3日  v1.0  create
 *
 */
public class UserDriverFactory {

    /** 配置文件存储队列**/
    List<String> configureList;

    /** 阻塞队列**/
    private BlockingQueue<IOSDriver<IOSElement>> bq;

    /** 当前队列有效驱动个数**/
    //private int size=0;
    public UserDriverFactory() {

    }

    /**
     * 构造函数
     * @param driverConfigureFileName 驱动配置文件名称，默认存在在initdata/androiddriver目录
     */
    public UserDriverFactory(String driverConfigureFileName) {

        bq = new ArrayBlockingQueue<IOSDriver<IOSElement>>(100);

        Gson gson = new Gson();

        //读取配置文件
        File file = new File(System.getProperty("user.dir") + "/initdata/iosdriver/" + driverConfigureFileName);
        try {
            configureList = FileUtils.readLines(file);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        //将配置文件转换成json对象
        //存储配置文件中的所有驱动熟悉，每行代表一个驱动
        ArrayList<DriverCap> driverCaps = new ArrayList<DriverCap>();
        for (int i = 0; i < configureList.size(); i++) {
            //单个驱动属性存储对象，由多个属性键值对组成
            DriverCap driverCap = new DriverCap();
            //每个单独属性键值对
            ArrayList<BaseProperty> properties = new ArrayList<BaseProperty>();
            JsonObject jsonObject = gson.fromJson(configureList.get(i), JsonObject.class);
            Set<Entry<String, JsonElement>> set = jsonObject.entrySet();
            for (Entry<String, JsonElement> entry : set) {
                BaseProperty baseProperty = new BaseProperty(entry.getKey(), entry.getValue().getAsString());
                properties.add(baseProperty);
            }
            driverCap.setProperties(properties);
            driverCaps.add(driverCap);
        }

        //注册驱动
        for (int i = 0; i < driverCaps.size(); i++) {
            /** default appium ip **/
            String ip = "127.0.0.1";
            /** default appium port **/
            String port = "4723";

            String tmpNameString = "";

            //参数文档位置http://appium.io/slate/en/master/#running-on-windows.md
            DesiredCapabilities capabilities = new DesiredCapabilities();
            for (int j = 0; j < driverCaps.get(i).getProperties().size(); j++) {
                tmpNameString = driverCaps.get(i).getProperties().get(j).getName();

                if (tmpNameString.equalsIgnoreCase("id")) {
                    continue;
                }

                if (tmpNameString.equalsIgnoreCase("ip")) {
                    ip = driverCaps.get(i).getProperties().get(j).getValue();
                    continue;
                }
                if (tmpNameString.equalsIgnoreCase("port")) {
                    port = driverCaps.get(i).getProperties().get(j).getValue();
                    continue;
                }
                capabilities.setCapability(tmpNameString, driverCaps.get(i).getProperties().get(j).getValue());
            }

            try {

                IOSDriver<IOSElement> driver = new IOSDriver<IOSElement>(new URL("http://" + ip + ":" + port + "/wd/hub"), capabilities);
                this.setDriver(driver);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }

    }

    /**
     * 从队列取驱动并将取到的驱动从队列移除，如果队列为空则等待
     * @return 驱动，如果异常返回null
     */
    public IOSDriver<IOSElement> getDriver() {
        try {
            return this.bq.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        } //blocking

    }

    /**
     * 从队列取驱动并将取到的驱动从队列移除，如果队列为空则返回null
     * @return 队列不为空返回驱动，否则返回null
     */
    public IOSDriver<IOSElement> getDriver_poll() {
        return this.bq.poll();
    }

    /**
     * 将驱动插入队列，如果队列满，则会等待
     * @param driver
     */
    public void setDriver(IOSDriver<IOSElement> driver) {
        try {
            this.bq.put(driver);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * 返回当前队列有效驱动个数
     * @return
     */
    public int getSize() {
        return this.bq.size();
    }
/*
    public void setSize(int size) {
	this.size = size;
    }*/

}
