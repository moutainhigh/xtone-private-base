package com.thirdpay.bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.common.util.ConfigManager;
import org.common.util.GenerateIdService;

import com.thirdpay.utils.CheckPayInfo;
import com.thirdpay.utils.ConnectionServicethirdpayCount;

/**
 * 1002 数据同步日志 订单号 目标地址 成功判定条件 结果:success,fail,timeout 发起时间 收到rsp时间
 * 发起url(包含queryString) 发起header 发起body rsp-status rsp-header rsp-body task_id
 * appkey or channelId 填入配置的id值 id_type数据库字段对应
 * 
 * @author 28518
 *
 */
public class ForwardLogBean implements Runnable {
	private static final Logger LOG = Logger.getLogger(ForwardLogBean.class);
	private Long id;
	private int status;
	private String ownOrderId;
	private String notify_url;
	private String successCoditions;
	private String result_status;
	private String send_time;
	private String rsp_time;
	private String send_url;
	private String send_header;
	private String body;
	private String rsp_status;
	private String rsp_header;
	private String rsp_body;
	private String task_id;
	private String key_type;
	private String id_type;
	private String forwardString;
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

	public String getOwnOrderId() {
		return ownOrderId;
	}

	public void setOwnOrderId(String ownOrderId) {
		this.ownOrderId = ownOrderId;
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

	public String getResult_status() {
		return result_status;
	}

	public void setResult_status(String result_status) {
		this.result_status = result_status;
	}

	public String getSend_time() {
		return send_time;
	}

	public void setSend_time(String send_time) {
		this.send_time = send_time;
	}

	public String getRsp_time() {
		return rsp_time;
	}

	public void setRsp_time(String rsp_time) {
		this.rsp_time = rsp_time;
	}

	public String getSend_url() {
		return send_url;
	}

	public void setSend_url(String send_url) {
		this.send_url = send_url;
	}

	public String getSend_header() {
		return send_header;
	}

	public void setSend_header(String send_header) {
		this.send_header = send_header;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getRsp_status() {
		return rsp_status;
	}

	public void setRsp_status(String rsp_status) {
		this.rsp_status = rsp_status;
	}

	public String getRsp_header() {
		return rsp_header;
	}

	public void setRsp_header(String rsp_header) {
		this.rsp_header = rsp_header;
	}

	public String getRsp_body() {
		return rsp_body;
	}

	public void setRsp_body(String rsp_body) {
		this.rsp_body = rsp_body;
	}

	public String getTask_id() {
		return task_id;
	}

	public void setTask_id(String task_id) {
		this.task_id = task_id;
	}

	public String getKey_type() {
		return key_type;
	}

	public void setKey_type(String key_type) {
		this.key_type = key_type;
	}

	public String getId_type() {
		return id_type;
	}

	public void setId_type(String id_type) {
		this.id_type = id_type;
	}

	
	
	public String getForwardString() {
		return forwardString;
	}

	public void setForwardString(String forwardString) {
		this.forwardString = forwardString;
	}

	public ForwardLogBean(int status, String ownOrderId, String notify_url, String successCoditions,
			String result_status, String send_time, String rsp_time, String send_url, String send_header, String body,
			String rsp_status, String rsp_header, String rsp_body, String task_id, String key_type, String id_type,String forwardString) {
		super();
		this.status = status;
		this.ownOrderId = ownOrderId;
		this.notify_url = notify_url;
		this.successCoditions = successCoditions;
		this.result_status = result_status;
		this.send_time = send_time;
		this.rsp_time = rsp_time;
		this.send_url = send_url;
		this.send_header = send_header;
		this.body = body;
		this.rsp_status = rsp_status;
		this.rsp_header = rsp_header;
		this.rsp_body = rsp_body;
		this.task_id = task_id;
		this.key_type = key_type;
		this.id_type = id_type;
		this.forwardString =forwardString;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		LOG.info(this.getOwnOrderId() + " 正在插入数据1002.. ");
		
		setId(GenerateIdService.getInstance()
				.generateNew(Integer.parseInt(ConfigManager.getConfigData("server.id").trim()), "clicks", 1));

		if (this.id > 0) {
			PreparedStatement ps = null;
			Connection con = null;
			try {
				// DbKey 选择使用的数据库
				con = ConnectionServicethirdpayCount.getInstance().getConnectionForLocal(); //
				// DbKey选择使用config.properties
				ps = con.prepareStatement(
						"insert into `log_async_generals` (id,logId,para01,para02,para03,para04,para05,para06,para07,para08,para09,para10,para11,para12,para13,para14,para15) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

				int m = 1;
				ps.setLong(m++, this.getId());
				ps.setInt(m++, this.getStatus());
				ps.setString(m++, this.getOwnOrderId());
				ps.setString(m++, this.getNotify_url());
				ps.setString(m++, this.getSuccessCoditions());
				ps.setString(m++, this.getResult_status());
				ps.setString(m++, this.getSend_time());
				ps.setString(m++, this.getRsp_time());
				ps.setString(m++, this.getSend_url());
				ps.setString(m++, this.getSend_header());
				ps.setString(m++, this.getBody());
				ps.setString(m++, this.getRsp_status());
				ps.setString(m++, this.getRsp_body());
				ps.setString(m++, this.getTask_id());
				ps.setString(m++, this.getKey_type());
				ps.setString(m++, this.getId_type());
				ps.setString(m++, this.getForwardString());
				
				if (ps.executeUpdate() == 1) {
					//插入1002成功
					LOG.info(this.getOwnOrderId() + " 数据1002插入成功.. ");
				}

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
