package com.bridge.main;

import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.bridge.routegoasetting.*;

public class RouteGoasetting {

	private static final Logger logger = Logger
			.getLogger(RouteGoasetting.class);

	public static void Routeing(String dataupdatelog, String entityname,
			String entitykey, String database) {

		if (entityname.equals("goa_dept")) {
			try {
				Goadept.routeGoadept(dataupdatelog, entityname, entitykey,
						database);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (entityname.equals("goa_purpose")) {
			try {
				Goapurpose.routeGoapurpose(dataupdatelog, entityname,
						entitykey, database);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (entityname.equals("goa_purpose_staff")) {
			try {
				Goapurposestaff.routeGoapurposestaff(dataupdatelog, entityname,
						entitykey, database);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (entityname.equals("goa_staff")) {
			try {
				Goastaff.routeGoastaff(dataupdatelog, entityname, entitykey,
						database);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (entityname.equals("goa_staff_limit_hdr")) {
			try {
				Goastafflimithdr.routeGoastafflimithdr(dataupdatelog,
						entityname, entitykey, database);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (entityname.equals("goa_tc")) {
			try {
				Goatc.routeGoatc(dataupdatelog, entityname, entitykey, database);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			logger.info("invalid entityname");

		}

	}

}
