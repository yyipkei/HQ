package com.bridge.routedeposit;

import com.bridge.insertdeposit.InsertBrguest;
import com.bridge.main.HikariMerge;
import com.bridge.main.HikariQracleFrom;
import com.bridge.result.Insertdataupdatelog;
import com.bridge.result.Logupdateresult;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.Objects;

/**
 * Created by keyip on 11-Jun-15.
 */
public class Brguest {

    private static final Logger logger = Logger.getLogger(Brguest.class);

    public static void routeBrguest(String dataupdatelog, String entityname,
                                    String entitykey, String database) throws SQLException {

        String[] parts = entitykey.split(",");
        String bridekey = parts[0];
        String guestkey = parts[1];

        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;

        String selectSQL = "SELECT BRIDAL_KEY,GUEST_KEY,GUEST_NAME,TEL_HOME,TEL_COMPANY,TEL_COMPANY_EX," +
                "TEL_MOBILE,GIFT_CARD_REQ,LAST_ORDER_NUM,MSG,UPDATED_DATETIME,UPDATED_USER,ROWGUID,LAST_UPD_DT "
                + "FROM BR_GUEST "
                + "where BRIDAL_KEY ='"
                + bridekey
                + "'"
                + "and GUEST_KEY ='"
                + guestkey
                + "'"
                +"Order BY LAST_UPD_DT";

        // List<Brguest> Brguests = new ArrayList<Brguest>();

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

                // Brguest Brguest = new Brguest();

                String rsbridalkey = rs.getString("BRIDAL_KEY");
                String rsguestkey = rs.getString("GUEST_KEY");
                String rsguestname = rs.getString("GUEST_NAME");
                String rstelhome = rs.getString("TEL_HOME");
                String rstelcompany = rs.getString("TEL_COMPANY");
                String rstelcompanyex = rs.getString("TEL_COMPANY_EX");
                String rstelmobile = rs.getString("TEL_MOBILE");
                String rsgiftcardreq = rs.getString("GIFT_CARD_REQ");
                String rslastordernum = rs.getString("LAST_ORDER_NUM");
                String rsmsg = rs.getString("MSG");
                Timestamp rsupdateddatetime = rs.getTimestamp("UPDATED_DATETIME");
                String rsupdateduser = rs.getString("UPDATED_USER");
                String rsrowguid = rs.getString("ROWGUID");
                Timestamp rslastupddt = rs.getTimestamp("LAST_UPD_DT");

                boolean Chkresult = InsertBrguest.Brguestchkexists(entitykey,
                        database);

                // logger.info(Chkresult);

                if (!Chkresult) {

                    // logger.info("insert");

                    boolean Insertresult = InsertBrguest.Brguestinsert(rsbridalkey, rsguestkey, rsguestname, rstelhome, rstelcompany,
                            rstelcompanyex, rstelmobile, rsgiftcardreq, rslastordernum, rsmsg, rsupdateddatetime, rsupdateduser, rsrowguid, rslastupddt
                            , database);

                    if (Insertresult) {
                        logger.info("Brguest: 1 row has been inserted. Key:"
                                + bridekey + "," + guestkey );
                    } else {
                        logger.info("Insert Error");
                    }
                    Logupdateresult.Updatelogresult(dataupdatelog, entityname,
                            Insertresult, database);

                    /*if ((!"Oracle".equals(database)) && (Insertresult)) {
                        Insertdataupdatelog.Updatelogresult(entityname, entitykey);
                    }*/

                } else {

                    // logger.info("update");

                    boolean Insertresult = InsertBrguest.Brguestupdate(rsbridalkey, rsguestkey, rsguestname, rstelhome, rstelcompany,
                            rstelcompanyex, rstelmobile, rsgiftcardreq, rslastordernum, rsmsg, rsupdateddatetime, rsupdateduser, rsrowguid, rslastupddt
                            , database);

                    if (Insertresult) {
                        logger.info("Brguest: 1 row has been updated. Key:"
                                + bridekey + "," + guestkey );
                    } else {
                        logger.info("Update Error");
                    }
                    Logupdateresult.Updatelogresult(dataupdatelog, entityname,
                            Insertresult, database);

                    /*if ((!"Oracle".equals(database)) && (Insertresult)) {
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
