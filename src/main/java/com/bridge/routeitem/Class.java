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

import com.bridge.insertitem.InsertClass;
import com.bridge.main.HikariMssql;
import com.bridge.main.HikariQracleFrom;
import com.bridge.result.Logupdateresult;

public class Class {

	private static final Logger logger = Logger.getLogger(Class.class);

	public static void routeClass(String dataupdatelog, String entityname,
			String entitykey, String database) throws SQLException {

		  String[] parts = entitykey.split(",");
		  String classcode = parts[0];
		  String deptcode = parts[1];

		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		String selectSQL;

		selectSQL = "SELECT CLASS_CODE,CLASS_DESC,DEPT_CODE,GP_PERCENT,PROD_TYPE,LAST_UPD_DT,ENTITY_KEY "
				+ "FROM Class " + "where CLASS_CODE ='" + classcode + "'" + "and DEPT_CODE ='" + deptcode + "'"
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

				String rsclasscode = rs.getString("CLASS_CODE");
				String rsclassdesc = rs.getString("CLASS_DESC");
				String rsdeptcode = rs.getString("DEPT_CODE");
				String rsgppercent = rs.getString("GP_PERCENT");
				String rsprodtype = rs.getString("PROD_TYPE");
				Timestamp rslastupddt = rs.getTimestamp("LAST_UPD_DT");
				String rsentitykey = rs.getString("ENTITY_KEY");

				// logger.info(rslastupddt);

				boolean Chkresult = InsertClass.Classchkexists(entitykey,
						database);

				if (!Chkresult) {

					// logger.info("insert");

					boolean Insertresult = InsertClass.Classinsert(rsclasscode,
							rsclassdesc, rsdeptcode, rsgppercent, rsprodtype,
							rslastupddt, rsentitykey, database);

					if (Insertresult) {
						logger.info("Class: 1 row has been inserted. Key:"
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

					boolean Insertresult = InsertClass.Classupdate(rsclasscode,
							rsclassdesc, rsdeptcode, rsgppercent, rsprodtype,
							rslastupddt, rsentitykey, database);

					if (Insertresult) {
						logger.info("Class: 1 row has been updated. Key:"
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
