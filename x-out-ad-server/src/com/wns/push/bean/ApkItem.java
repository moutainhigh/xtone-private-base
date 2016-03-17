package com.wns.push.bean;

import java.io.Serializable;

public class ApkItem implements Serializable
{

    /**
	 * 
	 */
    private static final long serialVersionUID = 5463407943802255296L;

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name
    		
    )
    {
        this.name = name;
    }

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    public String getChannel()
    {
        return channel;
    }

    public void setChannel(String channel)
    {
        this.channel = channel;
    }

    public String getPhonetype()
    {
        return phonetype;
    }

    public void setPhonetype(String phonetype)
    {
        this.phonetype = phonetype;
    }

    public Integer getStatus()
    {
        return status;
    }

    public void setStatus(Integer status)
    {
        this.status = status;
    }

    public Integer getSize()
    {
        return size;
    }

    public void setSize(Integer size)
    {
        this.size = size;
    }

    public String getVersion()
    {
        return version;
    }

    public void setVersion(String version)
    {
        this.version = version;
    }

    public Integer getVersioncode()
    {
        return versioncode;
    }

    public void setVersioncode(Integer versioncode)
    {
        this.versioncode = versioncode;
    }

    public String getPkgname()
    {
        return pkgname;
    }

    public void setPkgname(String pkgname)
    {
        this.pkgname = pkgname;
    }

    public String getSign()
    {
        return sign;
    }

    public void setSign(String sign)
    {
        this.sign = sign;
    }

    private Integer id;
    private String  name;
    private String  pkgname;
    private String  sign;
    private Integer size;
    private String  version;
    private Integer versioncode;
    private String  url;
    private String  channel;
    private String  phonetype;
    private Integer status;
}
