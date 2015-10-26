package com.bridge.routeitem;

import com.bridge.insertitem.InsertDiscmas;
import com.bridge.main.HikariMssql;
import com.bridge.main.HikariQracleFrom;
import com.bridge.main.HikariRms;
import com.bridge.result.Insertdataupdatelog;
import com.bridge.result.Logupdateresult;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.Objects;

/**
 * Created by keyip on 25-Jun-15.
 */
public class Discmas {

    private static final Logger logger = Logger.getLogger(Discmas.class);

    public static void routeDiscmas(String dataupdatelog, String entityname,
                                    String entitykey, String database) throws SQLException {
        /*
         * String[] parts = entitykey.split(","); String txdate = parts[0];
		 * String loccode = parts[1]; String regno = parts[2]; String txno =
		 * parts[3]; String seqno = parts[4];
		 */
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        String selectSQL;

        selectSQL = "SELECT DISC_ID,DISC_NAME,DISC_PER,DISC_AMT,DISC_TYPE,ALLOW_OVER,REASON_GROUP,DISC_TX_ITEM,LOC_TYPE," +
                "DISC_FLAG,EFF_FR_DATE,EFF_TO_DATE,PURCHASE_FLAG,PURCHASE_QTY,PURCHASE_AMT,OPEN_CEILING,STATUS,VERSION," +
                "DRAFT_DATE,DRAFT_BY,CFM_DT,CFM_BY,APP_DT,APP_BY,EXTRA_DISC_ID,LAST_UPD_DT,LAST_UPD_USR,LAST_UPD_VER,CONTROL_ID,ENTITY_KEY "
                + "FROM Discmas " + "where entity_key ='" + entitykey + "'"
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

                String rsdiscid = rs.getString("DISC_ID");
                String rsdiscname = rs.getString("DISC_NAME");
                String rsdiscper = rs.getString("DISC_PER");
                String rsdiscamt = rs.getString("DISC_AMT");
                String rsdisctype = rs.getString("DISC_TYPE");
                String rsallowover = rs.getString("ALLOW_OVER");
                String rsreasongroup = rs.getString("REASON_GROUP");
                String rsdisctxitem = rs.getString("DISC_TX_ITEM");
                String rsloctype = rs.getString("LOC_TYPE");
                String rsdiscflag = rs.getString("DISC_FLAG");
                String rsefffrdate = rs.getString("EFF_FR_DATE");
                String rsefftodate = rs.getString("EFF_TO_DATE");
                String rspurchaseflag = rs.getString("PURCHASE_FLAG");
                String rspurchaseqty = rs.getString("PURCHASE_QTY");
                String rspurchaseamt = rs.getString("PURCHASE_AMT");
                String rsopenceiling = rs.getString("OPEN_CEILING");
                String rsstatus = rs.getString("STATUS");
                String rsversion = rs.getString("VERSION");
                Timestamp rsdraftdate = rs.getTimestamp("DRAFT_DATE");
                String rsdraftby = rs.getString("DRAFT_BY");
                Timestamp rscfmdt = rs.getTimestamp("CFM_DT");
                String rscfmby = rs.getString("CFM_BY");
                Timestamp rsappdt = rs.getTimestamp("APP_DT");
                String rsappby = rs.getString("APP_BY");
                String rsextradiscid = rs.getString("EXTRA_DISC_ID");
                Timestamp rslastupddt = rs.getTimestamp("LAST_UPD_DT");
                String rslastupdusr = rs.getString("LAST_UPD_USR");
                String rslastupdver = rs.getString("LAST_UPD_VER");
                String rscontrolid = rs.getString("CONTROL_ID");
                String rsentitykey = rs.getString("ENTITY_KEY");

                // logger.info(rslastupddt);

                boolean Chkresult = InsertDiscmas.Discmaschkexists(entitykey,
                        database);

                if (!Chkresult) {

                    // logger.info("insert");

                    boolean Insertresult = InsertDiscmas.Discmasinsert(rsdiscid, rsdiscname, rsdiscper, rsdiscamt, rsdisctype, rsallowover,
                            rsreasongroup, rsdisctxitem, rsloctype, rsdiscflag, rsefffrdate, rsefftodate, rspurchaseflag, rspurchaseqty, rspurchaseamt,
                            rsopenceiling, rsstatus, rsversion, rsdraftdate, rsdraftby, rscfmdt, rscfmby, rsappdt, rsappby, rsextradiscid, rslastupddt
                            , rslastupdusr, rslastupdver, rscontrolid, rsentitykey, database);

                    if (Insertresult) {
                        logger.info("Discmas: 1 row has been inserted. Key:"
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

                    boolean Insertresult = InsertDiscmas.Discmasupdate(rsdiscid, rsdiscname, rsdiscper, rsdiscamt, rsdisctype, rsallowover,
                            rsreasongroup, rsdisctxitem, rsloctype, rsdiscflag, rsefffrdate, rsefftodate, rspurchaseflag, rspurchaseqty, rspurchaseamt,
                            rsopenceiling, rsstatus, rsversion, rsdraftdate, rsdraftby, rscfmdt, rscfmby, rsappdt, rsappby, rsextradiscid, rslastupddt
                            , rslastupdusr, rslastupdver, rscontrolid, rsentitykey, database);

                    if (Insertresult) {
                        logger.info("Discmas: 1 row has been updated. Key:"
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
