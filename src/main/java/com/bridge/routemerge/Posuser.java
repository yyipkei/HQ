package com.bridge.routemerge;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Objects;

import com.bridge.result.Insertdataupdatelog;
import org.apache.log4j.Logger;

import com.bridge.insertmerge.InsertPosuser;
import com.bridge.main.HikariMerge;
import com.bridge.main.HikariQracleFrom;
import com.bridge.result.Logupdateresult;

public class Posuser {

	private static final Logger logger = Logger.getLogger(Posuser.class);

	public static void routePosuser(String dataupdatelog, String entityname,
			String entitykey, String database) throws SQLException {

		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		String selectSQL = "SELECT USER_ID,PASSWORD,NAME,GROUP_ID,LOC_CODE,RES_PERIOD,ACTIVE,ROWGUID,LAST_UPD_DT "
				+ "from pos_user " + "where USER_ID ='" + entitykey + "'"
				+"Order BY LAST_UPD_DT";

		// List<Posuser> Posusers = new ArrayList<Posuser>();

		try {
			if (Objects.equals(database, "Oracle")) {
				HikariQracleFrom OrcaleFrompool = HikariQracleFrom
						.getInstance();
				dbConnection = OrcaleFrompool.getConnection();
			} else {
				HikariMerge Mergepool = HikariMerge.getInstance();
				dbConnection = Mergepool.getConnection();
			}

			preparedStatement = dbConnection.prepareStatement(selectSQL);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {

				// Posuser Posuser = new Posuser();

				String rsuserid = rs.getString("USER_ID");
				String rspassword = rs.getString("PASSWORD");
				String rsname = rs.getString("NAME");
				String rsgroupid = rs.getString("GROUP_ID");
				String rsloccode = rs.getString("LOC_CODE");
				String rsresperiod = rs.getString("RES_PERIOD");
				String rsactive = rs.getString("ACTIVE");
				String rsrowguid = rs.getString("ROWGUID");
				Timestamp rslastupddt = rs.getTimestamp("LAST_UPD_DT");

				boolean Chkresult = InsertPosuser.Posuserchkexists(entitykey,
						database);

				// logger.info(Chkresult);

				if (!Chkresult) {

					// logger.info("insert");

					boolean Insertresult = InsertPosuser.Posuserinsert(
							rsuserid, rspassword, rsname, rsgroupid, rsloccode,
							rsresperiod, rsactive, rsrowguid, rslastupddt,
							database);

					if (Insertresult) {
						logger.info("Posuser: 1 row has been inserted. Key:"
								+ entitykey);
					} else {
						logger.info("Insert Error");
					}
					Logupdateresult.Updatelogresult(dataupdatelog, entityname,
							Insertresult, database);

					/*if ((!"Oracle".equals(database)) && (Insertresult)) {
						Insertdataupdatelog.Updatelogresult(entityname, entitykey);
					}*/

				} else {

					// logger.info("update");

					boolean Insertresult = InsertPosuser.Posuserupdate(
							rsuserid, rspassword, rsname, rsgroupid, rsloccode,
							rsresperiod, rsactive, rsrowguid, rslastupddt,
							database);

					if (Insertresult) {
						logger.info("Posuser: 1 row has been updated. Key:"
								+ entitykey);
					} else {
						logger.info("Update Error");
					}
					Logupdateresult.Updatelogresult(dataupdatelog, entityname,
							Insertresult, database);

					/*if ((!"Oracle".equals(database)) && (Insertresult)) {
						Insertdataupdatelog.Updatelogresult(entityname, entitykey);
					}*/
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
