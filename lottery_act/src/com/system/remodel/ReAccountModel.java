package com.system.remodel;

import net.sf.json.JSONArray;

public class ReAccountModel
{
	private String EMAIL;
	private String NAME;
	private String UUID;
	private JSONArray DATA;
	
	public String getEMAIL()
	{
		return EMAIL;
	}
	public void setEMAIL(String eMAIL)
	{
		EMAIL = eMAIL;
	}
	public String getNAME()
	{
		return NAME;
	}
	public void setNAME(String nAME)
	{
		NAME = nAME;
	}
	public String getUUID()
	{
		return UUID;
	}
	public void setUUID(String uUID)
	{
		UUID = uUID;
	}
	public JSONArray getDATA()
	{
		return DATA;
	}
	public void setDATA(JSONArray dATA)
	{
		DATA = dATA;
	}
	
	
}
