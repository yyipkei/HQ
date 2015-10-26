package com.bridge.main;

import com.bridge.routestockonhand.*;
import org.apache.log4j.Logger;

import java.sql.SQLException;

public class RouteStockonhand {

	private static final Logger logger = Logger
			.getLogger(RouteStockonhand.class);

	public static void Routeing(String dataupdatelog, String entityname,
			String entitykey, String database) {

		/*
		 * System.out.println(dataupdatelog); System.out.println(entityname);
		 * System.out.println(entitykey); System.out.println(entityupddt);
		 * System.out.println(logdt); System.out.println(batchno);
		 * System.out.println(iscomp); System.out.println(Remark);
		 */

		if (entityname.equals("stk_ledger_by_day_pos")) {
			try {
				Stkledgerbydaypos.routeStkledgerbydaypos(dataupdatelog,
						entityname, entitykey, database);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (entityname.equals("inv_wri_off_hdr_pos")) {
			try {
				Invwrioffhdrpos.routeInvwrioffhdrpos(dataupdatelog, entityname,
						entitykey, database);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (entityname.equals("inv_wri_off_dtl_pos")) {
			try {
				Invwrioffdtlpos.routeInvwrioffdtlpos(dataupdatelog, entityname,
						entitykey, database);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (entityname.equals("sx_box_pos")) {
			try {
				Sxboxpos.routeSxboxpos(dataupdatelog, entityname, entitykey,
						database);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (entityname.equals("sx_item_pos")) {
			try {
				Sxitempos.routeSxitempos(dataupdatelog, entityname, entitykey,
						database);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (entityname.equals("sxs_req_hdr_pos")) {
			try {
				Sxsreqhdrpos.routeSxsreqhdrpos(dataupdatelog, entityname,
						entitykey, database);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (entityname.equals("sxs_req_dtl_pos")) {
			try {
				Sxsreqdtlpos.routeSxsreqdtlpos(dataupdatelog, entityname,
						entitykey, database);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (entityname.equals("stk_ledger_trn_pos")) {
			try {
				Stkledgertrnpos.routeStkledgertrnpos(dataupdatelog, entityname,
						entitykey, database);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (entityname.equals("stock_reservation_pos")) {
			try {
				Stockreservationpos.routeStockreservationpos(dataupdatelog,
						entityname, entitykey, database);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (entityname.equals("stk_holding_trn")) {
			try {
				Stkholdingtrn.routeStkholdingtrn(dataupdatelog, entityname,
						entitykey, database);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (entityname.equals("stk_ledger_pos_summary_pos")) {
			try {
				Stkledgerpossummarypos.routeStkledgerpossummarypos(dataupdatelog, entityname,
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
