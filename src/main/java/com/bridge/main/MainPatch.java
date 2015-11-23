package com.bridge.main;

import com.bridge.SQL.MSSQL;
import org.apache.log4j.Logger;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Kei on 11/23/2015.
 */

@DisallowConcurrentExecution
public class MainPatch implements Job {

    public void execute(JobExecutionContext arg0) throws JobExecutionException {
        bridgeStart();
    }

    private static final Logger logger = Logger.getLogger(MainPatch.class);

    public static void bridgeStart() {
        try {
            retryMSSQLFailRecord();
            retryRmsFailRecord();
            retryMergeFailRecord();
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }
    }

    private static void retryMSSQLFailRecord() throws SQLException {

        logger.info("retryMSSQLFailRecord");
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        HikariMssql Mssqlpool = HikariMssql.getInstance();
        dbConnection = Mssqlpool.getConnection();


        try {
            String updateSQL = MSSQL.retryMSSQLFailRecord;

            preparedStatement = dbConnection.prepareStatement(updateSQL);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            logger.info(e.getMessage());
        }
    }

    private static void retryRmsFailRecord() throws SQLException {

        logger.info("retryRmsFailRecord");
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        HikariRms Rmspool = HikariRms.getInstance();
        dbConnection = Rmspool.getConnection();

        try {
            String updateSQL = MSSQL.retryMSSQLFailRecord;

            preparedStatement = dbConnection.prepareStatement(updateSQL);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            logger.info(e.getMessage());
        }
    }

    private static void retryMergeFailRecord() throws SQLException {

        logger.info("retryMergeFailRecord");
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        HikariMerge Mergepool = HikariMerge.getInstance();
        dbConnection = Mergepool.getConnection();

        try {
            String updateSQL = MSSQL.retryMSSQLFailRecord;

            preparedStatement = dbConnection.prepareStatement(updateSQL);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            logger.info(e.getMessage());
        }
    }
}



