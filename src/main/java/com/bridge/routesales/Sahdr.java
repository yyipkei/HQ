package com.bridge.routesales;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Objects;

import com.bridge.result.Insertdataupdatelog;
import org.apache.log4j.Logger;

import com.bridge.insertsales.InsertSahdr;
import com.bridge.main.HikariMssql;
import com.bridge.main.HikariQracleFrom;
import com.bridge.result.Logupdateresult;

public class Sahdr {

    private static final Logger logger = Logger.getLogger(Sahdr.class);

    public static void routeSahdr(String dataupdatelog, String entityname,
                                  String entitykey, String database) throws SQLException {

        String[] parts = entitykey.split(",");
        String txdate = parts[0];
        String loccode = parts[1];
        String regno = parts[2];
        String txno = parts[3];

        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;

        String selectSQL = "SELECT TX_DATE,LOC_CODE,REG_NO,TX_NO,TX_TIME,TX_TYPE,VOID_BY,CASHIER_NO,CARD_TYPE,CARD_NO,GFR,GEX,LAST_UPD_DT "
                + "FROM SAHDR "
                + "where tx_date ='"
                + txdate
                + "'"
                + "and LOC_CODE ='"
                + loccode
                + "'"
                + "and reg_no='"
                + regno
                + "'" + "and tx_no ='" + txno + "'";

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

                // Sahdr sahdr = new Sahdr();

                String rstxdate = rs.getString("TX_DATE");
                String rsloccode = rs.getString("LOC_CODE");
                String rsregno = rs.getString("REG_NO");
                String rstxno = rs.getString("TX_NO");
                String rstxtime = rs.getString("TX_TIME");
                String rstxtype = rs.getString("TX_TYPE");
                String rsvoidby = rs.getString("VOID_BY");
                String rscashierno = rs.getString("CASHIER_NO");
                String rscardtype = rs.getString("CARD_TYPE");
                String rscardno = rs.getString("CARD_NO");
                String rsgfr = rs.getString("GFR");
                String rsgex = rs.getString("GEX");
                Timestamp rslastupddt = rs.getTimestamp("LAST_UPD_DT");

                boolean Chkresult = InsertSahdr.Sahdrchkexists(entitykey,
                        database);

                if (!Chkresult) {

                    //logger.info("insert");

                    boolean Insertresult = InsertSahdr.Sahdrinsert(rstxdate,
                            rsloccode, rsregno, rstxno, rstxtime, rstxtype,
                            rsvoidby, rscashierno, rscardtype, rscardno, rsgfr,
                            rsgex, rslastupddt, database);

                    if (Insertresult) {
                        logger.info("SAHDR: 1 row has been inserted. Key:"
                                + rstxdate + "," + rsloccode + "," + rsregno
                                + "'" + rstxno);
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

                    boolean Insertresult = InsertSahdr.Sahdrupdate(rstxdate,
                            rsloccode, rsregno, rstxno, rstxtime, rstxtype,
                            rsvoidby, rscashierno, rscardtype, rscardno, rsgfr,
                            rsgex, rslastupddt, database);

                    if (Insertresult) {
                        logger.info("SAHDR: 1 row has been updated. Key:"
                                + rstxdate + "," + rsloccode + "," + rsregno
                                + "'" + rstxno);
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
