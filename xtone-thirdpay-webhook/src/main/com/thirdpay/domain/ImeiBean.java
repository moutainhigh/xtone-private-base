package com.thirdpay.domain;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.common.util.ConnectionService;


public class ImeiBean implements Runnable{
	String imei ;
	Long timeid;
	String recentUseTime;
	
	
	public ImeiBean(String imei, Long timeid, String recentUseTime) {
		super();
		this.imei = imei;
		this.timeid = timeid;
		this.recentUseTime = recentUseTime;
	}
	
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public Long getTimeid() {
		return timeid;
	}
	public void setTimeid(Long timeid) {
		this.timeid = timeid;
	}
	public String getRecentUseTime() {
		return recentUseTime;
	}
	public void setRecentUseTime(String recentUseTime) {
		this.recentUseTime = recentUseTime;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub

	
		if (this.timeid > 0) {
			String countid = "";
			PreparedStatement ps = null;
			Connection con = null;
			try {
				// DbKey 选择使用的数据库
				con = ConnectionService.getInstance().getConnectionForLocal(); // DbKey选择使用config.properties
				ps = con.prepareStatement(
						"SELECT COUNT(id) FROM `tbl_imei_users` WHERE id = "+getImei());
				
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					countid = rs.getString("count(id)");
					System.out.println("countid = " +countid);
				}
				
				
				if("0".equals(countid)){
					//查询到id数据不存在-插入数据
					System.out.println("查询到id数据不存在-插入数据");
					ps = con.prepareStatement(
							"insert into `tbl_imei_users` (id,addTime,recentUseTime) values (?,?,?)");
					int m = 1;
					ps.setString(m++, this.getImei());
					ps.setLong(m++, this.getTimeid());
					ps.setString(m++, this.getRecentUseTime());
					
					int i = ps.executeUpdate();
					
				}else{
					//查询到id数据存在-更新recentUseTime
					System.out.println("查询到id数据存在-更新recentUseTime");
					//UPDATE tbl_imei_users SET recentUseTime = 111 WHERE id = 123
					ps = con.prepareStatement(
							"UPDATE `tbl_imei_users` SET recentUseTime = '"+this.getRecentUseTime()+"' WHERE id = "+this.getImei());
					int i = ps.executeUpdate();
					
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

	}

}
