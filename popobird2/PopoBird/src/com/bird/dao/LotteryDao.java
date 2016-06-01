package com.bird.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.common.util.ConnectionService;

import com.bird.bean.LotteryBean;

public class LotteryDao {
	

	/**
	 * 随机获取一个彩票验证码
	 * @return
	 */
	public LotteryBean random(String uid){
		
		String sql = "SELECT  * FROM  `lottery_tickets`  WHERE receiveUserId IS NULL  LIMIT 0, 1";
		
		Connection con = null;
		try {
			ConnectionService service = ConnectionService.getInstance();
			con = service.getConnectionForLocal();
			Statement st = con.createStatement();
		    ResultSet rs =st.executeQuery(sql);
		    rs.last();
		    LotteryBean bean = new LotteryBean(rs.getString("exchangeCode"),rs.getString("passwordCode"),rs.getString("expireTime"));
		    updateLottery(uid, bean.getExchangeCode());
			return bean;
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			if(con!=null){
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
	

	/**充值成功后获取5注彩票
	 * @param uid
	 * @return
	 */
  public List<LotteryBean> obtainLottery(String uid){
		
		String sql = "SELECT  * FROM  `lottery_tickets`  WHERE receiveUserId IS NULL  LIMIT 0, 5";
		
		Connection con = null;
		try {
			ConnectionService service = ConnectionService.getInstance();
			con = service.getConnectionForLocal();
			Statement st = con.createStatement();
		    ResultSet rs =st.executeQuery(sql);
		    List<LotteryBean> list = new ArrayList<>();
		    while(rs.next()){
		    	 LotteryBean bean = new LotteryBean(rs.getString("exchangeCode"),rs.getString("passwordCode"),rs.getString("expireTime"));
				 updateLottery(uid, bean.getExchangeCode());
				 list.add(bean);
		    }
		   
			return list;
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			if(con!=null){
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
  
  
    /**
	 * 用户的彩票
	 * @param receiveUserId
	 * @return
	 */
  public List<LotteryBean> findByReceiveUserId(String receiveUserId){
	    String sql = "SELECT  * FROM  `lottery_tickets`  WHERE receiveUserId ='"+receiveUserId+"'";
		Connection con = null;
		ResultSet rs = null ;
		try {
			ConnectionService service = ConnectionService.getInstance();
			con = service.getConnectionForLocal();
			Statement st = con.createStatement();
			rs=st.executeQuery(sql);
		    List<LotteryBean> list = new ArrayList<>();
		    while(rs.next()){
		    	 LotteryBean bean = new LotteryBean(rs.getString("exchangeCode"),rs.getString("passwordCode"),rs.getString("expireTime"));
				 list.add(bean);
		    }
			return list;
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			
			try {
				if (rs != null) {
					rs.close();
				}
				if (con != null) {
					con.close();
				} 
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return null;
  }
	
	
	
	public void updateLottery(String uid,String exchangeCode){
		//UPDATE `popobird_demo`.`lottery_tickets` SET `receiveUserId` = '456' ,`receiveTime` = '49848' WHERE `exchangeCode` = 'WJYX115985113761'; 
		String sql = "UPDATE `popobird_demo`.`lottery_tickets` SET `receiveUserId` = '"+uid+"' ,`receiveTime` = "+System.currentTimeMillis()+" WHERE `exchangeCode` = '"+exchangeCode+"'";
		PreparedStatement ps = null;
		Connection con = null;
		try {
			ConnectionService service = ConnectionService.getInstance();
			con = service.getConnectionForLocal();
			Statement st = con.createStatement();
			int n = st.executeUpdate(sql);
			//System.out.println("更新:"+exchangeCode);
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			if(con!=null){
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
	

}
