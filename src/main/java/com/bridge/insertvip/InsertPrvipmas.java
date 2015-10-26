package com.bridge.insertvip;

import com.bridge.main.HikariMssql;
import com.bridge.main.HikariQracleTo;
import com.bridge.main.HikariRms;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.Objects;

/**
 * Created by keyip on 11/09/2015.
 */
public class InsertPrvipmas {

    private static final Logger logger = Logger.getLogger(InsertPrvipmas.class);

    public static boolean Prvipmasinsert(String rsvipno,String rssurname,String rsfirstname,String rscsurname,String rscfirstname,
                                         String rstelno1,String rstelno2,String rscardtype,String rstitle,String rsaddr1,
                                         String rsaddr2,String rsaddrx,String rscity,String rsstate,String rscountrycode,
                                         String rspostalcode,String rsid,String rssex,String rsemailaddress,String rsbirthdate,
                                         String rsannvdate,String rsspousename,String rsconame,String rsdisable,String rsdiseff,
                                         String rsdisrea,String rsdiscper,String rsflag1,String rsflag2,String rsflag3,String rsflag4,
                                         String rsflag5,String rspurchasety,String rspurchasely,String rspurchaseever,String rsagegp,String rsincomegp,
                                         String rsoccugp,String rsmaritalstatus,String rsapplydate,String rslastupdate,String rslastcardissue,
                                         String rslastcardbatch,String rscardissue,String rsremarks,String rsbatchno,String rspqualifydate,String rspbatchno,
                                         String rstrackstatus,String rsmoddate,String rscreationdate,String rsloccode,String rsregno,String rsbonuspt,
                                         String rsegvamt,String rsegvconvdt,String rsegvmoddate,String rsreissuedate,String rsinstitcde,String rsmergedvipno,
                                         Timestamp rslastupddt,String rslastupdusr,String rslastupdver,Timestamp rspflastupddt,Timestamp rsqualifydate,Timestamp rsadhocdate,
                                         String rsordergroup,String rsbucde,String rsstaffid,String rshkregion,String rslcvipref,String rscurbonus,String rsbonusexpdt,
                                         String rslastbonuspt,String rstotredeem,String rslastredeem,String rslastredeemdt,String rslasttxdt,String rslastredeemitem,
                                         Timestamp rsqualifydttracking,Timestamp rsqualifydtgold,Timestamp rsqualifydtplatinum,String rstelno3,String rsfname,String rsperferlang,
                                         String rstel1flag,String rstel2flag,String rsremarks2,String rsrowguid,String rsextrabonuspt,Timestamp rsactivationdate,
                                         String rsrefereeno,String rspurgereason,String rsqualifyspending,String rsbonuspoint,Timestamp rsqualifydtdiamond,Timestamp rstierstartdt,
                                         Timestamp rstierenddt,String rsbclub,String rsentitykey,String frdatabase) throws SQLException {

        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        String insertTableSQL;

        insertTableSQL = "INSERT INTO pr_vip_mas (VIP_NO,SURNAME,FIRSTNAME,C_SURNAME,C_FIRSTNAME,TEL_NO1,TEL_NO2,CARD_TYPE,TITLE,ADDR1,ADDR2,ADDRX,CITY,STATE,COUNTRY_CODE,POSTAL_CODE,ID,SEX,EMAIL_ADDRESS,BIRTH_DATE,ANNV_DATE,SPOUSE_NAME,CONAME,DISABLE,DIS_EFF,DIS_REA,DISC_PER,FLAG1,FLAG2,FLAG3,FLAG4,FLAG5,PURCHASE_TY,PURCHASE_LY,PURCHASE_EVER,AGE_GP,INCOME_GP,OCCU_GP,MARITAL_STATUS,APPLY_DATE,LAST_UPDATE,LAST_CARD_ISSUE,LAST_CARD_BATCH,CARD_ISSUE,REMARKS,BATCH_NO,P_QUALIFY_DATE,P_BATCH_NO,TRACK_STATUS,MOD_DATE,CREATION_DATE,LOC_CODE,REG_NO,BONUS_PT,EGV_AMT,EGV_CONV_DT,EGVMOD_DATE,REISSUE_DATE,INSTIT_CDE,MERGED_VIP_NO,LAST_UPD_DT,LAST_UPD_USR,LAST_UPD_VER,PF_LAST_UPD_DT,QUALIFY_DATE,ADHOC_DATE,ORDER_GROUP,BU_CDE,STAFF_ID,HK_REGION,LC_VIP_REF,CUR_BONUS,BONUS_EXP_DT,LAST_BONUS_PT,TOT_REDEEM,LAST_REDEEM,LAST_REDEEM_DT,LAST_TX_DT,LAST_REDEEM_ITEM,QUALIFY_DT_TRACKING,QUALIFY_DT_GOLD,QUALIFY_DT_PLATINUM,TEL_NO3,F_NAME,PERFER_LANG,TEL1_FLAG,TEL2_FLAG,REMARKS2,ROWGUID,EXTRA_BONUS_PT,ACTIVATION_DATE,REFEREE_NO,PURGE_REASON,QUALIFY_SPENDING,BONUS_POINT,QUALIFY_DT_DIAMOND,TIER_START_DT,TIER_END_DT,BCLUB,ENTITY_KEY) "
                + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,? )";

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

            preparedStatement = dbConnection.prepareStatement(insertTableSQL);

            preparedStatement.setString(1, rsvipno);
            preparedStatement.setString(2, rssurname);
            preparedStatement.setString(3, rsfirstname);
            preparedStatement.setString(4, rscsurname);
            preparedStatement.setString(5, rscfirstname);
            preparedStatement.setString(6, rstelno1);
            preparedStatement.setString(7, rstelno2);
            preparedStatement.setString(8, rscardtype);
            preparedStatement.setString(9, rstitle);
            preparedStatement.setString(10, rsaddr1);
            preparedStatement.setString(11, rsaddr2);
            preparedStatement.setString(12, rsaddrx);
            preparedStatement.setString(13, rscity);
            preparedStatement.setString(14, rsstate);
            preparedStatement.setString(15, rscountrycode);
            preparedStatement.setString(16, rspostalcode);
            preparedStatement.setString(17, rsid);
            preparedStatement.setString(18, rssex);
            preparedStatement.setString(19, rsemailaddress);
            preparedStatement.setString(20, rsbirthdate);
            preparedStatement.setString(21, rsannvdate);
            preparedStatement.setString(22, rsspousename);
            preparedStatement.setString(23, rsconame);
            preparedStatement.setString(24, rsdisable);
            preparedStatement.setString(25, rsdiseff);
            preparedStatement.setString(26, rsdisrea);
            preparedStatement.setString(27, rsdiscper);
            preparedStatement.setString(28, rsflag1);
            preparedStatement.setString(29, rsflag2);
            preparedStatement.setString(30, rsflag3);
            preparedStatement.setString(31, rsflag4);
            preparedStatement.setString(32, rsflag5);
            preparedStatement.setString(33, rspurchasety);
            preparedStatement.setString(34, rspurchasely);
            preparedStatement.setString(35, rspurchaseever);
            preparedStatement.setString(36, rsagegp);
            preparedStatement.setString(37, rsincomegp);
            preparedStatement.setString(38, rsoccugp);
            preparedStatement.setString(39, rsmaritalstatus);
            preparedStatement.setString(40, rsapplydate);
            preparedStatement.setString(41, rslastupdate);
            preparedStatement.setString(42, rslastcardissue);
            preparedStatement.setString(43, rslastcardbatch);
            preparedStatement.setString(44, rscardissue);
            preparedStatement.setString(45, rsremarks);
            preparedStatement.setString(46, rsbatchno);
            preparedStatement.setString(47, rspqualifydate);
            preparedStatement.setString(48, rspbatchno);
            preparedStatement.setString(49, rstrackstatus);
            preparedStatement.setString(50, rsmoddate);
            preparedStatement.setString(51, rscreationdate);
            preparedStatement.setString(52, rsloccode);
            preparedStatement.setString(53, rsregno);
            preparedStatement.setString(54, rsbonuspt);
            preparedStatement.setString(55, rsegvamt);
            preparedStatement.setString(56, rsegvconvdt);
            preparedStatement.setString(57, rsegvmoddate);
            preparedStatement.setString(58, rsreissuedate);
            preparedStatement.setString(59, rsinstitcde);
            preparedStatement.setString(60, rsmergedvipno);
            preparedStatement.setTimestamp(61, rslastupddt);
            preparedStatement.setString(62, rslastupdusr);
            preparedStatement.setString(63, rslastupdver);
            preparedStatement.setTimestamp(64, rspflastupddt);
            preparedStatement.setTimestamp(65, rsqualifydate);
            preparedStatement.setTimestamp(66, rsadhocdate);
            preparedStatement.setString(67, rsordergroup);
            preparedStatement.setString(68, rsbucde);
            preparedStatement.setString(69, rsstaffid);
            preparedStatement.setString(70, rshkregion);
            preparedStatement.setString(71, rslcvipref);
            preparedStatement.setString(72, rscurbonus);
            preparedStatement.setString(73, rsbonusexpdt);
            preparedStatement.setString(74, rslastbonuspt);
            preparedStatement.setString(75, rstotredeem);
            preparedStatement.setString(76, rslastredeem);
            preparedStatement.setString(77, rslastredeemdt);
            preparedStatement.setString(78, rslasttxdt);
            preparedStatement.setString(79, rslastredeemitem);
            preparedStatement.setTimestamp(80, rsqualifydttracking);
            preparedStatement.setTimestamp(81, rsqualifydtgold);
            preparedStatement.setTimestamp(82, rsqualifydtplatinum);
            preparedStatement.setString(83, rstelno3);
            preparedStatement.setString(84, rsfname);
            preparedStatement.setString(85, rsperferlang);
            preparedStatement.setString(86, rstel1flag);
            preparedStatement.setString(87, rstel2flag);
            preparedStatement.setString(88, rsremarks2);
            preparedStatement.setString(89, rsrowguid);
            preparedStatement.setString(90, rsextrabonuspt);
            preparedStatement.setTimestamp(91, rsactivationdate);
            preparedStatement.setString(92, rsrefereeno);
            preparedStatement.setString(93, rspurgereason);
            preparedStatement.setString(94, rsqualifyspending);
            preparedStatement.setString(95, rsbonuspoint);
            preparedStatement.setTimestamp(96, rsqualifydtdiamond);
            preparedStatement.setTimestamp(97, rstierstartdt);
            preparedStatement.setTimestamp(98, rstierenddt);
            preparedStatement.setString(99, rsbclub);
            preparedStatement.setString(100, rsentitykey);

            preparedStatement.executeUpdate();

            return true;

        } catch (SQLException e) {

            logger.info(e.getMessage());

            return false;

        } finally {

            if (preparedStatement != null) {
                preparedStatement.close();
            }

            if (dbConnection != null) {
                dbConnection.close();
            }
        }

    }

    public static boolean Prvipmasupdate(String rsvipno,String rssurname,String rsfirstname,String rscsurname,String rscfirstname,
                                         String rstelno1,String rstelno2,String rscardtype,String rstitle,String rsaddr1,
                                         String rsaddr2,String rsaddrx,String rscity,String rsstate,String rscountrycode,
                                         String rspostalcode,String rsid,String rssex,String rsemailaddress,String rsbirthdate,
                                         String rsannvdate,String rsspousename,String rsconame,String rsdisable,String rsdiseff,
                                         String rsdisrea,String rsdiscper,String rsflag1,String rsflag2,String rsflag3,String rsflag4,
                                         String rsflag5,String rspurchasety,String rspurchasely,String rspurchaseever,String rsagegp,String rsincomegp,
                                         String rsoccugp,String rsmaritalstatus,String rsapplydate,String rslastupdate,String rslastcardissue,
                                         String rslastcardbatch,String rscardissue,String rsremarks,String rsbatchno,String rspqualifydate,String rspbatchno,
                                         String rstrackstatus,String rsmoddate,String rscreationdate,String rsloccode,String rsregno,String rsbonuspt,
                                         String rsegvamt,String rsegvconvdt,String rsegvmoddate,String rsreissuedate,String rsinstitcde,String rsmergedvipno,
                                         Timestamp rslastupddt,String rslastupdusr,String rslastupdver,Timestamp rspflastupddt,Timestamp rsqualifydate,Timestamp rsadhocdate,
                                         String rsordergroup,String rsbucde,String rsstaffid,String rshkregion,String rslcvipref,String rscurbonus,String rsbonusexpdt,
                                         String rslastbonuspt,String rstotredeem,String rslastredeem,String rslastredeemdt,String rslasttxdt,String rslastredeemitem,
                                         Timestamp rsqualifydttracking,Timestamp rsqualifydtgold,Timestamp rsqualifydtplatinum,String rstelno3,String rsfname,String rsperferlang,
                                         String rstel1flag,String rstel2flag,String rsremarks2,String rsrowguid,String rsextrabonuspt,Timestamp rsactivationdate,
                                         String rsrefereeno,String rspurgereason,String rsqualifyspending,String rsbonuspoint,Timestamp rsqualifydtdiamond,Timestamp rstierstartdt,
                                         Timestamp rstierenddt,String rsbclub,String rsentitykey,String frdatabase) throws SQLException {

        boolean result = true;
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        String updateSQL;

        updateSQL = "UPDATE PR_VIP_MAS "
                +"SET  ENTITY_KEY          = ? "
                +", SURNAME             = ? "
                +", FIRSTNAME           = ? "
                +", C_SURNAME           = ? "
                +", C_FIRSTNAME         = ? "
                +", TEL_NO1             = ? "
                +", TEL_NO2             = ? "
                +", CARD_TYPE           = ? "
                +", TITLE               = ? "
                +", ADDR1               = ? "
                +", ADDR2               = ? "
                +", ADDRX               = ? "
                +", CITY                = ? "
                +", STATE               = ? "
                +", COUNTRY_CODE        = ? "
                +", POSTAL_CODE         = ? "
                +", ID                  = ? "
                +", SEX                 = ? "
                +", EMAIL_ADDRESS       = ? "
                +", BIRTH_DATE          = ? "
                +", ANNV_DATE           = ? "
                +", SPOUSE_NAME         = ? "
                +", CONAME              = ? "
                +", DISABLE             = ? "
                +", DIS_EFF             = ? "
                +", DIS_REA             = ? "
                +", DISC_PER            = ? "
                +", FLAG1               = ? "
                +", FLAG2               = ? "
                +", FLAG3               = ? "
                +", FLAG4               = ? "
                +", FLAG5               = ? "
                +", PURCHASE_TY         = ? "
                +", PURCHASE_LY         = ? "
                +", PURCHASE_EVER       = ? "
                +", AGE_GP              = ? "
                +", INCOME_GP           = ? "
                +", OCCU_GP             = ? "
                +", MARITAL_STATUS      = ? "
                +", APPLY_DATE          = ? "
                +", LAST_UPDATE         = ? "
                +", LAST_CARD_ISSUE     = ? "
                +", LAST_CARD_BATCH     = ? "
                +", CARD_ISSUE          = ? "
                +", REMARKS             = ? "
                +", BATCH_NO            = ? "
                +", P_QUALIFY_DATE      = ? "
                +", P_BATCH_NO          = ? "
                +", TRACK_STATUS        = ? "
                +", MOD_DATE            = ? "
                +", CREATION_DATE       = ? "
                +", LOC_CODE            = ? "
                +", REG_NO              = ? "
                +", BONUS_PT            = ? "
                +", EGV_AMT             = ? "
                +", EGV_CONV_DT         = ? "
                +", EGVMOD_DATE         = ? "
                +", REISSUE_DATE        = ? "
                +", INSTIT_CDE          = ? "
                +", MERGED_VIP_NO       = ? "
                +", LAST_UPD_DT         = ? "
                +", LAST_UPD_USR        = ? "
                +", LAST_UPD_VER        = ? "
                +", PF_LAST_UPD_DT      = ? "
                +", QUALIFY_DATE        = ? "
                +", ADHOC_DATE          = ? "
                +", ORDER_GROUP         = ? "
                +", BU_CDE              = ? "
                +", STAFF_ID            = ? "
                +", HK_REGION           = ? "
                +", LC_VIP_REF          = ? "
                +", CUR_BONUS           = ? "
                +", BONUS_EXP_DT        = ? "
                +", LAST_BONUS_PT       = ? "
                +", TOT_REDEEM          = ? "
                +", LAST_REDEEM         = ? "
                +", LAST_REDEEM_DT      = ? "
                +", LAST_TX_DT          = ? "
                +", LAST_REDEEM_ITEM    = ? "
                +", QUALIFY_DT_TRACKING = ? "
                +", QUALIFY_DT_GOLD     = ? "
                +", QUALIFY_DT_PLATINUM = ? "
                +", TEL_NO3             = ? "
                +", F_NAME              = ? "
                +", PERFER_LANG         = ? "
                +", TEL1_FLAG           = ? "
                +", TEL2_FLAG           = ? "
                +", REMARKS2            = ? "
                +", ROWGUID             = ? "
                +", EXTRA_BONUS_PT      = ? "
                +", ACTIVATION_DATE     = ? "
                +", REFEREE_NO          = ? "
                +", PURGE_REASON        = ? "
                +", QUALIFY_SPENDING    = ? "
                +", BONUS_POINT         = ? "
                +", QUALIFY_DT_DIAMOND  = ? "
                +", TIER_START_DT       = ? "
                +", TIER_END_DT         = ? "
                +", BCLUB               = ? "
                +"WHERE VIP_NO          = ? ";


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

            preparedStatement = dbConnection.prepareStatement(updateSQL);

            preparedStatement.setString(1, rsentitykey);
            preparedStatement.setString(2, rssurname);
            preparedStatement.setString(3, rsfirstname);
            preparedStatement.setString(4, rscsurname);
            preparedStatement.setString(5, rscfirstname);
            preparedStatement.setString(6, rstelno1);
            preparedStatement.setString(7, rstelno2);
            preparedStatement.setString(8, rscardtype);
            preparedStatement.setString(9, rstitle);
            preparedStatement.setString(10, rsaddr1);
            preparedStatement.setString(11, rsaddr2);
            preparedStatement.setString(12, rsaddrx);
            preparedStatement.setString(13, rscity);
            preparedStatement.setString(14, rsstate);
            preparedStatement.setString(15, rscountrycode);
            preparedStatement.setString(16, rspostalcode);
            preparedStatement.setString(17, rsid);
            preparedStatement.setString(18, rssex);
            preparedStatement.setString(19, rsemailaddress);
            preparedStatement.setString(20, rsbirthdate);
            preparedStatement.setString(21, rsannvdate);
            preparedStatement.setString(22, rsspousename);
            preparedStatement.setString(23, rsconame);
            preparedStatement.setString(24, rsdisable);
            preparedStatement.setString(25, rsdiseff);
            preparedStatement.setString(26, rsdisrea);
            preparedStatement.setString(27, rsdiscper);
            preparedStatement.setString(28, rsflag1);
            preparedStatement.setString(29, rsflag2);
            preparedStatement.setString(30, rsflag3);
            preparedStatement.setString(31, rsflag4);
            preparedStatement.setString(32, rsflag5);
            preparedStatement.setString(33, rspurchasety);
            preparedStatement.setString(34, rspurchasely);
            preparedStatement.setString(35, rspurchaseever);
            preparedStatement.setString(36, rsagegp);
            preparedStatement.setString(37, rsincomegp);
            preparedStatement.setString(38, rsoccugp);
            preparedStatement.setString(39, rsmaritalstatus);
            preparedStatement.setString(40, rsapplydate);
            preparedStatement.setString(41, rslastupdate);
            preparedStatement.setString(42, rslastcardissue);
            preparedStatement.setString(43, rslastcardbatch);
            preparedStatement.setString(44, rscardissue);
            preparedStatement.setString(45, rsremarks);
            preparedStatement.setString(46, rsbatchno);
            preparedStatement.setString(47, rspqualifydate);
            preparedStatement.setString(48, rspbatchno);
            preparedStatement.setString(49, rstrackstatus);
            preparedStatement.setString(50, rsmoddate);
            preparedStatement.setString(51, rscreationdate);
            preparedStatement.setString(52, rsloccode);
            preparedStatement.setString(53, rsregno);
            preparedStatement.setString(54, rsbonuspt);
            preparedStatement.setString(55, rsegvamt);
            preparedStatement.setString(56, rsegvconvdt);
            preparedStatement.setString(57, rsegvmoddate);
            preparedStatement.setString(58, rsreissuedate);
            preparedStatement.setString(59, rsinstitcde);
            preparedStatement.setString(60, rsmergedvipno);
            preparedStatement.setTimestamp(61, rslastupddt);
            preparedStatement.setString(62, rslastupdusr);
            preparedStatement.setString(63, rslastupdver);
            preparedStatement.setTimestamp(64, rspflastupddt);
            preparedStatement.setTimestamp(65, rsqualifydate);
            preparedStatement.setTimestamp(66, rsadhocdate);
            preparedStatement.setString(67, rsordergroup);
            preparedStatement.setString(68, rsbucde);
            preparedStatement.setString(69, rsstaffid);
            preparedStatement.setString(70, rshkregion);
            preparedStatement.setString(71, rslcvipref);
            preparedStatement.setString(72, rscurbonus);
            preparedStatement.setString(73, rsbonusexpdt);
            preparedStatement.setString(74, rslastbonuspt);
            preparedStatement.setString(75, rstotredeem);
            preparedStatement.setString(76, rslastredeem);
            preparedStatement.setString(77, rslastredeemdt);
            preparedStatement.setString(78, rslasttxdt);
            preparedStatement.setString(79, rslastredeemitem);
            preparedStatement.setTimestamp(80, rsqualifydttracking);
            preparedStatement.setTimestamp(81, rsqualifydtgold);
            preparedStatement.setTimestamp(82, rsqualifydtplatinum);
            preparedStatement.setString(83, rstelno3);
            preparedStatement.setString(84, rsfname);
            preparedStatement.setString(85, rsperferlang);
            preparedStatement.setString(86, rstel1flag);
            preparedStatement.setString(87, rstel2flag);
            preparedStatement.setString(88, rsremarks2);
            preparedStatement.setString(89, rsrowguid);
            preparedStatement.setString(90, rsextrabonuspt);
            preparedStatement.setTimestamp(91, rsactivationdate);
            preparedStatement.setString(92, rsrefereeno);
            preparedStatement.setString(93, rspurgereason);
            preparedStatement.setString(94, rsqualifyspending);
            preparedStatement.setString(95, rsbonuspoint);
            preparedStatement.setTimestamp(96, rsqualifydtdiamond);
            preparedStatement.setTimestamp(97, rstierstartdt);
            preparedStatement.setTimestamp(98, rstierenddt);
            preparedStatement.setString(99, rsbclub);
            preparedStatement.setString(100, rsvipno);

            preparedStatement.executeUpdate();

            return result;

        } catch (SQLException e) {

            logger.info(e.getMessage());
            result = false;

            logger.info(false);

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

    public static boolean Prvipmaschkexists(String entitykey, String frdatabase)
            throws SQLException {
        /*
		 * String[] parts = entitykey.split(","); String txdate = parts[0];
		 * String loccode = parts[1]; String regno = parts[2]; String txno =
		 * parts[3]; String seqno = parts[4];
		 */
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

            String selectSQL = "SELECT VIP_NO " + "FROM pr_vip_mas "
                    + "where vip_no ='" + entitykey + "'";

            // logger.info(selectSQL);

            preparedStatement = dbConnection.prepareStatement(selectSQL);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String rschktxdate = rs.getString("vip_no");

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
