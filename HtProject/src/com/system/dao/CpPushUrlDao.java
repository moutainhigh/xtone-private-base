package com.system.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.system.database.JdbcControl;
import com.system.database.QueryCallBack;
import com.system.model.CpPushUrlModel;
import com.system.util.StringUtil;

public class CpPushUrlDao
{
	@SuppressWarnings("unchecked")
	public List<CpPushUrlModel> loadcpPushUrl()
	{
		String sql = "select * from daily_config.tbl_cp_push_url";
		return (List<CpPushUrlModel>)new JdbcControl().query(sql, new QueryCallBack()
		{
			@Override
			public Object onCallBack(ResultSet rs) throws SQLException
			{
				List<CpPushUrlModel> list = new ArrayList<CpPushUrlModel>();
				while(rs.next())
				{
					CpPushUrlModel model = new CpPushUrlModel();
					
					model.setId(rs.getInt("id"));
					model.setCpId(rs.getInt("cp_id"));
					model.setName(StringUtil.getString(rs.getString("name"), ""));
					model.setUrl(StringUtil.getString(rs.getString("url"), ""));
					
					list.add(model);
				}
				return list;
			}
		});
	}
}
