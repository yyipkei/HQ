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

public class InsertSastaffdiscdtl {

	private static final Logger logger = Logger
			.getLogger(InsertSastaffdiscdtl.class);

	public static boolean Sastaffdiscdtlinsert(String rscompanycde,
			String rsbatchno, String rsverno, String rsseqno,
			String rscardtype, String rsbucde, String rsprodtype,
			String rsdiscper, Timestamp rslastupddt, String rslastupdusr,
			String rslastupdver, String rsfmcompany, String rsfmbatch,
			String rsfmver, String rsentitykey, String frdatabase)
			throws SQLException {

		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		String insertTableSQL;

		insertTableSQL = "INSERT INTO SA_STAFF_DISC_DTL (COMPANY_CDE,BATCH_NO,VER_NO,SEQ_NO,"
				+ "CARD_TYPE,BU_CDE,PROD_TYPE,DISC_PER,LAST_UPD_DT,"
				+ "LAST_UPD_USR,LAST_UPD_VER,FM_COMPANY,FM_BATCH,FM_VER,ENTITY_KEY) "
				+ "VALUES ( ?,?,?,?,?,?,?,?,?,?,?,?,?,?,? )";

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
			preparedStatement.setString(3, rsverno);
			preparedStatement.setString(4, rsseqno);
			preparedStatement.setString(5, rscardtype);
			preparedStatement.setString(6, rsbucde);
			preparedStatement.setString(7, rsprodtype);
			preparedStatement.setString(8, rsdiscper);
			preparedStatement.setTimestamp(9, rslastupddt);
			preparedStatement.setString(10, rslastupdusr);
			preparedStatement.setString(11, rslastupdver);
			preparedStatement.setString(12, rsfmcompany);
			preparedStatement.setString(13, rsfmbatch);
			preparedStatement.setString(14, rsfmver);
			preparedStatement.setString(15, rsentitykey);

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

	public static boolean Sastaffdiscdtlupdate(String rscompanycde,
			String rsbatchno, String rsverno, String rsseqno,
			String rscardtype, String rsbucde, String rsprodtype,
			String rsdiscper, Timestamp rslastupddt, String rslastupdusr,
			String rslastupdver, String rsfmcompany, String rsfmbatch,
			String rsfmver, String rsentitykey, String frdatabase)
			throws SQLException {

		boolean result = true;
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		String updateSQL;

		updateSQL = "UPDATE SA_STAFF_DISC_DTL " + "SET COMPANY_CDE = ? "
				+ ", BATCH_NO      = ? " + ", VER_NO        = ? "
				+ ", SEQ_NO        = ? " + ", CARD_TYPE     = ? "
				+ ", BU_CDE        = ? " + ", PROD_TYPE     = ? "
				+ ", DISC_PER      = ? " + ", LAST_UPD_DT   = ? "
				+ ", LAST_UPD_USR  = ? " + ", LAST_UPD_VER  = ? "
				+ ", FM_COMPANY    = ? " + ", FM_BATCH      = ? "
				+ ", FM_VER        = ? " + "WHERE  ENTITY_KEY    = ? ";

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
			preparedStatement.setString(3, rsverno);
			preparedStatement.setString(4, rsseqno);
			preparedStatement.setString(5, rscardtype);
			preparedStatement.setString(6, rsbucde);
			preparedStatement.setString(7, rsprodtype);
			preparedStatement.setString(8, rsdiscper);
			preparedStatement.setTimestamp(9, rslastupddt);
			preparedStatement.setString(10, rslastupdusr);
			preparedStatement.setString(11, rslastupdver);
			preparedStatement.setString(12, rsfmcompany);
			preparedStatement.setString(13, rsfmbatch);
			preparedStatement.setString(14, rsfmver);
			preparedStatement.setString(15, rsentitykey);

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

	public static boolean Sastaffdiscdtlchkexists(String entitykey,
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
					+ "FROM SA_STAFF_DISC_DTL " + "where ENTITY_KEY ='"
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
