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

public class InsertHostsettlement {

	private static final Logger logger = Logger
			.getLogger(InsertHostsettlement.class);

	public static boolean Hostsettlementinsert(String rstxdate,
			String rsloccode, String rsregno, String rsseqno, String rsbatchno,
			String rsbrespcde, String rsbresptext, String rscardtype,
			String rssalec, String rssalet, String rsrefundc, String rsrefundt,
			String rsrespcde, String rsresptext, String rsbatch,
			String rssettled, String rsdccsalec, String rsdccsalet,
			String rsdccrefundc, String rsdccrefundt, String rsdccsettled,
			String rsdccbatchno, String rsdccbrespcde, String rsdccbresptext,
			Timestamp rslastupddt, String frdatabase) throws SQLException {

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

			String insertTableSQL = "INSERT INTO host_settlement"
					+ "(TX_DATE,LOC_CODE,REG_NO,SEQ_NO,BATCH_NO,B_RESP_CDE,"
					+ "B_RESP_TEXT,CARD_TYPE,SALE_C,SALE_T,REFUND_C,REFUND_T,RESP_CDE,"
					+ "RESP_TEXT,BATCH,SETTLED,DCC_SALE_C,DCC_SALE_T,DCC_REFUND_C,DCC_REFUND_T,"
					+ "DCC_SETTLED,DCC_BATCH_NO,DCC_B_RESP_CDE,DCC_B_RESP_TEXT,LAST_UPD_DT) "
					+ "VALUES"
					+ "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

			preparedStatement = dbConnection.prepareStatement(insertTableSQL);

			preparedStatement.setString(1, rstxdate);
			preparedStatement.setString(2, rsloccode);
			preparedStatement.setString(3, rsregno);
			preparedStatement.setString(4, rsseqno);
			preparedStatement.setString(5, rsbatchno);
			preparedStatement.setString(6, rsbrespcde);
			preparedStatement.setString(7, rsbresptext);
			preparedStatement.setString(8, rscardtype);
			preparedStatement.setString(9, rssalec);
			preparedStatement.setString(10, rssalet);
			preparedStatement.setString(11, rsrefundc);
			preparedStatement.setString(12, rsrefundt);
			preparedStatement.setString(13, rsrespcde);
			preparedStatement.setString(14, rsresptext);
			preparedStatement.setString(15, rsbatch);
			preparedStatement.setString(16, rssettled);
			preparedStatement.setString(17, rsdccsalec);
			preparedStatement.setString(18, rsdccsalet);
			preparedStatement.setString(19, rsdccrefundc);
			preparedStatement.setString(20, rsdccrefundt);
			preparedStatement.setString(21, rsdccsettled);
			preparedStatement.setString(22, rsdccbatchno);
			preparedStatement.setString(23, rsdccbrespcde);
			preparedStatement.setString(24, rsdccbresptext);
			preparedStatement.setTimestamp(25, rslastupddt);

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

	public static boolean Hostsettlementupdate(String rstxdate,
			String rsloccode, String rsregno, String rsseqno, String rsbatchno,
			String rsbrespcde, String rsbresptext, String rscardtype,
			String rssalec, String rssalet, String rsrefundc, String rsrefundt,
			String rsrespcde, String rsresptext, String rsbatch,
			String rssettled, String rsdccsalec, String rsdccsalet,
			String rsdccrefundc, String rsdccrefundt, String rsdccsettled,
			String rsdccbatchno, String rsdccbrespcde, String rsdccbresptext,
			Timestamp rslastupddt, String frdatabase) throws SQLException {

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

			String updateSQL = "UPDATE HOST_SETTLEMENT "
					+ "SET  BATCH_NO        = ? " + ", B_RESP_CDE      = ? "
					+ ", B_RESP_TEXT     = ? " + ", CARD_TYPE       = ? "
					+ ", SALE_C          = ? " + ", SALE_T          = ? "
					+ ", REFUND_C        = ? " + ", REFUND_T        = ? "
					+ ", RESP_CDE        = ? " + ", RESP_TEXT       = ? "
					+ ", BATCH           = ? " + ", SETTLED         = ? "
					+ ", DCC_SALE_C      = ? " + ", DCC_SALE_T      = ? "
					+ ", DCC_REFUND_C    = ? " + ", DCC_REFUND_T    = ? "
					+ ", DCC_SETTLED     = ? " + ", DCC_BATCH_NO    = ? "
					+ ", DCC_B_RESP_CDE  = ? " + ", DCC_B_RESP_TEXT = ? "
					+ ", LAST_UPD_DT     = ? " + "WHERE TX_DATE       = ? "
					+ "AND LOC_CODE        = ? " + "AND REG_NO          = ? "
					+ "AND SEQ_NO          = ? ";

			preparedStatement = dbConnection.prepareStatement(updateSQL);

			preparedStatement.setString(1, rsbatchno);
			preparedStatement.setString(2, rsbrespcde);
			preparedStatement.setString(3, rsbresptext);
			preparedStatement.setString(4, rscardtype);
			preparedStatement.setString(5, rssalec);
			preparedStatement.setString(6, rssalet);
			preparedStatement.setString(7, rsrefundc);
			preparedStatement.setString(8, rsrefundt);
			preparedStatement.setString(9, rsrespcde);
			preparedStatement.setString(10, rsresptext);
			preparedStatement.setString(11, rsbatch);
			preparedStatement.setString(12, rssettled);
			preparedStatement.setString(13, rsdccsalec);
			preparedStatement.setString(14, rsdccsalet);
			preparedStatement.setString(15, rsdccrefundc);
			preparedStatement.setString(16, rsdccrefundt);
			preparedStatement.setString(17, rsdccsettled);
			preparedStatement.setString(18, rsdccbatchno);
			preparedStatement.setString(19, rsdccbrespcde);
			preparedStatement.setString(20, rsdccbresptext);
			preparedStatement.setTimestamp(21, rslastupddt);
			preparedStatement.setString(22, rstxdate);
			preparedStatement.setString(23, rsloccode);
			preparedStatement.setString(24, rsregno);
			preparedStatement.setString(25, rsseqno);

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

	public static boolean Hostsettlementchkexists(String entitykey,
			String frdatabase) throws SQLException {

		String[] parts = entitykey.split(",");
		String txdate = parts[0];
		String loccode = parts[1];
		String regno = parts[2];
		String seqno = parts[3];

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

			String selectSQL = "SELECT TX_DATE " + "FROM HOST_SETTLEMENT "
					+ "where tx_date ='" + txdate + "'" + "and LOC_CODE ='"
					+ loccode + "'" + "and reg_no='" + regno + "'"
					+ "and seq_no ='" + seqno + "'";
			
			// logger.info(selectSQL);

			preparedStatement = dbConnection.prepareStatement(selectSQL);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				String rschktxdate = rs.getString("TX_DATE");

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
