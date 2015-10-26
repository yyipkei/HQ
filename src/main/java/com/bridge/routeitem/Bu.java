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

import com.bridge.insertitem.InsertBu;
import com.bridge.main.HikariMssql;
import com.bridge.main.HikariQracleFrom;
import com.bridge.result.Logupdateresult;

public class Bu {

	private static final Logger logger = Logger.getLogger(Bu.class);

	public static void routeBu(String dataupdatelog, String entityname,
			String entitykey, String database) throws SQLException {
		/*
		 * String[] parts = entitykey.split(","); String txdate = parts[0];
		 * String loccode = parts[1]; String regno = parts[2]; String txno =
		 * parts[3]; String seqno = parts[4];
		 */
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		String selectSQL;

		selectSQL = "SELECT BU_CODE,BU_DESC,DIV_CODE,LAST_UPD_DT,ENTITY_KEY "
				+ "FROM Bu " + "where BU_CODE ='" + entitykey + "'"
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

				String rsbucode = rs.getString("BU_CODE");
				String rsbudesc = rs.getString("BU_DESC");
				String rsdivcode = rs.getString("DIV_CODE");
				Timestamp rslastupddt = rs.getTimestamp("LAST_UPD_DT");
				String rsentitykey = rs.getString("ENTITY_KEY");

				// logger.info(rslastupddt);

				boolean Chkresult = InsertBu.Buchkexists(entitykey, database);

				if (!Chkresult) {

					// logger.info("insert");

					boolean Insertresult = InsertBu.Buinsert(rsbucode,
							rsbudesc, rsdivcode, rslastupddt, rsentitykey,
							database);

					if (Insertresult) {
						logger.info("Bu: 1 row has been inserted. Key:"
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

					boolean Insertresult = InsertBu.Buupdate(rsbucode,
							rsbudesc, rsdivcode, rslastupddt, rsentitykey,
							database);

					if (Insertresult) {
						logger.info("Bu: 1 row has been updated. Key:"
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
