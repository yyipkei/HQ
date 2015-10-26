package com.bridge.routestockonhand;

import com.bridge.insertstockonhand.InsertStockreservationpos;
import com.bridge.main.HikariQracleFrom;
import com.bridge.main.HikariRms;
import com.bridge.result.Insertdataupdatelog;
import com.bridge.result.Logupdateresult;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.Objects;

/**
 * Created by keyip on 17-Jun-15.
 */
public class Stockreservationpos {

    private static final Logger logger = Logger.getLogger(Stockreservationpos.class);

    public static void routeStockreservationpos(String dataupdatelog, String entityname,
                                                String entitykey, String database) throws SQLException {
        /*
         * String[] parts = entitykey.split(","); String txdate = parts[0];
		 * String loccode = parts[1]; String regno = parts[2]; String txno =
		 * parts[3]; String seqno = parts[4];
		 */
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        String selectSQL;

        selectSQL = "SELECT LOC_CDE,SKU,GOA_QTY,RES_QTY,LAST_UPD_DT,LAST_UPD_USR,LAST_UPD_VER,COMPANY_CDE,ROWGUID,ENTITY_KEY "
                + "FROM RMSADMIN.STOCK_RESERVATION_POS " + "where entity_key ='" + entitykey + "'"
                +"Order BY LAST_UPD_DT";

        // List<Sahdr> sahdrs = new ArrayList<Sahdr>();

        try {
            if (Objects.equals(database, "Oracle")) {
                // dbConnection = OracleFrom.getDBConnection();

                HikariQracleFrom OrcaleFrompool = HikariQracleFrom
                        .getInstance();
                dbConnection = OrcaleFrompool.getConnection();
            } else {
                // dbConnection = Mssql.getDBConnection();

                HikariRms Rmspool = HikariRms.getInstance();
                dbConnection = Rmspool.getConnection();
            }

            preparedStatement = dbConnection.prepareStatement(selectSQL);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {

                String rsloccde = rs.getString("LOC_CDE");
                String rssku = rs.getString("SKU");
                String rsgoaqty = rs.getString("GOA_QTY");
                String rsresqty = rs.getString("RES_QTY");
                Timestamp rslastupddt = rs.getTimestamp("LAST_UPD_DT");
                String rslastupdusr = rs.getString("LAST_UPD_USR");
                String rslastupdver = rs.getString("LAST_UPD_VER");
                String rscompanycde = rs.getString("COMPANY_CDE");
                String rsrowguid = rs.getString("ROWGUID");
                String rsentitykey = rs.getString("ENTITY_KEY");

                // logger.info(rslastupddt);

                boolean Chkresult = InsertStockreservationpos.Stockreservationposchkexists(entitykey,
                        database);

                if (!Chkresult) {

                    //logger.info("insert");

                    boolean Insertresult = InsertStockreservationpos.Stockreservationposinsert(rsloccde, rssku, rsgoaqty,
                            rsresqty, rslastupddt, rslastupdusr, rslastupdver, rscompanycde, rsrowguid, rsentitykey, database);

                    if (Insertresult) {
                        logger.info("Stockreservationpos: 1 row has been inserted. Key:"
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

                    //logger.info("update");

                    boolean Insertresult = InsertStockreservationpos.Stockreservationposupdate(rsloccde, rssku, rsgoaqty,
                            rsresqty, rslastupddt, rslastupdusr, rslastupdver, rscompanycde, rsrowguid, rsentitykey, database);

                    if (Insertresult) {
                        logger.info("Stockreservationpos: 1 row has been updated. Key:"
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
