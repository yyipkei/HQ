package com.bridge.main;

import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.bridge.routegoodsreturn.*;

public class RouteGoodsreturn {

	private static final Logger logger = Logger
			.getLogger(RouteGoodsreturn.class);

	public static void Routeing(String dataupdatelog, String entityname,
			String entitykey, String database) {

		if (entityname.equals("gr_org_det")) {
			try {
				Grorgdet.routeGrorgdet(dataupdatelog, entityname,
						entitykey, database);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (entityname.equals("gr_org_tender")) {
			try {
				Grorgtender.routeGrorgtender(dataupdatelog, entityname,
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
