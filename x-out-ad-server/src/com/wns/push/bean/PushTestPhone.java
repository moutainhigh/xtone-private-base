package com.wns.push.bean;

import java.io.Serializable;


public class PushTestPhone implements Serializable
{
	/**
     * 
     */
    private static final long serialVersionUID = -886372462050833395L;
    
    private Integer id;
	private String imsi;
	private String imei;
	private String channel;
	/** 
	 
	 
	*/
	public final void setid(Integer value)
	{
		id=value;
	}
	public final Integer getid()
	{
		return id;
	}
	/** 
	 
	 
	*/
	public final void setIMSI(String value)
	{
		imsi=value;
	}
	public final String getIMSI()
	{
		return imsi;
	}
	/** 
	 
	 
	*/
	public final void setIMEI(String value)
	{
		imei=value;
	}
	public final String getIMEI()
	{
		return imei;
	}
	/** 
	 
	 
	*/
	public final void setUser_id(String value)
	{
		channel=value;
	}
	public final String getUser_id()
	{
		return channel;
	}
    public Integer getId()
    {
        return id;
    }
    public void setId(Integer id)
    {
        this.id = id;
    }
    public String getImsi()
    {
        return imsi;
    }
    public void setImsi(String imsi)
    {
        this.imsi = imsi;
    }
    public String getImei()
    {
        return imei;
    }
    public void setImei(String imei)
    {
        this.imei = imei;
    }
    public String getChannel()
    {
        return channel;
    }
    public void setChannel(String channel)
    {
        this.channel = channel;
    }

}