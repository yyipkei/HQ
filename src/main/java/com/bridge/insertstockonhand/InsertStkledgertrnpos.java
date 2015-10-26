package com.bridge.insertstockonhand;

import com.bridge.main.HikariQracleTo;
import com.bridge.main.HikariRms;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.Objects;

/**
 * Created by keyip on 17-Jun-15.
 */
public class InsertStkledgertrnpos {

    private static final Logger logger = Logger.getLogger(InsertStkledgertrnpos.class);

    public static boolean Stkledgertrnposinsert(String rssltrnid, String rssku, Timestamp rsdate, String rstrntype, String rstrnsubtype, String rsinstitcde,
                                                String rsloccde, String rsdeptcde, String rsclasscde, String rsprodtype, String rsreasoncde, String rsqty,
                                                String rscost, String rsretail, String rspurcost, String rspackcost, String rsfreigcost, String rscommcost,
                                                String rstotalcost, String rsrefid, String rstrnno, String rsregno, String rsseqno, String rssubseqno, String rsslbatchno,
                                                Timestamp rslastupddt, String rslastupdusr, String rslastupdver, String rsrowguid, String rsggqty, String rsstoreqty,
                                                String rsggbjqty, String rsorgcost, String rsorgtotalcost, String rscompanycde, String rsentitykey,
                                                String frdatabase) throws SQLException {

        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        String insertTableSQL;

        insertTableSQL = "INSERT INTO stk_ledger_trn_pos (SL_TRN_ID,SKU,DATE_,TRN_TYPE,TRN_SUB_TYPE,INSTIT_CDE,LOC_CDE,DEPT_CDE," +
                "CLASS_CDE,PROD_TYPE,REASON_CDE,QTY,COST,RETAIL,PUR_COST,PACK_COST,FREIG_COST,COMM_COST,TOTALCOST,REF_ID," +
                "TRN_NO,REG_NO,SEQ_NO,SUB_SEQ_NO,SL_BATCH_NO,LAST_UPD_DT,LAST_UPD_USR,LAST_UPD_VER,ROWGUID,GG_QTY,STORE_QTY," +
                "GG_BJ_QTY,ORG_COST,ORG_TOTALCOST,COMPANY_CDE,ENTITY_KEY) "
                + "VALUES ( ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,? )";

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

            preparedStatement.setString(1, rssltrnid);
            preparedStatement.setString(2, rssku);
            preparedStatement.setTimestamp(3, rsdate);
            preparedStatement.setString(4, rstrntype);
            preparedStatement.setString(5, rstrnsubtype);
            preparedStatement.setString(6, rsinstitcde);
            preparedStatement.setString(7, rsloccde);
            preparedStatement.setString(8, rsdeptcde);
            preparedStatement.setString(9, rsclasscde);
            preparedStatement.setString(10, rsprodtype);
            preparedStatement.setString(11, rsreasoncde);
            preparedStatement.setString(12, rsqty);
            preparedStatement.setString(13, rscost);
            preparedStatement.setString(14, rsretail);
            preparedStatement.setString(15, rspurcost);
            preparedStatement.setString(16, rspackcost);
            preparedStatement.setString(17, rsfreigcost);
            preparedStatement.setString(18, rscommcost);
            preparedStatement.setString(19, rstotalcost);
            preparedStatement.setString(20, rsrefid);
            preparedStatement.setString(21, rstrnno);
            preparedStatement.setString(22, rsregno);
            preparedStatement.setString(23, rsseqno);
            preparedStatement.setString(24, rssubseqno);
            preparedStatement.setString(25, rsslbatchno);
            preparedStatement.setTimestamp(26, rslastupddt);
            preparedStatement.setString(27, rslastupdusr);
            preparedStatement.setString(28, rslastupdver);
            preparedStatement.setString(29, rsrowguid);
            preparedStatement.setString(30, rsggqty);
            preparedStatement.setString(31, rsstoreqty);
            preparedStatement.setString(32, rsggbjqty);
            preparedStatement.setString(33, rsorgcost);
            preparedStatement.setString(34, rsorgtotalcost);
            preparedStatement.setString(35, rscompanycde);
            preparedStatement.setString(36, rsentitykey);

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

    public static boolean Stkledgertrnposupdate(String rssltrnid, String rssku, Timestamp rsdate, String rstrntype, String rstrnsubtype, String rsinstitcde,
                                                String rsloccde, String rsdeptcde, String rsclasscde, String rsprodtype, String rsreasoncde, String rsqty,
                                                String rscost, String rsretail, String rspurcost, String rspackcost, String rsfreigcost, String rscommcost,
                                                String rstotalcost, String rsrefid, String rstrnno, String rsregno, String rsseqno, String rssubseqno, String rsslbatchno,
                                                Timestamp rslastupddt, String rslastupdusr, String rslastupdver, String rsrowguid, String rsggqty, String rsstoreqty,
                                                String rsggbjqty, String rsorgcost, String rsorgtotalcost, String rscompanycde, String rsentitykey,
                                                String frdatabase) throws SQLException {
        boolean result = true;
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        String updateSQL;

        updateSQL = "UPDATE STK_LEDGER_TRN_POS "
                + "SET  SL_TRN_ID   = ? "
                + ", SKU           = ? "
                + ", DATE_         = ? "
                + ", TRN_TYPE      = ? "
                + ", TRN_SUB_TYPE  = ? "
                + ", INSTIT_CDE    = ? "
                + ", LOC_CDE       = ? "
                + ", DEPT_CDE      = ? "
                + ", CLASS_CDE     = ? "
                + ", PROD_TYPE     = ? "
                + ", REASON_CDE    = ? "
                + ", QTY           = ? "
                + ", COST          = ? "
                + ", RETAIL        = ? "
                + ", PUR_COST      = ? "
                + ", PACK_COST     = ? "
                + ", FREIG_COST    = ? "
                + ", COMM_COST     = ? "
                + ", TOTALCOST     = ? "
                + ", REF_ID        = ? "
                + ", TRN_NO        = ? "
                + ", REG_NO        = ? "
                + ", SEQ_NO        = ? "
                + ", SUB_SEQ_NO    = ? "
                + ", SL_BATCH_NO   = ? "
                + ", LAST_UPD_DT   = ? "
                + ", LAST_UPD_USR  = ? "
                + ", LAST_UPD_VER  = ? "
                + ", ROWGUID       = ? "
                + ", GG_QTY        = ? "
                + ", STORE_QTY     = ? "
                + ", GG_BJ_QTY     = ? "
                + ", ORG_COST      = ? "
                + ", ORG_TOTALCOST = ? "
                + ", COMPANY_CDE   = ? "
                + "WHERE ENTITY_KEY    = ? "
        ;

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

            preparedStatement.setString(1, rssltrnid);
            preparedStatement.setString(2, rssku);
            preparedStatement.setTimestamp(3, rsdate);
            preparedStatement.setString(4, rstrntype);
            preparedStatement.setString(5, rstrnsubtype);
            preparedStatement.setString(6, rsinstitcde);
            preparedStatement.setString(7, rsloccde);
            preparedStatement.setString(8, rsdeptcde);
            preparedStatement.setString(9, rsclasscde);
            preparedStatement.setString(10, rsprodtype);
            preparedStatement.setString(11, rsreasoncde);
            preparedStatement.setString(12, rsqty);
            preparedStatement.setString(13, rscost);
            preparedStatement.setString(14, rsretail);
            preparedStatement.setString(15, rspurcost);
            preparedStatement.setString(16, rspackcost);
            preparedStatement.setString(17, rsfreigcost);
            preparedStatement.setString(18, rscommcost);
            preparedStatement.setString(19, rstotalcost);
            preparedStatement.setString(20, rsrefid);
            preparedStatement.setString(21, rstrnno);
            preparedStatement.setString(22, rsregno);
            preparedStatement.setString(23, rsseqno);
            preparedStatement.setString(24, rssubseqno);
            preparedStatement.setString(25, rsslbatchno);
            preparedStatement.setTimestamp(26, rslastupddt);
            preparedStatement.setString(27, rslastupdusr);
            preparedStatement.setString(28, rslastupdver);
            preparedStatement.setString(29, rsrowguid);
            preparedStatement.setString(30, rsggqty);
            preparedStatement.setString(31, rsstoreqty);
            preparedStatement.setString(32, rsggbjqty);
            preparedStatement.setString(33, rsorgcost);
            preparedStatement.setString(34, rsorgtotalcost);
            preparedStatement.setString(35, rscompanycde);
            preparedStatement.setString(36, rsentitykey);

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

    public static boolean Stkledgertrnposchkexists(String entitykey, String frdatabase)
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

            String selectSQL = "SELECT SL_TRN_ID " + "FROM STK_LEDGER_TRN_POS "
                    + "where ENTITY_KEY ='" + entitykey + "'";

            // logger.info(selectSQL);

            preparedStatement = dbConnection.prepareStatement(selectSQL);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String rschktxdate = rs.getString("SL_TRN_ID");

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
