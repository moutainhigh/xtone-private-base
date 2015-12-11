package com.xtone.server;

import com.xtone.dao.LeoDao;
import com.xtone.iap.ReceiveFromMsg;

public class ClientServer {
//	  LOG.debug(signature+";"+purchaseInfo+";"+environment+";"+pod+";"+signingStatus);
//	public boolean insertMsg(ReceiveFromMsg msg,long time)
//	{
//		System.out.println("insert....");
//		return new LeoDao().insertMsg(msg, time);
//	}
	
	public int insertMsg(ReceiveFromMsg msg,long time)
	{
		System.out.println("insert....");
		return new LeoDao().insertMsg(msg, time);
	}
	
	public Integer getLastInsertId()
	{
		System.out.println("getId...");
		return new LeoDao().getLastInsertId();
	}
	
	public boolean updateAppleMsg(int id,String appleMsg,long time)
	{
		return new LeoDao().updateMsg(id, appleMsg, time);
	}
	
	public boolean updateResponse(String result,int id,String url,long time)
	{
		return new LeoDao().updateResponseMsg(id, result, url, time);
	}
}
