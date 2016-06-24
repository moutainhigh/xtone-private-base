package com.thirdpay.test;

import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.common.util.ThreadPool;

import com.thirdpay.domain.ForwardsyncBean;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TestServlet() {
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

		// String jsonString = request.getParameter("appkey");
		//
		// System.out.println("接收到的jsonString = " + jsonString );
		//
		// if(jsonString != null){
		//
		// response.getWriter().append("200");
		// }

		// String appkey = request.getParameter("appkey");
		//
		// if (appkey.equals("cbl")) {
		// System.out.println("接收到的appkey = " + appkey);
		//
		// }
//		String payment = request.getParameter("payment");
//		if (!payment.equals("")) {
//			System.out.println("recivier-payment = " + payment);
//			response.getWriter().append("收到payment = "+payment);
//		}

		response.getWriter().append("200");

		// ThreadPool.mThreadPool.execute(
		// new ForwardsyncBean(1001, "ownOrderId", "0", "3000", "0", "url",
		// "200", "appkey=cbl", "appkey"));

		// Map<String, String[]> name = request.getParameterMap();
		// Iterator<Entry<String, String[]>> iterator =
		// name.entrySet().iterator();
		//
		// while (iterator.hasNext()) {
		// Map.Entry<String, String[]> entry = iterator.next();
		// String key = entry.getKey();
		//
		// String value[] = entry.getValue();
		// for (int i = 0; i < value.length; i++) {
		//
		// System.out.println(value[i]);
		//
		// }
		// }

		// response.setContentType("text/html; charset=utf-8");
		// request.setCharacterEncoding("utf-8");

		// byte b[] = new byte[1024];
		// int n = request.getInputStream().read(b);
		//
		// byte[] m = Arrays.copyOf(b, n);
		// System.out.println(new String(m));

		// String name = request.getParameter("username");
		// String password = request.getParameter("password");
		// response.getWriter().append(name);
		//
		// System.out.println(name);
		// System.out.println(password);

		// String name = request.getParameter("cbl");
		//
		// response.getWriter().append(name);
		// System.out.println("name = "+name);

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

}
