package org.x;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.common.util.ConnectionService;

/**
 * A class respresenting a set of packet and byte couters. It is bservable to
 * allw it to be watched, but only comment of a class
 */
public class SMSMO {
	/**
	 * Packet counters;
	 * 
	 */
	private static Logger logger = Logger.getLogger(SMSMO.class);
	protected String cpn;// 用户手机号
	protected int cpnType;
	protected String spCode; // 特别服务号(长号)
	protected String spCodeFirst;// 特服务号
	protected String svcType; // 业务类型
	protected String content; // 短信内容
	protected String serverName;// content分解0 ,业务代号
	protected String serverID; // 服务id
	protected String serverAction = ""; // content分解1 ,业务指令
	protected String deliverTime; // 接收时间
	protected String ismgID; // 省网关标志(01北京06辽宁08黑龙江15山东19广东
	protected int vcpID;
	protected int len;
	protected int tpUdhi = 0;
	protected int fmt = 0;
	protected String msgId = "";
	private Connection con = null;
	private PreparedStatement ps = null;
	private String strSql = "";
	private ResultSet rs = null;
	protected String corpID;
	protected String linkID;

	/**
	 * method of set and get
	 *
	 */
	public String getMOCpn() {
		return cpn;
	}

	public int getMOCpnType() {
		return cpnType;
	}

	public String getMOSpCode() {
		return spCode;
	}

	public String getMOSvcType() {
		return svcType;
	}

	public String getMOContent() {
		return content;
	}

	public String getMOServerName() {
		return serverName;
	}

	public String getMOServerID() {
		return serverID;
	}

	public String getMOServerAction() {
		return serverAction;
	}

	public String getMODeliverTime() {
		return deliverTime;
	}

	public String getMOIsmgID() {
		return ismgID;
	}

	public int getMOVcpID() {
		return vcpID;
	}

	public String getMOCorpID() {
		return corpID;
	}

	public String getMOLinkID() {
		return linkID;
	}

	public String getMOMsgId() {
		return msgId;
	}

	public void setMOCpn(String cpn) {
		this.cpn = cpn;
	}

	public void setMoCpnType(int cpntype) {
		this.cpnType = cpntype;
	}

	public void setMOSpCode(String spcode) {
		this.spCode = spcode;
	}

	public void setMOSpCodeFirst(String spCodeFirst) {
		this.spCodeFirst = spCodeFirst;
	}

	public void setMOSvcType(String svcType) {
		this.svcType = svcType;
	}

	public void setMOContent(String content) {
		this.content = content;
	}

	public void setMOServerName(String serverName) {
		this.serverName = serverName;
	}

	public void setMOServerID(String serverID) {
		this.serverID = serverID;
	}

	public void setMOServerAction(String serverAction) {
		this.serverAction = serverAction;
	}

	public void setMODeliverTime(String deliverTime) {
		this.deliverTime = deliverTime;
	}

	public void setMOIsmgID(String ismgID) {
		this.ismgID = ismgID;
	}

	public void setMOVcpID(int vcpID) {
		this.vcpID = vcpID;
	}

	public void setMOCorpID(String corpID) {
		this.corpID = corpID;
	}

	public void setMOLinkID(String linkid) {
		this.linkID = linkid;
	}

	///////////// add setMsgId
	public void setMOMsgId(String msgId) {
		this.msgId = msgId;
	}

	/**
	 * Construct Function
	 *
	 */
	public SMSMO() {
	}

	/**
	 * the first methods of class insert mo log
	 */
	public void insertMOLog() {
		try {
			strSql = "insert into sms_molog set ";
			strSql += " vcpid=" + vcpID;
			strSql += ",ismgid='" + ismgID + "'";
			strSql += ",corpid='" + corpID + "'";
			strSql += ",spcode_first='" + spCodeFirst + "'";
			strSql += ",content='" + content + "'";
			strSql += ",spcode='" + spCode + "'";
			strSql += ",msgid='" + msgId + "'";
			strSql += ",cpn='" + cpn + "'";
			strSql += ",serverid='" + serverID + "'";
			strSql += ",servername='" + serverName + "'";
			strSql += ",serveraction='" + serverAction + "'";
			strSql += ",delivertime='" + deliverTime + "'";
			strSql += ",linkid='" + linkID + "'";
			logger.debug(strSql);
			con = ConnectionService.getInstance().getConnectionForLocal();
			ps = con.prepareStatement(strSql);
			ps.executeUpdate();
		} catch (Exception e) {
			logger.error("insertMOLog", e);
			e.printStackTrace();
		} finally {
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
	public void insertErrorMOLog() {
		try {
			strSql = "insert into sms_molog_error set ";
			strSql += " vcpid=" + vcpID;
			strSql += ",ismgid='" + ismgID + "'";
			strSql += ",corpid='" + corpID + "'";
			strSql += ",spcode_first='" + spCodeFirst + "'";
			strSql += ",content='" + content + "'";
			strSql += ",spcode='" + spCode + "'";
			strSql += ",cpn='" + cpn + "'";
			strSql += ",serverid='" + serverID + "'";
			strSql += ",servername='" + serverName + "'";
			strSql += ",serveraction='" + serverAction + "'";
			strSql += ",delivertime='" + deliverTime + "'";
			logger.debug(strSql);
			con = ConnectionService.getInstance().getConnectionForLocal();
			ps = con.prepareStatement(strSql);
			ps.executeUpdate();
		} catch (Exception e) {
			logger.error("insertErrorMOLog", e);
			e.printStackTrace();
		} finally {
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

	public String getMOCorpID(String cpn) {
		try {
			strSql = "select corp_id from sms_user where cpn='" + cpn + "'";
			logger.debug(strSql);
			con = ConnectionService.getInstance().getConnectionForLocal();
			ps = con.prepareStatement(strSql);
			rs = ps.executeQuery();
			if (rs.next()) {
				String corp_id = rs.getString("corp_id");
				return corp_id;
			}
		} catch (Exception e) {
			logger.error("getMOCorpID", e);
			e.printStackTrace();
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
		return "00";
	}

	/**
	 * 取到gameid
	 *
	 */
	public String getGameID(int vcpid, String servername) {
		try {
			strSql = "select gameid from sms_gamelist where vcpid=" + vcpid + " and gamename='" + servername + "'";
			logger.debug(strSql);
			con = ConnectionService.getInstance().getConnectionForLocal();
			ps = con.prepareStatement(strSql);
			rs = ps.executeQuery();
			if (rs.next()) {
				String gameid = rs.getString("gameid");
				return gameid;
			}
		} catch (Exception e) {
			logger.error("getGameID", e);
			e.printStackTrace();
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
		return "";
	}

	/**
	 *
	 *
	 */

	public String getImsgID(String scpn) {
		try {
			strSql = " select ismgid from sms_user where cpn='" + scpn + "'";
			logger.debug(strSql);
			con = ConnectionService.getInstance().getConnectionForLocal();
			ps = con.prepareStatement(strSql);
			rs = ps.executeQuery();
			if (rs.next()) {
				String ismgid = rs.getString("ismgid");
				return ismgid;
			}
		} catch (Exception e) {
			logger.error("getImsgID", e);
			;
			e.printStackTrace();
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
		return "01";
	}

}