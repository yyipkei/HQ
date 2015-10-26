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

public class InsertGoadept {

	private static final Logger logger = Logger.getLogger(InsertGoadept.class);

	public static boolean Goadeptinsert(String rsdeptcde, String rsdeptdesc,
			String rssortseq, String rsdepthead, String rsemail,
			String rsemaildomain, Timestamp rslastupddt, String rsentitykey,
			String frdatabase) throws SQLException {

		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		String insertTableSQL;

		insertTableSQL = "INSERT INTO GOA_DEPT (DEPT_CDE,DEPT_DESC,SORT_SEQ,DEPT_HEAD,EMAIL,EMAIL_DOMAIN,LAST_UPD_DT,ENTITY_KEY) "
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

			preparedStatement.setString(1, rsdeptcde);
			preparedStatement.setString(2, rsdeptdesc);
			preparedStatement.setString(3, rssortseq);
			preparedStatement.setString(4, rsdepthead);
			preparedStatement.setString(5, rsemail);
			preparedStatement.setString(6, rsemaildomain);
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

	public static boolean Goadeptupdate(String rsdeptcde, String rsdeptdesc,
			String rssortseq, String rsdepthead, String rsemail,
			String rsemaildomain, Timestamp rslastupddt, String rsentitykey,
			String frdatabase) throws SQLException {

		boolean result = true;
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		String updateSQL;

		updateSQL = "UPDATE GOA_DEPT " + "SET DEPT_CDE   = ? "
				+ ", DEPT_DESC    = ? " + ", SORT_SEQ     = ? "
				+ ", DEPT_HEAD    = ? " + ", EMAIL        = ? "
				+ ", EMAIL_DOMAIN = ? " + ", LAST_UPD_DT  = ? "
				+ "WHERE  ENTITY_KEY   = ? ";

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

			preparedStatement.setString(1, rsdeptcde);
			preparedStatement.setString(2, rsdeptdesc);
			preparedStatement.setString(3, rssortseq);
			preparedStatement.setString(4, rsdepthead);
			preparedStatement.setString(5, rsemail);
			preparedStatement.setString(6, rsemaildomain);
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

	public static boolean Goadeptchkexists(String entitykey, String frdatabase)
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

			String selectSQL = "SELECT DEPT_CDE " + "FROM GOA_DEPT "
					+ "where ENTITY_KEY ='" + entitykey + "'";

			// logger.info(selectSQL);

			preparedStatement = dbConnection.prepareStatement(selectSQL);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				String rschktxdate = rs.getString("DEPT_CDE");

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
