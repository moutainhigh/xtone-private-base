package com.wns.push.admin.bean;

import java.io.Serializable;
import java.sql.Date;

public class WnsApp implements Serializable
{

    /**
     * 
     */
    private static final long serialVersionUID = -6422349735301865887L;

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getAppid()
    {
        return appid;
    }

    public void setAppid(String appid)
    {
        this.appid = appid;
    }

    public Integer getType()
    {
        return type;
    }

    public void setType(Integer type)
    {
        this.type = type;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDesc()
    {
        return desc;
    }

    public void setDesc(String desc)
    {
        this.desc = desc;
    }

    public String getPkgname()
    {
        return pkgname;
    }

    public void setPkgname(String pkgname)
    {
        this.pkgname = pkgname;
    }

    public Long getOrgid()
    {
        return orgid;
    }

    public void setOrgid(Long orgid)
    {
        this.orgid = orgid;
    }

    public String getFilename()
    {
        return filename;
    }

    public void setFilename(String filename)
    {
        this.filename = filename;
    }

    public Long getSize()
    {
        return size;
    }

    public void setSize(Long size)
    {
        this.size = size;
    }

    public String getIcon()
    {
        return icon;
    }

    public void setIcon(String icon)
    {
        this.icon = icon;
    }

    public String getFile()
    {
        return file;
    }

    public void setFile(String file)
    {
        this.file = file;
    }

    public String getVersion()
    {
        return version;
    }

    public void setVersion(String version)
    {
        this.version = version;
    }

    public String getVersionname()
    {
        return versionname;
    }

    public void setVersionname(String versionname)
    {
        this.versionname = versionname;
    }

    public String getAndroidversion()
    {
        return androidversion;
    }

    public void setAndroidversion(String androidversion)
    {
        this.androidversion = androidversion;
    }

    public String getAuthor()
    {
        return author;
    }

    public void setAuthor(String author)
    {
        this.author = author;
    }

    public Integer getStar()
    {
        return star;
    }

    public void setStar(Integer star)
    {
        this.star = star;
    }

    public Integer getPrice()
    {
        return price;
    }

    public void setPrice(Integer price)
    {
        this.price = price;
    }

    public String getSign()
    {
        return sign;
    }

    public void setSign(String sign)
    {
        this.sign = sign;
    }

    public Integer getDownnum()
    {
        return downnum;
    }

    public void setDownnum(Integer downnum)
    {
        this.downnum = downnum;
    }

    public String getKeyword()
    {
        return keyword;
    }

    public void setKeyword(String keyword)
    {
        this.keyword = keyword;
    }

    public Integer getStatus()
    {
        return status;
    }

    public void setStatus(Integer status)
    {
        this.status = status;
    }

    public Date getCreatetime()
    {
        return createtime;
    }

    public void setCreatetime(Date createtime)
    {
        this.createtime = createtime;
    }

    public Date getUpdatetime()
    {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime)
    {
        this.updatetime = updatetime;
    }

    private Integer id;
    private String  appid;
    private Integer type;
    private String  name;
    private String  desc;
    private String  pkgname;
    private Long    orgid;
    private String  filename;
    private Long    size;
    private String  icon;
    private String  file;
    private String  version;
    private String  versionname;
    private String  androidversion;
    private String  sign;
    private String  keyword;
    private String  author;
    private Integer star;
    private Integer price;
    private Integer downnum;
    private Integer status;
    private Date    createtime;
    private Date    updatetime;
}
