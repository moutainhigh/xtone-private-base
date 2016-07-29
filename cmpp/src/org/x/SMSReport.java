package org.x;
/*
 * Created on 2006-11-17
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.xiangtone.sql.JdbcControl;
import com.xiangtone.sql.Mysqldb;

public class SMSReport {
	private final static Logger LOG = Logger.getLogger(SMSReport.class);
	String ismgid;
	String msgid;
	String destcpn;
	String spcode;
	String src_cpn;
	String sub_time;
	String done_time;
	String linkId;
	Mysqldb mydb;
	int stat_dev;
	String statDetail;

	public SMSReport() {
		// String file_name = "";
		// file_name = TimeTools.get_month();//取得系统当月时间用于作为日志的文件。

	}

	public void insertReportLog() {

		String sql = "INSERT INTO sms_reportlog(id,ismgid,msg_id,linkid,spcode,dest_cpn,src_cpn,submit_time,done_time,stat,stat_msg) VALUES('',?,?,?,?,?,?,?,?,?,?)";

		Map<Integer, Object> param = new HashMap<Integer, Object>();

		param.put(1, ismgid);
		param.put(2, msgid);
		param.put(3, linkId);
		param.put(4, spcode);
		param.put(5, destcpn);
		param.put(6, src_cpn);
		param.put(7, sub_time);
		param.put(8, done_time);
		param.put(9, stat_dev);
		param.put(10, statDetail);
		new JdbcControl().execute(sql, param);

		String sql2 = "INSERT INTO sms_tempreportlog(id,ismgid,msg_id,linkid,spcode,dest_cpn,src_cpn,submit_time,done_time,stat,stat_msg) VALUES('',?,?,?,?,?,?,?,?,?,?)";

		Map<Integer, Object> param2 = new HashMap<Integer, Object>();

		param2.put(1, ismgid);
		param2.put(2, msgid);
		param2.put(3, linkId);
		param2.put(4, spcode);
		param2.put(5, destcpn);
		param2.put(6, src_cpn);
		param2.put(7, sub_time);
		param2.put(8, done_time);
		param2.put(9, stat_dev);
		param2.put(10, statDetail);
		new JdbcControl().execute(sql2, param2);

		String sql3 = "INSERT INTO companysms_reportlog(id,ismgid,msg_id,linkid,spcode,dest_cpn,src_cpn,submit_time,done_time,stat,stat_msg) VALUES('',?,?,?,?,?,?,?,?,?,?)";

		Map<Integer, Object> param3 = new HashMap<Integer, Object>();

		param3.put(1, ismgid);
		param3.put(2, msgid);
		param3.put(3, linkId);
		param3.put(4, spcode);
		param3.put(5, destcpn);
		param3.put(6, src_cpn);
		param3.put(7, sub_time);
		param3.put(8, done_time);
		param3.put(9, stat_dev);
		param3.put(10, statDetail);
		new JdbcControl().execute(sql3, param3);


	}

	public void setReport_ismgID(String _ismgid) {
		this.ismgid = _ismgid;
	}

	public void setReport_msgID(String _smgid) {
		this.msgid = _smgid;
	}

	public void setReport_destCpn(String _destcpn) {
		this.destcpn = _destcpn;
	}

	public void setReport_spCode(String _spcode) {
		this.spcode = _spcode;
	}

	public void setReport_srcCpn(String _strcpn) {
		this.src_cpn = _strcpn;
	}

	public void setReport_submitTime(String _subtime) {
		this.sub_time = _subtime;
	}

	public void setReport_doneTime(String _donetime) {
		this.done_time = _donetime;
	}

	public void setReport_stat(int _statdev) {
		this.stat_dev = _statdev;
	}

	public void setReport_linkId(String linkId) {
		this.linkId = linkId;
	}

	public void setReport_msg(String reportMsg) {
		this.statDetail = reportMsg;
	}
	/*
	 * str_cpn = str_cpn.substring(2,13); smsreport.setReport_ismgID(_ismgID);
	 * smsreport.setReport_msgID(msg_id.trim());
	 * smsreport.setReport_destCpn(dest_cpn);
	 * smsreport.setReport_spCode(str_spcode);
	 * smsreport.setReport_srcCpn(str_cpn);
	 * smsreport.setReport_submitTime(submit_time);
	 * smsreport.setReport_doneTime(done_time);
	 * smsreport.setReport_stat(stat_dev); smsreport.saveReportLog();
	 * smsreport.insertReportLog();
	 */

}
