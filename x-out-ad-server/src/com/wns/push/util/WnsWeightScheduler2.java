package com.wns.push.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.wns.push.bean.push_policy_ext;

/**
 * 权重轮询调度算法(WeightedRound-RobinScheduling)-Java实现
 * 
 * */
public class WnsWeightScheduler2
{

    private List<Item>                     itemList;
    private List<push_policy_ext>          policyList;

    private static WnsWeightScheduler2 instance = null;

    public static WnsWeightScheduler2 getInstance()
    {
        if (instance == null)
        {
            instance = new WnsWeightScheduler2();
        }
        return instance;
    }

    public synchronized push_policy_ext getBestPolicy(Map<Long, push_policy_ext> map)
    {
        Item item = null;
        Item best = null;
        int total = 0;
        for (int i = 0, len = itemList.size(); i < len; i++)
        {
            item = itemList.get(i);

            if (map.get(item.getId()) == null)
            {
                continue;
            }

            item.curWeight += item.effectWeight;
            total += item.effectWeight;

            if (item.effectWeight < item.weight)
            {
                item.effectWeight++;
            }

            if (best == null || item.curWeight > best.curWeight)
            {
                best = item;
            }

        }

        if (best == null)
        {
            return null;
        }

        best.curWeight -= total;
        return policyList.get(best.getIndex());
    }

    class Item
    {
        public int  index;
        public long id;
        public int  weight;
        public int  effectWeight;
        public int  curWeight;

        public Item(int index, long id, int weight)
        {
            super();
            this.index = index;
            this.id = id;
            this.weight = weight;
            this.effectWeight = this.weight;
            this.curWeight = 0;
        }

        public int getIndex()
        {
            return index;
        }

        public void setIndex(int index)
        {
            this.index = index;
        }

        public long getId()
        {
            return id;
        }

        public void setId(long id)
        {
            this.id = id;
        }

        public int getWeight()
        {
            return weight;
        }

        public void setWeight(int weight)
        {
            this.weight = weight;
        }

        public int getEffectiveWeight()
        {
            return effectWeight;
        }

        public void setEffectiveWeight(int effectiveWeight)
        {
            this.effectWeight = effectiveWeight;
        }

        public int getCurrentWeight()
        {
            return curWeight;
        }

        public void setCurrentWeight(int currentWeight)
        {
            this.curWeight = currentWeight;
        }

    }

    public void addTask(List<push_policy_ext> taskList)
    {
        if ((taskList == null) || (taskList.size() <= 0))
        {
            return;
        }

        itemList = new ArrayList<Item>();
        policyList = new ArrayList<push_policy_ext>();
        for (int i = 0; i < taskList.size(); i++)
        {
            push_policy_ext policy = taskList.get(i);
            Item item = new Item(i, policy.getId(), policy.getWeight());
            itemList.add(item);
            policyList.add(policy);
        }
    }

}
