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

public class InsertMnstaffcard {

	private static final Logger logger = Logger
			.getLogger(InsertMnstaffcard.class);

	public static boolean Mnstaffcardinsert(String rscardnum, String rscardtype,
			String rsstaffid, String rshkid, String rsholdername,
			String rscreditlmt, String rslocdesc, String rsdeptdesc,
			Timestamp rsissuedt, Timestamp rsreturndt, Timestamp rsstopdt,
			Timestamp rsrepldt, Timestamp rstermdt, String rssubcard,
			String rssubhkid, String rsholdername2, String rsstatus,
			String rsautopay, String rsremark, String rslastupdusr,
			Timestamp rslastupddt, String rslastupdver, String rsrowguid,
			String rsentitykey, String frdatabase) throws SQLException {

		boolean result = true;
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		String insertTableSQL;

		insertTableSQL = "INSERT INTO MN_STAFFCARD (CARD_NUM,CARD_TYPE,STAFF_ID,HK_ID,"
				+ "HOLDER_NAME,CREDIT_LMT,LOC_DESC,DEPT_DESC,ISSUE_DT,RETURN_DT,STOP_DT,"
				+ "REPL_DT,TERM_DT,SUBCARD,SUB_HK_ID,HOLDER_NAME2,STATUS,AUTOPAY,REMARK,"
				+ "LAST_UPD_USR,LAST_UPD_DT,LAST_UPD_VER,ROWGUID,ENTITY_KEY) "
				+ "VALUES ( ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,? )";

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

			preparedStatement = dbConnection.prepareStatement(insertTableSQL);

			preparedStatement.setString(1, rscardnum);
			preparedStatement.setString(2, rscardtype);
			preparedStatement.setString(3, rsstaffid);
			preparedStatement.setString(4, rshkid);
			preparedStatement.setString(5, rsholdername);
			preparedStatement.setString(6, rscreditlmt);
			preparedStatement.setString(7, rslocdesc);
			preparedStatement.setString(8, rsdeptdesc);
			preparedStatement.setTimestamp(9, rsissuedt);
			preparedStatement.setTimestamp(10, rsreturndt);
			preparedStatement.setTimestamp(11, rsstopdt);
			preparedStatement.setTimestamp(12, rsrepldt);
			preparedStatement.setTimestamp(13, rstermdt);
			preparedStatement.setString(14, rssubcard);
			preparedStatement.setString(15, rssubhkid);
			preparedStatement.setString(16, rsholdername2);
			preparedStatement.setString(17, rsstatus);
			preparedStatement.setString(18, rsautopay);
			preparedStatement.setString(19, rsremark);
			preparedStatement.setString(20, rslastupdusr);
			preparedStatement.setTimestamp(21, rslastupddt);
			preparedStatement.setString(22, rslastupdver);
			preparedStatement.setString(23, rsrowguid);
			preparedStatement.setString(24, rsentitykey);

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

	public static boolean Mnstaffcardupdate(String rscardnum, String rscardtype,
			String rsstaffid, String rshkid, String rsholdername,
			String rscreditlmt, String rslocdesc, String rsdeptdesc,
			Timestamp rsissuedt, Timestamp rsreturndt, Timestamp rsstopdt,
			Timestamp rsrepldt, Timestamp rstermdt, String rssubcard,
			String rssubhkid, String rsholdername2, String rsstatus,
			String rsautopay, String rsremark, String rslastupdusr,
			Timestamp rslastupddt, String rslastupdver, String rsrowguid,
			String rsentitykey, String frdatabase) throws SQLException {

		boolean result = true;
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		String updateSQL;

		updateSQL = "UPDATE MN_STAFFCARD " + "SET CARD_NUM   = ? "
				+ ", CARD_TYPE    = ? " + ", STAFF_ID     = ? "
				+ ", HK_ID        = ? " + ", HOLDER_NAME  = ? "
				+ ", CREDIT_LMT   = ? " + ", LOC_DESC     = ? "
				+ ", DEPT_DESC    = ? " + ", ISSUE_DT     = ? "
				+ ", RETURN_DT    = ? " + ", STOP_DT      = ? "
				+ ", REPL_DT      = ? " + ", TERM_DT      = ? "
				+ ", SUBCARD      = ? " + ", SUB_HK_ID    = ? "
				+ ", HOLDER_NAME2 = ? " + ", STATUS       = ? "
				+ ", AUTOPAY      = ? " + ", REMARK       = ? "
				+ ", LAST_UPD_USR = ? " + ", LAST_UPD_DT  = ? "
				+ ", LAST_UPD_VER = ? " + ", ROWGUID      = ? "
				+ "WHERE  ENTITY_KEY   = ? ";

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

			preparedStatement = dbConnection.prepareStatement(updateSQL);

			preparedStatement.setString(1, rscardnum);
			preparedStatement.setString(2, rscardtype);
			preparedStatement.setString(3, rsstaffid);
			preparedStatement.setString(4, rshkid);
			preparedStatement.setString(5, rsholdername);
			preparedStatement.setString(6, rscreditlmt);
			preparedStatement.setString(7, rslocdesc);
			preparedStatement.setString(8, rsdeptdesc);
			preparedStatement.setTimestamp(9, rsissuedt);
			preparedStatement.setTimestamp(10, rsreturndt);
			preparedStatement.setTimestamp(11, rsstopdt);
			preparedStatement.setTimestamp(12, rsrepldt);
			preparedStatement.setTimestamp(13, rstermdt);
			preparedStatement.setString(14, rssubcard);
			preparedStatement.setString(15, rssubhkid);
			preparedStatement.setString(16, rsholdername2);
			preparedStatement.setString(17, rsstatus);
			preparedStatement.setString(18, rsautopay);
			preparedStatement.setString(19, rsremark);
			preparedStatement.setString(20, rslastupdusr);
			preparedStatement.setTimestamp(21, rslastupddt);
			preparedStatement.setString(22, rslastupdver);
			preparedStatement.setString(23, rsrowguid);
			preparedStatement.setString(24, rsentitykey);

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

	public static boolean Mnstaffcardchkexists(String entitykey,
			String frdatabase) throws SQLException {
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

			String selectSQL = "SELECT CARD_NUM " + "FROM MN_STAFFCARD "
					+ "where ENTITY_KEY ='" + entitykey + "'";

			// logger.info(selectSQL);

			preparedStatement = dbConnection.prepareStatement(selectSQL);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				String rschktxdate = rs.getString("CARD_NUM");

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
