package org.demo.json;

import org.demo.info.User;

public class LoginRsp {
	private String status;
	private User data;	
	public User getData() {
		return data;
	}
	public void setData(User data) {
		this.data = data;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
