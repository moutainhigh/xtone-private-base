package com.thirdpay.servlet;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPObject;
import com.swiftpass.util.MD5;
import com.swiftpass.util.SignUtils;
import com.swiftpass.util.SwiftpassConfig;
import com.swiftpass.util.XmlUtils;
import com.thirdpay.utils.HttpUtils;
import com.thirdpay.utils.WeixinHttpsUtils;

//import com.swiftpass.action.HttpUtils;
//import com.swiftpass.config.SwiftpassConfig;
//import com.swiftpass.util.MD5;
//import com.swiftpass.util.SignUtils;
//import com.swiftpass.util.XmlUtils;

/**
 * 微信wap 支付
 */
@WebServlet("/WXWapServlet")
public class WXWapServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static Map<String, String> orderResult; // 用来存储订单的交易状态(key:订单号，value:状态(0:未支付，1：已支付))
													// ---- 这里可以根据需要存储在数据库中
	public static int orderStatus = 0;

	public WXWapServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");

		String xx_notifyData = req.getParameter("xx_notifyData");
		//// System.out.println(xx_notifyData);
		// {"c":"12345","k":"zgt","p":"wxWap","a":"zgt10010"}
		/**
		 * a = channel k = appkey p =platform s = OrderIdSelf c = OrderIdCp
		 */
		String channel = null;
		String appkey = null;
		String platform = null;
		String OrderIdSelf = null;
		String OrderIdCp = null;
		try {
			JSONObject object = JSON.parseObject(xx_notifyData);
			channel = object.getString("a");
			appkey = object.getString("k");
			platform = object.getString("p");
			OrderIdSelf = object.getString("s");
			OrderIdCp = object.getString("c");
		} catch (Exception e) {
			e.printStackTrace();
		}

		StringBuilder _builder = new StringBuilder();
		_builder.append("a:" + channel + "-");
		_builder.append("k:" + appkey + "-");
		_builder.append("p:" + platform + "-");
		_builder.append("s:" + OrderIdSelf + "-");
		_builder.append("c:" + OrderIdCp);

		@SuppressWarnings("unchecked")
		SortedMap<String, String> map = XmlUtils.getParameterMap(req);
		map.put("mch_id", SwiftpassConfig.mch_id);
		// map.put("notify_url",
		// SwiftpassConfig.notify_url+"?"+_builder.toString());
		map.put("notify_url", SwiftpassConfig.notify_url + "?a=" + _builder);
		// map.put("notify_url","http://chendefeng.f3322.net/youkaxiaoxiao/lotteryctrl/"+channel+"/"+appkey+"/"+platform+"/"+OrderIdSelf+"/"+OrderIdCp+"/userlottery");
		map.put("nonce_str", String.valueOf(new Date().getTime()));

		// 可以不要
		map.put("callback_url", "http://chendefeng.f3322.net/baidupay/Test");

		Map<String, String> params = SignUtils.paraFilter(map);
		StringBuilder buf = new StringBuilder((params.size() + 1) * 10);
		SignUtils.buildPayParams(buf, params, false);
		String preStr = buf.toString();
		String sign = MD5.sign(preStr, "&key=" + SwiftpassConfig.key, "utf-8");
		map.put("sign", sign);

		String reqUrl = SwiftpassConfig.req_url;
		//// System.out.println("reqUrl：" + reqUrl);

		// //System.out.println("reqParams:" + XmlUtils.parseXML(map));
		CloseableHttpResponse response = null;
		CloseableHttpClient client = null;
		String res = null;
		try {
			HttpPost httpPost = new HttpPost(reqUrl);
			StringEntity entityParams = new StringEntity(XmlUtils.parseXML(map), "utf-8");
			httpPost.setEntity(entityParams);
			// httpPost.setHeader("Content-Type",
			// "text/xml;charset=ISO-8859-1");
			client = HttpClients.createDefault();
			response = client.execute(httpPost);
			if (response != null && response.getEntity() != null) {
				Map<String, String> resultMap = XmlUtils.toMap(EntityUtils.toByteArray(response.getEntity()), "utf-8");
				res = XmlUtils.toXml(resultMap);
				// System.out.println("请求结果：" + res);

				if (resultMap.containsKey("sign")) {
					if (!SignUtils.checkParam(resultMap, SwiftpassConfig.key)) {
						res = "验证签名不通过";
					} else {
						if ("0".equals(resultMap.get("status")) && "0".equals(resultMap.get("result_code"))) {
							if (orderResult == null) {
								orderResult = new HashMap<String, String>();
							}
							orderResult.put(map.get("out_trade_no"), "0");// 初始状态

							String pay_info = resultMap.get("pay_info");
							// System.out.println("pay_info = "+pay_info);
							// 处理支付结果
							String string = HttpUtils.get(pay_info);
							if (string != null) {
								
								StringBuilder builder = new StringBuilder();
								String weixin=WeixinHttpsUtils.getWeixin(string);
								builder.append("{");
								builder.append("\"wixin\":" + "\"" + weixin + "\",");

								
                        		String myhttps=WeixinHttpsUtils.getWeixinHttps(string);
                        		builder.append("\"https\":"+"\""+myhttps+"\"");
                         		
                        		builder.append("}");
                        		
                        		resp.getWriter().write(builder.toString());
                        		
                        		
                        		return ;
								

							}
						} else {
							req.setAttribute("result", res);
						}
					}
				}
			} else {
				res = "操作失败";
			}
		} catch (Exception e) {
			e.printStackTrace();
			res = "系统异常";
		} finally {
			if (response != null) {
				response.close();
			}
			if (client != null) {
				client.close();
			}
		}
		if (res.startsWith("<")) {
			resp.setHeader("Content-type", "text/xml;charset=UTF-8");
		} else {
			resp.setHeader("Content-type", "text/html;charset=UTF-8");
		}
		resp.getWriter().write(res);

	}

}
