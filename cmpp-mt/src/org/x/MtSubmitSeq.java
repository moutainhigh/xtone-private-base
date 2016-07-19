package org.x;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.common.util.ConnectionService;

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
		Connection con = null;
		PreparedStatement ps = null;
		try {
			strSql = "update sms_mtlog set submit_seq = 0 ,submit_msgid='" + this.submitMsgID + "',submit_result="
					+ this.submitResult + " where submit_seq = " + this.submitSeq + " and ismgid ='" + this.ismgID
					+ "' order by id desc limit 1";
			logger.debug(strSql);
			con = ConnectionService.getInstance().getConnectionForLocal();
			ps = con.prepareStatement(strSql);
			ps.executeUpdate();
		} catch (Exception e) {
			logger.error(strSql, e);
		} finally {
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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