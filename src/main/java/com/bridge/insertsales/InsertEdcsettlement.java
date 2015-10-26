package com.bridge.insertsales;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Objects;

import org.apache.log4j.Logger;

import com.bridge.main.HikariMssql;
import com.bridge.main.HikariQracleTo;

public class InsertEdcsettlement {

	private static final Logger logger = Logger
			.getLogger(InsertEdcsettlement.class);

	public static boolean Edcsettlementinsert(Timestamp rstxdate,
			String rsloccode, String rsregno, String rstxno, String rstxamount,
			Timestamp rssettledate, String rsbatchno, String rsbankhost,
			String rsmerchno, String rssettleamount, String rsstatus,
			Timestamp rslastupddt, String rslastupdver, String rslastupdusr,
			String rsposedc, String rslongshort, String rslongshortamt,
			String rsvalueday, String rsautomanualflag, String frdatabase)
			throws SQLException {

		boolean result = true;
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		try {

			if (Objects.equals(frdatabase, "Oracle")) {
				// dbConnection = Mssql.getDBConnection();

				HikariMssql Mssqlpool = HikariMssql.getInstance();
				dbConnection = Mssqlpool.getConnection();
			} else {
				// dbConnection = OracleTo.getDBConnection();

				HikariQracleTo OrcaleFromTo = HikariQracleTo.getInstance();
				dbConnection = OrcaleFromTo.getConnection();
			}

			String insertTableSQL = "INSERT INTO EDC_SETTLEMENT "
					+ "(TX_DATE,LOC_CODE,REG_NO,TX_NO,TX_AMOUNT,SETTLE_DATE,BATCH_NO,BANK_HOST,"
					+ "MERCH_NO,SETTLE_AMOUNT,STATUS,LAST_UPD_DT,LAST_UPD_VER,LAST_UPD_USR,POS_EDC,LONG_SHORT,"
					+ "LONG_SHORT_AMT,VALUE_DAY,AUTO_MANUAL_FLAG) " + "VALUES"
					+ "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

			preparedStatement = dbConnection.prepareStatement(insertTableSQL);

			preparedStatement.setTimestamp(1, rstxdate);
			preparedStatement.setString(2, rsloccode);
			preparedStatement.setString(3, rsregno);
			preparedStatement.setString(4, rstxno);
			preparedStatement.setString(5, rstxamount);
			preparedStatement.setTimestamp(6, rssettledate);
			preparedStatement.setString(7, rsbatchno);
			preparedStatement.setString(8, rsbankhost);
			preparedStatement.setString(9, rsmerchno);
			preparedStatement.setString(10, rssettleamount);
			preparedStatement.setString(11, rsstatus);
			preparedStatement.setTimestamp(12, rslastupddt);
			preparedStatement.setString(13, rslastupdver);
			preparedStatement.setString(14, rslastupdusr);
			preparedStatement.setString(15, rsposedc);
			preparedStatement.setString(16, rslongshort);
			preparedStatement.setString(17, rslongshortamt);
			preparedStatement.setString(18, rsvalueday);
			preparedStatement.setString(19, rsautomanualflag);

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

	public static boolean Edcsettlementupdate(Timestamp rstxdate,
			String rsloccode, String rsregno, String rstxno, String rstxamount,
			Timestamp rssettledate, String rsbatchno, String rsbankhost,
			String rsmerchno, String rssettleamount, String rsstatus,
			Timestamp rslastupddt, String rslastupdver, String rslastupdusr,
			String rsposedc, String rslongshort, String rslongshortamt,
			String rsvalueday, String rsautomanualflag, String frdatabase)
			throws SQLException {

		boolean result = true;
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		try {

			if (Objects.equals(frdatabase, "Oracle")) {
				// dbConnection = Mssql.getDBConnection();

				HikariMssql Mssqlpool = HikariMssql.getInstance();
				dbConnection = Mssqlpool.getConnection();
			} else {
				// dbConnection = OracleTo.getDBConnection();

				HikariQracleTo OrcaleFromTo = HikariQracleTo.getInstance();
				dbConnection = OrcaleFromTo.getConnection();
			}

			String updateSQL = "UPDATE EDC_SETTLEMENT "
					+ "SET TX_AMOUNT        = ? " + ", SETTLE_DATE      = ? "
					+ ", BATCH_NO         = ? " + ", BANK_HOST        = ? "
					+ ", MERCH_NO         = ? " + ", SETTLE_AMOUNT    = ? "
					+ ", STATUS           = ? " + ", LAST_UPD_DT      = ? "
					+ ", LAST_UPD_VER     = ? " + ", LAST_UPD_USR     = ? "
					+ ", POS_EDC          = ? " + ", LONG_SHORT       = ? "
					+ ", LONG_SHORT_AMT   = ? " + ", VALUE_DAY        = ? "
					+ ", AUTO_MANUAL_FLAG = ? " + "WHERE TX_DATE        = ? "
					+ "AND LOC_CODE         = ? " + "AND REG_NO           = ? "
					+ "AND TX_NO            = ? ";

			preparedStatement = dbConnection.prepareStatement(updateSQL);

			preparedStatement.setString(1, rstxamount);
			preparedStatement.setTimestamp(2, rssettledate);
			preparedStatement.setString(3, rsbatchno);
			preparedStatement.setString(4, rsbankhost);
			preparedStatement.setString(5, rsmerchno);
			preparedStatement.setString(6, rssettleamount);
			preparedStatement.setString(7, rsstatus);
			preparedStatement.setTimestamp(8, rslastupddt);
			preparedStatement.setString(9, rslastupdver);
			preparedStatement.setString(10, rslastupdusr);
			preparedStatement.setString(11, rsposedc);
			preparedStatement.setString(12, rslongshort);
			preparedStatement.setString(13, rslongshortamt);
			preparedStatement.setString(14, rsvalueday);
			preparedStatement.setString(15, rsautomanualflag);
			preparedStatement.setTimestamp(16, rstxdate);
			preparedStatement.setString(17, rsloccode);
			preparedStatement.setString(18, rsregno);
			preparedStatement.setString(19, rstxno);

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

	public static boolean Edcsettlementchkexists(String entitykey,
			String frdatabase) throws SQLException {

		String[] parts = entitykey.split(",");
		String txdate = parts[0];
		String loccode = parts[1];
		String regno = parts[2];
		String txno = parts[3];

		boolean result = false;
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		String selectSQL;

		try {

			if (Objects.equals(frdatabase, "Oracle")) {
				// dbConnection = Mssql.getDBConnection();

				HikariMssql Mssqlpool = HikariMssql.getInstance();
				dbConnection = Mssqlpool.getConnection();
				selectSQL = "SELECT LOC_CODE " + "FROM EDC_SETTLEMENT "
						+ "where tx_date ='" + txdate + "'" + "and LOC_CODE ='"
						+ loccode + "'" + "and reg_no='" + regno + "'"
						+ "and tx_no ='" + txno + "'";

			} else {
				// dbConnection = OracleTo.getDBConnection();

				HikariQracleTo OrcaleFromTo = HikariQracleTo.getInstance();
				dbConnection = OrcaleFromTo.getConnection();
				selectSQL = "SELECT LOC_CODE " + "FROM EDC_SETTLEMENT "
						+ "where tx_date =to_date(substr('" + txdate
						+ "',1,19), 'yyyy-mm-dd hh24:mi:ss')"
						+ " and LOC_CODE ='" + loccode + "'" + "and reg_no='"
						+ regno + "'" + "and tx_no ='" + txno + "'";

			}

			preparedStatement = dbConnection.prepareStatement(selectSQL);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				String rschktxdate = rs.getString("LOC_CODE");

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
