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

import com.bridge.insertstaffpurchase.InsertSastaffdischdrcl;
import com.bridge.main.HikariMssql;
import com.bridge.main.HikariQracleFrom;
import com.bridge.result.Logupdateresult;

public class Sastaffdischdrcl {

	private static final Logger logger = Logger
			.getLogger(Sastaffdischdrcl.class);

	public static void routeSastaffdischdrcl(String dataupdatelog,
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

		selectSQL = "SELECT COMPANY_CDE,BATCH_NO,BATCH_DESC,"
				+ "VER_NO,CURR_VER,STATUS,EFF_DT_FR,EFF_DT_TO,PREPARE_BY,"
				+ "CFM_DT,CFM_BY,APP_DT,APP_BY,LAST_UPD_DT,LAST_UPD_USR,LAST_UPD_VER,ENTITY_KEY "
				+ "FROM RMSADMIN.SA_STAFF_DISC_HDR_CL " + "where entity_key ='"
				+ entitykey + "'"
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

				String rscompanycde = rs.getString("COMPANY_CDE");
				String rsbatchno = rs.getString("BATCH_NO");
				String rsbatchdesc = rs.getString("BATCH_DESC");
				String rsverno = rs.getString("VER_NO");
				String rscurrver = rs.getString("CURR_VER");
				String rsstatus = rs.getString("STATUS");
				Timestamp rseffdtfr = rs.getTimestamp("EFF_DT_FR");
				Timestamp rseffdtto = rs.getTimestamp("EFF_DT_TO");
				String rsprepareby = rs.getString("PREPARE_BY");
				Timestamp rscfmdt = rs.getTimestamp("CFM_DT");
				String rscfmby = rs.getString("CFM_BY");
				Timestamp rsappdt = rs.getTimestamp("APP_DT");
				String rsappby = rs.getString("APP_BY");
				Timestamp rslastupddt = rs.getTimestamp("LAST_UPD_DT");
				String rslastupdusr = rs.getString("LAST_UPD_USR");
				String rslastupdver = rs.getString("LAST_UPD_VER");
				String rsentitykey = rs.getString("ENTITY_KEY");

				// logger.info(rslastupddt);

				boolean Chkresult = InsertSastaffdischdrcl
						.Sastaffdischdrclchkexists(entitykey, database);

				if (!Chkresult) {

					// logger.info("insert");

					boolean Insertresult = InsertSastaffdischdrcl
							.Sastaffdischdrclinsert(rscompanycde, rsbatchno,
									rsbatchdesc, rsverno, rscurrver, rsstatus,
									rseffdtfr, rseffdtto, rsprepareby, rscfmdt,
									rscfmby, rsappdt, rsappby, rslastupddt,
									rslastupdusr, rslastupdver, rsentitykey,
									database);

					if (Insertresult) {
						logger.info("Sastaffdischdrcl: 1 row has been inserted. Key:"
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

					boolean Insertresult = InsertSastaffdischdrcl
							.Sastaffdischdrclupdate(rscompanycde, rsbatchno,
									rsbatchdesc, rsverno, rscurrver, rsstatus,
									rseffdtfr, rseffdtto, rsprepareby, rscfmdt,
									rscfmby, rsappdt, rsappby, rslastupddt,
									rslastupdusr, rslastupdver, rsentitykey,
									database);

					if (Insertresult) {
						logger.info("Sastaffdischdrcl: 1 row has been updated. Key:"
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
