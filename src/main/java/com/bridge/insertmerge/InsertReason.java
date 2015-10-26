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

public class InsertReason {

	private static final Logger logger = Logger.getLogger(InsertReason.class);

	public static boolean Reasoninsert(String rsreasongroup, String rsreason,
			String rsreasondesc, String rsreasondisp, String rsefffrdate,
			String rsefftodate, String rsvoid, String rsrowguid,
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

				insertTableSQL = "INSERT INTO REASON "
						+ "(REASON_GROUP,REASON,REASON_DESC,REASON_DISP,EFF_FR_DATE,EFF_TO_DATE,VOID,ROWGUID,LAST_UPD_DT) "
						+ "VALUES" + "(?,?,?,?,?,?,?," + "newid()" + ",?)";

				preparedStatement = dbConnection
						.prepareStatement(insertTableSQL);

				preparedStatement.setString(1, rsreasongroup);
				preparedStatement.setString(2, rsreason);
				preparedStatement.setString(3, rsreasondesc);
				preparedStatement.setString(4, rsreasondisp);
				preparedStatement.setString(5, rsefffrdate);
				preparedStatement.setString(6, rsefftodate);
				preparedStatement.setString(7, rsvoid);
				preparedStatement.setTimestamp(8, rslastupddt);

			} else {
				// dbConnection = OracleTo.getDBConnection();

				HikariQracleTo OrcaleFromTo = HikariQracleTo.getInstance();
				dbConnection = OrcaleFromTo.getConnection();

				insertTableSQL = "INSERT INTO REASON "
						+ "(REASON_GROUP,REASON,REASON_DESC,REASON_DISP,EFF_FR_DATE,EFF_TO_DATE,VOID,ROWGUID,LAST_UPD_DT) "
						+ "VALUES " + "(?,?,?,?,?,?,?,?,?)";

				preparedStatement = dbConnection
						.prepareStatement(insertTableSQL);

				preparedStatement.setString(1, rsreasongroup);
				preparedStatement.setString(2, rsreason);
				preparedStatement.setString(3, rsreasondesc);
				preparedStatement.setString(4, rsreasondisp);
				preparedStatement.setString(5, rsefffrdate);
				preparedStatement.setString(6, rsefftodate);
				preparedStatement.setString(7, rsvoid);
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

	public static boolean Reasonupdate(String rsreasongroup, String rsreason,
			String rsreasondesc, String rsreasondisp, String rsefffrdate,
			String rsefftodate, String rsvoid, String rsrowguid,
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

			String updateSQL = "UPDATE REASON " + "SET  REASON_DESC    = ? "
					+ ", REASON_DISP    = ? " + ", EFF_FR_DATE    = ? "
					+ ", EFF_TO_DATE    = ? " + ", VOID           = ? "
					+ ", LAST_UPD_DT    = ? " + "WHERE REASON_GROUP = ? "
					+ "AND REASON         = ? ";

			preparedStatement = dbConnection.prepareStatement(updateSQL);

			preparedStatement.setString(1, rsreasondesc);
			preparedStatement.setString(2, rsreasondisp);
			preparedStatement.setString(3, rsefffrdate);
			preparedStatement.setString(4, rsefftodate);
			preparedStatement.setString(5, rsvoid);
			preparedStatement.setTimestamp(6, rslastupddt);
			preparedStatement.setString(7, rsreasongroup);
			preparedStatement.setString(8, rsreason);

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

	public static boolean Reasonchkexists(String entitykey, String frdatabase)
			throws SQLException {

		String[] parts = entitykey.split(",");
		String reasongroup = parts[0];
		String reason = parts[1];

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

			String selectSQL = "SELECT REASON_GROUP " + "FROM REASON "
					+ "where REASON_GROUP ='" + reasongroup + "'"
					+ "and REASON ='" + reason + "'";

			// logger.info(selectSQL);

			preparedStatement = dbConnection.prepareStatement(selectSQL);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				String rschktxdate = rs.getString("REASON_GROUP");

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
