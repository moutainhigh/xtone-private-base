package com.thirdpay.servlet;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

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
 * 微信支付WXSwiftPayCallBackServlet 回调
 */
@WebServlet("/WXSwiftPayCallBackServlet")
public class WXSwiftPayCallBackServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(WXSwiftPayCallBackServlet.class);

	public WXSwiftPayCallBackServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LOG.info("------调用了WXSwiftPayCallBackServlet-----");
		String xx_notifyData = request.getParameter("xx_notifyData");
		LOG.info("-----回调后得xx_notifyData = " + xx_notifyData);

		String appKey = request.getParameter("appkey");// 付费用户ID，待用
		String releaseChannel = request.getParameter("channel");// 购买道具ID，待用
		String payChannel = request.getParameter("platform");// 付费用户ID，待用
		String ownOrderId = request.getParameter("OrderIdSelf");// 购买道具ID，待用
		String cpOrderId = request.getParameter("OrderIdCp");// 购买道具ID，待用
		LOG.info("appKey = " + appKey + "\n" + "releaseChannel = " + releaseChannel + "\n" + "payChannel = "
				+ payChannel + "\n" + "ownOrderId = " + ownOrderId + "\n" + "cpOrderId = " + cpOrderId + "\n");
		
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
			String payInfo = getPayInfo(request)+builder.toString();
			
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
				
			try {
				response.getWriter().append("success"); // 返回success
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	}

	public String getVale(String xmlData, String key) {
		String value = subStr(xmlData, "<" + key + "><![CDATA[", "]]></" + key + ">");
		return value;
	}

	public static String subStr(String str, String beginStr, String endStr) {

		if (str.contains(beginStr)) {
			String mnonce_str = str.substring(str.indexOf(beginStr), str.indexOf(endStr));
			String newStr = mnonce_str.substring(mnonce_str.lastIndexOf("[") + 1, mnonce_str.length());

			return newStr;
		}
		return null;

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

		Iterator<Entry<String, String[]>> iterator = map.entrySet().iterator();

		while (iterator.hasNext()) {
			Map.Entry<String, String[]> entry = (Map.Entry<String, String[]>) iterator.next();

			String key = entry.getKey(); // key为参数名称
			if (!key.equals("xx_notifyData")) {
				String[] value = map.get(key); // value为参数值

				for (int i = 0; i < value.length; i++) {

					payInfo += "\"" + key + "\"" + ":" + "\"" + value[i] + "\"" + ",";

				}

			}

		}

		return payInfo;
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
