package com.bridge.insertmerge;

import com.bridge.main.HikariMerge;
import com.bridge.main.HikariQracleTo;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.Objects;

public class InsertParkpmt {

    private static final Logger logger = Logger.getLogger(InsertParkpmt.class);

    public static boolean Parkpmtinsert(String rsseqno, String rsloccode,
                                        String rsdeptcode, String rsdate, String rstime,
                                        String rspermit1no, String rspermit2no, String rstxregno1,
                                        String rstxno1, String rstxdeptcode1, String rstxsale1,
                                        String rstxregno2, String rstxno2, String rstxdeptcode2,
                                        String rstxsale2, String rstxregno3, String rstxno3,
                                        String rstxdeptcode3, String rstxsale3, String rstxregno4,
                                        String rstxno4, String rstxdeptcode4, String rstxsale4,
                                        String rstxregno5, String rstxno5, String rstxdeptcode5,
                                        String rstxsale5, String rstxregno6, String rstxno6,
                                        String rstxdeptcode6, String rstxsale6, String rstxregno7,
                                        String rstxno7, String rstxdeptcode7, String rstxsale7,
                                        String rstxregno8, String rstxno8, String rstxdeptcode8,
                                        String rstxsale8, String rstxregno9, String rstxno9,
                                        String rstxdeptcode9, String rstxsale9, String rstxregno10,
                                        String rstxno10, String rstxdeptcode10, String rstxsale10,
                                        String rstxsale, String rsvipno, String rsvipcardtype,
                                        String rsvipsale, String rsissuedby, String rsapprovedby,
                                        String rsvipapprovedby, String rsmodifiedby, String rsmodifieddate,
                                        String rsmsreplsynctrants, String rspermit3no, String rspermit4no,
                                        String rspermit5no, String rsissueyear, String rsspvip,
                                        String rspurpose, String rsrowguid, String rstxloccode1,
                                        String rstxloccode2, String rstxloccode3, String rstxloccode4,
                                        String rstxloccode5, String rstxloccode6, String rstxloccode7,
                                        String rstxloccode8, String rstxloccode9, String rstxloccode10,
                                        Timestamp rslastupddt, String frdatabase) throws SQLException {

        boolean result = true;
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        String insertTableSQL;

        try {

            if (Objects.equals(frdatabase, "Oracle")) {
                // dbConnection = Merge.getDBConnection();

                HikariMerge Mergepool = HikariMerge.getInstance();
                dbConnection = Mergepool.getConnection();

                insertTableSQL = "INSERT INTO park_pmt"
                        + "(SEQ_NO,LOC_CODE,DEPT_CODE,DATE,TIME,PERMIT1_NO,PERMIT2_NO,TX_REG_NO_1,TX_NO_1,TX_DEPT_CODE_1,"
                        + "TX_SALE_1,TX_REG_NO_2,TX_NO_2,TX_DEPT_CODE_2,TX_SALE_2,TX_REG_NO_3,TX_NO_3,TX_DEPT_CODE_3,TX_SALE_3,"
                        + "TX_REG_NO_4,TX_NO_4,TX_DEPT_CODE_4,TX_SALE_4,TX_REG_NO_5,TX_NO_5,TX_DEPT_CODE_5,TX_SALE_5,TX_REG_NO_6,"
                        + "TX_NO_6,TX_DEPT_CODE_6,TX_SALE_6,TX_REG_NO_7,TX_NO_7,TX_DEPT_CODE_7,TX_SALE_7,TX_REG_NO_8,TX_NO_8,"
                        + "TX_DEPT_CODE_8,TX_SALE_8,TX_REG_NO_9,TX_NO_9,TX_DEPT_CODE_9,TX_SALE_9,TX_REG_NO_10,TX_NO_10,TX_DEPT_CODE_10,"
                        + "TX_SALE_10,TX_SALE,VIP_NO,VIP_CARD_TYPE,VIP_SALE,ISSUED_BY,APPROVED_BY,VIP_APPROVED_BY,MODIFIED_BY,MODIFIED_DATE,"
                        + "PERMIT3_NO,PERMIT4_NO,PERMIT5_NO,ISSUE_YEAR,SP_VIP,PURPOSE,ROWGUID,TX_LOC_CODE_1,"
                        + "TX_LOC_CODE_2,TX_LOC_CODE_3,TX_LOC_CODE_4,TX_LOC_CODE_5,TX_LOC_CODE_6,TX_LOC_CODE_7,TX_LOC_CODE_8,"
                        + "TX_LOC_CODE_9,TX_LOC_CODE_10,LAST_UPD_DT) "
                        + "VALUES"
                        + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
                        + "newid()" + ",?,?,?,?,?,?,?,?,?,?,?)";

                preparedStatement = dbConnection
                        .prepareStatement(insertTableSQL);

                preparedStatement.setString(1, rsseqno);
                preparedStatement.setString(2, rsloccode);
                preparedStatement.setString(3, rsdeptcode);
                preparedStatement.setString(4, rsdate);
                preparedStatement.setString(5, rstime);
                preparedStatement.setString(6, rspermit1no);
                preparedStatement.setString(7, rspermit2no);
                preparedStatement.setString(8, rstxregno1);
                preparedStatement.setString(9, rstxno1);
                preparedStatement.setString(10, rstxdeptcode1);
                preparedStatement.setString(11, rstxsale1);
                preparedStatement.setString(12, rstxregno2);
                preparedStatement.setString(13, rstxno2);
                preparedStatement.setString(14, rstxdeptcode2);
                preparedStatement.setString(15, rstxsale2);
                preparedStatement.setString(16, rstxregno3);
                preparedStatement.setString(17, rstxno3);
                preparedStatement.setString(18, rstxdeptcode3);
                preparedStatement.setString(19, rstxsale3);
                preparedStatement.setString(20, rstxregno4);
                preparedStatement.setString(21, rstxno4);
                preparedStatement.setString(22, rstxdeptcode4);
                preparedStatement.setString(23, rstxsale4);
                preparedStatement.setString(24, rstxregno5);
                preparedStatement.setString(25, rstxno5);
                preparedStatement.setString(26, rstxdeptcode5);
                preparedStatement.setString(27, rstxsale5);
                preparedStatement.setString(28, rstxregno6);
                preparedStatement.setString(29, rstxno6);
                preparedStatement.setString(30, rstxdeptcode6);
                preparedStatement.setString(31, rstxsale6);
                preparedStatement.setString(32, rstxregno7);
                preparedStatement.setString(33, rstxno7);
                preparedStatement.setString(34, rstxdeptcode7);
                preparedStatement.setString(35, rstxsale7);
                preparedStatement.setString(36, rstxregno8);
                preparedStatement.setString(37, rstxno8);
                preparedStatement.setString(38, rstxdeptcode8);
                preparedStatement.setString(39, rstxsale8);
                preparedStatement.setString(40, rstxregno9);
                preparedStatement.setString(41, rstxno9);
                preparedStatement.setString(42, rstxdeptcode9);
                preparedStatement.setString(43, rstxsale9);
                preparedStatement.setString(44, rstxregno10);
                preparedStatement.setString(45, rstxno10);
                preparedStatement.setString(46, rstxdeptcode10);
                preparedStatement.setString(47, rstxsale10);
                preparedStatement.setString(48, rstxsale);
                preparedStatement.setString(49, rsvipno);
                preparedStatement.setString(50, rsvipcardtype);
                preparedStatement.setString(51, rsvipsale);
                preparedStatement.setString(52, rsissuedby);
                preparedStatement.setString(53, rsapprovedby);
                preparedStatement.setString(54, rsvipapprovedby);
                preparedStatement.setString(55, rsmodifiedby);
                preparedStatement.setString(56, rsmodifieddate);
                preparedStatement.setString(57, rspermit3no);
                preparedStatement.setString(58, rspermit4no);
                preparedStatement.setString(59, rspermit5no);
                preparedStatement.setString(60, rsissueyear);
                preparedStatement.setString(61, rsspvip);
                preparedStatement.setString(62, rspurpose);
                preparedStatement.setString(63, rstxloccode1);
                preparedStatement.setString(64, rstxloccode2);
                preparedStatement.setString(65, rstxloccode3);
                preparedStatement.setString(66, rstxloccode4);
                preparedStatement.setString(67, rstxloccode5);
                preparedStatement.setString(68, rstxloccode6);
                preparedStatement.setString(69, rstxloccode7);
                preparedStatement.setString(70, rstxloccode8);
                preparedStatement.setString(71, rstxloccode9);
                preparedStatement.setString(72, rstxloccode10);
                preparedStatement.setTimestamp(73, rslastupddt);

            } else {
                // dbConnection = OracleTo.getDBConnection();

                HikariQracleTo OrcaleFromTo = HikariQracleTo.getInstance();
                dbConnection = OrcaleFromTo.getConnection();

                insertTableSQL = "INSERT INTO park_pmt"
                        + "(SEQ_NO,LOC_CODE,DEPT_CODE,DATE_,TIME,PERMIT1_NO,PERMIT2_NO,TX_REG_NO_1,TX_NO_1,TX_DEPT_CODE_1,"
                        + "TX_SALE_1,TX_REG_NO_2,TX_NO_2,TX_DEPT_CODE_2,TX_SALE_2,TX_REG_NO_3,TX_NO_3,TX_DEPT_CODE_3,TX_SALE_3,"
                        + "TX_REG_NO_4,TX_NO_4,TX_DEPT_CODE_4,TX_SALE_4,TX_REG_NO_5,TX_NO_5,TX_DEPT_CODE_5,TX_SALE_5,TX_REG_NO_6,"
                        + "TX_NO_6,TX_DEPT_CODE_6,TX_SALE_6,TX_REG_NO_7,TX_NO_7,TX_DEPT_CODE_7,TX_SALE_7,TX_REG_NO_8,TX_NO_8,"
                        + "TX_DEPT_CODE_8,TX_SALE_8,TX_REG_NO_9,TX_NO_9,TX_DEPT_CODE_9,TX_SALE_9,TX_REG_NO_10,TX_NO_10,TX_DEPT_CODE_10,"
                        + "TX_SALE_10,TX_SALE,VIP_NO,VIP_CARD_TYPE,VIP_SALE,ISSUED_BY,APPROVED_BY,VIP_APPROVED_BY,MODIFIED_BY,MODIFIED_DATE,"
                        + "MSREPL_SYNCTRAN_TS,PERMIT3_NO,PERMIT4_NO,PERMIT5_NO,ISSUE_YEAR,SP_VIP,PURPOSE,ROWGUID,TX_LOC_CODE_1,"
                        + "TX_LOC_CODE_2,TX_LOC_CODE_3,TX_LOC_CODE_4,TX_LOC_CODE_5,TX_LOC_CODE_6,TX_LOC_CODE_7,TX_LOC_CODE_8,"
                        + "TX_LOC_CODE_9,TX_LOC_CODE_10,LAST_UPD_DT) "
                        + "VALUES"
                        + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
                        + "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

                preparedStatement = dbConnection
                        .prepareStatement(insertTableSQL);

                preparedStatement.setString(1, rsseqno);
                preparedStatement.setString(2, rsloccode);
                preparedStatement.setString(3, rsdeptcode);
                preparedStatement.setString(4, rsdate);
                preparedStatement.setString(5, rstime);
                preparedStatement.setString(6, rspermit1no);
                preparedStatement.setString(7, rspermit2no);
                preparedStatement.setString(8, rstxregno1);
                preparedStatement.setString(9, rstxno1);
                preparedStatement.setString(10, rstxdeptcode1);
                preparedStatement.setString(11, rstxsale1);
                preparedStatement.setString(12, rstxregno2);
                preparedStatement.setString(13, rstxno2);
                preparedStatement.setString(14, rstxdeptcode2);
                preparedStatement.setString(15, rstxsale2);
                preparedStatement.setString(16, rstxregno3);
                preparedStatement.setString(17, rstxno3);
                preparedStatement.setString(18, rstxdeptcode3);
                preparedStatement.setString(19, rstxsale3);
                preparedStatement.setString(20, rstxregno4);
                preparedStatement.setString(21, rstxno4);
                preparedStatement.setString(22, rstxdeptcode4);
                preparedStatement.setString(23, rstxsale4);
                preparedStatement.setString(24, rstxregno5);
                preparedStatement.setString(25, rstxno5);
                preparedStatement.setString(26, rstxdeptcode5);
                preparedStatement.setString(27, rstxsale5);
                preparedStatement.setString(28, rstxregno6);
                preparedStatement.setString(29, rstxno6);
                preparedStatement.setString(30, rstxdeptcode6);
                preparedStatement.setString(31, rstxsale6);
                preparedStatement.setString(32, rstxregno7);
                preparedStatement.setString(33, rstxno7);
                preparedStatement.setString(34, rstxdeptcode7);
                preparedStatement.setString(35, rstxsale7);
                preparedStatement.setString(36, rstxregno8);
                preparedStatement.setString(37, rstxno8);
                preparedStatement.setString(38, rstxdeptcode8);
                preparedStatement.setString(39, rstxsale8);
                preparedStatement.setString(40, rstxregno9);
                preparedStatement.setString(41, rstxno9);
                preparedStatement.setString(42, rstxdeptcode9);
                preparedStatement.setString(43, rstxsale9);
                preparedStatement.setString(44, rstxregno10);
                preparedStatement.setString(45, rstxno10);
                preparedStatement.setString(46, rstxdeptcode10);
                preparedStatement.setString(47, rstxsale10);
                preparedStatement.setString(48, rstxsale);
                preparedStatement.setString(49, rsvipno);
                preparedStatement.setString(50, rsvipcardtype);
                preparedStatement.setString(51, rsvipsale);
                preparedStatement.setString(52, rsissuedby);
                preparedStatement.setString(53, rsapprovedby);
                preparedStatement.setString(54, rsvipapprovedby);
                preparedStatement.setString(55, rsmodifiedby);
                preparedStatement.setString(56, rsmodifieddate);
                preparedStatement.setString(57, rsmsreplsynctrants);
                preparedStatement.setString(58, rspermit3no);
                preparedStatement.setString(59, rspermit4no);
                preparedStatement.setString(60, rspermit5no);
                preparedStatement.setString(61, rsissueyear);
                preparedStatement.setString(62, rsspvip);
                preparedStatement.setString(63, rspurpose);
                preparedStatement.setString(64, rsrowguid);
                preparedStatement.setString(65, rstxloccode1);
                preparedStatement.setString(66, rstxloccode2);
                preparedStatement.setString(67, rstxloccode3);
                preparedStatement.setString(68, rstxloccode4);
                preparedStatement.setString(69, rstxloccode5);
                preparedStatement.setString(70, rstxloccode6);
                preparedStatement.setString(71, rstxloccode7);
                preparedStatement.setString(72, rstxloccode8);
                preparedStatement.setString(73, rstxloccode9);
                preparedStatement.setString(74, rstxloccode10);
                preparedStatement.setTimestamp(75, rslastupddt);

            }

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

    public static boolean Parkpmtupdate(String rsseqno, String rsloccode,
                                        String rsdeptcode, String rsdate, String rstime,
                                        String rspermit1no, String rspermit2no, String rstxregno1,
                                        String rstxno1, String rstxdeptcode1, String rstxsale1,
                                        String rstxregno2, String rstxno2, String rstxdeptcode2,
                                        String rstxsale2, String rstxregno3, String rstxno3,
                                        String rstxdeptcode3, String rstxsale3, String rstxregno4,
                                        String rstxno4, String rstxdeptcode4, String rstxsale4,
                                        String rstxregno5, String rstxno5, String rstxdeptcode5,
                                        String rstxsale5, String rstxregno6, String rstxno6,
                                        String rstxdeptcode6, String rstxsale6, String rstxregno7,
                                        String rstxno7, String rstxdeptcode7, String rstxsale7,
                                        String rstxregno8, String rstxno8, String rstxdeptcode8,
                                        String rstxsale8, String rstxregno9, String rstxno9,
                                        String rstxdeptcode9, String rstxsale9, String rstxregno10,
                                        String rstxno10, String rstxdeptcode10, String rstxsale10,
                                        String rstxsale, String rsvipno, String rsvipcardtype,
                                        String rsvipsale, String rsissuedby, String rsapprovedby,
                                        String rsvipapprovedby, String rsmodifiedby, String rsmodifieddate,
                                        String rsmsreplsynctrants, String rspermit3no, String rspermit4no,
                                        String rspermit5no, String rsissueyear, String rsspvip,
                                        String rspurpose, String rsrowguid, String rstxloccode1,
                                        String rstxloccode2, String rstxloccode3, String rstxloccode4,
                                        String rstxloccode5, String rstxloccode6, String rstxloccode7,
                                        String rstxloccode8, String rstxloccode9, String rstxloccode10,
                                        Timestamp rslastupddt, String frdatabase) throws SQLException {

        boolean result = true;
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        String updateSQL;

        try {

            if (Objects.equals(frdatabase, "Oracle")) {
                // dbConnection = Merge.getDBConnection();

                HikariMerge Mergepool = HikariMerge.getInstance();
                dbConnection = Mergepool.getConnection();
                updateSQL = "UPDATE PARK_PMT "
                        + "SET  DEPT_CODE          = ? "
                        + ", PERMIT1_NO         = ? "
                        + ", PERMIT2_NO         = ? "
                        + ", TX_REG_NO_1        = ? "
                        + ", TX_NO_1            = ? "
                        + ", TX_DEPT_CODE_1     = ? "
                        + ", TX_SALE_1          = ? "
                        + ", TX_REG_NO_2        = ? "
                        + ", TX_NO_2            = ? "
                        + ", TX_DEPT_CODE_2     = ? "
                        + ", TX_SALE_2          = ? "
                        + ", TX_REG_NO_3        = ? "
                        + ", TX_NO_3            = ? "
                        + ", TX_DEPT_CODE_3     = ? "
                        + ", TX_SALE_3          = ? "
                        + ", TX_REG_NO_4        = ? "
                        + ", TX_NO_4            = ? "
                        + ", TX_DEPT_CODE_4     = ? "
                        + ", TX_SALE_4          = ? "
                        + ", TX_REG_NO_5        = ? "
                        + ", TX_NO_5            = ? "
                        + ", TX_DEPT_CODE_5     = ? "
                        + ", TX_SALE_5          = ? "
                        + ", TX_REG_NO_6        = ? "
                        + ", TX_NO_6            = ? "
                        + ", TX_DEPT_CODE_6     = ? "
                        + ", TX_SALE_6          = ? "
                        + ", TX_REG_NO_7        = ? "
                        + ", TX_NO_7            = ? "
                        + ", TX_DEPT_CODE_7     = ? "
                        + ", TX_SALE_7          = ? "
                        + ", TX_REG_NO_8        = ? "
                        + ", TX_NO_8            = ? "
                        + ", TX_DEPT_CODE_8     = ? "
                        + ", TX_SALE_8          = ? "
                        + ", TX_REG_NO_9        = ? "
                        + ", TX_NO_9            = ? "
                        + ", TX_DEPT_CODE_9     = ? "
                        + ", TX_SALE_9          = ? "
                        + ", TX_REG_NO_10       = ? "
                        + ", TX_NO_10           = ? "
                        + ", TX_DEPT_CODE_10    = ? "
                        + ", TX_SALE_10         = ? "
                        + ", TX_SALE            = ? "
                        + ", VIP_NO             = ? "
                        + ", VIP_CARD_TYPE      = ? "
                        + ", VIP_SALE           = ? "
                        + ", ISSUED_BY          = ? "
                        + ", APPROVED_BY        = ? "
                        + ", VIP_APPROVED_BY    = ? "
                        + ", MODIFIED_BY        = ? "
                        + ", MODIFIED_DATE      = ? "
                        + ", MSREPL_SYNCTRAN_TS = ? "
                        + ", PERMIT3_NO         = ? "
                        + ", PERMIT4_NO         = ? "
                        + ", PERMIT5_NO         = ? "
                        + ", ISSUE_YEAR         = ? "
                        + ", SP_VIP             = ? "
                        + ", PURPOSE            = ? "
                        + ", ROWGUID            = ? "
                        + ", TX_LOC_CODE_1      = ? "
                        + ", TX_LOC_CODE_2      = ? "
                        + ", TX_LOC_CODE_3      = ? "
                        + ", TX_LOC_CODE_4      = ? "
                        + ", TX_LOC_CODE_5      = ? "
                        + ", TX_LOC_CODE_6      = ? "
                        + ", TX_LOC_CODE_7      = ? "
                        + ", TX_LOC_CODE_8      = ? "
                        + ", TX_LOC_CODE_9      = ? "
                        + ", TX_LOC_CODE_10     = ? "
                        + ", LAST_UPD_DT        = ? "
                        + "WHERE SEQ_NO           = ? "
                        + "AND LOC_CODE           = ? "
                        + "AND DATE           	  = ? "
                        + "AND TIME               = ? ";
            } else {
                // dbConnection = OracleTo.getDBConnection();

                HikariQracleTo OrcaleFromTo = HikariQracleTo.getInstance();
                dbConnection = OrcaleFromTo.getConnection();
                updateSQL = "UPDATE PARK_PMT "
                        + "SET  DEPT_CODE          = ? "
                        + ", PERMIT1_NO         = ? "
                        + ", PERMIT2_NO         = ? "
                        + ", TX_REG_NO_1        = ? "
                        + ", TX_NO_1            = ? "
                        + ", TX_DEPT_CODE_1     = ? "
                        + ", TX_SALE_1          = ? "
                        + ", TX_REG_NO_2        = ? "
                        + ", TX_NO_2            = ? "
                        + ", TX_DEPT_CODE_2     = ? "
                        + ", TX_SALE_2          = ? "
                        + ", TX_REG_NO_3        = ? "
                        + ", TX_NO_3            = ? "
                        + ", TX_DEPT_CODE_3     = ? "
                        + ", TX_SALE_3          = ? "
                        + ", TX_REG_NO_4        = ? "
                        + ", TX_NO_4            = ? "
                        + ", TX_DEPT_CODE_4     = ? "
                        + ", TX_SALE_4          = ? "
                        + ", TX_REG_NO_5        = ? "
                        + ", TX_NO_5            = ? "
                        + ", TX_DEPT_CODE_5     = ? "
                        + ", TX_SALE_5          = ? "
                        + ", TX_REG_NO_6        = ? "
                        + ", TX_NO_6            = ? "
                        + ", TX_DEPT_CODE_6     = ? "
                        + ", TX_SALE_6          = ? "
                        + ", TX_REG_NO_7        = ? "
                        + ", TX_NO_7            = ? "
                        + ", TX_DEPT_CODE_7     = ? "
                        + ", TX_SALE_7          = ? "
                        + ", TX_REG_NO_8        = ? "
                        + ", TX_NO_8            = ? "
                        + ", TX_DEPT_CODE_8     = ? "
                        + ", TX_SALE_8          = ? "
                        + ", TX_REG_NO_9        = ? "
                        + ", TX_NO_9            = ? "
                        + ", TX_DEPT_CODE_9     = ? "
                        + ", TX_SALE_9          = ? "
                        + ", TX_REG_NO_10       = ? "
                        + ", TX_NO_10           = ? "
                        + ", TX_DEPT_CODE_10    = ? "
                        + ", TX_SALE_10         = ? "
                        + ", TX_SALE            = ? "
                        + ", VIP_NO             = ? "
                        + ", VIP_CARD_TYPE      = ? "
                        + ", VIP_SALE           = ? "
                        + ", ISSUED_BY          = ? "
                        + ", APPROVED_BY        = ? "
                        + ", VIP_APPROVED_BY    = ? "
                        + ", MODIFIED_BY        = ? "
                        + ", MODIFIED_DATE      = ? "
                        + ", MSREPL_SYNCTRAN_TS = ? "
                        + ", PERMIT3_NO         = ? "
                        + ", PERMIT4_NO         = ? "
                        + ", PERMIT5_NO         = ? "
                        + ", ISSUE_YEAR         = ? "
                        + ", SP_VIP             = ? "
                        + ", PURPOSE            = ? "
                        + ", ROWGUID            = ? "
                        + ", TX_LOC_CODE_1      = ? "
                        + ", TX_LOC_CODE_2      = ? "
                        + ", TX_LOC_CODE_3      = ? "
                        + ", TX_LOC_CODE_4      = ? "
                        + ", TX_LOC_CODE_5      = ? "
                        + ", TX_LOC_CODE_6      = ? "
                        + ", TX_LOC_CODE_7      = ? "
                        + ", TX_LOC_CODE_8      = ? "
                        + ", TX_LOC_CODE_9      = ? "
                        + ", TX_LOC_CODE_10     = ? "
                        + ", LAST_UPD_DT        = ? "
                        + "WHERE SEQ_NO           = ? "
                        + "AND LOC_CODE           = ? "
                        + "AND DATE_              = ? "
                        + "AND TIME               = ? ";
            }

            preparedStatement = dbConnection.prepareStatement(updateSQL);

            preparedStatement.setString(1, rsdeptcode);
            preparedStatement.setString(2, rspermit1no);
            preparedStatement.setString(3, rspermit2no);
            preparedStatement.setString(4, rstxregno1);
            preparedStatement.setString(5, rstxno1);
            preparedStatement.setString(6, rstxdeptcode1);
            preparedStatement.setString(7, rstxsale1);
            preparedStatement.setString(8, rstxregno2);
            preparedStatement.setString(9, rstxno2);
            preparedStatement.setString(10, rstxdeptcode2);
            preparedStatement.setString(11, rstxsale2);
            preparedStatement.setString(12, rstxregno3);
            preparedStatement.setString(13, rstxno3);
            preparedStatement.setString(14, rstxdeptcode3);
            preparedStatement.setString(15, rstxsale3);
            preparedStatement.setString(16, rstxregno4);
            preparedStatement.setString(17, rstxno4);
            preparedStatement.setString(18, rstxdeptcode4);
            preparedStatement.setString(19, rstxsale4);
            preparedStatement.setString(20, rstxregno5);
            preparedStatement.setString(21, rstxno5);
            preparedStatement.setString(22, rstxdeptcode5);
            preparedStatement.setString(23, rstxsale5);
            preparedStatement.setString(24, rstxregno6);
            preparedStatement.setString(25, rstxno6);
            preparedStatement.setString(26, rstxdeptcode6);
            preparedStatement.setString(27, rstxsale6);
            preparedStatement.setString(28, rstxregno7);
            preparedStatement.setString(29, rstxno7);
            preparedStatement.setString(30, rstxdeptcode7);
            preparedStatement.setString(31, rstxsale7);
            preparedStatement.setString(32, rstxregno8);
            preparedStatement.setString(33, rstxno8);
            preparedStatement.setString(34, rstxdeptcode8);
            preparedStatement.setString(35, rstxsale8);
            preparedStatement.setString(36, rstxregno9);
            preparedStatement.setString(37, rstxno9);
            preparedStatement.setString(38, rstxdeptcode9);
            preparedStatement.setString(39, rstxsale9);
            preparedStatement.setString(40, rstxregno10);
            preparedStatement.setString(41, rstxno10);
            preparedStatement.setString(42, rstxdeptcode10);
            preparedStatement.setString(43, rstxsale10);
            preparedStatement.setString(44, rstxsale);
            preparedStatement.setString(45, rsvipno);
            preparedStatement.setString(46, rsvipcardtype);
            preparedStatement.setString(47, rsvipsale);
            preparedStatement.setString(48, rsissuedby);
            preparedStatement.setString(49, rsapprovedby);
            preparedStatement.setString(50, rsvipapprovedby);
            preparedStatement.setString(51, rsmodifiedby);
            preparedStatement.setString(52, rsmodifieddate);
            preparedStatement.setString(53, rsmsreplsynctrants);
            preparedStatement.setString(54, rspermit3no);
            preparedStatement.setString(55, rspermit4no);
            preparedStatement.setString(56, rspermit5no);
            preparedStatement.setString(57, rsissueyear);
            preparedStatement.setString(58, rsspvip);
            preparedStatement.setString(59, rspurpose);
            preparedStatement.setString(60, rsrowguid);
            preparedStatement.setString(61, rstxloccode1);
            preparedStatement.setString(62, rstxloccode2);
            preparedStatement.setString(63, rstxloccode3);
            preparedStatement.setString(64, rstxloccode4);
            preparedStatement.setString(65, rstxloccode5);
            preparedStatement.setString(66, rstxloccode6);
            preparedStatement.setString(67, rstxloccode7);
            preparedStatement.setString(68, rstxloccode8);
            preparedStatement.setString(69, rstxloccode9);
            preparedStatement.setString(70, rstxloccode10);
            preparedStatement.setTimestamp(71, rslastupddt);
            preparedStatement.setString(72, rsseqno);
            preparedStatement.setString(73, rsloccode);
            preparedStatement.setString(74, rsdate);
            preparedStatement.setString(75, rstime);

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

    public static boolean Parkpmtchkexists(String entitykey, String frdatabase)
            throws SQLException {

        String[] parts = entitykey.split(",");
        String seqno = parts[0];
        String loccode = parts[1];
        String date = parts[2];
        String time = parts[3];

        boolean result = false;
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;

        try {

            if (Objects.equals(frdatabase, "Oracle")) {
                // dbConnection = Merge.getDBConnection();

                HikariMerge Mergepool = HikariMerge.getInstance();
                dbConnection = Mergepool.getConnection();
            } else {
                // dbConnection = OracleTo.getDBConnection();

                HikariQracleTo OrcaleFromTo = HikariQracleTo.getInstance();
                dbConnection = OrcaleFromTo.getConnection();
            }

            String selectSQL = "SELECT DEPT_CODE " + "FROM PARK_PMT "
                    + "where SEQ_NO ='" + seqno + "'" + "and LOC_CODE ='"
                    + loccode + "'" + "and DATE_='" + date + "'"
                    + "and TIME ='" + time + "'";

            // logger.info(selectSQL);

            preparedStatement = dbConnection.prepareStatement(selectSQL);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String rschktxdate = rs.getString("DEPT_CODE");

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
