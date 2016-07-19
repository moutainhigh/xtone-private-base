package org.x;
/**
*Copyright 2003 Xiamen Xiangtone Co. Ltd.
*All right reserved.
*/

import java.io.*;
import java.sql.*;

import org.apache.log4j.Logger;
import org.common.util.ConnectionService;

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

	private Connection con = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	private String strSql;

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
	}

	public void lookupInfofeeByServerIDIOD(String serverID) {
		try {
			strSql = "select *  from sms_cost where serverid='" + serverID + "'";
			logger.debug(strSql);
			con = ConnectionService.getInstance().getConnectionForLocal();
			ps = con.prepareStatement(strSql);
			rs = ps.executeQuery();
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
			}
		} catch (Exception e) {
			logger.error(strSql, e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
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
			con = ConnectionService.getInstance().getConnectionForLocal();
			ps = con.prepareStatement(strSql);
			rs = ps.executeQuery();
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
		} catch (Exception e) {
			logger.error(strSql, e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
