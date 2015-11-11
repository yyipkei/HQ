package com.bridge.insertstockres;

import com.bridge.main.HikariMerge;
import com.bridge.main.HikariQracleTo;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.Objects;

public class InsertStockres {

    private static final Logger logger = Logger.getLogger(InsertStockres.class);

    public static boolean Stockresinsert(String rsloccode, String rsresno,
                                         String rsresby, String rsresat, String rsresdate, String rsrestime,
                                         String rsvipno, String rscustname, String rstelno, String rsremark,
                                         String rsautorelease, String rsoverrideby, String rsexpiredate,
                                         String rsrowguid, Timestamp rslastupddt, String frdatabase)
            throws SQLException {

        boolean result = true;
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        String insertTableSQL;

        try {

            if (Objects.equals(frdatabase, "Oracle")) {
                // dbConnection = Mssql.getDBConnection();
                /*
                HikariMssql Mssqlpool = HikariMssql.getInstance();
				dbConnection = Mssqlpool.getConnection();
				*/
                HikariMerge Mergepool = HikariMerge.getInstance();
                dbConnection = Mergepool.getConnection();

                insertTableSQL = "INSERT INTO STOCK_RES"
                        + "(LOC_CODE,RES_NO,RES_BY,RES_AT,RES_DATE,RES_TIME,VIP_NO,CUST_NAME,TEL_NO,REMARK,AUTO_RELEASE,OVERRIDE_BY,EXPIRE_DATE,ROWGUID,LAST_UPD_DT) "
                        + "VALUES" + "(?,?,?,?,?,?,?,?,?,?,?,?,?," + "newid()"
                        + ",?)";

                preparedStatement = dbConnection
                        .prepareStatement(insertTableSQL);

                preparedStatement.setString(1, rsloccode);
                preparedStatement.setString(2, rsresno);
                preparedStatement.setString(3, rsresby);
                preparedStatement.setString(4, rsresat);
                preparedStatement.setString(5, rsresdate);
                preparedStatement.setString(6, rsrestime);
                preparedStatement.setString(7, rsvipno);
                preparedStatement.setString(8, rscustname);
                preparedStatement.setString(9, rstelno);
                preparedStatement.setString(10, rsremark);
                preparedStatement.setString(11, rsautorelease);
                preparedStatement.setString(12, rsoverrideby);
                preparedStatement.setString(13, rsexpiredate);
                preparedStatement.setTimestamp(14, rslastupddt);

            } else {
                // dbConnection = OracleTo.getDBConnection();

                HikariQracleTo OrcaleFromTo = HikariQracleTo.getInstance();
                dbConnection = OrcaleFromTo.getConnection();

                insertTableSQL = "INSERT INTO STOCK_RES"
                        + "(LOC_CODE,RES_NO,RES_BY,RES_AT,RES_DATE,RES_TIME,VIP_NO,CUST_NAME,TEL_NO,REMARK,AUTO_RELEASE,OVERRIDE_BY,EXPIRE_DATE,ROWGUID,LAST_UPD_DT) "
                        + "VALUES" + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

                preparedStatement = dbConnection
                        .prepareStatement(insertTableSQL);

                preparedStatement.setString(1, rsloccode);
                preparedStatement.setString(2, rsresno);
                preparedStatement.setString(3, rsresby);
                preparedStatement.setString(4, rsresat);
                preparedStatement.setString(5, rsresdate);
                preparedStatement.setString(6, rsrestime);
                preparedStatement.setString(7, rsvipno);
                preparedStatement.setString(8, rscustname);
                preparedStatement.setString(9, rstelno);
                preparedStatement.setString(10, rsremark);
                preparedStatement.setString(11, rsautorelease);
                preparedStatement.setString(12, rsoverrideby);
                preparedStatement.setString(13, rsexpiredate);
                preparedStatement.setString(14, rsrowguid);
                preparedStatement.setTimestamp(15, rslastupddt);

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

    public static boolean Stockresupdate(String rsloccode, String rsresno,
                                         String rsresby, String rsresat, String rsresdate, String rsrestime,
                                         String rsvipno, String rscustname, String rstelno, String rsremark,
                                         String rsautorelease, String rsoverrideby, String rsexpiredate,
                                         String rsrowguid, Timestamp rslastupddt, String frdatabase)
            throws SQLException {

        boolean result = true;
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;

        try {

            if (Objects.equals(frdatabase, "Oracle")) {
                // dbConnection = Mssql.getDBConnection();
                /*
				HikariMssql Mssqlpool = HikariMssql.getInstance();
				dbConnection = Mssqlpool.getConnection();
				*/
                HikariMerge Mergepool = HikariMerge.getInstance();
                dbConnection = Mergepool.getConnection();

            } else {
                // dbConnection = OracleTo.getDBConnection();

                HikariQracleTo OrcaleFromTo = HikariQracleTo.getInstance();
                dbConnection = OrcaleFromTo.getConnection();
            }

            String updateSQL = "UPDATE STOCK_RES " + "SET  RES_BY       = ? "
                    + ", RES_AT       = ? " + ", RES_DATE     = ? "
                    + ", RES_TIME     = ? " + ", VIP_NO       = ? "
                    + ", CUST_NAME    = ? " + ", TEL_NO       = ? "
                    + ", REMARK       = ? " + ", AUTO_RELEASE = ? "
                    + ", OVERRIDE_BY  = ? " + ", EXPIRE_DATE  = ? "
                    + ", LAST_UPD_DT  = ? " + "WHERE LOC_CODE   = ? "
                    + "AND RES_NO       = ? ";

            preparedStatement = dbConnection.prepareStatement(updateSQL);

            preparedStatement.setString(1, rsresby);
            preparedStatement.setString(2, rsresat);
            preparedStatement.setString(3, rsresdate);
            preparedStatement.setString(4, rsrestime);
            preparedStatement.setString(5, rsvipno);
            preparedStatement.setString(6, rscustname);
            preparedStatement.setString(7, rstelno);
            preparedStatement.setString(8, rsremark);
            preparedStatement.setString(9, rsautorelease);
            preparedStatement.setString(10, rsoverrideby);
            preparedStatement.setString(11, rsexpiredate);
            preparedStatement.setTimestamp(12, rslastupddt);
            preparedStatement.setString(13, rsloccode);
            preparedStatement.setString(14, rsresno);

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

    public static boolean Stockreschkexists(String entitykey, String frdatabase)
            throws SQLException {

        String[] parts = entitykey.split(",");
        String loccode = parts[0];
        String resno = parts[1];

        boolean result = false;
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;

        try {

            if (Objects.equals(frdatabase, "Oracle")) {
                // dbConnection = Mssql.getDBConnection();

                /*
				HikariMssql Mssqlpool = HikariMssql.getInstance();
				dbConnection = Mssqlpool.getConnection();
				*/
                HikariMerge Mergepool = HikariMerge.getInstance();
                dbConnection = Mergepool.getConnection();

            } else {
                // dbConnection = OracleTo.getDBConnection();

                HikariQracleTo OrcaleFromTo = HikariQracleTo.getInstance();
                dbConnection = OrcaleFromTo.getConnection();
            }

            String selectSQL = "SELECT LOC_CODE " + "FROM STOCK_RES "
                    + "where LOC_CODE ='" + loccode + "'" + " and RES_NO ='"
                    + resno + "'";

            // logger.info(selectSQL);

            preparedStatement = dbConnection.prepareStatement(selectSQL);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String rschktxdate = rs.getString("LOC_CODE");

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
