package com.bridge.insertstockonhand;

import com.bridge.main.HikariQracleTo;
import com.bridge.main.HikariRms;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.Objects;

/**
 * Created by keyip on 10-Jun-15.
 */
public class InsertSxboxpos {

    private static final Logger logger = Logger.getLogger(InsertSxboxpos.class);

    public static boolean Sxboxposinsert(String rsboxno, String rssender, String rsprecvr, String rsarecvr, String rstsenqty, String rstrecqty, String rssldtid,
                                         String rsrldtid, String rssenstatus, String rsrecstatus, String rsboxstatus, Timestamp rssenscandt, Timestamp rsrecscandt, Timestamp rssenloaddt,
                                         Timestamp rsrecloaddt, Timestamp rssencmtdt, Timestamp rsreccmtdt, Timestamp rsplantxdt, Timestamp rsboxsendt, String rsdept, String rstype, String rsflag1,
                                         String rsflag2, String rsflag3, Timestamp rsdt1, Timestamp rsdt2, Timestamp rsdt3, String rscustom1, String rsremarks, String rslastupdusr,
                                         Timestamp rslastupddt, String rslastupdver, String rsrowguid, String rssencomp, String rspreccomp, String rsareccomp, String rscompanycde, String rsentitykey,
                                         String frdatabase) throws SQLException {

        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        String insertTableSQL;

        insertTableSQL = "INSERT INTO sx_box_pos (BOXNO,SENDER,P_RECVR,A_RECVR,T_SEN_QTY,T_REC_QTY,S_LDT_ID,R_LDT_ID," +
                "SEN_STATUS,REC_STATUS,BOX_STATUS,SEN_SCAN_DT,REC_SCAN_DT,SEN_LOAD_DT,REC_LOAD_DT,SEN_CMT_DT,REC_CMT_DT,PLAN_TX_DT," +
                "BOX_SEN_DT,DEPT,TYPE,FLAG1,FLAG2,FLAG3,DT1,DT2,DT3,CUSTOM1,REMARKS,LAST_UPD_USR,LAST_UPD_DT,LAST_UPD_VER,ROWGUID," +
                "SEN_COMP,P_REC_COMP,A_REC_COMP,COMPANY_CDE,ENTITY_KEY) "
                + "VALUES ( ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,? )";

        try {

            if (Objects.equals(frdatabase, "Oracle")) {
                // dbConnection = Mssql.getDBConnection();

                HikariRms Rmspool = HikariRms.getInstance();
                dbConnection = Rmspool.getConnection();
            } else {
                // dbConnection = OracleTo.getDBConnection();

                HikariQracleTo OrcaleFromTo = HikariQracleTo.getInstance();
                dbConnection = OrcaleFromTo.getConnection();
            }

            preparedStatement = dbConnection.prepareStatement(insertTableSQL);

            preparedStatement.setString(1, rsboxno);
            preparedStatement.setString(2, rssender);
            preparedStatement.setString(3, rsprecvr);
            preparedStatement.setString(4, rsarecvr);
            preparedStatement.setString(5, rstsenqty);
            preparedStatement.setString(6, rstrecqty);
            preparedStatement.setString(7, rssldtid);
            preparedStatement.setString(8, rsrldtid);
            preparedStatement.setString(9, rssenstatus);
            preparedStatement.setString(10, rsrecstatus);
            preparedStatement.setString(11, rsboxstatus);
            preparedStatement.setTimestamp(12, rssenscandt);
            preparedStatement.setTimestamp(13, rsrecscandt);
            preparedStatement.setTimestamp(14, rssenloaddt);
            preparedStatement.setTimestamp(15, rsrecloaddt);
            preparedStatement.setTimestamp(16, rssencmtdt);
            preparedStatement.setTimestamp(17, rsreccmtdt);
            preparedStatement.setTimestamp(18, rsplantxdt);
            preparedStatement.setTimestamp(19, rsboxsendt);
            preparedStatement.setString(20, rsdept);
            preparedStatement.setString(21, rstype);
            preparedStatement.setString(22, rsflag1);
            preparedStatement.setString(23, rsflag2);
            preparedStatement.setString(24, rsflag3);
            preparedStatement.setTimestamp(25, rsdt1);
            preparedStatement.setTimestamp(26, rsdt2);
            preparedStatement.setTimestamp(27, rsdt3);
            preparedStatement.setString(28, rscustom1);
            preparedStatement.setString(29, rsremarks);
            preparedStatement.setString(30, rslastupdusr);
            preparedStatement.setTimestamp(31, rslastupddt);
            preparedStatement.setString(32, rslastupdver);
            preparedStatement.setString(33, rsrowguid);
            preparedStatement.setString(34, rssencomp);
            preparedStatement.setString(35, rspreccomp);
            preparedStatement.setString(36, rsareccomp);
            preparedStatement.setString(37, rscompanycde);
            preparedStatement.setString(38, rsentitykey);


            preparedStatement.executeUpdate();

            return true;

        } catch (SQLException e) {

            logger.info(e.getMessage());

            return false;

        } finally {

            if (preparedStatement != null) {
                preparedStatement.close();
            }

            if (dbConnection != null) {
                dbConnection.close();
            }
        }

    }

    public static boolean Sxboxposupdate(String rsboxno, String rssender, String rsprecvr, String rsarecvr, String rstsenqty, String rstrecqty, String rssldtid,
                                         String rsrldtid, String rssenstatus, String rsrecstatus, String rsboxstatus, Timestamp rssenscandt, Timestamp rsrecscandt, Timestamp rssenloaddt,
                                         Timestamp rsrecloaddt, Timestamp rssencmtdt, Timestamp rsreccmtdt, Timestamp rsplantxdt, Timestamp rsboxsendt, String rsdept, String rstype, String rsflag1,
                                         String rsflag2, String rsflag3, Timestamp rsdt1, Timestamp rsdt2, Timestamp rsdt3, String rscustom1, String rsremarks, String rslastupdusr,
                                         Timestamp rslastupddt, String rslastupdver, String rsrowguid, String rssencomp, String rspreccomp, String rsareccomp, String rscompanycde, String rsentitykey,
                                         String frdatabase) throws SQLException {
        boolean result = true;
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        String updateSQL;

        updateSQL = "UPDATE SX_BOX_POS "
                + "SET BOXNO      = ? "
                + ", SENDER       = ? "
                + ", P_RECVR      = ? "
                + ", A_RECVR      = ? "
                + ", T_SEN_QTY    = ? "
                + ", T_REC_QTY    = ? "
                + ", S_LDT_ID     = ? "
                + ", R_LDT_ID     = ? "
                + ", SEN_STATUS   = ? "
                + ", REC_STATUS   = ? "
                + ", BOX_STATUS   = ? "
                + ", SEN_SCAN_DT  = ? "
                + ", REC_SCAN_DT  = ? "
                + ", SEN_LOAD_DT  = ? "
                + ", REC_LOAD_DT  = ? "
                + ", SEN_CMT_DT   = ? "
                + ", REC_CMT_DT   = ? "
                + ", PLAN_TX_DT   = ? "
                + ", BOX_SEN_DT   = ? "
                + ", DEPT         = ? "
                + ", TYPE         = ? "
                + ", FLAG1        = ? "
                + ", FLAG2        = ? "
                + ", FLAG3        = ? "
                + ", DT1          = ? "
                + ", DT2          = ? "
                + ", DT3          = ? "
                + ", CUSTOM1      = ? "
                + ", REMARKS      = ? "
                + ", LAST_UPD_USR = ? "
                + ", LAST_UPD_DT  = ? "
                + ", LAST_UPD_VER = ? "
                + ", ROWGUID      = ? "
                + ", SEN_COMP     = ? "
                + ", P_REC_COMP   = ? "
                + ", A_REC_COMP   = ? "
                + ", COMPANY_CDE  = ? "
                + "WHERE  ENTITY_KEY   = ? ";

        try {

            if (Objects.equals(frdatabase, "Oracle")) {
                // dbConnection = Mssql.getDBConnection();

                HikariRms Rmspool = HikariRms.getInstance();
                dbConnection = Rmspool.getConnection();
            } else {
                // dbConnection = OracleTo.getDBConnection();

                HikariQracleTo OrcaleFromTo = HikariQracleTo.getInstance();
                dbConnection = OrcaleFromTo.getConnection();
            }

            preparedStatement = dbConnection.prepareStatement(updateSQL);

            preparedStatement.setString(1, rsboxno);
            preparedStatement.setString(2, rssender);
            preparedStatement.setString(3, rsprecvr);
            preparedStatement.setString(4, rsarecvr);
            preparedStatement.setString(5, rstsenqty);
            preparedStatement.setString(6, rstrecqty);
            preparedStatement.setString(7, rssldtid);
            preparedStatement.setString(8, rsrldtid);
            preparedStatement.setString(9, rssenstatus);
            preparedStatement.setString(10, rsrecstatus);
            preparedStatement.setString(11, rsboxstatus);
            preparedStatement.setTimestamp(12, rssenscandt);
            preparedStatement.setTimestamp(13, rsrecscandt);
            preparedStatement.setTimestamp(14, rssenloaddt);
            preparedStatement.setTimestamp(15, rsrecloaddt);
            preparedStatement.setTimestamp(16, rssencmtdt);
            preparedStatement.setTimestamp(17, rsreccmtdt);
            preparedStatement.setTimestamp(18, rsplantxdt);
            preparedStatement.setTimestamp(19, rsboxsendt);
            preparedStatement.setString(20, rsdept);
            preparedStatement.setString(21, rstype);
            preparedStatement.setString(22, rsflag1);
            preparedStatement.setString(23, rsflag2);
            preparedStatement.setString(24, rsflag3);
            preparedStatement.setTimestamp(25, rsdt1);
            preparedStatement.setTimestamp(26, rsdt2);
            preparedStatement.setTimestamp(27, rsdt3);
            preparedStatement.setString(28, rscustom1);
            preparedStatement.setString(29, rsremarks);
            preparedStatement.setString(30, rslastupdusr);
            preparedStatement.setTimestamp(31, rslastupddt);
            preparedStatement.setString(32, rslastupdver);
            preparedStatement.setString(33, rsrowguid);
            preparedStatement.setString(34, rssencomp);
            preparedStatement.setString(35, rspreccomp);
            preparedStatement.setString(36, rsareccomp);
            preparedStatement.setString(37, rscompanycde);
            preparedStatement.setString(38, rsentitykey);

            preparedStatement.executeUpdate();

            return result;

        } catch (SQLException e) {

            logger.info(e.getMessage());
            result = false;

            logger.info(false);

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

    public static boolean Sxboxposchkexists(String entitykey, String frdatabase)
            throws SQLException {
        /*
         * String[] parts = entitykey.split(","); String txdate = parts[0];
		 * String loccode = parts[1]; String regno = parts[2]; String txno =
		 * parts[3]; String seqno = parts[4];
		 */
        boolean result = false;
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;

        try {

            if (Objects.equals(frdatabase, "Oracle")) {
                // dbConnection = Mssql.getDBConnection();

                HikariRms Rmspool = HikariRms.getInstance();
                dbConnection = Rmspool.getConnection();
            } else {
                // dbConnection = OracleTo.getDBConnection();

                HikariQracleTo OrcaleFromTo = HikariQracleTo.getInstance();
                dbConnection = OrcaleFromTo.getConnection();
            }

            String selectSQL = "SELECT BOXNO " + "FROM SX_BOX_POS "
                    + "where ENTITY_KEY ='" + entitykey + "'";

            // logger.info(selectSQL);

            preparedStatement = dbConnection.prepareStatement(selectSQL);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String rschktxdate = rs.getString("BOXNO");

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
