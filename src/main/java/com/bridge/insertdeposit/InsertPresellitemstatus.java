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

public class InsertPresellitemstatus {

	private static final Logger logger = Logger
			.getLogger(InsertPresellitemstatus.class);

	public static boolean Presellitemstatusinsert(String rstxdate,
			String rsloccode, String rsregno, String rstxno, String rsseqno,
			String rseventid, String rssku, String rsorderqty, String rsdpno,
			String rsstatus, String rspickuploc, String rslastupdusr,
			Timestamp rslastupddt, String rslastupdver, String rsallocateqty,
			String rspono, String rscururet, String rsrowguid, String frdatabase)
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

				insertTableSQL = "INSERT INTO PRESELL_ITEM_STATUS"
						+ "(TX_DATE,LOC_CODE,REG_NO,TX_NO,SEQ_NO,EVENT_ID,SKU,ORDER_QTY,DP_NO,STATUS,PICK_UP_LOC,LAST_UPD_USR,LAST_UPD_DT,LAST_UPD_VER,ALLOCATE_QTY,PO_NO,CUR_URET,ROWGUID) "
						+ "VALUES" + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
						+ "newid()" + ")";

				preparedStatement = dbConnection
						.prepareStatement(insertTableSQL);

				preparedStatement.setString(1, rstxdate);
				preparedStatement.setString(2, rsloccode);
				preparedStatement.setString(3, rsregno);
				preparedStatement.setString(4, rstxno);
				preparedStatement.setString(5, rsseqno);
				preparedStatement.setString(6, rseventid);
				preparedStatement.setString(7, rssku);
				preparedStatement.setString(8, rsorderqty);
				preparedStatement.setString(9, rsdpno);
				preparedStatement.setString(10, rsstatus);
				preparedStatement.setString(11, rspickuploc);
				preparedStatement.setString(12, rslastupdusr);
				preparedStatement.setTimestamp(13, rslastupddt);
				preparedStatement.setString(14, rslastupdver);
				preparedStatement.setString(15, rsallocateqty);
				preparedStatement.setString(16, rspono);
				preparedStatement.setString(17, rscururet);

			} else {
				// dbConnection = OracleTo.getDBConnection();

				HikariQracleTo OrcaleFromTo = HikariQracleTo.getInstance();
				dbConnection = OrcaleFromTo.getConnection();

				insertTableSQL = "INSERT INTO PRESELL_ITEM_STATUS"
						+ "(TX_DATE,LOC_CODE,REG_NO,TX_NO,SEQ_NO,EVENT_ID,SKU,ORDER_QTY,DP_NO,STATUS,PICK_UP_LOC,LAST_UPD_USR,LAST_UPD_DT,LAST_UPD_VER,ALLOCATE_QTY,PO_NO,CUR_URET,ROWGUID) "
						+ "VALUES" + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

				preparedStatement = dbConnection
						.prepareStatement(insertTableSQL);

				preparedStatement.setString(1, rstxdate);
				preparedStatement.setString(2, rsloccode);
				preparedStatement.setString(3, rsregno);
				preparedStatement.setString(4, rstxno);
				preparedStatement.setString(5, rsseqno);
				preparedStatement.setString(6, rseventid);
				preparedStatement.setString(7, rssku);
				preparedStatement.setString(8, rsorderqty);
				preparedStatement.setString(9, rsdpno);
				preparedStatement.setString(10, rsstatus);
				preparedStatement.setString(11, rspickuploc);
				preparedStatement.setString(12, rslastupdusr);
				preparedStatement.setTimestamp(13, rslastupddt);
				preparedStatement.setString(14, rslastupdver);
				preparedStatement.setString(15, rsallocateqty);
				preparedStatement.setString(16, rspono);
				preparedStatement.setString(17, rscururet);
				preparedStatement.setString(18, rsrowguid);
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

	public static boolean Presellitemstatusupdate(String rstxdate,
			String rsloccode, String rsregno, String rstxno, String rsseqno,
			String rseventid, String rssku, String rsorderqty, String rsdpno,
			String rsstatus, String rspickuploc, String rslastupdusr,
			Timestamp rslastupddt, String rslastupdver, String rsallocateqty,
			String rspono, String rscururet, String rsrowguid, String frdatabase)
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

			String updateSQL = "UPDATE PRESELL_ITEM_STATUS "
					+ "SET  EVENT_ID     = ? " + ", SKU          = ? "
					+ ", ORDER_QTY    = ? " + ", DP_NO        = ? "
					+ ", STATUS       = ? " + ", PICK_UP_LOC  = ? "
					+ ", LAST_UPD_USR = ? " + ", LAST_UPD_DT  = ? "
					+ ", LAST_UPD_VER = ? " + ", ALLOCATE_QTY = ? "
					+ ", PO_NO        = ? " + ", CUR_URET     = ? "
					+ ", ROWGUID      = ? " + "WHERE TX_DATE    = ? "
					+ "AND LOC_CODE     = ? " + "AND REG_NO       = ? "
					+ "AND TX_NO        = ? " + "AND SEQ_NO       = ? ";

			preparedStatement = dbConnection.prepareStatement(updateSQL);

			preparedStatement.setString(1, rseventid);
			preparedStatement.setString(2, rssku);
			preparedStatement.setString(3, rsorderqty);
			preparedStatement.setString(4, rsdpno);
			preparedStatement.setString(5, rsstatus);
			preparedStatement.setString(6, rspickuploc);
			preparedStatement.setString(7, rslastupdusr);
			preparedStatement.setTimestamp(8, rslastupddt);
			preparedStatement.setString(9, rslastupdver);
			preparedStatement.setString(10, rsallocateqty);
			preparedStatement.setString(11, rspono);
			preparedStatement.setString(12, rscururet);
			preparedStatement.setString(13, rsrowguid);
			preparedStatement.setString(14, rstxdate);
			preparedStatement.setString(15, rsloccode);
			preparedStatement.setString(16, rsregno);
			preparedStatement.setString(17, rstxno);
			preparedStatement.setString(18, rsseqno);

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

	public static boolean Presellitemstatuschkexists(String entitykey,
			String frdatabase) throws SQLException {

		String[] parts = entitykey.split(",");
		String txdate = parts[0];
		String loccode = parts[1];
		String regno = parts[2];
		String txno = parts[3];
		String seqno = parts[4];

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

			String selectSQL = "SELECT TX_DATE " + "FROM presell_item_status "
					+ "where TX_DATE ='" + txdate + "'" + "and LOC_CODE ='"
					+ loccode + "'" + "and REG_NO='" + regno + "'"
					+ "and TX_NO ='" + txno + "'" + "and SEQ_NO ='" + seqno
					+ "'";

			// logger.info(selectSQL);

			preparedStatement = dbConnection.prepareStatement(selectSQL);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				String rschktxdate = rs.getString("TX_DATE");

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
