package com.bridge.routestockonhand;

import com.bridge.insertstockonhand.InsertStkledgerbydaypos;
import com.bridge.main.HikariQracleFrom;
import com.bridge.main.HikariRms;
import com.bridge.result.Insertdataupdatelog;
import com.bridge.result.Logupdateresult;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.Objects;

public class Stkledgerbydaypos {

    private static final Logger logger = Logger.getLogger(Stkledgerbydaypos.class);

    public static void routeStkledgerbydaypos(String dataupdatelog, String entityname,
                                              String entitykey, String database) throws SQLException {
        /*
         * String[] parts = entitykey.split(","); String txdate = parts[0];
		 * String loccode = parts[1]; String regno = parts[2]; String txno =
		 * parts[3]; String seqno = parts[4];
		 */
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        String selectSQL;

        selectSQL = "SELECT DATE,LOC_CDE,SKU,OPEN_INV_QTY,OPEN_INV_COST,OPEN_INV_RETAIL,ORI_COST,ORI_RETAIL,SEASON_CDE,STYLE,GG_QTY,STORE_QTY,GG_BJ_QTY,COMPANY_CDE,ROWGUID,LAST_UPD_DT,ENTITY_KEY "
                + "FROM RMSADMIN.STK_LEDGER_BY_DAY_POS " + "where entity_key ='" + entitykey + "'"
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

                Timestamp rsdate = rs.getTimestamp("DATE");
                String rsloccde = rs.getString("LOC_CDE");
                String rssku = rs.getString("SKU");
                String rsopeninvqty = rs.getString("OPEN_INV_QTY");
                String rsopeninvcost = rs.getString("OPEN_INV_COST");
                String rsopeninvretail = rs.getString("OPEN_INV_RETAIL");
                String rsoricost = rs.getString("ORI_COST");
                String rsoriretail = rs.getString("ORI_RETAIL");
                String rsseasoncde = rs.getString("SEASON_CDE");
                String rsstyle = rs.getString("STYLE");
                String rsggqty = rs.getString("GG_QTY");
                String rsstoreqty = rs.getString("STORE_QTY");
                String rsggbjqty = rs.getString("GG_BJ_QTY");
                String rscompanycde = rs.getString("COMPANY_CDE");
                String rsrowguid = rs.getString("ROWGUID");
                Timestamp rslastupddt = rs.getTimestamp("LAST_UPD_DT");
                String rsentitykey = rs.getString("ENTITY_KEY");

                // logger.info(rslastupddt);

                boolean Chkresult = InsertStkledgerbydaypos.Stkledgerbydayposchkexists(entitykey,
                        database);

                if (!Chkresult) {

                    //logger.info("insert");

                    boolean Insertresult = InsertStkledgerbydaypos.Stkledgerbydayposinsert(rsdate, rsloccde, rssku, rsopeninvqty, rsopeninvcost, rsopeninvretail,
                            rsoricost, rsoriretail, rsseasoncde, rsstyle, rsggqty, rsstoreqty, rsggbjqty, rscompanycde, rsrowguid, rslastupddt, rsentitykey
                            , database);

                    if (Insertresult) {
                        logger.info("Stkledgerbydaypos: 1 row has been inserted. Key:"
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

                    boolean Insertresult = InsertStkledgerbydaypos.Stkledgerbydayposupdate(rsdate, rsloccde, rssku, rsopeninvqty, rsopeninvcost, rsopeninvretail,
                            rsoricost, rsoriretail, rsseasoncde, rsstyle, rsggqty, rsstoreqty, rsggbjqty, rscompanycde, rsrowguid, rslastupddt, rsentitykey
                            , database);

                    if (Insertresult) {
                        logger.info("Stkledgerbydaypos: 1 row has been updated. Key:"
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
