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

import com.bridge.insertitem.InsertItemmas;
import com.bridge.main.HikariMssql;
import com.bridge.main.HikariQracleFrom;
import com.bridge.result.Logupdateresult;

public class Itemmas {

	private static final Logger logger = Logger.getLogger(Itemmas.class);

	public static void routeItemmas(String dataupdatelog, String entityname,
			String entitykey, String database) throws SQLException {
		/*
		 * String[] parts = entitykey.split(","); String txdate = parts[0];
		 * String loccode = parts[1]; String regno = parts[2]; String txno =
		 * parts[3]; String seqno = parts[4];
		 */
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		String selectSQL;

		// List<Sahdr> sahdrs = new ArrayList<Sahdr>();

		try {
			if (Objects.equals(database, "Oracle")) {
				//dbConnection = OracleFrom.getDBConnection();
				
				 HikariQracleFrom OrcaleFrompool = HikariQracleFrom.getInstance(); 
				dbConnection = OrcaleFrompool.getConnection();
				
				selectSQL = "SELECT SKU,DEPT_CODE,CLASS_CODE, STYLE,COLOR,"
						+ "SIZE_, SEASON,YYYY, AVG_UCOST,CUR_UCOST,ORG_URET,"
						+ "CUR_URET,ONHAND,VENDOR,VSTYLE,DESC1,DESC2,OPEN_PRICE,"
						+ "FIX_PRICE, SIZE_SET,BRAND_CDE,PRODUCT_INFO_FLAG,SERIAL_NO_FLAG,"
						+ "LAST_UPD_DT,ENTITY_KEY " + "FROM Itemmas "
						+ "where SKU ='" + entitykey + "'"
						+"Order BY LAST_UPD_DT";
			} else {
				//dbConnection = Mssql.getDBConnection();
				
				HikariRms Rmspool = HikariRms.getInstance();
				dbConnection = Rmspool.getConnection();
				
				selectSQL = "SELECT SKU,DEPT_CODE,CLASS_CODE, STYLE,COLOR,"
						+ "SIZE, SEASON,YYYY, AVG_UCOST,CUR_UCOST,ORG_URET,"
						+ "CUR_URET,ONHAND,VENDOR,VSTYLE,DESC1,DESC2,OPEN_PRICE,"
						+ "FIX_PRICE, SIZE_SET,BRAND_CDE,PRODUCT_INFO_FLAG,SERIAL_NO_FLAG,"
						+ "LAST_UPD_DT,ENTITY_KEY " + "FROM Itemmas "
						+ "where SKU ='" + entitykey + "'"
						+"Order BY LAST_UPD_DT";
			}

			preparedStatement = dbConnection.prepareStatement(selectSQL);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {

				String rssku = rs.getString("SKU");
				String rsdeptcode = rs.getString("DEPT_CODE");
				String rsclasscode = rs.getString("CLASS_CODE");
				String rsstyle = rs.getString("STYLE");
				String rscolor = rs.getString("COLOR");
				String rssize;
				if (Objects.equals(database, "Oracle")) {
					rssize = rs.getString("SIZE_");
				} else {
					rssize = rs.getString("SIZE");
				}
				String rsseason = rs.getString("SEASON");
				String rsyyyy = rs.getString("YYYY");
				String rsavgucost = rs.getString("AVG_UCOST");
				String rscurucost = rs.getString("CUR_UCOST");
				String rsorguret = rs.getString("ORG_URET");
				String rscururet = rs.getString("CUR_URET");
				String rsonhand = rs.getString("ONHAND");
				String rsvendor = rs.getString("VENDOR");
				String rsvstyle = rs.getString("VSTYLE");
				String rsdesc1 = rs.getString("DESC1");
				String rsdesc2 = rs.getString("DESC2");
				String rsopenprice = rs.getString("OPEN_PRICE");
				String rsfixprice = rs.getString("FIX_PRICE");
				String rssizeset = rs.getString("SIZE_SET");
				String rsbrandcde = rs.getString("BRAND_CDE");
				String rsproductinfoflag = rs.getString("PRODUCT_INFO_FLAG");
				String rsserialnoflag = rs.getString("SERIAL_NO_FLAG");
				Timestamp rslastupddt = rs.getTimestamp("LAST_UPD_DT");
				String rsentitykey = rs.getString("ENTITY_KEY");

				boolean Chkresult = InsertItemmas.Itemmaschkexists(entitykey,
						database);

				if (!Chkresult) {

					 //logger.info("insert");

					boolean Insertresult = InsertItemmas.Itemmasinsert(rssku,
							rsdeptcode, rsclasscode, rsstyle, rscolor, rssize,
							rsseason, rsyyyy, rsavgucost, rscurucost,
							rsorguret, rscururet, rsonhand, rsvendor, rsvstyle,
							rsdesc1, rsdesc2, rsopenprice, rsfixprice,
							rssizeset, rsbrandcde, rsproductinfoflag,
							rsserialnoflag, rslastupddt, rsentitykey, database);

					if (Insertresult) {
						logger.info("Itemmas: 1 row has been inserted. Key:"
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

					boolean Insertresult = InsertItemmas.Itemmasupdate(rssku,
							rsdeptcode, rsclasscode, rsstyle, rscolor, rssize,
							rsseason, rsyyyy, rsavgucost, rscurucost,
							rsorguret, rscururet, rsonhand, rsvendor, rsvstyle,
							rsdesc1, rsdesc2, rsopenprice, rsfixprice,
							rssizeset, rsbrandcde, rsproductinfoflag,
							rsserialnoflag, rslastupddt, rsentitykey, database);

					if (Insertresult) {
						logger.info("Itemmas: 1 row has been updated. Key:"
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
