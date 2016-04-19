package com.thirdpay.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.message.BasicNameValuePair;
import org.common.util.ThreadPool;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.thirdpay.domain.PayInfoBean;

/**
 * Servlet implementation class BaidupayCountServlet
 */
@WebServlet("/BaidupayCountServlet")
public class BaidupayCountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BaidupayCountServlet() {
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
		requestPostData(request,response);

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

	
	public static String requestPostData(HttpServletRequest request, HttpServletResponse response) {
		String xx_notifyData = request.getParameter("xx_notifyData");// 自定义参数
		
		//先对xx_notifyData数据作处理 格式为aa:78-cc:123-xx:77
		StringBuilder builder = new StringBuilder("{");
		String [] strings = xx_notifyData.split("-");
		
		for (int i = 0; i < strings.length; i++) {
			String str2 = strings[i];
			String strings2[] = str2.split(":");
			
			
			builder.append("\""+strings2[0]+"\":\""+strings2[1]+"\"");
			builder.append(',');
//			System.out.print(strings2[0]+"   "); 
//			System.out.print(strings2[1]);
			
		}
		builder.deleteCharAt(builder.length()-1);
		builder.append("}");
		
		
		
		JSONObject json = JSON.parseObject(builder.toString()); // 解析自定义参数

		int price = Integer.parseInt(request.getParameter("unit_amount")); // 商品价格
		String payChannel = json.getString("platform");// 支付通道channel
		String ip = request.getHeader("X-Real-IP") != null ? request.getHeader("X-Real-IP") : request.getRemoteAddr();// 来源ip
		String payInfo = getPayInfo(request); // 从支付通道获取的原始内容

		String releaseChannel = json.getString("channel");// 发行通道ID，一般从payInfo中解析出
		String appKey = json.getString("appkey");// CP方ID，一般从payInfo中解析出
		String payChannelOrderId = request.getParameter("order_no");// 支付通道的订单号，一般从payInfo中解析出

		String ownUserId = request.getParameter("ownUserId");// 付费用户ID，待用
		String ownItemId = request.getParameter("ownItemId");// 购买道具ID，待用
		String ownOrderId = request.getParameter("ownOrderId");// 原始订单号ID，待用
		int testStatus = 1;// 是否是测试信息

		ThreadPool.mThreadPool.execute(new PayInfoBean(price, payChannel, ip, payInfo, releaseChannel, appKey,
				payChannelOrderId, ownUserId, ownItemId, ownOrderId, testStatus));
		try {
			response.getWriter().append("200");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
		List<BasicNameValuePair> formparams = new ArrayList<BasicNameValuePair>();

		Iterator<Entry<String, String[]>> iterator = map.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<String, String[]> entry = (Map.Entry<String, String[]>) iterator
					.next();

			String key = entry.getKey(); // key为参数名称
			String[] value = map.get(key); // value为参数值

			// logger.info(key);

			for (int i = 0; i < value.length; i++) {
				// System.out.println(value[i]);

				payInfo += key + "=" + value[i] + ";";

				// logger.info(value[i]);

			}

			// formparams.add(new BasicNameValuePair(entry.getKey(),
			// entry.getValue()[0]));

		}

		// 临时url
		// String other_url = "";
		// post(other_url, formparams);

		return payInfo;
	}
}
