package com.bridge.main;

import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.bridge.routemerge.*;

public class RouteMerge {

	private static final Logger logger = Logger.getLogger(RouteMerge.class);

	public static void Routeing(String dataupdatelog, String entityname,
			String entitykey, String database) {

		if (entityname.equals("cafe_coupon_det")) {
			try {
				Cafecoupondet.routeCafecoupondet(dataupdatelog, entityname,
						entitykey, database);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (entityname.equals("cafe_coupon_hdr")) {
			try {
				Cafecouponhdr.routeCafecouponhdr(dataupdatelog, entityname,
						entitykey, database);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (entityname.equals("cos_egc_det")) {
			try {
				Cosegcdet.routeCosegcdet(dataupdatelog, entityname, entitykey,
						database);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (entityname.equals("cos_egc_hdr")) {
			try {
				Cosegchdr.routeCosegchdr(dataupdatelog, entityname, entitykey,
						database);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (entityname.equals("xrate_override_user")) {
			try {
				Xrateoverrideuser.routeXrateoverrideuser(dataupdatelog,
						entityname, entitykey, database);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (entityname.equals("xratemas")) {
			try {
				Xratemas.routeXratemas(dataupdatelog, entityname, entitykey,
						database);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (entityname.equals("park_pmt")) {
			try {
				Parkpmt.routeParkpmt(dataupdatelog, entityname, entitykey,
						database);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (entityname.equals("vwp_dtl")) {
			try {
				Vwpdtl.routeVwpdtl(dataupdatelog, entityname, entitykey,
						database);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (entityname.equals("pos_user")) {
			try {
				Posuser.routePosuser(dataupdatelog, entityname, entitykey,
						database);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (entityname.equals("reason")) {
			try {
				Reason.routeReason(dataupdatelog, entityname, entitykey,
						database);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (entityname.equals("sastafftxn")) {
			try {
				Sastafftxn.routeSastafftxn(dataupdatelog, entityname,
						entitykey, database);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (entityname.equals("sastafftxn_cl")) {
			try {
				Sastafftxncl.routeSastafftxncl(dataupdatelog, entityname,
						entitykey, database);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (entityname.equals("sagoa_txn")) {
			try {
				Sagoatxn.routeSagoatxn(dataupdatelog, entityname,
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
