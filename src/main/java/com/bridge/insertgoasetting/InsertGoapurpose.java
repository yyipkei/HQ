package com.bridge.insertgoasetting;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Objects;

//import org.apache.log4j.Logger;

import com.bridge.main.HikariMssql;
import com.bridge.main.HikariQracleTo;
import org.apache.log4j.Logger;

public class InsertGoapurpose {

	private static final Logger logger = Logger
			.getLogger(InsertGoapurpose.class);

	public static boolean Goapurposeinsert(String rspurposecde,
			String rspurposedesc, String rssortseq, String rsefffrdate,
			String rsefftodate, String rsrecvtype, String rslimit,
			String rslimitfromdate, String rslimittodate, String rsreleaseflag,
			String rscompreq, String rswriteoffreq, String rslimitgrp,
			Timestamp rslastupddt, String rsentitykey, String frdatabase)
			throws SQLException {

		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		String insertTableSQL;

		insertTableSQL = "INSERT INTO GOA_PURPOSE (PURPOSE_CDE,PURPOSE_DESC,SORT_SEQ,EFF_FR_DATE,EFF_TO_DATE,RECV_TYPE,LIMIT,LIMIT_FROM_DATE,LIMIT_TO_DATE,RELEASE_FLAG,COMP_REQ,WRITE_OFF_REQ,LIMIT_GRP,LAST_UPD_DT,ENTITY_KEY) "
				+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

		try {

			if (Objects.equals(frdatabase, "Oracle")) {
				// dbConnection = Mssql.getDBConnection();

				HikariMssql Mssqlpool = HikariMssql.getInstance();
				dbConnection = Mssqlpool.getConnection();
			} else {
				// dbConnection = OracleTo.getDBConnection();

				HikariQracleTo OrcaleFromTo = HikariQracleTo.getInstance();
				dbConnection = OrcaleFromTo.getConnection();
			}

			preparedStatement = dbConnection.prepareStatement(insertTableSQL);

			preparedStatement.setString(1, rspurposecde);
			preparedStatement.setString(2, rspurposedesc);
			preparedStatement.setString(3, rssortseq);
			preparedStatement.setString(4, rsefffrdate);
			preparedStatement.setString(5, rsefftodate);
			preparedStatement.setString(6, rsrecvtype);
			preparedStatement.setString(7, rslimit);
			preparedStatement.setString(8, rslimitfromdate);
			preparedStatement.setString(9, rslimittodate);
			preparedStatement.setString(10, rsreleaseflag);
			preparedStatement.setString(11, rscompreq);
			preparedStatement.setString(12, rswriteoffreq);
			preparedStatement.setString(13, rslimitgrp);
			preparedStatement.setTimestamp(14, rslastupddt);
			preparedStatement.setString(15, rsentitykey);

			preparedStatement.executeUpdate();

			return true;

		} catch (SQLException e) {

			logger.info(e.getMessage());

			return false;

		} finally {

			if (preparedStatement != null) {
				preparedStatement.close();
			}

			if (dbConnection != null) {
				dbConnection.close();
			}
		}

	}

	public static boolean Goapurposeupdate(String rspurposecde,
			String rspurposedesc, String rssortseq, String rsefffrdate,
			String rsefftodate, String rsrecvtype, String rslimit,
			String rslimitfromdate, String rslimittodate, String rsreleaseflag,
			String rscompreq, String rswriteoffreq, String rslimitgrp,
			Timestamp rslastupddt, String rsentitykey, String frdatabase)
			throws SQLException {

		boolean result = true;
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		String updateSQL;

		updateSQL = "UPDATE GOA_PURPOSE " + "SET PURPOSE_CDE   = ? "
				+ ", PURPOSE_DESC    = ? " + ", SORT_SEQ        = ? "
				+ ", EFF_FR_DATE     = ? " + ", EFF_TO_DATE     = ? "
				+ ", RECV_TYPE       = ? " + ", LIMIT           = ? "
				+ ", LIMIT_FROM_DATE = ? " + ", LIMIT_TO_DATE   = ? "
				+ ", RELEASE_FLAG    = ? " + ", COMP_REQ        = ? "
				+ ", WRITE_OFF_REQ   = ? " + ", LIMIT_GRP       = ? "
				+ ", LAST_UPD_DT     = ? " + "WHERE  ENTITY_KEY      = ? ";

		try {

			if (Objects.equals(frdatabase, "Oracle")) {
				// dbConnection = Mssql.getDBConnection();

				HikariMssql Mssqlpool = HikariMssql.getInstance();
				dbConnection = Mssqlpool.getConnection();
			} else {
				// dbConnection = OracleTo.getDBConnection();

				HikariQracleTo OrcaleFromTo = HikariQracleTo.getInstance();
				dbConnection = OrcaleFromTo.getConnection();
			}

			preparedStatement = dbConnection.prepareStatement(updateSQL);

			preparedStatement.setString(1, rspurposecde);
			preparedStatement.setString(2, rspurposedesc);
			preparedStatement.setString(3, rssortseq);
			preparedStatement.setString(4, rsefffrdate);
			preparedStatement.setString(5, rsefftodate);
			preparedStatement.setString(6, rsrecvtype);
			preparedStatement.setString(7, rslimit);
			preparedStatement.setString(8, rslimitfromdate);
			preparedStatement.setString(9, rslimittodate);
			preparedStatement.setString(10, rsreleaseflag);
			preparedStatement.setString(11, rscompreq);
			preparedStatement.setString(12, rswriteoffreq);
			preparedStatement.setString(13, rslimitgrp);
			preparedStatement.setTimestamp(14, rslastupddt);
			preparedStatement.setString(15, rsentitykey);

			preparedStatement.executeUpdate();

			return result;

		} catch (SQLException e) {

			logger.info(e.getMessage());
			result = false;

			logger.info(false);

			return result;

		} finally {

			if (preparedStatement != null) {
				preparedStatement.close();
			}

			if (dbConnection != null) {
				dbConnection.close();
			}

		}

	}

	public static boolean Goapurposechkexists(String entitykey,
			String frdatabase) throws SQLException {
		/*
		 * String[] parts = entitykey.split(","); String txdate = parts[0];
		 * String loccode = parts[1]; String regno = parts[2]; String txno =
		 * parts[3]; String seqno = parts[4];
		 */
		boolean result = false;
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		try {

			if (Objects.equals(frdatabase, "Oracle")) {
				// dbConnection = Mssql.getDBConnection();

				HikariMssql Mssqlpool = HikariMssql.getInstance();
				dbConnection = Mssqlpool.getConnection();
			} else {
				// dbConnection = OracleTo.getDBConnection();

				HikariQracleTo OrcaleFromTo = HikariQracleTo.getInstance();
				dbConnection = OrcaleFromTo.getConnection();
			}

			String selectSQL = "SELECT PURPOSE_CDE " + "FROM GOA_PURPOSE "
					+ "where ENTITY_KEY ='" + entitykey + "'";

			// logger.info(selectSQL);

			preparedStatement = dbConnection.prepareStatement(selectSQL);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				String rschktxdate = rs.getString("PURPOSE_CDE");

				// logger.info(rschktxdate);

				if (rschktxdate != null) {
					result = true;// not exists
				}
			}
			return result;
		} catch (SQLException e) {

			logger.info(e.getMessage());
			result = false;

			return result;

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
