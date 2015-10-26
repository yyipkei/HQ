package com.bridge.routestockonhand;

import com.bridge.insertstockonhand.InsertSxsreqhdrpos;
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
public class Sxsreqhdrpos {

    private static final Logger logger = Logger.getLogger(Sxsreqhdrpos.class);

    public static void routeSxsreqhdrpos(String dataupdatelog, String entityname,
                                              String entitykey, String database) throws SQLException {
        /*
         * String[] parts = entitykey.split(","); String txdate = parts[0];
		 * String loccode = parts[1]; String regno = parts[2]; String txno =
		 * parts[3]; String seqno = parts[4];
		 */
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        String selectSQL;

        selectSQL = "SELECT REQ_ID,SEND_STAFF_ID,RECV_STAFF_ID,REC_LOC,REC_COMP,BU_CDE,SEN_LOC,SEN_COMP,STATUS,CONTACT,REMARKS,CREATE_DT,CREATE_BY,LAST_UPD_USR,LAST_UPD_DT,LAST_UPD_VER,SEND_STAFF_NAME,COMPANY_CDE,ROWGUID,ENTITY_KEY "
                + "FROM RMSADMIN.sxs_req_hdr_pos " + "where entity_key ='" + entitykey + "'"
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
                String rssendstaffid = rs.getString("SEND_STAFF_ID");
                String rsrecvstaffid = rs.getString("RECV_STAFF_ID");
                String rsrecloc = rs.getString("REC_LOC");
                String rsreccomp = rs.getString("REC_COMP");
                String rsbucde = rs.getString("BU_CDE");
                String rssenloc = rs.getString("SEN_LOC");
                String rssencomp = rs.getString("SEN_COMP");
                String rsstatus = rs.getString("STATUS");
                String rscontact = rs.getString("CONTACT");
                String rsremarks = rs.getString("REMARKS");
                Timestamp rscreatedt = rs.getTimestamp("CREATE_DT");
                String rscreateby = rs.getString("CREATE_BY");
                String rslastupdusr = rs.getString("LAST_UPD_USR");
                Timestamp rslastupddt = rs.getTimestamp("LAST_UPD_DT");
                String rslastupdver = rs.getString("LAST_UPD_VER");
                String rssendstaffname = rs.getString("SEND_STAFF_NAME");
                String rscompanycde = rs.getString("COMPANY_CDE");
                String rsrowguid = rs.getString("ROWGUID");
                String rsentitykey = rs.getString("ENTITY_KEY");

                // logger.info(rslastupddt);

                boolean Chkresult = InsertSxsreqhdrpos.Sxsreqhdrposchkexists(entitykey,
                        database);

                if (!Chkresult) {

                    //logger.info("insert");

                    boolean Insertresult = InsertSxsreqhdrpos.Sxsreqhdrposinsert(rsreqid,rssendstaffid,rsrecvstaffid,rsrecloc,rsreccomp,rsbucde,rssenloc,rssencomp,
                            rsstatus,rscontact,rsremarks,rscreatedt,rscreateby,rslastupdusr,rslastupddt,rslastupdver,rssendstaffname,rscompanycde,rsrowguid,rsentitykey
                            , database);

                    if (Insertresult) {
                        logger.info("Sxsreqhdrpos: 1 row has been inserted. Key:"
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

                    boolean Insertresult = InsertSxsreqhdrpos.Sxsreqhdrposupdate(rsreqid,rssendstaffid,rsrecvstaffid,rsrecloc,rsreccomp,rsbucde,rssenloc,rssencomp,rsstatus
                            ,rscontact,rsremarks,rscreatedt,rscreateby,rslastupdusr,rslastupddt,rslastupdver,rssendstaffname,rscompanycde,rsrowguid,rsentitykey
                            , database);

                    if (Insertresult) {
                        logger.info("Sxsreqhdrpos: 1 row has been updated. Key:"
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
