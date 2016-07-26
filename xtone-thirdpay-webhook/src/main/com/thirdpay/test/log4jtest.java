package com.thirdpay.test;

import org.apache.log4j.Logger;

import com.thirdpay.servlet.AlipayCountServlet;

public class log4jtest {
	private static final Logger LOG = Logger.getLogger(log4jtest.class);

	public static void main(String[] args) {
		LOG.info("123" + "\n" + "321312!@#");
		LOG.error("error123");
		
		
	}
}
