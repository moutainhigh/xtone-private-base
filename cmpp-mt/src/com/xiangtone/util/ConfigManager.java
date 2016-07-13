/**
 *Writter by airmirror
 *2003-12-14 
 */

package com.xiangtone.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {

	final private static String PFILE = "config.ini";
	/**
	 * 瀵瑰簲浜庡睘鎬ф枃浠剁殑鏂囦欢瀵硅薄鍙橀噺
	 */
	private File m_file = null;
	/**
	 * 灞炴�ф枃浠剁殑鏈�鍚庝慨鏀规棩鏈�
	 */
	private long m_lastModifiedTime = 0;
	/**
	 * 灞炴�ф枃浠舵墍瀵瑰簲鐨勫睘鎬у璞″彉閲�
	 */
	private static Properties m_props = null;
	/**
	 * 鏈被鍙兘瀛樺湪鐨勬儫涓�鐨勪竴涓疄渚�
	 */
	private static ConfigManager m_instance = null;

	/**
	 * 绉佹湁鐨勬瀯閫犲瓙锛岀敤浠ヤ繚璇佸鐣屾棤娉曠洿鎺ュ疄渚嬪寲
	 */
	private ConfigManager() {
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
		ClassLoader loader = ConfigManager.class.getClassLoader();
		try {
			if (loader != null) {
				in = loader.getResourceAsStream(resource);
			}
			if (in == null) {
				in = ClassLoader.getSystemResourceAsStream(resource);
			}
			if (in == null) {
				File file = new File(System.getProperty("user.dir") + "/" + resource);
				if (file.exists()) {
					in = new FileInputStream(System.getProperty("user.dir") + "/" + resource);
				}
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
			// filePath = (filePath == null || filePath.length() == 0) ?
			// CONFIG_PATH : filePath;
			// if(filePath.indexOf(":") == 2)
			// filePath = filePath.substring(1, filePath.length());
			// System.out.println(filePath);
			// prop.load(new FileInputStream(filePath));
			m_props.load(getResourceAsStream(filePath));
			// File file = new File(CONFIG_PATH);
			// lastModifyTime = file.lastModified();
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
	 * 闈欐�佸伐鍘傛柟娉�
	 * 
	 * @return 杩旇繕ConfigManager 绫荤殑鍗曚竴瀹炰緥
	 */
	synchronized public static ConfigManager getInstance() {
		if (m_instance == null) {
			m_instance = new ConfigManager();
		}
		return m_instance;
	}

	/**
	 * 璇诲彇涓�鐗瑰畾鐨勫睘鎬ч」
	 * 
	 * @param name
	 *          灞炴�ч」鐨勯」鍚�
	 * @param defaultVal
	 *          灞炴�ч」鐨勯粯璁ゅ��
	 * @return 灞炴�ч」鐨勫�硷紙濡傛椤瑰瓨鍦級锛� 榛樿鍊硷紙濡傛椤逛笉瀛樺湪锛�
	 */
	final public Object getConfigItem(String name, Object defaultVal) {
		long newTime = m_file.lastModified();
		// 妫�鏌ュ睘鎬ф枃浠舵槸鍚﹁鍏朵粬绋嬪簭
		// 锛堝鏁版儏鍐垫槸绋嬪簭鍛樻墜鍔級淇敼杩�
		// 濡傛灉鏄紝閲嶆柊璇诲彇姝ゆ枃浠�

		if (newTime == 0) {
			// 灞炴�ф枃浠朵笉瀛樺湪
			if (m_lastModifiedTime == 0) {
				System.err.println(PFILE + " file does not exist!");
			} else {
				System.err.println(PFILE + " file was deleted!!");
			}
			return defaultVal;
		} else if (newTime > m_lastModifiedTime) {
			// Get rid of the old properties
			m_props.clear();
			try {
				m_props.load(new FileInputStream(PFILE));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		m_lastModifiedTime = newTime;
		Object val = m_props.getProperty(name);
		if (val == null) {
			System.out.println("error:" + defaultVal);
			return defaultVal;
		} else {
			return val;
		}
	}
}