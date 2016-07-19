package org.x;
/**
*Copyright 2003 Xiamen Xiangtone Co. Ltd.
*All right reserved.
*/

import java.io.*;
import java.sql.*;

import org.apache.log4j.Logger;

import com.xiangtone.sql.Mysqldb;

public class SMSCost {
	private static Logger logger = Logger.getLogger(SMSCost.class);
	public String serverID = "8003";
	public String serverCodeIOD = "BZ";
	public String serverCodePUSH = "-BZ";
	public String serverName = "BZ";
	public String infoFee = "0";
	public String feeType = "01";
	public int mediaType = 1;
	public String spCode;
	public String memo;

	Mysqldb db;
	ResultSet rs = null;
	String strSql;

	public String getServerID() {
		return serverID;
	}

	public String getServerCodeIOD() {
		return serverCodeIOD;
	}

	public String getServerCodePUSH() {
		return serverCodePUSH;
	}

	public String getServerName() {
		return serverName;
	}

	public String getInfoFee() {
		return infoFee;
	}

	public String getFeeType() {
		return feeType;
	}

	public int getMediaType() {
		return mediaType;
	}

	public String getSpCode() {
		return spCode;
	}

	public String getMemo() {
		return memo;
	}

	public void setCost_serverID(String _serverID) {
		this.serverID = _serverID;
	}

	public SMSCost() {
		db = new Mysqldb();
	}

	public void lookupInfofeeByServerIDIOD(String serverID) {
		try {
			strSql = "select *  from sms_cost where serverid='" + serverID + "'";
			logger.debug(strSql);
			rs = db.execQuery(strSql);
			if (rs.next()) {
				// System.out.println("ddddddd");
				this.serverID = serverID;
				this.serverCodeIOD = rs.getString("feecode_iod");
				this.serverName = rs.getString("servername");
				this.serverCodePUSH = rs.getString("feecode_push");
				this.infoFee = rs.getString("infofee");
				this.feeType = rs.getString("feetype");
				this.mediaType = rs.getInt("mediatype");
				this.spCode = rs.getString("spcode");
				this.memo = rs.getString("memo");
				// System.out.println("spcode::"+this.spCode);
			}
			// rs =null;
			// db =null;
		} catch (Exception e) {
			logger.error(strSql, e);
		}
	}

	/**
	*
	*
	*/
	public void lookupInfofeeByServerIDPUSH(String serverID) {

		try {
			strSql = "select *  from sms_cost where serverid='" + serverID + "'";
			logger.debug(strSql);
			rs = db.execQuery(strSql);
			if (rs.next()) {
				this.serverID = serverID;
				this.serverCodeIOD = rs.getString("feecode_iod");
				this.serverName = rs.getString("servername");
				this.serverCodePUSH = rs.getString("feecode_push");
				this.infoFee = rs.getString("infofee");
				this.feeType = rs.getString("feetype");
				this.mediaType = rs.getInt("mediatype");
				this.spCode = rs.getString("spcode");
				this.memo = rs.getString("memo");
			}
			rs = null;
			// db =null;//not do it
		} catch (Exception e) {
			logger.error(strSql, e);
		}
	}
}
