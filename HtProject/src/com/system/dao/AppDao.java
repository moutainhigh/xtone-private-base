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
import com.system.model.AppModel;
import com.system.util.StringUtil;

public class AppDao {
	public Map<String, Object> loadAppByPageindex(int pageIndex)
	{
		String limit = " limit "+Constant.PAGE_SIZE*(pageIndex-1) + "," + Constant.PAGE_SIZE;
		
		String sql = "SELECT "+Constant.CONSTANT_REPLACE_STRING
				+ " FROM daily_config.`tbl_xy_app` ORDER BY id ASC ";
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		JdbcControl control = new JdbcControl();
		
		result.put("rows", control.query(sql.replace(Constant.CONSTANT_REPLACE_STRING, " count(*) "),
				new QueryCallBack() {
					
					@Override
					public Object onCallBack(ResultSet rs) throws SQLException {
						if(rs.next())
							return rs.getInt(1);
						return 0;
					}
				}));
		
		result.put("list", control.query(sql.replace(Constant.CONSTANT_REPLACE_STRING, " * ")+limit,
				new QueryCallBack() {
					
					@Override
					public Object onCallBack(ResultSet rs) throws SQLException {
						List<AppModel> list = new ArrayList<AppModel>();
						AppModel model = null;
						while (rs.next()) {
							model = new AppModel();
							model.setId(rs.getInt("id"));
							model.setAppkey(rs.getString("appkey"));
							model.setAppname(rs.getString("appname"));
							model.setHold_percent(rs.getInt("hold_percent"));
							model.setRemark(rs.getString("remark"));
							System.out.println("remark:"+rs.getString("remark"));
							System.out.println("model :"+model.getRemark());
							list.add(model);
						}
						
						return list;
					}
				})); 
		
		return result;
	}
	
	public Map<String, Object> loadAppByPageindex()
	{
		//String limit = " limit "+Constant.PAGE_SIZE*(pageIndex-1) + "," + Constant.PAGE_SIZE;
		
		String sql = "SELECT "+Constant.CONSTANT_REPLACE_STRING
				+ " FROM daily_config.`tbl_xy_app` ORDER BY id ASC ";
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		JdbcControl control = new JdbcControl();
		
		result.put("rows", control.query(sql.replace(Constant.CONSTANT_REPLACE_STRING, " count(*) "),
				new QueryCallBack() {
					
					@Override
					public Object onCallBack(ResultSet rs) throws SQLException {
						if(rs.next())
							return rs.getInt(1);
						return 0;
					}
				}));
		
		result.put("list", control.query(sql.replace(Constant.CONSTANT_REPLACE_STRING, " * "),
				new QueryCallBack() {
					
					@Override
					public Object onCallBack(ResultSet rs) throws SQLException {
						List<AppModel> list = new ArrayList<AppModel>();
						AppModel model = null;
						while (rs.next()) {
							model = new AppModel();
							model.setId(rs.getInt("id"));
							model.setAppkey(rs.getString("appkey"));
							model.setAppname(rs.getString("appname"));
							model.setHold_percent(rs.getInt("hold_percent"));
							model.setRemark(rs.getString("remark"));
				            System.out.println("ID:"+rs.getInt("id"));
							list.add(model);
						}
						
						return list;
					}
				})); 
		
		return result;
	}
	
	public Map<String, Object> loadAppByPageindex(int pageIndex,String appname,String appkey)
	{
		String limit = " limit "+Constant.PAGE_SIZE*(pageIndex-1) + "," + Constant.PAGE_SIZE;
		
		String sql = "SELECT "+Constant.CONSTANT_REPLACE_STRING
				+ " FROM daily_config.`tbl_xy_app` WHERE 1=1 ";
		
		if(!StringUtil.isNullOrEmpty(appname))
		{
			sql += " AND appname LIKE '%"+appname+"%' ";
		}
		
		if(!StringUtil.isNullOrEmpty(appkey))
		{
			sql += " AND appkey LIKE '%"+appkey+"%' ";
		}
		
		sql +=" ORDER BY id ASC ";
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		JdbcControl control = new JdbcControl();
		
		result.put("rows", control.query(sql.replace(Constant.CONSTANT_REPLACE_STRING, " count(*) "),
				new QueryCallBack() {
					
					@Override
					public Object onCallBack(ResultSet rs) throws SQLException {
						if(rs.next())
							return rs.getInt(1);
						return 0;
					}
				}));
		
		result.put("list", control.query(sql.replace(Constant.CONSTANT_REPLACE_STRING, " * ")+limit,
				new QueryCallBack() {
					
					@Override
					public Object onCallBack(ResultSet rs) throws SQLException {
						List<AppModel> list = new ArrayList<AppModel>();
						AppModel model = null;
						while (rs.next()) {
							model = new AppModel();
							model.setId(rs.getInt("id"));
							model.setAppkey(rs.getString("appkey"));
							model.setAppname(rs.getString("appname"));
							model.setHold_percent(rs.getInt("hold_percent"));
							model.setRemark(rs.getString("remark"));
							list.add(model);
						}
						
						return list;
					}
				})); 
		
		return result;
	}
	
	public Integer loadIdByName(String appname)
	{
		String sql = "SELECT id FROM daily_config.`tbl_xy_app` where appname='"+appname+"'";
		
		return (Integer)new JdbcControl().query(sql,
				new QueryCallBack() {
					
					@Override
					public Object onCallBack(ResultSet rs) throws SQLException {
						if(rs.next())
							return rs.getInt(1);
						return 0;
					}
				});
	}
	
	public AppModel loadAppById(int id)
	{
		String sql = "SELECT * FROM daily_config.`tbl_xy_app` where id = "+id;
		
		
		return (AppModel)new JdbcControl().query(sql,
				new QueryCallBack() {
					
					@Override
					public Object onCallBack(ResultSet rs) throws SQLException {
						
						if(rs.next())
						{
							AppModel model = new AppModel();
							model.setId(rs.getInt("id"));
							model.setAppkey(rs.getString("appkey"));
							model.setAppname(rs.getString("appname"));
							model.setHold_percent(rs.getInt("hold_percent"));
							model.setRemark(rs.getString("remark"));
							return model;
						}
						
						return null;
					}
				});
	}
	
	public boolean updataApp(AppModel model)
	{
		String sql = "UPDATE daily_config.`tbl_xy_app` SET "
				+ "appkey='"+model.getAppkey()+"',"
				+ " appname='"+model.getAppname()+"',"
			    + " hold_percent="+model.getHold_percent()+","
			    + " remark='"+model.getRemark()+"' WHERE id="+model.getId();
		return new JdbcControl().execute(sql);
	}
	
	public boolean deletApp(int id)
	{
		String sql = "DELETE FROM daily_config.`tbl_xy_app` WHERE id="+id;
		return new JdbcControl().execute(sql);
	}
	
	public boolean addApp(AppModel model)
	{
		System.out.println("AddRemark:"+model.getRemark());
		String sql = "insert into daily_config.`tbl_xy_app`("
				+ "appkey,appname,hold_percent,remark) value("
				+ "'"+model.getAppkey()+"','"+model.getAppname()+"',"+model.getHold_percent()
				+ ",'"+model.getRemark()+"')";
		
		return new JdbcControl().execute(sql);
	}
	
	
	
}