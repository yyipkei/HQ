package com.bridge.insertstockonhand;

import com.bridge.main.HikariQracleTo;
import com.bridge.main.HikariRms;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.Objects;

/**
 * Created by keyip on 7/07/2015.
 */
public class InsertStkledgerpossummarypos {
    private static final Logger logger = Logger.getLogger(InsertStkledgerpossummarypos.class);

    public static boolean Stkledgerpossummaryposinsert(String rsloccde, String rssku, String rsonhandqty, Timestamp rsopendate, Timestamp rslastupddt, String rsorionhandqty,
                                                String rscompanycde, String rsrowguid, String rsentitykey, String frdatabase) throws SQLException {

        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        String insertTableSQL;

        insertTableSQL = "INSERT INTO STK_LEDGER_POS_SUMMARY_POS (LOC_CDE,SKU,ONHAND_QTY,OPEN_DATE,LAST_UPD_DT,ORI_ONHAND_QTY,COMPANY_CDE,ROWGUID,ENTITY_KEY) "
                + "VALUES ( ?,?,?,?,?,?,?,?,? )";

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

            preparedStatement.setString(1, rsloccde);
            preparedStatement.setString(2, rssku);
            preparedStatement.setString(3, rsonhandqty);
            preparedStatement.setTimestamp(4, rsopendate);
            preparedStatement.setTimestamp(5, rslastupddt);
            preparedStatement.setString(6, rsorionhandqty);
            preparedStatement.setString(7, rscompanycde);
            preparedStatement.setString(8, rsrowguid);
            preparedStatement.setString(9, rsentitykey);

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

    public static boolean Stkledgerpossummaryposupdate(String rsloccde, String rssku, String rsonhandqty, Timestamp rsopendate, Timestamp rslastupddt, String rsorionhandqty,
                                                String rscompanycde, String rsrowguid, String rsentitykey, String frdatabase) throws SQLException {
        boolean result = true;
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        String updateSQL;

        updateSQL = "UPDATE STK_LEDGER_POS_SUMMARY_POS "
                + "SET LOC_CDE      = ? "
                + ", SKU            = ? "
                + ", ONHAND_QTY     = ? "
                + ", OPEN_DATE      = ? "
                + ", LAST_UPD_DT    = ? "
                + ", ORI_ONHAND_QTY = ? "
                + ", COMPANY_CDE    = ? "
                + ", ROWGUID        = ? "
                + "WHERE ENTITY_KEY     = ? ";

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

            preparedStatement.setString(1, rsloccde);
            preparedStatement.setString(2, rssku);
            preparedStatement.setString(3, rsonhandqty);
            preparedStatement.setTimestamp(4, rsopendate);
            preparedStatement.setTimestamp(5, rslastupddt);
            preparedStatement.setString(6, rsorionhandqty);
            preparedStatement.setString(7, rscompanycde);
            preparedStatement.setString(8, rsrowguid);
            preparedStatement.setString(9, rsentitykey);

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

    public static boolean Stkledgerpossummaryposchkexists(String entitykey, String frdatabase)
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

            String selectSQL = "SELECT LOC_CDE " + "FROM STK_LEDGER_POS_SUMMARY_POS "
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
