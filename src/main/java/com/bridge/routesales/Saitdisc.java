package com.bridge.routesales;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Objects;

import com.bridge.result.Insertdataupdatelog;
import org.apache.log4j.Logger;

import com.bridge.insertsales.InsertSaitdisc;
import com.bridge.main.HikariMssql;
import com.bridge.main.HikariQracleFrom;
import com.bridge.result.Logupdateresult;

public class Saitdisc {
	private static final Logger logger = Logger.getLogger(Saitdisc.class);

	public static void routeSaitdisc(String dataupdatelog, String entityname,
			String entitykey, String database) throws SQLException {

		String[] parts = entitykey.split(",");
		String txdate = parts[0];
		String loccode = parts[1];
		String regno = parts[2];
		String txno = parts[3];
		String seqno = parts[4];

		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		String selectSQL = "SELECT TX_DATE,LOC_CODE,REG_NO, TX_NO, SEQ_NO, TX_TYPE, VOID, SKU, DISC_AMT, "
				+ "ORG_DISC_AMT, DISC_PER,ORG_DISC_PER, DISC_OVER_ID, DISC_ID,DISC_REF, LAST_UPD_DT "
				+ "FROM Saitdisc "
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
				String rsdiscamt = rs.getString("DISC_AMT");
				String rsorgdiscamt = rs.getString("ORG_DISC_AMT");
				String rsdiscper = rs.getString("DISC_PER");
				String rsorgdiscper = rs.getString("ORG_DISC_PER");
				String rsdiscoverid = rs.getString("DISC_OVER_ID");
				String rsdiscid = rs.getString("DISC_ID");
				String rsdiscref = rs.getString("DISC_REF");
				Timestamp rslastupddt = rs.getTimestamp("LAST_UPD_DT");

				boolean Chkresult = InsertSaitdisc.Saitdiscchkexists(entitykey,
						database);

				// logger.info(selectSQL);

				if (!Chkresult) {

					// logger.info("insert");

					boolean Insertresult = InsertSaitdisc.Saitdiscinsert(
							rstxdate, rsloccode, rsregno, rstxno, rsseqno,
							rstxtype, rsvoid, rssku, rsdiscamt, rsorgdiscamt,
							rsdiscper, rsorgdiscper, rsdiscoverid, rsdiscid,
							rsdiscref, rslastupddt, database);

					if (Insertresult) {
						logger.info("Saitdisc: 1 row has been inserted. Key:"
								+ rstxdate + "," + rsloccode + "," + rsregno
								+ "'" + rstxno + "'" + rsseqno);
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

					boolean Insertresult = InsertSaitdisc.Saitdiscupdate(
							rstxdate, rsloccode, rsregno, rstxno, rsseqno,
							rstxtype, rsvoid, rssku, rsdiscamt, rsorgdiscamt,
							rsdiscper, rsorgdiscper, rsdiscoverid, rsdiscid,
							rsdiscref, rslastupddt, database);

					if (Insertresult) {
						logger.info("Saitdisc: 1 row has been updated. Key:"
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
