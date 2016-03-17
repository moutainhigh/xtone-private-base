package com.wns.push.bean;

import java.io.Serializable;
import java.util.Date;

public class AdminUser implements Serializable
{

    /**
	 * 
	 */
    private static final long serialVersionUID = 5463407943802255296L;

    private Long              id;
    private String            name;
    private String            password;
    private Long              role;
    private Date              createdate;
    private Date              updatedate;
    private Integer           status;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public Long getRole()
    {
        return role;
    }

    public void setRole(Long role)
    {
        this.role = role;
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
