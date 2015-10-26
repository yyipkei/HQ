package com.bridge.routemerge;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Objects;

import com.bridge.result.Insertdataupdatelog;
import org.apache.log4j.Logger;

import com.bridge.insertmerge.InsertReason;
import com.bridge.main.HikariMerge;
import com.bridge.main.HikariQracleFrom;
import com.bridge.result.Logupdateresult;

public class Reason {

	private static final Logger logger = Logger.getLogger(Reason.class);

	public static void routeReason(String dataupdatelog, String entityname,
			String entitykey, String database) throws SQLException {

		String[] parts = entitykey.split(",");
		String reasongroup = parts[0];
		String reason = parts[1];

		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		String selectSQL = "SELECT REASON_GROUP,REASON,REASON_DESC,REASON_DISP,EFF_FR_DATE,EFF_TO_DATE,VOID,ROWGUID,LAST_UPD_DT "
				+ "from REASON "
				+ "where REASON_GROUP ='"
				+ reasongroup
				+ "'"
				+ "and REASON ='" + reason + "'"
				+"Order BY LAST_UPD_DT";

		// List<Reason> Reasons = new ArrayList<Reason>();

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

				// Reason Reason = new Reason();

				String rsreasongroup = rs.getString("REASON_GROUP");
				String rsreason = rs.getString("REASON");
				String rsreasondesc = rs.getString("REASON_DESC");
				String rsreasondisp = rs.getString("REASON_DISP");
				String rsefffrdate = rs.getString("EFF_FR_DATE");
				String rsefftodate = rs.getString("EFF_TO_DATE");
				String rsvoid = rs.getString("VOID");
				String rsrowguid = rs.getString("ROWGUID");
				Timestamp rslastupddt = rs.getTimestamp("LAST_UPD_DT");

				boolean Chkresult = InsertReason.Reasonchkexists(entitykey,
						database);

				// logger.info(Chkresult);

				if (!Chkresult) {

					// logger.info("insert");

					boolean Insertresult = InsertReason.Reasoninsert(
							rsreasongroup, rsreason, rsreasondesc,
							rsreasondisp, rsefffrdate, rsefftodate, rsvoid,
							rsrowguid, rslastupddt, database);

					if (Insertresult) {
						logger.info("Reason: 1 row has been inserted. Key:"
								+ reasongroup + "," + reason);
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

					boolean Insertresult = InsertReason.Reasonupdate(
							rsreasongroup, rsreason, rsreasondesc,
							rsreasondisp, rsefffrdate, rsefftodate, rsvoid,
							rsrowguid, rslastupddt, database);

					if (Insertresult) {
						logger.info("Reason: 1 row has been updated. Key:"
								+ reasongroup + "," + reason);
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
