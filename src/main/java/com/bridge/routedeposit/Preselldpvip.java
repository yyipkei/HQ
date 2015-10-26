package com.bridge.routedeposit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Objects;

import com.bridge.result.Insertdataupdatelog;
import org.apache.log4j.Logger;

import com.bridge.insertdeposit.InsertPreselldpvip;
import com.bridge.main.HikariMerge;
import com.bridge.main.HikariQracleFrom;
import com.bridge.result.Logupdateresult;

public class Preselldpvip {

	private static final Logger logger = Logger.getLogger(Preselldpvip.class);

	public static void routePreselldpvip(String dataupdatelog,
			String entityname, String entitykey, String database)
			throws SQLException {

		String[] parts = entitykey.split(",");
		String txdate = parts[0];
		String dpno = parts[1];

		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		String selectSQL = "SELECT LOC_CODE,DP_NO,VIP_NO,EVENT_ID,CONTACT_PHONE_NO,LAST_UPD_USR,LAST_UPD_DT,LAST_UPD_VER,PICK_UP_LOC,ROWGUID "
				+ "FROM PRESELL_DP_VIP "
				+ "where LOC_CODE ='"
				+ txdate
				+ "'"
				+ "and DP_NO ='" + dpno + "'"
				+"Order BY LAST_UPD_DT";

		// List<Preselldpvip> Preselldpvips = new ArrayList<Preselldpvip>();

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

				// Preselldpvip Preselldpvip = new Preselldpvip();

				String rsloccode = rs.getString("LOC_CODE");
				String rsdpno = rs.getString("DP_NO");
				String rsvipno = rs.getString("VIP_NO");
				String rseventid = rs.getString("EVENT_ID");
				String rscontactphoneno = rs.getString("CONTACT_PHONE_NO");
				String rslastupdusr = rs.getString("LAST_UPD_USR");
				Timestamp rslastupddt = rs.getTimestamp("LAST_UPD_DT");
				String rslastupdver = rs.getString("LAST_UPD_VER");
				String rspickuploc = rs.getString("PICK_UP_LOC");
				String rsrowguid = rs.getString("ROWGUID");

				boolean Chkresult = InsertPreselldpvip.Preselldpvipchkexists(
						entitykey, database);


				// logger.info(Chkresult);

				if (!Chkresult) {

					// logger.info("insert");

					boolean Insertresult = InsertPreselldpvip
							.Preselldpvipinsert(rsloccode, rsdpno, rsvipno,
									rseventid, rscontactphoneno, rslastupdusr,
									rslastupddt, rslastupdver, rspickuploc,
									rsrowguid, database);

					if (Insertresult) {
						logger.info("Preselldpvip: 1 row has been inserted. Key:"
								+ rsloccode + "," + rsdpno);
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

					boolean Insertresult = InsertPreselldpvip
							.Preselldpvipupdate(rsloccode, rsdpno, rsvipno,
									rseventid, rscontactphoneno, rslastupdusr,
									rslastupddt, rslastupdver, rspickuploc,
									rsrowguid, database);

					if (Insertresult) {
						logger.info("Preselldpvip: 1 row has been updated. Key:"
								+ rsloccode + "," + rsdpno);
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
