package com.bridge.routeitem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Objects;

import com.bridge.main.HikariRms;
import com.bridge.result.Insertdataupdatelog;
import org.apache.log4j.Logger;

import com.bridge.insertitem.InsertStylmasele;
import com.bridge.main.HikariMssql;
import com.bridge.main.HikariQracleFrom;
import com.bridge.result.Logupdateresult;

public class Stylmasele {

	private static final Logger logger = Logger.getLogger(Stylmasele.class);

	public static void routeStylmasele(String dataupdatelog, String entityname,
			String entitykey, String database) throws SQLException {
		/*
		 * String[] parts = entitykey.split(","); String txdate = parts[0];
		 * String loccode = parts[1]; String regno = parts[2]; String txno =
		 * parts[3]; String seqno = parts[4];
		 */
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		String selectSQL;

		selectSQL = "SELECT STYLE,PRODUCT,SERIAL_NO_FLAG,MODEL,BAND,RESOLUTION,MEMORY,LINK,"
				+ "ZOOM,MAX_APERTURE,MANUFACTURE_PLACE,ACCESSORY,SER_PROVIDER,WARRANTY,REMARKS,"
				+ "STORAGE_MEDIA,RATIO,VOICE_RECORD,LINEIN_RECORD,LAST_UPD_DT,ENTITY_KEY "
				+ "FROM rmsadmin.STYL_MAS_ELE "
				+ "where entity_key ='"
				+ entitykey
				+ "'"
				+"Order BY LAST_UPD_DT";

		// List<Sahdr> sahdrs = new ArrayList<Sahdr>();

		try {
			if (Objects.equals(database, "Oracle")) {
				// dbConnection = OracleFrom.getDBConnection();

				HikariQracleFrom OrcaleFrompool = HikariQracleFrom
						.getInstance();
				dbConnection = OrcaleFrompool.getConnection();
			} else {
				// dbConnection = Mssql.getDBConnection();

				HikariRms Rmspool = HikariRms.getInstance();
				dbConnection = Rmspool.getConnection();
			}

			preparedStatement = dbConnection.prepareStatement(selectSQL);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {

				String rsstyle = rs.getString("STYLE");
				String rsproduct = rs.getString("PRODUCT");
				String rsserialnoflag = rs.getString("SERIAL_NO_FLAG");
				String rsmodel = rs.getString("MODEL");
				String rsband = rs.getString("BAND");
				String rsresolution = rs.getString("RESOLUTION");
				String rsmemory = rs.getString("MEMORY");
				String rslink = rs.getString("LINK");
				String rszoom = rs.getString("ZOOM");
				String rsmaxaperture = rs.getString("MAX_APERTURE");
				String rsmanufactureplace = rs.getString("MANUFACTURE_PLACE");
				String rsaccessory = rs.getString("ACCESSORY");
				String rsserprovider = rs.getString("SER_PROVIDER");
				String rswarranty = rs.getString("WARRANTY");
				String rsremarks = rs.getString("REMARKS");
				String rsstoragemedia = rs.getString("STORAGE_MEDIA");
				String rsratio = rs.getString("RATIO");
				String rsvoicerecord = rs.getString("VOICE_RECORD");
				String rslineinrecord = rs.getString("LINEIN_RECORD");
				Timestamp rslastupddt = rs.getTimestamp("LAST_UPD_DT");
				String rsentitykey = rs.getString("ENTITY_KEY");

				// logger.info(rslastupddt);

				boolean Chkresult = InsertStylmasele.Stylmaselechkexists(
						entitykey, database);

				if (!Chkresult) {

					// logger.info("insert");

					boolean Insertresult = InsertStylmasele.Stylmaseleinsert(
							rsstyle, rsproduct, rsserialnoflag, rsmodel,
							rsband, rsresolution, rsmemory, rslink, rszoom,
							rsmaxaperture, rsmanufactureplace, rsaccessory,
							rsserprovider, rswarranty, rsremarks,
							rsstoragemedia, rsratio, rsvoicerecord,
							rslineinrecord, rslastupddt, rsentitykey, database);

					if (Insertresult) {
						logger.info("Stylmasele: 1 row has been inserted. Key:"
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

					boolean Insertresult = InsertStylmasele.Stylmaseleupdate(
							rsstyle, rsproduct, rsserialnoflag, rsmodel,
							rsband, rsresolution, rsmemory, rslink, rszoom,
							rsmaxaperture, rsmanufactureplace, rsaccessory,
							rsserprovider, rswarranty, rsremarks,
							rsstoragemedia, rsratio, rsvoicerecord,
							rslineinrecord, rslastupddt, rsentitykey, database);

					if (Insertresult) {
						logger.info("Stylmasele: 1 row has been updated. Key:"
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
