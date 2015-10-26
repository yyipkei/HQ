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

public class InsertXratemas {

	private static final Logger logger = Logger.getLogger(InsertXratemas.class);

	public static boolean Xratemasinsert(String rscurrency,
			String rscurrencyname, String rsrate, String rstender,
			String rslastrate, String rstmprate, String rsforeignrate,
			String rsadjustment, String rsroundingdp, String rswarninglimit,
			String rsoverrideuser, Timestamp rsoverridedt,
			String rslastupduser, Timestamp rslastupddt, String rsrowguid,
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

				insertTableSQL = "INSERT INTO XRATEMAS"
						+ "(CURRENCY,CURRENCY_NAME,RATE,TENDER,LAST_RATE,TMP_RATE,FOREIGN_RATE,ADJUSTMENT,"
						+ "ROUNDING_DP,WARNING_LIMIT,OVERRIDE_USER,OVERRIDE_DT,LAST_UPD_USER,LAST_UPD_DT,ROWGUID) "
						+ "VALUES" + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
						+ "newid()" + ")";

				preparedStatement = dbConnection
						.prepareStatement(insertTableSQL);

				preparedStatement.setString(1, rscurrency);
				preparedStatement.setString(2, rscurrencyname);
				preparedStatement.setString(3, rsrate);
				preparedStatement.setString(4, rstender);
				preparedStatement.setString(5, rslastrate);
				preparedStatement.setString(6, rstmprate);
				preparedStatement.setString(7, rsforeignrate);
				preparedStatement.setString(8, rsadjustment);
				preparedStatement.setString(9, rsroundingdp);
				preparedStatement.setString(10, rswarninglimit);
				preparedStatement.setString(11, rsoverrideuser);
				preparedStatement.setTimestamp(12, rsoverridedt);
				preparedStatement.setString(13, rslastupduser);
				preparedStatement.setTimestamp(14, rslastupddt);

			} else {
				// dbConnection = OracleTo.getDBConnection();

				HikariQracleTo OrcaleFromTo = HikariQracleTo.getInstance();
				dbConnection = OrcaleFromTo.getConnection();

				insertTableSQL = "INSERT INTO XRATEMAS"
						+ "(CURRENCY,CURRENCY_NAME,RATE,TENDER,LAST_RATE,TMP_RATE,FOREIGN_RATE,ADJUSTMENT,"
						+ "ROUNDING_DP,WARNING_LIMIT,OVERRIDE_USER,OVERRIDE_DT,LAST_UPD_USER,LAST_UPD_DT,ROWGUID) "
						+ "VALUES" + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

				preparedStatement = dbConnection
						.prepareStatement(insertTableSQL);

				preparedStatement.setString(1, rscurrency);
				preparedStatement.setString(2, rscurrencyname);
				preparedStatement.setString(3, rsrate);
				preparedStatement.setString(4, rstender);
				preparedStatement.setString(5, rslastrate);
				preparedStatement.setString(6, rstmprate);
				preparedStatement.setString(7, rsforeignrate);
				preparedStatement.setString(8, rsadjustment);
				preparedStatement.setString(9, rsroundingdp);
				preparedStatement.setString(10, rswarninglimit);
				preparedStatement.setString(11, rsoverrideuser);
				preparedStatement.setTimestamp(12, rsoverridedt);
				preparedStatement.setString(13, rslastupduser);
				preparedStatement.setTimestamp(14, rslastupddt);
				preparedStatement.setString(15, rsrowguid);

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

	public static boolean Xratemasupdate(String rscurrency,
			String rscurrencyname, String rsrate, String rstender,
			String rslastrate, String rstmprate, String rsforeignrate,
			String rsadjustment, String rsroundingdp, String rswarninglimit,
			String rsoverrideuser, Timestamp rsoverridedt,
			String rslastupduser, Timestamp rslastupddt, String rsrowguid,
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

			String updateSQL = "UPDATE XRATEMAS " + "SET  CURRENCY_NAME = ? "
					+ ", RATE          = ? " + ", TENDER        = ? "
					+ ", LAST_RATE     = ? " + ", TMP_RATE      = ? "
					+ ", FOREIGN_RATE  = ? " + ", ADJUSTMENT    = ? "
					+ ", ROUNDING_DP   = ? " + ", WARNING_LIMIT = ? "
					+ ", OVERRIDE_USER = ? " + ", OVERRIDE_DT   = ? "
					+ ", LAST_UPD_USER = ? " + ", LAST_UPD_DT   = ? "
					+ ", ROWGUID       = ? " + "WHERE CURRENCY    = ? ";

			preparedStatement = dbConnection.prepareStatement(updateSQL);

			preparedStatement.setString(1, rscurrencyname);
			preparedStatement.setString(2, rsrate);
			preparedStatement.setString(3, rstender);
			preparedStatement.setString(4, rslastrate);
			preparedStatement.setString(5, rstmprate);
			preparedStatement.setString(6, rsforeignrate);
			preparedStatement.setString(7, rsadjustment);
			preparedStatement.setString(8, rsroundingdp);
			preparedStatement.setString(9, rswarninglimit);
			preparedStatement.setString(10, rsoverrideuser);
			preparedStatement.setTimestamp(11, rsoverridedt);
			preparedStatement.setString(12, rslastupduser);
			preparedStatement.setTimestamp(13, rslastupddt);
			preparedStatement.setString(14, rsrowguid);
			preparedStatement.setString(15, rscurrency);

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

	public static boolean Xratemaschkexists(String entitykey, String frdatabase)
			throws SQLException {

		String[] parts = entitykey.split(",");
		String currency = parts[0];

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

			String selectSQL = "SELECT CURRENCY_NAME " + "FROM XRATEMAS "
					+ "where CURRENCY ='" + currency + "'";

			// logger.info(selectSQL);

			preparedStatement = dbConnection.prepareStatement(selectSQL);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				String rschktxdate = rs.getString("CURRENCY_NAME");

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
