package com.bridge.routestockonhand;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Objects;

import com.bridge.result.Insertdataupdatelog;
import org.apache.log4j.Logger;

import com.bridge.insertstockonhand.InsertStkholdingtrn;
import com.bridge.main.HikariQracleFrom;
import com.bridge.main.HikariRms;
import com.bridge.result.Logupdateresult;

public class Stkholdingtrn {

	private static final Logger logger = Logger.getLogger(Stkholdingtrn.class);

	public static void routeStkholdingtrn(String dataupdatelog,
			String entityname, String entitykey, String database)
			throws SQLException {
		/*
		 * String[] parts = entitykey.split(","); String txdate = parts[0];
		 * String loccode = parts[1]; String regno = parts[2]; String txno =
		 * parts[3]; String seqno = parts[4];
		 */
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		String selectSQL;

		selectSQL = "SELECT TRN_ID,SKU,DATE,TRN_TYPE,TRN_SUB_TYPE,INSTIT_CDE,LOC_CDE,QTY,MR_COMP,"
				+ "MR_NO,MR_LOC,MR_SEQ, REF_NO,LAST_UPD_DT,LAST_UPD_USR,LAST_UPD_VER,entity_key "
				+ "FROM RMSADMIN.STK_HOLDING_TRN "
				+ "where entity_key ='"
				+ entitykey + "'"
				+"Order BY LAST_UPD_DT";

		// List<Sahdr> sahdrs = new ArrayList<Sahdr>();

		try {
			if (Objects.equals(database, "Oracle")) {
				// dbConnection = OracleFrom.getDBConnection();

				HikariQracleFrom OrcaleFrompool = HikariQracleFrom
						.getInstance();
				dbConnection = OrcaleFrompool.getConnection();
			} else {
				// dbConnection = Mssql.getDBConnection();

				HikariRms Rmspool = HikariRms.getInstance();
				dbConnection = Rmspool.getConnection();
			}

			preparedStatement = dbConnection.prepareStatement(selectSQL);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {

				String rstrnid = rs.getString("TRN_ID");
				String rssku = rs.getString("SKU");
				Timestamp rsdate = rs.getTimestamp("DATE");
				String rstrntype = rs.getString("TRN_TYPE");
				String rstrnsubtype = rs.getString("TRN_SUB_TYPE");
				String rsinstitcde = rs.getString("INSTIT_CDE");
				String rsloccde = rs.getString("LOC_CDE");
				String rsqty = rs.getString("QTY");
				String rsmrcomp = rs.getString("MR_COMP");
				String rsmrno = rs.getString("MR_NO");
				String rsmrloc = rs.getString("MR_LOC");
				String rsmrseq = rs.getString("MR_SEQ");
				String rsrefno = rs.getString("REF_NO");
				Timestamp rslastupddt = rs.getTimestamp("LAST_UPD_DT");
				String rslastupdusr = rs.getString("LAST_UPD_USR");
				String rslastupdver = rs.getString("LAST_UPD_VER");
				String rsentitykey = rs.getString("entity_key");

				// logger.info(rslastupddt);

				boolean Chkresult = InsertStkholdingtrn.Stkholdingtrnchkexists(
						entitykey, database);

				if (!Chkresult) {

					// logger.info("insert");

					boolean Insertresult = InsertStkholdingtrn
							.Stkholdingtrninsert(rstrnid, rssku, rsdate,
									rstrntype, rstrnsubtype, rsinstitcde,
									rsloccde, rsqty, rsmrcomp, rsmrno, rsmrloc,
									rsmrseq, rsrefno, rslastupddt,
									rslastupdusr, rslastupdver, rsentitykey,
									database);

					if (Insertresult) {
						logger.info("Stkholdingtrn: 1 row has been inserted. Key:"
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

					boolean Insertresult = InsertStkholdingtrn
							.Stkholdingtrnupdate(rstrnid, rssku, rsdate,
									rstrntype, rstrnsubtype, rsinstitcde,
									rsloccde, rsqty, rsmrcomp, rsmrno, rsmrloc,
									rsmrseq, rsrefno, rslastupddt,
									rslastupdusr, rslastupdver, rsentitykey,
									database);

					if (Insertresult) {
						logger.info("Stkholdingtrn: 1 row has been updated. Key:"
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
