package com.thirdpay.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.swiftpass.util.HttpUtils;
import com.swiftpass.util.MD5;
import com.swiftpass.util.SignUtils;
import com.swiftpass.util.SwiftpassConfig;
import com.swiftpass.util.XmlUtils;

@WebServlet("/WXSwiftPay")
public class WXSwiftPay extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	public static final String REQURL = "https://pay.swiftpass.cn/pay/gateway";
	
	public String xx_notifyData ;
	// 支付回调地址
	String WXSwiftPay_notify_url = SwiftpassConfig.WXSwiftPay_notify_url;
	//威富通商户号
	String mch_id = SwiftpassConfig.WXSwiftPay_mch_id;
	//威富通密钥
	String key = SwiftpassConfig.WXSwiftPay_key;
	
	String notify_url ="";
	
	public WXSwiftPay() {
		super();
	}

	
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		 request.setCharacterEncoding("utf-8");
	     response.setCharacterEncoding("utf-8");
	     
	     
		//价格（分）
		 String money = request.getParameter("total_fee");
		//商品名
		 String body = request.getParameter("body");
		//附加信息
		 String attach=request.getParameter("attach");
		 
		 xx_notifyData=request.getParameter("xx_notifyData");
		 
		;
		 
		 notify_url=WXSwiftPay_notify_url+ getbuilder(xx_notifyData);
		 
		 System.out.println("--------xx_notifyData = "+xx_notifyData);
		 System.out.println("--------notify_url = "+notify_url);
		 
		 String entity = getParams( body, attach, money);
		 
		 String result = HttpUtils.post(REQURL, entity);
		 try {
			 Map<String, String> maps = XmlUtils.toMap(result.getBytes(), "UTF-8");
			 //System.out.println(maps);
			 
			 String json = JSON.toJSONString(maps);
			 System.out.println(json);
			 
			 response.getWriter().append(json);
			 
		} catch (Exception e) {  
			e.printStackTrace();
		}
		 //System.out.println(result);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		doGet(request, response);
		
		
		
		
		
	}
	
	
	
	
	 /**
     * 组装参数
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    private String getParams( String _body,String _attach,String _money)
    {
        
        Map<String, String> params = new HashMap<String, String>();
        params.put("body", _body); // 商品名称
		params.put("attach", _attach);//附加信息，在查询接口和支付通知中原样返回，该字段主要用于商户携带订单的自定义数据
        params.put("service", "unified.trade.pay"); // 支付类型
        params.put("version", "2.0"); // 版本
        //params.put("mch_id", "7552900037"); // 威富通商户号
        params.put("mch_id", mch_id); // 威富通商户号
        //        params.put("mch_id", mchId.getText().toString()); // 威富通商户号
        
        
        /***
         * 后台通知url,默认是空格，商户测试及上线时一定要改为自己正式的
         */
        
//        params.put("notify_url",WXSwiftPay_notify_url+"?xx_notifyData="+xx_notifyData); // 后台通知url,默认是空格，商户测试及上线时一定要改为自己正式的
        params.put("notify_url",notify_url); // 后台通知url,默认是空格，商户测试及上线时一定要改为自己正式的
        params.put("nonce_str", genNonceStr()); // 随机数
        String   payOrderNo = genOutTradNo();
        params.put("out_trade_no", payOrderNo); //订单号
        params.put("mch_create_ip", "127.0.0.1"); // 机器ip地址
        params.put("total_fee", _money); // 总金额
        params.put("limit_credit_pay", "0"); // 是否限制信用卡支付， 0：不限制（默认），1：限制
     //   String sign = createSign("11f4aca52cf400263fdd8faf7a69e007", params); // 01133be809cd03a4726e8b861b58ad7a  威富通密钥
        String sign = createSign(key, params); // 01133be809cd03a4726e8b861b58ad7a  威富通密钥
        
        params.put("sign", sign); // sign签名
        
        return XmlUtils.toXml(params);
    }
    
    
    public String createSign(String signKey, Map<String, String> params)
    {
        StringBuilder buf = new StringBuilder((params.size() + 1) * 10);
        SignUtils.buildPayParams(buf, params, false);
        buf.append("&key=").append(signKey);
        String preStr = buf.toString();
        String sign = "";
        // 获得签名验证结果
        try
        {
            sign = MD5.md5s(preStr).toUpperCase();
        }
        catch (Exception e)
        {
            sign = MD5.md5s(preStr).toUpperCase();
        }
        return sign;
    }
    
    
    
    
    private String genNonceStr()
    {
        Random random = new Random();
        return MD5.getMessageDigest(String.valueOf(random.nextInt(10000)).getBytes());
    }
    
    private String genOutTradNo()
    {
        SimpleDateFormat format = new SimpleDateFormat("MMddHHmmss");
        Date date = new Date();
        String key = format.format(date);
        
        java.util.Random r = new java.util.Random();
        key += r.nextInt();
        key = key.substring(0, 15);
        return key;
    }
    
	private StringBuilder getbuilder(String xx_notifyData){
		
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
		_builder.append("?channel=" + channel + "&");
		_builder.append("appkey=" + appkey + "&");
		_builder.append("platform=" + platform + "&");
		_builder.append("OrderIdSelf=" + OrderIdSelf + "&");
		_builder.append("OrderIdCp=" + OrderIdCp);
		
		return _builder;
	}
	

}
