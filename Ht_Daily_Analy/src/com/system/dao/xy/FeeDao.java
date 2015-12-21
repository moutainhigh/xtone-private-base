package com.system.dao.xy;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.system.constant.Constant;
import com.system.database.JdbcControl;
import com.system.database.QueryCallBack;
import com.system.model.xy.XyFeeModel;
import com.system.util.StringUtil;

public class FeeDao
{
	public boolean analyFeeToSummer(String tableName,String startDate,String endDate)
	{
		String sql = " INSERT INTO game_log.`tbl_xypay_summer`(fee_date,appkey,channelid,data_rows,amount) ";
		sql += " SELECT DATE_FORMAT(createdate,'%Y-%m-%d') fee_date,appkey,channelid,";
		sql += " COUNT(*) data_rows,sum(amount)/100 amounts ";
		sql += " FROM game_log.`tbl_xypay_" + tableName + "`";
		sql += " where createdate >= '" + startDate + " 00:00:00' AND createdate <= '" + endDate + " 23:59:59' and status = 0 ";
		sql += " GROUP BY fee_date,appkey,channelid ";
		sql += " ORDER BY fee_date,appkey,channelid";
		
		JdbcControl control = new JdbcControl();
		
		control.execute(sql);
		
		String sql2 = "insert into game_log.tbl_xy_fee_summer (fee_date,appkey,amount)";
		sql2 += " select fee_date,appkey,sum(amount) amounts ";
		sql2 += " from game_log.tbl_xypay_summer";
		sql2 += " where fee_date >= '" + startDate + "' and fee_date <= '" + endDate + "' group by appkey";
		
		control.execute(sql2);
		
		return true;
	}
	
	public boolean deleteFeeFromSummer(String startDate,String endDate)
	{
		return new JdbcControl().execute("DELETE FROM game_log.`tbl_xypay_summer` WHERE fee_date >= '" + startDate + "' AND fee_date <= '" + endDate + "'");
	}
	
	public boolean updateQdAmount(String startDate,String endDate)
	{
		String sql = " UPDATE game_log.`tbl_xy_fee_summer` a,daily_config.`tbl_xy_app` b "
				+ " SET show_amount = a.`amount`*(100-b.`hold_percent`)/100  "
				+ " WHERE show_amount = 0  AND a.`appkey` = b.`appkey`"
				+ " AND fee_date >= '" + startDate + "' "
				+ " AND fee_date <= '" + endDate + "'";
		
		return new JdbcControl().execute(sql);
	}
	
	public boolean updateQdAmountStatus(String startDate,String endDate)
	{
		String sql = "UPDATE game_log.`tbl_xy_fee_summer` set status = 1 where fee_date >= '" + startDate + "' "
				+ "AND fee_date <= '" + endDate + "'";
		
		return new JdbcControl().execute(sql);
	}
	
	public Map<String, Object> loadAppFee(String startDate,String endDate,String appKey,String channelKey, int pageIndex)
	{
		String sqlCount = " count(*) ";
		String query = " a.*,b.appname ";
		String limit = " limit "  + Constant.PAGE_SIZE*(pageIndex-1) + "," + Constant.PAGE_SIZE;
		
		String sql = "select " + Constant.CONSTANT_REPLACE_STRING + " from game_log.tbl_xypay_summer a left join daily_config.tbl_xy_app b on a.appkey = b.appkey where 1=1 ";
		
		sql += " and fee_date >= '" + startDate + "' and fee_date <= '" + endDate + "' ";
		
		if(!StringUtil.isNullOrEmpty(appKey))
			sql += " and a.appkey like '%" + appKey + "%' ";
		
		if(!StringUtil.isNullOrEmpty(channelKey))
			sql += " and a.channelid like '%" + channelKey + "%' ";
		
		sql += " order by fee_date,b.appname,a.channelid asc";
		
		final Map<String, Object> result = new HashMap<String, Object>();
		
		int count = (Integer)new JdbcControl().query(sql.replace(Constant.CONSTANT_REPLACE_STRING, sqlCount), new QueryCallBack()
		{
			@Override
			public Object onCallBack(ResultSet rs) throws SQLException
			{
				if(rs.next())
					return rs.getInt(1);
				
				return 0;
			}
		});
		
		result.put("row", count);
		
		new JdbcControl().query(sql.replace(Constant.CONSTANT_REPLACE_STRING, " sum(data_rows), sum(amount)"), new QueryCallBack()
		{
			@Override
			public Object onCallBack(ResultSet rs) throws SQLException
			{
				if(rs.next())
				{
					result.put("data_rows", rs.getInt(1));
					result.put("total_amount", rs.getFloat(2));
				}
				
				return 0;
			}
		});
		
		result.put("list",new JdbcControl().query(sql.replace(Constant.CONSTANT_REPLACE_STRING, query) + limit, new QueryCallBack()
		{
			@Override
			public Object onCallBack(ResultSet rs) throws SQLException
			{
				List<XyFeeModel> list = new ArrayList<XyFeeModel>();
				
				XyFeeModel model = null;
				
				while(rs.next())
				{
					model = new XyFeeModel();
					
					model.setId(rs.getInt("id"));
					model.setFeeDate(rs.getString("fee_date"));
					model.setAppKey(rs.getString("appkey"));
					model.setAppName(StringUtil.getString(rs.getString("appname"),""));
					model.setChannelId(rs.getString("channelid"));
					model.setDataRows(rs.getInt("data_rows"));
					model.setAmount(rs.getFloat("amount"));
					
					list.add(model);
				}
				
				return list;
			}
		}));
		
		return result;
	}
	
	public Map<String, Object> loadAppFee(String startDate,String endDate,String appKey,int pageIndex)
	{
		String sqlCount = " count(*) ";
		String query = " a.*,b.appname ";
		String limit = " limit "  + Constant.PAGE_SIZE*(pageIndex-1) + "," + Constant.PAGE_SIZE;
		
		String sql = "select " + Constant.CONSTANT_REPLACE_STRING + " from game_log.tbl_xy_fee_summer a left join daily_config.tbl_xy_app b on a.appkey = b.appkey where 1=1 ";
		
		sql += " and fee_date >= '" + startDate + "' and fee_date <= '" + endDate + "' ";
		
		if(!StringUtil.isNullOrEmpty(appKey))
			sql += " and a.appkey like '%" + appKey + "%' ";
		
		sql += " order by fee_date,b.appname asc";
		
		final Map<String, Object> result = new HashMap<String, Object>();
		
		int count = (Integer)new JdbcControl().query(sql.replace(Constant.CONSTANT_REPLACE_STRING, sqlCount), new QueryCallBack()
		{
			@Override
			public Object onCallBack(ResultSet rs) throws SQLException
			{
				if(rs.next())
					return rs.getInt(1);
				
				return 0;
			}
		});
		
		result.put("row", count);
		
		new JdbcControl().query(sql.replace(Constant.CONSTANT_REPLACE_STRING, "  sum(amount) amounts, sum(show_amount) show_amounts "), new QueryCallBack()
		{
			@Override
			public Object onCallBack(ResultSet rs) throws SQLException
			{
				if(rs.next())
				{
					result.put("amounts", rs.getFloat(1));
					result.put("show_amounts", rs.getFloat(2));
				}
				
				return 0;
			}
		});
		
		result.put("list",new JdbcControl().query(sql.replace(Constant.CONSTANT_REPLACE_STRING, query) + limit, new QueryCallBack()
		{
			@Override
			public Object onCallBack(ResultSet rs) throws SQLException
			{
				List<XyFeeModel> list = new ArrayList<XyFeeModel>();
				
				XyFeeModel model = null;
				
				while(rs.next())
				{
					model = new XyFeeModel();
					
					model.setId(rs.getInt("id"));
					model.setFeeDate(rs.getString("fee_date"));
					model.setAppKey(rs.getString("appkey"));
					model.setAppName(StringUtil.getString(rs.getString("appname"),""));
					model.setAmount(rs.getFloat("amount"));
					model.setShowAmount(rs.getFloat("show_amount"));
					model.setStatus(rs.getInt("status"));
					
					list.add(model);
				}
				
				return list;
			}
		}));
		
		return result;
	}
	
	public Map<String, Object> loadQdAppFee(String startDate,String endDate,int userId,int pageIndex)
	{
		String sqlCount = " count(*) ";
		String query = " a.id,a.status,fee_date,b.appname,a.appkey,show_amount,data_rows ";
		String limit = " limit "  + Constant.PAGE_SIZE*(pageIndex-1) + "," + Constant.PAGE_SIZE;
		
		String sql = "select  " + Constant.CONSTANT_REPLACE_STRING
				+ " from game_log.tbl_xy_fee_summer a left join daily_config.tbl_xy_app b on a.appkey = b.appkey ";
		
		//董老板说把原来扣量后的激活数改为真实用户数 2015.10.30 16:21 更改者 	Andy.Chen (show_data_rows,data_rows) 
		sql += " LEFT JOIN ( SELECT active_date,appkey,SUM(data_rows) data_rows FROM game_log.`tbl_xy_user_summer` WHERE 1=1 AND active_date >= '"
				+ startDate + "' AND active_date <= '" + endDate
				+ "'  AND STATUS = 1 GROUP BY active_date,appkey  ) c ON a.fee_date = c.active_date AND a.`appkey` = c.appkey WHERE 1=1  AND fee_date >= '"
				+ startDate + "' AND fee_date <= '" + endDate
				+ "' AND a.status = 1 AND b.`user_id` = " + userId
				+ " ORDER BY fee_date,b.appname ASC ";
		
		final Map<String, Object> result = new HashMap<String, Object>();
		
		int count = (Integer)new JdbcControl().query(sql.replace(Constant.CONSTANT_REPLACE_STRING, sqlCount), new QueryCallBack()
		{
			@Override
			public Object onCallBack(ResultSet rs) throws SQLException
			{
				if(rs.next())
					return rs.getInt(1);
				
				return 0;
			}
		});
		
		result.put("row", count);
		
		new JdbcControl().query(sql.replace(Constant.CONSTANT_REPLACE_STRING, " sum(show_amount) show_amounts,sum(data_rows) show_rows "), new QueryCallBack()
		{
			@Override
			public Object onCallBack(ResultSet rs) throws SQLException
			{
				if(rs.next())
				{
					result.put("show_amounts", rs.getFloat(1));
					result.put("show_rows", rs.getFloat(2));
				}
				
				return 0;
			}
		});
		
		result.put("list",new JdbcControl().query(sql.replace(Constant.CONSTANT_REPLACE_STRING, query) + limit, new QueryCallBack()
		{
			@Override
			public Object onCallBack(ResultSet rs) throws SQLException
			{
				List<XyFeeModel> list = new ArrayList<XyFeeModel>();
				
				XyFeeModel model = null;
				
				while(rs.next())
				{
					model = new XyFeeModel();
					
					model.setId(rs.getInt("id"));
					model.setFeeDate(rs.getString("fee_date"));
					model.setAppKey(rs.getString("appkey"));
					model.setAppName(StringUtil.getString(rs.getString("appname"),""));
					model.setShowAmount(rs.getFloat("show_amount"));
					model.setDataRows(rs.getInt("data_rows"));
					model.setStatus(rs.getInt("status"));
					
					list.add(model);
				}
				
				return list;
			}
		}));
		
		return result;
	}
	
	public boolean updateQdFee(int id,int showAmount)
	{
		String sql = "update game_log.tbl_xy_fee_summer set show_amount = " + showAmount + " where id = " + id;
		return new JdbcControl().execute(sql);
	}
	
}
