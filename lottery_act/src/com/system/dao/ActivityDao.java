package com.system.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.system.database.JdbcControl;
import com.system.database.QueryCallBack;
import com.system.model.ActivityModel;
import com.system.util.StringUtil;

public class ActivityDao
{
	public ActivityModel getActivityDao(int flag,String curDate)
	{
		String sql = "SELECT * FROM `tbl_activity` WHERE flag = " + flag
				+ " AND STATUS = 1  AND start_date > '" + curDate
				+ "' AND endt_date < '" + curDate
				+ "' ORDER BY id DESC LIMIT 1";
		
		return (ActivityModel)new JdbcControl().query(sql, new QueryCallBack()
		{
			@Override
			public Object onCallBack(ResultSet rs) throws SQLException
			{
				if(rs.next())
				{
					ActivityModel model = new ActivityModel();
					model.setId(rs.getInt("id"));
					model.setName(StringUtil.getString(rs.getString("name"), ""));
					model.setStartDate(rs.getString("start_date"));
					model.setEndDate(rs.getString("end_date"));
					model.setTicketCount(rs.getInt("ticket_count"));
					model.setDescription(rs.getString("description"));
					model.setFlag(rs.getInt("flag"));
					return model;
				}
				return null;
			}
		});
	}
	
}
