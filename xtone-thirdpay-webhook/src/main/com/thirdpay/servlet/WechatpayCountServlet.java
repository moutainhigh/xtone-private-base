package com.thirdpay.servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
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

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.common.util.ThreadPool;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.thirdpay.domain.PayInfoBean;
import com.thirdpay.utils.payConstants;

/**
 *微信网关支付回调给商户的参数
 * 	    state	订单充值状态	1.充值成功 2.充值失败
		customerid	商户ID	商户注册的时候，网关自动分配的商户ID
		sd51no	订单在网关的订单号	该订单在网关系统的订单号
		sdcustomno	商户订单号	该订单在商户系统的流水号
		ordermoney	订单实际金额	商户订单实际金额 单位：（元）
		cardno	支付类型	固定值32为（微信）  36为（手机QQ）
		mark	支付备注	固定值APP,不可修改为其他值，否则会导致验签失败
		sign	md5签名字符串	发送给商户的签名字符串
		resign	md5二次签名字符串	发送给商户的签名字符串
		des	支付备注	描述订单支付成功或失败的系统备注
 */

/**
 * 微信支付统计Servlet
 */
@WebServlet("/WechatpayCountServlet")
public class WechatpayCountServlet extends HttpServlet {

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public WechatpayCountServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
	 * @throws IOException
	 */
	public static String requestPostData(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String state = ""; // 订单充值状态 1.充值成功 2.充值失败
		String payChannel = "";// 支付通道channel
		String appKey = "";// CP方ID，一般从payInfo中解析出
		String releaseChannel = "";// 发行通道ID，一般从payInfo中解析出

		String xx_notifyDataError = request.getParameter("xx_notifyData");
		if (xx_notifyDataError != null) {
			// 打印出数据xx_notifyData =
			// {"channel":"bl","appkey":"cblappkey","platform":"wechat"}?state=1
			String notifyData[] = xx_notifyDataError.split("\\?"); // 分割
			// System.out.println(notifyData[0]);
			String xx_notifyData = notifyData[0]; // 得到json数据
			JSONObject json = JSON.parseObject(xx_notifyData);
			// 注释notifyData[1] = state = 1
			state = (notifyData[1].split("="))[1]; // state = 1 //再分割
			payChannel = json.getString("platform");
			releaseChannel = json.getString("channel");
			appKey = json.getString("appkey");
		}

		// 得到回调参数

		String payChannelOrderId = request.getParameter("sd51no");// 订单在网关的订单号
																	// 该订单在网关系统的订单号

		String ordermoney = request.getParameter("ordermoney");// 订单实际金额
																// 商户订单实际金额
																// 单位：（元）
		int price = (int) ((Float.parseFloat(ordermoney)) * 100); // 支付宝转换单位:元→分

		String cardno = request.getParameter("cardno");// 支付类型 固定值32为（微信）
														// 36为（手机QQ）

		String ip = request.getHeader("X-Real-IP") != null ? request.getHeader("X-Real-IP") : request.getRemoteAddr();// 来源ip
		String payInfo = getPayInfo(request); // 从支付通道获取的原始内容

		String ownUserId = request.getParameter("ownUserId");// 付费用户ID，待用
		String ownItemId = request.getParameter("ownItemId");// 购买道具ID，待用
		String ownOrderId = request.getParameter("ownOrderId");// 原始订单号ID，待用

		int testStatus =  payConstants.payStatus;// 是否是测试信息

		ThreadPool.mThreadPool.execute(new PayInfoBean(price, payChannel, ip, payInfo, releaseChannel, appKey,
				payChannelOrderId, ownUserId, ownItemId, ownOrderId, testStatus));
		response.getWriter().append("<result>1</result>");

//		System.out.println("支付状态 state = " + state + "\n" + "订单号sd51no = " + payChannelOrderId + "\n" + "价格price = "
//				+ price + "\n" + "支付平台platform = " + payChannel);

		return "";
	}

	/**
	 * 发送 post请求访问本地应用并根据传递参数不同返回不同结果
	 */
	public static void post(String url, List<BasicNameValuePair> formparams) {
		// 创建默认的httpClient实例.
		CloseableHttpClient httpclient = HttpClients.createDefault();

		// 创建httppost
		HttpPost httppost = new HttpPost(url);
		// 创建参数队列

		UrlEncodedFormEntity uefEntity;
		try {
			uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");
			httppost.setEntity(uefEntity);
			System.out.println("executing request" + httppost.getURI());
			CloseableHttpResponse response = httpclient.execute(httppost);
			try {
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					System.out.println("--------------------------------------");
					System.out.println("Response content: " + EntityUtils.toString(entity, "UTF-8"));
					System.out.println("--------------------------------------");
				}
			} finally {
				response.close();
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 关闭连接,释放资源
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
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
			Map.Entry<String, String[]> entry = (Map.Entry<String, String[]>) iterator.next();

			String key = entry.getKey(); // key为参数名称
			String[] value = map.get(key); // value为参数值

			// logger.info(key);
			// System.out.println(key);

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
