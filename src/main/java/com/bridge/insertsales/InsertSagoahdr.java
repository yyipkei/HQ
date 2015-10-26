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

public class InsertSagoahdr {

	private static final Logger logger = Logger.getLogger(InsertSagoahdr.class);

	public static boolean Sagoahdrinsert(String rstxdate, String rsloccode,
			String rsregno, String rstxno, String rstxtime, String rstxtype,
			String rsvoidby, String rscashierno, String rsexprtndate,
			String rspurposecde, String rsotherinfo, String rsrecvtype,
			String rsstaffid, String rsstaffname, String rsdeptcde,
			String rsvipno, String rscustomername, String rscompname,
			String rswriteoffdept, Timestamp rslastupddt, String frdatabase)
			throws SQLException {

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

			String insertTableSQL = "INSERT INTO SAGOA_HDR "
					+ "(TX_DATE,LOC_CODE,REG_NO,TX_NO,TX_TIME,TX_TYPE,VOID_BY,CASHIER_NO,EXP_RTN_DATE,PURPOSE_CDE,OTHER_INFO,RECV_TYPE,STAFF_ID,STAFF_NAME,DEPT_CDE,VIP_NO,CUSTOMER_NAME,COMP_NAME,WRITE_OFF_DEPT,LAST_UPD_DT) "
					+ "VALUES" + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

			preparedStatement = dbConnection.prepareStatement(insertTableSQL);

			preparedStatement.setString(1, rstxdate);
			preparedStatement.setString(2, rsloccode);
			preparedStatement.setString(3, rsregno);
			preparedStatement.setString(4, rstxno);
			preparedStatement.setString(5, rstxtime);
			preparedStatement.setString(6, rstxtype);
			preparedStatement.setString(7, rsvoidby);
			preparedStatement.setString(8, rscashierno);
			preparedStatement.setString(9, rsexprtndate);
			preparedStatement.setString(10, rspurposecde);
			preparedStatement.setString(11, rsotherinfo);
			preparedStatement.setString(12, rsrecvtype);
			preparedStatement.setString(13, rsstaffid);
			preparedStatement.setString(14, rsstaffname);
			preparedStatement.setString(15, rsdeptcde);
			preparedStatement.setString(16, rsvipno);
			preparedStatement.setString(17, rscustomername);
			preparedStatement.setString(18, rscompname);
			preparedStatement.setString(19, rswriteoffdept);
			preparedStatement.setTimestamp(20, rslastupddt);

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

	public static boolean Sagoahdrupdate(String rstxdate, String rsloccode,
			String rsregno, String rstxno, String rstxtime, String rstxtype,
			String rsvoidby, String rscashierno, String rsexprtndate,
			String rspurposecde, String rsotherinfo, String rsrecvtype,
			String rsstaffid, String rsstaffname, String rsdeptcde,
			String rsvipno, String rscustomername, String rscompname,
			String rswriteoffdept, Timestamp rslastupddt, String frdatabase)
			throws SQLException {

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

			String updateSQL = "UPDATE SAGOA_HDR " + "SET TX_TIME        = ? "
					+ ", TX_TYPE        = ? " + ", VOID_BY        = ? "
					+ ", CASHIER_NO     = ? " + ", EXP_RTN_DATE   = ? "
					+ ", PURPOSE_CDE    = ? " + ", OTHER_INFO     = ? "
					+ ", RECV_TYPE      = ? " + ", STAFF_ID       = ? "
					+ ", STAFF_NAME     = ? " + ", DEPT_CDE       = ? "
					+ ", VIP_NO         = ? " + ", CUSTOMER_NAME  = ? "
					+ ", COMP_NAME      = ? " + ", WRITE_OFF_DEPT = ? "
					+ ", LAST_UPD_DT    = ? " + "WHERE TX_DATE      = ? "
					+ "AND LOC_CODE       = ? " + "AND REG_NO         = ? "
					+ "AND TX_NO          = ? ";

			preparedStatement = dbConnection.prepareStatement(updateSQL);

			preparedStatement.setString(1, rstxtime);
			preparedStatement.setString(2, rstxtype);
			preparedStatement.setString(3, rsvoidby);
			preparedStatement.setString(4, rscashierno);
			preparedStatement.setString(5, rsexprtndate);
			preparedStatement.setString(6, rspurposecde);
			preparedStatement.setString(7, rsotherinfo);
			preparedStatement.setString(8, rsrecvtype);
			preparedStatement.setString(9, rsstaffid);
			preparedStatement.setString(10, rsstaffname);
			preparedStatement.setString(11, rsdeptcde);
			preparedStatement.setString(12, rsvipno);
			preparedStatement.setString(13, rscustomername);
			preparedStatement.setString(14, rscompname);
			preparedStatement.setString(15, rswriteoffdept);
			preparedStatement.setTimestamp(16, rslastupddt);
			preparedStatement.setString(17, rstxdate);
			preparedStatement.setString(18, rsloccode);
			preparedStatement.setString(19, rsregno);
			preparedStatement.setString(20, rstxno);

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

	public static boolean Sagoahdrchkexists(String entitykey, String frdatabase)
			throws SQLException {

		String[] parts = entitykey.split(",");
		String txdate = parts[0];
		String loccode = parts[1];
		String regno = parts[2];
		String txno = parts[3];

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

			String selectSQL = "SELECT TX_DATE " + "FROM SAGOA_HDR "
					+ "where tx_date ='" + txdate + "'" + "and LOC_CODE ='"
					+ loccode + "'" + "and reg_no='" + regno + "'"
					+ "and tx_no ='" + txno + "'";

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
