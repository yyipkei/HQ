package com.bridge.routeitem;

import com.bridge.insertitem.InsertTendmas;
import com.bridge.main.HikariMssql;
import com.bridge.main.HikariQracleFrom;
import com.bridge.main.HikariRms;
import com.bridge.result.Insertdataupdatelog;
import com.bridge.result.Logupdateresult;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.Objects;

/**
 * Created by keyip on 17-Jun-15.
 */
public class Tendmas {

    private static final Logger logger = Logger.getLogger(Tendmas.class);

    public static void routeTendmas(String dataupdatelog, String entityname,
                                    String entitykey, String database) throws SQLException {
        /*
         * String[] parts = entitykey.split(","); String txdate = parts[0];
		 * String loccode = parts[1]; String regno = parts[2]; String txno =
		 * parts[3]; String seqno = parts[4];
		 */
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        String selectSQL;

        selectSQL = "SELECT TENDER,TENDER_NAME,CARD_PREFIX,TENDER_GROUP,IFX_REF,IS_MANUAL,BANK_HOST,COMM_RATE,MASK,MASK_ENABLED,DBMASK," +
                "DBMASK_ENABLED,EXPIRY_MASK,EXPIRY_MASK_ENABLED,MANUAL_ENTRY,LAST_UPD_DT,ENTITY_KEY "
                + "FROM tendmas " + "where entity_key ='" + entitykey + "'"
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

				HikariRms Rmspool = HikariRms.getInstance();
				dbConnection = Rmspool.getConnection();
            }

            preparedStatement = dbConnection.prepareStatement(selectSQL);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {

                String rstender = rs.getString("TENDER");
                String rstendername = rs.getString("TENDER_NAME");
                String rscardprefix = rs.getString("CARD_PREFIX");
                String rstendergroup = rs.getString("TENDER_GROUP");
                String rsifxref = rs.getString("IFX_REF");
                String rsismanual = rs.getString("IS_MANUAL");
                String rsbankhost = rs.getString("BANK_HOST");
                String rscommrate = rs.getString("COMM_RATE");
                String rsmask = rs.getString("MASK");
                String rsmaskenabled = rs.getString("MASK_ENABLED");
                String rsdbmask = rs.getString("DBMASK");
                String rsdbmaskenabled = rs.getString("DBMASK_ENABLED");
                String rsexpirymask = rs.getString("EXPIRY_MASK");
                String rsexpirymaskenabled = rs.getString("EXPIRY_MASK_ENABLED");
                String rsmanualentry = rs.getString("MANUAL_ENTRY");
                Timestamp rslastupddt = rs.getTimestamp("LAST_UPD_DT");
                String rsentitykey = rs.getString("ENTITY_KEY");


                // logger.info(rslastupddt);

                boolean Chkresult = InsertTendmas.Tendmaschkexists(entitykey,
                        database);

                if (!Chkresult) {

                    // logger.info("insert");

                    boolean Insertresult = InsertTendmas.Tendmasinsert(rstender, rstendername, rscardprefix, rstendergroup,
                            rsifxref, rsismanual, rsbankhost, rscommrate, rsmask, rsmaskenabled, rsdbmask, rsdbmaskenabled,
                            rsexpirymask, rsexpirymaskenabled, rsmanualentry, rslastupddt, rsentitykey, database);

                    if (Insertresult) {
                        logger.info("Tendmas: 1 row has been inserted. Key:"
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

                    boolean Insertresult = InsertTendmas.Tendmasupdate(rstender, rstendername, rscardprefix, rstendergroup,
                            rsifxref, rsismanual, rsbankhost, rscommrate, rsmask, rsmaskenabled, rsdbmask, rsdbmaskenabled,
                            rsexpirymask, rsexpirymaskenabled, rsmanualentry, rslastupddt, rsentitykey, database);

                    if (Insertresult) {
                        logger.info("Tendmas: 1 row has been updated. Key:"
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
