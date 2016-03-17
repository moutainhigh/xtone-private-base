package com.wns.push.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wns.push.bean.push_history;
import com.wns.push.bean.push_policy;
import com.wns.push.bean.push_policy_ext;
import com.wns.push.dao.pushHistoryDao;
import com.wns.push.dao.pushPolicyDao;
import com.wns2.factory.WnsEnum;
import com.wns2.factory.WnsRedisFactory;
import com.wns2.factory.WnsTag;
import com.wns2.util.WnsSpringHelper;
import com.wns2.util.WnsUtil;

public class WnsPushTaskManager
{
    private static String                             allKey       = "all";
    private static final String                       REDIS_ID_KEY = WnsPushTaskManager.class
                                                                           .getName()
                                                                           + "_id_";

    private static List<push_policy>                  task;
    private static Long                               startTime    = 0L;
    private static Long                               endTime      = 0L;
    private static Map<String, List<push_policy_ext>> map;

    public static List<push_policy_ext> getPush(String clientId, String model,
            String area, String channel, Boolean wifi, String age, String sex,
            String romVersion, List<Map<String, Object>> historyMap)
    {
        long now = new Date().getTime();
        System.out.println("endtime====>"+endTime);
        System.out.println("nowtime====>"+now);
        if ((task == null) || (now > endTime))
        {
            if ((now > endTime) && (task != null))
            {
                deleteRedisCount(startTime, endTime);
            }

            startTime = WnsDateUtil.startTime();
            endTime = WnsDateUtil.endTime();
            pushPolicyDao dao = (pushPolicyDao) WnsSpringHelper
                    .getBean("dpushPolicyDao");

            task = dao.findAllByTime(WnsDateUtil.TimeToString(startTime),
                    WnsDateUtil.TimeToString(endTime));
            map = new HashMap<String, List<push_policy_ext>>();
            List<push_policy_ext> allList = new ArrayList<push_policy_ext>();
            for (push_policy item : task)
            {
                if (isExceedLimit(item))
                {
                    continue;
                }

                push_policy_ext extItem = new push_policy_ext(item);
                extItem.setDitstict("");
                extItem.setAdmin_status(1);
                allList.add(extItem);
            }
            System.out.println("allList.size=======>"+allList.size());
            System.out.println("allList.tostring=======>"+allList.toString());
            map.put(allKey, allList);

//            NgsteamWeightScheduler2 instance = NgsteamWeightScheduler2
//                    .getInstance();
//            instance.addTask(allList);
        }

        return filter(map.get(allKey), clientId, channel, wifi, historyMap);
    }

    @SuppressWarnings("unchecked")
    public static List<push_policy_ext> filter(List<push_policy_ext> list,
            String clientId, String channel, Boolean wifi, List<Map<String, Object>> historyMap)
    {
        List<push_policy_ext> retList = null;
        List<push_policy_ext> tmpList = null;
        // long curTime = new Date().getTime();
//        Map<Long, push_policy_ext> tmpMap = new HashMap<Long, push_policy_ext>();

        retList = new ArrayList<push_policy_ext>();
        tmpList = new ArrayList<push_policy_ext>();

//        if (!wifi)
//        {
//            return retList;
//        }

        if ((list != null) && !list.isEmpty())
        {
            pushHistoryDao historyDao = (pushHistoryDao) WnsSpringHelper
                    .getBean("dpushHistoryDao");
            List<push_history> historyList = historyDao
                    .findTodayByClientId(clientId);
            Map<Long, String> pushMap = null;

            Date last = null;
            for (push_history item : historyList)
            {
                String[] pushIds = item.getPush_id().split("\\:");

                if (last == null)
                {
                    last = item.getPUSH_HISTORY_CREATEDATE();
                }
                else
                {
                    if (last.compareTo(item.getPUSH_HISTORY_CREATEDATE()) < 0)
                    {
                        last = item.getPUSH_HISTORY_CREATEDATE();
                    }
                }

                for (String pushid : pushIds)
                {
                    if (pushMap == null)
                    {
                        pushMap = new HashMap<Long, String>();
                    }
                    pushMap.put(Long.parseLong(pushid), item.getClient_id());
                }
            }

            // for (push_policy_ext item : list)
            // {
            // if ((item.getBegintime().getTime() < curTime)
            // && (curTime < item.getEndtime().getTime()))
            // {
            // if ((pushMap == null)
            // || (pushMap.get(item.getId()) == null))
            // {
            // if ("huiyep".equals(channel))
            // {
            // if (item.getChannel().equals(channel))
            // {
            // retList.add(item);
            // }
            // }
            // else
            // {
            // if (("ALL".equalsIgnoreCase(item.getChannel()))
            // || (item.getChannel().equals(channel)))
            // {
            // retList.add(item);
            // }
            // }
            // }
            // }
            // }
            for (push_policy_ext item : list)
            {

                if ((pushMap == null) || (pushMap.get(item.getId()) == null))
                {
                    if ("huiyep".equals(channel))
                    {
                        if (item.getChannel().equals(channel))
                        {
                            retList.add(item);
                        }
                    }
                    else
                    {
                        if (("ALL".equalsIgnoreCase(item.getChannel()))
                                || (item.getChannel().equals(channel)))
                        {
                            if (wifi 
                                    && ((item.getNetwork() == WnsEnum.NETWORK_WIFI) || ((item.getNetwork() == WnsEnum.NETWORK_ALL))))
                            {
                                tmpList.add(item);
                            }
                            else if (!wifi && ((item.getNetwork() == WnsEnum.NETWORK_2G_3G) || ((item.getNetwork() == WnsEnum.NETWORK_ALL))))
                            {
                                tmpList.add(item);
                            }
                        }
                    }
                }
            }

            long dur = new Date().getTime();
            if (last != null)
            {
                dur = Math.abs(dur - last.getTime());
            }
            // if ((tmpList.size() > 0) && (dur > 60*60*1000))
            // {
            // // int i = NgsteamUtil.random(0, tmpList.size());
            // // retList.add(tmpList.get(i));
            // // NgsteamWeightScheduler instance = new
            // NgsteamWeightScheduler();
            // // instance.addTask(tmpList);
            // tmpList.contains(item);
            // retList.add(NgsteamWeightScheduler.getInstance().GetTask());
            // }
            if ((tmpList.size() > 0))
            {
                int i = WnsUtil.random(0, tmpList.size());
                push_policy_ext item = tmpList.get(i);
//                push_policy_ext item = NgsteamWeightScheduler2.getInstance()
//                        .getBestPolicy(tmpMap);
                if (item != null)
                {
                    item.setIsRetry(false);
                    retList.add(item);
                    String key = REDIS_ID_KEY + item.getId();
                    WnsRedisFactory.incr(key, WnsRedisFactory.ONE_DAY);
                    if (isExceedLimit(item))
                    {
                        WnsHttp.sendCleanTask();
                    }
                }
                if (tmpList.size() > 1)
                {
                    item = tmpList.get((i + 1) % tmpList.size());
                    if (item != null)
                    {
                        item.setIsRetry(false);
                        retList.add(item);
                        String key = REDIS_ID_KEY + item.getId();
                        WnsRedisFactory.incr(key, WnsRedisFactory.ONE_DAY);
                        if (isExceedLimit(item))
                        {
                            WnsHttp.sendCleanTask();
                        }
                    }
                }
                
//                if (tmpList.size() > 2)
//                {
//                    item = tmpList.get((i + 2) % tmpList.size());
//                    if (item != null)
//                    {
//                        item.setIsRetry(false);
//                        retList.add(item);
//                        String key = REDIS_ID_KEY + item.getId();
//                        NgsteamRedisFactory.incr(key, NgsteamRedisFactory.ONE_DAY);
//                        if (isExceedLimit(item))
//                        {
//                            NgsteamHttp.sendCleanTask();
//                        }
//                    }
//                }
            }
            else //重试取广告
            {
                String key = WnsPushTaskManager.class.getName() + "_retry_" + clientId + "_" + WnsDateUtil.startTime();
                Map<Integer, Integer> map = (Map<Integer, Integer>)WnsCacheFactory.get(key);
                if (map == null)
                {
                    map = new HashMap<Integer, Integer>();
                }
                if ((historyMap != null) && historyMap.size() > 0)
                {
                    for (Map<String, Object> item : historyMap)
                    {
                        Integer value = (Integer)item.get(WnsTag.msg_policy_idTag);
                        map.put(value, value);
                    }
                    WnsCacheFactory.add(key, map, WnsCacheFactory.ONE_DAY);
                    String str = historyList.get(historyList.size() - 1).getPush_id();
                    String[] pushids = str.split("\\:");
                    Integer id = Integer.parseInt(pushids[0]); 
                    if (map.get(id) == null)
                    {
                        for (push_policy_ext  item : list)
                        {
                            if (item.getId().intValue() == id.intValue())
                            {
                                item.setIsRetry(true);
                                retList.add(item);
                                break;
                            }
                        }
                    }
                    Integer id2 = 0;
                    if (pushids.length > 1)
                    {
                        id2 = Integer.parseInt(pushids[1]);
                        if (map.get(id2) == null)
                        {
                            for (push_policy_ext  item : list)
                            {
                                if (item.getId().intValue() == id2.intValue())
                                {
                                    item.setIsRetry(true);
                                    retList.add(item);
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }

        return retList;
    }

    private static boolean isExceedLimit(push_policy item)
    {
        boolean ret = false;
        String key = REDIS_ID_KEY + item.getId();
        String count = WnsRedisFactory.get(key);
        if (count != null)
        {
            Integer c = Integer.valueOf(count);
            if (c >= item.getPush_count())
            {
                ret = true;
            }
        }
        return ret;
    }

    private static void deleteRedisCount(Long start, Long end)
    {
        pushPolicyDao dao = (pushPolicyDao) WnsSpringHelper
                .getBean("dpushPolicyDao");

        List<push_policy> list = dao.findAllByTime(WnsDateUtil
                .TimeToString(start), WnsDateUtil.TimeToString(end));
        if (list == null)
        {
            return;
        }
        for (push_policy item : list)
        {
            String key = REDIS_ID_KEY + item.getId();
            WnsRedisFactory.del(key);
        }
    }

    public static void cleanTask()
    {
        task = null;
        map = null;
        startTime = 0L;
        endTime = 0L;
    }
}
