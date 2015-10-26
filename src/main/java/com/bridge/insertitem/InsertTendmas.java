package com.bridge.insertitem;

import com.bridge.main.HikariMssql;
import com.bridge.main.HikariQracleTo;
import com.bridge.main.HikariRms;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.Objects;

/**
 * Created by keyip on 17-Jun-15.
 */
public class InsertTendmas {

    private static final Logger logger = Logger.getLogger(InsertTendmas.class);

    public static boolean Tendmasinsert(String rstender, String rstendername, String rscardprefix, String rstendergroup,
                                        String rsifxref, String rsismanual, String rsbankhost, String rscommrate, String rsmask,
                                        String rsmaskenabled, String rsdbmask, String rsdbmaskenabled, String rsexpirymask,
                                        String rsexpirymaskenabled, String rsmanualentry, Timestamp rslastupddt, String rsentitykey,
                                        String frdatabase) throws SQLException {

        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        String insertTableSQL;

        insertTableSQL = "INSERT INTO tendmas (TENDER,TENDER_NAME,CARD_PREFIX,TENDER_GROUP,IFX_REF,IS_MANUAL,BANK_HOST,COMM_RATE," +
                "MASK,MASK_ENABLED,DBMASK,DBMASK_ENABLED,EXPIRY_MASK,EXPIRY_MASK_ENABLED,MANUAL_ENTRY,LAST_UPD_DT,ENTITY_KEY) "
                + "VALUES ( ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

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

            preparedStatement.setString(1, rstender);
            preparedStatement.setString(2, rstendername);
            preparedStatement.setString(3, rscardprefix);
            preparedStatement.setString(4, rstendergroup);
            preparedStatement.setString(5, rsifxref);
            preparedStatement.setString(6, rsismanual);
            preparedStatement.setString(7, rsbankhost);
            preparedStatement.setString(8, rscommrate);
            preparedStatement.setString(9, rsmask);
            preparedStatement.setString(10, rsmaskenabled);
            preparedStatement.setString(11, rsdbmask);
            preparedStatement.setString(12, rsdbmaskenabled);
            preparedStatement.setString(13, rsexpirymask);
            preparedStatement.setString(14, rsexpirymaskenabled);
            preparedStatement.setString(15, rsmanualentry);
            preparedStatement.setTimestamp(16, rslastupddt);
            preparedStatement.setString(17, rsentitykey);

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

    public static boolean Tendmasupdate(String rstender, String rstendername, String rscardprefix, String rstendergroup,
                                        String rsifxref, String rsismanual, String rsbankhost, String rscommrate, String rsmask,
                                        String rsmaskenabled, String rsdbmask, String rsdbmaskenabled, String rsexpirymask,
                                        String rsexpirymaskenabled, String rsmanualentry, Timestamp rslastupddt, String rsentitykey,
                                        String frdatabase) throws SQLException {

        boolean result = true;
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        String updateSQL;

        updateSQL = "UPDATE TENDMAS "
                + "SET TENDER            = ? "
                + ", TENDER_NAME         = ? "
                + ", CARD_PREFIX         = ? "
                + ", TENDER_GROUP        = ? "
                + ", IFX_REF             = ? "
                + ", IS_MANUAL           = ? "
                + ", BANK_HOST           = ? "
                + ", COMM_RATE           = ? "
                + ", MASK                = ? "
                + ", MASK_ENABLED        = ? "
                + ", DBMASK              = ? "
                + ", DBMASK_ENABLED      = ? "
                + ", EXPIRY_MASK         = ? "
                + ", EXPIRY_MASK_ENABLED = ? "
                + ", MANUAL_ENTRY        = ? "
                + ", LAST_UPD_DT         = ? "
                + "WHERE ENTITY_KEY          = ? ";

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

            preparedStatement.setString(1, rstender);
            preparedStatement.setString(2, rstendername);
            preparedStatement.setString(3, rscardprefix);
            preparedStatement.setString(4, rstendergroup);
            preparedStatement.setString(5, rsifxref);
            preparedStatement.setString(6, rsismanual);
            preparedStatement.setString(7, rsbankhost);
            preparedStatement.setString(8, rscommrate);
            preparedStatement.setString(9, rsmask);
            preparedStatement.setString(10, rsmaskenabled);
            preparedStatement.setString(11, rsdbmask);
            preparedStatement.setString(12, rsdbmaskenabled);
            preparedStatement.setString(13, rsexpirymask);
            preparedStatement.setString(14, rsexpirymaskenabled);
            preparedStatement.setString(15, rsmanualentry);
            preparedStatement.setTimestamp(16, rslastupddt);
            preparedStatement.setString(17, rsentitykey);

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

    public static boolean Tendmaschkexists(String entitykey, String frdatabase)
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

            String selectSQL = "SELECT TENDER " + "FROM Tendmas "
                    + "where ENTITY_KEY ='" + entitykey + "'";

            // logger.info(selectSQL);

            preparedStatement = dbConnection.prepareStatement(selectSQL);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String rschktxdate = rs.getString("TENDER");

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
