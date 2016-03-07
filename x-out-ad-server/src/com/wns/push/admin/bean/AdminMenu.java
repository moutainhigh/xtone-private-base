package com.wns.push.admin.bean;

import java.io.Serializable;
import java.util.List;

public class AdminMenu implements Serializable {
	public List<AdminMenu> getChildren() {
		return children;
	}

	public void setChildren(List<AdminMenu> children) {
		this.children = children;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 5463407943802255296L;
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	private Integer id;
	private String name;
	private String url;
	private Integer pid;
	private Integer status;
	private List<AdminMenu>children;
}
