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

public class InsertStylmasele {
	private static final Logger logger = Logger
			.getLogger(InsertStylmasele.class);

	public static boolean Stylmaseleinsert(String rsstyle, String rsproduct,
			String rsserialnoflag, String rsmodel, String rsband,
			String rsresolution, String rsmemory, String rslink, String rszoom,
			String rsmaxaperture, String rsmanufactureplace,
			String rsaccessory, String rsserprovider, String rswarranty,
			String rsremarks, String rsstoragemedia, String rsratio,
			String rsvoicerecord, String rslineinrecord, Timestamp rslastupddt,
			String rsentitykey, String frdatabase) throws SQLException {

		boolean result = true;
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		String insertTableSQL;

		insertTableSQL = "INSERT INTO STYL_MAS_ELE (STYLE,PRODUCT,SERIAL_NO_FLAG,MODEL,BAND,RESOLUTION,MEMORY,"
				+ "LINK,ZOOM,MAX_APERTURE,MANUFACTURE_PLACE,ACCESSORY,SER_PROVIDER,WARRANTY,REMARKS,STORAGE_MEDIA,"
				+ "RATIO,VOICE_RECORD,LINEIN_RECORD,LAST_UPD_DT,ENTITY_KEY) "
				+ "VALUES ( ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,? )";

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
			preparedStatement.setString(2, rsproduct);
			preparedStatement.setString(3, rsserialnoflag);
			preparedStatement.setString(4, rsmodel);
			preparedStatement.setString(5, rsband);
			preparedStatement.setString(6, rsresolution);
			preparedStatement.setString(7, rsmemory);
			preparedStatement.setString(8, rslink);
			preparedStatement.setString(9, rszoom);
			preparedStatement.setString(10, rsmaxaperture);
			preparedStatement.setString(11, rsmanufactureplace);
			preparedStatement.setString(12, rsaccessory);
			preparedStatement.setString(13, rsserprovider);
			preparedStatement.setString(14, rswarranty);
			preparedStatement.setString(15, rsremarks);
			preparedStatement.setString(16, rsstoragemedia);
			preparedStatement.setString(17, rsratio);
			preparedStatement.setString(18, rsvoicerecord);
			preparedStatement.setString(19, rslineinrecord);
			preparedStatement.setTimestamp(20, rslastupddt);
			preparedStatement.setString(21, rsentitykey);

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

	public static boolean Stylmaseleupdate(String rsstyle, String rsproduct,
			String rsserialnoflag, String rsmodel, String rsband,
			String rsresolution, String rsmemory, String rslink, String rszoom,
			String rsmaxaperture, String rsmanufactureplace,
			String rsaccessory, String rsserprovider, String rswarranty,
			String rsremarks, String rsstoragemedia, String rsratio,
			String rsvoicerecord, String rslineinrecord, Timestamp rslastupddt,
			String rsentitykey, String frdatabase) throws SQLException {

		boolean result = true;
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		String updateSQL;

		updateSQL = "UPDATE STYL_MAS_ELE " + "SET STYLE           = ? "
				+ ", PRODUCT           = ? " + ", SERIAL_NO_FLAG    = ? "
				+ ", MODEL             = ? " + ", BAND              = ? "
				+ ", RESOLUTION        = ? " + ", MEMORY            = ? "
				+ ", LINK              = ? " + ", ZOOM              = ? "
				+ ", MAX_APERTURE      = ? " + ", MANUFACTURE_PLACE = ? "
				+ ", ACCESSORY         = ? " + ", SER_PROVIDER      = ? "
				+ ", WARRANTY          = ? " + ", REMARKS           = ? "
				+ ", STORAGE_MEDIA     = ? " + ", RATIO             = ? "
				+ ", VOICE_RECORD      = ? " + ", LINEIN_RECORD     = ? "
				+ ", LAST_UPD_DT       = ? " + "WHERE  ENTITY_KEY        = ? ";

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
			preparedStatement.setString(2, rsproduct);
			preparedStatement.setString(3, rsserialnoflag);
			preparedStatement.setString(4, rsmodel);
			preparedStatement.setString(5, rsband);
			preparedStatement.setString(6, rsresolution);
			preparedStatement.setString(7, rsmemory);
			preparedStatement.setString(8, rslink);
			preparedStatement.setString(9, rszoom);
			preparedStatement.setString(10, rsmaxaperture);
			preparedStatement.setString(11, rsmanufactureplace);
			preparedStatement.setString(12, rsaccessory);
			preparedStatement.setString(13, rsserprovider);
			preparedStatement.setString(14, rswarranty);
			preparedStatement.setString(15, rsremarks);
			preparedStatement.setString(16, rsstoragemedia);
			preparedStatement.setString(17, rsratio);
			preparedStatement.setString(18, rsvoicerecord);
			preparedStatement.setString(19, rslineinrecord);
			preparedStatement.setTimestamp(20, rslastupddt);
			preparedStatement.setString(21, rsentitykey);

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

	public static boolean Stylmaselechkexists(String entitykey,
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

			String selectSQL = "SELECT STYLE " + "FROM STYL_MAS_ELE "
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
