package com.thirdpay.domain;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.common.util.ConfigManager;
import org.common.util.ConnectionService;
import org.common.util.GenerateIdService;

import com.thirdpay.utils.ConnectionServicethirdpayCount;

public class PayOperateBean implements Runnable {

	private Long id;
	private int logId; // 价格，单位人民币，分
	private String payOperateStatus; // 支付操作状态
	private String Appkey;
	private String op_notifyData;
	private String payParams;
	private String ownOrderid;

	public PayOperateBean(Long timeid ,int logId, String payOperateStatus, String Appkey, String op_notifyData, String payParams,
			String ownOrderid) {
		super();
		this.id=timeid;
		this.logId = logId;
		this.payOperateStatus = payOperateStatus;
		this.Appkey = Appkey;
		this.op_notifyData = op_notifyData;
		this.payParams = payParams;
		this.ownOrderid = ownOrderid;
	}

	public String getOp_notifyData() {
		return op_notifyData;
	}

	public void setOp_notifyData(String op_notifyData) {
		this.op_notifyData = op_notifyData;
	}

	public String getAppkey() {
		return Appkey;
	}

	public void setAppkey(String appkey) {
		Appkey = appkey;
	}

	public int getLogId() {
		return logId;
	}

	public void setLogId(int logId) {
		this.logId = logId;
	}

	public String getPayOperateStatus() {
		return payOperateStatus;
	}

	public void setPayOperateStatus(String payOperateStatus) {
		this.payOperateStatus = payOperateStatus;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPayParams() {
		return payParams;
	}

	public void setPayParams(String payParams) {
		this.payParams = payParams;
	}

	public String getOwnOrderid() {
		return ownOrderid;
	}

	public void setOwnOrderid(String ownOrderid) {
		this.ownOrderid = ownOrderid;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	
		if (this.id > 0) {
			PreparedStatement ps = null;
			Connection con = null;
			try {
				// DbKey 选择使用的数据库
				con = ConnectionServicethirdpayCount.getInstance().getConnectionForLocal(); // DbKey选择使用config.properties
				ps = con.prepareStatement(
						"insert into `log_async_generals` (id,logId,para01,para02,para03,para04,para05) values (?,?,?,?,?,?,?)");
				int m = 1;
				ps.setLong(m++, this.getId());
				ps.setInt(m++, this.getLogId());
				ps.setString(m++, this.getPayOperateStatus());
				ps.setString(m++, "appkey = " + this.getAppkey());
				ps.setString(m++, this.getOp_notifyData());
				ps.setString(m++, this.getPayParams());
				ps.setString(m++, this.getOwnOrderid());
				ps.executeUpdate();

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {

				if (ps != null) {
					try {
						ps.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				if (con != null) {
					try {
						con.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}

	}

}
