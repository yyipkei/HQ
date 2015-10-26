package com.bridge.insertitem;

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

public class InsertStylmasservice {
	private static final Logger logger = Logger.getLogger(InsertStylmasservice.class);

	public static boolean Stylmasserviceinsert(String rsstyle, String rsseqno,
			String rssercentre, String rsaddress, String rstel,
			Timestamp rslastupddt, String rsentitykey, String frdatabase)
			throws SQLException {

		boolean result = true;
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		String insertTableSQL;

		insertTableSQL = "INSERT INTO STYL_MAS_SERVICE (STYLE,SEQ_NO,SER_CENTRE,ADDRESS,TEL,LAST_UPD_DT,ENTITY_KEY) "
				+ "VALUES (?,?,?,?,?,?,? )";

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

			preparedStatement.setString(1, rsstyle);
			preparedStatement.setString(2, rsseqno);
			preparedStatement.setString(3, rssercentre);
			preparedStatement.setString(4, rsaddress);
			preparedStatement.setString(5, rstel);
			preparedStatement.setTimestamp(6, rslastupddt);
			preparedStatement.setString(7, rsentitykey);

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

	public static boolean Stylmasserviceupdate(String rsstyle, String rsseqno,
			String rssercentre, String rsaddress, String rstel,
			Timestamp rslastupddt, String rsentitykey, String frdatabase)
			throws SQLException {

		boolean result = true;
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		String updateSQL;

		updateSQL = "UPDATE STYL_MAS_SERVICE " + "SET STYLE     = ? "
				+ ", SEQ_NO      = ? " + ", SER_CENTRE  = ? "
				+ ", ADDRESS     = ? " + ", TEL         = ? "
				+ ", LAST_UPD_DT = ? " + "WHERE  ENTITY_KEY  = ? ";

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

			preparedStatement.setString(1, rsstyle);
			preparedStatement.setString(2, rsseqno);
			preparedStatement.setString(3, rssercentre);
			preparedStatement.setString(4, rsaddress);
			preparedStatement.setString(5, rstel);
			preparedStatement.setTimestamp(6, rslastupddt);
			preparedStatement.setString(7, rsentitykey);

			// logger.info(updateSQL);

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

	public static boolean Stylmasservicechkexists(String entitykey,
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

			String selectSQL = "SELECT STYLE " + "FROM STYL_MAS_SERVICE "
					+ "where ENTITY_KEY ='" + entitykey + "'";

			// logger.info(selectSQL);

			preparedStatement = dbConnection.prepareStatement(selectSQL);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				String rschktxdate = rs.getString("STYLE");

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
