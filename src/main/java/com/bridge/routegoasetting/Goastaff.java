package com.bridge.routegoasetting;

import com.bridge.insertgoasetting.InsertGoastaff;
import com.bridge.main.HikariMssql;
import com.bridge.main.HikariQracleFrom;
import com.bridge.result.Insertdataupdatelog;
import com.bridge.result.Logupdateresult;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.Objects;

/**
 * Created by keyip on 01-Jun-15.
 */
public class Goastaff {

	private static final Logger logger = Logger.getLogger(Goastaff.class);

	public static void routeGoastaff(String dataupdatelog, String entityname,
			String entitykey, String database) throws SQLException {
		/*
		 * String[] parts = entitykey.split(","); String txdate = parts[0];
		 * String loccode = parts[1]; String regno = parts[2]; String txno =
		 * parts[3]; String seqno = parts[4];
		 */
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		String selectSQL;

		selectSQL = "SELECT STAFF_NAME,DEPT_CDE,EMAIL,PHONE,JOB_TITLE,ACTIVE,STAFF_ID,LAST_UPD_DT,LAST_UPD_USR,LAST_UPD_VER,ENTITY_KEY "
				+ "FROM GOA_STAFF " + "where entity_key ='" + entitykey + "'"
				+"Order BY LAST_UPD_DT";

		// List<Sahdr> sahdrs = new ArrayList<Sahdr>();

		try {
			if (Objects.equals(database, "Oracle")) {
				HikariQracleFrom OrcaleFrompool = HikariQracleFrom
						.getInstance();
				dbConnection = OrcaleFrompool.getConnection();
			} else {
				HikariMssql Mssqlpool = HikariMssql.getInstance();
				dbConnection = Mssqlpool.getConnection();
			}

			preparedStatement = dbConnection.prepareStatement(selectSQL);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {

				String rsstaffname = rs.getString("STAFF_NAME");
				String rsdeptcde = rs.getString("DEPT_CDE");
				String rsemail = rs.getString("EMAIL");
				String rsphone = rs.getString("PHONE");
				String rsjobtitle = rs.getString("JOB_TITLE");
				String rsactive = rs.getString("ACTIVE");
				String rsstaffid = rs.getString("STAFF_ID");
				Timestamp rslastupddt = rs.getTimestamp("LAST_UPD_DT");
				String rslastupdusr = rs.getString("LAST_UPD_USR");
				String rslastupdver = rs.getString("LAST_UPD_VER");
				String rsentitykey = rs.getString("ENTITY_KEY");

				// logger.info(rslastupddt);

				boolean Chkresult = InsertGoastaff.Goastaffchkexists(entitykey,
						database);

				if (!Chkresult) {

					// logger.info("insert");

					boolean Insertresult = InsertGoastaff.Goastaffinsert(
							rsstaffname, rsdeptcde, rsemail, rsphone,
							rsjobtitle, rsactive, rsstaffid, rslastupddt,
							rslastupdusr, rslastupdver, rsentitykey, database);

					if (Insertresult) {
						logger.info("Goastaff: 1 row has been inserted. Key:"
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

					boolean Insertresult = InsertGoastaff.Goastaffupdate(
							rsstaffname, rsdeptcde, rsemail, rsphone,
							rsjobtitle, rsactive, rsstaffid, rslastupddt,
							rslastupdusr, rslastupdver, rsentitykey, database);

					if (Insertresult) {
						logger.info("Goastaff: 1 row has been updated. Key:"
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
