package com.bridge.main;

import java.sql.SQLException;

import org.apache.log4j.Logger;
import com.bridge.routestockres.*;

public class RouteStock {

	private static final Logger logger = Logger.getLogger(RouteStock.class);

	public static void Routeing(String dataupdatelog, String entityname,
			String entitykey, String database) {

		/*
		 * System.out.println(dataupdatelog); System.out.println(entityname);
		 * System.out.println(entitykey); System.out.println(entityupddt);
		 * System.out.println(logdt); System.out.println(batchno);
		 * System.out.println(iscomp); System.out.println(Remark);
		 */

		if (entityname.equals("stock_res")) {
			try {
				Stockres.routeStockres(dataupdatelog, entityname, entitykey,
						database);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (entityname.equals("stockres_det")) {
			try {
				Stockresdet.routeStockresdet(dataupdatelog, entityname, entitykey,
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
