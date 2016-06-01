package com.bird.service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.common.util.ConnectionService;

import com.alibaba.fastjson.JSON;
import com.bird.bean.LotteryBean;
import com.bird.dao.LotteryDao;

@WebServlet("/LotteryService")
public class LotteryService extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LotteryService() {
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
		
		

		LotteryDao dao = new LotteryDao();
		LotteryBean bean = dao.random(uid);
		response.getWriter().append(JSON.toJSONString(bean));

		// response.getWriter().append("Served at:
		// ").append(request.getContextPath());
	}

}
