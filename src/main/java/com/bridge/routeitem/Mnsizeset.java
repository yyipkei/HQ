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

import com.bridge.insertitem.InsertMnsizeset;
import com.bridge.main.HikariMssql;
import com.bridge.main.HikariQracleFrom;
import com.bridge.result.Logupdateresult;

public class Mnsizeset {

	private static final Logger logger = Logger.getLogger(Mnsizeset.class);

	public static void routeMnsizeset(String dataupdatelog, String entityname,
			String entitykey, String database) throws SQLException {
		/*
		 * String[] parts = entitykey.split(","); String txdate = parts[0];
		 * String loccode = parts[1]; String regno = parts[2]; String txno =
		 * parts[3]; String seqno = parts[4];
		 */
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		String selectSQL;

		selectSQL = "SELECT SIZE_SET,SIZE_CDE1,SIZE_CDE2,SIZE_CDE3,SIZE_CDE4,"
				+ "SIZE_CDE5,SIZE_CDE6,SIZE_CDE7,SIZE_CDE8,SIZE_CDE9,SIZE_CDE10,"
				+ "SIZE_CDE11,SIZE_CDE12,SIZE_CDE13,SIZE_CDE14,SIZE_CDE15,SIZE_CDE16,"
				+ "SIZE_CDE17,SIZE_CDE18,SIZE_CDE19,SIZE_CDE20,LAST_UPD_USR,LAST_UPD_DT,"
				+ "LAST_UPD_VER,ROWGUID,ENABLE_STATUS,ENTITY_KEY "
				+ "FROM rmsadmin.MN_SIZE_SET " + "where entity_key ='" + entitykey + "'"
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

				String rssizeset = rs.getString("SIZE_SET");
				String rssizecde1 = rs.getString("SIZE_CDE1");
				String rssizecde2 = rs.getString("SIZE_CDE2");
				String rssizecde3 = rs.getString("SIZE_CDE3");
				String rssizecde4 = rs.getString("SIZE_CDE4");
				String rssizecde5 = rs.getString("SIZE_CDE5");
				String rssizecde6 = rs.getString("SIZE_CDE6");
				String rssizecde7 = rs.getString("SIZE_CDE7");
				String rssizecde8 = rs.getString("SIZE_CDE8");
				String rssizecde9 = rs.getString("SIZE_CDE9");
				String rssizecde10 = rs.getString("SIZE_CDE10");
				String rssizecde11 = rs.getString("SIZE_CDE11");
				String rssizecde12 = rs.getString("SIZE_CDE12");
				String rssizecde13 = rs.getString("SIZE_CDE13");
				String rssizecde14 = rs.getString("SIZE_CDE14");
				String rssizecde15 = rs.getString("SIZE_CDE15");
				String rssizecde16 = rs.getString("SIZE_CDE16");
				String rssizecde17 = rs.getString("SIZE_CDE17");
				String rssizecde18 = rs.getString("SIZE_CDE18");
				String rssizecde19 = rs.getString("SIZE_CDE19");
				String rssizecde20 = rs.getString("SIZE_CDE20");
				String rslastupdusr = rs.getString("LAST_UPD_USR");
				Timestamp rslastupddt = rs.getTimestamp("LAST_UPD_DT");
				String rslastupdver = rs.getString("LAST_UPD_VER");
				String rsrowguid = rs.getString("ROWGUID");
				String rsenablestatus = rs.getString("ENABLE_STATUS");
				String rsentitykey = rs.getString("ENTITY_KEY");

				// logger.info(rslastupddt);

				boolean Chkresult = InsertMnsizeset.Mnsizesetchkexists(
						entitykey, database);

				if (!Chkresult) {

					// logger.info("insert");

					boolean Insertresult = InsertMnsizeset.Mnsizesetinsert(
							rssizeset, rssizecde1, rssizecde2, rssizecde3,
							rssizecde4, rssizecde5, rssizecde6, rssizecde7,
							rssizecde8, rssizecde9, rssizecde10, rssizecde11,
							rssizecde12, rssizecde13, rssizecde14, rssizecde15,
							rssizecde16, rssizecde17, rssizecde18, rssizecde19,
							rssizecde20, rslastupdusr, rslastupddt,
							rslastupdver, rsrowguid, rsenablestatus,
							rsentitykey, database);

					if (Insertresult) {
						logger.info("Mnsizeset: 1 row has been inserted. Key:"
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

					boolean Insertresult = InsertMnsizeset.Mnsizesetupdate(
							rssizeset, rssizecde1, rssizecde2, rssizecde3,
							rssizecde4, rssizecde5, rssizecde6, rssizecde7,
							rssizecde8, rssizecde9, rssizecde10, rssizecde11,
							rssizecde12, rssizecde13, rssizecde14, rssizecde15,
							rssizecde16, rssizecde17, rssizecde18, rssizecde19,
							rssizecde20, rslastupdusr, rslastupddt,
							rslastupdver, rsrowguid, rsenablestatus,
							rsentitykey, database);

					if (Insertresult) {
						logger.info("Mnsizeset: 1 row has been updated. Key:"
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
