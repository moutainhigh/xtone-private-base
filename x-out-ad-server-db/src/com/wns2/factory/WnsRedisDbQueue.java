package com.wns2.factory;

import com.alibaba.fastjson.JSON;
import com.wns.push.bean.Client;
import com.wns.push.bean.push_history;
import com.wns.push.dao.ClientDao;
import com.wns.push.dao.pushHistoryDao;
import com.wns2.util.WnsSpringHelper;

public class WnsRedisDbQueue
{
    private static WnsRedisDbQueue instance = null;

    static
    {
        getInstance();
    }

    public static WnsRedisDbQueue getInstance()
    {
        if (instance == null)
        {
            instance = new WnsRedisDbQueue();
            instance.processRedis();
        }
        return instance;
    }


    public void processRedis()
    {
        new Thread(new Runnable()
        {
            public void run()
            {
                while (true)
                {
                    try
                    {
                        boolean has = false;
                        boolean has1, has2;
                        has1 = instance.insertPushClient(ClientDao.CACHE_KEY_INSERT);
                        has2 = instance.insertPushHistory(pushHistoryDao.CACHE_KEY_INSERT);

                        has = has1 || has2;
                        if (!has)
                        {
                            Thread.sleep(10000);
                            continue;
                        }
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
    
    public boolean insertPushClient(final String key)
    {
        Boolean has = WnsRedisFactory.exists(key);
        if ((has != null) && (has.booleanValue() == true))
        {
            String json = WnsRedisFactory.rpop(key);
            if (json != null)
            {
            	Client user = JSON.parseObject(json, Client.class);
            	ClientDao dao = (ClientDao) WnsSpringHelper
                        .getBean("dclientDao");
                if (user != null && user.getClient_id() != null)
                {
                	Client find = dao.findSingleByClient(user.getClient_id());
                	if (find == null){
                		dao.insert(user);
                	}
                }
            }
            return true;
        }
        return false;
    }
    
    public boolean insertPushHistory(final String key)
    {
        Boolean has = WnsRedisFactory.exists(key);
        if ((has != null) && (has.booleanValue() == true))
        {
            String json = WnsRedisFactory.rpop(key);
            if (json != null)
            {
            	push_history item = JSON.parseObject(json, push_history.class);
            	pushHistoryDao dao = (pushHistoryDao) WnsSpringHelper
                        .getBean("dpushHistoryDao");
                if (item != null)
                {
                    dao.Insert(item);
                }
            }
            return true;
        }
        return false;
    }

    public Long size(String key)
    {
        if (!WnsRedisFactory.exists(key))
            return 0l;
        return WnsRedisFactory.llen(key);
    }

}
