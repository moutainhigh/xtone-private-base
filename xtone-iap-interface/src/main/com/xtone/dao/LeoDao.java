package com.xtone.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.common.util.ConnectionService;

import com.system.database.JdbcControl;
import com.system.database.QueryCallBack;
import com.xtone.iap.ReceiveFromMsg;



public class LeoDao {
  
  static private final Logger LOG = Logger.getLogger(LeoDao.class);
  
	public void findAll()
	{
		Connection con = null;
		PreparedStatement ps = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		
		try {
			con = ConnectionService.getInstance().getConnectionForLocal();
			System.out.println("con:"+con);
			String sql = "SELECT * FROM `tbl_iap_leo_logs`";
			ps = con.prepareStatement(sql);
//			stmt = con.createStatement();
			rs = ps.executeQuery();
//			rs = stmt.executeQuery("SELECT * FROM `tbl_iap_leo_logs`");
//			while (rs.next()) {
//				System.out.println("id:"+rs.getInt(1));
//				
//			}
//			String sql = "SELECT * FROM `tbl_iap_leo_logs`";
			
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
		}finally{
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
		
	}
	
//	public boolean insertMsg(ReceiveFromMsg msg,long time)
//	{
////		String sql = "INSERT INTO `tbl_iap_leo_logs`(ADDTIME,ip,signature,purchaseInfo,environment,pod,signingStatus) VALUE("
////				+ time+",'"+msg.getIp()+"','"+msg.getSignature()+"','"
////				+ msg.getPurchaseInfo()+"','"+msg.getEnvironment()+"','"
////				+ msg.getPod()+"','"+msg.getSigningStatus()+"')";
////		
////		System.out.println("insert success!");
////		return new JdbcControl().execute(sql);
//		String sql = "INSERT INTO `tbl_iap_leo_logs`(ADDTIME,ip,signature,purchaseInfo,environment,pod,signingStatus) VALUE(?,?,?,?,?,?,?)";
//		
//		
//		Connection con = null;
//		PreparedStatement ps = null;
//		
//		try {
//			con = com.xtone.util.DBUtil.getConnection();
//			ps = con.prepareStatement(sql);
//			ps.setLong(1, time);
//			ps.setString(2, msg.getIp());
//			ps.setString(3, msg.getSignature());
//			ps.setString(4, msg.getPurchaseInfo());
//			ps.setString(5, msg.getEnvironment());
//			ps.setString(6, msg.getPod());
//			ps.setString(7, msg.getSigningStatus());
//			return ps.execute();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return false;
//		
//	}
	
	
	public int insertMsg(ReceiveFromMsg msg,long time)
	{
		String sql = "INSERT INTO `tbl_iap_leo_logs`(ADDTIME,ip,signature,purchaseInfo,environment,pod,signingStatus) VALUE(?,?,?,?,?,?,?)";
		
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = ConnectionService.getInstance().getConnectionForLocal();
			ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			ps.setLong(1, time);
			ps.setString(2, msg.getIp());
			ps.setString(3, msg.getSignature());
     		ps.setString(4, msg.getPurchaseInfo());
			ps.setString(5, msg.getEnvironment());
			ps.setString(6, msg.getPod());
			ps.setString(7, msg.getSigningStatus());
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys(); //获取结果  
			int a =0;
			if (rs.next()) {
			   a = rs.getInt(1);//取得ID
			}
			ps.clearParameters();
			return a;
		} catch (Exception e) {
			
			e.printStackTrace();
		}finally{
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
		return 0;
		
	}
	
	public Integer getLastInsertId()
	{
		String sql = "select @@IDENTITY ";
		
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
		String sql = "UPDATE `tbl_iap_leo_logs` SET appleResponse=?,appleResponseTime=? WHERE id = ?";
		
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = ConnectionService.getInstance().getConnectionForLocal();
			ps = con.prepareStatement(sql);
			ps.setString(1, appleMsg);
			ps.setLong(2, time);
			ps.setInt(3, id);
			return ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
		return false;
	}
	
	public boolean updateResponseMsg(int id,String result,String url,long time)
	{
//		String sql = "UPDATE `tbl_iap_leo_logs` SET "
//				+ "syncResponse='"+result+"',syncAddress='"+url+"',syncResponseTime="+time+" WHERE id = "+id;
		
		String sql = "UPDATE `tbl_iap_leo_logs` SET syncResponse=?,syncAddress=?,syncResponseTime=? WHERE id = ?";
		
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = ConnectionService.getInstance().getConnectionForLocal();
			ps = con.prepareStatement(sql);
			ps.setString(1, result);
			ps.setString(2, url);
			ps.setLong(3, time);
			ps.setInt(4, id);
			return ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
		return false;
	}
	
	public static void main(String[] args) {
		LeoDao dao = new LeoDao();
		dao.findAll();
	}
}
