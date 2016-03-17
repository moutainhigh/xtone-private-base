package com.wns2.base.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.wns2.tran.WnsPager;

public class ResponseBean
{

    public WnsPager getPager()
    {
        return pager;
    }

    public void setPager(WnsPager pager)
    {
        this.pager = pager;
    }

    public int getSrc()
    {
        return src;
    }

    public void setSrc(int src)
    {
        this.src = src;
    }

    public String getFunctionid()
    {
        return functionid;
    }

    public void setFunctionid(String functionid)
    {
        this.functionid = functionid;
    }

    public ResponseBean(int result)
    {
        this.result = result;
    }

    public ResponseBean(int result, int src)
    {
        this.result = result;
        this.src = src;
    }

    private String                                     functionid;
    /**
     * 处理结果
     */
    private int                                        src;
    /**
     * 处理结果
     */
    private int                                        result;
    /**
     * 返回的键值对数据
     */
    private Map<String, Object>                        body        = new HashMap<String, Object>();
    /**
     * 返回的结果集数据
     */
    private HashMap<String, List<Map<String, Object>>> dataSetBody = new HashMap<String, List<Map<String, Object>>>();

    private WnsPager                               pager;

    public int getResult()
    {
        return result;
    }

    public void setResult(int result)
    {
        this.result = result;
    }

    /**
     * 增加返回的数据集
     */
    public void addDataSet(String key, List<Map<String, Object>> obj)
    {
        dataSetBody.put(key, obj);
    }

    /**
     * 添加返回的数据集信息
     */
    public void addDataSetDate(String key, Map<String, Object> obj)
    {
        List<Map<String, Object>> list = dataSetBody.get(key);
        if (list == null)
        {
            list = new ArrayList<Map<String, Object>>();
        }
        list.add(obj);
        dataSetBody.put(key, list);
    }

    /**
     * 添加键值对信息
     */
    public void add(String key, Object obj)
    {
        if (obj != null && obj instanceof Boolean)
        {
            if ((Boolean) obj)
            {
                body.put(key, 1);
            }
            else
            {
                body.put(key, 0);
            }
        }
        else
        {
            body.put(key, obj);
        }
    }

    /**
     * 一次添加一个结果集到返回结果集中
     */
    public void add(Map<String, Object> hm)
    {
        body.putAll(hm);
    }

    public Object getValue(String key)
    {
        Object obj = body.get(key);
        if (obj == null)
        {
            return "";
        }
        return obj;
    }

    // public XMLParser getXmlValue(String key) {
    // XMLParser obj = (XMLParser) body.get(key);
    // if (obj != null) {
    // return obj;
    // }
    // return null;
    // }

    public Object getDataSetValue(String key)
    {
        return dataSetBody.get(key);
    }

    public Set<String> getBodyKeySet()
    {
        if (body != null)
        {
            return body.keySet();
        }
        return null;
    }

    public Set<String> getDataSetKeySet()
    {
        if (dataSetBody != null)
        {
            return dataSetBody.keySet();
        }
        return null;
    }
    
    public String toString()
    {
        if (body != null)
        {
            Set<String> setkey = body.keySet();
            Iterator<String> it = setkey.iterator();
            String key;
            StringBuffer sb = new StringBuffer();
            while (it.hasNext())
            {
                key = it.next();
                if (sb.length() > 0)
                {
                    sb.append("&");
                }
                sb.append(key);
                sb.append("=");
                sb.append(body.get(key));
            }
            return sb.toString();
        }
        return "";
    }
}
