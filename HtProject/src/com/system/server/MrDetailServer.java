package com.system.server;

import java.util.List;

import com.system.dao.MrDetailDataDao;
import com.system.util.StringUtil;
import com.system.vo.DetailDataVo;

public class MrDetailServer
{
	public List<DetailDataVo> loadDetailDataByPhoneMsg(String keyWord,String table,int type)
	{
		return new MrDetailDataDao().loadDetailDataByPhoneMsg(keyWord, table,type);
	}
	
	public List<DetailDataVo> loadDetailDataBySummer(String date,int spId,int cpId,int spTroneId,int troneId,int type,String joinId)
	{
		String table = StringUtil.getMonthFormat(date);
		return new MrDetailDataDao().loadDetailDataBySummer(table,date,spId,cpId,spTroneId,troneId,type,joinId);
	}
}
