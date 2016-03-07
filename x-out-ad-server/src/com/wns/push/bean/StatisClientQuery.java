package com.wns.push.bean;

import java.io.Serializable;

public class StatisClientQuery implements Serializable
{
    /**
     * 
     */
    private static final long serialVersionUID = -2992124659750991142L;

    String                    channel;
    Integer                   count;
    String                      date;
    Integer                   status;

    public String getChannel()
    {
        return channel;
    }

    public void setChannel(String channel)
    {
        this.channel = channel;
    }

    public Integer getCount()
    {
        return count;
    }

    public void setCount(Integer count)
    {
        this.count = count;
    }

    public String getDate()
    {
        return date;
    }

    public void setDate(String date)
    {
        this.date = date;
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
