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

public class InsertDpdet {

	private static final Logger logger = Logger.getLogger(InsertDpdet.class);

	public static boolean Dpdetinsert(String rstxdate, String rsloccode,
			String rsregno, String rstxno, String rsdpno, String rsaction,
			String rssettleamt, String rsissueloccode, String rsmodifiedby,
			String rsmodifieddate, String rsrowguid, Timestamp rslastupddt,
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

				insertTableSQL = "INSERT INTO Dpdet"
						+ "(TX_DATE,LOC_CODE,REG_NO,TX_NO,DP_NO,ACTION,SETTLE_AMT,ISSUE_LOC_CODE,MODIFIED_BY,MODIFIED_DATE,LAST_UPD_DT) "
						+ "VALUES" + "(?,?,?,?,?,?,?,?,?,?,?,?)";

				preparedStatement = dbConnection
						.prepareStatement(insertTableSQL);

				preparedStatement.setString(1, rstxdate);
				preparedStatement.setString(2, rsloccode);
				preparedStatement.setString(3, rsregno);
				preparedStatement.setString(4, rstxno);
				preparedStatement.setString(5, rsdpno);
				preparedStatement.setString(6, rsaction);
				preparedStatement.setString(7, rssettleamt);
				preparedStatement.setString(8, rsissueloccode);
				preparedStatement.setString(9, rsmodifiedby);
				preparedStatement.setString(10, rsmodifieddate);
				preparedStatement.setTimestamp(11, rslastupddt);

			} else {
				// dbConnection = OracleTo.getDBConnection();

				HikariQracleTo OrcaleFromTo = HikariQracleTo.getInstance();
				dbConnection = OrcaleFromTo.getConnection();

				insertTableSQL = "INSERT INTO Dpdet"
						+ "(TX_DATE,LOC_CODE,REG_NO,TX_NO,DP_NO,ACTION,SETTLE_AMT,ISSUE_LOC_CODE,MODIFIED_BY,MODIFIED_DATE,ROWGUID,LAST_UPD_DT) "
						+ "VALUES" + "(?,?,?,?,?,?,?,?,?,?,?,?)";

				preparedStatement = dbConnection
						.prepareStatement(insertTableSQL);

				preparedStatement.setString(1, rstxdate);
				preparedStatement.setString(2, rsloccode);
				preparedStatement.setString(3, rsregno);
				preparedStatement.setString(4, rstxno);
				preparedStatement.setString  (5, rsdpno);
				preparedStatement.setString(6, rsaction);
				preparedStatement.setString(7, rssettleamt);
				preparedStatement.setString(8, rsissueloccode);
				preparedStatement.setString(9, rsmodifiedby);
				preparedStatement.setString(10, rsmodifieddate);
				preparedStatement.setString(11, rsrowguid);
				preparedStatement.setTimestamp(12, rslastupddt);
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

	public static boolean Dpdetupdate(String rstxdate, String rsloccode,
			String rsregno, String rstxno, String rsdpno, String rsaction,
			String rssettleamt, String rsissueloccode, String rsmodifiedby,
			String rsmodifieddate, String rsrowguid, Timestamp rslastupddt,
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

			String updateSQL = "UPDATE DPDET " + "SET  ACTION         = ? "
					+ ", SETTLE_AMT     = ? " + ", MODIFIED_BY    = ? "
					+ ", MODIFIED_DATE  = ? " + ", LAST_UPD_DT    = ? "
					+ "WHERE TX_DATE      = ? " + "AND LOC_CODE       = ? "
					+ "AND REG_NO         = ? " + "AND TX_NO          = ? "
					+ "AND DP_NO          = ? " + "AND ISSUE_LOC_CODE = ? ";

			preparedStatement = dbConnection.prepareStatement(updateSQL);

			preparedStatement.setString(1, rsaction);
			preparedStatement.setString(2, rssettleamt);
			preparedStatement.setString(3, rsmodifiedby);
			preparedStatement.setString(4, rsmodifieddate);
			preparedStatement.setTimestamp(5, rslastupddt);
			preparedStatement.setString(6, rstxdate);
			preparedStatement.setString(7, rsloccode);
			preparedStatement.setString(8, rsregno);
			preparedStatement.setString(9, rstxno);
			preparedStatement.setString(10, rsdpno);
			preparedStatement.setString(11, rsissueloccode);

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

	public static boolean Dpdetchkexists(String entitykey, String frdatabase)
			throws SQLException {

		String[] parts = entitykey.split(",");
		String txdate = parts[0];
		String loccode = parts[1];
		String regno = parts[2];
		String txno = parts[3];
		String dpno = parts[4];
		String issueloccode = parts[5];

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

			String selectSQL = "SELECT TX_DATE " + "FROM Dpdet "
					+ "where TX_DATE ='" + txdate + "'" + "and LOC_CODE ='"
					+ loccode + "'" + "and REG_NO='" + regno + "'"
					+ "and TX_NO ='" + txno + "'" + "and DP_NO ='" + dpno + "'"
					+ "and ISSUE_LOC_CODE ='" + issueloccode + "'";

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
