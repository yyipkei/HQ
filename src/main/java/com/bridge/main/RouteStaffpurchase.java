package com.bridge.main;

import java.sql.SQLException;

import org.apache.log4j.Logger;
import com.bridge.routestaffpurchase.*;

public class RouteStaffpurchase {

	private static final Logger logger = Logger
			.getLogger(RouteStaffpurchase.class);

	public static void Routeing(String dataupdatelog, String entityname,
			String entitykey, String database) {

		if (entityname.equals("staff_purchase")) {
			try {
				Staffpurchase.routeStaffpurchase(dataupdatelog, entityname,
						entitykey, database);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (entityname.equals("sa_staff_disc_dtl")) {
			try {
				Sastaffdiscdtl.routeSastaffdiscdtl(dataupdatelog, entityname,
						entitykey, database);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (entityname.equals("sa_staff_disc_except")) {
			try {
				Sastaffdiscexcept.routeSastaffdiscexcept(dataupdatelog,
						entityname, entitykey, database);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (entityname.equals("sa_staff_disc_hdr")) {
			try {
				Sastaffdischdr.routeSastaffdischdr(dataupdatelog,
						entityname, entitykey, database);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (entityname.equals("staff_purchase_cl")) {
			try {
				Staffpurchasecl.routeStaffpurchasecl(dataupdatelog,
						entityname, entitykey, database);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (entityname.equals("sa_staff_disc_dtl_cl")) {
			try {
				Sastaffdiscdtlcl.routeSastaffdiscdtlcl(dataupdatelog,
						entityname, entitykey, database);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (entityname.equals("sa_staff_disc_except_cl")) {
			try {
				Sastaffdiscexceptcl.routeSastaffdiscexceptcl(dataupdatelog,
						entityname, entitykey, database);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (entityname.equals("sa_staff_disc_hdr_cl")) {
			try {
				Sastaffdischdrcl.routeSastaffdischdrcl(dataupdatelog,
						entityname, entitykey, database);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			logger.info("invalid entityname");

		}

	}

}
