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
import com.thirdpay.utils.payConstants;
/**
 * 参数名 参数含义 格式说明 长度 是否必须 sp_no 百度钱包支付商户号 10位数字组成的字符串  是 order_no 商户订单号 不超过20个字符 Max(20) 是 bfb_order_no 百度钱包支付交易号 不超过32个字符 Max(32) 是 bfb_order_create_time 百度钱包支付交易创建 时间 YYYYMMDDHHMMSS  是 pay_time 支付时间 YYYYMMDDHHMMSS  是 pay_type 支付方式 默认取值2  是 unit_amount 商品单价，以分为单位 非负整数  否 unit_count 商品数量 非负整数  否 transport_amount 运费，以分为单位 非负整数  否 total_amount 总金额，以分为单位 非负整数  是 fee_amount 百度钱包支付收取商户 的手续费，以分为单位 非负整数  是 currency 币种，目前仅支持人民 币 取值范围参见附录  是 buyer_sp_username 买家在商户网站的用户 名 允许包含中文；不超过 64字符或32个汉字 Max(64) 否 pay_result 支付结果代码 取值范围参见附录  是 input_charset 请求参数的字符编码 取值范围参见附录  是 
 */


/**
 * 百度统计Servlet
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

	public static String requestPostData(HttpServletRequest request, HttpServletResponse response) {
		String xx_notifyData = request.getParameter("xx_notifyData");// 自定义参数

		// 先对xx_notifyData数据作处理 格式为aa:78-cc:123-xx:77
		StringBuilder builder = new StringBuilder("{");
		String[] strings = xx_notifyData.split("-");

		for (int i = 0; i < strings.length; i++) {
			String str2 = strings[i];
			String strings2[] = str2.split(":");

			builder.append("\"" + strings2[0] + "\":\"" + strings2[1] + "\"");
			builder.append(',');
			// System.out.print(strings2[0]+" ");
			// System.out.print(strings2[1]);

		}
		builder.deleteCharAt(builder.length() - 1);
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

		System.out.println("xx_notifyData = " + builder.toString() + "\n" + "payChannel = " + payChannel + ",appKey = "
				+ appKey + ",payChannelOrderId = " + payChannelOrderId + ",price = " + price + ",Ip = " + ip
				+ ",cpOrderId = " + cpOrderId);

		ThreadPool.mThreadPool.execute(new PayInfoBean(price, payChannel, ip, payInfo, releaseChannel, appKey,
				payChannelOrderId, ownUserId, ownItemId, ownOrderId, cpOrderId, payStatus));
		try {
			response.getWriter().append("<meta name=\"VIP_BFB_PAYMENT\" content=\"BAIFUBAO\">");
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
//		List<BasicNameValuePair> formparams = new ArrayList<BasicNameValuePair>();

		Iterator<Entry<String, String[]>> iterator = map.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<String, String[]> entry = (Map.Entry<String, String[]>) iterator.next();

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
