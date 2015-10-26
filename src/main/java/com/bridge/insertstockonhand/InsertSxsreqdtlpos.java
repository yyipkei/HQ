package com.bridge.insertstockonhand;

import com.bridge.main.HikariQracleTo;
import com.bridge.main.HikariRms;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.Objects;

/**
 * Created by keyip on 10-Jun-15.
 */
public class InsertSxsreqdtlpos {
    private static final Logger logger = Logger.getLogger(InsertSxsreqdtlpos.class);

    public static boolean Sxsreqdtlposinsert(String rsreqid, String rsreqseqno, String rsboxno, String rssku, String rsstyle, String rsdeptcde, String rsclasscde, String rscolorcde,
                                             String rssizesetcde, String rssizecde, String rsseasoncde, String rsbrandcde, String rsretail, String rsstatus, Timestamp rsscandt, String rsscanby,
                                             Timestamp rsboxeddt, String rsboxedby, Timestamp rsintrandt, String rsintranby, Timestamp rscompdt, String rscompby, String rssenqty, String rsrecqty,
                                             String rsremarks, Timestamp rscreatedt, String rscreateby, String rslastupdusr, Timestamp rslastupddt, String rslastupdver, String rssremarks,
                                             String rsvipno, String rsviptelno, String rsvipname, String rsrecvstaffname, String rssendstaffname, String rscompanycde, String rsrowguid,
                                             String rsentitykey, String frdatabase) throws SQLException {

        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        String insertTableSQL;

        insertTableSQL = "INSERT INTO SXS_REQ_DTL_POS (REQ_ID,REQ_SEQ_NO,BOXNO,SKU,STYLE,DEPT_CDE,CLASS_CDE,COLOR_CDE,SIZE_SET_CDE,SIZE_CDE,SEASON_CDE," +
                "BRAND_CDE,RETAIL,STATUS,SCAN_DT,SCAN_BY,BOXED_DT,BOXED_BY,INTRAN_DT,INTRAN_BY,COMP_DT,COMP_BY,SEN_QTY,REC_QTY,REMARKS,CREATE_DT,CREATE_BY," +
                "LAST_UPD_USR,LAST_UPD_DT,LAST_UPD_VER,SREMARKS,VIP_NO,VIP_TELNO,VIP_NAME,RECV_STAFF_NAME,SEND_STAFF_NAME,COMPANY_CDE,ROWGUID,ENTITY_KEY) "
                + "VALUES ( ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,? )";

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
            preparedStatement.setString(2, rsreqseqno);
            preparedStatement.setString(3, rsboxno);
            preparedStatement.setString(4, rssku);
            preparedStatement.setString(5, rsstyle);
            preparedStatement.setString(6, rsdeptcde);
            preparedStatement.setString(7, rsclasscde);
            preparedStatement.setString(8, rscolorcde);
            preparedStatement.setString(9, rssizesetcde);
            preparedStatement.setString(10, rssizecde);
            preparedStatement.setString(11, rsseasoncde);
            preparedStatement.setString(12, rsbrandcde);
            preparedStatement.setString(13, rsretail);
            preparedStatement.setString(14, rsstatus);
            preparedStatement.setTimestamp(15, rsscandt);
            preparedStatement.setString(16, rsscanby);
            preparedStatement.setTimestamp(17, rsboxeddt);
            preparedStatement.setString(18, rsboxedby);
            preparedStatement.setTimestamp(19, rsintrandt);
            preparedStatement.setString(20, rsintranby);
            preparedStatement.setTimestamp(21, rscompdt);
            preparedStatement.setString(22, rscompby);
            preparedStatement.setString(23, rssenqty);
            preparedStatement.setString(24, rsrecqty);
            preparedStatement.setString(25, rsremarks);
            preparedStatement.setTimestamp(26, rscreatedt);
            preparedStatement.setString(27, rscreateby);
            preparedStatement.setString(28, rslastupdusr);
            preparedStatement.setTimestamp(29, rslastupddt);
            preparedStatement.setString(30, rslastupdver);
            preparedStatement.setString(31, rssremarks);
            preparedStatement.setString(32, rsvipno);
            preparedStatement.setString(33, rsviptelno);
            preparedStatement.setString(34, rsvipname);
            preparedStatement.setString(35, rsrecvstaffname);
            preparedStatement.setString(36, rssendstaffname);
            preparedStatement.setString(37, rscompanycde);
            preparedStatement.setString(38, rsrowguid);
            preparedStatement.setString(39, rsentitykey);


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

    public static boolean Sxsreqdtlposupdate(String rsreqid, String rsreqseqno, String rsboxno, String rssku, String rsstyle, String rsdeptcde, String rsclasscde, String rscolorcde,
                                             String rssizesetcde, String rssizecde, String rsseasoncde, String rsbrandcde, String rsretail, String rsstatus, Timestamp rsscandt, String rsscanby,
                                             Timestamp rsboxeddt, String rsboxedby, Timestamp rsintrandt, String rsintranby, Timestamp rscompdt, String rscompby, String rssenqty, String rsrecqty,
                                             String rsremarks, Timestamp rscreatedt, String rscreateby, String rslastupdusr, Timestamp rslastupddt, String rslastupdver, String rssremarks,
                                             String rsvipno, String rsviptelno, String rsvipname, String rsrecvstaffname, String rssendstaffname, String rscompanycde, String rsrowguid,
                                             String rsentitykey, String frdatabase) throws SQLException {
        boolean result = true;
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        String updateSQL;

        updateSQL = "UPDATE SXS_REQ_DTL_POS "
                + "SET REQ_ID        = ? "
                + ", REQ_SEQ_NO      = ? "
                + ", BOXNO           = ? "
                + ", SKU             = ? "
                + ", STYLE           = ? "
                + ", DEPT_CDE        = ? "
                + ", CLASS_CDE       = ? "
                + ", COLOR_CDE       = ? "
                + ", SIZE_SET_CDE    = ? "
                + ", SIZE_CDE        = ? "
                + ", SEASON_CDE      = ? "
                + ", BRAND_CDE       = ? "
                + ", RETAIL          = ? "
                + ", STATUS          = ? "
                + ", SCAN_DT         = ? "
                + ", SCAN_BY         = ? "
                + ", BOXED_DT        = ? "
                + ", BOXED_BY        = ? "
                + ", INTRAN_DT       = ? "
                + ", INTRAN_BY       = ? "
                + ", COMP_DT         = ? "
                + ", COMP_BY         = ? "
                + ", SEN_QTY         = ? "
                + ", REC_QTY         = ? "
                + ", REMARKS         = ? "
                + ", CREATE_DT       = ? "
                + ", CREATE_BY       = ? "
                + ", LAST_UPD_USR    = ? "
                + ", LAST_UPD_DT     = ? "
                + ", LAST_UPD_VER    = ? "
                + ", SREMARKS        = ? "
                + ", VIP_NO          = ? "
                + ", VIP_TELNO       = ? "
                + ", VIP_NAME        = ? "
                + ", RECV_STAFF_NAME = ? "
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
            preparedStatement.setString(2, rsreqseqno);
            preparedStatement.setString(3, rsboxno);
            preparedStatement.setString(4, rssku);
            preparedStatement.setString(5, rsstyle);
            preparedStatement.setString(6, rsdeptcde);
            preparedStatement.setString(7, rsclasscde);
            preparedStatement.setString(8, rscolorcde);
            preparedStatement.setString(9, rssizesetcde);
            preparedStatement.setString(10, rssizecde);
            preparedStatement.setString(11, rsseasoncde);
            preparedStatement.setString(12, rsbrandcde);
            preparedStatement.setString(13, rsretail);
            preparedStatement.setString(14, rsstatus);
            preparedStatement.setTimestamp(15, rsscandt);
            preparedStatement.setString(16, rsscanby);
            preparedStatement.setTimestamp(17, rsboxeddt);
            preparedStatement.setString(18, rsboxedby);
            preparedStatement.setTimestamp(19, rsintrandt);
            preparedStatement.setString(20, rsintranby);
            preparedStatement.setTimestamp(21, rscompdt);
            preparedStatement.setString(22, rscompby);
            preparedStatement.setString(23, rssenqty);
            preparedStatement.setString(24, rsrecqty);
            preparedStatement.setString(25, rsremarks);
            preparedStatement.setTimestamp(26, rscreatedt);
            preparedStatement.setString(27, rscreateby);
            preparedStatement.setString(28, rslastupdusr);
            preparedStatement.setTimestamp(29, rslastupddt);
            preparedStatement.setString(30, rslastupdver);
            preparedStatement.setString(31, rssremarks);
            preparedStatement.setString(32, rsvipno);
            preparedStatement.setString(33, rsviptelno);
            preparedStatement.setString(34, rsvipname);
            preparedStatement.setString(35, rsrecvstaffname);
            preparedStatement.setString(36, rssendstaffname);
            preparedStatement.setString(37, rscompanycde);
            preparedStatement.setString(38, rsrowguid);
            preparedStatement.setString(39, rsentitykey);

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

    public static boolean Sxsreqdtlposchkexists(String entitykey, String frdatabase)
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

            String selectSQL = "SELECT REQ_ID " + "FROM SXS_REQ_DTL_POS "
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
