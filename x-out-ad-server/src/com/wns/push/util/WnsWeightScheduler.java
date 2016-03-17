package com.wns.push.util;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.wns.push.bean.push_policy_ext;

/**
 * 权重轮询调度算法(WeightedRound-RobinScheduling)-Java实现
 * 
 * 
 * */
public class WnsWeightScheduler
{

    private int               currentIndex  = -1; // 上一次选择的任务
    private int               currentWeight = 0; // 当前调度的权值
    private int               maxWeight     = 0; // 最大权重
    private int               gcdWeight     = 0; // 所有任务权重的最大公约数
    private int               taskCount   = 0; // 任务数量
    private List<push_policy_ext> taskList;        // 任务集合
    
    private static WnsWeightScheduler instance = null;

    
    public static WnsWeightScheduler getInstance()
    {
        if (instance == null)
        {
            instance = new WnsWeightScheduler();
        }
        return instance;
    }
    
    
    /**
     * 返回最大公约数
     * 
     * @param a
     * @param b
     * @return
     */
    private static int gcd(int a, int b)
    {
        BigInteger b1 = new BigInteger(String.valueOf(a));
        BigInteger b2 = new BigInteger(String.valueOf(b));
        BigInteger gcd = b1.gcd(b2);
        return gcd.intValue();
    }

    /**
     * 返回所有任务权重的最大公约数
     * 
     * @param serverList
     * @return
     */
    private static int getGCD(List<push_policy_ext> task)
    {
        int w = 0;
        for (int i = 0, len = task.size(); i < len - 1; i++)
        {
            if (w == 0)
            {
                w = gcd(task.get(i).getWeight(), task.get(i + 1).getWeight());
            }
            else
            {
                w = gcd(w, task.get(i + 1).getWeight());
            }
        }
        return w;
    }

    /**
     * 返回所有任务中的最大权重
     * 
     * @param serverList
     * @return
     */
    public static int getMaxWeight(List<push_policy_ext> task)
    {
        int w = 0;
        for (int i = 0, len = task.size(); i < len - 1; i++)
        {
            if (w == 0)
            {
                w = Math.max(task.get(i).getWeight(),
                        task.get(i + 1).getWeight());
            }
            else
            {
                w = Math.max(w, task.get(i + 1).getWeight());
            }
        }
        return w;
    }

    /**
     * 算法流程： 假设有一组服务器 S = {S0, S1, …, Sn-1} 有相应的权重，变量currentIndex表示上次选择的服务器
     * 权值currentWeight初始化为0，currentIndex初始化为-1 ，当第一次的时候返回 权值取最大的那个服务器， 通过权重的不断递减
     * 寻找 适合的服务器返回，直到轮询结束，权值返回为0
     */
    public synchronized push_policy_ext GetTask(Map<Long, push_policy_ext> map)
    {
        if (map == null || map.size() <= 0)
        {
            return null;
        }
        
        while (true)
        {
            currentIndex = (currentIndex + 1) % taskCount;
            if (currentIndex == 0)
            {
                currentWeight = currentWeight - gcdWeight;
                if (currentWeight <= 0)
                {
                    currentWeight = maxWeight;
                    if (currentWeight == 0)
                        return null;
                }
            }
            push_policy_ext item = taskList.get(currentIndex);
            if ((item.getWeight() >= currentWeight) && (map.get(item.getId()) != null))
            {
                return taskList.get(currentIndex);
            }
        }
    }

    public void addTask(List<push_policy_ext> task)
    {
        if (task == null)
        {
            return;
        }
        taskList = new ArrayList<push_policy_ext>();
        for (push_policy_ext item : task)
        {
            taskList.add(item);
        }
        currentIndex = -1;
        currentWeight = 0;
        taskCount = taskList.size();
        maxWeight = getMaxWeight(taskList);
        gcdWeight = getGCD(taskList);
    }
}
