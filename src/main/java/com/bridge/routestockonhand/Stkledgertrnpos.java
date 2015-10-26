package com.bridge.routestockonhand;

import com.bridge.insertstockonhand.InsertStkledgertrnpos;
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
public class Stkledgertrnpos {

    private static final Logger logger = Logger.getLogger(Stkledgertrnpos.class);

    public static void routeStkledgertrnpos(String dataupdatelog, String entityname,
                                            String entitykey, String database) throws SQLException {
        /*
         * String[] parts = entitykey.split(","); String txdate = parts[0];
		 * String loccode = parts[1]; String regno = parts[2]; String txno =
		 * parts[3]; String seqno = parts[4];
		 */
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        String selectSQL;

        selectSQL = "SELECT SL_TRN_ID,SKU,DATE,TRN_TYPE,TRN_SUB_TYPE,INSTIT_CDE,LOC_CDE,DEPT_CDE,CLASS_CDE,PROD_TYPE,REASON_CDE,QTY,COST," +
                "RETAIL,PUR_COST,PACK_COST,FREIG_COST,COMM_COST,TOTALCOST,REF_ID,TRN_NO,REG_NO,SEQ_NO,SUB_SEQ_NO,SL_BATCH_NO,LAST_UPD_DT," +
                "LAST_UPD_USR,LAST_UPD_VER,ROWGUID,GG_QTY,STORE_QTY,GG_BJ_QTY,ORG_COST,ORG_TOTALCOST,COMPANY_CDE,ENTITY_KEY "
                + "FROM RMSADMIN.stk_ledger_trn_pos " + "where entity_key ='" + entitykey + "'"
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

                String rssltrnid = rs.getString("SL_TRN_ID");
                String rssku = rs.getString("SKU");
                Timestamp rsdate = rs.getTimestamp("DATE");
                String rstrntype = rs.getString("TRN_TYPE");
                String rstrnsubtype = rs.getString("TRN_SUB_TYPE");
                String rsinstitcde = rs.getString("INSTIT_CDE");
                String rsloccde = rs.getString("LOC_CDE");
                String rsdeptcde = rs.getString("DEPT_CDE");
                String rsclasscde = rs.getString("CLASS_CDE");
                String rsprodtype = rs.getString("PROD_TYPE");
                String rsreasoncde = rs.getString("REASON_CDE");
                String rsqty = rs.getString("QTY");
                String rscost = rs.getString("COST");
                String rsretail = rs.getString("RETAIL");
                String rspurcost = rs.getString("PUR_COST");
                String rspackcost = rs.getString("PACK_COST");
                String rsfreigcost = rs.getString("FREIG_COST");
                String rscommcost = rs.getString("COMM_COST");
                String rstotalcost = rs.getString("TOTALCOST");
                String rsrefid = rs.getString("REF_ID");
                String rstrnno = rs.getString("TRN_NO");
                String rsregno = rs.getString("REG_NO");
                String rsseqno = rs.getString("SEQ_NO");
                String rssubseqno = rs.getString("SUB_SEQ_NO");
                String rsslbatchno = rs.getString("SL_BATCH_NO");
                Timestamp rslastupddt = rs.getTimestamp("LAST_UPD_DT");
                String rslastupdusr = rs.getString("LAST_UPD_USR");
                String rslastupdver = rs.getString("LAST_UPD_VER");
                String rsrowguid = rs.getString("ROWGUID");
                String rsggqty = rs.getString("GG_QTY");
                String rsstoreqty = rs.getString("STORE_QTY");
                String rsggbjqty = rs.getString("GG_BJ_QTY");
                String rsorgcost = rs.getString("ORG_COST");
                String rsorgtotalcost = rs.getString("ORG_TOTALCOST");
                String rscompanycde = rs.getString("COMPANY_CDE");
                String rsentitykey = rs.getString("ENTITY_KEY");

                // logger.info(rslastupddt);

                boolean Chkresult = InsertStkledgertrnpos.Stkledgertrnposchkexists(entitykey,
                        database);

                if (!Chkresult) {

                    //logger.info("insert");

                    boolean Insertresult = InsertStkledgertrnpos.Stkledgertrnposinsert(rssltrnid, rssku, rsdate, rstrntype, rstrnsubtype, rsinstitcde, rsloccde, rsdeptcde
                            , rsclasscde, rsprodtype, rsreasoncde, rsqty, rscost, rsretail, rspurcost, rspackcost, rsfreigcost, rscommcost, rstotalcost, rsrefid,
                            rstrnno, rsregno, rsseqno, rssubseqno, rsslbatchno, rslastupddt, rslastupdusr, rslastupdver, rsrowguid, rsggqty, rsstoreqty, rsggbjqty,
                            rsorgcost, rsorgtotalcost, rscompanycde, rsentitykey, database);

                    if (Insertresult) {
                        logger.info("Stkledgertrnpos: 1 row has been inserted. Key:"
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

                    boolean Insertresult = InsertStkledgertrnpos.Stkledgertrnposupdate(rssltrnid, rssku, rsdate, rstrntype, rstrnsubtype, rsinstitcde, rsloccde, rsdeptcde
                            , rsclasscde, rsprodtype, rsreasoncde, rsqty, rscost, rsretail, rspurcost, rspackcost, rsfreigcost, rscommcost, rstotalcost, rsrefid,
                            rstrnno, rsregno, rsseqno, rssubseqno, rsslbatchno, rslastupddt, rslastupdusr, rslastupdver, rsrowguid, rsggqty, rsstoreqty, rsggbjqty,
                            rsorgcost, rsorgtotalcost, rscompanycde, rsentitykey, database);

                    if (Insertresult) {
                        logger.info("Stkledgertrnpos: 1 row has been updated. Key:"
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
