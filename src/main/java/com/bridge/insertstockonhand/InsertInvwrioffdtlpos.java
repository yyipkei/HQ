package com.bridge.insertstockonhand;

import com.bridge.main.HikariQracleTo;
import com.bridge.main.HikariRms;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.Objects;

/**
 * Created by keyip on 10-Jun-15.
 */
public class InsertInvwrioffdtlpos {
    private static final Logger logger = Logger.getLogger(InsertInvwrioffdtlpos.class);

    public static boolean Invwrioffdtlposinsert(String rsinstitcde, String rswrioffid, String rsseqno, String rssku, String rsunitcost, String rsqty, String rscost,
                                                  String rsamount, Timestamp rslastupddt, String rslastupdusr, String rslastupdver, String rsrowguid, String rsvendorupc,
                                                  String rscompanycde, String rsentitykey,
                                                  String frdatabase) throws SQLException {

        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        String insertTableSQL;

        insertTableSQL = "INSERT INTO inv_wri_off_dtl_pos (INSTIT_CDE,WRI_OFF_ID,SEQ_NO,SKU,UNIT_COST,QTY,COST,AMOUNT,LAST_UPD_DT,LAST_UPD_USR,LAST_UPD_VER,ROWGUID,VENDOR_UPC,COMPANY_CDE,ENTITY_KEY) "
                + "VALUES ( ?,?,?,?,?,?,?,?,?,?,?,?,?,?,? )";

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

            preparedStatement.setString(1, rsinstitcde);
            preparedStatement.setString(2, rswrioffid);
            preparedStatement.setString(3, rsseqno);
            preparedStatement.setString(4, rssku);
            preparedStatement.setString(5, rsunitcost);
            preparedStatement.setString(6, rsqty);
            preparedStatement.setString(7, rscost);
            preparedStatement.setString(8, rsamount);
            preparedStatement.setTimestamp(9, rslastupddt);
            preparedStatement.setString(10, rslastupdusr);
            preparedStatement.setString(11, rslastupdver);
            preparedStatement.setString(12, rsrowguid);
            preparedStatement.setString(13, rsvendorupc);
            preparedStatement.setString(14, rscompanycde);
            preparedStatement.setString(15, rsentitykey);

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

    public static boolean Invwrioffdtlposupdate(String rsinstitcde, String rswrioffid, String rsseqno, String rssku, String rsunitcost, String rsqty, String rscost,
                                                  String rsamount, Timestamp rslastupddt, String rslastupdusr, String rslastupdver, String rsrowguid, String rsvendorupc,
                                                  String rscompanycde, String rsentitykey,
                                                  String frdatabase) throws SQLException {
        boolean result = true;
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        String updateSQL;

        updateSQL = "UPDATE INV_WRI_OFF_DTL_POS "
                +"SET INSTIT_CDE = ? "
                +", WRI_OFF_ID   = ? "
                +", SEQ_NO       = ? "
                +", SKU          = ? "
                +", UNIT_COST    = ? "
                +", QTY          = ? "
                +", COST         = ? "
                +", AMOUNT       = ? "
                +", LAST_UPD_DT  = ? "
                +", LAST_UPD_USR = ? "
                +", LAST_UPD_VER = ? "
                +", ROWGUID      = ? "
                +", VENDOR_UPC   = ? "
                +", COMPANY_CDE  = ? "
                +"WHERE  ENTITY_KEY   = ? ";

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

            preparedStatement.setString(1, rsinstitcde);
            preparedStatement.setString(2, rswrioffid);
            preparedStatement.setString(3, rsseqno);
            preparedStatement.setString(4, rssku);
            preparedStatement.setString(5, rsunitcost);
            preparedStatement.setString(6, rsqty);
            preparedStatement.setString(7, rscost);
            preparedStatement.setString(8, rsamount);
            preparedStatement.setTimestamp(9, rslastupddt);
            preparedStatement.setString(10, rslastupdusr);
            preparedStatement.setString(11, rslastupdver);
            preparedStatement.setString(12, rsrowguid);
            preparedStatement.setString(13, rsvendorupc);
            preparedStatement.setString(14, rscompanycde);
            preparedStatement.setString(15, rsentitykey);

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

    public static boolean Invwrioffdtlposchkexists(String entitykey, String frdatabase)
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

            String selectSQL = "SELECT INSTIT_CDE " + "FROM INV_WRI_OFF_DTL_POS "
                    + "where ENTITY_KEY ='" + entitykey + "'";

            // logger.info(selectSQL);

            preparedStatement = dbConnection.prepareStatement(selectSQL);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String rschktxdate = rs.getString("INSTIT_CDE");

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
