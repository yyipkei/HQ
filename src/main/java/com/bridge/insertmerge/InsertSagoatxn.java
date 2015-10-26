package com.bridge.insertmerge;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Objects;

import org.apache.log4j.Logger;

import com.bridge.main.HikariMerge;
import com.bridge.main.HikariQracleTo;

public class InsertSagoatxn {

	private static final Logger logger = Logger.getLogger(InsertSagoatxn.class);

	public static boolean Sagoatxninsert(String rstxdate, String rsloccode,
			String rsregno, String rstxno, String rsseqno, String rstxtype,
			String rspurposecde, String rsstatus, String rsvoid, String rssku,
			String rsqty, String rscururet, String rsstaffid, String rsrowguid,
			Timestamp rslastupddt, String frdatabase) throws SQLException {

		boolean result = true;
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		String insertTableSQL;

		try {

			if (Objects.equals(frdatabase, "Oracle")) {
				// dbConnection = Merge.getDBConnection();

				HikariMerge Mergepool = HikariMerge.getInstance();
				dbConnection = Mergepool.getConnection();

				insertTableSQL = "INSERT INTO SAGOA_TXN"
						+ "(TX_DATE,LOC_CODE,REG_NO,TX_NO,SEQ_NO,TX_TYPE,PURPOSE_CDE,STATUS,VOID,SKU,QTY,CUR_URET,STAFF_ID,ROWGUID,LAST_UPD_DT) "
						+ "VALUES" + "(?,?,?,?,?,?,?,?,?,?,?,?,?," + "newid()"
						+ ",?)";

				preparedStatement = dbConnection
						.prepareStatement(insertTableSQL);

				preparedStatement.setString(1, rstxdate);
				preparedStatement.setString(2, rsloccode);
				preparedStatement.setString(3, rsregno);
				preparedStatement.setString(4, rstxno);
				preparedStatement.setString(5, rsseqno);
				preparedStatement.setString(6, rstxtype);
				preparedStatement.setString(7, rspurposecde);
				preparedStatement.setString(8, rsstatus);
				preparedStatement.setString(9, rsvoid);
				preparedStatement.setString(10, rssku);
				preparedStatement.setString(11, rsqty);
				preparedStatement.setString(12, rscururet);
				preparedStatement.setString(13, rsstaffid);
				preparedStatement.setTimestamp(14, rslastupddt);

			} else {
				// dbConnection = OracleTo.getDBConnection();

				HikariQracleTo OrcaleFromTo = HikariQracleTo.getInstance();
				dbConnection = OrcaleFromTo.getConnection();

				insertTableSQL = "INSERT INTO SAGOA_TXN"
						+ "(TX_DATE,LOC_CODE,REG_NO,TX_NO,SEQ_NO,TX_TYPE,PURPOSE_CDE,STATUS,VOID,SKU,QTY,CUR_URET,STAFF_ID,ROWGUID,LAST_UPD_DT) "
						+ "VALUES" + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

				preparedStatement = dbConnection
						.prepareStatement(insertTableSQL);

				preparedStatement.setString(1, rstxdate);
				preparedStatement.setString(2, rsloccode);
				preparedStatement.setString(3, rsregno);
				preparedStatement.setString(4, rstxno);
				preparedStatement.setString(5, rsseqno);
				preparedStatement.setString(6, rstxtype);
				preparedStatement.setString(7, rspurposecde);
				preparedStatement.setString(8, rsstatus);
				preparedStatement.setString(9, rsvoid);
				preparedStatement.setString(10, rssku);
				preparedStatement.setString(11, rsqty);
				preparedStatement.setString(12, rscururet);
				preparedStatement.setString(13, rsstaffid);
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

	public static boolean Sagoatxnupdate(String rstxdate, String rsloccode,
			String rsregno, String rstxno, String rsseqno, String rstxtype,
			String rspurposecde, String rsstatus, String rsvoid, String rssku,
			String rsqty, String rscururet, String rsstaffid, String rsrowguid,
			Timestamp rslastupddt, String frdatabase) throws SQLException {

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

			String updateSQL = "UPDATE SAGOA_TXN " + "SET  TX_TYPE     = ? "
					+ ", PURPOSE_CDE = ? " + ", STATUS      = ? "
					+ ", VOID        = ? " + ", SKU         = ? "
					+ ", QTY         = ? " + ", CUR_URET    = ? "
					+ ", STAFF_ID    = ? " + ", ROWGUID     = ? "
					+ ", LAST_UPD_DT = ? " 
					+ "WHERE TX_DATE   = ? " + "AND LOC_CODE    = ? "
					+ "AND REG_NO      = ? " + "AND TX_NO       = ? "
					+ "AND SEQ_NO      = ? ";

			preparedStatement = dbConnection.prepareStatement(updateSQL);

			preparedStatement.setString(1, rstxtype);
			preparedStatement.setString(2, rspurposecde);
			preparedStatement.setString(3, rsstatus);
			preparedStatement.setString(4, rsvoid);
			preparedStatement.setString(5, rssku);
			preparedStatement.setString(6, rsqty);
			preparedStatement.setString(7, rscururet);
			preparedStatement.setString(8, rsstaffid);
			preparedStatement.setString(9, rsrowguid);
			preparedStatement.setTimestamp(10, rslastupddt);
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

	public static boolean Sagoatxnchkexists(String entitykey, String frdatabase)
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
				// dbConnection = Merge.getDBConnection();

				HikariMerge Mergepool = HikariMerge.getInstance();
				dbConnection = Mergepool.getConnection();
			} else {
				// dbConnection = OracleTo.getDBConnection();

				HikariQracleTo OrcaleFromTo = HikariQracleTo.getInstance();
				dbConnection = OrcaleFromTo.getConnection();
			}

			String selectSQL = "SELECT TX_TYPE " + "FROM SAGOA_TXN "
					+ "where tx_date ='" + txdate + "'" + "and LOC_CODE ='"
					+ loccode + "'" + "and reg_no='" + regno + "'"
					+ "and tx_no ='" + txno + "'" + "and seq_no ='" + seqno
					+ "'";

			// logger.info(selectSQL);

			preparedStatement = dbConnection.prepareStatement(selectSQL);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				String rschktxdate = rs.getString("TX_TYPE");

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
