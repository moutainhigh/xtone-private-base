package org.demo.info;

public class Daily {
	
	private String id;
	private String appKey;
	private String channel;
	private float price;
	private int payUsers;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAppKey() {
		return appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getPayUsers() {
		return payUsers;
	}

	public void setPayUsers(int payUsers) {
		this.payUsers = payUsers;
	}

}
