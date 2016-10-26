package org.demo.info;


public class App {
 private String appKey;
 private String appName;
 private int type;        //状态码，用于判断信息是增加界面传送的还是修改界面传送的
 private String notify_url;
 private String encrypt;
 private String encrypt_key;
 private long cpid;
	public String getAppkey() {
		return appKey;
	}
	public void setAppkey(String appKey) {
		this.appKey = appKey;
	}
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public String getNotify_url() {
		return notify_url;
	}
	public void setNotify_url(String notify_url) {
		this.notify_url = notify_url;
	}
	public String getEncrypt() {
		return encrypt;
	}
	public void setEncrypt(String encrypt) {
		this.encrypt = encrypt;
	}
	public String getEncrypt_key() {
		return encrypt_key;
	}
	public void setEncrypt_key(String encrypt_key) {
		this.encrypt_key = encrypt_key;
	}
	public long getCpid() {
		return cpid;
	}
	public void setCpid(long cpid) {
		this.cpid = cpid;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	
}
