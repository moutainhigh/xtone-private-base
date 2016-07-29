package com.xiangtone.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.apache.log4j.Logger;
import org.common.util.ConnectionService;

public class DBForLocal {
	private static Logger myLogger = Logger.getLogger(DBForLocal.class);

	private Connection connection = null;
	private PreparedStatement preparedStatement = null;

	public DBForLocal() {
		connection = ConnectionService.getInstance().getConnectionForLocal();
	}

	public PreparedStatement getPreparedStatement(String paramString) {
		try {
			preparedStatement = connection.prepareStatement(paramString);
		} catch (SQLException e) {
			myLogger.error("",e);
		}
		return preparedStatement;
	}

	public Connection getConnection() {
		return connection;
	}

	public void close() {
		if (this.preparedStatement != null) {
			try {
				this.preparedStatement.close();
			} catch (SQLException localSQLException2) {
				this.myLogger.error("Statement close", localSQLException2);
			}
			this.preparedStatement = null;
		}
		if (this.connection != null) {
			try {
				this.connection.close();
			} catch (SQLException localSQLException3) {
				this.myLogger.error("Connection close", localSQLException3);
			}
			this.connection = null;
		}
	}

}