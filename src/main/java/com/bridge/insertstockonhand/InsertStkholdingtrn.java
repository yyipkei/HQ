package com.bridge.insertstockonhand;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Objects;

import org.apache.log4j.Logger;

import com.bridge.main.HikariQracleTo;
import com.bridge.main.HikariRms;

public class InsertStkholdingtrn {

	private static final Logger logger = Logger
			.getLogger(InsertStkholdingtrn.class);

	public static boolean Stkholdingtrninsert(String rstrnid, String rssku,
			Timestamp rsdate, String rstrntype, String rstrnsubtype,
			String rsinstitcde, String rsloccde, String rsqty, String rsmrcomp,
			String rsmrno, String rsmrloc, String rsmrseq, String rsrefno,
			Timestamp rslastupddt, String rslastupdusr, String rslastupdver,
			String rsentitykey, String frdatabase) throws SQLException {

		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		String insertTableSQL;

		insertTableSQL = "INSERT INTO STK_HOLDING_TRN (TRN_ID,SKU,DATE_,TRN_TYPE,TRN_SUB_TYPE,INSTIT_CDE,"
				+ "LOC_CDE,QTY,MR_COMP,MR_NO,MR_LOC,MR_SEQ,REF_NO,LAST_UPD_DT,LAST_UPD_USR,LAST_UPD_VER,entity_key) "
				+ "VALUES ( ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,? )";

		try {

			if (Objects.equals(frdatabase, "Oracle")) {
				// dbConnection = Mssql.getDBConnection();

				HikariRms Rmspool = HikariRms.getInstance();
				dbConnection = Rmspool.getConnection();
			} else {
				// dbConnection = OracleTo.getDBConnection();

				HikariQracleTo OrcaleFromTo = HikariQracleTo.getInstance();
				dbConnection = OrcaleFromTo.getConnection();
			}

			preparedStatement = dbConnection.prepareStatement(insertTableSQL);

			preparedStatement.setString(1, rstrnid);
			preparedStatement.setString(2, rssku);
			preparedStatement.setTimestamp(3, rsdate);
			preparedStatement.setString(4, rstrntype);
			preparedStatement.setString(5, rstrnsubtype);
			preparedStatement.setString(6, rsinstitcde);
			preparedStatement.setString(7, rsloccde);
			preparedStatement.setString(8, rsqty);
			preparedStatement.setString(9, rsmrcomp);
			preparedStatement.setString(10, rsmrno);
			preparedStatement.setString(11, rsmrloc);
			preparedStatement.setString(12, rsmrseq);
			preparedStatement.setString(13, rsrefno);
			preparedStatement.setTimestamp(14, rslastupddt);
			preparedStatement.setString(15, rslastupdusr);
			preparedStatement.setString(16, rslastupdver);
			preparedStatement.setString(17, rsentitykey);

			preparedStatement.executeUpdate();

			return true;

		} catch (SQLException e) {

			logger.info(e.getMessage());

			return false;

		} finally {

			if (preparedStatement != null) {
				preparedStatement.close();
			}

			if (dbConnection != null) {
				dbConnection.close();
			}
		}

	}

	public static boolean Stkholdingtrnupdate(String rstrnid, String rssku,
			Timestamp rsdate, String rstrntype, String rstrnsubtype,
			String rsinstitcde, String rsloccde, String rsqty, String rsmrcomp,
			String rsmrno, String rsmrloc, String rsmrseq, String rsrefno,
			Timestamp rslastupddt, String rslastupdusr, String rslastupdver,
			String rsentitykey, String frdatabase) throws SQLException {
		boolean result = true;
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		String updateSQL;

		updateSQL = "UPDATE STK_HOLDING_TRN " + "SET TRN_ID     = ? "
				+ ", SKU          = ? " + ", DATE_        = ? "
				+ ", TRN_TYPE     = ? " + ", TRN_SUB_TYPE = ? "
				+ ", INSTIT_CDE   = ? " + ", LOC_CDE      = ? "
				+ ", QTY          = ? " + ", MR_COMP      = ? "
				+ ", MR_NO        = ? " + ", MR_LOC       = ? "
				+ ", MR_SEQ       = ? " + ", REF_NO       = ? "
				+ ", LAST_UPD_DT  = ? " + ", LAST_UPD_USR = ? "
				+ ", LAST_UPD_VER = ? " + "WHERE ENTITY_KEY   = ? ";

		try {

			if (Objects.equals(frdatabase, "Oracle")) {
				// dbConnection = Mssql.getDBConnection();

				HikariRms Rmspool = HikariRms.getInstance();
				dbConnection = Rmspool.getConnection();
			} else {
				// dbConnection = OracleTo.getDBConnection();

				HikariQracleTo OrcaleFromTo = HikariQracleTo.getInstance();
				dbConnection = OrcaleFromTo.getConnection();
			}

			preparedStatement = dbConnection.prepareStatement(updateSQL);

			preparedStatement.setString(1, rstrnid);
			preparedStatement.setString(2, rssku);
			preparedStatement.setTimestamp(3, rsdate);
			preparedStatement.setString(4, rstrntype);
			preparedStatement.setString(5, rstrnsubtype);
			preparedStatement.setString(6, rsinstitcde);
			preparedStatement.setString(7, rsloccde);
			preparedStatement.setString(8, rsqty);
			preparedStatement.setString(9, rsmrcomp);
			preparedStatement.setString(10, rsmrno);
			preparedStatement.setString(11, rsmrloc);
			preparedStatement.setString(12, rsmrseq);
			preparedStatement.setString(13, rsrefno);
			preparedStatement.setTimestamp(14, rslastupddt);
			preparedStatement.setString(15, rslastupdusr);
			preparedStatement.setString(16, rslastupdver);
			preparedStatement.setString(17, rsentitykey);

			preparedStatement.executeUpdate();

			return result;

		} catch (SQLException e) {

			logger.info(e.getMessage());
			result = false;

			logger.info(false);

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

	public static boolean Stkholdingtrnchkexists(String entitykey,
			String frdatabase) throws SQLException {
		/*
		 * String[] parts = entitykey.split(","); String txdate = parts[0];
		 * String loccode = parts[1]; String regno = parts[2]; String txno =
		 * parts[3]; String seqno = parts[4];
		 */
		boolean result = false;
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		try {

			if (Objects.equals(frdatabase, "Oracle")) {
				// dbConnection = Mssql.getDBConnection();

				HikariRms Rmspool = HikariRms.getInstance();
				dbConnection = Rmspool.getConnection();
			} else {
				// dbConnection = OracleTo.getDBConnection();

				HikariQracleTo OrcaleFromTo = HikariQracleTo.getInstance();
				dbConnection = OrcaleFromTo.getConnection();
			}

			String selectSQL = "SELECT LOC_CDE "
					+ "FROM STK_HOLDING_TRN " + "where ENTITY_KEY ='"
					+ entitykey + "'";

			// logger.info(selectSQL);

			preparedStatement = dbConnection.prepareStatement(selectSQL);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				String rschktxdate = rs.getString("LOC_CDE");

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
