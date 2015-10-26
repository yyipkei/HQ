package com.bridge.routedeposit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Objects;

import com.bridge.result.Insertdataupdatelog;
import org.apache.log4j.Logger;

import com.bridge.insertdeposit.InsertPresellitem;
import com.bridge.main.HikariMerge;
import com.bridge.main.HikariQracleFrom;
import com.bridge.result.Logupdateresult;

public class Presellitem {

	private static final Logger logger = Logger.getLogger(Presellitem.class);

	public static void routePresellitem(String dataupdatelog,
			String entityname, String entitykey, String database)
			throws SQLException {

		String[] parts = entitykey.split(",");
		String sku = parts[0];
		String eventid = parts[1];
		String pono = parts[2];

		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		String selectSQL = "SELECT SKU,EVENT_ID,LAST_UPD_USR,LAST_UPD_DT,LAST_UPD_VER,PO_QTY,ORDER_QTY,PICK_QTY,PO_NO,ROWGUID "
				+ "FROM PRESELL_ITEM "
				+ "where SKU ='"
				+ sku
				+ "'"
				+ "and EVENT_ID ='"
				+ eventid
				+ "'"
				+ "and PO_NO='"
				+ pono
				+ "'"
				+"Order BY LAST_UPD_DT";

		// List<Presellitem> Presellitems = new ArrayList<Presellitem>();

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

				// Presellitem Presellitem = new Presellitem();

				String rssku = rs.getString("SKU");
				String rseventid = rs.getString("EVENT_ID");
				String rslastupdusr = rs.getString("LAST_UPD_USR");
				Timestamp rslastupddt = rs.getTimestamp("LAST_UPD_DT");
				String rslastupdver = rs.getString("LAST_UPD_VER");
				String rspoqty = rs.getString("PO_QTY");
				String rsorderqty = rs.getString("ORDER_QTY");
				String rspickqty = rs.getString("PICK_QTY");
				String rspono = rs.getString("PO_NO");
				String rsrowguid = rs.getString("ROWGUID");

				boolean Chkresult = InsertPresellitem.Presellitemchkexists(
						entitykey, database);

				// logger.info(Chkresult);

				if (!Chkresult) {

					// logger.info("insert");

					boolean Insertresult = InsertPresellitem.Preselliteminsert(
							rssku, rseventid, rslastupdusr, rslastupddt,
							rslastupdver, rspoqty, rsorderqty, rspickqty,
							rspono, rsrowguid, database);

					if (Insertresult) {
						logger.info("Presellitem: 1 row has been inserted. Key:"
								+ rssku + "," + rseventid + "," + rspono);
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

					boolean Insertresult = InsertPresellitem.Presellitemupdate(
							rssku, rseventid, rslastupdusr, rslastupddt,
							rslastupdver, rspoqty, rsorderqty, rspickqty,
							rspono, rsrowguid, database);

					if (Insertresult) {
						logger.info("Presellitem: 1 row has been updated. Key:"
								+ rssku + "," + rseventid + "," + rspono);
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
