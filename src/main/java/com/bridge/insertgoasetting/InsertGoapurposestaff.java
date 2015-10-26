package com.bridge.insertgoasetting;

import com.bridge.main.HikariMssql;
import com.bridge.main.HikariQracleTo;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.Objects;

/**
 * Created by keyip on 01-Jun-15.
 */
public class InsertGoapurposestaff {

	private static final Logger logger = Logger
			.getLogger(InsertGoapurposestaff.class);

	public static boolean Goapurposestaffinsert(String rsstaffid,
			String rspurposecde, String rslimit, String rslimitfrdate,
			String rslimittodate, Timestamp rslastupddt, String rsentitykey,
			String frdatabase) throws SQLException {

		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		String insertTableSQL;

		insertTableSQL = "INSERT INTO goa_purpose_staff (STAFF_ID,PURPOSE_CDE,LIMIT,LIMIT_FR_DATE,LIMIT_TO_DATE,LAST_UPD_DT,ENTITY_KEY) "
				+ "VALUES (?,?,?,?,?,?,? )";

		try {

			if (Objects.equals(frdatabase, "Oracle")) {
				// dbConnection = Mssql.getDBConnection();

				HikariMssql Mssqlpool = HikariMssql.getInstance();
				dbConnection = Mssqlpool.getConnection();
			} else {
				// dbConnection = OracleTo.getDBConnection();

				HikariQracleTo OrcaleFromTo = HikariQracleTo.getInstance();
				dbConnection = OrcaleFromTo.getConnection();
			}

			preparedStatement = dbConnection.prepareStatement(insertTableSQL);

			preparedStatement.setString(1, rsstaffid);
			preparedStatement.setString(2, rspurposecde);
			preparedStatement.setString(3, rslimit);
			preparedStatement.setString(4, rslimitfrdate);
			preparedStatement.setString(5, rslimittodate);
			preparedStatement.setTimestamp(6, rslastupddt);
			preparedStatement.setString(7, rsentitykey);

			preparedStatement.executeUpdate();

			return true;

		} catch (SQLException e) {

			logger.info(e.getMessage());

			return false;

		} finally {

			if (preparedStatement != null) {
				preparedStatement.close();
			}

			if (dbConnection != null) {
				dbConnection.close();
			}
		}

	}

	public static boolean Goapurposestaffupdate(String rsstaffid,
			String rspurposecde, String rslimit, String rslimitfrdate,
			String rslimittodate, Timestamp rslastupddt, String rsentitykey,
			String frdatabase) throws SQLException {

		boolean result = true;
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		String updateSQL;

		updateSQL = "UPDATE GOA_PURPOSE_STAFF " + "SET STAFF_ID    = ? "
				+ ", PURPOSE_CDE   = ? " + ", LIMIT         = ? "
				+ ", LIMIT_FR_DATE = ? " + ", LIMIT_TO_DATE = ? "
				+ ", LAST_UPD_DT   = ? " + "WHERE  ENTITY_KEY    = ? ";

		try {

			if (Objects.equals(frdatabase, "Oracle")) {
				// dbConnection = Mssql.getDBConnection();

				HikariMssql Mssqlpool = HikariMssql.getInstance();
				dbConnection = Mssqlpool.getConnection();
			} else {
				// dbConnection = OracleTo.getDBConnection();

				HikariQracleTo OrcaleFromTo = HikariQracleTo.getInstance();
				dbConnection = OrcaleFromTo.getConnection();
			}

			preparedStatement = dbConnection.prepareStatement(updateSQL);

			preparedStatement.setString(1, rsstaffid);
			preparedStatement.setString(2, rspurposecde);
			preparedStatement.setString(3, rslimit);
			preparedStatement.setString(4, rslimitfrdate);
			preparedStatement.setString(5, rslimittodate);
			preparedStatement.setTimestamp(6, rslastupddt);
			preparedStatement.setString(7, rsentitykey);

			preparedStatement.executeUpdate();

			return result;

		} catch (SQLException e) {

			logger.info(e.getMessage());
			result = false;

			logger.info(false);

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

	public static boolean Goapurposestaffchkexists(String entitykey,
			String frdatabase) throws SQLException {
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

				HikariMssql Mssqlpool = HikariMssql.getInstance();
				dbConnection = Mssqlpool.getConnection();
			} else {
				// dbConnection = OracleTo.getDBConnection();

				HikariQracleTo OrcaleFromTo = HikariQracleTo.getInstance();
				dbConnection = OrcaleFromTo.getConnection();
			}

			String selectSQL = "SELECT STAFF_ID " + "FROM GOA_PURPOSE_STAFF "
					+ "where ENTITY_KEY ='" + entitykey + "'";

			// logger.info(selectSQL);

			preparedStatement = dbConnection.prepareStatement(selectSQL);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				String rschktxdate = rs.getString("STAFF_ID");

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
