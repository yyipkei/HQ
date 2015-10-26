package com.bridge.routegoasetting;

import com.bridge.insertgoasetting.InsertGoastafflimithdr;
import com.bridge.main.HikariMssql;
import com.bridge.main.HikariQracleFrom;
import com.bridge.result.Insertdataupdatelog;
import com.bridge.result.Logupdateresult;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.Objects;

/**
 * Created by keyip on 01-Jun-15.
 */
public class Goastafflimithdr {

    private static final Logger logger = Logger.getLogger(Goastafflimithdr.class);

    public static void routeGoastafflimithdr(String dataupdatelog, String entityname,
                                    String entitykey, String database) throws SQLException {
        /*
		 * String[] parts = entitykey.split(","); String txdate = parts[0];
		 * String loccode = parts[1]; String regno = parts[2]; String txno =
		 * parts[3]; String seqno = parts[4];
		 */
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        String selectSQL;

        selectSQL = "SELECT COMPANY_CDE,STAFF_ID,GROUP_CODE,STATUS,ACTIVE,STAFF_NAME,DIV_DESC,DEPT_DESC,TITLE," +
                "GOA_LIMIT,ACCUM_GOA,ACCUM_PS,BORROW_DAY,EFF_DT_FR,EFF_DT_TO,PREPARE_BY,CFM_DT,CFM_BY,APP_DT,APP_BY," +
                "LAST_UPD_DT,LAST_UPD_USR,LAST_UPD_VER,ENTITY_KEY "
                + "FROM goa_staff_limit_hdr " + "where entity_key ='" + entitykey + "'"
                +"Order BY LAST_UPD_DT";

        // List<Sahdr> sahdrs = new ArrayList<Sahdr>();

        try {
            if (Objects.equals(database, "Oracle")) {
                HikariQracleFrom OrcaleFrompool = HikariQracleFrom
                        .getInstance();
                dbConnection = OrcaleFrompool.getConnection();
            } else {
                HikariMssql Mssqlpool = HikariMssql.getInstance();
                dbConnection = Mssqlpool.getConnection();
            }

            preparedStatement = dbConnection.prepareStatement(selectSQL);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {

                String rscompanycde = rs.getString("COMPANY_CDE");
                String rsstaffid = rs.getString("STAFF_ID");
                String rsgroupcode = rs.getString("GROUP_CODE");
                String rsstatus = rs.getString("STATUS");
                String rsactive = rs.getString("ACTIVE");
                String rsstaffname = rs.getString("STAFF_NAME");
                String rsdivdesc = rs.getString("DIV_DESC");
                String rsdeptdesc = rs.getString("DEPT_DESC");
                String rstitle = rs.getString("TITLE");
                String rsgoalimit = rs.getString("GOA_LIMIT");
                String rsaccumgoa = rs.getString("ACCUM_GOA");
                String rsaccumps = rs.getString("ACCUM_PS");
                String rsborrowday = rs.getString("BORROW_DAY");
                Timestamp rseffdtfr = rs.getTimestamp("EFF_DT_FR");
                Timestamp rseffdtto = rs.getTimestamp("EFF_DT_TO");
                String rsprepareby = rs.getString("PREPARE_BY");
                Timestamp rscfmdt = rs.getTimestamp("CFM_DT");
                String rscfmby = rs.getString("CFM_BY");
                Timestamp rsappdt = rs.getTimestamp("APP_DT");
                String rsappby = rs.getString("APP_BY");
                Timestamp rslastupddt = rs.getTimestamp("LAST_UPD_DT");
                String rslastupdusr = rs.getString("LAST_UPD_USR");
                String rslastupdver = rs.getString("LAST_UPD_VER");
                String rsentitykey = rs.getString("ENTITY_KEY");


                // logger.info(rslastupddt);

                boolean Chkresult = InsertGoastafflimithdr.Goastafflimithdrchkexists(entitykey,
                        database);

                if (!Chkresult) {

                    // logger.info("insert");

                    boolean Insertresult = InsertGoastafflimithdr.Goastafflimithdrinsert(
                            rscompanycde,rsstaffid,rsgroupcode,rsstatus,rsactive,rsstaffname,rsdivdesc,
                            rsdeptdesc,rstitle,rsgoalimit,rsaccumgoa,rsaccumps,rsborrowday,rseffdtfr,rseffdtto,
                            rsprepareby,rscfmdt,rscfmby,rsappdt,rsappby,rslastupddt,rslastupdusr,rslastupdver,rsentitykey,
                            database);

                    if (Insertresult) {
                        logger.info("Goastafflimithdr: 1 row has been inserted. Key:"
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

                    boolean Insertresult = InsertGoastafflimithdr.Goastafflimithdrupdate(
                            rscompanycde,rsstaffid,rsgroupcode,rsstatus,rsactive,rsstaffname,rsdivdesc,
                            rsdeptdesc,rstitle,rsgoalimit,rsaccumgoa,rsaccumps,rsborrowday,rseffdtfr,rseffdtto,
                            rsprepareby,rscfmdt,rscfmby,rsappdt,rsappby,rslastupddt,rslastupdusr,rslastupdver,rsentitykey,
                            database);

                    if (Insertresult) {
                        logger.info("Goastafflimithdr: 1 row has been updated. Key:"
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
