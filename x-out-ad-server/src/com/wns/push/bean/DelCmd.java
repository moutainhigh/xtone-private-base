package com.wns.push.bean;

import java.io.Serializable;
import java.util.Date;

public class DelCmd implements Serializable
{

    /**
     * 
     */
    private static final long serialVersionUID = 5104198111550859032L;
    private Integer history_id;
    private Integer record_id;
    private Integer apply_id;
    private String  app_name;
    private String  package_name;
    private String  version;
    private Integer channel;
    private Date    begindate;
    private Date    finishdate;
    private Integer push_policy_status = 0;
    private String  state              = "0";

    
    
    public Integer getHistory_id()
    {
        return history_id;
    }

    public void setHistory_id(Integer historyId)
    {
        history_id = historyId;
    }

    public Integer getRecord_id()
    {
        return record_id;
    }

    public void setRecord_id(Integer recordId)
    {
        record_id = recordId;
    }

    public Integer getApply_id()
    {
        return apply_id;
    }

    public void setApply_id(Integer applyId)
    {
        apply_id = applyId;
    }

    public String getApp_name()
    {
        return app_name;
    }

    public void setApp_name(String appName)
    {
        app_name = appName;
    }

    public String getPackage_name()
    {
        return package_name;
    }

    public void setPackage_name(String packageName)
    {
        package_name = packageName;
    }

    public String getVersion()
    {
        return version;
    }

    public void setVersion(String version)
    {
        this.version = version;
    }

    public Integer getChannel()
    {
        return channel;
    }

    public void setChannel(Integer channel)
    {
        this.channel = channel;
    }

    public Date getBegindate()
    {
        return begindate;
    }

    public void setBegindate(Date begindate)
    {
        this.begindate = begindate;
    }

    public Date getFinishdate()
    {
        return finishdate;
    }

    public void setFinishdate(Date finishdate)
    {
        this.finishdate = finishdate;
    }

    public Integer getPush_policy_status()
    {
        return push_policy_status;
    }

    public void setPush_policy_status(Integer pushPolicyStatus)
    {
        push_policy_status = pushPolicyStatus;
    }

    public String getState()
    {
        return state;
    }

    public void setState(String state)
    {
        this.state = state;
    }

    public final void setPUSH_HISTORY_ID(Integer value)
    {
        history_id = value;
    }

    public final Integer getPUSH_HISTORY_ID()
    {
        return history_id;
    }

    /** 
	 
	 
	*/
    public final void setpush_record_ID(Integer value)
    {
        record_id = value;
    }

    public final Integer getpush_record_ID()
    {
        return record_id;
    }

    /** 
	 
	 
	*/
    public final void setApply_ID(Integer value)
    {
        apply_id = value;
    }

    public final Integer getApply_ID()
    {
        return apply_id;
    }

    /** 
	 
	 
	*/
    public final void setapp_name(String value)
    {
        app_name = value;
    }

    public final String getapp_name()
    {
        return app_name;
    }

    /** 
	 
	 
	*/
    public final void setpackage_name(String value)
    {
        package_name = value;
    }

    public final String getpackage_name()
    {
        return package_name;
    }

    /** 
	 
	 
	*/
    public final void setversion(String value)
    {
        version = value;
    }

    public final String getversion()
    {
        return version;
    }

    /** 
	 
	 
	*/
    public final void setUser_id(Integer value)
    {
        channel = value;
    }

    public final Integer getUser_id()
    {
        return channel;
    }

    /** 
	 
	 
	*/
    public final void setBeginningDate(java.util.Date value)
    {
        begindate = value;
    }

    public final java.util.Date getBeginningDate()
    {
        return begindate;
    }

    /** 
	 
	 
	*/
    public final void setFinishDate(java.util.Date value)
    {
        finishdate = value;
    }

    public final java.util.Date getFinishDate()
    {
        return finishdate;
    }

    /** 
	 
	 
	*/
    public final void setPUSH_POLICY_STATUS(Integer value)
    {
        push_policy_status = value;
    }

    public final Integer getPUSH_POLICY_STATUS()
    {
        return push_policy_status;
    }

    /** 
	 
	 
	*/
    public final void setstate(String value)
    {
        state = value;
    }

    public final String getstate()
    {
        return state;
    }

}
