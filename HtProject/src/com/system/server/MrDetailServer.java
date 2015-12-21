package com.system.server;

import java.util.List;

import com.system.dao.MrDetailDataDao;
import com.system.vo.DetailDataVo;

public class MrDetailServer
{
	public List<DetailDataVo> loadDetailDataByPhoneMsg(String keyWord,String table)
	{
		return new MrDetailDataDao().loadDetailDataByPhoneMsg(keyWord, table);
	}
}
