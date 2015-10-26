package com.bridge.routesales;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Objects;

import com.bridge.result.Insertdataupdatelog;
import org.apache.log4j.Logger;

import com.bridge.insertsales.InsertSagoahdr;
import com.bridge.main.HikariMssql;
import com.bridge.main.HikariQracleFrom;
import com.bridge.result.Logupdateresult;

public class Sagoahdr {

	private static final Logger logger = Logger.getLogger(Sagoahdr.class);

	public static void routeSagoahdr(String dataupdatelog, String entityname,
			String entitykey, String database) throws SQLException {

		String[] parts = entitykey.split(",");
		String txdate = parts[0];
		String loccode = parts[1];
		String regno = parts[2];
		String txno = parts[3];

		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		String selectSQL = "SELECT TX_DATE,LOC_CODE,REG_NO,TX_NO,TX_TIME,TX_TYPE,VOID_BY,CASHIER_NO,EXP_RTN_DATE,PURPOSE_CDE,OTHER_INFO,RECV_TYPE,STAFF_ID,STAFF_NAME,DEPT_CDE,VIP_NO,CUSTOMER_NAME,COMP_NAME,WRITE_OFF_DEPT,LAST_UPD_DT "
				+ "FROM sagoa_hdr "
				+ "where tx_date ='"
				+ txdate
				+ "'"
				+ "and LOC_CODE ='"
				+ loccode
				+ "'"
				+ "and reg_no='"
				+ regno
				+ "'" + "and tx_no ='" + txno + "'"
				+"Order BY LAST_UPD_DT";

		// List<Sagoahdr> Sagoahdrs = new ArrayList<Sagoahdr>();

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

				// Sagoahdr Sagoahdr = new Sagoahdr();

				String rstxdate = rs.getString("TX_DATE");
				String rsloccode = rs.getString("LOC_CODE");
				String rsregno = rs.getString("REG_NO");
				String rstxno = rs.getString("TX_NO");
				String rstxtime = rs.getString("TX_TIME");
				String rstxtype = rs.getString("TX_TYPE");
				String rsvoidby = rs.getString("VOID_BY");
				String rscashierno = rs.getString("CASHIER_NO");
				String rsexprtndate = rs.getString("EXP_RTN_DATE");
				String rspurposecde = rs.getString("PURPOSE_CDE");
				String rsotherinfo = rs.getString("OTHER_INFO");
				String rsrecvtype = rs.getString("RECV_TYPE");
				String rsstaffid = rs.getString("STAFF_ID");
				String rsstaffname = rs.getString("STAFF_NAME");
				String rsdeptcde = rs.getString("DEPT_CDE");
				String rsvipno = rs.getString("VIP_NO");
				String rscustomername = rs.getString("CUSTOMER_NAME");
				String rscompname = rs.getString("COMP_NAME");
				String rswriteoffdept = rs.getString("WRITE_OFF_DEPT");
				Timestamp rslastupddt = rs.getTimestamp("LAST_UPD_DT");

				boolean Chkresult = InsertSagoahdr.Sagoahdrchkexists(entitykey,
						database);

				// logger.info(Chkresult);

				if (!Chkresult) {

					// logger.info("insert");

					boolean Insertresult = InsertSagoahdr.Sagoahdrinsert(
							rstxdate, rsloccode, rsregno, rstxno, rstxtime,
							rstxtype, rsvoidby, rscashierno, rsexprtndate,
							rspurposecde, rsotherinfo, rsrecvtype, rsstaffid,
							rsstaffname, rsdeptcde, rsvipno, rscustomername,
							rscompname, rswriteoffdept, rslastupddt, database);

					if (Insertresult) {
						logger.info("sagoa_hdr: 1 row has been inserted. Key:"
								+ rstxdate + "," + rsloccode + "," + rsregno
								+ "'" + rstxno);
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

					boolean Insertresult = InsertSagoahdr.Sagoahdrupdate(
							rstxdate, rsloccode, rsregno, rstxno, rstxtime,
							rstxtype, rsvoidby, rscashierno, rsexprtndate,
							rspurposecde, rsotherinfo, rsrecvtype, rsstaffid,
							rsstaffname, rsdeptcde, rsvipno, rscustomername,
							rscompname, rswriteoffdept, rslastupddt, database);

					if (Insertresult) {
						logger.info("sagoa_hdr: 1 row has been updated. Key:"
								+ rstxdate + "," + rsloccode + "," + rsregno
								+ "'" + rstxno);
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
