package com.bridge.insertgoasetting;

import com.bridge.main.HikariMssql;
import com.bridge.main.HikariQracleTo;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.Objects;

/**
 * Created by keyip on 01-Jun-15.
 */
public class InsertGoastafflimithdr {

    private static final Logger logger = Logger.getLogger(InsertGoastafflimithdr.class);

    public static boolean Goastafflimithdrinsert(String rscompanycde, String rsstaffid, String rsgroupcode, String rsstatus, String rsactive, String rsstaffname,
                                                 String rsdivdesc, String rsdeptdesc, String rstitle, String rsgoalimit, String rsaccumgoa, String rsaccumps, String rsborrowday,
                                                 Timestamp rseffdtfr, Timestamp rseffdtto, String rsprepareby, Timestamp rscfmdt, String rscfmby, Timestamp rsappdt, String rsappby,
                                                 Timestamp rslastupddt, String rslastupdusr, String rslastupdver, String rsentitykey,
                                                 String frdatabase) throws SQLException {

        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        String insertTableSQL;

        insertTableSQL = "INSERT INTO goa_staff_limit_hdr (COMPANY_CDE,STAFF_ID,GROUP_CODE,STATUS,ACTIVE,STAFF_NAME,DIV_DESC,DEPT_DESC,TITLE," +
                "GOA_LIMIT,ACCUM_GOA,ACCUM_PS,BORROW_DAY,EFF_DT_FR,EFF_DT_TO,PREPARE_BY,CFM_DT,CFM_BY,APP_DT,APP_BY,LAST_UPD_DT,LAST_UPD_USR," +
                "LAST_UPD_VER,ENTITY_KEY) "
                + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,? )";

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

            preparedStatement.setString(1, rscompanycde);
            preparedStatement.setString(2, rsstaffid);
            preparedStatement.setString(3, rsgroupcode);
            preparedStatement.setString(4, rsstatus);
            preparedStatement.setString(5, rsactive);
            preparedStatement.setString(6, rsstaffname);
            preparedStatement.setString(7, rsdivdesc);
            preparedStatement.setString(8, rsdeptdesc);
            preparedStatement.setString(9, rstitle);
            preparedStatement.setString(10, rsgoalimit);
            preparedStatement.setString(11, rsaccumgoa);
            preparedStatement.setString(12, rsaccumps);
            preparedStatement.setString(13, rsborrowday);
            preparedStatement.setTimestamp(14, rseffdtfr);
            preparedStatement.setTimestamp(15, rseffdtto);
            preparedStatement.setString(16, rsprepareby);
            preparedStatement.setTimestamp(17, rscfmdt);
            preparedStatement.setString(18, rscfmby);
            preparedStatement.setTimestamp(19, rsappdt);
            preparedStatement.setString(20, rsappby);
            preparedStatement.setTimestamp(21, rslastupddt);
            preparedStatement.setString(22, rslastupdusr);
            preparedStatement.setString(23, rslastupdver);
            preparedStatement.setString(24, rsentitykey);

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

    public static boolean Goastafflimithdrupdate(String rscompanycde, String rsstaffid, String rsgroupcode, String rsstatus, String rsactive, String rsstaffname,
                                                 String rsdivdesc, String rsdeptdesc, String rstitle, String rsgoalimit, String rsaccumgoa, String rsaccumps, String rsborrowday,
                                                 Timestamp rseffdtfr, Timestamp rseffdtto, String rsprepareby, Timestamp rscfmdt, String rscfmby, Timestamp rsappdt, String rsappby,
                                                 Timestamp rslastupddt, String rslastupdusr, String rslastupdver, String rsentitykey,
                                                 String frdatabase) throws SQLException {

        boolean result = true;
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        String updateSQL;

        updateSQL = "UPDATE GOA_STAFF_LIMIT_HDR "
                + "SET COMPANY_CDE = ? "
                + ", STAFF_ID      = ? "
                + ", GROUP_CODE    = ? "
                + ", STATUS        = ? "
                + ", ACTIVE        = ? "
                + ", STAFF_NAME    = ? "
                + ", DIV_DESC      = ? "
                + ", DEPT_DESC     = ? "
                + ", TITLE         = ? "
                + ", GOA_LIMIT     = ? "
                + ", ACCUM_GOA     = ? "
                + ", ACCUM_PS      = ? "
                + ", BORROW_DAY    = ? "
                + ", EFF_DT_FR     = ? "
                + ", EFF_DT_TO     = ? "
                + ", PREPARE_BY    = ? "
                + ", CFM_DT        = ? "
                + ", CFM_BY        = ? "
                + ", APP_DT        = ? "
                + ", APP_BY        = ? "
                + ", LAST_UPD_DT   = ? "
                + ", LAST_UPD_USR  = ? "
                + ", LAST_UPD_VER  = ? "
                + "WHERE  ENTITY_KEY    = ? ";

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

            preparedStatement.setString(1, rscompanycde);
            preparedStatement.setString(2, rsstaffid);
            preparedStatement.setString(3, rsgroupcode);
            preparedStatement.setString(4, rsstatus);
            preparedStatement.setString(5, rsactive);
            preparedStatement.setString(6, rsstaffname);
            preparedStatement.setString(7, rsdivdesc);
            preparedStatement.setString(8, rsdeptdesc);
            preparedStatement.setString(9, rstitle);
            preparedStatement.setString(10, rsgoalimit);
            preparedStatement.setString(11, rsaccumgoa);
            preparedStatement.setString(12, rsaccumps);
            preparedStatement.setString(13, rsborrowday);
            preparedStatement.setTimestamp(14, rseffdtfr);
            preparedStatement.setTimestamp(15, rseffdtto);
            preparedStatement.setString(16, rsprepareby);
            preparedStatement.setTimestamp(17, rscfmdt);
            preparedStatement.setString(18, rscfmby);
            preparedStatement.setTimestamp(19, rsappdt);
            preparedStatement.setString(20, rsappby);
            preparedStatement.setTimestamp(21, rslastupddt);
            preparedStatement.setString(22, rslastupdusr);
            preparedStatement.setString(23, rslastupdver);
            preparedStatement.setString(24, rsentitykey);

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

    public static boolean Goastafflimithdrchkexists(String entitykey, String frdatabase)
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

            String selectSQL = "SELECT COMPANY_CDE " + "FROM GOA_STAFF_LIMIT_HDR "
                    + "where ENTITY_KEY ='" + entitykey + "'";

            // logger.info(selectSQL);

            preparedStatement = dbConnection.prepareStatement(selectSQL);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String rschktxdate = rs.getString("COMPANY_CDE");

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
