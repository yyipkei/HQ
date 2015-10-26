package com.bridge.routestockonhand;

import com.bridge.insertstockonhand.InsertSxboxpos;
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
public class Sxboxpos {

    private static final Logger logger = Logger.getLogger(Sxboxpos.class);

    public static void routeSxboxpos(String dataupdatelog, String entityname,
                                     String entitykey, String database) throws SQLException {
        /*
         * String[] parts = entitykey.split(","); String txdate = parts[0];
		 * String loccode = parts[1]; String regno = parts[2]; String txno =
		 * parts[3]; String seqno = parts[4];
		 */
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        String selectSQL;

        selectSQL = "SELECT BOXNO,SENDER,P_RECVR,A_RECVR,T_SEN_QTY,T_REC_QTY,S_LDT_ID,R_LDT_ID,SEN_STATUS,REC_STATUS," +
                "BOX_STATUS,SEN_SCAN_DT,REC_SCAN_DT,SEN_LOAD_DT,REC_LOAD_DT,SEN_CMT_DT,REC_CMT_DT,PLAN_TX_DT,BOX_SEN_DT," +
                "DEPT,TYPE,FLAG1,FLAG2,FLAG3,DT1,DT2,DT3,CUSTOM1,REMARKS,LAST_UPD_USR,LAST_UPD_DT,LAST_UPD_VER,ROWGUID,SEN_COMP," +
                "P_REC_COMP,A_REC_COMP,COMPANY_CDE,ENTITY_KEY "
                + "FROM RMSADMIN.sx_box_pos " + "where entity_key ='" + entitykey + "'"
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
                String rssender = rs.getString("SENDER");
                String rsprecvr = rs.getString("P_RECVR");
                String rsarecvr = rs.getString("A_RECVR");
                String rstsenqty = rs.getString("T_SEN_QTY");
                String rstrecqty = rs.getString("T_REC_QTY");
                String rssldtid = rs.getString("S_LDT_ID");
                String rsrldtid = rs.getString("R_LDT_ID");
                String rssenstatus = rs.getString("SEN_STATUS");
                String rsrecstatus = rs.getString("REC_STATUS");
                String rsboxstatus = rs.getString("BOX_STATUS");
                Timestamp rssenscandt = rs.getTimestamp("SEN_SCAN_DT");
                Timestamp rsrecscandt = rs.getTimestamp("REC_SCAN_DT");
                Timestamp rssenloaddt = rs.getTimestamp("SEN_LOAD_DT");
                Timestamp rsrecloaddt = rs.getTimestamp("REC_LOAD_DT");
                Timestamp rssencmtdt = rs.getTimestamp("SEN_CMT_DT");
                Timestamp rsreccmtdt = rs.getTimestamp("REC_CMT_DT");
                Timestamp rsplantxdt = rs.getTimestamp("PLAN_TX_DT");
                Timestamp rsboxsendt = rs.getTimestamp("BOX_SEN_DT");
                String rsdept = rs.getString("DEPT");
                String rstype = rs.getString("TYPE");
                String rsflag1 = rs.getString("FLAG1");
                String rsflag2 = rs.getString("FLAG2");
                String rsflag3 = rs.getString("FLAG3");
                Timestamp rsdt1 = rs.getTimestamp("DT1");
                Timestamp rsdt2 = rs.getTimestamp("DT2");
                Timestamp rsdt3 = rs.getTimestamp("DT3");
                String rscustom1 = rs.getString("CUSTOM1");
                String rsremarks = rs.getString("REMARKS");
                String rslastupdusr = rs.getString("LAST_UPD_USR");
                Timestamp rslastupddt = rs.getTimestamp("LAST_UPD_DT");
                String rslastupdver = rs.getString("LAST_UPD_VER");
                String rsrowguid = rs.getString("ROWGUID");
                String rssencomp = rs.getString("SEN_COMP");
                String rspreccomp = rs.getString("P_REC_COMP");
                String rsareccomp = rs.getString("A_REC_COMP");
                String rscompanycde = rs.getString("COMPANY_CDE");
                String rsentitykey = rs.getString("ENTITY_KEY");

                // logger.info(rslastupddt);

                boolean Chkresult = InsertSxboxpos.Sxboxposchkexists(entitykey,
                        database);

                if (!Chkresult) {

                    //logger.info("insert");

                    boolean Insertresult = InsertSxboxpos.Sxboxposinsert(rsboxno, rssender, rsprecvr, rsarecvr, rstsenqty, rstrecqty, rssldtid,
                            rsrldtid, rssenstatus, rsrecstatus, rsboxstatus, rssenscandt, rsrecscandt, rssenloaddt, rsrecloaddt,
                            rssencmtdt, rsreccmtdt, rsplantxdt, rsboxsendt, rsdept, rstype, rsflag1, rsflag2, rsflag3, rsdt1, rsdt2, rsdt3,
                            rscustom1, rsremarks, rslastupdusr, rslastupddt, rslastupdver, rsrowguid, rssencomp, rspreccomp, rsareccomp, rscompanycde, rsentitykey
                            , database);

                    if (Insertresult) {
                        logger.info("Sxboxpos: 1 row has been inserted. Key:"
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

                    boolean Insertresult = InsertSxboxpos.Sxboxposupdate(rsboxno, rssender, rsprecvr, rsarecvr, rstsenqty, rstrecqty, rssldtid,
                            rsrldtid, rssenstatus, rsrecstatus, rsboxstatus, rssenscandt, rsrecscandt, rssenloaddt, rsrecloaddt,
                            rssencmtdt, rsreccmtdt, rsplantxdt, rsboxsendt, rsdept, rstype, rsflag1, rsflag2, rsflag3, rsdt1, rsdt2, rsdt3,
                            rscustom1, rsremarks, rslastupdusr, rslastupddt, rslastupdver, rsrowguid, rssencomp, rspreccomp, rsareccomp, rscompanycde, rsentitykey
                            , database);

                    if (Insertresult) {
                        logger.info("Sxboxpos: 1 row has been updated. Key:"
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
