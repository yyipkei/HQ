package com.bridge.routemerge;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Objects;

import com.bridge.result.Insertdataupdatelog;
import org.apache.log4j.Logger;

import com.bridge.insertmerge.InsertCafecouponhdr;
import com.bridge.main.HikariMerge;
import com.bridge.main.HikariQracleFrom;
import com.bridge.result.Logupdateresult;

public class Cafecouponhdr {

	private static final Logger logger = Logger.getLogger(Cafecouponhdr.class);

	public static void routeCafecouponhdr(String dataupdatelog,
			String entityname, String entitykey, String database)
			throws SQLException {

		String[] parts = entitykey.split(",");
		String id = parts[0];
		String loccode = parts[1];

		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		String selectSQL = "SELECT ID,TX_DATE,LOC_CODE,POS_NO,STAFF_ID,REASON_GROUP,REASON,REMARKS,ROWGUID,VIP_NO,LAST_UPD_DT "
				+ "from CAFE_COUPON_HDR "
				+ "where ID ='"
				+ id
				+ "'"
				+ "and LOC_CODE ='" + loccode + "'"
				+"Order BY LAST_UPD_DT";

		// List<Cafecouponhdr> Cafecouponhdrs = new ArrayList<Cafecouponhdr>();

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

				// Cafecouponhdr Cafecouponhdr = new Cafecouponhdr();

				String rsid = rs.getString("ID");
				String rstxdate = rs.getString("TX_DATE");
				String rsloccode = rs.getString("LOC_CODE");
				String rsposno = rs.getString("POS_NO");
				String rsstaffid = rs.getString("STAFF_ID");
				String rsreasongroup = rs.getString("REASON_GROUP");
				String rsreason = rs.getString("REASON");
				String rsremarks = rs.getString("REMARKS");
				String rsrowguid = rs.getString("ROWGUID");
				String rsvipno = rs.getString("VIP_NO");
				Timestamp rslastupddt = rs.getTimestamp("LAST_UPD_DT");

				boolean Chkresult = InsertCafecouponhdr.Cafecouponhdrchkexists(
						entitykey, database);

				// logger.info(Chkresult);

				if (!Chkresult) {

					// logger.info("insert");

					boolean Insertresult = InsertCafecouponhdr
							.Cafecouponhdrinsert(rsid, rstxdate, rsloccode,
									rsposno, rsstaffid, rsreasongroup,
									rsreason, rsremarks, rsrowguid, rsvipno,
									rslastupddt, database);

					if (Insertresult) {
						logger.info("Cafecouponhdr: 1 row has been inserted. Key:"
								+ id + "," + rsloccode);

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

					boolean Insertresult = InsertCafecouponhdr
							.Cafecouponhdrupdate(rsid, rstxdate, rsloccode,
									rsposno, rsstaffid, rsreasongroup,
									rsreason, rsremarks, rsrowguid, rsvipno,
									rslastupddt, database);

					if (Insertresult) {
						logger.info("Cafecouponhdr: 1 row has been updated. Key:"
								+ id + "," + rsloccode);
						
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
