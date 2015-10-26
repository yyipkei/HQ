package com.bridge.insertgoodsreturn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Objects;

import org.apache.log4j.Logger;

import com.bridge.main.HikariMerge;
import com.bridge.main.HikariQracleTo;

public class InsertGrorgdet {

	private static final Logger logger = Logger.getLogger(InsertGrorgdet.class);

	public static boolean Grorgdetinsert(String rstxdate, String rsloccode,
			String rsregno, String rstxno, String rsseqno, String rsvoid,
			String rsqty, String rsorgtxdate, String rsorgloccode,
			String rsorgregno, String rsorgtxno, String rsorgseqno,
			String rsvalidated, String rsnetsale, String rsnetdisc,
			String rsrowguid, String rspurpose, String rsvipno,
			String rsvipname, Timestamp rslastupddt, String frdatabase)
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

				insertTableSQL = "INSERT INTO GR_ORG_DET"
						+ "(TX_DATE,LOC_CODE,REG_NO,TX_NO,SEQ_NO,VOID,QTY,ORG_TX_DATE,ORG_LOC_CODE,"
						+ "ORG_REG_NO,ORG_TX_NO,ORG_SEQ_NO,VALIDATED,NET_SALE,NET_DISC,ROWGUID,"
						+ "PURPOSE,VIP_NO,VIP_NAME,LAST_UPD_DT) " + "VALUES"
						+ "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?," + "newid()"
						+ ",?,?,?,?)";

				preparedStatement = dbConnection
						.prepareStatement(insertTableSQL);

				preparedStatement.setString(1, rstxdate);
				preparedStatement.setString(2, rsloccode);
				preparedStatement.setString(3, rsregno);
				preparedStatement.setString(4, rstxno);
				preparedStatement.setString(5, rsseqno);
				preparedStatement.setString(6, rsvoid);
				preparedStatement.setString(7, rsqty);
				preparedStatement.setString(8, rsorgtxdate);
				preparedStatement.setString(9, rsorgloccode);
				preparedStatement.setString(10, rsorgregno);
				preparedStatement.setString(11, rsorgtxno);
				preparedStatement.setString(12, rsorgseqno);
				preparedStatement.setString(13, rsvalidated);
				preparedStatement.setString(14, rsnetsale);
				preparedStatement.setString(15, rsnetdisc);
				preparedStatement.setString(16, rspurpose);
				preparedStatement.setString(17, rsvipno);
				preparedStatement.setString(18, rsvipname);
				preparedStatement.setTimestamp(19, rslastupddt);

			} else {
				// dbConnection = OracleTo.getDBConnection();

				HikariQracleTo OrcaleFromTo = HikariQracleTo.getInstance();
				dbConnection = OrcaleFromTo.getConnection();

				insertTableSQL = "INSERT INTO GR_ORG_DET"
						+ "(TX_DATE,LOC_CODE,REG_NO,TX_NO,SEQ_NO,VOID,QTY,ORG_TX_DATE,ORG_LOC_CODE,"
						+ "ORG_REG_NO,ORG_TX_NO,ORG_SEQ_NO,VALIDATED,NET_SALE,NET_DISC,ROWGUID,"
						+ "PURPOSE,VIP_NO,VIP_NAME,LAST_UPD_DT) " + "VALUES"
						+ "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

				preparedStatement = dbConnection
						.prepareStatement(insertTableSQL);

				preparedStatement.setString(1, rstxdate);
				preparedStatement.setString(2, rsloccode);
				preparedStatement.setString(3, rsregno);
				preparedStatement.setString(4, rstxno);
				preparedStatement.setString(5, rsseqno);
				preparedStatement.setString(6, rsvoid);
				preparedStatement.setString(7, rsqty);
				preparedStatement.setString(8, rsorgtxdate);
				preparedStatement.setString(9, rsorgloccode);
				preparedStatement.setString(10, rsorgregno);
				preparedStatement.setString(11, rsorgtxno);
				preparedStatement.setString(12, rsorgseqno);
				preparedStatement.setString(13, rsvalidated);
				preparedStatement.setString(14, rsnetsale);
				preparedStatement.setString(15, rsnetdisc);
				preparedStatement.setString(16, rsrowguid);
				preparedStatement.setString(17, rspurpose);
				preparedStatement.setString(18, rsvipno);
				preparedStatement.setString(19, rsvipname);
				preparedStatement.setTimestamp(20, rslastupddt);
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

	public static boolean Grorgdetupdate(String rstxdate, String rsloccode,
			String rsregno, String rstxno, String rsseqno, String rsvoid,
			String rsqty, String rsorgtxdate, String rsorgloccode,
			String rsorgregno, String rsorgtxno, String rsorgseqno,
			String rsvalidated, String rsnetsale, String rsnetdisc,
			String rsrowguid, String rspurpose, String rsvipno,
			String rsvipname, Timestamp rslastupddt, String frdatabase)
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

			String updateSQL = "UPDATE GR_ORG_DET " + "SET  VOID         = ? "
					+ ", QTY          = ? " + ", ORG_TX_DATE  = ? "
					+ ", ORG_LOC_CODE = ? " + ", ORG_REG_NO   = ? "
					+ ", ORG_TX_NO    = ? " + ", ORG_SEQ_NO   = ? "
					+ ", VALIDATED    = ? " + ", NET_SALE     = ? "
					+ ", NET_DISC     = ? " + ", PURPOSE      = ? "
					+ ", VIP_NO       = ? " + ", VIP_NAME     = ? "
					+ ", LAST_UPD_DT  = ? " + "WHERE TX_DATE    = ? "
					+ "AND LOC_CODE     = ? " + "AND REG_NO       = ? "
					+ "AND TX_NO        = ? " + "AND SEQ_NO       = ? ";

			preparedStatement = dbConnection.prepareStatement(updateSQL);

			preparedStatement.setString(1, rsvoid);
			preparedStatement.setString(2, rsqty);
			preparedStatement.setString(3, rsorgtxdate);
			preparedStatement.setString(4, rsorgloccode);
			preparedStatement.setString(5, rsorgregno);
			preparedStatement.setString(6, rsorgtxno);
			preparedStatement.setString(7, rsorgseqno);
			preparedStatement.setString(8, rsvalidated);
			preparedStatement.setString(9, rsnetsale);
			preparedStatement.setString(10, rsnetdisc);
			preparedStatement.setString(11, rspurpose);
			preparedStatement.setString(12, rsvipno);
			preparedStatement.setString(13, rsvipname);
			preparedStatement.setTimestamp(14, rslastupddt);
			preparedStatement.setString(15, rstxdate);
			preparedStatement.setString(16, rsloccode);
			preparedStatement.setString(17, rsregno);
			preparedStatement.setString(18, rstxno);
			preparedStatement.setString(19, rsseqno);

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

	public static boolean Grorgdetchkexists(String entitykey, String frdatabase)
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

			String selectSQL = "SELECT TX_DATE " + "FROM GR_ORG_DET "
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
