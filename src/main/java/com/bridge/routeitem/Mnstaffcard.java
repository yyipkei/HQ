package com.bridge.routeitem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Objects;

import com.bridge.main.HikariRms;
import com.bridge.result.Insertdataupdatelog;
import org.apache.log4j.Logger;

import com.bridge.insertitem.InsertMnstaffcard;
import com.bridge.main.HikariMssql;
import com.bridge.main.HikariQracleFrom;
import com.bridge.result.Logupdateresult;

public class Mnstaffcard {

	private static final Logger logger = Logger.getLogger(Mnstaffcard.class);

	public static void routeMnstaffcard(String dataupdatelog,
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

		selectSQL = "SELECT CARD_NUM,CARD_TYPE,STAFF_ID,HK_ID,HOLDER_NAME,CREDIT_LMT,"
				+ "LOC_DESC,DEPT_DESC,ISSUE_DT,RETURN_DT,STOP_DT,REPL_DT,TERM_DT,SUBCARD,"
				+ "SUB_HK_ID,HOLDER_NAME2,STATUS,AUTOPAY,REMARK,LAST_UPD_USR,LAST_UPD_DT,"
				+ "LAST_UPD_VER,ROWGUID,ENTITY_KEY "
				+ "FROM rmsadmin.MN_STAFFCARD "
				+ "where entity_key ='" + entitykey + "'"
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

				String rscardnum = rs.getString("CARD_NUM");
				String rscardtype = rs.getString("CARD_TYPE");
				String rsstaffid = rs.getString("STAFF_ID");
				String rshkid = rs.getString("HK_ID");
				String rsholdername = rs.getString("HOLDER_NAME");
				String rscreditlmt = rs.getString("CREDIT_LMT");
				String rslocdesc = rs.getString("LOC_DESC");
				String rsdeptdesc = rs.getString("DEPT_DESC");
				Timestamp rsissuedt = rs.getTimestamp("ISSUE_DT");
				Timestamp rsreturndt = rs.getTimestamp("RETURN_DT");
				Timestamp rsstopdt = rs.getTimestamp("STOP_DT");
				Timestamp rsrepldt = rs.getTimestamp("REPL_DT");
				Timestamp rstermdt = rs.getTimestamp("TERM_DT");
				String rssubcard = rs.getString("SUBCARD");
				String rssubhkid = rs.getString("SUB_HK_ID");
				String rsholdername2 = rs.getString("HOLDER_NAME2");
				String rsstatus = rs.getString("STATUS");
				String rsautopay = rs.getString("AUTOPAY");
				String rsremark = rs.getString("REMARK");
				String rslastupdusr = rs.getString("LAST_UPD_USR");
				Timestamp rslastupddt = rs.getTimestamp("LAST_UPD_DT");
				String rslastupdver = rs.getString("LAST_UPD_VER");
				String rsrowguid = rs.getString("ROWGUID");
				String rsentitykey = rs.getString("ENTITY_KEY");

				// logger.info(rslastupddt);

				boolean Chkresult = InsertMnstaffcard.Mnstaffcardchkexists(
						entitykey, database);

				if (!Chkresult) {

					// logger.info("insert");

					boolean Insertresult = InsertMnstaffcard.Mnstaffcardinsert(
							rscardnum, rscardtype, rsstaffid, rshkid,
							rsholdername, rscreditlmt, rslocdesc, rsdeptdesc,
							rsissuedt, rsreturndt, rsstopdt, rsrepldt,
							rstermdt, rssubcard, rssubhkid, rsholdername2,
							rsstatus, rsautopay, rsremark, rslastupdusr,
							rslastupddt, rslastupdver, rsrowguid, rsentitykey,
							database);

					if (Insertresult) {
						logger.info("Mnstaffcard: 1 row has been inserted. Key:"
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

					boolean Insertresult = InsertMnstaffcard.Mnstaffcardupdate(
							rscardnum, rscardtype, rsstaffid, rshkid,
							rsholdername, rscreditlmt, rslocdesc, rsdeptdesc,
							rsissuedt, rsreturndt, rsstopdt, rsrepldt,
							rstermdt, rssubcard, rssubhkid, rsholdername2,
							rsstatus, rsautopay, rsremark, rslastupdusr,
							rslastupddt, rslastupdver, rsrowguid, rsentitykey,
							database);

					if (Insertresult) {
						logger.info("Mnstaffcard: 1 row has been updated. Key:"
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
