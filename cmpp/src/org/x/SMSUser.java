package org.x;
/**
*Copyright 2003 Xiamen Xiangtone Co. Ltd.
*All right reserved.
*/

import java.io.*;
import java.util.*;
import java.sql.*;

import com.xiangtone.sql.JdbcControl;
import com.xiangtone.sql.Mysqldb;
import com.xiangtone.sql.QueryCallBack;
import com.xiangtone.util.FormatSysTime;

/**
 * A System Time format
 *
 */
public class SMSUser {
	/**
	*
	*
	*/
	public String cpn;
	public int cpn_Type;
	public String ismgID;
	public String registerTime;
	public String lastVisitTime;
	public int creditMoney;
	public String corp_id;
	public String corp_spcode;

	private String strSql = "";
	private ResultSet rs = null;

	/**
	 * method of get and set
	 *
	 */
	public String getUser_cpn() {
		return cpn;
	}

	public String getUser_ismgID() {
		return ismgID;
	}

	public String getUser_register_time() {
		return registerTime;
	}

	public String getUser_lastVisitTime() {
		return lastVisitTime;
	}

	public int getUser_creditMoney() {
		return creditMoney;
	}

	public String getUser_corpID() {
		return corp_id;
	}

	public String getUser_corpSpcode() {
		return corp_spcode;
	}

	public void setUser_cpn(String strCpn) {
		this.cpn = strCpn;
	}

	public void setUser_cpnType(int cpn_type) {
		this.cpn_Type = cpn_type;
	}

	public void setUser_ismgID(String strIsmgID) {
		this.ismgID = strIsmgID;
	}

	public void setUser_registerTime(String register_time) {
		this.registerTime = register_time;
	}

	public void setUser_lastVisitTime(String _last_visit_time) {
		this.lastVisitTime = _last_visit_time;
	}

	public void setUser_creditMoney(int credit) {
		this.creditMoney = credit;
	}

	public void setUser_corpID(String strCorpid) {
		this.corp_id = strCorpid;
	}

	public void setUser_corpSpcode(String strCorpSpcode) {
		this.corp_spcode = strCorpSpcode;
	}

	public SMSUser() {
	}

	/**
	 * method one insert user log
	 */
	public void insertNewUser() {
		String sql = "INSERT INTO sms_user SET cpn=?,ismgid=?,corp_id=?,corp_spcode=?,register_time=?,last_visit_time=?,visit_times=1";

		Map<Integer, Object> param = new HashMap<Integer, Object>();

		param.put(1, this.cpn);
		param.put(2, this.ismgID);
		param.put(3, this.corp_id);
		param.put(4, this.corp_spcode);
		param.put(5, this.registerTime);
		param.put(6, this.lastVisitTime);

		new JdbcControl().execute(sql, param);
	}

	/**
	 * 判断该用户是否已经存在
	 *
	 */
	public boolean userIsExist() {
		String sql = "SELECT * FROM sms_user WHERE cpn='" + cpn + "'";
		return (Boolean) new JdbcControl().query(sql, new QueryCallBack() {

			@Override
			public Object onCallBack(ResultSet rs) throws SQLException {
				// TODO Auto-generated method stub
				if (rs.next()) {
					return true;
				}
				return false;
			}
		});
	}

	/**
	 * 更新
	 *
	 */
	public void updateUserVisitTime(String _cpn) {
		String sql = "update sms_user set last_visit_time='" + FormatSysTime.getCurrentTimeA()
				+ "',visit_times =visit_times +1 where cpn =?";

		Map<Integer, Object> param = new HashMap<Integer, Object>();

		param.put(1, _cpn);

		new JdbcControl().execute(sql, param);

	}

	public void updateUserVisitTime() {
		String sql = "update sms_user set last_visit_time='" + FormatSysTime.getCurrentTimeA()
				+ "',visit_times =visit_times +1 where cpn =?";

		Map<Integer, Object> param = new HashMap<Integer, Object>();

		param.put(1, this.cpn);

		new JdbcControl().execute(sql, param);

	}

	/**
	 * 加钱
	 *
	 */
	public boolean increaseCreditMoney(String cpn, int money) {
		String sql = " update sms_user set creditmoney=creditmoney+? where cpn =?";

		Map<Integer, Object> param = new HashMap<Integer, Object>();

		param.put(1, money);
		param.put(2, cpn);

		return new JdbcControl().execute(sql, param);
	}

	/**
	 * 减钱
	 *
	 */
	public boolean decreaseCreditMoney(String cpn, int money) {
		String sql = " update sms_user set creditmoney=creditmoney-? where cpn =?";

		Map<Integer, Object> param = new HashMap<Integer, Object>();

		param.put(1, money);
		param.put(2, cpn);

		return new JdbcControl().execute(sql, param);
	}

	/**
	 * 取用户余额
	 *
	 *
	 */
	public int getCreditMoney(String cpn) {
		String sql = "SELECT * FROM sms_user WHERE cpn='" + cpn + "'";
		return (Integer) new JdbcControl().query(sql, new QueryCallBack() {

			@Override
			public Object onCallBack(ResultSet rs) throws SQLException {
				// TODO Auto-generated method stub
				if (rs.next()) {
					return rs.getInt("creditmoney");
				}
				return 0;
			}
		});
	}

}