package com.bridge.routestockres;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Objects;

import com.bridge.result.Insertdataupdatelog;
import org.apache.log4j.Logger;

import com.bridge.insertstockres.InsertStockres;
import com.bridge.main.HikariMerge;
import com.bridge.main.HikariQracleFrom;
import com.bridge.result.Logupdateresult;

public class Stockres {

	private static final Logger logger = Logger.getLogger(Stockres.class);

	public static void routeStockres(String dataupdatelog, String entityname,
			String entitykey, String database) throws SQLException {

		String[] parts = entitykey.split(",");
		String loccode = parts[0];
		String resno = parts[1];

		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		String selectSQL = "SELECT LOC_CODE,RES_NO,RES_BY,RES_AT,RES_DATE,RES_TIME,"
				+ "VIP_NO,CUST_NAME,TEL_NO,REMARK,AUTO_RELEASE,OVERRIDE_BY,EXPIRE_DATE,ROWGUID,LAST_UPD_DT "
				+ "from STOCK_RES "
				+ "where LOC_CODE ='"
				+ loccode
				+ "'"
				+ " and RES_NO ='" + resno + "'"
				+"Order BY LAST_UPD_DT";


		// List<Stockres> Stockress = new ArrayList<Stockres>();

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

				// Stockres Stockres = new Stockres();

				String rsloccode = rs.getString("LOC_CODE");
				String rsresno = rs.getString("RES_NO");
				String rsresby = rs.getString("RES_BY");
				String rsresat = rs.getString("RES_AT");
				String rsresdate = rs.getString("RES_DATE");
				String rsrestime = rs.getString("RES_TIME");
				String rsvipno = rs.getString("VIP_NO");
				String rscustname = rs.getString("CUST_NAME");
				String rstelno = rs.getString("TEL_NO");
				String rsremark = rs.getString("REMARK");
				String rsautorelease = rs.getString("AUTO_RELEASE");
				String rsoverrideby = rs.getString("OVERRIDE_BY");
				String rsexpiredate = rs.getString("EXPIRE_DATE");
				String rsrowguid = rs.getString("ROWGUID");
				Timestamp rslastupddt = rs.getTimestamp("LAST_UPD_DT");

				boolean Chkresult = InsertStockres.Stockreschkexists(entitykey,
						database);

				// logger.info(Chkresult);

				if (!Chkresult) {

					// logger.info("insert");

					boolean Insertresult = InsertStockres.Stockresinsert(
							rsloccode, rsresno, rsresby, rsresat, rsresdate,
							rsrestime, rsvipno, rscustname, rstelno, rsremark,
							rsautorelease, rsoverrideby, rsexpiredate,
							rsrowguid, rslastupddt, database);

					if (Insertresult) {
						logger.info("Stockres: 1 row has been inserted. Key:"
								+ entitykey);
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

					boolean Insertresult = InsertStockres.Stockresupdate(
							rsloccode, rsresno, rsresby, rsresat, rsresdate,
							rsrestime, rsvipno, rscustname, rstelno, rsremark,
							rsautorelease, rsoverrideby, rsexpiredate,
							rsrowguid, rslastupddt, database);

					if (Insertresult) {
						logger.info("Stockres: 1 row has been updated. Key:"
								+ entitykey);
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
