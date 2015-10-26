package com.bridge.main;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class HikariMerge {

    private static HikariMerge instance = null;
    private HikariDataSource ds = null;

    static {
        try {
            instance = new HikariMerge();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }

    }

    private HikariMerge() {
        HikariConfig config = new HikariConfig();
        config.setMaximumPoolSize(10);

        config.setDataSourceClassName("net.sourceforge.jtds.jdbcx.JtdsDataSource");
        config.addDataSourceProperty("portNumber", 1433);
        config.addDataSourceProperty("serverName", "hkct5L");
        config.addDataSourceProperty("user", "sysadmin");
        config.addDataSourceProperty("password", "chocoa");
        config.addDataSourceProperty("databaseName", "merge_pos_revamp");
        config.setConnectionTestQuery("SELECT 1");

        ds = new HikariDataSource(config);
    }

    public static HikariMerge getInstance() {
        return instance;
    }

    public Connection getConnection() throws SQLException {
        return ds.getConnection();
    }


}
