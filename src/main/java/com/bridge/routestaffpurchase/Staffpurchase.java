package com.bridge.routestaffpurchase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Objects;

import com.bridge.main.HikariRms;
import com.bridge.result.Insertdataupdatelog;
import org.apache.log4j.Logger;

import com.bridge.insertstaffpurchase.InsertStaffpurchase;
import com.bridge.main.HikariMssql;
import com.bridge.main.HikariQracleFrom;
import com.bridge.result.Logupdateresult;


public class Staffpurchase {

	private static final Logger logger = Logger.getLogger(Staffpurchase.class);

	public static void routeStaffpurchase(String dataupdatelog, String entityname,
			String entitykey, String database) throws SQLException {
		/*
		 * String[] parts = entitykey.split(","); String txdate = parts[0];
		 * String loccode = parts[1]; String regno = parts[2]; String txno =
		 * parts[3]; String seqno = parts[4];
		 */
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		String selectSQL;

		selectSQL = "SELECT COMPANY,STAFF_ID,NAME,SPOUSE_NAME,CARD_TYPE,CREDIT_LIMIT,"
				+ "LOC_DESC,DIV_DESC,DEPT_DESC,ACTIVE,STAFF_LOC_CODE,ACCUM_PURCHASE,"
				+ "DEPT_CDE,EMAIL_ADD,LAST_UPD_DT,ENTITY_KEY "
				+ "FROM Staff_purchase "
				+ "where entity_key ='"
				+ entitykey
				+ "'"
				+"Order BY LAST_UPD_DT";

		// List<Sahdr> sahdrs = new ArrayList<Sahdr>();

		try {
			if (Objects.equals(database, "Oracle")) {
				HikariQracleFrom OrcaleFrompool = HikariQracleFrom
						.getInstance();
				dbConnection = OrcaleFrompool.getConnection();
			} else {
                HikariRms Rmspool = HikariRms.getInstance();
                dbConnection = Rmspool.getConnection();
			}

			preparedStatement = dbConnection.prepareStatement(selectSQL);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {

				String rscompany = rs.getString("COMPANY");
				String rsstaffid = rs.getString("STAFF_ID");
				String rsname = rs.getString("NAME");
				String rsspousename = rs.getString("SPOUSE_NAME");
				String rscardtype = rs.getString("CARD_TYPE");
				String rscreditlimit = rs.getString("CREDIT_LIMIT");
				String rslocdesc = rs.getString("LOC_DESC");
				String rsdivdesc = rs.getString("DIV_DESC");
				String rsdeptdesc = rs.getString("DEPT_DESC");
				String rsactive = rs.getString("ACTIVE");
				String rsstaffloccode = rs.getString("STAFF_LOC_CODE");
				String rsaccumpurchase = rs.getString("ACCUM_PURCHASE");
				String rsdeptcde = rs.getString("DEPT_CDE");
				String rsemailadd = rs.getString("EMAIL_ADD");
				Timestamp rslastupddt = rs.getTimestamp("LAST_UPD_DT");
				String rsentitykey = rs.getString("ENTITY_KEY");

				// logger.info(rslastupddt);

				boolean Chkresult = InsertStaffpurchase.Staffpurchasechkexists(entitykey,
						database);

				if (!Chkresult) {

					// logger.info("insert");

					boolean Insertresult = InsertStaffpurchase.Staffpurchaseinsert(rscompany,
							rsstaffid, rsname, rsspousename, rscardtype,
							rscreditlimit, rslocdesc, rsdivdesc, rsdeptdesc,
							rsactive, rsstaffloccode, rsaccumpurchase,
							rsdeptcde, rsemailadd, rslastupddt, rsentitykey,
							database);

					if (Insertresult) {
						logger.info("Staffpurchase: 1 row has been inserted. Key:"
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

					boolean Insertresult = InsertStaffpurchase.Staffpurchaseupdate(rscompany,
							rsstaffid, rsname, rsspousename, rscardtype,
							rscreditlimit, rslocdesc, rsdivdesc, rsdeptdesc,
							rsactive, rsstaffloccode, rsaccumpurchase,
							rsdeptcde, rsemailadd, rslastupddt, rsentitykey,
							database);

					if (Insertresult) {
						logger.info("Staffpurchase: 1 row has been updated. Key:"
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
