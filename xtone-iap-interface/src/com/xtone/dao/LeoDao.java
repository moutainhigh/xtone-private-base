package com.xtone.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.system.database.JdbcControl;
import com.system.database.QueryCallBack;
import com.xtone.iap.ReceiveFromMsg;



public class LeoDao {
	public void findAll()
	{
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		
		try {
			con = com.xtone.util.DBUtil.getConnection();
			System.out.println("con"+con);
			stmt = con.createStatement();
			rs = stmt.executeQuery("SELECT * FROM `tbl_iap_leo_logs`");
			while (rs.next()) {
				System.out.println("id:"+rs.getInt(1));
				
			}
			String sql = "SELECT * FROM `tbl_iap_leo_logs`";
			
			new JdbcControl().query(sql,
					new QueryCallBack() {
						
						@Override
						public Object onCallBack(ResultSet rs) throws SQLException {
							while (rs.next()) {
								System.out.println("id:"+rs.getInt(1));
							}
							return null;
						}
					});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public boolean insertMsg(ReceiveFromMsg msg,long time)
	{
		String sql = "INSERT INTO `tbl_iap_leo_logs`(ADDTIME,ip,signature,purchaseInfo,environment,pod,signingStatus) VALUE("
				+ time+",'"+msg.getIp()+"','"+msg.getSignature()+"','"
				+ msg.getPurchaseInfo()+"','"+msg.getEnvironment()+"','"
				+ msg.getPod()+"','"+msg.getSigningStatus()+"')";
		
		System.out.println("insert success!");
		return new JdbcControl().execute(sql);
	}
	
	public Integer getLastInsertId()
	{
		String sql = "SELECT LAST_INSERT_ID()";
		
		return (Integer)new JdbcControl().query(sql, new QueryCallBack() {
			
			@Override
			public Object onCallBack(ResultSet rs) throws SQLException {
				if(rs.next()){
					return rs.getInt(1);
				}
				return null;
			}
		});
	}
	
	public boolean updateMsg(int id,String appleMsg,long time)
	{
		String sql = "UPDATE `tbl_iap_leo_logs` SET "
				+ "appleResponse='"+appleMsg+"',appleResponseTime="+time+" WHERE id = "+id;
		
		return new JdbcControl().execute(sql);
	}
	
	public boolean updateResponseMsg(int id,String result,String url,long time)
	{
		String sql = "UPDATE `tbl_iap_leo_logs` SET "
				+ "syncResponse='"+result+"',syncAddress='"+url+"',syncResponseTime="+time+" WHERE id = "+id;
		
		return new JdbcControl().execute(sql);
	}
	
	public static void main(String[] args) {
		LeoDao dao = new LeoDao();
		dao.findAll();
	}
}
