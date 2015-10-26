package com.bridge.routestockonhand;

import com.bridge.insertstockonhand.InsertSxsreqdtlpos;
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
public class Sxsreqdtlpos {
    private static final Logger logger = Logger.getLogger(Sxsreqdtlpos.class);

    public static void routeSxsreqdtlpos(String dataupdatelog, String entityname,
                                              String entitykey, String database) throws SQLException {
        /*
         * String[] parts = entitykey.split(","); String txdate = parts[0];
		 * String loccode = parts[1]; String regno = parts[2]; String txno =
		 * parts[3]; String seqno = parts[4];
		 */
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        String selectSQL;

        selectSQL = "SELECT REQ_ID,REQ_SEQ_NO,BOXNO,SKU,STYLE,DEPT_CDE,CLASS_CDE,COLOR_CDE,SIZE_SET_CDE,SIZE_CDE,SEASON_CDE,BRAND_CDE,RETAIL," +
                "STATUS,SCAN_DT,SCAN_BY,BOXED_DT,BOXED_BY,INTRAN_DT,INTRAN_BY,COMP_DT,COMP_BY,SEN_QTY,REC_QTY,REMARKS,CREATE_DT,CREATE_BY,LAST_UPD_USR," +
                "LAST_UPD_DT,LAST_UPD_VER,SREMARKS,VIP_NO,VIP_TELNO,VIP_NAME,RECV_STAFF_NAME,SEND_STAFF_NAME,COMPANY_CDE,ROWGUID,ENTITY_KEY "
                + "FROM RMSADMIN.sxs_req_dtl_pos " + "where entity_key ='" + entitykey + "'"
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

                String rsreqid = rs.getString("REQ_ID");
                String rsreqseqno = rs.getString("REQ_SEQ_NO");
                String rsboxno = rs.getString("BOXNO");
                String rssku = rs.getString("SKU");
                String rsstyle = rs.getString("STYLE");
                String rsdeptcde = rs.getString("DEPT_CDE");
                String rsclasscde = rs.getString("CLASS_CDE");
                String rscolorcde = rs.getString("COLOR_CDE");
                String rssizesetcde = rs.getString("SIZE_SET_CDE");
                String rssizecde = rs.getString("SIZE_CDE");
                String rsseasoncde = rs.getString("SEASON_CDE");
                String rsbrandcde = rs.getString("BRAND_CDE");
                String rsretail = rs.getString("RETAIL");
                String rsstatus = rs.getString("STATUS");
                Timestamp rsscandt = rs.getTimestamp("SCAN_DT");
                String rsscanby = rs.getString("SCAN_BY");
                Timestamp rsboxeddt = rs.getTimestamp("BOXED_DT");
                String rsboxedby = rs.getString("BOXED_BY");
                Timestamp rsintrandt = rs.getTimestamp("INTRAN_DT");
                String rsintranby = rs.getString("INTRAN_BY");
                Timestamp rscompdt = rs.getTimestamp("COMP_DT");
                String rscompby = rs.getString("COMP_BY");
                String rssenqty = rs.getString("SEN_QTY");
                String rsrecqty = rs.getString("REC_QTY");
                String rsremarks = rs.getString("REMARKS");
                Timestamp rscreatedt = rs.getTimestamp("CREATE_DT");
                String rscreateby = rs.getString("CREATE_BY");
                String rslastupdusr = rs.getString("LAST_UPD_USR");
                Timestamp rslastupddt = rs.getTimestamp("LAST_UPD_DT");
                String rslastupdver = rs.getString("LAST_UPD_VER");
                String rssremarks = rs.getString("SREMARKS");
                String rsvipno = rs.getString("VIP_NO");
                String rsviptelno = rs.getString("VIP_TELNO");
                String rsvipname = rs.getString("VIP_NAME");
                String rsrecvstaffname = rs.getString("RECV_STAFF_NAME");
                String rssendstaffname = rs.getString("SEND_STAFF_NAME");
                String rscompanycde = rs.getString("COMPANY_CDE");
                String rsrowguid = rs.getString("ROWGUID");
                String rsentitykey = rs.getString("ENTITY_KEY");


                // logger.info(rslastupddt);

                boolean Chkresult = InsertSxsreqdtlpos.Sxsreqdtlposchkexists(entitykey,
                        database);

                if (!Chkresult) {

                    //logger.info("insert");

                    boolean Insertresult = InsertSxsreqdtlpos.Sxsreqdtlposinsert(rsreqid,rsreqseqno,rsboxno,rssku,rsstyle,rsdeptcde,rsclasscde,rscolorcde,
                            rssizesetcde,rssizecde,rsseasoncde,rsbrandcde,rsretail,rsstatus,rsscandt,rsscanby,rsboxeddt,rsboxedby,rsintrandt,rsintranby,rscompdt,
                            rscompby,rssenqty,rsrecqty,rsremarks,rscreatedt,rscreateby,rslastupdusr,rslastupddt,rslastupdver,rssremarks,rsvipno,rsviptelno,
                            rsvipname,rsrecvstaffname,rssendstaffname,rscompanycde,rsrowguid,rsentitykey
                            , database);

                    if (Insertresult) {
                        logger.info("Sxsreqdtlpos: 1 row has been inserted. Key:"
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

                    boolean Insertresult = InsertSxsreqdtlpos.Sxsreqdtlposupdate(rsreqid,rsreqseqno,rsboxno,rssku,rsstyle,rsdeptcde,rsclasscde,rscolorcde,
                            rssizesetcde,rssizecde,rsseasoncde,rsbrandcde,rsretail,rsstatus,rsscandt,rsscanby,rsboxeddt,rsboxedby,rsintrandt,rsintranby,rscompdt,
                            rscompby,rssenqty,rsrecqty,rsremarks,rscreatedt,rscreateby,rslastupdusr,rslastupddt,rslastupdver,rssremarks,rsvipno,rsviptelno,
                            rsvipname,rsrecvstaffname,rssendstaffname,rscompanycde,rsrowguid,rsentitykey
                            , database);

                    if (Insertresult) {
                        logger.info("Sxsreqdtlpos: 1 row has been updated. Key:"
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
