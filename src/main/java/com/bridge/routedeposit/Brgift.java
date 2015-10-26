package com.bridge.routedeposit;

import com.bridge.insertdeposit.InsertBrgift;
import com.bridge.main.HikariMerge;
import com.bridge.main.HikariQracleFrom;
import com.bridge.result.Insertdataupdatelog;
import com.bridge.result.Logupdateresult;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.Objects;

public class Brgift {

    private static final Logger logger = Logger.getLogger(Brgift.class);

    public static void routeBrgift(String dataupdatelog, String entityname,
                                   String entitykey, String database) throws SQLException {

        String[] parts = entitykey.split(",");
        String bridekey = parts[0];
        String giftkey = parts[1];
        String requesyloc = parts[2];

        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;

        String selectSQL = "SELECT BRIDE_KEY,GIFT_KEY,ITEM_DESC,SKU,COUPLE_SELECT,QTY," +
                "ORDERED_QTY,TO_BE_DEL_QTY,DELIVERED_QTY,PRICE,G_PAYMENT,REQUEST_LOC,STOCK_STATUS," +
                "REMARK,UPDATED_DATETIME,UPDATED_USER,ROWGUID,LAST_UPD_DT "
                + "FROM BR_GIFT "
                + "where BRIDE_KEY ='"
                + bridekey
                + "'"
                + "and GIFT_KEY ='"
                + giftkey
                + "'"
                + "and REQUEST_LOC='"
                + requesyloc
                + "'"
                +"Order BY LAST_UPD_DT";

        // List<Brgift> Brgifts = new ArrayList<Brgift>();

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

                // Brgift Brgift = new Brgift();

                String rsbridekey = rs.getString("BRIDE_KEY");
                String rsgiftkey = rs.getString("GIFT_KEY");
                String rsitemdesc = rs.getString("ITEM_DESC");
                String rssku = rs.getString("SKU");
                String rscoupleselect = rs.getString("COUPLE_SELECT");
                String rsqty = rs.getString("QTY");
                String rsorderedqty = rs.getString("ORDERED_QTY");
                String rstobedelqty = rs.getString("TO_BE_DEL_QTY");
                String rsdeliveredqty = rs.getString("DELIVERED_QTY");
                String rsprice = rs.getString("PRICE");
                String rsgpayment = rs.getString("G_PAYMENT");
                String rsrequestloc = rs.getString("REQUEST_LOC");
                String rsstockstatus = rs.getString("STOCK_STATUS");
                String rsremark = rs.getString("REMARK");
                Timestamp rsupdateddatetime = rs.getTimestamp("UPDATED_DATETIME");
                String rsupdateduser = rs.getString("UPDATED_USER");
                String rsrowguid = rs.getString("ROWGUID");
                Timestamp rslastupddt = rs.getTimestamp("LAST_UPD_DT");

                boolean Chkresult = InsertBrgift.Brgiftchkexists(entitykey,
                        database);

                // logger.info(Chkresult);

                if (!Chkresult) {

                    // logger.info("insert");

                    boolean Insertresult = InsertBrgift.Brgiftinsert(rsbridekey, rsgiftkey, rsitemdesc, rssku, rscoupleselect, rsqty,
                            rsorderedqty, rstobedelqty, rsdeliveredqty, rsprice, rsgpayment, rsrequestloc, rsstockstatus, rsremark,
                            rsupdateddatetime, rsupdateduser, rsrowguid, rslastupddt
                            , database);

                    if (Insertresult) {
                        logger.info("Brgift: 1 row has been inserted. Key:"
                                + bridekey + "," + giftkey + "," + requesyloc);
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

                    boolean Insertresult = InsertBrgift.Brgiftupdate(rsbridekey, rsgiftkey, rsitemdesc, rssku, rscoupleselect, rsqty,
                            rsorderedqty, rstobedelqty, rsdeliveredqty, rsprice, rsgpayment, rsrequestloc, rsstockstatus, rsremark,
                            rsupdateddatetime, rsupdateduser, rsrowguid, rslastupddt
                            , database);

                    if (Insertresult) {
                        logger.info("Brgift: 1 row has been updated. Key:"
                                + bridekey + "," + giftkey + "," + requesyloc);
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
