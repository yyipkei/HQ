package com.bridge.insertgoodsreturn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Objects;

import org.apache.log4j.Logger;

import com.bridge.main.HikariMerge;
import com.bridge.main.HikariQracleTo;

public class InsertGrorgtender {

	private static final Logger logger = Logger
			.getLogger(InsertGrorgtender.class);

	public static boolean Grorgtenderinsert(String rstxdate, String rsloccode,
			String rsregno, String rstxno, String rsseqno, String rsvoid,
			String rstender, String rsamount, String rsrefno, String rsrowguid,
			String rsholdername, String rscardexpiry, Timestamp rslastupddt,
			String frdatabase) throws SQLException {

		boolean result = true;
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		String insertTableSQL;

		try {

			if (Objects.equals(frdatabase, "Oracle")) {
				// dbConnection = Merge.getDBConnection();

				HikariMerge Mergepool = HikariMerge.getInstance();
				dbConnection = Mergepool.getConnection();
				insertTableSQL = "INSERT INTO GR_ORG_TENDER"
						+ "(TX_DATE,LOC_CODE,REG_NO,TX_NO,SEQ_NO,VOID,TENDER,AMOUNT,REF_NO,ROWGUID,HOLDER_NAME,CARD_EXPIRY,LAST_UPD_DT) "
						+ "VALUES" + "(?,?,?,?,?,?,?,?,?," + "newid()"
						+ ",?,?,?)";

				preparedStatement = dbConnection
						.prepareStatement(insertTableSQL);

				preparedStatement.setString(1, rstxdate);
				preparedStatement.setString(2, rsloccode);
				preparedStatement.setString(3, rsregno);
				preparedStatement.setString(4, rstxno);
				preparedStatement.setString(5, rsseqno);
				preparedStatement.setString(6, rsvoid);
				preparedStatement.setString(7, rstender);
				preparedStatement.setString(8, rsamount);
				preparedStatement.setString(9, rsrefno);
				preparedStatement.setString(10, rsholdername);
				preparedStatement.setString(11, rscardexpiry);
				preparedStatement.setTimestamp(12, rslastupddt);

			} else {
				// dbConnection = OracleTo.getDBConnection();

				HikariQracleTo OrcaleFromTo = HikariQracleTo.getInstance();
				dbConnection = OrcaleFromTo.getConnection();
				insertTableSQL = "INSERT INTO GR_ORG_TENDER"
						+ "(TX_DATE,LOC_CODE,REG_NO,TX_NO,SEQ_NO,VOID,TENDER,AMOUNT,REF_NO,ROWGUID,HOLDER_NAME,CARD_EXPIRY,LAST_UPD_DT) "
						+ "VALUES" + "(?,?,?,?,?,?,?,?,?,?,?,?,?)";

				preparedStatement = dbConnection
						.prepareStatement(insertTableSQL);

				preparedStatement.setString(1, rstxdate);
				preparedStatement.setString(2, rsloccode);
				preparedStatement.setString(3, rsregno);
				preparedStatement.setString(4, rstxno);
				preparedStatement.setString(5, rsseqno);
				preparedStatement.setString(6, rsvoid);
				preparedStatement.setString(7, rstender);
				preparedStatement.setString(8, rsamount);
				preparedStatement.setString(9, rsrefno);
				preparedStatement.setString(10, rsrowguid);
				preparedStatement.setString(11, rsholdername);
				preparedStatement.setString(12, rscardexpiry);
				preparedStatement.setTimestamp(13, rslastupddt);

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

	public static boolean Grorgtenderupdate(String rstxdate, String rsloccode,
			String rsregno, String rstxno, String rsseqno, String rsvoid,
			String rstender, String rsamount, String rsrefno, String rsrowguid,
			String rsholdername, String rscardexpiry, Timestamp rslastupddt,
			String frdatabase) throws SQLException {

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

			String updateSQL = "UPDATE GR_ORG_TENDER " + "SET VOID        = ? "
					+ ", TENDER      = ? " + ", AMOUNT      = ? "
					+ ", REF_NO      = ? " + ", HOLDER_NAME = ? "
					+ ", CARD_EXPIRY = ? " + ", LAST_UPD_DT = ? "
					+ "WHERE TX_DATE   = ? " + "AND LOC_CODE    = ? "
					+ "AND REG_NO      = ? " + "AND TX_NO       = ? "
					+ "AND SEQ_NO      = ? ";

			preparedStatement = dbConnection.prepareStatement(updateSQL);

			preparedStatement.setString(1, rsvoid);
			preparedStatement.setString(2, rstender);
			preparedStatement.setString(3, rsamount);
			preparedStatement.setString(4, rsrefno);
			preparedStatement.setString(5, rsholdername);
			preparedStatement.setString(6, rscardexpiry);
			preparedStatement.setTimestamp(7, rslastupddt);
			preparedStatement.setString(8, rstxdate);
			preparedStatement.setString(9, rsloccode);
			preparedStatement.setString(10, rsregno);
			preparedStatement.setString(11, rstxno);
			preparedStatement.setString(12, rsseqno);

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

	public static boolean Grorgtenderchkexists(String entitykey,
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

			String selectSQL = "SELECT TX_DATE " + "FROM GR_ORG_TENDER "
					+ "where tx_date ='" + txdate + "'" + "and LOC_CODE ='"
					+ loccode + "'" + "and reg_no='" + regno + "'"
					+ "and tx_no ='" + txno + "'" + "and seq_no ='" + seqno
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
