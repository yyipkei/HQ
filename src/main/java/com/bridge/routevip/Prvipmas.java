package com.bridge.routevip;

import com.bridge.insertvip.InsertPrvipmas;
import com.bridge.main.HikariMssql;
import com.bridge.main.HikariQracleFrom;
import com.bridge.result.Insertdataupdatelog;
import com.bridge.result.Logupdateresult;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.Objects;

/**
 * Created by keyip on 11/09/2015.
 */
public class Prvipmas {
    private static final Logger logger = Logger.getLogger(Prvipmas.class);

    public static void routePrvipmas(String dataupdatelog, String entityname,
                                  String entitykey, String database) throws SQLException {
		/*
		 * String[] parts = entitykey.split(","); String txdate = parts[0];
		 * String loccode = parts[1]; String regno = parts[2]; String txno =
		 * parts[3]; String seqno = parts[4];
		 */
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        String selectSQL;

        selectSQL = "SELECT VIP_NO,SURNAME,FIRSTNAME,C_SURNAME,C_FIRSTNAME,TEL_NO1,TEL_NO2,CARD_TYPE,TITLE,ADDR1,ADDR2,ADDRX,CITY,STATE,COUNTRY_CODE,POSTAL_CODE,ID,SEX," +
                "EMAIL_ADDRESS,BIRTH_DATE,ANNV_DATE,SPOUSE_NAME,CONAME,DISABLE,DIS_EFF,DIS_REA,DISC_PER,FLAG1,FLAG2,FLAG3,FLAG4,FLAG5,PURCHASE_TY,PURCHASE_LY,PURCHASE_EVER," +
                "AGE_GP,INCOME_GP,OCCU_GP,MARITAL_STATUS,APPLY_DATE,LAST_UPDATE,LAST_CARD_ISSUE,LAST_CARD_BATCH,CARD_ISSUE,REMARKS,BATCH_NO,P_QUALIFY_DATE,P_BATCH_NO," +
                "TRACK_STATUS,MOD_DATE,CREATION_DATE,LOC_CODE,REG_NO,BONUS_PT,EGV_AMT,EGV_CONV_DT,EGVMOD_DATE,REISSUE_DATE,INSTIT_CDE,MERGED_VIP_NO,LAST_UPD_DT,LAST_UPD_USR," +
                "LAST_UPD_VER,PF_LAST_UPD_DT,QUALIFY_DATE,ADHOC_DATE,ORDER_GROUP,BU_CDE,STAFF_ID,HK_REGION,LC_VIP_REF,CUR_BONUS,BONUS_EXP_DT,LAST_BONUS_PT,TOT_REDEEM," +
                "LAST_REDEEM,LAST_REDEEM_DT,LAST_TX_DT,LAST_REDEEM_ITEM,QUALIFY_DT_TRACKING,QUALIFY_DT_GOLD,QUALIFY_DT_PLATINUM,TEL_NO3,F_NAME,PERFER_LANG,TEL1_FLAG," +
                "TEL2_FLAG,REMARKS2,ROWGUID,EXTRA_BONUS_PT,ACTIVATION_DATE,REFEREE_NO,PURGE_REASON,QUALIFY_SPENDING,BONUS_POINT,QUALIFY_DT_DIAMOND,TIER_START_DT," +
                "TIER_END_DT,BCLUB,ENTITY_KEY "
                + "FROM pr_vip_mas " + "where VIP_NO ='" + entitykey + "'"
                +"Order BY LAST_UPD_DT";

        // List<Sahdr> sahdrs = new ArrayList<Sahdr>();

        try {
            if (Objects.equals(database, "Oracle")) {
                // dbConnection = OracleFrom.getDBConnection();

                HikariQracleFrom OrcaleFrompool = HikariQracleFrom
                        .getInstance();
                dbConnection = OrcaleFrompool.getConnection();
            } else {
                // dbConnection = Mssql.getDBConnection();

                HikariMssql Mssqlpool = HikariMssql.getInstance();
                dbConnection = Mssqlpool.getConnection();
            }

            preparedStatement = dbConnection.prepareStatement(selectSQL);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {

                String rsvipno = rs.getString("VIP_NO");
                String rssurname = rs.getString("SURNAME");
                String rsfirstname = rs.getString("FIRSTNAME");
                String rscsurname = rs.getString("C_SURNAME");
                String rscfirstname = rs.getString("C_FIRSTNAME");
                String rstelno1 = rs.getString("TEL_NO1");
                String rstelno2 = rs.getString("TEL_NO2");
                String rscardtype = rs.getString("CARD_TYPE");
                String rstitle = rs.getString("TITLE");
                String rsaddr1 = rs.getString("ADDR1");
                String rsaddr2 = rs.getString("ADDR2");
                String rsaddrx = rs.getString("ADDRX");
                String rscity = rs.getString("CITY");
                String rsstate = rs.getString("STATE");
                String rscountrycode = rs.getString("COUNTRY_CODE");
                String rspostalcode = rs.getString("POSTAL_CODE");
                String rsid = rs.getString("ID");
                String rssex = rs.getString("SEX");
                String rsemailaddress = rs.getString("EMAIL_ADDRESS");
                String rsbirthdate = rs.getString("BIRTH_DATE");
                String rsannvdate = rs.getString("ANNV_DATE");
                String rsspousename = rs.getString("SPOUSE_NAME");
                String rsconame = rs.getString("CONAME");
                String rsdisable = rs.getString("DISABLE");
                String rsdiseff = rs.getString("DIS_EFF");
                String rsdisrea = rs.getString("DIS_REA");
                String rsdiscper = rs.getString("DISC_PER");
                String rsflag1 = rs.getString("FLAG1");
                String rsflag2 = rs.getString("FLAG2");
                String rsflag3 = rs.getString("FLAG3");
                String rsflag4 = rs.getString("FLAG4");
                String rsflag5 = rs.getString("FLAG5");
                String rspurchasety = rs.getString("PURCHASE_TY");
                String rspurchasely = rs.getString("PURCHASE_LY");
                String rspurchaseever = rs.getString("PURCHASE_EVER");
                String rsagegp = rs.getString("AGE_GP");
                String rsincomegp = rs.getString("INCOME_GP");
                String rsoccugp = rs.getString("OCCU_GP");
                String rsmaritalstatus = rs.getString("MARITAL_STATUS");
                String rsapplydate = rs.getString("APPLY_DATE");
                String rslastupdate = rs.getString("LAST_UPDATE");
                String rslastcardissue = rs.getString("LAST_CARD_ISSUE");
                String rslastcardbatch = rs.getString("LAST_CARD_BATCH");
                String rscardissue = rs.getString("CARD_ISSUE");
                String rsremarks = rs.getString("REMARKS");
                String rsbatchno = rs.getString("BATCH_NO");
                String rspqualifydate = rs.getString("P_QUALIFY_DATE");
                String rspbatchno = rs.getString("P_BATCH_NO");
                String rstrackstatus = rs.getString("TRACK_STATUS");
                String rsmoddate = rs.getString("MOD_DATE");
                String rscreationdate = rs.getString("CREATION_DATE");
                String rsloccode = rs.getString("LOC_CODE");
                String rsregno = rs.getString("REG_NO");
                String rsbonuspt = rs.getString("BONUS_PT");
                String rsegvamt = rs.getString("EGV_AMT");
                String rsegvconvdt = rs.getString("EGV_CONV_DT");
                String rsegvmoddate = rs.getString("EGVMOD_DATE");
                String rsreissuedate = rs.getString("REISSUE_DATE");
                String rsinstitcde = rs.getString("INSTIT_CDE");
                String rsmergedvipno = rs.getString("MERGED_VIP_NO");
                Timestamp rslastupddt = rs.getTimestamp("LAST_UPD_DT");
                String rslastupdusr = rs.getString("LAST_UPD_USR");
                String rslastupdver = rs.getString("LAST_UPD_VER");
                Timestamp rspflastupddt = rs.getTimestamp("PF_LAST_UPD_DT");
                Timestamp rsqualifydate = rs.getTimestamp("QUALIFY_DATE");
                Timestamp rsadhocdate = rs.getTimestamp("ADHOC_DATE");
                String rsordergroup = rs.getString("ORDER_GROUP");
                String rsbucde = rs.getString("BU_CDE");
                String rsstaffid = rs.getString("STAFF_ID");
                String rshkregion = rs.getString("HK_REGION");
                String rslcvipref = rs.getString("LC_VIP_REF");
                String rscurbonus = rs.getString("CUR_BONUS");
                String rsbonusexpdt = rs.getString("BONUS_EXP_DT");
                String rslastbonuspt = rs.getString("LAST_BONUS_PT");
                String rstotredeem = rs.getString("TOT_REDEEM");
                String rslastredeem = rs.getString("LAST_REDEEM");
                String rslastredeemdt = rs.getString("LAST_REDEEM_DT");
                String rslasttxdt = rs.getString("LAST_TX_DT");
                String rslastredeemitem = rs.getString("LAST_REDEEM_ITEM");
                Timestamp rsqualifydttracking = rs.getTimestamp("QUALIFY_DT_TRACKING");
                Timestamp rsqualifydtgold = rs.getTimestamp("QUALIFY_DT_GOLD");
                Timestamp rsqualifydtplatinum = rs.getTimestamp("QUALIFY_DT_PLATINUM");
                String rstelno3 = rs.getString("TEL_NO3");
                String rsfname = rs.getString("F_NAME");
                String rsperferlang = rs.getString("PERFER_LANG");
                String rstel1flag = rs.getString("TEL1_FLAG");
                String rstel2flag = rs.getString("TEL2_FLAG");
                String rsremarks2 = rs.getString("REMARKS2");
                String rsrowguid = rs.getString("ROWGUID");
                String rsextrabonuspt = rs.getString("EXTRA_BONUS_PT");
                Timestamp rsactivationdate = rs.getTimestamp("ACTIVATION_DATE");
                String rsrefereeno = rs.getString("REFEREE_NO");
                String rspurgereason = rs.getString("PURGE_REASON");
                String rsqualifyspending = rs.getString("QUALIFY_SPENDING");
                String rsbonuspoint = rs.getString("BONUS_POINT");
                Timestamp rsqualifydtdiamond = rs.getTimestamp("QUALIFY_DT_DIAMOND");
                Timestamp rstierstartdt = rs.getTimestamp("TIER_START_DT");
                Timestamp rstierenddt = rs.getTimestamp("TIER_END_DT");
                String rsbclub = rs.getString("BCLUB");
                String rsentitykey = rs.getString("ENTITY_KEY");

                // logger.info(rslastupddt);

                boolean Chkresult = InsertPrvipmas.Prvipmaschkexists(entitykey,
                        database);

                if (!Chkresult) {

                    // logger.info("insert");

                    boolean Insertresult = InsertPrvipmas.Prvipmasinsert(rsvipno,rssurname,rsfirstname,rscsurname,rscfirstname,
                            rstelno1,rstelno2,rscardtype,rstitle,rsaddr1,rsaddr2,rsaddrx,rscity,rsstate,rscountrycode,rspostalcode,
                            rsid,rssex,rsemailaddress,rsbirthdate,rsannvdate,rsspousename,rsconame,rsdisable,rsdiseff,rsdisrea,
                            rsdiscper,rsflag1,rsflag2,rsflag3,rsflag4,rsflag5,rspurchasety,rspurchasely,rspurchaseever,rsagegp,
                            rsincomegp,rsoccugp,rsmaritalstatus,rsapplydate,rslastupdate,rslastcardissue,rslastcardbatch,rscardissue,
                            rsremarks,rsbatchno,rspqualifydate,rspbatchno,rstrackstatus,rsmoddate,rscreationdate,rsloccode,rsregno,
                            rsbonuspt,rsegvamt,rsegvconvdt,rsegvmoddate,rsreissuedate,rsinstitcde,rsmergedvipno,rslastupddt,
                            rslastupdusr,rslastupdver,rspflastupddt,rsqualifydate,rsadhocdate,rsordergroup,rsbucde,rsstaffid,
                            rshkregion,rslcvipref,rscurbonus,rsbonusexpdt,rslastbonuspt,rstotredeem,rslastredeem,rslastredeemdt,
                            rslasttxdt,rslastredeemitem,rsqualifydttracking,rsqualifydtgold,rsqualifydtplatinum,rstelno3,rsfname,
                            rsperferlang,rstel1flag,rstel2flag,rsremarks2,rsrowguid,rsextrabonuspt,rsactivationdate,rsrefereeno,
                            rspurgereason,rsqualifyspending,rsbonuspoint,rsqualifydtdiamond,rstierstartdt,rstierenddt,rsbclub,
                            rsentitykey, database);

                    if (Insertresult) {
                        logger.info("Prvipmas: 1 row has been inserted. Key:"
                                + entitykey);
                    } else {
                        logger.info("Insert Error");
                    }
                    Logupdateresult.Updatelogresult(dataupdatelog, entityname,
                            Insertresult, database);

                    if ((!"Oracle".equals(database)) && (Insertresult)) {
                        Insertdataupdatelog.Updatelogresult(entityname, rsentitykey);
                    }

                } else {

                    // logger.info("update");

                    boolean Insertresult = InsertPrvipmas.Prvipmasupdate(rsvipno,rssurname,rsfirstname,rscsurname,rscfirstname,
                            rstelno1,rstelno2,rscardtype,rstitle,rsaddr1,rsaddr2,rsaddrx,rscity,rsstate,rscountrycode,rspostalcode,
                            rsid,rssex,rsemailaddress,rsbirthdate,rsannvdate,rsspousename,rsconame,rsdisable,rsdiseff,rsdisrea,
                            rsdiscper,rsflag1,rsflag2,rsflag3,rsflag4,rsflag5,rspurchasety,rspurchasely,rspurchaseever,rsagegp,
                            rsincomegp,rsoccugp,rsmaritalstatus,rsapplydate,rslastupdate,rslastcardissue,rslastcardbatch,rscardissue,
                            rsremarks,rsbatchno,rspqualifydate,rspbatchno,rstrackstatus,rsmoddate,rscreationdate,rsloccode,rsregno,
                            rsbonuspt,rsegvamt,rsegvconvdt,rsegvmoddate,rsreissuedate,rsinstitcde,rsmergedvipno,rslastupddt,
                            rslastupdusr,rslastupdver,rspflastupddt,rsqualifydate,rsadhocdate,rsordergroup,rsbucde,rsstaffid,
                            rshkregion,rslcvipref,rscurbonus,rsbonusexpdt,rslastbonuspt,rstotredeem,rslastredeem,rslastredeemdt,
                            rslasttxdt,rslastredeemitem,rsqualifydttracking,rsqualifydtgold,rsqualifydtplatinum,rstelno3,rsfname,
                            rsperferlang,rstel1flag,rstel2flag,rsremarks2,rsrowguid,rsextrabonuspt,rsactivationdate,rsrefereeno,
                            rspurgereason,rsqualifyspending,rsbonuspoint,rsqualifydtdiamond,rstierstartdt,rstierenddt,rsbclub,
                            rsentitykey, database);

                    if (Insertresult) {
                        logger.info("Prvipmas: 1 row has been updated. Key:"
                                + entitykey);
                    } else {
                        logger.info("Update Error");
                    }
                    Logupdateresult.Updatelogresult(dataupdatelog, entityname,
                            Insertresult, database);

                    if ((!"Oracle".equals(database)) && (Insertresult)) {
                        Insertdataupdatelog.Updatelogresult(entityname, rsentitykey);
                    }
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
