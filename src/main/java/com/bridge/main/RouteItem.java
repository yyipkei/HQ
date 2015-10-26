package com.bridge.main;

import com.bridge.routeitem.*;
import com.bridge.routeitem.Class;
import org.apache.log4j.Logger;

import java.sql.SQLException;

public class RouteItem {

    private static final Logger logger = Logger.getLogger(RouteItem.class);

    public static void Routeing(String dataupdatelog, String entityname,
                                String entitykey, String database) {

		/*
         * System.out.println(dataupdatelog); System.out.println(entityname);
		 * System.out.println(entitykey); System.out.println(entityupddt);
		 * System.out.println(logdt); System.out.println(batchno);
		 * System.out.println(iscomp); System.out.println(Remark);
		 */

        if (entityname.equals("itemmas")) {
            try {
                Itemmas.routeItemmas(dataupdatelog, entityname, entitykey,
                        database);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        } else if (entityname.equals("presalemas")) {
            try {
                Presalemas.routePresalemas(dataupdatelog, entityname,
                        entitykey, database);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        } else if (entityname.equals("bu")) {
            try {
                Bu.routeBu(dataupdatelog, entityname, entitykey, database);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        } else if (entityname.equals("dept")) {
            try {
                Dept.routeDept(dataupdatelog, entityname, entitykey, database);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        } else if (entityname.equals("class")) {
            try {
                Class.routeClass(dataupdatelog, entityname, entitykey, database);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        } else if (entityname.equals("brand")) {
            try {
                Brand.routeBrand(dataupdatelog, entityname, entitykey, database);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        } else if (entityname.equals("color")) {
            try {
                Color.routeColor(dataupdatelog, entityname, entitykey, database);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        } else if (entityname.equals("size")) {
            try {
                Size.routeSize(dataupdatelog, entityname, entitykey, database);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        } else if (entityname.equals("mn_size_set")) {
            try {
                Mnsizeset.routeMnsizeset(dataupdatelog, entityname, entitykey,
                        database);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        } else if (entityname.equals("styl_mas_ele")) {
            try {
                Stylmasele.routeStylmasele(dataupdatelog, entityname,
                        entitykey, database);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        } else if (entityname.equals("styl_mas_service")) {
            try {
                Stylmasservice.routeStylmasservice(dataupdatelog, entityname,
                        entitykey, database);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        } else if (entityname.equals("mn_staffcard")) {
            try {
                Mnstaffcard.routeMnstaffcard(dataupdatelog, entityname,
                        entitykey, database);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        } else if (entityname.equals("mn_vendor_upc")) {
            try {
                Mnvendorupc.routeMnvendorupc(dataupdatelog, entityname,
                        entitykey, database);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        } else if (entityname.equals("mn_season")) {
            try {
                Mnseason.routeMnseason(dataupdatelog, entityname,
                        entitykey, database);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        } else if (entityname.equals("dummy_sku")) {
            try {
                Dummysku.routeDummysku(dataupdatelog, entityname,
                        entitykey, database);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else if (entityname.equals("tendmas")) {
            try {
                Tendmas.routeTendmas(dataupdatelog, entityname,
                        entitykey, database);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else if (entityname.equals("discmas")) {
            try {
                Discmas.routeDiscmas(dataupdatelog, entityname,
                        entitykey, database);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else if (entityname.equals("pm_promo_presales_discount")) {
            try {
                Pmprompresalesdiscount.routePmprompresalesdiscount(dataupdatelog, entityname,
                        entitykey, database);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else {
            logger.info("invalid entityname");

        }
    }

}
