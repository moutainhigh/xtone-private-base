package com.system.model;

public class SpTroneModel
{
	private int id;
	private int spId;
	private String spName;
	private String spTroneName;
	private int operator;
	private String operatorName;
	private float jieSuanLv;
	private String provinces;
	private int troneType;
	
	public int getTroneType()
	{
		return troneType;
	}
	public void setTroneType(int troneType)
	{
		this.troneType = troneType;
	}
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public int getSpId()
	{
		return spId;
	}
	public void setSpId(int spId)
	{
		this.spId = spId;
	}
	public String getSpName()
	{
		return spName;
	}
	public void setSpName(String spName)
	{
		this.spName = spName;
	}
	public String getSpTroneName()
	{
		return spTroneName;
	}
	public void setSpTroneName(String spTroneName)
	{
		this.spTroneName = spTroneName;
	}
	public int getOperator()
	{
		return operator;
	}
	public void setOperator(int operator)
	{
		this.operator = operator;
	}
	public String getOperatorName()
	{
		return operatorName;
	}
	public void setOperatorName(String operatorName)
	{
		this.operatorName = operatorName;
	}
	public float getJieSuanLv()
	{
		return jieSuanLv;
	}
	public void setJieSuanLv(float jieSuanLv)
	{
		this.jieSuanLv = jieSuanLv;
	}
	public String getProvinces()
	{
		return provinces;
	}
	public void setProvinces(String provinces)
	{
		this.provinces = provinces;
	}
}
