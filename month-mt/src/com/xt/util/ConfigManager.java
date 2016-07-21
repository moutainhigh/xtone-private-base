package com.xt.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.PrintStream;
import java.util.Properties;

import org.apache.log4j.Logger;

public class ConfigManager {
	
	private static Logger myLogger = Logger.getLogger(ConfigManager.class);
	
	private static final String PFILE = System.getProperty("user.dir") + File.separator + "config.ini";

	private File mFile = null;

	private long mLastModifiedTime = 0L;

	private Properties mProps = null;

	private static ConfigManager mInstance = null;

	private ConfigManager() {
		this.mFile = new File(PFILE);
		this.mLastModifiedTime = this.mFile.lastModified();
		if (this.mLastModifiedTime == 0L) {
			myLogger.debug(PFILE + " file does not exist!");
		}
		this.mProps = new Properties();
		try {
			this.mProps.load(new FileInputStream(PFILE));
		} catch (Exception e) {
			e.printStackTrace();
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
				e.printStackTrace();
			}
		}
		this.mLastModifiedTime = newTime;
		Object val = this.mProps.getProperty(name);
		if (val == null) {
			return defaultVal;
		}
		return val;
	}
}