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

import com.bridge.insertstaffpurchase.InsertSastaffdiscexceptcl;
import com.bridge.main.HikariMssql;
import com.bridge.main.HikariQracleFrom;
import com.bridge.result.Logupdateresult;

public class Sastaffdiscexceptcl {

	private static final Logger logger = Logger
			.getLogger(Sastaffdiscexceptcl.class);

	public static void routeSastaffdiscexceptcl(String dataupdatelog,
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

		selectSQL = "SELECT COMPANY_CDE,BATCH_NO,VER_NO,SEQ_NO,EXCEPT_TYPE,"
				+ "CARD_TYPE,BU_CDE,DEPT_CDE,CLASS_CDE,BRAND_CDE,PROD_TYPE,"
				+ "DISC_PER,LAST_UPD_DT,LAST_UPD_USR,LAST_UPD_VER,STYLE,"
				+ "COLOR_CDE,SIZE_CDE,SEASON_CDE,ENTITY_KEY "
				+ "FROM RMSADMIN.sa_staff_disc_except_cl " + "where entity_key ='"
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
				String rsverno = rs.getString("VER_NO");
				String rsseqno = rs.getString("SEQ_NO");
				String rsexcepttype = rs.getString("EXCEPT_TYPE");
				String rscardtype = rs.getString("CARD_TYPE");
				String rsbucde = rs.getString("BU_CDE");
				String rsdeptcde = rs.getString("DEPT_CDE");
				String rsclasscde = rs.getString("CLASS_CDE");
				String rsbrandcde = rs.getString("BRAND_CDE");
				String rsprodtype = rs.getString("PROD_TYPE");
				String rsdiscper = rs.getString("DISC_PER");
				Timestamp rslastupddt = rs.getTimestamp("LAST_UPD_DT");
				String rslastupdusr = rs.getString("LAST_UPD_USR");
				String rslastupdver = rs.getString("LAST_UPD_VER");
				String rsstyle = rs.getString("STYLE");
				String rscolorcde = rs.getString("COLOR_CDE");
				String rssizecde = rs.getString("SIZE_CDE");
				String rsseasoncde = rs.getString("SEASON_CDE");
				String rsentitykey = rs.getString("ENTITY_KEY");

				// logger.info(rslastupddt);

				boolean Chkresult = InsertSastaffdiscexceptcl
						.Sastaffdiscexceptclchkexists(entitykey, database);

				if (!Chkresult) {

					// logger.info("insert");

					boolean Insertresult = InsertSastaffdiscexceptcl
							.Sastaffdiscexceptclinsert(rscompanycde, rsbatchno,
									rsverno, rsseqno, rsexcepttype, rscardtype,
									rsbucde, rsdeptcde, rsclasscde, rsbrandcde,
									rsprodtype, rsdiscper, rslastupddt,
									rslastupdusr, rslastupdver, rsstyle,
									rscolorcde, rssizecde, rsseasoncde,
									rsentitykey, database);

					if (Insertresult) {
						logger.info("Sastaffdiscexceptcl: 1 row has been inserted. Key:"
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

					boolean Insertresult = InsertSastaffdiscexceptcl
							.Sastaffdiscexceptclupdate(rscompanycde, rsbatchno,
									rsverno, rsseqno, rsexcepttype, rscardtype,
									rsbucde, rsdeptcde, rsclasscde, rsbrandcde,
									rsprodtype, rsdiscper, rslastupddt,
									rslastupdusr, rslastupdver, rsstyle,
									rscolorcde, rssizecde, rsseasoncde,
									rsentitykey, database);

					if (Insertresult) {
						logger.info("Sastaffdiscexceptcl: 1 row has been updated. Key:"
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
