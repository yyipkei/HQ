package com.bridge.main;

import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.bridge.routesales.*;

public class RouteSales {

	private static final Logger logger = Logger.getLogger(RouteSales.class);

	public static void Routeing(String dataupdatelog, String entityname,
			String entitykey, String database) {

		/*
		 * System.out.println(dataupdatelog); System.out.println(entityname);
		 * System.out.println(entitykey); System.out.println(entityupddt);
		 * System.out.println(logdt); System.out.println(batchno);
		 * System.out.println(iscomp); System.out.println(Remark);
		 */

		if (entityname.equals("sahdr")) {
			try {
				Sahdr.routeSahdr(dataupdatelog, entityname, entitykey, database);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (entityname.equals("sadet")) {
			try {
				Sadet.routeSadet(dataupdatelog, entityname, entitykey, database);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (entityname.equals("satender")) {
			try {
				Satender.routeSatender(dataupdatelog, entityname, entitykey,
						database);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (entityname.equals("saitdisc")) {
			try {
				Saitdisc.routeSaitdisc(dataupdatelog, entityname, entitykey,
						database);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (entityname.equals("satxdisc")) {
			try {
				Satxdisc.routeSatxdisc(dataupdatelog, entityname, entitykey,
						database);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (entityname.equals("sastaff")) {
			try {
				Sastaff.routeSastaff(dataupdatelog, entityname, entitykey,
						database);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (entityname.equals("sastaffitem")) {
			try {
				Sastaffitem.routeSastaffitem(dataupdatelog, entityname,
						entitykey, database);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (entityname.equals("sareason")) {
			try {
				Sareason.routeSareason(dataupdatelog, entityname, entitykey,
						database);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (entityname.equals("sagwp")) {
			try {
				Sagwp.routeSagwp(dataupdatelog, entityname, entitykey, database);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (entityname.equals("sadesc")) {
			try {
				Sadesc.routeSadesc(dataupdatelog, entityname, entitykey,
						database);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (entityname.equals("sacard")) {
			try {
				Sacard.routeSacard(dataupdatelog, entityname, entitykey,
						database);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (entityname.equals("savwp")) {
			try {
				Savwp.routeSavwp(dataupdatelog, entityname, entitykey, database);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (entityname.equals("saserial")) {
			try {
				Saserial.routeSaserial(dataupdatelog, entityname, entitykey,
						database);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (entityname.equals("saonlineshop")) {
			try {
				Saonlineshop.routeSaonlineshop(dataupdatelog, entityname,
						entitykey, database);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (entityname.equals("sa_delivery")) {
			try {
				Sadelivery.routeSadelivery(dataupdatelog, entityname,
						entitykey, database);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (entityname.equals("sa_mr_item")) {
			try {
				Samritem.routeSamritem(dataupdatelog, entityname, entitykey,
						database);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (entityname.equals("sadisc_ref")) {
			try {
				Sadiscref.routeSadiscref(dataupdatelog, entityname, entitykey,
						database);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (entityname.equals("sagoa_action")) {
			try {
				Sagoaaction.routeSagoaaction(dataupdatelog, entityname,
						entitykey, database);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (entityname.equals("sagoa_det")) {
			try {
				Sagoadet.routeSagoadet(dataupdatelog, entityname, entitykey,
						database);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (entityname.equals("sagoa_hdr")) {
			try {
				Sagoahdr.routeSagoahdr(dataupdatelog, entityname, entitykey,
						database);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (entityname.equals("sagoa_staff")) {
			try {
				Sagoastaff.routeSagoastaff(dataupdatelog, entityname,
						entitykey, database);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (entityname.equals("edc_settlement")) {
			try {
				Edcsettlement.routeEdcsettlement(dataupdatelog, entityname,
						entitykey, database);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (entityname.equals("host_settlement")) {
			try {
				Hostsettlement.routeHostsettlement(dataupdatelog, entityname,
						entitykey, database);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		/*case "gr_org_det":
			try {
				Grorgdet.routeGrorgdet(dataupdatelog, entityname, entitykey,
						database);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "gr_org_tender":
			try {
				Grorgtender.routeGrorgtender(dataupdatelog, entityname,
						entitykey, database);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;*/
		} else if (entityname.equals("dphdr_desc")) {
			try {
				Dphdrdesc.routeDphdrdesc(dataupdatelog, entityname, entitykey,
						database);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (entityname.equals("pm_memo_map")) {
			try {
				Pmmemomap.routePmmemomap(dataupdatelog, entityname, entitykey,
						database);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        } else if (entityname.equals("dkt_prt_det")) {
            try {
                Dktprtdet.routeDktprtdet(dataupdatelog, entityname, entitykey,
                        database);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
		} else if (entityname.equals("discmas")) {
			try {
				SalesDiscmas.routeSalesDiscmas(dataupdatelog, entityname,
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
