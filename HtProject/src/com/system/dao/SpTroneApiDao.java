package com.system.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.system.database.JdbcControl;
import com.system.database.QueryCallBack;
import com.system.model.SpTroneApiModel;
import com.system.util.StringUtil;

public class SpTroneApiDao
{
	@SuppressWarnings("unchecked")
	public List<SpTroneApiModel> loadSpTroneApi()
	{
		String sql = "select * from daily_config.tbl_sp_trone_api";
		
		return (List<SpTroneApiModel>)new JdbcControl().query(sql, new QueryCallBack()
		{
			@Override
			public Object onCallBack(ResultSet rs) throws SQLException
			{
				List<SpTroneApiModel> list = new ArrayList<SpTroneApiModel>();
				
				SpTroneApiModel model = null;
				
				while(rs.next())
				{
					model = new SpTroneApiModel();
					
					model.setId(rs.getInt("id"));
					model.setName(StringUtil.getString(rs.getString("name"), ""));
					model.setMatchField(rs.getInt("match_field"));
					model.setMatchKeyword(StringUtil.getString(rs.getString("match_keyword"), ""));
					model.setApiFields(StringUtil.getString(rs.getString("api_fields"), ""));
					model.setLoaclMatch(rs.getInt("locate_match"));
					model.setDayLimit(rs.getFloat("day_limit"));
					model.setMonthLimit(rs.getFloat("month_limit"));
					model.setCurDayLimit(rs.getFloat("cur_day_limit"));
					model.setCurMonthLimit(rs.getFloat("cur_month_limit"));
					model.setUserDayLimit(rs.getFloat("user_day_limit"));
					model.setUserMonthLimit(rs.getFloat("user_month_limit"));
					
					list.add(model);
				}
				
				return list;
			}
		});
	}
}
