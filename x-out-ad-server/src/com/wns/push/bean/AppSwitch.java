package com.wns.push.bean;

import java.io.Serializable;

public class AppSwitch implements Serializable
{
    /**
     * 
     */
    private static final long serialVersionUID = 4704330730743976495L;

    private Long              id;
    private String              app_id;
    private String            appname;
    private Integer           status;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }


    public String getApp_id()
    {
        return app_id;
    }

    public void setApp_id(String appId)
    {
        app_id = appId;
    }

    public String getAppname()
    {
        return appname;
    }

    public void setAppname(String appname)
    {
        this.appname = appname;
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
