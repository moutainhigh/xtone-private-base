package com.thirdpay.test;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.thirdpay.domain.PayInfoBean;

/**
 * Servlet implementation class TestReceiverServlet
 */
@WebServlet("/TestReceiverServlet")
public class TestReceiverServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(TestReceiverServlet.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TestReceiverServlet() {
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
		// String payment = request.getParameter("payment");
		// if(!payment.equals("")){
		// System.out.println("recivier-payment = " + payment);
		// response.getWriter().append("200");
		// }
		String payinfo = getPayInfo(request);
		response.getWriter().append("200");
		LOG.info("----------------------payinfo = " + payinfo);
		// String payment = request.getParameter("appkey");
		// System.out.println("appkey = " + payment);
		// response.setCharacterEncoding("gbk");
		// response.setContentType("text/html;charset=gbk");
		//
		// response.getWriter().append("小心我锋哥打你 ");

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

		// List<BasicNameValuePair> formparams = new
		// ArrayList<BasicNameValuePair>();

		Iterator<Entry<String, String[]>> iterator = map.entrySet().iterator();

		while (iterator.hasNext()) {
			Map.Entry<String, String[]> entry = (Map.Entry<String, String[]>) iterator.next();

			String key = entry.getKey(); // key为参数名称
			String[] value = map.get(key); // value为参数值

			for (int i = 0; i < value.length; i++) {

				payInfo += "\"" + key + "\"" + ":" + "\"" + value[i] + "\"" + ",";

			}

		}
		return payInfo;
	}
}
