package com.wns.push.admin.bean;

import java.io.Serializable;
import java.util.Date;

public class WnsFile implements Serializable
{

    /**
     * 
     */
    private static final long serialVersionUID = -6764558519166860936L;


    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getFilename()
    {
        return filename;
    }

    public void setFilename(String filename)
    {
        this.filename = filename;
    }

    public String getFile()
    {
        return file;
    }

    public void setFile(String file)
    {
        this.file = file;
    }

    public Long getSize()
    {
        return size;
    }

    public void setSize(Long size)
    {
        this.size = size;
    }

    public Integer getDownnum()
    {
        return downnum;
    }

    public void setDownnum(Integer downnum)
    {
        this.downnum = downnum;
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

    private Integer id;
    private String filename;
    private String file;
    private Long size;
    private Integer downnum;
    private Integer status;
    private Date createdate;
    private Date updatedate;
}
