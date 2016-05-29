package com.system.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.system.database.JdbcControl;
import com.system.database.QueryCallBack;
import com.system.model.UserModel;
import com.system.util.StringUtil;

public class UserDao 
{
	public boolean isEmailExist(String email)
	{
		String sql = "SELECT COUNT(*) FROM tbl_user WHERE email = '" + email + "'";
		
		JdbcControl control = new JdbcControl();
		
		return (Boolean)control.query(sql, new QueryCallBack() {
			
			@Override
			public Object onCallBack(ResultSet rs) throws SQLException {
				if(rs.next())
					return rs.getInt(1) > 0 ;
				return false;
			}
		});
	}
	
	public boolean isNameExist(String name)
	{
		String sql = "Select count(*) from tbl_user WHERE name = '" + name + "'";
		
		JdbcControl control = new JdbcControl();
		
		return (Boolean)control.query(sql, new QueryCallBack() {
			
			@Override
			public Object onCallBack(ResultSet rs) throws SQLException {
				if(rs.next())
					return rs.getInt(1) > 0 ;
				return false;
			}
		});
	}
	
	public boolean isRegistFinish(String email)
	{
		String sql = "select uuid from tbl_user WHERE email = '" + email + "'";
		
		JdbcControl control = new JdbcControl();
		
		return (Boolean)control.query(sql, new QueryCallBack() {
			
			@Override
			public Object onCallBack(ResultSet rs) throws SQLException {
				if(rs.next())
				{
					return !StringUtil.isNullOrEmpty(rs.getString("uuid"));
				}
				return false;
			}
		});
	}
	
	public void updateRegistUser(UserModel model)
	{
		String sql = "UPDATE `tbl_user` SET NAME = ? ,pwd = ?, UUID= ?, regist_date = NOW() WHERE email = ? ";
		
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		
		map.put(1, model.getName());
		map.put(2, model.getPwd());
		map.put(3, model.getUuid());
		map.put(4, model.getEmail());
		
		JdbcControl control = new JdbcControl();
		
		control.execute(sql, map);
	}
	
	public UserModel login(UserModel model)
	{
		String sql = "SELECT * FROM `tbl_user` WHERE (NAME = '" + model.getName() + "' AND pwd = '" + model.getPwd() + "') OR (email='" + model.getName() + "' AND pwd = '" +  model.getPwd() + "')";
		
		JdbcControl control = new JdbcControl();
		
		return (UserModel)control.query(sql, new QueryCallBack()
		{
			@Override
			public Object onCallBack(ResultSet rs) throws SQLException
			{
				if(rs.next())
				{
					UserModel model = new UserModel();
					model.setName(StringUtil.getString(rs.getString("name"), ""));
					model.setPwd(StringUtil.getString(rs.getString("pwd"), ""));
					model.setEmail(StringUtil.getString(rs.getString("email"), ""));
					model.setNickName(StringUtil.getString(rs.getString("nick_name"), ""));
					model.setFlag(rs.getInt("flag"));
					model.setUuid(StringUtil.getString(rs.getString("uuid"), ""));
					return model;
				}
				
				return null;
			}
		});
	}
	
	public void updateLoginTime(String name)
	{
		String sql = "udpate tbl_user set last_login_date = now() where name = '" + name + "'";
		new JdbcControl().execute(sql);
	}
	
	public UserModel getUser(String nameOrEmail)
	{
		String sql = "SELECT * FROM tbl_user WHERE NAME = '" + nameOrEmail + "' OR email = '" + nameOrEmail + "'";
		return (UserModel)new JdbcControl().query(sql, new QueryCallBack()
		{
			@Override
			public Object onCallBack(ResultSet rs) throws SQLException
			{
				if(rs.next())
				{
					UserModel model = new UserModel();
					model.setName(StringUtil.getString(rs.getString("name"), ""));
					model.setPwd(StringUtil.getString(rs.getString("pwd"), ""));
					model.setEmail(StringUtil.getString(rs.getString("email"), ""));
					model.setNickName(StringUtil.getString(rs.getString("nick_name"), ""));
					model.setFlag(rs.getInt("flag"));
					model.setUuid(StringUtil.getString(rs.getString("uuid"), ""));
					model.setId(rs.getInt("id"));
					return model;
				}
				return null;
			}
		});
		
	}
	
	
	
}
