package com.system.dao;

import java.util.HashMap;
import java.util.Map;

import com.system.database.JdbcControl;
import com.system.model.ApiOrderModel;
import com.system.util.StringUtil;

public class RecordDao
{
	/*
	public void addRecord(ApiOrderModel model)
	{
		String sql = "insert into daily_log.tbl_api_order_" + StringUtil.getMonthFormat() + " (trone_order_id,api_id,"
				+ "imsi,imei,mobile,lac,cid,ExtrData,sdkversion,packagename,ip,clientip,nettype,sp_linkid,sp_exField,cp_verifyCode,FirstDate,port,msg,api_exdata,status,is_hidden)"
				+ " values(" + model.getTroneOrderId() + "," + model.getApiOrderId() + ",'" + model.getImsi() + "','" 
				+ model.getImei() + "','" + model.getMobile() + "'," + model.getLac() + "," + model.getCid() + ",'" + model.getExtrData() 
				+ "','" + model.getSdkVersion() + "','" + model.getPackageName() + "','" + model.getIp()+ "','" + model.getClientIp() + "','" 
				+ model.getNetType() + "','" + model.getSpLinkId() + "','" + model.getSpExField() + "','" + model.getCpVerifyCode() + "',now(),'" 
				+ model.getPort() + "','" + model.getMsg() + "','" + model.getApiExdata() + "'," + model.getStatus() + ","+ model.getIsHidden() +")";
		
		new JdbcControl().execute(sql);
	}
	*/
	
	
	public void addRecord(ApiOrderModel model)
	{
		String sql = "insert into daily_log.tbl_api_order_" + StringUtil.getMonthFormat() + " (trone_order_id,api_id,imsi,imei,mobile,lac,"
				+ "cid,ExtrData,sdkversion,packagename,ip,clientip,nettype,"
				+ "sp_linkid,sp_exField,cp_verifyCode,FirstDate,port,"
				+ "msg,api_exdata,status,is_hidden,trone_id,extra_param) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,now(),?,?,?,?,?,?,?)";
		
		Map<Integer, Object> params = new HashMap<>();
		
		params.put(1, model.getTroneOrderId());
		params.put(2, model.getApiOrderId());
		params.put(3, model.getImsi());
		params.put(4, model.getImei());
		params.put(5, model.getMobile());
		params.put(6, model.getLac());
		params.put(7, model.getCid());
		params.put(8, model.getExtrData());
		params.put(9, model.getSdkVersion());
		params.put(10, model.getPackageName());
		params.put(11, model.getIp());
		params.put(12, model.getClientIp());
		params.put(13, model.getNetType());
		params.put(14, model.getSpLinkId());
		params.put(15, model.getSpExField());
		params.put(16, model.getCpVerifyCode());
		params.put(17, model.getPort());
		params.put(18, model.getMsg());
		params.put(19, model.getApiExdata());
		params.put(20, model.getStatus());
		params.put(21, model.getIsHidden());
		params.put(22, model.getTroneId());
		params.put(23, model.getExtraParams());
		
		model.setId(new JdbcControl().insertWithGenKey(sql, params));
	}
	
	public void updateRecord(ApiOrderModel model)
	{
		String sql = "update daily_log.tbl_api_order_" + StringUtil.getMonthFormat() + " set sp_linkid = ? ,sp_exField = ?,cp_verifyCode = ?,port = ?,"
				+ "msg=?,api_exdata=?,status=?,is_hidden=? where id = ?";
		
		Map<Integer, Object> params = new HashMap<>();
		params.put(1, model.getSpLinkId());
		params.put(2, model.getSpExField());
		params.put(3, model.getCpVerifyCode());
		params.put(4, model.getPort());
		params.put(5, model.getMsg());
		params.put(6, model.getApiExdata());
		params.put(7, model.getStatus());
		params.put(8, model.getIsHidden());
		params.put(9, model.getId());
		
		new JdbcControl().execute(sql, params);
		
	}
	
	public static void main(String[] args)
	{
		System.out.println(StringUtil.getMonthFormat());
	}
}
