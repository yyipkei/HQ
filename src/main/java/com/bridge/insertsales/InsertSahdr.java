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

public class InsertSahdr {

	private static final Logger logger = Logger.getLogger(InsertSahdr.class);

	public static boolean Sahdrinsert(String rstxdate, String rsloccode,
			String rsregno, String rstxno, String rstxtime, String rstxtype,
			String rsvoidby, String rscashierno, String rscardtype,
			String rscardno, String rsgfr, String rsgex,
			Timestamp rslastupddt, String frdatabase) throws SQLException {

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

			String insertTableSQL = "INSERT INTO SAHDR"
					+ "(TX_DATE,LOC_CODE,REG_NO,TX_NO,TX_TIME,TX_TYPE,VOID_BY,CASHIER_NO,CARD_TYPE,CARD_NO,GFR,GEX,LAST_UPD_DT) "
					+ "VALUES" + "(?,?,?,?,?,?,?,?,?,?,?,?,?)";

			preparedStatement = dbConnection.prepareStatement(insertTableSQL);

			preparedStatement.setString(1, rstxdate);
			preparedStatement.setString(2, rsloccode);
			preparedStatement.setString(3, rsregno);
			preparedStatement.setString(4, rstxno);
			preparedStatement.setString(5, rstxtime);
			preparedStatement.setString(6, rstxtype);
			preparedStatement.setString(7, rsvoidby);
			preparedStatement.setString(8, rscashierno);
			preparedStatement.setString(9, rscardtype);
			preparedStatement.setString(10, rscardno);
			preparedStatement.setString(11, rsgfr);
			preparedStatement.setString(12, rsgex);
			preparedStatement.setTimestamp(13, rslastupddt);

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

	public static boolean Sahdrupdate(String rstxdate, String rsloccode,
			String rsregno, String rstxno, String rstxtime, String rstxtype,
			String rsvoidby, String rscashierno, String rscardtype,
			String rscardno, String rsgfr, String rsgex, 
			Timestamp rslastupddt, String frdatabase) throws SQLException {

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

			// String updateSQL = "Update SAHDR set VOID_BY = ? "
			// +
			// "where TX_DATE = ? and LOC_CODE = ? and REG_NO = ? and TX_NO = ?";

			String updateSQL = "UPDATE SAHDR "
					+ "SET TX_TIME  =?,TX_TYPE =?,VOID_BY=?,CASHIER_NO=?,CARD_TYPE=?,CARD_NO=?,"
					+ "GFR =?,GEX=?,LAST_UPD_DT =? "
					+ "WHERE TX_DATE   = ? " + "AND LOC_CODE    = ? "
					+ "AND REG_NO      = ? " + "AND TX_NO       = ?";

			preparedStatement = dbConnection.prepareStatement(updateSQL);

			preparedStatement.setString(1, rstxtime);
			preparedStatement.setString(2, rstxtype);
			preparedStatement.setString(3, rsvoidby);
			preparedStatement.setString(4, rscashierno);
			preparedStatement.setString(5, rscardtype);
			preparedStatement.setString(6, rscardno);
			preparedStatement.setString(7, rsgfr);
			preparedStatement.setString(8, rsgex);
			preparedStatement.setTimestamp(9, rslastupddt);
			preparedStatement.setString(10, rstxdate);
			preparedStatement.setString(11, rsloccode);
			preparedStatement.setString(12, rsregno);
			preparedStatement.setString(13, rstxno);

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

	public static boolean Sahdrchkexists(String entitykey, String frdatabase)
			throws SQLException {

		String[] parts = entitykey.split(",");
		String txdate = parts[0];
		String loccode = parts[1];
		String regno = parts[2];
		String txno = parts[3];

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

			String selectSQL = "SELECT TX_DATE " + "FROM SAHDR "
					+ "where tx_date ='" + txdate + "'" + "and LOC_CODE ='"
					+ loccode + "'" + "and reg_no='" + regno + "'"
					+ "and tx_no ='" + txno + "'";

			preparedStatement = dbConnection.prepareStatement(selectSQL);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				String rstxdate = rs.getString("TX_DATE");

				// logger.info(rstxdate);

				if (rstxdate != null) {
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
