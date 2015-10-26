package com.bridge.insertdeposit;

import com.bridge.main.HikariMerge;
import com.bridge.main.HikariQracleTo;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.Objects;

/**
 * Created by keyip on 11-Jun-15.
 */
public class InsertBrgift {
	private static final Logger logger = Logger.getLogger(InsertBrgift.class);

	public static boolean Brgiftinsert(String rsbridekey, String rsgiftkey,
			String rsitemdesc, String rssku, String rscoupleselect,
			String rsqty, String rsorderedqty, String rstobedelqty,
			String rsdeliveredqty, String rsprice, String rsgpayment,
			String rsrequestloc, String rsstockstatus, String rsremark,
			Timestamp rsupdateddatetime, String rsupdateduser,
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

				insertTableSQL = "INSERT INTO BR_GIFT"
						+ "(BRIDE_KEY,GIFT_KEY,ITEM_DESC,SKU,COUPLE_SELECT,QTY,ORDERED_QTY,TO_BE_DEL_QTY,DELIVERED_QTY,PRICE,"
						+ "G_PAYMENT,REQUEST_LOC,STOCK_STATUS,REMARK,UPDATED_DATETIME,UPDATED_USER,ROWGUID,LAST_UPD_DT) "
						+ "VALUES" + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
						+ "newid()" + ",?)";

				preparedStatement = dbConnection
						.prepareStatement(insertTableSQL);

				preparedStatement.setString(1, rsbridekey);
				preparedStatement.setString(2, rsgiftkey);
				preparedStatement.setString(3, rsitemdesc);
				preparedStatement.setString(4, rssku);
				preparedStatement.setString(5, rscoupleselect);
				preparedStatement.setString(6, rsqty);
				preparedStatement.setString(7, rsorderedqty);
				preparedStatement.setString(8, rstobedelqty);
				preparedStatement.setString(9, rsdeliveredqty);
				preparedStatement.setString(10, rsprice);
				preparedStatement.setString(11, rsgpayment);
				preparedStatement.setString(12, rsrequestloc);
				preparedStatement.setString(13, rsstockstatus);
				preparedStatement.setString(14, rsremark);
				preparedStatement.setTimestamp(15, rsupdateddatetime);
				preparedStatement.setString(16, rsupdateduser);
				preparedStatement.setTimestamp(17, rslastupddt);

			} else {
				// dbConnection = OracleTo.getDBConnection();

				HikariQracleTo OrcaleFromTo = HikariQracleTo.getInstance();
				dbConnection = OrcaleFromTo.getConnection();

				insertTableSQL = "INSERT INTO BR_GIFT"
						+ "(BRIDE_KEY,GIFT_KEY,ITEM_DESC,SKU,COUPLE_SELECT,QTY,ORDERED_QTY,TO_BE_DEL_QTY,DELIVERED_QTY,PRICE,"
						+ "G_PAYMENT,REQUEST_LOC,STOCK_STATUS,REMARK,UPDATED_DATETIME,UPDATED_USER,ROWGUID,LAST_UPD_DT) "
						+ "VALUES" + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

				preparedStatement = dbConnection
						.prepareStatement(insertTableSQL);

				preparedStatement.setString(1, rsbridekey);
				preparedStatement.setString(2, rsgiftkey);
				preparedStatement.setString(3, rsitemdesc);
				preparedStatement.setString(4, rssku);
				preparedStatement.setString(5, rscoupleselect);
				preparedStatement.setString(6, rsqty);
				preparedStatement.setString(7, rsorderedqty);
				preparedStatement.setString(8, rstobedelqty);
				preparedStatement.setString(9, rsdeliveredqty);
				preparedStatement.setString(10, rsprice);
				preparedStatement.setString(11, rsgpayment);
				preparedStatement.setString(12, rsrequestloc);
				preparedStatement.setString(13, rsstockstatus);
				preparedStatement.setString(14, rsremark);
				preparedStatement.setTimestamp(15, rsupdateddatetime);
				preparedStatement.setString(16, rsupdateduser);
				preparedStatement.setString(17, rsrowguid);
				preparedStatement.setTimestamp(18, rslastupddt);
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

	public static boolean Brgiftupdate(String rsbridekey, String rsgiftkey,
			String rsitemdesc, String rssku, String rscoupleselect,
			String rsqty, String rsorderedqty, String rstobedelqty,
			String rsdeliveredqty, String rsprice, String rsgpayment,
			String rsrequestloc, String rsstockstatus, String rsremark,
			Timestamp rsupdateddatetime, String rsupdateduser,
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

			String updateSQL = "UPDATE BR_GIFT " + "SET  ITEM_DESC        = ? "
					+ ", SKU              = ? " + ", COUPLE_SELECT    = ? "
					+ ", QTY              = ? " + ", ORDERED_QTY      = ? "
					+ ", TO_BE_DEL_QTY    = ? " + ", DELIVERED_QTY    = ? "
					+ ", PRICE            = ? " + ", G_PAYMENT        = ? "
					+ ", STOCK_STATUS     = ? " + ", REMARK           = ? "
					+ ", UPDATED_DATETIME = ? " + ", UPDATED_USER     = ? "
					+ ", LAST_UPD_DT      = ? " + "WHERE BRIDE_KEY      = ? "
					+ "AND GIFT_KEY         = ? " + "AND REQUEST_LOC      = ? ";

			preparedStatement = dbConnection.prepareStatement(updateSQL);

			preparedStatement.setString(1, rsitemdesc);
			preparedStatement.setString(2, rssku);
			preparedStatement.setString(3, rscoupleselect);
			preparedStatement.setString(4, rsqty);
			preparedStatement.setString(5, rsorderedqty);
			preparedStatement.setString(6, rstobedelqty);
			preparedStatement.setString(7, rsdeliveredqty);
			preparedStatement.setString(8, rsprice);
			preparedStatement.setString(9, rsgpayment);
			preparedStatement.setString(10, rsstockstatus);
			preparedStatement.setString(11, rsremark);
			preparedStatement.setTimestamp(12, rsupdateddatetime);
			preparedStatement.setString(13, rsupdateduser);
			preparedStatement.setTimestamp(14, rslastupddt);
			preparedStatement.setString(15, rsbridekey);
			preparedStatement.setString(16, rsgiftkey);
			preparedStatement.setString(17, rsrequestloc);

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

	public static boolean Brgiftchkexists(String entitykey, String frdatabase)
			throws SQLException {

		String[] parts = entitykey.split(",");
		String bridekey = parts[0];
		String giftkey = parts[1];
		String requesyloc = parts[2];

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

			String selectSQL = "SELECT ITEM_DESC " + "FROM BR_GIFT "
					+ "where BRIDE_KEY ='" + bridekey + "'" + "and GIFT_KEY ='"
					+ giftkey + "'" + "and REQUEST_LOC='" + requesyloc + "'";

			// logger.info(selectSQL);

			preparedStatement = dbConnection.prepareStatement(selectSQL);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				String rschktxdate = rs.getString("ITEM_DESC");

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
