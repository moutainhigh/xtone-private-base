package com.thirdpay.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.common.util.ThreadPool;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.thirdpay.domain.PayInfoBean;

/**
 * 测试用不做正式使用
 * 微信支付wap 回调
 */
@WebServlet("/WxWapCallBackServlet")
public class WxWapCallBackServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public WxWapCallBackServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String xx_notifyData = request.getParameter("xx_notifyData");// 自定义参数
		String wexinInfo = request.getParameter("wexinInfo");// 自定义参数
		if(xx_notifyData!=null&&!"".equals(xx_notifyData)){
		
		JSONObject json = JSON.parseObject(xx_notifyData); // 解析自定义参数
		
		String payChannel = null;
		String releaseChannel = null;
		String appKey = null;
		String ownOrderId = null;
		String cpOrderId = null;
		
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
		
		
		System.err.println(payChannel+":"+releaseChannel+":"+appKey+":"+ownOrderId+":"+cpOrderId);
		
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
