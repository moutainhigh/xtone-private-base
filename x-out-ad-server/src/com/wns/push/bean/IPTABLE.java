package com.wns.push.bean;

import java.io.Serializable;

public class IPTABLE implements Serializable
{

    /**
     * 
     */
    private static final long serialVersionUID = -9212932386087512962L;
    private Integer           iD;
    private Long              startIPNum       = 0L;
    private String            startIPText;
    private Long              endIPNum         = 0L;
    private String            endIPText;
    private String            country;
    private String            local;

    /** 
	 
	 
	*/
    public final void setID(Integer value)
    {
        iD = value;
    }

    public final Integer getID()
    {
        return iD;
    }

    /** 
	 
	 
	*/
    public final void setStartIPNum(Long value)
    {
        startIPNum = value;
    }

    public final Long getStartIPNum()
    {
        return startIPNum;
    }

    /** 
	 
	 
	*/
    public final void setStartIPText(String value)
    {
        startIPText = value;
    }

    public final String getStartIPText()
    {
        return startIPText;
    }

    /** 
	 
	 
	*/
    public final void setEndIPNum(Long value)
    {
        endIPNum = value;
    }

    public final Long getEndIPNum()
    {
        return endIPNum;
    }

    /** 
	 
	 
	*/
    public final void setEndIPText(String value)
    {
        endIPText = value;
    }

    public final String getEndIPText()
    {
        return endIPText;
    }

    /** 
	 
	 
	*/
    public final void setCountry(String value)
    {
        country = value;
    }

    public final String getCountry()
    {
        return country;
    }

    /** 
	 
	 
	*/
    public final void setLocal(String value)
    {
        local = value;
    }

    public final String getLocal()
    {
        return local;
    }

}
