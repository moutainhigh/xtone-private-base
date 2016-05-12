package com.thirdpay.domain;

public class PayCallbackParaBean {
	private int price;
	private String payChannel;
	private String ip;
	private String payInfo;
	private String releaseChannel;
	private String appKey;
	private String payChannelOrderId;
	private String ownUserId;
	private String ownItemId;
	private String ownOrderId;
	private String cpOrderId;
	private int payStatus;

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

	public String getCpOrderId() {
		return cpOrderId;
	}

	public void setCpOrderId(String cpOrderId) {
		this.cpOrderId = cpOrderId;
	}

	public int getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(int payStatus) {
		this.payStatus = payStatus;
	}

}
