package com.bridge.routesales;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Objects;

import com.bridge.result.Insertdataupdatelog;
import org.apache.log4j.Logger;

import com.bridge.insertsales.InsertEdcsettlement;
import com.bridge.main.HikariMssql;
import com.bridge.main.HikariQracleFrom;
import com.bridge.result.Logupdateresult;

public class Edcsettlement {

	private static final Logger logger = Logger.getLogger(Edcsettlement.class);

	public static void routeEdcsettlement(String dataupdatelog,
										  String entityname, String entitykey, String database)
			throws SQLException {

		String[] parts = entitykey.split(",");
		String txdate = parts[0];
		String loccode = parts[1];
		String regno = parts[2];
		String txno = parts[3];
		String txamount = parts[4];
		String bankhost = parts[5];
		String settlestatus = parts[6];

		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		String selectSQL = null;

		try {
			if (Objects.equals(database, "Oracle")) {
				HikariQracleFrom OrcaleFrompool = HikariQracleFrom
						.getInstance();
				dbConnection = OrcaleFrompool.getConnection();
				selectSQL="SELECT TX_DATE,LOC_CODE,REG_NO,TX_NO,TX_AMOUNT,SETTLE_DATE,BATCH_NO,BANK_HOST,"
						+ "MERCH_NO,SETTLE_AMOUNT,STATUS,LAST_UPD_DT,LAST_UPD_VER,LAST_UPD_USR,"
						+ "POS_EDC,LONG_SHORT,LONG_SHORT_AMT,VALUE_DAY,AUTO_MANUAL_FLAG "
						+ "FROM EDC_SETTLEMENT "
						+ "where TO_DATE(tx_date,'DD-MM-YY') ='"
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
						+ "and tx_amount ='"
						+ txamount
						+ "'"
						+ "and bank_host ='"
						+ bankhost
						+ "'"
						+ "and status ='"
						+ settlestatus
						+ "'"
						+"Order BY LAST_UPD_DT";
			} else {
				HikariMssql Mssqlpool = HikariMssql.getInstance();
				dbConnection = Mssqlpool.getConnection();
				selectSQL ="SELECT TX_DATE,LOC_CODE,REG_NO,TX_NO,TX_AMOUNT,SETTLE_DATE,BATCH_NO,BANK_HOST,"
						+ "MERCH_NO,SETTLE_AMOUNT,STATUS,LAST_UPD_DT,LAST_UPD_VER,LAST_UPD_USR,"
						+ "POS_EDC,LONG_SHORT,LONG_SHORT_AMT,VALUE_DAY,AUTO_MANUAL_FLAG "
						+ "FROM EDC_SETTLEMENT "
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
						+ "and tx_amount ='"
						+ txamount
						+ "'"
						+ "and bank_host ='"
						+ bankhost
						+ "'"
						+ "and status ='"
						+ settlestatus
						+ "'"
						+"Order BY LAST_UPD_DT";
			}

			preparedStatement = dbConnection.prepareStatement(selectSQL);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {

				Timestamp rstxdate = rs.getTimestamp("TX_DATE");
				String rsloccode = rs.getString("LOC_CODE");
				String rsregno = rs.getString("REG_NO");
				String rstxno = rs.getString("TX_NO");
				String rstxamount = rs.getString("TX_AMOUNT");
				Timestamp rssettledate = rs.getTimestamp("SETTLE_DATE");
				String rsbatchno = rs.getString("BATCH_NO");
				String rsbankhost = rs.getString("BANK_HOST");
				String rsmerchno = rs.getString("MERCH_NO");
				String rssettleamount = rs.getString("SETTLE_AMOUNT");
				String rsstatus = rs.getString("STATUS");
				Timestamp rslastupddt = rs.getTimestamp("LAST_UPD_DT");
				String rslastupdver = rs.getString("LAST_UPD_VER");
				String rslastupdusr = rs.getString("LAST_UPD_USR");
				String rsposedc = rs.getString("POS_EDC");
				String rslongshort = rs.getString("LONG_SHORT");
				String rslongshortamt = rs.getString("LONG_SHORT_AMT");
				String rsvalueday = rs.getString("VALUE_DAY");
				String rsautomanualflag = rs.getString("AUTO_MANUAL_FLAG");

				boolean Chkresult = InsertEdcsettlement.Edcsettlementchkexists(
						entitykey, database);

				// logger.info(selectSQL);

				if (!Chkresult) {

					// logger.info("insert");

					boolean Insertresult = InsertEdcsettlement
							.Edcsettlementinsert(rstxdate, rsloccode, rsregno,
									rstxno, rstxamount, rssettledate,
									rsbatchno, rsbankhost, rsmerchno,
									rssettleamount, rsstatus, rslastupddt,
									rslastupdver, rslastupdusr, rsposedc,
									rslongshort, rslongshortamt, rsvalueday,
									rsautomanualflag, database);

					if (Insertresult) {
						logger.info("Edcsettlement: 1 row has been inserted. Key:"
								+ rstxdate
								+ ","
								+ rsloccode
								+ ","
								+ rsregno
								+ "'"
								+ rstxno
								+ "'"
								+ rstxamount
								+ "'"
								+ rsbankhost
								+ "'"
								+ rsstatus
								+ "'");
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

					boolean Insertresult = InsertEdcsettlement
							.Edcsettlementupdate(rstxdate, rsloccode, rsregno,
									rstxno, rstxamount, rssettledate,
									rsbatchno, rsbankhost, rsmerchno,
									rssettleamount, rsstatus, rslastupddt,
									rslastupdver, rslastupdusr, rsposedc,
									rslongshort, rslongshortamt, rsvalueday,
									rsautomanualflag, database);

					if (Insertresult) {
						logger.info("Edcsettlement: 1 row has been updated. Key:"
								+ rstxdate
								+ ","
								+ rsloccode
								+ ","
								+ rsregno
								+ "'"
								+ rstxno
								+ "'"
								+ rstxamount
								+ "'"
								+ rsbankhost
								+ "'"
								+ rsstatus
								+ "'");
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
