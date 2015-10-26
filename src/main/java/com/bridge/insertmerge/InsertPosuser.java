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

public class InsertPosuser {

	private static final Logger logger = Logger.getLogger(InsertPosuser.class);

	public static boolean Posuserinsert(String rsuserid, String rspassword,
			String rsname, String rsgroupid, String rsloccode,
			String rsresperiod, String rsactive, String rsrowguid,
			Timestamp rslastupddt, String frdatabase) throws SQLException {

		boolean result = true;
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		String insertTableSQL;

		try {

			if (Objects.equals(frdatabase, "Oracle")) {
				// dbConnection = Merge.getDBConnection();

				HikariMerge Mergepool = HikariMerge.getInstance();
				dbConnection = Mergepool.getConnection();

				insertTableSQL = "INSERT INTO pos_user"
						+ "(USER_ID,PASSWORD,NAME,GROUP_ID,LOC_CODE,RES_PERIOD,ACTIVE,ROWGUID,LAST_UPD_DT) "
						+ "VALUES" + "(?,?,?,?,?,?,?," + "newid()" + ",?)";

				preparedStatement = dbConnection
						.prepareStatement(insertTableSQL);

				preparedStatement.setString(1, rsuserid);
				preparedStatement.setString(2, rspassword);
				preparedStatement.setString(3, rsname);
				preparedStatement.setString(4, rsgroupid);
				preparedStatement.setString(5, rsloccode);
				preparedStatement.setString(6, rsresperiod);
				preparedStatement.setString(7, rsactive);
				preparedStatement.setTimestamp(8, rslastupddt);

			} else {
				// dbConnection = OracleTo.getDBConnection();

				HikariQracleTo OrcaleFromTo = HikariQracleTo.getInstance();
				dbConnection = OrcaleFromTo.getConnection();

				insertTableSQL = "INSERT INTO pos_user"
						+ "(USER_ID,PASSWORD,NAME,GROUP_ID,LOC_CODE,RES_PERIOD,ACTIVE,ROWGUID,LAST_UPD_DT) "
						+ "VALUES" + "(?,?,?,?,?,?,?,?,?)";

				preparedStatement = dbConnection
						.prepareStatement(insertTableSQL);

				preparedStatement.setString(1, rsuserid);
				preparedStatement.setString(2, rspassword);
				preparedStatement.setString(3, rsname);
				preparedStatement.setString(4, rsgroupid);
				preparedStatement.setString(5, rsloccode);
				preparedStatement.setString(6, rsresperiod);
				preparedStatement.setString(7, rsactive);
				preparedStatement.setString(8, rsrowguid);
				preparedStatement.setTimestamp(9, rslastupddt);

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

	public static boolean Posuserupdate(String rsuserid, String rspassword,
			String rsname, String rsgroupid, String rsloccode,
			String rsresperiod, String rsactive, String rsrowguid,
			Timestamp rslastupddt, String frdatabase) throws SQLException {

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

			String updateSQL = "UPDATE POS_USER " + "SET  PASSWORD    = ? "
					+ ", NAME        = ? " + ", GROUP_ID    = ? "
					+ ", LOC_CODE    = ? " + ", RES_PERIOD  = ? "
					+ ", ACTIVE      = ? " + ", ROWGUID     = ? "
					+ ", LAST_UPD_DT = ? " + "WHERE USER_ID   = ? ";

			preparedStatement = dbConnection.prepareStatement(updateSQL);

			preparedStatement.setString(1, rspassword);
			preparedStatement.setString(2, rsname);
			preparedStatement.setString(3, rsgroupid);
			preparedStatement.setString(4, rsloccode);
			preparedStatement.setString(5, rsresperiod);
			preparedStatement.setString(6, rsactive);
			preparedStatement.setString(7, rsrowguid);
			preparedStatement.setTimestamp(8, rslastupddt);
			preparedStatement.setString(9, rsuserid);

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

	public static boolean Posuserchkexists(String entitykey, String frdatabase)
			throws SQLException {

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

			String selectSQL = "SELECT USER_ID " + "FROM pos_user "
					+ "where USER_ID ='" + entitykey + "'";

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
