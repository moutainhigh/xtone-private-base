package org.x;

import org.apache.log4j.Logger;

import com.xiangtone.util.DBForLocal;

/**
 * Copyright 2003 Xiamen Xiangtone Co. Ltd. All right reserved.
 */
public class MtSubmitSeq {
	private int submitSeq;
	private String submitMsgID;
	private int submitResult;
	private String ismgID;

	public MtSubmitSeq() {
	}

	public int getSubmitSeq() {
		return submitSeq;
	}

	public void setSubmitSeq(int submitSeq) {
		this.submitSeq = submitSeq;
	}

	public String getSubmitMsgID() {
		return submitMsgID;
	}

	public void setSubmitMsgID(String submitMsgID) {
		this.submitMsgID = submitMsgID;
	}

	public int getSubmitResult() {
		return submitResult;
	}

	public void setSubmitResult(int submitResult) {
		this.submitResult = submitResult;
	}

	public String getIsmgID() {
		return ismgID;
	}

	public void setIsmgID(String ismgID) {
		this.ismgID = ismgID;
	}

//	public void setMTSubmitSeq(int submitSeq) {
//		this.submitSeq = submitSeq;
//	}
//
//	public void setMTSubmitMsgID(String submitMsgID) {
//		this.submitMsgID = submitMsgID;
//	}
//
//	public void setMTSubmitResult(int submitResult) {
//		this.submitResult = submitResult;
//	}
//
//	public void setMTIsmgID(String ismgID) {
//		this.ismgID = ismgID;
//	}
	
}