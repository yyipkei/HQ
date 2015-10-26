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

public class InsertMnvendorupc {

	private static final Logger logger = Logger
			.getLogger(InsertMnvendorupc.class);

	public static boolean Mnvendorupcinsert(String rsvendorupc, String rssku,
			String rslastupdusr, Timestamp rslastupddt, String rslastupdver,
			String rsloccde, String rsentitykey, String frdatabase)
			throws SQLException {

		boolean result = true;
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		String insertTableSQL;

		insertTableSQL = "INSERT INTO mn_vendor_upc (VENDOR_UPC,SKU,LAST_UPD_USR,LAST_UPD_DT,LAST_UPD_VER,LOC_CDE,ENTITY_KEY) "
				+ "VALUES ( ?,?,?,?,?,?,? )";

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

			preparedStatement.setString(1, rsvendorupc);
			preparedStatement.setString(2, rssku);
			preparedStatement.setString(3, rslastupdusr);
			preparedStatement.setTimestamp(4, rslastupddt);
			preparedStatement.setString(5, rslastupdver);
			preparedStatement.setString(6, rsloccde);
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

	public static boolean Mnvendorupcupdate(String rsvendorupc, String rssku,
			String rslastupdusr, Timestamp rslastupddt, String rslastupdver,
			String rsloccde, String rsentitykey, String frdatabase)
			throws SQLException {

		boolean result = true;
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		String updateSQL;

		updateSQL = "UPDATE MN_VENDOR_UPC "
                +"SET LAST_UPD_USR = ? "
                +", LAST_UPD_DT  = ? "
                +", LAST_UPD_VER = ? "
                +", ENTITY_KEY   = ? "
                +"WHERE VENDOR_UPC = ? "
                +"AND SKU          = ? "
                +"AND LOC_CDE      = ? ";

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

            preparedStatement.setString(1, rslastupdusr);
            preparedStatement.setTimestamp(2, rslastupddt);
            preparedStatement.setString(3, rslastupdver);
            preparedStatement.setString(4, rsentitykey);
            preparedStatement.setString(5, rsvendorupc);
            preparedStatement.setString(6, rssku);
            preparedStatement.setString(7, rsloccde);

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

	public static boolean Mnvendorupcchkexists(String entitykey,
			String frdatabase) throws SQLException {


        String[] parts = entitykey.split(",");
        String vendorupc = parts[0];
        String sku = parts[1];
        String loccde = parts[2];

        if (vendorupc.indexOf("'") != -1) {
            vendorupc = vendorupc.replaceAll("'", "''");
        }

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

			String selectSQL = "SELECT VENDOR_UPC " + "FROM MN_VENDOR_UPC "
                    + "where VENDOR_UPC ='"
                    + vendorupc
                    + "'"
                    + "and sku ='"
                    + sku
                    + "'"
                    + "and LOC_CDE ='"
                    + loccde
                    + "'";

			// logger.info(selectSQL);

			preparedStatement = dbConnection.prepareStatement(selectSQL);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				String rschktxdate = rs.getString("VENDOR_UPC");

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
