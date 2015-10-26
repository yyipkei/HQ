package com.bridge.routemerge;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Objects;

import com.bridge.result.Insertdataupdatelog;
import org.apache.log4j.Logger;

import com.bridge.insertmerge.InsertCosegcdet;
import com.bridge.main.HikariMerge;
import com.bridge.main.HikariQracleFrom;
import com.bridge.result.Logupdateresult;

public class Cosegcdet {

	private static final Logger logger = Logger.getLogger(Cosegcdet.class);

	public static void routeCosegcdet(String dataupdatelog, String entityname,
			String entitykey, String database) throws SQLException {

		String[] parts = entitykey.split(",");
		String id = parts[0];
		String loccode = parts[1];
		String couponno = parts[2];

		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		String selectSQL = "SELECT ID,LOC_CODE,COUPON_NO,COUPON_AMOUNT,SEQ_NO,REMARKS,ROWGUID,"
				+ "REASON_GROUP,REASON,LAST_UPD_DT "
				+ "from COS_EGC_DET "
				+ "where id ='"
				+ id
				+ "'"
				+ "and LOC_CODE ='"
				+ loccode
				+ "'"
				+ "and COUPON_NO='" + couponno + "'"
				+"Order BY LAST_UPD_DT";

		// List<Cosegcdet> Cosegcdets = new ArrayList<Cosegcdet>();

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

				// Cosegcdet Cosegcdet = new Cosegcdet();

				String rsid = rs.getString("ID");
				String rsloccode = rs.getString("LOC_CODE");
				String rscouponno = rs.getString("COUPON_NO");
				String rscouponamount = rs.getString("COUPON_AMOUNT");
				String rsseqno = rs.getString("SEQ_NO");
				String rsremarks = rs.getString("REMARKS");
				String rsrowguid = rs.getString("ROWGUID");
				String rsreasongroup = rs.getString("REASON_GROUP");
				String rsreason = rs.getString("REASON");
				Timestamp rslastupddt = rs.getTimestamp("LAST_UPD_DT");

				boolean Chkresult = InsertCosegcdet.Cosegcdetchkexists(
						entitykey, database);

				// logger.info(Chkresult);

				if (!Chkresult) {

					// logger.info("insert");

					boolean Insertresult = InsertCosegcdet.Cosegcdetinsert(
							rsid, rsloccode, rscouponno, rscouponamount,
							rsseqno, rsremarks, rsrowguid, rsreasongroup,
							rsreason, rslastupddt, database);

					if (Insertresult) {
						logger.info("Cosegcdet: 1 row has been inserted. Key:"
								+ rsid + "," + rsloccode + "," + rscouponno);
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

					boolean Insertresult = InsertCosegcdet.Cosegcdetupdate(
							rsid, rsloccode, rscouponno, rscouponamount,
							rsseqno, rsremarks, rsrowguid, rsreasongroup,
							rsreason, rslastupddt, database);

					if (Insertresult) {
						logger.info("Cosegcdet: 1 row has been updated. Key:"
								+ rsid + "," + rsloccode + "," + rscouponno);
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
