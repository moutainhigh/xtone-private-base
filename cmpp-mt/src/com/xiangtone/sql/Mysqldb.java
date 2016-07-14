/**
*Copyright 2003 Xiamen Xiangtone Co. Ltd.
*All right reserved.
*/
package com.xiangtone.sql;

import java.util.*;

import org.apache.log4j.Logger;

import com.xiangtone.util.ConfigManager;

import java.sql.*;

/**
 * 数据库处理类
 *
 */

public class Mysqldb {

	private static Logger logger = Logger.getLogger(Mysqldb.class);
	String sDBDriver = "com.mysql.jdbc.Driver";
	Connection conn = null;
	ResultSet rs = null;
	Statement stmt = null;

	public String dbIp;
	public int dbPort;
	public String dbName;
	public String dbUser;
	public String dbPwd;

	String DBCon = "";

	public void setdbIp(String dbIp) {
		this.dbIp = dbIp;
	}

	public void setdbPort(int dbPort) {
		this.dbPort = dbPort;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public void setDbUser(String dbUser) {
		this.dbUser = dbUser;
	}

	public void setDbPwd(String dbPwd) {
		this.dbPwd = dbPwd;
	}

	public Mysqldb(String dbIp, String dbPort, String dbname, String dbuser, String dbpwd) {
		try {
			Class.forName(sDBDriver);
		} catch (java.lang.ClassNotFoundException e) {
			logger.error("Unable to load driver:", e);
		}
		DBCon = "jdbc:mysql://" + dbIp + ":" + dbPort + "/" + dbname + "?";
		DBCon += "user=" + dbuser + "&password=" + dbpwd + "&useUnicode=true&characterEncoding=GBK";
		logger.debug("DBCon:" + DBCon);
	}

	public Mysqldb() {
		try {
			Class.forName(sDBDriver);
			this.dbIp = (String) ConfigManager.getConfigData("xiangtone_dbIp", "xiangtone_dbIp" + " dbIp not found!");
			this.dbPort = Integer.parseInt((String) ConfigManager.getConfigData("xiangtone_dbPort",
					"xiangtone_dbPort" + " dbPort not found!"));
			this.dbName = (String) ConfigManager.getConfigData("xiangtone_dbname",
					"xiangtone_dbname" + " dbname not found!");
			this.dbUser = (String) ConfigManager.getConfigData("xiangtone_dbuser",
					"xiangtone_dbuser" + " dbuser not found!");
			this.dbPwd = (String) ConfigManager.getConfigData("xiangtone_dbpwd",
					"xiangtone_dbpwd" + " dbpwd not found!");
		} catch (java.lang.ClassNotFoundException e) {
			logger.error("Unable to load driver:", e);
			e.printStackTrace();
		}
		DBCon = "jdbc:mysql://" + this.dbIp + ":" + this.dbPort + "/" + this.dbName + "?";
		DBCon += "user=" + this.dbUser + "&password=" + this.dbPwd + "&useUnicode=true&characterEncoding=GBK";
		logger.debug("DBCon:" + DBCon);
	}

	/**
	 * update .insert,delete 等操作
	 *
	 */
	public void executeUpdate(String sql) {
		try {
			conn = DriverManager.getConnection(DBCon);
			stmt = conn.createStatement();
			logger.debug(sql);
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			logger.error(sql, e);
			e.printStackTrace();
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException et) {
					et.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException ec) {
					ec.printStackTrace();
				}
			}
		}

	}

	/**
	 * update .insert,delete 等操作
	 *
	 */
	public void execUpdate(String sql) {
		try {
			conn = DriverManager.getConnection(DBCon);
			stmt = conn.createStatement();
			logger.debug(sql);
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			logger.error(sql, e);
			e.printStackTrace();
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException et) {
					et.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException ec) {
					ec.printStackTrace();
				}
			}
		}

	}

	/**
	 * select 数据操作
	 *
	 */
	public ResultSet executeQuery(String sql) {
		rs = null;
		try {
			conn = DriverManager.getConnection(DBCon);
			stmt = conn.createStatement();
			logger.debug(sql);
			rs = stmt.executeQuery(sql);
			conn.close();
		} catch (SQLException e) {
			logger.error(sql, e);
			e.printStackTrace();
		}
		return rs;
	}

	/**
	 * select 数据操作
	 *
	 */
	public ResultSet execQuery(String sql) {
		rs = null;
		try {
			conn = DriverManager.getConnection(DBCon);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			logger.debug(sql);
			conn.close();
		} catch (SQLException e) {
			logger.error(sql, e);
			e.printStackTrace();
		}
		return rs;
	}

	/**
	 * 关闭所有连接
	 *
	 */
	public void close() throws Exception {
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}