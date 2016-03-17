package com.system.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.system.constant.Constant;
import com.system.database.JdbcControl;
import com.system.database.QueryCallBack;
import com.system.model.CpSpTroneRateModel;
import com.system.util.StringUtil;

public class CpSpTroneRateDao
{
	public Map<String, Object> loadCpSpTroneRate(String keyWord,int pageIndex)
	{
		String sql = "select " + Constant.CONSTANT_REPLACE_STRING + " FROM daily_config.`tbl_cp_trone_rate` a ";
		
		sql += " LEFT JOIN daily_config.`tbl_cp` b ON a.`cp_id` = b.`id`";
		sql += " LEFT JOIN daily_config.`tbl_sp_trone` c ON a.`sp_trone_id` = c.`id`";
		sql += " LEFT JOIN daily_config.`tbl_sp` d ON c.`sp_id` = d.`id`";
		sql += " WHERE 1=1";
		
		if(StringUtil.isNullOrEmpty(keyWord))
		{
			sql += " AND (b.`short_name` LIKE '%" + keyWord + "%' OR b.`full_name` LIKE '%" + keyWord + "%' ";
			sql += " OR c.`name` LIKE '%" + keyWord + "%' OR d.`short_name` LIKE '%" + keyWord + "%' OR d.`full_name` LIKE '%" + keyWord + "%')";
		}
		
		String limit = " limit "  + Constant.PAGE_SIZE*(pageIndex-1) + "," + Constant.PAGE_SIZE;
		
		sql += " order by a.id desc ";
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		JdbcControl control = new JdbcControl();
		
		map.put("rows",control.query(sql.replace(Constant.CONSTANT_REPLACE_STRING, "count(*)"), new QueryCallBack()
		{
			@Override
			public Object onCallBack(ResultSet rs) throws SQLException
			{
				if(rs.next())
					return rs.getInt(1);
				
				return 0;
			}
		}));
		
		map.put("list", control.query(sql.replace(Constant.CONSTANT_REPLACE_STRING, " a.*,b.`short_name` cp_name,c.`name` sp_trone_name,d.`id` sp_id,d.`short_name` sp_name ") + limit, new QueryCallBack()
		{
			@Override
			public Object onCallBack(ResultSet rs) throws SQLException
			{
				List<CpSpTroneRateModel> list = new ArrayList<CpSpTroneRateModel>();
				
				while(rs.next())
				{
					CpSpTroneRateModel model = new CpSpTroneRateModel();
					
					model.setId(rs.getInt("id"));
					model.setCpId(rs.getInt("cp_id"));
					model.setSpTroneId(rs.getInt("sp_trone_id"));
					model.setRate(rs.getFloat("rate"));
					model.setSpName(StringUtil.getString(rs.getString("sp_name"), ""));
					model.setCpName(StringUtil.getString(rs.getString("cp_name"), ""));
					model.setSpTroneName(StringUtil.getString(rs.getString("sp_trone_name"), ""));
					
					list.add(model);
				}
				
				return list;
			}
		}));
		
		return map;
	}
	
	public CpSpTroneRateModel loadCpSpTroneRateById(int id)
	{
		String sql = "select a.*,b.`short_name` cp_name,c.`name` sp_trone_name,d.`id` sp_id,d.`short_name` sp_name FROM daily_config.`tbl_cp_trone_rate` a ";
		
		sql += " LEFT JOIN daily_config.`tbl_cp` b ON a.`cp_id` = b.`id`";
		sql += " LEFT JOIN daily_config.`tbl_sp_trone` c ON a.`sp_trone_id` = c.`id`";
		sql += " LEFT JOIN daily_config.`tbl_sp` d ON c.`sp_id` = d.`id`";
		sql += " WHERE a.id = " + id;
		
		return (CpSpTroneRateModel)new JdbcControl().query(sql, new QueryCallBack()
		{
			@Override
			public Object onCallBack(ResultSet rs) throws SQLException
			{
				if(rs.next())
				{
					CpSpTroneRateModel model = new CpSpTroneRateModel();
					
					model.setId(rs.getInt("id"));
					model.setCpId(rs.getInt("cp_id"));
					model.setSpTroneId(rs.getInt("sp_trone_id"));
					model.setRate(rs.getFloat("rate"));
					model.setSpName(StringUtil.getString(rs.getString("sp_name"), ""));
					model.setCpName(StringUtil.getString(rs.getString("cp_name"), ""));
					model.setSpTroneName(StringUtil.getString(rs.getString("sp_trone_name"), ""));
					
					return model;
				}
				return null;
			}
		});
	}
	
	public void addCpSpTroneRate(CpSpTroneRateModel model)
	{
		String sql = "insert into daily_config.tbl_cp_trone_rate(cp_id,sp_trone_id,rate) values(?,?,?)";
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		map.put(1, model.getCpId());
		
	}
	
	public void updateCpSpTroneRate(CpSpTroneRateModel model)
	{
		
	}
	
	public void delCpSpTroneRate(int id)
	{
		
	}
}
