package com.system.model;

public class LotteryTicketModel
{
	private int id;
	private String exchangeCode;
	private String pwdCode;
	private String expireTime;
	private int status;
	
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
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
	public int getStatus()
	{
		return status;
	}
	public void setStatus(int status)
	{
		this.status = status;
	}
	
	
}
