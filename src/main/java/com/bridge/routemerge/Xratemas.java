package com.bridge.routemerge;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Objects;

import com.bridge.result.Insertdataupdatelog;
import org.apache.log4j.Logger;

import com.bridge.insertmerge.InsertXratemas;
import com.bridge.main.HikariMerge;
import com.bridge.main.HikariQracleFrom;
import com.bridge.result.Logupdateresult;

public class Xratemas {

	private static final Logger logger = Logger.getLogger(Xratemas.class);

	public static void routeXratemas(String dataupdatelog, String entityname,
			String entitykey, String database) throws SQLException {

		String[] parts = entitykey.split(",");
		String currency = parts[0];

		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		String selectSQL = "SELECT CURRENCY,CURRENCY_NAME,RATE,TENDER,LAST_RATE,"
				+ "TMP_RATE,FOREIGN_RATE,ADJUSTMENT,ROUNDING_DP,WARNING_LIMIT,"
				+ "OVERRIDE_USER,OVERRIDE_DT,LAST_UPD_USER,LAST_UPD_DT,ROWGUID "
				+ "from XRATEMAS " + "where CURRENCY ='" + currency + "'"
				+"Order BY LAST_UPD_DT";

		// List<Xratemas> Xratemass = new ArrayList<Xratemas>();

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

				// Xratemas Xratemas = new Xratemas();

				String rscurrency = rs.getString("CURRENCY");
				String rscurrencyname = rs.getString("CURRENCY_NAME");
				String rsrate = rs.getString("RATE");
				String rstender = rs.getString("TENDER");
				String rslastrate = rs.getString("LAST_RATE");
				String rstmprate = rs.getString("TMP_RATE");
				String rsforeignrate = rs.getString("FOREIGN_RATE");
				String rsadjustment = rs.getString("ADJUSTMENT");
				String rsroundingdp = rs.getString("ROUNDING_DP");
				String rswarninglimit = rs.getString("WARNING_LIMIT");
				String rsoverrideuser = rs.getString("OVERRIDE_USER");
				Timestamp rsoverridedt = rs.getTimestamp("OVERRIDE_DT");
				String rslastupduser = rs.getString("LAST_UPD_USER");
				Timestamp rslastupddt = rs.getTimestamp("LAST_UPD_DT");
				String rsrowguid = rs.getString("ROWGUID");

				boolean Chkresult = InsertXratemas.Xratemaschkexists(entitykey,
						database);

				// logger.info(Chkresult);

				if (!Chkresult) {

					// logger.info("insert");

					boolean Insertresult = InsertXratemas.Xratemasinsert(
							rscurrency, rscurrencyname, rsrate, rstender,
							rslastrate, rstmprate, rsforeignrate, rsadjustment,
							rsroundingdp, rswarninglimit, rsoverrideuser,
							rsoverridedt, rslastupduser, rslastupddt,
							rsrowguid, database);

					if (Insertresult) {
						logger.info("Xratemas: 1 row has been inserted. Key:"
								+ currency);
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

					boolean Insertresult = InsertXratemas.Xratemasupdate(
							rscurrency, rscurrencyname, rsrate, rstender,
							rslastrate, rstmprate, rsforeignrate, rsadjustment,
							rsroundingdp, rswarninglimit, rsoverrideuser,
							rsoverridedt, rslastupduser, rslastupddt,
							rsrowguid, database);

					if (Insertresult) {
						logger.info("Xratemas: 1 row has been updated. Key:"
								+ currency);
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
