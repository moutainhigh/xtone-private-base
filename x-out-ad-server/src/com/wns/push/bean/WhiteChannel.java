package com.wns.push.bean;

import java.io.Serializable;

public class WhiteChannel implements Serializable
{
    /**
     * 
     */
    private static final long serialVersionUID = 4704330730743976495L;
    
    Integer id;
    String  channel;
    Integer status;

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getChannel()
    {
        return channel;
    }

    public void setChannel(String channel)
    {
        this.channel = channel;
    }

    public Integer getStatus()
    {
        return status;
    }

    public void setStatus(Integer status)
    {
        this.status = status;
    }

}
