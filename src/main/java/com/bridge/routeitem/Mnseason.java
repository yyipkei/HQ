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

import com.bridge.insertitem.InsertMnseason;
import com.bridge.main.HikariMssql;
import com.bridge.main.HikariQracleFrom;
import com.bridge.result.Logupdateresult;

public class Mnseason {

	private static final Logger logger = Logger.getLogger(Mnseason.class);

	public static void routeMnseason(String dataupdatelog, String entityname,
			String entitykey, String database) throws SQLException {
		/*
		 * String[] parts = entitykey.split(","); String txdate = parts[0];
		 * String loccode = parts[1]; String regno = parts[2]; String txno =
		 * parts[3]; String seqno = parts[4];
		 */
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		String selectSQL;

		selectSQL = "SELECT SEASON_CDE,SEASON_DESC,SEASON_YEAR,SEASON_SEQ,SEASON_TYPE,"
				+ "SEASON_SUB_TYPE,SEASON_START_DATE,SEASON_END_DATE,LAST_UPD_USR,LAST_UPD_DT,"
				+ "LAST_UPD_VER,ROWGUID,ENTITY_KEY "
				+ "FROM rmsadmin.MN_SEASON "
				+ "where entity_key ='" + entitykey + "'"
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

				String rsseasoncde = rs.getString("SEASON_CDE");
				String rsseasondesc = rs.getString("SEASON_DESC");
				String rsseasonyear = rs.getString("SEASON_YEAR");
				String rsseasonseq = rs.getString("SEASON_SEQ");
				String rsseasontype = rs.getString("SEASON_TYPE");
				String rsseasonsubtype = rs.getString("SEASON_SUB_TYPE");
				Timestamp rsseasonstartdate = rs.getTimestamp("SEASON_START_DATE");
				Timestamp rsseasonenddate = rs.getTimestamp("SEASON_END_DATE");
				String rslastupdusr = rs.getString("LAST_UPD_USR");
				Timestamp rslastupddt = rs.getTimestamp("LAST_UPD_DT");
				String rslastupdver = rs.getString("LAST_UPD_VER");
				String rsrowguid = rs.getString("ROWGUID");
				String rsentitykey = rs.getString("ENTITY_KEY");

				// logger.info(rslastupddt);

				boolean Chkresult = InsertMnseason.Mnseasonchkexists(entitykey,
						database);

				if (!Chkresult) {

					// logger.info("insert");

					boolean Insertresult = InsertMnseason.Mnseasoninsert(
							rsseasoncde, rsseasondesc, rsseasonyear,
							rsseasonseq, rsseasontype, rsseasonsubtype,
							rsseasonstartdate, rsseasonenddate, rslastupdusr,
							rslastupddt, rslastupdver, rsrowguid, rsentitykey,
							database);

					if (Insertresult) {
						logger.info("Mnseason: 1 row has been inserted. Key:"
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

					boolean Insertresult = InsertMnseason.Mnseasonupdate(
							rsseasoncde, rsseasondesc, rsseasonyear,
							rsseasonseq, rsseasontype, rsseasonsubtype,
							rsseasonstartdate, rsseasonenddate, rslastupdusr,
							rslastupddt, rslastupdver, rsrowguid, rsentitykey,
							database);

					if (Insertresult) {
						logger.info("Mnseason: 1 row has been updated. Key:"
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
