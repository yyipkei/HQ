package com.bridge.main;

import com.bridge.routedeposit.*;
import org.apache.log4j.Logger;

import java.sql.SQLException;

public class RouteDeposit {

    private static final Logger logger = Logger.getLogger(RouteDeposit.class);

    public static void Routeing(String dataupdatelog, String entityname,
                                String entitykey, String database) {

        if (entityname.equals("dphdr")) {
            try {
                Dphdr.routeDphdr(dataupdatelog, entityname, entitykey, database);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        } else if (entityname.equals("dpdet")) {
            try {
                Dpdet.routeDpdet(dataupdatelog, entityname, entitykey, database);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        } else if (entityname.equals("dppayment")) {
            try {
                Dppayment.routeDppayment(dataupdatelog, entityname, entitykey,
                        database);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        } else if (entityname.equals("presell_dp_vip")) {
            try {
                Preselldpvip.routePreselldpvip(dataupdatelog, entityname,
                        entitykey, database);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        } else if (entityname.equals("presell_item")) {
            try {
                Presellitem.routePresellitem(dataupdatelog, entityname,
                        entitykey, database);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        } else if (entityname.equals("presell_item_status")) {
            try {
                Presellitemstatus.routePresellitemstatus(dataupdatelog,
                        entityname, entitykey, database);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        } else if (entityname.equals("br_gift")) {
            try {
                Brgift.routeBrgift(dataupdatelog, entityname, entitykey,
                        database);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        } else if (entityname.equals("br_guest")) {
            try {
                Brguest.routeBrguest(dataupdatelog, entityname, entitykey,
                        database);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        } else if (entityname.equals("br_reg")) {
            try {
                Brreg.routeBrreg(dataupdatelog, entityname, entitykey, database);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        } else if (entityname.equals("brdp_det")) {
            try {
                Brdpdet.routeBrdpdet(dataupdatelog, entityname, entitykey,
                        database);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        } else if (entityname.equals("brdp_hdr")) {
            try {
                Brdphdr.routeBrdphdr(dataupdatelog, entityname, entitykey,
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
