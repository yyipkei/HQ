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

public class InsertSacard {

    private static final Logger logger = Logger.getLogger(InsertSacard.class);

    public static boolean Sacardinsert(String rstxdate, String rsloccode,
                                       String rsregno, String rstxno, String rsseqno, String rstxtype,
                                       String rsvoid, String rscardno, String rsstaffid,
                                       String rsholdername, String rscardtype, String rscardexpiry,
                                       String rsnetsales, String rsauthorcode, String rsbankno,
                                       String rsecrreferno, String rstermno, String rsmerchno,
                                       String rsbatchno, String rstraceno, String rsresponsecde,
                                       String rsresponsetext, String rslastupdusr, Timestamp rslastupddt,
                                       String rslastupdver, String rstrantype, String rsstoreno,
                                       String rsvalueday, String rsdebitaccno, String rsbankaddresp,
                                       String rsreferenceno, String rsemventrymode, String rsemvaid,
                                       String rsemvtc, String rsemvapp, Timestamp rstrandt, String rsdccamt,
                                       String rsdcctip, String rsdccxrate, String rsdcclocalcurr,
                                       String rsdccforeigncurr, String rsdccprinttext,
                                       String rsdccxrateformat, String rsacqid, String rssmalltickettran,
                                       String rsdccmarkupratetext, String frdatabase) throws SQLException {

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

            String insertTableSQL = "INSERT INTO Sacard"
                    + "(TX_DATE,LOC_CODE,REG_NO,TX_NO,SEQ_NO,TX_TYPE,VOID,CARD_NO,STAFF_ID,HOLDER_NAME,CARD_TYPE,CARD_EXPIRY,"
                    + "NET_SALES,AUTHOR_CODE,BANK_NO,ECR_REFER_NO,TERM_NO,MERCH_NO,BATCH_NO,TRACE_NO,RESPONSE_CDE,RESPONSE_TEXT,"
                    + "LAST_UPD_USR,LAST_UPD_DT,LAST_UPD_VER,TRAN_TYPE,STORE_NO,VALUE_DAY,DEBIT_ACC_NO,BANK_ADD_RESP,REFERENCE_NO,"
                    + "EMV_ENTRY_MODE,EMV_AID,EMV_TC,EMV_APP,TRAN_DT,DCC_AMT,DCC_TIP,DCC_XRATE,DCC_LOCAL_CURR,DCC_FOREIGN_CURR,DCC_PRINT_TEXT,"
                    + "DCC_XRATE_FORMAT,ACQ_ID,SMALL_TICKET_TRAN,DCC_MARKUP_RATE_TEXT) "
                    + "VALUES"
                    + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

            preparedStatement = dbConnection.prepareStatement(insertTableSQL);

            preparedStatement.setString(1, rstxdate);
            preparedStatement.setString(2, rsloccode);
            preparedStatement.setString(3, rsregno);
            preparedStatement.setString(4, rstxno);
            preparedStatement.setString(5, rsseqno);
            preparedStatement.setString(6, rstxtype);
            preparedStatement.setString(7, rsvoid);
            preparedStatement.setString(8, rscardno);
            preparedStatement.setString(9, rsstaffid);
            preparedStatement.setString(10, rsholdername);
            preparedStatement.setString(11, rscardtype);
            preparedStatement.setString(12, rscardexpiry);
            preparedStatement.setString(13, rsnetsales);
            preparedStatement.setString(14, rsauthorcode);
            preparedStatement.setString(15, rsbankno);
            preparedStatement.setString(16, rsecrreferno);
            preparedStatement.setString(17, rstermno);
            preparedStatement.setString(18, rsmerchno);
            preparedStatement.setString(19, rsbatchno);
            preparedStatement.setString(20, rstraceno);
            preparedStatement.setString(21, rsresponsecde);
            preparedStatement.setString(22, rsresponsetext);
            preparedStatement.setString(23, rslastupdusr);
            preparedStatement.setTimestamp(24, rslastupddt);
            preparedStatement.setString(25, rslastupdver);
            preparedStatement.setString(26, rstrantype);
            preparedStatement.setString(27, rsstoreno);
            preparedStatement.setString(28, rsvalueday);
            preparedStatement.setString(29, rsdebitaccno);
            preparedStatement.setString(30, rsbankaddresp);
            preparedStatement.setString(31, rsreferenceno);
            preparedStatement.setString(32, rsemventrymode);
            preparedStatement.setString(33, rsemvaid);
            preparedStatement.setString(34, rsemvtc);
            preparedStatement.setString(35, rsemvapp);
            preparedStatement.setTimestamp(36, rstrandt);
            preparedStatement.setString(37, rsdccamt);
            preparedStatement.setString(38, rsdcctip);
            preparedStatement.setString(39, rsdccxrate);
            preparedStatement.setString(40, rsdcclocalcurr);
            preparedStatement.setString(41, rsdccforeigncurr);
            preparedStatement.setString(42, rsdccprinttext);
            preparedStatement.setString(43, rsdccxrateformat);
            preparedStatement.setString(44, rsacqid);
            preparedStatement.setString(45, rssmalltickettran);
            preparedStatement.setString(46, rsdccmarkupratetext);

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

    public static boolean Sacardupdate(String rstxdate, String rsloccode,
                                       String rsregno, String rstxno, String rsseqno, String rstxtype,
                                       String rsvoid, String rscardno, String rsstaffid,
                                       String rsholdername, String rscardtype, String rscardexpiry,
                                       String rsnetsales, String rsauthorcode, String rsbankno,
                                       String rsecrreferno, String rstermno, String rsmerchno,
                                       String rsbatchno, String rstraceno, String rsresponsecde,
                                       String rsresponsetext, String rslastupdusr, Timestamp rslastupddt,
                                       String rslastupdver, String rstrantype, String rsstoreno,
                                       String rsvalueday, String rsdebitaccno, String rsbankaddresp,
                                       String rsreferenceno, String rsemventrymode, String rsemvaid,
                                       String rsemvtc, String rsemvapp, Timestamp rstrandt, String rsdccamt,
                                       String rsdcctip, String rsdccxrate, String rsdcclocalcurr,
                                       String rsdccforeigncurr, String rsdccprinttext,
                                       String rsdccxrateformat, String rsacqid, String rssmalltickettran,
                                       String rsdccmarkupratetext, String frdatabase) throws SQLException {

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

            String updateSQL = "UPDATE SACARD "
                    + "SET  TX_TYPE              = ? "
                    + ", VOID                 = ? "
                    + ", CARD_NO              = ? "
                    + ", STAFF_ID             = ? "
                    + ", HOLDER_NAME          = ? "
                    + ", CARD_TYPE            = ? "
                    + ", CARD_EXPIRY          = ? "
                    + ", NET_SALES            = ? "
                    + ", AUTHOR_CODE          = ? "
                    + ", BANK_NO              = ? "
                    + ", ECR_REFER_NO         = ? "
                    + ", TERM_NO              = ? "
                    + ", MERCH_NO             = ? "
                    + ", BATCH_NO             = ? "
                    + ", TRACE_NO             = ? "
                    + ", RESPONSE_CDE         = ? "
                    + ", RESPONSE_TEXT        = ? "
                    + ", LAST_UPD_USR         = ? "
                    + ", LAST_UPD_DT          = ? "
                    + ", LAST_UPD_VER         = ? "
                    + ", TRAN_TYPE            = ? "
                    + ", STORE_NO             = ? "
                    + ", VALUE_DAY            = ? "
                    + ", DEBIT_ACC_NO         = ? "
                    + ", BANK_ADD_RESP        = ? "
                    + ", REFERENCE_NO         = ? "
                    + ", EMV_ENTRY_MODE       = ? "
                    + ", EMV_AID              = ? "
                    + ", EMV_TC               = ? "
                    + ", EMV_APP              = ? "
                    + ", TRAN_DT              = ? "
                    + ", DCC_AMT              = ? "
                    + ", DCC_TIP              = ? "
                    + ", DCC_XRATE            = ? "
                    + ", DCC_LOCAL_CURR       = ? "
                    + ", DCC_FOREIGN_CURR     = ? "
                    + ", DCC_PRINT_TEXT       = ? "
                    + ", DCC_XRATE_FORMAT     = ? "
                    + ", ACQ_ID               = ? "
                    + ", SMALL_TICKET_TRAN    =? "
                    + ", DCC_MARKUP_RATE_TEXT = ? "
                    + "WHERE TX_DATE            = ? "
                    + "and LOC_CODE             = ? "
                    + "and REG_NO               = ? "
                    + "and TX_NO                = ? "
                    + "and SEQ_NO               = ? ";

            preparedStatement = dbConnection.prepareStatement(updateSQL);

            preparedStatement.setString(1, rstxtype);
            preparedStatement.setString(2, rsvoid);
            preparedStatement.setString(3, rscardno);
            preparedStatement.setString(4, rsstaffid);
            preparedStatement.setString(5, rsholdername);
            preparedStatement.setString(6, rscardtype);
            preparedStatement.setString(7, rscardexpiry);
            preparedStatement.setString(8, rsnetsales);
            preparedStatement.setString(9, rsauthorcode);
            preparedStatement.setString(10, rsbankno);
            preparedStatement.setString(11, rsecrreferno);
            preparedStatement.setString(12, rstermno);
            preparedStatement.setString(13, rsmerchno);
            preparedStatement.setString(14, rsbatchno);
            preparedStatement.setString(15, rstraceno);
            preparedStatement.setString(16, rsresponsecde);
            preparedStatement.setString(17, rsresponsetext);
            preparedStatement.setString(18, rslastupdusr);
            preparedStatement.setTimestamp(19, rslastupddt);
            preparedStatement.setString(20, rslastupdver);
            preparedStatement.setString(21, rstrantype);
            preparedStatement.setString(22, rsstoreno);
            preparedStatement.setString(23, rsvalueday);
            preparedStatement.setString(24, rsdebitaccno);
            preparedStatement.setString(25, rsbankaddresp);
            preparedStatement.setString(26, rsreferenceno);
            preparedStatement.setString(27, rsemventrymode);
            preparedStatement.setString(28, rsemvaid);
            preparedStatement.setString(29, rsemvtc);
            preparedStatement.setString(30, rsemvapp);
            preparedStatement.setTimestamp(31, rstrandt);
            preparedStatement.setString(32, rsdccamt);
            preparedStatement.setString(33, rsdcctip);
            preparedStatement.setString(34, rsdccxrate);
            preparedStatement.setString(35, rsdcclocalcurr);
            preparedStatement.setString(36, rsdccforeigncurr);
            preparedStatement.setString(37, rsdccprinttext);
            preparedStatement.setString(38, rsdccxrateformat);
            preparedStatement.setString(39, rsacqid);
            preparedStatement.setString(40, rssmalltickettran);
            preparedStatement.setString(41, rsdccmarkupratetext);
            preparedStatement.setString(42, rstxdate);
            preparedStatement.setString(43, rsloccode);
            preparedStatement.setString(44, rsregno);
            preparedStatement.setString(45, rstxno);
            preparedStatement.setString(46, rsseqno);

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

    public static boolean Sacardchkexists(String entitykey, String frdatabase)
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
			
            String selectSQL = "SELECT TX_DATE " + "FROM Sacard "
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
