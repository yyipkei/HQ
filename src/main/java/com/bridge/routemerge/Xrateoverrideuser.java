package com.bridge.routemerge;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Objects;

import com.bridge.result.Insertdataupdatelog;
import org.apache.log4j.Logger;

import com.bridge.insertmerge.InsertXrateoverrideuser;
import com.bridge.main.HikariMerge;
import com.bridge.main.HikariQracleFrom;
import com.bridge.result.Logupdateresult;

public class Xrateoverrideuser {

	private static final Logger logger = Logger
			.getLogger(Xrateoverrideuser.class);

	public static void routeXrateoverrideuser(String dataupdatelog,
			String entityname, String entitykey, String database)
			throws SQLException {

		String[] parts = entitykey.split(",");
		String userid = parts[0];

		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		String selectSQL = "SELECT USER_ID,OVERRIDE_VARIANCE,OVERRIDE_ADJ_ROUNDING,ROWGUID,LAST_UPD_DT "
				+ "from XRATE_OVERRIDE_USER "
				+ "where USER_ID ='"
				+ userid
				+ "'"
				+"Order BY LAST_UPD_DT";

		// List<Xrateoverrideuser> Xrateoverrideusers = new
		// ArrayList<Xrateoverrideuser>();

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

				// Xrateoverrideuser Xrateoverrideuser = new
				// Xrateoverrideuser();

				String rsuserid = rs.getString("USER_ID");
				String rsoverridevariance = rs.getString("OVERRIDE_VARIANCE");
				String rsoverrideadjrounding = rs
						.getString("OVERRIDE_ADJ_ROUNDING");
				String rsrowguid = rs.getString("ROWGUID");
				Timestamp rslastupddt = rs.getTimestamp("LAST_UPD_DT");

				boolean Chkresult = InsertXrateoverrideuser
						.Xrateoverrideuserchkexists(entitykey, database);

				// logger.info(Chkresult);

				if (!Chkresult) {

					// logger.info("insert");

					boolean Insertresult = InsertXrateoverrideuser
							.Xrateoverrideuserinsert(rsuserid,
									rsoverridevariance, rsoverrideadjrounding,
									rsrowguid, rslastupddt, database);

					if (Insertresult) {
						logger.info("Xrateoverrideuser: 1 row has been inserted. Key:"
								+ userid);
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

					boolean Insertresult = InsertXrateoverrideuser
							.Xrateoverrideuserupdate(rsuserid,
									rsoverridevariance, rsoverrideadjrounding,
									rsrowguid, rslastupddt, database);

					if (Insertresult) {
						logger.info("Xrateoverrideuser: 1 row has been updated. Key:"
								+ userid);
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
