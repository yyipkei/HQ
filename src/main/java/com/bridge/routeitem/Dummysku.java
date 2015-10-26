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

import com.bridge.insertitem.InsertDummysku;
import com.bridge.main.HikariMssql;
import com.bridge.main.HikariQracleFrom;
import com.bridge.result.Logupdateresult;

public class Dummysku {
	private static final Logger logger = Logger.getLogger(Dummysku.class);

	public static void routeDummysku(String dataupdatelog, String entityname,
			String entitykey, String database) throws SQLException {
		/*
		 * String[] parts = entitykey.split(","); String txdate = parts[0];
		 * String loccode = parts[1]; String regno = parts[2]; String txno =
		 * parts[3]; String seqno = parts[4];
		 */
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		String selectSQL;

		selectSQL = "SELECT CLASS_CODE,DEPT_CODE,SKU,DESC1,LAST_UPD_DT,ENTITY_KEY "
				+ "FROM dummy_sku " + "where entity_key ='" + entitykey + "'"
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

				String rsclasscode = rs.getString("CLASS_CODE");
				String rsdeptcode = rs.getString("DEPT_CODE");
				String rssku = rs.getString("SKU");
				String rsdesc1 = rs.getString("DESC1");
				Timestamp rslastupddt = rs.getTimestamp("LAST_UPD_DT");
				String rsentitykey = rs.getString("ENTITY_KEY");

				// logger.info(rslastupddt);

				boolean Chkresult = InsertDummysku.Dummyskuchkexists(entitykey,
						database);

				if (!Chkresult) {

					// logger.info("insert");

					boolean Insertresult = InsertDummysku.Dummyskuinsert(
							rsclasscode, rsdeptcode, rssku, rsdesc1,
							rslastupddt, rsentitykey, database);

					if (Insertresult) {
						logger.info("Dummysku: 1 row has been inserted. Key:"
								+ entitykey);
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

					boolean Insertresult = InsertDummysku.Dummyskuupdate(
							rsclasscode, rsdeptcode, rssku, rsdesc1,
							rslastupddt, rsentitykey, database);

					if (Insertresult) {
						logger.info("Dummysku: 1 row has been updated. Key:"
								+ entitykey);
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
