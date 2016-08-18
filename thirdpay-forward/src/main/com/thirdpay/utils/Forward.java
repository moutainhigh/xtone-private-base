package com.thirdpay.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;
import org.common.util.ThreadPool;

import com.thirdpay.bean.ForwardLogBean;
import com.thirdpay.main.ServiceScan;

/**
 * 转发
 * 
 * @author 28518
 *
 */
public class Forward {
	private static final Logger LOG = Logger.getLogger(ServiceScan.class);

	public static void forward(String notify_url, String ownOrderId) {
		
		String forwardString = CheckPayInfo.CheckInfo(ownOrderId);

		List<BasicNameValuePair> formparams = new ArrayList<BasicNameValuePair>();
		formparams.add(new BasicNameValuePair("payment", forwardString));

		String responseContent = HttpUtils.post(notify_url, formparams,ownOrderId);

		// 判断返回状态
		if (responseContent.equals(Contents.successCondition)) {

			// 更新0为
			LOG.info(ownOrderId + "返回200 , 更新数据中...");
			// 插入1002数据
			CheckPayInfo.InsertInfo(ownOrderId, notify_url);

		} else {
			//返回不为200重复发送
			LOG.info(ownOrderId + "返回数据不为200 失败 ");
			//更新1001的下次转发时间为1分钟
			CheckPayInfo.UpdataInfoTime(ownOrderId);
		}
	}
}
