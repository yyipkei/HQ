package com.bridge.routeitem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Objects;

import com.bridge.main.HikariRms;
import com.bridge.result.Insertdataupdatelog;
import org.apache.log4j.Logger;

import com.bridge.insertitem.InsertMnvendorupc;
import com.bridge.main.HikariMssql;
import com.bridge.main.HikariQracleFrom;
import com.bridge.result.Logupdateresult;

public class Mnvendorupc {

	private static final Logger logger = Logger.getLogger(Mnvendorupc.class);

	public static void routeMnvendorupc(String dataupdatelog,
			String entityname, String entitykey, String database)
			throws SQLException {

		  String[] parts = entitykey.split(",");
          String vendorupc = parts[0];
		  String sku = parts[1];
          String loccde = parts[2];

		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		String selectSQL;

        if (vendorupc.indexOf("'") != -1) {
            vendorupc = vendorupc.replaceAll("'", "''");
        }

        selectSQL = "SELECT VENDOR_UPC,SKU,LAST_UPD_USR,LAST_UPD_DT,LAST_UPD_VER,LOC_CDE,ENTITY_KEY "
				+ "FROM rmsadmin.mn_vendor_upc "
				+ "where VENDOR_UPC ='"
				+ vendorupc
				+ "'"
                + "and sku ='"
                + sku
                + "'"
                + "and LOC_CDE ='"
                + loccde
                + "'"
				+"Order BY LAST_UPD_DT";

		// List<Sahdr> sahdrs = new ArrayList<Sahdr>();

		try {
			if (Objects.equals(database, "Oracle")) {
				// dbConnection = OracleFrom.getDBConnection();

				HikariQracleFrom OrcaleFrompool = HikariQracleFrom
						.getInstance();
				dbConnection = OrcaleFrompool.getConnection();
			} else {
				// dbConnection = Mssql.getDBConnection();

				HikariRms Rmspool = HikariRms.getInstance();
				dbConnection = Rmspool.getConnection();
			}
			
			preparedStatement = dbConnection.prepareStatement(selectSQL);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {

				String rsvendorupc = rs.getString("VENDOR_UPC");
				String rssku = rs.getString("SKU");
				String rslastupdusr = rs.getString("LAST_UPD_USR");
				Timestamp rslastupddt = rs.getTimestamp("LAST_UPD_DT");
				String rslastupdver = rs.getString("LAST_UPD_VER");
				String rsloccde = rs.getString("LOC_CDE");
				String rsentitykey = rs.getString("ENTITY_KEY");

				// logger.info(rslastupddt);

				boolean Chkresult = InsertMnvendorupc.Mnvendorupcchkexists(
						entitykey, database);

				if (!Chkresult) {

					// logger.info("insert");

					boolean Insertresult = InsertMnvendorupc.Mnvendorupcinsert(
							rsvendorupc, rssku, rslastupdusr, rslastupddt,
							rslastupdver, rsloccde, rsentitykey, database);

					if (Insertresult) {
						logger.info("Mnvendorupc: 1 row has been inserted. Key:"
								+ vendorupc +"," + sku + ","
                                + loccde);
					} else {
						logger.info("Insert Error");
					}
					Logupdateresult.Updatelogresult(dataupdatelog, entityname,
							Insertresult, database);

					if ((!"Oracle".equals(database)) && (Insertresult)) {
						Insertdataupdatelog.Updatelogresult(entityname, rsentitykey);
					}

				} else {

					// logger.info("update");

					boolean Insertresult = InsertMnvendorupc.Mnvendorupcupdate(
							rsvendorupc, rssku, rslastupdusr, rslastupddt,
							rslastupdver, rsloccde, rsentitykey, database);

					if (Insertresult) {
						logger.info("Mnvendorupc: 1 row has been updated. Key:"
                                + vendorupc +"," + sku + ","
                                + loccde);
					} else {
						logger.info("Update Error");
					}
					Logupdateresult.Updatelogresult(dataupdatelog, entityname,
							Insertresult, database);

					if ((!"Oracle".equals(database)) && (Insertresult)) {
						Insertdataupdatelog.Updatelogresult(entityname, rsentitykey);
					}
				}
			}
		} catch (SQLException e) {

			logger.info(e.getMessage());

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
