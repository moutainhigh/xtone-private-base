package com.system.server;

import java.util.Map;

import com.system.dao.CpAppDao;
import com.system.model.CpAppModel;


public class CpAppServer {
	public Map<String, Object> loadCpApp(int pageIndex)
	{
		return new CpAppDao().loadCpAppByPageindex(pageIndex);
	}
	
	public Map<String, Object> loadCpApp(int pageIndex,String appname,String startDate,String endDate)
	{
		System.out.println("appname:"+appname);
		return new CpAppDao().loadCpApp(pageIndex, appname, startDate, endDate);
	}
	
	public boolean addCpApp(CpAppModel model)
	{
		System.out.println("addCpApp ... ");
		return new CpAppDao().addCpApp(model);
	}
	
	public boolean deletCpApp(int id)
	{
		System.out.println("deletCpApp  ...");
		return new CpAppDao().deletCpApp(id);
	}
	
	public CpAppModel loadCpAppById(int id)
	{
		System.out.println("loadCpAppById ...");
		return new CpAppDao().loadCpAppById(id);
	}
	
	public boolean updateCpApp(CpAppModel model)
	{
		System.out.println("updatecpapp ....");
		System.out.println();
		return new CpAppDao().updateCpApp(model);
	}
	
	public void aaaa(String startdate,String enddate,String appnamestr,String channel)
	{
		System.out.println("aaaaaaaa");
		System.out.println(startdate+","+enddate+","+appnamestr+","+channel);
	}
}
