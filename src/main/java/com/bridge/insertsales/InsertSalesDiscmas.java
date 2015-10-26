package com.bridge.insertsales;

import com.bridge.main.HikariMssql;
import com.bridge.main.HikariQracleTo;
import com.bridge.main.HikariRms;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.Objects;

/**
 * Created by keyip on 25-Jun-15.
 */
public class InsertSalesDiscmas {

    private static final Logger logger = Logger.getLogger(InsertSalesDiscmas.class);

    public static boolean SalesDiscmasinsert(String rsdiscid, String rsdiscname, String rsdiscper, String rsdiscamt, String rsdisctype, String rsallowover, String rsreasongroup,
                                             String rsdisctxitem, String rsloctype, String rsdiscflag, String rsefffrdate, String rsefftodate, String rspurchaseflag,
                                             String rspurchaseqty, String rspurchaseamt, String rsopenceiling, String rsstatus, String rsversion, Timestamp rsdraftdate,
                                             String rsdraftby, Timestamp rscfmdt, String rscfmby, Timestamp rsappdt, String rsappby, String rsextradiscid, Timestamp rslastupddt,
                                             String rslastupdusr, String rslastupdver, String rscontrolid, String rsentitykey, String frdatabase) throws SQLException {

        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        String insertTableSQL;

        insertTableSQL = "INSERT INTO Discmas (DISC_ID,DISC_NAME,DISC_PER,DISC_AMT,DISC_TYPE,ALLOW_OVER,REASON_GROUP,DISC_TX_ITEM,LOC_TYPE,DISC_FLAG,EFF_FR_DATE," +
                "EFF_TO_DATE,PURCHASE_FLAG,PURCHASE_QTY,PURCHASE_AMT,OPEN_CEILING,STATUS,VERSION,DRAFT_DATE,DRAFT_BY,CFM_DT,CFM_BY,APP_DT,APP_BY,EXTRA_DISC_ID," +
                "LAST_UPD_DT,LAST_UPD_USR,LAST_UPD_VER,CONTROL_ID,ENTITY_KEY) "
                + "VALUES ( ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

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

            preparedStatement = dbConnection.prepareStatement(insertTableSQL);

            preparedStatement.setString(1, rsdiscid);
            preparedStatement.setString(2, rsdiscname);
            preparedStatement.setString(3, rsdiscper);
            preparedStatement.setString(4, rsdiscamt);
            preparedStatement.setString(5, rsdisctype);
            preparedStatement.setString(6, rsallowover);
            preparedStatement.setString(7, rsreasongroup);
            preparedStatement.setString(8, rsdisctxitem);
            preparedStatement.setString(9, rsloctype);
            preparedStatement.setString(10, rsdiscflag);
            preparedStatement.setString(11, rsefffrdate);
            preparedStatement.setString(12, rsefftodate);
            preparedStatement.setString(13, rspurchaseflag);
            preparedStatement.setString(14, rspurchaseqty);
            preparedStatement.setString(15, rspurchaseamt);
            preparedStatement.setString(16, rsopenceiling);
            preparedStatement.setString(17, rsstatus);
            preparedStatement.setString(18, rsversion);
            preparedStatement.setTimestamp(19, rsdraftdate);
            preparedStatement.setString(20, rsdraftby);
            preparedStatement.setTimestamp(21, rscfmdt);
            preparedStatement.setString(22, rscfmby);
            preparedStatement.setTimestamp(23, rsappdt);
            preparedStatement.setString(24, rsappby);
            preparedStatement.setString(25, rsextradiscid);
            preparedStatement.setTimestamp(26, rslastupddt);
            preparedStatement.setString(27, rslastupdusr);
            preparedStatement.setString(28, rslastupdver);
            preparedStatement.setString(29, rscontrolid);
            preparedStatement.setString(30, rsentitykey);

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

    public static boolean SalesDiscmasupdate(String rsdiscid, String rsdiscname, String rsdiscper, String rsdiscamt, String rsdisctype, String rsallowover, String rsreasongroup,
                                             String rsdisctxitem, String rsloctype, String rsdiscflag, String rsefffrdate, String rsefftodate, String rspurchaseflag,
                                             String rspurchaseqty, String rspurchaseamt, String rsopenceiling, String rsstatus, String rsversion, Timestamp rsdraftdate,
                                             String rsdraftby, Timestamp rscfmdt, String rscfmby, Timestamp rsappdt, String rsappby, String rsextradiscid, Timestamp rslastupddt,
                                             String rslastupdusr, String rslastupdver, String rscontrolid, String rsentitykey, String frdatabase) throws SQLException {

        boolean result = true;
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        String updateSQL;

        updateSQL = "UPDATE DISCMAS "
                + "SET DISC_ID     = ? "
                + ", DISC_NAME     = ? "
                + ", DISC_PER      = ? "
                + ", DISC_AMT      = ? "
                + ", DISC_TYPE     = ? "
                + ", ALLOW_OVER    = ? "
                + ", REASON_GROUP  = ? "
                + ", DISC_TX_ITEM  = ? "
                + ", LOC_TYPE      = ? "
                + ", DISC_FLAG     = ? "
                + ", EFF_FR_DATE   = ? "
                + ", EFF_TO_DATE   = ? "
                + ", PURCHASE_FLAG = ? "
                + ", PURCHASE_QTY  = ? "
                + ", PURCHASE_AMT  = ? "
                + ", OPEN_CEILING  = ? "
                + ", STATUS        = ? "
                + ", VERSION       = ? "
                + ", DRAFT_DATE    = ? "
                + ", DRAFT_BY      = ? "
                + ", CFM_DT        = ? "
                + ", CFM_BY        = ? "
                + ", APP_DT        = ? "
                + ", APP_BY        = ? "
                + ", EXTRA_DISC_ID = ? "
                + ", LAST_UPD_DT   = ? "
                + ", LAST_UPD_USR  = ? "
                + ", LAST_UPD_VER  = ? "
                + ", CONTROL_ID    = ? "
                + "WHERE ENTITY_KEY    = ? ";

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

            preparedStatement = dbConnection.prepareStatement(updateSQL);

            preparedStatement.setString(1, rsdiscid);
            preparedStatement.setString(2, rsdiscname);
            preparedStatement.setString(3, rsdiscper);
            preparedStatement.setString(4, rsdiscamt);
            preparedStatement.setString(5, rsdisctype);
            preparedStatement.setString(6, rsallowover);
            preparedStatement.setString(7, rsreasongroup);
            preparedStatement.setString(8, rsdisctxitem);
            preparedStatement.setString(9, rsloctype);
            preparedStatement.setString(10, rsdiscflag);
            preparedStatement.setString(11, rsefffrdate);
            preparedStatement.setString(12, rsefftodate);
            preparedStatement.setString(13, rspurchaseflag);
            preparedStatement.setString(14, rspurchaseqty);
            preparedStatement.setString(15, rspurchaseamt);
            preparedStatement.setString(16, rsopenceiling);
            preparedStatement.setString(17, rsstatus);
            preparedStatement.setString(18, rsversion);
            preparedStatement.setTimestamp(19, rsdraftdate);
            preparedStatement.setString(20, rsdraftby);
            preparedStatement.setTimestamp(21, rscfmdt);
            preparedStatement.setString(22, rscfmby);
            preparedStatement.setTimestamp(23, rsappdt);
            preparedStatement.setString(24, rsappby);
            preparedStatement.setString(25, rsextradiscid);
            preparedStatement.setTimestamp(26, rslastupddt);
            preparedStatement.setString(27, rslastupdusr);
            preparedStatement.setString(28, rslastupdver);
            preparedStatement.setString(29, rscontrolid);
            preparedStatement.setString(30, rsentitykey);

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

    public static boolean SalesDiscmaschkexists(String entitykey, String frdatabase)
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

                HikariMssql Mssqlpool = HikariMssql.getInstance();
                dbConnection = Mssqlpool.getConnection();
            } else {
                // dbConnection = OracleTo.getDBConnection();

                HikariQracleTo OrcaleFromTo = HikariQracleTo.getInstance();
                dbConnection = OrcaleFromTo.getConnection();
            }

            String selectSQL = "SELECT DISC_ID " + "FROM Discmas "
                    + "where ENTITY_KEY ='" + entitykey + "'";

            // logger.info(selectSQL);

            preparedStatement = dbConnection.prepareStatement(selectSQL);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String rschktxdate = rs.getString("DISC_ID");

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
