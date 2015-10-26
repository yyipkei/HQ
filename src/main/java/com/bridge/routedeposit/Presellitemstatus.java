package com.bridge.routedeposit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Objects;

import com.bridge.result.Insertdataupdatelog;
import org.apache.log4j.Logger;

import com.bridge.insertdeposit.InsertPresellitemstatus;
import com.bridge.main.HikariMerge;
import com.bridge.main.HikariQracleFrom;
import com.bridge.result.Logupdateresult;

public class Presellitemstatus {

	private static final Logger logger = Logger
			.getLogger(Presellitemstatus.class);

	public static void routePresellitemstatus(String dataupdatelog,
			String entityname, String entitykey, String database)
			throws SQLException {

		String[] parts = entitykey.split(",");
		String txdate = parts[0];
		String loccode = parts[1];
		String regno = parts[2];
		String txno = parts[3];
		String seqno = parts[4];

		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		String selectSQL = "SELECT TX_DATE,LOC_CODE,REG_NO,TX_NO,SEQ_NO,EVENT_ID,SKU,ORDER_QTY,DP_NO,STATUS,PICK_UP_LOC,LAST_UPD_USR,LAST_UPD_DT,LAST_UPD_VER,ALLOCATE_QTY,PO_NO,CUR_URET,ROWGUID "
				+ "FROM presell_item_status "
				+ "where TX_DATE ='"
				+ txdate
				+ "'"
				+ "and LOC_CODE ='"
				+ loccode
				+ "'"
				+ "and REG_NO='"
				+ regno
				+ "'"
				+ "and TX_NO ='"
				+ txno
				+ "'"
				+ "and SEQ_NO ='"
				+ seqno + "'"
				+"Order BY LAST_UPD_DT";

		// List<Presellitemstatus> Presellitemstatuss = new
		// ArrayList<Presellitemstatus>();

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

				// Presellitemstatus Presellitemstatus = new
				// Presellitemstatus();

				String rstxdate = rs.getString("TX_DATE");
				String rsloccode = rs.getString("LOC_CODE");
				String rsregno = rs.getString("REG_NO");
				String rstxno = rs.getString("TX_NO");
				String rsseqno = rs.getString("SEQ_NO");
				String rseventid = rs.getString("EVENT_ID");
				String rssku = rs.getString("SKU");
				String rsorderqty = rs.getString("ORDER_QTY");
				String rsdpno = rs.getString("DP_NO");
				String rsstatus = rs.getString("STATUS");
				String rspickuploc = rs.getString("PICK_UP_LOC");
				String rslastupdusr = rs.getString("LAST_UPD_USR");
				Timestamp rslastupddt = rs.getTimestamp("LAST_UPD_DT");
				String rslastupdver = rs.getString("LAST_UPD_VER");
				String rsallocateqty = rs.getString("ALLOCATE_QTY");
				String rspono = rs.getString("PO_NO");
				String rscururet = rs.getString("CUR_URET");
				String rsrowguid = rs.getString("ROWGUID");

				boolean Chkresult = InsertPresellitemstatus
						.Presellitemstatuschkexists(entitykey, database);

				// logger.info(Chkresult);

				if (!Chkresult) {

					// logger.info("insert");

					boolean Insertresult = InsertPresellitemstatus
							.Presellitemstatusinsert(rstxdate, rsloccode,
									rsregno, rstxno, rsseqno, rseventid, rssku,
									rsorderqty, rsdpno, rsstatus, rspickuploc,
									rslastupdusr, rslastupddt, rslastupdver,
									rsallocateqty, rspono, rscururet,
									rsrowguid, database);

					if (Insertresult) {
						logger.info("Presellitemstatus: 1 row has been inserted. Key:"
								+ rstxdate
								+ ","
								+ rsloccode
								+ ","
								+ rsregno
								+ "," + rstxno + "," + rsseqno);
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

					boolean Insertresult = InsertPresellitemstatus
							.Presellitemstatusupdate(rstxdate, rsloccode,
									rsregno, rstxno, rsseqno, rseventid, rssku,
									rsorderqty, rsdpno, rsstatus, rspickuploc,
									rslastupdusr, rslastupddt, rslastupdver,
									rsallocateqty, rspono, rscururet,
									rsrowguid, database);

					if (Insertresult) {
						logger.info("Presellitemstatus: 1 row has been updated. Key:"
								+ rstxdate
								+ ","
								+ rsloccode
								+ ","
								+ rsregno
								+ "," + rstxno + "," + rsseqno);
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
