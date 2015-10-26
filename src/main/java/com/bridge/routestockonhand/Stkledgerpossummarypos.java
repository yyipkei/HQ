package com.bridge.routestockonhand;

import com.bridge.insertstockonhand.InsertStkledgerpossummarypos;
import com.bridge.main.HikariQracleFrom;
import com.bridge.main.HikariRms;
import com.bridge.result.Insertdataupdatelog;
import com.bridge.result.Logupdateresult;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.Objects;

/**
 * Created by keyip on 7/07/2015.
 */
public class Stkledgerpossummarypos {

    private static final Logger logger = Logger.getLogger(Stkledgerpossummarypos.class);

    public static void routeStkledgerpossummarypos(String dataupdatelog, String entityname,
                                              String entitykey, String database) throws SQLException {
        /*
         * String[] parts = entitykey.split(","); String txdate = parts[0];
		 * String loccode = parts[1]; String regno = parts[2]; String txno =
		 * parts[3]; String seqno = parts[4];
		 */
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        String selectSQL;

        selectSQL = "SELECT LOC_CDE,SKU,ONHAND_QTY,OPEN_DATE,LAST_UPD_DT,ORI_ONHAND_QTY,COMPANY_CDE,ROWGUID,ENTITY_KEY "
                + "FROM RMSADMIN.STK_LEDGER_POS_SUMMARY_POS " + "where entity_key ='" + entitykey + "'"
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
                String rsonhandqty = rs.getString("ONHAND_QTY");
                Timestamp rsopendate = rs.getTimestamp("OPEN_DATE");
                Timestamp rslastupddt = rs.getTimestamp("LAST_UPD_DT");
                String rsorionhandqty = rs.getString("ORI_ONHAND_QTY");
                String rscompanycde = rs.getString("COMPANY_CDE");
                String rsrowguid = rs.getString("ROWGUID");
                String rsentitykey = rs.getString("ENTITY_KEY");

                // logger.info(rslastupddt);

                boolean Chkresult = InsertStkledgerpossummarypos.Stkledgerpossummaryposchkexists(entitykey,
                        database);

                if (!Chkresult) {

                    //logger.info("insert");

                    boolean Insertresult = InsertStkledgerpossummarypos.Stkledgerpossummaryposinsert(rsloccde, rssku, rsonhandqty, rsopendate, rslastupddt,
                            rsorionhandqty, rscompanycde, rsrowguid, rsentitykey, database);

                    if (Insertresult) {
                        logger.info("Stkledgerpossummarypos: 1 row has been inserted. Key:"
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

                    boolean Insertresult = InsertStkledgerpossummarypos.Stkledgerpossummaryposupdate(rsloccde, rssku, rsonhandqty, rsopendate, rslastupddt,
                            rsorionhandqty, rscompanycde, rsrowguid, rsentitykey, database);

                    if (Insertresult) {
                        logger.info("Stkledgerpossummarypos: 1 row has been updated. Key:"
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

