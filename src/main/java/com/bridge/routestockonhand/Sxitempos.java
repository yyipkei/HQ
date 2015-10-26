package com.bridge.routestockonhand;

import com.bridge.insertstockonhand.InsertSxitempos;
import com.bridge.main.HikariQracleFrom;
import com.bridge.main.HikariRms;
import com.bridge.result.Insertdataupdatelog;
import com.bridge.result.Logupdateresult;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.Objects;

/**
 * Created by keyip on 10-Jun-15.
 */
public class Sxitempos {

    private static final Logger logger = Logger.getLogger(Sxitempos.class);

    public static void routeSxitempos(String dataupdatelog, String entityname,
                                              String entitykey, String database) throws SQLException {
        /*
         * String[] parts = entitykey.split(","); String txdate = parts[0];
		 * String loccode = parts[1]; String regno = parts[2]; String txno =
		 * parts[3]; String seqno = parts[4];
		 */
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        String selectSQL;

        selectSQL = "SELECT BOXNO,SKU,SEN_QTY,REC_QTY,S_BAR_TYPE,R_BAR_TYPE,SEN_COST,REC_COST,FLAG1,DT1,LAST_UPD_USR,LAST_UPD_DT,LAST_UPD_VER,ROWGUID,COMPANY_CDE,ENTITY_KEY "
                + "FROM RMSADMIN.SX_ITEM_POS " + "where entity_key ='" + entitykey + "'"
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

                String rsboxno = rs.getString("BOXNO");
                String rssku = rs.getString("SKU");
                String rssenqty = rs.getString("SEN_QTY");
                String rsrecqty = rs.getString("REC_QTY");
                String rssbartype = rs.getString("S_BAR_TYPE");
                String rsrbartype = rs.getString("R_BAR_TYPE");
                String rssencost = rs.getString("SEN_COST");
                String rsreccost = rs.getString("REC_COST");
                String rsflag1 = rs.getString("FLAG1");
                String rsdt1 = rs.getString("DT1");
                String rslastupdusr = rs.getString("LAST_UPD_USR");
                Timestamp rslastupddt = rs.getTimestamp("LAST_UPD_DT");
                String rslastupdver = rs.getString("LAST_UPD_VER");
                String rsrowguid = rs.getString("ROWGUID");
                String rscompanycde = rs.getString("COMPANY_CDE");
                String rsentitykey = rs.getString("ENTITY_KEY");

                // logger.info(rslastupddt);

                boolean Chkresult = InsertSxitempos.Sxitemposchkexists(entitykey,
                        database);

                if (!Chkresult) {

                    //logger.info("insert");

                    boolean Insertresult = InsertSxitempos.Sxitemposinsert(rsboxno, rssku, rssenqty, rsrecqty, rssbartype, rsrbartype, rssencost, rsreccost, rsflag1, rsdt1, rslastupdusr, rslastupddt, rslastupdver, rsrowguid, rscompanycde, rsentitykey
                            , database);

                    if (Insertresult) {
                        logger.info("Sxitempos: 1 row has been inserted. Key:"
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

                    boolean Insertresult = InsertSxitempos.Sxitemposupdate(rsboxno, rssku, rssenqty, rsrecqty, rssbartype, rsrbartype, rssencost, rsreccost, rsflag1, rsdt1, rslastupdusr, rslastupddt, rslastupdver, rsrowguid, rscompanycde, rsentitykey
                            , database);

                    if (Insertresult) {
                        logger.info("Sxitempos: 1 row has been updated. Key:"
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
