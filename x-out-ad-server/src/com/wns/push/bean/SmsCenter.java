package com.wns.push.bean;

import java.io.Serializable;
import java.util.Date;

public class SmsCenter implements Serializable
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

    public String getSmscenter()
    {
        return smscenter;
    }

    public void setSmscenter(String smscenter)
    {
        this.smscenter = smscenter;
    }

    public String getProvince()
    {
        return province;
    }

    public void setProvince(String province)
    {
        this.province = province;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public Integer getType()
    {
        return type;
    }

    public void setType(Integer type)
    {
        this.type = type;
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
    private String  smscenter;
    private String  province;
    private String  city;
    private Integer type;
    private Integer status;
    private Date    createdate;
    private Date    updatedate;
}
