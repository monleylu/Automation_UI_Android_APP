package com.monleylu.olbb.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReglarExp {
	
	/**
	 * 
	 * @param textToPattern 待匹配字符串
	 * @return 返回已经选择的游客数
	 */
	public static String getSelectTouristNum(String textToPattern) {
		
		Pattern pattern = Pattern.compile("已选择([^/]+)");
		Matcher matcher = pattern.matcher(textToPattern);
		if (matcher.find()) {
			return matcher.group(1);
			
		}else {
			return "0";
		}
		
	}

}
