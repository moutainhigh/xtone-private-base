package com.wns2.factory;

import com.alibaba.fastjson.JSON;

public class WnsRedisDbQueue
{
    private static WnsRedisDbQueue instance       = null;

    public static WnsRedisDbQueue getInstance()
    {
        if (instance == null)
        {
            instance = new WnsRedisDbQueue();
        }
        return instance;
    }

    public Long push(String key, Object obj)
    {
        return WnsRedisFactory.lpush(key, toJson(obj));
    }

    public String toJson(Object obj)
    {
        return JSON.toJSONString(obj);
    }

}
