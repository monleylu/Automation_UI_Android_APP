package com.monleylu.olbb.result;

public class ProductBookInformation {
	
	private  String HostUrl ; // 域名
	private  String ProductID ;// 产品id
	private  int AdultNum ; // 预订成人数
	private  int ChildNum ; // 预订儿童数
	private  int ChildFreedNum ; // 免票预订儿童数
	private  String BuyDate; // 预订日期
	private  int productType; //产品类型
	private  String productTypeName; //产品类型名称
	private  int productMode;//产品采购方式，2为跟团火车票地接产品
	private  String  productModeName;//采购方式名称
	//private  boolean checked=false; //是否选中，提供给测试用例关联测试产品使用(此处废弃不用)
	
	public ProductBookInformation(){
		
	}
	
	public ProductBookInformation(String hosturl , String productID ,String buyDate,int adultNum , int childNum ,int childFreeNum,int productType,int productMode){
		HostUrl=hosturl;
		ProductID = productID;
		BuyDate = buyDate;
		AdultNum = adultNum;
		ChildNum = childNum;
		ChildFreedNum = childFreeNum;
		this.productType=productType;
		this.productMode=productMode;
		
	}
	
	
	
	public String getHostUrl() {
		return HostUrl;
	}
	public void setHostUrl(String hostUrl) {
		HostUrl = hostUrl;
	}
	public String getProductID() {
		return ProductID;
	}
	public void setProductID(String productID) {
		ProductID = productID;
	}
	public int getAdultNum() {
		return AdultNum;
	}
	public void setAdultNum(int adultNum) {
		AdultNum = adultNum;
	}
	public int getChildNum() {
		return ChildNum;
	}
	public void setChildNum(int childNum) {
		ChildNum = childNum;
	}
	public int getChildFreedNum() {
		return ChildFreedNum;
	}
	public void setChildFreedNum(int childFreedNum) {
		ChildFreedNum = childFreedNum;
	}
	public String getBuyDate() {
		return BuyDate;
	}
	public void setBuyDate(String buyDate) {
		BuyDate = buyDate;
	}
	public int getProductMode() {
		return productMode;
	}
	public void setProductMode(int productMode) {
		this.productMode = productMode;
		
		//设置售卖方式
		this.getProductModeName();
	}

	public int getProductType() {
		return productType;
	}

	public void setProductType(int productType) {
		this.productType = productType;
		
		//设置产品类型
		this.getProductTypeName();
	}

	public String getProductTypeName() {
	    
	    switch (this.getProductType()) {
	    case 1:
		this.productTypeName= "跟团游";
		break;
	    case 2:
		this.productTypeName="自助游";
		break;
	    case 8:
		this.productTypeName="自驾游";
		break;

	    default:
		this.productTypeName="未知品类";
		break;
	    }
	    return productTypeName;
	}

	public void setProductTypeName(String productTypeName) {
	    
	    this.productTypeName = productTypeName;
	}

	public String getProductModeName() {
	    
	    switch (this.getProductMode()) {
	    case 1:
		this.productModeName="打包";
		break;
	    case 2:
		this.productModeName="火车票+地接";
		break;
	    case 3:
		this.productModeName="汽车票+地接";
		break;
	    case 4:
		this.productModeName="酒店+门票";
		break;
	    case 5:
		this.productModeName="机票+资源";
		break;
	    case 6:
		this.productModeName="火车票+资源";
		break;
	    case 7:
		this.productModeName="机票+地接";	
		break;
	    case 9:
		this.productModeName="资源组合";
		break;

	    default:
		this.productModeName="未知售卖方式";
		break;
	    }
	    return productModeName;
	}

	public void setProductModeName(String productModeName) {
	    this.productModeName = productModeName;
	}
	
	

}
