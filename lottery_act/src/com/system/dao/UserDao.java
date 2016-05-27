package com.system.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.system.database.JdbcControl;
import com.system.database.QueryCallBack;

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
	
	
	
}
