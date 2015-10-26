package com.bridge.main;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class HikariRms {

    private static HikariRms instance = null;
    private HikariDataSource ds = null;

    static {
        try {
            instance = new HikariRms();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }

    }

    private HikariRms() {
        HikariConfig config = new HikariConfig();
        config.setMaximumPoolSize(10);

        config.setDataSourceClassName("net.sourceforge.jtds.jdbcx.JtdsDataSource");
        config.addDataSourceProperty("portNumber", 1433);
        config.addDataSourceProperty("serverName", "hkgt20L");
        config.addDataSourceProperty("user", "sa");
        config.addDataSourceProperty("password", "%Q[Y2Tm/");
        config.addDataSourceProperty("databaseName", "rmsdb_prd");
        config.setConnectionTestQuery("SELECT 1");

        ds = new HikariDataSource(config);
    }

    public static HikariRms getInstance() {
        return instance;
    }

    public Connection getConnection() throws SQLException {
        return ds.getConnection();
    }


}
