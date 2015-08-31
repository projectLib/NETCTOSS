package com.chenqf.entity.page;

import java.io.Serializable;

/** ºÃ≥–Page¿‡ */
public class ServicePage extends Page implements Serializable {

	private static final long serialVersionUID = 1L;
	

	private String osUserName;
	private String unixHost;
	private String idcardNo;
	private String status;
	
	public String getOsUserName() {
		return osUserName;
	}
	public void setOsUserName(String osUserName) {
		this.osUserName = osUserName;
	}
	public String getUnixHost() {
		return unixHost;
	}
	public void setUnixHost(String unixHost) {
		this.unixHost = unixHost;
	}
	public String getIdcardNo() {
		return idcardNo;
	}
	public void setIdcardNo(String idcardNo) {
		this.idcardNo = idcardNo;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
