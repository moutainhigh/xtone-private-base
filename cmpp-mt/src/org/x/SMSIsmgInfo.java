package org.x;
/**
*Copyright 2003 Xiamen Xiangtone Co. Ltd.
*All right reserved.
*/

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class SMSIsmgInfo {

	public static String fjIsmgSpCode;
	public static String fjIsmgIp;
	public static int fjIsmgPort;
	public static String fjIcpID;
	public static String fjIcpShareKey;

	public static String gdIsmgSpCode;
	public static String gdIsmgIp;
	public static int gdIsmgPort;
	public static String gdIcpID;
	public static String gdIcpShareKey;

	public static String qwIsmgSpCode;
	public static String qwIsmgIp;
	public static int qwIsmgPort;
	public static String qwIcpID;
	public static String qwIcpShareKey;
	
	String configFile;

	public SMSIsmgInfo(String sConfigFile) {
		configFile = sConfigFile;
	}

	public boolean loadParam() {
		Properties props;
		try {
			InputStream in = getClass().getResourceAsStream(configFile);
			if (in == null) {
				in = ClassLoader.getSystemResourceAsStream(configFile);
			}
			if (in == null) {
				File file = new File(System.getProperty("user.dir") + "/" + configFile);
				if (file.exists()) {
					in = new FileInputStream(System.getProperty("user.dir") + "/" + configFile);
				}
			}
			if (in == null) {
				String filePath = Thread.currentThread().getContextClassLoader().getResource("").toString().replaceAll("file:",
						"") + configFile;
				if (filePath.indexOf(":") == 2)
					filePath = filePath.substring(1, filePath.length());
				File file = new File(filePath);
				if (file.exists()) {
					in = new FileInputStream(filePath);
				}
			}
			if (in != null) {
				props = new Properties();
				props.load(in);
			} else {
				System.out.println("Can not read the properties file:" + configFile);
				return false;
			}
			////////////// fj ismg&sp information
			String strTmp = "";
			strTmp = props.getProperty("fjIsmgPort", "paramName").trim();
			this.fjIsmgIp = props.getProperty("fjIsmgIp", "paramName").trim();
			this.fjIsmgSpCode = props.getProperty("fjIsmgSpCode", "paraName").trim();
			this.fjIcpID = props.getProperty("fjIcpID", "paramName").trim();
			this.fjIcpShareKey = props.getProperty("fjIcpShareKey", "paramName").trim();
			try {
				this.fjIsmgPort = Integer.parseInt(strTmp);
			} catch (Exception e) {
				System.out.println("ICPSocket conver to int error!!!!!!!");
			}
			////////////// gd ismg&sp information
			// String strTmp = "";

			strTmp = props.getProperty("qwIsmgPort", "paramName").trim();
			this.qwIsmgIp = props.getProperty("qwIsmgIp", "paramName").trim();
			this.qwIsmgSpCode = props.getProperty("qwIsmgSpCode", "paraName").trim();
			this.qwIcpID = props.getProperty("qwIcpID", "paramName").trim();
			this.qwIcpShareKey = props.getProperty("qwIcpShareKey", "paramName").trim();
			try {
				this.qwIsmgPort = Integer.parseInt(strTmp);
			} catch (Exception e) {
				System.out.println("ICPSocket conver to int error!!");
			}

		} catch (Exception e) {
			System.out.println("read profile.ini file error!" + e);
			return false;
		}
		return true;
	}

	public void printParam() {
		/*
		 * prt("fjIsmgIp:",fjIsmgIp); prt("fjIsmgSpcode:",fjIsmgSpCode);
		 * prt("fjIsmgProt:",fjIsmgPort); prt("fjIcpID:",fjIcpID);
		 * prt("fjIcpShareKey:",fjIcpShareKey);
		 */
		prt("qwIsmgIp:", qwIsmgIp);
		prt("qwIsmgSpcode:", qwIsmgSpCode);
		prt("qwIsmgProt:", qwIsmgPort);
		prt("qwIcpID:", qwIcpID);
		prt("qwIcpShareKey:", qwIcpShareKey);
	}

	private void prt(String name, String content) {
		System.out.println(name + content);
	}

	private void prt(String name, int num) {
		System.out.println(name + num);
	}

	public static String getIsmgSpCode(String ismgid) {
		//用于有多个网关连接得到的网关的spcode,企业代码
		String spcode = "05511";
		if (ismgid.equals("01")) {
			spcode = fjIsmgSpCode;
		} else if (ismgid.equals("02")) {
			spcode = gdIsmgSpCode;
		}
		return spcode;
	}

}
