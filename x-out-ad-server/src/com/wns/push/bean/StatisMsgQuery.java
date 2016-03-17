package com.wns.push.bean;

import java.io.Serializable;

public class StatisMsgQuery implements Serializable
{
    /**
     * 
     */
    private static final long serialVersionUID = -2992124659750991142L;

    private String                    msgid;
    private String                    msgname;
    private String                    channel;
    private Integer                   count;
    private String                    date;
    private Integer                   status;


    
    public String getMsgid()
    {
        return msgid;
    }

    public void setMsgid(String msgid)
    {
        this.msgid = msgid;
    }

    public String getMsgname()
    {
        return msgname;
    }

    public void setMsgname(String msgname)
    {
        this.msgname = msgname;
    }

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
