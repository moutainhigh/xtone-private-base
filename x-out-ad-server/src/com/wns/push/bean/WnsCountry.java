package com.wns.push.bean;

import java.io.Serializable;

public class WnsCountry implements Serializable
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

    public String getMcc()
    {
        return mcc;
    }

    public void setMcc(String mcc)
    {
        this.mcc = mcc;
    }

    public String getMnc()
    {
        return mnc;
    }

    public void setMnc(String mnc)
    {
        this.mnc = mnc;
    }

    public String getCountry()
    {
        return country;
    }

    public void setCountry(String country)
    {
        this.country = country;
    }

    public String getUnit()
    {
        return unit;
    }

    public void setUnit(String unit)
    {
        this.unit = unit;
    }

    public String getUnitname()
    {
        return unitname;
    }

    public void setUnitname(String unitname)
    {
        this.unitname = unitname;
    }

    public String getTimezone()
    {
        return timezone;
    }

    public void setTimezone(String timezone)
    {
        this.timezone = timezone;
    }

    public String getCcode()
    {
        return ccode;
    }

    public void setCcode(String ccode)
    {
        this.ccode = ccode;
    }

    public String getLanguage()
    {
        return language;
    }

    public void setLanguage(String language)
    {
        this.language = language;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getEname()
    {
        return ename;
    }

    public void setEname(String ename)
    {
        this.ename = ename;
    }

    public String getLocalname()
    {
        return localname;
    }

    public void setLocalname(String localname)
    {
        this.localname = localname;
    }

    public String getRemark()
    {
        return remark;
    }

    public void setRemark(String remark)
    {
        this.remark = remark;
    }

    private Long   id;
    private String mcc;
    private String mnc;
    private String country;
    private String unit;
    private String unitname;
    private String timezone;
    private String ccode;
    private String language;
    private String name;
    private String ename;
    private String localname;
    private String remark;
}
