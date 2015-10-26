package com.bridge.insertdeposit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Objects;

import org.apache.log4j.Logger;

import com.bridge.main.HikariMerge;
import com.bridge.main.HikariQracleTo;

public class InsertPresellitem {

	private static final Logger logger = Logger
			.getLogger(InsertPresellitem.class);

	public static boolean Preselliteminsert(String rssku, String rseventid,
			String rslastupdusr, Timestamp rslastupddt, String rslastupdver,
			String rspoqty, String rsorderqty, String rspickqty, String rspono,
			String rsrowguid, String frdatabase) throws SQLException {

		boolean result = true;
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		String insertTableSQL;

		try {

			if (Objects.equals(frdatabase, "Oracle")) {
				// dbConnection = Merge.getDBConnection();

				HikariMerge Mergepool = HikariMerge.getInstance();
				dbConnection = Mergepool.getConnection();

				insertTableSQL = "INSERT INTO presell_item"
						+ "(SKU,EVENT_ID,LAST_UPD_USR,LAST_UPD_DT,LAST_UPD_VER,PO_QTY,ORDER_QTY,PICK_QTY,PO_NO) "
						+ "VALUES" + "(?,?,?,?,?,?,?,?,?)";

				preparedStatement = dbConnection
						.prepareStatement(insertTableSQL);

				preparedStatement.setString(1, rssku);
				preparedStatement.setString(2, rseventid);
				preparedStatement.setString(3, rslastupdusr);
				preparedStatement.setTimestamp(4, rslastupddt);
				preparedStatement.setString(5, rslastupdver);
				preparedStatement.setString(6, rspoqty);
				preparedStatement.setString(7, rsorderqty);
				preparedStatement.setString(8, rspickqty);
				preparedStatement.setString(9, rspono);

			} else {
				// dbConnection = OracleTo.getDBConnection();

				HikariQracleTo OrcaleFromTo = HikariQracleTo.getInstance();
				dbConnection = OrcaleFromTo.getConnection();

				insertTableSQL = "INSERT INTO presell_item"
						+ "(SKU,EVENT_ID,LAST_UPD_USR,LAST_UPD_DT,LAST_UPD_VER,PO_QTY,ORDER_QTY,PICK_QTY,PO_NO,ROWGUID) "
						+ "VALUES" + "(?,?,?,?,?,?,?,?,?,?)";

				preparedStatement = dbConnection
						.prepareStatement(insertTableSQL);

				preparedStatement.setString(1, rssku);
				preparedStatement.setString(2, rseventid);
				preparedStatement.setString(3, rslastupdusr);
				preparedStatement.setTimestamp(4, rslastupddt);
				preparedStatement.setString(5, rslastupdver);
				preparedStatement.setString(6, rspoqty);
				preparedStatement.setString(7, rsorderqty);
				preparedStatement.setString(8, rspickqty);
				preparedStatement.setString(9, rspono);
				preparedStatement.setString(10, rsrowguid);

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

	public static boolean Presellitemupdate(String rssku, String rseventid,
			String rslastupdusr, Timestamp rslastupddt, String rslastupdver,
			String rspoqty, String rsorderqty, String rspickqty, String rspono,
			String rsrowguid, String frdatabase) throws SQLException {

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

			String updateSQL = "UPDATE PRESELL_ITEM "
					+ "SET  LAST_UPD_USR = ? " + ", LAST_UPD_DT  = ? "
					+ ", LAST_UPD_VER = ? " + ", PO_QTY       = ? "
					+ ", ORDER_QTY    = ? " + ", PICK_QTY     = ? "
					+ ", ROWGUID      = ? " + "WHERE SKU        = ? "
					+ "AND EVENT_ID     = ? " + "AND PO_NO        = ? ";

			preparedStatement = dbConnection.prepareStatement(updateSQL);

			preparedStatement.setString(1, rslastupdusr);
			preparedStatement.setTimestamp(2, rslastupddt);
			preparedStatement.setString(3, rslastupdver);
			preparedStatement.setString(4, rspoqty);
			preparedStatement.setString(5, rsorderqty);
			preparedStatement.setString(6, rspickqty);
			preparedStatement.setString(7, rsrowguid);
			preparedStatement.setString(8, rssku);
			preparedStatement.setString(9, rseventid);
			preparedStatement.setString(10, rspono);

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

	public static boolean Presellitemchkexists(String entitykey,
			String frdatabase) throws SQLException {

		String[] parts = entitykey.split(",");
		String sku = parts[0];
		String eventid = parts[1];
		String pono = parts[2];

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

			String selectSQL = "SELECT SKU " + "FROM PRESELL_ITEM "
					+ "where SKU ='" + sku + "'" + "and EVENT_ID ='" + eventid
					+ "'" + "and PO_NO='" + pono + "'";

			// logger.info(selectSQL);

			preparedStatement = dbConnection.prepareStatement(selectSQL);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				String rschktxdate = rs.getString("SKU");

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
