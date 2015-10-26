package com.bridge.result;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;

import com.bridge.main.HikariRms;
import org.apache.log4j.Logger;

import com.bridge.main.HikariMssql;
import com.bridge.main.HikariQracleFrom;
import com.bridge.main.HikariMerge;

public class Logupdateresult {

	private static final Logger logger = Logger.getLogger(Logupdateresult.class);

	public static void Updatelogresult(String dataupdatelog, String entityname,
		boolean result, String database) throws SQLException {

		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		String updateSQL;

		try {

			if (Objects.equals(database, "Oracle")) {
				//dbConnection = OracleFrom.getDBConnection();
				HikariQracleFrom OrcaleFrompool = HikariQracleFrom.getInstance();
				dbConnection = OrcaleFrompool.getConnection();
				//updateSQL = "Update DATA_UPDATE_LOG_POS set IS_COMP = ? ,LOG_DT = SYSTIMESTAMP where DATA_UPDATE_LOG_ID = ? and ENTITY_NAME = ? ";
				updateSQL = "Update DATA_UPDATE_LOG_POS set IS_COMP = ? ,LOG_DT = SYSTIMESTAMP where DATA_UPDATE_LOG_ID = ? ";
			} else if (Objects.equals(database, "RMS")){
				HikariRms Rmspool = HikariRms.getInstance();
				dbConnection = Rmspool.getConnection();
				//updateSQL = "Update DATA_UPDATE_LOG_POS set IS_COMP = ? ,LOG_DT = getdate() where DATA_UPDATE_LOG_ID = ? and ENTITY_NAME = ? ";
				updateSQL = "Update DATA_UPDATE_LOG_POS set IS_COMP = ? ,LOG_DT = getdate() where DATA_UPDATE_LOG_ID = ? ";
			} else if (Objects.equals(database, "Merge")){
				HikariMerge Mergepool = HikariMerge.getInstance();
				dbConnection = Mergepool.getConnection();
				//updateSQL = "Update DATA_UPDATE_LOG_POS set IS_COMP = ? ,LOG_DT = getdate() where DATA_UPDATE_LOG_ID = ? and ENTITY_NAME = ? ";
				updateSQL = "Update DATA_UPDATE_LOG_POS set IS_COMP = ? ,LOG_DT = getdate() where DATA_UPDATE_LOG_ID = ? ";
			} else {
				// dbConnection = Mssql.getDBConnection();
				HikariMssql Mssqlpool = HikariMssql.getInstance();
				dbConnection = Mssqlpool.getConnection();
				//updateSQL = "Update DATA_UPDATE_LOG_POS set IS_COMP = ? ,LOG_DT = getdate() where DATA_UPDATE_LOG_ID = ? and ENTITY_NAME = ? ";
				updateSQL = "Update DATA_UPDATE_LOG_POS set IS_COMP = ? ,LOG_DT = getdate() where DATA_UPDATE_LOG_ID = ? ";
			}

			preparedStatement = dbConnection.prepareStatement(updateSQL);

			if (result) {
				preparedStatement.setString(1, "Y");
				//logger.debug("true");
			} else {
				preparedStatement.setString(1, "N");
				//logger.debug("flase");
			}

			preparedStatement.setString(2, dataupdatelog);
			//preparedStatement.setString(3, entityname);

			preparedStatement.executeUpdate();
		} catch (SQLException e) {

			System.out.println(e.getMessage());

		} finally {

			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			if (dbConnection != null) {
				try {
					dbConnection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
