package com.bridge.routedeposit;

import com.bridge.insertdeposit.InsertBrreg;
import com.bridge.main.HikariMerge;
import com.bridge.main.HikariQracleFrom;
import com.bridge.result.Insertdataupdatelog;
import com.bridge.result.Logupdateresult;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.Objects;

/**
 * Created by keyip on 12-Jun-15.
 */
public class Brreg {
    private static final Logger logger = Logger.getLogger(Brreg.class);

    public static void routeBrreg(String dataupdatelog, String entityname,
                                   String entitykey, String database) throws SQLException {

        String[] parts = entitykey.split(",");
        String bridekey = parts[0];

        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;

        String selectSQL = "SELECT BRIDE_KEY,WED_DATE,BRIDE_NAME,GROOM_NAME,RECEP_DATE,ADDR_1,ADDR_2,ADDR_3,DEL_ADDR_1," +
                "DEL_ADDR_2,DEL_ADDR_3,DEL_DATE,TEL_HOME,TEL_COMPANY,TEL_COMPANY_EX,TEL_MOBILE,TEL_FAX,TEL_DEL,TEL_FAX_DEL," +
                "EMAIL_ADDR,P_CARD,REMARK,CREATED_LOC,UPDATED_DATETIME,UPDATED_USER,LAST_GIFT_NUM,LAST_GUEST_NUM,TIME_STAMP," +
                "ROWGUID,LAST_UPD_DT "
                + "FROM BR_REG "
                + "where BRIDE_KEY ='"
                + bridekey
                + "'"
                +"Order BY LAST_UPD_DT";

        // List<Brreg> Brregs = new ArrayList<Brreg>();

        try {
            if (Objects.equals(database, "Oracle")) {
                HikariQracleFrom OrcaleFrompool = HikariQracleFrom
                        .getInstance();
                dbConnection = OrcaleFrompool.getConnection();
            } else {
                HikariMerge Mergepool = HikariMerge.getInstance();
                dbConnection = Mergepool.getConnection();
            }

            preparedStatement = dbConnection.prepareStatement(selectSQL);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {

                // Brreg Brreg = new Brreg();

                String rsbridekey = rs.getString("BRIDE_KEY");
                Timestamp rsweddate = rs.getTimestamp("WED_DATE");
                String rsbridename = rs.getString("BRIDE_NAME");
                String rsgroomname = rs.getString("GROOM_NAME");
                Timestamp rsrecepdate = rs.getTimestamp("RECEP_DATE");
                String rsaddr1 = rs.getString("ADDR_1");
                String rsaddr2 = rs.getString("ADDR_2");
                String rsaddr3 = rs.getString("ADDR_3");
                String rsdeladdr1 = rs.getString("DEL_ADDR_1");
                String rsdeladdr2 = rs.getString("DEL_ADDR_2");
                String rsdeladdr3 = rs.getString("DEL_ADDR_3");
                Timestamp rsdeldate = rs.getTimestamp("DEL_DATE");
                String rstelhome = rs.getString("TEL_HOME");
                String rstelcompany = rs.getString("TEL_COMPANY");
                String rstelcompanyex = rs.getString("TEL_COMPANY_EX");
                String rstelmobile = rs.getString("TEL_MOBILE");
                String rstelfax = rs.getString("TEL_FAX");
                String rsteldel = rs.getString("TEL_DEL");
                String rstelfaxdel = rs.getString("TEL_FAX_DEL");
                String rsemailaddr = rs.getString("EMAIL_ADDR");
                String rspcard = rs.getString("P_CARD");
                String rsremark = rs.getString("REMARK");
                String rscreatedloc = rs.getString("CREATED_LOC");
                Timestamp rsupdateddatetime = rs.getTimestamp("UPDATED_DATETIME");
                String rsupdateduser = rs.getString("UPDATED_USER");
                String rslastgiftnum = rs.getString("LAST_GIFT_NUM");
                String rslastguestnum = rs.getString("LAST_GUEST_NUM");
                Timestamp rstimestamp = rs.getTimestamp("TIME_STAMP");
                String rsrowguid = rs.getString("ROWGUID");
                Timestamp rslastupddt = rs.getTimestamp("LAST_UPD_DT");


                boolean Chkresult = InsertBrreg.Brregchkexists(entitykey,
                        database);

                // logger.info(Chkresult);

                if (!Chkresult) {

                    // logger.info("insert");

                    boolean Insertresult = InsertBrreg.Brreginsert(rsbridekey,rsweddate,rsbridename,rsgroomname,rsrecepdate,
                            rsaddr1,rsaddr2,rsaddr3,rsdeladdr1,rsdeladdr2,rsdeladdr3,rsdeldate,rstelhome,rstelcompany,
                            rstelcompanyex,rstelmobile,rstelfax,rsteldel,rstelfaxdel,rsemailaddr,rspcard,rsremark,
                            rscreatedloc,rsupdateddatetime,rsupdateduser,rslastgiftnum,rslastguestnum,rstimestamp,
                            rsrowguid,rslastupddt, database);

                    if (Insertresult) {
                        logger.info("Brreg: 1 row has been inserted. Key:"
                                + bridekey  );
                    } else {
                        logger.info("Insert Error");
                    }
                    Logupdateresult.Updatelogresult(dataupdatelog, entityname,
                            Insertresult, database);

                   /* if ((!"Oracle".equals(database)) && (Insertresult)) {
                        Insertdataupdatelog.Updatelogresult(entityname, entitykey);
                    }*/

                } else {

                    // logger.info("update");

                    boolean Insertresult = InsertBrreg.Brregupdate(rsbridekey,rsweddate,rsbridename,rsgroomname,rsrecepdate,
                            rsaddr1,rsaddr2,rsaddr3,rsdeladdr1,rsdeladdr2,rsdeladdr3,rsdeldate,rstelhome,rstelcompany,
                            rstelcompanyex,rstelmobile,rstelfax,rsteldel,rstelfaxdel,rsemailaddr,rspcard,rsremark,
                            rscreatedloc,rsupdateddatetime,rsupdateduser,rslastgiftnum,rslastguestnum,rstimestamp,
                            rsrowguid,rslastupddt, database);

                    if (Insertresult) {
                        logger.info("Brreg: 1 row has been updated. Key:"
                                + bridekey );
                    } else {
                        logger.info("Update Error");
                    }
                    Logupdateresult.Updatelogresult(dataupdatelog, entityname,
                            Insertresult, database);

                   /* if ((!"Oracle".equals(database)) && (Insertresult)) {
                        Insertdataupdatelog.Updatelogresult(entityname, entitykey);
                    }*/
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
