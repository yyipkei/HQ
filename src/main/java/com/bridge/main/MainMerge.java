package com.bridge.main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.bridge.SQL.MSSQL;
import com.bridge.SQL.ORACLE;
import org.apache.log4j.Logger;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.bridge.projo.Dataupdatelog;

@DisallowConcurrentExecution
public class MainMerge implements Job {

	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		bridgeStart();
	}

	private static final Logger logger = Logger.getLogger(MainMerge.class);

	public static void bridgeStart() {
		// BasicConfigurator.configure();
		try {

			runStoredProcedure("Oracle");
			selectRecordsFromTable("Oracle");
			for (Dataupdatelog dataupdatelog : dataupdatelogs) logger.info(dataupdatelog);

			processLog("Oracle");

			logger.info("Finished Oracle -> Merge");

		} catch (SQLException e) {

			logger.info(e.getMessage());

		}

		dataupdatelogs.clear();

		/*try {

			runStoredProcedureMergelastupddt("MSSQL");
			runStoredProcedure("Merge");
			selectRecordsFromTable("Merge");
			for (Dataupdatelog dataupdatelog : dataupdatelogs)
				logger.info(dataupdatelog);

			processLog("Merge");
			runlogStoredProcedure("Oracle");
			runpstxcountStoredProcedure("Oracle");
			logger.info("Finished Merge -> Oracle");

		} catch (SQLException e) {

			logger.info(e.getMessage());

		}
		dataupdatelogs.clear();*/

	}

	public static void processLog(String database) {
		for (Dataupdatelog d : MainMerge.dataupdatelogs) {
			RouteMerge.Routeing(d.getDatalogid(), d.getEntityname(),
					d.getEntitykey(), database);
		}
	}

	public static final List<Dataupdatelog> dataupdatelogs = new ArrayList<Dataupdatelog>();


	private static void selectRecordsFromTable(String database)
			throws SQLException {

		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		String selectSQL = "SELECT DATA_UPDATE_LOG_ID ,ENTITY_NAME, ENTITY_KEY,ENTITY_UPD_DT,"
				+ " LOG_DT,BATCH_NO,IS_COMP,REMARK FROM DATA_UPDATE_LOG_POS where IS_COMP ='P' and REMARK ='Merge'";

		try {

			if (Objects.equals(database, "Oracle")) {
				 HikariQracleFrom OrcaleFrompool = HikariQracleFrom.getInstance(); 
				dbConnection = OrcaleFrompool.getConnection();
				//dbConnection = OracleFrom.getDBConnection();
			} else {
				 HikariMerge Mergepool = HikariMerge.getInstance();  
				dbConnection = Mergepool.getConnection();  
				//dbConnection = Merge.getDBConnection();
			}

			preparedStatement = dbConnection.prepareStatement(selectSQL);
			// preparedStatement.setInt(1, 1001);

			// execute select SQL statement
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {

				Dataupdatelog dataupdatelog = new Dataupdatelog();

				// String datalogid = rs.getString("DATA_UPDATE_LOG_ID");
				// String entityname = rs.getString("ENTITY_NAME");
				// String entitykey = rs.getString("ENTITY_KEY");
				// String entityupddt = rs.getString("ENTITY_UPD_DT");
				// String logdt = rs.getString("LOG_DT");
				// String batchno = rs.getString("BATCH_NO");
				// String iscomp = rs.getString("IS_COMP");
				// String remark = rs.getString("REMARK");
				//
				// System.out.println("Data : " + datalogid);
				// System.out.println("Data : " + entityname);
				// System.out.println("Data : " + entitykey);
				// System.out.println("Data : " + entityupddt);
				// System.out.println("Data : " + logdt);
				// System.out.println("Data : " + batchno);
				// System.out.println("Data : " + iscomp);
				// System.out.println("Data : " + remark);

				dataupdatelog.setDatalogid(rs.getString("DATA_UPDATE_LOG_ID"));
				dataupdatelog.setEntityname(rs.getString("ENTITY_NAME"));
				dataupdatelog.setEntitykey(rs.getString("ENTITY_KEY"));
				dataupdatelog.setEntityupddt(rs.getString("ENTITY_UPD_DT"));
				dataupdatelog.setLogdt(rs.getString("LOG_DT"));
				dataupdatelog.setBatchno(rs.getString("BATCH_NO"));
				dataupdatelog.setIscomp(rs.getString("IS_COMP"));
				dataupdatelog.setRemark(rs.getString("REMARK"));

				dataupdatelogs.add(dataupdatelog);

			}

		} catch (SQLException e) {

			logger.info(e.getMessage());

		} finally {

			if (preparedStatement != null) {
				preparedStatement.close();
			}

			if (dbConnection != null) {
				dbConnection.close();
			}

		}

	}

	private static void runStoredProcedure(String database) throws SQLException {

		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		logger.info("Starting USP_DATA_UPDATE_LOG_POS_MERGE");
		//String spSQL = "{call USP_DATA_UPDATE_LOG_POS_MERGE}";
		String spSQL = null;
		try {

			if (Objects.equals(database, "Oracle")) {
				 HikariQracleFrom OrcaleFrompool = HikariQracleFrom.getInstance(); 
				dbConnection = OrcaleFrompool.getConnection();
				//dbConnection = OracleFrom.getDBConnection();
				spSQL = ORACLE.MergeUpdate;
			} else {
				 HikariMerge Mergepool = HikariMerge.getInstance();  
				dbConnection = Mergepool.getConnection();  
				//dbConnection = Merge.getDBConnection();
				spSQL = MSSQL.MergeUpdate;
			}

			preparedStatement = dbConnection.prepareStatement(spSQL);
			preparedStatement.executeUpdate();

		} catch (SQLException e) {

			logger.info(e.getMessage());

		} finally {

			if (preparedStatement != null) {
				preparedStatement.close();
			}

			if (dbConnection != null) {
				dbConnection.close();
			}

		}

	}

	private static void runlogStoredProcedure(String database)
			throws SQLException {

		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		logger.info("Starting usp_data_update_log_merge");
		//String spSQL = "{call usp_data_update_log_merge}";
		String spSQL = ORACLE.InsertMergeDataLog;
		try {

			if (Objects.equals(database, "Oracle")) {
				HikariQracleTo OrcaleTopool = HikariQracleTo.getInstance();
				dbConnection = OrcaleTopool.getConnection();
				//dbConnection = OracleFrom.getDBConnection();
			} else {
				 HikariMerge Mergepool = HikariMerge.getInstance();  
				dbConnection = Mergepool.getConnection();  
				//dbConnection = Merge.getDBConnection();
			}

			preparedStatement = dbConnection.prepareStatement(spSQL);
			preparedStatement.executeUpdate();

		} catch (SQLException e) {

			logger.info(e.getMessage());

		} finally {

			if (preparedStatement != null) {
				preparedStatement.close();
			}

			if (dbConnection != null) {
				dbConnection.close();
			}

		}

	}
	private static void runpstxcountStoredProcedure(String database)
			throws SQLException {

		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		logger.info("Starting USP_PS_TX_COUNT_COUPON ");
		//String spSQL = "{call USP_PS_TX_COUNT_COUPON}";
		String spSQL = ORACLE.CouponPsTxCount;
		try {

			if (Objects.equals(database, "Oracle")) {
				HikariQracleTo OrcaleTopool = HikariQracleTo.getInstance();
				dbConnection = OrcaleTopool.getConnection();
				// dbConnection = OracleFrom.getDBConnection();
			} else {
				HikariMssql Mssqlpool = HikariMssql.getInstance();
				dbConnection = Mssqlpool.getConnection();
				// dbConnection = Mssql.getDBConnection();
			}

			preparedStatement = dbConnection.prepareStatement(spSQL);
			preparedStatement.executeUpdate();

		} catch (SQLException e) {

			logger.info(e.getMessage());

		} finally {

			if (preparedStatement != null) {
				preparedStatement.close();
			}

			if (dbConnection != null) {
				dbConnection.close();
			}

		}

	}

	private static void runStoredProcedureMergelastupddt(String database) throws SQLException {

		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		logger.info("Starting usp_merge_txn_last_upd_dt_batch");
		//String spSQL = "{call usp_merge_txn_last_upd_dt_batch}";
		String spSQL = MSSQL.CafeLastUpdDate;
		try {

			if (Objects.equals(database, "Oracle")) {
				HikariQracleFrom OrcaleFrompool = HikariQracleFrom
						.getInstance();
				dbConnection = OrcaleFrompool.getConnection();
				// dbConnection = OracleFrom.getDBConnection();
			} else {
				HikariMssql Mssqlpool = HikariMssql.getInstance();
				dbConnection = Mssqlpool.getConnection();
				// dbConnection = Mssql.getDBConnection();
			}

			preparedStatement = dbConnection.prepareStatement(spSQL);
			preparedStatement.executeUpdate();

		} catch (SQLException e) {

			logger.info(e.getMessage());

		} finally {

			if (preparedStatement != null) {
				preparedStatement.close();
			}

			if (dbConnection != null) {
				dbConnection.close();
			}

		}

	}

}
