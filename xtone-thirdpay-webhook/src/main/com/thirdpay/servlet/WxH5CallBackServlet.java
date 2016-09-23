package com.thirdpay.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
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
 * 微信支付wap 回调
 */
@WebServlet("/WxH5CallBackServlet")
public class WxH5CallBackServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(WxH5CallBackServlet.class);
   
    public WxH5CallBackServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOG.info("--------------------调用了WxH5CallBackServlet ------------------------ ");
		
		try {
		request.setCharacterEncoding("utf-8");
	    String a = request.getParameter("a");
		
		String payChannel = null;
		String releaseChannel = null;
		String appKey = null;
		String ownOrderId = null;
		String cpOrderId = null;
		
		try {
			StringBuilder jsonBuilder = new StringBuilder("{");
			String[] strings = a.split("-");
			for (int i = 0; i < strings.length; i++) {
				String str2 = strings[i];
				String strings2[] = str2.split(":");
				jsonBuilder.append("\"" + strings2[0] + "\":\"" + strings2[1] + "\"");
				jsonBuilder.append(',');
			}
			jsonBuilder.deleteCharAt(jsonBuilder.length() - 1);
			jsonBuilder.append("}");
			JSONObject json = JSON.parseObject(jsonBuilder.toString()); // 解析自定义参数

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
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	    //-----------------------------------------------------
		
		
		ServletInputStream in=request.getInputStream();
		StringBuilder builder = new StringBuilder();
		byte [] bytes = new byte[1024];
		int len;
		while((len=in.read(bytes))!=-1){
			String string = new String(bytes, 0, len);
			builder.append(string);
		}
		//System.out.println(builder);
		String xmlData = builder.toString();
		
		String price = getVale(xmlData,"total_fee");//总金额，以分为单位，不允许包含任何字、符号
		String ip =  getVale(xmlData,"device_info");//
		//String payInfo =  getVale(xmlData,"pay_info");//支付结果信息，支付成功时为空 
		String payInfo = a+builder.toString();
		
		String payChannelOrderId =  getVale(xmlData,"out_transaction_id");//第三方订单号
        String payStatus =  getVale(xmlData,"result_code");
        
        String ownUserId = getVale(xmlData,"mch_id");
        String ownItemId =  getVale(xmlData,"nonce_str");
		
    	String payChannelUserID = "";// 支付渠道的付费用户标识

    	String IMEIforwardString = CheckPayInfo.CheckInfoIMEI(ownOrderId);
		String payUserIMEI ="";
		if(!"".equals(IMEIforwardString)){
			JSONObject IMEIjson = JSON.parseObject(IMEIforwardString); // 解析自定义参数
			payUserIMEI = IMEIjson.getString("imei");// 支付用户IMEI //2016/09/20
			
		}
        
		ThreadPool.mThreadPool.execute(new PayInfoBean(Integer.valueOf(price), payChannel, ip, payInfo, releaseChannel, appKey,
				payChannelOrderId, ownUserId, ownItemId, ownOrderId, cpOrderId,payConstants.paytestStatus,payChannelUserID, payUserIMEI));
		

		
		response.getWriter().append("success");
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		 
	}
	
	
	public String getVale(String xmlData,String key){
		String value = subStr(xmlData, "<"+key+"><![CDATA[", "]]></"+key+">");
		return value;
	}
	
	public static String subStr(String str,String beginStr,String endStr ){
		
		if(str.contains(beginStr)){
			 String mnonce_str = str.substring(str.indexOf(beginStr), str.indexOf(endStr));
			 String newStr = mnonce_str.substring(mnonce_str.lastIndexOf("[")+1, mnonce_str.length());
			 
			 return newStr;
		}
		return null;
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
