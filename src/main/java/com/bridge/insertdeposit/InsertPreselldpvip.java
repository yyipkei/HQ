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

public class InsertPreselldpvip {

	private static final Logger logger = Logger
			.getLogger(InsertPreselldpvip.class);

	public static boolean Preselldpvipinsert(String rsloccode, String rsdpno,
			String rsvipno, String rseventid, String rscontactphoneno,
			String rslastupdusr, Timestamp rslastupddt, String rslastupdver,
			String rspickuploc, String rsrowguid, String frdatabase)
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

				insertTableSQL = "INSERT INTO presell_dp_vip"
						+ "(LOC_CODE,DP_NO,VIP_NO,EVENT_ID,CONTACT_PHONE_NO,LAST_UPD_USR,LAST_UPD_DT,LAST_UPD_VER,PICK_UP_LOC,ROWGUID) "
						+ "VALUES" + "(?,?,?,?,?,?,?,?,?," + "newid()" + ")";

				preparedStatement = dbConnection
						.prepareStatement(insertTableSQL);

				preparedStatement.setString(1, rsloccode);
				preparedStatement.setString(2, rsdpno);
				preparedStatement.setString(3, rsvipno);
				preparedStatement.setString(4, rseventid);
				preparedStatement.setString(5, rscontactphoneno);
				preparedStatement.setString(6, rslastupdusr);
				preparedStatement.setTimestamp(7, rslastupddt);
				preparedStatement.setString(8, rslastupdver);
				preparedStatement.setString(9, rspickuploc);

			} else {
				// dbConnection = OracleTo.getDBConnection();

				HikariQracleTo OrcaleFromTo = HikariQracleTo.getInstance();
				dbConnection = OrcaleFromTo.getConnection();

				insertTableSQL = "INSERT INTO presell_dp_vip"
						+ "(LOC_CODE,DP_NO,VIP_NO,EVENT_ID,CONTACT_PHONE_NO,LAST_UPD_USR,LAST_UPD_DT,LAST_UPD_VER,PICK_UP_LOC,ROWGUID) "
						+ "VALUES" + "(?,?,?,?,?,?,?,?,?,?)";

				preparedStatement = dbConnection
						.prepareStatement(insertTableSQL);

				preparedStatement.setString(1, rsloccode);
				preparedStatement.setString(2, rsdpno);
				preparedStatement.setString(3, rsvipno);
				preparedStatement.setString(4, rseventid);
				preparedStatement.setString(5, rscontactphoneno);
				preparedStatement.setString(6, rslastupdusr);
				preparedStatement.setTimestamp(7, rslastupddt);
				preparedStatement.setString(8, rslastupdver);
				preparedStatement.setString(9, rspickuploc);
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

	public static boolean Preselldpvipupdate(String rsloccode, String rsdpno,
			String rsvipno, String rseventid, String rscontactphoneno,
			String rslastupdusr, Timestamp rslastupddt, String rslastupdver,
			String rspickuploc, String rsrowguid, String frdatabase)
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

			String updateSQL = "UPDATE PRESELL_DP_VIP "
					+ "SET  VIP_NO           = ? " + ", EVENT_ID         = ? "
					+ ", CONTACT_PHONE_NO = ? " + ", LAST_UPD_USR     = ? "
					+ ", LAST_UPD_DT      = ? " + ", LAST_UPD_VER     = ? "
					+ ", PICK_UP_LOC      = ? " 
					+ "WHERE LOC_CODE       = ? " + "AND DP_NO            = ? ";

			preparedStatement = dbConnection.prepareStatement(updateSQL);

			preparedStatement.setString(1, rsvipno);
			preparedStatement.setString(2, rseventid);
			preparedStatement.setString(3, rscontactphoneno);
			preparedStatement.setString(4, rslastupdusr);
			preparedStatement.setTimestamp(5, rslastupddt);
			preparedStatement.setString(6, rslastupdver);
			preparedStatement.setString(7, rspickuploc);
			preparedStatement.setString(8, rsloccode);
			preparedStatement.setString(9, rsdpno);

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

	public static boolean Preselldpvipchkexists(String entitykey,
			String frdatabase) throws SQLException {

		String[] parts = entitykey.split(",");
		String txdate = parts[0];
		String dpno = parts[1];

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

			String selectSQL = "SELECT LOC_CODE " + "FROM PRESELL_DP_VIP "
					+ "where LOC_CODE ='" + txdate + "'" + "and DP_NO ='"
					+ dpno + "'";

			// logger.info(selectSQL);a

			preparedStatement = dbConnection.prepareStatement(selectSQL);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				String rschktxdate = rs.getString("LOC_CODE");

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
