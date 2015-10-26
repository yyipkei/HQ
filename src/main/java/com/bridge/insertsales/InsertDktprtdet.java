package com.bridge.insertsales;

import com.bridge.main.HikariMssql;
import com.bridge.main.HikariQracleTo;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.Objects;

/**
 * Created by keyip on 8/09/2015.
 */
public class InsertDktprtdet {
    private static final Logger logger = Logger.getLogger(InsertDktprtdet.class);

    public static boolean Dktprtdetinsert(String rstxdate, String rsloccode, String rsregno, String rstxno, String rsseqno,
                                          String rsdeptcode, String rssku, String rsitemdesc, String rsqty, String rsorguret,
                                          String rsneturet, String rsnetsale, String rsitemdiscdesc, String rsitemdiscper, String rsitemdiscamt,
                                          String rstxdiscdesc, String rstxdiscper, String rstxdiscamt, String rsmemo1, String rsmemo2,
                                          String rsmemo3, String rsmemo4, String rsmemo5, String rsremark, String rsstatus, String rspickuploc,
                                          String rstxdiscdesc2, String rstxdiscper2, String rstxdiscamt2, String rsvstyle, String rsrowguid,
                                          Timestamp rslastupddt, String frdatabase) throws SQLException {

        boolean result = true;
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;

        try {

            if (Objects.equals(frdatabase, "Oracle")) {
                // dbConnection = Mssql.getDBConnection();

                HikariMssql Mssqlpool = HikariMssql.getInstance();
                dbConnection = Mssqlpool.getConnection();
            } else {
                // dbConnection = OracleTo.getDBConnection();

                HikariQracleTo OrcaleFromTo = HikariQracleTo.getInstance();
                dbConnection = OrcaleFromTo.getConnection();
            }

            String insertTableSQL = "INSERT INTO Dkt_prt_det"
                    + "(TX_DATE,\n" +
                    "    LOC_CODE,\n" +
                    "    REG_NO,\n" +
                    "    TX_NO,\n" +
                    "    SEQ_NO,\n" +
                    "    DEPT_CODE,\n" +
                    "    SKU,\n" +
                    "    ITEM_DESC,\n" +
                    "    QTY,\n" +
                    "    ORG_URET,\n" +
                    "    NET_URET,\n" +
                    "    NET_SALE,\n" +
                    "    ITEM_DISC_DESC,\n" +
                    "    ITEM_DISC_PER,\n" +
                    "    ITEM_DISC_AMT,\n" +
                    "    TX_DISC_DESC,\n" +
                    "    TX_DISC_PER,\n" +
                    "    TX_DISC_AMT,\n" +
                    "    MEMO_1,\n" +
                    "    MEMO_2,\n" +
                    "    MEMO_3,\n" +
                    "    MEMO_4,\n" +
                    "    MEMO_5,\n" +
                    "    REMARK,\n" +
                    "    STATUS,\n" +
                    "    PICKUP_LOC,\n" +
                    "    TX_DISC_DESC2,\n" +
                    "    TX_DISC_PER2,\n" +
                    "    TX_DISC_AMT2,\n" +
                    "    VSTYLE,\n" +
                    "    ROWGUID,\n" +
                    "    LAST_UPD_DT) "
                    + "VALUES"
                    + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

            preparedStatement = dbConnection.prepareStatement(insertTableSQL);

            preparedStatement.setString(1, rstxdate);
            preparedStatement.setString(2, rsloccode);
            preparedStatement.setString(3, rsregno);
            preparedStatement.setString(4, rstxno);
            preparedStatement.setString(5, rsseqno);
            preparedStatement.setString(6, rsdeptcode);
            preparedStatement.setString(7, rssku);
            preparedStatement.setString(8, rsitemdesc);
            preparedStatement.setString(9, rsqty);
            preparedStatement.setString(10, rsorguret);
            preparedStatement.setString(11, rsneturet);
            preparedStatement.setString(12, rsnetsale);
            preparedStatement.setString(13, rsitemdiscdesc);
            preparedStatement.setString(14, rsitemdiscper);
            preparedStatement.setString(15, rsitemdiscamt);
            preparedStatement.setString(16, rstxdiscdesc);
            preparedStatement.setString(17, rstxdiscper);
            preparedStatement.setString(18, rstxdiscamt);
            preparedStatement.setString(19, rsmemo1);
            preparedStatement.setString(20, rsmemo2);
            preparedStatement.setString(21, rsmemo3);
            preparedStatement.setString(22, rsmemo4);
            preparedStatement.setString(23, rsmemo5);
            preparedStatement.setString(24, rsremark);
            preparedStatement.setString(25, rsstatus);
            preparedStatement.setString(26, rspickuploc);
            preparedStatement.setString(27, rstxdiscdesc2);
            preparedStatement.setString(28, rstxdiscper2);
            preparedStatement.setString(29, rstxdiscamt2);
            preparedStatement.setString(30, rsvstyle);
            preparedStatement.setString(31, rsrowguid);
            preparedStatement.setTimestamp(32, rslastupddt);


            preparedStatement.executeUpdate();
            return result;

        } catch (SQLException e) {

            logger.info(e.getMessage());
            result = false;
            return result;

        } finally {

            if (preparedStatement != null) {
                preparedStatement.close();
            }

            if (dbConnection != null) {
                dbConnection.close();
            }
        }

    }

    public static boolean Dktprtdetupdate(String rstxdate, String rsloccode, String rsregno, String rstxno, String rsseqno,
                                          String rsdeptcode, String rssku, String rsitemdesc, String rsqty, String rsorguret,
                                          String rsneturet, String rsnetsale, String rsitemdiscdesc, String rsitemdiscper, String rsitemdiscamt,
                                          String rstxdiscdesc, String rstxdiscper, String rstxdiscamt, String rsmemo1, String rsmemo2,
                                          String rsmemo3, String rsmemo4, String rsmemo5, String rsremark, String rsstatus, String rspickuploc,
                                          String rstxdiscdesc2, String rstxdiscper2, String rstxdiscamt2, String rsvstyle, String rsrowguid,
                                          Timestamp rslastupddt, String frdatabase) throws SQLException {

        boolean result = true;
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;

        try {

            if (Objects.equals(frdatabase, "Oracle")) {
                // dbConnection = Mssql.getDBConnection();

                HikariMssql Mssqlpool = HikariMssql.getInstance();
                dbConnection = Mssqlpool.getConnection();
            } else {
                // dbConnection = OracleTo.getDBConnection();

                HikariQracleTo OrcaleFromTo = HikariQracleTo.getInstance();
                dbConnection = OrcaleFromTo.getConnection();
            }

            String updateSQL = "UPDATE DKT_PRT_DET "
                    + "SET DEPT_CODE      = ? "
                    + ", SKU            = ? "
                    + ", ITEM_DESC      = ? "
                    + ", QTY            = ? "
                    + ", ORG_URET       = ? "
                    + ", NET_URET       = ? "
                    + ", NET_SALE       = ? "
                    + ", ITEM_DISC_DESC = ? "
                    + ", ITEM_DISC_PER  = ? "
                    + ", ITEM_DISC_AMT  = ? "
                    + ", TX_DISC_DESC   = ? "
                    + ", TX_DISC_PER    = ? "
                    + ", TX_DISC_AMT    = ? "
                    + ", MEMO_1         = ? "
                    + ", MEMO_2         = ? "
                    + ", MEMO_3         = ? "
                    + ", MEMO_4         = ? "
                    + ", MEMO_5         = ? "
                    + ", REMARK         = ? "
                    + ", STATUS         = ? "
                    + ", PICKUP_LOC     = ? "
                    + ", TX_DISC_DESC2  = ? "
                    + ", TX_DISC_PER2   = ? "
                    + ", TX_DISC_AMT2   = ? "
                    + ", VSTYLE         = ? "
                    + ", ROWGUID        = ? "
                    + ", LAST_UPD_DT    = ? "
                    + "WHERE TX_DATE      = ? "
                    + "AND LOC_CODE       = ? "
                    + "AND REG_NO         = ? "
                    + "AND TX_NO          = ? "
                    + "AND SEQ_NO         = ? ";

            preparedStatement = dbConnection.prepareStatement(updateSQL);

            preparedStatement.setString(1, rsdeptcode);
            preparedStatement.setString(2, rssku);
            preparedStatement.setString(3, rsitemdesc);
            preparedStatement.setString(4, rsqty);
            preparedStatement.setString(5, rsorguret);
            preparedStatement.setString(6, rsneturet);
            preparedStatement.setString(7, rsnetsale);
            preparedStatement.setString(8, rsitemdiscdesc);
            preparedStatement.setString(9, rsitemdiscper);
            preparedStatement.setString(10, rsitemdiscamt);
            preparedStatement.setString(11, rstxdiscdesc);
            preparedStatement.setString(12, rstxdiscper);
            preparedStatement.setString(13, rstxdiscamt);
            preparedStatement.setString(14, rsmemo1);
            preparedStatement.setString(15, rsmemo2);
            preparedStatement.setString(16, rsmemo3);
            preparedStatement.setString(17, rsmemo4);
            preparedStatement.setString(18, rsmemo5);
            preparedStatement.setString(19, rsremark);
            preparedStatement.setString(20, rsstatus);
            preparedStatement.setString(21, rspickuploc);
            preparedStatement.setString(22, rstxdiscdesc2);
            preparedStatement.setString(23, rstxdiscper2);
            preparedStatement.setString(24, rstxdiscamt2);
            preparedStatement.setString(25, rsvstyle);
            preparedStatement.setString(26, rsrowguid);
            preparedStatement.setTimestamp(27, rslastupddt);
            preparedStatement.setString(28, rstxdate);
            preparedStatement.setString(29, rsloccode);
            preparedStatement.setString(30, rsregno);
            preparedStatement.setString(31, rstxno);
            preparedStatement.setString(32, rsseqno);

            // logger.info(updateSQL);

            preparedStatement.executeUpdate();
            return result;

        } catch (SQLException e) {

            logger.info(e.getMessage());
            result = false;

            logger.info(result);
            return result;

        } finally {

            if (preparedStatement != null) {
                preparedStatement.close();
            }

            if (dbConnection != null) {
                dbConnection.close();
            }

        }

    }

    public static boolean Dktprtdetchkexists(String entitykey, String frdatabase)
            throws SQLException {

        String[] parts = entitykey.split(",");
        String txdate = parts[0];
        String loccode = parts[1];
        String regno = parts[2];
        String txno = parts[3];
        String seqno = parts[4];

        boolean result = false;
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;

        try {

            if (Objects.equals(frdatabase, "Oracle")) {
                // dbConnection = Mssql.getDBConnection();

                HikariMssql Mssqlpool = HikariMssql.getInstance();
                dbConnection = Mssqlpool.getConnection();
            } else {
                // dbConnection = OracleTo.getDBConnection();

                HikariQracleTo OrcaleFromTo = HikariQracleTo.getInstance();
                dbConnection = OrcaleFromTo.getConnection();
            }

            String selectSQL = "SELECT TX_DATE " + "FROM Dkt_prt_det "
                    + "where tx_date ='" + txdate + "'" + "and LOC_CODE ='"
                    + loccode + "'" + "and reg_no='" + regno + "'"
                    + "and tx_no ='" + txno + "'" + "and seq_no ='" + seqno
                    + "'";

            // logger.info(selectSQL);

            preparedStatement = dbConnection.prepareStatement(selectSQL);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String rschktxdate = rs.getString("TX_DATE");

                // logger.info(rschktxdate);

                if (rschktxdate != null) {
                    result = true;// not exists
                }
            }
            return result;
        } catch (SQLException e) {

            logger.info(e.getMessage());
            result = false;
            return result;

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
