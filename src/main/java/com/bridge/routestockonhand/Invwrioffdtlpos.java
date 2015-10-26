package com.bridge.routestockonhand;

import com.bridge.insertstockonhand.InsertInvwrioffdtlpos;
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
public class Invwrioffdtlpos {

    private static final Logger logger = Logger.getLogger(Invwrioffdtlpos.class);

    public static void routeInvwrioffdtlpos(String dataupdatelog, String entityname,
                                            String entitykey, String database) throws SQLException {
        /*
         * String[] parts = entitykey.split(","); String txdate = parts[0];
		 * String loccode = parts[1]; String regno = parts[2]; String txno =
		 * parts[3]; String seqno = parts[4];
		 */
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        String selectSQL;

        selectSQL = "SELECT INSTIT_CDE,WRI_OFF_ID,SEQ_NO,SKU,UNIT_COST,QTY,COST,AMOUNT,LAST_UPD_DT," +
                "LAST_UPD_USR,LAST_UPD_VER,ROWGUID,VENDOR_UPC,COMPANY_CDE,ENTITY_KEY "
                + "FROM RMSADMIN.inv_wri_off_dtl_pos " + "where entity_key ='" + entitykey + "'"
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

                String rsinstitcde = rs.getString("INSTIT_CDE");
                String rswrioffid = rs.getString("WRI_OFF_ID");
                String rsseqno = rs.getString("SEQ_NO");
                String rssku = rs.getString("SKU");
                String rsunitcost = rs.getString("UNIT_COST");
                String rsqty = rs.getString("QTY");
                String rscost = rs.getString("COST");
                String rsamount = rs.getString("AMOUNT");
                Timestamp rslastupddt = rs.getTimestamp("LAST_UPD_DT");
                String rslastupdusr = rs.getString("LAST_UPD_USR");
                String rslastupdver = rs.getString("LAST_UPD_VER");
                String rsrowguid = rs.getString("ROWGUID");
                String rsvendorupc = rs.getString("VENDOR_UPC");
                String rscompanycde = rs.getString("COMPANY_CDE");
                String rsentitykey = rs.getString("ENTITY_KEY");

                // logger.info(rslastupddt);

                boolean Chkresult = InsertInvwrioffdtlpos.Invwrioffdtlposchkexists(entitykey,
                        database);

                if (!Chkresult) {

                    //logger.info("insert");

                    boolean Insertresult = InsertInvwrioffdtlpos.Invwrioffdtlposinsert(rsinstitcde, rswrioffid, rsseqno, rssku, rsunitcost, rsqty
                            , rscost, rsamount, rslastupddt, rslastupdusr,
                            rslastupdver, rsrowguid, rsvendorupc, rscompanycde, rsentitykey
                            , database);

                    if (Insertresult) {
                        logger.info("Invwrioffdtlpos: 1 row has been inserted. Key:"
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

                    boolean Insertresult = InsertInvwrioffdtlpos.Invwrioffdtlposupdate(rsinstitcde, rswrioffid, rsseqno, rssku, rsunitcost, rsqty
                            , rscost, rsamount, rslastupddt, rslastupdusr,
                            rslastupdver, rsrowguid, rsvendorupc, rscompanycde, rsentitykey
                            , database);

                    if (Insertresult) {
                        logger.info("Invwrioffdtlpos: 1 row has been updated. Key:"
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
