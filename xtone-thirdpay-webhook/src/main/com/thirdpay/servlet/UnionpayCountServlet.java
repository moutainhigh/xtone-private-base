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
import com.thirdpay.utils.CheckPayInfo;
import com.thirdpay.utils.payConstants;

/**
 * 银联统计Servlet
 */
@WebServlet("/UnionpayCountServlet")
public class UnionpayCountServlet extends HttpServlet {
	private static final Logger LOG = Logger.getLogger(UnionpayCountServlet.class);
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UnionpayCountServlet() {
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
		requestPostData(request, response);
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
	public static String requestPostData(HttpServletRequest request, HttpServletResponse response) {
		LOG.info("访问了银联UnionpayCountServlet  - --- - - - - - - - - - -- - - ");
		String xx_notifyData = request.getParameter("xx_notifyData");// 自定义参数

		JSONObject json = JSON.parseObject(xx_notifyData); // 解析自定义参数

		int price = Integer.parseInt(request.getParameter("txnAmt")); // 商品价格
		String payChannel = json.getString("platform");// 支付通道
		String ip = request.getHeader("X-Real-IP") != null ? request.getHeader("X-Real-IP") : request.getRemoteAddr();// 来源ip
		String payInfo = getPayInfo(request); // 从支付通道获取的原始内容
		String releaseChannel = json.getString("channel");// 发行通道ID，一般从payInfo中解析出
		String appKey = json.getString("appkey");// CP方ID，一般从payInfo中解析出
		String payChannelOrderId = request.getParameter("orderId");// 支付通道的订单号，一般从payInfo中解析出

		String ownUserId = request.getParameter("ownUserId");// 付费用户ID，待用
		String ownItemId = request.getParameter("ownItemId");// 购买道具ID，待用
		String ownOrderId = json.getString("OrderIdSelf");// 原始订单号ID
		String cpOrderId = json.getString("OrderIdCp"); // cp方订单号

		int payStatus = payConstants.paytestStatus;// 是否是测试信息
		if (payChannel == null) {
			payChannel = json.getString("p");
		}
		if (releaseChannel == null) {
			releaseChannel = json.getString("a");
		}
		if (appKey == null) {
			appKey = json.getString("k");
		}
		if (ownOrderId == null) {
			ownOrderId = json.getString("s");
		}
		if (cpOrderId == null) {
			cpOrderId = json.getString("c");
		}

//		System.out.println("xx_notifyData = " + xx_notifyData + "\n" + "payChannel = " + payChannel + ",appKey = "
//				+ appKey + ",payChannelOrderId = " + payChannelOrderId + ",price = " + price + ",Ip = " + ip
//				+ ",cpOrderId = " + cpOrderId);

		String payChannelUserID = "";// 支付渠道的付费用户标识

		String IMEIforwardString = CheckPayInfo.CheckInfoIMEI(ownOrderId);
		JSONObject IMEIjson = JSON.parseObject(IMEIforwardString); // 解析自定义参数
		String payUserIMEI = IMEIjson.getString("imei");// 支付用户IMEI //2016/09/20

		ThreadPool.mThreadPool
				.execute(new PayInfoBean(price, payChannel, ip, payInfo, releaseChannel, appKey, payChannelOrderId,
						ownUserId, ownItemId, ownOrderId, cpOrderId, payStatus, payChannelUserID, payUserIMEI));

		try {
			response.getWriter().append(response.getStatus() + "");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";

	}

	/**
	 * 得到支付所有数据
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
			Map.Entry<String, String[]> entry = (Map.Entry<String, String[]>) iterator.next();

			String key = entry.getKey();
			String[] value = map.get(key);

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