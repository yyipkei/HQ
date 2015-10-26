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

public class InsertSaitdisc {

	private static final Logger logger = Logger.getLogger(InsertSaitdisc.class);

	public static boolean Saitdiscinsert(String rstxdate, String rsloccode,
			String rsregno, String rstxno, String rsseqno, String rstxtype,
			String rsvoid, String rssku, String rsdiscamt, String rsorgdiscamt,
			String rsdiscper, String rsorgdiscper, String rsdiscoverid,
			String rsdiscid, String rsdiscref, Timestamp rslastupddt,
			String frdatabase) throws SQLException {

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

			String insertTableSQL = "INSERT INTO SAITDISC (TX_DATE,LOC_CODE, REG_NO, TX_NO,SEQ_NO,TX_TYPE,"
					+ "VOID,SKU,DISC_AMT,ORG_DISC_AMT,DISC_PER,ORG_DISC_PER,DISC_OVER_ID, DISC_ID, DISC_REF, LAST_UPD_DT) VALUES "
					+ "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";

			preparedStatement = dbConnection.prepareStatement(insertTableSQL);

			preparedStatement.setString(1, rstxdate);
			preparedStatement.setString(2, rsloccode);
			preparedStatement.setString(3, rsregno);
			preparedStatement.setString(4, rstxno);
			preparedStatement.setString(5, rsseqno);
			preparedStatement.setString(6, rstxtype);
			preparedStatement.setString(7, rsvoid);
			preparedStatement.setString(8, rssku);
			preparedStatement.setString(9, rsdiscamt);
			preparedStatement.setString(10, rsorgdiscamt);
			preparedStatement.setString(11, rsdiscper);
			preparedStatement.setString(12, rsorgdiscper);
			preparedStatement.setString(13, rsdiscoverid);
			preparedStatement.setString(14, rsdiscid);
			preparedStatement.setString(15, rsdiscref);
			preparedStatement.setTimestamp(16, rslastupddt);

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

	public static boolean Saitdiscupdate(String rstxdate, String rsloccode,
			String rsregno, String rstxno, String rsseqno, String rstxtype,
			String rsvoid, String rssku, String rsdiscamt, String rsorgdiscamt,
			String rsdiscper, String rsorgdiscper, String rsdiscoverid,
			String rsdiscid, String rsdiscref, Timestamp rslastupddt,
			String frdatabase) throws SQLException {

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


			String updateSQL ="UPDATE SAITDISC SET TX_TYPE      = ? , VOID         = ? , SKU          = ? , DISC_AMT     = ? , "
					+ "ORG_DISC_AMT = ? , DISC_PER     = ? , ORG_DISC_PER = ? , DISC_OVER_ID = ? , DISC_ID      = ? , DISC_REF     = ? , "
					+ "LAST_UPD_DT  = ?"
					+ " WHERE TX_DATE    = ? AND LOC_CODE     = ? AND REG_NO       = ? AND TX_NO        = ? AND SEQ_NO       = ?";

			preparedStatement = dbConnection.prepareStatement(updateSQL);

			preparedStatement.setString(1, rstxtype);
			preparedStatement.setString(2, rsvoid);
			preparedStatement.setString(3, rssku);
			preparedStatement.setString(4, rsdiscamt);
			preparedStatement.setString(5, rsorgdiscamt);
			preparedStatement.setString(6, rsdiscper);
			preparedStatement.setString(7, rsorgdiscper);
			preparedStatement.setString(8, rsdiscoverid);
			preparedStatement.setString(9, rsdiscid);
			preparedStatement.setString(10, rsdiscref);
			preparedStatement.setTimestamp(11, rslastupddt);
			preparedStatement.setString(12, rstxdate);
			preparedStatement.setString(13, rsloccode);
			preparedStatement.setString(14, rsregno);
			preparedStatement.setString(15, rstxno);
			preparedStatement.setString(16, rsseqno);
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

	public static boolean Saitdiscchkexists(String entitykey, String frdatabase)
			throws SQLException {

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
				// dbConnection = Mssql.getDBConnection();

				HikariMssql Mssqlpool = HikariMssql.getInstance();
				dbConnection = Mssqlpool.getConnection();
			} else {
				// dbConnection = OracleTo.getDBConnection();

				HikariQracleTo OrcaleFromTo = HikariQracleTo.getInstance();
				dbConnection = OrcaleFromTo.getConnection();
			}

			String selectSQL = "SELECT TX_DATE " + "FROM Saitdisc "
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
