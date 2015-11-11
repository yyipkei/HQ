package com.bridge.routesales;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Objects;

import com.bridge.result.Insertdataupdatelog;
import org.apache.log4j.Logger;

import com.bridge.insertsales.InsertSagoaaction;
import com.bridge.main.HikariMssql;
import com.bridge.main.HikariQracleFrom;
import com.bridge.result.Logupdateresult;

public class Sagoaaction {

	private static final Logger logger = Logger.getLogger(Sagoaaction.class);

	public static void routeSagoaaction(String dataupdatelog,
										String entityname, String entitykey, String database)
			throws SQLException {

		String[] parts = entitykey.split(",");
		String txdate = parts[0];
		String loccode = parts[1];
		String regno = parts[2];
		String txno = parts[3];
		String seqno = parts[4];
		//String rowid = parts[5];

		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		String selectSQL = "SELECT ROW_ID,TX_DATE,LOC_CODE,REG_NO,TX_NO,SEQ_NO,ACTION_QTY,ACTION_DATE,ACTION,RELEASE_CODE,SALES_TX_NO,MODIFIED_DATE,MODIFIED_BY,REMARK,LAST_UPD_DT "
				+ "FROM SAGOA_ACTION "
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

		// List<Sagoaaction> Sagoaactions = new ArrayList<Sagoaaction>();

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

				// Sagoaaction Sagoaaction = new Sagoaaction();

				String rsrowid = rs.getString("ROW_ID");
				String rstxdate = rs.getString("TX_DATE");
				String rsloccode = rs.getString("LOC_CODE");
				String rsregno = rs.getString("REG_NO");
				String rstxno = rs.getString("TX_NO");
				String rsseqno = rs.getString("SEQ_NO");
				String rsactionqty = rs.getString("ACTION_QTY");
				Timestamp rsactiondate = rs.getTimestamp("ACTION_DATE");
				String rsaction = rs.getString("ACTION");
				String rsreleasecode = rs.getString("RELEASE_CODE");
				String rssalestxno = rs.getString("SALES_TX_NO");
				Timestamp rsmodifieddate = rs.getTimestamp("MODIFIED_DATE");
				String rsmodifiedby = rs.getString("MODIFIED_BY");
				String rsremark = rs.getString("REMARK");
				Timestamp rslastupddt = rs.getTimestamp("LAST_UPD_DT");

				boolean Chkresult = InsertSagoaaction.Sagoaactionchkexists(
						entitykey, database);

				// logger.info(Chkresult);

				if (!Chkresult) {

					// logger.info("insert");

					boolean Insertresult = InsertSagoaaction.Sagoaactioninsert(
							rsrowid, rstxdate, rsloccode, rsregno, rstxno,
							rsseqno, rsactionqty, rsactiondate, rsaction,
							rsreleasecode, rssalestxno, rsmodifieddate,
							rsmodifiedby, rsremark, rslastupddt, database);

					if (Insertresult) {
						logger.info("SAGOA_ACTION: 1 row has been inserted. Key:"
								+ rstxdate
								+ ","
								+ rsloccode
								+ ","
								+ rsregno
								+ "'" + rstxno + "'" + rsseqno + "'" + rsrowid);
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

					boolean Insertresult = InsertSagoaaction.Sagoaactionupdate(
							rsrowid, rstxdate, rsloccode, rsregno, rstxno,
							rsseqno, rsactionqty, rsactiondate, rsaction,
							rsreleasecode, rssalestxno, rsmodifieddate,
							rsmodifiedby, rsremark, rslastupddt, database);

					if (Insertresult) {
						logger.info("SAGOA_ACTION: 1 row has been updated. Key:"
								+ rstxdate + "," + rsloccode + "," + rsregno
								+ "'" + rstxno + "'" + rsseqno + "'" + rsrowid);
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
