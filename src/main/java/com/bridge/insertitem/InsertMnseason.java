package com.bridge.insertitem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Objects;

import com.bridge.main.HikariRms;
import org.apache.log4j.Logger;

import com.bridge.main.HikariMssql;
import com.bridge.main.HikariQracleTo;

public class InsertMnseason {

	private static final Logger logger = Logger.getLogger(InsertMnseason.class);

	public static boolean Mnseasoninsert(String rsseasoncde,
			String rsseasondesc, String rsseasonyear, String rsseasonseq,
			String rsseasontype, String rsseasonsubtype,
			Timestamp rsseasonstartdate, Timestamp rsseasonenddate,
			String rslastupdusr, Timestamp rslastupddt, String rslastupdver,
			String rsrowguid, String rsentitykey, String frdatabase)
			throws SQLException {

		boolean result = true;
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		String insertTableSQL;

		insertTableSQL = "INSERT INTO MN_SEASON (SEASON_CDE,SEASON_DESC,SEASON_YEAR,SEASON_SEQ,SEASON_TYPE,SEASON_SUB_TYPE,"
				+ "SEASON_START_DATE,SEASON_END_DATE,LAST_UPD_USR,LAST_UPD_DT,LAST_UPD_VER,ROWGUID,ENTITY_KEY) "
				+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,? )";

		try {

			if (Objects.equals(frdatabase, "Oracle")) {
				// dbConnection = Mssql.getDBConnection();

                HikariRms Rmspool = HikariRms.getInstance();
                dbConnection = Rmspool.getConnection();
			} else {
				// dbConnection = OracleTo.getDBConnection();

				HikariQracleTo OrcaleFromTo = HikariQracleTo.getInstance();
				dbConnection = OrcaleFromTo.getConnection();
			}

			preparedStatement = dbConnection.prepareStatement(insertTableSQL);

			preparedStatement.setString(1, rsseasoncde);
			preparedStatement.setString(2, rsseasondesc);
			preparedStatement.setString(3, rsseasonyear);
			preparedStatement.setString(4, rsseasonseq);
			preparedStatement.setString(5, rsseasontype);
			preparedStatement.setString(6, rsseasonsubtype);
			preparedStatement.setTimestamp(7, rsseasonstartdate);
			preparedStatement.setTimestamp(8, rsseasonenddate);
			preparedStatement.setString(9, rslastupdusr);
			preparedStatement.setTimestamp(10, rslastupddt);
			preparedStatement.setString(11, rslastupdver);
			preparedStatement.setString(12, rsrowguid);
			preparedStatement.setString(13, rsentitykey);

			preparedStatement.executeUpdate();
			return result;

		} catch (SQLException e) {

			logger.info(e.getMessage());
			result = false;
			return result;

		} finally {

			if (preparedStatement != null) {
				preparedStatement.close();
			}

			if (dbConnection != null) {
				dbConnection.close();
			}
		}

	}

	public static boolean Mnseasonupdate(String rsseasoncde,
			String rsseasondesc, String rsseasonyear, String rsseasonseq,
			String rsseasontype, String rsseasonsubtype,
			Timestamp rsseasonstartdate, Timestamp rsseasonenddate,
			String rslastupdusr, Timestamp rslastupddt, String rslastupdver,
			String rsrowguid, String rsentitykey, String frdatabase)
			throws SQLException {

		boolean result = true;
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		String updateSQL;

		updateSQL = "UPDATE MN_SEASON " + "SET SEASON_CDE      = ? "
				+ ", SEASON_DESC       = ? " + ", SEASON_YEAR       = ? "
				+ ", SEASON_SEQ        = ? " + ", SEASON_TYPE       = ? "
				+ ", SEASON_SUB_TYPE   = ? " + ", SEASON_START_DATE = ? "
				+ ", SEASON_END_DATE   = ? " + ", LAST_UPD_USR      = ? "
				+ ", LAST_UPD_DT       = ? " + ", LAST_UPD_VER      = ? "
				+ ", ROWGUID           = ? " + "WHERE  ENTITY_KEY        = ? ";

		try {

			if (Objects.equals(frdatabase, "Oracle")) {
				// dbConnection = Mssql.getDBConnection();

                HikariRms Rmspool = HikariRms.getInstance();
                dbConnection = Rmspool.getConnection();
			} else {
				// dbConnection = OracleTo.getDBConnection();

				HikariQracleTo OrcaleFromTo = HikariQracleTo.getInstance();
				dbConnection = OrcaleFromTo.getConnection();
			}

			preparedStatement = dbConnection.prepareStatement(updateSQL);

			preparedStatement.setString(1, rsseasoncde);
			preparedStatement.setString(2, rsseasondesc);
			preparedStatement.setString(3, rsseasonyear);
			preparedStatement.setString(4, rsseasonseq);
			preparedStatement.setString(5, rsseasontype);
			preparedStatement.setString(6, rsseasonsubtype);
			preparedStatement.setTimestamp(7, rsseasonstartdate);
			preparedStatement.setTimestamp(8, rsseasonenddate);
			preparedStatement.setString(9, rslastupdusr);
			preparedStatement.setTimestamp(10, rslastupddt);
			preparedStatement.setString(11, rslastupdver);
			preparedStatement.setString(12, rsrowguid);
			preparedStatement.setString(13, rsentitykey);

			// logger.info(updateSQL);

			preparedStatement.executeUpdate();
			return result;

		} catch (SQLException e) {

			logger.info(e.getMessage());
			result = false;

			logger.info(result);
			return result;

		} finally {

			if (preparedStatement != null) {
				preparedStatement.close();
			}

			if (dbConnection != null) {
				dbConnection.close();
			}
		}

	}

	public static boolean Mnseasonchkexists(String entitykey, String frdatabase)
			throws SQLException {
		/*
		 * String[] parts = entitykey.split(","); String txdate = parts[0];
		 * String loccode = parts[1]; String regno = parts[2]; String txno =
		 * parts[3]; String seqno = parts[4];
		 */
		boolean result = false;
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		try {

			if (Objects.equals(frdatabase, "Oracle")) {
				// dbConnection = Mssql.getDBConnection();

                HikariRms Rmspool = HikariRms.getInstance();
                dbConnection = Rmspool.getConnection();
			} else {
				// dbConnection = OracleTo.getDBConnection();

				HikariQracleTo OrcaleFromTo = HikariQracleTo.getInstance();
				dbConnection = OrcaleFromTo.getConnection();
			}

			String selectSQL = "SELECT SEASON_CDE " + "FROM MN_SEASON "
					+ "where ENTITY_KEY ='" + entitykey + "'";

			// logger.info(selectSQL);

			preparedStatement = dbConnection.prepareStatement(selectSQL);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				String rschktxdate = rs.getString("SEASON_CDE");

				// logger.info(rschktxdate);

				if (rschktxdate != null) {
					result = true;// not exists
				}
			}
			return result;
		} catch (SQLException e) {

			logger.info(e.getMessage());
			result = false;
			return result;

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
