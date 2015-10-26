package com.bridge.insertstockonhand;

import com.bridge.main.HikariQracleTo;
import com.bridge.main.HikariRms;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.Objects;

/**
 * Created by keyip on 10-Jun-15.
 */
public class InsertSxsreqhdrpos {

    private static final Logger logger = Logger.getLogger(InsertSxsreqhdrpos.class);

    public static boolean Sxsreqhdrposinsert(String rsreqid, String rssendstaffid, String rsrecvstaffid, String rsrecloc, String rsreccomp, String rsbucde,
                                             String rssenloc, String rssencomp, String rsstatus, String rscontact, String rsremarks, Timestamp rscreatedt, String rscreateby,
                                             String rslastupdusr, Timestamp rslastupddt, String rslastupdver, String rssendstaffname, String rscompanycde, String rsrowguid, String rsentitykey,
                                             String frdatabase) throws SQLException {

        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        String insertTableSQL;

        insertTableSQL = "INSERT INTO SXS_REQ_HDR_POS (REQ_ID,SEND_STAFF_ID,RECV_STAFF_ID,REC_LOC,REC_COMP,BU_CDE,SEN_LOC,SEN_COMP,STATUS,CONTACT,REMARKS,CREATE_DT,CREATE_BY,LAST_UPD_USR,LAST_UPD_DT,LAST_UPD_VER,SEND_STAFF_NAME,COMPANY_CDE,ROWGUID,ENTITY_KEY) "
                + "VALUES ( ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,? )";

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

            preparedStatement.setString(1, rsreqid);
            preparedStatement.setString(2, rssendstaffid);
            preparedStatement.setString(3, rsrecvstaffid);
            preparedStatement.setString(4, rsrecloc);
            preparedStatement.setString(5, rsreccomp);
            preparedStatement.setString(6, rsbucde);
            preparedStatement.setString(7, rssenloc);
            preparedStatement.setString(8, rssencomp);
            preparedStatement.setString(9, rsstatus);
            preparedStatement.setString(10, rscontact);
            preparedStatement.setString(11, rsremarks);
            preparedStatement.setTimestamp(12, rscreatedt);
            preparedStatement.setString(13, rscreateby);
            preparedStatement.setString(14, rslastupdusr);
            preparedStatement.setTimestamp(15, rslastupddt);
            preparedStatement.setString(16, rslastupdver);
            preparedStatement.setString(17, rssendstaffname);
            preparedStatement.setString(18, rscompanycde);
            preparedStatement.setString(19, rsrowguid);
            preparedStatement.setString(20, rsentitykey);


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

    public static boolean Sxsreqhdrposupdate(String rsreqid, String rssendstaffid, String rsrecvstaffid, String rsrecloc, String rsreccomp, String rsbucde,
                                             String rssenloc, String rssencomp, String rsstatus, String rscontact, String rsremarks, Timestamp rscreatedt, String rscreateby,
                                             String rslastupdusr, Timestamp rslastupddt, String rslastupdver, String rssendstaffname, String rscompanycde, String rsrowguid, String rsentitykey,
                                             String frdatabase) throws SQLException {

        boolean result = true;
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        String updateSQL;

        updateSQL = "UPDATE SXS_REQ_HDR_POS "
                + "SET REQ_ID        = ? "
                + ", SEND_STAFF_ID   = ? "
                + ", RECV_STAFF_ID   = ? "
                + ", REC_LOC         = ? "
                + ", REC_COMP        = ? "
                + ", BU_CDE          = ? "
                + ", SEN_LOC         = ? "
                + ", SEN_COMP        = ? "
                + ", STATUS          = ? "
                + ", CONTACT         = ? "
                + ", REMARKS         = ? "
                + ", CREATE_DT       = ? "
                + ", CREATE_BY       = ? "
                + ", LAST_UPD_USR    = ? "
                + ", LAST_UPD_DT     = ? "
                + ", LAST_UPD_VER    = ? "
                + ", SEND_STAFF_NAME = ? "
                + ", COMPANY_CDE     = ? "
                + ", ROWGUID         = ? "
                + "WHERE  ENTITY_KEY      = ? ";

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

            preparedStatement.setString(1, rsreqid);
            preparedStatement.setString(2, rssendstaffid);
            preparedStatement.setString(3, rsrecvstaffid);
            preparedStatement.setString(4, rsrecloc);
            preparedStatement.setString(5, rsreccomp);
            preparedStatement.setString(6, rsbucde);
            preparedStatement.setString(7, rssenloc);
            preparedStatement.setString(8, rssencomp);
            preparedStatement.setString(9, rsstatus);
            preparedStatement.setString(10, rscontact);
            preparedStatement.setString(11, rsremarks);
            preparedStatement.setTimestamp(12, rscreatedt);
            preparedStatement.setString(13, rscreateby);
            preparedStatement.setString(14, rslastupdusr);
            preparedStatement.setTimestamp(15, rslastupddt);
            preparedStatement.setString(16, rslastupdver);
            preparedStatement.setString(17, rssendstaffname);
            preparedStatement.setString(18, rscompanycde);
            preparedStatement.setString(19, rsrowguid);
            preparedStatement.setString(20, rsentitykey);


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

    public static boolean Sxsreqhdrposchkexists(String entitykey, String frdatabase)
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

            String selectSQL = "SELECT REQ_ID " + "FROM SXS_REQ_HDR_POS "
                    + "where ENTITY_KEY ='" + entitykey + "'";

            // logger.info(selectSQL);

            preparedStatement = dbConnection.prepareStatement(selectSQL);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String rschktxdate = rs.getString("REQ_ID");

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
