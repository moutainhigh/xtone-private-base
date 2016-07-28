package org.x;

import org.apache.log4j.Logger;

import com.xiangtone.util.DBForLocal;

/**
 * Copyright 2003 Xiamen Xiangtone Co. Ltd. All right reserved.
 */
public class MtSubmitSeq {
	public int submitSeq;
	public String submitMsgID;
	public int submitResult;
	public String ismgID;
	private static Logger logger = Logger.getLogger(MtSubmitSeq.class);

	public MtSubmitSeq() {
	}

	public void updateSubmitSeq() {
		String strSql = null;
		DBForLocal db=new DBForLocal();
		try {
			strSql = "update sms_mtlog set submit_seq = 0 ,submit_msgid='" + this.submitMsgID + "',submit_result="
					+ this.submitResult + " where submit_seq = " + this.submitSeq + " and ismgid ='" + this.ismgID
					+ "' order by id desc limit 1";
			logger.debug(strSql);
			db.executeUpdate(strSql);
		} catch (Exception e) {
			logger.error(strSql, e);
		} finally {
			db.close();
		}

	}

	public void setMTSubmitSeq(int submitSeq) {
		this.submitSeq = submitSeq;
	}

	public void setMTSubmitMsgID(String submitMsgID) {
		this.submitMsgID = submitMsgID;
	}

	public void setMTSubmitResult(int submitResult) {
		this.submitResult = submitResult;
	}

	public void setMTIsmgID(String ismgID) {
		this.ismgID = ismgID;
	}
}