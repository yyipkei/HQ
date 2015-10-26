package com.bridge.main;

import java.sql.Connection;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class HikariQracleFrom {

	private static HikariQracleFrom instance = null;
	private HikariDataSource ds = null;

	static {
		try {
			instance = new HikariQracleFrom();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}

	}

	private HikariQracleFrom() {
		HikariConfig config = new HikariConfig();
		config.setMaximumPoolSize(10);

		String serverName = "10.1.7.60";
		String serverPort = "1521";
		String sid = "stsmprd";
		String url = "jdbc:oracle:thin:@" + serverName + ":" + serverPort
				+ "/" + sid;

		config.setJdbcUrl(url);
		config.setUsername("BRIDGEFR");
		config.setPassword("BRIDGElc");
		/*
		QA - 10.1.7.44
		SYSTEM
		lcdev
		SID ORCL

		UAT - 10.1.7.42
		sys
		posdev
		SID ORCL

		Prod - 10.1.7.55
		sys
		posdev
		Service name stsmprd
		*/

		//config.setDataSourceClassName("oracle.jdbc.pool.OracleDataSource");
		//config.addDataSourceProperty("portNumber", 1521);
		//config.addDataSourceProperty("serverName", "10.1.7.44");
		//config.addDataSourceProperty("user", "BRIDGE");
		//config.addDataSourceProperty("password", "BRIDGElc");
		//config.addDataSourceProperty("databaseName", "BRIDGE");
		//config.setConnectionTestQuery("SELECT 1");

		ds = new HikariDataSource(config);
	}

	public static HikariQracleFrom getInstance() {
		return instance;
	}

	public Connection getConnection() throws SQLException {
		return ds.getConnection();
	}


}
