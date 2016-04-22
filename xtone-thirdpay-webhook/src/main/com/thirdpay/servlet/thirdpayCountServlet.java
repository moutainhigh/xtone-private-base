package com.thirdpay.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.common.util.ThreadPool;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.thirdpay.domain.LogInsert;

/**
 * 第三发支付统计(未使用)
 */

@WebServlet("/thirdpayCountServlet")
public class thirdpayCountServlet extends HttpServlet {
	// private static Logger logger =
	// Logger.getLogger(ThirdpayCountServlet.class);

	// private String clickToUrl;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public thirdpayCountServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String xx_notifyData = request.getParameter("xx_notifyData");

		if (xx_notifyData != null) {

			JSONObject json = JSON.parseObject(xx_notifyData); // 解析自定义参数
			String releaseChannel = json.getString("channel");// 发行通道ID，一般从payInfo中解析出
			String appKey = json.getString("appkey"); // CP方ID，一般从payInfo中解析出
			String payChannel = json.getString("platform"); // 支付通道channel

			System.out.println("channel = " + releaseChannel + "\n" + " , appKey = " + appKey + "\n" + " , platform = "
					+ payChannel);

			if (payChannel.equals("unionpay")) {
				System.out.println("调用银联支付回调统计");
				UnionpayCountServlet.requestPostData(request, response);
			} else if (payChannel.equals("alipay")) {
				System.out.println("调用支付宝支付回调统计");
				AlipayCountServlet.requestPostData(request, response);
			} else if (payChannel.equals("wechat")) {
				System.out.println("调用微信支付回调统计");
				WechatpayCountServlet.requestPostData(request, response);
			} else if (payChannel.equals("baidu")) {
				System.out.println("调用百度支付回调统计");
				// AlipayCountServlet.requestPostData(request,response);
			}

		} else {

			System.out.println("找不到channel,appkey,platform");

		}

		// response.getWriter().append("success");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);

	}

}
