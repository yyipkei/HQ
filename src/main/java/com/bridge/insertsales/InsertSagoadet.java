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

public class InsertSagoadet {

	private static final Logger logger = Logger.getLogger(InsertSagoadet.class);

	public static boolean Sagoadetinsert(String rstxdate, String rsloccode,
			String rsregno, String rstxno, String rsseqno, String rstxtype,
			String rsstatus, String rsvoid, String rssku, String rsqty,
			String rscururet, Timestamp rslastupddt, String frdatabase)
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

			String insertTableSQL = "INSERT INTO SAGOA_DET"
					+ "(TX_DATE,LOC_CODE,REG_NO,TX_NO,SEQ_NO,TX_TYPE,STATUS,VOID,SKU,QTY,CUR_URET,LAST_UPD_DT) "
					+ "VALUES" + "(?,?,?,?,?,?,?,?,?,?,?,?)";

			preparedStatement = dbConnection.prepareStatement(insertTableSQL);

			preparedStatement.setString(1, rstxdate);
			preparedStatement.setString(2, rsloccode);
			preparedStatement.setString(3, rsregno);
			preparedStatement.setString(4, rstxno);
			preparedStatement.setString(5, rsseqno);
			preparedStatement.setString(6, rstxtype);
			preparedStatement.setString(7, rsstatus);
			preparedStatement.setString(8, rsvoid);
			preparedStatement.setString(9, rssku);
			preparedStatement.setString(10, rsqty);
			preparedStatement.setString(11, rscururet);
			preparedStatement.setTimestamp(12, rslastupddt);

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

	public static boolean Sagoadetupdate(String rstxdate, String rsloccode,
			String rsregno, String rstxno, String rsseqno, String rstxtype,
			String rsstatus, String rsvoid, String rssku, String rsqty,
			String rscururet, Timestamp rslastupddt, String frdatabase)
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

			String updateSQL = "UPDATE SAGOA_DET " + "SET  TX_TYPE     = ? "
					+ ", STATUS      = ? " + ", VOID        = ? "
					+ ", SKU         = ? " + ", QTY         = ? "
					+ ", CUR_URET    = ? " + ", LAST_UPD_DT = ? "
					+ "WHERE TX_DATE   = ? " + "AND LOC_CODE    = ? "
					+ "AND REG_NO      = ? " + "AND TX_NO       = ? "
					+ "AND SEQ_NO      = ? ";

			preparedStatement = dbConnection.prepareStatement(updateSQL);

			preparedStatement.setString(1, rstxtype);
			preparedStatement.setString(2, rsstatus);
			preparedStatement.setString(3, rsvoid);
			preparedStatement.setString(4, rssku);
			preparedStatement.setString(5, rsqty);
			preparedStatement.setString(6, rscururet);
			preparedStatement.setTimestamp(7, rslastupddt);
			preparedStatement.setString(8, rstxdate);
			preparedStatement.setString(9, rsloccode);
			preparedStatement.setString(10, rsregno);
			preparedStatement.setString(11, rstxno);
			preparedStatement.setString(12, rsseqno);

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

	public static boolean Sagoadetchkexists(String entitykey, String frdatabase)
			throws SQLException {

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

			String selectSQL = "SELECT TX_DATE " + "FROM SAGOA_DET "
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
