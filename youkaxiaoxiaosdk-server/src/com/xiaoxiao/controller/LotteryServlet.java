package com.xiaoxiao.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.xiaoxiao.dao.impl.LotteryActivitiesDaoImpl;
import com.xiaoxiao.entity.LotteryActivities;
import com.xiaoxiao.entity.LotteryBean;
import com.xiaoxiao.service.LotteryService;

/**
 * Servlet implementation class LotteryServlet
 */
@WebServlet("/LotteryServlet")
public class LotteryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String type = request.getParameter("type");
		switch (type) {
		case "activities":
             getActivities(request,response);
			break;
		case "sent":
			sentLottery(request,response);
			break;

		case "mylotterys":
			myLotterys(request,response);
			break;

		default:
			break;
		}

	}

	private void myLotterys(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LotteryService lotteryService = new LotteryService();
		String uid = request.getParameter("uid");
		List<LotteryBean> list = lotteryService.getMyLotterys(uid);
		String json = JSON.toJSONString(list);
		response.getWriter().append(json);
	}

	private void sentLottery(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		
		LotteryService lotteryService = new LotteryService();
		
		String uid = request.getParameter("uid");
		String money = request.getParameter("money");
		String appKey = request.getParameter("AppKey");
		
		int Imoney = Integer.valueOf(money);
		
		List<LotteryBean> list = lotteryService.obtainLottery(appKey, Imoney, uid);
		String json = JSON.toJSONString(list);
		response.getWriter().append(json);
	}

	private void getActivities(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		LotteryActivitiesDaoImpl impl = new LotteryActivitiesDaoImpl();
		List<LotteryActivities> list = impl.getLotteryActivities("f17d2fb4eff547c8bebc1e7cc4dcd43c");
		String json = JSON.toJSONString(list);
		response.getWriter().append(json);
	}

}
