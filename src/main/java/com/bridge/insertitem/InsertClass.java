package com.bridge.insertitem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Objects;

import com.bridge.main.HikariRms;
import org.apache.log4j.Logger;

import com.bridge.main.HikariMssql;
import com.bridge.main.HikariQracleTo;

public class InsertClass {

    private static final Logger logger = Logger.getLogger(InsertClass.class);

    public static boolean Classinsert(String rsclasscode, String rsclassdesc,
                                      String rsdeptcode, String rsgppercent, String rsprodtype,
                                      Timestamp rslastupddt, String rsentitykey, String frdatabase)
            throws SQLException {

        boolean result = true;
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        String insertTableSQL;

        insertTableSQL = "INSERT INTO Class (CLASS_CODE,CLASS_DESC,DEPT_CODE,GP_PERCENT,PROD_TYPE,LAST_UPD_DT,ENTITY_KEY) "
                + "VALUES ( ?,?,?,?,?,?,? )";

        try {

            if (Objects.equals(frdatabase, "Oracle")) {
                HikariRms Rmspool = HikariRms.getInstance();
                dbConnection = Rmspool.getConnection();
            } else {
                HikariQracleTo OrcaleFromTo = HikariQracleTo.getInstance();
                dbConnection = OrcaleFromTo.getConnection();
            }

            preparedStatement = dbConnection.prepareStatement(insertTableSQL);

            preparedStatement.setString(1, rsclasscode);
            preparedStatement.setString(2, rsclassdesc);
            preparedStatement.setString(3, rsdeptcode);
            preparedStatement.setString(4, rsgppercent);
            preparedStatement.setString(5, rsprodtype);
            preparedStatement.setTimestamp(6, rslastupddt);
            preparedStatement.setString(7, rsentitykey);

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

    public static boolean Classupdate(String rsclasscode, String rsclassdesc,
                                      String rsdeptcode, String rsgppercent, String rsprodtype,
                                      Timestamp rslastupddt, String rsentitykey, String frdatabase)
            throws SQLException {

        boolean result = true;
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        String updateSQL;

        updateSQL = "UPDATE CLASS "
                +"SET CLASS_DESC   = ? "
                +", GP_PERCENT   = ? "
                +", PROD_TYPE    = ? "
                +", LAST_UPD_DT  = ? "
                +", ENTITY_KEY   = ? "
                +"WHERE CLASS_CODE = ? "
                +"and DEPT_CODE    = ? ";

        try {

            if (Objects.equals(frdatabase, "Oracle")) {
                HikariRms Rmspool = HikariRms.getInstance();
                dbConnection = Rmspool.getConnection();
            } else {
                HikariQracleTo OrcaleFromTo = HikariQracleTo.getInstance();
                dbConnection = OrcaleFromTo.getConnection();
            }

            preparedStatement = dbConnection.prepareStatement(updateSQL);

            preparedStatement.setString(1, rsclassdesc);
            preparedStatement.setString(2, rsgppercent);
            preparedStatement.setString(3, rsprodtype);
            preparedStatement.setTimestamp(4, rslastupddt);
            preparedStatement.setString(5, rsentitykey);
            preparedStatement.setString(6, rsclasscode);
            preparedStatement.setString(7, rsdeptcode);


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

    public static boolean Classchkexists(String entitykey, String frdatabase)
            throws SQLException {
        /*
         * String[] parts = entitykey.split(","); String txdate = parts[0];
		 * String loccode = parts[1]; String regno = parts[2]; String txno =
		 * parts[3]; String seqno = parts[4];
		 */

        String[] parts = entitykey.split(",");
        String classcode = parts[0];
        String deptcode = parts[1];

        boolean result = false;
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;

        try {

            if (Objects.equals(frdatabase, "Oracle")) {
                HikariRms Rmspool = HikariRms.getInstance();
                dbConnection = Rmspool.getConnection();
            } else {
                HikariQracleTo OrcaleFromTo = HikariQracleTo.getInstance();
                dbConnection = OrcaleFromTo.getConnection();
            }

            String selectSQL = "SELECT CLASS_CODE " + "FROM Class "
                    + "where CLASS_CODE ='" + classcode + "'" + "and DEPT_CODE ='" + deptcode + "'";

            // logger.info(selectSQL);

            preparedStatement = dbConnection.prepareStatement(selectSQL);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String rschktxdate = rs.getString("CLASS_CODE");

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
