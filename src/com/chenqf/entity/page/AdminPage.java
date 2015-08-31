package com.chenqf.entity.page;

import java.io.Serializable;

/** ºÃ≥–Page¿‡ */
public class AdminPage extends Page implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String roleName;
	
	private Integer moduleId;

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setModuleId(Integer moduleId) {
		this.moduleId = moduleId;
	}

	public Integer getModuleId() {
		return moduleId;
	}

}
