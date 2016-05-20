package com.thirdpay.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.common.util.ConnectionService;
import org.common.util.ThreadPool;


/**
 * 查询cp信息 返回cpInfoBean对象
 * 
 * @author 28518
 *
 */
public class CheckPayInfo {
	private static final Logger LOG = Logger.getLogger(CheckPayInfo.class);
	public static String CheckInfo(String ownOrderId) {
		// TODO Auto-generated method stub
		// ForwardsyncBean forwardsyncBean = new ForwardsyncBean();
		String jsonString = "";
		PreparedStatement ps = null;
		Connection con = null;

		try {
			// DbKey 选择使用的数据库
			con = ConnectionServicethirdpayCount.getInstance().getConnectionForLocal(); // DbKey选择使用config.properties
			ps = con.prepareStatement("SELECT * FROM log_async_generals WHERE para05 = " + ownOrderId);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				jsonString = rs.getString("para04");
			}
//			System.out.println("jsonString = " + jsonString );
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

		return jsonString;
	}
		
	public static void UpdataInfo(String ownOrderId) {
		// TODO Auto-generated method stub
		
//	 	ForwardsyncBean forwardsyncBean = new ForwardsyncBean();
//		String jsonString = "";
		PreparedStatement ps = null;
		Connection con = null;

		try {
			// DbKey 选择使用的数据库
			con = ConnectionService.getInstance().getConnectionForLocal(); // DbKey选择使用config.properties
			ps = con.prepareStatement("UPDATE `log_async_generals` SET para02 = 1 WHERE para01 = " + ownOrderId);
			
			if(!ps.execute()){
				LOG.info("数据状态更新成功为1");
			}
			
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
	
	public static void InsertInfo(String ownOrderId,String notify_url){
//		ThreadPool.mThreadPool.execute(new ForwardLogBean(1002, ownOrderId, notify_url, "success", "result_status",
//				"send_time", "rsp_time", "send_url", "send_header", "body", "rsp_status","rsp_header", "rsp_body", "task_id",
//				"key_type", "id_type"));

//		ThreadPool.mThreadPool.execute(new ForwardLogBean(Contents.forwardsuccess, ownOrderId, notify_url, "200", "success",
//				"send_time", "rsp_time", "send_url", "send_header", "body", "rsp_status","rsp_header", "rsp_body", "task_id",
//				"key_type", "id_type"));

		
		
		
	}
	
	
}
