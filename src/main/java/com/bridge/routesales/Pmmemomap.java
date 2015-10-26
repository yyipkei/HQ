package com.bridge.routesales;

import com.bridge.insertsales.InsertPmmemomap;
import com.bridge.main.HikariMssql;
import com.bridge.main.HikariQracleFrom;
import com.bridge.result.Insertdataupdatelog;
import com.bridge.result.Logupdateresult;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.Objects;

/**
 * Created by keyip on 20/07/2015.
 */
public class Pmmemomap {

    private static final Logger logger = Logger.getLogger(Pmmemomap.class);

    public static void routePmmemomap(String dataupdatelog, String entityname,
                                      String entitykey, String database) throws SQLException {

       /* String[] parts = entitykey.split(",");
        String txdate = parts[0];
        String loccode = parts[1];
        String regno = parts[2];
        String txno = parts[3];*/

        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;

        String selectSQL = "SELECT FPOS_MEMO_NO,FPOS_SERVER_ID,FPOS_LOCATION,FPOS_TERMINAL,FPOS_TX_TYPE,FPOS_DEPOSIT_NO,FPOS_RESERVE_NO,POS2000_SEQ,POS2000_TX_TYPE,TX_DATE,LAST_UPD_DT "
                + "FROM PM_MEMO_MAP "
                + "where FPOS_MEMO_NO ='"
                + entitykey
                + "'"
                +"Order BY LAST_UPD_DT";

        // List<Pmmemomap> Pmmemomaps = new ArrayList<Pmmemomap>();

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

                // Pmmemomap Pmmemomap = new Pmmemomap();

                String rsfposmemono = rs.getString("FPOS_MEMO_NO");
                String rsfposserverid = rs.getString("FPOS_SERVER_ID");
                String rsfposlocation = rs.getString("FPOS_LOCATION");
                String rsfposterminal = rs.getString("FPOS_TERMINAL");
                String rsfpostxtype = rs.getString("FPOS_TX_TYPE");
                String rsfposdepositno = rs.getString("FPOS_DEPOSIT_NO");
                String rsfposreserveno = rs.getString("FPOS_RESERVE_NO");
                String rspos2000seq = rs.getString("POS2000_SEQ");
                String rspos2000txtype = rs.getString("POS2000_TX_TYPE");
                String rstxdate = rs.getString("TX_DATE");
                Timestamp rslastupddt = rs.getTimestamp("LAST_UPD_DT");

                boolean Chkresult = InsertPmmemomap.Pmmemomapchkexists(entitykey,
                        database);

                if (!Chkresult) {

                    //logger.info("insert");

                    boolean Insertresult = InsertPmmemomap.Pmmemomapinsert(
                            rsfposmemono, rsfposserverid, rsfposlocation, rsfposterminal, rsfpostxtype, rsfposdepositno, rsfposreserveno, rspos2000seq, rspos2000txtype, rstxdate,
                            rslastupddt, database);

                    if (Insertresult) {
                        logger.info("Pmmemomap: 1 row has been inserted. Key:" +
                                entitykey);
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

                    boolean Insertresult = InsertPmmemomap.Pmmemomapupdate(
                            rsfposmemono, rsfposserverid, rsfposlocation, rsfposterminal, rsfpostxtype, rsfposdepositno, rsfposreserveno, rspos2000seq, rspos2000txtype, rstxdate,
                            rslastupddt, database);

                    if (Insertresult) {
                        logger.info("Pmmemomap: 1 row has been updated. Key:" +
                                entitykey);
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
