package com.thirdpay.test;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;

import com.thirdpay.utils.AES;
import com.thirdpay.utils.CheckPayInfo;
import com.thirdpay.utils.HttpUtils;
/**
 * 手动post回调数据
 * 
 * @author 28518
 *
 */
public class postDemo {

	static Logger LOG = Logger.getLogger(testInsert.class);
	
	
	
	public static void main(String[] args) {
		
		
		
		String notify_url = "http://pay.vpayplay.com:808/openapi/haotianpay";
		String ownOrderId = "1471324382268034344";
		String encrypt ="0";
		String appkey = "11cff472487b47069aa8ca239b42d9ad";
		String encrypt_key = "";
		String forwardString = CheckPayInfo.CheckInfo(ownOrderId);
		try {
			
			
			postPayment(notify_url, ownOrderId, encrypt,appkey, encrypt_key,forwardString);
			
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  
	}
	
	/**
	 * post转发数据
	 * 
	 * @param notify_url
	 * @param ownOrderId
	 * @throws Exception
	 */
	public static void postPayment(String notify_url, String ownOrderId, String encrypt, String appkey, String encrypt_key,String forwardString)
			throws Exception {

	
//		String forwardString ="{\"buyNum\":0,\"coinNum\":0,\"cpOrderId\":\"2016081613130152384900\",\"price\":1200,\"productDesc\":\"开通会员 精彩马上呈现 客服QQ：3529606836\",\"productName\":\"开通会员 精彩马上呈现 客服QQ：3529606836\",\"ratio\":0,\"roleLevel\":0,\"webOrderid\":\"1471324382268034344\"}";

		LOG.info("appkey = " + appkey + " ownOrderId = " + ownOrderId + " 加密前的字串是：" + forwardString + " 加密的key是: "+encrypt_key);

		if ("1".equals(encrypt) && encrypt_key != null && !"".equals(encrypt_key) && encrypt_key.length() == 16) {
			// 加密
			forwardString = AES.Encrypt(forwardString, encrypt_key);
			LOG.info("appkey = " + appkey + " ownOrderId = " + ownOrderId + "加密后的字串是：" + forwardString);
   
		}

		List<BasicNameValuePair> formparams = new ArrayList<BasicNameValuePair>();
		formparams.add(new BasicNameValuePair("payment", forwardString));

		String responseContent = HttpUtils.post(notify_url, formparams, ownOrderId);

		// 判断返回状态
		if (responseContent.equals("200")) {

			// 更新0为
			LOG.info(ownOrderId + "返回200 , 插入1002数据");
			// 插入1002数据
			CheckPayInfo.Insert1002(ownOrderId, notify_url,forwardString);

		} else {
			// 返回不为200重复发送
			LOG.info(ownOrderId + "返回数据不为200 失败 ");
			// 更新1001的下次转发时间为1分钟
			CheckPayInfo.Updata1001Time(ownOrderId,responseContent);
		}

	}

}
