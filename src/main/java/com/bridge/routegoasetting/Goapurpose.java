package com.bridge.routegoasetting;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Objects;

import com.bridge.result.Insertdataupdatelog;
import org.apache.log4j.Logger;

import com.bridge.insertgoasetting.InsertGoapurpose;
import com.bridge.main.HikariMssql;
import com.bridge.main.HikariQracleFrom;
import com.bridge.result.Logupdateresult;

public class Goapurpose {

	private static final Logger logger = Logger.getLogger(Goapurpose.class);

	public static void routeGoapurpose(String dataupdatelog, String entityname,
			String entitykey, String database) throws SQLException {
		/*
		 * String[] parts = entitykey.split(","); String txdate = parts[0];
		 * String loccode = parts[1]; String regno = parts[2]; String txno =
		 * parts[3]; String seqno = parts[4];
		 */
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		String selectSQL;

		selectSQL = "SELECT PURPOSE_CDE,PURPOSE_DESC,SORT_SEQ,EFF_FR_DATE,EFF_TO_DATE,RECV_TYPE,LIMIT,LIMIT_FROM_DATE,"
				+ "LIMIT_TO_DATE,RELEASE_FLAG,COMP_REQ,WRITE_OFF_REQ,LIMIT_GRP,LAST_UPD_DT,ENTITY_KEY "
				+ "FROM GOA_PURPOSE " + "where entity_key ='" + entitykey + "'"
				+"Order BY LAST_UPD_DT";

		// List<Sahdr> sahdrs = new ArrayList<Sahdr>();

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

				String rspurposecde = rs.getString("PURPOSE_CDE");
				String rspurposedesc = rs.getString("PURPOSE_DESC");
				String rssortseq = rs.getString("SORT_SEQ");
				String rsefffrdate = rs.getString("EFF_FR_DATE");
				String rsefftodate = rs.getString("EFF_TO_DATE");
				String rsrecvtype = rs.getString("RECV_TYPE");
				String rslimit = rs.getString("LIMIT");
				String rslimitfromdate = rs.getString("LIMIT_FROM_DATE");
				String rslimittodate = rs.getString("LIMIT_TO_DATE");
				String rsreleaseflag = rs.getString("RELEASE_FLAG");
				String rscompreq = rs.getString("COMP_REQ");
				String rswriteoffreq = rs.getString("WRITE_OFF_REQ");
				String rslimitgrp = rs.getString("LIMIT_GRP");
				Timestamp rslastupddt = rs.getTimestamp("LAST_UPD_DT");
				String rsentitykey = rs.getString("ENTITY_KEY");

				// logger.info(rslastupddt);

				boolean Chkresult = InsertGoapurpose.Goapurposechkexists(
						entitykey, database);

				if (!Chkresult) {

					// logger.info("insert");

					boolean Insertresult = InsertGoapurpose.Goapurposeinsert(
							rspurposecde, rspurposedesc, rssortseq,
							rsefffrdate, rsefftodate, rsrecvtype, rslimit,
							rslimitfromdate, rslimittodate, rsreleaseflag,
							rscompreq, rswriteoffreq, rslimitgrp, rslastupddt,
							rsentitykey, database);

					if (Insertresult) {
						logger.info("Goapurpose: 1 row has been inserted. Key:"
								+ entitykey);
					} else {
						logger.info("Insert Error");
					}
					Logupdateresult.Updatelogresult(dataupdatelog, entityname,
							Insertresult, database);

					if ((!"Oracle".equals(database)) && (Insertresult)) {
						Insertdataupdatelog.Updatelogresult(entityname, rsentitykey);
					}

				} else {

					// logger.info("update");

					boolean Insertresult = InsertGoapurpose.Goapurposeupdate(
							rspurposecde, rspurposedesc, rssortseq,
							rsefffrdate, rsefftodate, rsrecvtype, rslimit,
							rslimitfromdate, rslimittodate, rsreleaseflag,
							rscompreq, rswriteoffreq, rslimitgrp, rslastupddt,
							rsentitykey, database);

					if (Insertresult) {
						logger.info("Goapurpose: 1 row has been updated. Key:"
								+ entitykey);
					} else {
						logger.info("Update Error");
					}
					Logupdateresult.Updatelogresult(dataupdatelog, entityname,
							Insertresult, database);

					if ((!"Oracle".equals(database)) && (Insertresult)) {
						Insertdataupdatelog.Updatelogresult(entityname, rsentitykey);
					}
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
