package com.bridge.insertsales;

import com.bridge.main.HikariMssql;
import com.bridge.main.HikariQracleTo;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.Objects;

/**
 * Created by keyip on 20/07/2015.
 */
public class InsertPmmemomap {

    private static final Logger logger = Logger.getLogger(InsertPmmemomap.class);

    public static boolean Pmmemomapinsert(String rsfposmemono, String rsfposserverid, String rsfposlocation, String rsfposterminal,
                                          String rsfpostxtype, String rsfposdepositno, String rsfposreserveno, String rspos2000seq, String rspos2000txtype,
                                          String rstxdate, Timestamp rslastupddt, String frdatabase) throws SQLException {

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

            String insertTableSQL = "INSERT INTO PM_MEMO_MAP"
                    + "(FPOS_MEMO_NO,FPOS_SERVER_ID,FPOS_LOCATION,FPOS_TERMINAL,FPOS_TX_TYPE,FPOS_DEPOSIT_NO,FPOS_RESERVE_NO,POS2000_SEQ,POS2000_TX_TYPE,TX_DATE,LAST_UPD_DT) "
                    + "VALUES" + "(?,?,?,?,?,?,?,?,?,?,?)";

            preparedStatement = dbConnection.prepareStatement(insertTableSQL);

            preparedStatement.setString(1, rsfposmemono);
            preparedStatement.setString(2, rsfposserverid);
            preparedStatement.setString(3, rsfposlocation);
            preparedStatement.setString(4, rsfposterminal);
            preparedStatement.setString(5, rsfpostxtype);
            preparedStatement.setString(6, rsfposdepositno);
            preparedStatement.setString(7, rsfposreserveno);
            preparedStatement.setString(8, rspos2000seq);
            preparedStatement.setString(9, rspos2000txtype);
            preparedStatement.setString(10, rstxdate);
            preparedStatement.setTimestamp(11, rslastupddt);

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

    public static boolean Pmmemomapupdate(String rsfposmemono, String rsfposserverid, String rsfposlocation, String rsfposterminal,
                                          String rsfpostxtype, String rsfposdepositno, String rsfposreserveno, String rspos2000seq, String rspos2000txtype,
                                          String rstxdate, Timestamp rslastupddt, String frdatabase) throws SQLException {

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

            // String updateSQL = "Update Pmmemomap set VOID_BY = ? "
            // +
            // "where TX_DATE = ? and LOC_CODE = ? and REG_NO = ? and TX_NO = ?";

            String updateSQL = "UPDATE PM_MEMO_MAP "
                    + "SET FPOS_SERVER_ID  = ? "
                    + ", FPOS_LOCATION   = ? "
                    + ", FPOS_TERMINAL   = ? "
                    + ", FPOS_TX_TYPE    = ? "
                    + ", FPOS_DEPOSIT_NO = ? "
                    + ", FPOS_RESERVE_NO = ? "
                    + ", POS2000_SEQ     = ? "
                    + ", POS2000_TX_TYPE = ? "
                    + ", TX_DATE         = ? "
                    + ", LAST_UPD_DT     = ? "
                    + "WHERE FPOS_MEMO_NO  = ? ";


            preparedStatement = dbConnection.prepareStatement(updateSQL);

            preparedStatement.setString(1, rsfposserverid);
            preparedStatement.setString(2, rsfposlocation);
            preparedStatement.setString(3, rsfposterminal);
            preparedStatement.setString(4, rsfpostxtype);
            preparedStatement.setString(5, rsfposdepositno);
            preparedStatement.setString(6, rsfposreserveno);
            preparedStatement.setString(7, rspos2000seq);
            preparedStatement.setString(8, rspos2000txtype);
            preparedStatement.setString(9, rstxdate);
            preparedStatement.setTimestamp(10, rslastupddt);
            preparedStatement.setString(11, rsfposmemono);

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

    public static boolean Pmmemomapchkexists(String entitykey, String frdatabase)
            throws SQLException {

      /*  String[] parts = entitykey.split(",");
        String txdate = parts[0];
        String loccode = parts[1];
        String regno = parts[2];
        String txno = parts[3];*/

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

            String selectSQL = "SELECT FPOS_MEMO_NO " + "FROM PM_MEMO_MAP "
                    + "where FPOS_MEMO_NO ='" + entitykey + "'";

            preparedStatement = dbConnection.prepareStatement(selectSQL);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String rstxdate = rs.getString("FPOS_MEMO_NO");

                // logger.info(rstxdate);

                if (rstxdate != null) {
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