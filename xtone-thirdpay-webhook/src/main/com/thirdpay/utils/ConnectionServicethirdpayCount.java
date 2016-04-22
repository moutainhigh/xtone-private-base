package com.thirdpay.utils;


import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.common.util.ConfigManager;

/**
 */
public class ConnectionServicethirdpayCount {

	private static final String DB_PREFIX="thirdpayCount";
  private static ConnectionServicethirdpayCount instance = new ConnectionServicethirdpayCount();
	private ConnectionServicethirdpayCount(){
	}
	public static ConnectionServicethirdpayCount getInstance(){
		return instance;
	}
	
	//private final static Logger logger = Logger.getLogger(ConnectionService.class);
	private DataSource ds_isthird =setupDataSource(DB_PREFIX, 5, 50, 5, 2);

	public synchronized Connection getConnectionForLocal() {
		try {
			return ds_isthird.getConnection();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	/**
	 * @param initialSize TODO
	 * @param maxActive TODO
	 * @param maxIdle TODO
	 * @param minIdle TODO
	 * @return DataSource 
	 */
	public  static DataSource setupDataSource(String db, int initialSize, int maxActive, int maxIdle, int minIdle) {
		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName("com.mysql.jdbc.Driver");
		ds.setUrl(ConfigManager.getConfigData(db+".url"));
        ds.setUsername(ConfigManager.getConfigData(db+".user"));
        ds.setPassword(ConfigManager.getConfigData(db+".password"));
		ds.setInitialSize(initialSize);
		ds.setMaxActive(maxActive);
		ds.setMaxIdle(maxIdle);
		ds.setMinIdle(minIdle);
		ds.setMaxWait(5000);
		ds.setRemoveAbandoned(true);
		ds.setRemoveAbandonedTimeout(60);  
		ds.setLogAbandoned(true);
		ds.setMinEvictableIdleTimeMillis(30*1000);
		ds.setTimeBetweenEvictionRunsMillis(10*1000);
		return ds;
	}

	public static void printDataSourceStats(DataSource ds) throws SQLException {
		BasicDataSource bds = (BasicDataSource) ds;
		System.out.println("NumActive: " + bds.getNumActive());
		System.out.println("NumIdle: " + bds.getNumIdle());
	}

	public static void shutdownDataSource(DataSource ds) throws SQLException {
		BasicDataSource bds = (BasicDataSource) ds;
		bds.close();
	}
	
}

