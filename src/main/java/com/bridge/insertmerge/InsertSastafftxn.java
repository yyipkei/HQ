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

public class InsertSastafftxn {

	private static final Logger logger = Logger
			.getLogger(InsertSastafftxn.class);

	public static boolean Sastafftxninsert(String rsrowguid, String rstxdate,
			String rsloccode, String rsregno, String rstxno, String rsseqno,
			String rstxtype, String rsvoid, String rstender, String rsamount,
			String rsforeignamt, String rsstaffid, String rsstaffloccode,
			String rsqty, Timestamp rslastupddt, String frdatabase)
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

				insertTableSQL = "INSERT INTO Sastafftxn"
						+ "(ROWGUID,TX_DATE,LOC_CODE,REG_NO,TX_NO,SEQ_NO,TX_TYPE,VOID,TENDER,AMOUNT,FOREIGN_AMT,STAFF_ID,STAFF_LOC_CODE,QTY,LAST_UPD_DT) "
						+ "VALUES" + "(" + "newid()"
						+ ",?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

				preparedStatement = dbConnection
						.prepareStatement(insertTableSQL);

				preparedStatement.setString(1, rstxdate);
				preparedStatement.setString(2, rsloccode);
				preparedStatement.setString(3, rsregno);
				preparedStatement.setString(4, rstxno);
				preparedStatement.setString(5, rsseqno);
				preparedStatement.setString(6, rstxtype);
				preparedStatement.setString(7, rsvoid);
				preparedStatement.setString(8, rstender);
				preparedStatement.setString(9, rsamount);
				preparedStatement.setString(10, rsforeignamt);
				preparedStatement.setString(11, rsstaffid);
				preparedStatement.setString(12, rsstaffloccode);
				preparedStatement.setString(13, rsqty);
				preparedStatement.setTimestamp(14, rslastupddt);

			} else {
				// dbConnection = OracleTo.getDBConnection();

				HikariQracleTo OrcaleFromTo = HikariQracleTo.getInstance();
				dbConnection = OrcaleFromTo.getConnection();

				insertTableSQL = "INSERT INTO Sastafftxn"
						+ "(ROWGUID,TX_DATE,LOC_CODE,REG_NO,TX_NO,SEQ_NO,TX_TYPE,VOID,TENDER,AMOUNT,FOREIGN_AMT,STAFF_ID,STAFF_LOC_CODE,QTY,LAST_UPD_DT) "
						+ "VALUES" + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

				preparedStatement = dbConnection
						.prepareStatement(insertTableSQL);

				preparedStatement.setString(1, rsrowguid);
				preparedStatement.setString(2, rstxdate);
				preparedStatement.setString(3, rsloccode);
				preparedStatement.setString(4, rsregno);
				preparedStatement.setString(5, rstxno);
				preparedStatement.setString(6, rsseqno);
				preparedStatement.setString(7, rstxtype);
				preparedStatement.setString(8, rsvoid);
				preparedStatement.setString(9, rstender);
				preparedStatement.setString(10, rsamount);
				preparedStatement.setString(11, rsforeignamt);
				preparedStatement.setString(12, rsstaffid);
				preparedStatement.setString(13, rsstaffloccode);
				preparedStatement.setString(14, rsqty);
				preparedStatement.setTimestamp(15, rslastupddt);

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

	public static boolean Sastafftxnupdate(String rsrowguid, String rstxdate,
			String rsloccode, String rsregno, String rstxno, String rsseqno,
			String rstxtype, String rsvoid, String rstender, String rsamount,
			String rsforeignamt, String rsstaffid, String rsstaffloccode,
			String rsqty, Timestamp rslastupddt, String frdatabase)
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

			String updateSQL = "UPDATE SASTAFFTXN "
					+ "SET  TX_TYPE        = ? " + ", VOID           = ? "
					+ ", TENDER         = ? " + ", AMOUNT         = ? "
					+ ", FOREIGN_AMT    = ? " + ", STAFF_ID       = ? "
					+ ", STAFF_LOC_CODE = ? " + ", QTY            = ? "
					+ ", LAST_UPD_DT    = ? " + "WHERE  TX_DATE        = ? "
					+ "AND LOC_CODE       = ? " + "AND REG_NO         = ? "
					+ "AND TX_NO          = ? " + "AND SEQ_NO         = ? ";

			preparedStatement = dbConnection.prepareStatement(updateSQL);

			preparedStatement.setString(1, rstxtype);
			preparedStatement.setString(2, rsvoid);
			preparedStatement.setString(3, rstender);
			preparedStatement.setString(4, rsamount);
			preparedStatement.setString(5, rsforeignamt);
			preparedStatement.setString(6, rsstaffid);
			preparedStatement.setString(7, rsstaffloccode);
			preparedStatement.setString(8, rsqty);
			preparedStatement.setTimestamp(9, rslastupddt);
			preparedStatement.setString(10, rstxdate);
			preparedStatement.setString(11, rsloccode);
			preparedStatement.setString(12, rsregno);
			preparedStatement.setString(13, rstxno);
			preparedStatement.setString(14, rsseqno);

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

	public static boolean Sastafftxnchkexists(String entitykey,
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

			String selectSQL = "SELECT TX_DATE " + "FROM SASTAFFTXN "
					+ "where tx_date ='" + txdate + "'" + "and LOC_CODE ='"
					+ loccode + "'" + "and reg_no='" + regno + "'"
					+ "and tx_no ='" + txno + "'" + "and seq_no ='" + seqno
					+ "'";

			// logger.info(selectSQL);

			preparedStatement = dbConnection.prepareStatement(selectSQL);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				String rschktxdate = rs.getString("TX_DATE");

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
