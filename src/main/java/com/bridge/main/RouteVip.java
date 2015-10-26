package com.bridge.main;

import org.apache.log4j.Logger;
import java.sql.SQLException;

import com.bridge.routevip.*;

 /**
 * Created by keyip on 11/09/2015.
 */
public class RouteVip {

    private static final Logger logger = Logger.getLogger(RouteVip.class);

    public static void Routeing(String dataupdatelog, String entityname,
                                String entitykey, String database) {

        if (entityname.equals("pr_vip_mas")) {
            try {
                Prvipmas.routePrvipmas(dataupdatelog, entityname, entitykey,
                        database);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else {
            logger.info("invalid entityname");

        }

    }

}

