/**
*Copyright (c) 2006-2016 . All Rights Reserved.
*/
package com.monleylu.olbb.data;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.monleylu.olbb.http.AutoMationHttp;
import com.monleylu.olbb.result.ProductBookInformation;
import com.monleylu.olbb.testcase.TestUnit;
import com.monleylu.olbb.testcase.TestcaseWithRelateProductBean;
import com.monleylu.olbb.xml.XmlDataSrc;

/**
 * 
* @Description: 通过奥卡姆剃刀获取cookie、测试用例等内容相关操作
* @author lujian
* @date 2016年9月14日
* @version 
*    2016年9月14日  v1.0  create 
*
 */
public class DataUtil {
    
    /**
     * 从奥卡姆剃刀系统获取cookie并写入文件
     * @param ID cookies的ID
     * @param file cookies写入的文件
     */
    public static void  getCookiesWriteToFile(String ID ,File file) {
	Gson gson = new Gson();
	
	
	String dataToWrite ="";
	String nameString="";
	String valueString="";
	
	try {
	    	String responseData = AutoMationHttp.getTestCooikes(ID);
		JsonObject jsonObject = gson.fromJson(responseData, new TypeToken<JsonObject>(){}.getType());
		JsonArray jsonArray = jsonObject.getAsJsonArray("rows").get(0).getAsJsonObject().getAsJsonArray("relateParameter");
		for (JsonElement jsonElement : jsonArray) {
		     nameString=jsonElement.getAsJsonObject().get("Name").getAsString();
		     valueString=jsonElement.getAsJsonObject().get("Value").getAsString();
		     dataToWrite=dataToWrite.concat(nameString).concat(":").concat(valueString).concat(System.getProperty("line.separator"));
		}
	} catch (Exception e) {
	    // TODO: handle exception
	    System.err.println("get cookies failed");
	    e.printStackTrace();
	}
	
	try {
	    FileUtils.writeStringToFile(file, dataToWrite);
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	
    }
    
    
    /**
     * 从奥卡姆剃刀系统获取测试产品并写入文件
     * @param srcData 待获取测试用例ID
     * @param file  数据写入文件
     */
    public static void  getTestProductWriteToFile(String srcData,File file) {

	Gson gson = new Gson();
	ArrayList<TestcaseWithRelateProductBean>  arrTestcaseWithRelateProducts = new ArrayList<>();
	String responseDataString="";
	String stringToWrite="";
	try {
	    
        	String[] arrsrcData = srcData.split(",");
        	for (int i = 0; i < arrsrcData.length; i++) {
        	    if (!arrsrcData[i].trim().isEmpty()) {
        		TestcaseWithRelateProductBean tmpTestcaseWithRelateProductBean = new TestcaseWithRelateProductBean();
        		responseDataString = AutoMationHttp.getTestCaseRelateProduct(arrsrcData[i].trim());
        		tmpTestcaseWithRelateProductBean = gson.fromJson(responseDataString, new TypeToken<TestcaseWithRelateProductBean>(){}.getType());
        		arrTestcaseWithRelateProducts.add(tmpTestcaseWithRelateProductBean);
        	    }
        	}
        	
        	
        	 for (TestcaseWithRelateProductBean aa : arrTestcaseWithRelateProducts) {
        	     TestUnit tmpTestUnit = new TestUnit();
        	     tmpTestUnit.setTestCase(aa.getTestCase());
        	     for (ProductBookInformation  pd : aa.getTestProducts()) {
        		 tmpTestUnit.setProductBookInformation(pd);
        		 stringToWrite=stringToWrite.concat(gson.toJson(tmpTestUnit)).concat(System.getProperty("line.separator"));
        	    }
	     
        	 }
	 
	} catch (Exception e) {
	    // TODO: handle exception
	    e.printStackTrace();
	}
	 
	try {
	    FileUtils.writeStringToFile(file, stringToWrite);
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} 
    }
    
    /**
     * 从存储测试用例和产品的文件中读取数据
     * @param file 存储测试产品的文件
     * @return 返回测试用例和测试产品对象数组list
     */
    public static ArrayList<TestUnit[]> getTestUnitsFromFile(File file) {
	
	Gson gson = new Gson();
	ArrayList<TestUnit[]> arrayList = new ArrayList<>();
	   
	    try {
		List<String> allTestDataList= FileUtils.readLines(file);
		//System.err.println(allTestDataList.size());
		for (String string : allTestDataList) {
		    TestUnit testUnit = gson.fromJson(string, new TypeToken<TestUnit>(){}.getType());
		    TestUnit[] arrTestUnits=new TestUnit[]{testUnit};
		    arrayList.add(arrTestUnits);
		}
	    } catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	    return arrayList;
    }
    
    /**
     * 返回测试文件配置的测试数据
     * @param file 配置文件名
     * @return 
     */
    public static HashMap<String, XmlDataSrc> getDataSrcConfigure(File file) {
	
	HashMap<String, XmlDataSrc> hashMap = new HashMap<>();

	    SAXReader reader = new SAXReader();
	    try {
		Document   document = reader.read(file);
		//根节点
		Element element = document.getRootElement();
		@SuppressWarnings("unchecked")
		//datameta节点
		List<Element> list = element.elements();
		for (Element element2 : list) {
		    XmlDataSrc dataSrc = new XmlDataSrc();
		    dataSrc.setId(element2.attributeValue("id"));
		    @SuppressWarnings("unchecked")
		    //datameta子节点数据源配置
		    List<Element> element3 = element2.elements();
		    
		    for (Element element4 : element3) {
			if (element4.getName().equalsIgnoreCase("cookie")) {
			    dataSrc.setCookieFlag(element4.elementTextTrim("cookieFlag"));
			    dataSrc.setCookieData(element4.elementTextTrim("cookieData"));
			}else if (element4.getName().equalsIgnoreCase("src")) {
			    dataSrc.setSrcFlag(element4.elementTextTrim("srcFlag"));
			    dataSrc.setSrcData(element4.elementTextTrim("srcData"));
			}
		    }
		
		    //保存获取数据
		    hashMap.put(dataSrc.getId(), dataSrc); 
		}
		
		
	    } catch (DocumentException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	    
	    return hashMap;
	}
    
}








