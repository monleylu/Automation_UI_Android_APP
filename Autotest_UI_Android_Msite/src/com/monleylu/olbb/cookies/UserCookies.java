package com.monleylu.olbb.cookies;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;



public class UserCookies {
	
	
    private String fileNameStoreCookie;
    public UserCookies(String fileNameStoreCookie){
	
	this.fileNameStoreCookie=fileNameStoreCookie;
    }
	/**
	 * 获取cookie文件路径
	 * @return 返回cookie文件路径
	 */
		public  String getCookiesPath() {
			
			String userdirString = System.getProperty("user.dir");
			return userdirString + "/initdata/cookies/" + fileNameStoreCookie;
			
		}
		
		/**
		 * 返回键值对cookie值
		 * 
		 * @param strCookiesFilePath  cookies文件路径
		 * @return 返回键值对cookie
		 */

		public ArrayList<UserCookiesObj> arrayListCookies(String strCookiesFilePath){
			
			ArrayList<UserCookiesObj> arrListCookies = new ArrayList<UserCookiesObj>();
			BufferedReader bufferedReader ;
			File file  = new  File(strCookiesFilePath);
		
			try {
				bufferedReader = new BufferedReader(new FileReader(file));
				String strLine =  bufferedReader.readLine();
				
				while(strLine!=null){
					String[] splitStr =strLine.split(":");
					UserCookiesObj cookiesObj = new UserCookiesObj(splitStr[0], splitStr[1]);
					arrListCookies.add(cookiesObj);
					strLine =  bufferedReader.readLine();
					
				}
				bufferedReader.close();
			} catch (FileNotFoundException e) {
				
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return arrListCookies;
			
		}
}
