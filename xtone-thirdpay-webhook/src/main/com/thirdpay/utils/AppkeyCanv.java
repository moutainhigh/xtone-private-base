package com.thirdpay.utils;

import java.util.HashMap;

public class AppkeyCanv {
	public static HashMap<String, String> parm;

	static {
		parm = new HashMap<>();
		parm.put("ae03d9d6e0444bb08af1f1098b2afafc", "http://weixin.hejupay.com/weixin_app_haotian/notify_url.php");
//		parm.put("ae03d9d6e0444bb08af1f1098b2afafc", "http://thirdpay-webhook.n8wan.com:29141/TestReceiverServlet");
		
	}
}
