package org.x;
/*
 * Created on 2006-11-17
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

/**
 * @author Administrator
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
import java.util.*;

import org.apache.log4j.Logger;

import java.io.*;

import com.xiangtone.sql.Mysqldb;
import tempreportlog.*;

public class SMSReport {
	private static Logger logger = Logger.getLogger(SMSReport.class);
	String ismgId;
	String msgId;
	String destCpn;
	String spCode;
	String srcCpn;
	String subTime;
	String doneTime;
	String linkId;
	Mysqldb mydb;
	int statDev;
	String statDetail;

	// FileWriter fw = null;
	// StringBuffer sb;
	public SMSReport() {
		// String file_name = "";
		// file_name = TimeTools.get_month();//取得系统当月时间用于作为日志的文件。
		mydb = new Mysqldb();
		/*
		 * try{
		 * 
		 * //fw = new FileWriter("/home/smsapp/smsapp/CMPP/log/" +
		 * file_name+".txt",true); //sb = new StringBuffer(); }catch(IOException
		 * e){ System.out.println(e.toString()); }
		 */

	}

	public void insertReportLog() {

		String strSql = "insert into sms_reportlog(id,ismgId,msg_id,linkid,spcode,dest_cpn,src_cpn,submit_time,done_time,stat,stat_msg) values('','"
				+ ismgId + "','" + msgId + "','" + linkId + "','" + spCode + "','" + destCpn + "','" + srcCpn + "','"
				+ subTime + "','" + doneTime + "','" + statDev + "','" + statDetail + "')";
		String tempstrSql = "insert into sms_tempreportlog(id,ismgId,msg_id,linkid,spcode,dest_cpn,src_cpn,submit_time,done_time,stat,stat_msg) values('','"
				+ ismgId + "','" + msgId + "','" + linkId + "','" + spCode + "','" + destCpn + "','" + srcCpn + "','"
				+ subTime + "','" + doneTime + "','" + statDev + "','" + statDetail + "')";
		String companystrSql = "insert into companysms_reportlog(id,ismgId,msg_id,linkid,spcode,dest_cpn,src_cpn,submit_time,done_time,stat,stat_msg) values('','"
				+ ismgId + "','" + msgId + "','" + linkId + "','" + spCode + "','" + destCpn + "','" + srcCpn + "','"
				+ subTime + "','" + doneTime + "','" + statDev + "','" + statDetail + "')";

		/*
		 * sb.append("ismg:" + ismgId + "."); sb.append("msgid:" + msgid + ".");
		 * sb.append("destcpn:" + destcpn + "."); sb.append("spcode:" + spcode +
		 * "."); sb.append("srcpn:" + src_cpn + "."); sb.append("subtime:" +
		 * sub_time + "."); sb.append("donetime:" + done_time + ".");
		 * sb.append("stat:" + stat_dev + ".");
		 */
		try {
			logger.debug(strSql);
			logger.debug(tempstrSql);
			logger.debug(companystrSql);
			mydb.execUpdate(strSql);
			mydb.execUpdate(tempstrSql);
			mydb.execUpdate(companystrSql);
			// fw.write(sb.toString());tempstr_sql
			// fw.flush();
			// fw.close();
			mydb.close();
			// ReportHandle tempReportLog = new ReportHandle();
			// tempReportLog.logReport(str_sql);
		} catch (Exception e) {
			logger.error("insertReportLog", e);
			try {
				mydb.close();
			} catch (Exception e1) {
			}

		}

	}

	public void setIsmgId(String ismgId) {
		this.ismgId = ismgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

	public void setDestCpn(String destCpn) {
		this.destCpn = destCpn;
	}

	public void setSpCode(String spCode) {
		this.spCode = spCode;
	}

	public void setSrcCpn(String srcCpn) {
		this.srcCpn = srcCpn;
	}

	public void setSubTime(String subTime) {
		this.subTime = subTime;
	}

	public void setDoneTime(String doneTime) {
		this.doneTime = doneTime;
	}

	public void setLinkId(String linkId) {
		this.linkId = linkId;
	}

	public void setMydb(Mysqldb mydb) {
		this.mydb = mydb;
	}

	public void setStatDev(int statDev) {
		this.statDev = statDev;
	}

	public void setStatDetail(String statDetail) {
		this.statDetail = statDetail;
	}

	public void setReportMsg(String reportMsg) {
		this.statDetail = reportMsg;
	}
	/*
	 * str_cpn = str_cpn.substring(2,13); smsreport.setReport_ismgId(_ismgId);
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
