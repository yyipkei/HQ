package com.bridge.routedeposit;

import com.bridge.insertdeposit.InsertBrdphdr;
import com.bridge.main.HikariMerge;
import com.bridge.main.HikariQracleFrom;
import com.bridge.result.Insertdataupdatelog;
import com.bridge.result.Logupdateresult;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.Objects;

public class Brdphdr {

    private static final Logger logger = Logger.getLogger(Brdphdr.class);

    public static void routeBrdphdr(String dataupdatelog, String entityname,
                                    String entitykey, String database) throws SQLException {

        String[] parts = entitykey.split(",");
        String brdpno = parts[0];
        String bridalregno = parts[1];
        String issueloccode = parts[2];

        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;

        String selectSQL = "SELECT BRDP_NO,BRIDAL_REG_NO,ISSUE_LOC_CODE,VIP_REF,BRIDAL_DESC," +
                "TX_DATE,LOC_CODE,REG_NO,TX_NO,AMOUNT,BALANCE,STATUS,MODIFIED_BY,MODIFIED_DATE," +
                "MODIFIED_TIME,BRIDAL_GUEST_NO,ROWGUID,LAST_UPD_DT "
                + "FROM brdp_hdr "
                + "where BRDP_NO ='"
                + brdpno
                + "'"
                + "and BRIDAL_REG_NO ='"
                + bridalregno
                + "'"
                + "and ISSUE_LOC_CODE='"
                + issueloccode
                + "'"
                +"Order BY LAST_UPD_DT";

        // List<Brdphdr> Brdphdrs = new ArrayList<Brdphdr>();

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

                // Brdphdr Brdphdr = new Brdphdr();

                String rsbrdpno = rs.getString("BRDP_NO");
                String rsbridalregno = rs.getString("BRIDAL_REG_NO");
                String rsissueloccode = rs.getString("ISSUE_LOC_CODE");
                String rsvipref = rs.getString("VIP_REF");
                String rsbridaldesc = rs.getString("BRIDAL_DESC");
                String rstxdate = rs.getString("TX_DATE");
                String rsloccode = rs.getString("LOC_CODE");
                String rsregno = rs.getString("REG_NO");
                String rstxno = rs.getString("TX_NO");
                String rsamount = rs.getString("AMOUNT");
                String rsbalance = rs.getString("BALANCE");
                String rsstatus = rs.getString("STATUS");
                String rsmodifiedby = rs.getString("MODIFIED_BY");
                String rsmodifieddate = rs.getString("MODIFIED_DATE");
                String rsmodifiedtime = rs.getString("MODIFIED_TIME");
                String rsbridalguestno = rs.getString("BRIDAL_GUEST_NO");
                String rsrowguid = rs.getString("ROWGUID");
                Timestamp rslastupddt = rs.getTimestamp("LAST_UPD_DT");


                boolean Chkresult = InsertBrdphdr.Brdphdrchkexists(entitykey,
                        database);

                // logger.info(Chkresult);

                if (!Chkresult) {

                    // logger.info("insert");

                    boolean Insertresult = InsertBrdphdr.Brdphdrinsert(rsbrdpno, rsbridalregno, rsissueloccode, rsvipref, rsbridaldesc,
                            rstxdate, rsloccode, rsregno, rstxno, rsamount, rsbalance, rsstatus, rsmodifiedby, rsmodifieddate,
                            rsmodifiedtime, rsbridalguestno, rsrowguid, rslastupddt, database);

                    if (Insertresult) {
                        logger.info("Brdphdr: 1 row has been inserted. Key:"
                                + brdpno + "," + bridalregno + "," + issueloccode);
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

                    boolean Insertresult = InsertBrdphdr.Brdphdrupdate(rsbrdpno, rsbridalregno, rsissueloccode, rsvipref, rsbridaldesc,
                            rstxdate, rsloccode, rsregno, rstxno, rsamount, rsbalance, rsstatus, rsmodifiedby, rsmodifieddate,
                            rsmodifiedtime, rsbridalguestno, rsrowguid, rslastupddt, database);

                    if (Insertresult) {
                        logger.info("Brdphdr: 1 row has been updated. Key:"
                                + brdpno + "," + bridalregno + "," + issueloccode);
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
