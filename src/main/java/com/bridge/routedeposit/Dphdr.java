package com.bridge.routedeposit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Objects;

import com.bridge.result.Insertdataupdatelog;
import org.apache.log4j.Logger;

import com.bridge.insertdeposit.InsertDphdr;
import com.bridge.main.HikariMerge;
import com.bridge.main.HikariQracleFrom;
import com.bridge.result.Logupdateresult;

public class Dphdr {

	private static final Logger logger = Logger.getLogger(Dphdr.class);

	public static void routeDphdr(String dataupdatelog, String entityname,
			String entitykey, String database) throws SQLException {

		String[] parts = entitykey.split(",");
		String txdate = parts[0];
		String loccode = parts[1];
		String regno = parts[2];
		String txno = parts[3];
		String dpno = parts[4];
		String issueloccode = parts[5];

		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		String selectSQL = "SELECT TX_DATE,LOC_CODE,REG_NO,TX_NO,DP_NO,AMOUNT,BALANCE,REASON,DP_STATUS,ISSUE_LOC_CODE,MODIFIED_BY,MODIFIED_DATE,ROWGUID,LAST_UPD_DT "
				+ "FROM DPHDR "
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
				+ "'" + "and ISSUE_LOC_CODE ='" + issueloccode + "'"
				+"Order BY LAST_UPD_DT";

		// List<Dphdr> Dphdrs = new ArrayList<Dphdr>();

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

				// Dphdr Dphdr = new Dphdr();

				String rstxdate = rs.getString("TX_DATE");
				String rsloccode = rs.getString("LOC_CODE");
				String rsregno = rs.getString("REG_NO");
				String rstxno = rs.getString("TX_NO");
				String rsdpno = rs.getString("DP_NO");
				String rsamount = rs.getString("AMOUNT");
				String rsbalance = rs.getString("BALANCE");
				String rsreason = rs.getString("REASON");
				String rsdpstatus = rs.getString("DP_STATUS");
				String rsissueloccode = rs.getString("ISSUE_LOC_CODE");
				String rsmodifiedby = rs.getString("MODIFIED_BY");
				String rsmodifieddate = rs.getString("MODIFIED_DATE");
				String rsrowguid = rs.getString("ROWGUID");
				Timestamp rslastupddt = rs.getTimestamp("LAST_UPD_DT");

				boolean Chkresult = InsertDphdr.Dphdrchkexists(entitykey,
						database);

				// logger.info(Chkresult);

				if (!Chkresult) {

					// logger.info("insert");

					boolean Insertresult = InsertDphdr.Dphdrinsert(rstxdate,
							rsloccode, rsregno, rstxno, rsdpno, rsamount,
							rsbalance, rsreason, rsdpstatus, rsissueloccode,
							rsmodifiedby, rsmodifieddate, rsrowguid,
							rslastupddt, database);

					if (Insertresult) {
						logger.info("Dphdr: 1 row has been inserted. Key:"
								+ rstxdate + "," + rsloccode + "," + rsregno
								+ "," + rstxno + "," + dpno + ","
								+ issueloccode);
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

					boolean Insertresult = InsertDphdr.Dphdrupdate(rstxdate,
							rsloccode, rsregno, rstxno, rsdpno, rsamount,
							rsbalance, rsreason, rsdpstatus, rsissueloccode,
							rsmodifiedby, rsmodifieddate, rsrowguid,
							rslastupddt, database);

					if (Insertresult) {
						logger.info("Dphdr: 1 row has been updated. Key:"
								+ rstxdate + "," + rsloccode + "," + rsregno
								+ "," + rstxno + "," + dpno + ","
								+ issueloccode);
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
