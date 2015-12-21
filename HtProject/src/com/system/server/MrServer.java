
package com.system.server;

import java.util.Map;

import com.system.dao.MrDao;
import com.system.util.StringUtil;

public class MrServer
{
	public Map<String, Object> getMrData(String startDate, String endDate,
			int spId,int spTroneId, int troneId, int cpId, int troneOrderId, int provinceId,
			int cityId,int operatorId,int dataType,int sortType)
	{
		return new MrDao().getMrAnalyData(startDate, endDate, spId, spTroneId,troneId,
				cpId, troneOrderId, provinceId, cityId,operatorId,dataType, sortType);
	}
	
	public Map<String, Object> getMrDataQiBa(String startDate, String endDate,
			int spId,int spTroneId, int troneId, int cpId, int troneOrderId, int provinceId,
			int cityId, int sortType)
	{
		return new MrDao().getMrAnalyDataQiBa(startDate, endDate, spId, spTroneId,troneId,
				cpId, troneOrderId, provinceId, cityId, sortType);
	}
	
	public Map<String, Object> getMrTodayData(
			int spId, int spTroneId,int troneId, int cpId, int troneOrderId, int provinceId,
			int cityId, int sortType)
	{
		String tableName = StringUtil.getMonthFormat();
		String startDate = StringUtil.getDefaultDate();
		return new MrDao().getMrTodayData(tableName, startDate, spId,spTroneId, troneId,
				cpId, troneOrderId, provinceId, cityId, sortType);
	}
	
	public Map<String, Object> getMrTodayDataQiBa(
			int spId, int spTroneId,int troneId, int cpId, int troneOrderId, int provinceId,
			int cityId, int sortType)
	{
		String tableName = StringUtil.getMonthFormat();
		String startDate = StringUtil.getDefaultDate();
		return new MrDao().getMrTodayDataQiBa(tableName, startDate, spId,spTroneId, troneId,
				cpId, troneOrderId, provinceId, cityId, sortType);
	}
	
	public Map<String,Object> getCpMrTodayShowData(int userId)
	{
		String tableName = StringUtil.getMonthFormat();
		String startDate = StringUtil.getDefaultDate();
		return new MrDao().getCpMrTodayShowData(tableName, startDate, userId);
	}
	
	public Map<String,Object> getCpMrShowData(String startDate,String endDate,int userId)
	{
		return new MrDao().getCpMrShowData(startDate, endDate, userId);
	}
}
