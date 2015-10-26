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

public class InsertMnsizeset {

    private static final Logger logger = Logger.getLogger(InsertMnsizeset.class);

    public static boolean Mnsizesetinsert(String rssizeset, String rssizecde1,
                                          String rssizecde2, String rssizecde3, String rssizecde4,
                                          String rssizecde5, String rssizecde6, String rssizecde7,
                                          String rssizecde8, String rssizecde9, String rssizecde10,
                                          String rssizecde11, String rssizecde12, String rssizecde13,
                                          String rssizecde14, String rssizecde15, String rssizecde16,
                                          String rssizecde17, String rssizecde18, String rssizecde19,
                                          String rssizecde20, String rslastupdusr, Timestamp rslastupddt,
                                          String rslastupdver, String rsrowguid, String rsenablestatus,
                                          String rsentitykey, String frdatabase) throws SQLException {

        boolean result = true;
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        String insertTableSQL;

        insertTableSQL = "INSERT INTO MN_SIZE_SET (SIZE_SET,SIZE_CDE1,SIZE_CDE2,SIZE_CDE3,SIZE_CDE4,"
                + "SIZE_CDE5,SIZE_CDE6,SIZE_CDE7,SIZE_CDE8,SIZE_CDE9,SIZE_CDE10,SIZE_CDE11,SIZE_CDE12,"
                + "SIZE_CDE13,SIZE_CDE14,SIZE_CDE15,SIZE_CDE16,SIZE_CDE17,SIZE_CDE18,SIZE_CDE19,SIZE_CDE20,"
                + "LAST_UPD_USR,LAST_UPD_DT,LAST_UPD_VER,ROWGUID,ENABLE_STATUS,ENTITY_KEY) "
                + "VALUES ( ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,? )";

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

            preparedStatement.setString(1, rssizeset);
            preparedStatement.setString(2, rssizecde1);
            preparedStatement.setString(3, rssizecde2);
            preparedStatement.setString(4, rssizecde3);
            preparedStatement.setString(5, rssizecde4);
            preparedStatement.setString(6, rssizecde5);
            preparedStatement.setString(7, rssizecde6);
            preparedStatement.setString(8, rssizecde7);
            preparedStatement.setString(9, rssizecde8);
            preparedStatement.setString(10, rssizecde9);
            preparedStatement.setString(11, rssizecde10);
            preparedStatement.setString(12, rssizecde11);
            preparedStatement.setString(13, rssizecde12);
            preparedStatement.setString(14, rssizecde13);
            preparedStatement.setString(15, rssizecde14);
            preparedStatement.setString(16, rssizecde15);
            preparedStatement.setString(17, rssizecde16);
            preparedStatement.setString(18, rssizecde17);
            preparedStatement.setString(19, rssizecde18);
            preparedStatement.setString(20, rssizecde19);
            preparedStatement.setString(21, rssizecde20);
            preparedStatement.setString(22, rslastupdusr);
            preparedStatement.setTimestamp(23, rslastupddt);
            preparedStatement.setString(24, rslastupdver);
            preparedStatement.setString(25, rsrowguid);
            preparedStatement.setString(26, rsenablestatus);
            preparedStatement.setString(27, rsentitykey);

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

    public static boolean Mnsizesetupdate(String rssizeset, String rssizecde1,
                                          String rssizecde2, String rssizecde3, String rssizecde4,
                                          String rssizecde5, String rssizecde6, String rssizecde7,
                                          String rssizecde8, String rssizecde9, String rssizecde10,
                                          String rssizecde11, String rssizecde12, String rssizecde13,
                                          String rssizecde14, String rssizecde15, String rssizecde16,
                                          String rssizecde17, String rssizecde18, String rssizecde19,
                                          String rssizecde20, String rslastupdusr, Timestamp rslastupddt,
                                          String rslastupdver, String rsrowguid, String rsenablestatus,
                                          String rsentitykey, String frdatabase) throws SQLException {

        boolean result = true;
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        String updateSQL;

        updateSQL = "UPDATE MN_SIZE_SET " + "SET SIZE_SET    = ? "
                + ", SIZE_CDE1     = ? " + ", SIZE_CDE2     = ? "
                + ", SIZE_CDE3     = ? " + ", SIZE_CDE4     = ? "
                + ", SIZE_CDE5     = ? " + ", SIZE_CDE6     = ? "
                + ", SIZE_CDE7     = ? " + ", SIZE_CDE8     = ? "
                + ", SIZE_CDE9     = ? " + ", SIZE_CDE10    = ? "
                + ", SIZE_CDE11    = ? " + ", SIZE_CDE12    = ? "
                + ", SIZE_CDE13    = ? " + ", SIZE_CDE14    = ? "
                + ", SIZE_CDE15    = ? " + ", SIZE_CDE16    = ? "
                + ", SIZE_CDE17    = ? " + ", SIZE_CDE18    = ? "
                + ", SIZE_CDE19    = ? " + ", SIZE_CDE20    = ? "
                + ", LAST_UPD_USR  = ? " + ", LAST_UPD_DT   = ? "
                + ", LAST_UPD_VER  = ? " + ", ROWGUID       = ? "
                + ", ENABLE_STATUS = ? " + "WHERE ENTITY_KEY    = ? ";

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

            preparedStatement.setString(1, rssizeset);
            preparedStatement.setString(2, rssizecde1);
            preparedStatement.setString(3, rssizecde2);
            preparedStatement.setString(4, rssizecde3);
            preparedStatement.setString(5, rssizecde4);
            preparedStatement.setString(6, rssizecde5);
            preparedStatement.setString(7, rssizecde6);
            preparedStatement.setString(8, rssizecde7);
            preparedStatement.setString(9, rssizecde8);
            preparedStatement.setString(10, rssizecde9);
            preparedStatement.setString(11, rssizecde10);
            preparedStatement.setString(12, rssizecde11);
            preparedStatement.setString(13, rssizecde12);
            preparedStatement.setString(14, rssizecde13);
            preparedStatement.setString(15, rssizecde14);
            preparedStatement.setString(16, rssizecde15);
            preparedStatement.setString(17, rssizecde16);
            preparedStatement.setString(18, rssizecde17);
            preparedStatement.setString(19, rssizecde18);
            preparedStatement.setString(20, rssizecde19);
            preparedStatement.setString(21, rssizecde20);
            preparedStatement.setString(22, rslastupdusr);
            preparedStatement.setTimestamp(23, rslastupddt);
            preparedStatement.setString(24, rslastupdver);
            preparedStatement.setString(25, rsrowguid);
            preparedStatement.setString(26, rsenablestatus);
            preparedStatement.setString(27, rsentitykey);

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

    public static boolean Mnsizesetchkexists(String entitykey, String frdatabase)
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

            String selectSQL = "SELECT SIZE_SET " + "FROM MN_SIZE_SET "
                    + "where ENTITY_KEY ='" + entitykey + "'";

            // logger.info(selectSQL);

            preparedStatement = dbConnection.prepareStatement(selectSQL);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String rschktxdate = rs.getString("SIZE_SET");

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
