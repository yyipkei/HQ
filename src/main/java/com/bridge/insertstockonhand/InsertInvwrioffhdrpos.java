package com.bridge.insertstockonhand;

import com.bridge.main.HikariQracleTo;
import com.bridge.main.HikariRms;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.Objects;

/**
 * Created by keyip on 10-Jun-15.
 */
public class InsertInvwrioffhdrpos {

    private static final Logger logger = Logger.getLogger(InsertInvwrioffhdrpos.class);

    public static boolean Invwrioffhdrposinsert(String rsinstitcde, String rswrioffid, String rswrioffno, String rsstatus, Timestamp rseffdt, String rsreasontype,
                                                String rsreasoncode, String rsloccde, String rsremark, String rstotcost, String rsappby, Timestamp rsappdt, Timestamp rslastupddt,
                                                String rslastupdusr, String rslastupdver, String rsrowguid, String rsrevby, Timestamp rsrevdt, String rsinsuranceclaim, String rsgoodsinspection,
                                                String rscompanycde, String rsentitykey,
                                                String frdatabase) throws SQLException {

        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        String insertTableSQL;

        insertTableSQL = "INSERT INTO INV_WRI_OFF_HDR_POS (INSTIT_CDE,WRI_OFF_ID,WRI_OFF_NO,STATUS,EFF_DT,REASON_TYPE,REASON_CODE,LOC_CDE,REMARK,TOT_COST,APP_BY,APP_DT,LAST_UPD_DT,LAST_UPD_USR,LAST_UPD_VER,ROWGUID,REV_BY,REV_DT,INSURANCE_CLAIM,GOODS_INSPECTION,COMPANY_CDE,ENTITY_KEY) "
                + "VALUES ( ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,? )";

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
            preparedStatement.setString(3, rswrioffno);
            preparedStatement.setString(4, rsstatus);
            preparedStatement.setTimestamp(5, rseffdt);
            preparedStatement.setString(6, rsreasontype);
            preparedStatement.setString(7, rsreasoncode);
            preparedStatement.setString(8, rsloccde);
            preparedStatement.setString(9, rsremark);
            preparedStatement.setString(10, rstotcost);
            preparedStatement.setString(11, rsappby);
            preparedStatement.setTimestamp(12, rsappdt);
            preparedStatement.setTimestamp(13, rslastupddt);
            preparedStatement.setString(14, rslastupdusr);
            preparedStatement.setString(15, rslastupdver);
            preparedStatement.setString(16, rsrowguid);
            preparedStatement.setString(17, rsrevby);
            preparedStatement.setTimestamp(18, rsrevdt);
            preparedStatement.setString(19, rsinsuranceclaim);
            preparedStatement.setString(20, rsgoodsinspection);
            preparedStatement.setString(21, rscompanycde);
            preparedStatement.setString(22, rsentitykey);


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

    public static boolean Invwrioffhdrposupdate(String rsinstitcde, String rswrioffid, String rswrioffno, String rsstatus, Timestamp rseffdt, String rsreasontype,
                                                String rsreasoncode, String rsloccde, String rsremark, String rstotcost, String rsappby, Timestamp rsappdt, Timestamp rslastupddt,
                                                String rslastupdusr, String rslastupdver, String rsrowguid, String rsrevby, Timestamp rsrevdt, String rsinsuranceclaim, String rsgoodsinspection,
                                                String rscompanycde, String rsentitykey,
                                                String frdatabase) throws SQLException {
        boolean result = true;
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        String updateSQL;

        updateSQL = "UPDATE INV_WRI_OFF_HDR_POS "
                + "SET INSTIT_CDE     = ? "
                + ", WRI_OFF_ID       = ? "
                + ", WRI_OFF_NO       = ? "
                + ", STATUS           = ? "
                + ", EFF_DT           = ? "
                + ", REASON_TYPE      = ? "
                + ", REASON_CODE      = ? "
                + ", LOC_CDE          = ? "
                + ", REMARK           = ? "
                + ", TOT_COST         = ? "
                + ", APP_BY           = ? "
                + ", APP_DT           = ? "
                + ", LAST_UPD_DT      = ? "
                + ", LAST_UPD_USR     = ? "
                + ", LAST_UPD_VER     = ? "
                + ", ROWGUID          = ? "
                + ", REV_BY           = ? "
                + ", REV_DT           = ? "
                + ", INSURANCE_CLAIM  = ? "
                + ", GOODS_INSPECTION = ? "
                + ", COMPANY_CDE      = ? "
                + "WHERE  ENTITY_KEY       = ? ";

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
            preparedStatement.setString(3, rswrioffno);
            preparedStatement.setString(4, rsstatus);
            preparedStatement.setTimestamp(5, rseffdt);
            preparedStatement.setString(6, rsreasontype);
            preparedStatement.setString(7, rsreasoncode);
            preparedStatement.setString(8, rsloccde);
            preparedStatement.setString(9, rsremark);
            preparedStatement.setString(10, rstotcost);
            preparedStatement.setString(11, rsappby);
            preparedStatement.setTimestamp(12, rsappdt);
            preparedStatement.setTimestamp(13, rslastupddt);
            preparedStatement.setString(14, rslastupdusr);
            preparedStatement.setString(15, rslastupdver);
            preparedStatement.setString(16, rsrowguid);
            preparedStatement.setString(17, rsrevby);
            preparedStatement.setTimestamp(18, rsrevdt);
            preparedStatement.setString(19, rsinsuranceclaim);
            preparedStatement.setString(20, rsgoodsinspection);
            preparedStatement.setString(21, rscompanycde);
            preparedStatement.setString(22, rsentitykey);

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

    public static boolean Invwrioffhdrposchkexists(String entitykey, String frdatabase)
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

            String selectSQL = "SELECT INSTIT_CDE " + "FROM INV_WRI_OFF_HDR_POS "
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
