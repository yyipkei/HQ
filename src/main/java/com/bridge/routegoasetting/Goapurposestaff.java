package com.bridge.routegoasetting;

import com.bridge.insertgoasetting.InsertGoapurposestaff;
import com.bridge.main.HikariMssql;
import com.bridge.main.HikariQracleFrom;
import com.bridge.result.Insertdataupdatelog;
import com.bridge.result.Logupdateresult;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.Objects;

/**
 * Created by keyip on 01-Jun-15.
 */
public class Goapurposestaff {

    private static final Logger logger = Logger.getLogger(Goapurposestaff.class);

    public static void routeGoapurposestaff(String dataupdatelog, String entityname,
                                            String entitykey, String database) throws SQLException {
        /*
         * String[] parts = entitykey.split(","); String txdate = parts[0];
		 * String loccode = parts[1]; String regno = parts[2]; String txno =
		 * parts[3]; String seqno = parts[4];
		 */
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        String selectSQL;

        selectSQL = "SELECT STAFF_ID,PURPOSE_CDE,LIMIT,LIMIT_FR_DATE,LIMIT_TO_DATE,LAST_UPD_DT,ENTITY_KEY "
                + "FROM GOA_PURPOSE_STAFF " + "where entity_key ='" + entitykey + "'"
                +"Order BY LAST_UPD_DT";

        // List<Sahdr> sahdrs = new ArrayList<Sahdr>();

        try {
            if (Objects.equals(database, "Oracle")) {
                HikariQracleFrom OrcaleFrompool = HikariQracleFrom
                        .getInstance();
                dbConnection = OrcaleFrompool.getConnection();
            } else {
                HikariMssql Mssqlpool = HikariMssql.getInstance();
                dbConnection = Mssqlpool.getConnection();
            }

            preparedStatement = dbConnection.prepareStatement(selectSQL);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {

                String rsstaffid = rs.getString("STAFF_ID");
                String rspurposecde = rs.getString("PURPOSE_CDE");
                String rslimit = rs.getString("LIMIT");
                String rslimitfrdate = rs.getString("LIMIT_FR_DATE");
                String rslimittodate = rs.getString("LIMIT_TO_DATE");
                Timestamp rslastupddt = rs.getTimestamp("LAST_UPD_DT");
                String rsentitykey = rs.getString("ENTITY_KEY");

                // logger.info(rslastupddt);

                boolean Chkresult = InsertGoapurposestaff.Goapurposestaffchkexists(entitykey,
                        database);

                if (!Chkresult) {

                    // logger.info("insert");

                    boolean Insertresult = InsertGoapurposestaff.Goapurposestaffinsert(
                            rsstaffid, rspurposecde, rslimit, rslimitfrdate, rslimittodate, rslastupddt,
                            rsentitykey,
                            database);

                    if (Insertresult) {
                        logger.info("Goapurposestaff: 1 row has been inserted. Key:"
                                + entitykey);
                    } else {
                        logger.info("Insert Error");
                    }
                    Logupdateresult.Updatelogresult(dataupdatelog, entityname,
                            Insertresult, database);

                    if ((!"Oracle".equals(database)) && (Insertresult)) {
                        Insertdataupdatelog.Updatelogresult(entityname, rsentitykey);
                    }

                } else {

                    // logger.info("update");

                    boolean Insertresult = InsertGoapurposestaff.Goapurposestaffupdate(
                            rsstaffid, rspurposecde, rslimit, rslimitfrdate, rslimittodate, rslastupddt,
                            rsentitykey,
                            database);

                    if (Insertresult) {
                        logger.info("Goapurposestaff: 1 row has been updated. Key:"
                                + entitykey);
                    } else {
                        logger.info("Update Error");
                    }
                    Logupdateresult.Updatelogresult(dataupdatelog, entityname,
                            Insertresult, database);

                    if ((!"Oracle".equals(database)) && (Insertresult)) {
                        Insertdataupdatelog.Updatelogresult(entityname, rsentitykey);
                    }
                }
            }
        } catch (SQLException e) {

            logger.info(e.getMessage());

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
