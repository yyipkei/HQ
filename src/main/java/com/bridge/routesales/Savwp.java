package com.bridge.routesales;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Objects;

import com.bridge.result.Insertdataupdatelog;
import org.apache.log4j.Logger;

import com.bridge.insertsales.InsertSavwp;
import com.bridge.main.HikariMssql;
import com.bridge.main.HikariQracleFrom;
import com.bridge.result.Logupdateresult;

public class Savwp {

	private static final Logger logger = Logger.getLogger(Savwp.class);

	public static void routeSavwp(String dataupdatelog, String entityname,
			String entitykey, String database) throws SQLException {

		String[] parts = entitykey.split(",");
		String txdate = parts[0];
		String loccode = parts[1];
		String regno = parts[2];
		String txno = parts[3];
		String seqno = parts[4];

		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		String selectSQL = "SELECT TX_DATE,LOC_CODE, REG_NO, TX_NO, SEQ_NO, TX_TYPE, VOID, VOUCHER_NO, FACE_VALUE, VWP_CDE, LAST_UPD_DT "
				+ "FROM Savwp "
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

		// List<Savwp> Savwps = new ArrayList<Savwp>();

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

				// Savwp Savwp = new Savwp();

				String rstxdate = rs.getString("TX_DATE");
				String rsloccode = rs.getString("LOC_CODE");
				String rsregno = rs.getString("REG_NO");
				String rstxno = rs.getString("TX_NO");
				String rsseqno = rs.getString("SEQ_NO");
				String rstxtype = rs.getString("TX_TYPE");
				String rsvoid = rs.getString("VOID");
				String rsvoucherno = rs.getString("VOUCHER_NO");
				String rsfacevalue = rs.getString("FACE_VALUE");
				String rsvwpcde = rs.getString("VWP_CDE");
				Timestamp rslastupddt = rs.getTimestamp("LAST_UPD_DT");

				boolean Chkresult = InsertSavwp.Savwpchkexists(entitykey,
						database);

				// logger.info(Chkresult);

				if (!Chkresult) {

					// logger.info("insert");

					boolean Insertresult = InsertSavwp.Savwpinsert(rstxdate,
							rsloccode, rsregno, rstxno, rsseqno, rstxtype,
							rsvoid, rsvoucherno, rsfacevalue, rsvwpcde,
							rslastupddt, database);

					if (Insertresult) {
						logger.info("Savwp: 1 row has been inserted. Key:"
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

					boolean Insertresult = InsertSavwp.Savwpupdate(rstxdate,
							rsloccode, rsregno, rstxno, rsseqno, rstxtype,
							rsvoid, rsvoucherno, rsfacevalue, rsvwpcde,
							rslastupddt, database);

					if (Insertresult) {
						logger.info("Savwp: 1 row has been updated. Key:"
								+ rstxdate + "," + rsloccode + "," + rsregno
								+ "'" + rstxno + "'" + rsseqno);
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
