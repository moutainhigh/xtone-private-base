package com.system.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.system.database.JdbcControl;
import com.system.database.QueryCallBack;
import com.system.model.UserLotteryModel;
import com.system.util.StringUtil;

public class UserLotteryDao
{
	/**
	 * 获取用户所有的过的活动与彩票
	 * @param userId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<UserLotteryModel> loadUserLotteryByUserId(int userId)
	{
		String sql = "SELECT a.*,c.*,d.* FROM `tbl_user_lottery_list` a  LEFT JOIN tbl_user b ON a.`user_id` = b.`id` LEFT JOIN tbl_lottery_ticket c ON a.`ticket_id` = c.`id` LEFT JOIN tbl_activity d ON a.`activity_id`= d.`id` WHERE b.id = " + userId + " ORDER BY a.`add_time` DESC,a.activity_id desc,c.`exchange_code` ASC";
		
		return (List<UserLotteryModel>)new JdbcControl().query(sql, new QueryCallBack()
		{
			@Override
			public Object onCallBack(ResultSet rs) throws SQLException
			{
				List<UserLotteryModel> list = new ArrayList<UserLotteryModel>();
				
				while(rs.next())
				{
					UserLotteryModel model = new UserLotteryModel();
					
					model.setActEndDate(rs.getString("end_date"));
					model.setActStartDate(rs.getString("start_date"));
					model.setActivityId(rs.getInt("activity_id"));
					model.setActName(StringUtil.getString(rs.getString("name"), ""));
					model.setAddTime(rs.getString("add_time"));
					model.setExchangeCode(rs.getString("exchange_code"));
					model.setPwdCode(rs.getString("pwd_code"));
					model.setExpireTime(rs.getString("expire_time"));
					model.setUserId(rs.getInt("user_id"));
					model.setTicketId(rs.getInt("ticket_id"));
					
					list.add(model);
				}
				
				return list;
			}
		});
	}
	
	/**
	 * 用户是否参与过活动
	 * @param userId
	 * @param activityId
	 * @return
	 */
	public boolean isUserAddActivity(int userId,int activityId)
	{
		String sql = "select count(*) from tbl_user_lottery_list where user_id = " + userId + " and activity_id = " + activityId;
		
		return (Boolean)new JdbcControl().query(sql, new QueryCallBack()
		{
			@Override
			public Object onCallBack(ResultSet rs) throws SQLException
			{
				if(rs.next())
					return rs.getInt(1) > 0;
					
				return false;
			}
		});
	}
	
	
	public void addUserLotteryCode(int userId,int activityId,List<Integer> ticketList)
	{
		if(ticketList==null || ticketList.isEmpty())
			return;
		
		String sql = "insert into tbl_user_lottery_list(user_id,ticket_id,activity_id,add_time) values";
		
		for(int ticket : ticketList)
		{
			sql += "(" + userId + "," + ticket + "," + activityId + ",now()),";
		}
		
		sql = sql.substring(0,sql.length()-1);
		
		new JdbcControl().execute(sql);
	}
	
}
