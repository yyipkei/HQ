package com.bridge.routesales;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Objects;

import com.bridge.result.Insertdataupdatelog;
import org.apache.log4j.Logger;

import com.bridge.insertsales.InsertSadet;
import com.bridge.main.HikariMssql;
import com.bridge.main.HikariQracleFrom;
import com.bridge.result.Logupdateresult;

public class Sadet {
	private static final Logger logger = Logger.getLogger(Sadet.class);

	public static void routeSadet(String dataupdatelog, String entityname,
			String entitykey, String database) throws SQLException {

		String[] parts = entitykey.split(",");
		String txdate = parts[0];
		String loccode = parts[1];
		String regno = parts[2];
		String txno = parts[3];
		String seqno = parts[4];

		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		String selectSQL = "SELECT TX_DATE,LOC_CODE,REG_NO,TX_NO,SEQ_NO,TX_TYPE,VOID,SKU,QTY,ORG_URET,CUR_URET, NET_URET,NET_DISC,NET_SALE,AVG_UCOST,"
				+ "CUR_UCOST,COST_SOLD,ITEM_DISC,TX_DISC,PRICE_OVER_ID,SALES_X_TAX,TAX_AMT,TAX_AMT2,TAX_SHOWN,VENDOR_UPC,LAST_UPD_DT "
				+ "FROM SADET "
				+ "where tx_date ='"
				+ txdate
				+ "'"
				+ "and LOC_CODE ='"
				+ loccode
				+ "'"
				+ "and reg_no='"
				+ regno
				+ "'"
				+ "and tx_no ='"
				+ txno
				+ "'"
				+ "and seq_no ='"
				+ seqno
				+ "'"
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
				String rstxno = rs.getString("TX_NO");
				String rsseqno = rs.getString("SEQ_NO");
				String rstxtype = rs.getString("TX_TYPE");
				String rsvoid = rs.getString("VOID");
				String rssku = rs.getString("SKU");
				String rsqty = rs.getString("QTY");
				String rsorguret = rs.getString("ORG_URET");
				String rscururet = rs.getString("CUR_URET");
				String rsneturet = rs.getString("NET_URET");
				String rsnetdisc = rs.getString("NET_DISC");
				String rsnetsale = rs.getString("NET_SALE");
				String rsavgucost = rs.getString("AVG_UCOST");
				String rscurucost = rs.getString("CUR_UCOST");
				String rscostsold = rs.getString("COST_SOLD");
				String rsitemdisc = rs.getString("ITEM_DISC");
				String rstxdisc = rs.getString("TX_DISC");
				String rspriceoverid = rs.getString("PRICE_OVER_ID");
				String rssalesxtax = rs.getString("SALES_X_TAX");
				String rstaxamt = rs.getString("TAX_AMT");
				String rstaxamt2 = rs.getString("TAX_AMT2");
				String rstaxshown = rs.getString("TAX_SHOWN");
				String rsvendorupc = rs.getString("VENDOR_UPC");
				Timestamp rslastupddt = rs.getTimestamp("LAST_UPD_DT");

				boolean Chkresult = InsertSadet.Sadetchkexists(entitykey,
						database);

				// logger.info(selectSQL);

				if (!Chkresult) {

					// logger.info("insert");

					boolean Insertresult = InsertSadet.Sadetinsert(rstxdate,
							rsloccode, rsregno, rstxno, rsseqno, rstxtype,
							rsvoid, rssku, rsqty, rsorguret, rscururet,
							rsneturet, rsnetdisc, rsnetsale, rsavgucost,
							rscurucost, rscostsold, rsitemdisc, rstxdisc,
							rspriceoverid, rssalesxtax, rstaxamt, rstaxamt2,
							rstaxshown, rsvendorupc, rslastupddt, database);

					if (Insertresult) {
						logger.info("SADET: 1 row has been inserted. Key:"
								+ rstxdate + "," + rsloccode + "," + rsregno
								+ "'" + rstxno + "'" + rsseqno);
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

					boolean Insertresult = InsertSadet.Sadetupdate(rstxdate,
							rsloccode, rsregno, rstxno, rsseqno, rstxtype,
							rsvoid, rssku, rsqty, rsorguret, rscururet,
							rsneturet, rsnetdisc, rsnetsale, rsavgucost,
							rscurucost, rscostsold, rsitemdisc, rstxdisc,
							rspriceoverid, rssalesxtax, rstaxamt, rstaxamt2,
							rstaxshown, rsvendorupc, rslastupddt, database);

					if (Insertresult) {
						logger.info("SADET: 1 row has been updated. Key:"
								+ rstxdate + "," + rsloccode + "," + rsregno
								+ "'" + rstxno + "'" + rsseqno);
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
