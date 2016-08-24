package org.demo.json;

import org.demo.info.Code;

public class CodeRsp {
	private String status;
	private Code data;

	
	public String getStatus() {
		return status;          
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Code getData() {
		return data;
	}

	public void setData(Code data) {
		this.data = data;
	}

}
