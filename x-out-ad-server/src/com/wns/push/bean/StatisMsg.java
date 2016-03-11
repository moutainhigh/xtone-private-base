package com.wns.push.bean;

import java.io.Serializable;
import java.util.Date;

public class StatisMsg implements Serializable
{
    /**
     * 
     */
    private static final long serialVersionUID = -2992124659750991142L;

    private Long                      id;
    private Long                      msgId;
    private String                    msgName;
    private String                    channel;
    private Integer                   count;
    private Date                      createdate;
    private Date                      updatedate;
    private Integer                   status;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getMsgId()
    {
        return msgId;
    }

    public void setMsgId(Long msgId)
    {
        this.msgId = msgId;
    }

    public String getMsgName()
    {
        return msgName;
    }

    public void setMsgName(String msgName)
    {
        this.msgName = msgName;
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

    public Date getCreatedate()
    {
        return createdate;
    }

    public void setCreatedate(Date createdate)
    {
        this.createdate = createdate;
    }

    public Date getUpdatedate()
    {
        return updatedate;
    }

    public void setUpdatedate(Date updatedate)
    {
        this.updatedate = updatedate;
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
