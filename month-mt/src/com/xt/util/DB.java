package com.xt.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.log4j.Logger;
import org.common.util.ConnectionService;

public class DB {
	private static Logger myLogger = Logger.getLogger(DB.class);

	private Connection conn = null;
	private Statement stmt = null;
	private ResultSet rs = null;

	public DB(){
//		String str1 = (String) ConfigManager.getInstance().getConfigItem("dbip", " dbip not found!");
//		String str2 = (String) ConfigManager.getInstance().getConfigItem("dbport", "dbport not found!");
//		String str3 = (String) ConfigManager.getInstance().getConfigItem("dbname", "dbname not found!");
//		String str4 = (String) ConfigManager.getInstance().getConfigItem("dbuser", "dbuser not found!");
//		String str5 = (String) ConfigManager.getInstance().getConfigItem("dbpassword", "dbpwd not found!");
//		String str6 = "com.mysql.jdbc.Driver";
//		String str7 = "jdbc:mysql://" + str1 + ":" + str2 + "/" + str3 + "?useUnicode=true&characterEncoding=GBK";
//
//		try {
//			Class.forName(str6);
//			this.conn = DriverManager.getConnection(str7, str4, str5);
//			this.stmt = this.conn.createStatement();
//		} catch (ClassNotFoundException e) {
//			myLogger.error("DB",e);
//		} catch (SQLException e) {
//			myLogger.error("DB",e);
//		}
//		
//		this.myLogger.debug("open conn " + str7);
		
		try {
			conn=ConnectionService.getInstance().getConnectionForLocal();
			stmt = conn.createStatement();
		} catch (SQLException e) {
			myLogger.error("DB",e);
		}
	}

	public int executeUpdate(String paramString) throws SQLException {
		return this.stmt.executeUpdate(paramString);
	}

	public void executeQuery(String paramString) throws SQLException {
		this.rs = null;
		this.rs = this.stmt.executeQuery(paramString);
	}

	public void close() {
		if (this.rs != null) {
			try {
				this.rs.close();
				this.myLogger.debug("ResultSet close ok.");
			} catch (SQLException localSQLException1) {
				this.myLogger.error("ResultSet close", localSQLException1);
			}
			this.rs = null;
		}
		if (this.stmt != null) {
			try {
				this.stmt.close();
				this.myLogger.debug("Statement close ok.");
			} catch (SQLException localSQLException2) {
				this.myLogger.error("Statement close", localSQLException2);
			}
			this.stmt = null;
		}
		if (this.conn != null) {
			try {
				this.conn.close();
				this.myLogger.debug("Connection close ok.");
			} catch (SQLException localSQLException3) {
				this.myLogger.error("Connection close", localSQLException3);
			}
			this.conn = null;
		}
	}

	public ResultSet getRs() {
		return this.rs;
	}

//	public void setRs(ResultSet paramResultSet) {
//		this.rs = paramResultSet;
//	}
	
	public static void main(String[] args) {
		String sql="SELECT * FROM `tbl_base_users` WHERE id=1";
		DB db=new DB();
		try {
			db.executeQuery(sql);
			ResultSet rs=db.getRs();
			if(rs.next()){
				myLogger.debug(rs.getString("name"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}