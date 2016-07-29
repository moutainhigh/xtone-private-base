package org.x;

/**
*Copyright 2003 Xiamen Xiangtone Co. Ltd.
*All right reserved.
*/
import java.io.*;
import java.util.*;

import com.xiangtone.sql.JdbcControl;
import com.xiangtone.sql.Mysqldb;

import java.sql.*;

/**
*
*
*/
public class SMSMT {

	public int vcpID;
	public String ismgID;
	public String spCode;

	public String destCpn; // 接收方的手机号
	public String feeCpn; // 发送方的手机号
	public String serverID; // 内容属于那个项目的
	public String serverName;
	public String infoFee; // 费用的值(以分为单位)
	public String feeCode;
	public String feeType; // 收费的方式
	public String sendContent;
	public int mediaType;
	public String sendTime; //

	public String corpID;
	// add at 061123
	public String linkID;
	public String msgId;// add at 08-11-27
	public int cpnType;
	//

	public int submitSeq;
	public String submitMsgID;
	public int submitResult;

	public int tp_pid;
	public int tp_udhi;
	public int data_coding;
	public int report_flag;

	private Mysqldb db;
	ResultSet rs = null;
	private String strSql;

	/**
	 * set and get method
	 *
	 */
	public int getMT_vcpID() {
		return vcpID;
	}

	public String getMT_ismgID() {
		return ismgID;
	}

	public String getMT_spCode() {
		return spCode;
	}

	public String getMT_destCpn() {
		return destCpn;
	}

	public String getMT_feeCpn() {
		return feeCpn;
	}

	public String getMT_serverID() {
		return serverID;
	}

	public String getMT_serverName() {
		return serverName;
	}

	public String getMT_infoFee() {
		return infoFee;
	}

	public String getMT_feeCode() {
		return feeCode;
	}

	public String getMT_feeType() {
		return feeType;
	}

	public String getMT_sendContent() {
		return sendContent;
	}

	public int getMT_mediaType() {
		return mediaType;
	}

	public String getMT_sendTime() {
		return sendTime;
	}

	public int getMT_submitSeq() {
		return submitSeq;
	}

	public String getMT_submitMsgID() {
		return submitMsgID;
	}

	public int getMT_submitResult() {
		return submitResult;
	}

	public String getMT_msgId() {
		return msgId;
	}

	//

	public void setMT_vcpID(int vcpID) {
		this.vcpID = vcpID;
	}

	public void setMT_ismgID(String ismgID) {
		this.ismgID = ismgID;
	}

	public void setMT_spCode(String spCode) {
		this.spCode = spCode;
	}

	public void setMT_destCpn(String destCpn) {
		this.destCpn = destCpn;
	}

	public void setMT_feeCpn(String feeCpn) {
		this.feeCpn = feeCpn;
	}

	public void setMT_serverID(String serverID) {
		this.serverID = serverID;
	}

	public void setMT_serverName(String serverName) {
		this.serverName = serverName;
	}

	public void setMT_infoFee(String infoFee) {
		this.infoFee = infoFee;
	}

	public void setMT_feeCode(String feeCode) {
		this.feeCode = feeCode;
	}

	public void setMT_feeType(String feeType) {
		this.feeType = feeType;
	}

	public void setMT_sendContent(String sendContent) {
		this.sendContent = sendContent;
	}

	public void setMT_mediaType(int mediaType) {
		this.mediaType = mediaType;
	}

	public void setMT_sendTime(String sendTime) {
		this.sendTime = sendTime;
	}

	public void setMT_submitSeq(int submitSeq) {
		this.submitSeq = submitSeq;
	}

	public void setMT_submitMsgID(String submitMsgID) {
		this.submitMsgID = submitMsgID;
	}

	public void setMT_submitResult(int submitResult) {
		this.submitResult = submitResult;
	}

	public void setMT_corpID(String _corpid) {
		this.corpID = _corpid;
	}

	// add at 061123
	public void setMT_linkID(String _linkid) {
		this.linkID = _linkid;
	}

	public void setMT_cpnType(int _cpntype) {
		this.cpnType = _cpntype;
	}

	public void setMT_msgID(String msgId) {
		this.msgId = msgId;
	}

	/**
	 * Constructor
	 *
	 */
	public SMSMT() {
		db = new Mysqldb();
	}

	/**
	*
	*
	*/

	public void insertMTLog() {

		String sql = "INSERT INTO sms_mtlog SET vcpid=?,ismgid=?,comp_msgid=?,corpid=?,spcode=?,destcpn=?,feecpn=?,serverid=?,servername=?,infofee=?,feetype=?,feecode=?,content=?,linkid=?,mediatype=?,sendtime=?,submit_msgid=?,submit_result=?,submit_seq=?";
		Map<Integer, Object> param = new HashMap<Integer, Object>();

		param.put(1, vcpID);
		param.put(2, ismgID);
		param.put(3, submitMsgID);
		param.put(4, corpID);
		param.put(5, spCode);
		param.put(6, destCpn);
		param.put(7, feeCpn);
		param.put(8, serverID);
		param.put(9, serverName);
		param.put(10, infoFee);
		param.put(11, feeType);
		param.put(12, feeCode);
		param.put(13, sendContent);
		param.put(14, linkID);
		param.put(15, mediaType);
		param.put(16, sendTime);
		param.put(17, submitMsgID);
		param.put(18, submitResult);
		param.put(19, submitSeq);
		new JdbcControl().execute(sql, param);
	}

	/**
	*
	*
	*/
	public void updateSubmitSeq(String ismgid, int seq, String msg_id, int submit_result) {
		String sql = "UPDATE sms_mtlog SET submit_seq = 0 ,submit_msgid=?,submit_result=? WHERE submit_seq =? AND ismgid =? ORDER BY id DESC LIMIT 1";

		Map<Integer, Object> param = new HashMap<Integer, Object>();

		param.put(1, msg_id);
		param.put(2, submit_result);
		param.put(3, seq);
		param.put(4, ismgid);
		new JdbcControl().execute(sql, param);
	}

	public void updateSubmitSeq() {
		String sql = "UPDATE sms_mtlog SET submit_seq = 0 ,submit_msgid=?,submit_result=? WHERE submit_seq =? AND ismgid =? ORDER BY id DESC LIMIT 1";

		Map<Integer, Object> param = new HashMap<Integer, Object>();

		param.put(1, this.submitMsgID);
		param.put(2, this.submitResult);
		param.put(3, this.submitSeq);
		param.put(4, this.ismgID);
		new JdbcControl().execute(sql, param);
	}

	public void insertMCLog(int card_flag) {
		String sql = "INSERT INTO sms_mclog SET vcpid=?,ismgid=?,corpid=?,spcode=?,destcpn=?,feecpn=?,serverid=?,servername=?,infofee=?,feetype=?,feecode=?,content=?,mediatype=?,sendtime=?,submit_seq=?,card_flag=?";

		Map<Integer, Object> param = new HashMap<Integer, Object>();

		param.put(1, vcpID);
		param.put(2, ismgID);
		param.put(3, corpID);
		param.put(4, spCode);
		param.put(5, destCpn);
		param.put(6, feeCpn);
		param.put(7, serverID);
		param.put(8, serverName);
		param.put(9, infoFee);
		param.put(10, feeType);
		param.put(11, feeCode);
		param.put(12, sendContent);
		param.put(13, mediaType);
		param.put(14, sendTime);
		param.put(15, submitSeq);
		param.put(16, card_flag);
		new JdbcControl().execute(sql, param);
	}
}