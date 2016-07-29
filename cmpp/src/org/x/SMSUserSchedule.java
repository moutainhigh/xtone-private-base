package org.x;
/**
*Copyright 2003 Xiamen Xiangtone Co. Ltd.
*All right reserved.
*/

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.xiangtone.sql.ConnConfigMain;
import com.xiangtone.sql.JdbcControl;
import com.xiangtone.sql.Mysqldb;
import com.xiangtone.sql.QueryCallBack;

/**
 * this Class operate gamelisttbl
 *
 */
public class SMSUserSchedule {
	private final static Logger logger = Logger.getLogger(SMSUserSchedule.class);
	/**
	*
	*/
	ResultSet rs = null;
	String strSql;
	public static final int SPCODE_LEN = 8; // 基础号长度
	public static final int CORP_LEN = 2;// 媒体号长度
	public static final int GAME_LEN = 3;// 游戏号长度
	public static final int GAME_LEN1 = 2;// 游戏长度为2
	public static final int GAME_LEN2 = 1;// 游戏长度为1
	public static final int GAME_LEN3 = 4;// 游戏长度为4
	public static final int GAME_LEN4 = 5;// 游戏长度为5

	String gameCode = "";
	String spCode = "";
	String gameID = "";
	String corpID = "00";
	String actionCode = "";
	public String serverID = "1000";
	int vcpID = 1;
	String ismgId = "01";

	/**
	 * get method
	 *
	 */

	public String getUSched_gameCode() {
		return gameCode;
	}

	public String getUSched_serverID() {
		return getServerIDbyServerName(getUSched_gameCode());
	}

	public String getUSched_spCode() {
		return spCode;
	}

	public String getUSched_actionCode() {
		return actionCode;
	}

	public int getUSched_vcpID() {
		return vcpID;
	}

	public String getUSched_corpID() {
		return corpID;
	}

	/**
	 *
	 *
	 */
	public SMSUserSchedule() {
	}

	/**
	 * 取得最终结果 游戏名称
	 *
	 */
	public void getUserDetail(String spcode, String info) {
		try {
			this.serverID = "1000";
			this.vcpID = 1;
			this.spCode = spcode;
			String content = info.toUpperCase().trim();

			if (isItemExist(content)) {
				if (this.spCode.length() > 8) {
					this.spCode = this.spCode.substring(0, 8);
					this.spCode += "" + this.gameID;// change at 070201
					this.actionCode = "";
					this.gameCode = content;
					return;
				}

			}
			logger.info(":::::::::::"+":::::::::::"+":::::::::::");
			logger.info("game id:" + this.gameID);
			logger.info("spcode:" + this.spCode);
			logger.info(":::::::::::"+":::::::::::"+":::::::::::");
			int offset = content.indexOf(" ", 0);// 判断空格
			if (offset > 0) {
				gameCode = content.substring(0, offset);
				actionCode = content.substring(offset + 1);
			} else {
				gameCode = content;
				actionCode = "";
			}
			if (isItemExist(gameCode)) {
				this.gameCode = gameCode;
			} else {
				this.gameCode = "ERROR";
				this.actionCode = content;
			}

			int len = spCode.length();
			logger.info("----------------------"+"----------------------");
			logger.info("this length is:" + len);
			logger.info("----------------------"+"----------------------");
			String _strspcode = "";
			String _strgame = "";
			switch (len) {
			case SPCODE_LEN:
				if (isItemExist(gameCode)) {
					this.gameCode = gameCode;
					this.gameID = this.gameID;
					this.vcpID = this.vcpID;
					this.corpID = "";
					this.spCode = this.spCode + this.corpID + this.gameID;
				} else {
					this.gameCode = "ERROR";
					this.vcpID = 1;
				}
				break;
			/*
			 * case SPCODE_LEN+CORP_LEN: String strspcode =
			 * spCode.substring(0,SPCODE_LEN); String strcorpId =
			 * spCode.substring(SPCODE_LEN,SPCODE_LEN+CORP_LEN);
			 * if(!isCorpIDExist(strcorpId)) { this.corpID = "00"; this.spCode =
			 * strspcode+this.corpID; } if(isItemExist(gameCode)) { this.spCode =
			 * this.spCode+this.gameID; } else { this.vcpID = 1; this.gameCode =
			 * "ERROR"; } break;
			 */
			// case SPCODE_LEN+CORP_LEN+GAME_LEN:

			case SPCODE_LEN + GAME_LEN:
				_strspcode = spCode.substring(0, SPCODE_LEN);
				/*
				 * String _strcorp = spCode.substring(SPCODE_LEN,SPCODE_LEN);
				 */
				_strgame = spCode.substring(SPCODE_LEN, SPCODE_LEN + GAME_LEN);
				/*
				 * if(!isCorpIDExist(_strcorp)) { this.corpID = "00"; this.spCode =
				 * _strspcode+this.corpID+_strgame; }
				 */
				if (!isGameIDExist(_strgame)) {
					this.gameCode = "ERROR";
					this.spCode = _strspcode;
				}
				logger.info("***********this.vcpID:" + this.vcpID);
				logger.info("***********this.vcpID:" + this.spCode);

				break;
			case SPCODE_LEN + GAME_LEN1:
				_strspcode = spCode.substring(0, SPCODE_LEN);
				/*
				 * String _strcorp = spCode.substring(SPCODE_LEN,SPCODE_LEN);
				 */
				_strgame = spCode.substring(SPCODE_LEN, SPCODE_LEN + GAME_LEN1);
				/*
				 * if(!isCorpIDExist(_strcorp)) { this.corpID = "00"; this.spCode =
				 * _strspcode+this.corpID+_strgame; }
				 */
				if (!isGameIDExist(_strgame)) {
					this.gameCode = "ERROR";
					this.spCode = _strspcode;
				}
				logger.info("***********this.vcpID:" + this.vcpID);
				logger.info("***********this.vcpID:" + this.spCode);

				break;
			case SPCODE_LEN + GAME_LEN2:
				_strspcode = spCode.substring(0, SPCODE_LEN);
				/*
				 * String _strcorp = spCode.substring(SPCODE_LEN,SPCODE_LEN);
				 */
				_strgame = spCode.substring(SPCODE_LEN, SPCODE_LEN + GAME_LEN2);
				/*
				 * if(!isCorpIDExist(_strcorp)) { this.corpID = "00"; this.spCode =
				 * _strspcode+this.corpID+_strgame; }
				 */
				if (!isGameIDExist(_strgame)) {
					this.gameCode = "ERROR";
					this.spCode = _strspcode;
				}
				logger.info("***********this.vcpID:" + this.vcpID);
				logger.info("***********this.vcpID:" + this.spCode);

				break;
			case SPCODE_LEN + GAME_LEN3:
				_strspcode = spCode.substring(0, SPCODE_LEN);
				/*
				 * String _strcorp = spCode.substring(SPCODE_LEN,SPCODE_LEN);
				 */
				_strgame = spCode.substring(SPCODE_LEN, SPCODE_LEN + GAME_LEN3);
				/*
				 * if(!isCorpIDExist(_strcorp)) { this.corpID = "00"; this.spCode =
				 * _strspcode+this.corpID+_strgame; }
				 */
				if (!isGameIDExist(_strgame)) {
					this.gameCode = "ERROR";
					this.spCode = _strspcode;
				}
				logger.info("***********this.vcpID:" + this.vcpID);
				logger.info("***********this.vcpID:" + this.spCode);

				break;
			case SPCODE_LEN + GAME_LEN4:
				_strspcode = spCode.substring(0, SPCODE_LEN);
				/*
				 * String _strcorp = spCode.substring(SPCODE_LEN,SPCODE_LEN);
				 */
				_strgame = spCode.substring(SPCODE_LEN, SPCODE_LEN + GAME_LEN4);
				/*
				 * if(!isCorpIDExist(_strcorp)) { this.corpID = "00"; this.spCode =
				 * _strspcode+this.corpID+_strgame; }
				 */
				if (!isGameIDExist(_strgame)) {
					this.gameCode = "ERROR";
					this.spCode = _strspcode;
				}
				logger.info("***********this.vcpID:" + this.vcpID);
				logger.info("***********this.vcpID:" + this.spCode);

				break;
			default:

				this.spCode = spCode.substring(0, 8);
				if (isItemExist(gameCode)) {
					this.gameCode = this.gameCode;
					this.corpID = "";
					this.spCode = this.spCode + this.corpID + this.gameID;
				} else {
					this.gameCode = "ERROR";
					this.vcpID = 1;
				}

				break;

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	/**
	*
	*
	*/
	private boolean isItemExist(String strGameCode) {
		boolean flag = false;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM sms_gamelist WHERE gamename='"+strGameCode+"' AND ismgid='"+ismgId+"'";
		try
		{
			conn = ConnConfigMain.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			logger.debug("finish query sql [ " + sql + " ]");
			if(rs.next()){
				flag = true;
				this.gameID = new Integer(rs.getInt("gameid")).toString();
				this.gameCode = rs.getString("gamename");

				this.vcpID = rs.getInt("vcpid");
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			logger.error("query sql [" + sql + "] error :" + ex.getMessage());
		}
		finally
		{
			try{ if(rs!=null)rs.close(); }catch(Exception ex){}
			try{ if(stmt!=null)stmt.close(); }catch(Exception ex){}
			try{ if(conn!=null)conn.close(); }catch(Exception ex){}
		}
		return flag;
	}

	/**
	*
	*
	*/
	private boolean isGameIDExist(String gameId) {
		boolean flag = false;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "select * from sms_gamelist where gameid='" + gameId + "' and ismgid='" + ismgId + "'";
		try
		{
			conn = ConnConfigMain.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			logger.debug("finish query sql [ " + sql + " ]");
			if(rs.next()){
				flag = true;
				this.gameCode = rs.getString("gamename");
				this.serverID = getServerIDbyServerName(this.gameCode);
				this.gameID = new Integer(rs.getString("gameid")).toString();
				// this.gameID = gameId;
				this.vcpID = rs.getInt("vcpid");
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			logger.error("query sql [" + sql + "] error :" + ex.getMessage());
		}
		finally
		{
			try{ if(rs!=null)rs.close(); }catch(Exception ex){}
			try{ if(stmt!=null)stmt.close(); }catch(Exception ex){}
			try{ if(conn!=null)conn.close(); }catch(Exception ex){}
		}
		return flag;
	}

	/**
	 * 如果存在合作伙伴id 就返回 不存在就使用默认
	 */
	private boolean isCorpIDExist(String id) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "select * from sms_company where corp_id='" + id + "'";
		try
		{
			conn = ConnConfigMain.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			logger.debug("finish query sql [ " + sql + " ]");
			if (!rs.next()){
				return false;
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			logger.error("query sql [" + sql + "] error :" + ex.getMessage());
		}
		finally
		{
			try{ if(rs!=null)rs.close(); }catch(Exception ex){}
			try{ if(stmt!=null)stmt.close(); }catch(Exception ex){}
			try{ if(conn!=null)conn.close(); }catch(Exception ex){}
		}
		this.corpID = id;
		return true;

	}

	/**
	 * 通过servername 得到serverid
	 *
	 */
	private String getServerIDbyServerName(String servername) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "select * from sms_cost where servername='" + servername + "' limit 1";
		try
		{
			conn = ConnConfigMain.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			logger.debug("finish query sql [ " + sql + " ]");
			if(rs.next()){
				return rs.getString("serverid");
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			logger.error("query sql [" + sql + "] error :" + ex.getMessage());
		}
		finally
		{
			try{ if(rs!=null)rs.close(); }catch(Exception ex){}
			try{ if(stmt!=null)stmt.close(); }catch(Exception ex){}
			try{ if(conn!=null)conn.close(); }catch(Exception ex){}
		}
		return this.serverID;
	}

	public static void main(String[] args) {
		SMSUserSchedule sms = new SMSUserSchedule();
		sms.getUserDetail(args[0], args[1]);
		String game_code = sms.getUSched_gameCode();
		String sp_code = sms.getUSched_spCode();
		String action_code = sms.getUSched_actionCode();
		int vcp_id = sms.getUSched_vcpID();
		String corp_id = sms.getUSched_corpID();
		String server_id = sms.getUSched_serverID();

		logger.debug("game_code:" + game_code);
		logger.debug("sp_code:" + sp_code);
		logger.debug("action_code:" + action_code);
		logger.debug("vcp_id:" + vcp_id);
		logger.debug("corp_id:" + corp_id);
		logger.debug("serverid:" + server_id);
	}

}