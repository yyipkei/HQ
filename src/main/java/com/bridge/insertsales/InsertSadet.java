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

public class InsertSadet {
	private static final Logger logger = Logger.getLogger(InsertSadet.class);

	public static boolean Sadetinsert(String rstxdate, String rsloccode,
			String rsregno, String rstxno, String rsseqno, String rstxtype,
			String rsvoid, String rssku, String rsqty, String rsorguret,
			String rscururet, String rsneturet, String rsnetdisc,
			String rsnetsale, String rsavgucost, String rscurucost,
			String rscostsold, String rsitemdisc, String rstxdisc,
			String rspriceoverid, String rssalesxtax, String rstaxamt,
			String rstaxamt2, String rstaxshown, String rsvendorupc,
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

			String insertTableSQL = "INSERT INTO SADET"
					+ "(TX_DATE,LOC_CODE,REG_NO,TX_NO,SEQ_NO,TX_TYPE,VOID,SKU,QTY,ORG_URET,CUR_URET,NET_URET, "
					+ "NET_DISC,NET_SALE,AVG_UCOST,CUR_UCOST,COST_SOLD,ITEM_DISC,TX_DISC,PRICE_OVER_ID,SALES_X_TAX,"
					+ "TAX_AMT,TAX_AMT2,TAX_SHOWN,VENDOR_UPC,LAST_UPD_DT) "
					+ "VALUES"
					+ "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

			preparedStatement = dbConnection.prepareStatement(insertTableSQL);

			preparedStatement.setString(1, rstxdate);
			preparedStatement.setString(2, rsloccode);
			preparedStatement.setString(3, rsregno);
			preparedStatement.setString(4, rstxno);
			preparedStatement.setString(5, rsseqno);
			preparedStatement.setString(6, rstxtype);
			preparedStatement.setString(7, rsvoid);
			preparedStatement.setString(8, rssku);
			preparedStatement.setString(9, rsqty);
			preparedStatement.setString(10, rsorguret);
			preparedStatement.setString(11, rscururet);
			preparedStatement.setString(12, rsneturet);
			preparedStatement.setString(13, rsnetdisc);
			preparedStatement.setString(14, rsnetsale);
			preparedStatement.setString(15, rsavgucost);
			preparedStatement.setString(16, rscurucost);
			preparedStatement.setString(17, rscostsold);
			preparedStatement.setString(18, rsitemdisc);
			preparedStatement.setString(19, rstxdisc);
			preparedStatement.setString(20, rspriceoverid);
			preparedStatement.setString(21, rssalesxtax);
			preparedStatement.setString(22, rstaxamt);
			preparedStatement.setString(23, rstaxamt2);
			preparedStatement.setString(24, rstaxshown);
			preparedStatement.setString(25, rsvendorupc);
			preparedStatement.setTimestamp(26, rslastupddt);

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

	public static boolean Sadetupdate(String rstxdate, String rsloccode,
			String rsregno, String rstxno, String rsseqno, String rstxtype,
			String rsvoid, String rssku, String rsqty, String rsorguret,
			String rscururet, String rsneturet, String rsnetdisc,
			String rsnetsale, String rsavgucost, String rscurucost,
			String rscostsold, String rsitemdisc, String rstxdisc,
			String rspriceoverid, String rssalesxtax, String rstaxamt,
			String rstaxamt2, String rstaxshown, String rsvendorupc,
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

			String updateSQL = "UPDATE SADET " + "SET TX_TYPE     = ? "
					+ ", VOID          = ? " + ", SKU           = ? "
					+ ", QTY           = ? " + ", ORG_URET      = ? "
					+ ", CUR_URET      = ? " + ", NET_URET      = ? "
					+ ", NET_DISC      = ? " + ", NET_SALE      = ? "
					+ ", AVG_UCOST     = ? " + ", CUR_UCOST     = ? "
					+ ", COST_SOLD     = ? " + ", ITEM_DISC     = ? "
					+ ", TX_DISC       = ? " + ", PRICE_OVER_ID = ? "
					+ ", SALES_X_TAX   = ? " + ", TAX_AMT       = ? "
					+ ", TAX_AMT2      = ? " + ", TAX_SHOWN     = ? "
					+ ", VENDOR_UPC    = ? " + ", LAST_UPD_DT    = ? "
					+ "WHERE TX_DATE     = ? " + "AND LOC_CODE      = ? "
					+ "AND REG_NO        = ? " + "AND TX_NO         = ? "
					+ "AND SEQ_NO        = ? ";

			preparedStatement = dbConnection.prepareStatement(updateSQL);

			preparedStatement.setString(1, rstxtype);
			preparedStatement.setString(2, rsvoid);
			preparedStatement.setString(3, rssku);
			preparedStatement.setString(4, rsqty);
			preparedStatement.setString(5, rsorguret);
			preparedStatement.setString(6, rscururet);
			preparedStatement.setString(7, rsneturet);
			preparedStatement.setString(8, rsnetdisc);
			preparedStatement.setString(9, rsnetsale);
			preparedStatement.setString(10, rsavgucost);
			preparedStatement.setString(11, rscurucost);
			preparedStatement.setString(12, rscostsold);
			preparedStatement.setString(13, rsitemdisc);
			preparedStatement.setString(14, rstxdisc);
			preparedStatement.setString(15, rspriceoverid);
			preparedStatement.setString(16, rssalesxtax);
			preparedStatement.setString(17, rstaxamt);
			preparedStatement.setString(18, rstaxamt2);
			preparedStatement.setString(19, rstaxshown);
			preparedStatement.setString(20, rsvendorupc);
			preparedStatement.setTimestamp(21, rslastupddt);
			preparedStatement.setString(22, rstxdate);
			preparedStatement.setString(23, rsloccode);
			preparedStatement.setString(24, rsregno);
			preparedStatement.setString(25, rstxno);
			preparedStatement.setString(26, rsseqno);
			

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

	public static boolean Sadetchkexists(String entitykey, String frdatabase)
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

			String selectSQL = "SELECT TX_DATE " + "FROM SADET "
					+ "where tx_date ='" + txdate + "'" + "and LOC_CODE ='"
					+ loccode + "'" + "and reg_no='" + regno + "'"
					+ "and tx_no ='" + txno + "'" + "and seq_no ='" + seqno
					+ "'";

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
