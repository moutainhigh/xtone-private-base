package com.system.server;

import java.util.List;

import com.system.util.ServiceUtil;

public class NetServer
{
	public boolean synDataList(String url,List<String> list)
	{
		for(String data : list)
		{
			if(ServiceUtil.sendData(url + "?" + data, null, null).equalsIgnoreCase("OK"))
				continue;
			else
				return false;
		}
		return true;
	}
	
}
