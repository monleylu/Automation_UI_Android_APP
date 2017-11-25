/**
 * 配置cookie的结构
 */
package com.monleylu.olbb.cookies;

public class UserCookiesObj {
	
	private String Name;
	private String Value;
	
	public  UserCookiesObj() {
		
	}
	
	public UserCookiesObj(String nameString,String valueString){
		this.Name=nameString;
		this.Value=valueString;
	}
	
	public String getNameString() {
		return Name;
	}
	public void setNameString(String nameString) {
		this.Name = nameString;
	}
	public String getValueString() {
		return Value;
	}
	public void setValueString(String valueString) {
		this.Value = valueString;
	}
	
	

}
