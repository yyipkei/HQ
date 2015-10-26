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

public class InsertVwpdtl {

	private static final Logger logger = Logger.getLogger(InsertVwpdtl.class);

	public static boolean Vwpdtlinsert(String rsinstitcde, String rsvwpcde,
			String rsvoucherno, String rsminpurchaseamt, String rsfacevalue,
			Timestamp rslastupddt, String rslastupdusr, String rslastupdver,
			String rsstatus, String rsrowguid, String frdatabase)
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

				insertTableSQL = "INSERT INTO VWP_DTL"
						+ "(INSTIT_CDE,VWP_CDE,VOUCHER_NO,MIN_PURCHASE_AMT,FACE_VALUE,LAST_UPD_DT,LAST_UPD_USR,LAST_UPD_VER,STATUS,ROWGUID) "
						+ "VALUES" + "(?,?,?,?,?,?,?,?,?," + "newid()" + ")";

				preparedStatement = dbConnection
						.prepareStatement(insertTableSQL);

				preparedStatement.setString(1, rsinstitcde);
				preparedStatement.setString(2, rsvwpcde);
				preparedStatement.setString(3, rsvoucherno);
				preparedStatement.setString(4, rsminpurchaseamt);
				preparedStatement.setString(5, rsfacevalue);
				preparedStatement.setTimestamp(6, rslastupddt);
				preparedStatement.setString(7, rslastupdusr);
				preparedStatement.setString(8, rslastupdver);
				preparedStatement.setString(9, rsstatus);

			} else {
				// dbConnection = OracleTo.getDBConnection();

				HikariQracleTo OrcaleFromTo = HikariQracleTo.getInstance();
				dbConnection = OrcaleFromTo.getConnection();

				insertTableSQL = "INSERT INTO VWP_DTL"
						+ "(INSTIT_CDE,VWP_CDE,VOUCHER_NO,MIN_PURCHASE_AMT,FACE_VALUE,LAST_UPD_DT,LAST_UPD_USR,LAST_UPD_VER,STATUS,ROWGUID) "
						+ "VALUES" + "(?,?,?,?,?,?,?,?,?,?)";

				preparedStatement = dbConnection
						.prepareStatement(insertTableSQL);

				preparedStatement.setString(1, rsinstitcde);
				preparedStatement.setString(2, rsvwpcde);
				preparedStatement.setString(3, rsvoucherno);
				preparedStatement.setString(4, rsminpurchaseamt);
				preparedStatement.setString(5, rsfacevalue);
				preparedStatement.setTimestamp(6, rslastupddt);
				preparedStatement.setString(7, rslastupdusr);
				preparedStatement.setString(8, rslastupdver);
				preparedStatement.setString(9, rsstatus);
				preparedStatement.setString(10, rsrowguid);

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

	public static boolean Vwpdtlupdate(String rsinstitcde, String rsvwpcde,
			String rsvoucherno, String rsminpurchaseamt, String rsfacevalue,
			Timestamp rslastupddt, String rslastupdusr, String rslastupdver,
			String rsstatus, String rsrowguid, String frdatabase)
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

			String updateSQL = "UPDATE VWP_DTL " + "SET  MIN_PURCHASE_AMT = ? "
					+ ", FACE_VALUE       = ? " + ", LAST_UPD_DT      = ? "
					+ ", LAST_UPD_USR     = ? " + ", LAST_UPD_VER     = ? "
					+ ", STATUS           = ? " + ", ROWGUID          = ? "
					+ "WHERE INSTIT_CDE     = ? " + "AND VWP_CDE          = ? "
					+ "AND VOUCHER_NO       = ? ";

			preparedStatement = dbConnection.prepareStatement(updateSQL);

			preparedStatement.setString(1, rsminpurchaseamt);
			preparedStatement.setString(2, rsfacevalue);
			preparedStatement.setTimestamp(3, rslastupddt);
			preparedStatement.setString(4, rslastupdusr);
			preparedStatement.setString(5, rslastupdver);
			preparedStatement.setString(6, rsstatus);
			preparedStatement.setString(7, rsrowguid);
			preparedStatement.setString(8, rsinstitcde);
			preparedStatement.setString(9, rsvwpcde);
			preparedStatement.setString(10, rsvoucherno);

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

	public static boolean Vwpdtlchkexists(String entitykey, String frdatabase)
			throws SQLException {

		String[] parts = entitykey.split(",");
		String institcde = parts[0];
		String vwpcde = parts[1];
		String voucherno = parts[2];

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

			String selectSQL = "SELECT VWP_CDE " + "FROM VWP_DTL "
					+ "where INSTIT_CDE ='" + institcde + "'"
					+ "and VWP_CDE ='" + vwpcde + "'" + "and VOUCHER_NO='"
					+ voucherno + "'";

			// logger.info(selectSQL);

			preparedStatement = dbConnection.prepareStatement(selectSQL);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				String rschktxdate = rs.getString("VWP_CDE");

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
