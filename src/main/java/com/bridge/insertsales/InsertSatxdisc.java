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

public class InsertSatxdisc {
	private static final Logger logger = Logger.getLogger(InsertSatxdisc.class);

	public static boolean Satxdiscinsert(String rstxdate, String rsloccode,
			String rsregno, String rstxno, String rsseqno, String rstxtype,
			String rsvoid, String rsdiscamt, String rsorgdiscamt,
			String rsdiscper, String rsorgdiscper, String rsdiscoverid,
			String rsdiscid, String rsdiscreg, Timestamp rslastupddt,
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

			String insertTableSQL = "INSERT INTO SATXDISC (TX_DATE,LOC_CODE,REG_NO,TX_NO, SEQ_NO, TX_TYPE, VOID,"
					+ " DISC_AMT,ORG_DISC_AMT, DISC_PER,  ORG_DISC_PER,  DISC_OVER_ID,DISC_ID, DISC_REF, LAST_UPD_DATE) "
					+ "VALUES ( ?,  ?,  ?,  ?,  ?, ?,  ?,  ?,  ?, ?,  ?,  ?,  ?, ?,  ?)";

			preparedStatement = dbConnection.prepareStatement(insertTableSQL);

			preparedStatement.setString(1, rstxdate);
			preparedStatement.setString(2, rsloccode);
			preparedStatement.setString(3, rsregno);
			preparedStatement.setString(4, rstxno);
			preparedStatement.setString(5, rsseqno);
			preparedStatement.setString(6, rstxtype);
			preparedStatement.setString(7, rsvoid);
			preparedStatement.setString(8, rsdiscamt);
			preparedStatement.setString(9, rsorgdiscamt);
			preparedStatement.setString(10, rsdiscper);
			preparedStatement.setString(11, rsorgdiscper);
			preparedStatement.setString(12, rsdiscoverid);
			preparedStatement.setString(13, rsdiscid);
			preparedStatement.setString(14, rsdiscreg);
			preparedStatement.setTimestamp(15, rslastupddt);

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

	public static boolean Satxdiscupdate(String rstxdate, String rsloccode,
			String rsregno, String rstxno, String rsseqno, String rstxtype,
			String rsvoid, String rsdiscamt, String rsorgdiscamt,
			String rsdiscper, String rsorgdiscper, String rsdiscoverid,
			String rsdiscid, String rsdiscreg, Timestamp rslastupddt,
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

			String updateSQL = "UPDATE SATXDISC SET  TX_TYPE                  = ? , VOID                     = ? , DISC_AMT                 = ? ,"
					+ " ORG_DISC_AMT             = ? , DISC_PER                 = ? , ORG_DISC_PER             = ? , DISC_OVER_ID             = ? ,"
					+ " DISC_ID                  = ? , DISC_REF                 = ? , LAST_UPD_DATE            = ?"
					+ "WHERE TX_DATE                = ? AND LOC_CODE                 = ? AND REG_NO                   = ? "
					+ "AND TX_NO                    = ? AND SEQ_NO                   = ?";

			preparedStatement = dbConnection.prepareStatement(updateSQL);


			preparedStatement.setString(1, rstxtype);
			preparedStatement.setString(2, rsvoid);
			preparedStatement.setString(3, rsdiscamt);
			preparedStatement.setString(4, rsorgdiscamt);
			preparedStatement.setString(5, rsdiscper);
			preparedStatement.setString(6, rsorgdiscper);
			preparedStatement.setString(7, rsdiscoverid);
			preparedStatement.setString(8, rsdiscid);
			preparedStatement.setString(9, rsdiscreg);
			preparedStatement.setTimestamp(10, rslastupddt);
			preparedStatement.setString(11, rstxdate);
			preparedStatement.setString(12, rsloccode);
			preparedStatement.setString(13, rsregno);
			preparedStatement.setString(14, rstxno);
			preparedStatement.setString(15, rsseqno);
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

	public static boolean Satxdiscchkexists(String entitykey, String frdatabase)
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

			String selectSQL = "SELECT TX_DATE " + "FROM Satxdisc "
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
