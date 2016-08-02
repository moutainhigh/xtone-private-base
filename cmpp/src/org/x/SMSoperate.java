package org.x;

import com.xiangtone.util.FormatSysTime;
import com.xiangtone.util.MyTools;

public class SMSoperate {

	/*
	 * 
	 * public SMSMO smsmo;
	 * 
	 * public SMSMT smsmt;
	 * 
	 * public SMSUserSchedule smsus;
	 * 
	 * public SMSMOtoVCP smsTovcp;
	 * 
	 * public SMSReport smsreport;
	 * 
	 * public SMSUser user;
	 * 
	 * 
	 * 
	 */

	public SMSUserSchedule smsus;

	// public SMSReport smsreport;

	public SMSUser user;

	// public SMSMO smsmo;

	// public SMSMT smsmt;

	public SMSMOtoVCP smsTovcp;

	public String deliver_ismgID;

	public String deliver_msgID;

	public String deliver_spCode;

	public String deliver_serverID;

	public int deliver_fmt;

	public String deliver_srcCpn;

	public int src_CpnType;

	public int deliver_contentLen;

	public byte[] deliver_content;

	public String linkid = "";

	public void setDeliver_ismgID(String _ismgID) {
		this.deliver_ismgID = _ismgID;
	}

	public void setDeliver_msgID(String _msgID) {
		this.deliver_msgID = _msgID;
	}

	public void setDeliver_spCode(String _spCode) {
		this.deliver_spCode = _spCode;
	}

	public void setDeliver_serverID(String _serverID) {
		this.deliver_serverID = _serverID;
	}

	public void setDeliver_fmt(int _fmt) {
		this.deliver_fmt = _fmt;
	}

	public void setDeliver_srcCpn(String _srcCpn) {

		try {

			System.out.println(_srcCpn.length());

			if (_srcCpn.trim().length() > 11)

				_srcCpn = _srcCpn.substring(2, 13);

		}

		catch (IndexOutOfBoundsException e) {

			System.out.println(e.toString());

			e.printStackTrace();

		}

		this.deliver_srcCpn = _srcCpn;

	}

	public void setDeliver_srcCpnType(int srccpntype) {

		this.src_CpnType = srccpntype;

	}

	public void setDeliver_contentLen(int _len) {
		this.deliver_contentLen = _len;
	}

	public void setDeliver_content(byte[] _content) {
		this.deliver_content = _content;
	}

	public void setDeliver_linkId(String _linkid) {

		if (_linkid.length() < 1) {

			_linkid = "";

		}

		this.linkid = _linkid;

	}

	public SMSoperate() {

		/*
		 * 
		 * smsmo = new SMSMO();
		 * 
		 * smsmt = new SMSMT();
		 * 
		 * smsus = new SMSUserSchedule();
		 * 
		 * smsreport = new SMSReport();
		 * 
		 * smsTovcp = new SMSMOtoVCP();
		 * 
		 * user=new SMSUser();
		 * 
		 */

		smsus = new SMSUserSchedule();

		// smsreport = new SMSReport();//change at 090804

		// user=new SMSUser();

		// smsmo = new SMSMO();

		// smsmt = new SMSMT();//change at 090804

		smsTovcp = new SMSMOtoVCP();

	}

	/**
	 * 
	 * 更新提交后返回的序号
	 *
	 * 
	 * 
	 */

	public void receiveSubmitResp(String _ismgID, int _seq, String _msgID, int _submitResult) {

		try {

			SMSMT smsmt = new SMSMT();

			smsmt.setMT_ismgID(_ismgID);

			smsmt.setMT_submitSeq(_seq);

			smsmt.setMT_submitMsgID(_msgID.trim());

			smsmt.setMT_submitResult(_submitResult);

			// smsmt.updateSubmitSeq(_ismgID,_seq,_msgID,_submitResult);

			smsmt.updateSubmitSeq();

		}

		catch (Exception e) {

			System.out.println("receiveSubmitResp:" + e.toString());

		}

	}

	/**
	 * 
	 * 接受report
	 *
	 * 
	 * 
	 */

	public void receiveReport(String _ismgID, String msgId, String linkId,

			String dest_cpn, String str_spcode, String str_cpn,

			String submit_time, String done_time, int stat_dev, String reportMsg) {

		try {
			SMSReport smsreport = new SMSReport();// change at 090804
			if (dest_cpn.trim().length() > 11)

				dest_cpn = dest_cpn.substring(2, 13);

			if (str_cpn.trim().length() > 11)

				str_cpn = str_cpn.substring(2, 13);

			smsreport.setReport_ismgID(_ismgID);

			smsreport.setReport_msgID(msgId);

			smsreport.setReport_linkId(linkId.trim());

			smsreport.setReport_destCpn(dest_cpn);

			smsreport.setReport_spCode(str_spcode);

			smsreport.setReport_srcCpn(str_cpn);

			smsreport.setReport_submitTime(submit_time);

			smsreport.setReport_doneTime(done_time);

			smsreport.setReport_stat(stat_dev);

			smsreport.setReport_msg(reportMsg);// add at 2009-02-17

			// smsreport.saveReportLog();

			smsreport.insertReportLog();

		}

		catch (Exception e) {

			e.printStackTrace();

			System.out.println("receiveReport:" + e.toString());

		}

	}

	public void receiveDeliver() {
		SMSMO smsmo = new SMSMO();// change at 091105
		try {

			String str_content = "";

			switch (this.deliver_fmt) // 根据不同编码的信息处理

			{

			case 0:
			case 15:
			case 17:
				str_content = new String(this.deliver_content);// .substring(0,this.deliver_contentLen);

				break;

			case 8:

				str_content = MyTools.UCS2GB2312(this.deliver_content);

				break;

			default:

				System.out.println("消息编码有误");

				str_content = "消息编码有误" + this.deliver_fmt;

				break;

			}

			// change at 2012-08-07
			if (str_content.endsWith(" ")) {
				str_content = str_content.replace(" ", "_");
			}
			//

			str_content = str_content.toUpperCase().trim();

			System.out.println("..............:::::::::::::::");

			System.out.println("..............:::::::::::::::");

			System.out.println("..............:::::::::::::::");

			System.out.println("..............:::::::::::::::");

			System.out.println("mo.content:" + str_content);

			System.out.println("spcode :" + deliver_spCode);

			// 处理长号//061116

			smsus.getUserDetail(this.deliver_spCode, str_content);

			// System.out.println("this.deliver_spCode:"+deliver_spCode);

			// 处理长号//061116

			// 记录用户

			String str_time = FormatSysTime.getCurrentTimeA();

			/*
			 * user.setUser_cpn(this.deliver_srcCpn);
			 * 
			 * user.setUser_cpnType(this.src_CpnType);//用户手机号码是明码还是伪码。
			 * 
			 * user.setUser_ismgID(this.deliver_ismgID);
			 * 
			 * user.setUser_corpID(smsus.getUSched_corpID());
			 * 
			 * user.setUser_corpSpcode(smsus.getUSched_spCode());
			 * 
			 * user.setUser_registerTime(str_time);
			 * 
			 * user.setUser_lastVisitTime(str_time);
			 * 
			 * if(user.userIsExist())
			 * 
			 * {
			 * 
			 * user.updateUserVisitTime();
			 * 
			 * }
			 * 
			 * else
			 * 
			 * {
			 * 
			 * user.insertNewUser();
			 * 
			 * 
			 * 
			 * }
			 */// changed at 090804

			// 设置mo具体的信息

			smsmo.setMO_msgId(this.deliver_msgID);

			smsmo.setMO_cpn(this.deliver_srcCpn);

			smsmo.setMo_cpntype(this.src_CpnType);

			smsmo.setMO_spCode(smsus.getUSched_spCode());

			smsmo.setMO_serverID(smsus.getUSched_serverID());

			smsmo.setMO_serverName(smsus.getUSched_gameCode());

			smsmo.setMO_serverAction(smsus.getUSched_actionCode());

			smsmo.setMO_vcpID(smsus.getUSched_vcpID());

			smsmo.setMO_spCode_first(this.deliver_spCode);

			smsmo.setMO_svcType(this.deliver_serverID);

			smsmo.setMO_content(str_content);

			smsmo.setMO_deliverTime(str_time);

			smsmo.setMO_ismgID(deliver_ismgID);

			smsmo.setMO_corpID(smsus.getUSched_corpID());

			smsmo.setMO_linkID(this.linkid);

			System.out.println("::::::::::::::::::::::::");

			System.out.println("::::::::::::::::::::::::");

			System.out.println(smsus.getUSched_spCode());

			System.out.println("linkid is:" + this.linkid);

			System.out.println("::::::::::::::::::::::::");

			System.out.println("::::::::::::::::::::::::");

			System.out.println("::::::::::::::::::::::::");

			// 加入监控模块

			// QueueSingle qs = QueueSingle.getInstance();

			// qs.enq(smsmo);

			// 插入上行日志

			smsmo.insertMOLog();

			// 派发并发送

			String stat = smsTovcp.send_mosms_to_vcp(smsmo);

			System.out.println("派发并发送结果:" + stat);

			// 如果发送不成功,应该进一步处理

			if (stat.equals("-1")) {

				smsmo.insertErrorMOLog();

			}

		}

		catch (Exception e) {

			System.out.println(e.toString());

		}

	}

}
