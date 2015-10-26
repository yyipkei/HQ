package com.bridge.routesales;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Objects;

import com.bridge.result.Insertdataupdatelog;
import org.apache.log4j.Logger;

import com.bridge.insertsales.InsertSaonlineshop;
import com.bridge.main.HikariMssql;
import com.bridge.main.HikariQracleFrom;
import com.bridge.result.Logupdateresult;

public class Saonlineshop {

	private static final Logger logger = Logger.getLogger(Saonlineshop.class);

	public static void routeSaonlineshop(String dataupdatelog,
			String entityname, String entitykey, String database)
			throws SQLException {

		String[] parts = entitykey.split(",");
		String txdate = parts[0];
		String loccode = parts[1];
		String regno = parts[2];
		String txno = parts[3];

		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		String selectSQL = "SELECT TX_TYPE,VOID,ONLINESHOP_NO,PRICE_LIST_ID,SUBMISSION_TIME,RETROSPECTIVE_RMA_ID,LAST_UPD_DT,TX_DATE,LOC_CODE,REG_NO,TX_NO "
				+ "FROM Saonlineshop "
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

		// List<Saonlineshop> Saonlineshops = new ArrayList<Saonlineshop>();

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

				// Saonlineshop Saonlineshop = new Saonlineshop();

				String rstxdate = rs.getString("TX_DATE");
				String rsloccode = rs.getString("LOC_CODE");
				String rsregno = rs.getString("REG_NO");
				String rstxno = rs.getString("TX_NO");
				String rstxtype = rs.getString("TX_TYPE");
				String rsvoid = rs.getString("VOID");
				String rsonlineshopno = rs.getString("ONLINESHOP_NO");
				String rspricelistid = rs.getString("PRICE_LIST_ID");
				Timestamp rssubmissiontime = rs.getTimestamp("SUBMISSION_TIME");
				String rsretrospectivermaid = rs
						.getString("RETROSPECTIVE_RMA_ID");
				Timestamp rslastupddt = rs.getTimestamp("LAST_UPD_DT");

				boolean Chkresult = InsertSaonlineshop.Saonlineshopchkexists(
						entitykey, database);

				// logger.info(Chkresult);

				if (!Chkresult) {

					// logger.info("insert");

					boolean Insertresult = InsertSaonlineshop
							.Saonlineshopinsert(rstxdate, rsloccode, rsregno,
									rstxno, rstxtype, rsvoid, rsonlineshopno,
									rspricelistid, rssubmissiontime,
									rsretrospectivermaid, rslastupddt, database);

					if (Insertresult) {
						logger.info("Saonlineshop: 1 row has been inserted. Key:"
								+ rstxdate
								+ ","
								+ rsloccode
								+ ","
								+ rsregno
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

					boolean Insertresult = InsertSaonlineshop
							.Saonlineshopupdate(rstxdate, rsloccode, rsregno,
									rstxno, rstxtype, rsvoid, rsonlineshopno,
									rspricelistid, rssubmissiontime,
									rsretrospectivermaid, rslastupddt, database);

					if (Insertresult) {
						logger.info("Saonlineshop: 1 row has been updated. Key:"
								+ rstxdate
								+ ","
								+ rsloccode
								+ ","
								+ rsregno
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
