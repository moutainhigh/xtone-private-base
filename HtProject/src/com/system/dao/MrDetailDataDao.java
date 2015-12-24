package com.system.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.system.database.JdbcControl;
import com.system.database.QueryCallBack;
import com.system.util.StringUtil;
import com.system.vo.DetailDataVo;

public class MrDetailDataDao
{
	@SuppressWarnings("unchecked")
	public List<DetailDataVo> loadDetailDataByPhoneMsg(String keyWord,String table,int type)
	{
		String chkType = "a.mobile";
		
		if(type==1)
		{
			chkType = "a.mobile";
		}
		else if(type==2)
		{
			chkType = "a.imei";
		}
		else if(type==3)
		{
			chkType = "a.imsi";
		}
		
		String sql = " SELECT a.`imei`,a.`imsi`,a.`mobile`,";
		sql += " g.`name` province_name,h.`name` city_name,e.`short_name` sp_name,d.`name` sp_trone_name,";
		sql += " b.`price`,f.`short_name` cp_name,a.`syn_flag`,a.`create_date`";
		sql += " FROM daily_log.`tbl_mr_"+ table +"` a";
		sql += " LEFT JOIN daily_config.`tbl_trone` b ON a.`trone_id` = b.`id`";
		sql += " LEFT JOIN daily_config.`tbl_trone_order` c ON a.`trone_order_id` = c.`id`";
		sql += " LEFT JOIN daily_config.`tbl_sp_trone` d ON b.`sp_trone_id` = d.`id`";
		sql += " LEFT JOIN daily_config.`tbl_sp` e ON d.`sp_id` = e.`id`";
		sql += " LEFT JOIN daily_config.`tbl_cp` f ON c.`cp_id` = f.`id`";
		sql += " LEFT JOIN daily_config.`tbl_province` g ON a.`province_id` = g.`id`";
		sql += " LEFT JOIN daily_config.`tbl_city` h ON a.`city_id` = h.`id`";
		sql += " WHERE "+ chkType +" in ("+ keyWord +") order by a.create_date desc limit 0,100";
		
		return (List<DetailDataVo>)new JdbcControl().query(sql, new QueryCallBack()
		{
			@Override
			public Object onCallBack(ResultSet rs) throws SQLException
			{
				List<DetailDataVo> list = new ArrayList<DetailDataVo>();
				
				while(rs.next())
				{
					DetailDataVo vo = new DetailDataVo();
					vo.setCityName(StringUtil.getString(rs.getString("city_name"), ""));
					vo.setProvinceName(StringUtil.getString(rs.getString("province_name"), ""));
					vo.setImei(StringUtil.getString(rs.getString("imei"), ""));
					vo.setImsi(StringUtil.getString(rs.getString("imsi"), ""));
					vo.setMobile(StringUtil.getString(rs.getString("mobile"), ""));
					vo.setSpName(StringUtil.getString(rs.getString("sp_name"), ""));
					vo.setSpTroneName(StringUtil.getString(rs.getString("sp_trone_name"), ""));
					vo.setPrice(rs.getFloat("price"));
					vo.setCpName(StringUtil.getString(rs.getString("cp_name"), ""));
					vo.setSynFlag(rs.getInt("syn_flag"));
					vo.setCreateDate(rs.getString("create_date"));
					list.add(vo);
				}
				
				return list;
			}
		});
		
	}
}	
