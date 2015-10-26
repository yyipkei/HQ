package com.bridge.result;

import com.bridge.main.HikariQracleTo;
import com.bridge.projo.Dataupdatelog;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;

/**
 * Created by keyip on 3/08/2015.
 */
public class Insertdataupdatelog {

    private static final Logger logger = Logger.getLogger(Insertdataupdatelog.class);

    public static void Updatelogresult(String entityname, String entitykey) {

        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;

        String insertSQL;

        if(Objects.equals(entityname,"size")){
            entityname = "size_";
        }

        insertSQL ="INSERT INTO DATA_UPDATE_LOG (ENTITY_NAME,ENTITY_KEY,ENTITY_UPD_DT,LOG_DT,BATCH_NO, IS_COMP, REMARK) " +
                "values(?,?,systimestamp,systimestamp,?,0,'BRIDGE')";
        try {

            HikariQracleTo OrcaleFromTo = HikariQracleTo.getInstance();
            dbConnection = OrcaleFromTo.getConnection();
            preparedStatement = dbConnection.prepareStatement(insertSQL);

            preparedStatement.setString(1, entityname);
            preparedStatement.setString(2, entitykey);
            preparedStatement.setString(3, Dataupdatelog.getBatchno());

            preparedStatement.executeUpdate();

        }
        catch (SQLException e) {

            logger.info(e.getMessage());

        }finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            if (dbConnection != null) {
                try {
                    dbConnection.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }
}
