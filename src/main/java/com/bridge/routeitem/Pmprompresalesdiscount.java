package com.bridge.routeitem;

import com.bridge.insertitem.InsertPmprompresalesdiscount;
import com.bridge.main.HikariQracleFrom;
import com.bridge.main.HikariRms;
import com.bridge.result.Insertdataupdatelog;
import com.bridge.result.Logupdateresult;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.Objects;

/**
 * Created by keyip on 7/08/2015.
 */
public class Pmprompresalesdiscount {

    private static final Logger logger = Logger.getLogger(Pmprompresalesdiscount.class);

    public static void routePmprompresalesdiscount(String dataupdatelog, String entityname,
                                  String entitykey, String database) throws SQLException {
		/*
		 * String[] parts = entitykey.split(","); String txdate = parts[0];
		 * String loccode = parts[1]; String regno = parts[2]; String txno =
		 * parts[3]; String seqno = parts[4];
		 */
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        String selectSQL;

        selectSQL = "SELECT ID,SKU,VIP_TIER,CARD_TYPE,PRESALES_CODE,ALLOW_PROMOTION,EFFECTIVE_FROM,EFFECTIVE_TO,STATUS,DISCOUNTED_PRICE,LAST_UPDATE_DATE,ENTITY_KEY "
                + "FROM PM_PROMO_PRESALES_DISCOUNT " + "where ENTITY_KEY ='" + entitykey + "'"
                +"Order BY LAST_UPDATE_DATE";

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

                String rsid = rs.getString("ID");
                String rssku = rs.getString("SKU");
                String rsviptier = rs.getString("VIP_TIER");
                String rscardtype = rs.getString("CARD_TYPE");
                String rspresalescode = rs.getString("PRESALES_CODE");
                String rsallowpromotion = rs.getString("ALLOW_PROMOTION");
                Timestamp rseffectivefrom = rs.getTimestamp("EFFECTIVE_FROM");
                Timestamp rseffectiveto = rs.getTimestamp("EFFECTIVE_TO");
                String rsstatus = rs.getString("STATUS");
                String rsdiscountedprice = rs.getString("DISCOUNTED_PRICE");
                Timestamp rslastupdatedate = rs.getTimestamp("LAST_UPDATE_DATE");
                String rsentitykey = rs.getString("ENTITY_KEY");

                // logger.info(rslastupddt);

                boolean Chkresult = InsertPmprompresalesdiscount.Pmprompresalesdiscountchkexists(entitykey,
                        database);

                if (!Chkresult) {

                    // logger.info("insert");

                    boolean Insertresult = InsertPmprompresalesdiscount.Pmprompresalesdiscountinsert(rsid,rssku,rsviptier,rscardtype,rspresalescode,
                            rsallowpromotion,rseffectivefrom,rseffectiveto,rsstatus,rsdiscountedprice,rslastupdatedate,rsentitykey,
                            database);

                    if (Insertresult) {
                        logger.info("Pmprompresalesdiscount: 1 row has been inserted. Key:"
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

                    boolean Insertresult = InsertPmprompresalesdiscount.Pmprompresalesdiscountupdate(rsid,rssku,rsviptier,rscardtype,rspresalescode,
                            rsallowpromotion,rseffectivefrom,rseffectiveto,rsstatus,rsdiscountedprice,rslastupdatedate,rsentitykey,
                            database);

                    if (Insertresult) {
                        logger.info("Pmprompresalesdiscount: 1 row has been updated. Key:"
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

            logger.error(e.getMessage());

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
