package com.xt.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Properties;

import org.apache.log4j.Logger;

public class ConfigManager {
	
	private static Logger myLogger = Logger.getLogger(ConfigManager.class);
	
	private static final String PFILE ="config.ini";

	private File mFile = null;

	private long mLastModifiedTime = 0L;

	private Properties mProps = null;

	private static ConfigManager mInstance = null;

	private ConfigManager() {
		this.mFile = new File(PFILE);
		this.mProps = new Properties();
		try {
			this.mProps.load(getResourceAsStream(PFILE));
		} catch (Exception e) {
			myLogger.error("",e);
		}
	}

	public static synchronized ConfigManager getInstance() {
		if (mInstance == null) {
			mInstance = new ConfigManager();
		}
		return mInstance;
	}

	public final Object getConfigItem(String name, Object defaultVal) {
		long newTime = this.mFile.lastModified();

		if (newTime == 0L) {
			if (this.mLastModifiedTime == 0L)
				myLogger.debug(PFILE + " file does not exist!");
			else {
				myLogger.debug(PFILE + " file was deleted!!");
			}
			return defaultVal;
		}
		if (newTime > this.mLastModifiedTime) {
			this.mProps.clear();
			try {
				this.mProps.load(new FileInputStream(PFILE));
			} catch (Exception e) {
				myLogger.error("",e);
			}
		}
		this.mLastModifiedTime = newTime;
		Object val = this.mProps.getProperty(name);
		if (val == null) {
			return defaultVal;
		}
		return val;
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
				String filePath = Thread.currentThread().getContextClassLoader().getResource("").toString()
						.replaceAll("file:", "") + resource;
				if (filePath.indexOf(":") == 2)
					filePath = filePath.substring(1, filePath.length());
				File file = new File(filePath);
				if (file.exists()) {
					in = new FileInputStream(filePath);
				}
			}
		} catch (Exception e) {
			myLogger.error("getResourceAsStream",e);
		}
		if (in == null)
			throw new IOException("Could not find resource " + resource);
		return in;
	}
	
	public static void main(String[] args) {
		new ConfigManager();
	}
}