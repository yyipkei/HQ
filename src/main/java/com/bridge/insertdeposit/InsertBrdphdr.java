package com.bridge.insertdeposit;

import com.bridge.main.HikariMerge;
import com.bridge.main.HikariQracleTo;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.Objects;

public class InsertBrdphdr {

    private static final Logger logger = Logger.getLogger(InsertBrdphdr.class);

    public static boolean Brdphdrinsert(String rsbrdpno, String rsbridalregno, String rsissueloccode, String rsvipref,
                                        String rsbridaldesc, String rstxdate, String rsloccode, String rsregno, String rstxno,
                                        String rsamount, String rsbalance, String rsstatus, String rsmodifiedby, String rsmodifieddate,
                                        String rsmodifiedtime, String rsbridalguestno, String rsrowguid, Timestamp rslastupddt, String frdatabase) throws SQLException {

        boolean result = true;
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        String insertTableSQL;

        try {

            if (Objects.equals(frdatabase, "Oracle")) {
                // dbConnection = Merge.getDBConnection();

                HikariMerge Mergepool = HikariMerge.getInstance();
                dbConnection = Mergepool.getConnection();

                insertTableSQL = "INSERT INTO BRDP_HDR"
                        + "(BRDP_NO,BRIDAL_REG_NO,ISSUE_LOC_CODE,VIP_REF,BRIDAL_DESC,TX_DATE,LOC_CODE,REG_NO,TX_NO,AMOUNT,BALANCE," +
                        "STATUS,MODIFIED_BY,MODIFIED_DATE,MODIFIED_TIME,BRIDAL_GUEST_NO,ROWGUID,LAST_UPD_DT) "
                        + "VALUES" + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
                        + "newid()" + ",?)";

                preparedStatement = dbConnection
                        .prepareStatement(insertTableSQL);

                preparedStatement.setString(1, rsbrdpno);
                preparedStatement.setString(2, rsbridalregno);
                preparedStatement.setString(3, rsissueloccode);
                preparedStatement.setString(4, rsvipref);
                preparedStatement.setString(5, rsbridaldesc);
                preparedStatement.setString(6, rstxdate);
                preparedStatement.setString(7, rsloccode);
                preparedStatement.setString(8, rsregno);
                preparedStatement.setString(9, rstxno);
                preparedStatement.setString(10, rsamount);
                preparedStatement.setString(11, rsbalance);
                preparedStatement.setString(12, rsstatus);
                preparedStatement.setString(13, rsmodifiedby);
                preparedStatement.setString(14, rsmodifieddate);
                preparedStatement.setString(15, rsmodifiedtime);
                preparedStatement.setString(16, rsbridalguestno);
                preparedStatement.setTimestamp(17, rslastupddt);


            } else {
                // dbConnection = OracleTo.getDBConnection();

                HikariQracleTo OrcaleFromTo = HikariQracleTo.getInstance();
                dbConnection = OrcaleFromTo.getConnection();

                insertTableSQL = "INSERT INTO BRDP_HDR"
                        + "(BRDP_NO,BRIDAL_REG_NO,ISSUE_LOC_CODE,VIP_REF,BRIDAL_DESC,TX_DATE,LOC_CODE,REG_NO,TX_NO,AMOUNT,BALANCE," +
                        "STATUS,MODIFIED_BY,MODIFIED_DATE,MODIFIED_TIME,BRIDAL_GUEST_NO,ROWGUID,LAST_UPD_DT) "
                        + "VALUES" + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

                preparedStatement = dbConnection
                        .prepareStatement(insertTableSQL);

                preparedStatement.setString(1, rsbrdpno);
                preparedStatement.setString(2, rsbridalregno);
                preparedStatement.setString(3, rsissueloccode);
                preparedStatement.setString(4, rsvipref);
                preparedStatement.setString(5, rsbridaldesc);
                preparedStatement.setString(6, rstxdate);
                preparedStatement.setString(7, rsloccode);
                preparedStatement.setString(8, rsregno);
                preparedStatement.setString(9, rstxno);
                preparedStatement.setString(10, rsamount);
                preparedStatement.setString(11, rsbalance);
                preparedStatement.setString(12, rsstatus);
                preparedStatement.setString(13, rsmodifiedby);
                preparedStatement.setString(14, rsmodifieddate);
                preparedStatement.setString(15, rsmodifiedtime);
                preparedStatement.setString(16, rsbridalguestno);
                preparedStatement.setString(17, rsrowguid);
                preparedStatement.setTimestamp(18, rslastupddt);
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

    public static boolean Brdphdrupdate(String rsbrdpno, String rsbridalregno, String rsissueloccode, String rsvipref,
                                        String rsbridaldesc, String rstxdate, String rsloccode, String rsregno, String rstxno,
                                        String rsamount, String rsbalance, String rsstatus, String rsmodifiedby, String rsmodifieddate,
                                        String rsmodifiedtime, String rsbridalguestno, String rsrowguid, Timestamp rslastupddt, String frdatabase) throws SQLException {

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

            String updateSQL = "UPDATE BRDP_HDR "
                    + "SET  VIP_REF         = ? "
                    + ", BRIDAL_DESC     = ? "
                    + ", TX_DATE         = ? "
                    + ", LOC_CODE        = ? "
                    + ", REG_NO          = ? "
                    + ", TX_NO           = ? "
                    + ", AMOUNT          = ? "
                    + ", BALANCE         = ? "
                    + ", STATUS          = ? "
                    + ", MODIFIED_BY     = ? "
                    + ", MODIFIED_DATE   = ? "
                    + ", MODIFIED_TIME   = ? "
                    + ", BRIDAL_GUEST_NO = ? "
                    + ", LAST_UPD_DT     = ? "
                    + "WHERE BRDP_NO       = ? "
                    + "AND BRIDAL_REG_NO   = ? "
                    + "AND ISSUE_LOC_CODE  = ? ";

            preparedStatement = dbConnection.prepareStatement(updateSQL);

            preparedStatement.setString(1, rsvipref);
            preparedStatement.setString(2, rsbridaldesc);
            preparedStatement.setString(3, rstxdate);
            preparedStatement.setString(4, rsloccode);
            preparedStatement.setString(5, rsregno);
            preparedStatement.setString(6, rstxno);
            preparedStatement.setString(7, rsamount);
            preparedStatement.setString(8, rsbalance);
            preparedStatement.setString(9, rsstatus);
            preparedStatement.setString(10, rsmodifiedby);
            preparedStatement.setString(11, rsmodifieddate);
            preparedStatement.setString(12, rsmodifiedtime);
            preparedStatement.setString(13, rsbridalguestno);
            preparedStatement.setTimestamp(14, rslastupddt);
            preparedStatement.setString(15, rsbrdpno);
            preparedStatement.setString(16, rsbridalregno);
            preparedStatement.setString(17, rsissueloccode);


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

    public static boolean Brdphdrchkexists(String entitykey, String frdatabase)
            throws SQLException {

        String[] parts = entitykey.split(",");
        String brdpno = parts[0];
        String bridalregno = parts[1];
        String issueloccode = parts[2];

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

            String selectSQL = "SELECT BRDP_NO "
                    + "FROM BRDP_HDR "
                    + "where BRDP_NO ='"
                    + brdpno
                    + "'"
                    + "and BRIDAL_REG_NO ='"
                    + bridalregno
                    + "'"
                    + "and ISSUE_LOC_CODE='"
                    + issueloccode
                    + "'";

            //logger.info(selectSQL);

            preparedStatement = dbConnection.prepareStatement(selectSQL);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String rschktxdate = rs.getString("BRDP_NO");

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
