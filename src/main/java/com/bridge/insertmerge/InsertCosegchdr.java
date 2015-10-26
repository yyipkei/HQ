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

public class InsertCosegchdr {

	private static final Logger logger = Logger
			.getLogger(InsertCosegchdr.class);

	public static boolean Cosegchdrinsert(String rsid, String rstxdate,
			String rsloccode, String rsposno, String rsstaffid,
			String rsorgloc, String rsorgreg, String rsorgtxno,
			String rsorgtxamt, String rsreasongroup, String rsreason,
			String rsremarks, String rsrowguid, String rsvipno,
			String rsbucode, String rsvoidstaffid, String rsvoiddate,
			String rsvoidreason, String rsvoid, Timestamp rslastupddt,
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

				insertTableSQL = "INSERT INTO COS_EGC_HDR"
						+ "(ID,TX_DATE,LOC_CODE,POS_NO,STAFF_ID,ORG_LOC,ORG_REG,ORG_TX_NO,"
						+ "ORG_TX_AMT,REASON_GROUP,REASON,REMARKS,ROWGUID,VIP_NO,BU_CODE,"
						+ "VOID_STAFF_ID,VOID_DATE,VOID_REASON,VOID,LAST_UPD_DT) "
						+ "VALUES" + "(?,?,?,?,?,?,?,?,?,?,?,?," + "newid()"
						+ ",?,?,?,?,?,?,?)";

				preparedStatement = dbConnection
						.prepareStatement(insertTableSQL);

				preparedStatement.setString(1, rsid);
				preparedStatement.setString(2, rstxdate);
				preparedStatement.setString(3, rsloccode);
				preparedStatement.setString(4, rsposno);
				preparedStatement.setString(5, rsstaffid);
				preparedStatement.setString(6, rsorgloc);
				preparedStatement.setString(7, rsorgreg);
				preparedStatement.setString(8, rsorgtxno);
				preparedStatement.setString(9, rsorgtxamt);
				preparedStatement.setString(10, rsreasongroup);
				preparedStatement.setString(11, rsreason);
				preparedStatement.setString(12, rsremarks);
				preparedStatement.setString(13, rsvipno);
				preparedStatement.setString(14, rsbucode);
				preparedStatement.setString(15, rsvoidstaffid);
				preparedStatement.setString(16, rsvoiddate);
				preparedStatement.setString(17, rsvoidreason);
				preparedStatement.setString(18, rsvoid);
				preparedStatement.setTimestamp(19, rslastupddt);

			} else {
				// dbConnection = OracleTo.getDBConnection();

				HikariQracleTo OrcaleFromTo = HikariQracleTo.getInstance();
				dbConnection = OrcaleFromTo.getConnection();

				insertTableSQL = "INSERT INTO COS_EGC_HDR"
						+ "(ID,TX_DATE,LOC_CODE,POS_NO,STAFF_ID,ORG_LOC,ORG_REG,ORG_TX_NO,"
						+ "ORG_TX_AMT,REASON_GROUP,REASON,REMARKS,ROWGUID,VIP_NO,BU_CODE,"
						+ "VOID_STAFF_ID,VOID_DATE,VOID_REASON,VOID,LAST_UPD_DT) "
						+ "VALUES"
						+ "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

				preparedStatement = dbConnection
						.prepareStatement(insertTableSQL);

				preparedStatement.setString(1, rsid);
				preparedStatement.setString(2, rstxdate);
				preparedStatement.setString(3, rsloccode);
				preparedStatement.setString(4, rsposno);
				preparedStatement.setString(5, rsstaffid);
				preparedStatement.setString(6, rsorgloc);
				preparedStatement.setString(7, rsorgreg);
				preparedStatement.setString(8, rsorgtxno);
				preparedStatement.setString(9, rsorgtxamt);
				preparedStatement.setString(10, rsreasongroup);
				preparedStatement.setString(11, rsreason);
				preparedStatement.setString(12, rsremarks);
				preparedStatement.setString(13, rsrowguid);
				preparedStatement.setString(14, rsvipno);
				preparedStatement.setString(15, rsbucode);
				preparedStatement.setString(16, rsvoidstaffid);
				preparedStatement.setString(17, rsvoiddate);
				preparedStatement.setString(18, rsvoidreason);
				preparedStatement.setString(19, rsvoid);
				preparedStatement.setTimestamp(20, rslastupddt);

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

	public static boolean Cosegchdrupdate(String rsid, String rstxdate,
			String rsloccode, String rsposno, String rsstaffid,
			String rsorgloc, String rsorgreg, String rsorgtxno,
			String rsorgtxamt, String rsreasongroup, String rsreason,
			String rsremarks, String rsrowguid, String rsvipno,
			String rsbucode, String rsvoidstaffid, String rsvoiddate,
			String rsvoidreason, String rsvoid, Timestamp rslastupddt,
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

			String updateSQL = "UPDATE COS_EGC_HDR "
					+ "SET  TX_DATE       = ? " + ", POS_NO        = ? "
					+ ", STAFF_ID      = ? " + ", ORG_LOC       = ? "
					+ ", ORG_REG       = ? " + ", ORG_TX_NO     = ? "
					+ ", ORG_TX_AMT    = ? " + ", REASON_GROUP  = ? "
					+ ", REASON        = ? " + ", REMARKS       = ? "
					+ ", ROWGUID       = ? " + ", VIP_NO        = ? "
					+ ", BU_CODE       = ? " + ", VOID_STAFF_ID = ? "
					+ ", VOID_DATE     = ? " + ", VOID_REASON   = ? "
					+ ", VOID          = ? " + ", LAST_UPD_DT   = ? "
					+ "WHERE ID          = ? " + "AND LOC_CODE      = ? ";

			preparedStatement = dbConnection.prepareStatement(updateSQL);

			preparedStatement.setString(1, rstxdate);
			preparedStatement.setString(2, rsposno);
			preparedStatement.setString(3, rsstaffid);
			preparedStatement.setString(4, rsorgloc);
			preparedStatement.setString(5, rsorgreg);
			preparedStatement.setString(6, rsorgtxno);
			preparedStatement.setString(7, rsorgtxamt);
			preparedStatement.setString(8, rsreasongroup);
			preparedStatement.setString(9, rsreason);
			preparedStatement.setString(10, rsremarks);
			preparedStatement.setString(11, rsrowguid);
			preparedStatement.setString(12, rsvipno);
			preparedStatement.setString(13, rsbucode);
			preparedStatement.setString(14, rsvoidstaffid);
			preparedStatement.setString(15, rsvoiddate);
			preparedStatement.setString(16, rsvoidreason);
			preparedStatement.setString(17, rsvoid);
			preparedStatement.setTimestamp(18, rslastupddt);
			preparedStatement.setString(19, rsid);
			preparedStatement.setString(20, rsloccode);

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

	public static boolean Cosegchdrchkexists(String entitykey, String frdatabase)
			throws SQLException {

		String[] parts = entitykey.split(",");
		String id = parts[0];
		String loccode = parts[1];

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

			String selectSQL = "SELECT TX_DATE " + "FROM COS_EGC_HDR "
					+ "where id ='" + id + "'" + "and LOC_CODE ='"
					+ loccode + "'";

			// logger.info(selectSQL);

			preparedStatement = dbConnection.prepareStatement(selectSQL);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				String rschktxdate = rs.getString("TX_DATE");

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
