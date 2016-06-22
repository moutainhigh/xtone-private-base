package com.xiaoxiao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoxiao.dao.LotteryActivitiesDao;
import com.xiaoxiao.entity.LotteryActivities;

@Service
public class LotteryActivitiesService {

	@Autowired
	private LotteryActivitiesDao dao;
	
	/**
	 * 
	 * @return
	 */
	public List<LotteryActivities> getLotteryActivities(String appKey) {
		return dao.getLotteryActivities(appKey);
	}

}
