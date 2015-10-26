package com.bridge.insertgoasetting;

import com.bridge.main.HikariMssql;
import com.bridge.main.HikariQracleTo;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.Objects;

/**
 * Created by keyip on 01-Jun-15.
 */
public class InsertGoastaff {

	private static final Logger logger = Logger.getLogger(InsertGoastaff.class);

	public static boolean Goastaffinsert(String rsstaffname, String rsdeptcde,
			String rsemail, String rsphone, String rsjobtitle, String rsactive,
			String rsstaffid, Timestamp rslastupddt, String rslastupdusr,
			String rslastupdver, String rsentitykey, String frdatabase)
			throws SQLException {

		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		String insertTableSQL;

		insertTableSQL = "INSERT INTO GOA_STAFF (STAFF_NAME,DEPT_CDE,EMAIL,PHONE,JOB_TITLE,ACTIVE,STAFF_ID,LAST_UPD_DT,LAST_UPD_USR,LAST_UPD_VER,ENTITY_KEY) "
				+ "VALUES (?,?,?,?,?,?,?,?,?,?,? )";

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

			preparedStatement.setString(1, rsstaffname);
			preparedStatement.setString(2, rsdeptcde);
			preparedStatement.setString(3, rsemail);
			preparedStatement.setString(4, rsphone);
			preparedStatement.setString(5, rsjobtitle);
			preparedStatement.setString(6, rsactive);
			preparedStatement.setString(7, rsstaffid);
			preparedStatement.setTimestamp(8, rslastupddt);
			preparedStatement.setString(9, rslastupdusr);
			preparedStatement.setString(10, rslastupdver);
			preparedStatement.setString(11, rsentitykey);

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

	public static boolean Goastaffupdate(String rsstaffname, String rsdeptcde,
			String rsemail, String rsphone, String rsjobtitle, String rsactive,
			String rsstaffid, Timestamp rslastupddt, String rslastupdusr,
			String rslastupdver, String rsentitykey, String frdatabase)
			throws SQLException {

		boolean result = true;
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		String updateSQL;

		updateSQL = "UPDATE GOA_STAFF " + "SET STAFF_NAME = ? "
				+ ", DEPT_CDE     = ? " + ", EMAIL        = ? "
				+ ", PHONE        = ? " + ", JOB_TITLE    = ? "
				+ ", ACTIVE       = ? " + ", STAFF_ID     = ? "
				+ ", LAST_UPD_DT  = ? " + ", LAST_UPD_USR = ? "
				+ ", LAST_UPD_VER = ? " + "WHERE ENTITY_KEY   = ? ";

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

			preparedStatement.setString(1, rsstaffname);
			preparedStatement.setString(2, rsdeptcde);
			preparedStatement.setString(3, rsemail);
			preparedStatement.setString(4, rsphone);
			preparedStatement.setString(5, rsjobtitle);
			preparedStatement.setString(6, rsactive);
			preparedStatement.setString(7, rsstaffid);
			preparedStatement.setTimestamp(8, rslastupddt);
			preparedStatement.setString(9, rslastupdusr);
			preparedStatement.setString(10, rslastupdver);
			preparedStatement.setString(11, rsentitykey);

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

	public static boolean Goastaffchkexists(String entitykey, String frdatabase)
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

				HikariMssql Mssqlpool = HikariMssql.getInstance();
				dbConnection = Mssqlpool.getConnection();
			} else {
				// dbConnection = OracleTo.getDBConnection();

				HikariQracleTo OrcaleFromTo = HikariQracleTo.getInstance();
				dbConnection = OrcaleFromTo.getConnection();
			}

			String selectSQL = "SELECT STAFF_NAME " + "FROM GOA_STAFF "
					+ "where ENTITY_KEY ='" + entitykey + "'";

			// logger.info(selectSQL);

			preparedStatement = dbConnection.prepareStatement(selectSQL);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				String rschktxdate = rs.getString("STAFF_NAME");

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
