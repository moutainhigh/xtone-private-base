/**
 *Writter by airmirror
 *2003-12-14 
 *��ʵ��ȴ�о�̬����
 */

package com.xiangtone.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

public class ConfigManager1 {

	final private static Logger LOG = Logger.getLogger(ConfigManager1.class);

	final private static String PFILE = "config.ini";
	/**
	 * ��Ӧ�������ļ����ļ��������
	 */
	private File m_file = null;
	/**
	 * �����ļ�������޸�����
	 */
	private long m_lastModifiedTime = 0;
	/**
	 * �����ļ�����Ӧ�����Զ������
	 */
	private static Properties m_props = null;
	/**
	 * ������ܴ��ڵ�Ωһ��һ��ʵ��
	 */
	private static ConfigManager1 m_instance = null;

	/**
	 * ˽�еĹ����ӣ����Ա�֤����޷�ֱ��ʵ����
	 */
	private ConfigManager1() {
		// m_file = new File(PFILE);
		// m_lastModifiedTime = m_file.lastModified();
		// if(m_lastModifiedTime == 0)
		// {
		// System.err.println(PFILE + " file does not exist!");
		// }
		// m_props = new Properties();
		// try
		// {
		// m_props.load(new FileInputStream(PFILE));
		// }
		// catch(Exception e)
		// {
		// e.printStackTrace();
		// }
		init(PFILE);
	}

	public static InputStream getResourceAsStream(String resource) throws IOException {
		InputStream in = null;
		ClassLoader loader = ConfigManager1.class.getClassLoader();
		try {
			if (loader != null) {
				in = loader.getResourceAsStream(resource);
				LOG.debug("load config from loader.getResourceAsStream:" + resource);
			}
			if (in == null) {
				in = ClassLoader.getSystemResourceAsStream(resource);
				LOG.debug("load config from ClassLoader.getSystemResourceAsStream:" + resource);
			}
			if (in == null) {
				File file = new File(System.getProperty("user.dir") + "/" + resource);
				if (file.exists()) {
					in = new FileInputStream(System.getProperty("user.dir") + "/" + resource);
				}
				LOG.debug(
						"load config from System.getProperty(\"user.dir\"):" + System.getProperty("user.dir") + "/" + resource);
				// ClassLoader.getSystemResourceAsStream(System.getProperty("user.dir")+"/"+resource);
			}
			if (in == null) {
				String filePath = Thread.currentThread().getContextClassLoader().getResource("").toString().replaceAll("file:",
						"") + resource;
				if (filePath.indexOf(":") == 2)
					filePath = filePath.substring(1, filePath.length());
				File file = new File(filePath);
				if (file.exists()) {
					in = new FileInputStream(filePath);
				}
				LOG.debug("load config from filePath:" + filePath);
				// ClassLoader.getSystemResourceAsStream(System.getProperty("user.dir")+"/"+resource);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (in == null)
			throw new IOException("Could not find resource " + resource);
		return in;
	}

	private static void init(String filePath) {

		m_props = new Properties();
		try {
			m_props.load(getResourceAsStream(filePath));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static String getProperty(String key) {
		String result = "";
		if (m_props == null) {
			init(PFILE);
		}
		try {
			// File file = new File(CONFIG_PATH);
			// long tempTime = file.lastModified();
			// if (tempTime > lastModifyTime) {
			// prop.clear();
			// init("");
			// }
			if (m_props.containsKey(key)) {
				result = m_props.getProperty(key);
			}
		} catch (Exception exce) {
			exce.printStackTrace();
		}
		return result;
	}

	public static String getConfigData(String key) {
		return getProperty(key);
	}

	public static String getConfigData(String key, String defaultValue) {
		return getProperty(key).length() == 0 ? defaultValue : getProperty(key);
	}

	/**
	 * ��̬��������
	 * 
	 * @return ����ConfigManager ��ĵ�һʵ��
	 */
	synchronized public static ConfigManager1 getInstance() {
		if (m_instance == null) {
			m_instance = new ConfigManager1();
		}
		return m_instance;
	}

}
