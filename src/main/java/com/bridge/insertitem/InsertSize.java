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

public class InsertSize {

	private static final Logger logger = Logger.getLogger(InsertSize.class);

	public static boolean Sizeinsert(String rssizeset, String rssizecode,
									 String rssizedesc, Timestamp rslastupddt, String rsentitykey,
									 String frdatabase) throws SQLException {

		boolean result = true;
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		String insertTableSQL;

		try {

			if (Objects.equals(frdatabase, "Oracle")) {
				// dbConnection = Mssql.getDBConnection();

				HikariRms Rmspool = HikariRms.getInstance();
				dbConnection = Rmspool.getConnection();
				insertTableSQL = "INSERT INTO Size (SIZE_SET,SIZE_CODE,SIZE_DESC,LAST_UPD_DT,ENTITY_KEY) "
						+ "VALUES ( ?,?,?,?,? )";
			} else {
				// dbConnection = OracleTo.getDBConnection();

				HikariQracleTo OrcaleFromTo = HikariQracleTo.getInstance();
				dbConnection = OrcaleFromTo.getConnection();
				insertTableSQL = "INSERT INTO SIZE_ (SIZE_SET,SIZE_CODE,SIZE_DESC,LAST_UPD_DT,ENTITY_KEY) "
						+ "VALUES ( ?,?,?,?,? )";
			}

			preparedStatement = dbConnection.prepareStatement(insertTableSQL);

			preparedStatement.setString(1, rssizeset);
			preparedStatement.setString(2, rssizecode);
			preparedStatement.setString(3, rssizedesc);
			preparedStatement.setTimestamp(4, rslastupddt);
			preparedStatement.setString(5, rsentitykey);

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

	public static boolean Sizeupdate(String rssizeset, String rssizecode,
									 String rssizedesc, Timestamp rslastupddt, String rsentitykey,
									 String frdatabase) throws SQLException {

		boolean result = true;
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		String updateSQL;

		updateSQL = "UPDATE SIZE_ " +  "SET SIZE_DESC   = ? "
				+ ", LAST_UPD_DT = ? " + ", ENTITY_KEY = ? " + "WHERE  SIZE_SET  = ? " + "and  SIZE_CODE  = ? ";

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

			preparedStatement.setString(1, rssizedesc);
			preparedStatement.setTimestamp(2, rslastupddt);
			preparedStatement.setString(3, rsentitykey);
			preparedStatement.setString(4, rssizeset);
			preparedStatement.setString(5, rssizecode);


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

	public static boolean Sizechkexists(String entitykey, String frdatabase)
			throws SQLException {
		/*
		 * String[] parts = entitykey.split(","); String txdate = parts[0];
		 * String loccode = parts[1]; String regno = parts[2]; String txno =
		 * parts[3]; String seqno = parts[4];
		 */

		String[] parts =  entitykey.split(",");
		String sizeset = parts[0];
		String sizecode = parts[1];

		boolean result = false;
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		String selectSQL;

		try {

			if (Objects.equals(frdatabase, "Oracle")) {
				// dbConnection = Mssql.getDBConnection();

				HikariRms Rmspool = HikariRms.getInstance();
				dbConnection = Rmspool.getConnection();
				selectSQL = "SELECT SIZE_SET " + "FROM Size "
						+ "where SIZE_SET ='"
						+ sizeset
						+ "'"
						+ "and SIZE_CODE ='"
						+ sizecode
						+ "'";
			} else {
				// dbConnection = OracleTo.getDBConnection();

				HikariQracleTo OrcaleFromTo = HikariQracleTo.getInstance();
				dbConnection = OrcaleFromTo.getConnection();
				selectSQL = "SELECT SIZE_SET " + "FROM Size_ "
						+ "where SIZE_SET ='"
						+ sizeset
						+ "'"
						+ "and SIZE_CODE ='"
						+ sizecode
						+ "'";
			}

			// logger.info(selectSQL);

			preparedStatement = dbConnection.prepareStatement(selectSQL);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				String rschktxdate = rs.getString("SIZE_SET");

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
