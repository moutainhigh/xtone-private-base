package com.system.model;

import net.sf.json.JSONObject;

public class BaseResponseModel
{
	private int status;
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
	
}
