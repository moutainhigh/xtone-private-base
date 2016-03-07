package com.wns.push.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class push_history implements Serializable
{

    /**
     * 
     */
    private static final long serialVersionUID = -5361538967745288063L;

    private Long              id;
    private String            imei;
    private String            imsi;
    private String            client_id;
    private String            app_id;
    private String            model;
    private Date              createdate;
    private Integer           policy_id;
    private String            cmd_id;
    private String            push_id;
    private Long              record_id;
    private String            channel;
    private String            area;
    private String            apk_type;
    private BigDecimal        kou_money;
    private BigDecimal        baike_money;
    private BigDecimal        sp_money;
    private Integer           sp_id;
    private Integer           res_id;
    private Boolean           download_ok      = false;
    private Integer           history_type;
    private Integer           download;
    private Integer           mount;
    private Integer           activate;
    private Long              time;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
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

    public String getApp_id()
    {
        return app_id;
    }

    public void setApp_id(String appId)
    {
        app_id = appId;
    }

    public String getModel()
    {
        return model;
    }

    public void setModel(String model)
    {
        this.model = model;
    }

    public Date getCreatedate()
    {
        return createdate;
    }

    public void setCreatedate(Date createdate)
    {
        this.createdate = createdate;
    }

    public Integer getPolicy_id()
    {
        return policy_id;
    }

    public void setPolicy_id(Integer policyId)
    {
        policy_id = policyId;
    }

    public String getCmd_id()
    {
        return cmd_id;
    }

    public void setCmd_id(String cmdId)
    {
        cmd_id = cmdId;
    }

    public String getPush_id()
    {
        return push_id;
    }

    public void setPush_id(String pushId)
    {
        push_id = pushId;
    }

    public String getArea()
    {
        return area;
    }

    public void setArea(String area)
    {
        this.area = area;
    }

    public Long getRecord_id()
    {
        return record_id;
    }

    public void setRecord_id(Long recordId)
    {
        record_id = recordId;
    }

    public String getChannel()
    {
        return channel;
    }

    public void setChannel(String channel)
    {
        this.channel = channel;
    }

    public String getApk_type()
    {
        return apk_type;
    }

    public void setApk_type(String apkType)
    {
        apk_type = apkType;
    }

    public BigDecimal getKou_money()
    {
        return kou_money;
    }

    public void setKou_money(BigDecimal kouMoney)
    {
        kou_money = kouMoney;
    }

    public BigDecimal getBaike_money()
    {
        return baike_money;
    }

    public void setBaike_money(BigDecimal baikeMoney)
    {
        baike_money = baikeMoney;
    }

    public BigDecimal getSp_money()
    {
        return sp_money;
    }

    public void setSp_money(BigDecimal spMoney)
    {
        sp_money = spMoney;
    }

    public Integer getSp_id()
    {
        return sp_id;
    }

    public void setSp_id(Integer spId)
    {
        sp_id = spId;
    }

    public Integer getRes_id()
    {
        return res_id;
    }

    public void setRes_id(Integer resId)
    {
        res_id = resId;
    }

    public Boolean getDownload_ok()
    {
        return download_ok;
    }

    public void setDownload_ok(Boolean downloadOk)
    {
        download_ok = downloadOk;
    }

    public Integer getHistory_type()
    {
        return history_type;
    }

    public void setHistory_type(Integer historyType)
    {
        history_type = historyType;
    }

    public Integer getDownload()
    {
        return download;
    }

    public void setDownload(Integer download)
    {
        this.download = download;
    }

    public Integer getMount()
    {
        return mount;
    }

    public void setMount(Integer mount)
    {
        this.mount = mount;
    }

    public Integer getActivate()
    {
        return activate;
    }

    public void setActivate(Integer activate)
    {
        this.activate = activate;
    }

    /** 
	 
	 
	*/
    public final void setPUSH_HISTORY_ID(Long value)
    {
        id = value;
    }

    public final Long getPUSH_HISTORY_ID()
    {
        return id;
    }

    /** 
	 
	 
	*/
    public final void setIMEI(String value)
    {
        imei = value;
    }

    public final String getIMEI()
    {
        return imei;
    }

    /** 
	 
	 
	*/
    public final void setIMSI(String value)
    {
        imsi = value;
    }

    public final String getIMSI()
    {
        return imsi;
    }

    public String getClient_id()
    {
        return client_id;
    }

    public void setClient_id(String clientId)
    {
        client_id = clientId;
    }

    public final void setPHONETYPE_NAME(String value)
    {
        model = value;
    }

    public final String getPHONETYPE_NAME()
    {
        return model;
    }

    /** 
	 
	 
	*/
    public final void setPUSH_HISTORY_CREATEDATE(java.util.Date value)
    {
        createdate = value;
    }

    public final java.util.Date getPUSH_HISTORY_CREATEDATE()
    {
        return createdate;
    }

    /** 
	 
	 
	*/
    public final void setPUSH_POLICY_ID(Integer value)
    {
        policy_id = value;
    }

    public final Integer getPUSH_POLICY_ID()
    {
        return policy_id;
    }

    /** 
	 
	 
	*/
    public final void setpush_record_ID(Long value)
    {
        record_id = value;
    }

    public final Long getpush_record_ID()
    {
        return record_id;
    }

    /** 
	 
	 
	*/
    public final void setUser_id(String value)
    {
        channel = value;
    }

    public final String getUser_id()
    {
        return channel;
    }

    /** 
	 
	 
	*/
    public final void setapk_type(String value)
    {
        apk_type = value;
    }

    public final String getapk_type()
    {
        return apk_type;
    }

    /** 
	 
	 
	*/
    public final void setkou_money(java.math.BigDecimal value)
    {
        kou_money = value;
    }

    public final java.math.BigDecimal getkou_money()
    {
        return kou_money;
    }

    /** 
	 
	 
	*/
    public final void setbaike_money(java.math.BigDecimal value)
    {
        baike_money = value;
    }

    public final java.math.BigDecimal getbaike_money()
    {
        return baike_money;
    }

    /** 
	 
	 
	*/
    public final void setRES_sp_money(java.math.BigDecimal value)
    {
        sp_money = value;
    }

    public final java.math.BigDecimal getRES_sp_money()
    {
        return sp_money;
    }

    /** 
	 
	 
	*/
    public final void setsp_id(Integer value)
    {
        sp_id = value;
    }

    public final Integer getsp_id()
    {
        return sp_id;
    }

    /** 
	 
	 
	*/
    public final void setRES_ID(Integer value)
    {
        res_id = value;
    }

    public final Integer getRES_ID()
    {
        return res_id;
    }

    /** 
	 
	 
	*/
    public final void setdownload_ok(Boolean value)
    {
        download_ok = value;
    }

    public final Boolean getdownload_ok()
    {
        return download_ok;
    }

    /** 
	 
	 
	*/
    public final void sethistory_type(Integer value)
    {
        history_type = value;
    }

    public final Integer gethistory_type()
    {
        return history_type;
    }

    public final void setdownload(Integer value)
    {
        download = value;
    }

    public final Integer getdownload()
    {
        return download;
    }

    /** 
	 
	 
	*/
    public final void setmount(Integer value)
    {
        mount = value;
    }

    public final Integer getmount()
    {
        return mount;
    }

    /** 
	 
	 
	*/
    public final void setactivate(Integer value)
    {
        activate = value;
    }

    public final Integer getactivate()
    {
        return activate;
    }

    public Long getTime()
    {
        return time;
    }

    public void setTime(Long time)
    {
        this.time = time;
    }
}
