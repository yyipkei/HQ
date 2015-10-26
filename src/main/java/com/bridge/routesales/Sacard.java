package com.bridge.routesales;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Objects;

import com.bridge.result.Insertdataupdatelog;
import org.apache.log4j.Logger;

import com.bridge.insertsales.InsertSacard;
import com.bridge.main.HikariMssql;
import com.bridge.main.HikariQracleFrom;
import com.bridge.result.Logupdateresult;

public class Sacard {

	private static final Logger logger = Logger.getLogger(Sacard.class);

	public static void routeSacard(String dataupdatelog, String entityname,
			String entitykey, String database) throws SQLException {

		String[] parts = entitykey.split(",");
		String txdate = parts[0];
		String loccode = parts[1];
		String regno = parts[2];
		String txno = parts[3];
		String seqno = parts[4];

		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		String selectSQL = "SELECT  TX_DATE,LOC_CODE, REG_NO,TX_NO,SEQ_NO,TX_TYPE,VOID,CARD_NO,STAFF_ID,"
				+ "HOLDER_NAME,CARD_TYPE,CARD_EXPIRY,NET_SALES,AUTHOR_CODE, BANK_NO, ECR_REFER_NO, TERM_NO, MERCH_NO,"
				+ " BATCH_NO,TRACE_NO, RESPONSE_CDE, RESPONSE_TEXT, LAST_UPD_USR, LAST_UPD_DT, LAST_UPD_VER, TRAN_TYPE, STORE_NO, "
				+ "VALUE_DAY, DEBIT_ACC_NO,BANK_ADD_RESP, REFERENCE_NO, EMV_ENTRY_MODE, EMV_AID, EMV_TC, EMV_APP, TRAN_DT, DCC_AMT,DCC_TIP,DCC_XRATE, "
				+ "DCC_LOCAL_CURR, DCC_FOREIGN_CURR,DCC_PRINT_TEXT,DCC_XRATE_FORMAT, ACQ_ID,  SMALL_TICKET_TRAN, DCC_MARKUP_RATE_TEXT "
				+ "FROM Sacard "
				+ "where tx_date ='"
				+ txdate
				+ "'"
				+ "and LOC_CODE ='"
				+ loccode
				+ "'"
				+ "and reg_no='"
				+ regno
				+ "'"
				+ "and tx_no ='"
				+ txno
				+ "'"
				+ "and seq_no ='"
				+ seqno
				+ "'"
				+"Order BY LAST_UPD_DT";

		// List<Sacard> Sacards = new ArrayList<Sacard>();

		try {
			if (Objects.equals(database, "Oracle")) {
				HikariQracleFrom OrcaleFrompool = HikariQracleFrom
						.getInstance();
				dbConnection = OrcaleFrompool.getConnection();
			} else {
				HikariMssql Mssqlpool = HikariMssql.getInstance();
				dbConnection = Mssqlpool.getConnection();
			}

			preparedStatement = dbConnection.prepareStatement(selectSQL);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {

				// Sacard Sacard = new Sacard();

				String rstxdate = rs.getString("TX_DATE");
				String rsloccode = rs.getString("LOC_CODE");
				String rsregno = rs.getString("REG_NO");
				String rstxno = rs.getString("TX_NO");
				String rsseqno = rs.getString("SEQ_NO");
				String rstxtype = rs.getString("TX_TYPE");
				String rsvoid = rs.getString("VOID");
				String rscardno = rs.getString("CARD_NO");
				String rsstaffid = rs.getString("STAFF_ID");
				String rsholdername = rs.getString("HOLDER_NAME");
				String rscardtype = rs.getString("CARD_TYPE");
				String rscardexpiry = rs.getString("CARD_EXPIRY");
				String rsnetsales = rs.getString("NET_SALES");
				String rsauthorcode = rs.getString("AUTHOR_CODE");
				String rsbankno = rs.getString("BANK_NO");
				String rsecrreferno = rs.getString("ECR_REFER_NO");
				String rstermno = rs.getString("TERM_NO");
				String rsmerchno = rs.getString("MERCH_NO");
				String rsbatchno = rs.getString("BATCH_NO");
				String rstraceno = rs.getString("TRACE_NO");
				String rsresponsecde = rs.getString("RESPONSE_CDE");
				String rsresponsetext = rs.getString("RESPONSE_TEXT");
				String rslastupdusr = rs.getString("LAST_UPD_USR");
				Timestamp rslastupddt = rs.getTimestamp("LAST_UPD_DT");
				String rslastupdver = rs.getString("LAST_UPD_VER");
				String rstrantype = rs.getString("TRAN_TYPE");
				String rsstoreno = rs.getString("STORE_NO");
				String rsvalueday = rs.getString("VALUE_DAY");
				String rsdebitaccno = rs.getString("DEBIT_ACC_NO");
				String rsbankaddresp = rs.getString("BANK_ADD_RESP");
				String rsreferenceno = rs.getString("REFERENCE_NO");
				String rsemventrymode = rs.getString("EMV_ENTRY_MODE");
				String rsemvaid = rs.getString("EMV_AID");
				String rsemvtc = rs.getString("EMV_TC");
				String rsemvapp = rs.getString("EMV_APP");
				Timestamp rstrandt = rs.getTimestamp("TRAN_DT");
				String rsdccamt = rs.getString("DCC_AMT");
				String rsdcctip = rs.getString("DCC_TIP");
				String rsdccxrate = rs.getString("DCC_XRATE");
				String rsdcclocalcurr = rs.getString("DCC_LOCAL_CURR");
				String rsdccforeigncurr = rs.getString("DCC_FOREIGN_CURR");
				String rsdccprinttext = rs.getString("DCC_PRINT_TEXT");
				String rsdccxrateformat = rs.getString("DCC_XRATE_FORMAT");
				String rsacqid = rs.getString("ACQ_ID");
				String rssmalltickettran = rs.getString("SMALL_TICKET_TRAN");
				String rsdccmarkupratetext = rs
						.getString("DCC_MARKUP_RATE_TEXT");

				boolean Chkresult = InsertSacard.Sacardchkexists(entitykey,
						database);

				// logger.info(Chkresult);

				if (!Chkresult) {

					// logger.info("insert");

					boolean Insertresult = InsertSacard.Sacardinsert(rstxdate,
							rsloccode, rsregno, rstxno, rsseqno, rstxtype,
							rsvoid, rscardno, rsstaffid, rsholdername,
							rscardtype, rscardexpiry, rsnetsales, rsauthorcode,
							rsbankno, rsecrreferno, rstermno, rsmerchno,
							rsbatchno, rstraceno, rsresponsecde,
							rsresponsetext, rslastupdusr, rslastupddt,
							rslastupdver, rstrantype, rsstoreno, rsvalueday,
							rsdebitaccno, rsbankaddresp, rsreferenceno,
							rsemventrymode, rsemvaid, rsemvtc, rsemvapp,
							rstrandt, rsdccamt, rsdcctip, rsdccxrate,
							rsdcclocalcurr, rsdccforeigncurr, rsdccprinttext,
							rsdccxrateformat, rsacqid, rssmalltickettran,
							rsdccmarkupratetext, database);

					if (Insertresult) {
						logger.info("Sacard: 1 row has been inserted. Key:"
								+ rstxdate + "," + rsloccode + "," + rsregno
								+ "'" + rstxno + "'" + rsseqno);
					} else {
						logger.info("Insert Error");
					}
					Logupdateresult.Updatelogresult(dataupdatelog, entityname,
							Insertresult, database);

                    /*if ((!"Oracle".equals(database)) && (Insertresult)) {
                        Insertdataupdatelog.Updatelogresult(entityname, entitykey);
                    }*/

				} else {

					// logger.info("update");

					boolean Insertresult = InsertSacard.Sacardupdate(rstxdate,
							rsloccode, rsregno, rstxno, rsseqno, rstxtype,
							rsvoid, rscardno, rsstaffid, rsholdername,
							rscardtype, rscardexpiry, rsnetsales, rsauthorcode,
							rsbankno, rsecrreferno, rstermno, rsmerchno,
							rsbatchno, rstraceno, rsresponsecde,
							rsresponsetext, rslastupdusr, rslastupddt,
							rslastupdver, rstrantype, rsstoreno, rsvalueday,
							rsdebitaccno, rsbankaddresp, rsreferenceno,
							rsemventrymode, rsemvaid, rsemvtc, rsemvapp,
							rstrandt, rsdccamt, rsdcctip, rsdccxrate,
							rsdcclocalcurr, rsdccforeigncurr, rsdccprinttext,
							rsdccxrateformat, rsacqid, rssmalltickettran,
							rsdccmarkupratetext,database);

					if (Insertresult) {
						logger.info("Sacard: 1 row has been updated. Key:"
								+ rstxdate + "," + rsloccode + "," + rsregno
								+ "'" + rstxno + "'" + rsseqno);
					} else {
						logger.info("Update Error");
					}
					Logupdateresult.Updatelogresult(dataupdatelog, entityname,
							Insertresult, database);

                   /* if ((!"Oracle".equals(database)) && (Insertresult)) {
                        Insertdataupdatelog.Updatelogresult(entityname, entitykey);
                    }*/
				}
			}
		} catch (SQLException e) {

			logger.info(e.getMessage());

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
