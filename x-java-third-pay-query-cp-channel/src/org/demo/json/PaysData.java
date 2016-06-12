package org.demo.json;

import java.util.List;

import org.demo.info.Pays;

public class PaysData {
	private String status;
	private List<Pays> data;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<Pays> getData() {
		return data;
	}
	public void setData(List<Pays> data) {
		this.data = data;
	}
	
}
