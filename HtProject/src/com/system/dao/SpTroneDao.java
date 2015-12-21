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
import com.system.model.SpTroneModel;
import com.system.util.StringUtil;

public class SpTroneDao
{
	public Map<String, Object> loadSpTroneList(int pageIndex,int spId,String spTroneName)
	{
		String query = " a.*,b.short_name,c.`name_cn` ";
		
		String sql = "SELECT " + Constant.CONSTANT_REPLACE_STRING;
		sql += " FROM daily_config.`tbl_sp_trone` a";
		sql += " LEFT JOIN daily_config.`tbl_sp` b ON a.`sp_id` = b.`id`";
		sql += " LEFT JOIN daily_config.`tbl_operator` c ON a.operator = c.`id` where 1=1 ";
		
		if(spId>0)
		{
			sql +=  " and b.id =" + spId;
		}
		
		if(!StringUtil.isNullOrEmpty(spTroneName))
		{
			sql += " and name like '%" + spTroneName + "%' ";
		}
		
		String limit = " limit "  + Constant.PAGE_SIZE*(pageIndex-1) + "," + Constant.PAGE_SIZE;
		
		String orders = " order by  convert(b.short_name using gbk),convert(a.name using gbk) asc ";
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("rows",new JdbcControl().query(sql.replace(Constant.CONSTANT_REPLACE_STRING, "count(*)"), new QueryCallBack()
		{
			@Override
			public Object onCallBack(ResultSet rs) throws SQLException
			{
				if(rs.next())
					return rs.getInt(1);
				
				return 0;
			}
		}));
		
		map.put("list",new JdbcControl().query(sql.replace(Constant.CONSTANT_REPLACE_STRING, query) + orders + limit, new QueryCallBack()
		{
			@Override
			public Object onCallBack(ResultSet rs) throws SQLException
			{
				List<SpTroneModel> list = new ArrayList<SpTroneModel>();
				
				while(rs.next())
				{
					SpTroneModel model = new SpTroneModel();
					
					model.setId(rs.getInt("id"));
					model.setSpId(rs.getInt("sp_id"));
					model.setSpName(StringUtil.getString(rs.getString("short_name"), ""));
					model.setSpTroneName(StringUtil.getString(rs.getString("name"), ""));
					model.setOperator(rs.getInt("operator"));
					model.setJieSuanLv(rs.getFloat("jiesuanlv"));
					model.setOperatorName(StringUtil.getString(rs.getString("name_cn"), ""));
					model.setTroneType(rs.getInt("trone_type"));
					
					list.add(model);
				}
				
				return list;
			}
		}));
		
		return map;
	}
	
	@SuppressWarnings("unchecked")
	public List<SpTroneModel> loadSpTroneList()
	{
		String sql = "SELECT  a.*,b.short_name,c.`name_cn`  ";
		sql += " FROM daily_config.`tbl_sp_trone` a";
		sql += " LEFT JOIN daily_config.`tbl_sp` b ON a.`sp_id` = b.`id`";
		sql += " LEFT JOIN daily_config.`tbl_operator` c ON a.operator = c.`id`";
		
		
		return (List<SpTroneModel>)new JdbcControl().query(sql, new QueryCallBack()
		{
			@Override
			public Object onCallBack(ResultSet rs) throws SQLException
			{
				List<SpTroneModel> list = new ArrayList<SpTroneModel>();
				
				while(rs.next())
				{
					SpTroneModel model = new SpTroneModel();
					
					model.setId(rs.getInt("id"));
					model.setSpId(rs.getInt("sp_id"));
					model.setSpName(StringUtil.getString(rs.getString("short_name"), ""));
					model.setSpTroneName(StringUtil.getString(rs.getString("name"), ""));
					model.setOperator(rs.getInt("operator"));
					model.setJieSuanLv(rs.getFloat("jiesuanlv"));
					model.setOperatorName(StringUtil.getString(rs.getString("name_cn"), ""));
					model.setTroneType(rs.getInt("trone_type"));
					
					list.add(model);
				}
				
				return list;
			}
		});
	}
	
	@SuppressWarnings("unchecked")
	public List<SpTroneModel> loadSpTroneList(int spId)
	{
		String sql = "SELECT  a.*,b.short_name,c.`name_cn`  ";
		sql += " FROM daily_config.`tbl_sp_trone` a";
		sql += " LEFT JOIN daily_config.`tbl_sp` b ON a.`sp_id` = b.`id`";
		sql += " LEFT JOIN daily_config.`tbl_operator` c ON a.operator = c.`id`";
		sql += " where b.id =" + spId;
		
		return (List<SpTroneModel>)new JdbcControl().query(sql, new QueryCallBack()
		{
			@Override
			public Object onCallBack(ResultSet rs) throws SQLException
			{
				List<SpTroneModel> list = new ArrayList<SpTroneModel>();
				
				while(rs.next())
				{
					SpTroneModel model = new SpTroneModel();
					
					model.setId(rs.getInt("id"));
					model.setSpId(rs.getInt("sp_id"));
					model.setSpName(StringUtil.getString(rs.getString("short_name"), ""));
					model.setSpTroneName(StringUtil.getString(rs.getString("name"), ""));
					model.setOperator(rs.getInt("operator"));
					model.setJieSuanLv(rs.getFloat("jiesuanlv"));
					model.setOperatorName(StringUtil.getString(rs.getString("name_cn"), ""));
					model.setTroneType(rs.getInt("trone_type"));
					
					list.add(model);
				}
				
				return list;
			}
		});
	}
	
	
	public SpTroneModel loadSpTroneById(int id)
	{
		String sql = "SELECT  a.*,b.short_name,c.`name_cn`  ";
		sql += " FROM daily_config.`tbl_sp_trone` a";
		sql += " LEFT JOIN daily_config.`tbl_sp` b ON a.`sp_id` = b.`id`";
		sql += " LEFT JOIN daily_config.`tbl_operator` c ON a.operator = c.`id`";
		sql += " WHERE a.id = " + id;
		
		return (SpTroneModel)new JdbcControl().query(sql, new QueryCallBack()
		{
			@Override
			public Object onCallBack(ResultSet rs) throws SQLException
			{
				if(rs.next())
				{
					SpTroneModel model = new SpTroneModel();
					
					model.setId(rs.getInt("id"));
					model.setSpId(rs.getInt("sp_id"));
					model.setSpName(StringUtil.getString(rs.getString("short_name"), ""));
					model.setSpTroneName(StringUtil.getString(rs.getString("name"), ""));
					model.setOperator(rs.getInt("operator"));
					model.setJieSuanLv(rs.getFloat("jiesuanlv"));
					model.setOperatorName(StringUtil.getString(rs.getString("name_cn"), ""));
					model.setProvinces(StringUtil.getString(rs.getString("provinces"), ""));
					model.setTroneType(rs.getInt("trone_type"));
					
					return model;
				}
				
				return null;
			}
		});
	}
	
	public boolean addSpTrone(SpTroneModel model)
	{
		String sql = "insert into daily_config.tbl_sp_trone(sp_id,name,operator,jiesuanlv,provinces,create_date,trone_type) values("
				+ model.getSpId() + ",'" + model.getSpTroneName() + "',"
				+ model.getOperator() + "," + model.getJieSuanLv() + ",'"
				+ model.getProvinces() + "',now()," + model.getTroneType() + ")";
		return new JdbcControl().execute(sql);
	}
	
	public boolean updateSpTroneModel(SpTroneModel model)
	{
		String sql = "update daily_config.tbl_sp_trone set sp_id = "
				+ model.getSpId() + " ,name = '" + model.getSpTroneName()
				+ "',operator = " + model.getOperator() + ", jiesuanlv = "
				+ model.getJieSuanLv() + ",provinces = '" + model.getProvinces()
				+ "',trone_type = " + model.getTroneType() + " where id =" + model.getId();
		
		return new JdbcControl().execute(sql);
	}
	
	public boolean delSpTrone(int id)
	{
		String sql = "delete from daily_config.tbl_sp_trone where id =" + id;
		return new JdbcControl().execute(sql);
	}
	
}
