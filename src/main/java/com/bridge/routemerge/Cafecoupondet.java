package com.bridge.routemerge;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Objects;

import com.bridge.result.Insertdataupdatelog;
import org.apache.log4j.Logger;

import com.bridge.insertmerge.InsertCafecoupondet;
import com.bridge.main.HikariMerge;
import com.bridge.main.HikariQracleFrom;
import com.bridge.result.Logupdateresult;

public class Cafecoupondet {

	private static final Logger logger = Logger.getLogger(Cafecoupondet.class);

	public static void routeCafecoupondet(String dataupdatelog,
			String entityname, String entitykey, String database)
			throws SQLException {

		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		String selectSQL = "SELECT ID,LOC_CODE,COUPON_NO,SEQ_NO,REMARKS,ROWGUID,REASON_GROUP,REASON,LAST_UPD_DT "
				+ "from CAFE_COUPON_DET "
				+ "where COUPON_NO ='"
				+ entitykey
				+ "'"
				+"Order BY LAST_UPD_DT";

		// List<Cafecoupondet> Cafecoupondets = new ArrayList<Cafecoupondet>();

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

				// Cafecoupondet Cafecoupondet = new Cafecoupondet();

				String rsid = rs.getString("ID");
				String rsloccode = rs.getString("LOC_CODE");
				String rscouponno = rs.getString("COUPON_NO");
				String rsseqno = rs.getString("SEQ_NO");
				String rsremarks = rs.getString("REMARKS");
				String rsrowguid = rs.getString("ROWGUID");
				String rsreasongroup = rs.getString("REASON_GROUP");
				String rsreason = rs.getString("REASON");
				Timestamp rslastupddt = rs.getTimestamp("LAST_UPD_DT");

				boolean Chkresult = InsertCafecoupondet.Cafecoupondetchkexists(
						entitykey, database);

				// logger.info(Chkresult);

				if (!Chkresult) {

					// logger.info("insert");

					boolean Insertresult = InsertCafecoupondet
							.Cafecoupondetinsert(rsid, rsloccode, rscouponno,
									rsseqno, rsremarks, rsrowguid,
									rsreasongroup, rsreason, rslastupddt,
									database);

					if (Insertresult) {
						logger.info("Cafecoupondet: 1 row has been inserted. Key:"
								+ rscouponno);
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

					boolean Insertresult = InsertCafecoupondet
							.Cafecoupondetupdate(rsid, rsloccode, rscouponno,
									rsseqno, rsremarks, rsrowguid,
									rsreasongroup, rsreason, rslastupddt,
									database);

					if (Insertresult) {
						logger.info("Cafecoupondet: 1 row has been updated. Key:"
								+ rscouponno);
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
