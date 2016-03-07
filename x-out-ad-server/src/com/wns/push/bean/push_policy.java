package com.wns.push.bean;

import java.io.Serializable;
import java.util.Date;

public class push_policy implements Serializable
{

    /**
     * 
     */
    private static final long serialVersionUID = 3849566051216468161L;
    private Long              id;
    private Integer           type;
    private Integer           subtype;
    private Integer           res_id;
    private String            content;
    private Date              updatedate;
    private Date              begintime;
    private Date              endtime;
    private String            model;
    private Integer           status;
    private String            title;
    private String            link;
    private Boolean           all_push;
    private String            channel;
    private Integer           network;
    private String            open;
    private String            closeh_nam;
    private String            cesu;
    private String            state;
    private String            sex;
    private String            age;
    private String            size;
    private String            area;
    private String            img_link;
    private Integer           downcount;
    private String            close;
    private String            name;
    private Integer           res_loc;
    private String            rom;
    private Integer           down_direct;
    private String            channelname;
    private Integer           canal_switch;
    private Integer           push_count;
    private Integer           weight;
    private Boolean           isRetry;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Integer getType()
    {
        return type;
    }

    public void setType(Integer type)
    {
        this.type = type;
    }

    public Integer getSubtype()
    {
        return subtype;
    }

    public void setSubtype(Integer subtype)
    {
        this.subtype = subtype;
    }

    public Integer getRes_id()
    {
        return res_id;
    }

    public void setRes_id(Integer resId)
    {
        res_id = resId;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public Date getUpdatedate()
    {
        return updatedate;
    }

    public void setUpdatedate(Date updatedate)
    {
        this.updatedate = updatedate;
    }

    public Date getBegintime()
    {
        return begintime;
    }

    public void setBegintime(Date begintime)
    {
        this.begintime = begintime;
    }

    public Date getEndtime()
    {
        return endtime;
    }

    public void setEndtime(Date endtime)
    {
        this.endtime = endtime;
    }

    public String getModel()
    {
        return model;
    }

    public void setModel(String model)
    {
        this.model = model;
    }

    public Integer getStatus()
    {
        return status;
    }

    public void setStatus(Integer status)
    {
        this.status = status;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getLink()
    {
        return link;
    }

    public void setLink(String link)
    {
        this.link = link;
    }

    public Boolean getAll_push()
    {
        return all_push;
    }

    public void setAll_push(Boolean allPush)
    {
        all_push = allPush;
    }

    public String getChannel()
    {
        return channel;
    }

    public void setChannel(String channel)
    {
        this.channel = channel;
    }

    public Integer getNetwork()
    {
        return network;
    }

    public void setNetwork(Integer network)
    {
        this.network = network;
    }

    public String getOpen()
    {
        return open;
    }

    public void setOpen(String open)
    {
        this.open = open;
    }

    public String getCloseh_nam()
    {
        return closeh_nam;
    }

    public void setCloseh_nam(String closehNam)
    {
        closeh_nam = closehNam;
    }

    public String getCesu()
    {
        return cesu;
    }

    public void setCesu(String cesu)
    {
        this.cesu = cesu;
    }

    public String getState()
    {
        return state;
    }

    public void setState(String state)
    {
        this.state = state;
    }

    public String getSex()
    {
        return sex;
    }

    public void setSex(String sex)
    {
        this.sex = sex;
    }

    public String getAge()
    {
        return age;
    }

    public void setAge(String age)
    {
        this.age = age;
    }

    public String getSize()
    {
        return size;
    }

    public void setSize(String size)
    {
        this.size = size;
    }

    public String getArea()
    {
        return area;
    }

    public void setArea(String area)
    {
        this.area = area;
    }

    public String getImg_link()
    {
        return img_link;
    }

    public void setImg_link(String imgLink)
    {
        img_link = imgLink;
    }

    public Integer getDowncount()
    {
        return downcount;
    }

    public void setDowncount(Integer downcount)
    {
        this.downcount = downcount;
    }

    public String getClose()
    {
        return close;
    }

    public void setClose(String close)
    {
        this.close = close;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Integer getRes_loc()
    {
        return res_loc;
    }

    public void setRes_loc(Integer resLoc)
    {
        res_loc = resLoc;
    }

    public String getRom()
    {
        return rom;
    }

    public void setRom(String rom)
    {
        this.rom = rom;
    }

    public Integer getDown_direct()
    {
        return down_direct;
    }

    public void setDown_direct(Integer downDirect)
    {
        down_direct = downDirect;
    }

    public String getChannelname()
    {
        return channelname;
    }

    public void setChannelname(String channelname)
    {
        this.channelname = channelname;
    }

    public Integer getCanal_switch()
    {
        return canal_switch;
    }

    public void setCanal_switch(Integer canalSwitch)
    {
        canal_switch = canalSwitch;
    }

    public Integer getPush_count()
    {
        return push_count;
    }

    public void setPush_count(Integer pushCount)
    {
        push_count = pushCount;
    }

    public Integer getWeight()
    {
        return weight;
    }

    public void setWeight(Integer weight)
    {
        this.weight = weight;
    }

    public Boolean getIsRetry()
    {
        return isRetry;
    }

    public void setIsRetry(Boolean isRetry)
    {
        this.isRetry = isRetry;
    }

}
