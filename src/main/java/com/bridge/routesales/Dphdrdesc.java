package com.bridge.routesales;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Objects;

import com.bridge.result.Insertdataupdatelog;
import org.apache.log4j.Logger;

import com.bridge.insertsales.InsertDphdrdesc;
import com.bridge.main.HikariMssql;
import com.bridge.main.HikariQracleFrom;
import com.bridge.result.Logupdateresult;

public class Dphdrdesc {

	private static final Logger logger = Logger.getLogger(Dphdrdesc.class);

	public static void routeDphdrdesc(String dataupdatelog, String entityname,
			String entitykey, String database) throws SQLException {

		String[] parts = entitykey.split(",");
		String loccode = parts[0];
		String dpno = parts[1];

		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		String selectSQL = "SELECT LOC_CODE,DP_NO,BU_CODE,DEPT_CODE,BRAND_CODE,DESC_SP,SKU,LAST_UPD_DT "
				+ "FROM DPHDR_DESC "
				+ "where LOC_CODE ='"
				+ loccode
				+ "'"
				+ "and DP_NO ='" + dpno + "'"
				+"Order BY LAST_UPD_DT";

		// List<Dphdrdesc> Dphdrdescs = new ArrayList<Dphdrdesc>();

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

				// Dphdrdesc Dphdrdesc = new Dphdrdesc();

				String rsloccode = rs.getString("LOC_CODE");
				String rsdpno = rs.getString("DP_NO");
				String rsbucode = rs.getString("BU_CODE");
				String rsdeptcode = rs.getString("DEPT_CODE");
				String rsbrandcode = rs.getString("BRAND_CODE");
				String rsdescsp = rs.getString("DESC_SP");
				String rssku = rs.getString("SKU");
				Timestamp rslastupddt = rs.getTimestamp("LAST_UPD_DT");

				boolean Chkresult = InsertDphdrdesc.Dphdrdescchkexists(
						entitykey, database);

				if (!Chkresult) {

					// logger.info("insert");

					boolean Insertresult = InsertDphdrdesc
							.Dphdrdescinsert(rsloccode, rsdpno, rsbucode,
									rsdeptcode, rsbrandcode, rsdescsp, rssku,
									rslastupddt, database);

					if (Insertresult) {
						logger.info("Dphdrdesc: 1 row has been inserted. Key:"
								+ rsloccode + "," + rsdpno);
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

					boolean Insertresult = InsertDphdrdesc
							.Dphdrdescupdate(rsloccode, rsdpno, rsbucode,
									rsdeptcode, rsbrandcode, rsdescsp, rssku,
									rslastupddt, database);

					if (Insertresult) {
						logger.info("Dphdrdesc: 1 row has been updated. Key:"
								+ rsloccode + "," + rsdpno);
					} else {
						logger.info("Update Error");
					}
					Logupdateresult.Updatelogresult(dataupdatelog, entityname,
							Insertresult, database);

                   /* if ((!"Oracle".equals(database)) && (Insertresult)) {
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
