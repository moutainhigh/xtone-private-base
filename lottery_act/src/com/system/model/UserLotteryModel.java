package com.system.model;

public class UserLotteryModel
{
	private int userId;
	private int ticketId;
	private int activityId;
	private String addTime;
	private String exchangeCode;
	private String pwdCode;
	private String expireTime;
	private String actStartDate;
	private String actEndDate;
	private String actName;
	
	public int getUserId()
	{
		return userId;
	}
	public void setUserId(int userId)
	{
		this.userId = userId;
	}
	public int getTicketId()
	{
		return ticketId;
	}
	public void setTicketId(int ticketId)
	{
		this.ticketId = ticketId;
	}
	public int getActivityId()
	{
		return activityId;
	}
	public void setActivityId(int activityId)
	{
		this.activityId = activityId;
	}
	public String getAddTime()
	{
		return addTime;
	}
	public void setAddTime(String addTime)
	{
		this.addTime = addTime;
	}
	public String getExchangeCode()
	{
		return exchangeCode;
	}
	public void setExchangeCode(String exchangeCode)
	{
		this.exchangeCode = exchangeCode;
	}
	public String getPwdCode()
	{
		return pwdCode;
	}
	public void setPwdCode(String pwdCode)
	{
		this.pwdCode = pwdCode;
	}
	public String getExpireTime()
	{
		return expireTime;
	}
	public void setExpireTime(String expireTime)
	{
		this.expireTime = expireTime;
	}
	public String getActStartDate()
	{
		return actStartDate;
	}
	public void setActStartDate(String actStartDate)
	{
		this.actStartDate = actStartDate;
	}
	public String getActEndDate()
	{
		return actEndDate;
	}
	public void setActEndDate(String actEndDate)
	{
		this.actEndDate = actEndDate;
	}
	public String getActName()
	{
		return actName;
	}
	public void setActName(String actName)
	{
		this.actName = actName;
	}
}
