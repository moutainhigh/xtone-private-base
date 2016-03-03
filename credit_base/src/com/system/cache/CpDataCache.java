package com.system.cache;

import java.util.ArrayList;
import java.util.List;

import com.system.model.TroneOrderModel;

public class CpDataCache
{
	//private static Logger logger = Logger.getLogger(CpDataCache.class);
	
	private static List<TroneOrderModel> _troneOrderList = new ArrayList<>();
	
	protected static void setTroneOrder(List<TroneOrderModel> troneOrderList)
	{
		_troneOrderList = troneOrderList;
	}
	
	/**
	 * 检测是否存在传过来的TRONE ORDER ID，如果存在则返回 troneId
	 * @param troneOrderId
	 * @return
	 */
	public static int getTroneIdByTroneOrderId(int troneOrderId)
	{
		if(troneOrderId<=0)
			return -1;
		
		for(TroneOrderModel model : _troneOrderList)
		{
			if(model.getId()==troneOrderId)
			{
				return model.getTroneId();
			}
		}
		return -1;
	}
}
