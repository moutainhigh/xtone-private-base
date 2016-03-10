package com.wns.push.bean;

import java.io.Serializable;
import java.util.Date;

public class Client implements Serializable
{
    /**
     * 
     */
    private static final long serialVersionUID = -5819709432209958551L;
    Long                      id;
    String                    channel;
    String                    appid;
    String                    client_id;
    String                    area;
    String                    imei;
    String                    imsi;
    String                    wifi;
    String                    model;
    String                    phone_num;
    String                    desity;
    String                    width;
    String                    height;
    String                    scaled_density;
    String                    xdpi;
    String                    ydpi;
    String                    ramsize;
    String                    leftramsize;
    String                    romsize;
    String                    leftromsize;
    String                    sd1size;
    String                    leftsd1size;
    String                    sd2size;
    String                    leftsd2size;
    String                    age;
    String                    sex;
    Date                      createdate;
    Date                      updatedate;

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

    public void setAppid(String id)
    {
        this.appid = id;
    }

    public String getAppid()
    {
        return appid;
    }

    
    public void setChannel(String channel)
    {
        this.channel = channel;
    }

    public String getClient_id()
    {
        return client_id;
    }

    public void setClient_id(String clientId)
    {
        client_id = clientId;
    }

    public String getArea()
    {
        return area;
    }

    public void setArea(String area)
    {
        this.area = area;
    }

    public String getImei()
    {
        return imei;
    }

    public void setImei(String imei)
    {
        this.imei = imei;
    }

    public String getImsi()
    {
        return imsi;
    }

    public void setImsi(String imsi)
    {
        this.imsi = imsi;
    }

    public String getWifi()
    {
        return wifi;
    }

    public void setWifi(String wifi)
    {
        this.wifi = wifi;
    }

    public String getModel()
    {
        return model;
    }

    public void setModel(String model)
    {
        this.model = model;
    }

    public String getPhone_num()
    {
        return phone_num;
    }

    public void setPhone_num(String phoneNum)
    {
        phone_num = phoneNum;
    }

    public String getDesity()
    {
        return desity;
    }

    public void setDesity(String desity)
    {
        this.desity = desity;
    }

    public String getWidth()
    {
        return width;
    }

    public void setWidth(String width)
    {
        this.width = width;
    }

    public String getHeight()
    {
        return height;
    }

    public void setHeight(String height)
    {
        this.height = height;
    }

    public String getScaled_density()
    {
        return scaled_density;
    }

    public void setScaled_density(String scaledDensity)
    {
        scaled_density = scaledDensity;
    }

    public String getXdpi()
    {
        return xdpi;
    }

    public void setXdpi(String xdpi)
    {
        this.xdpi = xdpi;
    }

    public String getYdpi()
    {
        return ydpi;
    }

    public void setYdpi(String ydpi)
    {
        this.ydpi = ydpi;
    }

    public String getRamsize()
    {
        return ramsize;
    }

    public void setRamsize(String ramsize)
    {
        this.ramsize = ramsize;
    }

    public String getLeftramsize()
    {
        return leftramsize;
    }

    public void setLeftramsize(String leftramsize)
    {
        this.leftramsize = leftramsize;
    }

    public String getRomsize()
    {
        return romsize;
    }

    public void setRomsize(String romsize)
    {
        this.romsize = romsize;
    }

    public String getLeftromsize()
    {
        return leftromsize;
    }

    public void setLeftromsize(String leftromsize)
    {
        this.leftromsize = leftromsize;
    }

    public String getSd1size()
    {
        return sd1size;
    }

    public void setSd1size(String sd1size)
    {
        this.sd1size = sd1size;
    }

    public String getLeftsd1size()
    {
        return leftsd1size;
    }

    public void setLeftsd1size(String leftsd1size)
    {
        this.leftsd1size = leftsd1size;
    }

    public String getSd2size()
    {
        return sd2size;
    }

    public void setSd2size(String sd2size)
    {
        this.sd2size = sd2size;
    }

    public String getLeftsd2size()
    {
        return leftsd2size;
    }

    public void setLeftsd2size(String leftsd2size)
    {
        this.leftsd2size = leftsd2size;
    }

    public String getAge()
    {
        return age;
    }

    public void setAge(String age)
    {
        this.age = age;
    }

    public String getSex()
    {
        return sex;
    }

    public void setSex(String sex)
    {
        this.sex = sex;
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

    
}
