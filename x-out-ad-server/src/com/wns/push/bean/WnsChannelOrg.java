package com.wns.push.bean;

import java.io.Serializable;
import java.util.Date;

public class WnsChannelOrg implements Serializable
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 8600171038056056297L;
	/**
     * 
     */

    private Long              id;
    private String            channel;
    private Integer           status;
    private String 			  channelname;
    private String 			  key;
    private Long 			  orgid;
    private Date              createdate;
    private Date              updatedate;

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

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }

	public String getChannelname() {
		return channelname;
	}

	public void setChannelname(String channelname) {
		this.channelname = channelname;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Long getOrgid() {
		return orgid;
	}

	public void setOrgid(Long orgid) {
		this.orgid = orgid;
	}

}
