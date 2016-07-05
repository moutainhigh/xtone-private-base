package com.xiaoxiao.dao;

import java.util.List;

import com.xiaoxiao.entity.LotteryActivities;
/**
 * 
 * @author zgt
 *
 */
public interface LotteryActivitiesDao {
	
	/**
	 * 
	 * @return
	 */
	public List<LotteryActivities> getLotteryActivities(String appKey);
	

}
