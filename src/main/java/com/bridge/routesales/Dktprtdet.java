package com.bridge.routesales;

import com.bridge.insertsales.InsertDktprtdet;
import com.bridge.main.HikariMssql;
import com.bridge.main.HikariQracleFrom;
import com.bridge.result.Logupdateresult;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.Objects;

/**
 * Created by keyip on 8/09/2015.
 */
public class Dktprtdet {
    private static final Logger logger = Logger.getLogger(Dktprtdet.class);

    public static void routeDktprtdet(String dataupdatelog, String entityname,
                                      String entitykey, String database) throws SQLException {

        String[] parts = entitykey.split(",");
        String txdate = parts[0];
        String loccode = parts[1];
        String regno = parts[2];
        String txno = parts[3];
        String seqno = parts[4];

        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;

        String selectSQL = "SELECT\n" +
                "  TX_DATE,\n" +
                "  LOC_CODE,\n" +
                "  REG_NO,\n" +
                "  TX_NO,\n" +
                "  SEQ_NO,\n" +
                "  DEPT_CODE,\n" +
                "  SKU,\n" +
                "  ITEM_DESC,\n" +
                "  QTY,\n" +
                "  ORG_URET,\n" +
                "  NET_URET,\n" +
                "  NET_SALE,\n" +
                "  ITEM_DISC_DESC,\n" +
                "  ITEM_DISC_PER,\n" +
                "  ITEM_DISC_AMT,\n" +
                "  TX_DISC_DESC,\n" +
                "  TX_DISC_PER,\n" +
                "  TX_DISC_AMT,\n" +
                "  MEMO_1,\n" +
                "  MEMO_2,\n" +
                "  MEMO_3,\n" +
                "  MEMO_4,\n" +
                "  MEMO_5,\n" +
                "  REMARK,\n" +
                "  STATUS,\n" +
                "  PICKUP_LOC,\n" +
                "  TX_DISC_DESC2,\n" +
                "  TX_DISC_PER2,\n" +
                "  TX_DISC_AMT2,\n" +
                "  VSTYLE,\n" +
                "  ROWGUID,\n" +
                "  LAST_UPD_DT FROM Dkt_prt_det \n" +
                "where tx_date ='"
                + txdate
                + "' and LOC_CODE ='"
                + loccode
                + "' and reg_no='"
                + regno
                + "' and tx_no ='"
                + txno
                + "' and seq_no ='"
                + seqno
                + "' Order BY LAST_UPD_DT";

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

                String rstxdate = rs.getString("TX_DATE");
                String rsloccode = rs.getString("LOC_CODE");
                String rsregno = rs.getString("REG_NO");
                String rstxno = rs.getString("TX_NO");
                String rsseqno = rs.getString("SEQ_NO");
                String rsdeptcode = rs.getString("DEPT_CODE");
                String rssku = rs.getString("SKU");
                String rsitemdesc = rs.getString("ITEM_DESC");
                String rsqty = rs.getString("QTY");
                String rsorguret = rs.getString("ORG_URET");
                String rsneturet = rs.getString("NET_URET");
                String rsnetsale = rs.getString("NET_SALE");
                String rsitemdiscdesc = rs.getString("ITEM_DISC_DESC");
                String rsitemdiscper = rs.getString("ITEM_DISC_PER");
                String rsitemdiscamt = rs.getString("ITEM_DISC_AMT");
                String rstxdiscdesc = rs.getString("TX_DISC_DESC");
                String rstxdiscper = rs.getString("TX_DISC_PER");
                String rstxdiscamt = rs.getString("TX_DISC_AMT");
                String rsmemo1 = rs.getString("MEMO_1");
                String rsmemo2 = rs.getString("MEMO_2");
                String rsmemo3 = rs.getString("MEMO_3");
                String rsmemo4 = rs.getString("MEMO_4");
                String rsmemo5 = rs.getString("MEMO_5");
                String rsremark = rs.getString("REMARK");
                String rsstatus = rs.getString("STATUS");
                String rspickuploc = rs.getString("PICKUP_LOC");
                String rstxdiscdesc2 = rs.getString("TX_DISC_DESC2");
                String rstxdiscper2 = rs.getString("TX_DISC_PER2");
                String rstxdiscamt2 = rs.getString("TX_DISC_AMT2");
                String rsvstyle = rs.getString("VSTYLE");
                String rsrowguid = rs.getString("ROWGUID");
                Timestamp rslastupddt = rs.getTimestamp("LAST_UPD_DT");


                boolean Chkresult = InsertDktprtdet.Dktprtdetchkexists(entitykey,
                        database);

                // logger.info(selectSQL);

                if (!Chkresult) {

                    // logger.info("insert");

                    boolean Insertresult = InsertDktprtdet.Dktprtdetinsert(rstxdate,rsloccode,rsregno,rstxno,rsseqno,
                            rsdeptcode,rssku,rsitemdesc,rsqty,rsorguret,rsneturet,rsnetsale,rsitemdiscdesc,rsitemdiscper,
                            rsitemdiscamt,rstxdiscdesc,rstxdiscper,rstxdiscamt,rsmemo1,rsmemo2,rsmemo3,rsmemo4,rsmemo5,rsremark,
                            rsstatus,rspickuploc,rstxdiscdesc2,rstxdiscper2,rstxdiscamt2,rsvstyle,rsrowguid,rslastupddt, database);

                    if (Insertresult) {
                        logger.info("Dktprtdet: 1 row has been inserted. Key:"
                                + rstxdate + "," + rsloccode + "," + rsregno
                                + "'" + rstxno + "'" + rsseqno);
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

                    boolean Insertresult = InsertDktprtdet.Dktprtdetupdate(rstxdate,rsloccode,rsregno,rstxno,rsseqno,
                            rsdeptcode,rssku,rsitemdesc,rsqty,rsorguret,rsneturet,rsnetsale,rsitemdiscdesc,rsitemdiscper,
                            rsitemdiscamt,rstxdiscdesc,rstxdiscper,rstxdiscamt,rsmemo1,rsmemo2,rsmemo3,rsmemo4,rsmemo5,rsremark,
                            rsstatus,rspickuploc,rstxdiscdesc2,rstxdiscper2,rstxdiscamt2,rsvstyle,rsrowguid,rslastupddt, database);

                    if (Insertresult) {
                        logger.info("Dktprtdet: 1 row has been updated. Key:"
                                + rstxdate + "," + rsloccode + "," + rsregno
                                + "'" + rstxno + "'" + rsseqno);
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
