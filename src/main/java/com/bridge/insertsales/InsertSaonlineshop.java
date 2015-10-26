package com.bridge.insertsales;

import com.bridge.main.HikariMssql;
import com.bridge.main.HikariQracleTo;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.Objects;

public class InsertSaonlineshop {

    private static final Logger logger = Logger
            .getLogger(InsertSaonlineshop.class);

    public static boolean Saonlineshopinsert(String rstxdate, String rsloccode,
                                             String rsregno, String rstxno, String rstxtype, String rsvoid,
                                             String rsonlineshopno, String rspricelistid,
                                             Timestamp rssubmissiontime, String rsretrospectivermaid,
                                             Timestamp rslastupddt, String frdatabase) throws SQLException {

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

            String insertTableSQL = "INSERT INTO Saonlineshop "
                    + "(TX_DATE,LOC_CODE,REG_NO,TX_NO,TX_TYPE,VOID,ONLINESHOP_NO,PRICE_LIST_ID,SUBMISSION_TIME,RETROSPECTIVE_RMA_ID,LAST_UPD_DT) "
                    + "VALUES" + "(?,?,?,?,?,?,?,?,?,?,?)";

            preparedStatement = dbConnection.prepareStatement(insertTableSQL);

            preparedStatement.setString(1, rstxdate);
            preparedStatement.setString(2, rsloccode);
            preparedStatement.setString(3, rsregno);
            preparedStatement.setString(4, rstxno);
            preparedStatement.setString(5, rstxtype);
            preparedStatement.setString(6, rsvoid);
            preparedStatement.setString(7, rsonlineshopno);
            preparedStatement.setString(8, rspricelistid);
            preparedStatement.setTimestamp(9, rssubmissiontime);
            preparedStatement.setString(10, rsretrospectivermaid);
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

    public static boolean Saonlineshopupdate(String rstxdate, String rsloccode,
                                             String rsregno, String rstxno, String rstxtype, String rsvoid,
                                             String rsonlineshopno, String rspricelistid,
                                             Timestamp rssubmissiontime, String rsretrospectivermaid,
                                             Timestamp rslastupddt, String frdatabase) throws SQLException {

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

            String updateSQL = "UPDATE SAONLINESHOP "
                    + "SET  VOID                 = ? "
                    + ", ONLINESHOP_NO        = ? "
                    + ", PRICE_LIST_ID        = ? "
                    + ", SUBMISSION_TIME      = ? "
                    + ", RETROSPECTIVE_RMA_ID = ? "
                    + ", LAST_UPD_DT          = ? "
                    + "WHERE TX_DATE            = ? "
                    + "AND LOC_CODE             = ? "
                    + "AND REG_NO               = ? "
                    + "AND TX_NO                = ? "
                    + "AND TX_TYPE              = ? ";

            preparedStatement = dbConnection.prepareStatement(updateSQL);

            preparedStatement.setString(1, rstxtype);
            preparedStatement.setString(2, rsvoid);
            preparedStatement.setString(3, rsonlineshopno);
            preparedStatement.setString(4, rspricelistid);
            preparedStatement.setTimestamp(5, rssubmissiontime);
            preparedStatement.setString(6, rsretrospectivermaid);
            preparedStatement.setTimestamp(7, rslastupddt);
            preparedStatement.setString(8, rstxdate);
            preparedStatement.setString(9, rsloccode);
            preparedStatement.setString(10, rsregno);
            preparedStatement.setString(11, rstxno);

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

    public static boolean Saonlineshopchkexists(String entitykey,
                                                String frdatabase) throws SQLException {

        String[] parts = entitykey.split(",");
        String txdate = parts[0];
        String loccode = parts[1];
        String regno = parts[2];
        String txno = parts[3];

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

            String selectSQL = "SELECT TX_DATE " + "FROM Saonlineshop "
                    + "where tx_date ='" + txdate + "'" + "and LOC_CODE ='"
                    + loccode + "'" + "and reg_no='" + regno + "'"
                    + "and tx_no ='" + txno + "'";

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
