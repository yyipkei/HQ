package com.bridge.routemerge;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Objects;

import com.bridge.result.Insertdataupdatelog;
import org.apache.log4j.Logger;

import com.bridge.insertmerge.InsertParkpmt;
import com.bridge.main.HikariMerge;
import com.bridge.main.HikariQracleFrom;
import com.bridge.result.Logupdateresult;

public class Parkpmt {

	private static final Logger logger = Logger.getLogger(Parkpmt.class);

	public static void routeParkpmt(String dataupdatelog, String entityname,
			String entitykey, String database) throws SQLException {

		String[] parts = entitykey.split(",");
		String seqno = parts[0];
		String loccode = parts[1];
		String date = parts[2];
		String time = parts[3];

		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		String selectSQL;

		// List<Parkpmt> Parkpmts = new ArrayList<Parkpmt>();

		try {
			if (Objects.equals(database, "Oracle")) {
				// dbConnection = OracleFrom.getDBConnection();

				HikariQracleFrom OrcaleFrompool = HikariQracleFrom
						.getInstance();
				dbConnection = OrcaleFrompool.getConnection();
				selectSQL = "SELECT SEQ_NO,LOC_CODE,DEPT_CODE,DATE_,TIME,PERMIT1_NO"
						+ ",PERMIT2_NO,TX_REG_NO_1,TX_NO_1,TX_DEPT_CODE_1,TX_SALE_1,TX_REG_NO_2"
						+ ",TX_NO_2,TX_DEPT_CODE_2,TX_SALE_2,TX_REG_NO_3,TX_NO_3,TX_DEPT_CODE_3"
						+ ",TX_SALE_3,TX_REG_NO_4,TX_NO_4,TX_DEPT_CODE_4,TX_SALE_4,TX_REG_NO_5"
						+ ",TX_NO_5,TX_DEPT_CODE_5,TX_SALE_5,TX_REG_NO_6,TX_NO_6,TX_DEPT_CODE_6"
						+ ",TX_SALE_6,TX_REG_NO_7,TX_NO_7,TX_DEPT_CODE_7,TX_SALE_7,TX_REG_NO_8"
						+ ",TX_NO_8,TX_DEPT_CODE_8,TX_SALE_8,TX_REG_NO_9,TX_NO_9,TX_DEPT_CODE_9"
						+ ",TX_SALE_9,TX_REG_NO_10,TX_NO_10,TX_DEPT_CODE_10,TX_SALE_10,"
						+ "TX_SALE,VIP_NO,VIP_CARD_TYPE,VIP_SALE,ISSUED_BY,APPROVED_BY,VIP_APPROVED_BY,MODIFIED_BY,"
						+ "MODIFIED_DATE,MSREPL_SYNCTRAN_TS,PERMIT3_NO,PERMIT4_NO,PERMIT5_NO,ISSUE_YEAR,SP_VIP,PURPOSE,"
						+ "ROWGUID,TX_LOC_CODE_1,TX_LOC_CODE_2,TX_LOC_CODE_3,TX_LOC_CODE_4,TX_LOC_CODE_5,TX_LOC_CODE_6,"
						+ "TX_LOC_CODE_7,TX_LOC_CODE_8,TX_LOC_CODE_9,TX_LOC_CODE_10,LAST_UPD_DT "
						+ "from PARK_PMT "
						+ "where SEQ_NO ='"
						+ seqno
						+ "'"
						+ "and LOC_CODE ='"
						+ loccode
						+ "'"
						+ "and DATE_='"
						+ date + "'" + "and TIME ='" + time + "'"
						+"Order BY LAST_UPD_DT";
			} else {
				// dbConnection = Merge.getDBConnection();

				HikariMerge Mergepool = HikariMerge.getInstance();
				dbConnection = Mergepool.getConnection();
				selectSQL = "SELECT SEQ_NO,LOC_CODE,DEPT_CODE,DATE,TIME,PERMIT1_NO"
						+ ",PERMIT2_NO,TX_REG_NO_1,TX_NO_1,TX_DEPT_CODE_1,TX_SALE_1,TX_REG_NO_2"
						+ ",TX_NO_2,TX_DEPT_CODE_2,TX_SALE_2,TX_REG_NO_3,TX_NO_3,TX_DEPT_CODE_3"
						+ ",TX_SALE_3,TX_REG_NO_4,TX_NO_4,TX_DEPT_CODE_4,TX_SALE_4,TX_REG_NO_5"
						+ ",TX_NO_5,TX_DEPT_CODE_5,TX_SALE_5,TX_REG_NO_6,TX_NO_6,TX_DEPT_CODE_6"
						+ ",TX_SALE_6,TX_REG_NO_7,TX_NO_7,TX_DEPT_CODE_7,TX_SALE_7,TX_REG_NO_8"
						+ ",TX_NO_8,TX_DEPT_CODE_8,TX_SALE_8,TX_REG_NO_9,TX_NO_9,TX_DEPT_CODE_9"
						+ ",TX_SALE_9,TX_REG_NO_10,TX_NO_10,TX_DEPT_CODE_10,TX_SALE_10,"
						+ "TX_SALE,VIP_NO,VIP_CARD_TYPE,VIP_SALE,ISSUED_BY,APPROVED_BY,VIP_APPROVED_BY,MODIFIED_BY,"
						+ "MODIFIED_DATE,MSREPL_SYNCTRAN_TS,PERMIT3_NO,PERMIT4_NO,PERMIT5_NO,ISSUE_YEAR,SP_VIP,PURPOSE,"
						+ "ROWGUID,TX_LOC_CODE_1,TX_LOC_CODE_2,TX_LOC_CODE_3,TX_LOC_CODE_4,TX_LOC_CODE_5,TX_LOC_CODE_6,"
						+ "TX_LOC_CODE_7,TX_LOC_CODE_8,TX_LOC_CODE_9,TX_LOC_CODE_10,LAST_UPD_DT "
						+ "from PARK_PMT "
						+ "where SEQ_NO ='"
						+ seqno
						+ "'"
						+ "and LOC_CODE ='"
						+ loccode
						+ "'"
						+ "and DATE='"
						+ date + "'" + "and TIME ='" + time + "'"
						+"Order BY LAST_UPD_DT";
			}

			preparedStatement = dbConnection.prepareStatement(selectSQL);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {

				// Parkpmt Parkpmt = new Parkpmt();

				String rsseqno = rs.getString("SEQ_NO");
				String rsloccode = rs.getString("LOC_CODE");
				String rsdeptcode = rs.getString("DEPT_CODE");

				String rsdate;
				if (Objects.equals(database, "Oracle")) {
					rsdate = rs.getString("DATE_");
				} else {
					rsdate = rs.getString("DATE");
				}

				String rstime = rs.getString("TIME");
				String rspermit1no = rs.getString("PERMIT1_NO");
				String rspermit2no = rs.getString("PERMIT2_NO");
				String rstxregno1 = rs.getString("TX_REG_NO_1");
				String rstxno1 = rs.getString("TX_NO_1");
				String rstxdeptcode1 = rs.getString("TX_DEPT_CODE_1");
				String rstxsale1 = rs.getString("TX_SALE_1");
				String rstxregno2 = rs.getString("TX_REG_NO_2");
				String rstxno2 = rs.getString("TX_NO_2");
				String rstxdeptcode2 = rs.getString("TX_DEPT_CODE_2");
				String rstxsale2 = rs.getString("TX_SALE_2");
				String rstxregno3 = rs.getString("TX_REG_NO_3");
				String rstxno3 = rs.getString("TX_NO_3");
				String rstxdeptcode3 = rs.getString("TX_DEPT_CODE_3");
				String rstxsale3 = rs.getString("TX_SALE_3");
				String rstxregno4 = rs.getString("TX_REG_NO_4");
				String rstxno4 = rs.getString("TX_NO_4");
				String rstxdeptcode4 = rs.getString("TX_DEPT_CODE_4");
				String rstxsale4 = rs.getString("TX_SALE_4");
				String rstxregno5 = rs.getString("TX_REG_NO_5");
				String rstxno5 = rs.getString("TX_NO_5");
				String rstxdeptcode5 = rs.getString("TX_DEPT_CODE_5");
				String rstxsale5 = rs.getString("TX_SALE_5");
				String rstxregno6 = rs.getString("TX_REG_NO_6");
				String rstxno6 = rs.getString("TX_NO_6");
				String rstxdeptcode6 = rs.getString("TX_DEPT_CODE_6");
				String rstxsale6 = rs.getString("TX_SALE_6");
				String rstxregno7 = rs.getString("TX_REG_NO_7");
				String rstxno7 = rs.getString("TX_NO_7");
				String rstxdeptcode7 = rs.getString("TX_DEPT_CODE_7");
				String rstxsale7 = rs.getString("TX_SALE_7");
				String rstxregno8 = rs.getString("TX_REG_NO_8");
				String rstxno8 = rs.getString("TX_NO_8");
				String rstxdeptcode8 = rs.getString("TX_DEPT_CODE_8");
				String rstxsale8 = rs.getString("TX_SALE_8");
				String rstxregno9 = rs.getString("TX_REG_NO_9");
				String rstxno9 = rs.getString("TX_NO_9");
				String rstxdeptcode9 = rs.getString("TX_DEPT_CODE_9");
				String rstxsale9 = rs.getString("TX_SALE_9");
				String rstxregno10 = rs.getString("TX_REG_NO_10");
				String rstxno10 = rs.getString("TX_NO_10");
				String rstxdeptcode10 = rs.getString("TX_DEPT_CODE_10");
				String rstxsale10 = rs.getString("TX_SALE_10");
				String rstxsale = rs.getString("TX_SALE");
				String rsvipno = rs.getString("VIP_NO");
				String rsvipcardtype = rs.getString("VIP_CARD_TYPE");
				String rsvipsale = rs.getString("VIP_SALE");
				String rsissuedby = rs.getString("ISSUED_BY");
				String rsapprovedby = rs.getString("APPROVED_BY");
				String rsvipapprovedby = rs.getString("VIP_APPROVED_BY");
				String rsmodifiedby = rs.getString("MODIFIED_BY");
				String rsmodifieddate = rs.getString("MODIFIED_DATE");
				String rsmsreplsynctrants = rs.getString("MSREPL_SYNCTRAN_TS");
				String rspermit3no = rs.getString("PERMIT3_NO");
				String rspermit4no = rs.getString("PERMIT4_NO");
				String rspermit5no = rs.getString("PERMIT5_NO");
				String rsissueyear = rs.getString("ISSUE_YEAR");
				String rsspvip = rs.getString("SP_VIP");
				String rspurpose = rs.getString("PURPOSE");
				String rsrowguid = rs.getString("ROWGUID");
				String rstxloccode1 = rs.getString("TX_LOC_CODE_1");
				String rstxloccode2 = rs.getString("TX_LOC_CODE_2");
				String rstxloccode3 = rs.getString("TX_LOC_CODE_3");
				String rstxloccode4 = rs.getString("TX_LOC_CODE_4");
				String rstxloccode5 = rs.getString("TX_LOC_CODE_5");
				String rstxloccode6 = rs.getString("TX_LOC_CODE_6");
				String rstxloccode7 = rs.getString("TX_LOC_CODE_7");
				String rstxloccode8 = rs.getString("TX_LOC_CODE_8");
				String rstxloccode9 = rs.getString("TX_LOC_CODE_9");
				String rstxloccode10 = rs.getString("TX_LOC_CODE_10");
				Timestamp rslastupddt = rs.getTimestamp("LAST_UPD_DT");

				boolean Chkresult = InsertParkpmt.Parkpmtchkexists(entitykey,
						database);

				// logger.info(Chkresult);

				if (!Chkresult) {

					// logger.info("insert");

					boolean Insertresult = InsertParkpmt.Parkpmtinsert(rsseqno,
							rsloccode, rsdeptcode, rsdate, rstime, rspermit1no,
							rspermit2no, rstxregno1, rstxno1, rstxdeptcode1,
							rstxsale1, rstxregno2, rstxno2, rstxdeptcode2,
							rstxsale2, rstxregno3, rstxno3, rstxdeptcode3,
							rstxsale3, rstxregno4, rstxno4, rstxdeptcode4,
							rstxsale4, rstxregno5, rstxno5, rstxdeptcode5,
							rstxsale5, rstxregno6, rstxno6, rstxdeptcode6,
							rstxsale6, rstxregno7, rstxno7, rstxdeptcode7,
							rstxsale7, rstxregno8, rstxno8, rstxdeptcode8,
							rstxsale8, rstxregno9, rstxno9, rstxdeptcode9,
							rstxsale9, rstxregno10, rstxno10, rstxdeptcode10,
							rstxsale10, rstxsale, rsvipno, rsvipcardtype,
							rsvipsale, rsissuedby, rsapprovedby,
							rsvipapprovedby, rsmodifiedby, rsmodifieddate,
							rsmsreplsynctrants, rspermit3no, rspermit4no,
							rspermit5no, rsissueyear, rsspvip, rspurpose,
							rsrowguid, rstxloccode1, rstxloccode2,
							rstxloccode3, rstxloccode4, rstxloccode5,
							rstxloccode6, rstxloccode7, rstxloccode8,
							rstxloccode9, rstxloccode10, rslastupddt, database);

					if (Insertresult) {
						logger.info("Parkpmt: 1 row has been inserted. Key:"
								+ seqno + "," + loccode + "," + date + "'"
								+ time);
					} else {
						logger.info("Insert Error");
					}
					Logupdateresult.Updatelogresult(dataupdatelog, entityname,
							Insertresult, database);

					/*if ((!"Oracle".equals(database)) && (Insertresult)) {
						Insertdataupdatelog.Updatelogresult(entityname, entitykey);
					}*/

				} else {

					// logger.info("update");

					boolean Insertresult = InsertParkpmt.Parkpmtupdate(rsseqno,
							rsloccode, rsdeptcode, rsdate, rstime, rspermit1no,
							rspermit2no, rstxregno1, rstxno1, rstxdeptcode1,
							rstxsale1, rstxregno2, rstxno2, rstxdeptcode2,
							rstxsale2, rstxregno3, rstxno3, rstxdeptcode3,
							rstxsale3, rstxregno4, rstxno4, rstxdeptcode4,
							rstxsale4, rstxregno5, rstxno5, rstxdeptcode5,
							rstxsale5, rstxregno6, rstxno6, rstxdeptcode6,
							rstxsale6, rstxregno7, rstxno7, rstxdeptcode7,
							rstxsale7, rstxregno8, rstxno8, rstxdeptcode8,
							rstxsale8, rstxregno9, rstxno9, rstxdeptcode9,
							rstxsale9, rstxregno10, rstxno10, rstxdeptcode10,
							rstxsale10, rstxsale, rsvipno, rsvipcardtype,
							rsvipsale, rsissuedby, rsapprovedby,
							rsvipapprovedby, rsmodifiedby, rsmodifieddate,
							rsmsreplsynctrants, rspermit3no, rspermit4no,
							rspermit5no, rsissueyear, rsspvip, rspurpose,
							rsrowguid, rstxloccode1, rstxloccode2,
							rstxloccode3, rstxloccode4, rstxloccode5,
							rstxloccode6, rstxloccode7, rstxloccode8,
							rstxloccode9, rstxloccode10, rslastupddt, database);

					if (Insertresult) {
						logger.info("Parkpmt: 1 row has been updated. Key:"
								+ seqno + "," + loccode + "," + date + "'"
								+ time);
					} else {
						logger.info("Update Error");
					}
					Logupdateresult.Updatelogresult(dataupdatelog, entityname,
							Insertresult, database);

					/*if ((!"Oracle".equals(database)) && (Insertresult)) {
						Insertdataupdatelog.Updatelogresult(entityname, entitykey);
					}*/
				}
			}
		} catch (SQLException e) {

			logger.info(e.getMessage());

		} finally {

			if (preparedStatement != null) {
				preparedStatement.close();
			}

			if (dbConnection != null) {
				dbConnection.close();
			}
		}
	}

}
