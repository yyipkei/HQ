package com.bridge.main;

import java.sql.Connection;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class HikariQracleTo {

	private static HikariQracleTo instance = null;
	private HikariDataSource ds = null;

	static {
		try {
			instance = new HikariQracleTo();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}

	}

	private HikariQracleTo() {
		HikariConfig config = new HikariConfig();
		config.setMaximumPoolSize(10);
		
		String serverName = "10.1.7.60";
		String serverPort = "1521";
		String sid = "stsmprd";
		String url = "jdbc:oracle:thin:@" + serverName + ":" + serverPort
				+ "/" + sid;

		config.setJdbcUrl(url);
		config.setUsername("BRIDGE");
		config.setPassword("BRIDGElc");

		//config.setDataSourceClassName("oracle.jdbc.pool.OracleDataSource");
		//config.addDataSourceProperty("portNumber", 1521);
		//config.addDataSourceProperty("serverName", "10.1.7.44");
		//config.addDataSourceProperty("user", "BRIDGE");
		//config.addDataSourceProperty("password", "BRIDGElc");
		//config.addDataSourceProperty("databaseName", "BRIDGE");
		//config.setConnectionTestQuery("SELECT 1");

		ds = new HikariDataSource(config);
	}

	public static HikariQracleTo getInstance() {
		return instance;
	}

	public Connection getConnection() throws SQLException {
		return ds.getConnection();
	}


}
