package com.thirdpay.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;
import org.common.util.ThreadPool;

/**
 * 转发
 * 
 * @author 28518
 *
 */
public class Forward {
	private static final Logger LOG = Logger.getLogger(Forward.class);

	public static void forward(String notify_url, String ownOrderId) {
		String forwardString = CheckPayInfo.CheckInfo(ownOrderId);

		List<BasicNameValuePair> formparams = new ArrayList<BasicNameValuePair>();
		formparams.add(new BasicNameValuePair("payment", forwardString));
		
		LOG.info("forwardString = " + forwardString);
		
		String responseContent = HttpUtils.post(notify_url, formparams);

		// 判断返回状态
		if (responseContent.equals("200")) {

			// 更新0为
			// LOG.info("更新数据中...");
			// 插入1002数据

			// CheckPayInfo.InsertInfo(ownOrderId, notify_url);

		} else {
			// 返回不为200重复发送

		}
	}
	
	
	public static void wj_forward(String appkey , String channelid,String amount,String orderid , String imei ,String imsi,String userorderid){

		List<BasicNameValuePair> formparams = new ArrayList<BasicNameValuePair>();
		
		String createdate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		formparams.add(new BasicNameValuePair("createdate", createdate));
		formparams.add(new BasicNameValuePair("appkey", appkey));
		formparams.add(new BasicNameValuePair("channelid", channelid));
		formparams.add(new BasicNameValuePair("amount", amount));
		formparams.add(new BasicNameValuePair("orderid", orderid));
		formparams.add(new BasicNameValuePair("imei", imei));
		formparams.add(new BasicNameValuePair("imsi", imsi));
		formparams.add(new BasicNameValuePair("userorderid", userorderid));
		formparams.add(new BasicNameValuePair("status", "0"));
		
//		String url = "http://192.168.0.101:8080/thirdpay-webhook/TestServlet";
		String responseContent = HttpUtils.post(payConstants.wj_url, formparams);
		System.out.println("--------------responseContent = " + responseContent);
//		String responseContent = HttpUtils.post(url, formparams);

		// 判断返回状态
		if (responseContent.equals("ok")) {

			 LOG.info("收到ok");

		} else {
			LOG.info("返回error");
		}
	}
}
