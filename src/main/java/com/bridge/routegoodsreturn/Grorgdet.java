package com.bridge.routegoodsreturn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Objects;

import com.bridge.result.Insertdataupdatelog;
import org.apache.log4j.Logger;

import com.bridge.insertgoodsreturn.InsertGrorgdet;
import com.bridge.main.HikariMerge;
import com.bridge.main.HikariQracleFrom;
import com.bridge.result.Logupdateresult;

public class Grorgdet {

	private static final Logger logger = Logger.getLogger(Grorgdet.class);

	public static void routeGrorgdet(String dataupdatelog, String entityname,
			String entitykey, String database) throws SQLException {

		String[] parts = entitykey.split(",");
		String txdate = parts[0];
		String loccode = parts[1];
		String regno = parts[2];
		String txno = parts[3];
		String seqno = parts[4];

		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		String selectSQL = "SELECT TX_DATE,LOC_CODE,REG_NO,TX_NO,SEQ_NO,"
				+ "VOID,QTY,ORG_TX_DATE,ORG_LOC_CODE,ORG_REG_NO,ORG_TX_NO,"
				+ "ORG_SEQ_NO,VALIDATED,NET_SALE,NET_DISC,ROWGUID,PURPOSE,VIP_NO,VIP_NAME,LAST_UPD_DT from GR_ORG_DET "
				+ "where TX_DATE ='" + txdate + "'" + "and LOC_CODE ='"
				+ loccode + "'" + "and reg_no='" + regno + "'" + "and tx_no ='"
				+ txno + "'" + "and seq_no ='" + seqno + "'"
				+"Order BY LAST_UPD_DT";

		// List<Grorgdet> Grorgdets = new ArrayList<Grorgdet>();

		try {
			if (Objects.equals(database, "Oracle")) {
				HikariQracleFrom OrcaleFrompool = HikariQracleFrom
						.getInstance();
				dbConnection = OrcaleFrompool.getConnection();
			} else {
				HikariMerge Mergepool = HikariMerge.getInstance();
				dbConnection = Mergepool.getConnection();
			}

			preparedStatement = dbConnection.prepareStatement(selectSQL);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {

				// Grorgdet Grorgdet = new Grorgdet();

				String rstxdate = rs.getString("TX_DATE");
				String rsloccode = rs.getString("LOC_CODE");
				String rsregno = rs.getString("REG_NO");
				String rstxno = rs.getString("TX_NO");
				String rsseqno = rs.getString("SEQ_NO");
				String rsvoid = rs.getString("VOID");
				String rsqty = rs.getString("QTY");
				String rsorgtxdate = rs.getString("ORG_TX_DATE");
				String rsorgloccode = rs.getString("ORG_LOC_CODE");
				String rsorgregno = rs.getString("ORG_REG_NO");
				String rsorgtxno = rs.getString("ORG_TX_NO");
				String rsorgseqno = rs.getString("ORG_SEQ_NO");
				String rsvalidated = rs.getString("VALIDATED");
				String rsnetsale = rs.getString("NET_SALE");
				String rsnetdisc = rs.getString("NET_DISC");
				String rsrowguid = rs.getString("ROWGUID");
				String rspurpose = rs.getString("PURPOSE");
				String rsvipno = rs.getString("VIP_NO");
				String rsvipname = rs.getString("VIP_NAME");
				Timestamp rslastupddt = rs.getTimestamp("LAST_UPD_DT");

				boolean Chkresult = InsertGrorgdet.Grorgdetchkexists(entitykey,
						database);

				// logger.info(Chkresult);

				if (!Chkresult) {

					// logger.info("insert");

					boolean Insertresult = InsertGrorgdet.Grorgdetinsert(
							rstxdate, rsloccode, rsregno, rstxno, rsseqno,
							rsvoid, rsqty, rsorgtxdate, rsorgloccode,
							rsorgregno, rsorgtxno, rsorgseqno, rsvalidated,
							rsnetsale, rsnetdisc, rsrowguid, rspurpose,
							rsvipno, rsvipname, rslastupddt, database);

					if (Insertresult) {
						logger.info("Grorgdet: 1 row has been inserted. Key:"
								+ rstxdate + "," + rsloccode + "," + rsregno
								+ "'" + rstxno + "'" + rsseqno);
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

					boolean Insertresult = InsertGrorgdet.Grorgdetupdate(
							rstxdate, rsloccode, rsregno, rstxno, rsseqno,
							rsvoid, rsqty, rsorgtxdate, rsorgloccode,
							rsorgregno, rsorgtxno, rsorgseqno, rsvalidated,
							rsnetsale, rsnetdisc, rsrowguid, rspurpose,
							rsvipno, rsvipname, rslastupddt, database);

					if (Insertresult) {
						logger.info("Grorgdet: 1 row has been updated. Key:"
								+ rstxdate + "," + rsloccode + "," + rsregno
								+ "'" + rstxno + "'" + rsseqno);
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
