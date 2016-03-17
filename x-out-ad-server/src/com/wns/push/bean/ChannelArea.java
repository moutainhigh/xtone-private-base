package com.wns.push.bean;

import java.io.Serializable;
import java.util.Date;

public class ChannelArea implements Serializable
{

    /**
	 * 
	 */
    private static final long serialVersionUID = 5463407943802255296L;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
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

    public String getArea()
    {
        return area;
    }

    public void setArea(String area)
    {
        this.area = area;
    }

    public Integer getStatus()
    {
        return status;
    }

    public void setStatus(Integer status)
    {
        this.status = status;
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

    private Long    id;
    private String  channel;
    private String  area;
    private Integer status;
    private Date    createdate;
    private Date    updatedate;
}
