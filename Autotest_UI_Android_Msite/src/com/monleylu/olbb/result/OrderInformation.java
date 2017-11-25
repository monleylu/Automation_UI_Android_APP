package com.monleylu.olbb.result;

public class OrderInformation {
	
	private  String HostUrl ; // 域名
	private  String OrderID ;// 订单ID
	private  int OrderType; //订单类型
	
	public OrderInformation(){
		
	}
	
	public OrderInformation(String hosturl,String orderID,int orderType){
		this.HostUrl = hosturl;
		this.OrderID = orderID;
		this.OrderType = orderType;
	}
	
	public String getHostUrl(){
		return HostUrl;
	}
	
	public void setHostUrl(String hosturl){
		this.HostUrl = hosturl;
	}
	
	public String getOrderID(){
		return OrderID;
	}
	
	public void setOrderID(String orderID){
		this.OrderID = orderID;
	}
	
	public int getOrderType(){
		return OrderType;
	}
	
	public void setOrderType(int orderType){
		this.OrderType = orderType;
	}

}
