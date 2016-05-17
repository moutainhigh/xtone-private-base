package com.thirdpay.domain;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.common.util.ConfigManager;
import org.common.util.ConnectionService;
import org.common.util.GenerateIdService;

import com.thirdpay.utils.ConnectionServicethirdpayCount;

/**
 * 订单号状态0表示等待同步；1表示同步成功 计划下次处理时间，毫秒数 已经处理次数 目标地址 成功判定条件 appkey or channelId
 * 填入配置的id值 id_type数据库字段对应
 */
public class ForwardsyncBean implements Runnable {

	private Long id;
	private int status;
	private String orderId;
	private String sync_status;
	private String next_time;
	private String sendCount;
	private String notify_url;
	private String successCoditions;
	private String appkey;
	private String id_type;

	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getSync_status() {
		return sync_status;
	}

	public void setSync_status(String sync_status) {
		this.sync_status = sync_status;
	}

	public String getNext_time() {
		return next_time;
	}

	public void setNext_time(String next_time) {
		this.next_time = next_time;
	}

	public String getSendCount() {
		return sendCount;
	}

	public void setSendCount(String sendCount) {
		this.sendCount = sendCount;
	}

	public String getNotify_url() {
		return notify_url;
	}

	public void setNotify_url(String notify_url) {
		this.notify_url = notify_url;
	}

	public String getSuccessCoditions() {
		return successCoditions;
	}

	public void setSuccessCoditions(String successCoditions) {
		this.successCoditions = successCoditions;
	}

	public String getAppkey() {
		return appkey;
	}

	public void setAppkey(String appkey) {
		this.appkey = appkey;
	}

	public String getId_type() {
		return id_type;
	}

	public void setId_type(String id_type) {
		this.id_type = id_type;
	}

	public ForwardsyncBean(int status, String orderId, String sync_status, String next_time, String sendCount,
			String notify_url, String successCoditions, String appkey, String id_type) {
		super();
		this.status = status;
		this.orderId = orderId;
		this.sync_status = sync_status;
		this.next_time = next_time;
		this.sendCount = sendCount;
		this.notify_url = notify_url;
		this.successCoditions = successCoditions;
		this.appkey = appkey;
		this.id_type = id_type;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

		setId(GenerateIdService.getInstance()
				.generateNew(Integer.parseInt(ConfigManager.getConfigData("server.id").trim()), "clicks", 1));

		if (this.id > 0) {
			PreparedStatement ps = null;
			Connection con = null;
			try {
				// DbKey 选择使用的数据库
				con = ConnectionServicethirdpayCount.getInstance().getConnectionForLocal(); // DbKey选择使用config.properties
				ps = con.prepareStatement("insert into `log_async_generals` (id,logId,para01,para02,para03,para04,para05,para06,para07,para08) values (?,?,?,?,?,?,?,?,?,?)");
				
				int m = 1;
				ps.setLong(m++, this.getId());
				ps.setInt(m++, this.getStatus());
				ps.setString(m++, this.getOrderId());
				ps.setString(m++, this.getSync_status());
				ps.setString(m++, this.getNext_time());
				ps.setString(m++, this.getSendCount());
				ps.setString(m++, this.getNotify_url());
				ps.setString(m++, this.getSuccessCoditions());
				ps.setString(m++, this.getAppkey());
				ps.setString(m++, this.getId_type());
				ps.executeUpdate();

			} catch (Exception e) {
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
