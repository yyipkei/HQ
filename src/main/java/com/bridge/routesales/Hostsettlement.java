package com.bridge.routesales;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Objects;

import com.bridge.result.Insertdataupdatelog;
import org.apache.log4j.Logger;

import com.bridge.insertsales.InsertHostsettlement;
import com.bridge.main.HikariMssql;
import com.bridge.main.HikariQracleFrom;
import com.bridge.result.Logupdateresult;

public class Hostsettlement {

	private static final Logger logger = Logger.getLogger(Hostsettlement.class);

	public static void routeHostsettlement(String dataupdatelog,
			String entityname, String entitykey, String database)
			throws SQLException {

		String[] parts = entitykey.split(",");
		String txdate = parts[0];
		String loccode = parts[1];
		String regno = parts[2];
		String seqno = parts[3];

		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		String selectSQL = "SELECT TX_DATE,LOC_CODE,REG_NO,SEQ_NO,"
				+ "BATCH_NO,B_RESP_CDE,B_RESP_TEXT,CARD_TYPE,SALE_C,SALE_T,REFUND_C,"
				+ "REFUND_T,RESP_CDE,RESP_TEXT,BATCH,SETTLED,DCC_SALE_C,DCC_SALE_T,DCC_REFUND_C,DCC_REFUND_T,"
				+ "DCC_SETTLED,DCC_BATCH_NO,DCC_B_RESP_CDE,DCC_B_RESP_TEXT,LAST_UPD_DT "
				+ "FROM host_settlement " + "where tx_date ='" + txdate + "'"
				+ "and LOC_CODE ='" + loccode + "'" + "and reg_no='" + regno
				+ "'" + "and seq_no ='" + seqno + "'"
				+"Order BY LAST_UPD_DT";

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

				String rstxdate = rs.getString("TX_DATE");
				String rsloccode = rs.getString("LOC_CODE");
				String rsregno = rs.getString("REG_NO");
				String rsseqno = rs.getString("SEQ_NO");
				String rsbatchno = rs.getString("BATCH_NO");
				String rsbrespcde = rs.getString("B_RESP_CDE");
				String rsbresptext = rs.getString("B_RESP_TEXT");
				String rscardtype = rs.getString("CARD_TYPE");
				String rssalec = rs.getString("SALE_C");
				String rssalet = rs.getString("SALE_T");
				String rsrefundc = rs.getString("REFUND_C");
				String rsrefundt = rs.getString("REFUND_T");
				String rsrespcde = rs.getString("RESP_CDE");
				String rsresptext = rs.getString("RESP_TEXT");
				String rsbatch = rs.getString("BATCH");
				String rssettled = rs.getString("SETTLED");
				String rsdccsalec = rs.getString("DCC_SALE_C");
				String rsdccsalet = rs.getString("DCC_SALE_T");
				String rsdccrefundc = rs.getString("DCC_REFUND_C");
				String rsdccrefundt = rs.getString("DCC_REFUND_T");
				String rsdccsettled = rs.getString("DCC_SETTLED");
				String rsdccbatchno = rs.getString("DCC_BATCH_NO");
				String rsdccbrespcde = rs.getString("DCC_B_RESP_CDE");
				String rsdccbresptext = rs.getString("DCC_B_RESP_TEXT");
				Timestamp rslastupddt = rs.getTimestamp("LAST_UPD_DT");

				boolean Chkresult = InsertHostsettlement
						.Hostsettlementchkexists(entitykey, database);

				// logger.info(selectSQL);

				if (!Chkresult) {

					// logger.info("insert");

					boolean Insertresult = InsertHostsettlement
							.Hostsettlementinsert(rstxdate, rsloccode, rsregno,
									rsseqno, rsbatchno, rsbrespcde,
									rsbresptext, rscardtype, rssalec, rssalet,
									rsrefundc, rsrefundt, rsrespcde,
									rsresptext, rsbatch, rssettled, rsdccsalec,
									rsdccsalet, rsdccrefundc, rsdccrefundt,
									rsdccsettled, rsdccbatchno, rsdccbrespcde,
									rsdccbresptext, rslastupddt, database);

					if (Insertresult) {
						logger.info("Hostsettlement: 1 row has been inserted. Key:"
								+ rstxdate
								+ ","
								+ rsloccode
								+ ","
								+ rsregno
								+ "'" + rsseqno);
					} else {
						logger.info("Insert Error");
					}
					Logupdateresult.Updatelogresult(dataupdatelog, entityname,
							Insertresult, database);

                   /* if ((!"Oracle".equals(database)) && (Insertresult)) {
                        Insertdataupdatelog.Updatelogresult(entityname, entitykey);
                    }*/

				} else {

					// logger.info("update");

					boolean Insertresult = InsertHostsettlement
							.Hostsettlementupdate(rstxdate, rsloccode, rsregno,
									rsseqno, rsbatchno, rsbrespcde,
									rsbresptext, rscardtype, rssalec, rssalet,
									rsrefundc, rsrefundt, rsrespcde,
									rsresptext, rsbatch, rssettled, rsdccsalec,
									rsdccsalet, rsdccrefundc, rsdccrefundt,
									rsdccsettled, rsdccbatchno, rsdccbrespcde,
									rsdccbresptext, rslastupddt, database);

					if (Insertresult) {
						logger.info("Hostsettlement: 1 row has been updated. Key:"
								+ rstxdate
								+ ","
								+ rsloccode
								+ ","
								+ rsregno
								+ "'" + rsseqno);
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
