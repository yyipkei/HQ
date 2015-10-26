package com.bridge.routestockres;

import com.bridge.insertstockres.InsertStockresdet;
import com.bridge.main.HikariMerge;
import com.bridge.main.HikariQracleFrom;
import com.bridge.result.Insertdataupdatelog;
import com.bridge.result.Logupdateresult;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.Objects;

/**
 * Created by keyip on 08-Jun-15.
 */
public class Stockresdet {

    private static final Logger logger = Logger.getLogger(Stockresdet.class);

    public static void routeStockresdet(String dataupdatelog, String entityname,
                                        String entitykey, String database) throws SQLException {

        String[] parts = entitykey.split(",");
        String loccode = parts[0];
        String resno = parts[1];
        String resseq = parts[2];

        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;

        String selectSQL;

        // List<Stockresdet> Stockresdets = new ArrayList<Stockresdet>();

        try {
            if (Objects.equals(database, "Oracle")) {
                HikariQracleFrom OrcaleFrompool = HikariQracleFrom
                        .getInstance();
                dbConnection = OrcaleFrompool.getConnection();

                selectSQL = "SELECT LOC_CODE,RES_NO,RES_SEQ,SKU,CLASS_CODE,STYLE,COLOR,SIZE_,QTY,STATUS,UNRES_BY,UNRES_DATE,UNRES_TIME,ROWGUID,LAST_UPD_DT "
                        + "from stockres_det "
                        + "where LOC_CODE ='"
                        + loccode
                        + "'"
                        + " and RES_NO ='" + resno + "'" + " and RES_SEQ ='" + resseq + "'"
                        +"Order BY LAST_UPD_DT";
            } else {
                HikariMerge Mergepool = HikariMerge.getInstance();
                dbConnection = Mergepool.getConnection();

                selectSQL = "SELECT LOC_CODE,RES_NO,RES_SEQ,SKU,CLASS_CODE,STYLE,COLOR,SIZE,QTY,STATUS,UNRES_BY,UNRES_DATE,UNRES_TIME,ROWGUID,LAST_UPD_DT "
                        + "from stockres_det "
                        + "where LOC_CODE ='"
                        + loccode
                        + "'"
                        + " and RES_NO ='" + resno + "'" + " and RES_SEQ ='" + resseq + "'"
                        +"Order BY LAST_UPD_DT";
            }

            preparedStatement = dbConnection.prepareStatement(selectSQL);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {

                // Stockresdet Stockresdet = new Stockresdet();

                String rsloccode = rs.getString("LOC_CODE");
                String rsresno = rs.getString("RES_NO");
                String rsresseq = rs.getString("RES_SEQ");
                String rssku = rs.getString("SKU");
                String rsclasscode = rs.getString("CLASS_CODE");
                String rsstyle = rs.getString("STYLE");
                String rscolor = rs.getString("COLOR");
                String rssize;
                if (Objects.equals(database, "Oracle")) {
                    rssize = rs.getString("SIZE_");
                } else {
                    rssize = rs.getString("SIZE");
                }
                String rsqty = rs.getString("QTY");
                String rsstatus = rs.getString("STATUS");
                String rsunresby = rs.getString("UNRES_BY");
                String rsunresdate = rs.getString("UNRES_DATE");
                String rsunrestime = rs.getString("UNRES_TIME");
                String rsrowguid = rs.getString("ROWGUID");
                Timestamp rslastupddt = rs.getTimestamp("LAST_UPD_DT");


                boolean Chkresult = InsertStockresdet.Stockresdetchkexists(entitykey,
                        database);

                // logger.info(Chkresult);

                if (!Chkresult) {

                    // logger.info("insert");

                    boolean Insertresult = InsertStockresdet.Stockresdetinsert(
                            rsloccode, rsresno, rsresseq, rssku, rsclasscode, rsstyle, rscolor,
                            rssize, rsqty, rsstatus, rsunresby, rsunresdate, rsunrestime, rsrowguid, rslastupddt, database);

                    if (Insertresult) {
                        logger.info("Stockresdet: 1 row has been inserted. Key:"
                                + entitykey);
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

                    boolean Insertresult = InsertStockresdet.Stockresdetupdate(
                            rsloccode, rsresno, rsresseq, rssku, rsclasscode, rsstyle, rscolor,
                            rssize, rsqty, rsstatus, rsunresby, rsunresdate, rsunrestime, rsrowguid, rslastupddt, database);

                    if (Insertresult) {
                        logger.info("Stockresdet: 1 row has been updated. Key:"
                                + entitykey);
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
