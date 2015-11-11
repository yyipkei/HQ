package com.bridge.insertsales;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Objects;

import org.apache.log4j.Logger;

import com.bridge.main.HikariMssql;
import com.bridge.main.HikariQracleTo;

public class InsertSagoaaction {
	private static final Logger logger = Logger.getLogger(InsertSagoaaction.class);

	public static boolean Sagoaactioninsert(String rsrowid, String rstxdate,
											String rsloccode, String rsregno, String rstxno, String rsseqno,
											String rsactionqty, Timestamp rsactiondate, String rsaction,
											String rsreleasecode, String rssalestxno, Timestamp rsmodifieddate,
											String rsmodifiedby, String rsremark, Timestamp rslastupddt,
											String frdatabase) throws SQLException {

		boolean result = true;
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		try {

			if (Objects.equals(frdatabase, "Oracle")) {
				// dbConnection = Mssql.getDBConnection();

				HikariMssql Mssqlpool = HikariMssql.getInstance();
				dbConnection = Mssqlpool.getConnection();

				String insertTableSQL = "INSERT INTO SAGOA_ACTION"
						+ "(TX_DATE,LOC_CODE,REG_NO,TX_NO,SEQ_NO,ACTION_QTY,ACTION_DATE,ACTION,RELEASE_CODE,SALES_TX_NO,MODIFIED_DATE,MODIFIED_BY,REMARK,LAST_UPD_DT) "
						+ "VALUES" + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

				preparedStatement = dbConnection.prepareStatement(insertTableSQL);

				preparedStatement.setString(1, rstxdate);
				preparedStatement.setString(2, rsloccode);
				preparedStatement.setString(3, rsregno);
				preparedStatement.setString(4, rstxno);
				preparedStatement.setString(5, rsseqno);
				preparedStatement.setString(6, rsactionqty);
				preparedStatement.setTimestamp(7, rsactiondate);
				preparedStatement.setString(8, rsaction);
				preparedStatement.setString(9, rsreleasecode);
				preparedStatement.setString(10, rssalestxno);
				preparedStatement.setTimestamp(11, rsmodifieddate);
				preparedStatement.setString(12, rsmodifiedby);
				preparedStatement.setString(13, rsremark);
				preparedStatement.setTimestamp(14, rslastupddt);

			} else {
				// dbConnection = OracleTo.getDBConnection();

				HikariQracleTo OrcaleFromTo = HikariQracleTo.getInstance();
				dbConnection = OrcaleFromTo.getConnection();

				String insertTableSQL = "INSERT INTO SAGOA_ACTION"
						+ "(ROW_ID,TX_DATE,LOC_CODE,REG_NO,TX_NO,SEQ_NO,ACTION_QTY,ACTION_DATE,ACTION,RELEASE_CODE,SALES_TX_NO,MODIFIED_DATE,MODIFIED_BY,REMARK,LAST_UPD_DT) "
						+ "VALUES" + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

				preparedStatement = dbConnection.prepareStatement(insertTableSQL);

				preparedStatement.setString(1, rsrowid);
				preparedStatement.setString(2, rstxdate);
				preparedStatement.setString(3, rsloccode);
				preparedStatement.setString(4, rsregno);
				preparedStatement.setString(5, rstxno);
				preparedStatement.setString(6, rsseqno);
				preparedStatement.setString(7, rsactionqty);
				preparedStatement.setTimestamp(8, rsactiondate);
				preparedStatement.setString(9, rsaction);
				preparedStatement.setString(10, rsreleasecode);
				preparedStatement.setString(11, rssalestxno);
				preparedStatement.setTimestamp(12, rsmodifieddate);
				preparedStatement.setString(13, rsmodifiedby);
				preparedStatement.setString(14, rsremark);
				preparedStatement.setTimestamp(15, rslastupddt);

			}

			/*
			String insertTableSQL = "INSERT INTO SAGOA_ACTION"
					+ "(ROW_ID,TX_DATE,LOC_CODE,REG_NO,TX_NO,SEQ_NO,ACTION_QTY,ACTION_DATE,ACTION,RELEASE_CODE,SALES_TX_NO,MODIFIED_DATE,MODIFIED_BY,REMARK,LAST_UPD_DT) "
					+ "VALUES" + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

			preparedStatement = dbConnection.prepareStatement(insertTableSQL);

			preparedStatement.setString(1, rsrowid);
			preparedStatement.setString(2, rstxdate);
			preparedStatement.setString(3, rsloccode);
			preparedStatement.setString(4, rsregno);
			preparedStatement.setString(5, rstxno);
			preparedStatement.setString(6, rsseqno);
			preparedStatement.setString(7, rsactionqty);
			preparedStatement.setTimestamp(8, rsactiondate);
			preparedStatement.setString(9, rsaction);
			preparedStatement.setString(10, rsreleasecode);
			preparedStatement.setString(11, rssalestxno);
			preparedStatement.setTimestamp(12, rsmodifieddate);
			preparedStatement.setString(13, rsmodifiedby);
			preparedStatement.setString(14, rsremark);
			preparedStatement.setTimestamp(15, rslastupddt);
*/
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

	public static boolean Sagoaactionupdate(String rsrowid, String rstxdate,
											String rsloccode, String rsregno, String rstxno, String rsseqno,
											String rsactionqty, Timestamp rsactiondate, String rsaction,
											String rsreleasecode, String rssalestxno, Timestamp rsmodifieddate,
											String rsmodifiedby, String rsremark, Timestamp rslastupddt,
											String frdatabase) throws SQLException {

		boolean result = true;
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		try {

			if (Objects.equals(frdatabase, "Oracle")) {
				// dbConnection = Mssql.getDBConnection();

				HikariMssql Mssqlpool = HikariMssql.getInstance();
				dbConnection = Mssqlpool.getConnection();
			} else {
				// dbConnection = OracleTo.getDBConnection();

				HikariQracleTo OrcaleFromTo = HikariQracleTo.getInstance();
				dbConnection = OrcaleFromTo.getConnection();
			}

			String updateSQL = "UPDATE SAGOA_ACTION "
					+ "SET  ACTION_QTY    = ? " + ", ACTION_DATE   = ? "
					+ ", ACTION        = ? " + ", RELEASE_CODE  = ? "
					+ ", SALES_TX_NO   = ? " + ", MODIFIED_DATE = ? "
					+ ", MODIFIED_BY   = ? " + ", REMARK        = ? "
					+ ", LAST_UPD_DT   = ? " + "WHERE ROW_ID      = ? "
					+ "AND TX_DATE       = ? " + "AND LOC_CODE      = ? "
					+ "AND REG_NO        = ? " + "AND TX_NO         = ? "
					+ "AND SEQ_NO        = ? ";

			preparedStatement = dbConnection.prepareStatement(updateSQL);

			preparedStatement.setString(1, rsactionqty);
			preparedStatement.setTimestamp(2, rsactiondate);
			preparedStatement.setString(3, rsaction);
			preparedStatement.setString(4, rsreleasecode);
			preparedStatement.setString(5, rssalestxno);
			preparedStatement.setTimestamp(6, rsmodifieddate);
			preparedStatement.setString(7, rsmodifiedby);
			preparedStatement.setString(8, rsremark);
			preparedStatement.setTimestamp(9, rslastupddt);
			preparedStatement.setString(10, rsrowid);
			preparedStatement.setString(11, rstxdate);
			preparedStatement.setString(12, rsloccode);
			preparedStatement.setString(13, rsregno);
			preparedStatement.setString(14, rstxno);
			preparedStatement.setString(15, rsseqno);

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

	public static boolean Sagoaactionchkexists(String entitykey,
											   String frdatabase) throws SQLException {

		String[] parts = entitykey.split(",");
		String txdate = parts[0];
		String loccode = parts[1];
		String regno = parts[2];
		String txno = parts[3];
		String seqno = parts[4];

		boolean result = false;
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		try {

			if (Objects.equals(frdatabase, "Oracle")) {
				// dbConnection = Mssql.getDBConnection();

				HikariMssql Mssqlpool = HikariMssql.getInstance();
				dbConnection = Mssqlpool.getConnection();
			} else {
				// dbConnection = OracleTo.getDBConnection();

				HikariQracleTo OrcaleFromTo = HikariQracleTo.getInstance();
				dbConnection = OrcaleFromTo.getConnection();
			}

			String selectSQL = "SELECT TX_DATE " + "FROM SAGOA_ACTION "
					+ "where tx_date ='" + txdate + "'" + "and LOC_CODE ='"
					+ loccode + "'" + "and reg_no='" + regno + "'"
					+ "and tx_no ='" + txno + "'" + "and seq_no ='" + seqno
					+ "'";

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
