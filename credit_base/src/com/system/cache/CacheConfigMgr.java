package com.system.cache;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.system.dao.CpDataDao;
import com.system.dao.LocateDao;
import com.system.dao.SpDataDao;
import com.system.model.CityModel;
import com.system.model.ProvinceModel;
import com.system.model.SpTroneApiModel;
import com.system.model.SpTroneModel;
import com.system.model.TroneModel;
import com.system.model.TroneOrderModel;

public class CacheConfigMgr
{
	private static Logger logger  = Logger.getLogger(CacheConfigMgr.class); 
	
	static
	{
		refreshAllCache();
	}
	
	public static void refreshAllCache()
	{
		refreshTroneOrderCache();
		refreshSpTroneCache();
		refreshTroneCache();
		refreshSpTroneApiCache();
		refreshPhoneLocateCache();
		refreshProvinceCache();
		refreshCityCache();
	}
	
	public static void refreshAllTroneCache()
	{
		refreshTroneOrderCache();
		refreshSpTroneCache();
		refreshTroneCache();
		refreshSpTroneApiCache();
	}
	
	public static void refreshAllLocateCache()
	{
		refreshPhoneLocateCache();
		refreshProvinceCache();
		refreshCityCache();
	}
	
	public static void refreshTroneOrderCache()
	{
		List<TroneOrderModel> list = new CpDataDao().loadTroneOrderList();
		CpDataCache.setTroneOrder(list);
		logger.info("refreshTroneOrderCache finish");
	}
	
	public static void refreshSpTroneCache()
	{
		List<SpTroneModel> list = new SpDataDao().loadSpTroneList();
		SpDataCache.setSpTroneList(list);
		logger.info("refreshSpTroneCache finish");
	}
	
	public static void refreshTroneCache()
	{
		List<TroneModel> list = new SpDataDao().loadTrone();
		SpDataCache.setTroneList(list);
		logger.info("refreshTroneCache finish");
	}
	
	public static void refreshSpTroneApiCache()
	{
		List<SpTroneApiModel> list = new SpDataDao().loadSpTroneApi();
		SpDataCache.setSpTroneApiList(list);
		logger.info("refreshSpTroneApiCache finish");
	}
	
	public static void refreshPhoneLocateCache()
	{
		 Map<String,Integer> map = new LocateDao().loadPhoneLocateMap();
		 LocateCache.setPhoneLocate(map);
		 logger.info("refreshPhoneLocateCache finish");
	}
	
	public static void refreshProvinceCache()
	{
		List<ProvinceModel> list = new LocateDao().loadProvinceList();
		LocateCache.setProvince(list);
		logger.info("refreshProvinceCache finish");
	}
	
	public static void refreshCityCache()
	{
		List<CityModel> list = new LocateDao().loadCityList();
		LocateCache.setCity(list);
		logger.info("refreshCityCache finish");
	}
	
	public static void init(){}
}
