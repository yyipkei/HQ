package com.bridge.routestockonhand;

import com.bridge.insertstockonhand.InsertInvwrioffhdrpos;
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
public class Invwrioffhdrpos {

    private static final Logger logger = Logger.getLogger(Invwrioffhdrpos.class);

    public static void routeInvwrioffhdrpos(String dataupdatelog, String entityname,
                                            String entitykey, String database) throws SQLException {
        /*
         * String[] parts = entitykey.split(","); String txdate = parts[0];
		 * String loccode = parts[1]; String regno = parts[2]; String txno =
		 * parts[3]; String seqno = parts[4];
		 */
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        String selectSQL;

        selectSQL = "SELECT INSTIT_CDE,WRI_OFF_ID,WRI_OFF_NO,STATUS,EFF_DT,REASON_TYPE,REASON_CODE,LOC_CDE," +
                "REMARK,TOT_COST,APP_BY,APP_DT,LAST_UPD_DT,LAST_UPD_USR,LAST_UPD_VER,ROWGUID,REV_BY,REV_DT," +
                "INSURANCE_CLAIM,GOODS_INSPECTION,COMPANY_CDE,ENTITY_KEY "
                + "FROM RMSADMIN.INV_WRI_OFF_HDR_POS " + "where entity_key ='" + entitykey + "'"
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
                String rswrioffno = rs.getString("WRI_OFF_NO");
                String rsstatus = rs.getString("STATUS");
                Timestamp rseffdt = rs.getTimestamp("EFF_DT");
                String rsreasontype = rs.getString("REASON_TYPE");
                String rsreasoncode = rs.getString("REASON_CODE");
                String rsloccde = rs.getString("LOC_CDE");
                String rsremark = rs.getString("REMARK");
                String rstotcost = rs.getString("TOT_COST");
                String rsappby = rs.getString("APP_BY");
                Timestamp rsappdt = rs.getTimestamp("APP_DT");
                Timestamp rslastupddt = rs.getTimestamp("LAST_UPD_DT");
                String rslastupdusr = rs.getString("LAST_UPD_USR");
                String rslastupdver = rs.getString("LAST_UPD_VER");
                String rsrowguid = rs.getString("ROWGUID");
                String rsrevby = rs.getString("REV_BY");
                Timestamp rsrevdt = rs.getTimestamp("REV_DT");
                String rsinsuranceclaim = rs.getString("INSURANCE_CLAIM");
                String rsgoodsinspection = rs.getString("GOODS_INSPECTION");
                String rscompanycde = rs.getString("COMPANY_CDE");
                String rsentitykey = rs.getString("ENTITY_KEY");


                // logger.info(rslastupddt);

                boolean Chkresult = InsertInvwrioffhdrpos.Invwrioffhdrposchkexists(entitykey,
                        database);

                if (!Chkresult) {

                    //logger.info("insert");

                    boolean Insertresult = InsertInvwrioffhdrpos.Invwrioffhdrposinsert(rsinstitcde, rswrioffid, rswrioffno, rsstatus, rseffdt,
                            rsreasontype, rsreasoncode, rsloccde, rsremark, rstotcost, rsappby, rsappdt, rslastupddt, rslastupdusr, rslastupdver, rsrowguid,
                            rsrevby, rsrevdt, rsinsuranceclaim, rsgoodsinspection, rscompanycde, rsentitykey
                            , database);

                    if (Insertresult) {
                        logger.info("Invwrioffhdrpos: 1 row has been inserted. Key:"
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

                    boolean Insertresult = InsertInvwrioffhdrpos.Invwrioffhdrposupdate(rsinstitcde, rswrioffid, rswrioffno, rsstatus, rseffdt,
                            rsreasontype, rsreasoncode, rsloccde, rsremark, rstotcost, rsappby, rsappdt, rslastupddt, rslastupdusr, rslastupdver, rsrowguid,
                            rsrevby, rsrevdt, rsinsuranceclaim, rsgoodsinspection, rscompanycde, rsentitykey
                            , database);

                    if (Insertresult) {
                        logger.info("Invwrioffhdrpos: 1 row has been updated. Key:"
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
