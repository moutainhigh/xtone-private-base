package org.x;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>All right reserved</p>
 * <p>Company: Disney.com</p>
 * @author Gavin Wang
 * @version 0.5
 */
import java.io.*;
import java.util.*;

import com.xiangtone.sql.JdbcControl;
import com.xiangtone.sql.Mysqldb;
import com.xiangtone.sql.QueryCallBack;

import java.sql.*;

/**
 * A class respresenting a set of packet and byte couters. It is bservable to
 * allw it to be watched, but only comment of a class
 */
public class SMSMO {
	/**
	 * Packet counters;
	 * 
	 */
	protected String cpn; // 用户手机号
	protected int cpnType;
	protected String spCode; // 特别服务号(长号)
	protected String spCode_first; // 特服务号
	protected String svcType; // 业务类型
	protected String content; // 短信内容
	protected String serverName; // content分解0 ,业务代号
	protected String serverID; // 服务id
	protected String serverAction = ""; // content分解1 ,业务指令
	protected String deliverTime; // 接收时间
	protected String ismgID; // 省网关标志(01北京06辽宁08黑龙江15山东19广东
	protected int vcpID;
	protected int len;
	protected int tp_udhi = 0;
	protected int fmt = 0;
	protected String msgId = "";
	public Mysqldb db;
	private String strSql = "";
	private ResultSet rs = null;

	protected String corpID;
	protected String linkID;

	/**
	 * method of set and get
	 *
	 */
	public String getMO_cpn() {
		return cpn;
	}

	public int getMO_cpnType() {
		return cpnType;
	}

	public String getMO_spCode() {
		return spCode;
	}

	public String getMO_svcType() {
		return svcType;
	}

	public String getMO_content() {
		return content;
	}

	public String getMO_serverName() {
		return serverName;
	}

	public String getMO_serverID() {
		return serverID;
	}

	public String getMO_serverAction() {
		return serverAction;
	}

	public String getMO_deliverTime() {
		return deliverTime;
	}

	public String getMO_ismgID() {
		return ismgID;
	}

	public int getMO_vcpID() {
		return vcpID;
	}

	public String getMO_corpID() {
		return corpID;
	}

	public String getMO_linkID() {
		return linkID;
	}

	public String getMO_msgId() {
		return msgId;
	}

	public void setMO_cpn(String cpn) {
		this.cpn = cpn;
	}

	public void setMo_cpntype(int _cpntype) {
		this.cpnType = _cpntype;
	}

	public void setMO_spCode(String spcode) {
		this.spCode = spcode;
	}

	public void setMO_spCode_first(String spCode_first) {
		this.spCode_first = spCode_first;
	}

	public void setMO_svcType(String svcType) {
		this.svcType = svcType;
	}

	public void setMO_content(String content) {
		this.content = content;
	}

	public void setMO_serverName(String serverName) {
		this.serverName = serverName;
	}

	public void setMO_serverID(String serverID) {
		this.serverID = serverID;
	}

	public void setMO_serverAction(String serverAction) {
		this.serverAction = serverAction;
	}

	public void setMO_deliverTime(String deliverTime) {
		this.deliverTime = deliverTime;
	}

	public void setMO_ismgID(String ismgID) {
		this.ismgID = ismgID;
	}

	public void setMO_vcpID(int vcpID) {
		this.vcpID = vcpID;
	}

	public void setMO_corpID(String _corpID) {
		this.corpID = _corpID;
	}

	public void setMO_linkID(String linkid) {
		this.linkID = linkid;
	}

	///////////// add setMsgId
	public void setMO_msgId(String msgId) {
		this.msgId = msgId;
	}

	/**
	 * Construct Function
	 *
	 */
	public SMSMO() {
		db = new Mysqldb();
	}

	/**
	 * the first methods of class insert mo log
	 */
	public void insertMOLog() {
		String sql = "INSERT INTO sms_molog SET  vcpid=?,ismgid=?,corpid=?,spcode_first=?,content=?,spcode=?,msgid=?,cpn=?,serverid=?,servername=?,serveraction=?,delivertime=?,linkid=?";

		Map<Integer, Object> param = new HashMap<Integer, Object>();

		param.put(1, vcpID);
		param.put(2, ismgID);
		param.put(3, corpID);
		param.put(4, spCode_first);
		param.put(5, content);
		param.put(6, spCode);
		param.put(7, msgId);
		param.put(8, cpn);
		param.put(9, serverID);
		param.put(10, serverName);
		param.put(11, serverAction);
		param.put(12, deliverTime);
		param.put(13, linkID);
		new JdbcControl().execute(sql, param);
	}

	/**
	*
	*
	*/
	public void insertErrorMOLog() {
		String sql = "INSERT INTO sms_molog_error SET  vcpid=?,ismgid=?,corpid=?,spcode_first=?,content=?,spcode=?,cpn=?,serverid=?,servername=?,serveraction=?,delivertime=?";

		Map<Integer, Object> param = new HashMap<Integer, Object>();

		param.put(1, vcpID);
		param.put(2, ismgID);
		param.put(3, corpID);
		param.put(4, spCode_first);
		param.put(5, content);
		param.put(6, spCode);
		param.put(7, cpn);
		param.put(8, serverID);
		param.put(9, serverName);
		param.put(10, serverAction);
		param.put(11, deliverTime);
		new JdbcControl().execute(sql, param);
	}

	@SuppressWarnings("unchecked")
	public String getMO_corpID(String cpn) {
		String sql = "SELECT corp_id FROM sms_user WHERE cpn='"+cpn+"'";
		return (String)new JdbcControl().query(sql, new QueryCallBack() {
			
			@Override
			public Object onCallBack(ResultSet rs) throws SQLException
			{
				if(rs.next())
				{
					return rs.getString("corp_id");
				}
				return "00";
			}
		});
	}

	/**
	 * 取到gameid
	 *
	 */
	@SuppressWarnings("unchecked")
	public String getGameID(int vcpid, String servername) {
		String sql = "SELECT gameid FROM sms_gamelist SHERE vcpid="+vcpid+" AND gamename='"+servername+"'";
		return (String)new JdbcControl().query(sql, new QueryCallBack()
		{
			@Override
			public Object onCallBack(ResultSet rs) throws SQLException
			{
				if(rs.next())
				{
					return rs.getString("gameid");
				}
				return "";
			}
		});
	}

	/**
	 *
	 *
	 */
	@SuppressWarnings("unchecked")
	public String getImsgID(String scpn) {
		String sql = " select ismgid from sms_user where cpn='"+scpn+"'";
		return (String)new JdbcControl().query(sql, new QueryCallBack()
		{
			@Override
			public Object onCallBack(ResultSet rs) throws SQLException
			{
				if(rs.next())
				{
					return rs.getString("ismgid");
				}
				return "04";
			}
		});
	}

}