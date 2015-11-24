package com.xtone.iap;

import com.google.gson.annotations.SerializedName;

public class ReceiveFromMsg {
	@SerializedName("signature")
	private String signature;
	@SerializedName("purchase-info")
	private String purchaseInfo;
	@SerializedName("environment")
	private String environment;
	@SerializedName("pod")
	private String pod;
	@SerializedName("signing-status")
	private String signingStatus;
	private String ip;
	
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public String getPurchaseInfo() {
		return purchaseInfo;
	}
	public void setPurchaseInfo(String purchaseInfo) {
		this.purchaseInfo = purchaseInfo;
	}
	public String getEnvironment() {
		return environment;
	}
	public void setEnvironment(String environment) {
		this.environment = environment;
	}
	public String getPod() {
		return pod;
	}
	public void setPod(String pod) {
		this.pod = pod;
	}
	public String getSigningStatus() {
		return signingStatus;
	}
	public void setSigningStatus(String signingStatus) {
		this.signingStatus = signingStatus;
	}
	
	
}
