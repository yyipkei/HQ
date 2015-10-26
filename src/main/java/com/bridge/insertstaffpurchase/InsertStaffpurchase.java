package com.bridge.insertstaffpurchase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Objects;

import com.bridge.main.HikariRms;
import org.apache.log4j.Logger;

import com.bridge.main.HikariMssql;
import com.bridge.main.HikariQracleTo;

public class InsertStaffpurchase {
	private static final Logger logger = Logger
			.getLogger(InsertStaffpurchase.class);

	public static boolean Staffpurchaseinsert(String rscompany,
			String rsstaffid, String rsname, String rsspousename,
			String rscardtype, String rscreditlimit, String rslocdesc,
			String rsdivdesc, String rsdeptdesc, String rsactive,
			String rsstaffloccode, String rsaccumpurchase, String rsdeptcde,
			String rsemailadd, Timestamp rslastupddt, String rsentitykey,
			String frdatabase) throws SQLException {

		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		String insertTableSQL;

		insertTableSQL = "INSERT INTO STAFF_PURCHASE (COMPANY,STAFF_ID,NAME,SPOUSE_NAME,CARD_TYPE,CREDIT_LIMIT,LOC_DESC,DIV_DESC,DEPT_DESC,ACTIVE,STAFF_LOC_CODE,ACCUM_PURCHASE,DEPT_CDE,EMAIL_ADD,LAST_UPD_DT,ENTITY_KEY) "
				+ "VALUES ( ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,? )";

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

			preparedStatement.setString(1, rscompany);
			preparedStatement.setString(2, rsstaffid);
			preparedStatement.setString(3, rsname);
			preparedStatement.setString(4, rsspousename);
			preparedStatement.setString(5, rscardtype);
			preparedStatement.setString(6, rscreditlimit);
			preparedStatement.setString(7, rslocdesc);
			preparedStatement.setString(8, rsdivdesc);
			preparedStatement.setString(9, rsdeptdesc);
			preparedStatement.setString(10, rsactive);
			preparedStatement.setString(11, rsstaffloccode);
			preparedStatement.setString(12, rsaccumpurchase);
			preparedStatement.setString(13, rsdeptcde);
			preparedStatement.setString(14, rsemailadd);
			preparedStatement.setTimestamp(15, rslastupddt);
			preparedStatement.setString(16, rsentitykey);

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

	public static boolean Staffpurchaseupdate(String rscompany,
			String rsstaffid, String rsname, String rsspousename,
			String rscardtype, String rscreditlimit, String rslocdesc,
			String rsdivdesc, String rsdeptdesc, String rsactive,
			String rsstaffloccode, String rsaccumpurchase, String rsdeptcde,
			String rsemailadd, Timestamp rslastupddt, String rsentitykey,
			String frdatabase) throws SQLException {

		boolean result = true;
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		String updateSQL;

		updateSQL = "UPDATE STAFF_PURCHASE " + "SET COMPANY      = ? "
				+ ", STAFF_ID       = ? " + ", NAME           = ? "
				+ ", SPOUSE_NAME    = ? " + ", CARD_TYPE      = ? "
				+ ", CREDIT_LIMIT   = ? " + ", LOC_DESC       = ? "
				+ ", DIV_DESC       = ? " + ", DEPT_DESC      = ? "
				+ ", ACTIVE         = ? " + ", STAFF_LOC_CODE = ? "
				+ ", ACCUM_PURCHASE = ? " + ", DEPT_CDE       = ? "
				+ ", EMAIL_ADD      = ? " + ", LAST_UPD_DT    = ? "
				+ "WHERE ENTITY_KEY     = ? ";

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

			preparedStatement.setString(1, rscompany);
			preparedStatement.setString(2, rsstaffid);
			preparedStatement.setString(3, rsname);
			preparedStatement.setString(4, rsspousename);
			preparedStatement.setString(5, rscardtype);
			preparedStatement.setString(6, rscreditlimit);
			preparedStatement.setString(7, rslocdesc);
			preparedStatement.setString(8, rsdivdesc);
			preparedStatement.setString(9, rsdeptdesc);
			preparedStatement.setString(10, rsactive);
			preparedStatement.setString(11, rsstaffloccode);
			preparedStatement.setString(12, rsaccumpurchase);
			preparedStatement.setString(13, rsdeptcde);
			preparedStatement.setString(14, rsemailadd);
			preparedStatement.setTimestamp(15, rslastupddt);
			preparedStatement.setString(16, rsentitykey);

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

	public static boolean Staffpurchasechkexists(String entitykey,
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

			String selectSQL = "SELECT STAFF_ID " + "FROM STAFF_PURCHASE "
					+ "where ENTITY_KEY ='" + entitykey + "'";

			// logger.info(selectSQL);

			preparedStatement = dbConnection.prepareStatement(selectSQL);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				String rschktxdate = rs.getString("STAFF_ID");

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
