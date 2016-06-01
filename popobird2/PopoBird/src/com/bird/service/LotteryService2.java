package com.bird.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.bird.bean.LotteryBean;
import com.bird.dao.LotteryDao;

@WebServlet("/LotteryService2")
public class LotteryService2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LotteryService2() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("utf-8");
		String uid = request.getParameter("uid");

		String method = request.getParameter("method");
		if (uid == null || "".equals(uid)) {
			return;
		}
		if (method.equals("obtainLottery")) {
			LotteryDao dao = new LotteryDao();
			List<LotteryBean> list = dao.obtainLottery(uid);
			response.getWriter().append(JSON.toJSONString(list));
		}  
		
		if(method.equals("receiveUserId")){
			LotteryDao dao = new LotteryDao();
			List<LotteryBean> list = dao.findByReceiveUserId(uid);
			response.getWriter().append(JSON.toJSONString(list));
		}
		
	}

}
