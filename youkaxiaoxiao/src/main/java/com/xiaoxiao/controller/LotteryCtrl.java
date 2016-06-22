package com.xiaoxiao.controller;

import java.util.List;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiaoxiao.entity.LotteryActivities;
import com.xiaoxiao.entity.LotteryBean;
import com.xiaoxiao.service.LotteryActivitiesService;
import com.xiaoxiao.service.LotteryService;

/**
 * 彩票
 * @author zgt
 *
 */
@Controller
@RequestMapping(value = "/lotteryctrl")
public class LotteryCtrl {
	
	@Autowired
	private LotteryActivitiesService lotteryActivitiesService;
	
	
	@Autowired
	private LotteryService lotteryService;
	
	
	
	
	
	//活动  获取 AppKey
	@RequestMapping(value="/activities")
	@ResponseBody
	public List<LotteryActivities> obtainTop5Lottery (HttpServletRequest request, HttpServletResponse response){
		String appKey = request.getParameter("AppKey");
		
		List<LotteryActivities> list=lotteryActivitiesService.getLotteryActivities(appKey);
		return list;
	}
	
	
	@RequestMapping(value="/sent")
	@ResponseBody
	public List<LotteryBean> sentLottery (HttpServletRequest request, HttpServletResponse response){
		String uid = request.getParameter("uid");
		String money = request.getParameter("money");
		String appKey = request.getParameter("AppKey");
		
		int Imoney = Integer.valueOf(money);
		
		List<LotteryBean> list = lotteryService.obtainLottery(appKey, Imoney, uid);
		return list;
	}
	
	
	@RequestMapping(value="/mylotterys")
	@ResponseBody
	public List<LotteryBean> myLotterys (HttpServletRequest request, HttpServletResponse response){
		String uid = request.getParameter("uid");
		List<LotteryBean> list = lotteryService.getMyLotterys(uid);
		return list;
	}
	
	

	
	

}
