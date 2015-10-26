package com.bridge.routemerge;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Objects;

import com.bridge.result.Insertdataupdatelog;
import org.apache.log4j.Logger;

import com.bridge.insertmerge.InsertSastafftxn;
import com.bridge.main.HikariMerge;
import com.bridge.main.HikariQracleFrom;
import com.bridge.result.Logupdateresult;

public class Sastafftxn {

	private static final Logger logger = Logger.getLogger(Sastafftxn.class);

	public static void routeSastafftxn(String dataupdatelog, String entityname,
			String entitykey, String database) throws SQLException {

		String[] parts = entitykey.split(",");
		String txdate = parts[0];
		String loccode = parts[1];
		String regno = parts[2];
		String txno = parts[3];
		String seqno = parts[4];

		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		String selectSQL = "SELECT ROWGUID,TX_DATE,LOC_CODE,REG_NO,TX_NO,SEQ_NO,TX_TYPE,VOID,"
				+ "TENDER,AMOUNT,FOREIGN_AMT,STAFF_ID,STAFF_LOC_CODE,QTY,LAST_UPD_DT "
				+ "from SASTAFFTXN "
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


		// List<Sastafftxn> Sastafftxns = new ArrayList<Sastafftxn>();

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

				// Sastafftxn Sastafftxn = new Sastafftxn();

				String rsrowguid = rs.getString("ROWGUID");
				String rstxdate = rs.getString("TX_DATE");
				String rsloccode = rs.getString("LOC_CODE");
				String rsregno = rs.getString("REG_NO");
				String rstxno = rs.getString("TX_NO");
				String rsseqno = rs.getString("SEQ_NO");
				String rstxtype = rs.getString("TX_TYPE");
				String rsvoid = rs.getString("VOID");
				String rstender = rs.getString("TENDER");
				String rsamount = rs.getString("AMOUNT");
				String rsforeignamt = rs.getString("FOREIGN_AMT");
				String rsstaffid = rs.getString("STAFF_ID");
				String rsstaffloccode = rs.getString("STAFF_LOC_CODE");
				String rsqty = rs.getString("QTY");
				Timestamp rslastupddt = rs.getTimestamp("LAST_UPD_DT");

				boolean Chkresult = InsertSastafftxn.Sastafftxnchkexists(
						entitykey, database);

				// logger.info(Chkresult);

				if (!Chkresult) {

					// logger.info("insert");

					boolean Insertresult = InsertSastafftxn.Sastafftxninsert(
							rsrowguid, rstxdate, rsloccode, rsregno, rstxno,
							rsseqno, rstxtype, rsvoid, rstender, rsamount,
							rsforeignamt, rsstaffid, rsstaffloccode, rsqty,
							rslastupddt, database);

					if (Insertresult) {
						logger.info("Sastafftxn: 1 row has been inserted. Key:"
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

					boolean Insertresult = InsertSastafftxn.Sastafftxnupdate(
							rsrowguid, rstxdate, rsloccode, rsregno, rstxno,
							rsseqno, rstxtype, rsvoid, rstender, rsamount,
							rsforeignamt, rsstaffid, rsstaffloccode, rsqty,
							rslastupddt, database);

					if (Insertresult) {
						logger.info("Sastafftxn: 1 row has been updated. Key:"
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
