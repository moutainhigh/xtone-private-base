package org.x;

import org.apache.log4j.Logger;

import com.xiangtone.sql.Mysqldb;

/**
 * Copyright 2003 Xiamen Xiangtone Co. Ltd. All right reserved.
 */
public class MtSubmitSeq {
	public int submitSeq;
	public String submitMsgID;
	public int submitResult;
	public String ismgID;
	private Mysqldb db;
	private static Logger logger = Logger.getLogger(MtSubmitSeq.class);

	public MtSubmitSeq() {
		db = new Mysqldb();
	}

	public void updateSubmitSeq() {
		String strSql = null;
		try {
			strSql = "update sms_mtlog set submit_seq = 0 ,submit_msgid='" + this.submitMsgID + "',submit_result="
					+ this.submitResult + " where submit_seq = " + this.submitSeq + " and ismgid ='" + this.ismgID
					+ "' order by id desc limit 1";
			logger.debug(strSql);
			db.execUpdate(strSql);
			db.close();
		} catch (Exception e) {
			logger.error(strSql, e);
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