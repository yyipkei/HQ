package com.bridge.insertstockres;

import com.bridge.main.HikariMerge;
import com.bridge.main.HikariQracleTo;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.Objects;

/**
 * Created by keyip on 08-Jun-15.
 */
public class InsertStockresdet {

	private static final Logger logger = Logger
			.getLogger(InsertStockresdet.class);

	public static boolean Stockresdetinsert(String rsloccode, String rsresno,
			String rsresseq, String rssku, String rsclasscode, String rsstyle,
			String rscolor, String rssize, String rsqty, String rsstatus,
			String rsunresby, String rsunresdate, String rsunrestime,
			String rsrowguid, Timestamp rslastupddt, String frdatabase)
			throws SQLException {

		boolean result = true;
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		String insertTableSQL;

		try {

			if (Objects.equals(frdatabase, "Oracle")) {
				// dbConnection = Mssql.getDBConnection();

				/*
				HikariMssql Mssqlpool = HikariMssql.getInstance();
				dbConnection = Mssqlpool.getConnection();
				*/
				HikariMerge Mergepool = HikariMerge.getInstance();
				dbConnection = Mergepool.getConnection();

				insertTableSQL = "INSERT INTO STOCKRES_DET"
						+ "(LOC_CODE,RES_NO,RES_SEQ,SKU,CLASS_CODE,STYLE,COLOR,SIZE,QTY,STATUS,UNRES_BY,UNRES_DATE,UNRES_TIME,ROWGUID,LAST_UPD_DT) "
						+ "VALUES" + "(?,?,?,?,?,?,?,?,?,?,?,?,?," + "newid()"
						+ ",?)";

				preparedStatement = dbConnection
						.prepareStatement(insertTableSQL);

				preparedStatement.setString(1, rsloccode);
				preparedStatement.setString(2, rsresno);
				preparedStatement.setString(3, rsresseq);
				preparedStatement.setString(4, rssku);
				preparedStatement.setString(5, rsclasscode);
				preparedStatement.setString(6, rsstyle);
				preparedStatement.setString(7, rscolor);
				preparedStatement.setString(8, rssize);
				preparedStatement.setString(9, rsqty);
				preparedStatement.setString(10, rsstatus);
				preparedStatement.setString(11, rsunresby);
				preparedStatement.setString(12, rsunresdate);
				preparedStatement.setString(13, rsunrestime);
				preparedStatement.setTimestamp(14, rslastupddt);

			} else {
				// dbConnection = OracleTo.getDBConnection();

				HikariQracleTo OrcaleFromTo = HikariQracleTo.getInstance();
				dbConnection = OrcaleFromTo.getConnection();

				insertTableSQL = "INSERT INTO STOCKRES_DET"
						+ "(LOC_CODE,RES_NO,RES_SEQ,SKU,CLASS_CODE,STYLE,COLOR,SIZE_,QTY,STATUS,UNRES_BY,UNRES_DATE,UNRES_TIME,ROWGUID,LAST_UPD_DT) "
						+ "VALUES" + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

				preparedStatement = dbConnection
						.prepareStatement(insertTableSQL);

				preparedStatement.setString(1, rsloccode);
				preparedStatement.setString(2, rsresno);
				preparedStatement.setString(3, rsresseq);
				preparedStatement.setString(4, rssku);
				preparedStatement.setString(5, rsclasscode);
				preparedStatement.setString(6, rsstyle);
				preparedStatement.setString(7, rscolor);
				preparedStatement.setString(8, rssize);
				preparedStatement.setString(9, rsqty);
				preparedStatement.setString(10, rsstatus);
				preparedStatement.setString(11, rsunresby);
				preparedStatement.setString(12, rsunresdate);
				preparedStatement.setString(13, rsunrestime);
				preparedStatement.setString(14, rsrowguid);
				preparedStatement.setTimestamp(15, rslastupddt);

			}

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

	public static boolean Stockresdetupdate(String rsloccode, String rsresno,
			String rsresseq, String rssku, String rsclasscode, String rsstyle,
			String rscolor, String rssize, String rsqty, String rsstatus,
			String rsunresby, String rsunresdate, String rsunrestime,
			String rsrowguid, Timestamp rslastupddt, String frdatabase)
			throws SQLException {

		boolean result = true;
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		String updateSQL;

		try {

			if (Objects.equals(frdatabase, "Oracle")) {
				// dbConnection = Mssql.getDBConnection();

				/*
				HikariMssql Mssqlpool = HikariMssql.getInstance();
				dbConnection = Mssqlpool.getConnection();
				*/
				HikariMerge Mergepool = HikariMerge.getInstance();
				dbConnection = Mergepool.getConnection();


				updateSQL = "UPDATE STOCKRES_DET " + "SET  SKU         = ? "
						+ ", CLASS_CODE  = ? " + ", STYLE       = ? "
						+ ", COLOR       = ? " + ", SIZE       = ? "
						+ ", QTY         = ? " + ", STATUS      = ? "
						+ ", UNRES_BY    = ? " + ", UNRES_DATE  = ? "
						+ ", UNRES_TIME  = ? " + ", LAST_UPD_DT = ? "
						+ "WHERE LOC_CODE  = ? " + "AND RES_NO      = ? "
						+ "AND RES_SEQ     = ? ";
			} else {
				// dbConnection = OracleTo.getDBConnection();

				HikariQracleTo OrcaleFromTo = HikariQracleTo.getInstance();
				dbConnection = OrcaleFromTo.getConnection();

				updateSQL = "UPDATE STOCKRES_DET " + "SET  SKU         = ? "
						+ ", CLASS_CODE  = ? " + ", STYLE       = ? "
						+ ", COLOR       = ? " + ", SIZE_       = ? "
						+ ", QTY         = ? " + ", STATUS      = ? "
						+ ", UNRES_BY    = ? " + ", UNRES_DATE  = ? "
						+ ", UNRES_TIME  = ? " + ", LAST_UPD_DT = ? "
						+ "WHERE LOC_CODE  = ? " + "AND RES_NO      = ? "
						+ "AND RES_SEQ     = ? ";
			}

			preparedStatement = dbConnection.prepareStatement(updateSQL);

			preparedStatement.setString(1, rssku);
			preparedStatement.setString(2, rsclasscode);
			preparedStatement.setString(3, rsstyle);
			preparedStatement.setString(4, rscolor);
			preparedStatement.setString(5, rssize);
			preparedStatement.setString(6, rsqty);
			preparedStatement.setString(7, rsstatus);
			preparedStatement.setString(8, rsunresby);
			preparedStatement.setString(9, rsunresdate);
			preparedStatement.setString(10, rsunrestime);
			preparedStatement.setTimestamp(11, rslastupddt);
			preparedStatement.setString(12, rsloccode);
			preparedStatement.setString(13, rsresno);
			preparedStatement.setString(14, rsresseq);

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

	public static boolean Stockresdetchkexists(String entitykey,
			String frdatabase) throws SQLException {

		String[] parts = entitykey.split(",");
		String loccode = parts[0];
		String resno = parts[1];
		String resseq = parts[2];

		boolean result = false;
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		try {

			if (Objects.equals(frdatabase, "Oracle")) {
				// dbConnection = Mssql.getDBConnection();

				/*
				HikariMssql Mssqlpool = HikariMssql.getInstance();
				dbConnection = Mssqlpool.getConnection();
				*/
				HikariMerge Mergepool = HikariMerge.getInstance();
				dbConnection = Mergepool.getConnection();

			} else {
				// dbConnection = OracleTo.getDBConnection();

				HikariQracleTo OrcaleFromTo = HikariQracleTo.getInstance();
				dbConnection = OrcaleFromTo.getConnection();
			}

			String selectSQL = "SELECT LOC_CODE " + "FROM stockres_det "
					+ "where LOC_CODE ='" + loccode + "'" + " and RES_NO ='"
					+ resno + "'" + " and RES_SEQ ='" + resseq + "'";

			// logger.info(selectSQL);

			preparedStatement = dbConnection.prepareStatement(selectSQL);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				String rschktxdate = rs.getString("LOC_CODE");

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
