
package com.wns.push.admin.bean;


public class WnsApkFeature
{

    /**
     * 要的设备特性名称。
     */
    private String feature;

    /**
     * 表明所需特性的内容。
     */
    private String value;

    public WnsApkFeature()
    {
        super();
    }

    public WnsApkFeature(String feature, String value)
    {
        super();
        this.feature = feature;
        this.value = value;
    }

    public String getFeature()
    {
        return feature;
    }

    public void setFeature(String feature)
    {
        this.feature = feature;
    }

    public String getValue()
    {
        return value;
    }

    public void setValue(String value)
    {
        this.value = value;
    }

    @Override
    public String toString()
    {
        return "Feature [feature=" + feature + ", value=" + value + "]";
    }
}
