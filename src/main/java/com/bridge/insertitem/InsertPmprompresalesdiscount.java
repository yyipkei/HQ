package com.bridge.insertitem;

import com.bridge.main.HikariQracleTo;
import com.bridge.main.HikariRms;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.Objects;

/**
 * Created by keyip on 7/08/2015.
 */
public class InsertPmprompresalesdiscount {
    private static final Logger logger = Logger.getLogger(InsertPmprompresalesdiscount.class);

    public static boolean Pmprompresalesdiscountinsert(String rsid,String rssku,String rsviptier,String rscardtype,String rspresalescode,String rsallowpromotion,Timestamp rseffectivefrom,
                                                       Timestamp rseffectiveto,String rsstatus,String rsdiscountedprice,Timestamp rslastupdatedate,String rsentitykey,
                                                       String frdatabase) throws SQLException {

        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        String insertTableSQL;

        insertTableSQL = "INSERT INTO PM_PROMO_PRESALES_DISCOUNT (ID,SKU,VIP_TIER,CARD_TYPE,PRESALES_CODE,ALLOW_PROMOTION,EFFECTIVE_FROM,EFFECTIVE_TO,STATUS,DISCOUNTED_PRICE,LAST_UPDATE_DATE,ENTITY_KEY) "
                + "VALUES ( ?,?,?,?,?,?,?,?,?,?,?,? )";

        try {

            if (Objects.equals(frdatabase, "Oracle")) {
                // dbConnection = Mssql.getDBConnection();

                HikariRms Rmspool = HikariRms.getInstance();
                dbConnection = Rmspool.getConnection();
            } else {
                // dbConnection = OracleTo.getDBConnection();

                HikariQracleTo OrcaleFromTo = HikariQracleTo.getInstance();
                dbConnection = OrcaleFromTo.getConnection();
            }

            preparedStatement = dbConnection.prepareStatement(insertTableSQL);

            preparedStatement.setString(1, rsid);
            preparedStatement.setString(2, rssku);
            preparedStatement.setString(3, rsviptier);
            preparedStatement.setString(4, rscardtype);
            preparedStatement.setString(5, rspresalescode);
            preparedStatement.setString(6, rsallowpromotion);
            preparedStatement.setTimestamp(7, rseffectivefrom);
            preparedStatement.setTimestamp(8, rseffectiveto);
            preparedStatement.setString(9, rsstatus);
            preparedStatement.setString(10, rsdiscountedprice);
            preparedStatement.setTimestamp(11, rslastupdatedate);
            preparedStatement.setString(12, rsentitykey);

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

    public static boolean Pmprompresalesdiscountupdate(String rsid,String rssku,String rsviptier,String rscardtype,String rspresalescode,String rsallowpromotion,Timestamp rseffectivefrom,
                                                       Timestamp rseffectiveto,String rsstatus,String rsdiscountedprice,Timestamp rslastupdatedate,String rsentitykey,
                                                       String frdatabase) throws SQLException {

        boolean result = true;
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        String updateSQL;

        updateSQL = "UPDATE PM_PROMO_PRESALES_DISCOUNT "
                +"SET  ID             = ? "
                +", SKU              = ? "
                +", VIP_TIER         = ? "
                +", CARD_TYPE        = ? "
                +", PRESALES_CODE    = ? "
                +", ALLOW_PROMOTION  = ? "
                +", EFFECTIVE_FROM   = ? "
                +", EFFECTIVE_TO     = ? "
                +", STATUS           = ? "
                +", DISCOUNTED_PRICE = ? "
                +", LAST_UPDATE_DATE = ? "
                +"WHERE ENTITY_KEY       = ? ";

        try {

            if (Objects.equals(frdatabase, "Oracle")) {
                // dbConnection = Mssql.getDBConnection();

                HikariRms Rmspool = HikariRms.getInstance();
                dbConnection = Rmspool.getConnection();
            } else {
                // dbConnection = OracleTo.getDBConnection();

                HikariQracleTo OrcaleFromTo = HikariQracleTo.getInstance();
                dbConnection = OrcaleFromTo.getConnection();
            }

            preparedStatement = dbConnection.prepareStatement(updateSQL);

            preparedStatement.setString(1, rsid);
            preparedStatement.setString(2, rssku);
            preparedStatement.setString(3, rsviptier);
            preparedStatement.setString(4, rscardtype);
            preparedStatement.setString(5, rspresalescode);
            preparedStatement.setString(6, rsallowpromotion);
            preparedStatement.setTimestamp(7, rseffectivefrom);
            preparedStatement.setTimestamp(8, rseffectiveto);
            preparedStatement.setString(9, rsstatus);
            preparedStatement.setString(10, rsdiscountedprice);
            preparedStatement.setTimestamp(11, rslastupdatedate);
            preparedStatement.setString(12, rsentitykey);

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

    public static boolean Pmprompresalesdiscountchkexists(String entitykey, String frdatabase)
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

                HikariRms Rmspool = HikariRms.getInstance();
                dbConnection = Rmspool.getConnection();
            } else {
                // dbConnection = OracleTo.getDBConnection();

                HikariQracleTo OrcaleFromTo = HikariQracleTo.getInstance();
                dbConnection = OrcaleFromTo.getConnection();
            }

            String selectSQL = "SELECT ID " + "FROM PM_PROMO_PRESALES_DISCOUNT "
                    + "where ENTITY_KEY ='" + entitykey + "'";

            // logger.info(selectSQL);

            preparedStatement = dbConnection.prepareStatement(selectSQL);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String rschktxdate = rs.getString("ID");

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
