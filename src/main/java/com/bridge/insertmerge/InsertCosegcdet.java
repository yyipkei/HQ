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

public class InsertCosegcdet {

	private static final Logger logger = Logger
			.getLogger(InsertCosegcdet.class);

	public static boolean Cosegcdetinsert(String rsid, String rsloccode,
			String rscouponno, String rscouponamount, String rsseqno,
			String rsremarks, String rsrowguid, String rsreasongroup,
			String rsreason, Timestamp rslastupddt, String frdatabase)
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

				insertTableSQL = "INSERT INTO COS_EGC_DET"
						+ "(ID,LOC_CODE,COUPON_NO,COUPON_AMOUNT,SEQ_NO,REMARKS,ROWGUID,REASON_GROUP,REASON,LAST_UPD_DT) "
						+ "VALUES" + "(?,?,?,?,?,?," + "newid()" + ",?,?,?)";

				preparedStatement = dbConnection
						.prepareStatement(insertTableSQL);

				preparedStatement.setString(1, rsid);
				preparedStatement.setString(2, rsloccode);
				preparedStatement.setString(3, rscouponno);
				preparedStatement.setString(4, rscouponamount);
				preparedStatement.setString(5, rsseqno);
				preparedStatement.setString(6, rsremarks);
				preparedStatement.setString(7, rsreasongroup);
				preparedStatement.setString(8, rsreason);
				preparedStatement.setTimestamp(9, rslastupddt);

			} else {
				// dbConnection = OracleTo.getDBConnection();

				HikariQracleTo OrcaleFromTo = HikariQracleTo.getInstance();
				dbConnection = OrcaleFromTo.getConnection();

				insertTableSQL = "INSERT INTO COS_EGC_DET"
						+ "(ID,LOC_CODE,COUPON_NO,COUPON_AMOUNT,SEQ_NO,REMARKS,ROWGUID,REASON_GROUP,REASON,LAST_UPD_DT) "
						+ "VALUES" + "(?,?,?,?,?,?,?,?,?,?)";

				preparedStatement = dbConnection
						.prepareStatement(insertTableSQL);

				preparedStatement.setString(1, rsid);
				preparedStatement.setString(2, rsloccode);
				preparedStatement.setString(3, rscouponno);
				preparedStatement.setString(4, rscouponamount);
				preparedStatement.setString(5, rsseqno);
				preparedStatement.setString(6, rsremarks);
				preparedStatement.setString(7, rsrowguid);
				preparedStatement.setString(8, rsreasongroup);
				preparedStatement.setString(9, rsreason);
				preparedStatement.setTimestamp(10, rslastupddt);

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

	public static boolean Cosegcdetupdate(String rsid, String rsloccode,
			String rscouponno, String rscouponamount, String rsseqno,
			String rsremarks, String rsrowguid, String rsreasongroup,
			String rsreason, Timestamp rslastupddt, String frdatabase)
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

			String updateSQL = "UPDATE COS_EGC_DET "
					+ "SET  COUPON_AMOUNT = ? " + ", SEQ_NO        = ? "
					+ ", REMARKS       = ? " + ", REASON_GROUP  = ? "
					+ ", REASON        = ? " + ", LAST_UPD_DT   = ? "
					+ "WHERE ID          = ? " + "AND LOC_CODE      = ? "
					+ "AND COUPON_NO     = ? ";

			preparedStatement = dbConnection.prepareStatement(updateSQL);

			preparedStatement.setString(1, rscouponamount);
			preparedStatement.setString(2, rsseqno);
			preparedStatement.setString(3, rsremarks);
			preparedStatement.setString(4, rsreasongroup);
			preparedStatement.setString(5, rsreason);
			preparedStatement.setTimestamp(6, rslastupddt);
			preparedStatement.setString(7, rsid);
			preparedStatement.setString(8, rsloccode);
			preparedStatement.setString(9, rscouponno);

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

	public static boolean Cosegcdetchkexists(String entitykey, String frdatabase)
			throws SQLException {

		String[] parts = entitykey.split(",");
		String id = parts[0];
		String loccode = parts[1];
		String couponno = parts[2];

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

			String selectSQL = "SELECT SEQ_NO " + "FROM COS_EGC_DET "
					+ "where id ='" + id + "'" + "and LOC_CODE ='" + loccode
					+ "'" + "and COUPON_NO='" + couponno + "'";

			// logger.info(selectSQL);

			preparedStatement = dbConnection.prepareStatement(selectSQL);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				String rschktxdate = rs.getString("SEQ_NO");

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
