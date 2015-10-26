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

public class InsertSastaffdischdr {

	private static final Logger logger = Logger
			.getLogger(InsertSastaffdischdr.class);

	public static boolean Sastaffdischdrinsert(String rscompanycde,
			String rsbatchno, String rsbatchdesc, String rsverno,
			String rscurrver, String rsstatus, Timestamp rseffdtfr,
			Timestamp rseffdtto, String rsprepareby, Timestamp rscfmdt,
			String rscfmby, Timestamp rsappdt, String rsappby,
			Timestamp rslastupddt, String rslastupdusr, String rslastupdver,
			String rsfmcompany, String rsfmbatch, String rsfmver,
			String rsentitykey, String frdatabase) throws SQLException {

		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		String insertTableSQL;

		insertTableSQL = "INSERT INTO SA_STAFF_DISC_HDR (COMPANY_CDE,BATCH_NO,BATCH_DESC,VER_NO,"
				+ "CURR_VER,STATUS,EFF_DT_FR,EFF_DT_TO,PREPARE_BY,CFM_DT,CFM_BY,APP_DT,APP_BY,"
				+ "LAST_UPD_DT,LAST_UPD_USR,LAST_UPD_VER,FM_COMPANY,FM_BATCH,FM_VER,ENTITY_KEY) "
				+ "VALUES ( ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,? )";

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

			preparedStatement.setString(1, rscompanycde);
			preparedStatement.setString(2, rsbatchno);
			preparedStatement.setString(3, rsbatchdesc);
			preparedStatement.setString(4, rsverno);
			preparedStatement.setString(5, rscurrver);
			preparedStatement.setString(6, rsstatus);
			preparedStatement.setTimestamp(7, rseffdtfr);
			preparedStatement.setTimestamp(8, rseffdtto);
			preparedStatement.setString(9, rsprepareby);
			preparedStatement.setTimestamp(10, rscfmdt);
			preparedStatement.setString(11, rscfmby);
			preparedStatement.setTimestamp(12, rsappdt);
			preparedStatement.setString(13, rsappby);
			preparedStatement.setTimestamp(14, rslastupddt);
			preparedStatement.setString(15, rslastupdusr);
			preparedStatement.setString(16, rslastupdver);
			preparedStatement.setString(17, rsfmcompany);
			preparedStatement.setString(18, rsfmbatch);
			preparedStatement.setString(19, rsfmver);
			preparedStatement.setString(20, rsentitykey);

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

	public static boolean Sastaffdischdrupdate(String rscompanycde,
			String rsbatchno, String rsbatchdesc, String rsverno,
			String rscurrver, String rsstatus, Timestamp rseffdtfr,
			Timestamp rseffdtto, String rsprepareby, Timestamp rscfmdt,
			String rscfmby, Timestamp rsappdt, String rsappby,
			Timestamp rslastupddt, String rslastupdusr, String rslastupdver,
			String rsfmcompany, String rsfmbatch, String rsfmver,
			String rsentitykey, String frdatabase) throws SQLException {

		boolean result = true;
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		String updateSQL;

		updateSQL = "UPDATE SA_STAFF_DISC_HDR " + "SET COMPANY_CDE = ? "
				+ ", BATCH_NO      = ? " + ", BATCH_DESC    = ? "
				+ ", VER_NO        = ? " + ", CURR_VER      = ? "
				+ ", STATUS        = ? " + ", EFF_DT_FR     = ? "
				+ ", EFF_DT_TO     = ? " + ", PREPARE_BY    = ? "
				+ ", CFM_DT        = ? " + ", CFM_BY        = ? "
				+ ", APP_DT        = ? " + ", APP_BY        = ? "
				+ ", LAST_UPD_DT   = ? " + ", LAST_UPD_USR  = ? "
				+ ", LAST_UPD_VER  = ? " + ", FM_COMPANY    = ? "
				+ ", FM_BATCH      = ? " + ", FM_VER        = ? "
				+ "WHERE ENTITY_KEY    = ? ";

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

			preparedStatement.setString(1, rscompanycde);
			preparedStatement.setString(2, rsbatchno);
			preparedStatement.setString(3, rsbatchdesc);
			preparedStatement.setString(4, rsverno);
			preparedStatement.setString(5, rscurrver);
			preparedStatement.setString(6, rsstatus);
			preparedStatement.setTimestamp(7, rseffdtfr);
			preparedStatement.setTimestamp(8, rseffdtto);
			preparedStatement.setString(9, rsprepareby);
			preparedStatement.setTimestamp(10, rscfmdt);
			preparedStatement.setString(11, rscfmby);
			preparedStatement.setTimestamp(12, rsappdt);
			preparedStatement.setString(13, rsappby);
			preparedStatement.setTimestamp(14, rslastupddt);
			preparedStatement.setString(15, rslastupdusr);
			preparedStatement.setString(16, rslastupdver);
			preparedStatement.setString(17, rsfmcompany);
			preparedStatement.setString(18, rsfmbatch);
			preparedStatement.setString(19, rsfmver);
			preparedStatement.setString(20, rsentitykey);

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

	public static boolean Sastaffdischdrchkexists(String entitykey,
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

			String selectSQL = "SELECT COMPANY_CDE "
					+ "FROM SA_STAFF_DISC_HDR " + "where ENTITY_KEY ='"
					+ entitykey + "'";

			// logger.info(selectSQL);

			preparedStatement = dbConnection.prepareStatement(selectSQL);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				String rschktxdate = rs.getString("COMPANY_CDE");

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
