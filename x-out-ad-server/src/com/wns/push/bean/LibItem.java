package com.wns.push.bean;

import java.io.Serializable;

public class LibItem implements Serializable
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

    public void setName(String name)
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

    public String getCrc32()
    {
        return crc32;
    }

    public void setCrc32(String crc32)
    {
        this.crc32 = crc32;
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

    private Integer id;
    private String  name;
    private String  version;
    private Integer versioncode;
    private String crc32;
    private String  url;
    private String  channel;
    private String  phonetype;
    private Integer status;
}
