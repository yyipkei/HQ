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

public class InsertCafecoupondet {

	private static final Logger logger = Logger.getLogger(InsertCafecoupondet.class);

	public static boolean Cafecoupondetinsert(String rsid, String rsloccode,
			String rscouponno, String rsseqno, String rsremarks,
			String rsrowguid, String rsreasongroup, String rsreason,
			Timestamp rslastupddt, String frdatabase) throws SQLException {

		boolean result = true;
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		String insertTableSQL;

		try {

			if (Objects.equals(frdatabase, "Oracle")) {
				// dbConnection = Merge.getDBConnection();

				HikariMerge Mergepool = HikariMerge.getInstance();
				dbConnection = Mergepool.getConnection();

				insertTableSQL = "INSERT INTO CAFE_COUPON_DET"
						+ "(ID,LOC_CODE,COUPON_NO,SEQ_NO,REMARKS,ROWGUID,REASON_GROUP,REASON,LAST_UPD_DT) "
						+ "VALUES" + "(?,?,?,?,?," + "newid()" + ",?,?,?)";

				preparedStatement = dbConnection
						.prepareStatement(insertTableSQL);

				preparedStatement.setString(1, rsid);
				preparedStatement.setString(2, rsloccode);
				preparedStatement.setString(3, rscouponno);
				preparedStatement.setString(4, rsseqno);
				preparedStatement.setString(5, rsremarks);
				preparedStatement.setString(6, rsreasongroup);
				preparedStatement.setString(7, rsreason);
				preparedStatement.setTimestamp(8, rslastupddt);

			} else {
				// dbConnection = OracleTo.getDBConnection();

				HikariQracleTo OrcaleFromTo = HikariQracleTo.getInstance();
				dbConnection = OrcaleFromTo.getConnection();

				insertTableSQL = "INSERT INTO CAFE_COUPON_DET"
						+ "(ID,LOC_CODE,COUPON_NO,SEQ_NO,REMARKS,ROWGUID,REASON_GROUP,REASON,LAST_UPD_DT) "
						+ "VALUES " + "(?,?,?,?,?,?,?,?,?)";

				preparedStatement = dbConnection
						.prepareStatement(insertTableSQL);

				preparedStatement.setString(1, rsid);
				preparedStatement.setString(2, rsloccode);
				preparedStatement.setString(3, rscouponno);
				preparedStatement.setString(4, rsseqno);
				preparedStatement.setString(5, rsremarks);
				preparedStatement.setString(6, rsrowguid);
				preparedStatement.setString(7, rsreasongroup);
				preparedStatement.setString(8, rsreason);
				preparedStatement.setTimestamp(9, rslastupddt);

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

	public static boolean Cafecoupondetupdate(String rsid, String rsloccode,
			String rscouponno, String rsseqno, String rsremarks,
			String rsrowguid, String rsreasongroup, String rsreason,
			Timestamp rslastupddt, String frdatabase) throws SQLException {

		boolean result = true;
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		String updateSQL;

		try {

			if (Objects.equals(frdatabase, "Oracle")) {
				// dbConnection = Merge.getDBConnection();

				HikariMerge Mergepool = HikariMerge.getInstance();
				dbConnection = Mergepool.getConnection();

				updateSQL = "UPDATE CAFE_COUPON_DET "
						+ "SET ID         = ? " + ", LOC_CODE     = ? "
						+ ", SEQ_NO       = ? " + ", REMARKS      = ? "
						+ ", REASON_GROUP = ? " + ", REASON       = ? "
						+ ", LAST_UPD_DT  = ? " + "WHERE COUPON_NO    = ? ";
			} else {
				// dbConnection = OracleTo.getDBConnection();

				HikariQracleTo OrcaleFromTo = HikariQracleTo.getInstance();
				dbConnection = OrcaleFromTo.getConnection();

				updateSQL = "UPDATE CAFE_COUPON_DET "
						+ "SET ID         = ? " + ", LOC_CODE     = ? "
						+ ", SEQ_NO       = ? " + ", REMARKS      = ? "
						+ ", REASON_GROUP = ? " + ", REASON       = ? "
						+ ", LAST_UPD_DT  = ? " + "WHERE COUPON_NO    = ? ";
			}

			preparedStatement = dbConnection.prepareStatement(updateSQL);

			preparedStatement.setString(1, rsid);
			preparedStatement.setString(2, rsloccode);
			preparedStatement.setString(3, rsseqno);
			preparedStatement.setString(4, rsremarks);
			preparedStatement.setString(5, rsreasongroup);
			preparedStatement.setString(6, rsreason);
			preparedStatement.setTimestamp(7, rslastupddt);
			preparedStatement.setString(8, rscouponno);

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

	public static boolean Cafecoupondetchkexists(String entitykey, String frdatabase)
			throws SQLException {

		boolean result = false;
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		String selectSQL;

		try {

			if (Objects.equals(frdatabase, "Oracle")) {
				// dbConnection = Merge.getDBConnection();

				HikariMerge Mergepool = HikariMerge.getInstance();
				dbConnection = Mergepool.getConnection();

				selectSQL = "SELECT LOC_CODE " + "FROM CAFE_COUPON_DET "
						+ "where COUPON_NO ='" + entitykey + "'"  ;
			} else {
				// dbConnection = OracleTo.getDBConnection();

				HikariQracleTo OrcaleFromTo = HikariQracleTo.getInstance();
				dbConnection = OrcaleFromTo.getConnection();

				selectSQL = "SELECT LOC_CODE " + "FROM CAFE_COUPON_DET "
						+ "where COUPON_NO ='" + entitykey + "'"  ;
			}

			// logger.info(selectSQL);

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
