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

public class InsertDphdrdesc {

	private static final Logger logger = Logger
			.getLogger(InsertDphdrdesc.class);

	public static boolean Dphdrdescinsert(String rsloccode, String rsdpno,
			String rsbucode, String rsdeptcode, String rsbrandcode,
			String rsdescsp, String rssku, Timestamp rslastupddt,
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

			String insertTableSQL = "INSERT INTO DPHDR_DESC"
					+ "(LOC_CODE,DP_NO,BU_CODE,DEPT_CODE,BRAND_CODE,DESC_SP,SKU,LAST_UPD_DT) "
					+ "VALUES" + "(?,?,?,?,?,?,?,?)";

			preparedStatement = dbConnection.prepareStatement(insertTableSQL);

			preparedStatement.setString(1, rsloccode);
			preparedStatement.setString(2, rsdpno);
			preparedStatement.setString(3, rsbucode);
			preparedStatement.setString(4, rsdeptcode);
			preparedStatement.setString(5, rsbrandcode);
			preparedStatement.setString(6, rsdescsp);
			preparedStatement.setString(7, rssku);
			preparedStatement.setTimestamp(8, rslastupddt);

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

	public static boolean Dphdrdescupdate(String rsloccode, String rsdpno,
			String rsbucode, String rsdeptcode, String rsbrandcode,
			String rsdescsp, String rssku, Timestamp rslastupddt,
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

			// String updateSQL = "Update Dphdrdesc set VOID_BY = ? "
			// +
			// "where TX_DATE = ? and LOC_CODE = ? and REG_NO = ? and TX_NO = ?";

			String updateSQL = "UPDATE DPHDR_DESC " + "SET BU_CODE     = ? "
					+ ", DEPT_CODE   = ? " + ", BRAND_CODE  = ? "
					+ ", DESC_SP     = ? " + ", SKU         = ? "
					+ ", LAST_UPD_DT = ? " + "WHERE LOC_CODE  = ? "
					+ "AND DP_NO       = ? ";

			preparedStatement = dbConnection.prepareStatement(updateSQL);

			preparedStatement.setString(1, rsbucode);
			preparedStatement.setString(2, rsdeptcode);
			preparedStatement.setString(3, rsbrandcode);
			preparedStatement.setString(4, rsdescsp);
			preparedStatement.setString(5, rssku);
			preparedStatement.setTimestamp(6, rslastupddt);
			preparedStatement.setString(7, rsloccode);
			preparedStatement.setString(8, rsdpno);

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

	public static boolean Dphdrdescchkexists(String entitykey, String frdatabase)
			throws SQLException {

		String[] parts = entitykey.split(",");
		String loccode = parts[0];
		String dpno = parts[1];

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

			String selectSQL = "SELECT DP_NO " + "FROM DPHDR_DESC "
					+ "where LOC_CODE ='" + loccode + "'" + "and DP_NO ='"
					+ dpno + "'";

			preparedStatement = dbConnection.prepareStatement(selectSQL);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				String rstxdate = rs.getString("DP_NO");

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
