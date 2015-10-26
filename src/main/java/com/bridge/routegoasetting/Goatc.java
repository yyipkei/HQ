package com.bridge.routegoasetting;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Objects;

import com.bridge.result.Insertdataupdatelog;
import org.apache.log4j.Logger;

import com.bridge.insertgoasetting.InsertGoatc;
import com.bridge.main.HikariMssql;
import com.bridge.main.HikariQracleFrom;
import com.bridge.result.Logupdateresult;

public class Goatc {

	private static final Logger logger = Logger.getLogger(Goatc.class);

	public static void routeGoatc(String dataupdatelog, String entityname,
			String entitykey, String database) throws SQLException {
		/*
		 * String[] parts = entitykey.split(","); String txdate = parts[0];
		 * String loccode = parts[1]; String regno = parts[2]; String txno =
		 * parts[3]; String seqno = parts[4];
		 */
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		String selectSQL;

		selectSQL = "SELECT TC_CDE,TC_DESC,TC_TYPE,DKT_TYPE,TC_DESC_TC,TC_DESC_SC,LAST_UPD_DT,ENTITY_KEY "
				+ "FROM GOA_TC " + "where entity_key ='" + entitykey + "'"
				+"Order BY LAST_UPD_DT";

		// List<Sahdr> sahdrs = new ArrayList<Sahdr>();

		try {
			if (Objects.equals(database, "Oracle")) {
				HikariQracleFrom OrcaleFrompool = HikariQracleFrom
						.getInstance();
				dbConnection = OrcaleFrompool.getConnection();
			} else {
				HikariMssql Mssqlpool = HikariMssql.getInstance();
				dbConnection = Mssqlpool.getConnection();
			}

			preparedStatement = dbConnection.prepareStatement(selectSQL);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {

				String rstccde = rs.getString("TC_CDE");
				String rstcdesc = rs.getString("TC_DESC");
				String rstctype = rs.getString("TC_TYPE");
				String rsdkttype = rs.getString("DKT_TYPE");
				String rstcdesctc = rs.getString("TC_DESC_TC");
				String rstcdescsc = rs.getString("TC_DESC_SC");
				Timestamp rslastupddt = rs.getTimestamp("LAST_UPD_DT");
				String rsentitykey = rs.getString("ENTITY_KEY");

				// logger.info(rslastupddt);

				boolean Chkresult = InsertGoatc.Goatcchkexists(entitykey,
						database);

				if (!Chkresult) {

					// logger.info("insert");

					boolean Insertresult = InsertGoatc.Goatcinsert(rstccde,
							rstcdesc, rstctype, rsdkttype, rstcdesctc,
							rstcdescsc, rslastupddt, rsentitykey, database);

					if (Insertresult) {
						logger.info("Goatc: 1 row has been inserted. Key:"
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

					boolean Insertresult = InsertGoatc.Goatcupdate(rstccde,
							rstcdesc, rstctype, rsdkttype, rstcdesctc,
							rstcdescsc, rslastupddt, rsentitykey, database);

					if (Insertresult) {
						logger.info("Goatc: 1 row has been updated. Key:"
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
