package com.bridge.insertgoasetting;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Objects;

import org.apache.log4j.Logger;

import com.bridge.main.HikariMssql;
import com.bridge.main.HikariQracleTo;

public class InsertGoatc {

	private static final Logger logger = Logger.getLogger(InsertGoatc.class);

	public static boolean Goatcinsert(String rstccde, String rstcdesc,
			String rstctype, String rsdkttype, String rstcdesctc,
			String rstcdescsc, Timestamp rslastupddt, String rsentitykey,
			String frdatabase) throws SQLException {

		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		String insertTableSQL;

		insertTableSQL = "INSERT INTO GOA_TC (TC_CDE,TC_DESC,TC_TYPE,DKT_TYPE,TC_DESC_TC,TC_DESC_SC,LAST_UPD_DT,ENTITY_KEY) "
				+ "VALUES (?,?,?,?,?,?,?,? )";

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

			preparedStatement.setString(1, rstccde);
			preparedStatement.setString(2, rstcdesc);
			preparedStatement.setString(3, rstctype);
			preparedStatement.setString(4, rsdkttype);
			preparedStatement.setString(5, rstcdesctc);
			preparedStatement.setString(6, rstcdescsc);
			preparedStatement.setTimestamp(7, rslastupddt);
			preparedStatement.setString(8, rsentitykey);

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

	public static boolean Goatcupdate(String rstccde, String rstcdesc,
			String rstctype, String rsdkttype, String rstcdesctc,
			String rstcdescsc, Timestamp rslastupddt, String rsentitykey,
			String frdatabase) throws SQLException {

		boolean result = true;
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		String updateSQL;

		updateSQL = "UPDATE GOA_TC " + "SET TC_CDE    = ? "
				+ ", TC_DESC     = ? " + ", TC_TYPE     = ? "
				+ ", DKT_TYPE    = ? " + ", TC_DESC_TC  = ? "
				+ ", TC_DESC_SC  = ? " + ", LAST_UPD_DT = ? "
				+ "WHERE ENTITY_KEY  = ? ";

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

			preparedStatement.setString(1, rstccde);
			preparedStatement.setString(2, rstcdesc);
			preparedStatement.setString(3, rstctype);
			preparedStatement.setString(4, rsdkttype);
			preparedStatement.setString(5, rstcdesctc);
			preparedStatement.setString(6, rstcdescsc);
			preparedStatement.setTimestamp(7, rslastupddt);
			preparedStatement.setString(8, rsentitykey);

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

	public static boolean Goatcchkexists(String entitykey, String frdatabase)
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

			String selectSQL = "SELECT TC_CDE " + "FROM GOA_TC "
					+ "where ENTITY_KEY ='" + entitykey + "'";

			// logger.info(selectSQL);

			preparedStatement = dbConnection.prepareStatement(selectSQL);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				String rschktxdate = rs.getString("TC_CDE");

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
