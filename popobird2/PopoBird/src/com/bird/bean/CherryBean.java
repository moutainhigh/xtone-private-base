package com.bird.bean;

public class CherryBean {
	
	private String uid;
	private int cherryNmun;
	
	public CherryBean() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	public CherryBean(String uid, int cherryNmun) {
		super();
		this.uid = uid;
		this.cherryNmun = cherryNmun;
	}



	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public int getCherryNmun() {
		return cherryNmun;
	}
	public void setCherryNmun(int cherryNmun) {
		this.cherryNmun = cherryNmun;
	}
	
	

}
