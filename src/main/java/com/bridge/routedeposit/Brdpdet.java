package com.bridge.routedeposit;

import com.bridge.insertdeposit.InsertBrdpdet;
import com.bridge.main.HikariMerge;
import com.bridge.main.HikariQracleFrom;
import com.bridge.result.Insertdataupdatelog;
import com.bridge.result.Logupdateresult;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.Objects;

public class Brdpdet {
	private static final Logger logger = Logger.getLogger(Brdpdet.class);

	public static void routeBrdpdet(String dataupdatelog, String entityname,
			String entitykey, String database) throws SQLException {

		String[] parts = entitykey.split(",");
		String brdpno = parts[0];
		String bridalregno = parts[1];
		String issueloccode = parts[2];

		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		String selectSQL = "SELECT BRDP_NO,BRIDAL_REG_NO,ISSUE_LOC_CODE,TX_DATE,LOC_CODE,REG_NO,"
				+ "TX_NO,SETTLE_AMT,STATUS,MODIFIED_BY,MODIFIED_DATE,MODIFIED_TIME,ROWGUID,LAST_UPD_DT "
				+ "FROM BRDP_DET "
				+ "where BRDP_NO ='"
				+ brdpno
				+ "'"
				+ "and BRIDAL_REG_NO ='"
				+ bridalregno
				+ "'"
				+ "and ISSUE_LOC_CODE='" + issueloccode + "'"
				+"Order BY LAST_UPD_DT";

		// List<Brdpdet> Brdpdets = new ArrayList<Brdpdet>();

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

				// Brdpdet Brdpdet = new Brdpdet();

				String rsbrdpno = rs.getString("BRDP_NO");
				String rsbridalregno = rs.getString("BRIDAL_REG_NO");
				String rsissueloccode = rs.getString("ISSUE_LOC_CODE");
				String rstxdate = rs.getString("TX_DATE");
				String rsloccode = rs.getString("LOC_CODE");
				String rsregno = rs.getString("REG_NO");
				String rstxno = rs.getString("TX_NO");
				String rssettleamt = rs.getString("SETTLE_AMT");
				String rsstatus = rs.getString("STATUS");
				String rsmodifiedby = rs.getString("MODIFIED_BY");
				String rsmodifieddate = rs.getString("MODIFIED_DATE");
				String rsmodifiedtime = rs.getString("MODIFIED_TIME");
				String rsrowguid = rs.getString("ROWGUID");
				Timestamp rslastupddt = rs.getTimestamp("LAST_UPD_DT");

				boolean Chkresult = InsertBrdpdet.Brdpdetchkexists(entitykey,
						database);

				// logger.info(Chkresult);

				if (!Chkresult) {

					// logger.info("insert");

					boolean Insertresult = InsertBrdpdet.Brdpdetinsert(
							rsbrdpno, rsbridalregno, rsissueloccode, rstxdate,
							rsloccode, rsregno, rstxno, rssettleamt, rsstatus,
							rsmodifiedby, rsmodifieddate, rsmodifiedtime,
							rsrowguid, rslastupddt, database);

					if (Insertresult) {
						logger.info("Brdpdet: 1 row has been inserted. Key:"
								+ brdpno + "," + bridalregno + ","
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

					boolean Insertresult = InsertBrdpdet.Brdpdetupdate(
							rsbrdpno, rsbridalregno, rsissueloccode, rstxdate,
							rsloccode, rsregno, rstxno, rssettleamt, rsstatus,
							rsmodifiedby, rsmodifieddate, rsmodifiedtime,
							rsrowguid, rslastupddt, database);

					if (Insertresult) {
						logger.info("Brdpdet: 1 row has been updated. Key:"
								+ brdpno + "," + bridalregno + ","
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
