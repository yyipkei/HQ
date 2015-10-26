package com.bridge.insertdeposit;

import com.bridge.main.HikariMerge;
import com.bridge.main.HikariQracleTo;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.Objects;

/**
 * Created by keyip on 12-Jun-15.
 */
public class InsertBrreg {

	private static final Logger logger = Logger.getLogger(InsertBrreg.class);

	public static boolean Brreginsert(String rsbridekey, Timestamp rsweddate,
			String rsbridename, String rsgroomname, Timestamp rsrecepdate,
			String rsaddr1, String rsaddr2, String rsaddr3, String rsdeladdr1,
			String rsdeladdr2, String rsdeladdr3, Timestamp rsdeldate,
			String rstelhome, String rstelcompany, String rstelcompanyex,
			String rstelmobile, String rstelfax, String rsteldel,
			String rstelfaxdel, String rsemailaddr, String rspcard,
			String rsremark, String rscreatedloc, Timestamp rsupdateddatetime,
			String rsupdateduser, String rslastgiftnum, String rslastguestnum,
			Timestamp rstimestamp, String rsrowguid, Timestamp rslastupddt,
			String frdatabase) throws SQLException {

		boolean result = true;
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		String insertTableSQL;

		try {

			if (Objects.equals(frdatabase, "Oracle")) {
				// dbConnection = Merge.getDBConnection();

				HikariMerge Mergepool = HikariMerge.getInstance();
				dbConnection = Mergepool.getConnection();

				insertTableSQL = "INSERT INTO BR_REG"
						+ "(BRIDE_KEY,WED_DATE,BRIDE_NAME,GROOM_NAME,RECEP_DATE,ADDR_1,ADDR_2,ADDR_3,DEL_ADDR_1,DEL_ADDR_2,"
						+ "DEL_ADDR_3,DEL_DATE,TEL_HOME,TEL_COMPANY,TEL_COMPANY_EX,TEL_MOBILE,TEL_FAX,TEL_DEL,TEL_FAX_DEL,EMAIL_ADDR,"
						+ "P_CARD,REMARK,CREATED_LOC,UPDATED_DATETIME,UPDATED_USER,LAST_GIFT_NUM,LAST_GUEST_NUM,TIME_STAMP,ROWGUID,LAST_UPD_DT) "
						+ "VALUES"
						+ "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
						+ "newid()" + ",?)";

				preparedStatement = dbConnection
						.prepareStatement(insertTableSQL);

				preparedStatement.setString(1, rsbridekey);
				preparedStatement.setTimestamp(2, rsweddate);
				preparedStatement.setString(3, rsbridename);
				preparedStatement.setString(4, rsgroomname);
				preparedStatement.setTimestamp(5, rsrecepdate);
				preparedStatement.setString(6, rsaddr1);
				preparedStatement.setString(7, rsaddr2);
				preparedStatement.setString(8, rsaddr3);
				preparedStatement.setString(9, rsdeladdr1);
				preparedStatement.setString(10, rsdeladdr2);
				preparedStatement.setString(11, rsdeladdr3);
				preparedStatement.setTimestamp(12, rsdeldate);
				preparedStatement.setString(13, rstelhome);
				preparedStatement.setString(14, rstelcompany);
				preparedStatement.setString(15, rstelcompanyex);
				preparedStatement.setString(16, rstelmobile);
				preparedStatement.setString(17, rstelfax);
				preparedStatement.setString(18, rsteldel);
				preparedStatement.setString(19, rstelfaxdel);
				preparedStatement.setString(20, rsemailaddr);
				preparedStatement.setString(21, rspcard);
				preparedStatement.setString(22, rsremark);
				preparedStatement.setString(23, rscreatedloc);
				preparedStatement.setTimestamp(24, rsupdateddatetime);
				preparedStatement.setString(25, rsupdateduser);
				preparedStatement.setString(26, rslastgiftnum);
				preparedStatement.setString(27, rslastguestnum);
				preparedStatement.setTimestamp(28, rstimestamp);
				preparedStatement.setTimestamp(29, rslastupddt);

			} else {
				// dbConnection = OracleTo.getDBConnection();

				HikariQracleTo OrcaleFromTo = HikariQracleTo.getInstance();
				dbConnection = OrcaleFromTo.getConnection();

				insertTableSQL = "INSERT INTO BR_REG"
						+ "(BRIDE_KEY,WED_DATE,BRIDE_NAME,GROOM_NAME,RECEP_DATE,ADDR_1,ADDR_2,ADDR_3,DEL_ADDR_1,DEL_ADDR_2,"
						+ "DEL_ADDR_3,DEL_DATE,TEL_HOME,TEL_COMPANY,TEL_COMPANY_EX,TEL_MOBILE,TEL_FAX,TEL_DEL,TEL_FAX_DEL,EMAIL_ADDR,"
						+ "P_CARD,REMARK,CREATED_LOC,UPDATED_DATETIME,UPDATED_USER,LAST_GIFT_NUM,LAST_GUEST_NUM,TIME_STAMP,ROWGUID,LAST_UPD_DT) "
						+ "VALUES"
						+ "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

				preparedStatement = dbConnection
						.prepareStatement(insertTableSQL);

				preparedStatement.setString(1, rsbridekey);
				preparedStatement.setTimestamp(2, rsweddate);
				preparedStatement.setString(3, rsbridename);
				preparedStatement.setString(4, rsgroomname);
				preparedStatement.setTimestamp(5, rsrecepdate);
				preparedStatement.setString(6, rsaddr1);
				preparedStatement.setString(7, rsaddr2);
				preparedStatement.setString(8, rsaddr3);
				preparedStatement.setString(9, rsdeladdr1);
				preparedStatement.setString(10, rsdeladdr2);
				preparedStatement.setString(11, rsdeladdr3);
				preparedStatement.setTimestamp(12, rsdeldate);
				preparedStatement.setString(13, rstelhome);
				preparedStatement.setString(14, rstelcompany);
				preparedStatement.setString(15, rstelcompanyex);
				preparedStatement.setString(16, rstelmobile);
				preparedStatement.setString(17, rstelfax);
				preparedStatement.setString(18, rsteldel);
				preparedStatement.setString(19, rstelfaxdel);
				preparedStatement.setString(20, rsemailaddr);
				preparedStatement.setString(21, rspcard);
				preparedStatement.setString(22, rsremark);
				preparedStatement.setString(23, rscreatedloc);
				preparedStatement.setTimestamp(24, rsupdateddatetime);
				preparedStatement.setString(25, rsupdateduser);
				preparedStatement.setString(26, rslastgiftnum);
				preparedStatement.setString(27, rslastguestnum);
				preparedStatement.setTimestamp(28, rstimestamp);
				preparedStatement.setString(29, rsrowguid);
				preparedStatement.setTimestamp(30, rslastupddt);
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

	public static boolean Brregupdate(String rsbridekey, Timestamp rsweddate,
			String rsbridename, String rsgroomname, Timestamp rsrecepdate,
			String rsaddr1, String rsaddr2, String rsaddr3, String rsdeladdr1,
			String rsdeladdr2, String rsdeladdr3, Timestamp rsdeldate,
			String rstelhome, String rstelcompany, String rstelcompanyex,
			String rstelmobile, String rstelfax, String rsteldel,
			String rstelfaxdel, String rsemailaddr, String rspcard,
			String rsremark, String rscreatedloc, Timestamp rsupdateddatetime,
			String rsupdateduser, String rslastgiftnum, String rslastguestnum,
			Timestamp rstimestamp, String rsrowguid, Timestamp rslastupddt,
			String frdatabase) throws SQLException {

		boolean result = true;
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		try {

			if (Objects.equals(frdatabase, "Oracle")) {
				// dbConnection = Merge.getDBConnection();

				HikariMerge Mergepool = HikariMerge.getInstance();
				dbConnection = Mergepool.getConnection();
			} else {
				// dbConnection = OracleTo.getDBConnection();

				HikariQracleTo OrcaleFromTo = HikariQracleTo.getInstance();
				dbConnection = OrcaleFromTo.getConnection();
			}

			String updateSQL = "UPDATE BR_REG " + "SET  WED_DATE         = ? "
					+ ", BRIDE_NAME       = ? " + ", GROOM_NAME       = ? "
					+ ", RECEP_DATE       = ? " + ", ADDR_1           = ? "
					+ ", ADDR_2           = ? " + ", ADDR_3           = ? "
					+ ", DEL_ADDR_1       = ? " + ", DEL_ADDR_2       = ? "
					+ ", DEL_ADDR_3       = ? " + ", DEL_DATE         = ? "
					+ ", TEL_HOME         = ? " + ", TEL_COMPANY      = ? "
					+ ", TEL_COMPANY_EX   = ? " + ", TEL_MOBILE       = ? "
					+ ", TEL_FAX          = ? " + ", TEL_DEL          = ? "
					+ ", TEL_FAX_DEL      = ? " + ", EMAIL_ADDR       = ? "
					+ ", P_CARD           = ? " + ", REMARK           = ? "
					+ ", CREATED_LOC      = ? " + ", UPDATED_DATETIME = ? "
					+ ", UPDATED_USER     = ? " + ", LAST_GIFT_NUM    = ? "
					+ ", LAST_GUEST_NUM   = ? " + ", TIME_STAMP       = ? "
					+ ", LAST_UPD_DT      = ? " + "WHERE BRIDE_KEY      = ? ";

			preparedStatement = dbConnection.prepareStatement(updateSQL);

			preparedStatement.setTimestamp(1, rsweddate);
			preparedStatement.setString(2, rsbridename);
			preparedStatement.setString(3, rsgroomname);
			preparedStatement.setTimestamp(4, rsrecepdate);
			preparedStatement.setString(5, rsaddr1);
			preparedStatement.setString(6, rsaddr2);
			preparedStatement.setString(7, rsaddr3);
			preparedStatement.setString(8, rsdeladdr1);
			preparedStatement.setString(9, rsdeladdr2);
			preparedStatement.setString(10, rsdeladdr3);
			preparedStatement.setTimestamp(11, rsdeldate);
			preparedStatement.setString(12, rstelhome);
			preparedStatement.setString(13, rstelcompany);
			preparedStatement.setString(14, rstelcompanyex);
			preparedStatement.setString(15, rstelmobile);
			preparedStatement.setString(16, rstelfax);
			preparedStatement.setString(17, rsteldel);
			preparedStatement.setString(18, rstelfaxdel);
			preparedStatement.setString(19, rsemailaddr);
			preparedStatement.setString(20, rspcard);
			preparedStatement.setString(21, rsremark);
			preparedStatement.setString(22, rscreatedloc);
			preparedStatement.setTimestamp(23, rsupdateddatetime);
			preparedStatement.setString(24, rsupdateduser);
			preparedStatement.setString(25, rslastgiftnum);
			preparedStatement.setString(26, rslastguestnum);
			preparedStatement.setTimestamp(27, rstimestamp);
			preparedStatement.setTimestamp(28, rslastupddt);
			preparedStatement.setString(29, rsbridekey);

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

	public static boolean Brregchkexists(String entitykey, String frdatabase)
			throws SQLException {

		String[] parts = entitykey.split(",");
		String bridekey = parts[0];

		boolean result = false;
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		try {

			if (Objects.equals(frdatabase, "Oracle")) {
				// dbConnection = Merge.getDBConnection();

				HikariMerge Mergepool = HikariMerge.getInstance();
				dbConnection = Mergepool.getConnection();
			} else {
				// dbConnection = OracleTo.getDBConnection();

				HikariQracleTo OrcaleFromTo = HikariQracleTo.getInstance();
				dbConnection = OrcaleFromTo.getConnection();
			}

			String selectSQL = "SELECT WED_DATE " + "FROM BR_REG "
					+ "where BRIDE_KEY ='" + bridekey + "'";

			// logger.info(selectSQL);

			preparedStatement = dbConnection.prepareStatement(selectSQL);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				String rschktxdate = rs.getString("WED_DATE");

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
