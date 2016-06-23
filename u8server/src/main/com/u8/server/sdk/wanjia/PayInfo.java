package com.u8.server.sdk.wanjia;

/**
 * Created by ant on 2016/6/22.
 */
public class PayInfo {
	private int price;	         //价格（以分为单位）
	private String cpOrderId;	 //Cp订单号
	private String productName;	 //产品名称
	private String productDesc;	 //产品描述
	private String webOrderid;   //SDK生成的订单号
	private int buyNum;		     //购买数
	private int coinNum; 	     //金币数
	private int ratio;		     //兑换比例
	private String roleId;  	 //角色ID
	private String roleName;	 //角色名称
	private String roleLevel;	 //角色等级
	private String vip;			 //vip
	private String extension;	 //扩展
	private String serverId; 	 //服务区号
	private String serverName;	 //服务区名称
	
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getCpOrderId() {
		return cpOrderId;
	}
	public void setCpOrderId(String cpOrderId) {
		this.cpOrderId = cpOrderId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductDesc() {
		return productDesc;
	}
	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}
	public int getBuyNum() {
		return buyNum;
	}
	public void setBuyNum(int buyNum) {
		this.buyNum = buyNum;
	}
	public int getCoinNum() {
		return coinNum;
	}
	public void setCoinNum(int coinNum) {
		this.coinNum = coinNum;
	}
	public int getRatio() {
		return ratio;
	}
	public void setRatio(int ratio) {
		this.ratio = ratio;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getRoleLevel() {
		return roleLevel;
	}
	public void setRoleLevel(String roleLevel) {
		this.roleLevel = roleLevel;
	}
	public String getVip() {
		return vip;
	}
	public void setVip(String vip) {
		this.vip = vip;
	}
	public String getExtension() {
		return extension;
	}
	public void setExtension(String extension) {
		this.extension = extension;
	}
	public String getServerId() {
		return serverId;
	}
	public void setServerId(String serverId) {
		this.serverId = serverId;
	}
	public String getServerName() {
		return serverName;
	}
	public void setServerName(String serverName) {
		this.serverName = serverName;
	}
	public String getWebOrderid() {
		return webOrderid;
	}
	public void setWebOrderid(String webOrderid) {
		this.webOrderid = webOrderid;
	}
}
