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

import com.bridge.insertitem.InsertSize;
import com.bridge.main.HikariMssql;
import com.bridge.main.HikariQracleFrom;
import com.bridge.result.Logupdateresult;

public class Size {

	private static final Logger logger = Logger.getLogger(Size.class);

	public static void routeSize(String dataupdatelog, String entityname,
								 String entitykey, String database) throws SQLException {
		/*
		 * String[] parts = entitykey.split(","); String txdate = parts[0];
		 * String loccode = parts[1]; String regno = parts[2]; String txno =
		 * parts[3]; String seqno = parts[4];
		 */

		String[] parts = entitykey.split(",");
		String sizeset = parts[0];
		String sizecode = parts[1];

		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		String selectSQL;

		// List<Sahdr> sahdrs = new ArrayList<Sahdr>();

		try {
			if (Objects.equals(database, "Oracle")) {
				// dbConnection = OracleFrom.getDBConnection();

				HikariQracleFrom OrcaleFrompool = HikariQracleFrom
						.getInstance();
				dbConnection = OrcaleFrompool.getConnection();

				selectSQL = "SELECT SIZE_SET,SIZE_CODE,SIZE_DESC,LAST_UPD_DT,ENTITY_KEY "
						+ "FROM SIZE_ "
						+ "where SIZE_SET ='"
						+ sizeset
						+ "'"
						+ "and SIZE_CODE ='"
						+ sizecode
						+ "'"
						+"Order BY LAST_UPD_DT";
			} else {
				// dbConnection = Mssql.getDBConnection();

				HikariRms Rmspool = HikariRms.getInstance();
				dbConnection = Rmspool.getConnection();

				selectSQL = "SELECT SIZE_SET,SIZE_CODE,SIZE_DESC,LAST_UPD_DT,ENTITY_KEY "
						+ "FROM Size "
						+ "where SIZE_SET ='"
						+ sizeset
						+ "'"
						+ "and SIZE_CODE ='"
						+ sizecode
						+ "'"
						+"Order BY LAST_UPD_DT";
			}

			preparedStatement = dbConnection.prepareStatement(selectSQL);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {

				String rssizeset = rs.getString("SIZE_SET");
				String rssizecode = rs.getString("SIZE_CODE");
				String rssizedesc = rs.getString("SIZE_DESC");
				Timestamp rslastupddt = rs.getTimestamp("LAST_UPD_DT");
				String rsentitykey = rs.getString("ENTITY_KEY");

				// logger.info(rslastupddt);

				boolean Chkresult = InsertSize.Sizechkexists(entitykey,
						database);

				if (!Chkresult) {

					// logger.info("insert");

					boolean Insertresult = InsertSize.Sizeinsert(rssizeset,
							rssizecode, rssizedesc, rslastupddt, rsentitykey,
							database);

					if (Insertresult) {
						logger.info("Size: 1 row has been inserted. Key:"
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

					boolean Insertresult = InsertSize.Sizeupdate(rssizeset,
							rssizecode, rssizedesc, rslastupddt, rsentitykey,
							database);

					if (Insertresult) {
						logger.info("Size: 1 row has been updated. Key:"
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
