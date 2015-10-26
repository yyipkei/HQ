package com.bridge.insertstockonhand;

import com.bridge.main.HikariQracleTo;
import com.bridge.main.HikariRms;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.Objects;

/**
 * Created by keyip on 10-Jun-15.
 */
public class InsertSxitempos {

    private static final Logger logger = Logger.getLogger(InsertSxitempos.class);

    public static boolean Sxitemposinsert(String rsboxno, String rssku, String rssenqty, String rsrecqty, String rssbartype,
                                          String rsrbartype, String rssencost, String rsreccost, String rsflag1, String rsdt1,
                                          String rslastupdusr, Timestamp rslastupddt, String rslastupdver, String rsrowguid,
                                          String rscompanycde, String rsentitykey,
                                          String frdatabase) throws SQLException {

        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        String insertTableSQL;

        insertTableSQL = "INSERT INTO sx_item_pos (BOXNO,SKU,SEN_QTY,REC_QTY,S_BAR_TYPE,R_BAR_TYPE,SEN_COST," +
                "REC_COST,FLAG1,DT1,LAST_UPD_USR,LAST_UPD_DT,LAST_UPD_VER,ROWGUID,COMPANY_CDE,ENTITY_KEY) "
                + "VALUES ( ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,? )";

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
            preparedStatement.setString(2, rssku);
            preparedStatement.setString(3, rssenqty);
            preparedStatement.setString(4, rsrecqty);
            preparedStatement.setString(5, rssbartype);
            preparedStatement.setString(6, rsrbartype);
            preparedStatement.setString(7, rssencost);
            preparedStatement.setString(8, rsreccost);
            preparedStatement.setString(9, rsflag1);
            preparedStatement.setString(10, rsdt1);
            preparedStatement.setString(11, rslastupdusr);
            preparedStatement.setTimestamp(12, rslastupddt);
            preparedStatement.setString(13, rslastupdver);
            preparedStatement.setString(14, rsrowguid);
            preparedStatement.setString(15, rscompanycde);
            preparedStatement.setString(16, rsentitykey);


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

    public static boolean Sxitemposupdate(String rsboxno, String rssku, String rssenqty, String rsrecqty, String rssbartype,
                                          String rsrbartype, String rssencost, String rsreccost, String rsflag1, String rsdt1,
                                          String rslastupdusr, Timestamp rslastupddt, String rslastupdver, String rsrowguid,
                                          String rscompanycde, String rsentitykey,
                                          String frdatabase) throws SQLException {
        boolean result = true;
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        String updateSQL;

        updateSQL = "UPDATE SX_ITEM_POS "
                + "SET BOXNO      = ? "
                + ", SKU          = ? "
                + ", SEN_QTY      = ? "
                + ", REC_QTY      = ? "
                + ", S_BAR_TYPE   = ? "
                + ", R_BAR_TYPE   = ? "
                + ", SEN_COST     = ? "
                + ", REC_COST     = ? "
                + ", FLAG1        = ? "
                + ", DT1          = ? "
                + ", LAST_UPD_USR = ? "
                + ", LAST_UPD_DT  = ? "
                + ", LAST_UPD_VER = ? "
                + ", ROWGUID      = ? "
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
            preparedStatement.setString(2, rssku);
            preparedStatement.setString(3, rssenqty);
            preparedStatement.setString(4, rsrecqty);
            preparedStatement.setString(5, rssbartype);
            preparedStatement.setString(6, rsrbartype);
            preparedStatement.setString(7, rssencost);
            preparedStatement.setString(8, rsreccost);
            preparedStatement.setString(9, rsflag1);
            preparedStatement.setString(10, rsdt1);
            preparedStatement.setString(11, rslastupdusr);
            preparedStatement.setTimestamp(12, rslastupddt);
            preparedStatement.setString(13, rslastupdver);
            preparedStatement.setString(14, rsrowguid);
            preparedStatement.setString(15, rscompanycde);
            preparedStatement.setString(16, rsentitykey);

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

    public static boolean Sxitemposchkexists(String entitykey, String frdatabase)
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

            String selectSQL = "SELECT BOXNO " + "FROM SX_ITEM_POS "
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
