package com.bridge.routedeposit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Objects;

import com.bridge.result.Insertdataupdatelog;
import org.apache.log4j.Logger;

import com.bridge.insertdeposit.InsertDppayment;
import com.bridge.main.HikariMerge;
import com.bridge.main.HikariQracleFrom;
import com.bridge.result.Logupdateresult;

public class Dppayment {

	private static final Logger logger = Logger.getLogger(Dppayment.class);

	public static void routeDppayment(String dataupdatelog, String entityname,
			String entitykey, String database) throws SQLException {

		String[] parts = entitykey.split(",");
		String txdate = parts[0];
		String loccode = parts[1];
		String regno = parts[2];
		String txno = parts[3];
		String dpno = parts[4];
		String paymentno = parts[5];

		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		String selectSQL = "SELECT TX_DATE,LOC_CODE,REG_NO,TX_NO,DP_NO,PAYMENT_NO,TENDER,CARD_NO,HOLDER_NAME,CARD_EXPIRY,PAYMENT_AMOUNT,PAYMENT_DATE,REF_NO,LAST_UPD_DT "
				+ "FROM Dppayment "
				+ "where TX_DATE ='"
				+ txdate
				+ "'"
				+ "and LOC_CODE ='"
				+ loccode
				+ "'"
				+ "and REG_NO='"
				+ regno
				+ "'"
				+ "and TX_NO ='"
				+ txno
				+ "'"
				+ "and DP_NO ='"
				+ dpno
				+ "'" + "and PAYMENT_NO ='" + paymentno + "'"
				+"Order BY LAST_UPD_DT";

		// List<Dppayment> Dppayments = new ArrayList<Dppayment>();

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

				// Dppayment Dppayment = new Dppayment();

				String rstxdate = rs.getString("TX_DATE");
				String rsloccode = rs.getString("LOC_CODE");
				String rsregno = rs.getString("REG_NO");
				String rstxno = rs.getString("TX_NO");
				String rsdpno = rs.getString("DP_NO");
				String rspaymentno = rs.getString("PAYMENT_NO");
				String rstender = rs.getString("TENDER");
				String rscardno = rs.getString("CARD_NO");
				String rsholdername = rs.getString("HOLDER_NAME");
				String rscardexpiry = rs.getString("CARD_EXPIRY");
				String rspaymentamount = rs.getString("PAYMENT_AMOUNT");
				String rspaymentdate = rs.getString("PAYMENT_DATE");
				String rsrefno = rs.getString("REF_NO");
				Timestamp rslastupddt = rs.getTimestamp("LAST_UPD_DT");

				boolean Chkresult = InsertDppayment.Dppaymentchkexists(
						entitykey, database);

				// logger.info(Chkresult);

				if (!Chkresult) {

					// logger.info("insert");

					boolean Insertresult = InsertDppayment.Dppaymentinsert(
							rstxdate, rsloccode, rsregno, rstxno, rsdpno,
							rspaymentno, rstender, rscardno, rsholdername,
							rscardexpiry, rspaymentamount, rspaymentdate,
							rsrefno, rslastupddt, database);

					if (Insertresult) {
						logger.info("Dppayment: 1 row has been inserted. Key:"
								+ rstxdate + "," + rsloccode + "," + rsregno
								+ "," + rstxno + "," + dpno + ","
								+ paymentno);
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

					boolean Insertresult = InsertDppayment.Dppaymentupdate(
							rstxdate, rsloccode, rsregno, rstxno, rsdpno,
							rspaymentno, rstender, rscardno, rsholdername,
							rscardexpiry, rspaymentamount, rspaymentdate,
							rsrefno, rslastupddt, database);

					if (Insertresult) {
						logger.info("Dppayment: 1 row has been updated. Key:"
								+ rstxdate + "," + rsloccode + "," + rsregno
								+ "," + rstxno + "," + dpno + ","
								+ paymentno);
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
