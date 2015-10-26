package com.bridge.routemerge;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Objects;

import com.bridge.result.Insertdataupdatelog;
import org.apache.log4j.Logger;

import com.bridge.insertmerge.InsertSagoatxn;
import com.bridge.main.HikariMerge;
import com.bridge.main.HikariQracleFrom;
import com.bridge.result.Logupdateresult;

public class Sagoatxn {

	private static final Logger logger = Logger.getLogger(Sagoatxn.class);

	public static void routeSagoatxn(String dataupdatelog, String entityname,
			String entitykey, String database) throws SQLException {

		String[] parts = entitykey.split(",");
		String txdate = parts[0];
		String loccode = parts[1];
		String regno = parts[2];
		String txno = parts[3];
		String seqno = parts[4];

		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		String selectSQL = "SELECT TX_DATE,LOC_CODE,REG_NO,TX_NO,SEQ_NO,TX_TYPE,PURPOSE_CDE,STATUS,VOID,SKU,QTY,CUR_URET,STAFF_ID,ROWGUID,LAST_UPD_DT "
				+ "from SAGOA_TXN "
				+ "where tx_date ='"
				+ txdate
				+ "'"
				+ "and LOC_CODE ='"
				+ loccode
				+ "'"
				+ "and reg_no='"
				+ regno
				+ "'"
				+ "and tx_no ='"
				+ txno
				+ "'"
				+ "and seq_no ='"
				+ seqno
				+ "'"
				+"Order BY LAST_UPD_DT";

		// List<Sagoatxn> Sagoatxns = new ArrayList<Sagoatxn>();

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

				// Sagoatxn Sagoatxn = new Sagoatxn();

				String rstxdate = rs.getString("TX_DATE");
				String rsloccode = rs.getString("LOC_CODE");
				String rsregno = rs.getString("REG_NO");
				String rstxno = rs.getString("TX_NO");
				String rsseqno = rs.getString("SEQ_NO");
				String rstxtype = rs.getString("TX_TYPE");
				String rspurposecde = rs.getString("PURPOSE_CDE");
				String rsstatus = rs.getString("STATUS");
				String rsvoid = rs.getString("VOID");
				String rssku = rs.getString("SKU");
				String rsqty = rs.getString("QTY");
				String rscururet = rs.getString("CUR_URET");
				String rsstaffid = rs.getString("STAFF_ID");
				String rsrowguid = rs.getString("ROWGUID");
				Timestamp rslastupddt = rs.getTimestamp("LAST_UPD_DT");

				boolean Chkresult = InsertSagoatxn.Sagoatxnchkexists(entitykey,
						database);

				// logger.info(Chkresult);

				if (!Chkresult) {

					// logger.info("insert");

					boolean Insertresult = InsertSagoatxn.Sagoatxninsert(
							rstxdate, rsloccode, rsregno, rstxno, rsseqno,
							rstxtype, rspurposecde, rsstatus, rsvoid, rssku,
							rsqty, rscururet, rsstaffid, rsrowguid,
							rslastupddt, database);

					if (Insertresult) {
						logger.info("Sagoatxn: 1 row has been inserted. Key:"
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

					boolean Insertresult = InsertSagoatxn.Sagoatxnupdate(
							rstxdate, rsloccode, rsregno, rstxno, rsseqno,
							rstxtype, rspurposecde, rsstatus, rsvoid, rssku,
							rsqty, rscururet, rsstaffid, rsrowguid,
							rslastupddt, database);

					if (Insertresult) {
						logger.info("Sagoatxn: 1 row has been updated. Key:"
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
