package com.bridge.routemerge;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Objects;

import com.bridge.result.Insertdataupdatelog;
import org.apache.log4j.Logger;

import com.bridge.insertmerge.InsertCosegchdr;
import com.bridge.main.HikariMerge;
import com.bridge.main.HikariQracleFrom;
import com.bridge.result.Logupdateresult;

public class Cosegchdr {

	private static final Logger logger = Logger.getLogger(Cosegchdr.class);

	public static void routeCosegchdr(String dataupdatelog, String entityname,
			String entitykey, String database) throws SQLException {

		String[] parts = entitykey.split(",");
		String id = parts[0];
		String loccode = parts[1];

		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		String selectSQL = "SELECT ID,TX_DATE,LOC_CODE,POS_NO,STAFF_ID,ORG_LOC,"
				+ "ORG_REG,ORG_TX_NO,ORG_TX_AMT,REASON_GROUP,REASON,REMARKS,ROWGUID,"
				+ "VIP_NO,BU_CODE,VOID_STAFF_ID,VOID_DATE,VOID_REASON,VOID,LAST_UPD_DT "
				+ "from COS_EGC_HDR "
				+ "where ID ='"
				+ id
				+ "'"
				+ "and LOC_CODE ='" + loccode + "'"
				+"Order BY LAST_UPD_DT";

		// List<Cosegchdr> Cosegchdrs = new ArrayList<Cosegchdr>();

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

				// Cosegchdr Cosegchdr = new Cosegchdr();

				String rsid = rs.getString("ID");
				String rstxdate = rs.getString("TX_DATE");
				String rsloccode = rs.getString("LOC_CODE");
				String rsposno = rs.getString("POS_NO");
				String rsstaffid = rs.getString("STAFF_ID");
				String rsorgloc = rs.getString("ORG_LOC");
				String rsorgreg = rs.getString("ORG_REG");
				String rsorgtxno = rs.getString("ORG_TX_NO");
				String rsorgtxamt = rs.getString("ORG_TX_AMT");
				String rsreasongroup = rs.getString("REASON_GROUP");
				String rsreason = rs.getString("REASON");
				String rsremarks = rs.getString("REMARKS");
				String rsrowguid = rs.getString("ROWGUID");
				String rsvipno = rs.getString("VIP_NO");
				String rsbucode = rs.getString("BU_CODE");
				String rsvoidstaffid = rs.getString("VOID_STAFF_ID");
				String rsvoiddate = rs.getString("VOID_DATE");
				String rsvoidreason = rs.getString("VOID_REASON");
				String rsvoid = rs.getString("VOID");
				Timestamp rslastupddt = rs.getTimestamp("LAST_UPD_DT");

				boolean Chkresult = InsertCosegchdr.Cosegchdrchkexists(
						entitykey, database);

				// logger.info(Chkresult);

				if (!Chkresult) {

					// logger.info("insert");

					boolean Insertresult = InsertCosegchdr.Cosegchdrinsert(
							rsid, rstxdate, rsloccode, rsposno, rsstaffid,
							rsorgloc, rsorgreg, rsorgtxno, rsorgtxamt,
							rsreasongroup, rsreason, rsremarks, rsrowguid,
							rsvipno, rsbucode, rsvoidstaffid, rsvoiddate,
							rsvoidreason, rsvoid, rslastupddt, database);

					if (Insertresult) {
						logger.info("Cosegchdr: 1 row has been inserted. Key:"
								+ id + "," + loccode);
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

					boolean Insertresult = InsertCosegchdr.Cosegchdrupdate(
							rsid, rstxdate, rsloccode, rsposno, rsstaffid,
							rsorgloc, rsorgreg, rsorgtxno, rsorgtxamt,
							rsreasongroup, rsreason, rsremarks, rsrowguid,
							rsvipno, rsbucode, rsvoidstaffid, rsvoiddate,
							rsvoidreason, rsvoid, rslastupddt, database);

					if (Insertresult) {
						logger.info("Cosegchdr: 1 row has been updated. Key:"
								+ id + "," + loccode);
						
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
