package com.bridge.insertmerge;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Objects;

import org.apache.log4j.Logger;

import com.bridge.main.HikariMerge;
import com.bridge.main.HikariQracleTo;

public class InsertXrateoverrideuser {

	private static final Logger logger = Logger
			.getLogger(InsertXrateoverrideuser.class);

	public static boolean Xrateoverrideuserinsert(String rsuserid,
			String rsoverridevariance, String rsoverrideadjrounding,
			String rsrowguid, Timestamp rslastupddt, String frdatabase)
			throws SQLException {

		boolean result = true;
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		String insertTableSQL;

		try {

			if (Objects.equals(frdatabase, "Oracle")) {
				// dbConnection = Merge.getDBConnection();

				HikariMerge Mergepool = HikariMerge.getInstance();
				dbConnection = Mergepool.getConnection();


				insertTableSQL = "INSERT INTO XRATE_OVERRIDE_USER"
						+ "(USER_ID,OVERRIDE_VARIANCE,OVERRIDE_ADJ_ROUNDING,ROWGUID,LAST_UPD_DT) "
						+ "VALUES" + "(?,?,?," + "newid()" + ",?)";

				preparedStatement = dbConnection
						.prepareStatement(insertTableSQL);

				preparedStatement.setString(1, rsuserid);
				preparedStatement.setString(2, rsoverridevariance);
				preparedStatement.setString(3, rsoverrideadjrounding);
				preparedStatement.setTimestamp(4, rslastupddt);

			} else {
				// dbConnection = OracleTo.getDBConnection();

				HikariQracleTo OrcaleFromTo = HikariQracleTo.getInstance();
				dbConnection = OrcaleFromTo.getConnection();

				insertTableSQL = "INSERT INTO XRATE_OVERRIDE_USER"
						+ "(USER_ID,OVERRIDE_VARIANCE,OVERRIDE_ADJ_ROUNDING,ROWGUID,LAST_UPD_DT) "
						+ "VALUES" + "(?,?,?,?,?)";

				preparedStatement = dbConnection
						.prepareStatement(insertTableSQL);

				preparedStatement.setString(1, rsuserid);
				preparedStatement.setString(2, rsoverridevariance);
				preparedStatement.setString(3, rsoverrideadjrounding);
				preparedStatement.setString(4, rsrowguid);
				preparedStatement.setTimestamp(5, rslastupddt);

			}

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

	public static boolean Xrateoverrideuserupdate(String rsuserid,
			String rsoverridevariance, String rsoverrideadjrounding,
			String rsrowguid, Timestamp rslastupddt, String frdatabase)
			throws SQLException {

		boolean result = true;
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		try {

			if (Objects.equals(frdatabase, "Oracle")) {
				// dbConnection = Merge.getDBConnection();

				HikariMerge Mergepool = HikariMerge.getInstance();
				dbConnection = Mergepool.getConnection();
			} else {
				// dbConnection = OracleTo.getDBConnection();

				HikariQracleTo OrcaleFromTo = HikariQracleTo.getInstance();
				dbConnection = OrcaleFromTo.getConnection();
			}

			String updateSQL = "UPDATE XRATE_OVERRIDE_USER "
					+ "SET  OVERRIDE_VARIANCE     = ? "
					+ ", OVERRIDE_ADJ_ROUNDING = ? "
					+ ", ROWGUID               = ? "
					+ ", LAST_UPD_DT           = ? "
					+ "WHERE USER_ID             = ? ";

			preparedStatement = dbConnection.prepareStatement(updateSQL);

			preparedStatement.setString(1, rsoverridevariance);
			preparedStatement.setString(2, rsoverrideadjrounding);
			preparedStatement.setString(3, rsrowguid);
			preparedStatement.setTimestamp(4, rslastupddt);
			preparedStatement.setString(5, rsuserid);

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

	public static boolean Xrateoverrideuserchkexists(String entitykey,
			String frdatabase) throws SQLException {

		String[] parts = entitykey.split(",");
		String userid = parts[0];

		boolean result = false;
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		try {

			if (Objects.equals(frdatabase, "Oracle")) {
				// dbConnection = Merge.getDBConnection();

				HikariMerge Mergepool = HikariMerge.getInstance();
				dbConnection = Mergepool.getConnection();
			} else {
				// dbConnection = OracleTo.getDBConnection();

				HikariQracleTo OrcaleFromTo = HikariQracleTo.getInstance();
				dbConnection = OrcaleFromTo.getConnection();
			}

			String selectSQL = "SELECT USER_ID " + "FROM XRATE_OVERRIDE_USER "
					+ "where USER_ID ='" + userid + "'";

			// logger.info(selectSQL);

			preparedStatement = dbConnection.prepareStatement(selectSQL);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				String rschktxdate = rs.getString("USER_ID");

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
