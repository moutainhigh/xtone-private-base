package com.system.server;

import java.util.List;

import com.system.dao.CpPushUrlDao;
import com.system.model.CpPushUrlModel;

public class CpPushUrlServer
{
	public List<CpPushUrlModel> loadcpPushUrl()
	{
		return new CpPushUrlDao().loadcpPushUrl();
	}
}
