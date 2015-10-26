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

public class InsertDppayment {

	private static final Logger logger = Logger
			.getLogger(InsertDppayment.class);

	public static boolean Dppaymentinsert(String rstxdate, String rsloccode,
			String rsregno, String rstxno, String rsdpno, String rspaymentno,
			String rstender, String rscardno, String rsholdername,
			String rscardexpiry, String rspaymentamount, String rspaymentdate,
			String rsrefno, Timestamp rslastupddt, String frdatabase)
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

			} else {
				// dbConnection = OracleTo.getDBConnection();

				HikariQracleTo OrcaleFromTo = HikariQracleTo.getInstance();
				dbConnection = OrcaleFromTo.getConnection();

			}

			insertTableSQL = "INSERT INTO Dppayment"
					+ "(TX_DATE,LOC_CODE,REG_NO,TX_NO,DP_NO,PAYMENT_NO,TENDER,CARD_NO,HOLDER_NAME,CARD_EXPIRY,PAYMENT_AMOUNT,PAYMENT_DATE,REF_NO,LAST_UPD_DT) "
					+ "VALUES" + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

			preparedStatement = dbConnection.prepareStatement(insertTableSQL);

			preparedStatement.setString(1, rstxdate);
			preparedStatement.setString(2, rsloccode);
			preparedStatement.setString(3, rsregno);
			preparedStatement.setString(4, rstxno);
			preparedStatement.setString(5, rsdpno);
			preparedStatement.setString(6, rspaymentno);
			preparedStatement.setString(7, rstender);
			preparedStatement.setString(8, rscardno);
			preparedStatement.setString(9, rsholdername);
			preparedStatement.setString(10, rscardexpiry);
			preparedStatement.setString(11, rspaymentamount);
			preparedStatement.setString(12, rspaymentdate);
			preparedStatement.setString(13, rsrefno);
			preparedStatement.setTimestamp(14, rslastupddt);

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

	public static boolean Dppaymentupdate(String rstxdate, String rsloccode,
			String rsregno, String rstxno, String rsdpno, String rspaymentno,
			String rstender, String rscardno, String rsholdername,
			String rscardexpiry, String rspaymentamount, String rspaymentdate,
			String rsrefno, Timestamp rslastupddt, String frdatabase)
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

			String updateSQL = "UPDATE DPPAYMENT " + "SET TENDER         = ? "
					+ ", CARD_NO        = ? " + ", HOLDER_NAME    = ? "
					+ ", CARD_EXPIRY    = ? " + ", PAYMENT_AMOUNT = ? "
					+ ", PAYMENT_DATE   = ? " + ", REF_NO         = ? "
					+ ", LAST_UPD_DT    = ? " 
					+ "WHERE TX_DATE      = ? " + "AND LOC_CODE       = ? "
					+ "AND REG_NO         = ? " + "AND TX_NO          = ? "
					+ "AND DP_NO          = ? " + "AND PAYMENT_NO     = ? ";

			preparedStatement = dbConnection.prepareStatement(updateSQL);

			preparedStatement.setString(1, rstender);
			preparedStatement.setString(2, rscardno);
			preparedStatement.setString(3, rsholdername);
			preparedStatement.setString(4, rscardexpiry);
			preparedStatement.setString(5, rspaymentamount);
			preparedStatement.setString(6, rspaymentdate);
			preparedStatement.setString(7, rsrefno);
			preparedStatement.setTimestamp(8, rslastupddt);
			preparedStatement.setString(9, rstxdate);
			preparedStatement.setString(10, rsloccode);
			preparedStatement.setString(11, rsregno);
			preparedStatement.setString(12, rstxno);
			preparedStatement.setString(13, rsdpno);
			preparedStatement.setString(14, rspaymentno);

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

	public static boolean Dppaymentchkexists(String entitykey, String frdatabase)
			throws SQLException {

		String[] parts = entitykey.split(",");
		String txdate = parts[0];
		String loccode = parts[1];
		String regno = parts[2];
		String txno = parts[3];
		String dpno = parts[4];
		String paymentno = parts[5];

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

			String selectSQL = "SELECT TX_DATE " + "FROM Dppayment "
					+ "where TX_DATE ='" + txdate + "'" + "and LOC_CODE ='"
					+ loccode + "'" + "and REG_NO='" + regno + "'"
					+ "and TX_NO ='" + txno + "'" + "and DP_NO ='" + dpno + "'"
					+ "and PAYMENT_NO ='" + paymentno + "'";

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
