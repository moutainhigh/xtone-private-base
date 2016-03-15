package com.system.model;

import net.sf.json.JSONObject;

public class BaseResponseModel
{
	private int status;
	private String orderNum;
	private JSONObject resultJson;
	
	public int getStatus()
	{
		return status;
	}
	public void setStatus(int status)
	{
		this.status = status;
	}
	public JSONObject getResultJson()
	{
		return resultJson;
	}
	public void setResultJson(JSONObject resultJson)
	{
		this.resultJson = resultJson;
	}
	public String getOrderNum()
	{
		return orderNum;
	}
	public void setOrderNum(String orderNum)
	{
		this.orderNum = orderNum;
	}
	
}
