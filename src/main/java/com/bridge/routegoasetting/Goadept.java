package com.bridge.routegoasetting;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Objects;

import com.bridge.result.Insertdataupdatelog;
import org.apache.log4j.Logger;

import com.bridge.insertgoasetting.InsertGoadept;
import com.bridge.main.HikariMssql;
import com.bridge.main.HikariQracleFrom;
import com.bridge.result.Logupdateresult;

public class Goadept {

	private static final Logger logger = Logger.getLogger(Goadept.class);

	public static void routeGoadept(String dataupdatelog, String entityname,
			String entitykey, String database) throws SQLException {
		/*
		 * String[] parts = entitykey.split(","); String txdate = parts[0];
		 * String loccode = parts[1]; String regno = parts[2]; String txno =
		 * parts[3]; String seqno = parts[4];
		 */
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		String selectSQL;

		selectSQL = "SELECT DEPT_CDE,DEPT_DESC,SORT_SEQ,DEPT_HEAD,EMAIL,EMAIL_DOMAIN,LAST_UPD_DT,ENTITY_KEY "
				+ "FROM GOA_DEPT " + "where entity_key ='" + entitykey + "'"
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

				String rsdeptcde = rs.getString("DEPT_CDE");
				String rsdeptdesc = rs.getString("DEPT_DESC");
				String rssortseq = rs.getString("SORT_SEQ");
				String rsdepthead = rs.getString("DEPT_HEAD");
				String rsemail = rs.getString("EMAIL");
				String rsemaildomain = rs.getString("EMAIL_DOMAIN");
				Timestamp rslastupddt = rs.getTimestamp("LAST_UPD_DT");
				String rsentitykey = rs.getString("ENTITY_KEY");

				// logger.info(rslastupddt);

				boolean Chkresult = InsertGoadept.Goadeptchkexists(entitykey,
						database);

				if (!Chkresult) {

					// logger.info("insert");

					boolean Insertresult = InsertGoadept.Goadeptinsert(
							rsdeptcde, rsdeptdesc, rssortseq, rsdepthead,
							rsemail, rsemaildomain, rslastupddt, rsentitykey,
							database);

					if (Insertresult) {
						logger.info("Goadept: 1 row has been inserted. Key:"
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

					boolean Insertresult = InsertGoadept.Goadeptupdate(
							rsdeptcde, rsdeptdesc, rssortseq, rsdepthead,
							rsemail, rsemaildomain, rslastupddt, rsentitykey,
							database);

					if (Insertresult) {
						logger.info("Goadept: 1 row has been updated. Key:"
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
