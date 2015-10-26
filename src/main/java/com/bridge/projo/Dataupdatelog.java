package com.bridge.projo;

public class Dataupdatelog {

	// public static final char[] getDatalogid = null;
	private String datalogid;
	private String entityname;
	private String entitykey;
	private String entityupddt;
	private String logdt;
	private static String batchno;
	private String iscomp;
	private String remark;

	public String getDatalogid() {
		return datalogid;
	}

	public void setDatalogid(String datalogid) {
		this.datalogid = datalogid;
	}

	public String getEntityname() {
		return entityname;
	}

	public void setEntityname(String entityname) {
		this.entityname = entityname;
	}

	public String getEntitykey() {
		return entitykey;
	}

	public void setEntitykey(String entitykey) {
		this.entitykey = entitykey;
	}

	public String getEntityupddt() {
		return entityupddt;
	}

	public void setEntityupddt(String entityupddt) {
		this.entityupddt = entityupddt;
	}

	public String getLogdt() {
		return logdt;
	}

	public void setLogdt(String logdt) {
		this.logdt = logdt;
	}

	public static String getBatchno() {
		return batchno;
	}

	public void setBatchno(String batchno) {
		this.batchno = batchno;
	}

	public String getIscomp() {
		return iscomp;
	}

	public void setIscomp(String iscomp) {
		this.iscomp = iscomp;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return ("datalogid:" + this.getDatalogid() + " entityname: "
				+ this.getEntityname() + " entitykey: " + this.getEntitykey()
				+ " entityupddt: " + this.getEntityupddt() + " logdt: "
				+ this.getLogdt() + " batchno: " + this.getBatchno()
				+ " iscomp: " + this.getIscomp() + " remark: " + this
					.getRemark());
	}

}
