package com.thirdpay.servlet;

import java.io.IOException;
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
import com.thirdpay.domain.PayInfoBean;

/**
 * Servlet implementation class AlipayCountServlet
 */
@WebServlet("/AlipayCountServlet")
public class AlipayCountServlet extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//	private static Logger logger = Logger.getLogger(thirdpayCountServlet.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AlipayCountServlet() {
		super();
		// TODO Auto-generated constructor stub

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		requestPostData(request);
		response.getWriter().append("success");
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

	/**
	 * 打印数据
	 * 
	 * @param request
	 * @return
	 */
	public static String requestPostData(HttpServletRequest request) {
		
		String xx_notifyData = request.getParameter("xx_notifyData");// 自定义参数
		JSONObject json = JSON.parseObject(xx_notifyData); // 解析自定义参数
		
		int price = (int) ((Float.parseFloat(request.getParameter("price"))) * 100);
		String payChannel = json.getString("platform");// 支付通道channel
		String ip = request.getHeader("X-Real-IP") != null ? request.getHeader("X-Real-IP") : request.getRemoteAddr();// 来源ip
		String payInfo = getPayInfo(request); // 从支付通道获取的原始内容
		String releaseChannel = json.getString("channel");// 发行通道ID，一般从payInfo中解析出
		String appKey = json.getString("appkey");// CP方ID，一般从payInfo中解析出
		String payChannelOrderId = request.getParameter("out_trade_no");// 支付通道的订单号，一般从payInfo中解析出

		String ownUserId = request.getParameter("ownUserId");// 付费用户ID，待用
		String ownItemId = request.getParameter("ownItemId");// 购买道具ID，待用
		String ownOrderId = request.getParameter("ownOrderId");// 原始订单号ID，待用
		int testStatus = 1;// 是否是测试信息

		System.out.println("payChannel = " + payChannel + "appKey = " + appKey + "payChannelOrderId = "
				+ payChannelOrderId + "price=" + price + "Ip = " + ip);

		// 数据库写入操作
		ThreadPool.mThreadPool.execute(new PayInfoBean(price, payChannel, ip, payInfo, releaseChannel, appKey,
				payChannelOrderId, ownUserId, ownItemId, ownOrderId, testStatus));

		return "";
	}

	/**
	 * 得到所有的参数与参数值
	 * 
	 * @param request
	 * @return
	 */
	public static String getPayInfo(HttpServletRequest request) {
		String payInfo = "";
		// 测试用数据
		Map<String, String[]> map = request.getParameterMap();

		Iterator<Entry<String, String[]>> iterator = map.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<java.lang.String, java.lang.String[]> entry = (Map.Entry<java.lang.String, java.lang.String[]>) iterator
					.next();

			String key = entry.getKey(); // key为参数名称
			String[] value = map.get(key); // value为参数值

			// logger.info(key);

			for (int i = 0; i < value.length; i++) {
				// System.out.println(value[i]);
				payInfo += key + "=" + value[i] + ";";

				// logger.info(value[i]);

			}

		}
		return payInfo;
	}
}
