package com.bridge.insertmerge;

import com.bridge.main.HikariMerge;
import com.bridge.main.HikariQracleTo;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.Objects;

public class InsertCafecouponhdr {

    private static final Logger logger = Logger
            .getLogger(InsertCafecouponhdr.class);

    public static boolean Cafecouponhdrinsert(String rsid, String rstxdate,
                                              String rsloccode, String rsposno, String rsstaffid,
                                              String rsreasongroup, String rsreason, String rsremarks,
                                              String rsrowguid, String rsvipno, Timestamp rslastupddt,
                                              String frdatabase) throws SQLException {

        boolean result = true;
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        String insertTableSQL;

        try {

            if (Objects.equals(frdatabase, "Oracle")) {
                // dbConnection = Merge.getDBConnection();

                HikariMerge Mergepool = HikariMerge.getInstance();
                dbConnection = Mergepool.getConnection();

                insertTableSQL = "INSERT INTO CAFE_COUPON_HDR"
                        + "(ID,TX_DATE,LOC_CODE,POS_NO,STAFF_ID,REASON_GROUP,REASON,REMARKS,ROWGUID,VIP_NO,LAST_UPD_DT) "
                        + "VALUES" + "(?,?,?,?,?,?,?,?," + "newid() " + ",?,?)";

                preparedStatement = dbConnection
                        .prepareStatement(insertTableSQL);

                preparedStatement.setString(1, rsid);
                preparedStatement.setString(2, rstxdate);
                preparedStatement.setString(3, rsloccode);
                preparedStatement.setString(4, rsposno);
                preparedStatement.setString(5, rsstaffid);
                preparedStatement.setString(6, rsreasongroup);
                preparedStatement.setString(7, rsreason);
                preparedStatement.setString(8, rsremarks);
                preparedStatement.setString(9, rsvipno);
                preparedStatement.setTimestamp(10, rslastupddt);

            } else {
                // dbConnection = OracleTo.getDBConnection();

                HikariQracleTo OrcaleFromTo = HikariQracleTo.getInstance();
                dbConnection = OrcaleFromTo.getConnection();

                insertTableSQL = "INSERT INTO CAFE_COUPON_HDR"
                        + "(ID,TX_DATE,LOC_CODE,POS_NO,STAFF_ID,REASON_GROUP,REASON,REMARKS,ROWGUID,VIP_NO,LAST_UPD_DT) "
                        + "VALUES" + "(?,?,?,?,?,?,?,?,?,?,?)";

                preparedStatement = dbConnection
                        .prepareStatement(insertTableSQL);

                preparedStatement.setString(1, rsid);
                preparedStatement.setString(2, rstxdate);
                preparedStatement.setString(3, rsloccode);
                preparedStatement.setString(4, rsposno);
                preparedStatement.setString(5, rsstaffid);
                preparedStatement.setString(6, rsreasongroup);
                preparedStatement.setString(7, rsreason);
                preparedStatement.setString(8, rsremarks);
                preparedStatement.setString(9, rsrowguid);
                preparedStatement.setString(10, rsvipno);
                preparedStatement.setTimestamp(11, rslastupddt);

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

    public static boolean Cafecouponhdrupdate(String rsid, String rstxdate,
                                              String rsloccode, String rsposno, String rsstaffid,
                                              String rsreasongroup, String rsreason, String rsremarks,
                                              String rsrowguid, String rsvipno, Timestamp rslastupddt,
                                              String frdatabase) throws SQLException {

        boolean result = true;
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        String updateSQL;

        try {

            if (Objects.equals(frdatabase, "Oracle")) {
                // dbConnection = Merge.getDBConnection();

                HikariMerge Mergepool = HikariMerge.getInstance();
                dbConnection = Mergepool.getConnection();

                updateSQL = "UPDATE CAFE_COUPON_HDR "
                        + "SET  TX_DATE      = ? " + ", POS_NO       = ? "
                        + ", STAFF_ID     = ? " + ", REASON_GROUP = ? "
                        + ", REASON       = ? " + ", REMARKS      = ? "
                        + ", VIP_NO       = ? "
                        + ", LAST_UPD_DT  = ? " + "WHERE ID         = ? "
                        + "AND LOC_CODE     = ? ";

                preparedStatement = dbConnection.prepareStatement(updateSQL);

                preparedStatement.setString(1, rstxdate);
                preparedStatement.setString(2, rsposno);
                preparedStatement.setString(3, rsstaffid);
                preparedStatement.setString(4, rsreasongroup);
                preparedStatement.setString(5, rsreason);
                preparedStatement.setString(6, rsremarks);
                preparedStatement.setString(7, rsvipno);
                preparedStatement.setTimestamp(8, rslastupddt);
                preparedStatement.setString(9, rsid);
                preparedStatement.setString(10, rsloccode);
            } else {
                // dbConnection = OracleTo.getDBConnection();

                HikariQracleTo OrcaleFromTo = HikariQracleTo.getInstance();
                dbConnection = OrcaleFromTo.getConnection();

                updateSQL = "UPDATE CAFE_COUPON_HDR "
                        + "SET  TX_DATE      = ? " + ", POS_NO       = ? "
                        + ", STAFF_ID     = ? " + ", REASON_GROUP = ? "
                        + ", REASON       = ? " + ", REMARKS      = ? "
                        + ", ROWGUID      = ? " + ", VIP_NO       = ? "
                        + ", LAST_UPD_DT  = ? " + "WHERE ID         = ? "
                        + "AND LOC_CODE     = ? ";

                preparedStatement = dbConnection.prepareStatement(updateSQL);

                preparedStatement.setString(1, rstxdate);
                preparedStatement.setString(2, rsposno);
                preparedStatement.setString(3, rsstaffid);
                preparedStatement.setString(4, rsreasongroup);
                preparedStatement.setString(5, rsreason);
                preparedStatement.setString(6, rsremarks);
                preparedStatement.setString(7, rsrowguid);
                preparedStatement.setString(8, rsvipno);
                preparedStatement.setTimestamp(9, rslastupddt);
                preparedStatement.setString(10, rsid);
                preparedStatement.setString(11, rsloccode);
            }


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

    public static boolean Cafecouponhdrchkexists(String entitykey,
                                                 String frdatabase) throws SQLException {

        String[] parts = entitykey.split(",");
        String id = parts[0];
        String loccode = parts[1];

        boolean result = false;
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        String selectSQL;

        try {

            if (Objects.equals(frdatabase, "Oracle")) {
                // dbConnection = Merge.getDBConnection();

                HikariMerge Mergepool = HikariMerge.getInstance();
                dbConnection = Mergepool.getConnection();

                selectSQL = "SELECT TX_DATE " + "FROM CAFE_COUPON_HDR "
                        + "where ID ='" + id + "'" + "and LOC_CODE ='" + loccode
                        + "'";
            } else {
                // dbConnection = OracleTo.getDBConnection();

                HikariQracleTo OrcaleFromTo = HikariQracleTo.getInstance();
                dbConnection = OrcaleFromTo.getConnection();

                selectSQL = "SELECT TX_DATE " + "FROM CAFE_COUPON_HDR "
                        + "where ID ='" + id + "'" + "and LOC_CODE ='" + loccode
                        + "'";
            }

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
