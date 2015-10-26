package com.bridge.insertdeposit;

import com.bridge.main.HikariMerge;
import com.bridge.main.HikariQracleTo;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.Objects;

/**
 * Created by keyip on 11-Jun-15.
 */
public class InsertBrguest {

    private static final Logger logger = Logger.getLogger(InsertBrguest.class);

    public static boolean Brguestinsert(String rsbridalkey, String rsguestkey, String rsguestname, String rstelhome, String rstelcompany,
                                        String rstelcompanyex, String rstelmobile, String rsgiftcardreq, String rslastordernum, String rsmsg,
                                        Timestamp rsupdateddatetime, String rsupdateduser, String rsrowguid, Timestamp rslastupddt, String frdatabase) throws SQLException {

        boolean result = true;
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        String insertTableSQL;

        try {

            if (Objects.equals(frdatabase, "Oracle")) {
                // dbConnection = Merge.getDBConnection();

                HikariMerge Mergepool = HikariMerge.getInstance();
                dbConnection = Mergepool.getConnection();

                insertTableSQL = "INSERT INTO BR_GUEST"
                        + "(BRIDAL_KEY,GUEST_KEY,GUEST_NAME,TEL_HOME,TEL_COMPANY,TEL_COMPANY_EX,TEL_MOBILE,GIFT_CARD_REQ," +
                        "LAST_ORDER_NUM,MSG,UPDATED_DATETIME,UPDATED_USER,ROWGUID,LAST_UPD_DT) "
                        + "VALUES" + "(?,?,?,?,?,?,?,?,?,?,?,?," + "newid()" + ",?)";


                preparedStatement = dbConnection
                        .prepareStatement(insertTableSQL);

                preparedStatement.setString(1, rsbridalkey);
                preparedStatement.setString(2, rsguestkey);
                preparedStatement.setString(3, rsguestname);
                preparedStatement.setString(4, rstelhome);
                preparedStatement.setString(5, rstelcompany);
                preparedStatement.setString(6, rstelcompanyex);
                preparedStatement.setString(7, rstelmobile);
                preparedStatement.setString(8, rsgiftcardreq);
                preparedStatement.setString(9, rslastordernum);
                preparedStatement.setString(10, rsmsg);
                preparedStatement.setTimestamp(11, rsupdateddatetime);
                preparedStatement.setString(12, rsupdateduser);
                preparedStatement.setTimestamp(13, rslastupddt);


            } else {
                // dbConnection = OracleTo.getDBConnection();

                HikariQracleTo OrcaleFromTo = HikariQracleTo.getInstance();
                dbConnection = OrcaleFromTo.getConnection();

                insertTableSQL = "INSERT INTO BR_GUEST"
                        + "(BRIDAL_KEY,GUEST_KEY,GUEST_NAME,TEL_HOME,TEL_COMPANY,TEL_COMPANY_EX,TEL_MOBILE,GIFT_CARD_REQ," +
                        "LAST_ORDER_NUM,MSG,UPDATED_DATETIME,UPDATED_USER,ROWGUID,LAST_UPD_DT) "
                        + "VALUES" + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

                preparedStatement = dbConnection
                        .prepareStatement(insertTableSQL);

                preparedStatement.setString(1, rsbridalkey);
                preparedStatement.setString(2, rsguestkey);
                preparedStatement.setString(3, rsguestname);
                preparedStatement.setString(4, rstelhome);
                preparedStatement.setString(5, rstelcompany);
                preparedStatement.setString(6, rstelcompanyex);
                preparedStatement.setString(7, rstelmobile);
                preparedStatement.setString(8, rsgiftcardreq);
                preparedStatement.setString(9, rslastordernum);
                preparedStatement.setString(10, rsmsg);
                preparedStatement.setTimestamp(11, rsupdateddatetime);
                preparedStatement.setString(12, rsupdateduser);
                preparedStatement.setString(13, rsrowguid);
                preparedStatement.setTimestamp(14, rslastupddt);

            }

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

    public static boolean Brguestupdate(String rsbridalkey, String rsguestkey, String rsguestname, String rstelhome, String rstelcompany,
                                        String rstelcompanyex, String rstelmobile, String rsgiftcardreq, String rslastordernum, String rsmsg,
                                        Timestamp rsupdateddatetime, String rsupdateduser, String rsrowguid, Timestamp rslastupddt, String frdatabase) throws SQLException {

        boolean result = true;
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;

        try {

            if (Objects.equals(frdatabase, "Oracle")) {
                // dbConnection = Merge.getDBConnection();

                HikariMerge Mergepool = HikariMerge.getInstance();
                dbConnection = Mergepool.getConnection();
            } else {
                // dbConnection = OracleTo.getDBConnection();

                HikariQracleTo OrcaleFromTo = HikariQracleTo.getInstance();
                dbConnection = OrcaleFromTo.getConnection();
            }

            String updateSQL = "UPDATE BR_GUEST "
                    + "SET  GUEST_NAME       = ? "
                    + ", TEL_HOME         = ? "
                    + ", TEL_COMPANY      = ? "
                    + ", TEL_COMPANY_EX   = ? "
                    + ", TEL_MOBILE       = ? "
                    + ", GIFT_CARD_REQ    = ? "
                    + ", LAST_ORDER_NUM   = ? "
                    + ", MSG              = ? "
                    + ", UPDATED_DATETIME = ? "
                    + ", UPDATED_USER     = ? "
                    + ", LAST_UPD_DT      = ? "
                    + "WHERE BRIDAL_KEY     = ? "
                    + "AND GUEST_KEY        = ? ";

            preparedStatement = dbConnection.prepareStatement(updateSQL);

            preparedStatement.setString(1, rsguestname);
            preparedStatement.setString(2, rstelhome);
            preparedStatement.setString(3, rstelcompany);
            preparedStatement.setString(4, rstelcompanyex);
            preparedStatement.setString(5, rstelmobile);
            preparedStatement.setString(6, rsgiftcardreq);
            preparedStatement.setString(7, rslastordernum);
            preparedStatement.setString(8, rsmsg);
            preparedStatement.setTimestamp(9, rsupdateddatetime);
            preparedStatement.setString(10, rsupdateduser);
            preparedStatement.setTimestamp(11, rslastupddt);
            preparedStatement.setString(12, rsbridalkey);
            preparedStatement.setString(13, rsguestkey);


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

    public static boolean Brguestchkexists(String entitykey, String frdatabase)
            throws SQLException {

        String[] parts = entitykey.split(",");
        String bridekey = parts[0];
        String guestkey = parts[1];

        boolean result = false;
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;

        try {

            if (Objects.equals(frdatabase, "Oracle")) {
                // dbConnection = Merge.getDBConnection();

                HikariMerge Mergepool = HikariMerge.getInstance();
                dbConnection = Mergepool.getConnection();
            } else {
                // dbConnection = OracleTo.getDBConnection();

                HikariQracleTo OrcaleFromTo = HikariQracleTo.getInstance();
                dbConnection = OrcaleFromTo.getConnection();
            }

            String selectSQL = "SELECT BRIDAL_KEY " + "FROM BR_GUEST "
                    + "where BRIDAL_KEY ='"
                    + bridekey
                    + "'"
                    + "and GUEST_KEY ='"
                    + guestkey
                    + "'";

            // logger.info(selectSQL);

            preparedStatement = dbConnection.prepareStatement(selectSQL);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String rschktxdate = rs.getString("BRIDAL_KEY");

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
