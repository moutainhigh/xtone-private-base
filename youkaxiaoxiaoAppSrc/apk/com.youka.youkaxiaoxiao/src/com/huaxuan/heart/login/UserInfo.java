package com.huaxuan.heart.login;

public class UserInfo {
	
	private int id;
	private String uid;
	private String email;
	private String uname;
	private String password;
	
	public UserInfo() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	public UserInfo(String uid, String email, String uname, String password) {
		super();
		this.uid = uid;
		this.email = email;
		this.uname = uname;
		this.password = password;
	}




	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	

}
