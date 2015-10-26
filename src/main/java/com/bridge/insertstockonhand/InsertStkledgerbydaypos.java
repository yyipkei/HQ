package com.bridge.insertstockonhand;

import com.bridge.main.HikariQracleTo;
import com.bridge.main.HikariRms;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.Objects;

/**
 * Created by keyip on 09-Jun-15.
 */
public class InsertStkledgerbydaypos {

    private static final Logger logger = Logger.getLogger(InsertStkledgerbydaypos.class);

    public static boolean Stkledgerbydayposinsert(Timestamp rsdate, String rsloccde, String rssku, String rsopeninvqty,
                                                  String rsopeninvcost, String rsopeninvretail, String rsoricost,
                                                  String rsoriretail, String rsseasoncde, String rsstyle, String rsggqty, String rsstoreqty, String rsggbjqty,
                                                  String rscompanycde, String rsrowguid, Timestamp rslastupddt, String rsentitykey,
                                                  String frdatabase) throws SQLException {

        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        String insertTableSQL;

        insertTableSQL = "INSERT INTO stk_ledger_by_day_pos (DATE_,LOC_CDE,SKU,OPEN_INV_QTY,OPEN_INV_COST,OPEN_INV_RETAIL,ORI_COST,ORI_RETAIL,SEASON_CDE,STYLE,GG_QTY,STORE_QTY,GG_BJ_QTY,COMPANY_CDE,ROWGUID,LAST_UPD_DT,ENTITY_KEY) "
                + "VALUES ( ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,? )";

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

            preparedStatement.setTimestamp(1, rsdate);
            preparedStatement.setString(2, rsloccde);
            preparedStatement.setString(3, rssku);
            preparedStatement.setString(4, rsopeninvqty);
            preparedStatement.setString(5, rsopeninvcost);
            preparedStatement.setString(6, rsopeninvretail);
            preparedStatement.setString(7, rsoricost);
            preparedStatement.setString(8, rsoriretail);
            preparedStatement.setString(9, rsseasoncde);
            preparedStatement.setString(10, rsstyle);
            preparedStatement.setString(11, rsggqty);
            preparedStatement.setString(12, rsstoreqty);
            preparedStatement.setString(13, rsggbjqty);
            preparedStatement.setString(14, rscompanycde);
            preparedStatement.setString(15, rsrowguid);
            preparedStatement.setTimestamp(16, rslastupddt);
            preparedStatement.setString(17, rsentitykey);

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

    public static boolean Stkledgerbydayposupdate(Timestamp rsdate, String rsloccde, String rssku, String rsopeninvqty,
                                                  String rsopeninvcost, String rsopeninvretail, String rsoricost,
                                                  String rsoriretail, String rsseasoncde, String rsstyle, String rsggqty, String rsstoreqty, String rsggbjqty,
                                                  String rscompanycde, String rsrowguid, Timestamp rslastupddt, String rsentitykey,
                                                  String frdatabase) throws SQLException {
        boolean result = true;
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        String updateSQL;

        updateSQL = "UPDATE STK_LEDGER_BY_DAY_POS "
                +"SET DATE_         = ? "
                +", LOC_CDE         = ? "
                +", SKU             = ? "
                +", OPEN_INV_QTY    = ? "
                +", OPEN_INV_COST   = ? "
                +", OPEN_INV_RETAIL = ? "
                +", ORI_COST        = ? "
                +", ORI_RETAIL      = ? "
                +", SEASON_CDE      = ? "
                +", STYLE           = ? "
                +", GG_QTY          = ? "
                +", STORE_QTY       = ? "
                +", GG_BJ_QTY       = ? "
                +", COMPANY_CDE     = ? "
                +", ROWGUID         = ? "
                +", LAST_UPD_DT     = ? "
                +"WHERE ENTITY_KEY      = ? ";

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

            preparedStatement.setTimestamp(1, rsdate);
            preparedStatement.setString(2, rsloccde);
            preparedStatement.setString(3, rssku);
            preparedStatement.setString(4, rsopeninvqty);
            preparedStatement.setString(5, rsopeninvcost);
            preparedStatement.setString(6, rsopeninvretail);
            preparedStatement.setString(7, rsoricost);
            preparedStatement.setString(8, rsoriretail);
            preparedStatement.setString(9, rsseasoncde);
            preparedStatement.setString(10, rsstyle);
            preparedStatement.setString(11, rsggqty);
            preparedStatement.setString(12, rsstoreqty);
            preparedStatement.setString(13, rsggbjqty);
            preparedStatement.setString(14, rscompanycde);
            preparedStatement.setString(15, rsrowguid);
            preparedStatement.setTimestamp(16, rslastupddt);
            preparedStatement.setString(17, rsentitykey);

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

    public static boolean Stkledgerbydayposchkexists(String entitykey, String frdatabase)
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

            String selectSQL = "SELECT LOC_CDE " + "FROM STK_LEDGER_BY_DAY_POS "
                    + "where ENTITY_KEY ='" + entitykey + "'";

            // logger.info(selectSQL);

            preparedStatement = dbConnection.prepareStatement(selectSQL);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String rschktxdate = rs.getString("LOC_CDE");

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
