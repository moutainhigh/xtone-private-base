package com.system.model;

public class SpTroneApiModel
{
	private int id;
	private String name;
	private int matchField;
	private String matchKeyword;
	private String apiFields;
	private int loaclMatch;
	private float userDayLimit;
	private float userMonthLimit;
	private float dayLimit;
	private float monthLimit;
	private float curDayLimit;
	private float curMonthLimit;
	
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public int getMatchField()
	{
		return matchField;
	}
	public void setMatchField(int matchField)
	{
		this.matchField = matchField;
	}
	public String getMatchKeyword()
	{
		return matchKeyword;
	}
	public void setMatchKeyword(String matchKeyword)
	{
		this.matchKeyword = matchKeyword;
	}
	public String getApiFields()
	{
		return apiFields;
	}
	public void setApiFields(String apiFields)
	{
		this.apiFields = apiFields;
	}
	public int getLoaclMatch()
	{
		return loaclMatch;
	}
	public void setLoaclMatch(int loaclMatch)
	{
		this.loaclMatch = loaclMatch;
	}
	public float getUserDayLimit()
	{
		return userDayLimit;
	}
	public void setUserDayLimit(float userDayLimit)
	{
		this.userDayLimit = userDayLimit;
	}
	public float getUserMonthLimit()
	{
		return userMonthLimit;
	}
	public void setUserMonthLimit(float userMonthLimit)
	{
		this.userMonthLimit = userMonthLimit;
	}
	public float getDayLimit()
	{
		return dayLimit;
	}
	public void setDayLimit(float dayLimit)
	{
		this.dayLimit = dayLimit;
	}
	public float getMonthLimit()
	{
		return monthLimit;
	}
	public void setMonthLimit(float monthLimit)
	{
		this.monthLimit = monthLimit;
	}
	public float getCurDayLimit()
	{
		return curDayLimit;
	}
	public void setCurDayLimit(float curDayLimit)
	{
		this.curDayLimit = curDayLimit;
	}
	public float getCurMonthLimit()
	{
		return curMonthLimit;
	}
	public void setCurMonthLimit(float curMonthLimit)
	{
		this.curMonthLimit = curMonthLimit;
	}
	
}
