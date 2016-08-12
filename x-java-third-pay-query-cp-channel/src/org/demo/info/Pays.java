package org.demo.info;
/**
 * 支付成功信息
 * @author dev.jiabin
 *
 */
public class Pays {
	private String id;
	private int price;                 //价格
	private String payChannel;		   //支付
	private String ip;				   //来源IP
	private String payInfo;			   //从支付通道获取的原始内容
	private String releaseChannel;     //发行通道ID
	private String appKey;             //CP方ID
	private String payChannelOrderId;  //支付通道的订单号
	private String ownUserId;          //付费用户ID
	private String ownItemId;          //购买道具ID
	private String ownOrderId;         //原始订单号ID
	private String cpOrderId;          //cp订单号
	private String testStatus;         //状态
	
	
	public String getCpOrderId() {
		return cpOrderId;
	}
	public void setCpOrderId(String cpOrderId) {
		this.cpOrderId = cpOrderId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getPayChannel() {
		return payChannel;
	}
	public void setPayChannel(String payChannel) {
		this.payChannel = payChannel;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getPayInfo() {
		return payInfo;
	}
	public void setPayInfo(String payInfo) {
		this.payInfo = payInfo;
	}
	public String getReleaseChannel() {
		return releaseChannel;
	}
	public void setReleaseChannel(String releaseChannel) {
		this.releaseChannel = releaseChannel;
	}
	public String getAppKey() {
		return appKey;
	}
	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}
	public String getPayChannelOrderId() {
		return payChannelOrderId;
	}
	public void setPayChannelOrderId(String payChannelOrderId) {
		this.payChannelOrderId = payChannelOrderId;
	}
	public String getOwnUserId() {
		return ownUserId;
	}
	public void setOwnUserId(String ownUserId) {
		this.ownUserId = ownUserId;
	}
	public String getOwnItemId() {
		return ownItemId;
	}
	public void setOwnItemId(String ownItemId) {
		this.ownItemId = ownItemId;
	}
	public String getOwnOrderId() {
		return ownOrderId;
	}
	public void setOwnOrderId(String ownOrderId) {
		this.ownOrderId = ownOrderId;
	}
	public String getTestStatus() {
		return testStatus;
	}
	public void setTestStatus(String testStatus) {
		this.testStatus = testStatus;
	}
	
	
}
