package com.bridge.routesales;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Objects;

import com.bridge.result.Insertdataupdatelog;
import org.apache.log4j.Logger;

import com.bridge.insertsales.InsertSaserial;
import com.bridge.main.HikariMssql;
import com.bridge.main.HikariQracleFrom;
import com.bridge.result.Logupdateresult;

public class Saserial {

	private static final Logger logger = Logger.getLogger(Saserial.class);

	public static void routeSaserial(String dataupdatelog, String entityname,
			String entitykey, String database) throws SQLException {

		String[] parts = entitykey.split(",");
		String txdate = parts[0];
		String loccode = parts[1];
		String regno = parts[2];
		String txno = parts[3];
		String seqno = parts[4];

		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		String selectSQL = "SELECT TX_DATE,LOC_CODE,REG_NO,TX_NO,SEQ_NO,TX_TYPE,VOID,SERIAL_NO,PRODUCT_INFO_FLAG,CUST_FIRSTNAME,CUST_LASTNAME,CUST_EMAIL_ADDR,CUST_PHONE_NO,LAST_UPD_DT "
				+ "FROM Saserial "
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

		// List<Saserial> Saserials = new ArrayList<Saserial>();

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

				// Saserial Saserial = new Saserial();

				String rstxdate = rs.getString("TX_DATE");
				String rsloccode = rs.getString("LOC_CODE");
				String rsregno = rs.getString("REG_NO");
				String rstxno = rs.getString("TX_NO");
				String rsseqno = rs.getString("SEQ_NO");
				String rstxtype = rs.getString("TX_TYPE");
				String rsvoid = rs.getString("VOID");
				String rsserialno = rs.getString("SERIAL_NO");
				String rsproductinfoflag = rs.getString("PRODUCT_INFO_FLAG");
				String rscustfirstname = rs.getString("CUST_FIRSTNAME");
				String rscustlastname = rs.getString("CUST_LASTNAME");
				String rscustemailaddr = rs.getString("CUST_EMAIL_ADDR");
				String rscustphoneno = rs.getString("CUST_PHONE_NO");
				Timestamp rslastupddt = rs.getTimestamp("LAST_UPD_DT");

				boolean Chkresult = InsertSaserial.Saserialchkexists(entitykey,
						database);

				// logger.info(Chkresult);

				if (!Chkresult) {

					// logger.info("insert");

					boolean Insertresult = InsertSaserial.Saserialinsert(
							rstxdate, rsloccode, rsregno, rstxno, rsseqno,
							rstxtype, rsvoid, rsserialno, rsproductinfoflag,
							rscustfirstname, rscustlastname, rscustemailaddr,
							rscustphoneno, rslastupddt, database);

					if (Insertresult) {
						logger.info("Saserial: 1 row has been inserted. Key:"
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

					boolean Insertresult = InsertSaserial.Saserialupdate(
							rstxdate, rsloccode, rsregno, rstxno, rsseqno,
							rstxtype, rsvoid, rsserialno, rsproductinfoflag,
							rscustfirstname, rscustlastname, rscustemailaddr,
							rscustphoneno, rslastupddt, database);

					if (Insertresult) {
						logger.info("Saserial: 1 row has been updated. Key:"
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
