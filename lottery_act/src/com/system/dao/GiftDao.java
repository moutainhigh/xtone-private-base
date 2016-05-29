package com.system.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.system.database.JdbcControl;
import com.system.database.QueryCallBack;
import com.system.model.LotteryTicketModel;

public class GiftDao
{
	@SuppressWarnings("unchecked")
	public List<LotteryTicketModel> getUnUsedTicked(int count)
	{
		String sql = "SELECT * FROM `tbl_lottery_ticket` WHERE STATUS = 0 ORDER BY id ASC LIMIT " + count;
		
		return (List<LotteryTicketModel>)new JdbcControl().query(sql, new QueryCallBack()
		{
			@Override
			public Object onCallBack(ResultSet rs) throws SQLException
			{
				List<LotteryTicketModel> list = new ArrayList<LotteryTicketModel>();
				
				while(rs.next())
				{
					LotteryTicketModel model = new LotteryTicketModel();
					
					model.setId(rs.getInt("id"));
					model.setExchangeCode(rs.getString("exchange_code"));
					model.setPwdCode(rs.getString("pwd_code"));
					model.setExpireTime(rs.getString("expire_time"));
					model.setStatus(rs.getInt("status"));
					
					list.add(model);
				}
				
				return list;
			}
		});
	}
	
	public void updateTicketStatus(List<Integer> list,int status)
	{
		String ids = "";
		
		for(int id : list)
		{
			ids += id + ",";
		}
		
		if(ids.length()>0)
			ids = ids.substring(0, ids.length()-1);
		
		String sql = "update tbl_lottery_ticeket set status = " + status + " where id in(" + ids + ")";
		
		new JdbcControl().execute(sql);
	}
}
