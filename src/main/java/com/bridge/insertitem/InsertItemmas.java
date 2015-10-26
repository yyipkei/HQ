package com.bridge.insertitem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Objects;

import com.bridge.main.HikariRms;
import org.apache.log4j.Logger;

import com.bridge.main.HikariMssql;
import com.bridge.main.HikariQracleTo;

public class InsertItemmas {
	private static final Logger logger = Logger.getLogger(InsertItemmas.class);

	public static boolean Itemmasinsert(String rssku, String rsdeptcode,
			String rsclasscode, String rsstyle, String rscolor, String rssize,
			String rsseason, String rsyyyy, String rsavgucost,
			String rscurucost, String rsorguret, String rscururet,
			String rsonhand, String rsvendor, String rsvstyle, String rsdesc1,
			String rsdesc2, String rsopenprice, String rsfixprice,
			String rssizeset, String rsbrandcde, String rsproductinfoflag,
			String rsserialnoflag, Timestamp rslastupddt, String rsentitykey,
			String frdatabase) throws SQLException {

		boolean result = true;
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		String insertTableSQL;

		try {

			if (Objects.equals(frdatabase, "Oracle")) {
				// dbConnection = Mssql.getDBConnection();

                HikariRms Rmspool = HikariRms.getInstance();
                dbConnection = Rmspool.getConnection();

				insertTableSQL = "INSERT INTO ITEMMAS (SKU,DEPT_CODE,CLASS_CODE,STYLE,COLOR,SIZE,SEASON,YYYY,AVG_UCOST,CUR_UCOST"
						+ ",ORG_URET,CUR_URET,ONHAND,VENDOR,VSTYLE,DESC1,DESC2,OPEN_PRICE,FIX_PRICE,SIZE_SET,BRAND_CDE,PRODUCT_INFO_FLAG"
						+ ",SERIAL_NO_FLAG,LAST_UPD_DT,ENTITY_KEY) "
						+ "VALUES ( ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			} else {
				// dbConnection = OracleTo.getDBConnection();

				HikariQracleTo OrcaleFromTo = HikariQracleTo.getInstance();
				dbConnection = OrcaleFromTo.getConnection();

				insertTableSQL = "INSERT INTO ITEMMAS (SKU,DEPT_CODE,CLASS_CODE,STYLE,COLOR,SIZE_,SEASON,YYYY,AVG_UCOST,CUR_UCOST"
						+ ",ORG_URET,CUR_URET,ONHAND,VENDOR,VSTYLE,DESC1,DESC2,OPEN_PRICE,FIX_PRICE,SIZE_SET,BRAND_CDE,PRODUCT_INFO_FLAG"
						+ ",SERIAL_NO_FLAG,LAST_UPD_DT,ENTITY_KEY) "
						+ "VALUES ( ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			}

			preparedStatement = dbConnection.prepareStatement(insertTableSQL);

			preparedStatement.setString(1, rssku);
			preparedStatement.setString(2, rsdeptcode);
			preparedStatement.setString(3, rsclasscode);
			preparedStatement.setString(4, rsstyle);
			preparedStatement.setString(5, rscolor);
			preparedStatement.setString(6, rssize);
			preparedStatement.setString(7, rsseason);
			preparedStatement.setString(8, rsyyyy);
			preparedStatement.setString(9, rsavgucost);
			preparedStatement.setString(10, rscurucost);
			preparedStatement.setString(11, rsorguret);
			preparedStatement.setString(12, rscururet);
			preparedStatement.setString(13, rsonhand);
			preparedStatement.setString(14, rsvendor);
			preparedStatement.setString(15, rsvstyle);
			preparedStatement.setString(16, rsdesc1);
			preparedStatement.setString(17, rsdesc2);
			preparedStatement.setString(18, rsopenprice);
			preparedStatement.setString(19, rsfixprice);
			preparedStatement.setString(20, rssizeset);
			preparedStatement.setString(21, rsbrandcde);
			preparedStatement.setString(22, rsproductinfoflag);
			preparedStatement.setString(23, rsserialnoflag);
			preparedStatement.setTimestamp(24, rslastupddt);
			preparedStatement.setString(25, rsentitykey);

			preparedStatement.executeUpdate();
			return result;

		} catch (SQLException e) {

			logger.info(e.getMessage());
			result = false;
			return result;

		} finally {

			if (preparedStatement != null) {
				preparedStatement.close();
			}

			if (dbConnection != null) {
				dbConnection.close();
			}
		}

	}

	public static boolean Itemmasupdate(String rssku, String rsdeptcode,
			String rsclasscode, String rsstyle, String rscolor, String rssize,
			String rsseason, String rsyyyy, String rsavgucost,
			String rscurucost, String rsorguret, String rscururet,
			String rsonhand, String rsvendor, String rsvstyle, String rsdesc1,
			String rsdesc2, String rsopenprice, String rsfixprice,
			String rssizeset, String rsbrandcde, String rsproductinfoflag,
			String rsserialnoflag, Timestamp rslastupddt, String rsentitykey,
			String frdatabase) throws SQLException {

		boolean result = true;
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		String updateSQL;

		try {

			if (Objects.equals(frdatabase, "Oracle")) {
				// dbConnection = Mssql.getDBConnection();

                HikariRms Rmspool = HikariRms.getInstance();
                dbConnection = Rmspool.getConnection();

				updateSQL = "UPDATE ITEMMAS SET ENTITY_KEY             = ? "
						+ ", DEPT_CODE         = ? , CLASS_CODE        = ?, STYLE             = ?  "
						+ ", COLOR             = ? , SIZE              = ? , SEASON            = ?  "
						+ ", YYYY              = ? , AVG_UCOST         = ? , CUR_UCOST         = ?  "
						+ ", ORG_URET          = ? , CUR_URET          = ? , ONHAND            = ?  "
						+ ", VENDOR            = ? , VSTYLE            = ? , DESC1             = ?  "
						+ ", DESC2             = ? , OPEN_PRICE        = ? , FIX_PRICE         = ?  "
						+ ", SIZE_SET          = ? , BRAND_CDE         = ? , PRODUCT_INFO_FLAG = ?  "
						+ ", SERIAL_NO_FLAG    = ? , LAST_UPD_DT       = ?  "
						+ "WHERE SKU        = ?  ";
			} else {
				// dbConnection = OracleTo.getDBConnection();

				HikariQracleTo OrcaleFromTo = HikariQracleTo.getInstance();
				dbConnection = OrcaleFromTo.getConnection();

				updateSQL = "UPDATE ITEMMAS SET ENTITY_KEY             = ? "
						+ ", DEPT_CODE         = ? , CLASS_CODE        = ?, STYLE             = ?  "
						+ ", COLOR             = ? , SIZE_             = ? , SEASON            = ?  "
						+ ", YYYY              = ? , AVG_UCOST         = ? , CUR_UCOST         = ?  "
						+ ", ORG_URET          = ? , CUR_URET          = ? , ONHAND            = ?  "
						+ ", VENDOR            = ? , VSTYLE            = ? , DESC1             = ?  "
						+ ", DESC2             = ? , OPEN_PRICE        = ? , FIX_PRICE         = ?  "
						+ ", SIZE_SET          = ? , BRAND_CDE         = ? , PRODUCT_INFO_FLAG = ?  "
						+ ", SERIAL_NO_FLAG    = ? , LAST_UPD_DT       = ?  "
						+ "WHERE SKU        = ?  ";
			}

			preparedStatement = dbConnection.prepareStatement(updateSQL);

			preparedStatement.setString(1, rsentitykey);
			preparedStatement.setString(2, rsdeptcode);
			preparedStatement.setString(3, rsclasscode);
			preparedStatement.setString(4, rsstyle);
			preparedStatement.setString(5, rscolor);
			preparedStatement.setString(6, rssize);
			preparedStatement.setString(7, rsseason);
			preparedStatement.setString(8, rsyyyy);
			preparedStatement.setString(9, rsavgucost);
			preparedStatement.setString(10, rscurucost);
			preparedStatement.setString(11, rsorguret);
			preparedStatement.setString(12, rscururet);
			preparedStatement.setString(13, rsonhand);
			preparedStatement.setString(14, rsvendor);
			preparedStatement.setString(15, rsvstyle);
			preparedStatement.setString(16, rsdesc1);
			preparedStatement.setString(17, rsdesc2);
			preparedStatement.setString(18, rsopenprice);
			preparedStatement.setString(19, rsfixprice);
			preparedStatement.setString(20, rssizeset);
			preparedStatement.setString(21, rsbrandcde);
			preparedStatement.setString(22, rsproductinfoflag);
			preparedStatement.setString(23, rsserialnoflag);
			preparedStatement.setTimestamp(24, rslastupddt);
			preparedStatement.setString(25, rssku);

			// logger.info(updateSQL);

			preparedStatement.executeUpdate();
			return result;

		} catch (SQLException e) {

			logger.info(e.getMessage());
			result = false;

			logger.info(result);
			return result;

		} finally {

			if (preparedStatement != null) {
				preparedStatement.close();
			}

			if (dbConnection != null) {
				dbConnection.close();
			}
		}

	}

	public static boolean Itemmaschkexists(String entitykey, String frdatabase)
			throws SQLException {
		/*
		 * String[] parts = entitykey.split(","); String txdate = parts[0];
		 * String loccode = parts[1]; String regno = parts[2]; String txno =
		 * parts[3]; String seqno = parts[4];
		 */
		boolean result = false;
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		try {

			if (Objects.equals(frdatabase, "Oracle")) {
				// dbConnection = Mssql.getDBConnection();

                HikariRms Rmspool = HikariRms.getInstance();
                dbConnection = Rmspool.getConnection();
			} else {
				// dbConnection = OracleTo.getDBConnection();

				HikariQracleTo OrcaleFromTo = HikariQracleTo.getInstance();
				dbConnection = OrcaleFromTo.getConnection();
			}

			String selectSQL = "SELECT SKU " + "FROM Itemmas "
					+ "where SKU ='" + entitykey + "'";

			// logger.info(selectSQL);

			preparedStatement = dbConnection.prepareStatement(selectSQL);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				String rschktxdate = rs.getString("SKU");

				// logger.info(rschktxdate);

				if (rschktxdate != null) {
					result = true;// not exists
				}
			}
			return result;
		} catch (SQLException e) {

			logger.info(e.getMessage());
			result = false;
			return result;

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
