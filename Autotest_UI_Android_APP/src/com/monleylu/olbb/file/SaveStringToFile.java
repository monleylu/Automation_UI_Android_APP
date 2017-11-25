package com.monleylu.olbb.file;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import com.google.gson.Gson;

public class SaveStringToFile {

	/**
	 * 保存json对象到文件中去
	 * @param object 待保存json对象
	 * @param fileName 保存文件名称
	 * @return 保存成功返回true，否则返回false
	 */
	public static boolean SaveGsonObjToFile(Object object,String fileName) {
		Gson gson = new Gson();
		
		
		//保存订单信息
		File file = new File(System.getProperty("user.dir") + "/results/"  + fileName);
		try {
			String resultGsonString= gson.toJson(object);
			FileUtils.writeStringToFile(file, resultGsonString+ System.getProperty("line.separator"), true);
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		
	}
}
