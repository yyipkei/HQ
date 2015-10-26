package com.bridge.insertdeposit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Objects;

import org.apache.log4j.Logger;

import com.bridge.main.HikariMerge;
import com.bridge.main.HikariQracleTo;

/**
 * Created by keyip on 12-Jun-15.
 */
public class InsertBrdpdet {
	private static final Logger logger = Logger.getLogger(InsertBrdpdet.class);

	public static boolean Brdpdetinsert(String rsbrdpno, String rsbridalregno,
			String rsissueloccode, String rstxdate, String rsloccode,
			String rsregno, String rstxno, String rssettleamt, String rsstatus,
			String rsmodifiedby, String rsmodifieddate, String rsmodifiedtime,
			String rsrowguid, Timestamp rslastupddt, String frdatabase)
			throws SQLException {

		boolean result = true;
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		String insertTableSQL;

		try {

			if (Objects.equals(frdatabase, "Oracle")) {
				// dbConnection = Merge.getDBConnection();

				HikariMerge Mergepool = HikariMerge.getInstance();
				dbConnection = Mergepool.getConnection();

				insertTableSQL = "INSERT INTO BRDP_DET"
						+ "(BRDP_NO,BRIDAL_REG_NO,ISSUE_LOC_CODE,TX_DATE,LOC_CODE,REG_NO,TX_NO,SETTLE_AMT,STATUS,MODIFIED_BY,MODIFIED_DATE,MODIFIED_TIME,ROWGUID,LAST_UPD_DT) "
						+ "VALUES" + "(?,?,?,?,?,?,?,?,?,?,?,?," + "newid()"
						+ ",?)";

				preparedStatement = dbConnection
						.prepareStatement(insertTableSQL);

				preparedStatement.setString(1, rsbrdpno);
				preparedStatement.setString(2, rsbridalregno);
				preparedStatement.setString(3, rsissueloccode);
				preparedStatement.setString(4, rstxdate);
				preparedStatement.setString(5, rsloccode);
				preparedStatement.setString(6, rsregno);
				preparedStatement.setString(7, rstxno);
				preparedStatement.setString(8, rssettleamt);
				preparedStatement.setString(9, rsstatus);
				preparedStatement.setString(10, rsmodifiedby);
				preparedStatement.setString(11, rsmodifieddate);
				preparedStatement.setString(12, rsmodifiedtime);
				preparedStatement.setTimestamp(13, rslastupddt);

			} else {
				// dbConnection = OracleTo.getDBConnection();

				HikariQracleTo OrcaleFromTo = HikariQracleTo.getInstance();
				dbConnection = OrcaleFromTo.getConnection();

				insertTableSQL = "INSERT INTO BRDP_DET"
						+ "(BRDP_NO,BRIDAL_REG_NO,ISSUE_LOC_CODE,TX_DATE,LOC_CODE,REG_NO,TX_NO,SETTLE_AMT,STATUS,MODIFIED_BY,MODIFIED_DATE,MODIFIED_TIME,ROWGUID,LAST_UPD_DT) "
						+ "VALUES" + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

				preparedStatement = dbConnection
						.prepareStatement(insertTableSQL);

				preparedStatement.setString(1, rsbrdpno);
				preparedStatement.setString(2, rsbridalregno);
				preparedStatement.setString(3, rsissueloccode);
				preparedStatement.setString(4, rstxdate);
				preparedStatement.setString(5, rsloccode);
				preparedStatement.setString(6, rsregno);
				preparedStatement.setString(7, rstxno);
				preparedStatement.setString(8, rssettleamt);
				preparedStatement.setString(9, rsstatus);
				preparedStatement.setString(10, rsmodifiedby);
				preparedStatement.setString(11, rsmodifieddate);
				preparedStatement.setString(12, rsmodifiedtime);
				preparedStatement.setString(13, rsrowguid);
				preparedStatement.setTimestamp(14, rslastupddt);
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

	public static boolean Brdpdetupdate(String rsbrdpno, String rsbridalregno,
			String rsissueloccode, String rstxdate, String rsloccode,
			String rsregno, String rstxno, String rssettleamt, String rsstatus,
			String rsmodifiedby, String rsmodifieddate, String rsmodifiedtime,
			String rsrowguid, Timestamp rslastupddt, String frdatabase)
			throws SQLException {

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

			String updateSQL = "UPDATE BRDP_DET " + "SET  TX_DATE        = ? "
					+ ", LOC_CODE       = ? " + ", REG_NO         = ? "
					+ ", TX_NO          = ? " + ", SETTLE_AMT     = ? "
					+ ", STATUS         = ? " + ", MODIFIED_BY    = ? "
					+ ", MODIFIED_DATE  = ? " + ", MODIFIED_TIME  = ? "
					+ ", ROWGUID        = ? " + ", LAST_UPD_DT    = ? "
					+ "WHERE BRDP_NO      = ? " + "AND BRIDAL_REG_NO  = ? "
					+ "AND ISSUE_LOC_CODE = ? ";

			preparedStatement = dbConnection.prepareStatement(updateSQL);

			preparedStatement.setString(1, rstxdate);
			preparedStatement.setString(2, rsloccode);
			preparedStatement.setString(3, rsregno);
			preparedStatement.setString(4, rstxno);
			preparedStatement.setString(5, rssettleamt);
			preparedStatement.setString(6, rsstatus);
			preparedStatement.setString(7, rsmodifiedby);
			preparedStatement.setString(8, rsmodifieddate);
			preparedStatement.setString(9, rsmodifiedtime);
			preparedStatement.setString(10, rsrowguid);
			preparedStatement.setTimestamp(11, rslastupddt);
			preparedStatement.setString(12, rsbrdpno);
			preparedStatement.setString(13, rsbridalregno);
			preparedStatement.setString(14, rsissueloccode);

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

	public static boolean Brdpdetchkexists(String entitykey, String frdatabase)
			throws SQLException {

		String[] parts = entitykey.split(",");
		String brdpno = parts[0];
		String bridalregno = parts[1];
		String issueloccode = parts[2];

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

			String selectSQL = "SELECT TX_DATE " + "FROM BRDP_DET "
					+ "where BRDP_NO ='" + brdpno + "'"
					+ "and BRIDAL_REG_NO ='" + bridalregno + "'"
					+ "and ISSUE_LOC_CODE='" + issueloccode + "'";

			// logger.info(selectSQL);

			preparedStatement = dbConnection.prepareStatement(selectSQL);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				String rschktxdate = rs.getString("TX_DATE");

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
