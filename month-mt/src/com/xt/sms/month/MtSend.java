package com.xt.sms.month;

import com.xt.sms.mt.MessageSubmit;
import com.xt.util.DB;
import com.xt.util.DateTimeTool;
import java.io.File;
import java.io.PrintStream;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class MtSend implements Runnable {
	private static Logger logger = Logger.getLogger(MtSend.class);

	private DB db = null;

	private Map<String, Map<String, String>> service_map = new HashMap();
	private Map<String, List<String>> messages_map = new HashMap();
	private Map<String, List<String>> messages_special_map = new HashMap();

	public static void main(String[] args) {
		MtSend ms = new MtSend();
		Thread t = new Thread(ms);
		t.start();
	}

	public void run() {
		while (true) {
			try {
				this.db = new DB();
				int hourOfDay = DateTimeTool.getHourOfDay();
				int min = DateTimeTool.getMinute();
				if ((hourOfDay >= 9) && (hourOfDay < 19)) {
					if ((hourOfDay > 9) || (min >= 30)) {
						loadServicePrice();
						loadMessages();
						loadMessagesSpecial();
						sendRetainedUser();
						sendNewUser();
					} else {
						logger.debug("9:00-9:30 not send...");
					}
				} else{
					logger.debug("out of range 9:00-19:00 not send...");
				}
			} catch (Exception e) {
				logger.error("",e);
			} finally {
				if (this.db != null) {
					this.db.close();
				}
			}
			logger.debug("sleep 60s.");
			try {
				Thread.sleep(60000L);
			} catch (InterruptedException e) {
			}
		}
	}

	private void loadServicePrice() throws Exception {
		if (this.service_map.size() > 0) {
			this.service_map.clear();
		}
		String sql = "select gameid,gamecode,spcode,gamename,price from companygames";
		logger.debug(sql);
		this.db.executeQuery(sql);
		while (this.db.getRs().next()) {
			String gamecode = this.db.getRs().getString("gamecode");
			String gameid = this.db.getRs().getString("gameid");
			String spcode = this.db.getRs().getString("spcode");
			String gamename = this.db.getRs().getString("gamename");
			int price = this.db.getRs().getInt("price") / 100;
			Map map = new HashMap();
			map.put("gameid", gameid);
			map.put("spcode", spcode);
			map.put("gamename", gamename);
			map.put("price", String.valueOf(price));
			map.put("gameid", gameid);
			this.service_map.put(gamecode, map);
		}
		logger.debug("size : " + this.service_map.size() + " service_map : " + this.service_map);
	}

	private void loadMessages() throws Exception {
		if (this.messages_map.size() > 0) {
			this.messages_map.clear();
		}
		String sql = "select serverid,msg from messages";
		logger.debug(sql);
		this.db.executeQuery(sql);
		String msg;
		while (this.db.getRs().next()) {
			String serverid = this.db.getRs().getString("serverid");
			msg = this.db.getRs().getString("msg");
			List list = (List) this.messages_map.get(serverid);
			if (list == null) {
				list = new ArrayList();
				list.add(msg);
				this.messages_map.put(serverid, list);
			} else {
				list.add(msg);
			}
		}
		for (String serverid : this.messages_map.keySet())
			logger.debug("'" + serverid + "' : " + ((List) this.messages_map.get(serverid)).size());
	}

	private void loadMessagesSpecial() throws Exception {
		if (this.messages_special_map.size() > 0) {
			this.messages_special_map.clear();
		}
		String sql = "select serverid,msg from messages_special";
		logger.debug(sql);
		this.db.executeQuery(sql);
		String msg;
		while (this.db.getRs().next()) {
			String serverid = this.db.getRs().getString("serverid");
			msg = this.db.getRs().getString("msg");
			List list = (List) this.messages_special_map.get(serverid);
			if (list == null) {
				list = new ArrayList();
				list.add(msg);
				this.messages_special_map.put(serverid, list);
			} else {
				list.add(msg);
			}
		}
		logger.debug("load messages_special...");
		for (String serverid : this.messages_special_map.keySet())
			logger.debug("'" + serverid + "' : " + ((List) this.messages_special_map.get(serverid)).size());
	}

	private int getSuccCount(String cpn, String serverid) throws Exception {
		int count = 0;

		String sql = "select count(*) as c from sms_platform.sms_mtlogbackup where feetype='03' and reptstat='DELIVRD' and destcpn = '"
				+ cpn + "' and serverid = '" + serverid + "'";
		logger.debug(sql);
		this.db.executeQuery(sql);
		logger.debug(sql);
		if (this.db.getRs().next()) {
			count = this.db.getRs().getInt("c");
		}
		return count;
	}

	private int updateCompanysUser(String id, String msgid, String sendate) throws Exception {
		String sql = "update companys_user set msgid = " + msgid + "," + "sendate = '" + sendate + "',"
				+ "firstsend = '1'," + "sendflag = '1'," + "sendedtime = now() " + "where id = " + id;
		logger.debug(sql);
		return this.db.executeUpdate(sql);
	}

	private int updateCompanysUser(String id, String sendate) throws Exception {
		String sql = "update companys_user set sendate = '" + sendate + "' where id = " + id;
		logger.debug(sql);
		return this.db.executeUpdate(sql);
	}

	private int insertSendRecord(Map<String, String> map) throws Exception {
		String sql = "insert into sendrecord_month(cpn, serviceid, senddate, provid, sendtime, msg) values('"
				+ (String) map.get("cpn") + "', " + "'" + (String) map.get("serviceid") + "', " + "'"
				+ DateTimeTool.getToday() + "', " + "'" + (String) map.get("provid") + "', " + "now(), " + "'"
				+ (String) map.get("msg") + "')";
		logger.debug(sql);
		return this.db.executeUpdate(sql);
	}

	private void getMessage(Map<String, String> map) {
		String serviceid = (String) map.get("serviceid");
		int msgid = Integer.valueOf((String) map.get("msgid")).intValue();
		String msg = "谢谢您的关注，精彩内容稍后奉上，敬请期待";
		List list = (List) this.messages_special_map.get(serviceid);
		if (list == null) {
			list = (List) this.messages_map.get(serviceid);
		}
		if (msgid >= list.size()) {
			msgid = 0;
		}
		msg = (String) list.get(msgid);
		map.put("msgid", String.valueOf(msgid + 1));
		map.put("msg", msg);
	}

	private void getFirstSendMessage(Map<String, String> map) {
		String serviceid = (String) map.get("serviceid");
		int msgid = Integer.valueOf((String) map.get("msgid")).intValue();
		String msg = "谢谢您的关注，精彩内容稍后奉上，敬请期待";
		List list = (List) this.messages_map.get(serviceid);
		if (list != null) {
			if (msgid >= list.size()) {
				msgid = 0;
			}
			msg = (String) list.get(msgid);
		}
		map.put("msgid", String.valueOf(msgid + 1));
		map.put("msg", msg);
	}

	private void filterSendList(List<Map<String, String>> list) {
		long millis = System.currentTimeMillis();
		int enough_count = 0;
		int not_enough_count = 0;
		int send_mt_count = 0;
		int error_count = 0;
		int send_mt_tmp_count = 0;
		for (int i = 0; i < list.size(); i++) {
			Map map = (Map) list.get(i);
			String id = (String) map.get("id");
			String company = (String) map.get("company");
			String cpn = (String) map.get("cpn");
			String serviceid = (String) map.get("serviceid");

			logger.debug("RetainedUser filter " + (i + 1) + " count.");
			try {
				Map service = (Map) this.service_map.get(serviceid);
				if (service != null) {
					String serverid = (String) service.get("gameid");

					int succ_count = getSuccCount(cpn, serverid);
					int price = Integer.valueOf((String) service.get("price")).intValue();
					int fee_count = price / 2 + 1;

					if (succ_count < fee_count) {
						logger.debug("price : " + price + " fee_count : " + fee_count + " succ_count : "
								+ succ_count + " not_enough.");
						not_enough_count++;
						getMessage(map);
						updateCompanysUser(id, (String) map.get("msgid"), DateTimeTool.getTomorrow());
						insertSendRecord(map);
						String[] _msg = splitConent((String) map.get("msg"));
						sendMT(map, _msg);
						send_mt_count += _msg.length;
						send_mt_tmp_count += _msg.length;
						if (send_mt_tmp_count >= 18) {
							long cur_millis = System.currentTimeMillis();
							long send_millis = cur_millis - millis;
							logger.debug("RetainedUser send 18 time " + send_millis + " ms.");
							if (send_millis < 1000L) {
								long sleep_millis = 1000L - send_millis;
								logger.debug("RetainedUser sleep " + sleep_millis + " ms.");
								Thread.sleep(sleep_millis);
							}
							send_mt_tmp_count = 0;
							millis = System.currentTimeMillis();
						}
					} else {
						logger.debug("price : " + price + " fee_count : " + fee_count + " succ_count : "
								+ succ_count + " enough.");
						enough_count++;
						updateCompanysUser(id, DateTimeTool.getNextMonthFirstday());
					}
				} else {
					error_count++;
					logger.warn("error service : " + id + ", " + company + ", " + cpn + ", " + serviceid);
				}
			} catch (Exception e) {
				error_count++;
				logger.debug("error service : " + id + ", " + company + ", " + cpn + ", " + serviceid);
				logger.error(e.getMessage(), e);
			}
		}
		logger.debug("total :  " + list.size());
		logger.debug("enough_count : " + enough_count);
		logger.debug("send_mt_count : " + send_mt_count);
		logger.debug("not_enough_count : " + not_enough_count);
		logger.debug("error_count : " + error_count);
	}

	private void sendMT(Map<String, String> map, String[] _msg) {
		String serviceid = (String) map.get("serviceid");
		Map service = (Map) this.service_map.get(serviceid);
		if (service == null) {
			return;
		}
		String destcpn = (String) map.get("cpn");
		String feecpn = (String) map.get("cpn");
		int temp_cpntype = 0;
		String cpnlinkid = "";

		String serverID = (String) service.get("gameid");
		String vcpID = "1";

		String provID = "01";

		String sp_code = (String) service.get("spcode");
		String str_feeType = "0";
		String mediaType = "1";
		String delivery = "0";

		String gameCode = serviceid.substring(1);
		String _msgId = "";
		for (int i = 0; i < _msg.length; i++) {
			MessageSubmit ms = new MessageSubmit();
			ms.setDestCpn(destcpn);
			ms.setFeeCpn(feecpn);
			ms.setCpnType(temp_cpntype);
			ms.setLinkId(cpnlinkid);
			ms.setContent(_msg[i]);
			ms.setServerID(serverID);
			ms.setVcpID(vcpID);
			ms.setProvID(provID);
			ms.setSpCode(sp_code);
			ms.setFeeType(str_feeType);

			ms.setMediaType(mediaType);
			ms.setDelivery(delivery);
			ms.setGameCode(gameCode);
			ms.setMsgId(_msgId);
			ms.setSendTime(DateTimeTool.getCurrentTime());

			logger.debug("destCpn : '" + ms.destCpn + "', " + "feeCpn : '" + ms.feeCpn + "', " + "cpnType : '"
					+ ms.cpntype + "', " + "linkid : '" + ms.linkId + "', " + "content : '" + ms.content + "', "
					+ "serverID : '" + ms.serverID + "', " + "vcpID : '" + ms.vcpID + "', " + "provID : '" + ms.provID
					+ "', " + "spCode : '" + ms.spCode + "', " + "feeType : '" + ms.feeType + "', " + "mediaType : '"
					+ ms.mediaType + "', " + "delivery : '" + ms.delivery + "', " + "gameCode : '" + ms.gameCode + "', "
					+ "msgId : '" + ms.msgId + "'");
			ms.sendResultToSmsPlatform();
		}
	}

	private String[] splitConent(String smContent) {
		int nLen = smContent.length();
		int n1 = (nLen + 69) / 70;
		System.out.println("短信条数:" + n1);
		String[] str1 = new String[n1];
		for (int j = 0; j < n1; j++) {
			int j1 = j * 70;
			int j2 = (j + 1) * 70;
			if (j < n1 - 1)
				str1[j] = smContent.substring(j1, j2);
			else {
				str1[j] = smContent.substring(j1);
			}
		}
		return str1;
	}

	private void sendRetainedUser() throws Exception {
		int id = 0;
		List sendList = new ArrayList();
		do {
			sendList.clear();
			String sql = "select id,company,cpn,serviceid,msgid,provid from companys_user where (state = '1' or state='3') and firstsend = 1 and sendate <= '"
					+ DateTimeTool.getToday() + "' " + "and (UNIX_TIMESTAMP(now())-UNIX_TIMESTAMP(addate))>=3600*72 "
					+ "and id > " + id + " " + "order by id limit 5000";
			this.db.executeQuery(sql);
			logger.debug("sql==" + sql);

			while (this.db.getRs().next()) {
				id = this.db.getRs().getInt("id");
				String company = this.db.getRs().getString("company");
				String cpn = this.db.getRs().getString("cpn");
				String serviceid = this.db.getRs().getString("serviceid");
				int msgid = this.db.getRs().getInt("msgid");
				String provid = this.db.getRs().getString("provid");

				Map map = new HashMap();
				map.put("id", String.valueOf(id));
				map.put("company", company);
				map.put("cpn", cpn);
				map.put("serviceid", serviceid);
				map.put("msgid", String.valueOf(msgid));
				map.put("provid", provid);

				sendList.add(map);
			}

			logger.debug("retained sendList : " + sendList.size());

			filterSendList(sendList);
		} while (sendList.size() > 0);
	}

	private void sendNewUser() throws Exception {
		List sendList = new ArrayList();

		String sql = "select id,company,cpn,serviceid,msgid,provid from companys_user where (state = '1' or state='3') and firstsend = 0 order by id limit 1000";

		this.db.executeQuery(sql);
		logger.debug("sql==" + sql);

		while (this.db.getRs().next()) {
			int id = this.db.getRs().getInt("id");
			String company = this.db.getRs().getString("company");
			String cpn = this.db.getRs().getString("cpn");
			String serviceid = this.db.getRs().getString("serviceid");
			int msgid = this.db.getRs().getInt("msgid");
			String provid = this.db.getRs().getString("provid");

			Map map = new HashMap();
			map.put("id", String.valueOf(id));
			map.put("company", company);
			map.put("cpn", cpn);
			map.put("serviceid", serviceid);
			map.put("msgid", String.valueOf(msgid));
			map.put("provid", provid);

			sendList.add(map);
		}

		logger.debug("new user sendList : " + sendList.size());

		long millis = System.currentTimeMillis();
		int send_mt_count = 0;
		int error_count = 0;
		int send_mt_tmp_count = 0;

		for (int i = 0; i < sendList.size(); i++) {
			Map map = (Map) sendList.get(i);
			String id = (String) map.get("id");
			String company = (String) map.get("company");
			String cpn = (String) map.get("cpn");
			String serviceid = (String) map.get("serviceid");
			logger.debug("NewUser send " + (i + 1) + " count.");
			try {
				Map service = (Map) this.service_map.get(serviceid);
				if (service != null) {
					getFirstSendMessage(map);
					updateCompanysUser(id, (String) map.get("msgid"), DateTimeTool.getThreeDaysLater());
					insertSendRecord(map);
					String[] _msg = splitConent((String) map.get("msg"));
					sendMT(map, _msg);
					send_mt_count += _msg.length;
					send_mt_tmp_count += _msg.length;
					if (send_mt_tmp_count >= 18) {
						long cur_millis = System.currentTimeMillis();
						long send_millis = cur_millis - millis;
						logger.debug("NewUser send 18 time " + send_millis + " ms.");
						if (send_millis < 1000L) {
							long sleep_millis = 1000L - send_millis;
							logger.debug("NewUser sleep " + sleep_millis + " ms.");
							Thread.sleep(sleep_millis);
						}
						send_mt_tmp_count = 0;
						millis = System.currentTimeMillis();
					}
				} else {
					error_count++;
					logger.debug("error service : " + id + ", " + company + ", " + cpn + ", " + serviceid);
				}
			} catch (Exception e) {
				error_count++;
				logger.debug("error service : " + id + ", " + company + ", " + cpn + ", " + serviceid);
				logger.error(e.getMessage(), e);
			}
		}
		logger.debug("total :  " + sendList.size());
		logger.debug("send_mt_count : " + send_mt_count);
		logger.debug("error_count : " + error_count);
	}
}