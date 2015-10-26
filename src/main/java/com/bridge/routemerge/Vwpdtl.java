package com.bridge.routemerge;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Objects;

import com.bridge.result.Insertdataupdatelog;
import org.apache.log4j.Logger;

import com.bridge.insertmerge.InsertVwpdtl;
import com.bridge.main.HikariMerge;
import com.bridge.main.HikariQracleFrom;
import com.bridge.result.Logupdateresult;

public class Vwpdtl {

	private static final Logger logger = Logger.getLogger(Vwpdtl.class);

	public static void routeVwpdtl(String dataupdatelog, String entityname,
			String entitykey, String database) throws SQLException {

		String[] parts = entitykey.split(",");
		String institcde = parts[0];
		String vwpcde = parts[1];
		String voucherno = parts[2];

		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		String selectSQL = "SELECT INSTIT_CDE,VWP_CDE,VOUCHER_NO,MIN_PURCHASE_AMT,FACE_VALUE,LAST_UPD_DT,LAST_UPD_USR,LAST_UPD_VER,STATUS,ROWGUID "
				+ "from VWP_DTL "
				+ "where INSTIT_CDE ='"
				+ institcde
				+ "'"
				+ "and VWP_CDE ='"
				+ vwpcde
				+ "'"
				+ "and VOUCHER_NO='"
				+ voucherno + "'"
				+"Order BY LAST_UPD_DT";

		// List<Vwpdtl> Vwpdtls = new ArrayList<Vwpdtl>();

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

				// Vwpdtl Vwpdtl = new Vwpdtl();

				String rsinstitcde = rs.getString("INSTIT_CDE");
				String rsvwpcde = rs.getString("VWP_CDE");
				String rsvoucherno = rs.getString("VOUCHER_NO");
				String rsminpurchaseamt = rs.getString("MIN_PURCHASE_AMT");
				String rsfacevalue = rs.getString("FACE_VALUE");
				Timestamp rslastupddt = rs.getTimestamp("LAST_UPD_DT");
				String rslastupdusr = rs.getString("LAST_UPD_USR");
				String rslastupdver = rs.getString("LAST_UPD_VER");
				String rsstatus = rs.getString("STATUS");
				String rsrowguid = rs.getString("ROWGUID");

				boolean Chkresult = InsertVwpdtl.Vwpdtlchkexists(entitykey,
						database);

				// logger.info(Chkresult);

				if (!Chkresult) {

					// logger.info("insert");

					boolean Insertresult = InsertVwpdtl.Vwpdtlinsert(
							rsinstitcde, rsvwpcde, rsvoucherno,
							rsminpurchaseamt, rsfacevalue, rslastupddt,
							rslastupdusr, rslastupdver, rsstatus, rsrowguid,
							database);

					if (Insertresult) {
						logger.info("Vwpdtl: 1 row has been inserted. Key:"
								+ institcde + "," + vwpcde + "," + voucherno);
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

					boolean Insertresult = InsertVwpdtl.Vwpdtlupdate(
							rsinstitcde, rsvwpcde, rsvoucherno,
							rsminpurchaseamt, rsfacevalue, rslastupddt,
							rslastupdusr, rslastupdver, rsstatus, rsrowguid,
							database);

					if (Insertresult) {
						logger.info("Vwpdtl: 1 row has been updated. Key:"
								+ institcde + "," + vwpcde + "," + voucherno);
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
